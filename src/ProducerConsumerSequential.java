import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerSequential {

    static class SharedQueue
    {
        private Queue<Integer>queue=new LinkedList<>();
        private int count=1;//ensure sequential execution(one time at a time)

        private synchronized void produce(int value)throws InterruptedException
        {
            while(!queue.isEmpty())
            {
                wait();//wait if the queue is full
            }
            queue.add(value);
            System.out.println("Produced "+value);
            notifyAll();//notify consumer
        }
        private synchronized void consume() throws InterruptedException
        {
            while(queue.isEmpty())
            {
                wait();
            }
            /*
                poll()	    Returns null	                Removes and returns head element
                remove()	Throws NoSuchElementException	Removes and returns head element
            */

          int value=queue.poll();
//          System.out.println("Consumed "+value);
//            int value=queue.remove();
//            try
//            {
//                System.out.println("Consumed " + value);
//            }catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//            System.out.println("Consumed "+value);
            System.out.println("Consumed "+value    );
            notifyAll();
        }
    }

    static class Producer extends Thread
    {
        private SharedQueue sharedQueue;
        Producer(SharedQueue sharedQueue)
        {
            this.sharedQueue=sharedQueue;
        }
        public void run()
        {
            for(int i=1;i<=10;++i)
            {
                try
                {
                    sharedQueue.produce(i);
                    Thread.sleep(200);
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Consumer extends Thread
    {
        private SharedQueue sharedQueue;
        Consumer(SharedQueue sharedQueue)
        {
            this.sharedQueue=sharedQueue;
        }
        public void run()
        {
            for(int i=1;i<=10;++i)
            {
                try
                {
                    sharedQueue.consume();
                    Thread.sleep(200);
                }catch(InterruptedException e)
                {
                    e.printStackTrace();// for detailed error message(stack tree)
                }
            }
        }
    }
    public static void main(String[] args) {
        SharedQueue sharedQueue=new SharedQueue();
        Producer producer=new Producer(sharedQueue);
        Consumer consumer=new Consumer(sharedQueue);

        producer.start();
        consumer.start();
    }
}
