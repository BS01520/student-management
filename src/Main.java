import java.util.*;

// ADT Interface for student storage
interface StudentRepository {
    void addStudent(Student student);
    List<Student> getAllStudents();
    Student findStudentById(int id);
    boolean deleteStudent(int id);
}

// Implementation of the ADT using ArrayList
class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.id == id) return s;
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.id == id);
    }
}

// Student class
class Student {
    int id;
    String name;
    int age;
    String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course);
    }
}

// Main App
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static StudentRepository repository = new InMemoryStudentRepository();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> deleteStudent();
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    static void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        Student s = new Student(id, name, age, course);
        repository.addStudent(s);
        System.out.println("Student added!");
    }

    static void viewStudents() {
        List<Student> all = repository.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            all.forEach(Student::display);
        }
    }

    static void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        Student s = repository.findStudentById(id);
        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found.");
        }
    }

    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        boolean deleted = repository.deleteStudent(id);
        System.out.println(deleted ? "Student deleted." : "Student not found.");
    }
}
