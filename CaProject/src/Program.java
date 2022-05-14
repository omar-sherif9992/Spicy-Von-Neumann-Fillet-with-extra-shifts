public class Program {

    private int beginIndex;
    private int endIndex;
    private String name;

    public Program(int beginIndex, int endIndex, String name) {
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.name = name;
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
