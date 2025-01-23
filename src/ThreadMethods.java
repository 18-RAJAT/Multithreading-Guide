public class ThreadMethods extends Thread{

    //self thread name
    public ThreadMethods(String name)
    {
        super(name);
    }
    @Override
    public void run()
    {
//        System.out.println("Thread is running state");
//        for(int i=1;i<=3;++i)
//        {
//            System.out.println(Thread.currentThread().getName()+" - Priority"+Thread.currentThread().getPriority()+" - count="+i);
//            try
//            {
//                Thread.sleep(5000);
//            }catch(Exception e)
//            {
//                System.out.println(Thread.currentThread().getName()+" - Priority"+Thread.currentThread().getPriority()+" - count="+i);
//            }
//        }

        //Yield
//        for(int i=1;i<=3;++i)
//        {
//            System.out.println(Thread.currentThread().getName()+" is running.");
//
//            //scheduler
//            Thread.yield();
//        }


        //Daemon
        while(true)
        {
            System.out.println("Running Thread - inf time");
        }

    }
    public static void main(String[] args) throws InterruptedException {
//        ThreadMethods thread=new ThreadMethods("Rajat");
//        thread.setPriority(Thread.MAX_PRIORITY);
//        thread.start();

//        ThreadMethods LowPriority=new ThreadMethods("LowPriority");
//        ThreadMethods MediumPriority=new ThreadMethods("MediumPriority");
//        ThreadMethods HighPriority=new ThreadMethods("HighPriority");
        //priority set
//        LowPriority.setPriority(Thread.MIN_PRIORITY);
//        MediumPriority.setPriority(Thread.NORM_PRIORITY);
//        HighPriority.setPriority(Thread.MAX_PRIORITY);

//        LowPriority.start();
//        MediumPriority.start();
//        HighPriority.start();

//        System.out.println("LowPriority: "+LowPriority.getPriority());
//        System.out.println("MediumPriority: "+MediumPriority.getPriority());
//        System.out.println("HighPriority: "+HighPriority.getPriority());

        //interrupt method
//        LowPriority.interrupt();
//        MediumPriority.interrupt();
//        HighPriority.interrupt();


        //yield method
//        ThreadMethods t1=new ThreadMethods("T1");
//        ThreadMethods t2=new ThreadMethods("T2");
//
//        t1.start();
//        t2.start();


        //set daemon ->> Daemon thread is basically is background threads(JVM not wait)
        ThreadMethods t1 = new ThreadMethods("T1");
        t1.setDaemon(true);
        t1.start();
//        System.out.println(t1.toString());
        System.out.println("Main thread started");
    }
}
