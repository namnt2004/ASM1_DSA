import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagement sm = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        // Create the list of students
        System.out.print("Enter the number of students to generate randomly: ");
        int numStudents = scanner.nextInt();
        sm.addRandomStudents(numStudents);

        // Menu for user interaction

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display students");
            System.out.println("2. Add a student");
            System.out.println("3. Edit a student");
            System.out.println("4. Delete a student");
            System.out.println("5. Search for a student by ID");
            System.out.println("6. Compare sorting algorithms ");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    sm.displayStudents();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String id = scanner.next();
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter student grade: ");
                    double grade = scanner.nextDouble();
                    sm.addStudent(id, name, grade);
                    break;
                case 3:
                    System.out.print("Enter student ID to edit: ");
                    String editId = scanner.next();
                    System.out.print("Enter new name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new grade: ");
                    double newGrade = scanner.nextDouble();
                    sm.editStudent(editId, newName, newGrade);
                    break;
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.next();
                    sm.deleteStudent(deleteId);
                    break;
                case 5:
                    System.out.print("Enter student ID to search: ");
                    String searchId = scanner.next();
                    Student student = sm.searchStudent(searchId);
                    if (student != null) {
                        System.out.println("Student found: " + student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    compareSortAlgorithms(sm);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }


    }
    public static void compareSortAlgorithms(StudentManagement sm) {
        // Clone the original list for Bubble Sort
        StudentManagement bubbleSortManager = new StudentManagement();
        bubbleSortManager.getStudents().addAll(sm.getStudents()); // Clone the original list

        // Quick Sort
        long startQuickSort = System.nanoTime();
        sm.quickSort(0, sm.getStudents().size() - 1);
        long endQuickSort = System.nanoTime();
        double quickSortTime = (endQuickSort - startQuickSort) / 1_000_000.0;

        // Bubble Sort
        long startBubbleSort = System.nanoTime();
        bubbleSortManager.bubbleSort();
        long endBubbleSort = System.nanoTime();
        double bubbleSortTime = (endBubbleSort - startBubbleSort) / 1_000_000.0;

        // Display results
        System.out.println("\nComparison of Sorting Algorithms:");
        System.out.println("Quick Sort:");
        sm.displayStudents();


        System.out.println("\nBubble Sort:");
        bubbleSortManager.displayStudents();
        System.out.println("\nExecution time: ");
        System.out.println("Execution time BubbleSort: " + bubbleSortTime + " ms");
        System.out.println("Execution time QuickSort: " + quickSortTime + " ms");


    }

}
