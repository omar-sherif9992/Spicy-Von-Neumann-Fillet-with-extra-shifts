import java.io.FileNotFoundException;
import java.util.ArrayList;

import exceptions.MemoryOutOfBoundException;
import exceptions.SyntaxError;

public class Main {
	
    public static void main(String[] args) throws FileNotFoundException, MemoryOutOfBoundException, SyntaxError {
   
    	Computer computer = new Computer("Program_1.txt");
    	computer.getProcessor().run( computer.getDataPath());
    }
   
}
