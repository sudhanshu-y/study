import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Optional3CodeExample {

    public static void main(String[] args) {

        // ------------------------------------------------------------
        // Example 1: Optional.stream() → Stream of 0 or 1 element
        // ------------------------------------------------------------
        Optional<String> name1 = Optional.ofNullable("Rohan");
        Optional<String> name2 = Optional.ofNullable(null);

        Stream<String> nameStream1 = name1.stream();   // Stream("Rohan")
        Stream<String> nameStream2 = name2.stream();   // Stream() (empty)

        // Print stream contents in uppercase
        nameStream1.map(String::toUpperCase).forEach(System.out::println);

        nameStream2.map(String::toUpperCase).forEach(System.out::println);

        // ------------------------------------------------------------
        // Example 2: Convert List<Optional<Integer>> → List<Integer>
        // ------------------------------------------------------------
        List<Optional<Integer>> numbers = Arrays.asList(
            Optional.empty(),    
            Optional.of(10),
            Optional.of(20),
            Optional.empty(),
            Optional.of(-30),
            Optional.of(50)
        );

        List<Integer> result = numbers.stream()
            .flatMap(Optional::stream)   // convert Optional<T> → 0 or 1 elements
            .toList();                   // Java 16+ immutable list

        // map(Optional::stream) => [Stream(10), Stream(), Stream(30)]
        // flatMap(Optional::stream) => Stream(10, 30)

        System.out.println(result);
        // [10, 20, -30, 50]

        // ------------------------------------------------------------
        // Example 3: Return First Non-Empty Optional from a List
        // ------------------------------------------------------------

        Integer firstNonEmpty = numbers.stream().flatMap(Optional::stream).findFirst().orElse(Integer.MIN_VALUE);
        System.out.println("First non-empty number: "+ firstNonEmpty);
    }
}
