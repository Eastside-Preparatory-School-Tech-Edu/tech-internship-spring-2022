import static spark.Spark.*;

public class Main {

	public static void main(String[] args) {
		FormManager ebc = new FormManager();
		
		staticFiles.location("/");
        port(80);

        // get a silly route up for testing
        get("/hello", (req, res) -> {
            System.out.println("Hey we were invoked:");
            return "Hello world from code";
        });
	}

}
