import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamProblems {
    public static void main(String[] args) {

        // Find the second highest number in a list
        List<Integer> numbers = Arrays.asList(10, -30, 20, 40, -50, -50, 20);
        System.out.println(numbers);
        int secondHighest = numbers.stream()
        .sorted(Collections.reverseOrder())
        .skip(1)
        .findFirst().get();

        System.out.println("Second Highest: "+secondHighest);

        // Find duplicate elements in a list
        List<Integer> duplicates = numbers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter(e->e.getValue()>1)
        .map(Map.Entry::getKey)
        .toList();

        System.out.println("Duplicate numbers: "+duplicates);
        

        // Top-K most frequent words
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");
        int k = 2;

        List<String> topK = words.stream()
        // 1. Count Frequencies
        .collect(
            Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            )
        ).entrySet()
        .stream()
        // 2. Sort: Frequency Descending, then Word Ascending
        .sorted(
            Map.Entry.<String, Long>comparingByValue(Collections.reverseOrder())
            .thenComparing(Map.Entry.comparingByKey())
        )
        // 3. Limit and Extract
        .limit(k)
        .map(Map.Entry::getKey)
        .toList();

        System.out.println("Top-K most frequent words: "+ topK);

    }
}
