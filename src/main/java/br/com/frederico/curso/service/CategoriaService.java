package br.com.frederico.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.frederico.curso.model.Categoria;
import br.com.frederico.curso.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listAll(){
		return categoriaRepository.findAll();
	}
	
	public void salvar(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
	
	public Categoria get(Long id) {
		return categoriaRepository.findById(id).get();
	}
	
	public void deletar(Long id) {
		categoriaRepository.deleteById(id);
	}
		
}
