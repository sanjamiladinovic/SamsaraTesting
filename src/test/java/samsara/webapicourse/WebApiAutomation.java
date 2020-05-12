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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import resources.BaseClassAPI;
import resources.BaseClassSamsara;

public class WebApiAutomation extends BaseClassAPI{
	public static Logger log = LogManager.getLogger(WebApiAutomation.class.getName());

	
	



	@Test
	public void getBaseUrlReturns200() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		int actualStatus = response.getStatusLine().getStatusCode();
		assertEquals(actualStatus, 200);
		log.debug("Test 'getBaseUrlReturns200' passes.");

	}

	@Test
	public void rateLimitReturns200() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");
		response = client.execute(get);
		int actualStatus = response.getStatusLine().getStatusCode();
		assertEquals(actualStatus, 200);
		log.info("Test 'rateLimitReturns200' passes.");
	}

	@Test
	public void searchResponseReturns200() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
		response = client.execute(get);
		int actualStatus = response.getStatusLine().getStatusCode();
		assertEquals(actualStatus, 200);
		log.info("Test 'rateLimitReturns200' passes.");
	}

}
