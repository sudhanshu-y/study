import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream2Person {
    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> people = Arrays.asList(
            new Person("Alice", 23, "New York"),
            new Person("Bob", 30, "London"),
            new Person("Charlie", 28, "New York"),
            new Person("Diana", 35, "Paris"), 
            new Person("Charmi", 35, "Mumbai")
        );

        // Filter: People from New York
        List<Person> newYorkPeople = people.stream()
        .filter(p -> p.getCity().equals("New York"))
        .collect(Collectors.toList());
        
        System.out.println("People from New York: " + newYorkPeople);

        // Extract names and cities of all people
        List<String> allNames = people.stream().map(Person::getName).toList();
        List<String> allCities = people.stream().map(Person::getCity).toList();
        
        System.out.println("All Names: " + allNames);
        System.out.println("All Cities: " + allCities);

        // Filter: Names of people from New York
        List<String> newYorkNames = people.stream()
        .filter(p -> p.getCity().equals("New York"))
        .map(Person::getName)
        .toList();
        
        System.out.println("Names from New York: " + newYorkNames);

        // Sort people by age (ascending)
        List<Person> peopleSortedByAge = people.stream()
        .sorted(Comparator.comparing(Person::getAge))
        .toList();

        System.out.println("People sorted by age: " + peopleSortedByAge);

        // Sort people by age in reverse order (descending)
        List<Person> peopleSortedByAgeDesc = people.stream()
        .sorted(Comparator.comparing(Person::getAge).reversed())
        .toList();
        
        System.out.println("People sorted by age (desc): " + peopleSortedByAgeDesc);

        // Multiple sorting: first by age, then by name
        List<Person> peopleSortedByAgeThenName = people.stream()
        .sorted(
            Comparator.comparing(Person::getAge)
            .thenComparing(Person::getName)
        ).toList();
    
        System.out.println("People sorted by age then name: " + peopleSortedByAgeThenName);

        // Multiple sorting: first by age, then by name
        List<Person> peopleSortedByAgeASCThenNameDESC = people.stream()
        .sorted(
            Comparator.comparing(Person::getAge)
            .thenComparing(Person::getName, Collections.reverseOrder())
        ).toList();
        
        System.out.println("People sorted by age ascending then name descending: " + peopleSortedByAgeASCThenNameDESC);

        // Get sorted names (age then name)
        List<String> sortedNames = people.stream()
        .sorted(
            Comparator.comparing(Person::getAge)
            .thenComparing(Person::getName)
        ).map(Person::getName)
        .toList();
        
        System.out.println("Sorted names by age then name: " + sortedNames);

        // Group people by city
        Map<String, List<Person>> peopleGroupedByCity = people.stream()
        .collect(Collectors.groupingBy(Person::getCity));
        
        peopleGroupedByCity.forEach((city, persons) -> System.out.println(city + " -> " + persons));

        // Group names of people by city
        Map<String, List<String>> namesGroupedByCity = people.stream()
        .collect(
            Collectors.groupingBy(
                Person::getCity,
                Collectors.mapping(Person::getName, Collectors.toList())
            )
        );
        
        namesGroupedByCity.forEach((city, names) -> System.out.println(city + " -> " + names));

        // Count of people in each city
        Map<String, Long> countByCity = people.stream()
        .collect(
            Collectors.groupingBy(
                Person::getCity, 
                Collectors.counting()
            )
        );

        countByCity.forEach((city, count) -> System.out.println(city + " -> " + count));

        // Multi-level grouping: city -> age -> list of people
        Map<String, Map<Integer, List<Person>>> peopleGroupedByCityAndAge = people.stream()
        .collect(
            Collectors.groupingBy(
                Person::getCity,
                Collectors.groupingBy(Person::getAge)
            )
        );

        peopleGroupedByCityAndAge.forEach((city, ageMap) -> {
            System.out.println(city + " -> ");
            ageMap.forEach((age, persons) -> 
                System.out.println("  " + age + " -> " + persons)
            );
        });

        // Function.identity()
        Map<String, Long> identityCount = people.stream()
        .map(Person::getName)
        .collect(
            Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            )
        );
        System.out.println("Function.identity(): "+ identityCount);

        // List of distinct cities
        List<String> distinctCities = people.stream()
        .map(Person::getCity)
        .distinct()
        .toList();
        
        System.out.println("Distinct cities: " + distinctCities);
    }
}

class Person {
    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() { 
        return name; 
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return name + " (" + age + ") - " + city;
    }
}
