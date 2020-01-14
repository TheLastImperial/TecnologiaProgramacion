public class Main {
    public static void main(String args[]){
        Tree tr = new Tree();
        tr.insert(5);
        tr.insert(6);
        tr.insert(7);
        tr.insert(1);
        tr.insert(2);
        tr.insert(4);
        System.out.println(tr.max());
        System.out.println(tr.size());
        System.out.println(tr.father(5));
        System.out.println("Hola");
        System.out.println(tr.sum());
        System.out.println(tr.size());
        System.out.println(tr.avg());
        System.out.println("Hola");
        System.out.println(tr.genNodes(1));
    }
}
