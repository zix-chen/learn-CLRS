package algorithm;

public class Dijkstra {
    public static void main(String[] args) {
        graphnode[] nodes = new graphnode[4];
        graphnode temp = new graphnode(0,0,null);
        nodes[0] = temp;
        temp.next = new graphnode(1,2,null);
        temp = temp.next;
        temp.next = new graphnode(3,4,null);
        nodes[1] = new graphnode(1,0,null);
        nodes[1].next = new graphnode(0,2,null);
        temp = nodes[1].next;
        temp.next = new graphnode(2,1,null);
        temp =temp.next;
        temp.next = new graphnode(3,1,null);

        nodes[3] = new graphnode(3,0,null);
        nodes[3].next = new graphnode(0,4,null);
        temp = nodes[3].next;
        temp.next = new graphnode(1,1,null);
        nodes[2] = new graphnode(2,0,null);
        temp = nodes[2];
        temp.next = new graphnode(1,1,null);
        int i1 = 0;
        int[][] ans = sort(nodes,i1);
        for (int i = 0; i < ans[0].length; i++) {
            System.out.print(i +" to "+i1+" dist is "+" "+ans[0][i]+",last node is "+ans[1][i]);
            System.out.println();
        }
        System.out.println();

    }
    public static int[][] sort(graphnode[] arr,int i1){
        int[][] ans = new int[2][arr.length];

        boolean[] find = new boolean[arr.length];
        int[] cur = new int[arr.length];
        int[] last = new int[arr.length];
        for(int i = 0;i<arr.length;i++){
            cur[i] = Integer.MAX_VALUE;
        }
        find[i1] = true;
        ans[0][i1] = 0;
        ans[1][i1] = i1;
        int min;
        int key1 = i1;
        int key2 = 0;
        for (int i = 1; i < arr.length; i++) {
            min = Integer.MAX_VALUE;
            int minedge = ans[0][key1];
            graphnode temp = arr[key1];
            while(temp!=null){
                if(!find[temp.key]&&temp.edge+minedge<cur[temp.key]){
                    cur[temp.key] = temp.edge+minedge;
                    ans[1][temp.key] = key1;
                }
                temp = temp.next;
            }
            for (int j = 0; j < arr.length; j++) {
                if(!find[j]&&cur[j]<min){
                    min = cur[j];
                    key1 = j;
                }
            }
            ans[0][key1] = min;
            find[key1] = true;
        }
        return ans;
    }
}
