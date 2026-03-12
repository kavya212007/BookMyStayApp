/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC9
 * =================================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Goal:
 * Demonstrate structured validation and error handling
 * for hotel bookings to prevent invalid system state.
 *
 * @author Developer
 * @version 9.0
 */
public class BookMyStayAppUC9 {

    public static void main(String[] args) {

        // Sample booking input
        String roomType = "Suite";  // valid options: "Single", "Double", "Suite"
        int requestedRooms = 5;     // number of rooms requested

        try {
            // Validate booking input
            BookingValidator.validateRoomType(roomType);
            BookingValidator.validateRequestedRooms(requestedRooms);

            // Simulate booking (for demo purposes, assume max 3 rooms available)
            int availableRooms = 3;
            if (requestedRooms > availableRooms) {
                throw new InvalidBookingException("Requested rooms exceed availability!");
            }

            // If all validations pass
            System.out.println("Booking confirmed for " + requestedRooms + " " + roomType + " room(s).");

        } catch (InvalidBookingException e) {
            // Handle invalid booking scenarios
            System.err.println("Booking failed: " + e.getMessage());
        } catch (Exception e) {
            // Catch-all for unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

        System.out.println("Application terminated safely.");
    }
}

/**
 * =================================================================
 * CLASS - BookingValidator
 * =================================================================
 * Performs input validation for bookings.
 */
class BookingValidator {

    public static void validateRoomType(String roomType) throws InvalidBookingException {
        if (!roomType.equalsIgnoreCase("Single") &&
                !roomType.equalsIgnoreCase("Double") &&
                !roomType.equalsIgnoreCase("Suite")) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }

    public static void validateRequestedRooms(int requestedRooms) throws InvalidBookingException {
        if (requestedRooms <= 0) {
            throw new InvalidBookingException("Number of rooms must be greater than zero.");
        }
    }
}

/**
 * =================================================================
 * CLASS - InvalidBookingException
 * =================================================================
 * Custom exception for invalid booking scenarios.
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}