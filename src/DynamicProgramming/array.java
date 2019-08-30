package DynamicProgramming;


public class array {
    public static void main(String[] args) {
        int[] A = {1,3,4,2,5,6,8,9,1};
        array array = new array();
        array.LongestSubIncreasintArray(A);
    }
    public int LongestSubIncreasintArray(int[] A){
        if(A==null)return 0;
        int[] A2 = new int[A.length];
        A2[0] = 1;
        for (int i = 1; i < A.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if(A[j]<=A[i]&&A2[j]+1>max) max =A2[j]+1;
            }
            A2[i] = max;
        }
        int Max= 0;
        for (int i = 0; i < A2.length; i++) {
            if(A2[i]>Max) Max = A2[i];
        }
        System.out.println(Max);
        return Max;
    }
}
