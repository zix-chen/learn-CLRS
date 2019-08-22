package chapter7quicksortAndOtherSort;
import heap.*;
public class sort {
    static void test4(){
        int[] test = {4,2,6,1,5,3,7,8,9,15,12,14,11};
        int k = 2;
        mynode[] nodes = new mynode[test.length];
        for (int i = 0; i < test.length; i++) {
            mynode temp = new mynode(test[i],i);
            nodes[i] = temp;
        }
        int[] ans  = SortAlmostSroted(nodes,test.length,2);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        test4();
        int[] test = {1,5,2,3,6,42,55,2,2,6,9,8,8,8,8};
        int[] test2 = {11,1,5,8,3,9,4,2};
        Node[] nodes = new Node[test2.length];
        for (int i = 0; i < test2.length; i++) {
            Node temp = new Node(test2[i]);
            nodes[i] = temp;
        }
        sort sort = new sort();
        Node temp = sort.SelectIterative(nodes,0,test2.length-1,8);
        System.out.println(temp.key);
        //sort.QuickSort(nodes,0,nodes.length-1);
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
    //*****************************
    //for chapter 9
    Node RandommizedSelect(Node[] A,int p,int r, int i){
        if(p==r){
            return A[p];
        }
        int q = Partition(A,p,r)[0];
        int k = q-p+1;
        if(i==k) return A[q];
        else if(i<k) return RandommizedSelect(A,p,q-1,i);
        else return RandommizedSelect(A,q+1,r,i-k);
    }
    Node SelectIterative(Node[] A,int p,int r, int i ){
        while(r>p){
            int q = Partition(A,p,r)[0];
            int k = q-p+1;
            if(i==k) return A[q];
            else if(i<k){
                r = q-1;
            }
            else{
                p = q+1;
                i = i-k;
            }
        }
        return A[p];
    }

    //对几乎有序数组 Fitance from proper position<=K进行排序，基于堆排序
    public static int[] SortAlmostSroted(mynode[] A,int n,int k){
        mynode[] arr = new  mynode[2*k];
        int id = 0;
        for (int i = n-1; i >=n-2*k; i--) {
            mynode temp = new mynode(A[i].key,i);

            arr[id] = temp;
            id++;
        }
        HeapOfClass heap = new HeapOfClass(arr,2*k);
        heap.buildMaxHeap();
        int[] ans = new int[n];
        for (int i = n-1; i >=0; i--) {
            ans[i] = heap.heap[0].key;
            if(heap.heap[0].inf-2*k>=0){
                heap.heap[0].key = A[heap.heap[0].inf-2*k].key;
                heap.heap[0].inf = heap.heap[0].inf-2*k;
                heap.MaxHeapify(0);
            }
            else {
                heap.heapDelete(0);
            }
        }
        return ans;
    }
}
