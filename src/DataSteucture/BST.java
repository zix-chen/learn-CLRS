package DataSteucture;

import java.util.Stack;
//also apply RedBlackTree function, to use it ,you should us insert and delete for RBtree
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
        bst2.RedBlackInsert(node);
        for (int i = 0; i < 10; i++) {
            nodes2[i] = bst2.new node(i*16,i*16);
            //bst2.TreeInsertRecrusion(bst2.root,nodes2[i]);
            bst2.RedBlackInsert(nodes2[i]);
            bst2.InOrder(bst2.root);
        }
        System.out.println();
        for (int i = 10; i < 20; i++) {
            nodes2[i] = bst2.new node((i-9)*22,(i-9)*22);
            bst2.RedBlackInsert(nodes2[i]);
        }
        for (int i = 20; i < 40; i++) {
            nodes2[i] = bst2.new node((i-17)*11,(i-17)*11);
            bst2.RedBlackInsert(nodes2[i]);
        }
        bst2.InOrder(bst2.root);
        System.out.println();
        for (int i = 7; i < 47; i++) {
            bst2.TreeDelteteBalance(nodes2[i%40]);
            if(i==21){
                bst2.TreeDelteteBalance(node);
            }
            bst2.InOrderByPointer(bst2.root);
            System.out.println();
            System.out.println(bst2.size);
        }
        System.out.println();


    }
    int size;
    node root;
    node[] arr;
    public class node{
        public int size;
        public int height;
        public boolean color;
        public int key;
        public T data;
        public node p;
        public node left;
        public node right;
        public node(int key,T data){
            this.key = key;
            this.data = data;
        }
        public node(node p,node left, node right,int height, T data){
            this.data = data;
            this.p = p;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        public node(node p,node left, node right, T data){
            this.data = data;
            this.p = p;
            this.left = left;
            this.right = right;
        }
        public node(int key,T data,boolean color){
            this(key,data);
            this.color = color;
        }
    }
    public BST(){
        root = null;
        size = 0;
    }
    public BST(node root,int size){
        this.root = root;
        this.size = size;
    }
    public int[] InOrderByPointer(node root){
        if(root==null)return null;
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
        if(root.p!=null&&root.p.color==true&&root.color==true){
            System.out.println();
            System.out.println(root+" "+root.key+"wrong!!!");
            throw new NullPointerException(" sdd");
        }
        System.out.print(root.data+" ");
        InOrder(root.right);
    }
    public int[] InOrderByStack(node root){
        if(root ==null)return null;
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
            node temp = TreeMinunm(z.right);
            Transplant(temp,temp.right);
            temp.right = z.right;
            temp.right.p = temp;
            temp.left = z.left;
            temp.left.p = temp;
            Transplant(z,temp);
        }
    }
    public void TreeDelteteBalance(node z){
        if(size/2==0){
            TreeDelete(z);
        }
        else{
            size--;
            if(z.left==null){
                Transplant(z,z.right);
            }
            else if(z.right==null){
                Transplant(z,z.left);
            }
            else {
                node temp = TreeMaxium(z.left);
                if(temp.p!=z){
                    Transplant(temp,temp.left);
                    temp.left = z.left;
                    temp.left.p = temp;
                }
                temp.right =z.right;
                temp.right.p = temp;
                Transplant(z,temp);
            }
        }

    }
    public node TressSearch(int key){
        node cur = root;
        while(cur!=null){
            if(cur.key==key) return cur;
            else if(cur.key<key) cur = cur.right;
            else cur = cur.left;
        }
        return null;
    }
    //***********************************
    //functions for RedBlackTree
    public void RedBlackInsert(node node){
        node y = root;
        node x = root;
        while(x!=null){
            y = x;
            if(node.key>x.key) x = x.right;
            else x = x.left;
        }
        if(y==null) root = node;
        else if(y.key>node.key) {
            y.left = node;
        }
        else{
            y.right = node;
        }
        node.p = y;
        size++;
        //TreeInsert(node);
        node.color = true;
        RBInsertFixup(node);
    }
    public void LeftRotate(node node){
        node y = node.right;
        node.right = y.left;
        if(y.left!=null){
            y.left.p = node;
        }
        y.p = node.p;
        if(node==root) root = y;
        else if(node == node.p.left){
            node.p.left = y;
        }
        else {
            node.p.right = y;
        }
        y.left = node;
        node.p = y;
    }
    public void RightRotate(node node){
        node y = node.left;
        node.left = y.right;
        if(y.right!=null){
            y.right.p = node;
        }
        y.p = node.p;
        if(node==root){
            root=y;
        }
        else if(node == node.p.left){
            node.p.left = y;
        }
        else{
            node.p.right = y;
        }
        node.p = y;
        y.right = node;
    }
    public void RBInsertFixup(node node){
        while(node!=root&&node.p.color==true){
            if(node.p==node.p.p.left){
                node y = node.p.p.right;
                if(y!=null&&y.color==true){
                    node.p.color = false;
                    y.color = false;
                    node.p.p.color = true;
                    node = node.p.p;
                }
                else{
                    if(node == node.p.right){
                        node = node.p;
                        LeftRotate(node);
                    }
                    node.p.color = false;
                    node.p.p.color = true;
                    RightRotate(node.p.p);
                }
            }
            else{
                node y = node.p.p.left;
                if(y!=null&&y.color==true){
                    node.p.color = false;
                    y.color = false;
                    node.p.p.color = true;
                    node = node.p.p;
                }
                else{
                    if(node == node.p.left){
                        node = node.p;
                        RightRotate(node);
                    }
                    node.p.color = false;
                    node.p.p.color = true;
                    LeftRotate(node.p.p);
                }
            }
        }
        root.color = false;
    }
    //**************************************
    /*
    *persistent dynamic sets 持久动态集的插入功能
    *未测试过，didn't check so may have bugs;
    * there is a mistake I should not add the parent ,because those nodes that didnot changed ,
    * their parent also didnot change so the parent is not right;
     */
    public node Copy(node node){
        if(node==null) return null;
        node temp = new node(node.p,node.left,node.right,node.data);
        return temp;
    }
    public node Copy(node node,node p){
        node temp = Copy(node);
        temp.p = p;
        return temp;
    }
    public BST PersisitentTreeInsert(BST<T> bst, int key){
        BST<T> ans= new BST<>();
        ans.size = bst.size+1;
        ans.root = Copy(bst.root);
        node cur = ans.root;
        node last = ans.root;
        while(cur!=null){
            last = cur;
            if(cur.key>key){
                cur.left = Copy(cur.left,cur);
                cur = cur.left;
            }
            else{
                cur.right = Copy(cur.right,cur);
                cur = cur.right;
            }
        }
        node temp = new node(key,null);
        if(last==null){
            ans.root = temp;
        }
        else if(last.key>key){
            last.left = temp;
        }
        else{
            last.right = temp;
        }
        return ans;
    }
    //*******************************
    /*
    *实现AVL tree 的 插入操作
    * 利用了height，不使用height 则是普通BST
    * 暂时先不写了~~~~~~
     */
    public void AVLInsert(node troot,node node){
        node y = troot;
        node x = troot;
        while(x!=null){
            y = x;
            if(x.key>node.key){
                x = x.left;
            }
            else{
                x = x.right;
            }
        }
        node.p = y;
        if(y==null){
            root = node;
        }
        else if(y.key>node.key){
            y.left = node;
            if(y.right==null){
                y.height = 1;
            }
        }
        else{
            y.right = node;
            if(y.left==null){
                y.height = 1;
            }
        }
        node.height = 0;
        while(y!=troot){
            y.height = Math.max(y.left.height,y.right.height);
        }
    }
}
