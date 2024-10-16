import java.util.Scanner;

public class ASM1 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
       FunctionsForStudentManagement sm= new FunctionsForStudentManagement();
        boolean running = true;
        while (running) {
            System.out.println("\nSelect action:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Show student list");
            System.out.println("5. Undo");
            System.out.println("6. Redo");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter ID Student:");
                    String id = scanner.nextLine();
                    System.out.println("Enter Student Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Student Scores:");
                    double marks = scanner.nextDouble();
                    sm.addStudent(id, name, marks);
                    break;
                case 2:
                    System.out.println("Enter the student ID to be edited:");
                    String editId = scanner.nextLine();
                    System.out.println("Enter new name:");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new scores:");
                    double newMarks = scanner.nextDouble();
                    sm.editStudent(editId, newName, newMarks);
                    break;
                case 3:
                    System.out.println("Enter the student ID to delete:");
                    String deleteId = scanner.nextLine();
                    sm.deleteStudent(deleteId);
                    break;
                case 4:
                    sm.displayStudents();
                    break;
                case 5:
                    sm.undo();
                    break;
                case 6:
                    sm.redo();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection, please try again.");
                    break;
            }
        }
        scanner.close();
    }
}



