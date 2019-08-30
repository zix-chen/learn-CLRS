package DynamicProgramming;

public class Matric {
    public static void main(String[] args) {
        int[] ar = {5, 10, 3,12, 5,50, 6};
        System.out.println(multip(ar));
    }
    public static int  multip(int[] rank){
        int n = rank.length-1;
        int[][] T = new int[n+1][n+1];
        int[][] S = new int[n+1][n+1];
        for (int i = 1; i <=n ; i++) {
            T[i][i] = 0;
        }
        for (int i = 1; i <n; i++) {
            for (int j = 1; j+i <=n ; j++) {
                int temp = Integer.MAX_VALUE;
                for (int k = j; k < j+i; k++) {
                    int temp2 = rank[j-1]*rank[k]*rank[j+i]+T[j][k]+T[k+1][j+i];
                    if(temp>temp2){
                        temp = temp2;
                        S[j][j+i]=k;
                    }
                }
                T[j][j+i] = temp;
            }
        }
        MatricChainperform(S,1,n);
        return T[1][n];
    }
    //***************
    //CLRS 15.2-1
    public static void MatricChainperform(int[][] S,int i,int j){
        if(i==j) System.out.print("A"+i);
        else{
            System.out.print("(");
            MatricChainperform(S,i,S[i][j]);
            System.out.print("*");
            MatricChainperform(S,S[i][j]+1,j);
            System.out.print(")");
        }
    }
}
