package br.com.spring.api.model;

import javax.persistence.*;

@Entity//Especifica a Criação da Tabela no banco de dados.
@Table(name = "pessoas")
public class Pessoa {
	
	@Id//Difinição da Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Responsavel por gerar valores de maneira crescente auto.
	private Integer id;
	private String nome;
	private Integer idade;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the idade
	 */
	public Integer getIdade() {
		return idade;
	}
	/**
	 * @param idade the idade to set
	 */
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
}
