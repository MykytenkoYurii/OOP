package lab2;

import java.awt.Desktop;
import java.io.*;
import java.nio.file.*;

public abstract class Document {
    private Path filePath;

    // Конструктор тепер приймає шлях у вигляді Path, а не просто ім'я файлу
    public Document(String fileName) {
        this.filePath = Paths.get(fileName);  // Створює об'єкт Path для зберігання шляху до файлу
    }

    // Отримання шляху до файлу
    public Path getFilePath() {
        return filePath;
    }

    // Відкриття файлу у зовнішній програмі
    public void openInExternalApp() {
        try {
            File file = getFilePath().toFile();  // Конвертація Path у File
            if (Files.exists(getFilePath())) {   // Перевірка існування файлу за допомогою Files
                Desktop.getDesktop().open(file);
                System.out.println("Document opened in the default application. Please edit, save, and close the file.");
            } else {
                System.out.println("File not found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
    }

    // Читання файлу
    protected void readFile(String message) {
        try (BufferedReader reader = Files.newBufferedReader(getFilePath())) {  // Використовуємо Files для читання
            String line;
            System.out.println(message);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
        }
    }

    // Абстрактний метод для читання контенту файлу
    public abstract void readFileContent();
}
