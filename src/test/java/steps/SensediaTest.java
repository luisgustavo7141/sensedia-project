package steps;

import org.junit.jupiter.api.Assertions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;
import objects.CardObject;

/**
 * @author lbertoli
 *
 */
public class SensediaTest{

	CardObject card = new CardObject();
	Response resp_create, resp_edit, resp_remove;
	
	
	@Dado("^que o usuario possua os dados de autenticacao na API da Trello$")
	public void que_o_usuario_esteja_autenticado_na_API_Trello(){
		assert true;
	}
	
	@Quando("^solicitar a criacao de um card informando os atributos nome '(.*)' e descricao '(.*)'$")
	public void solicitar_a_criacao_de_um_card(String nome, String desc){
		resp_create = card.createCard(nome, desc);
	}
	
	@E("^realizar uma alteracao do nome para '(.*)' e descricao '(.*)'$")
	public void realizar_uma_alteracao(String nome, String desc){
		resp_edit = card.editCard(nome, desc);
	}
	
	@E("^excluir o card criado$")
	public void excluir_card_criado(){
		resp_remove = card.removeCard();
	}

	@Entao("^todas as operacoes devem ser realizadas com sucesso$")
	public void todas_operacoes_devem_ser_realizadas_com_sucesso(){
		Assertions.assertEquals(200, resp_create.statusCode());
		Assertions.assertEquals(200, resp_edit.statusCode());
		Assertions.assertEquals(200, resp_remove.statusCode());
	}
	
}
