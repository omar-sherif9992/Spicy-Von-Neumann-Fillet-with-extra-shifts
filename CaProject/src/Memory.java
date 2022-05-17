import exceptions.MemoryOutOfBoundException;
import exceptions.SyntaxError;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Memory {
    private Object[] rows;
    public static int numberOfTotalInstructions;
    private ArrayList<Program> programs;

    FileManager fileManger;

    public Memory(String fileName) throws SyntaxError, FileNotFoundException, MemoryOutOfBoundException {
        programs = new ArrayList<Program>();
        fileManger = new FileManager();
        this.rows = new Object[2048];
        initializeMemory(fileName);
    }

    public void initializeMemory(String fileName) throws FileNotFoundException, MemoryOutOfBoundException, SyntaxError {

        Instruction[] instructions = Parser.parseInstructions(fileManger.readProgram(fileName), fileName);
        if (instructions.length >= 1024) {
            throw new MemoryOutOfBoundException("Program Size is more than Memory Space Limit 0->1023 Instructions Only !");
        }
        int index = programs.size() == 0 ? 0 : programs.get(programs.size() - 1).getEndIndex() + 1; //
        if ((index + instructions.length) >= 1024) {
            throw new MemoryOutOfBoundException("We Can't That Program Currently Due to Occupied Programs in the Memory Space");
        }
        // To Store multiple Programs in memory
        int i = 0;
        for (; i < instructions.length; i++) {
            rows[i + index] = instructions[i];
        }
        programs.add(new Program(index, index + i - 1, fileName.replace(".txt", "")));
        numberOfTotalInstructions = index + i - 1; // 0 index

    }

    public Instruction[] getInstructions() {
        return (Instruction[]) rows[0];
    }

    public Object[] getRows() {
        return rows;
    }

    public void setRows(Object[] rows) {
        this.rows = rows;
    }

    public static int getNumberOfTotalInstructions() {
        return numberOfTotalInstructions;
    }

    public static void setNumberOfTotalInstructions(int numberOfTotalInstructions) {
        Memory.numberOfTotalInstructions = numberOfTotalInstructions;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

    public void setRows(Object[][] rows) {
        this.rows = rows;
    }

    public int getNumberOfInstructions() {
        return numberOfTotalInstructions;
    }

    public void setNumberOfInstructions(int numberOfInstructions) {
        numberOfTotalInstructions = numberOfInstructions;
    }

    public FileManager getFileManger() {
        return fileManger;
    }

    public void setFileManger(FileManager fileManger) {
        this.fileManger = fileManger;
    }
}
