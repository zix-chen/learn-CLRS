package DynamicProgramming;

import org.omg.CORBA.StringHolder;

public class MyString {
    public static void main(String[] args) {
        String y= "BDCABA";
        String x = "ABCBDAB";
        String x1 = "10010101";
        String y1 = "010110100";
        String s = "character";
        LongestPalindromeSubsequence(s);
        System.out.println(LCSlen(x1,y1));
    }
    public static int LongestPalindromeSubsequence(String s){
        int ans = 0;
        for (int i = 0; i < s.length()-1; i++) {
            String x ="";
            for (int j = 0; j <= i; j++) {
                x = x+s.charAt(i-j);
            }
            int temp = LCSlength(x,s.substring(i+1,s.length()));
            if(ans<temp*2)ans = temp*2;
        }
        for(int i = 0 ;i<s.length();i++){
            String x = "";
            for (int j = 1; j <=i ; j++) {
                x = x+s.charAt(i-j);
            }
            int temp = LCSlength(x,s.substring(i+1,s.length()));
            if(ans<temp*2+1) ans = temp*2+1;
        }
        return ans;
    }
    public static int LCSlength(String x,String y){
        int[][] tab = new int[2][y.length()+1];
        for (int i = 0; i < 2; i++) {
            tab[i][0] = 0;
        }
        for (int i = 0; i < y.length()+1; i++) {
            tab[0][i] = 0;
        }
        for (int i = 1; i < x.length()+1; i++) {
            for (int j = 1; j < y.length()+1; j++) {
                if(x.charAt(i-1)==y.charAt(j-1)) tab[i%2][j] = tab[(i-1)%2][j-1]+1;
                else if(tab[(i-1)%2][j]>=tab[i%2][j-1]) tab[i%2][j] = tab[(i-1)%2][j];
                else tab[i%2][j] = tab[i%2][j-1];
            }
        }
        return tab[x.length()%2][y.length()];
    }
    public static int LCSlen(String x,String y){
        int[][] tab = new int[x.length()+1][y.length()+1];
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <=y.length() ; j++) {
                tab[i][j] = Integer.MIN_VALUE;
            }
        }
        for (int i = 0; i <= x.length(); i++) {
            tab[i][0] = 0;
        }
        for (int i = 0; i <=y.length() ; i++) {
            tab[0][i] = 0;
        }
        LSCmemory(x,y,x.length(),y.length(),tab);
        PrintLSC(tab,x);
        return tab[x.length()][y.length()];
    }
    static void PrintLSC(int[][] tab,String x){
        int a= tab.length-1;
        int b=tab[0].length-1;
        while(a>0&&b>0){
            if(tab[a-1][b]==tab[a][b])a--;
            else if(tab[a][b-1]==tab[a][b])b--;
            else{
                System.out.print(x.charAt(a-1));
                a--;
                b--;
            }
        }
        System.out.println();
    }
    private static int LSCmemory(String x,String y,int a, int b,int[][] tab){
        if(tab[a][b]>=0) return tab[a][b];
        else if(x.charAt(a-1)==y.charAt(b-1))tab[a][b]= LSCmemory(x,y,a-1,b-1,tab)+1;
        else if(LSCmemory(x,y,a-1,b,tab)>=LSCmemory(x,y,a,b-1,tab))tab[a][b]= LSCmemory(x,y,a-1,b,tab);
        else tab[a][b]= LSCmemory(x,y,a,b-1,tab);
        return tab[a][b];
    }
}
