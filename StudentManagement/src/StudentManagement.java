import java.util.Scanner;
import java.util.Random;

public class StudentManagement {
    private StudentStack studentStack;

    public StudentManagement() {
        studentStack = new StudentStack();
    }

    public void addStudent(String id, String name, double marks) {
        Student student = new Student(id, name, marks);
        studentStack.push(student);
    }

    public void deleteStudent() {
        if (!studentStack.isEmpty()) {
            studentStack.pop();
            System.out.println("Student removed from the stack.");
        } else {
            System.out.println("Stack is empty, nothing to delete.");
        }
    }

    public void editStudent(String id, String newName, double newMarks) {
        StudentStack tempStack = new StudentStack();
        boolean found = false;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setMarks(newMarks);
                found = true;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (found) {
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void displayStudents() {
        System.out.println("Students in the stack:");
        studentStack.displayStack();
    }

    public void sortStudentsBubble() {
        Student[] studentsArray = toArray();
        int n = studentsArray.length;

        // Bubble Sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (studentsArray[j].getMarks() < studentsArray[j + 1].getMarks()) {
                    Student temp = studentsArray[j];
                    studentsArray[j] = studentsArray[j + 1];
                    studentsArray[j + 1] = temp;
                }
            }
        }

        rebuildStack(studentsArray);
        System.out.println("Bubble Sort completed.");
    }

    public void quickSortStudents(Student[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortStudents(array, low, pivotIndex - 1);
            quickSortStudents(array, pivotIndex + 1, high);
        }
    }

    private int partition(Student[] array, int low, int high) {
        Student pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].getMarks() >= pivot.getMarks()) { // Sort descending
                i++;
                Student temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        Student temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public void quickSortWrapper() {
        Student[] studentsArray = toArray();
        long startTime = System.nanoTime();
        quickSortStudents(studentsArray, 0, studentsArray.length - 1);
        long endTime = System.nanoTime();
        rebuildStack(studentsArray);
        System.out.println("Quick Sort completed.");
        System.out.println("Students sorted by marks using QuickSort:");
        System.out.println("QuickSort time: " + (endTime - startTime) + " nanoseconds");
    }

    public void bubbleSortWrapper() {
        Student[] studentsArray = toArray();
        long startTime = System.nanoTime();
        sortStudentsBubble();
        long endTime = System.nanoTime();
        System.out.println("Students sorted by marks using Bubble Sort:");
        System.out.println("BubbleSort time: " + (endTime - startTime) + " nanoseconds");
    }

    public void searchStudent(String id) {
        StudentStack tempStack = new StudentStack();
        boolean found = false;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                System.out.println("Student found: " + student);
                found = true;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private Student[] toArray() {
        StudentStack tempStack = new StudentStack();
        int count = 0;

        while (!studentStack.isEmpty()) {
            tempStack.push(studentStack.pop());
            count++;
        }

        Student[] studentsArray = new Student[count];
        for (int i = 0; i < count; i++) {
            studentsArray[i] = tempStack.pop();
            studentStack.push(studentsArray[i]);
        }

        return studentsArray;
    }

    private void rebuildStack(Student[] studentsArray) {
        studentStack = new StudentStack();
        for (Student student : studentsArray) {
            studentStack.push(student);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Vòng lặp chính
            StudentManagement sm = new StudentManagement();
            Random random = new Random();

            System.out.print("Enter the number of students to generate automatically: ");
            int numStudents = 0;

            while (true) {
                try {
                    numStudents = scanner.nextInt();
                    if (numStudents <= 0) {
                        System.out.println("Number of students must be a positive integer. Please try again.");
                    } else {
                        break; // Đầu vào hợp lệ
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                    scanner.nextLine(); // Xóa dữ liệu không hợp lệ
                }
            }

            for (int i = 1; i <= numStudents; i++) {
                String id = "ID" + i;
                String name = "Student" + i;
                double marks = 1.0 + (random.nextDouble() * 9.0); // Random marks between 1.0 and 10.0
                sm.addStudent(id, name, marks);
            }

            System.out.println(numStudents + " students added successfully!");

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. Delete Student");
                System.out.println("3. Edit Student");
                System.out.println("4. Display Students");
                System.out.println("5. Sort Students (Bubble Sort)");
                System.out.println("6. Sort Students (Quick Sort)");
                System.out.println("7. Search for Student");
                System.out.println("8. Exit to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = scanner.nextDouble();
                        sm.addStudent(id, name, marks);
                        break;
                    case 2:
                        sm.deleteStudent();
                        break;
                    case 3:
                        System.out.print("Enter ID to edit: ");
                        String editId = scanner.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Marks: ");
                        double newMarks = scanner.nextDouble();
                        sm.editStudent(editId, newName, newMarks);
                        break;
                    case 4:
                        sm.displayStudents();
                        break;
                    case 5:
                        sm.bubbleSortWrapper();
                        break;
                    case 6:
                        sm.quickSortWrapper();
                        break;
                    case 7:
                        System.out.print("Enter ID to search: ");
                        String searchId = scanner.nextLine();
                        sm.searchStudent(searchId);
                        break;
                    case 8:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                if (choice == 8) {
                    break; // Quay lại menu chính
                }
            }

            System.out.print("Do you want to restart the program? (yes/no): ");
            String restart = scanner.nextLine();
            if (!restart.equalsIgnoreCase("yes")) {
                System.out.println("Exiting the program. Goodbye!");
                break; // Thoát chương trình
            }
        }

        scanner.close();
    }


}
