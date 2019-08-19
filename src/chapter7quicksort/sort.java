package chapter7quicksort;

public class sort {
    public static void main(String[] args) {
        int[] test = {1,5,2,3,6,42,55,2,2,6,9,8,8,8,8,};
        Node[] nodes = new Node[test.length];
        for (int i = 0; i < test.length; i++) {
            Node temp = new Node(test[i]);
            nodes[i] = temp;
        }
        sort sort = new sort();
        sort.QuickSort(nodes,0,nodes.length-1);
        for (int i = 0; i < nodes.length; i++){
            System.out.print(nodes[i].key+" ");
        }
        System.out.println();
    }
    public  Node[] QuickSort(Node[] nodes,int p,int r){
        if(p>=r){
            return nodes;
        }
        int[] temp = Partition(nodes,p,r);
        for (int i = 0; i < nodes.length; i++){
            System.out.print(nodes[i].key+" ");
        }
        System.out.println();
        QuickSort(nodes,p,temp[0]-1);
        QuickSort(nodes,temp[1]+1,r);
        return nodes;
    }
    void exchange(Node[] nodes,int i,int j){
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }
    public int[] Partition(Node[] nodes,int p,int r){
        int key = nodes[r].key;
        int q = p-1;
        int t = p-1;
        for (int i = p; i <r; i++) {
            int temp = nodes[i].key;
            if(temp==key){
                t++;
                exchange(nodes,t,i);
            }
            else if(temp<key){
                q++;
                t++;
                exchange(nodes,q,t);
                exchange(nodes,q,i);
            }
        }
        q++;
        t++;
        exchange(nodes,r,t);
        int[] arr = {q,t};
        return arr;
    }
}
