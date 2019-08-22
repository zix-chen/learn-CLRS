package heap;

public class HeapOfClass {
    public static void main(String[] args) {
        int[] test = {2,17,14,6,13,10,1,5,1,12};
        int[] test2 ={15,13,9,5,12,8,7,4,0,6,2,1};
        mynode[] nodes = new mynode[10];
        for (int i = 0; i < 10; i++) {
            mynode temp = new mynode(test[i],i);
            nodes[i] = temp;
        }
        HeapOfClass heap1 = new HeapOfClass(nodes,10);
        {
            int max =heap1.heapsize;
            int print = 2;
            int cal = 0;
            int i =0;
            loop:while(i<max){
                for (; i < print-1; i++) {
                    if(i>=heap1.heapsize){
                        break loop;
                    }
                    System.out.print(heap1.heap[i].key+" ");
                }
                System.out.println();
                print*=2;
            }
            System.out.println();
        }
        heap1.buildMaxHeap();
        //heap.MaxHeapify();
        //heap1.maxHeapInsert(10);
        //heap1.heapDelete(0);
        {
            int max =heap1.heapsize;
            int print = 2;
            int cal = 0;
            int i =0;
            loop:while(i<max){
                for (; i < print-1; i++) {
                    if(i>=heap1.heapsize){
                        break loop;
                    }
                    System.out.print(heap1.heap[i].key+" ");
                }
                System.out.println();
                print*=2;
            }
            System.out.println();
        }
    }
    //**********************************************************
    public mynode[] heap;
    public int heapsize;
    public HeapOfClass(mynode[] heap, int heapsize){
        this.heapsize = heapsize;
        this.heap = heap;
    }
    public void exchange(int i, int j) {
        mynode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    public void setHeapNode(int i, mynode node){
        mynode[] temp;
        if(i>=heap.length){
            temp = new mynode[i*2];
        }
        else if(heapsize<heap.length/4){
            temp = new mynode[heapsize*2];
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
    public void buildMaxHeap(){
        for (int i = heapsize/2+1; i >=0; i--) {
            MaxHeapify(i);
        }
    }
    int left(int parent){
        int temp  = parent *2+1;
        if(temp>=heapsize){
            throw new IndexOutOfBoundsException("have no left child");
        }
        return temp;

    }
    int right(int parent){
        int temp = parent *2 +2;
        if(temp>=heapsize){
            throw new IndexOutOfBoundsException("have no right child");
        }
        return temp;
    }
    int parent(int child){
        int temp = (child-1)/2;
        if(temp<0){
            throw new IndexOutOfBoundsException("have no paents");
        }
        return temp;
    }

    public boolean hasLeft(int p){
        return p*2+1<heapsize;
    }
    public boolean hasRight(int p){
        return p*2+2<heapsize;
    }
    public boolean hasParent(int c){
        return c!=0;
    }
    public void MaxHeapify(int i){

        int l = 0,r = 0;

        while(hasLeft(i)||hasRight(i)){
            if(hasLeft(i) &&hasRight(i)){
                l = left(i);
                r = right(i);
            }
            else if(hasLeft(i)){
                l = left(i);
                r = i;
            }
            else {
                //it is only a trick for convenient!
                l = i;
                r = right(i);
            }
            if(heap[i].key>=heap[l].key&&heap[i].key>=heap[r].key){
                break;
            }
            else if(heap[l].key>=heap[r].key){
                exchange(i,l);
                i  =l;
            }
            else{
                exchange(i,r);
                i = r;
            }
        }
    }
    //Some funtions of max-priority queuea of CLRS chapter 5
    public int heapExtractMax(){
        if(heapsize<1){
            throw new IndexOutOfBoundsException("heap underflow");
        }
        int max = heap[0].key;
        exchange(0,heapsize-1);
        heapsize--;
        MaxHeapify(0);
        return max;
    }
    public void heapIncreaseKey(int i, int key){
        if(key<heap[i].key){
            throw new IndexOutOfBoundsException("decreasing");
        }
        heap[i].key = key;
        mynode temp = heap[i];
        while(i>0){
            int p = parent(i);
            if (heap[p].key>=key){
                heap[i] = temp;
                break;
            }
            heap[i] = heap[p];
            i = p;
        }
        if(i==0){
            heap[0] = temp;
        }
    }
    public void maxHeapInsert(mynode node){
        heapsize++;
        setHeapNode(heapsize-1,node);
        heapIncreaseKey(heapsize-1,node.key);
    }
    public void heapDelete(int i){
        //mind the possibility that heap[i] is smaller than heap[heapsize-1]
        // because its not a sorted array,it will not influence the ans, but will change the order。
        if(i>=heapsize){
            throw new ArrayIndexOutOfBoundsException("node i didnt exist");
        }
        exchange(i,heapsize-1);
        heapsize--;
        MaxHeapify(i);
        //remind for writing
    }
}
