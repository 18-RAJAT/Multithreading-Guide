import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
    private final Lock lock=new ReentrantLock(true); //Fair mode(true)
    //volatile ensures that changes to a variable are visible to all threads immediately and prevents instruction reordering for that variable.
    private volatile int turn=1; //To enforce strict order
    private static final int TOTAL_THREADS=4;

    public void accessResource(int threadId)
    {
        while(true)
        {
            lock.lock();
            try
            {
                if(turn==threadId)
                {
                    System.out.println("Thread" + threadId + " is acquiring lock");
                    Thread.sleep(1000);
                    System.out.println("Thread" + threadId + " is leaving lock");
                    turn=(threadId%TOTAL_THREADS)+1; // Next thread's turn
                    break;
                }
            }catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }finally
            {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args)
    {
//        FairLock ex=new FairLock();
//        Runnable task1=()->ex.accessResource(1);
//        Runnable task2=()->ex.accessResource(2);
//        Runnable task3=()->ex.accessResource(3);
//
//        Thread t1=new Thread(task1,"Thread1");
//        Thread t2=new Thread(task2,"Thread2");
//        Thread t3=new Thread(task3,"Thread3");
//        Thread t4=new Thread(task3,"Thread4");
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();

        FairLock ex=new FairLock();
        for(int i=1;i<=TOTAL_THREADS;++i)
        {
            final int threadId = i;
            Thread t=new Thread(()->ex.accessResource(threadId),"Thread"+threadId);
            t.start();
        }
    }
}
