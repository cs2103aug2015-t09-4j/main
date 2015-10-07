import java.io.*;
import java.util.Stack;

public class FileStorage {
	
	private static void saveObject(String path, Object obj) {   
        FileOutputStream fos = null;  
        ObjectOutputStream oos = null;  
        File f = new File(path);  
        try {  
            fos = new FileOutputStream(f);  
            oos = new ObjectOutputStream(fos);  
            oos.writeObject(obj); 
        	oos.flush();
            oos.close();
            fos.flush();
            fos.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    private static Object readObject(String path){  
    	Object temp = null;
        FileInputStream fis = null;  
        ObjectInputStream ois = null;     
        File f = new File(path);  
        try {  
            fis = new FileInputStream(f);  
            ois = new ObjectInputStream(fis);  
            temp = ois.readObject();
            ois.close();  
            fis.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } finally{  
            return temp;
        }  
    }  
    
    /* use for testing
    public static void main(String[] args) {
    	Task test = new Task();
    	test.setRecurEndDate(34);
    	saveObject("C:\\Users\\lemonaoko\\Desktop\\aaa.txt", test); 
    	Object obj;
    	obj = readObject("C:\\Users\\lemonaoko\\Desktop\\aaa.txt");
    	System.out.println(obj);
    	
    }
    */
}
