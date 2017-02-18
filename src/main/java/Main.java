import static spark.Spark.*;

public class Main{
    public static void main(String[] args) {
	port(8000); 
	System.out.println("Starting server"); 
	get("/hello", (req, res) -> "Hello World");
	    
    }
    
}
