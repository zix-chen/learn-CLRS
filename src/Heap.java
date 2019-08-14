

public class Heap {

    public static void main(String[] args) {
        int[] test = {2,17,14,6,13,10,1,5,1,12};
        Heap heap = new Heap(test,10);
        heap.MaxHeapify(heap);
        for(int i =0;i<heap.heapsize;i++){
            System.out.println(heap.heap[i]);
        }
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
    public void MaxHeapify(Heap h){
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
}
