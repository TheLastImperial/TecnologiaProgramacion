import java.lang.reflect.Array;
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
        // testSimpleObj();
        // testP();
        // testSaul();
        // testSaul2();
        // testBarron();
        // testBarron2();
        // testClone();
        // testFinal();
        testMultiClass();
    }

    public static void testMultiClass(){
        Conjunto<?> elBueno;
        Conjunto<Alumno> alumnos = new Conjunto<Alumno>(
                new Alumno[]{
                        new Alumno("Rafael", 10, "1234"),
                        new Alumno("Ricardo", 20, "2354"),
                        new Alumno("Imperial", 26, "M12170614")
                }
        );

        Conjunto<Maestro> maestros = new Conjunto<Maestro>(
                new Maestro[]{
                        new Maestro("Lucia", 10, "zxcv"),
                        new Maestro("Pedro", 20, "qwer"),
                        new Maestro("Ramon", 26, "oiu")
                }
        );

        elBueno = alumnos.union(maestros);
        elBueno.print("El bueno: ");
    }

    private static void testFinal(){
        String[] strings1 = new String[]{"A", "B", "C"};
        Conjunto<String> set1 = new Conjunto<String>(strings1);

        Collection<String> strings2 = new ArrayList<>();
        strings2.add("RAFAEL");
        strings2.add("IMPERIAL");
        strings2.add("ROJO");
        Conjunto<String> set2 = new Conjunto<>(strings2);

        Conjunto<Par> productoC = set1.productC(set2);

        set1.print("Conjunto 1: ");
        set2.print("Conjunto 2: ");
        productoC.print("Producto C: ");
        set2.pow().print("POW producto: ");


    }

    private static void testClone(){
        SimpleObj a = new SimpleObj("Maria", 1);
        SimpleObj b = new SimpleObj("Simon", 2);
        ArrayList<SimpleObj> al = new ArrayList<SimpleObj>();
        al.add(a);
        CompleteObj co = new CompleteObj(al, "Hola", b);

        a = new SimpleObj("Maria", 1);
        b = new SimpleObj("Simon", 2);
        al = new ArrayList<SimpleObj>();
        al.add(a);
        CompleteObj co2 = new CompleteObj(al, "Hola", b);

        Conjunto<CompleteObj> c = new Conjunto<CompleteObj>();
        c.add(co);
        c.add(co2);

        CompleteObj co3 = new CompleteObj(al, "Hola", b);;

        c.add(co3);

        System.out.println(c);

        Conjunto<CompleteObj> c2 = c.clone();

        Conjunto<String> c3 = new Conjunto<String>(new String[]{
                "Hola", "Hola 2", "Hola 4"
        });

        Conjunto c4 = c2.union(c3);

        System.out.println(c);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c);

    }

    private static void testBarron2(){
        Conjunto<Integer> c1 = new Conjunto<Integer>(new Integer[]{1, 2, 3});
        Conjunto<Integer> c2 = new Conjunto<Integer>(new Integer[]{5, 7, 9});
        Conjunto<Integer> c3 = new Conjunto<Integer>(new Integer[]{1});
        Conjunto<Conjunto<Integer>> c4 = new Conjunto<Conjunto<Integer>>();
        c4.add(c1);
        c4.add(c2);
        c4.add(c3);
        System.out.println(c4);
        System.out.println(c4.contains(c3));
        c1 = (Conjunto<Integer>) c1.difference(c3);
        System.out.println(c1);
        System.out.println(c4);

    }

    private static void testBarron(){
        Integer arr[] = {
            1, 2, 3, 3, 1, 2
        };

        ArrayList<String> nombres = new ArrayList<String>();
        nombres.add("Nombre");
        nombres.add("Juan");
        nombres.add("Pedro");
        nombres.add("Pedro");
        nombres.add("Maria");

        Conjunto<String> a = new Conjunto<String>();
        Conjunto<Integer> b = new Conjunto<Integer>(arr);
        Conjunto<String> c = new Conjunto<String>(nombres);

        System.out.println(b);
        Conjunto n = new Conjunto(b);
        n.add("String");
        System.out.println(n);
        n = c.union(b);
        System.out.println(n);

        ArrayList<SimpleObj> sObj = new ArrayList<SimpleObj>();
        sObj.add(new SimpleObj("Persona", 1));
        sObj.add(new SimpleObj("Persona", 1));
        sObj.add(new SimpleObj("Juan", 2));
        sObj.add(new SimpleObj("Pedro", 3));
        sObj.add(new SimpleObj("Roberto", 4));
        sObj.add(new SimpleObj("Roberto", 4));

        Conjunto<SimpleObj> p = new Conjunto<SimpleObj>(sObj);

        Conjunto testPO = p.union(b);
        System.out.println(p);
        System.out.println(testPO);
        System.out.println(p);
        System.out.println(testPO.contains(new SimpleObj("Roberto", 4)));
        System.out.println(testPO.contains(1));
        System.out.println(testPO.contains(10));

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

    private static void testP(){
        Conjunto<String> a = new Conjunto<String>(new String[]{
                "a1", "a1", "a2", "a3"
        });
        Conjunto<String> b = new Conjunto<String>(new String[]{
                "b1", "b2", "b3"
        });

        System.out.println(a);
        System.out.println(b);
        // System.out.println(a.productC(b));
    }

    private static void testSaul(){
        System.out.println("+++String Sets+++");

        String[] strings1 = new String[]{"A", "AB", "B"};
        Conjunto<String> set1 = new Conjunto<String>(strings1);

        Collection<String> strings2 = new ArrayList<>();
        strings2.add("A");
        strings2.add("AB");
        strings2.add("C");
        strings2.add("CD");
        strings2.add("D");
        strings2.add("E");
        Conjunto<String> set2 = new Conjunto<>(strings2);

        Conjunto<String> set3 = new Conjunto<>();
        set3.add("CD");
        set3.add("D");

        Conjunto<String> set4 = new Conjunto<>();
        set4.add("A");
        set4.add("AB");
        set4.add("B");
        set4.add("C");
        set4.add("CD");
        set4.add("D");
        set4.add("E");
        set4.add("F");
        set4.add("FG");
        set4.add("G");
        set4.add("H");

        set1.print("set1");
        set2.print("set2");
        set3.print("set3");
        set4.print("Universo String");

        System.out.println("+++++++++++++++++++++++++++");
        System.out.println();

        System.out.println("+++Int Sets+++");

        Conjunto<Integer> set5 = new Conjunto<>();
        set5.add(1);
        set5.add(2);
        set5.add(4);

        Conjunto<Integer> set6 = new Conjunto<>();
        set6.add(3);
        set6.add(5);
        set6.add(8);

        Conjunto<Integer> set7 = new Conjunto<>();

        for (int i = 0; i <= 20; i++) {
            set7.add(i);
        }

        set5.print("set5");
        set6.print("set6");
        set7.print("Universe Int");

        System.out.println("+++++++++++++++++++++++++++");
        System.out.println();

        String A = "A";
        String BC = "BC";
        System.out.println("\"A\" ∈ set2 : " + set2.contains(A));
        System.out.println("\"BC\" ∈ set2 : " + set2.contains(BC));
        System.out.println("|set2| : " + set2.size());

        set2.union(set1).print("set2 ⋃ set1");
        set2.intersection(set1).print("set2 ⋂ set1");
        set2.difference(set1).print("set2 - set1");

        System.out.println("set3 ⊆ set2 : " + set3.subsetP(set2));
        System.out.println("set2 ⊆ set3 : " + set2.subsetP(set3));
        System.out.println("set3 ⊆ set3 : " + set3.subsetP(set3));
        System.out.println("set3 ⊂ set3 : " + set3.subset(set3));

        set1.productC(set2).print("set1 x set2");
        set1.productC(set5).print("set1 x set5");
        set5.productC(set1).print("set5 x set1");
        set2.complement(set4).print("complement set2");
        set5.complement(set7).print("complement set5");

        // set1.pow().print("set1 pow");

        // var clonedSet = set1.clone();
        // clonedSet.print("Cloned Set1");
    }


    private static void testSaul2(){
        System.out.println("+++String Sets+++");

        String[] strings1 = new String[]{"A", "AB", "B"};
        Conjunto<String> set1 = new Conjunto<String>(strings1);

        Collection<String> strings2 = new ArrayList<>();
        strings2.add("A");
        strings2.add("AB");
        strings2.add("C");
        strings2.add("CD");
        strings2.add("D");
        strings2.add("E");
        Conjunto<String> set2 = new Conjunto<>(strings2);

        Conjunto<String> set3 = new Conjunto<>();
        set3.add("CD");
        set3.add("D");

        Conjunto<String> set4 = new Conjunto<>();
        set4.add("A");
        set4.add("AB");
        set4.add("B");
        set4.add("C");
        set4.add("CD");
        set4.add("D");
        set4.add("E");
        set4.add("F");
        set4.add("FG");
        set4.add("G");
        set4.add("H");

        set1.print("set1");
        set2.print("set2");

        Conjunto<Integer> set5 = new Conjunto<>();
        set5.add(1);
        set5.add(2);
        set5.add(4);

        Conjunto<Integer> set6 = new Conjunto<>();
        set6.add(3);
        set6.add(5);
        set6.add(8);

        Conjunto<Integer> set7 = new Conjunto<>();

        for (int i = 0; i <= 20; i++) {
            set7.add(i);
        }

        set5.print("set5");

        System.out.println("+++++++++++++++++++++++++++");
        System.out.println();

        set1.productC(set2).print("set1 x set2");
        // set1.productC(set5).print("set1 x set5");
        // set5.productC(set1).print("set5 x set1");
    }

}
