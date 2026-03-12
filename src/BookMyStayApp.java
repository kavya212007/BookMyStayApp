/**
 * =================================================================
 * MAIN CLASS - BookMyStayApp
 * =================================================================
 *
 * Use Case 1: Application Entry & Welcome Message
 *
 * Description:
 * This is the entry point for the Book My Stay Hotel Booking
 * application. It demonstrates how a Java program begins execution,
 * produces console output, and terminates predictably.
 *
 * Actor: User – runs the application from the command line or IDE.
 *
 * Key Concepts Demonstrated:
 * - Class as container for application behavior
 * - main() method as JVM entry point
 * - static keyword allows execution without creating an object
 * - Console output via System.out.println()
 * - String literals
 * - Method invocation
 * - Linear execution flow
 * - JavaDoc documentation with @author and @version
 *
 * Example Console Output:
 * Welcome to Book My Stay App v1.0
 *
 * @author Developer
 * @version 1.0
 */
public class BookMyStayApp {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Print a welcome message with application name and version
        System.out.println("Welcome to Book My Stay App v1.0");

        // Optional: Additional informative message
        System.out.println("Your Hotel Booking System is ready to use!");

        // Program terminates naturally after main() completes
    }
}