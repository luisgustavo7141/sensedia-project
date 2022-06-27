package utils;

import static io.restassured.RestAssured.baseURI;

/**
 * @author lbertoli
 *
 */
public class BaseTest {
	
	protected final String CHAVE_API = "55223661ec8ce01be940d0ba5e626a24";
	protected final String TOKEN = "2761a0925a72a047162e0436a9db03bde3cc17d2e92220b85dfc398f281d20fd";
	protected final String ID_LIST = "62b65e6f58808c4965180aa5";

	public BaseTest() {
		baseURI = "https://api.trello.com";
	}
}
