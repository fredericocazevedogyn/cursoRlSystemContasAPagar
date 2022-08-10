package br.com.frederico.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.frederico.curso.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
