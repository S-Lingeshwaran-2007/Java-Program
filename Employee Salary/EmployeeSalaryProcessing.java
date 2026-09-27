import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    double salary;

    // Constructor
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Display employee details
    @Override
    public String toString() {
        return "ID: " + id +
               ", Name: " + name +
               ", Salary: Rs." + salary;
    }
}

public class EmployeeSalaryProcessing {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Employee salary data
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, "Arun", 45000));
        employees.add(new Employee(102, "Bala", 60000));
        employees.add(new Employee(103, "Charan", 35000));
        employees.add(new Employee(104, "Divya", 75000));
        employees.add(new Employee(105, "Ezhil", 50000));
        employees.add(new Employee(106, "Fathima", 90000));

        System.out.println("======================================");
        System.out.println("   EMPLOYEE SALARY PROCESSING SYSTEM");
        System.out.println("======================================");

        System.out.print("Enter salary threshold: ");
        double threshold = sc.nextDouble();

        // ------------------------------------------------
        // 1. FILTER EMPLOYEES
        // ------------------------------------------------
        System.out.println("\nEmployees with salary >= Rs." + threshold);

        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.salary >= threshold)
                .collect(Collectors.toList());

        filteredEmployees.forEach(emp -> System.out.println(emp));

        // ------------------------------------------------
        // 2. SORT IN ASCENDING ORDER
        // ------------------------------------------------
        System.out.println("\nSalary - Ascending Order");

        employees.stream()
                .sorted(Comparator.comparingDouble(emp -> emp.salary))
                .forEach(emp -> System.out.println(emp));

        // ------------------------------------------------
        // 3. SORT IN DESCENDING ORDER
        // ------------------------------------------------
        System.out.println("\nSalary - Descending Order");

        employees.stream()
                .sorted(Comparator.comparingDouble((Employee emp) -> emp.salary)
                        .reversed())
                .forEach(emp -> System.out.println(emp));

        // ------------------------------------------------
        // 4. CALCULATE AVERAGE SALARY
        // ------------------------------------------------
        double averageSalary = employees.stream()
                .mapToDouble(emp -> emp.salary)
                .average()
                .orElse(0.0);

        System.out.println("\nAverage Salary: Rs." + averageSalary);

        // ------------------------------------------------
        // 5. FIND HIGHEST SALARY
        // ------------------------------------------------
        Employee highestSalary = employees.stream()
                .max(Comparator.comparingDouble(emp -> emp.salary))
                .orElse(null);

        System.out.println("\nHighest Salary Employee:");
        System.out.println(highestSalary);

        // ------------------------------------------------
        // 6. FIND LOWEST SALARY
        // ------------------------------------------------
        Employee lowestSalary = employees.stream()
                .min(Comparator.comparingDouble(emp -> emp.salary))
                .orElse(null);

        System.out.println("\nLowest Salary Employee:");
        System.out.println(lowestSalary);

        // ------------------------------------------------
        // 7. COUNT EMPLOYEES ABOVE THRESHOLD
        // ------------------------------------------------
        long count = employees.stream()
                .filter(emp -> emp.salary >= threshold)
                .count();

        System.out.println("\nNumber of employees with salary >= Rs."
                + threshold + ": " + count);

        sc.close();
    }
}

