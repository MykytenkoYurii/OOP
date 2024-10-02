package lab2;

public class SpreadsheetDocument extends Document {
    public SpreadsheetDocument(String fileName) {
        super(fileName);
    }

    @Override
    public void readFileContent() {
        readFile("Reading content of the spreadsheet document:");
    }
    
