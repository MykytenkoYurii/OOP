package lab2;

import java.util.Scanner;

public class DocumentProcessingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DocumentCollection collection = new DocumentCollection();
        boolean running = true;

        while (running) {
            clearConsole();
            displayMenu();

            int action = getUserInput(scanner, "Choose an action (1-3): ", 3);

            if (action == 3) {
                running = false; // Завершення програми
                System.out.println("Exiting the system.");
                break; // Вихід з циклу
            }

            String fileName = getFileName(scanner);
            try {
                Document document = DocumentFactory.createDocument(fileName);
                collection.addDocument(document);

                if (action == 1) {
                    document.openInExternalApp();  // Open document in external application
                } else if (action == 2) {
                    document.readFileContent();  // Read the content of a specific file
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            promptEnterKey(scanner);
        }

        scanner.close();
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayMenu() {
        System.out.println("=== Document Processing System ===");
        System.out.println("1. Open document in external application");
        System.out.println("2. Read file content");
        System.out.println("3. Exit");
    }

    public static int getUserInput(Scanner scanner, String prompt, int maxOption) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("[1-" + maxOption + "]")) {
                return Integer.parseInt(input);
            }
            System.out.println("Invalid input. Please enter a number between 1 and " + maxOption + ".");
        }
    }

    public static String getFileName(Scanner scanner) {
        System.out.print("Enter the name of the document (e.g., file.txt, file.csv, file.pptx): ");
        return scanner.nextLine().trim();
    }

    public static void promptEnterKey(Scanner scanner) {
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine();
    }
}
