package leetcode;

import javax.lang.model.element.NestingKind;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean bol = solution.isPalindrome(1000000001);
        String s = "";
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.append('a');
        System.out.println(stringBuilder);
        String st = stringBuilder.toString();
    }
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        else if(x==0){
            return true;
        }
        int len = 0;
        long temp = 10;
        for(int i =1;i<12;i++){
            if(x/temp==0){
                len = i;
                break;
            }
            //temp should not be int or it will stack over flow
            temp*=10;
        }
        long left = 0;
        long lmax = x;
        long rmax = x;
        long right = 0;
        temp/=10;
        long[][] check = new long[2][12];
        for(int i =0;i<=len/2;i++){
            left = lmax%10;
            right = rmax/temp;
            lmax/=10;
            rmax-=right*temp;
            temp/=10;
            check[0][i] = left;
            check[1][i] = right;
            if(left!=right){
                return false;
            }
        }
        return true;
    }
}
