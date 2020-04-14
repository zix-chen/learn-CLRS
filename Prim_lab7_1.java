package Algorithm_ADOJ;


import java.io.*;
import java.util.*;

/**
 * Hong is now challenging the great mystery! The map of the great mystery can be regarded as n∗mn*mn∗m grids, and Hong can only walk up, down, left or right between the grids. Every grid has a magic value. If Hong wants to pass from a grid(x1,y1x_1, y_1x1​,y1​) with value wx1y1w_{x_1y_1}wx1​y1​​ to a grid(x2,y2x_2,y_2x2​,y2​) with value wx2y2w_{x_2y_2}wx2​y2​​ for the first time, she should use wx1y1w_{x_1y_1}wx1​y1​​ xor wx2y2w_{x_2y_2}wx2​y2​​ unit of magic power. Here xor means bitwise exclusive or.
 * <p>
 * Hong is at grid (1,1) initially, and she wants to know at least how many unit of magic power she should use to reach all the grids.
 * Input
 * <p>
 * The first line contains two integers n,mn,mn,m, which is described before.
 * <p>
 * Following nnn lines contain mmm integers for each line, indicates the magic value for each grid.
 * Output
 * <p>
 * Output one integer, indicating the answer.
 * Sample input
 * <p>
 * 3 3
 * 1 4 3
 * 2 7 1
 * 2 9 0
 * <p>
 * Copy
 * Sample output
 * <p>
 * 29
 * <p>
 * Copy
 * Limit
 * <p>
 * 1 second for each test case. The memory limit is 256MB.
 * <p>
 * For 50% of the test cases, n,m≤10,0≤wij≤100000n,m\le10,0\le w_{ij}\le100000n,m≤10,0≤wij​≤100000.
 * <p>
 * For 100% of the test cases, n,m≤1000,0≤wij≤100000n,m\le1000,0\le w_{ij}\le100000n,m≤1000,0≤wij​≤100000.
 */
public class Prim_lab7_1 {
    static class Node {
        int id;
        int key;

        Node(int id, int key) {
            this.id = id;
            this.key = key;
        }
        public Node() {
        }
    }
    static class Edge{
        int edge;
        int nextNode;

        public Edge(int edge, int nextNode) {
            this.edge = edge;
            this.nextNode = nextNode;
        }
    }

    static void prim(int[][] table, int n, int m) {
        int all = n * m;
        /**
         * 计算并存入无向边
         */
        ArrayList<LinkedList<Edge>> myMatrix = new ArrayList<LinkedList<Edge>>();
        for (int i = 0; i < all; i++) {
            LinkedList<Edge> temp = new LinkedList<>();
            myMatrix.add(i, temp);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                int edge = table[i][j] ^ table[i][j + 1];
                myMatrix.get(i * m + j).add(new Edge(edge,i * m + (j + 1)));
                myMatrix.get(i * m + (j + 1)).add(new Edge(edge,i * m + j));
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                int edge = table[i][j] ^ table[i + 1][j];
                myMatrix.get(i * m + j).add(new Edge(edge,(i + 1) * m + j));
                myMatrix.get((i + 1) * m + j).add(new Edge(edge,i * m + j));
            }
        }
        Comparator<Node> cmp = new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return node.key - t1.key;
            }
        };
        PriorityQueue<Node> queue = new PriorityQueue<>(cmp);
        Node[] nodes = new Node[all];
        for (int i = 0; i < all; i++) {
            nodes[i] = new Node(i, Integer.MAX_VALUE);
            queue.add(nodes[i]);
        }
        nodes[0].key = 0;
        /**
         * 测试集合可能很大 用long
         */
        long ans = 0;
        HashSet<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            set.add(node.id);
            ans+=node.key;
            int i = 0;
            LinkedList<Edge> list = myMatrix.get(node.id);
            for (Iterator<Edge> iter = list.listIterator(); iter.hasNext(); ) {
                Edge e = iter.next();
                if ((!set.contains(e.nextNode))&&e.edge<nodes[e.nextNode].key){
                    /**
                     * 直接修改值优先队列不会重新排序,要先删除再插入.
                     */
//                    nodes[e.nextNode].key = e.edge;
                    queue.remove(nodes[e.nextNode]);
                    nodes[e.nextNode].key = e.edge;
                    queue.add(nodes[e.nextNode]);

                }
            }

        }
        System.out.println(ans);

    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] table = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table[i][j] = in.nextInt();
            }
        }
        prim(table, n, m);


        out.close();
    }


    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}
