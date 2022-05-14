import exceptions.MemoryOutOfBoundException;
import exceptions.SyntaxError;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Memory {
    private Object[][] rows;
    public static int numberOfTotalInstructions;
    private ArrayList<Integer> programsPointer;

    FileManager fileManger;

    public Memory(String fileName) throws SyntaxError, FileNotFoundException, MemoryOutOfBoundException {
        programsPointer = new ArrayList<Integer>();
        fileManger = new FileManager();
        this.rows = new Object[2][];
        this.rows[0] = new Instruction[1024];
        this.rows[1] = new Integer[1024];
        numberOfTotalInstructions = initializeMemory(fileName);
    }

    public int initializeMemory(String fileName) throws FileNotFoundException, MemoryOutOfBoundException, SyntaxError {

        Instruction[] instructions = Parser.parseInstructions(fileManger.readProgram(fileName), fileName);
        if (instructions.length >= 1024) {
            throw new MemoryOutOfBoundException("Program Size is more than Memory Space Limit 0->1023 Instructions Only !");
        }
        int index = programsPointer.size() == 0 ? 0 : programsPointer.get(programsPointer.size() - 1);
        if ((index + instructions.length) >= 1024) {
            throw new MemoryOutOfBoundException("We Can't That Program Currently Due to Occupied Programs in the Memory Space");
        }
        // To Store multiple Programs in memory
        int i = 0;
        for (; i < instructions.length; i++) {
            rows[0][i + index] = instructions[i];
        }
        programsPointer.add(i + index);
        return instructions.length;
    }

    public Instruction[] getInstructions() {
        return (Instruction[]) rows[0];
    }

    public Object[][] getRows() {
        return rows;
    }

    public void setRows(Object[][] rows) {
        this.rows = rows;
    }

    public int getNumberOfInstructions() {
        return numberOfTotalInstructions;
    }

    public void setNumberOfInstructions(int numberOfInstructions) {
        this.numberOfTotalInstructions = numberOfInstructions;
    }

    public FileManager getFileManger() {
        return fileManger;
    }

    public void setFileManger(FileManager fileManger) {
        this.fileManger = fileManger;
    }
}
