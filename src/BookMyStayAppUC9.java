/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC9
 * =================================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Goal: Strengthen system reliability by validating booking input
 * and preventing invalid or inconsistent system states.
 *
 * @author Developer
 * @version 9.0
 */
import java.util.*;

public class BookMyStayAppUC9 {

    public static void main(String[] args) {
        System.out.println("=== Welcome to Book My Stay App v9.0 ===");

        // Example guest booking input
        String roomType = "Double";
        int requestedRooms = 2;

        // Initialize inventory for demonstration
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 1);  // intentionally low to trigger validation
        inventory.addRoomType("Suite", 2);

        try {
            // Validate booking
            validateBooking(roomType, requestedRooms, inventory);

            // If validation passes, confirm booking
            System.out.println("Booking successful for " + requestedRooms + " " + roomType + " room(s).");
        } catch (InvalidBookingException e) {
            // Handle invalid booking gracefully
            System.out.println("Booking failed: " + e.getMessage());
        }

        System.out.println("=== Application Terminated ===");
    }

    /**
     * Validates a booking request against the current inventory.
     *
     * @param roomType Type of room requested
     * @param count Number of rooms requested
     * @param inventory The current RoomInventory
     * @throws InvalidBookingException if input is invalid or rooms unavailable
     */
    private static void validateBooking(String roomType, int count, RoomInventory inventory)
            throws InvalidBookingException {

        if (roomType == null || roomType.isEmpty()) {
            throw new InvalidBookingException("Room type must be specified.");
        }

        if (count <= 0) {
            throw new InvalidBookingException("Requested rooms must be at least 1.");
        }

        int available = inventory.getAvailability(roomType);
        if (available < count) {
            throw new InvalidBookingException(
                    "Only " + available + " " + roomType + " room(s) available. Requested: " + count);
        }
    }
}

/**
 * =================================================================
 * CLASS - RoomInventory
 * =================================================================
 *
 * Maintains room availability using a centralized map.
 */
class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public void addRoomType(String type, int quantity) {
        rooms.put(type, quantity);
    }

    public int getAvailability(String type) {
        return rooms.getOrDefault(type, 0);
    }

    public void updateAvailability(String type, int quantity) {
        rooms.put(type, quantity);
    }
}

/**
 * =================================================================
 * CLASS - InvalidBookingException
 * =================================================================
 *
 * Custom exception for invalid booking requests.
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}