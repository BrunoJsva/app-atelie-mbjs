package br.com.spring.api.model;

import org.springframework.stereotype.Component;

@Component//Quando estiver sendo executada ele irar procurar tudo dentro dessa classe e depois disponibiliza para ser utilizado atraves do autowired
public class Mensagem {
	
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
