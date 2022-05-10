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
		FormManager makerspace = new FormManager();
		makerspace.formNames.addAll( Arrays.asList(
				"Laser Cutter",
				"3D Printer",
				"Power Tools"
		));
		
		makerspace.participants.addAll( Arrays.asList(
				"Cadence Ching",
				"Jonathan Nister",
				"Zarak Tareen",
				"Tyler Zhang",
				"Everest Oreizy",
				"Benjamin Brundage"
				));
		
		//should do this in a better way if this was bigger scale.
		Collections.sort(makerspace.participants, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(makerspace.formNames, String.CASE_INSENSITIVE_ORDER);		
		
		//Run the project, then go to localhost:80/hello in your browser.
		
		staticFiles.location("/");
        port(80);
        
        get("/", (req, res) -> {
        	Map<String,Object> attr = new HashMap<>();
        	
        	attr.put("data", makerspace);
        	
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
        	
        	
        	attr.put("uniquekeys", makerspace.formNames);
        	attr.put("people", makerspace.participants);
        	
        	return new ModelAndView(attr, "manager.html");
        }, new VelocityTemplateEngine());
        
        get("/getFormStatus/:name/:form", (req, res) -> {
        	String name = req.params(":name");
        	String form = req.params(":form");
        	return makerspace.get(name, form);
        });
        
        get("/addFormStatus/:name/:form/:lvl", (req, res) -> {
        	String name = req.params(":name");
        	String form = req.params(":form");
        	String lvl = req.params(":lvl");
        	
        	makerspace.add(name, form, Integer.valueOf(lvl));
        	
        	System.out.print("set " + name + " - " + form + " as true");
        	
        	return "Done.";
        });

        get("/addParticipant/:name", (req, res) -> {
        	makerspace.participants.add(req.params(":name"));
        	Collections.sort(makerspace.participants, String.CASE_INSENSITIVE_ORDER);
        	
        	return "Done.";
        });
        
        get("/addRequiredForm/:title", (req, res) -> {
        	makerspace.formNames.add(req.params(":title"));
        	Collections.sort(makerspace.formNames, String.CASE_INSENSITIVE_ORDER);
        	
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
