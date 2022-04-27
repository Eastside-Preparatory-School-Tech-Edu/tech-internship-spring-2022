import java.util.Hashtable;

public class FormManager extends HashTable2D<Object, Object> {

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
	public void add(String name, String formName, boolean status) {
		super.put(name, formName, status);
	}
	
}
