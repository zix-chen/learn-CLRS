package DynamicProgramming;

public class CutRod {
    public static void main(String[] args) {
        int[] p ={1, 5, 8, 9, 10 ,17, 17 ,20 ,24, 30};
        int[][] ans = CutRodWithCost(7,p,1);
        for (int i = 0; i < ans[0].length; i++) {
            System.out.print(ans[1][i]+" ");
        }
        System.out.println();
        for (int i = 0; i <ans[0].length ; i++) {
            System.out.print(ans[0][i]+" ");
        }
    }

    public static int  Cut(int n,int[] price){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.MIN_VALUE;
        }
        return CutRodRecursion(n,price,arr);
    }
    private static int CutRodRecursion(int n,int[] price,int[] ans){
        if(n==0) return 0;
        else if(ans[n-1]>0) return ans[n-1];
        else {
            int m = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                m = Math.max(m,CutRodRecursion(n-i-1,price,ans)+price[i]);
            }
            ans[n-1] = m;
            return m;
        }
    }
    public static int[][] CutRodDownUp(int n,int[] price){
        int[][] ans = new int[2][n+1];
        ans[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int temp = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                if(ans[0][i-j]+price[j-1]>temp) {
                    temp =ans[0][i-j]+price[j-1];
                    ans[1][i] = j;
                }

            }
            ans[0][i] = temp;
        }
        return ans;
    }
    public static int[][] CutRodWithCost(int n,int[] p,int cost){
        int[][] ans = new int[2][n+1];
        for (int i = 0; i < n+1; i++) {
            ans[0][i] = Integer.MIN_VALUE;
        }
        ans[0][0] = 0;
        int pi = 0;
        for (int i = 1; i <= n; i++) {
             int temp = Integer.MIN_VALUE;
            for (int j = 1; j < i; j++) {
                if(temp<ans[0][i-j]+p[j-1]-cost){
                    temp = ans[0][i-j]+p[j-1]-cost;
                    ans[1][i]=j;
                }
            }
            if(temp<p[i-1]){
                temp = p[i-1];
                ans[1][i] = i;
            }
            ans[0][i] = temp;
        }
        return ans;
    }
}
