import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Lambda3PracticalExample {
    public static void main(String[] args) {

        // Supplier → generates a list of random product names
        Supplier<List<String>> productSupplier = () -> {
            List<String> products = new ArrayList<>();
            String[] names = {"TV", "Phone", "Laptop", "Mouse", "Tablet", "Watch"};
            Random rand = new Random();
            for (int i = 0; i < 5; i++) {
                products.add(names[rand.nextInt(names.length)]);
            }
            return products;
        };

        // Using the Supplier and processing the stream
        productSupplier.get().stream()
            .filter(name -> name.length() > 4)            // Predicate
            .map(name -> name.toUpperCase())              // Function
            .forEach(name -> System.out.println(name));   // Consumer
    }
}
