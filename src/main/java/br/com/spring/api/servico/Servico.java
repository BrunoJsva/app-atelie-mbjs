package br.com.spring.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.spring.api.model.Mensagem;
import br.com.spring.api.model.Pessoa;
import br.com.spring.api.reposi.Reposi;

@Service
public class Servico {
	
	@Autowired
	private Mensagem mensagem;
	
	@Autowired
	private Reposi acao;
	
	/*
	 * Método responsavel por cadastrar pessoas.
	 * @Return Pessoa
	 * */
	public ResponseEntity<?> cadastrar(Pessoa pessoa){
		
		if(pessoa.getNome().equals("")) {
			mensagem.setMensagem("O nome precisa ser preenchido!");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
			
		}else if(pessoa.getIdade() <= 0){
			mensagem.setMensagem("A idade não e valida!");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
			
		}else {
			return new ResponseEntity<>(acao.save(pessoa), HttpStatus.CREATED);
		}
	}
	
	/*
	 * Método responsavel por selecionar pessoas.
	 * @Return Pessoa.
	 * */
	public ResponseEntity<?> selecionar(){
		return new ResponseEntity(acao.findAll(), HttpStatus.OK);
	}
	
	/*
	 * Método para selecionar pessoas pelo ID.
	 * @Return Pessoa.
	 * */
	public ResponseEntity<?> selecionarPeloId(Integer id){
		if(acao.countById(id)==0) {
			mensagem.setMensagem("Não foi encontrado nenhuma pessoa!");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(acao.findById(id), HttpStatus.OK);
		}
	}
	
	/*
	 * Método para editar dados.
	 * */
	public ResponseEntity<?> editar(Pessoa pessoa){
		
		if(acao.countById(pessoa.getId()) ==0) {
			mensagem.setMensagem("O ID informado não existe!");
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}else if(pessoa.getNome().equals("")) {
			mensagem.setMensagem("É necessario informar um nome!");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);			
		}else if(pessoa.getIdade() <= 0){
			mensagem.setMensagem("É necessario indormar uma idade valida!");
			return new ResponseEntity<>(mensagem, HttpStatus.BAD_GATEWAY);
		}else {
			return new ResponseEntity<>(acao.save(pessoa), HttpStatus.OK);
		}
	}
	
	
	/*
	 * Método para remover cadastros
	 * */
	public ResponseEntity<?> remover(int id){
		
		if(acao.countById(id) == 0){
			mensagem.setMensagem("O código infomado não existe!");
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}else {
			Pessoa pessoa = acao.findByid(id);
			acao.delete(pessoa);
			mensagem.setMensagem("Cadastro removido com sucesso!");
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}
	}
}
