import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileStorage {
	
	static int count = 0;

     public static void main(String[] args) throws Exception {
         String path = "d:" + File.separator + "test.txt";
         File f = new File(path);
         Person p1 = new Person("张三", 23);
         write(f, p1);
         read(f, count);
         Person p2 = new Person("李四", 32);
         write(f, p2); 
         read(f, count);
     }

 
     public static void write(File f, Object p) throws Exception { 
         OutputStream out = new FileOutputStream(f, true);
         MyObjectOutputStream oos = MyObjectOutputStream.newInstance(f, out);
         oos.writeObject(p);
         count++;
         oos.close();
     }

	public static ArrayList<Object> read(File f, int count) throws Exception {
    	 ArrayList<Object> objectList = new ArrayList<Object>();
         FileInputStream in = new FileInputStream(f);
         ObjectInputStream ois = new ObjectInputStream(in);
         for (int i = 0; i < count; i++) {
        	  objectList.add(i, ois.readObject());
              System.out.println((Person) ois.readObject());
         }
         ois.close();
         return objectList;
     }
}