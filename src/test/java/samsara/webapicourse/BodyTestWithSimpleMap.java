package samsara.webapicourse;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import resources.BaseClassAPI;
import static entities.User.LOGIN;
import static entities.User.ID;

public class BodyTestWithSimpleMap extends BaseClassAPI {

	@Test
	public void returnsCorrectLogin() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);
		String jsonBody = EntityUtils.toString(response.getEntity());// da preuzmemo json body
		// System.out.println(jsonBody);
		JSONObject jsonObject = new JSONObject(jsonBody);
		String loginValue = (String) getValueFor(jsonObject, LOGIN);// hashMap
		assertEquals(loginValue, "andrejss88");
	}

	@Test
	public void returnsCorrectId() throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);
		String jsonBody = EntityUtils.toString(response.getEntity());// da preuzmemo json body
		// System.out.println(jsonBody);
		JSONObject jsonObject = new JSONObject(jsonBody);
		Integer idValue = (Integer) getValueFor(jsonObject, ID);// hashMap
		assertEquals(idValue, Integer.valueOf(11834443));
	}

	private Object getValueFor(JSONObject jsonObject, String key) {
		// TODO Auto-generated method stub
		return jsonObject.get(key);
	}

}
