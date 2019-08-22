package heap;

public class mynode {
    public int key;

    public mynode(int key){
        this.key = key;
    }
    public mynode(int key, int inf){
        this.key = key;
        this.inf = inf;
    }
    public int inf;
}
