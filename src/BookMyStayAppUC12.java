package src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * =================================================================
 * CLASS - BookMyStayAppUC12
 * =================================================================
 * Use Case 12: Data Persistence & System Recovery
 * Demonstrates saving and restoring system state (bookings & inventory)
 * using file-based persistence.
 *
 * @author Developer
 * @version 12.0
 */
public class BookMyStayAppUC12 {

    private static final String INVENTORY_FILE = "inventory.dat";
    private static final String BOOKING_FILE = "bookings.dat";

    // In-memory state
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, String> bookings = new HashMap<>();

    public static void main(String[] args) {
        BookMyStayAppUC12 app = new BookMyStayAppUC12();

        // Initialize example state
        app.inventory.put("SingleRoom", 10);
        app.inventory.put("DoubleRoom", 5);
        app.bookings.put("R001", "SingleRoom");

        System.out.println("=== Initial State ===");
        app.printState();

        // Persist state
        app.saveState();

        // Clear in-memory state to simulate restart
        app.inventory.clear();
        app.bookings.clear();

        System.out.println("\n=== State After Clearing Memory ===");
        app.printState();

        // Restore state
        app.loadState();

        System.out.println("\n=== State After Recovery ===");
        app.printState();
    }

    /** Save inventory and bookings to files */
    public void saveState() {
        try (ObjectOutputStream invOut = new ObjectOutputStream(new FileOutputStream(INVENTORY_FILE));
             ObjectOutputStream bookOut = new ObjectOutputStream(new FileOutputStream(BOOKING_FILE))) {
            invOut.writeObject(inventory);
            bookOut.writeObject(bookings);
            System.out.println("\n[INFO] System state saved successfully.");
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to save state: " + e.getMessage());
        }
    }

    /** Load inventory and bookings from files */
    @SuppressWarnings("unchecked")
    public void loadState() {
        try (ObjectInputStream invIn = new ObjectInputStream(new FileInputStream(INVENTORY_FILE));
             ObjectInputStream bookIn = new ObjectInputStream(new FileInputStream(BOOKING_FILE))) {
            inventory = (Map<String, Integer>) invIn.readObject();
            bookings = (Map<String, String>) bookIn.readObject();
            System.out.println("\n[INFO] System state restored successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("[WARN] Persistence files not found. Starting with empty state.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[ERROR] Failed to restore state: " + e.getMessage());
        }
    }

    /** Display current inventory and booking state */
    public void printState() {
        System.out.println("Inventory: " + inventory);
        System.out.println("Bookings: " + bookings);
    }
}