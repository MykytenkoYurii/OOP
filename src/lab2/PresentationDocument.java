import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PresentationDocument extends Document {
    public PresentationDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        Path filePath = Paths.get(getFileName());
        if (Files.exists(filePath)) {
            System.out.println("The presentation is ready for editing. Please open it in PowerPoint.");
        } else {
            System.out.println("File not found.");
        }
    }
}
