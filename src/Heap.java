

public class Heap {

    public static void main(String[] args) {
        int[] test = {2,17,14,6,13,10,1,5,1,12};
        int[] test2 ={15,13,9,5,12,8,7,4,0,6,2,1,0,0,0,0,};
        Heap heap1 = new Heap(test2,12);
        //heap.MaxHeapify();
        heap1.maxHeapInsert(10);
        for(int i =0;i<heap1.heapsize;i++){
            System.out.print(heap1.heap[i]+" ");
        }
        System.out.println();
    }
    public Heap(int[] arr, int size){
        heap =arr;
        heapsize = size;
    }
    int[] heap;
    int heapsize;
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
    public void MaxHeapify(){
        int key = 0;
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
        MaxHeapify();
        return max;
    }
    void heapIncreaseKey(int i, int key){
        if(key<heap[i]){
            throw new IndexOutOfBoundsException("decreasing");
        }
        heap[i] = key;
        while(i>0){
            int p = parent(i);
            if (heap[p]>=heap[i]){
                break;
            }
            exchange(i,p);
            i = p;
        }
    }
    void maxHeapInsert(int key){
        heapsize++;
        heap[heapsize-1] = -0xffffff;
        heapIncreaseKey(heapsize-1,key);
    }
}
