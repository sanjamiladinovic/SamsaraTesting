package samsara.webapicourse;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import resources.BaseClassAPI;
import resources.BaseClassSamsara;
import resources.DataProviders;

public class Get401 extends BaseClassAPI {
	public static Logger log = LogManager.getLogger(Get401.class.getName());

	CloseableHttpClient client;// mora da se koristi ova klasa koju implementira HttpClient interface da bi
								// mogli da zatvorimo konekciju
	CloseableHttpResponse response;

	@BeforeMethod
	public void setup() {
		client = HttpClientBuilder.create().build();// open client
	}

	@AfterMethod
	public void closeResources() throws IOException {// close client, close response
		client.close();
		response.close();

	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "endpointsAPI")
	public void userReturns401(String endPoint) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + endPoint);
		response = client.execute(get);
		int actualStatus = response.getStatusLine().getStatusCode();
		assertEquals(actualStatus, 401);
		log.debug("Test 'userReturns401' passes.");

	}

}
