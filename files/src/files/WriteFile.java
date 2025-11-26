package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {
    static void main(String[] args) {
        boolean append = false;
        String fileName = "./files/my_file.txt";
        File file = new File(fileName);

        try {
            append = file.exists();
            PrintWriter output = new PrintWriter(new FileWriter(fileName, append));
            String content = "New content\nadded to the file";
            output.println(content);
            output.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file " + e.getMessage());
            e.getStackTrace();
        }

    }
}
