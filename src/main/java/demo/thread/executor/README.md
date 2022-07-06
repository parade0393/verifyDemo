### 线程池相关
1. Executors.newSingleThreadExecutor() --线程数固定为1
    使用场景:多个任务串行-使用了误解堵塞队列
   区别:
   1. 自己创建单线程执行串行任务，如果任务执行失败而终止，则没有任何补救措施，而线程池还回创建一个新线程，保证池的正常工作，对外只暴漏了`ExecutorService`接口
   2. Executors.newFixedThreadPool(1)和newSingleThreadExecutor功能类似，只是fixe的可以修改核心线程个数，对外暴露了`ThreadPoolExecutor`实现类
    
2. 关闭线程池
    1. shutDown，只关闭空闲线程
    2. shutDownNow，把所有线程都关闭，返回没有执行的让任务
    3. isShutDown 不再running的状态都回返回true
    4. isTerminated //线程池已经停止工作了
    5. awaitTermination//调用shutDown后，由于调用线程并不会等待所有任务运行结束，因此如果它想在线程池terminate后做些事情，可以利用此方法等待