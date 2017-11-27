import java.io.IOException;
import java.net.URISyntaxException;

public class Test {
    GitClient gitClient = new GitClient();

    public Test() throws URISyntaxException {
    }

    @org.testng.annotations.Test
    public void returnUserId() throws IOException {
        gitClient.getUserInfo("mojombo");
        gitClient.getUserInfo("mojodna");
    }
}
