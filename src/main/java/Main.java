import static spark.Spark.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import spark.QueryParamsMap;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

public class Main{
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACc66abeeb6305138885e7991748beed5b";
    public static final String AUTH_TOKEN = "39dcf1af88bab3d3dbc54a7188d6a3f0";

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        port(Integer.parseInt(env.get("PORT")));
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("Starting server");
        // get("/", (req, res) -> "Hello World");
        get("/",  (req, res) -> {
            System.out.println("Got Message");
            Message message = Message
                    // to -> from
                    .creator(new PhoneNumber(getFrom(req)), new PhoneNumber("+19788505030"),
                           getBody(req))
                    .create();


            System.out.println("Message from : " + getFrom(req));
            return "sent message";
        });



        System.out.println("end of main");
    }


    public static String getBody(Request request) {
        Map<String, String[]> reqMap = request.queryMap().toMap();
        String body = "";
        if (reqMap.containsKey("Body")) {
            String[] bodyAr = reqMap.get("Body");
            for (int i = 0 ; i < bodyAr.length; i++) {
                String cur = bodyAr[i];
                body += cur;
            }
        }

        return body;
    }

    public static String getFrom (Request request) {
        System.out.println("getting number");
        Map<String, String[]> reqMap = request.queryMap().toMap();
        String from = "";
        if (reqMap.containsKey("From")) {
            String[] bodyAr = reqMap.get("From");
            for (int i = 0 ; i < bodyAr.length; i++) {
                String cur = bodyAr[i];
                from += cur;
            }
        }

        System.out.println("from: " + from);
        return from ;

    }



}
