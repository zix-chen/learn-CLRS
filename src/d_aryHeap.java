//My solution for CLRS problem 6-2
public class d_aryHeap {
    public static void main(String[] args) {
        int[] test = {1,2,5,3,8,6,9,4,7};
        mynode2<String>[] nodes = new mynode2[9];
        for (int i = 0; i < 9; i++) {
            mynode2<String> temp = new mynode2<>(test[i],"some test");
            nodes[i] = temp;
        }
        d_aryHeap Maxheap = new d_aryHeap(nodes,9,3);
        Maxheap.buildMaxHeap();
        mynode2 temp = new mynode2(10,"just a try");
        Maxheap.insert(temp);
        //Maxheap.extertMax();
        {
            int max =Maxheap.heapsize;
            int print = Maxheap.d;
            int cal = 0;
            int i =0;
            loop:while(i<max){
                for (; i < print-2; i++) {
                    if(i>=Maxheap.heapsize){
                        break loop;
                    }
                    System.out.print(Maxheap.heap[i].key+" "+Maxheap.heap[i].inf+" ");
                }
                System.out.println();
                print*=Maxheap.d;
            }
            System.out.println();
        }
        System.out.println();
    }
    mynode2[] heap;
    int heapsize;
    int d;
    d_aryHeap(mynode2[] heap, int heapsize, int D_ary){
        this.heap = heap;
        this.heapsize = heapsize;
        d = D_ary;
    }
    int child(int p,int rank){
        return p*d+rank;
    }
    int parent(int c){
        if(c<=0){
            throw new IndexOutOfBoundsException(" it is the root");
        }
        return (c-1)/d;
    }
    boolean hasChild(int p,int rank){
        return p*d+rank<heapsize;
    }
    void exchange(int i, int j) {
        mynode2 temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    void maxHeapify(int i){

        while(hasChild(i,1)){
            int maxchi = heap[i].key;
            int id = 0;
            for (int j = 1; j <= d; j++) {
                if(!hasChild(i,j)){
                    break;
                }
                int child = child(i,j);
                if(heap[child].key>maxchi){
                    maxchi = heap[child].key;
                    id = child;
                }
            }
            if(maxchi>heap[i].key){
                exchange(i,id);
                i = id;
            }
            else return;
        }
    }
    mynode2  extertMax(){
        mynode2 temp = heap[0];
        exchange(0,heapsize-1);
        heapsize--;
        maxHeapify(0);
        return temp;
    }
    void buildMaxHeap(){
        for (int i = heapsize/d+1; i >=0 ; i--) {
            maxHeapify(i);
        }
    }
    void setHeapNode(int i, mynode2 node){
        mynode2[] temp;
        if(i>=heap.length){
            temp = new mynode2[i*2];
        }
        else if(heapsize<heap.length/4){
            temp = new mynode2[heapsize*2];
        }
        else{
            heap[i] = node;
            return;
        }
        for (int j = 0; j <heapsize&&j<heap.length; j++) {
            //mind the heapsize `
            temp[j] = heap[j];
        }
        heap = temp;
        heap[i] = node;
    }
    void insert(mynode2 node){
        heapsize++;
        setHeapNode(heapsize-1,node);
        increaseKey(heapsize-1,node.key);
    }
    boolean increaseKey(int i, int key){
        if(key<heap[i].key)return false;
        mynode2 temp = heap[i];
        temp.key = key;
        while(i>0){
            int p = parent(i);
            if(heap[p].key>=key) break;
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = temp;
        return true;
    }
}
