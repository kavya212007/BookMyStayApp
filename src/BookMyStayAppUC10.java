
/**
 * =================================================================
 * Book My Stay App - Use Case 10
 * =================================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * This class demonstrates safe booking cancellation and inventory rollback
 * using a Stack to track released room IDs and a HashMap to manage room availability.
 *
 * Author: Developer
 * Version: 10.0
 */
import java.util.*;

public class BookMyStayAppUC10 {

    // Centralized inventory (room type -> available count)
    private static Map<String, Integer> inventory = new HashMap<>();

    // Allocated room IDs per room type
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    // Stack to track released room IDs for rollback
    private static Stack<String> releasedRoomIDs = new Stack<>();

    public static void main(String[] args) {
        // Initialize inventory
        inventory.put("Single", 3);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);

        // Initialize allocated rooms sets
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());

        // Simulate booking allocation
        allocateRoom("Single", "S101");
        allocateRoom("Double", "D201");
        allocateRoom("Suite", "SU301");

        System.out.println("Inventory before cancellation: " + inventory);
        System.out.println("Allocated rooms: " + allocatedRooms);

        // Cancel a booking
        cancelBooking("Single", "S101");

        System.out.println("Inventory after cancellation: " + inventory);
        System.out.println("Allocated rooms after cancellation: " + allocatedRooms);
        System.out.println("Released room IDs stack: " + releasedRoomIDs);
    }

    // Allocate a room
    private static void allocateRoom(String roomType, String roomID) {
        if (!inventory.containsKey(roomType)) {
            System.out.println("Invalid room type: " + roomType);
            return;
        }
        if (inventory.get(roomType) <= 0) {
            System.out.println("No rooms available for type: " + roomType);
            return;
        }
        inventory.put(roomType, inventory.get(roomType) - 1);
        allocatedRooms.get(roomType).add(roomID);
        System.out.println("Allocated room " + roomID + " of type " + roomType);
    }

    // Cancel a booking
    private static void cancelBooking(String roomType, String roomID) {
        Set<String> allocatedSet = allocatedRooms.get(roomType);
        if (allocatedSet == null || !allocatedSet.contains(roomID)) {
            System.out.println("Cannot cancel. Room ID " + roomID + " not found in allocated rooms.");
            return;
        }
        allocatedSet.remove(roomID);
        inventory.put(roomType, inventory.get(roomType) + 1);
        releasedRoomIDs.push(roomID); // track released room for rollback
        System.out.println("Cancelled booking for room " + roomID + " of type " + roomType);
    }
}