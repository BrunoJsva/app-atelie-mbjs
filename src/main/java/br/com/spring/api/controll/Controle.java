package br.com.spring.api.controll;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.api.model.Cliente;
import br.com.spring.api.model.Pessoa;
import br.com.spring.api.reposi.Reposi;
import br.com.spring.api.servico.Servico;

@RestController
public class Controle {
	
	@Autowired
	private Reposi acao;
	
	@Autowired
	private Servico servico;
	
	@GetMapping("api/status")
	public ResponseEntity<?> status() {//ResponseEntity retorna o status da requisição, o simbolo "?" quer dizer que pode ou não haver um objeto a se retornar.
		return new ResponseEntity<>(HttpStatus.CREATED);//Instancia um novo objeto ResponseEntity que retorna o numero do status para a requisição.
	}
	
	@GetMapping("api/somaIdades")
	public int somaIdades() {
		return acao.somaIdades();//Soma Todas as idades das tabelas no banco de dados.
	}
	
	@GetMapping("api/idadeMaiorIgual")
	public List<Pessoa> idadeMaiorIgual() {
		return acao.idadeMaiorIgual(55);//selecione tudo de pessoas onde a coluna idade e maior ou igual a idade passada.
	}
	
	@GetMapping("api/obgComecaCom")
	public List<Pessoa> obgComecaCom(){
		return acao.findByNomeStartingWith("Br");//Função para verificar se o termo obrigatoriamente começa ou termina.(INICIA)
	}
	
	@GetMapping("api/obgTerminaCom")
	public List<Pessoa> obgTerminaCom(){
		return acao.findByNomeEndingWith("ta");//Função para verificar se o termo obrigatoriamente começa ou termina.(TERMINA)
	}
	
	@GetMapping("/api/contem")
	public List<Pessoa> contem(){
		return acao.findByNomeContaining("B");//Função para verificar se o nome contem determinada string, parecido com a função Like do SQL.
	}
	
	@GetMapping("api/findByNomeOrderByIdadeDesc")
	public List<Pessoa> findByNomeOrderByIdadeDesc(){
		return acao.findByNomeOrderByIdadeDesc("Eduardo Costa ");//Filtrar pelo nome e ordernar pela idade maior para o menor.
	}
	
	@GetMapping("api/findByNomeOrderByIdadeAsc")
	public List<Pessoa> findByNomeOrderByIdadeAsc(){
		return acao.findByNomeOrderByIdadeAsc("Eduardo Costa ");//Filtrar pelo nome e ordernar pela idade menor para o maior.
	}
	
	@GetMapping("api/ordenarNomesAsc")
	public List<Pessoa> ordenarNomesAsc(){
		return acao.findByOrderByNomeAsc();//Ordenar nome de A a Z.
	}
	
	@GetMapping("api/ordenarNomesDesc")
	public List<Pessoa> ordenarNomesDesc(){
		return acao.findByOrderByNomeDesc();//Ordenar nome de Z a A.
	}
	
	@GetMapping("api/contarCadastros")
	public long contarCadastros() {//Count retorna um long.
		return acao.count();//Count e um comando utilizado para retorna a quantidade de cadastros que estao na base.
	}
	
	@DeleteMapping("api/removerCadastro/{id}")//Deletar Cadastro pelo id passado na URL.
	public ResponseEntity<?> removerCadastro(@PathVariable Integer id) {// setando o valor passado na URL para a variável.
		return servico.remover(id);
	}
	
	@PutMapping("api/alterarDados")//No metodo PUT so é possivel alterar o Objeto inteiro, sendo nescessario passar todos os paramentros de cada obj
	public ResponseEntity<?> alterarDados(@RequestBody Pessoa obj) {//Setando o valor de Pessoa para o obj.
		return servico.editar(obj);//No save ao passar um objeto que ja tenha o id, ele atualiza, caso o objeto esteja vazio ele cria um novo id.
	}
	
	@GetMapping("api/buscarPeloId/{id}")//Buscando dados pelo id passado na url.
	public ResponseEntity<?> buscarPeloId(@PathVariable Integer id) {//Setando o valor enviado pela url na variável.
		return servico.selecionarPeloId(id);
	}
	
	@PostMapping("api/cadastrar")//Cadastrar pessoas atraves do REST POST.
	public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj) {
		return servico.cadastrar(obj);//Propriedade do CrudRepository para salvar a informação no banco.
	} 
	
	@GetMapping("api/listarTudo")
	public ResponseEntity<?> listarTodos(){//Retornar uma Lista com todas as pessoas do BD.
		return servico.selecionar();//Propriedade do CrudRepository para salvar a informação no banco.(CRIADO MANUALMENTE).
	}
	
	@PostMapping("/api/clienteADD")
	public void cliente(@Valid @RequestBody Cliente cliente) {
		
	}

}
