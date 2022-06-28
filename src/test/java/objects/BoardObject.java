package objects;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseTest;

/**
 * @author lbertoli
 *
 */
public class BoardObject extends BaseTest {
	
	final String endpoint = "/1/boards";
	private Response response;
		
	//REALIZA CRIAÇÃO DO CARD
	public Response createBoard(String name){
		response =				
			given()
				.header("Content-type", "application/json")
                	.when()
                		.post(endpoint + "?name=" + name + "&key=" + CHAVE_API + "&token=" + TOKEN)
                	.then()
                		.log().all()
                		.extract().response();	
		
		System.out.println("Foi realizado uma criação do board: " + response.asString());
		return response;
	}

	//REALIZA REMOÇÃO DO CARD
	public Response removeBoard(String idBoard) {
		response =	
			given()
				.header("Content-type", "application/json")
	                .when()
	                	.delete(endpoint + "/" + idBoard + "?key=" + CHAVE_API + "&token=" + TOKEN)
	                .then()
	                	.log().all()
	                	.extract().response();
		
		System.out.println("O board: " + idBoard + " foi removido com sucesso!");		
		return response;
	}
	
	public String getIdList(String idBoard) {

		Response response =				
			given()
				.contentType(ContentType.JSON)
		        .when()
	             	        .get(endpoint + "/" + idBoard + "/lists?key=" + CHAVE_API + "&token=" + TOKEN)
			.then()
	              		.log().all()
	              		.extract().response();
		return response.jsonPath().getString("[0].id");

	}
		
}
