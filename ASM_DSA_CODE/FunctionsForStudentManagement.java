import java.util.ArrayList;
import java.util.Stack;

public class FunctionsForStudentManagement {

        private ArrayList<Student> students = new ArrayList<>();
        private Stack<String> undoStack = new Stack<>();
        private Stack<String> redoStack = new Stack<>();

        // Add Student
        public void addStudent(String id, String name, double marks) {
            students.add(new Student(id, name, marks));
            undoStack.push("ADD " + id);  // Save operations to stack
            redoStack.clear();  // Clear redo when new operation occurs
        }
        //Edit Student
        public void editStudent(String id, String newName, double newMarks) {
            for (Student s : students) {
                if (s.getId().equals(id)) {
                    undoStack.push("EDIT " + id + " " + s.getName() + " " + s.getMarks());
                    s.setName(newName);
                    s.setMarks(newMarks);
                    redoStack.clear();
                    return;
                }
            }
        }
        // Delete student
        public void deleteStudent(String id) {
            for (Student s : students) {
                if (s.getId().equals(id)) {
                    undoStack.push("DELETE " + id + " " + s.getName() + " " + s.getMarks());
                    students.remove(s);
                    redoStack.clear();
                    return;
                }
            }
        }
        // Find student by ID
        private Student findStudentById(String id) {
            for (Student s : students) {
                if (s.getId().equals(id)) {
                    return s;
                }
            }
            return null;
        }

        // Show student list
        public void displayStudents() {
            for (Student s : students) {
                System.out.println(s);
            }
        }
        // Undo
        public void undo() {
            if (!undoStack.isEmpty()) {
                String action = undoStack.pop();
                String[] parts = action.split(" ");

                switch (parts[0]) {
                    case "ADD":
                        redoStack.push("ADD " + parts[1]);
                        students.removeIf(s -> s.getId().equals(parts[1]));
                        break;
                    case "EDIT":
                        redoStack.push("EDIT " + parts[1] + " " + findStudentById(parts[1]).getName() + " " + findStudentById(parts[1]).getMarks());
                        editStudent(parts[1], parts[2], Double.parseDouble(parts[3]));
                        break;
                    case "DELETE":
                        redoStack.push("DELETE " + parts[1]);
                        students.add(new Student(parts[1], parts[2], Double.parseDouble(parts[3])));
                        break;
                }
            } else {
                System.out.println("There is no action to undo.");
            }
        }

        // Redo
        public void redo() {
            if (!redoStack.isEmpty()) {
                String action = redoStack.pop();
                String[] parts = action.split(" ");

                switch (parts[0]) {
                    case "ADD":
                        undoStack.push("ADD " + parts[1]);
                        students.add(new Student(parts[1], "", 0));
                        break;
                    case "EDIT":
                        undoStack.push("EDIT " + parts[1] + " " + findStudentById(parts[1]).getName() + " " + findStudentById(parts[1]).getMarks());
                        editStudent(parts[1], parts[2], Double.parseDouble(parts[3]));
                        break;
                    case "DELETE":
                        undoStack.push("DELETE " + parts[1]);
                        students.removeIf(s -> s.getId().equals(parts[1]));
                        break;
                }
            } else {
                System.out.println("There is no action to redo.");
            }
        }
}



