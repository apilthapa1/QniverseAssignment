package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import io.restassured.response.Response;

public class UserTests {
	
	Response response = null;
	UserEndPoints userEndPoints;
	@Test(priority=1)
	public void testGetAllUser() {
		userEndPoints = new UserEndPoints();
		response = userEndPoints.GetAllUsers();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(userEndPoints.verifyJsonKeys(response), "One of the key is not populated");
	}
	
	@Test(priority=2)
	public void verifySpecificUser() {
		Assert.assertTrue(userEndPoints.verifyDesiredUser(), "Desired user value not match");
	}
}
