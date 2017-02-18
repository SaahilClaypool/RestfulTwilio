import static spark.Spark.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.HashMap;

public class Main{
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACc66abeeb6305138885e7991748beed5b";
    public static final String AUTH_TOKEN = "39dcf1af88bab3d3dbc54a7188d6a3f0";

    public static void main(String[] args) {
	port(8000); 
	System.out.println("Starting server"); 
	get("/", (req, res) -> "Hello World");

	post("/", (req, res) -> {
		return "posting"; 
	    }); 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber(" +19787601330"), new PhoneNumber("+19788505030"),
                        "This is from TWILIO! what up isaac")
                .create();

        System.out.println(message.getSid());

	System.out.println("end of main"); 
    }
    


}
