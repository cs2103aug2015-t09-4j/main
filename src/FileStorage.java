import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FileStorage {
	/*
	 * refer to Person class and this is an example
    public static void main(String[] args) throws Exception {
        String path = "d:" + File.separator + "test.txt";
        File f = new File(path);
        Person p1 = new Person("a", 23);
        write(f, p1);
        read(f);
        Person p2 = new Person("c", 32);
        write(f, p2); 
        read(f);
    }
    */

 
    public static void write(File f, Object p) throws Exception {
    	 
        OutputStream out = new FileOutputStream(f, true);
        MyObjectOutputStream oos = MyObjectOutputStream.newInstance(f, out);
        oos.writeObject(p);
        oos.close();
        System.out.println("-----Oject write successfully....");
    }

     public static ArrayList<Task> read(File f) throws Exception {
         FileInputStream in = new FileInputStream(f);
         ObjectInputStream ois = new ObjectInputStream(in);
         int i = 0;
         ArrayList<Task> objectList = new ArrayList<Task>();
         while(true) {   
        	 try{
        	  Task temp = new Task();
        	  temp = (Task) ois.readObject();
        	  objectList.add(i, temp);
        	  i++;
              System.out.println((Task)temp);
        	 } catch (EOFException e){
        		 break;
        	 }
        
         } 

        ois.close();
		return objectList;
     }
}