package heap;

public class Heap {

    public static void main(String[] args) {
        int[] test = {2,17,14,6,13,10,1,5,1,12};
        int[] test2 ={15,13,9,5,12,8,7,4,0,6,2,1};

        Heap heap1 = new Heap(test2,12);
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
                    System.out.print(heap1.heap[i]+" ");
                }
                System.out.println();
                print*=2;
            }
            System.out.println();
        }
        int[][] ints = new int[2][12];
        System.out.println(ints[1][0]);
        heap1.heapIncreaseKey(10,20);
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
                    System.out.print(heap1.heap[i]+" ");
                }
                System.out.println();
                print*=2;
            }
            System.out.println();
        }
    }

    public Heap(int[] arr, int heapsize){
        heap =arr;
        this.heapsize = heapsize;
    }
    int[] heap;
    int heapsize;
    //avoid the possibilty of out of bound and also save storge when needed
    void setHeapNode(int i,int key){
        int[] temp;
        if(i>=heap.length){
             temp = new int[i*2];
        }
        else if(heapsize<heap.length/4){
            temp = new int[heapsize*2];
        }
        else{
            heap[i] = key;
            return;
        }
        for (int j = 0; j <heapsize&&j<heap.length; j++) {
            //mind the heapsize `

            temp[j] = heap[j];
        }
        heap = temp;
        heap[i] = key;
    }
    void buildMaxHeap(){
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
    void exchange(int i, int j ){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    boolean hasLeft(int p){
        return p*2+1<heapsize;
    }
    boolean hasRight(int p){
        return p*2+2<heapsize;
    }
    boolean hasParent(int c){
        return c!=0;
    }
    public void MaxHeapify(int i){
        int key = i;
        int l = 0,r = 0;

        while(hasLeft(key)||hasRight(key)){
            if(hasLeft(key) &&hasRight(key)){
                l = left(key);
                r = right(key);
            }
            else if(hasLeft(key)){
                l = left(key);
                r = key;
            }
            else {
                //it is only a trick for convenient!
                l = key;
                r = right(key);
            }
            if(heap[key]>=heap[l]&&heap[key]>=heap[r]){
                break;
            }
            else if(heap[l]>=heap[r]){
                exchange(key,l);
                key  =l;
            }
            else{
                exchange(key,r);
                key = r;
            }
        }
    }
    //Some funtions of max-priority queuea of CLRS chapter 5
    int heapExtractMax(){
        if(heapsize<1){
            throw new IndexOutOfBoundsException("heap underflow");
        }
        int max = heap[0];
        exchange(0,heapsize-1);
        heapsize--;
        MaxHeapify(0);
        return max;
    }
    void heapIncreaseKey(int i, int key){
        if(key<heap[i]){
            throw new IndexOutOfBoundsException("decreasing");
        }
        heap[i] = key;
        while(i>0){
            int p = parent(i);
            if (heap[p]>=key){
                heap[i] = key;
                break;
            }
            heap[i] = heap[p];
            i = p;
        }
        if (i==0){
            heap[0] = key;
        }
    }
    void maxHeapInsert(int key){
        heapsize++;
        setHeapNode(heapsize-1,0xffffffff);
        heapIncreaseKey(heapsize-1,key);
    }
    void heapDelete(int i){
        //mind the possibility that heap[i] is smaller than heap[heapsize-1] because its not a sorted array,it will not influence the ans, but will change the order。
        if(i>=heapsize){
            throw new ArrayIndexOutOfBoundsException("node i didnt exist");
        }
        exchange(i,heapsize-1);
        heapsize--;
        MaxHeapify(i);
        //remind for writing
    }
}
