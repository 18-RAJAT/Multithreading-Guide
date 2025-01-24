import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount
{
    private int balance=5000;
    private final Lock lock=new ReentrantLock();  //ReentrantLock advanced and flexible thread synchronization
    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is attempting to withdraw " + amount);
        try
        {
//            lock.lock();//wait
            if(amount<=0)
            {
                System.out.println(Thread.currentThread().getName() + " invalid withdrawal amount: " + amount);
                return;
            }
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS))
            {
                // Attempt to acquire the lock
                try
                {
                    if(balance>=amount)
                    {
                        System.out.println(Thread.currentThread().getName() + " is processing the withdrawal...");
                        Thread.sleep(5000); // Simulate processing time
                        balance-=amount;
                        System.out.println(Thread.currentThread().getName() + " completed the transaction. Remaining balance: " + balance);
                    }
                    else
                    {
                        System.out.println(Thread.currentThread().getName() + " insufficient balance for withdrawal.");
                    }
                }catch(Exception e)
                {
                    Thread.currentThread().interrupt();
                }
                finally
                {
                    lock.unlock(); // Ensure the lock is released
                }
            }
            else
            {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock within the timeout.");
            }
        }catch(InterruptedException e)
        {
            throw new RuntimeException("Thread was interrupted", e);
        }
    }
}
