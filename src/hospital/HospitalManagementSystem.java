package hospital;

import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 *
 * Ties together four data structures around one workflow:
 *   1. PatientBST     - permanent patient records, keyed by Patient ID
 *   2. EmergencyQueue  - FIFO line of patients waiting for treatment
 *   3. TreatmentStack  - LIFO history of completed treatments
 *   4. VisitHistory    - singly linked list of each patient's past visits (one per Patient)
 *
 * Typical flow demonstrated by the menu:
 *   Register patient (BST) -> Enqueue for treatment (Queue) ->
 *   Dequeue + complete treatment (push to Stack) -> add a Visit to that
 *   patient's VisitHistory (Linked List)
 */
public class HospitalManagementSystem {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextVisitId = 1000; // simple auto-incrementing visit ID

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("=================================================");
        System.out.println(" Mini Hospital Emergency Management System");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientBST.inOrderTraversal();
                case 5 -> addToEmergencyQueue();
                case 6 -> callNextPatient();
                case 7 -> emergencyQueue.display();
                case 8 -> treatmentStack.display();
                case 9 -> popTreatmentRecord();
                case 10 -> addVisitToPatient();
                case 11 -> removeVisitFromPatient();
                case 12 -> searchVisitForPatient();
                case 13 -> displayPatientVisitHistory();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------- MAIN MENU -----------------");
        System.out.println(" Patient Records (BST)");
        System.out.println("  1. Register new patient");
        System.out.println("  2. Search patient by ID");
        System.out.println("  3. Delete patient by ID");
        System.out.println("  4. Display all patients (in-order)");
        System.out.println(" Emergency Queue");
        System.out.println("  5. Add patient to emergency queue");
        System.out.println("  6. Call next patient for treatment (dequeue)");
        System.out.println("  7. Display waiting queue");
        System.out.println(" Treatment History (Stack)");
        System.out.println("  8. Display treatment history");
        System.out.println("  9. Undo / remove most recent treatment record (pop)");
        System.out.println(" Patient Visit History (Linked List)");
        System.out.println("  10. Add visit to a patient's history");
        System.out.println("  11. Remove a visit from a patient's history");
        System.out.println("  12. Search for a visit in a patient's history");
        System.out.println("  13. Display a patient's visit history");
        System.out.println("  0. Exit");
        System.out.println("----------------------------------------------");
    }

    // ---------------- BST operations ----------------

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient found = patientBST.search(id);
        if (found == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Patient found:");
            System.out.println(found);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        System.out.println(deleted ? "Patient deleted successfully." : "No patient found with that ID.");
    }

    // ---------------- Queue operations ----------------

    private static void addToEmergencyQueue() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No registered patient with that ID. Please register the patient first (option 1).");
            return;
        }
        String reason = readString("Enter reason for emergency visit: ");
        emergencyQueue.enqueue(new EmergencyPatient(id, patient.getName(), reason));
    }

    private static void callNextPatient() {
        EmergencyPatient next = emergencyQueue.dequeue();
        if (next == null) {
            return; // EmergencyQueue already printed an "empty" message
        }
        System.out.println("Now treating: " + next);

        String treatmentGiven = readString("Enter treatment given: ");
        String dateTime = readString("Enter completion date/time (e.g. 2026-09-02 10:30): ");

        TreatmentRecord record = new TreatmentRecord(next.getPatientId(), next.getName(), treatmentGiven, dateTime);
        treatmentStack.push(record);
    }

    private static void popTreatmentRecord() {
        TreatmentRecord removed = treatmentStack.pop();
        if (removed != null) {
            System.out.println("Removed most recent treatment record: " + removed);
        }
    }

    // ---------------- Linked List (Visit History) operations ----------------

    private static void addVisitToPatient() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No registered patient with that ID.");
            return;
        }
        String date = readString("Enter visit date (e.g. 2026-01-15): ");
        String doctor = readString("Enter doctor name: ");
        String diagnosis = readString("Enter diagnosis: ");
        String treatment = readString("Enter treatment given: ");

        Visit visit = new Visit(nextVisitId++, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added with Visit ID " + visit.getVisitId());
    }

    private static void removeVisitFromPatient() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No registered patient with that ID.");
            return;
        }
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "No visit found with that ID.");
    }

    private static void searchVisitForPatient() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No registered patient with that ID.");
            return;
        }
        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit == null ? "No visit found with that ID." : "Found visit: " + visit);
    }

    private static void displayPatientVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No registered patient with that ID.");
            return;
        }
        System.out.println("Visit history for " + patient.getName() + " (ID " + id + "):");
        patient.getVisitHistory().display();
    }

    // ---------------- Input helpers ----------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
