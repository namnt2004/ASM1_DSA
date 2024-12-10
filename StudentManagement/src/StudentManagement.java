import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    private List<Student> students = new ArrayList<>();

    // Add random students to the list
    public void addRandomStudents(int n) {
        for (int i = 0; i < n; i++) {
            String id = "ST" + (students.size() + 1);
            String name = "Student" + (students.size() + 1);
            double grade = Math.round((Math.random() * 10) * 10.0) / 10.0; // Random grade from 0 to 10
            students.add(new Student(id, name, grade));
        }
    }

    // Display the list of students
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Add a student to the list
    public void addStudent(String id, String name, double grade) {
        students.add(new Student(id, name, grade));
    }

    // Edit a student's information
    public void editStudent(String id, String newName, double newGrade) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setGrade(newGrade);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    // Delete a student by ID
    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    // Search for a student by ID
    public Student searchStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Quick Sort
    public void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);  // Sort the left part
            quickSort(pi + 1, high); // Sort the right part
        }
    }

    // Partition the list for Quick Sort
    private int partition(int low, int high) {
        double pivot = students.get(high).getGrade(); // Choose the grade as the pivot
        int i = (low - 1); // Index of the element smaller than pivot

        for (int j = low; j < high; j++) {
            if (students.get(j).getGrade() <= pivot) {
                i++;
                // Swap
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        // Swap the pivot to its correct position
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    // Bubble Sort
    public void bubbleSort() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getGrade() > students.get(j + 1).getGrade()) {
                    // Swap
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    public List<Student> getStudents() {
        return students;
    }
}
