import java.util.Optional;

public class Optional2MethodsExample {
    public static void main(String[] args) {

        Optional<String> opt1 = Optional.of("Learning!!"); 
        Optional<String> opt2 = Optional.empty();

        System.out.println("===== Using get() =====");
        // get(): Returns value if present, else throws NoSuchElementException
        if (opt1.isPresent()) {
            System.out.println("opt1.get(): " + opt1.get());
        }
        try {
            System.out.println("opt2.get(): " + opt2.get()); // Throws exception
        } catch (Exception e) {
            System.out.println("opt2.get() Exception: " + e.getMessage());
        }

        System.out.println("\n===== Using isPresent() and isEmpty() =====");
        // isPresent(): true if value exists
        // isEmpty(): true if no value exists
        System.out.println("opt1.isPresent(): " + opt1.isPresent()); // true
        System.out.println("opt2.isPresent(): " + opt2.isPresent()); // false
        System.out.println("opt1.isEmpty(): " + opt1.isEmpty());     // false
        System.out.println("opt2.isEmpty(): " + opt2.isEmpty());     // true

        System.out.println("\n===== Using ifPresent() =====");
        // Executes lambda only if value is present
        opt1.ifPresent(value -> System.out.println("opt1 via ifPresent: " + value));
        // opt1 via ifPresent: Learning!!
        opt2.ifPresent(value -> System.out.println("opt2 via ifPresent: " + value)); 
        // No output

        System.out.println("\n===== Using ifPresentOrElse() =====");
        // Executes one block if value is present, another if absent
        opt1.ifPresentOrElse(
                value -> System.out.println("opt1 via ifPresentOrElse: " + value),
                () -> System.out.println("opt1 is empty")
        );
        opt2.ifPresentOrElse(
                value -> System.out.println("opt2 via ifPresentOrElse: " + value),
                () -> System.out.println("opt2 is empty")
        );

        System.out.println("\n===== Using orElse() =====");
        // Returns value if present, else given default
        String val1 = opt1.orElse("Default");
        String val2 = opt2.orElse("Default");
        System.out.println("orElse opt1: " + val1); // Hello World
        System.out.println("orElse opt2: " + val2); // Default

        System.out.println("\n===== Using orElseGet() =====");
        // Returns value if present, else lazily computes default
        String val3 = opt1.orElseGet(() -> "Lazy Default");
        String val4 = opt2.orElseGet(() -> "Lazy Default");
        System.out.println("orElseGet opt1: " + val3); // Hello World
        System.out.println("orElseGet opt2: " + val4); // Lazy Default

        System.out.println("\n===== Using orElseThrow() =====");
        // Throws NoSuchElementException if value not present
        try {
            String val5 = opt1.orElseThrow(); // Works fine
            System.out.println("orElseThrow opt1: " + val5);
        } catch (Exception e) {
            System.out.println("orElseThrow opt1 Exception: " + e.getMessage());
        }

        try {
            String val6 = opt2.orElseThrow(); // Throws exception
            System.out.println("orElseThrow opt2: " + val6);
        } catch (Exception e) {
            System.out.println("orElseThrow opt2 Exception: " + e.getMessage());
        }

        System.out.println("\n===== Using orElseThrow(Supplier) =====");
        // Throws custom exception if empty
        try {
            String val7 = opt1.orElseThrow(() -> new RuntimeException("Missing Value"));
            System.out.println("orElseThrow(Supplier) opt1: " + val7);
        } catch (Exception e) {
            System.out.println("opt1 Exception: " + e.getMessage());
        }

        try {
            String val8 = opt2.orElseThrow(() -> new RuntimeException("Missing Value"));
            System.out.println("orElseThrow(Supplier) opt2: " + val8);
        } catch (Exception e) {
            System.out.println("orElseThrow(Supplier) opt2 Exception: " + e.getMessage());
        }
    }
}
