import java.util.*;

/**
 * =================================================================
 * MAIN CLASS - BookMyStayAppUC7
 * =================================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * Demonstrates how guests can select optional add-on services
 * for an existing reservation. Multiple services can be associated
 * with a single reservation, and additional cost is calculated.
 *
 * Core booking and inventory remain unaffected.
 *
 * @author Developer
 * @version 7.0
 */
public class BookMyStayAppUC7 {

    public static void main(String[] args) {
        // Example reservation ID
        String reservationId = "RES123";

        // Initialize the Add-On Service Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects multiple services
        manager.addServiceToReservation(reservationId, new Service("Breakfast", 15.0));
        manager.addServiceToReservation(reservationId, new Service("Airport Pickup", 30.0));
        manager.addServiceToReservation(reservationId, new Service("Spa Package", 50.0));

        // Display reservation services and total additional cost
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Selected Add-On Services:");
        List<Service> services = manager.getServicesForReservation(reservationId);
        for (Service s : services) {
            System.out.println(" - " + s.getName() + " ($" + s.getCost() + ")");
        }
        System.out.println("Total Additional Cost: $" + manager.calculateTotalCost(reservationId));
    }
}

/**
 * Represents an individual add-on service.
 */
class Service {
    private final String name;
    private final double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * Manages mapping of reservation IDs to selected add-on services.
 */
class AddOnServiceManager {
    private final Map<String, List<Service>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    /**
     * Adds a service to the reservation.
     */
    public void addServiceToReservation(String reservationId, Service service) {
        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    /**
     * Retrieves services associated with a reservation.
     */
    public List<Service> getServicesForReservation(String reservationId) {
        return reservationServices.getOrDefault(reservationId, Collections.emptyList());
    }

    /**
     * Calculates total additional cost for a reservation.
     */
    public double calculateTotalCost(String reservationId) {
        return getServicesForReservation(reservationId)
                .stream()
                .mapToDouble(Service::getCost)
                .sum();
    }
}