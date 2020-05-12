package samsara.webapicourse;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpOptions;
import org.testng.annotations.Test;

import resources.BaseClassAPI;
import util.ResponseAPIUtils;

public class Options204 extends BaseClassAPI {

	@Test
	public void optionsReturnsCorrectMethodsList() throws ClientProtocolException, IOException {
//Testiramo da li su nam dozvoljene metode iz expectedReply
		String header = "Access-Control-Allow-Methods";
		String expectedReply = "GET, POST, PATCH, PUT, DELETE";

		HttpOptions request = new HttpOptions(BASE_ENDPOINT);
		response = client.execute(request);

		String actualValue = ResponseAPIUtils.getHeader(response, header);
		assertEquals(actualValue, expectedReply);
	}

}
