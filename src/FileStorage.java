import java.io.*;

public class FileStorage {
	
	private void saveObject(String path, Object obj) {   
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
      
    private Object readObject(String path){  
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
}
