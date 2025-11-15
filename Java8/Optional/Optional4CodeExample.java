import java.util.Optional;

/**
 * Optional-Based Null-Safe Getter Chain (Nested Objects)
 */

class City{
    String cityName;
    City(String cityName) { this.cityName=cityName; }
    public String getCityName() { return this.cityName;}
}

class Address{
    City city;
    Address(City city) { this.city = city; }
    public City getCity() { return this.city; }
}

class Person{
    Address address;
    Person(Address address) { this.address = address; }
    public Address getAddress() { return this.address; }
}

public class Optional4CodeExample {
    public static void main(String[] args) {

        Person p1 = new Person(new Address(new City("Magarpatta")));
        Person p2 = new Person(new Address(null));
        Person p3 = new Person(null);
        Person p4 = null;

        System.out.println("Calling getCityName(p1): "+getCityName(p1));

        System.out.println("Calling getCityName(p2): "+getCityName(p2));

        System.out.println("Calling getCityName(p3): "+getCityName(p3));

        System.out.println("Calling getCityName(p4): "+getCityName(p4));
    }

    public static String getCityName(Person person){
        return Optional.ofNullable(person)
        .map(Person::getAddress)
        .map(Address::getCity)
        .map(City::getCityName)
        .orElse("Unknown");
    }
}
