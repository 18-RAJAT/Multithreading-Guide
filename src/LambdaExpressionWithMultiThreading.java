import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class LambdaExpressionWithMultiThreading {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor=Executors.newFixedThreadPool(5);
        List<Integer>numbers= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);

        //runnable->this task doesn't return anything
        Runnable runnableTask=()->{
            System.out.println(Thread.currentThread().getName()+" is executing runnable task");
        };
        executor.submit(runnableTask);

        //callable->this returns the result
        Callable<Integer>callableTask1=()->{
            System.out.println(Thread.currentThread().getName()+" is executing callable task 1");
            TimeUnit.SECONDS.sleep(5);
            return 20;
        };
        Callable<Integer>callableTask2=()->{
            System.out.println(Thread.currentThread().getName()+" is executing callable task 1");
            TimeUnit.SECONDS.sleep(5);
            return 40;
        };

        //submitting callable tasks and getting future objects
        Future<Integer>f1=executor.submit(callableTask1);
        Future<Integer>f2=executor.submit(callableTask2);

        System.out.println("Callable task1 "+f1.get());
        System.out.println("Callable task2 "+f2.get());

        //parallel stream example
        int sum=numbers.parallelStream().
                mapToInt(i->i*2).sum();
        System.out.println("Sum of all numbers is "+sum);
        executor.shutdown();
    }
}
