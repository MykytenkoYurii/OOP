import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;

// Abstract class Document
abstract class Document {
    private String fileName;

    public Document(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    // Method to open the document in an external application
    public void openInExternalApp() {
        try {
            File file = new File(getFileName());
            if (file.exists()) {
                Desktop.getDesktop().open(file);
                System.out.println("Document opened in the default application. Please edit, save, and close the file.");
            } else {
                System.out.println("File not found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
    }

    // Generalized method to read a file
    protected void readFile(String message) {
        try {
            File file = new File(getFileName());
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                System.out.println(message);
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } else {
                System.out.println("File not found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
        }
    }

    // Abstract method for reading file content
    public abstract void readFileContent();
}

// Class for text documents
class TextDocument extends Document {
    public TextDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        readFile("File content:");
    }
}

// Class for spreadsheets (.csv)
class SpreadsheetDocument extends Document {
    public SpreadsheetDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        readFile("Spreadsheet content:");
    }
}

// Class for presentations (.pptx)
class PresentationDocument extends Document {
    public PresentationDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        File file = new File(getFileName());
        if (file.exists()) {
            System.out.println("The presentation is ready for editing. Please open it in PowerPoint.");
        } else {
            System.out.println("File not found.");
        }
    }
}

// Factory method for creating documents
class DocumentFactory {
    public static Document createDocument(String fileName) {
        if (fileName.endsWith(".txt")) {
            return new TextDocument(fileName);
        } else if (fileName.endsWith(".csv")) {
            return new SpreadsheetDocument(fileName);
        } else if (fileName.endsWith(".pptx")) {
            return new PresentationDocument(fileName);
        } else {
            throw new IllegalArgumentException("Unsupported file type.");
        }
    }
}

// Main class for document processing
public class DocumentProcessingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Clear the console
            clearConsole();

            // Display the main menu
            System.out.println("=== Document Processing System ===");
            System.out.println("1. Open document in external application");
            System.out.println("2. Read file content");
            System.out.println("3. Exit");
            System.out.print("Choose an action (1-3): ");

            String input = scanner.nextLine().trim();

            // Input validation
            if (!input.matches("[1-3]")) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }

            int action = Integer.parseInt(input);

            // Exit the system
            if (action == 3) {
                System.out.println("Exiting the system.");
                running = false;
                break;
            }

            // Enter the document name
            System.out.println("Enter the name of the document (e.g., file.txt, file.csv, file.pptx):");
            String fileName = scanner.nextLine();

            try {
                // Create the document using the factory method
                Document document = DocumentFactory.createDocument(fileName);

                // Perform the selected action
                switch (action) {
                    case 1:
                        document.openInExternalApp();  // Open in external application for editing
                        break;
                    case 2:
                        document.readFileContent();  // Read file content
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Press Enter to return to the main menu...");
            scanner.nextLine();  // Wait for Enter key press to continue
        }

        // Close the scanner after use
        scanner.close();
    }

    // Method to clear the console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
