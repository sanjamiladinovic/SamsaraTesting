package samsara.webapicourse;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import com.google.common.net.HttpHeaders;

import resources.BaseClassAPI;

public class DeleteAndPost extends BaseClassAPI {

	@Test(enabled = false)
	public void deleteIsSuccessful() throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete(
				"https://api.github.com/repos/sanjamiladinovic/Getting-Started-with-Web-API-Test-Automation-in-Java");
		request.setHeader(HttpHeaders.AUTHORIZATION, "token " + "c45ce29caf39a6476620c3f5b538f6d4159c657e");// umesto
																											// tokena
																											// trebalo
																											// bi da
																											// stoji
																											// Credentials.TOKEN
																											// i on
																											// negde da
																											// se
																											// definise
		response = client.execute(request);
		int actualStatusCode = response.getStatusLine().getStatusCode();
		assertEquals(actualStatusCode, 204);

	}

	@Test
	public void createRepoReturns201() throws ClientProtocolException, IOException {

		// Create an HttpPost with a valid EndPoint
		HttpPost request = new HttpPost("https://api.github.com/user/repos");

		// Set the Basic Auth Header
		String auth = "sanja.miladinovic@endava.com" + ":" + "87leptiric87";
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);//nesto nece da radi
		request.setHeader(HttpHeaders.AUTHORIZATION, encodedAuth);
		// byte[] encodedAuth=Base64.get(auth.getBytes(Charset.forName("ISO-8859-1")));
		// Define Json to Post and set as Entity
		String json = "{\"name\": \"deleteme\"}";
		request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
		// Send
		response = client.execute(request);
		int actualStatusCode = response.getStatusLine().getStatusCode();
		assertEquals(actualStatusCode, 201);// 201 za create

	}

}
