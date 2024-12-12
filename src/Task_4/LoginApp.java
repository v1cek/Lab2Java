import java.util.*;
import java.io.*;

// Singleton Logger Class
class Logger {
    private static Logger instance;
    private PrintWriter writer;

    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter("logs.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        writer.println(message);
        writer.flush();
        System.out.println("LOG: " + message);
    }

    public void close() {
        writer.close();
    }
}

// Notification Interface
interface Notification {
    void send(String message);
}

// Email Notification
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

// SMS Notification
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}

// Push Notification
class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Push Notification sent: " + message);
    }
}

// Notification Factory
class NotificationFactory {
    public static Notification createNotification(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotification();
            case "sms" -> new SMSNotification();
            case "push" -> new PushNotification();
            default -> throw new IllegalArgumentException("Invalid notification type");
        };
    }
}

// Subscriber Interface
interface Subscriber {
    void update(String news);
}

// News Agency
class NewsAgency {
    private final List<Subscriber> subscribers;

    public NewsAgency() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
        Logger.getInstance().log("Subscriber added.");
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
        Logger.getInstance().log("Subscriber removed.");
    }

    public void publishNews(String news) {
        System.out.println("Publishing news: " + news);
        for (Subscriber subscriber : subscribers) {
            subscriber.update(news);
        }
        Logger.getInstance().log("News published: " + news);
    }
}

// Main Application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Logger logger = Logger.getInstance();
        logger.log("Application started.");

        NewsAgency agency = new NewsAgency();

        while (true) {
            System.out.println("\n=== Application Menu ===");
            System.out.println("1. Send Notification");
            System.out.println("2. Manage News Subscribers");
            System.out.println("3. Publish News");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter notification type (Email/SMS/Push): ");
                    String type = scanner.nextLine();
                    System.out.println("Enter message: ");
                    String message = scanner.nextLine();

                    try {
                        Notification notification = NotificationFactory.createNotification(type);
                        notification.send(message);
                        logger.log("Notification sent: " + type + " - " + message);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("1. Add Subscriber\n2. Remove Subscriber");
                    int subChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (subChoice) {
                        case 1 -> {
                            System.out.println("Enter subscriber name: ");
                            String name = scanner.nextLine();
                            Subscriber subscriber = news -> System.out.println(name + " received news: " + news);
                            agency.addSubscriber(subscriber);
                        }
                        case 2 -> {
                            System.out.println("Enter subscriber name to remove: ");
                            String name = scanner.nextLine();
                            // Example assumes subscriber references are maintained
                        }
                        default -> System.out.println("Invalid choice");
                    }
                }
                case 3 -> {
                    System.out.println("Enter news to publish: ");
                    String news = scanner.nextLine();
                    agency.publishNews(news);
                }
                case 4 -> {
                    logger.log("Application terminated.");
                    logger.close();
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
