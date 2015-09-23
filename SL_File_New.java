package hwSL;

import java.io.*;
import java.util.Iterator;

/**
 * Created by panasyuk on 22.09.2015.
 */
public class SL_File_New implements SimpleList {
    private File list;
    private int count = 0;
    RandomAccessFile raf = null;


    public SL_File_New() throws Exception {
        list = new File("list.txt");
        if (!list.exists()) {
            list.createNewFile();
        }

    }

    @Override
    public void add(Object object) {
        try {
            raf = new RandomAccessFile(list, "rw");
            String s;
            while ((s=raf.readLine()) != null){
                raf.seek(raf.getFilePointer());
            }
            raf.writeBytes(object.toString() + "\n");
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException i) {
            }
        }


    }

    @Override
    public boolean contains(Object object) {
        try {
            raf = new RandomAccessFile(list, "r");
            String s;
            while ((s = raf.readLine()) != null) {
                if (s.equals(object.toString())){
                    return true;
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException i) {
            }
        }
        return false;
    }

    @Override
    public void remove(Object object) {
        File list_temp = new File("list_temp.txt");
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(list)));
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(list_temp)), true);
            String str;
            while ((str = br.readLine()) != null) {
                if (!str.equals(object.toString())) {
                    pw.println(str);
                }
            }
        } catch (IOException io) {
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException io) {
            }
        }
        list.delete();
        list_temp.renameTo(list);
    }

    @Override
    public int size() {
        int n = 0;
        try {
            raf = new RandomAccessFile(list, "r");
            String s;
            while ((s = raf.readLine()) != null) {
                n++;
            }
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException i) {
            }
        }
        return n;

    }

    public Object getCounter(int counter) {
        Object object = null;
        if (counter < size()) {
            try {
                raf = new RandomAccessFile(list, "r");
                String s;
                int n = 0;
                while ((s = raf.readLine()) != null) {
                    if (n == counter) {
                        object = s;
                    }
                    n++;
                }
            } catch (IOException io) {
            } finally {
                try {
                    raf.close();
                } catch (IOException i) {
                }
            }
            return object;
        }
        return "Object on pos. " + counter + " - not found!" ;
    }

    @Override
    public Iterator iterator() {

        return new FileIterator();

    }

    class FileIterator implements Iterator {
        private int counter = count;
        private Object nextObject = null;


        @Override
        public boolean hasNext() {
            return (counter < size());
        }

        @Override
        public Object next() {

            if (nextObject == null) {
                nextObject = getCounter(counter);
            } else if (hasNext()) {
                nextObject = getCounter(count + 1);
            }
            count++;
            return nextObject;
        }
    }
}
