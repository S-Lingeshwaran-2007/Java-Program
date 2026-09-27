import java.util.InputMismatchException;
import java.util.Scanner;

// User-defined exception
class InvalidMarksException extends Exception {

    public InvalidMarksException(String message) {
        super(message);
    }
}

// Main class
public class StudentMarksValidation {

    // Method to validate marks
    public static void validateMarks(int marks)
            throws InvalidMarksException {

        if (marks < 0) {
            throw new InvalidMarksException(
                    "Marks cannot be negative.");
        }

        if (marks > 100) {
            throw new InvalidMarksException(
                    "Marks cannot be greater than 100.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        System.out.println("====================================");
        System.out.println("     STUDENT MARKS VALIDATION");
        System.out.println("====================================");

        do {

            System.out.println("\n1. Validate Student Marks");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            try {

                choice = sc.nextInt();

                switch (choice) {

                    case 1:

                        System.out.print("\nEnter student name: ");
                        String name = sc.next();

                        System.out.print("Enter marks: ");

                        try {

                            int marks = sc.nextInt();

                            // Validate marks
                            validateMarks(marks);

                            System.out.println("\nStudent Name : " + name);
                            System.out.println("Marks        : " + marks);
                            System.out.println(
                                    "Result       : Valid Marks");

                            if (marks >= 50) {
                                System.out.println(
                                        "Status       : PASS");
                            } else {
                                System.out.println(
                                        "Status       : FAIL");
                            }

                        } catch (InvalidMarksException e) {

                            System.out.println(
                                    "Invalid Marks Error: "
                                    + e.getMessage());

                        } catch (InputMismatchException e) {

                            System.out.println(
                                    "Input Error: Please enter "
                                    + "numeric marks.");

                            sc.nextLine();

                        } finally {

                            System.out.println(
                                    "Marks validation completed.");

                        }

                        break;

                    case 2:

                        System.out.println(
                                "\nExiting program...");

                        break;

                    default:

                        System.out.println(
                                "Invalid choice. "
                                + "Please select 1 or 2.");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Input Error: Please enter a number "
                        + "for the menu choice.");

                sc.nextLine();
                choice = 0;

            } finally {

                System.out.println(
                        "Transaction completed.");

            }

        } while (choice != 2);

        sc.close();

        System.out.println(
                "\nThank you for using Student Marks Validation.");
    }
}

