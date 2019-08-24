package DataSteucture;

import java.util.Stack;

public class BST<T> {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>(null,0);
        bst.size = 8;
        BST<Integer>.node[] nodes = new BST.node[6];
        nodes[0] = bst.new node(6,6);
        bst.root = nodes[0];
        for (int i = 0; i < 3; i++) {
            BST.node temp1 = bst.new node(i,i);
            BST.node temp2 = bst.new node(i+10,i+10);
            nodes[i+1] = temp1;
            nodes[i+2] = temp2;
            nodes[i].left = temp1;
            temp1.p = nodes[i];
            nodes[i].right = temp2;
            temp2.p = nodes[i];
        }
        bst.InOrderByPointer(bst.root);
        System.out.println();
        BST.node test = bst.TreePredecessor(nodes[0]);
        System.out.println(test.key);
        BST<Integer> bst2 = new BST<>(null,0);
        BST<Integer>.node[] nodes2 = new BST.node[40];
        BST.node node = bst2.new node(99,99);
        bst2.TreeInsertRecrusion(bst2.root,node);
        for (int i = 0; i < 10; i++) {
            nodes2[i] = bst2.new node(i*16,i*16);
            //bst2.TreeInsertRecrusion(bst2.root,nodes2[i]);
            bst2.TreeInsert(nodes2[i]);
        }
        System.out.println();
        for (int i = 10; i < 20; i++) {
            nodes2[i] = bst2.new node((i-9)*22,(i-9)*22);
            bst2.TreeInsert(nodes2[i]);
        }
        bst2.InOrder(bst2.root);
        System.out.println();
        bst2.TreeDelete(nodes2[15]);
        bst2.TreeDelete(nodes2[4]);
        bst2.InOrder(bst2.root);
        System.out.println(bst2.size);
    }
    int size;
    node root;
    node[] arr;
    public class node{
        public int key;
        public T data;
        public node p;
        public node left;
        public node right;
        public node(int key,T data){
            this.key = key;
            this.data = data;
        }
        public node(node p,node left, node right, T data){
            this.data = data;
            this.p = p;
            this.left = left;
            this.right = right;
        }
    }

    public BST(node root,int size){
        this.root = root;
        this.size = size;
    }
    public int[] InOrderByPointer(node root){
        node cur1 = root;
        node cur2 = cur1;
        int i =0;
        int[] ans = new int[size];
        loop:while(true){
            while(cur1.left!= null){
                cur2 = cur1;
                cur1 = cur1.left ;
            }
            while(cur1.right==null||cur1.right==cur2){
                if (cur1.right == null) {
                    System.out.print(cur1.data+"    ");
                    ans[i++] = cur1.key;
                }
                cur2 = cur1;
                cur1 = cur1.p;
                if(cur1==null){
                    break loop;
                }
            }
            System.out.print(cur1.data+"    ");
            ans[i++] = cur1.key;
            cur2 = cur1;
            cur1 = cur1.right;
        }

        return ans;
    }
    public void InOrder(node root){
        if(root==null)return;
        InOrder(root.left);
        System.out.print(root.data+" ");
        InOrder(root.right);
    }
    public int[] InOrderByStack(node root){
        Stack<node> stack = new Stack<>();
        node cur = root;
        int[] ans = new int[size];
        int i = 0;
        stack.push(cur);
        loop:while(true){
            while(cur.left!=null){
                stack.push(cur.left);
                cur = cur.left;
            }
            while(cur.right==null){
                System.out.print(cur.data+" ");
                ans[i++] = cur.key;
                stack.pop();

                if(stack.empty()){
                    break loop;
                }
                cur = stack.peek();
            }
            System.out.print(cur.data+" ");
            ans[i++] = cur.key;
            stack.pop();
            cur = cur.right;
            stack.push(cur);
        }
        return ans;
    }
    node TreeMinunm(node root){
        if(root.left==null)return root;
        return TreeMinunm(root.left);
    }
    node TreeMaxium(node root){
        return root.right==null?root:TreeMaxium(root.right);
    }
    node TreePredecessor(node node){
        if(node.left!=null){
            return TreeMaxium(node.left);
        }
        node temp = node;
        while(node!=null){
            temp = node;
            node = node.p;
            if(temp==node.right)return node;
        }
        return null;
    }
    node TreeSeccessor(node node){
        if(node.right!=null){
            return TreeMinunm(node.right);
        }
        node temp = node;
        while(node!=null){
            temp = node;
            node = node.p;
            if(temp==node.left)return node;
        }
        return null;
    }
    void TreeInsert(node node){
        node y = root;
        node x = root;
        while(x!=null){
            y = x;
            if(x.key>node.key) x = x.left;
            else x = x.right;
        }
        if(y==null) root = node;
        else if(y.key>node.key) y.left = node;
        else y.right = node;
        node.p = y;
        size++;
    }
    void TreeInsertRecrusion(node troot,node node){
        if(troot==null){
            root = node;
            size++;
        }
        else if(troot.left==null&&troot.key>node.key){
            troot.left = node;
            node.p = troot;
            size++;
        }
        else if(troot.right==null&&troot.key<=node.key){
            troot.right = node;
            node.p = troot;
            size++;
        }
        else if(troot.key>node.key) TreeInsertRecrusion(troot.left,node);
        else TreeInsertRecrusion(troot.right,node);
    }
    public void Transplant(node u, node v){
        //u cannot be null ,but v may be null
        if(u.p==null) root = v;
        else if(u == u.p.left)u.p.left = v;
        else u.p.right = v;
        if(v!=null) v.p =u.p;
    }
    public void TreeDelete(node z){
        size--;
        if(z.left==null){
            Transplant(z,z.right);
        }
        else if(z.right==null){
            Transplant(z,z.left);
        }
        else if(z.right.left==null){
            Transplant(z,z.right);
            z.right.left = z.left;
            z.right.left.p = z.right;
        }
        else {
            node temp = TreeSeccessor(z.right);
            Transplant(temp,temp.right);
            temp.right = z.right;
            temp.right.p = temp;
            temp.left = z.left;
            temp.left.p = temp;
            Transplant(z,temp);
        }
    }
}
