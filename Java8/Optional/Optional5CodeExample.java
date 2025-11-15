import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Transform Stream of Strings into Only Valid Integers Using Optional
 * Given: List<String> list = Arrays.asList("10", "x", "20", "y", "30");
 * Return a List<Integer>, Ignore invalid ones (like "x" or "y")
 */

public class Optional5CodeExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("10", "x", "20", "y", "30");
        System.out.println("Before: "+list);

        List<Integer> numbers = list.stream()
            .map(Optional5CodeExample::safeParse)
            .flatMap(Optional::stream)
            .toList();

        System.out.println("After: "+ numbers);
    }

    public static Optional<Integer> safeParse(String str){
        try{
            return Optional.of(Integer.parseInt(str));
        }catch(NumberFormatException ex){
            return Optional.empty();
        }
    }
}
