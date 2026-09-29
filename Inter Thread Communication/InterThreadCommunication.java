// Inter-Thread Communication in Java
// Demonstrates wait(), notify(), and notifyAll()

class SharedData {

    private int data;
    private boolean available = false;

    // Producer adds data
    public synchronized void produce(int value) {

        while (available) {
            try {
                // Wait until the consumer takes the data
                wait();
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        data = value;
        available = true;

        System.out.println(
                "Producer produced: " + data);

        // Notify one waiting thread
        notify();

        // If multiple consumers are waiting,
        // notifyAll() can wake all of them.
        // notifyAll();
    }

    // Consumer takes data
    public synchronized void consume() {

        while (!available) {
            try {
                // Wait until producer adds data
                wait();
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(
                "Consumer consumed: " + data);

        available = false;

        // Notify waiting producer
        notify();

        // notifyAll() can be used when multiple
        // threads are waiting.
        // notifyAll();
    }
}


// Producer thread
class Producer extends Thread {

    private SharedData sharedData;

    Producer(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            sharedData.produce(i);
        }
    }
}


// Consumer thread
class Consumer extends Thread {

    private SharedData sharedData;

    Consumer(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            sharedData.consume();
        }
    }
}


// Main class
public class InterThreadCommunication {

    public static void main(String[] args) {

        SharedData sharedData = new SharedData();

        Producer producer = new Producer(sharedData);
        Consumer consumer = new Consumer(sharedData);

        System.out.println(
                "====================================");
        System.out.println(
                "   INTER-THREAD COMMUNICATION");
        System.out.println(
                "====================================\n");

        producer.start();
        consumer.start();

        try {

            producer.join();
            consumer.join();

        } catch (InterruptedException e) {

            System.out.println(
                    "Main thread interrupted.");

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "\n====================================");
        System.out.println(
                "Communication completed successfully.");
        System.out.println(
                "====================================");
    }
}
