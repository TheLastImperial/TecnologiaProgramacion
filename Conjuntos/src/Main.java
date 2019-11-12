import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String args[]){
        // unionEmptyConj();
        // unionArrConj();
        // unionCollConj();
        // intEmptyConj();
        // intArrConj();
        // difArrConjB();
        testSimpleObj();
    }

    private static void unionEmptyConj(){
        Conjunto<String> a = new Conjunto<String>();
        Conjunto<String> b = new Conjunto<String>();

        a.add("a1");
        a.add("a1");
        a.add("a2");
        a.add("a3");
        a.add("a4");
        a.add("a5");
        a.add("a6");
        a.add("a7");

        b.add("b1");
        b.add("b2");
        b.add("b3");
        b.add("b4");
        b.add("b5");
        b.add("b6");
        b.add("b7");

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.union(b));
    }

    private static void unionArrConj(){
        Conjunto<String> a = new Conjunto<String>(new String[]{
                "a1", "a1", "a2", "a3"
        });
        Conjunto<String> b = new Conjunto<String>(new String[]{
                "b1", "b2", "b3"
        });

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.union(b));
    }

    private static void unionCollConj(){
        ArrayList<String> colA = new ArrayList<String>();

        colA.add("a1");
        colA.add("a1");
        colA.add("a2");
        colA.add("a3");
        colA.add("a4");
        colA.add("a5");
        colA.add("a6");
        colA.add("a7");

        ArrayList<String> colB = new ArrayList<String>();

        colB.add("b1");
        colB.add("b2");
        colB.add("b3");
        colB.add("b4");
        colB.add("b5");
        colB.add("b6");
        colB.add("b7");

        Conjunto<String> a = new Conjunto<String>(colA);
        Conjunto<String> b = new Conjunto<String>(colB);

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.union(b));
    }

    private static void intEmptyConj(){
        Conjunto<String> a = new Conjunto<String>();
        Conjunto<String> b = new Conjunto<String>();

        a.add("a1");
        a.add("a1");
        a.add("a2");
        a.add("a3");
        a.add("a4");
        a.add("a5");
        a.add("a6");
        a.add("a7");

        b.add("b1");
        b.add("b2");
        b.add("b3");
        b.add("b4");
        b.add("b5");
        b.add("b6");
        b.add("b7");

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.intersection(b));
    }

    private static void intArrConj(){
        Conjunto<String> a = new Conjunto<String>(new String[]{
                "a1", "a1", "a2", "a3"
        });
        Conjunto<String> b = new Conjunto<String>(new String[]{
                "a1", "a2", "a5"
        });

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.intersection(b));
    }

    private static void intArrConjB(){
        Conjunto<String> a = new Conjunto<String>(new String[]{
                "a1", "a1", "a2", "a3"
        });
        Conjunto<String> b = new Conjunto<String>(new String[]{
                "a1", "a2", "a5"
        });

        System.out.println(a);
        System.out.println(b);
        System.out.println(b.intersection(a));
    }

    private static void difArrConjB(){
        Conjunto<String> a = new Conjunto<String>(new String[]{
                "a1", "a1", "a2", "a3"
        });
        Conjunto<String> b = new Conjunto<String>(new String[]{
                "a1", "a2", "a5"
        });

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.difference(b));
        System.out.println(b.difference(a));
    }

    private static void testSimpleObj(){
        Conjunto<SimpleObj> a = new Conjunto<SimpleObj>(new SimpleObj[]{
                new SimpleObj("Juan", 1),
                new SimpleObj("Juan", 1),
                new SimpleObj("Pedro", 2),
                new SimpleObj("Ramon", 3),
                new SimpleObj("Perez", 4)
        });
        Conjunto<SimpleObj> b = new Conjunto<SimpleObj>(new SimpleObj[]{
                new SimpleObj("Juan", 5),
                new SimpleObj("Pedro", 5),
                new SimpleObj("Ramon", 5),
                new SimpleObj("Perez", 5),
                new SimpleObj("Ramon", 3)
        });

        System.out.println(a);
        System.out.println(b);
        // System.out.println(a.union(b));
        // System.out.println(a.intersection(b));
        System.out.println(a.difference(b));
        System.out.println(b.difference(a));
    }
}
