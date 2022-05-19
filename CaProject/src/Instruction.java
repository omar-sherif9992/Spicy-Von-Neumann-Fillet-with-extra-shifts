public class Instruction implements Cloneable{
    private int instructionBinaryValue;
    private String instructionString;
    private String type;
    private int instructionNumber;
    private String fromProgram;
    private int rd; //r1
    private int rs; //r2
    private int rt; //r3
    private int immediate;
    private int label;
    private int shamt;
    private int opcode;


    public Instruction(int instructionBinaryValue, String instructionString, String type, String fromProgram, int instructionNumber) {
        this.instructionBinaryValue = instructionBinaryValue;

        this.instructionString = instructionString;
        this.type = type;
        this.fromProgram = fromProgram;
        this.instructionNumber = instructionNumber;
    }

    public void setCurrentInstruction( int rd, int rs, int rt, int immediate, int label, int shamt, int opcode) {
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
        return instructionNumber + ". " + instructionString + " type: " + type + "-format  from: " + fromProgram;
    }

    public String decodedString() {
        String code = "";
        code += "opcode: " + getBinaryValue(opcode, 4);
        switch (type) {
            case "R":
                code += " R1: " + getBinaryValue(rd, 5);
                code += " R2: " + getBinaryValue(rs, 5);
                code += " R3: " + getBinaryValue(rt, 5);
                code += " SHAMT: " + getBinaryValue(shamt, 13);
                break;
            case "I":
                code += " R1: " + getBinaryValue(rd, 5);
                code += " R2: " + getBinaryValue(rs, 5);
                code += " Immediate: " + getBinaryValue(immediate, 18);
                break;
            case "J":
                code += " Address: " + getBinaryValue(label, 28);
                break;
        }


        return instructionNumber + ". " + "Binary Value: " + getBinaryValue(instructionBinaryValue, 32) + " " + code;
    }

    public String getBinaryValue(int value, int lengthExpected) {
        String str = Integer.toBinaryString(value) + "";
        for (int i = str.length(); i < lengthExpected; i++) {
            str = "0" + str;
        }
        return str;


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

    @Override
    public Instruction clone() {
        try {
            Instruction clone = (Instruction) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInstructionNumber() {
        return instructionNumber;
    }

    public void setInstructionNumber(int instructionNumber) {
        this.instructionNumber = instructionNumber;
    }

    public void setFromProgram(String fromProgram) {
        this.fromProgram = fromProgram;
    }
}
