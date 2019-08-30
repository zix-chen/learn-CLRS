package DataSteucture;
//继承泛型类并且不保持泛型
//code没测试过
public class IntervalTree extends BST<Data> {
    public node SearchExactly(int low,int high){
        node y = root;
        while(y!=null){
            if(y.data.high==high&&y.key==low) break;
            else if(y.key<=low) y = y.right;
            else if(y.key>low) y=y.left;
            else return null;
        }
        return y;
    }
    public int  Overlapped(node node,int low,int high){
        int t = 0;
        if(node.data.high<low) return node.data.high-low;
        else if(node.key>high) return node.key-high;
        else return 0;
    }
}
