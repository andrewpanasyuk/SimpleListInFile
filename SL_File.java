package hwSL;

import java.io.*;
import java.util.Iterator;

/**
 * Created by panasyuk on 12.09.2015.
 */
public class SL_File implements SimpleList {
    private long lastPosition;
    private File list;
    private File list_temp;
    private int size;
    private int count = 0;


    public SL_File() throws Exception {
        list = new File("list.txt");
        if (!list.exists()) {
            list.createNewFile();
            //lastPosition = 0;
        }
        //lastPosition = lastPoint();
        size = size();

    }


    public void print() {
        try {
            RandomAccessFile raf = new RandomAccessFile(list, "r");
            String s;
            while ((s = raf.readLine()) != null) {
                System.out.println(s);
            }
            raf.close();
        } catch (IOException io) {

        }

    }

    @Override
    public void add(Object object) {

        String string = object.toString() + "\n";

        byte[] bytes = string.getBytes();
        RandomAccessFile raf = null;
        boolean flagRec = false;
        try {
            try {
                raf = new RandomAccessFile(list, "rw");
                String s;
//                long startPosCurrentLine = 0;
                lastPosition = 0;
                while ((s = raf.readLine()) != null) {
                    if (s.length() == 0) {

                        long carrentLongFile = raf.length();
//                        long startpoint = startPosCurrentLine;
                        long startpoint = lastPosition;
                        raf.seek(raf.getFilePointer());

                        byte[] buffer = new byte[(int) (carrentLongFile - raf.getFilePointer())];
                        raf.readFully(buffer);

                        raf.seek(startpoint);
                        raf.writeBytes(string);

                        flagRec = true;


                        raf.setLength(lastPosition + bytes.length + buffer.length);
//                        raf.setLength(startPosCurrentLine + bytes.length + buffer.length);
                        raf.seek(startpoint + bytes.length);
                        raf.write(buffer);

                    }
                    lastPosition = raf.getFilePointer();
//                    startPosCurrentLine = raf.getFilePointer();
                }
                if (!flagRec) {
                    raf.seek(raf.getFilePointer());
                    raf.writeBytes(string);
                }

            } catch (EOFException eof) {

            }

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException io) {

            }
        }
        size = size();

    }

    @Override
    public boolean contains(Object object) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(new FileInputStream(list));
            br = new BufferedReader(isr);

            for (int i = 0; i < size(); i++) {
                if (br.readLine().equals(object.toString())) {
                    return true;
                }
            }
        } catch (IOException io) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException io) {
            }
        }

        return false;
    }

    public Object getCounter(int counter) {
        RandomAccessFile raf = null;
        Object object = null;
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
            if (object == null) {
                try {
                    throw new Exception("position not found");
                } catch (Exception newExeption) {
                    newExeption.printStackTrace();
                }

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

    @Override
    public void remove(Object object) {
        list_temp = new File("list_temp.txt");
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(list)));
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(list_temp)), true);
            String str;
            while ((str = br.readLine()) != null) {
                if (!str.equals(object.toString())) {
                    pw.println(str);
                } else {
                    pw.println("");
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
        InputStreamReader isr = null;
        BufferedReader br = null;
        int size = 0;
        try {
            isr = new InputStreamReader(new FileInputStream(list));
            br = new BufferedReader(isr);

            while ((br.readLine() != null)) {
                size++;
            }

        } catch (IOException io) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException io) {
            }
        }
        return size;
    }

    @Override
    public Iterator iterator() {

        return new FileIterator();

    }

    public long lastPoint() {
        long l = 0;
        try {
            RandomAccessFile raf = new RandomAccessFile(list, "r");
            l = raf.length();
        } catch (IOException io) {

        }
        return l;
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
