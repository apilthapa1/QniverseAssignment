package api.endpoints;

public class Routes {
	
	public static String userId = null;
	public static String getUserId() {
		return userId;
	}
	public static void setUserId(String userId) {
		Routes.userId = userId;
	}
	
	public static String base_url = "https://dummyapi.io/data/v1";
	public static String getAll_url = base_url + "/user?limit=10";
	public static String getById_url = base_url + "/user/";
}
