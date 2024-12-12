import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Клас University
class University {

    private String name;

    public University(String name) {
        this.name = name;
    }

    // Внутрішній клас Department
    class Department {
        private String name;
        private List<String> professors;

        public Department(String name) {
            this.name = name;
            this.professors = new ArrayList<>();
        }

        public void addProfessor(String professor) {
            professors.add(professor);
        }

        public void listProfessors() {
            System.out.println("Professors in " + name + ":");
            for (String professor : professors) {
                System.out.println("- " + professor);
            }
        }
    }

    // Статичний вкладений клас Helper
    static class Helper {
        public static double calculateAverageGrade(List<Integer> grades) {
            if (grades.isEmpty()) {
                return 0;
            }
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return (double) sum / grades.size();
        }
    }

    // Метод manageEvents з використанням анонімного класу
    public void manageEvents() {
        Event event = new Event() {
            @Override
            public void organize() {
                System.out.println("Organizing a university event: Conference on Innovation.");
            }
        };
        event.organize();
    }

    // Інтерфейс для подій
    interface Event {
        void organize();
    }

    public void showDetails() {
        System.out.println("University: " + name);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University university = new University("Tech University");

        // Створення кафедри
        Department csDepartment = university.new Department("Computer Science");
        csDepartment.addProfessor("Dr. Alice");
        csDepartment.addProfessor("Dr. Bob");

        // Використання Helper для розрахунку середнього балу
        List<Integer> grades = java.util.Arrays.asList(85, 90, 78, 92);
        double average = University.Helper.calculateAverageGrade(grades);
        System.out.println("Average grade: " + average);

        // Взаємодія з користувачем
        while (true) {
            System.out.println("\n=== University Management System ===");
            System.out.println("1. Show University Details");
            System.out.println("2. List Professors in Department");
            System.out.println("3. Manage Events");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> university.showDetails();
                case 2 -> csDepartment.listProfessors();
                case 3 -> university.manageEvents();
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}