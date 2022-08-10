package br.com.frederico.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.frederico.curso.model.Contas;

public interface ContasRepository extends JpaRepository<Contas, Long>{

	List<Contas> findByDescricaoContaining(String descricao);
	List<Contas> findByValor(Float valor);
	
}
