package files;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFullFile {
    static void main(String[] args) {
        String fileName = "./files/my_file.txt";
        try {

            List<String> lines = Files.readAllLines(Paths.get(fileName));
            System.out.println("File content:");
//            for(String line: lines){
//                System.out.println(line);
//            }
            lines.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading file " + e.getMessage());
            e.getStackTrace();
        }
    }
}
