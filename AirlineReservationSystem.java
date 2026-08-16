import java.util.*;

// Base class for inheritance hierarchy
class Person {
    String name;
    String lastName;
    int age;
    String email;
    String mobile;

    Person(String name, String lastName, int age, String email, String mobile) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.mobile = mobile;
    }

    void displayInfo() {
        System.out.println("Name: " + name + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Mobile: " + mobile);
    }

    String getFullName() {
        return name + " " + lastName;
    }
}

// Hierarchical Inheritance - Multiple classes inherit from Person
class Passenger extends Person {
    String passengerType;
    String seatNumber;
    int ticketPrice;
    int discountAmount;
    String passportNumber;
    String visaNumber;
    String foodItem;
    int foodPrice;

    Passenger(String name, String lastName, int age, String email, String mobile, String passengerType) {
        super(name, lastName, age, email, mobile);
        this.passengerType = passengerType;
        this.foodItem = "None";
        this.foodPrice = 0;
    }

    void displayInfo() {
        super.displayInfo();
        System.out.println("Passenger Type: " + passengerType);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Ticket Price: ₹" + ticketPrice);
        if (discountAmount > 0) {
            System.out.println("Discount Applied: ₹" + discountAmount);
        }
        if (!foodItem.equals("None")) {
            System.out.println("Food Order: " + foodItem + " (₹" + foodPrice + ")");
        }
    }

    int calculateDiscount(String type, int basePrice) {
        if (type.equals("Infant")) {
            return (int)(basePrice * 0.15);  // 15% discount
        } else if (type.equals("Child")) {
            return (int)(basePrice * 0.10);   // 10% discount
        } else if (type.equals("Senior")) {
            return (int)(basePrice * 0.10);  // 10% discount
        } else if (type.equals("Student")) {
            return (int)(basePrice * 0.05); // 5% discount
        } else {
            return 0; // No discount for adults
        }
    }

    int calculateFinalPrice(String type, int basePrice) {
        int discount = calculateDiscount(type, basePrice);
        return basePrice - discount;
    }
}

// Multilevel Inheritance - InternationalPassenger extends Passenger extends Person
class InternationalPassenger extends Passenger {
    InternationalPassenger(String name, String lastName, int age, String email, String mobile,
                           String passengerType, String passport, String visa) {
        super(name, lastName, age, email, mobile, passengerType);
        this.passportNumber = passport;
        this.visaNumber = visa;
    }

    void displayInfo() {
        super.displayInfo();
        System.out.println("Passport: " + passportNumber);
        System.out.println("Visa: " + visaNumber);
    }
}

// Hierarchical Inheritance - Admin also inherits from Person
class Admin extends Person {
    String adminId;
    String role;

    Admin(String name, String adminId) {
        super(name, "", 0, "", "");
        this.adminId = adminId;
    }

    void displayInfo() {
        System.out.println("Admin Name: " + name);
        System.out.println("Admin ID: " + adminId);
        System.out.println("Role: " + role);
    }
}

// Food Menu class
class FoodMenu {
    String itemName;
    int price;
    String category;

    FoodMenu(String itemName, int price, String category) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    String getItemName() { return itemName; }
    int getPrice() { return price; }

    void displayItem() {
        System.out.println(itemName + " - ₹" + price);
    }
}

// Flight class to store flight information
class Flight {
    String flightNumber;
    String flightType;
    String fromLocation;
    String toLocation;
    String flightDate;
    String flightTime;
    String flightDuration;
    int totalSeats;
    int availableSeats;
    int basePrice;
    String status;

    // Seat selection additions
    boolean[] bookedSeats;  // Track which seats are booked
    int windowSeatCharge;   // Extra charge for window seats
    int aisleSeatCharge;    // Extra charge for aisle seats

    Flight(String flightNumber, String flightType, String from, String to, String date,
           String time, String duration, int totalSeats, int basePrice, String status) {
        this.flightNumber = flightNumber;
        this.flightType = flightType;
        this.fromLocation = from;
        this.toLocation = to;
        this.flightDate = date;
        this.flightTime = time;
        this.flightDuration = duration;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.basePrice = basePrice;
        this.status = status;
        this.bookedSeats = new boolean[totalSeats + 1]; // Index 0 unused, seats 1 to totalSeats
        this.windowSeatCharge = 200;  // ₹200 extra for window seats
        this.aisleSeatCharge = 150;   // ₹150 extra for aisle seats
    }

    void displayFlightInfo() {
        System.out.println("Flight: " + flightNumber + " (" + flightType + ")");
        System.out.println("Route: " + fromLocation + " → " + toLocation);
        System.out.println("Date: " + flightDate + " | Time: " + flightTime);
        System.out.println("Duration: " + flightDuration);
        System.out.println("Seats: " + availableSeats + "/" + totalSeats + " available");
        System.out.println("Base Price: ₹" + basePrice);
        System.out.println("Status: " + status);
    }

    // Get seat type based on seat number
    // Pattern: 1W, 2M, 3A, 4W, 5M, 6A, ... (Window, Middle, Aisle repeating)
    char getSeatType(int seatNumber) {
        int position = (seatNumber - 1) % 3;
        if (position == 0) {
            return 'W';  // Window
        } else if (position == 1) {
            return 'M';  // Middle
        } else {
            return 'A';  // Aisle
        }
    }

    // Get extra charge for a seat type
    int getSeatExtraCharge(char seatType) {
        if (seatType == 'W') {
            return windowSeatCharge;
        } else if (seatType == 'A') {
            return aisleSeatCharge;
        } else {
            return 0;  // Middle seats have no extra charge
        }
    }

    // Display all seats horizontally with their status
    void displaySeats() {
        System.out.println("========================================");
        System.out.println("   SEAT MAP - Flight " + flightNumber);
        System.out.println("========================================");
        System.out.println("Total Seats: " + totalSeats + " | Available: " + availableSeats);
        System.out.println("Seat Types:");
        System.out.println("  W = Window (+₹" + windowSeatCharge + ")");
        System.out.println("  M = Middle (+₹0)");
        System.out.println("  A = Aisle (+₹" + aisleSeatCharge + ")");
        System.out.println("  [X] = Booked (Not Available)");
        System.out.println("========================================");

        // Display seats in rows of 10
        for (int i = 1; i <= totalSeats; i++) {
            char seatType = getSeatType(i);

            if (bookedSeats[i]) {
                System.out.print("[" + i + "X]   ");  // Booked seat with X
            } else {
                System.out.print(i + "" + seatType + "    ");  // Available seat
            }

            // New line after every 10 seats
            if (i % 10 == 0) {
                System.out.println();
            }
        }

        // Add newline if last row wasn't complete
        if (totalSeats % 10 != 0) {
            System.out.println();
        }

        System.out.println("========================================");
    }

    // Book a specific seat
    boolean bookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            return false;  // Invalid seat number
        }
        if (bookedSeats[seatNumber]) {
            return false;  // Already booked
        }
        bookedSeats[seatNumber] = true;
        availableSeats--;
        return true;
    }

    // Release a specific seat (for cancellation)
    void releaseSeat(int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= totalSeats && bookedSeats[seatNumber]) {
            bookedSeats[seatNumber] = false;
            availableSeats++;
        }
    }
}

// Booking class to store booking information
class Booking {
    String bookingId;
    String username;
    String flightNumber;
    Passenger[] passengers;
    int passengerCount;
    int totalPrice;
    int totalFoodPrice;
    String status;
    boolean hasFoodOrder;

    Booking(String bookingId, String username, String flightNumber, int passengerCount) {
        this.bookingId = bookingId;
        this.username = username;
        this.flightNumber = flightNumber;
        this.passengerCount = passengerCount;
        this.passengers = new Passenger[passengerCount];
        this.status = "Active";
        this.hasFoodOrder = false;
        this.totalPrice = 0;
        this.totalFoodPrice = 0;
    }

    void addPassenger(int index, Passenger passenger) {
        if (index >= 0 && index < passengerCount) {
            passengers[index] = passenger;
        }
    }

    void calculateTotalPrice() {
        totalPrice = 0;
        totalFoodPrice = 0;
        for (int i = 0; i < passengerCount; i++) {
            if (passengers[i] != null) {
                totalPrice += passengers[i].ticketPrice;
                if (!passengers[i].foodItem.equals("None")) {
                    totalFoodPrice += passengers[i].foodPrice;
                    totalPrice += passengers[i].foodPrice;
                }
            }
        }
    }

    void displayBookingInfo() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight: " + flightNumber);
        System.out.println("Passengers: " + passengerCount);
        if (hasFoodOrder && totalFoodPrice > 0) {
            System.out.println("Food Total: ₹" + totalFoodPrice);
        }
        System.out.println("Total Price: ₹" + totalPrice);
        System.out.println("Status: " + status);
    }
}

// User class to store user credentials
class User {
    String username;
    String password;  // Direct storage instead of hash

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    boolean verifyPassword(String password)
    {
        return this.password.equals(password);
    }
}

// Static Validation Class - All validation methods centralized here
class Validate {

    // Validate username - must contain only alphabets (using recursion)
    static boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return checkAllLettersRecursive(username, 0);
    }

    // Recursive helper method to check if all characters are letters
    static boolean checkAllLettersRecursive(String str, int index) {
        // Base case: reached end of string
        if (index >= str.length()) {
            return true;
        }

        // Check current character
        if (!Character.isLetter(str.charAt(index))) {
            return false;
        }

        // Recursive case: check next character
        return checkAllLettersRecursive(str, index + 1);
    }

    // Validate password - exactly 5 characters with special chars at 1st and 3rd position
    static boolean isValidPassword(String password) {
        if (password.length() != 5) {
            System.out.println("✗ Invalid password! Password must be exactly 5 characters long.");
            return false;
        }

        char firstChar = password.charAt(0);
        char thirdChar = password.charAt(2);

        // Check if 1st position has a special character
        if (Character.isLetterOrDigit(firstChar)) {
            System.out.println("✗ Invalid password! 1st position must be a special character (not alphabet or number).");
            System.out.println("Example: @a#bc");
            return false;
        }

        // Check if 3rd position has a special character
        if (Character.isLetterOrDigit(thirdChar)) {
            System.out.println("✗ Invalid password! 3rd position must be a special character (not alphabet or number).");
            System.out.println("Example: @a#bc");
            return false;
        }

        return true;
    }

    // Validate name - must contain only alphabets
    static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    // Validate visa number - must start with a capital letter
    static boolean isValidVisaNumber(String visa) {
        if (visa == null || visa.isEmpty()) {
            System.out.println("✗ Invalid visa number! Visa number cannot be empty.");
            return false;
        }

        char firstChar = visa.charAt(0);

        if (!Character.isUpperCase(firstChar)) {
            if (Character.isLetter(firstChar)) {
                System.out.println("✗ Invalid visa number! First character must be a CAPITAL letter.");
                System.out.println("You entered '" + firstChar + "' which is lowercase.");
                System.out.println("Please try again.");
            } else {
                System.out.println("✗ Invalid visa number! First character must be a CAPITAL letter.");
                System.out.println("You entered '" + firstChar + "' which is not an alphabet.");
                System.out.println("Please try again.");
            }
            return false;
        }

        return true;
    }

    // Validate date format and range
    static boolean isValidDate(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return false;
        }

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Basic range checks
        if (month < 1 || month > 12 || year < 2024 || day < 1) {
            return false;
        }

        // Days in each month
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Check for leap year
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        // Validate day against month
        if (day > daysInMonth[month - 1]) {
            return false;
        }

        return true;
    }

    // Check if date is in the future
    static boolean isFutureDate(String date) {
        String[] parts = date.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Current date: 03/02/2026
        int currentDay = 3;
        int currentMonth = 2;
        int currentYear = 2026;

        if (year > currentYear) return true;
        if (year < currentYear) return false;

        if (month > currentMonth) return true;
        if (month < currentMonth) return false;

        return day >= currentDay;
    }

    // Check if year is a leap year
    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Validate time format (HH:MM)
    static boolean isValidTime(String time) {
        if (time == null || time.length() != 5 || time.charAt(2) != ':') {
            return false;
        }

        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));

        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }

    // Validate email format
    static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Invalid email. Email cannot be empty.");
            return false;
        }

        // Check if first 3 characters are alphabets
        if (email.length() < 3) {
            System.out.println("Invalid email. Email must have at least 3 alphabets at the beginning.");
            return false;
        }

        // Validate first 3 characters are alphabets
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(email.charAt(i))) {
                System.out.println("Invalid email. First 3 characters must be alphabets only.");
                System.out.println("Example: abc@example.com");
                return false;
            }
        }

        // Check for @ and . positions
        int atPos = email.indexOf('@');
        int dotPos = email.lastIndexOf('.');

        if (atPos <= 0 || dotPos <= atPos + 1 || dotPos >= email.length() - 1) {
            System.out.println("Invalid email format. Please use format: abc@example.com");
            return false;
        }

        return true;
    }

    // Validate mobile number
    static boolean isValidMobile(String mobile) {
        if (mobile == null || mobile.length() != 10) {
            System.out.println("Invalid mobile. Mobile number must be 10 digits.");
            return false;
        }

        // Check if all characters are digits
        for (int i = 0; i < mobile.length(); i++) {
            if (!Character.isDigit(mobile.charAt(i))) {
                System.out.println("Invalid mobile. Mobile number must contain only digits.");
                return false;
            }
        }

        // Check if first digit is 0-5
        char firstDigit = mobile.charAt(0);
        if (firstDigit >= '0' && firstDigit <= '5') {
            System.out.println("Invalid mobile. Mobile number cannot start with 0, 1, 2, 3, 4, or 5.");
            return false;
        }

        return true;
    }
}
public class AirlineReservationSystem {
    Scanner scanner = new Scanner(System.in);

    // User storage
    User[] users = new User[100];
    int userCount = 0;

    // Flight storage
    Flight[] flights = new Flight[100];
    int flightCount = 0;

    // Booking storage
    Booking[] bookings = new Booking[500];
    int bookingCount = 0;

    final String ADMIN_USERNAME = "admin";
    final String ADMIN_PASSWORD = "admin123";

    // Food menus
    FoodMenu[] vegMenu;
    FoodMenu[] nonVegMenu;

    static void main(String[] args) {
        AirlineReservationSystem system = new AirlineReservationSystem();
        system.run();
    }

    void run() {
        // Initialize food menus
        initializeFoodMenus();

        // Initialize some sample flights
        initializeSampleFlights();

        System.out.println("========================================");
        System.out.println("   AIRLINE RESERVATION SYSTEM");
        System.out.println("========================================");

        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            if (choice == 1) {
                userLogin();
            } else if (choice == 2) {
                Admin admin = new Admin("admin","123456");
                adminLogin();
            } else if (choice == 3) {
                System.out.println("\nThank you for using Airline Reservation System!");
                System.exit(0);
            } else {
                System.out.println("\nInvalid choice! Please try again.");
            }
        }
    }

    void initializeFoodMenus() {
        vegMenu = new FoodMenu[6];
        vegMenu[0] = new FoodMenu("Veg Sandwich", 150, "Veg");
        vegMenu[1] = new FoodMenu("Paneer Wrap", 200, "Veg");
        vegMenu[2] = new FoodMenu("Veg Burger", 180, "Veg");
        vegMenu[3] = new FoodMenu("Pasta Alfredo", 250, "Veg");
        vegMenu[4] = new FoodMenu("Fresh Fruit Salad", 120, "Veg");
        vegMenu[5] = new FoodMenu("Veg Biryani", 280, "Veg");

        nonVegMenu = new FoodMenu[6];
        nonVegMenu[0] = new FoodMenu("Chicken Sandwich", 200, "Non-Veg");
        nonVegMenu[1] = new FoodMenu("Chicken Wrap", 250, "Non-Veg");
        nonVegMenu[2] = new FoodMenu("Chicken Burger", 230, "Non-Veg");
        nonVegMenu[3] = new FoodMenu("Grilled Chicken", 300, "Non-Veg");
        nonVegMenu[4] = new FoodMenu("Egg Sandwich", 160, "Non-Veg");
        nonVegMenu[5] = new FoodMenu("Chicken Biryani", 350, "Non-Veg");
    }

    void initializeSampleFlights() {
        // Domestic flights
        flights[flightCount++] = new Flight("AI101", "Domestic", "Mumbai", "Delhi",
                "07/02/2026", "08:00", "2h 15m", 100, 5000, "Active");
        flights[flightCount++] = new Flight("AI102", "Domestic", "Delhi", "Bangalore",
                "07/02/2026", "10:30", "2h 45m", 100, 4500, "Active");
        flights[flightCount++] = new Flight("AI103", "Domestic", "Bangalore", "Chennai",
                "07/02/2026", "14:00", "1h 10m", 100, 3500, "Active");
        flights[flightCount++] = new Flight("AI104", "Domestic", "Mumbai", "Kolkata",
                "07/02/2026", "06:00", "2h 30m", 100, 5500, "Active");
        flights[flightCount++] = new Flight("AI105", "Domestic", "Delhi", "Hyderabad",
                "07/02/2026", "16:00", "2h 20m", 100, 4000, "Active");

        // International flights
        flights[flightCount++] = new Flight("IN201", "International", "Delhi", "Dubai",
                "07/02/2026", "09:00", "3h 30m", 100, 25000, "Active");
        flights[flightCount++] = new Flight("IN202", "International", "Mumbai", "London",
                "07/02/2026", "22:00", "9h 15m", 100, 55000, "Active");
        flights[flightCount++] = new Flight("IN203", "International", "Bangalore", "Singapore",
                "07/02/2026", "11:30", "4h 20m", 100, 18000, "Active");
        flights[flightCount++] = new Flight("IN204", "International", "Delhi", "New York",
                "07/02/2026", "23:00", "15h 30m", 100, 75000, "Active");
    }

    void displayMainMenu() {
        System.out.println("\n========================================");
        System.out.println("1. User Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Exit");
        System.out.println("========================================");
    }

    void userLogin() {
        System.out.println("\n--- USER LOGIN ---");

        // Username validation - must contain only alphabets
        String username = "";
        while (true) {
            System.out.print("Username (alphabets only): ");
            username = scanner.nextLine().trim();

            if (Validate.isValidUsername(username)) {
                break;
            } else {
                System.out.println("✗ Invalid username! Username must contain only alphabets (no numbers or special characters).");
                System.out.println("Please try again.");
            }
        }

        User user = findUser(username);

        if (user != null) {
            // Existing user - validate password
            while (true) {
                System.out.print("Password: ");
                String password = scanner.nextLine();

                if (!Validate.isValidPassword(password)) {
                    continue;  // Error message already shown in validation
                }

                if (user.verifyPassword(password)) {
                    System.out.println("\n✓ Login Successful!");
                    userDashboard(username);
                    break;
                } else {
                    System.out.println("\n✗ Invalid password! Please try again.");
                }
            }
        } else {
            // New user registration
            if (userCount >= 100) {
                System.out.println("\n✗ User limit reached! Cannot register new users.");
                return;
            }

            System.out.println("\nNew user detected! Let's set up your account.");
            System.out.println("\nPassword Requirements:");
            System.out.println("  - Must be exactly 5 characters long");
            System.out.println("  - Must have a special character at 1st position");
            System.out.println("  - Must have a special character at 3rd position");
            System.out.println("  Example: @a#bc (@ at 1st, # at 3rd)");

            String password = "";
            while (true) {
                System.out.print("\nSet your password: ");
                password = scanner.nextLine();

                if (Validate.isValidPassword(password)) {
                    break;
                }
            }

            String confirmPassword = "";
            while (true) {
                System.out.print("Confirm password: ");
                confirmPassword = scanner.nextLine();

                if (!Validate.isValidPassword(confirmPassword)) {
                    continue;
                }

                if (password.equals(confirmPassword)) {
                    users[userCount++] = new User(username, password);
                    System.out.println("\n✓ Account created successfully!");
                    System.out.println("Please login again with your credentials.");
                    break;
                } else {
                    System.out.println("\n✗ Passwords do not match! Please try again.");
                }
            }
        }
    }

    User findUser(String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    void adminLogin() {
        System.out.println("--- ADMIN LOGIN ---");
        System.out.print("Admin Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Admin Password: ");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("✓ Admin Login Successful!");
            adminDashboard();
        } else {
            System.out.println("✗ Invalid admin credentials! Access denied.");
        }
    }

    void userDashboard(String username) {
        while (true) {
            System.out.println("========================================");
            System.out.println("   WELCOME, " + username.toUpperCase());
            System.out.println("========================================");
            System.out.println("1. Book Flight");
            System.out.println("2. View Bookings");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Logout");
            System.out.println("========================================");

            int choice = getIntInput("Enter your choice: ");

            if (choice == 1) {
                bookFlight(username);
            } else if (choice == 2) {
                viewBookings(username);
            } else if (choice == 3) {
                cancelBooking(username);
            } else if (choice == 4) {
                System.out.println("Logging out...");
                return;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    void bookFlight(String username) {
        System.out.println("--- BOOK FLIGHT ---");
        System.out.println("1. Domestic Flight");
        System.out.println("2. International Flight");

        int flightTypeChoice = getIntInput("Enter your choice: ");
        String selectedFlightType = "";

        if (flightTypeChoice == 1) {
            selectedFlightType = "Domestic";
        } else if (flightTypeChoice == 2) {
            selectedFlightType = "International";
        } else {
            System.out.println("✗ Invalid choice!");
            return;
        }

        // Display all available flights of selected type first
        displayAvailableFlightsByType(selectedFlightType);

        System.out.print("From (City): ");
        String from = scanner.nextLine().trim();

        System.out.print("To (City): ");
        String to = scanner.nextLine().trim();

        // Re-ask for date until valid
        String date = "";
        while (true) {
            System.out.print("Date of flying (DD/MM/YYYY): ");
            date = scanner.nextLine().trim();

            if (!Validate.isValidDate(date)) {
                System.out.println("✗ Invalid date! Please enter a valid date in DD/MM/YYYY format.");
                continue;
            }

            if (!Validate.isFutureDate(date)) {
                System.out.println("✗ Cannot book flights for past dates! Please enter a future date.");
                continue;
            }

            break;
        }

        // Search for available flights
        int[] matchingFlights = new int[100];
        int matchCount = 0;

        for (int i = 0; i < flightCount; i++) {
            if (flights[i].flightType.equals(selectedFlightType) &&
                    flights[i].fromLocation.equalsIgnoreCase(from) &&
                    flights[i].toLocation.equalsIgnoreCase(to) &&
                    flights[i].flightDate.equals(date) &&
                    flights[i].status.equals("Active") &&
                    flights[i].availableSeats > 0) {
                matchingFlights[matchCount++] = i;
            }
        }

        if (matchCount == 0) {
            System.out.println("✗ No flights available on this route for the selected date.");
            System.out.println("Searching for nearby dates...");

            // Search for nearby dates
            int[] nearbyFlights = new int[100];
            int nearbyCount = 0;

            for (int i = 0; i < flightCount; i++) {
                if (flights[i].flightType.equals(selectedFlightType) &&
                        flights[i].fromLocation.equalsIgnoreCase(from) &&
                        flights[i].toLocation.equalsIgnoreCase(to) &&
                        flights[i].status.equals("Active") &&
                        flights[i].availableSeats > 0) {
                    nearbyFlights[nearbyCount++] = i;
                }
            }

            if (nearbyCount == 0) {
                System.out.println("✗ No flights available on this route.");
                return;
            }

            System.out.println("--- Available Flights on Nearby Dates ---");
            for (int i = 0; i < nearbyCount; i++) {
                int idx = nearbyFlights[i];
                System.out.println((i + 1) + ". Flight " + flights[idx].flightNumber +
                        " | Date: " + flights[idx].flightDate +
                        " | Time: " + flights[idx].flightTime +
                        " | Duration: " + flights[idx].flightDuration +
                        " | Available Seats: " + flights[idx].availableSeats +
                        " | Base Price: ₹" + flights[idx].basePrice);
            }

            System.out.print("Do you want to proceed with any of these flights? (yes/no): ");
            String proceed = scanner.nextLine().trim().toLowerCase();

            if (!proceed.equals("yes")) {
                System.out.println("✗ Booking cancelled.");
                return;
            }

            int flightChoice = getIntInput("Select flight number (1-" + nearbyCount + "): ");
            if (flightChoice < 1 || flightChoice > nearbyCount) {
                System.out.println("✗ Invalid flight selection!");
                return;
            }

            int selectedFlightIdx = nearbyFlights[flightChoice - 1];
            processBooking(username, selectedFlightIdx, selectedFlightType);

        } else {
            System.out.println("--- Available Flights ---");
            for (int i = 0; i < matchCount; i++) {
                int idx = matchingFlights[i];
                System.out.println((i + 1) + ". Flight " + flights[idx].flightNumber +
                        " | Time: " + flights[idx].flightTime +
                        " | Duration: " + flights[idx].flightDuration +
                        " | Available Seats: " + flights[idx].availableSeats +
                        " | Base Price: ₹" + flights[idx].basePrice);
            }

            int flightChoice = getIntInput("Select flight number (1-" + matchCount + "): ");
            if (flightChoice < 1 || flightChoice > matchCount) {
                System.out.println("✗ Invalid flight selection!");
                return;
            }

            int selectedFlightIdx = matchingFlights[flightChoice - 1];
            processBooking(username, selectedFlightIdx, selectedFlightType);
        }
    }

    void displayAvailableFlightsByType(String flightType) {
        System.out.println("========================================");
        System.out.println("   AVAILABLE " + flightType.toUpperCase() + " FLIGHTS");
        System.out.println("========================================");

        boolean foundFlights = false;
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].flightType.equals(flightType) &&
                    flights[i].status.equals("Active") &&
                    flights[i].availableSeats > 0) {
                foundFlights = true;
                flights[i].displayFlightInfo();
                System.out.println("----------------------------------------");
            }
        }

        if (!foundFlights) {
            System.out.println("No " + flightType.toLowerCase() + " flights available at the moment.");
        }
    }

    void processBooking(String username, int flightIdx, String flightType) {
        Flight selectedFlight = flights[flightIdx];

        int numPassengers = getIntInput("Enter number of passengers: ");

        if (numPassengers <= 0) {
            System.out.println("✗ Invalid number of passengers!");
            return;
        }

        if (numPassengers > selectedFlight.availableSeats) {
            System.out.println("✗ Not enough seats available! Only " + selectedFlight.availableSeats + " seats remaining.");
            return;
        }

        // Create new booking
        String bookingId = "BK" + (System.currentTimeMillis() % 100000);
        Booking booking = new Booking(bookingId, username, selectedFlight.flightNumber, numPassengers);

        // Collect passenger details
        for (int i = 0; i < numPassengers; i++) {
            System.out.println("--- Passenger " + (i + 1) + " Details ---");

            // First name validation - only alphabets
            String name = "";
            while (true) {
                System.out.print("First Name (alphabets only): ");
                name = scanner.nextLine().trim();

                if (Validate.isValidName(name)) {
                    break;
                } else {
                    System.out.println("✗ Invalid first name! First name must contain only alphabets (no numbers or special characters).");
                    System.out.println("Please try again.");
                }
            }

            // Last name validation - only alphabets
            String lastName = "";
            while (true) {
                System.out.print("Last Name (alphabets only): ");
                lastName = scanner.nextLine().trim();

                if (Validate.isValidName(lastName)) {
                    break;
                } else {
                    System.out.println("✗ Invalid last name! Last name must contain only alphabets (no numbers or special characters).");
                    System.out.println("Please try again.");
                }
            }

            String email = getValidatedInput("Email: ", "email");
            String mobile = getValidatedInput("Mobile (10 digits): ", "mobile");
            int age = getValidatedAge();

            // Determine passenger type
            String passengerType = determinePassengerType(age);

            // Create appropriate passenger object
            Passenger passenger;
            if (flightType.equals("International")) {
                String passport = getValidatedInput("Passport Number (min 6 characters): ", "passport");

                // Visa number validation - must start with capital letter
                String visa = "";
                while (true) {
                    System.out.print("Visa Number for " + selectedFlight.toLocation + " (must start with a CAPITAL letter): ");
                    visa = scanner.nextLine().trim();

                    if (Validate.isValidVisaNumber(visa)) {
                        break;
                    }
                }

                passenger = new InternationalPassenger(name, lastName, age, email, mobile, passengerType, passport, visa);
            } else {
                passenger = new Passenger(name, lastName, age, email, mobile, passengerType);
            }

            // Calculate price with discount
            int basePrice = selectedFlight.basePrice;
            int discountAmount = passenger.calculateDiscount(passengerType, basePrice);
            int ticketPrice = passenger.calculateFinalPrice(passengerType, basePrice);

            passenger.discountAmount = discountAmount;
            passenger.ticketPrice = ticketPrice;

            // Display seat selection
            System.out.println("--- Seat Selection for Passenger " + (i + 1) + " ---");

            // Seat selection loop - continue until user confirms
            boolean seatConfirmed = false;
            while (!seatConfirmed) {
                selectedFlight.displaySeats();

                // Display pricing information
                System.out.println("SEAT PRICING INFORMATION:");
                System.out.println("Base Fare: ₹" + passenger.ticketPrice);
                System.out.println("Window Seat (W): Extra ₹" + selectedFlight.windowSeatCharge);
                System.out.println("Aisle Seat (A): Extra ₹" + selectedFlight.aisleSeatCharge);
                System.out.println("Middle Seat (M): No extra charge");
                System.out.println("");

                // Select seat with validation
                int seatNumber = 0;
                while (true) {
                    System.out.print("Enter seat number (1-" + selectedFlight.totalSeats + ") to book: ");
                    String seatInput = scanner.nextLine().trim();

                    // Check if input is a valid number
                    boolean isNumber = true;
                    for (int j = 0; j < seatInput.length(); j++) {
                        if (!Character.isDigit(seatInput.charAt(j))) {
                            isNumber = false;
                            break;
                        }
                    }

                    if (!isNumber) {
                        System.out.println("✗ Invalid input: '" + seatInput + "' is not a valid number");
                        System.out.println("Please enter a valid seat number.");
                        continue;
                    }

                    seatNumber = Integer.parseInt(seatInput);

                    // Check if seat number is within range
                    if (seatNumber < 1 || seatNumber > selectedFlight.totalSeats) {
                        System.out.println("✗ Invalid seat number: Seat " + seatNumber + " does not exist");
                        System.out.println("Please enter a number between 1 and " + selectedFlight.totalSeats);
                        continue;
                    }

                    // Check if seat is already booked
                    if (selectedFlight.bookedSeats[seatNumber]) {
                        System.out.println("✗ Seat " + seatNumber + " is already booked (marked with X)");
                        System.out.println("Please select a different seat.");
                        selectedFlight.displaySeats();
                        continue;
                    }

                    // Valid seat selected
                    break;
                }

                // Get seat type and calculate extra charge
                char seatType = selectedFlight.getSeatType(seatNumber);
                int seatExtraCharge = selectedFlight.getSeatExtraCharge(seatType);

                // Show price breakdown before confirmation
                System.out.println("========================================");
                System.out.println("PRICE BREAKDOWN");
                System.out.println("========================================");
                System.out.println("Seat Number: " + seatNumber + "" + seatType);
                System.out.println("Base Fare: ₹" + passenger.ticketPrice);
                if (seatExtraCharge > 0) {
                    String seatTypeName = (seatType == 'W') ? "Window" : "Aisle";
                    System.out.println(seatTypeName + " Seat Extra Charge: ₹" + seatExtraCharge);
                } else {
                    System.out.println("Middle Seat Extra Charge: ₹0");
                }
                System.out.println("========================================");
                System.out.println("TOTAL PRICE: ₹" + (passenger.ticketPrice + seatExtraCharge));
                System.out.println("========================================");

                System.out.print("Confirm seat selection? (yes/no): ");
                String confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("yes")) {
                    // Book the seat
                    selectedFlight.bookSeat(seatNumber);
                    passenger.seatNumber = seatNumber + "" + seatType;
                    passenger.ticketPrice += seatExtraCharge;
                    seatConfirmed = true;  // Exit the seat selection loop
                } else {
                    System.out.println("Seat selection cancelled. Please select another seat.");
                    // Loop continues, re-asking for seat number
                }
            }

            booking.addPassenger(i, passenger);
        }

        // Food ordering
        System.out.print("Would you like to order food for the flight? (yes/no): ");
        String wantFood = scanner.nextLine().trim().toLowerCase();

        if (wantFood.equals("yes")) {
            booking.hasFoodOrder = true;

            System.out.println("--- FOOD MENU ---");
            System.out.println("1. Vegetarian Menu");
            System.out.println("2. Non-Vegetarian Menu");

            int menuChoice = getIntInput("Enter your choice: ");

            FoodMenu[] selectedMenu;
            if (menuChoice == 1) {
                selectedMenu = vegMenu;
                System.out.println("--- VEGETARIAN MENU ---");
            } else if (menuChoice == 2) {
                selectedMenu = nonVegMenu;
                System.out.println("--- NON-VEGETARIAN MENU ---");
            } else {
                System.out.println("✗ Invalid choice! Skipping food order.");
                booking.hasFoodOrder = false;
                selectedMenu = null;
            }

            if (selectedMenu != null) {
                for (int i = 0; i < selectedMenu.length; i++) {
                    System.out.print((i + 1) + ". ");
                    selectedMenu[i].displayItem();
                }

                // Order food for each passenger
                for (int i = 0; i < numPassengers; i++) {
                    Passenger passenger = booking.passengers[i];
                    System.out.println("--- Food for Passenger " + (i + 1) + " (" + passenger.getFullName() + ") ---");
                    System.out.print("Select food item (1-" + selectedMenu.length + ") or 0 to skip: ");

                    int foodChoice = getIntInput("");

                    if (foodChoice > 0 && foodChoice <= selectedMenu.length) {
                        FoodMenu selectedItem = selectedMenu[foodChoice - 1];
                        passenger.foodItem = selectedItem.getItemName();
                        passenger.foodPrice = selectedItem.getPrice();
                        System.out.println("✓ Added: " + selectedItem.getItemName() + " - ₹" + selectedItem.getPrice());
                    } else if (foodChoice == 0) {
                        passenger.foodItem = "None";
                        passenger.foodPrice = 0;
                        System.out.println("✓ No food ordered for this passenger.");
                    } else {
                        System.out.println("✗ Invalid choice! No food ordered for this passenger.");
                        passenger.foodItem = "None";
                        passenger.foodPrice = 0;
                    }
                }
            }
        }

        // Calculate total price
        booking.calculateTotalPrice();

        // Display booking summary
        displayBookingSummary(booking, selectedFlight);

        System.out.print("Confirm booking? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            bookings[bookingCount++] = booking;
            System.out.println("✓ Booking confirmed successfully!");

            // Display tickets
            displayTickets(booking, selectedFlight);
        } else {
            // Release all booked seats
            for (int i = 0; i < numPassengers; i++) {
                if (booking.passengers[i] != null && booking.passengers[i].seatNumber != null) {
                    // Extract seat number from seatNumber string (e.g., "15W" -> 15)
                    String seatStr = booking.passengers[i].seatNumber;
                    int seatNum = 0;
                    for (int j = 0; j < seatStr.length(); j++) {
                        if (Character.isDigit(seatStr.charAt(j))) {
                            seatNum = seatNum * 10 + (seatStr.charAt(j) - '0');
                        }
                    }
                    selectedFlight.releaseSeat(seatNum);
                }
            }
            System.out.println("✗ Booking cancelled.");
        }
    }

    String determinePassengerType(int age) {
        if (age >= 1 && age < 2) {
            return "Infant";
        } else if (age >= 2 && age < 6) {
            return "Child";
        } else if (age >= 60) {
            return "Senior";
        } else {
            System.out.print("Are you a student? (yes/no): ");
            String isStudent = scanner.nextLine().trim().toLowerCase();
            if (isStudent.equals("yes")) {
                return "Student";
            } else {
                return "Adult";
            }
        }
    }

    int getValidatedAge() {
        while (true) {
            int age = getIntInput("Age: ");

            // Age 0 is invalid
            if (age == 0) {
                System.out.println("Invalid age. Age cannot be 0. Please enter a valid age.");
                continue;
            }

            if (age < 0 || age > 120) {
                System.out.println("Invalid age. Please enter age between 1-120.");
                continue;
            }

            // Infant confirmation for ages 1-2
            if (age >= 1 && age <= 2) {
                while (true) {
                    System.out.print("⚠ Are you sure you want to book a seat for an infant (age " + age + ")? (yes/no): ");
                    String infantConfirm = scanner.nextLine().trim().toLowerCase();

                    if (infantConfirm.equals("yes")) {
                        return age;
                    } else if (infantConfirm.equals("no")) {
                        System.out.println("Please re-enter passenger age.");
                        break;
                    } else {
                        System.out.println("Please enter 'yes' or 'no'.");
                    }
                }
            } else {
                return age;
            }
        }
    }

    String getValidatedInput(String prompt, String type) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (type.equals("email")) {
                if (Validate.isValidEmail(input)) {
                    return input;
                }
                System.out.println("Invalid email format. Please try again.");
            } else if (type.equals("mobile")) {
                if (Validate.isValidMobile(input)) {
                    return input;
                }
            } else if (type.equals("passport")) {
                if (input.length() >= 6) {
                    return input;
                }
                System.out.println("Invalid passport. Must be at least 6 characters.");
            }
        }
    }

    void displayBookingSummary(Booking booking, Flight flight) {
        System.out.println("========================================");
        System.out.println("   BOOKING SUMMARY");
        System.out.println("========================================");
        System.out.println("Booking ID: " + booking.bookingId);
        System.out.println("Flight: " + flight.flightNumber);
        System.out.println("From: " + flight.fromLocation);
        System.out.println("To: " + flight.toLocation);
        System.out.println("Date: " + flight.flightDate);
        System.out.println("Time: " + flight.flightTime);
        System.out.println("Duration: " + flight.flightDuration);
        System.out.println("--- Passenger Details ---");

        for (int i = 0; i < booking.passengerCount; i++) {
            Passenger p = booking.passengers[i];
            System.out.println("Passenger " + (i + 1) + ":");
            System.out.println("  Full Name: " + p.getFullName());
            System.out.println("  Type: " + p.passengerType);
            System.out.println("  Seat: " + p.seatNumber);
            System.out.println("  Base Price: ₹" + flight.basePrice);

            if (p.discountAmount > 0) {
                System.out.println("  Discount (" + p.passengerType + " - " +
                        (int)((p.discountAmount * 100.0) / flight.basePrice) + "%): -₹" + p.discountAmount);
            }

            System.out.println("  Ticket Price: ₹" + p.ticketPrice);
            if (booking.hasFoodOrder && !p.foodItem.equals("None")) {
                System.out.println("  Food: " + p.foodItem + " - ₹" + p.foodPrice);
            }
        }

        System.out.println("========================================");
        if (booking.hasFoodOrder && booking.totalFoodPrice > 0) {
            System.out.println("Flight Tickets Total: ₹" + (booking.totalPrice - booking.totalFoodPrice));
            System.out.println("Food Total: ₹" + booking.totalFoodPrice);
            System.out.println("----------------------------------------");
        }
        System.out.println("Total Price: ₹" + booking.totalPrice);
        System.out.println("========================================");
    }

    void displayTickets(Booking booking, Flight flight) {
        System.out.println("========================================");
        System.out.println("   FLIGHT TICKETS");
        System.out.println("========================================");
        System.out.println("Booking ID: " + booking.bookingId);
        System.out.println("Flight Number: " + flight.flightNumber);
        System.out.println("From: " + flight.fromLocation + " → To: " + flight.toLocation);
        System.out.println("Date: " + flight.flightDate + " | Time: " + flight.flightTime);
        System.out.println("Duration: " + flight.flightDuration);

        System.out.println("--- Passengers ---");
        for (int i = 0; i < booking.passengerCount; i++) {
            Passenger p = booking.passengers[i];
            System.out.println("Ticket " + (i + 1) + ":");
            System.out.println("  Full Name: " + p.getFullName());
            System.out.println("  Seat: " + p.seatNumber);
            System.out.println("  Type: " + p.passengerType);
            System.out.println("  Base Price: ₹" + flight.basePrice);

            if (p.discountAmount > 0) {
                System.out.println("  Discount (" + p.passengerType + " - " +
                        (int)((p.discountAmount * 100.0) / flight.basePrice) + "%): -₹" + p.discountAmount);
            }

            System.out.println("  Ticket Price: ₹" + p.ticketPrice);
            if (!p.foodItem.equals("None")) {
                System.out.println("  Food: " + p.foodItem + " (₹" + p.foodPrice + ")");
            }
        }

        System.out.println("========================================");
        System.out.println("Total Amount: ₹" + booking.totalPrice);
        System.out.println("========================================");
    }

    void viewBookings(String username) {
        System.out.println("========================================");
        System.out.println("   YOUR BOOKINGS");
        System.out.println("========================================");

        boolean hasBookings = false;

        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].username.equals(username) && bookings[i].status.equals("Active")) {
                hasBookings = true;
                Flight flight = findFlightByNumber(bookings[i].flightNumber);

                bookings[i].displayBookingInfo();
                if (flight != null) {
                    System.out.println("Route: " + flight.fromLocation + " → " + flight.toLocation);
                    System.out.println("Date: " + flight.flightDate + " | Time: " + flight.flightTime);
                    System.out.println("Duration: " + flight.flightDuration);
                    System.out.println("Flight Status: " + flight.status);
                }
                System.out.println("----------------------------------------");
            }
        }

        if (!hasBookings) {
            System.out.println("No active bookings found.");
        }
    }

    void cancelBooking(String username) {
        System.out.println("--- CANCEL BOOKING ---");
        System.out.print("Enter booking ID: ");
        String bookingId = scanner.nextLine().trim();

        Booking booking = null;
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].bookingId.equals(bookingId) && bookings[i].username.equals(username)) {
                booking = bookings[i];
                break;
            }
        }

        if (booking == null) {
            System.out.println("✗ Booking not found!");
            return;
        }

        if (booking.status.equals("Cancelled")) {
            System.out.println("✗ This booking is already cancelled!");
            return;
        }

        System.out.println("Booking Details:");
        System.out.println("Flight: " + booking.flightNumber);
        System.out.println("Passengers: " + booking.passengerCount);
        System.out.println("Total Price: ₹" + booking.totalPrice);

        System.out.print("Are you sure you want to cancel this booking? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            booking.status = "Cancelled";

            // Release seats
            Flight flight = findFlightByNumber(booking.flightNumber);
            if (flight != null) {
                // Release each booked seat
                for (int i = 0; i < booking.passengerCount; i++) {
                    if (booking.passengers[i] != null && booking.passengers[i].seatNumber != null) {
                        // Extract seat number from seatNumber string (e.g., "15W" -> 15)
                        String seatStr = booking.passengers[i].seatNumber;
                        int seatNum = 0;
                        for (int j = 0; j < seatStr.length(); j++) {
                            if (Character.isDigit(seatStr.charAt(j))) {
                                seatNum = seatNum * 10 + (seatStr.charAt(j) - '0');
                            }
                        }
                        flight.releaseSeat(seatNum);
                    }
                }
            }

            // Calculate refund (80% refund as per norms)
            int refund = (int)(booking.totalPrice * 0.8);

            System.out.println("✓ Booking cancelled successfully!");
            System.out.println("Refund Amount (80%): ₹" + refund);
            System.out.println("Refund will be processed within 7 working days.");
        } else {
            System.out.println("✗ Cancellation aborted.");
        }
    }

    void adminDashboard() {
        while (true) {
            System.out.println("========================================");
            System.out.println("   ADMIN DASHBOARD");
            System.out.println("========================================");
            System.out.println("1. Schedule New Flight");
            System.out.println("2. Update Flight");
            System.out.println("3. Cancel Flight");
            System.out.println("4. Delay Flight");
            System.out.println("5. View All Flights");
            System.out.println("6. View All Bookings");
            System.out.println("7. View All Users");
            System.out.println("8. Logout");
            System.out.println("========================================");

            int choice = getIntInput("Enter your choice: ");

            if (choice == 1) {
                scheduleNewFlight();
            } else if (choice == 2) {
                updateFlight();
            } else if (choice == 3) {
                cancelFlight();
            } else if (choice == 4) {
                delayFlight();
            } else if (choice == 5) {
                viewAllFlights();
            } else if (choice == 6) {
                viewAllBookings();
            } else if (choice == 7) {
                viewAllUsers();
            } else if (choice == 8) {
                System.out.println("Logging out...");
                return;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    void scheduleNewFlight() {
        System.out.println("--- SCHEDULE NEW FLIGHT ---");

        if (flightCount >= 100) {
            System.out.println("✗ Flight limit reached!");
            return;
        }

        System.out.print("Flight Number: ");
        String flightNo = scanner.nextLine().trim().toUpperCase();

        // Check if flight already exists
        if (findFlightByNumber(flightNo) != null) {
            System.out.println("✗ Flight number already exists!");
            return;
        }

        System.out.println("Flight Type:");
        System.out.println("1. Domestic");
        System.out.println("2. International");
        int typeChoice = getIntInput("Enter choice: ");
        String type = (typeChoice == 1) ? "Domestic" : "International";

        System.out.print("From (City): ");
        String from = scanner.nextLine().trim();

        System.out.print("To (City): ");
        String to = scanner.nextLine().trim();

        // Re-ask for date until valid
        String date = "";
        while (true) {
            System.out.print("Date (DD/MM/YYYY): ");
            date = scanner.nextLine().trim();

            if (!Validate.isValidDate(date)) {
                System.out.println("✗ Invalid date! Please enter a valid date in DD/MM/YYYY format.");
                continue;
            }

            if (!Validate.isFutureDate(date)) {
                System.out.println("✗ Cannot schedule flights for past dates! Please enter a future date.");
                continue;
            }

            break;
        }

        String time = "";
        while (true) {
            System.out.print("Time (HH:MM): ");
            time = scanner.nextLine().trim();

            if (Validate.isValidTime(time)) {
                break;
            } else {
                System.out.println("✗ Invalid time! Please enter time in HH:MM format (00:00 to 23:59).");
            }
        }

        System.out.print("Duration (e.g., 2h 30m): ");
        String duration = scanner.nextLine().trim();

        int seats = getIntInput("Total Seats: ");
        boolean b = true;
        int basePrice =getIntInput("Base Price (₹): ");;
        while(b){
         basePrice = getIntInput("Base Price (₹): ");
            if(basePrice<4000){
                System.out.println("Base Price should be atleast 4000");
            }
            else
                break;
        }

        flights[flightCount++] = new Flight(flightNo, type, from, to, date, time, duration,
                seats, basePrice, "Active");

        System.out.println("✓ Flight scheduled successfully!");
    }

    void updateFlight() {
        System.out.println("--- UPDATE FLIGHT ---");
        System.out.print("Enter Flight Number: ");
        String flightNo = scanner.nextLine().trim().toUpperCase();

        Flight flight = findFlightByNumber(flightNo);

        if (flight == null) {
            System.out.println("✗ Flight not found!");
            return;
        }

        System.out.println("Current Flight Details:");
        flight.displayFlightInfo();

        System.out.println("What do you want to update?");
        System.out.println("1. Date");
        System.out.println("2. Time");
        System.out.println("3. Price");
        System.out.println("4. Duration");

        int choice = getIntInput("Enter choice: ");

        if (choice == 1) {
            String newDate = "";
            while (true) {
                System.out.print("New Date (DD/MM/YYYY): ");
                newDate = scanner.nextLine().trim();

                if (!Validate.isValidDate(newDate)) {
                    System.out.println("✗ Invalid date! Please enter a valid date in DD/MM/YYYY format.");
                    continue;
                }

                if (!Validate.isFutureDate(newDate)) {
                    System.out.println("✗ Cannot schedule flights for past dates! Please enter a future date.");
                    continue;
                }

                flight.flightDate = newDate;
                notifyPassengers(flightNo, "Date changed to " + newDate);
                System.out.println("\n✓ Date updated successfully!");
                break;
            }
        } else if (choice == 2) {
            String newTime = "";
            while (true) {
                System.out.print("New Time (HH:MM): ");
                newTime = scanner.nextLine().trim();

                if (Validate.isValidTime(newTime)) {
                    flight.flightTime = newTime;
                    notifyPassengers(flightNo, "Time changed to " + newTime);
                    System.out.println("✓ Time updated successfully!");
                    break;
                } else {
                    System.out.println("✗ Invalid time! Please enter time in HH:MM format (00:00 to 23:59).");
                }
            }
        } else if (choice == 3) {
            int newPrice = getIntInput("New Base Price (₹): ");
            flight.basePrice = newPrice;
            System.out.println("✓ Price updated successfully!");
        } else if (choice == 4) {
            System.out.print("New Duration (e.g., 2h 30m): ");
            String newDuration = scanner.nextLine().trim();
            flight.flightDuration = newDuration;
            System.out.println("✓ Duration updated successfully!");
        } else {
            System.out.println("✗ Invalid choice!");
        }
    }

    void cancelFlight() {
        System.out.println("--- CANCEL FLIGHT ---");
        System.out.print("Enter Flight Number: ");
        String flightNo = scanner.nextLine().trim().toUpperCase();

        Flight flight = findFlightByNumber(flightNo);

        if (flight == null) {
            System.out.println("✗ Flight not found!");
            return;
        }

        System.out.print("Are you sure you want to cancel flight " + flightNo + "? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            flight.status = "Cancelled";

            // Process refunds for all bookings
            for (int i = 0; i < bookingCount; i++) {
                if (bookings[i].flightNumber.equals(flightNo) && bookings[i].status.equals("Active")) {
                    bookings[i].status = "Cancelled";
                    int refund = bookings[i].totalPrice; // Full refund for airline cancellation
                    System.out.println("Refund of ₹" + refund + " processed for booking " + bookings[i].bookingId);
                }
            }

            notifyPassengers(flightNo, "Flight CANCELLED. Full refund will be processed.");
            System.out.println("✓ Flight cancelled successfully!");
        } else {
            System.out.println("✗ Cancellation aborted.");
        }
    }

    void delayFlight() {
        System.out.println("--- DELAY FLIGHT ---");
        System.out.print("Enter Flight Number: ");
        String flightNo = scanner.nextLine().trim().toUpperCase();

        Flight flight = findFlightByNumber(flightNo);

        if (flight == null) {
            System.out.println("✗ Flight not found!");
            return;
        }

        String newTime = "";
        while (true) {
            System.out.print("New Time (HH:MM): ");
            newTime = scanner.nextLine().trim();

            if (Validate.isValidTime(newTime)) {
                flight.status = "Delayed";
                flight.flightTime = newTime;
                notifyPassengers(flightNo, "Flight DELAYED. New departure time: " + newTime);
                System.out.println("✓ Flight delay updated successfully!");
                break;
            } else {
                System.out.println("✗ Invalid time! Please enter time in HH:MM format (00:00 to 23:59).");
            }
        }
    }

    void viewAllFlights() {
        System.out.println("========================================");
        System.out.println("   ALL FLIGHTS");
        System.out.println("========================================");

        for (int i = 0; i < flightCount; i++) {
            flights[i].displayFlightInfo();
            System.out.println("----------------------------------------");
        }
    }

    void viewAllBookings() {
        System.out.println("========================================");
        System.out.println("   ALL BOOKINGS");
        System.out.println("========================================");

        if (bookingCount == 0) {
            System.out.println("No bookings in the system.");
            return;
        }

        for (int i = 0; i < bookingCount; i++) {
            bookings[i].displayBookingInfo();
            System.out.println("----------------------------------------");
        }
    }

    void viewAllUsers() {
        System.out.println("========================================");
        System.out.println("   REGISTERED USERS");
        System.out.println("========================================");

        if (userCount == 0) {
            System.out.println("No users registered yet.");
        } else {
            for (int i = 0; i < userCount; i++) {
                System.out.println((i + 1) + ". " + users[i].username);
            }
        }
    }

    void notifyPassengers(String flightNo, String message) {
        System.out.println("--- NOTIFICATIONS SENT ---");

        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].flightNumber.equals(flightNo) && bookings[i].status.equals("Active")) {
                System.out.println("✉ User: " + bookings[i].username + " | Message: " + message);
            }
        }
    }

    Flight findFlightByNumber(String flightNo) {
        for (int i = 0; i < flightCount; i++) {
            if (flights[i].flightNumber.equals(flightNo)) {
                return flights[i];
            }
        }
        return null;
    }

    int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        }
    }
}