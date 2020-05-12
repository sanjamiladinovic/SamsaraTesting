package samsara.webapicourse;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import entities.NotFound;
import entities.User;
import resources.BaseClassAPI;
import util.ResponseAPIUtils;

public class BodyTestWithJackson extends BaseClassAPI {

	@Test
	public void returnsCorrectLogin() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);
		User user = ResponseAPIUtils.unmarshallGeneric(response, User.class);// moralo bi za svako polje u JSON fileu da
																				// se
		// definise polje u
		// javi, umesto toga mu se u umarshall metodi kaze da ako ne
		// prepozna nesto nastavi dalje
		assertEquals(user.getLogin(), "andrejss88");
	}

	@Test
	public void returnsCorrectId() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);
		User user = ResponseAPIUtils.unmarshallGeneric(response, User.class);// moralo bi za svako polje u JSON fileu da
																				// se
		// definise polje u
		// javi, umesto toga mu se u umarshall metodi kaze da ako ne
		// prepozna nesto nastavi dalje
		assertEquals(user.getId(), 11834443);
	}

	@Test
	public void notFoundMessageIsCorrect() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");
		response = client.execute(get);
		NotFound notFoundMessage = ResponseAPIUtils.unmarshallGeneric(response, NotFound.class);// moralo bi za svako
																								// polje u JSON fileu da
																								// se
		// definise polje u
		// javi, umesto toga mu se u umarshall metodi kaze da ako ne
		// prepozna nesto nastavi dalje
		assertEquals(notFoundMessage.getMessage(), "Not Found");
	}

	@Test
	public void correctRateLimitsAreSet() throws ClientProtocolException, IOException {
		// problem with nested fields
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
		response = client.execute(get);
		// past above response in jacson method i try to unmarshall Jacson to RateLimit
		// Class and save that in variable
		RateLimit rateLimits = ResponseAPIUtils.unmarshallGeneric(response, RateLimit.class);
		assertEquals(rateLimits.getCoreLimit(), 60);
		assertEquals(rateLimits.getSearchLimit(), "10");
	}
}
