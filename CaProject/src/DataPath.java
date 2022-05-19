import exceptions.MemoryOutOfBoundException;

import java.util.Arrays;

public class DataPath {
    private final Decoder decoder;
    private final Computer computer;
    private int memoryLoad;
     static Instruction IF;
     static Instruction ID;
     static Instruction EX;
     static Instruction MEM;
     static Instruction WB;


    public DataPath(Computer computer) {
        this.computer = computer;
        this.decoder = new Decoder();
    }


    public void fetch() {
        IF=(Instruction) computer.getMemory().getRows()[computer.getProcessor().getProgramCounter().getData()];
        computer.getProcessor().getProgramCounter().setData((computer.getProcessor().getProgramCounter().getData() + 1));
        System.out.println("PC: " + computer.getProcessor().getProgramCounter().getData());
        System.out.println(IF.fetchString());
    }


    public void decode() {
        decoder.decode(ID);
    }


    public void execute() {
        computer.getProcessor().getAlu().execute(EX.getOpcode(),
                computer.getProcessor().getGprs()[EX.getRd()].getData(), // R1
                computer.getProcessor().getGprs()[EX.getRs()].getData(), // R2
                computer.getProcessor().getGprs()[EX.getRt()].getData(), // R3
                EX.getImmediate(), // Immediate
                EX.getShamt()); // Shamt
    }

    public void mem() throws MemoryOutOfBoundException {
        if (MEM.getOpcode() == 10) {//lw
            int address = computer.getProcessor().getAlu().getOutput();
            if (address < 1024 || address > 2047) {
                throw new MemoryOutOfBoundException("Data Memory out of Bound Exception");
            }
            try {
                memoryLoad = (int) computer.getMemory().getRows()[address];
            } catch (NullPointerException error) {
                System.out.println("Memory[address] is Null");
            }
            System.out.println("address: " + memoryLoad);
            System.out.println("memoryLoad: " + memoryLoad);

        } else if (MEM.getOpcode() == 11) {//sw
            int address = computer.getProcessor().getAlu().getOutput();
            if (address < 1024 || address > 2047) {
                throw new MemoryOutOfBoundException("Data Memory out of Bound Exception");
            }
            int rdData = computer.getProcessor().getGprs()[MEM.getRd()].getData();
            computer.getMemory().getRows()[address] = rdData;
            System.out.println("address: " + address);
            System.out.println("memoryStore: " + rdData);
        }

    }

    public void writeBack() throws MemoryOutOfBoundException {
        if (WB.getOpcode() == 0 || WB.getOpcode() == 1 || WB.getOpcode() == 2 || WB.getOpcode() == 3 || WB.getOpcode() == 5 || WB.getOpcode() == 6 || WB.getOpcode() == 8 || WB.getOpcode() == 9) {
            int dataToWriteBack = computer.getProcessor().getAlu().getOutput();
            computer.getProcessor().getGprs()[WB.getRd()].setData(dataToWriteBack);
        } else if (WB.getOpcode() == 4) { // BNE
            if (!computer.getProcessor().getAlu().isZero()) {
                // todo addition in ALU
                int newPC = computer.getProcessor().getProgramCounter().getData() + WB.getImmediate();
                if (newPC > 1024 || newPC < 0)
                    throw new MemoryOutOfBoundException("PC is outbound of instruction Memory");
                computer.getProcessor().getProgramCounter().setData(newPC);
            }
        } else if (WB.getOpcode() == 7) { // jump
            //todo addition in ALU
            int tmp = computer.getProcessor().getProgramCounter().getData();
            tmp = tmp & (0b1111 << 28);
            tmp = tmp + WB.getLabel();
            if (tmp > 1024 || tmp < 0)
                throw new MemoryOutOfBoundException("PC is outbound of instruction Memory");

            computer.getProcessor().getProgramCounter().setData(tmp);
        } else if (WB.getOpcode() == 10) { // lw
            computer.getProcessor().getGprs()[WB.getRd()].setData(memoryLoad);
        } else if (WB.getOpcode() == 11) { // sw
            // Empty intended
        }
        Register reg = computer.getProcessor().getGprs()[WB.getRd()];
        System.out.println("Register " + reg.getName() + " :" + reg.getData());
    }



    public Decoder getDecoder() {
        return decoder;
    }

    public Computer getComputer() {
        return computer;
    }

    public int getMemoryLoad() {
        return memoryLoad;
    }

    public void setMemoryLoad(int memoryLoad) {
        this.memoryLoad = memoryLoad;
    }

    public static Instruction getIF() {
        return IF;
    }

    public static void setIF(Instruction IF) {
        DataPath.IF = IF;
    }

    public static Instruction getID() {
        return ID;
    }

    public static void setID(Instruction ID) {
        DataPath.ID = ID;
    }

    public static Instruction getEX() {
        return EX;
    }

    public static void setEX(Instruction EX) {
        DataPath.EX = EX;
    }

    public static Instruction getMEM() {
        return MEM;
    }

    public static void setMEM(Instruction MEM) {
        DataPath.MEM = MEM;
    }

    public static Instruction getWB() {
        return WB;
    }

    public static void setWB(Instruction WB) {
        DataPath.WB = WB;
    }

}
