import static spark.Spark.*;

import java.util.Hashtable;

import org.json.simple.JSONObject;

public class Main {

	public static void main(String[] args) {
		FormManager ebc = new FormManager();
		
		ebc.add("Everest Oreizy", "Kohala Zipline", true);
		
		//Run the project, then go to localhost:80/hello in your browser.
		
		staticFiles.location("/");
        port(80);

        // get a silly route up for testing
        get("/", (req, res) -> {
            return "Hello world from code";
        });
        
        get("/getFormStatus/:name/:form", (req, res) -> {
        	String name = req.params(":name");
        	String form = req.params(":form");
        	
        	return ebc.get(name, form);
        });
        
        get("/addFormStatus/:name/:form", (req, res) -> {
        	String name = req.params(":name");
        	String form = req.params(":form");
        	
        	ebc.add(name, form, true);
        	
        	System.out.print("set ");
        	System.out.print(name);
        	System.out.print(form);
        	System.out.println(" as true");
        	
        	return "Done.";
        });
        
        get("/getParticipantSummary/:name", (req, res) -> {
        	Hashtable<String, Boolean> hash1 = ebc.getParticipantForms(req.params(":name"));
        	JSONObject summary = new JSONObject(hash1);
        	return summary;
        });
	}

}
