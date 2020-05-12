package samsara.webapicourse;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import resources.BaseClassAPI;
import resources.DataProviders;
import util.ResponseAPIUtils;

public class ResponseHeaders extends BaseClassAPI {
	public static Logger log = LogManager.getLogger(ResponseHeaders.class.getName());

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

	@Test
	public void contentTypeJson() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		Header contentType = response.getEntity().getContentType();
		assertEquals(contentType.getValue(), "application/json; charset=utf-8");// ovo je ako hocemo da testiramo i
																				// media(MIME) type i charset
		ContentType ct = ContentType.getOrDefault(response.getEntity());// ovo je ako hocemo da testiramo samo
																		// media(MIME) type
		assertEquals(ct.getMimeType(), "application/json");

	}

	@Test
	public void serverIsGitHub() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		String headerValue = ResponseAPIUtils.getHeader(response, "Server");
		assertEquals(headerValue, "GitHub.com");

	}

	@Test
	public void xRateLimitIsSixty() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		String limitVal = ResponseAPIUtils.getHeaderJava8Way(response, "X-RateLimit-Limit");
		assertEquals(limitVal, "60");
	}

	@Test
	public void eTagIsPresent() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		boolean tagIsPresent = ResponseAPIUtils.headerIsPresent(response, "ETag");
		assertTrue(tagIsPresent);
	}
}
