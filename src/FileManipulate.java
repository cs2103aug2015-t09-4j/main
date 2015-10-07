import java.io.Serializable;
import java.util.Iterator;
import java.util.Stack;

public class FileManipulate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Stack<Object> file_list;
	static Stack<Object> remove_file;
	
	private static void addObject(Object obj) {
		file_list.push(obj);
	}
	
	private static void removeObject() {
		Object obj = null;
		obj = file_list.pop();
		remove_file.push(obj);
	}
	
	private static void undoOperation() {
		Object obj = null;
		obj = remove_file.pop();
		file_list.push(obj);
	}	
	
	/*
    //Overriding toString to be able to print out the object in a readable way
    //when it is later read from the file.
    public String toString() {
    	Iterator<Object> iter = file_list.iterator();    	
        StringBuffer buffer = new StringBuffer();        
        while(iter.hasNext()) {
        	buffer.append(iter.next());
        	buffer.append("\n");
        }        
        return buffer.toString();
    }
    */
}
