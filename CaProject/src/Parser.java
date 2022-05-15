import exceptions.SyntaxError;

import java.util.ArrayList;

public class Parser {


    public void parseInstruction(String nextInstruction) throws Exception {
        String[] instruction = nextInstruction.split(" ");

    }

    public static Instruction[] parseInstructions(ArrayList<String> instructions, String fileName) throws SyntaxError {
        Instruction[] parsedInstructions = new Instruction[instructions.size()];
        int i = 0;
        for (String instruction : instructions) {

            String[] instructionArr = instruction.split(" ");
            String type = "R";
            int parsed = 0;
            switch (instructionArr[0]) {
                case "ADD":
                    parsed |= (0b0000 << 28);//opcode
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;// R1
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;// R2
                    parsed |= Integer.parseInt(instructionArr[3].substring(1)) << 13; // R3

                    break;

                case "SUB":
                    parsed |= (0b0001 << 28);//R
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3].substring(1)) << 13;
                    break;

                case "MULI":
                    parsed |= (0b0010 << 28);
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    type = "I";
                    break;

                case "ADDI":
                    parsed |= (0b0011 << 28);
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    type = "I";
                    break;

                case "BNE":
                    parsed |= (0b0100 << 28);
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    type = "I";
                    break;

                case "ANDI":
                    parsed |= (0b0101 << 28);
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    type = "I";
                    break;

                case "ORI":
                    parsed |= (0b0110 << 28);
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    type = "I";
                    break;

                case "J":
                    parsed |= (0b0111 << 28);
                    parsed |= Integer.parseInt(instructionArr[1]);
                    type = "J";
                    break;

                case "SLL":
                    parsed |= (0b1000 << 28);//opcode
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;//R1
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;//R2
                    parsed |= Integer.parseInt(instructionArr[3]);//R3
                    break;

                case "SRL":
                    parsed |= (0b1001 << 28);//R
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    break;

                case "LW":
                    parsed |= (0b1010 << 28);
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    type = "I";
                    break;

                case "SW":
                    parsed |= (0b1011 << 28);
                    parsed |= Integer.parseInt(instructionArr[1].substring(1)) << 23;
                    parsed |= Integer.parseInt(instructionArr[2].substring(1)) << 18;
                    parsed |= Integer.parseInt(instructionArr[3]);
                    type = "I";
                    break;

                default:
                    throw new SyntaxError("Syntax Error");
            }
            parsedInstructions[i] = new Instruction(parsed, instruction, type, fileName.replace(".txt", ""),i);
            i++;
        }

        return parsedInstructions;

    }

}
