public class Test {
    public static void main(String[] args)
    {
        Counter cnt=new Counter();
        CounterThread cntThread=new CounterThread(cnt);
        CounterThread cntThread1=new CounterThread(cnt);
        cntThread.start();
        cntThread1.start();
        try
        {
            cntThread.join();
            cntThread1.join();
        }catch(Exception e)
        {
            System.out.println("again...");
        }
        System.out.println(cnt.getCount());
    }
}
