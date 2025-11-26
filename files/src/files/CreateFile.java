package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateFile {
    static void main(String[] args) throws IOException {
        String fileName = "./files/my_file.txt";
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("File already exists");
        } else {
            try {
                PrintWriter output = new PrintWriter(new FileWriter(fileName));
                output.close();
                System.out.println("File created successfully!");
            } catch (IOException e) {
                System.out.println("Error creating file " + e.getMessage());
                e.getStackTrace();
            }
        }
    }
}
