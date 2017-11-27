import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;

public class GitClient {

    public GitClient() throws URISyntaxException {
    }

    public void getUserInfo(String login) throws IOException {
        //String userName = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("https://api.github.com/users");
        System.out.println(httpget);
        CloseableHttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                User[] users = mapper.readValue(entityString, User[].class);
                //User[] users = mapper.reader(User.class).readValues(entityString);
                for (int i = 0; i < users.length; i++) {
                    int userId;
                    String userName = users[i].getLogin();
                    System.out.println(userName);
                    System.out.println(login + " login");
                    if (userName == login) {
                        System.out.println("userName == login");
                        userId = users[i].getId();
                        System.out.println("userId " + userId);
                    }else {
                        System.out.println("userName != login");
                    }
                }
            } catch (Exception exception) {
                System.out.println("This is an exception");
            }

        }


    }

}
