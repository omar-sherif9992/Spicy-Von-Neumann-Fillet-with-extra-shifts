import java.util.Arrays;

public class DataPath {
    private final Decoder decoder;
    private final Computer computer;
    private Instruction currentInstruction;
    private int opcode;
    private int memoryLoad;

    public DataPath(Computer computer) {
        this.computer = computer;
        this.decoder = new Decoder();
    }


    public void fetch() {
        setCurrentInstruction((Instruction) computer.getMemory().getRows()[0][computer.getProcessor().getProgramCounter().getData()]);
        computer.getProcessor().getProgramCounter().setData((computer.getProcessor().getProgramCounter().getData() + 1));
        System.out.println("PC: " + computer.getProcessor().getProgramCounter().getData());
        System.out.println("@ Fetch Stage");
        System.out.println(getCurrentInstruction().fetchString());

    }


    public void decode() {
        decoder.decode(getCurrentInstruction());
        opcode = getCurrentInstruction().getOpcode();
    }

    public void execute() {
        computer.getProcessor().getAlu().execute(opcode,
                getCurrentInstruction().getRs(),
                getCurrentInstruction().getRt(),
                getCurrentInstruction().getImmediate(),
                getCurrentInstruction().getShamt());
    }

    public void mem() {
        if (opcode == 10) {//lw
            memoryLoad = (int) computer.getMemory().getRows()[1][computer.getProcessor().getAlu().getOutput()];
            System.out.println("memoryLoad: " + memoryLoad);
        } else if (opcode == 11) {//sw
            computer.getMemory().getRows()[1][computer.getProcessor().getAlu().getOutput()] = computer.getProcessor().getGprs()[currentInstruction.getRd() - 1].getData();
            System.out.println("memoryStore: " + Arrays.toString(computer.getMemory().getRows()[computer.getProcessor().getAlu().getOutput()]));

        }
        System.out.println("index: " + computer.getProcessor().getGprs()[currentInstruction.getRd() - 1].getData());
        System.out.println("data: " + computer.getMemory().getRows()[1][computer.getProcessor().getGprs()[currentInstruction.getRd() - 1].getData()]);
    }

    public void writeBack() {
        if (opcode == 0 || opcode == 1 || opcode == 2 || opcode == 3 || opcode == 5 || opcode == 6 || opcode == 8 || opcode == 9) {
            computer.getProcessor().getGprs()[currentInstruction.getRd() - 1].setData(computer.getProcessor().getAlu().getOutput());
        } else if (opcode == 4) {//BNE
            if (computer.getProcessor().getAlu().isZero()) {
                computer.getProcessor().getProgramCounter().setData(computer.getProcessor().getProgramCounter().getData() + currentInstruction.getImmediate());
            }
        } else if (opcode == 7) { //jump
            int tmp = computer.getProcessor().getProgramCounter().getData();
            tmp = tmp & (0b1111 << 28);
            tmp = tmp + getCurrentInstruction().getLabel();
            computer.getProcessor().getProgramCounter().setData(tmp);
        } else if (opcode == 10) {//lw
            computer.getProcessor().getGprs()[currentInstruction.getRd() - 1].setData(memoryLoad);
        } else if (opcode == 11) {

        }
        System.out.println("PC: " + computer.getProcessor().getProgramCounter().getData());
        System.out.println("Register: " + computer.getProcessor().getGprs()[currentInstruction.getRd() - 1].getData());
    }

    public Instruction getCurrentInstruction() {
        return currentInstruction;
    }

    public void setCurrentInstruction(Instruction currentInstruction) {
        this.currentInstruction = currentInstruction;
    }
}
