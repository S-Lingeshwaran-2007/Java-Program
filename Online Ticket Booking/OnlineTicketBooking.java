// Online Ticket Booking System
// Demonstrates Thread, Runnable and Synchronization

// Shared resource class
class TicketCounter {

    private int availableTickets = 5;

    // Synchronized method prevents race condition
    public synchronized void bookTicket(String userName, int tickets) {

        System.out.println(
                userName + " is trying to book "
                + tickets + " ticket(s)...");

        // Simulate booking processing
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(
                    userName + " thread was interrupted.");
            Thread.currentThread().interrupt();
        }

        // Check ticket availability
        if (tickets <= availableTickets) {

            availableTickets = availableTickets - tickets;

            System.out.println(
                    "Booking successful for " + userName);

            System.out.println(
                    "Tickets booked: " + tickets);

            System.out.println(
                    "Tickets remaining: "
                    + availableTickets);

        } else {

            System.out.println(
                    "Booking failed for " + userName);

            System.out.println(
                    "Only " + availableTickets
                    + " ticket(s) available.");
        }

        System.out.println("--------------------------------");
    }

    public int getAvailableTickets() {
        return availableTickets;
    }
}


// Thread class implementation
class UserThread extends Thread {

    private TicketCounter counter;
    private String userName;
    private int tickets;

    public UserThread(
            TicketCounter counter,
            String userName,
            int tickets) {

        this.counter = counter;
        this.userName = userName;
        this.tickets = tickets;
    }

    @Override
    public void run() {

        counter.bookTicket(userName, tickets);
    }
}


// Runnable interface implementation
class UserRunnable implements Runnable {

    private TicketCounter counter;
    private String userName;
    private int tickets;

    public UserRunnable(
            TicketCounter counter,
            String userName,
            int tickets) {

        this.counter = counter;
        this.userName = userName;
        this.tickets = tickets;
    }

    @Override
    public void run() {

        counter.bookTicket(userName, tickets);
    }
}


// Main class
public class OnlineTicketBooking {

    public static void main(String[] args) {

        // Shared ticket counter
        TicketCounter counter = new TicketCounter();

        System.out.println("======================================");
        System.out.println("       ONLINE TICKET BOOKING");
        System.out.println("======================================");

        System.out.println(
                "Total available tickets: 5\n");

        // -----------------------------------------
        // Thread created using Thread class
        // -----------------------------------------

        Thread user1 = new UserThread(
                counter,
                "User 1",
                2);

        Thread user2 = new UserThread(
                counter,
                "User 2",
                2);

        // -----------------------------------------
        // Thread created using Runnable interface
        // -----------------------------------------

        Thread user3 = new Thread(
                new UserRunnable(
                        counter,
                        "User 3",
                        2));

        Thread user4 = new Thread(
                new UserRunnable(
                        counter,
                        "User 4",
                        1));

        // Start all threads
        user1.start();
        user2.start();
        user3.start();
        user4.start();

        // Wait for all threads to complete
        try {

            user1.join();
            user2.join();
            user3.join();
            user4.join();

        } catch (InterruptedException e) {

            System.out.println(
                    "Main thread was interrupted.");

            Thread.currentThread().interrupt();
        }

        System.out.println("\n======================================");
        System.out.println("       BOOKING COMPLETED");
        System.out.println("======================================");

        System.out.println(
                "Final available tickets: "
                + counter.getAvailableTickets());
    }
}
