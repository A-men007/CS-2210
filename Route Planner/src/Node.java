public class Node {
    private int name;
    private boolean mark;

    public Node(int name) {
        this.mark = false;
        this.name = name;
    }

    public void setMark(boolean newMark) {
        this.mark = newMark;
    }

    public boolean getMark() {
        return this.mark;
    }

    public int getName() {
        return this.name;
    }

    public boolean equal(Node otherNode) {
        return this.name == otherNode.name;
    }

}