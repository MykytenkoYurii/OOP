package lab2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DocumentFactory {

    // Карта для асоціації розширення файлу з відповідним класом документу
    private static final Map<String, Function<String, Document>> documentMap = new HashMap<>();

    // Статичний блок для ініціалізації карти
    static {
        documentMap.put(".txt", TextDocument::new);
        documentMap.put(".csv", SpreadsheetDocument::new);
        documentMap.put(".pptx", PresentationDocument::new);
    }

    // Метод для створення документа на основі розширення
    public static Document createDocument(String fileName) {
        String extension = getFileExtension(fileName);
        Function<String, Document> documentConstructor = documentMap.get(extension);

        if (documentConstructor != null) {
            return documentConstructor.apply(fileName);
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + extension);
        }
    }

    // Метод для отримання розширення файлу
    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex);
    }
}
