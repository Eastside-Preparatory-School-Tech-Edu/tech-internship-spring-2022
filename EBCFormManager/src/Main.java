import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.json.simple.JSONObject;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

	public static void main(String[] args) {
		FormManager ebc = new FormManager();
		
		ebc.add("Everest Oreizy", "Kohala Zipline", true);
		ebc.add("Everest Oreizy", "Medication Waiver", true);
		ebc.add("Jonathan Nister", "Medication Waiver", false);
		
		//Run the project, then go to localhost:80/hello in your browser.
		
		staticFiles.location("/");
        port(80);

        get("/", (req, res) -> {
        	return "Hey there...";
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
        
        //returns a list of all the forms for a person, and whether they're completed.
        get("/getParticipantSummary/:name", (req, res) -> {
        	Hashtable<String, Boolean> hash1 = ebc.getParticipantForms(req.params(":name"));
        	JSONObject summary = new JSONObject(hash1);
        	return summary;
        });
        
        //basically return everything as a json object
        get("/getCompleteSummary", (req, res) -> {
        	JSONObject summary = new JSONObject(ebc);
        	return summary;
        });
        
        get("/view/:name", (req, res) -> {
        	Map<String,Object> attr = new HashMap<>();
        	
        	String name = req.params(":name");
        	Hashtable forms = ebc.getParticipantForms(name);
        	attr.put("name", name);
        	attr.put("forms", forms);
        	
        	return new ModelAndView(attr, "viewer.html");
        }, new VelocityTemplateEngine());
        
        
	}

}
