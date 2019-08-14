/*
Add the mathematical operators + or - before any of the digits in the decimal numeric string 123456789 such that the resulting mathematical expression adds up to 100. Return all possible solutions.

There are 12 solutions:

1+2+3-4+5+6+78+9
1+2+34-5+67-8+9
1+23-4+5+6+78-9
1+23-4+56+7+8+9
12+3+4+5-6-7+89
12+3-4+5+67+8+9
12-3-4+5-6+7+89
123+4-5+67-89
123+45-67+8-9
123-4-5-6-7+8-9
123-45-67+89
-1+2-3+4+5+6+78+9
Solution
Java: https://leetcode.com/playground/HV2jmPMU
* */
package leetcode;

public class SumTo100 {
    public static void main(String[] args) {
        SumTo100 sumTo100 = new SumTo100();
        int[] arr = new int[1];
        sumTo100.dfs(0,1,arr,0);
        System.out.println(sumTo100.counter );

    }
    public SumTo100(){
        remindmax = new int[12];
        remindmax[9] = 0;
        int temp =1;
        for (int i =8;i>=0;i--){
            remindmax[i] = remindmax[i+1]+temp*(i+1);
            temp *=10;
        }
    }
    int counter =0;
    int[] remindmax;
    void dfs(int sum,int key,int[] result,int num){
        int[] arr =new int[num+1];
        for (int i = 0; i < num; i++) {
            arr[i] = result[i];
        }

        if(sum==100&&key==10){
            //mind that while sum==100,key shoud be 10;
            for (int i = 0; i < num; i++) {
                System.out.print(result[i]+" ");
            }
            System.out.println();
            counter++;
        }
        int temp = 0;
        for(int i = 0; (i < 3) && ((key + i) < 10); i++){
            temp = temp*10+key+i;
            //mind the remindmax should also consider the 100;
            if(sum+temp<=100+remindmax[key+i]&&sum+temp>=100-remindmax[key+i]){
                arr[num] = temp;
              dfs(sum+temp,key+i+1,arr,num+1);
            }
            if(sum-temp<=100+remindmax[key+i]&&sum-temp>=100-remindmax[key+i]){
                arr[num] = -temp;
                dfs(sum-temp,key+i+1,arr,num+1);
            }
        }
    }
}
