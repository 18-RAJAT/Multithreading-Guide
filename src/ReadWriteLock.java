import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock=lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock=lock.writeLock();
    private String sharedResource="Initial Value";

    // Method to read the shared resource
    public String read()
    {
        readLock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + " is reading.");
            Thread.sleep(1000);
            return sharedResource;
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }finally
        {
            System.out.println(Thread.currentThread().getName() + " finished reading.");
            readLock.unlock();
        }
    }
    public void write(String value)
    {
        writeLock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + " is writing.");
            Thread.sleep(1000);
            sharedResource=value;
            System.out.println(Thread.currentThread().getName() + " wrote: " + value);
        }catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }finally
        {
            System.out.println(Thread.currentThread().getName() + " finished writing.");
            writeLock.unlock();
        }
    }
    public static void main(String[] args)
    {
        ReadWriteLock ex=new ReadWriteLock();

        Thread reader1=new Thread(()->System.out.println("Reader1 read: " + ex.read()), "Reader1");
        Thread reader2=new Thread(()->System.out.println("Reader2 read: " + ex.read()), "Reader2");

        Thread writer=new Thread(()->ex.write("Updated Value"), "Writer");

        reader1.start();
        reader2.start();
        writer.start();

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
