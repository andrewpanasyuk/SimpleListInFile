package hwSL;

/**
 * Created by panasyuk on 12.09.2015.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        SL_File slf = new SL_File();
//        System.out.println(slf.size());
//        demo(slf);

//        slf.add("Proverka");
//        slf.add("Proverka_2");
//        slf.add("Proverka_3");
//        slf.add(new Zaglushka());
//        System.out.println(slf.getCounter(5));
//        for (Object o: slf){
//
//        }
        while (slf.iterator().hasNext()){
            System.out.println(slf.iterator().next());
        }
//        System.out.println(slf.iterator().next());
//        System.out.println();
//
//        System.out.println("size = " + slf.size());

    }

    public static void demo(SL_File slf) throws Exception{
        slf.add(1);
        slf.add(2);
        slf.add(3);
        slf.add(4);
        slf.add(5);
        slf.add(6);
        slf.add(7);
        slf.add(8);
        slf.remove(2);
        slf.remove(6);
    }
}
