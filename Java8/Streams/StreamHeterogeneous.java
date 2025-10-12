import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamHeterogeneous {
    public static void main(String[] args) {
        
        // Word frequency from a list
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple", "strawberry");

        Map<String, Long> wordCount = words.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Word Frequency: " + wordCount);

        // Character frequency from a String
        String input = "banana";
        Map<Character, Long> charCount = input.chars()  // IntStream of characters
        .mapToObj(c -> (char) c)                        // convert int to char
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Character Frequency from String: " + charCount);

        // Reverse each word in a sentence
        String sentence = "Java Stream Interview Questions";
        System.out.println("Sentence: " + sentence);

        String reversed = Arrays.stream(sentence.split(" "))
        .map(str -> new StringBuilder(str).reverse())
        .collect(Collectors.joining(" "));

        System.out.println("Reversed words: " + reversed);

        // Find the longest string in a list
        String longest = words.stream()
        .max(Comparator.comparing(String::length))
        .get();
        System.out.println("Longest words: " + longest);

    }
}
