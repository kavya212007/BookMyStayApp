/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC2
 * =================================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * Demonstrates object-oriented domain modeling for a hotel booking system.
 * Abstract class Room defines common attributes. Concrete classes
 * SingleRoom, DoubleRoom, and SuiteRoom extend Room.
 *
 * Room availability is stored using simple static variables to illustrate
 * state representation without advanced data structures.
 *
 * Author: Developer
 * Version: 2.1
 */
public class BookMyStayAppUC2 {

    public static void main(String[] args) {

        // Initialize room objects
        Room singleRoom = new SingleRoom(1, 1, 100.0);
        Room doubleRoom = new DoubleRoom(2, 2, 180.0);
        Room suiteRoom = new SuiteRoom(3, 4, 350.0);

        // Static availability for each room type
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Display room details and availability
        System.out.println("---- Book My Stay App UC2: Room Information ----");
        System.out.println(singleRoom);
        System.out.println("Available: " + singleRoomAvailable);
        System.out.println();

        System.out.println(doubleRoom);
        System.out.println("Available: " + doubleRoomAvailable);
        System.out.println();

        System.out.println(suiteRoom);
        System.out.println("Available: " + suiteRoomAvailable);
        System.out.println();

        System.out.println("Application execution completed successfully.");
    }
}

/**
 * =================================================================
 * ABSTRACT CLASS - Room
 * =================================================================
 * Defines common attributes and behavior for all room types.
 */
abstract class Room {
    private int roomNumber;
    private int beds;
    private double pricePerNight;

    public Room(int roomNumber, int beds, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getBeds() {
        return beds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [Room #" + roomNumber +
                ", Beds: " + beds + ", Price per night: $" + pricePerNight + "]";
    }
}

/**
 * Concrete class representing a single room.
 */
class SingleRoom extends Room {
    public SingleRoom(int roomNumber, int beds, double pricePerNight) {
        super(roomNumber, beds, pricePerNight);
    }
}

/**
 * Concrete class representing a double room.
 */
class DoubleRoom extends Room {
    public DoubleRoom(int roomNumber, int beds, double pricePerNight) {
        super(roomNumber, beds, pricePerNight);
    }
}

/**
 * Concrete class representing a suite room.
 */
class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber, int beds, double pricePerNight) {
        super(roomNumber, beds, pricePerNight);
    }
}