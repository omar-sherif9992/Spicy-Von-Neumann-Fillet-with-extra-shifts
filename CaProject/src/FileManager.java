import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private Scanner scanner ;
    // Reads the Program and Returns an Array of Array space-seperated-instructions
    public ArrayList<String> readProgram(String fileName) throws FileNotFoundException {
        ArrayList<String> instructions = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + "/src/programs/" + fileName);
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            instructions.add(scanner.nextLine());
        }
        return instructions;
    }
}
