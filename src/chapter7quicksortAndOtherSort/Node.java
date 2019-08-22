package chapter7quicksortAndOtherSort;

public class Node implements comparable<Node> {
    int key;
    int inf;
    Node(int key){
        this.key = key;
    }
    @Override
    public int compareTo(Node node) {
        return 0;
    }

    @Override
    public int compareTo2(Object obj) {
        Node temp = (Node)obj;
        return key-temp.key;
    }
}
