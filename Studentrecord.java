import java.util.Scanner;

class Student {
int rollNo;
String name;
String department;
int semester;
int attendance;
int marks;


Student(int rollNo, String name, String department, int semester, int attendance, int marks) {
    this.rollNo = rollNo;
    this.name = name;
    this.department = department;
    this.semester = semester;
    this.attendance = attendance;
    this.marks = marks;
}

void displayStudent() {
    System.out.println("\n----- Student Details -----");
    System.out.println("Roll Number: " + rollNo);
    System.out.println("Name: " + name);
    System.out.println("Department: " + department);
    System.out.println("Semester: " + semester);
    System.out.println("Attendance: " + attendance + "%");
    System.out.println("Marks: " + marks + "/100");
}

void generateReportCard() {
    System.out.println("\n===== STUDENT REPORT CARD =====");
    System.out.println("Student Name: " + name);
    System.out.println("Roll Number: " + rollNo);
    System.out.println("Department: " + department);
    System.out.println("Semester: " + semester);
    System.out.println("Attendance: " + attendance + "%");
    System.out.println("Total Marks: " + marks + "/100");

    if (marks >= 90) {
        System.out.println("Grade: A+");
    } else if (marks >= 80) {
        System.out.println("Grade: A");
    } else if (marks >= 70) {
        System.out.println("Grade: B");
    } else if (marks >= 60) {
        System.out.println("Grade: C");
    } else if (marks >= 40) {
        System.out.println("Grade: D");
    } else {
        System.out.println("Grade: F");
    }

    if (marks >= 40 && attendance >= 75) {
        System.out.println("Result: PASS");
    } else {
        System.out.println("Result: FAIL");
    }

    System.out.println("==============================");
}


}

public class Studentrecord {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);


    Student[] students = new Student[100];
    int count = 0;
    int choice;

    do {
        System.out.println("\n===== UNIVERSITY STUDENT RECORD SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Generate Report Card");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                if (count == students.length) {
                    System.out.println("Student record limit reached.");
                    break;
                }

                System.out.print("Enter Roll Number: ");
                int rollNo = sc.nextInt();
                sc.nextLine();

                boolean exists = false;

                for (int i = 0; i < count; i++) {
                    if (students[i].rollNo == rollNo) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Student already exists.");
                    break;
                }

                System.out.print("Enter Student Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Department: ");
                String department = sc.nextLine();

                System.out.print("Enter Semester: ");
                int semester = sc.nextInt();

                System.out.print("Enter Attendance Percentage: ");
                int attendance = sc.nextInt();

                System.out.print("Enter Marks out of 100: ");
                int marks = sc.nextInt();

                if (attendance < 0 || attendance > 100 || marks < 0 || marks > 100
                        || semester < 1) {
                    System.out.println("Invalid input. Please enter valid values.");
                    break;
                }

                students[count] = new Student(
                    rollNo, name, department, semester, attendance, marks
                );

                count++;
                System.out.println("Student record added successfully!");
                break;

            case 2:
                if (count == 0) {
                    System.out.println("No student records found.");
                } else {
                    for (int i = 0; i < count; i++) {
                        students[i].displayStudent();
                    }
                }
                break;

            case 3:
                System.out.print("Enter Roll Number to Search: ");
                int searchRoll = sc.nextInt();

                boolean found = false;

                for (int i = 0; i < count; i++) {
                    if (students[i].rollNo == searchRoll) {
                        students[i].displayStudent();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Student record not found.");
                }
                break;

            case 4:
                System.out.print("Enter Roll Number for Report Card: ");
                int reportRoll = sc.nextInt();

                boolean reportFound = false;

                for (int i = 0; i < count; i++) {
                    if (students[i].rollNo == reportRoll) {
                        students[i].generateReportCard();
                        reportFound = true;
                        break;
                    }
                }

                if (!reportFound) {
                    System.out.println("Student record not found.");
                }
                break;

            case 5:
                System.out.println("Thank you for using the Student Record System!");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }

    } while (choice != 5);

    sc.close();
}

}
