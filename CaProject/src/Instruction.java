public class Instruction {
    private int instructionBinaryValue;
    private String instructionString;
    private final String type;
    private final int instructionNumber;
    private final String fromProgram;
    private int rd; //r1
    private int rs; //r2
    private int rt; //r3
    private int immediate;
    private int label;
    private int shamt;
    private int opcode;

    public Instruction(int instructionBinaryValue, String instructionString, String type, String fromProgram,int instructionNumber) {
        this.instructionBinaryValue = instructionBinaryValue;
        this.instructionString = instructionString;
        this.type = type;
        this.fromProgram = fromProgram;
        this.instructionNumber=instructionNumber;
    }

    public void setInstruction(int rd, int rs, int rt, int immediate, int label, int shamt, int opcode) {
        this.opcode = opcode;
        switch (type) {
            case "R":
                this.rd = rd;
                this.rs = rs;
                this.rt = rt;
                this.shamt = shamt;
                break;
            case "I":
                this.rd = rd;
                this.rs = rs;
                this.immediate = immediate;
                break;
            case "J":
                this.label = label;
                break;
        }


    }

    public String fetchString() {
        return instructionNumber+". "+instructionString + " type: " + type + "-format  from: " + fromProgram;
    }

    public String decodedString() {
        String code = "";
        code += "opcode: " + Integer.toBinaryString(opcode);
        switch (type) {
            case "R":
                code += " R1: " + Integer.toBinaryString(rd);
                code += " R2: " + Integer.toBinaryString(rs);
                code += " R3: " + Integer.toBinaryString(rt);
                code += " SHAMT: " + Integer.toBinaryString(shamt);
                break;
            case "I":
                code += " R1: " + Integer.toBinaryString(rd);
                code += " R2: " + Integer.toBinaryString(rs);
                code += " Immediate: " + Integer.toBinaryString(instructionBinaryValue);
                break;
            case "J":
                code += " Address: " + Integer.toBinaryString(label);
                break;
        }


        return "Binary Value: " + Integer.toBinaryString(instructionBinaryValue) + " " + code;
    }


    public int getInstructionBinaryValue() {
        return instructionBinaryValue;
    }

    public void setInstructionBinaryValue(int instructionBinaryValue) {
        this.instructionBinaryValue = instructionBinaryValue;
    }

    public String getInstructionString() {
        return instructionString;
    }

    public void setInstructionString(String instructionString) {
        this.instructionString = instructionString;
    }


    public String getType() {
        return type;
    }

    public String getFromProgram() {
        return fromProgram;
    }

    public int getRd() {
        return rd;
    }

    public void setRd(int rd) {
        this.rd = rd;
    }

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getImmediate() {
        return immediate;
    }

    public void setImmediate(int immediate) {
        this.immediate = immediate;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getShamt() {
        return shamt;
    }

    public void setShamt(int shamt) {
        this.shamt = shamt;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }
}
