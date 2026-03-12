/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC6
 * =================================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * Confirms booking requests from a queue, assigns unique room IDs,
 * updates centralized inventory, and prevents double-booking.
 *
 * Author: Developer
 * Version: 6.0
 */

import java.util.*;

public class BookMyStayAppUC6 {

    public static void main(String[] args) {

        // 1. Initialize inventory (room type -> available count)
        InventoryService inventory = new InventoryService();
        inventory.addRoomType("SingleRoom", 2);
        inventory.addRoomType("DoubleRoom", 1);
        inventory.addRoomType("SuiteRoom", 1);

        // 2. Initialize booking request queue
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Alice", "SingleRoom"));
        bookingQueue.add(new Reservation("Bob", "DoubleRoom"));
        bookingQueue.add(new Reservation("Charlie", "SuiteRoom"));
        bookingQueue.add(new Reservation("Diana", "SingleRoom"));
        bookingQueue.add(new Reservation("Ethan", "SingleRoom")); // Will fail if inventory exhausted

        // 3. Process requests and allocate rooms
        RoomAllocationService allocator = new RoomAllocationService(inventory);

        System.out.println("---- Reservation Confirmation & Room Allocation ----");
        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.poll();
            String assignedRoomId = allocator.allocateRoom(r.getGuestName(), r.getRoomType());
            if (assignedRoomId != null) {
                System.out.println("Guest: " + r.getGuestName() +
                        ", Room Type: " + r.getRoomType() +
                        ", Assigned Room ID: " + assignedRoomId);
            } else {
                System.out.println("Guest: " + r.getGuestName() +
                        ", Room Type: " + r.getRoomType() +
                        " --> Allocation Failed (No Availability)");
            }
        }

        System.out.println("\nFinal Inventory Status:");
        inventory.displayInventory();
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

/**
 * =================================================================
 * CLASS - InventoryService
 * =================================================================
 *
 * Maintains centralized room inventory with available counts.
 */
class InventoryService {
    private Map<String, Integer> availableRooms = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        availableRooms.put(roomType, count);
    }

    public boolean isAvailable(String roomType) {
        return availableRooms.getOrDefault(roomType, 0) > 0;
    }

    public boolean decrementRoom(String roomType) {
        if (isAvailable(roomType)) {
            availableRooms.put(roomType, availableRooms.get(roomType) - 1);
            return true;
        }
        return false;
    }

    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : availableRooms.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + ", Available: " + entry.getValue());
        }
    }
}

/**
 * =================================================================
 * CLASS - RoomAllocationService
 * =================================================================
 *
 * Assigns unique room IDs and updates inventory safely.
 */
class RoomAllocationService {

    private InventoryService inventory;
    private Map<String, Set<String>> allocatedRooms; // RoomType -> Set of assigned IDs
    private int roomCounter;

    public RoomAllocationService(InventoryService inventory) {
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
        this.roomCounter = 1;
    }

    /**
     * Allocates a room for a guest if available.
     * @param guestName Name of the guest
     * @param roomType Requested room type
     * @return Assigned Room ID or null if allocation failed
     */
    public String allocateRoom(String guestName, String roomType) {
        if (!inventory.isAvailable(roomType)) {
            return null; // No rooms left of this type
        }

        // Generate unique room ID
        String roomId = roomType + "-" + roomCounter++;
        allocatedRooms.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);

        // Update inventory
        inventory.decrementRoom(roomType);

        return roomId;
    }
}
