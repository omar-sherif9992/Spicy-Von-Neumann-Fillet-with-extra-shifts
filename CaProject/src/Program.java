public class Program {

    private int beginIndex;
    private int endIndex;
    private String name;
    private int totalProgramInstructions;

    public Program(int beginIndex, int endIndex, String name) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.name = name;
        this.totalProgramInstructions=beginIndex-endIndex+1;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public int getTotalProgramInstructions() {
        return totalProgramInstructions;
    }

    public void setTotalProgramInstructions(int totalProgramInstructions) {
        this.totalProgramInstructions = totalProgramInstructions;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
