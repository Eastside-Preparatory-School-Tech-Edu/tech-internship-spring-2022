import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {
	
	public static void main(String[] args) {
		FormManager ebc = new FormManager();
		ebc.formNames.addAll( Arrays.asList(
				"Kohala Zipline",
				"Medicine Information",
				"Liability Waiver",
				"Allergy Notice",
				"Identification"
		));
		
		ebc.participants.addAll( Arrays.asList(
				"Zach Hanken",
				"Ethan Audia",
				"Joseph Knight",
				"Feliks Kisielius",
				"Rami Rifaat",
				"Sanjana Satagopan",
				"Everest Oreizy",
				"Egan Tardif",
				"Evelyn Drake",
				"Roya Mansour",
				"Ana Hera",
				"Leah Guse",
				"Sara Dunnigan",
				"Ben Spickett",
				"Michael Zyskowski",
				"Emma Grua",
				"Jack Denney",
				"Myan Ngo",
				"Rahul Bothra",
				"Daniel Kirov-Tomilov",
				"Michael Sanders"
				));
		
		//should do this in a better way if this was bigger scale.
		Collections.sort(ebc.participants, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(ebc.formNames, String.CASE_INSENSITIVE_ORDER);		
		
		//Run the project, then go to localhost:80/hello in your browser.
		
		staticFiles.location("/");
        port(80);
        
        get("/", (req, res) -> {
        	Map<String,Object> attr = new HashMap<>();
        	
        	attr.put("data", ebc);
        	
//        	Set<String> uniqueKeys = new HashSet<String>();
//        	
//        	Set<?> participants = ebc.keySet();
//        	
//        	for ( Object p : participants) {
//        		uniqueKeys.addAll(ebc.getParticipantForms(p.toString()).keySet());
//        	}
//        	
//        	System.out.println("Participants: " + participants.toString());
//        	System.out.println("Unique keys: " + uniqueKeys.toString());
        	
        	
        	attr.put("uniquekeys", ebc.formNames);
        	attr.put("people", ebc.participants);
        	
        	return new ModelAndView(attr, "manager.html");
        }, new VelocityTemplateEngine());
        
        get("/getFormStatus/:name/:form", (req, res) -> {
        	String name = req.params(":name");
        	String form = req.params(":form");
        	return ebc.get(name, form);
        });
        
        get("/addFormStatus/:name/:form", (req, res) -> {
        	String name = req.params(":name");
        	String form = req.params(":form");
        	
        	ebc.add(name, form, true);
        	
        	System.out.print("set " + name + " - " + form + " as true");
        	
        	return "Done.";
        });

        get("/addParticipant/:name", (req, res) -> {
        	ebc.participants.add(req.params(":name"));
        	Collections.sort(ebc.participants, String.CASE_INSENSITIVE_ORDER);
        	
        	return "Done.";
        });
        
        get("/addRequiredForm/:title", (req, res) -> {
        	ebc.formNames.add(req.params(":title"));
        	Collections.sort(ebc.formNames, String.CASE_INSENSITIVE_ORDER);
        	
        	return "Done.";
        });
        
//        //returns a list of all the forms for a person, and whether they're completed.
//        get("/getParticipantSummary/:name", (req, res) -> {
//        	Hashtable<String, Boolean> hash1 = ebc.getParticipantForms(req.params(":name"));
//        	JSONObject summary = new JSONObject(hash1);
//        	return summary;
//        });
//        
//        //basically return everything as a json object
//        get("/getCompleteSummary", (req, res) -> {
//        	JSONObject summary = new JSONObject(ebc);
//        	return summary;
//        });
        
//        get("/view/:name", (req, res) -> {
//        	Map<String,Object> attr = new HashMap<>();
//        	
//        	String name = req.params(":name");
//        	Hashtable forms = ebc.getParticipantForms(name);
//        	attr.put("name", name);
//        	attr.put("forms", forms);
//        	
//        	return new ModelAndView(attr, "viewer.html");
//        }, new VelocityTemplateEngine());
        
               
	}

}
