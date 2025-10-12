import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream3Department {
    public static void main(String[] args) {
        List<Department> departments = Arrays.asList(
            new Department("HR", Arrays.asList(
                new Employee("Alice", 5000),
                new Employee("Bob", 4500)
            )),
            new Department("IT", Arrays.asList(
                new Employee("Charlie", 7000),
                new Employee("Diana", 6500)
            ))
        );

        // Get all employees across departments
        List<Employee> allEmployees = departments.stream()
        .flatMap(department -> department.getEmployees().stream())
        .toList();
        System.out.println("All Employees: " + allEmployees);

        // Get only employee names
        List<String> employeeNames = departments.stream()
        .flatMap(department -> department.getEmployees().stream())
        .map(Employee::getName)
        .toList();
        System.out.println("Employee Names: " + employeeNames);

        // Collect all salaries into a single list
        List<Double> allSalaries = departments.stream()
        .flatMap(d -> d.getEmployees().stream())
        .map(Employee::getSalary)
        .toList();
        System.out.println("All Salaries: " + allSalaries);

        // Filter employees with salary > 5000
        List<Employee> highSalaryEmployees = departments.stream()
        .flatMap(department -> department.getEmployees().stream())
        .filter(employee -> employee.getSalary() > 5000)
        .toList();
        System.out.println("High Salary Employees: " + highSalaryEmployees);

        // Calculate total salary
        double totalSalary = departments.stream()
        .flatMap(d -> d.getEmployees().stream())
        .mapToDouble(Employee::getSalary)
        .sum();
        System.out.println("Total Salary: $" + totalSalary);

        // Find employee with maximum salary
        departments.stream()
        .flatMap(d -> d.getEmployees().stream())
        .max(Comparator.comparing(Employee::getSalary))
        .ifPresent(e -> System.out.println("Highest Paid: " + e));

        // Find employee with minimum salary
        departments.stream()
        .flatMap(d -> d.getEmployees().stream())
        .min(Comparator.comparing(Employee::getSalary))
        .ifPresent(e -> System.out.println("Lowest Paid: " + e));

        // Group employees by department name
        Map<String, List<Employee>> employeesByDept = departments.stream()
        .collect(
            Collectors.toMap(
                Department::getName,
                Department::getEmployees
            )
        );
        
        employeesByDept.forEach((dept, emps) ->
            System.out.println(dept + " -> " + emps)
        );
    }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (" + salary + ")";
    }
}

class Department {
    private String name;
    private List<Employee> employees;

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() { return name; }
    public List<Employee> getEmployees() { return employees; }

    @Override
    public String toString() {
        return name + " Dept " + employees;
    }
}
