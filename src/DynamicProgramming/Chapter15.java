package DynamicProgramming;

public class Chapter15 {
    public static void main(String[] args) {
        Chapter15 print = new Chapter15();
        int m = 16;
        int[] A = {1,3,5,1,2,1,2,5,3};
        print.Print15_3(A,m);
        int[] B = {1,99,1,20,29,30,30,1,1,1,1};
        System.out.println(print.PlaningParty15_6(B));
     }
    public int Print15_3(int[] A, int m ){
        int n = A.length;
        int[] tab = new int[n+1];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = Integer.MAX_VALUE;
        }
        tab[0] = 0;
        for (int i = 1; i < n; i++) {
            int k =1;
            int cur = m-A[i-1];
            int cube=0;
            do{
                cube = cur*cur*cur;
                if(cube+tab[i-k]<tab[i])tab[i]=cube+tab[i-k];
                k++;
                if(i-k<0)break;
                cur=cur-A[i-k]-1;
            }while(cur>0);
        }
        int k =1;
        int cur = m+1;
        do{
            cur = cur-A[n-k]-1;
            if(tab[n-k]<tab[n])tab[n]=tab[n-k];
        }while(cur>0);
        return tab[n];
    }

    public int PlaningParty15_6(int[] ConvivialityTree){
        //For convience use arry to represent Tree,the key is the Conviviality,
        // 0 represent no soch a person, I assume that Conviviality>=0
        int n = ConvivialityTree.length;
        int[] attend = new int[n];
        int[] unattend = new int[n];
        for (int i = 0; i < n; i++) {
            attend[i] = -1;
            unattend[i] = -1;
            if(left(i)>=n){
                //leaf node
                attend[i] =ConvivialityTree[i];
                unattend[i] = 0;
            }
        }
        for (int i = n-1; i >=0 ; i--) {
            if(left(i)>=n)continue;
            attend[i] = ConvivialityTree[i]+unattend[left(i)]+unattend[right(i)];
            unattend[i] = attend[left(i)]+attend[right(i)];
        }

        return attend[0]>=unattend[0]?attend[0]:unattend[0];
    }
    int p(int child){
        return (child-1)/2;
    }
    int left(int p){
        return p*2+1;
    }
    int right(int p){
        return p*2+2;
    }
    int imageCompression15_8(int[][] pixels,int[][] disruption){
        //code did not checked!
        //but the method is right;
        int m = pixels.length;
        int n = pixels[0].length;
        int[][] tab = new int[2][n];
        for (int i = 0; i < n; i++) {
            tab[0][i] = 0;
        }
        for (int i = 1; i <= m; i++) {
            tab[i%2][0] = Math.min(tab[(i-1)%2][0]+disruption[i][0],tab[(i-1)%2][1]+disruption[i][0]);
            tab[i%2][n-1]=Math.min(tab[(i-1)%2][n-2]+disruption[i][n-1],tab[(i-1)%2][n-1]+disruption[i][n-1]);
            for (int j = 1; j < n-1; j++) {
                tab[i%2][j]=Math.min(tab[(i-1)%2][j-1]+disruption[i][j],tab[(i-1)%2][j]+disruption[i][j]);
                tab[i%2][j]=Math.min(tab[i%2][j],tab[(i-1)%2][j+1]+disruption[i][j]);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = min<=tab[m%2][i]?min:tab[m%2][i];
        }
        return min;
    }
    int invest(int invest,int feeToChag, int feeNoChag,int[][] rate){
        //without check!
        int[][] tab = new int[rate.length][rate[0].length];
        int n = rate.length;
        int m = rate[0].length;
        for (int i = 0; i < m; i++) {
            tab[0][i] = rate[0][i]*invest;
        }
        for (int i = 1; i < n; i++) {
            int maxsum = 0,maxsumj = 0;
            for (int j = 0; j < m; j++) {
                if(tab[i-1][j]>maxsum){
                    maxsum = tab[i-1][j];
                    maxsumj = j;
                }
            }
            for (int j = 0; j < m; j++) {
                if(j==maxsumj) tab[i][j]=rate[i][j]*maxsum+feeNoChag;
                else tab[i][j] = rate[i][j]*maxsum+feeToChag>=rate[i][j]*tab[i-1][j]+feeNoChag?
                        rate[i][j]*maxsum+feeToChag:rate[i][j]*tab[i-1][j]+feeNoChag;
            }
        }
        int max = 0;
        for (int i = 0; i <m ; i++) {
            if(max<tab[n-1][i])max = tab[n-1][i];
        }
        return max;
    }
}
