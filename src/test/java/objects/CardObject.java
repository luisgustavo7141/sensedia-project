package objects;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import utils.BaseTest;

/**
 * @author lbertoli
 *
 */
public class CardObject extends BaseTest {
	
	final String endpoint = "/1/cards";

	private String idCard;
	private String jsonResult;
	private Response response;
	
	public String getIdcard() {
		return idCard;
	}

	public void setIdcard(String idCard) {
		this.idCard = idCard;
	}
	
	//REALIZA CRIAÇÃO DO CARD
	public Response createCard(String name, String desc, String idList){
		
		HashMap<String,String> requestBody = new HashMap<>();
		requestBody.put("name", name);
		requestBody.put("desc", desc);
		requestBody.put("idList", idList);

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonResult = mapper.writerWithDefaultPrettyPrinter()
			  .writeValueAsString(requestBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		response =				
			given()
				.header("Content-type", "application/json")
				.and()
				.body(jsonResult)
                	.when()
                		.post(endpoint + "?key=" + CHAVE_API + "&token=" + TOKEN)
                	.then()
                		.log().all()
                		.extract().response();	
		
			System.out.println("Foi realizado uma criação do card: " + response.asString());
			setIdcard(response.jsonPath().getString("id"));
			return response;
	}
	
	//REALIZA ALTERAÇÃO DO CARD
	public Response editCard(String name, String desc, String idList) {
		
		HashMap<String,String> requestBody = new HashMap<>();
		requestBody.put("name", name);
		requestBody.put("desc", desc);
		requestBody.put("idList", idList);

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonResult = mapper.writerWithDefaultPrettyPrinter()
			  .writeValueAsString(requestBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
			
		response =	
			given()
				.header("Content-type", "application/json")
				.and()
				.body(jsonResult)
	                .when()
	                	.put(endpoint + "/" + this.getIdcard() + "?key=" + CHAVE_API + "&token=" + TOKEN)
	                .then()
	                	.log().all()
	                	.extract().response();
		
		System.out.println("O card foi editado: " + response.asString());
		return response;
	}
	
	//REALIZA REMOÇÃO DO CARD
	public Response removeCard() {
		response =	
			given()
				.header("Content-type", "application/json")
	                .when()
	                	.delete(endpoint + "/" + this.getIdcard() + "?key=" + CHAVE_API + "&token=" + TOKEN)
	                .then()
	                	.log().all()
	                	.extract().response();
		
	    System.out.println("O card: " + this.getIdcard() + " foi removido com sucesso!");		
	    return response;
	}
	
}
