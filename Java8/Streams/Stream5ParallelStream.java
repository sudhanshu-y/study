import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Stream5ParallelStream {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Doe", "Alice", "Bob");

        // Using default parallel stream (uses ForkJoinPool.commonPool())
        names.parallelStream()
        .map(String::toUpperCase)
        .forEach(n -> System.out.println(Thread.currentThread().getName() + " -> " + n));

        System.out.println("---- Using custom ForkJoinPool ----");

        // Example 2: Using custom ForkJoinPool with 3 threads
        ForkJoinPool fjPool = new ForkJoinPool(3);

        // Submit a task (stream pipeline) to the custom pool
        fjPool.submit(() -> 
            names.parallelStream()
            .map(String::toUpperCase)
            .forEach(name -> System.out.println(Thread.currentThread().getName() + " -> " + name))
        ).get();  // .get() ensures the task finishes before continuing

        // Always shut down custom pools
        fjPool.shutdown();
    }
}
