package DataSteucture;

public class RedBlackTree{
    //笔记22--成员内部类--子类为内部类且不在同一个外部类
    class InnerClassChildDM extends Person.InnerClassParent {
        InnerClassChildDM(Person per){
            per.super();
            System.out.println("成员内部类的子类为内部类且与父类不在同一个外部类");
        }
    }
    public static void main(String[] args) {
        RedBlackTree du = new RedBlackTree();
        Person per = new Person();
        InnerClassChildDM inCChDM = du.new InnerClassChildDM(per);
    }
}
