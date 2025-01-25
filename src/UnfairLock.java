import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnfairLock {

    private final Lock lock=new ReentrantLock();

    public void accessResource()
    {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is acquiring lock");
            Thread.sleep(2000);
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }finally
        {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " is leaving lock");
        }
    }
    public static void main(String[] args)
    {
        UnfairLock ex=new UnfairLock();
        //runnable object
        Runnable task=new Runnable()
        {
            @Override
            public void run()
            {
                ex.accessResource();
            }
        };
        Thread t1=new Thread(task,"Thread1");
        Thread t2=new Thread(task,"Thread2");
        Thread t3=new Thread(task,"Thread3");

        t1.start();
        t2.start();
        t3.start();
    }
}
