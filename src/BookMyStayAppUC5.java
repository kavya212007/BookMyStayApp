/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC5
 * =================================================================
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Description:
 * Demonstrates handling of multiple booking requests fairly by
 * storing them in a queue in arrival order. No room allocation
 * is performed at this stage.
 *
 * Author: Developer
 * Version: 5.0
 */

import java.util.LinkedList;
import java.util.Queue;

public class BookMyStayAppUC5 {

    public static void main(String[] args) {
        // Create a booking request queue
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Simulate incoming booking requests
        bookingQueue.add(new Reservation("Alice", "SingleRoom"));
        bookingQueue.add(new Reservation("Bob", "DoubleRoom"));
        bookingQueue.add(new Reservation("Charlie", "SuiteRoom"));
        bookingQueue.add(new Reservation("Diana", "SingleRoom"));

        // Display queued requests in order
        System.out.println("---- Booking Request Queue (FIFO) ----");
        for (Reservation r : bookingQueue) {
            System.out.println("Guest: " + r.getGuestName() +
                    ", Requested Room: " + r.getRoomType());
        }

        System.out.println("\nAll booking requests are queued fairly. No allocation performed yet.");
    }
}

/**
 * =================================================================
 * CLASS - Reservation
 * =================================================================
 *
 * Represents a guest booking request.
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}