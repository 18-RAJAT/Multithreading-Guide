import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private final Lock lock=new ReentrantLock();

    /*
    *   Deadlock condition->>
    *   Because first the lock.lock of outer-method call then going to try block and try is printing outer-method-called
    *   then this will call inner-method and inner method is also says there is lock.lock then this will be the deadlock condition...
    *   and they are depending on each-other.
    *
    *
    *   and now that's why we are using here ReentrantLock to run the process of lock.lock and don't wait, basically this ReentrantLock is
    *   use how many times the lock is acquired.->[maintaining a count]
    * */
    public void outerMethod()
    {
        lock.lock();
        try
        {
            System.out.println("Outer Method called");
            innerMethod();
        }finally
        {
            lock.unlock();
        }
    }

    public void innerMethod()
    {
        lock.lock();
        try
        {
            System.out.println("Inner Method called");
        }finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args)
    {
        ReentrantLockTest test=new ReentrantLockTest();
        test.outerMethod();
    }
}
