import java.util.ArrayList;
import java.util.Hashtable;

public class FormManager extends HashTable2D<Object, Object> {

	public ArrayList<String> formNames = new ArrayList<String>();
	
	public ArrayList<String> participants = new ArrayList<String>();
	
	// default constructor
	public FormManager() {
		super();
	}
	
	// add applicant
	public void addApplicant(String name) {
        if(!this.containsKey(name)) {
            Hashtable<String, Boolean> h = new Hashtable<String, Boolean>();
            this.put(name, h);
        }
	}
	
	// add full data
	public void add(String name, String formName, int status) {
		super.put(name, formName, status);
	}
	
	
}
