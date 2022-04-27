import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Participant {
	String name;
	String email;
	String birthdate;
	
	public Participant(String name, String email, String birthdate) {
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
	}
	
	@SuppressWarnings("unchecked")
	public static Participant[] importJSON(String filepath) {
		
		JSONParser parser = new JSONParser();
		
		try (FileReader reader = new FileReader(filepath)){
			//read json file
			Object obj = parser.parse(reader);
			JSONArray participants2 = (JSONArray) obj;
			
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		return new Participant[] {};
		
	} 
}