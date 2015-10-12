import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileStorage{
//	private static final long serialVersionUID = -769626947865283;
	static ArrayList<Task> objectList = new ArrayList<Task>();
	
	public static void clear(File f) throws Exception {
		OutputStream out = new FileOutputStream(f, false);
		MyObjectOutputStream oos = MyObjectOutputStream.newInstance(f, out);
		oos.close();
		//out.flush();
	}
 
    public static void write(File f, Object p) throws IOException {
    	 
        OutputStream out = new FileOutputStream(f, true);
        MyObjectOutputStream oos = MyObjectOutputStream.newInstance(f, out);
        oos.writeObject(p);
        oos.close();
        System.out.println("-----Oject write successfully....");
    }

     public static ArrayList<Task> read(File f) throws IOException, ClassNotFoundException {
    	 objectList = new ArrayList<Task>();
         FileInputStream in = new FileInputStream(f);
         ObjectInputStream ois = new ObjectInputStream(in);
         int i = 0;

         while(true) {   
        	 try{
        	  Task temp = new Task();
        	  temp = (Task) ois.readObject();
        	  objectList.add(i, temp);
        	  i++;
              //System.out.println((Task)temp);
        	 } catch (EOFException e){
        		 break;
        	 }
        
         } 

        ois.close();
		return objectList;
     }
}