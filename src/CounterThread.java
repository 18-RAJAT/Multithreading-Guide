public class CounterThread extends Thread {

    private Counter counter;
    @Override
    public void run()
    {
//        super.run();
        for(int i=0;i<505;++i)
        {
            counter.increment();
        }
    }
    public CounterThread(Counter counter)
    {
        this.counter=counter;
    }
}
