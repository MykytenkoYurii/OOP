package lab2;

import java.util.ArrayList;

public class DocumentCollection {
    private ArrayList<Document> documents = new ArrayList<>();

    // Метод для додавання документів до колекції
    public void addDocument(Document document) {
        documents.add(document);
    }

    // Відкриття всіх документів у зовнішніх додатках
    public void openAllDocuments() {
        if (documents.isEmpty()) {
            System.out.println("No documents to open.");
            return;  // Якщо немає документів, виходимо з методу
        }

        for (Document doc : documents) {
            doc.openInExternalApp();  // Використання методу відкриття з класу Document
        }
    }

    // Читання вмісту всіх документів
    public void readAllDocuments() {
        if (documents.isEmpty()) {
            System.out.println("No documents to read.");
            return;  // Якщо немає документів, виходимо з методу
        }

        for (Document doc : documents) {
            doc.readFileContent();  // Використання абстрактного методу читання з класу Document
        }
    }

    // Додатковий метод для видалення документів
    public void removeDocument(Document document) {
        if (documents.remove(document)) {
            System.out.println("Document removed successfully.");
        } else {
            System.out.println("Document not found in the collection.");
        }
    }

    // Метод для отримання кількості документів
    public int getDocumentCount() {
        return documents.size();
    }
}
