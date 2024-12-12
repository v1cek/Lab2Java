import java.util.*;

// Клас Library<T extends Media>
class Library<T extends Media> {
    private List<T> resources;

    public Library() {
        resources = new ArrayList<>();
    }

    public void addResource(T resource) {
        resources.add(resource);
        System.out.println(resource.getTitle() + " has been added to the library.");
    }

    public void removeResource(String title) {
        resources.removeIf(resource -> resource.getTitle().equalsIgnoreCase(title));
        System.out.println(title + " has been removed from the library.");
    }

    public void searchByTitle(String title) {
        System.out.println("Search results for title: " + title);
        for (T resource : resources) {
            if (resource.getTitle().equalsIgnoreCase(title)) {
                System.out.println(resource);
            }
        }
    }

    public void displayAllResources() {
        System.out.println("All resources in the library:");
        for (T resource : resources) {
            System.out.println(resource);
        }
    }
}

// Базовий клас Media
abstract class Media {
    private String title;

    public Media(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Media [title=" + title + "]";
    }
}

// Клас Book
class Book extends Media {
    private String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [title=" + getTitle() + ", author=" + author + "]";
    }
}

// Клас Magazine
class Magazine extends Media {
    private String issue;

    public Magazine(String title, String issue) {
        super(title);
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "Magazine [title=" + getTitle() + ", issue=" + issue + "]";
    }
}

// Клас DVD
class DVD extends Media {
    private String director;

    public DVD(String title, String director) {
        super(title);
        this.director = director;
    }

    @Override
    public String toString() {
        return "DVD [title=" + getTitle() + ", director=" + director + "]";
    }
}

// Головний клас для тестування
public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library<Media> library = new Library<>();

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Resource");
            System.out.println("2. Remove Resource");
            System.out.println("3. Search Resource by Title");
            System.out.println("4. Display All Resources");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter resource type (Book/Magazine/DVD): ");
                    String type = scanner.nextLine();
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();

                    switch (type.toLowerCase()) {
                        case "book" -> {
                            System.out.println("Enter author: ");
                            String author = scanner.nextLine();
                            library.addResource(new Book(title, author));
                        }
                        case "magazine" -> {
                            System.out.println("Enter issue: ");
                            String issue = scanner.nextLine();
                            library.addResource(new Magazine(title, issue));
                        }
                        case "dvd" -> {
                            System.out.println("Enter director: ");
                            String director = scanner.nextLine();
                            library.addResource(new DVD(title, director));
                        }
                        default -> System.out.println("Invalid resource type.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter title to remove: ");
                    String title = scanner.nextLine();
                    library.removeResource(title);
                }
                case 3 -> {
                    System.out.println("Enter title to search: ");
                    String title = scanner.nextLine();
                    library.searchByTitle(title);
                }
                case 4 -> library.displayAllResources();
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
