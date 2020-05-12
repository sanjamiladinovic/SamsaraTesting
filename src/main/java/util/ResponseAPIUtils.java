package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.User;

public class ResponseAPIUtils {
	public static String getHeader(CloseableHttpResponse response, String headerName) {

		// Get All headers
		Header[] headers = response.getAllHeaders();
		List<Header> httpHeaders = Arrays.asList(headers);
		String returnHeader = "";

		// Loop over the headers list
		for (Header header : httpHeaders) {
			if (headerName.equalsIgnoreCase(header.getName())) {
				returnHeader = header.getValue();
			}
		}
		// If no header found - throw an exception
		if (returnHeader.isEmpty()) {
			throw new RuntimeException("Didn't find the header: " + headerName);
		}
		// Return the header
		return returnHeader;
	}

	public static String getHeaderJava8Way(CloseableHttpResponse response, String headerName) {

		// Naci trazeni header kao u prethodnoj metodi samo uz pomoc lambdi i strimova
		List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

		// flow in the stream one by one, after flow we filter for a header we care
		// about, than find and return first header that matches the filtering criteria
		// and if we don't find it we throw an exception, at the end we returning the
		// String value of the header
		Header matchHeader = httpHeaders.stream().filter(header -> headerName.equalsIgnoreCase(header.getName()))
				.findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header"));

		return matchHeader.getValue();
	}

	public static boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
		List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
		// trazimo bilo koje podudaranje headera iz liste i parametra
		return httpHeaders.stream().anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
	}

	public static User unmarshall(CloseableHttpResponse response, Class<User> clazz)
			throws ParseException, IOException {
		// TODO Auto-generated method stub
		String jsonBody = EntityUtils.toString(response.getEntity());
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, clazz);
	}

	public static <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> clazz)
			throws ParseException, IOException {
		// TODO Auto-generated method stub
		String jsonBody = EntityUtils.toString(response.getEntity());
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, clazz);
	}
}
