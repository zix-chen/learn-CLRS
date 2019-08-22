package heap;

//this is the My solution of 6.5-9 of <<CLRS>>
public class MergeSortedArraysByHeapSort {
    public static void main(String[] args) {

        int[][] ar = {{1,5,8},{1,3,5},{5,6,12}};
        MergeSortedArraysByHeapSort test = new MergeSortedArraysByHeapSort();
        int[] ans = test.sort(ar, 3,9);
    }
    public int[] sort(int[][] ar,int k,int n){
        //the k arrays don need to have the same length, as far as the len sum is n is OK
        int[] ans = new int[n];
        int len = n/k+1;
        int pt = n-1;
        int[] arpt = new int[k];
        mynode[] cur = new mynode[k];
        for (int i = 0; i < k; i++) {
            arpt[i] = ar[i].length-1;
            cur[i] = new mynode(ar[i][arpt[i]],i);
        }
        HeapOfClass maxheap = new HeapOfClass(cur,k);
        maxheap.buildMaxHeap();
        while(pt>=0){
            mynode root = maxheap.heap[0];
            ans[pt] = root.key;
            pt--;
            arpt[root.inf]--;
            if(arpt[root.inf]<0){
                maxheap.heapDelete(0);
                continue;
            }
            root.key = ar[root.inf][arpt[root.inf]];
            maxheap.MaxHeapify(0);
        }
        return ans;
    }
}
