package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import api.utilities.Utilities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	private Properties prop;
	JSONArray jsonArray = null;
	
	public Response GetAllUsers() {

		Response response = given().header("app-id", "64c0f6579643bdc0b430e2fa").contentType(ContentType.JSON)
				.accept(ContentType.JSON).when().get(Routes.getAll_url);
		return response;
	}

	public Response GetById(String id) {
		Response response = given().header("app-id", "64c0f6579643bdc0b430e2fa").contentType(ContentType.JSON).accept(ContentType.JSON).when()
				.get(Routes.getById_url + id);

		return response;
	}

	public boolean verifyJsonKeys(Response response) {
		JSONObject jsonObject = new JSONObject(response.body().asString());
		jsonArray = new JSONArray(jsonObject.getJSONArray("data"));
		JSONObject eachData = jsonArray.getJSONObject(0);
		Map<String, String> map = getHashMapValue(eachData);
		List<String> givenKeys = getGivenKeys();
		for (String string : givenKeys) {
			if(!map.containsKey(string)) {
				return false;
			}
		}
		return true;
	}

	private List<String> getGivenKeys() {
		List<String> list = new ArrayList<String>();
		prop = Utilities.loadPropertiesFile();
		
		addValuesToList(list, "key1");
		addValuesToList(list, "key2");
		addValuesToList(list, "key3");
		addValuesToList(list, "key4");
		addValuesToList(list, "key5");
		addValuesToList(list, "key6");
		return list;
	}
	
	private void addValuesToList(List<String> list, String key) {
		list.add(prop.getProperty(key));
	}

	private Map<String, String> getHashMapValue(JSONObject jObject) {
		HashMap<String, String> map = new HashMap<String, String>();
		Iterator<String> keys = jObject.keys();
		while(keys.hasNext()) {
			String key = (String)keys.next();
            String value = jObject.get(key).toString(); 
            map.put(key, value);
		}
		return map;
	}
	
	private Map<String, String> getDesiredUser() {
		String firstName = prop.getProperty("name");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject eachData = jsonArray.getJSONObject(i);
			Map<String, String> map = getHashMapValue(eachData);
			if(map.containsValue(firstName)) {
				return map;
			}
		}
		return null;
	}
	
	public boolean verifyDesiredUser() {
		Map<String, String> mapUser = getDesiredUser();
		String desiredId = mapUser.get("id");
		Routes.setUserId(desiredId);
		
		Response response = GetById(desiredId);
		JSONObject jsonObject = new JSONObject(response.body().asString());
		Map<String, String> responseUser = getHashMapValue(jsonObject);
		for (String key : mapUser.keySet()) {
			if(!mapUser.get(key).equals(responseUser.get(key))) {
				return false;
			}
		}
		return true;
	}
}
