package DynamicProgramming;

public class BalancedBST {
    public void Print(int[][] root,int a,int b){
        if(a-1==b) System.out.print("D"+a);
        else{
            int r = root[a][b];
            System.out.print(r);
            Print(root,a,r-1);
            System.out.print(" is the left");
            Print(root,r+1,b);
            System.out.print(" is the right");
        }
    }
}
