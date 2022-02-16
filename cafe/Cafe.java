package cafe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cafe {
    private String name;
    private int capacity;
    private List<Employee> employees;

    public Cafe(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public int getCount() {
        return this.employees.size();
    }

    public void addEmployee(Employee employee) {
        if (capacity > this.getCount()) {
            this.employees.add(employee);
            capacity--;
        }
    }

    public boolean removeEmployee(String name) {
        Employee employeeToRemove = employees.stream().filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
        if (employeeToRemove != null) {
            capacity++;
            return employees.remove(employeeToRemove);
        }
        return false;
    }

    public Employee getOldestEmployee() {
        Employee oldestEmployee = employees.stream().max(Comparator.comparing(Employee::getAge)).get();
        return oldestEmployee;
    }

    public Employee getEmployee(String name) {
        return employees.stream().filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Employees working at Cafe %s:", this.name)).append("\n");
        for (Employee employee : employees) {
            builder.append(employee.toString()).append("\n");
        }
        return builder.toString();
    }
}
