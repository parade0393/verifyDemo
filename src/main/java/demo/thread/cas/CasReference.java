package demo.thread.cas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference 感知不到其他线程对变量的修改
 * 典型的ABA问题。其他线程把A修改未B再修改成A，这个时候主线程只是知道变量的值没有改变，但是并不知道其他线程修改过了变量
 */
public class CasReference {

    public static void main(String[] args) {
        DecimalAccount cas = new DecimalAccountCas(new BigDecimal("10000"));
        DecimalAccount.demo(cas);
    }

}

class DecimalAccountCas implements DecimalAccount{

    private AtomicReference<BigDecimal> balance;

    public DecimalAccountCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withDraw(BigDecimal amount) {
        while (true){
            BigDecimal pre = balance.get();
            BigDecimal next = pre.subtract(amount);
            if (balance.compareAndSet(pre,next)) {
                break;
            }
        }


    }
}

interface DecimalAccount{
    BigDecimal getBalance();
    void withDraw(BigDecimal bigDecimal);

    static void demo(DecimalAccount account){
        ArrayList<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withDraw(BigDecimal.TEN);
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.getBalance());
    }
}
