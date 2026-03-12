/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC3
 * =================================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * Demonstrates centralized inventory management for the Book My Stay App.
 * Room availability is stored in a single HashMap, replacing scattered variables.
 * Inventory operations are encapsulated in the RoomInventory class.
 *
 * Author: Developer
 * Version: 3.1
 */
import java.util.HashMap;
import java.util.Map;

public class BookMyStayAppUC3 {

    public static void main(String[] args) {
        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types with initial availability
        inventory.addRoomType("SingleRoom", 5);
        inventory.addRoomType("DoubleRoom", 3);
        inventory.addRoomType("SuiteRoom", 2);

        // Display current inventory state
        System.out.println("---- Book My Stay App UC3: Current Inventory ----");
        inventory.displayInventory();

        // Simulate booking a room
        System.out.println("\nBooking 1 DoubleRoom...");
        boolean booked = inventory.bookRoom("DoubleRoom");
        System.out.println("Booking successful? " + booked);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();

        System.out.println("\nApplication execution completed successfully.");
    }
}

/**
 * =================================================================
 * CLASS - RoomInventory
 * =================================================================
 *
 * Manages centralized inventory of rooms using a HashMap.
 */
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    /**
     * Adds a new room type to the inventory with the given availability.
     *
     * @param roomType Name of the room type
     * @param count    Number of available rooms
     */
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    /**
     * Retrieves the current availability of a specific room type.
     *
     * @param roomType Name of the room type
     * @return number of available rooms, or -1 if room type doesn't exist
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, -1);
    }

    /**
     * Books a room of the given type if available.
     *
     * @param roomType Name of the room type
     * @return true if booking succeeded, false if no rooms are available
     */
    public boolean bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    /**
     * Displays the current state of the inventory.
     */
    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " - Available: " + entry.getValue());
        }
    }
}