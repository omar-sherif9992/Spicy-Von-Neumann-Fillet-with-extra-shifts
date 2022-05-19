import exceptions.MemoryOutOfBoundException;

public class Processor {
    private Register[] gprs;
    private final Register zeroRegister;
    private Register programCounter;
    private ALU alu;
    private int clockCycle;

    public Processor() {
        gprs = new Register[32]; // I've put zeroRegister in it
        zeroRegister = new Register(0, "R0");
        gprs[0] = zeroRegister;
        for (int i = 1; i < gprs.length; i++)
            gprs[i] = new Register(0, "R" + i);

        programCounter = new Register(0, "PC");
        alu = new ALU(this);
    }

    public void run(DataPath dataPath) throws MemoryOutOfBoundException {
        int numberOfClockCycles = 7 + ((Memory.numberOfTotalInstructions ) * 2);
        for (this.clockCycle = 1; this.clockCycle <= numberOfClockCycles; this.clockCycle++) {
            System.out.println("@ Clock Cycle:" + clockCycle);
            if (this.clockCycle > 6) {
                if (clockCycle % 2 == 1) { // Setting WB
                    DataPath.WB = (DataPath.MEM.clone());
                    System.out.println("@ WriteBack Stage");
                    System.out.println("WB Instruction " + DataPath.WB.getInstructionNumber());
                    dataPath.writeBack();
                }
            }
            if (this.clockCycle > 5 && this.clockCycle <= (numberOfClockCycles - 1)) {
                if (this.clockCycle % 2 == 0) { // Setting MEM
                    System.out.println("@ Memory Stage");
                    DataPath.MEM = (DataPath.EX.clone());
                    System.out.println("Mem Instruction " + DataPath.MEM.getInstructionNumber());
                    dataPath.mem();
                }
            }
            if (this.clockCycle > 3 && this.clockCycle <= (numberOfClockCycles - 2)) {
                if (clockCycle % 2 == 0) // Setting EX
                    DataPath.EX = (DataPath.ID.clone());
                System.out.println("@ Execute Stage");
                System.out.println("Executing Instruction " + DataPath.EX.getInstructionNumber());

                if (clockCycle % 2 == 1)
                    dataPath.execute();
            }
            if (this.clockCycle > 1 && this.clockCycle <= (numberOfClockCycles - 4)) {
                if (clockCycle % 2 == 0) // Setting ID
                    DataPath.ID = (DataPath.IF.clone());
                System.out.println("@ Decode Stage");
                System.out.println("Decoding Instruction " + DataPath.ID.getInstructionNumber());
                if (clockCycle % 2 == 1)
                    dataPath.decode();
            }
            if (clockCycle % 2 == 1 && this.clockCycle <= (numberOfClockCycles - 6)) { // Setting IF
                System.out.println("@ Fetch Stage");
                dataPath.fetch();
            }
//

            System.out.println("---------------------------------------");
        }
    }

    public Register[] getGprs() {
        return gprs;
    }

    public void setGprs(Register[] gprs) {
        this.gprs = gprs;
    }

    public Register getZeroRegister() {
        return zeroRegister;
    }

    public Register getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(Register programCounter) {
        this.programCounter = programCounter;
    }

    public ALU getAlu() {
        return alu;
    }

    public void setAlu(ALU alu) {
        this.alu = alu;
    }
}
