package resources;

import java.io.IOException;
import java.security.Principal;

import org.apache.http.auth.Credentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClassAPI {
	protected static final String BASE_ENDPOINT = "https://api.github.com";
	// protected static final String TOKEN =
	// "c45ce29caf39a6476620c3f5b538f6d4159c657e";
	protected CloseableHttpClient client;// mora da se koristi ova klasa koju implementira HttpClient interface da bi
	// mogli da zatvorimo konekciju
	protected CloseableHttpResponse response;

	@BeforeMethod
	public void setup() {
		client = HttpClientBuilder.create().build();// open client
	}

	@AfterMethod
	public void closeResources() throws IOException {// close client, close response
		client.close();
		response.close();

	}

}
