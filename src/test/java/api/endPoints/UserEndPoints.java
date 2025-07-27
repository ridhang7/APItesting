package api.endPoints;

import static io.restassured.RestAssured.given;

import api.payloads.User;
import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.*;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(User payload) {
		
		Response res = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON).body(payload).post(Routes.post_url);
		return res;
	}

	public static Response readUser(String username) {
		Response res = given().pathParam("username", username)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON).get(Routes.get_url);
		return res;
	}

	public static Response updateUser(String username, User payload) {
		Response res = given().pathParam("username", username)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON).body(payload).patch(Routes.update_url);
		return res;
	}
	
	public static Response deleteUser(String username) {
		Response res = given().pathParam("username", username)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON).delete(Routes.delete_url);
		return res;
	}

}
