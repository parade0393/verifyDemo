package demo.thread.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "TestPool")
public class TestPool {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10, ((queue, task) -> {
            //1.死等
//            queue.put(task);
            //2)带超时等待
            queue.offer(task, 500, TimeUnit.MILLISECONDS);
            //3)放弃认任务执行
            log.debug("放弃执行");
            //4)抛出异常
//            throw new RuntimeException("任务执行失败"+task);
            //5)让调用者自己执行任务
            task.run();
        }));
        for (int i = 0; i < 15; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}", j);
            });
        }
    }
}

interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j(topic = "ThreadPool")
class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;//线程安全的

    //线程集合
    private HashSet<Worker> workers = new HashSet();

    //核心线程数
    private int coreSize;

    //任务超时时间
    private long timeout;
    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectpolicy;

    //执行任务
    public void execute(Runnable task) {
        //当任务数没超过coreSize时，直接交给Work对象执行
        //如果任务数超过coreSize，那么加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {

                Worker worker = new Worker(task);
                log.debug("新增worker{},{}", worker, task);
                worker.start();
                workers.add(worker);//加入线程
            } else {
//                taskQueue.put(task);
                //1)死等
                //2)带超时等待
                //3)放弃认任务执行
                //4)抛出异常
                //5)让调用者自己执行任务
                taskQueue.tryPut(rejectpolicy, task);
            }
        }
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapcity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
        this.rejectpolicy = rejectPolicy;
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //1)当task不为空，直接执行任务
            //2)当task执行完毕，再接着从任务队列获取任务并执行
//            while (task != null || (task = taskQueue.take()) !=null){
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.debug("正在执行..{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.debug("worker被移除{}", this);
                workers.remove(this);
            }
        }
    }
}

@Slf4j(topic = "BlockingQueue")
class BlockingQueue<T> {
    //1.任务队列 ArrayDeque再很多情况下比linkedList性能好一些
    private Deque<T> queue = new ArrayDeque<>();

    //2.锁
    private ReentrantLock lock = new ReentrantLock();

    //3.生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4.消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5.容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //带超时的阻塞获取
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            //将timeout统一转为为纳秒
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    //返回的是剩余的时间，
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();//获取队列头部元素
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();//获取队列头部元素
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() == capcity) {
                try {
                    log.debug("等待加入任务队列{}", element);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列{}", element);
            queue.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //带超时时间阻塞添加
    public boolean offer(T task, long timeOut, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeOut);
            while (queue.size() == capcity) {
                try {
                    log.debug("等待加入任务队列{}", task);
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列{}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    //获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            //队列是否已满
            if (queue.size() == capcity) {
                if (rejectPolicy != null) {
                    rejectPolicy.reject(this, task);
                }
            } else {//有空闲
                log.debug("加入任务队列{}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
