import java.util.Scanner;

class Patient {
int patientId;
String name;
int age;
String disease;
String doctor;
String appointmentDate;
double bill;


Patient(int patientId, String name, int age, String disease) {
    this.patientId = patientId;
    this.name = name;
    this.age = age;
    this.disease = disease;
    this.doctor = "Not Assigned";
    this.appointmentDate = "Not Booked";
    this.bill = 0;
}

void displayPatient() {
    System.out.println("\n----- Patient Details -----");
    System.out.println("Patient ID: " + patientId);
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Disease: " + disease);
    System.out.println("Doctor: " + doctor);
    System.out.println("Appointment Date: " + appointmentDate);
    System.out.println("Bill Amount: Rs." + bill);
}


}

public class HospitalManagement {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);

    Patient[] patients = new Patient[100];
    int count = 0;
    int choice;

    do {
        System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
        System.out.println("1. Register Patient");
        System.out.println("2. Book Appointment");
        System.out.println("3. View Patient Records");
        System.out.println("4. Search Patient");
        System.out.println("5. Generate Bill");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                if (count == patients.length) {
                    System.out.println("Patient record limit reached.");
                    break;
                }

                System.out.print("Enter Patient ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                boolean exists = false;

                for (int i = 0; i < count; i++) {
                    if (patients[i].patientId == id) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Patient ID already exists.");
                    break;
                }

                System.out.print("Enter Patient Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Patient Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                if (age <= 0 || age > 120) {
                    System.out.println("Invalid age.");
                    break;
                }

                System.out.print("Enter Disease: ");
                String disease = sc.nextLine();

                patients[count] = new Patient(id, name, age, disease);
                count++;

                System.out.println("Patient registered successfully!");
                break;

            case 2:
                System.out.print("Enter Patient ID: ");
                int appointmentId = sc.nextInt();
                sc.nextLine();

                boolean appointmentFound = false;

                for (int i = 0; i < count; i++) {
                    if (patients[i].patientId == appointmentId) {
                        System.out.print("Enter Doctor Name: ");
                        patients[i].doctor = sc.nextLine();

                        System.out.print("Enter Appointment Date: ");
                        patients[i].appointmentDate = sc.nextLine();

                        appointmentFound = true;
                        System.out.println("Appointment booked successfully!");
                        break;
                    }
                }

                if (!appointmentFound) {
                    System.out.println("Patient not found.");
                }
                break;

            case 3:
                if (count == 0) {
                    System.out.println("No patient records available.");
                } else {
                    for (int i = 0; i < count; i++) {
                        patients[i].displayPatient();
                    }
                }
                break;

            case 4:
                System.out.print("Enter Patient ID to Search: ");
                int searchId = sc.nextInt();

                boolean found = false;

                for (int i = 0; i < count; i++) {
                    if (patients[i].patientId == searchId) {
                        patients[i].displayPatient();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Patient record not found.");
                }
                break;

            case 5:
                System.out.print("Enter Patient ID: ");
                int billId = sc.nextInt();

                boolean billFound = false;

                for (int i = 0; i < count; i++) {
                    if (patients[i].patientId == billId) {
                        System.out.print("Enter Consultation Fee: Rs.");
                        double consultation = sc.nextDouble();

                        System.out.print("Enter Medicine Charges: Rs.");
                        double medicine = sc.nextDouble();

                        System.out.print("Enter Other Charges: Rs.");
                        double other = sc.nextDouble();

                        if (consultation < 0 || medicine < 0 || other < 0) {
                            System.out.println("Invalid charges.");
                            break;
                        }

                        patients[i].bill = consultation + medicine + other;

                        System.out.println("\n===== HOSPITAL BILL =====");
                        System.out.println("Patient Name: " + patients[i].name);
                        System.out.println("Consultation Fee: Rs." + consultation);
                        System.out.println("Medicine Charges: Rs." + medicine);
                        System.out.println("Other Charges: Rs." + other);
                        System.out.println("Total Bill: Rs." + patients[i].bill);
                        System.out.println("=========================");

                        billFound = true;
                        break;
                    }
                }

                if (!billFound) {
                    System.out.println("Patient not found or invalid charges.");
                }
                break;

            case 6:
                System.out.println("Thank you for using Hospital Management System!");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }

    } while (choice != 6);

    sc.close();
}


}
