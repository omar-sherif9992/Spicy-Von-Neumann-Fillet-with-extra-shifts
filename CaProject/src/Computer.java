import java.io.FileNotFoundException;

import exceptions.MemoryOutOfBoundException;
import exceptions.SyntaxError;

public class Computer {

    private Memory memory;
    private Processor processor;
    private DataPath dataPath;


    public Computer(String filename) throws FileNotFoundException, MemoryOutOfBoundException, SyntaxError {
        this.memory=new Memory(filename);
        this.processor = new Processor();
        this.dataPath = new DataPath(this);
    }


    public DataPath getDataPath() {
        return dataPath;
    }


    public void setDataPath(DataPath dataPath) {
        this.dataPath = dataPath;
    }


    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}
