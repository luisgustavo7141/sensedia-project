package steps;

import org.junit.jupiter.api.Assertions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;
import objects.BoardObject;
import objects.CardObject;

/**
 * @author lbertoli
 *
 */
public class SensediaTest{
	
	private BoardObject board = new BoardObject();;
	private CardObject card = new CardObject();;
	
	private Response resp_create_board, resp_remove_board;
	private Response resp_create_card, resp_edit_card, resp_remove_card;
	
	private static String idBoard, idList;
	
	@Dado("^que o usuario possua os dados de autenticacao na API da Trello$")
	public void que_o_usuario_esteja_autenticado_na_API_Trello(){
		assert true;
	}
	
	@Quando("^solicitar a criacao de um board informando o atributo nome '(.*)'$")
	public void solicitar_a_criacao_de_um_board(String nome){
		resp_create_board = board.createBoard(nome);
		idBoard = resp_create_board.jsonPath().getString("id");
		idList = board.getIdList(idBoard);
	}
	
	@Entao("^a operacao deve ser realizada com sucesso$")
	public void operacao_deve_ser_realizada_com_sucesso(){
		Assertions.assertEquals(200, resp_create_board.statusCode());
	}

	@Quando("^solicitar a criacao de um card informando os atributos nome '(.*)' e descricao '(.*)'$")
	public void solicitar_a_criacao_de_um_card(String nome, String desc){
		resp_create_card = card.createCard(nome, desc, idList );
	}
	
	@E("^realizar uma alteracao do nome para '(.*)' e descricao '(.*)'$")
	public void realizar_uma_alteracao(String nome, String desc){
		resp_edit_card = card.editCard(nome, desc, idList);
	}
	
	@E("^excluir o card criado$")
	public void excluir_card_criado(){
		resp_remove_card = card.removeCard();
	}

	@Entao("^todas as operacoes devem ser realizadas com sucesso$")
	public void todas_operacoes_devem_ser_realizadas_com_sucesso(){
		Assertions.assertEquals(200, resp_create_card.statusCode());
		Assertions.assertEquals(200, resp_edit_card.statusCode());
		Assertions.assertEquals(200, resp_remove_card.statusCode());
	}
		
	@Quando("^solicitar a remocao de um board$")
	public void solicitar_a_remocao_de_um_board(){
		resp_remove_board = board.removeBoard(idBoard);
	}
	
	@Entao("^a operacao remover board deve ser realizada com sucesso$")
	public void operacao_remover_board_deve_ser_realizada_com_sucesso(){
		Assertions.assertEquals(200, resp_remove_board.statusCode());
	}
	
}
