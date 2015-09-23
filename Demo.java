package hwSL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panasyuk on 12.09.2015.
 */
public class Demo {
    public static void main(String[] args) throws Exception, ArithmeticException {
       SL_File_New slfn = new SL_File_New();
        //demo(slfn);
        System.out.println(slfn.size());



    }

    public static void demo(SL_File_New slf) throws Exception{
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
        slf.add(88);

    }
}
