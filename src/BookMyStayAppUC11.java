import java.util.*;
import java.util.concurrent.*;

/**
 * =================================================================
 * CLASS - BookMyStayAppUC11
 * =================================================================
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 *
 * Demonstrates safe handling of booking requests in a multi-threaded
 * environment using synchronized blocks to prevent race conditions.
 *
 * Author: Developer
 * Version: 11.0
 */
public class BookMyStayAppUC11 {

    // Shared inventory map: room type -> available count
    private static final Map<String, Integer> inventory = new HashMap<>();
    private static final Queue<BookingRequest> bookingQueue = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        // Initialize inventory
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        // Simulate multiple guests submitting booking requests
        bookingQueue.add(new BookingRequest("Guest1", "Single"));
        bookingQueue.add(new BookingRequest("Guest2", "Double"));
        bookingQueue.add(new BookingRequest("Guest3", "Suite"));
        bookingQueue.add(new BookingRequest("Guest4", "Single"));
        bookingQueue.add(new BookingRequest("Guest5", "Double"));
        bookingQueue.add(new BookingRequest("Guest6", "Single"));

        // Create a thread pool to process bookings concurrently
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit booking tasks
        for (int i = 0; i < 6; i++) {
            executor.submit(BookMyStayAppUC11::processNextBooking);
        }

        // Shutdown executor and wait for completion
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        // Print final inventory state
        System.out.println("\nFinal Inventory:");
        inventory.forEach((room, count) -> System.out.println(room + ": " + count));
    }

    // Synchronized method to process bookings safely
    private static synchronized void processNextBooking() {
        BookingRequest request = bookingQueue.poll();
        if (request != null) {
            String roomType = request.roomType;
            int available = inventory.getOrDefault(roomType, 0);

            if (available > 0) {
                // Allocate room and update inventory
                inventory.put(roomType, available - 1);
                System.out.println("Allocated " + roomType + " to " + request.guestName);
            } else {
                System.out.println("No " + roomType + " rooms available for " + request.guestName);
            }
        }
    }

    // Booking request class
    private static class BookingRequest {
        String guestName;
        String roomType;

        BookingRequest(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }
}