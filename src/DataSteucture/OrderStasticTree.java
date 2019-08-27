package DataSteucture;

public class OrderStasticTree<T> extends BST<T> {
    public static void main(String[] args) {
        OrderStasticTree<Integer> tree = new OrderStasticTree<>();
        OrderStasticTree.node node = tree.new node( 99,99);
        tree.TreeInsert(node);
    }
    OrderStasticTree(){
        super();
    }
    public int Rank(node node){
        int r = node.left==null?1:node.left.size+1;
        node y = node;
        while(y!=root){
            if(y==y.p.right){
                r+=y.p.left==null?1:(y.p.left.size+1);
            }
            y = y.p;
        }
        return r;
    }
    public node Select(node troot,int rank){
        node y = troot;
        int r;
        int l = rank;
        while(y!=null){
             r = y.left.size+1;
            if(r==l){
                return y;
            }
            else if(r<l){
                l = l - r;
                y = y.right;
            }
            else{
                y = y.left;
            }
        }
        return y;
    }
    public int Rank(node troot,int key){
        if(troot.key==key) return troot.left.size+1;
        else if(troot.key>key) return Rank(troot.left, key);
        else return troot.left.size + 1 + Rank(troot.right, key);
    }
}
