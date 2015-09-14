package hwSL;

/**
 * Created by panasyuk on 12.09.2015.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        SL_File slf = new SL_File();
        demo(slf);

        slf.add("Proverka");
        slf.add("Proverka_2");
        slf.add("Proverka_3");
        slf.add(new Zaglushka());

        System.out.println("size = " + slf.size());

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
