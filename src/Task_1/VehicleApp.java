import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> vehicles = new ArrayList<>();

        while (true) {
            System.out.println("\n=== Vehicle Management System ===");
            System.out.println("1. Create a Car");
            System.out.println("2. Create a Bike");
            System.out.println("3. Create a Truck");
            System.out.println("4. Perform actions on vehicles");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    vehicles.add(new Car(make, model, year));
                    System.out.println("Car created successfully.");
                }
                case 2 -> {
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    vehicles.add(new Bike(make, model, year));
                    System.out.println("Bike created successfully.");
                }
                case 3 -> {
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    vehicles.add(new Truck(make, model, year));
                    System.out.println("Truck created successfully.");
                }
                case 4 -> {
                    if (vehicles.isEmpty()) {
                        System.out.println("No vehicles available.");
                        break;
                    }

                    for (int i = 0; i < vehicles.size(); i++) {
                        System.out.println((i + 1) + ". " + vehicles.get(i));
                    }

                    System.out.print("Select a vehicle to perform actions: ");
                    int vehicleIndex = scanner.nextInt() - 1;

                    if (vehicleIndex < 0 || vehicleIndex >= vehicles.size()) {
                        System.out.println("Invalid selection.");
                        break;
                    }

                    Vehicle selectedVehicle = vehicles.get(vehicleIndex);
                    System.out.println("1. Start");
                    System.out.println("2. Stop");
                    if (selectedVehicle instanceof Refuelable) {
                        System.out.println("3. Refuel");
                    }

                    System.out.print("Select an action: ");
                    int action = scanner.nextInt();

                    switch (action) {
                        case 1 -> selectedVehicle.start();
                        case 2 -> selectedVehicle.stop();
                        case 3 -> {
                            if (selectedVehicle instanceof Refuelable refuelable) {
                                refuelable.refuel();
                            } else {
                                System.out.println("This vehicle cannot refuel.");
                            }
                        }
                        default -> System.out.println("Invalid action.");
                    }
                }
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
