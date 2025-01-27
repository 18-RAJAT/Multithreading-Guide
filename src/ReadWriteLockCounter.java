import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {

    private int count=0;
    private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock=lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock=lock.writeLock();

    // Increment the counter
    public void increment()
    {
        writeLock.lock();
        try
        {
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented count to " + count);
        }finally
        {
            writeLock.unlock();
        }
    }

    // Get the current counter value
    public void getCount()
    {
        readLock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + " read count as " + count);
        }finally
        {
            readLock.unlock();
        }
    }

    public static void main(String[] args)
    {
        ReadWriteLockCounter counter=new ReadWriteLockCounter();
        // Create and start threads to demonstrate concurrent access
        Thread reader1=new Thread(()->
        {
            for(int i=0;i<5;++i)
            {
                counter.getCount();
                try
                {
                    Thread.sleep(100);
                }catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        },"Reader1");
        Thread reader2=new Thread(()->
        {
            for(int i=0;i<5;++i)
            {
                counter.getCount();
                try
                {
                    Thread.sleep(150);
                }catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        },"Reader2");
        Thread writer=new Thread(()->
        {
            for(int i=0;i<5;++i)
            {
                counter.increment();
                try
                {
                    Thread.sleep(200);
                }catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        },"Writer");

        reader1.start();
        reader2.start();
        writer.start();
        //Wait for threads to finish
        try
        {
            reader1.join();
            reader2.join();
            writer.join();
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
