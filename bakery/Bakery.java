package bakery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bakery {
    private String name;
    private int capacity;
    private List<Employee> employees;

    public Bakery(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public int getCount() {
        return this.employees.size();
    }

    public void add(Employee employee) {
        if (this.capacity >= getCount() + 1) {
            this.employees.add(employee);
        }
    }

    public boolean remove(String name) {
        Employee employeeToRemove = employees.stream().filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
        if (employeeToRemove != null) {
            return this.employees.remove(employeeToRemove);
        } else {
            return false;
        }
    }

    public Employee getOldestEmployee() {
        return this.employees.stream().max(Comparator.comparing(Employee::getAge)).get();
    }

    public Employee getEmployee(String name) {
        return this.employees.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Employees working at Bakery %s:", this.name)).append("\n");
        for (Employee employee : employees) {
            builder.append(employee.toString()).append("\n");
        }
        return builder.toString().trim();
    }
}
