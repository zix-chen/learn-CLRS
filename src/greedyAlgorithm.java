import javafx.beans.binding.IntegerExpression;

public class greedyAlgorithm {
    public static void main(String[] args) {
        greedyAlgorithm algorithm = new greedyAlgorithm();
        int[] s = {1, 3, 0 ,5 ,3 ,5 ,6, 8, 8, 2, 12};
        int[] f = {4, 5 ,6 ,7, 9, 9, 10, 11, 12, 14 ,16};
        algorithm.ActivitiesArrangeUseDP(s,f);
    }
    public int[][][] ActivitiesArrangeUseDP(int[] s, int[] f){
        int n = s.length;
        int[][][] tab = new int[2][n+2][n+2];
        for (int i = 0; i <= n; i++) {
            tab[0][i][i+1] = 0;
        }
        int[] mys = new int[n+2];
        int[] myf = new int[n+2];
        for (int i = 1; i < mys.length-1; i++) {
            mys[i] = s[i-1];
            myf[i] = f[i-1];
        }
        myf[0] = 0;
        mys[mys.length-1] = Integer.MAX_VALUE;
        for (int i = 3; i <= n+2 ; i++) {
            for (int j = 0; j <= n-i+2; j++){
                for (int k = j+1; k <j+i-1;k++) {
                    if(myf[j]<=mys[k]&&myf[k]<=mys[j+i-1]){
                        if(tab[0][j][j+i-1]<tab[0][j][k]+tab[0][k][j+i-1]+1){
                            tab[0][j][j+i-1] = tab[0][j][k]+tab[0][k][j+i-1]+1;
                            tab[1][j][j+i-1] = k;
                        }
                    }
                }
            }
        }
        return tab;
    }
    public void Activities(int[] s ,int[] f){
        //It is needed that si<fi
        int n = s.length;
        int max = f[f.length-1];
        int[][][] tab = new int[2][max+1][max+1];
        for (int i = 0; i < max+1; i++) {
            for (int j = 0; j < max+1; j++) {
                tab[0][i][j] = Integer.MIN_VALUE;

            }
        }
        ActivitiesArrangeDP(s,f,1,max,tab);
        System.out.println();
    }
    public int ActivitiesArrangeDP(int[] s, int[] f,int a,int b,int[][][] tab){
        if(tab[0][a][b]>=0) return tab[0][a][b];
        else if(a>=b){
            tab[0][a][b] = 0;
            return 0;
        }
        else{
            int max= 0;
            int left,right,thing = 0;
            for (int i = 0; i <f.length ; i++) {
                if(f[i]<=b&&s[i]>=a){
                    left = ActivitiesArrangeDP(s,f,a,s[i],tab);
                    right = ActivitiesArrangeDP(s,f,f[i],b,tab);
                    if(left+right+1>max){
                        max = left+right+1;
                        thing = i;
                    }
                }
            }
            tab[0][a][b] = max;
            tab[1][a][b] = thing;
            return max;
        }
    }
}
