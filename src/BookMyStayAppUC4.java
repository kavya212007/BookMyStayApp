/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC4
 * =================================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * Allows guests to view available rooms and their details without
 * modifying inventory state. Search functionality is separated
 * from booking logic to ensure read-only access.
 *
 * Author: Developer
 * Version: 4.0
 */
import java.util.HashMap;
import java.util.Map;

public class BookMyStayAppUC4 {

    public static void main(String[] args) {
        // Initialize room inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("SingleRoom", 5);
        inventory.addRoomType("DoubleRoom", 3);
        inventory.addRoomType("SuiteRoom", 0); // currently unavailable

        // Initialize room details
        Map<String, Room> rooms = new HashMap<>();
        rooms.put("SingleRoom", new Room("SingleRoom", 1, 100.0));
        rooms.put("DoubleRoom", new Room("DoubleRoom", 2, 180.0));
        rooms.put("SuiteRoom", new Room("SuiteRoom", 3, 300.0));

        // Perform room search
        System.out.println("---- Available Rooms ----");
        for (String roomType : rooms.keySet()) {
            int available = inventory.getAvailability(roomType);
            if (available > 0) {
                Room room = rooms.get(roomType);
                System.out.println(roomType + ": Beds = " + room.getBeds() +
                        ", Price = $" + room.getPrice() +
                        ", Available = " + available);
            }
        }

        System.out.println("\nRoom search completed successfully.");
    }
}

/**
 * =================================================================
 * CLASS - Room
 * =================================================================
 *
 * Represents a room type with basic properties.
 */
class Room {
    private String type;
    private int beds;
    private double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }
}

/**
 * =================================================================
 * CLASS - RoomInventory
 * =================================================================
 *
 * Centralized inventory for storing available rooms.
 */
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // No booking or mutation logic here; search is read-only
}