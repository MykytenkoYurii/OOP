package lab2;

public class TextDocument extends Document {
    public TextDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        readFile("Text Document Content:");
    }
}
