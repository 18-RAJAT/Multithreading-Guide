class Resource
{
    // Method to acquire lock on current and another resource
    void methodA(Resource r)
    {
        // Locking the current resource
        synchronized(this)
        {
            System.out.println(Thread.currentThread().getName() + " locked " + this);
            try
            {
                Thread.sleep(100);
            }catch(InterruptedException e){}

            // Trying to lock another resource
            synchronized(r)
            {
                System.out.println(Thread.currentThread().getName() + " locked " + r);
            }
        }
    }
}

public class JavaDeadlock
{
    public static void main(String[] args)
    {
        Resource r1=new Resource();
        Resource r2=new Resource();

        // Thread-1 locks r1 first, then tries to lock r2
        Thread t1=new Thread(new Runnable()
        {
            public void run()
            {
                r1.methodA(r2);// Acquiring lock on r1, then trying for r2
            }
        },"Thread-1");

        // Thread-2 locks r2 first, then tries to lock r1
        Thread t2=new Thread(new Runnable()
        {
            public void run()
            {
                r2.methodA(r1);// Acquiring lock on r2, then trying for r1
            }
        },"Thread-2");
        t1.start();
        t2.start();
    }
}


/*
    How Deadlock Happens Step by Step
    Thread-1 locks r1, then tries to lock r2 (which is locked by Thread-2).
    Thread-2 locks r2, then tries to lock r1 (which is locked by Thread-1).
    Both threads keep waiting for each other to release the lock, which never happens. Deadlock!


    How to Fix Deadlock?
    Always lock resources in the same order.
    Use tryLock() with a timeout in ReentrantLock (from java.util.concurrent.locks).
    Avoid nested locks when possible.


    Time 	Thread-1 (Locks r1)	|  Thread-2 (Locks r2)
    T1	    Locked r1	        |  Locked r2
    T2	    Waiting for r2	    |  Waiting for r1
    T3	    Stuck (Deadlock)	|  Stuck (Deadlock)

*/