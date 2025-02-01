import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer
{
    private final Queue<Integer>queue=new LinkedList<>();
    private final int capacity=5;

    public synchronized void produce(int item) throws InterruptedException
    {
        while(queue.size()==capacity)
        {
            wait();// Wait if the queue is full
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        notifyAll();// Notify consumers
    }
    public synchronized int consume() throws InterruptedException
    {
        while(queue.isEmpty())
        {
            wait();// Wait if the queue is empty
        }
        int item=queue.poll();// Removes the element at the front end of the container
        System.out.println("Consumed: " + item);
        notifyAll();// Notify producers
        return item;
    }
}
class Producer implements Runnable
{
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer)
    {
        this.buffer=buffer;
    }
    @Override
    public void run()
    {
        int item=1;
        try
        {
            while(true)
            {
                buffer.produce(item++);
                Thread.sleep(1000);// Simulate work
            }
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable
{
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer)
    {
        this.buffer = buffer;
    }
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                buffer.consume();
                Thread.sleep(1500); // Simulate work
            }
        }catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedBuffer buffer=new SharedBuffer();

        Thread producerThread=new Thread(new Producer(buffer));
        Thread consumerThread=new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
