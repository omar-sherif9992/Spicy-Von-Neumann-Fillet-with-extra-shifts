public class Decoder {
    private int rd; //r1
    private int rs; //r2
    private int rt; //r3
    private int immediate;
    private int label;
    private int shamt;
    private int opcode;

    public void decode(Instruction currentInstruction) {
        opcode = (currentInstruction.getInstructionBinaryValue() & (0b1111 << 28)) >> 28; //  4 Bits[31-28]
        opcode = opcode & 15;
        rd = (currentInstruction.getInstructionBinaryValue() & (0b11111 << 23)) >> 23; // [27-23] R1
        rs = (currentInstruction.getInstructionBinaryValue() & (0b11111 << 18)) >> 18; // [22-18] R2
        rt = (currentInstruction.getInstructionBinaryValue() & (0b11111 << 13)) >> 13; // [17-13] R3
        immediate = currentInstruction.getInstructionBinaryValue() & (0b111111111111111111); // [17-0] Immediate
        label = currentInstruction.getInstructionBinaryValue() & (0b1111111111111111111111111111); // [27-0]
        shamt = currentInstruction.getInstructionBinaryValue() & (0b1111111111111); // [12-0]

        // Setting ID
        DataPath.ID.setCurrentInstruction( rd, rs, rt, immediate, label, shamt, opcode);
        System.out.println(DataPath.ID.decodedString());
    }
}
