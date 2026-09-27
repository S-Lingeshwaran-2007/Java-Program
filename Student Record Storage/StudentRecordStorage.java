import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;

public class StudentRecordStorage {

    // File name
    static final String FILE_NAME = "students.txt";

    // Method to add student record
    public static void addStudent(Scanner sc) {

        FileWriter writer = null;

        try {
            writer = new FileWriter(FILE_NAME, true);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            sc.nextLine(); // Clear buffer

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Student Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Student Marks: ");
            double marks = sc.nextDouble();

            // Store record in file
            writer.write("ID: " + id + "\n");
            writer.write("Name: " + name + "\n");
            writer.write("Department: " + department + "\n");
            writer.write("Marks: " + marks + "\n");
            writer.write("----------------------------\n");

            System.out.println("\nStudent record saved successfully!");

        } catch (IOException e) {

            System.out.println(
                    "Error while writing to file: "
                    + e.getMessage());

        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println(
                        "Error while closing the file.");
            }
        }
    }

    // Method to retrieve student records
    public static void displayStudents() {

        FileReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            reader = new FileReader(FILE_NAME);
            bufferedReader = new BufferedReader(reader);

            String line;

            System.out.println("\n================================");
            System.out.println("       STUDENT RECORDS");
            System.out.println("================================");

            boolean hasRecords = false;

            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);
                hasRecords = true;
            }

            if (!hasRecords) {
                System.out.println("No student records found.");
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while reading file: "
                    + e.getMessage());

        } finally {

            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (reader != null) {
                    reader.close();
                }

            } catch (IOException e) {

                System.out.println(
                        "Error while closing the file.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        System.out.println("======================================");
        System.out.println("      STUDENT RECORD STORAGE");
        System.out.println("======================================");

        do {

            System.out.println("\n1. Add Student Record");
            System.out.println("2. Display Student Records");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            try {

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        addStudent(sc);
                        break;

                    case 2:
                        displayStudents();
                        break;

                    case 3:
                        System.out.println(
                                "\nExiting program...");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select 1, 2 or 3.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid input. Please enter a number.");

                sc.nextLine();
                choice = 0;
            }

        } while (choice != 3);

        sc.close();

        System.out.println(
                "Thank you for using Student Record Storage.");
    }
}

