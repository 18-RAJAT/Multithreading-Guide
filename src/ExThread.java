public class ExThread extends Thread {
    @Override
    public void run()
    {
        System.out.println("Running Thread");
        try
        {
            Thread.sleep(5000);
        }catch(InterruptedException e)
        {
            System.out.println("Thread interrupted");
        }
    }
    public static void main(String[] args) throws  InterruptedException
    {
        ExThread thread=new ExThread();
        ExThread thread1=new ExThread();
        ExThread thread2=new ExThread();
        System.out.println("Starting Thread "+thread.getState());
        System.out.println("Starting Thread1 "+thread1.getState());
        System.out.println("Starting Thread2 "+thread2.getState());
        thread.start();
        thread1.start();
        thread2.start();
        System.out.println("Thread "+thread.getState());
        System.out.println("Thread1 "+thread1.getState());
        System.out.println("Thread2 "+thread2.getState());
        Thread.sleep(5000);
        thread.join();
        System.out.println("Thread "+thread.getState());
        thread1.join();
        System.out.println("Thread1 "+thread1.getState());
        thread2.join();
        System.out.println("Thread2 "+thread2.getState());
    }
}