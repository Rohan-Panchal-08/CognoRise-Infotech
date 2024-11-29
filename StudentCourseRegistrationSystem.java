import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudentCourseRegistrationSystem {
    static class Course {
        private String courseCode;
        private String title;
        private String description;
        private int capacity;
        private String schedule;
        private ArrayList<String> registeredStudents;

        public Course(String courseCode, String title, String description, int capacity, String schedule) {
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.schedule = schedule;
            this.registeredStudents = new ArrayList<>();
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getCapacity() {
            return capacity;
        }

        public String getSchedule() {
            return schedule;
        }

        public int getAvailableSlots() {
            return capacity - registeredStudents.size();
        }

        public boolean registerStudent(String studentID) {
            if (registeredStudents.size() < capacity) {
                registeredStudents.add(studentID);
                return true;
            }
            return false;
        }

        public boolean removeStudent(String studentID) {
            return registeredStudents.remove(studentID);
        }

        @Override
        public String toString() {
            return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description +
                    ", Capacity: " + capacity + ", Schedule: " + schedule + 
                    ", Available Slots: " + getAvailableSlots();
        }
    }

    static class Student {
        private String studentID;
        private String name;
        private ArrayList<String> registeredCourses;

        public Student(String studentID, String name) {
            this.studentID = studentID;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }

        public String getStudentID() {
            return studentID;
        }

        public String getName() {
            return name;
        }

        public ArrayList<String> getRegisteredCourses() {
            return registeredCourses;
        }

        public void registerCourse(String courseCode) {
            registeredCourses.add(courseCode);
        }

        public void dropCourse(String courseCode) {
            registeredCourses.remove(courseCode);
        }

        @Override
        public String toString() {
            return "Student ID: " + studentID + ", Name: " + name + ", Registered Courses: " + registeredCourses;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Course> courses = new HashMap<>();
        HashMap<String, Student> students = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        // Sample data
        courses.put("CS101", new Course("CS101", "Introduction to Programming", "Learn basic programming concepts.", 30, "Mon-Wed 9 AM"));
        courses.put("CS102", new Course("CS102", "Data Structures", "Learn about data structures.", 25, "Tue-Thu 11 AM"));
        courses.put("CS103", new Course("CS103", "Database Systems", "Learn database design and SQL.", 20, "Fri 10 AM"));

        while (true) {
            System.out.println("\nStudent Course Registration System:");
            System.out.println("1. Add Student");
            System.out.println("2. List Available Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. View Student Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    students.put(studentID, new Student(studentID, name));
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses.values()) {
                        System.out.println(course);
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    if (!students.containsKey(studentID)) {
                        System.out.println("Student not found. Please add the student first.");
                        break;
                    }
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    if (!courses.containsKey(courseCode)) {
                        System.out.println("Course not found.");
                        break;
                    }
                    Student student = students.get(studentID);
                    Course course = courses.get(courseCode);
                    if (course.registerStudent(studentID)) {
                        student.registerCourse(courseCode);
                        System.out.println("Course registered successfully.");
                    } else {
                        System.out.println("Course is full.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    if (!students.containsKey(studentID)) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course code to drop: ");
                    courseCode = scanner.nextLine();
                    if (!courses.containsKey(courseCode)) {
                        System.out.println("Course not found.");
                        break;
                    }
                    student = students.get(studentID);
                    course = courses.get(courseCode);
                    if (course.removeStudent(studentID)) {
                        student.dropCourse(courseCode);
                        System.out.println("Course dropped successfully.");
                    } else {
                        System.out.println("You are not registered for this course.");
                    }
                    break;

                case 5:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    if (!students.containsKey(studentID)) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.println(students.get(studentID));
                    break;

                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
