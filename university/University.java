package university;

import java.util.ArrayList;
import java.util.List;

public class University {
    public int capacity;
    public List<Student> students;

    public University(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getStudentCount() {
        return getStudents().size();
    }

    public String registerStudent(Student student) {
        if (getCapacity() > getStudentCount() && getStudents().isEmpty()) {
            this.students.add(student);
            return "Added student " + student.getFirstName() + " " + student.getLastName();
        } else if (getCapacity() > getStudentCount()) {
            for (Student s : students) {
                if (s.getFirstName().equals(student.getFirstName()) &&
                        s.getLastName().equals(student.getLastName())) {
                    return "Student is already in the university";
                } else {
                    this.students.add(student);
                    return "Added student " + student.getFirstName() + " " + student.getLastName();
                }
            }
        }
        return "No seats in the university";
    }

    public String dismissStudent(Student student) {
        for (Student s : students) {
            if (s.getFirstName().equals(student.getFirstName()) &&
                s.getLastName().equals(student.getLastName())) {
                this.students.remove(student);
                return "Removed student " + s.getFirstName() + " " + s.getLastName();
            }
        }
        return "Student not found";
    }

    public Student getStudent(String firstName, String lastName) {
        return students.stream()
                .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
                .findFirst().orElse(null);
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        for (Student student : students) {
            builder.append(String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s",
                    student.getFirstName(), student.getLastName(), student.getBestSubject())).append("\n");
        }
        return builder.toString();
    }
}
