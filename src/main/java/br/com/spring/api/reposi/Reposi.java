package br.com.spring.api.reposi;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.api.model.Pessoa;

@Repository
public interface Reposi extends CrudRepository<Pessoa, Integer>{
	
	List<Pessoa> findAll();//Lista todos os dados da lista(Pessoa).
	
	Pessoa findByid(int id);//Procura pelo @id de cada pessoa.
	
	List<Pessoa> findByOrderByNomeAsc();//Ordenar nomes de A a Z.
	
	List<Pessoa> findByOrderByNomeDesc();//Ordenar nome de Z a A.
	
	List<Pessoa> findByNomeOrderByIdadeDesc(String nome);//Filtrar pelo nome e ordernar pela idade maior para o menor.
	
	List<Pessoa> findByNomeOrderByIdadeAsc(String nome);//Filtrar pelo nome e ordernar pela idade menor para o maior.
	
	List<Pessoa> findByNomeContaining(String termo);//Função para verificar se o nome contem determinada string, parecido com a função Like do SQL.
	
	List<Pessoa> findByNomeStartingWith(String termo);//Função para verificar se o termo obrigatoriamente começa ou termina.(INICIA)
	
	List<Pessoa> findByNomeEndingWith(String termo);//Função para verificar se o termo obrigatoriamente começa ou termina.(TERMINA)
	
	@Query(value = "SELECT SUM(idade) FROM pessoas", nativeQuery = true)
	int somaIdades();//Soma Todas as idades das tabelas no banco de dados.
	
	//selecione tudo de pessoas onde a coluna idade e maior ou igual a idade passada.
	@Query(value = "SELECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
	List<Pessoa> idadeMaiorIgual(int idade);
	
	int countById(int id);
}
