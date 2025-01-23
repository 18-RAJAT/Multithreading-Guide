public class Process extends Thread {
    private final String threadName;
    public Process(String name)
    {
        this.threadName=name;
    }
    @Override
    public void run()
    {
        System.out.println(threadName+" - Running Process");
        try
        {
            Thread.sleep(5000);
        }catch(InterruptedException e)
        {
            System.out.println((threadName+" - Process interrupted"));
        }
    }
    public static void main(String[] args) throws InterruptedException
    {
        //multiple thread
        Process p1=new Process("Thread 1");
        Process p2=new Process("Thread 2");
        Process p3=new Process("Thread 3");

        //starting with threads
        System.out.println("Starting " + p1.getName()+" - "+p1.getState());
        p1.start();

        System.out.println("Starting " + p2.getName()+" - "+p2.getState());
        p2.start();

        System.out.println("Starting " + p3.getName()+" - "+p3.getState());
        p3.start();

        //thread status
        System.out.println("Thread1 status "+p1.getState());
        System.out.println("Thread2 status "+p2.getState());
        System.out.println("Thread3 status "+p3.getState());

        //eg one thread is interrupt
        p2.interrupt();
        Process.sleep(5000);

        //check state after interrupt
        System.out.println("Thread1 status "+p1.getState());
        System.out.println("Thread2 status "+p2.getState());
        System.out.println("Thread3 status "+p3.getState());

        //wait for thread to finish
        p1.join();
        p2.join();
        p3.join();

        //final state thread
        System.out.println("Thread1 status "+p1.getState());
        System.out.println("Thread2 status "+p2.getState());
        System.out.println("Thread3 status "+p3.getState());
    }
}
