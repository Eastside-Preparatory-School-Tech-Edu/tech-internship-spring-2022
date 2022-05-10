import java.util.Hashtable;

public class HashTable2D<key1, innerHashtable> extends Hashtable<Object, Object> {
    
	private static final long serialVersionUID = 75243965052722407L;

	// default constructor, calling super    
    public HashTable2D() {
        super();
    }
    
    // overriding methods to add functionality for 2D hash table
    
    // custom put method to have two keys for 2D hashtable
    // outer hash table is for the person's name, inner hash table is between 
    // string for makerspace tool and integer for proficiency level 1-5
    public void put(String key1, String key2, Integer proficiency) {
        
        // if key1 doesnt exist, make it and put a hashtable as the value
        if(!this.containsKey(key1)) {
            Hashtable<String, Integer> h = new Hashtable<String, Integer>();
            this.put(key1, h);
        }
       
        // put value in the correct place
        ((Hashtable)this.get(key1)).put(key2, proficiency);
        
        // probably not needed, commented out for now
//        // switch keys around to make it directional from both sides
//        if(!this.containsKey(key2)) {
//            Hashtable<String, Boolean> h = new Hashtable<String, Boolean>();
//            this.put(key2, h);
//        }
//        ((Hashtable)this.get(key2)).put(key1, status);
        
    }
    
    // custom get method for 2D hashtable
    // use this to get proficiency level for person key1 and tool key2
    // returns -1 if person does ont exist in system
    public int get(String key1, String key2) {
        if(this.containsKey(key1)) {
            if(((Hashtable)this.get(key1)).containsKey(key2)) {
                return (int)((Hashtable)this.get(key1)).get(key2);
            }
        }
        return 0; 
        
    }
    
    
    public Hashtable<String,Integer> getPersonProficiencies(String key1name) {
    	if(this.containsKey(key1name)) {
    		return (Hashtable)this.get(key1name);
    	}
    	
    	else return new Hashtable<String,Integer>();
    }
   
    // get method for 2D hashtable for a certain trip
    // takes the trip name and the list of participants and returns a string
    // containing which participants have not completed the necessary forms
    
    // not needed for the makerspace tracker
    /*
    public String getFormCompletion(String tripName, Participant[] pList){
    	String toReturn = "";
    	for(int i = 0; i < pList.length; i++)
    	{
    		String currentParticipantName = pList[i].name; 
    		if(((Hashtable)this.get(tripName)).containsKey(currentParticipantName)) {
    			if((boolean)((Hashtable)this.get(tripName)).get(currentParticipantName) == false)
    			{
    				//if person has not completed form, add to string
    				toReturn = toReturn + ", " + currentParticipantName; 
    			}
    		}
    	}
    	return toReturn; 
    }
    */
}
