package website;

/*
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;
*/

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuayClub {

        private DataDriven data;
        //private UserAgent userAgent;

        String url = "";
        String username = "";
        String password = "";
        String token = "";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36";

        public void initialize()  throws IOException {
            data = new DataDriven();
            url = data.getGlobalData("Huayclub_url");
            username = data.getGlobalData("Huayclub_username");
            password = data.getGlobalData("Huayclub_password");
            token = data.getGlobalData("Huayclub_token ");
        }

        public void getData_HuayClub() throws IOException {
            initialize();
            System.out.println("HuayClub");
            System.out.println("url : " + url);
            System.out.println("user : " + username);
            System.out.println("pass : " + password);
            try {
                HashMap<String, String> cookies = new HashMap<>();
                HashMap<String, String> formData = new HashMap<>();

                Connection.Response loginForm = Jsoup.connect(url).userAgent(userAgent).method(Connection.Method.GET).execute();
                Document loginDoc = loginForm.parse(); // this is the document that contains response html
                cookies.putAll(loginForm.cookies()); // save the cookies, this will be passed on to next request
                String authToken = loginDoc.select("#login-box > div > div > form > input[type=hidden]")
                        .first()
                        .attr("value");

                formData.put("_token", authToken);
                formData.put("username", username);
                formData.put("password", password);
                Connection.Response response = Jsoup.connect(url)
                        .userAgent(userAgent)
                        .timeout(12000)
                        .followRedirects(true)
                        .cookies(cookies)
                        .data(formData)
                        .method(Connection.Method.POST)
                        .execute();
                //System.out.println(response.parse().html());
               Map<String, String> loginCookies = response.cookies();
              //  String sessionId = response.cookie("laravel_session");
               // System.out.println(sessionId);
               // Document doc2 = Jsoup.connect("https://agent.superlot999.com/welcome?_=1582404119820&&game_id=53878") .cookies(loginCookies).get();
                Document doc2 = Jsoup.connect("https://agent.superlot999.com/reports/by-date/17115/week?list=1%2C39%2C110%2C5%2C138%2C139%2C140%2C25%2C22%2C24%2C6%2C11%2C7%2C12%2C13%2C8%2C14%2C9%2C15%2C16%2C17%2C18%2C19%2C20%2C10%2C73%2C144%2C74%2C75%2C76%2C77%2C78%2C79%2C80%2C82%2C83%2C84%2C85%2C86%2C87%2C88%2C89%2C91%2C92%2C93%2C94%2C95%2C96%2C97%2C98%2C99%2C100%2C101%2C102%2C103%2C104%2C105%2C106%2C107%2C108%2C109")
                        .userAgent(userAgent)
                        .cookies(loginCookies)
                        .get();
                System.out.println(doc2.location());
                System.out.println(doc2.html());

            } catch (Error  e) {
                System.err.println(e);
            }
        }

}
