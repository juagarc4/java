package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    static void main(String[] args) {
        String fileName = "./files/my_file.txt";
        File file = new File(fileName);

        try {
            System.out.println("File content:");
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String line = input.readLine();
            while(line != null) {
                System.out.println(line);
                line = input.readLine();
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error reading file " + e.getMessage());
            e.getStackTrace();
        }
    }
}
