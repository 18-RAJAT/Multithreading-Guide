public class Counter {
    private int cnt=0;

    //when we not use synchronization this is called race-condition...->using this synchronization we can achieve one thread access at a time critical section->. called mutual exclusion(Prevention) <<---

//    public synchronized void increment()
//    {
//        cnt++;
//    }

    //another way
    public void increment()
    {
        synchronized(this)
        {
            cnt++;
        }
    }
    public int getCount() {
//        System.out.println(cnt);
        return cnt;
    }
}
