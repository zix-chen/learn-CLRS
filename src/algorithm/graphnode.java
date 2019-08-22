package algorithm;

public class graphnode {
    int key;
    int edge;
    graphnode next;
    graphnode(int key,int edge,graphnode  next){
        this.key = key;
        this.edge = edge;
        this.next = next;
    }
}
