import java.awt.Desktop;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;

abstract class Document {
    private Path filePath;

    public Document(String fileName) {
        this.filePath = Paths.get(fileName).toAbsolutePath();
    }

    public Path getFilePath() {
        return filePath;
    }

    public void openInExternalApp() {
        try {
            File file = getFilePath().toFile();
            if (Files.exists(getFilePath())) {
                Desktop.getDesktop().open(file);
                System.out.println("Document opened in the default application. Please edit, save, and close the file.");
            } else {
                System.out.println("File not found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
    }

    protected void readFile(String message) {
        try (BufferedReader reader = Files.newBufferedReader(getFilePath())) {
            String line;
            System.out.println(message);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
        }
    }

    public abstract void readFileContent();
}

class DocumentCollection {
    private ArrayList<Document> documents = new ArrayList<>();

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void openAllDocuments() {
        if (documents.isEmpty()) {
            System.out.println("No documents to open.");
            return;
        }

        for (Document doc : documents) {
            doc.openInExternalApp();
        }
    }

    public void readAllDocuments() {
        if (documents.isEmpty()) {
            System.out.println("No documents to read.");
            return;
        }

        for (Document doc : documents) {
            doc.readFileContent();
        }
    }

    public void removeDocument(Document document) {
        if (documents.remove(document)) {
            System.out.println("Document removed successfully.");
        } else {
            System.out.println("Document not found in the collection.");
        }
    }

    public int getDocumentCount() {
        return documents.size();
    }
}

class DocumentFactory {

    private static final Map<String, Function<String, Document>> documentMap = new HashMap<>();

    static {
        documentMap.put(".txt", TextDocument::new);
        documentMap.put(".csv", SpreadsheetDocument::new);
        documentMap.put(".pptx", PresentationDocument::new);
    }

    public static Document createDocument(String fileName) {
        String extension = getFileExtension(fileName);
        Function<String, Document> documentConstructor = documentMap.get(extension);

        if (documentConstructor != null) {
            return documentConstructor.apply(fileName);
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + extension);
        }
    }

    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex);
    }
}

class DocumentProcessingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DocumentCollection collection = new DocumentCollection();
        boolean running = true;

        while (running) {
            clearConsole();
            displayMenu();

            int action = getUserInput(scanner, "Choose an action (1-3): ", 3);

            if (action == 3) {
                running = false;
                System.out.println("Exiting the system.");
                break;
            }

            String fileName = getFileName(scanner);
            String filePath = getFilePath(scanner, fileName);

            try {
                Document document = DocumentFactory.createDocument(filePath);
                collection.addDocument(document);

                if (action == 1) {
                    document.openInExternalApp();
                } else if (action == 2) {
                    document.readFileContent();
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

    public static String getFilePath(Scanner scanner, String fileName) {
        System.out.print("Enter the folder (e.g., 'example_files/') or leave empty for current directory: ");
        String folder = scanner.nextLine().trim();
        if (folder.isEmpty()) {
            return fileName; // Якщо не вказана папка, шукаємо в поточній директорії
        } else {
            return folder + File.separator + fileName; // Додаємо папку до імені файлу
        }
    }

    public static void promptEnterKey(Scanner scanner) {
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine();
    }
}

class PresentationDocument extends Document {
    public PresentationDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        System.out.println("The presentation cannot be read in the terminal. Please open it in PowerPoint or another presentation software.");
    }
}

class SpreadsheetDocument extends Document {
    public SpreadsheetDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        readFile("Reading content of the spreadsheet document (CSV format):");
    }
}

class TextDocument extends Document {
    public TextDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        readFile("Text Document Content:");
    }
}
