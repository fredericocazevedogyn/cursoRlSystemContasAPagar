package br.com.frederico.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.frederico.curso.model.Contas;
import br.com.frederico.curso.repository.ContasRepository;

@Service
@Transactional
public class ContasService {

	@Autowired
	private ContasRepository contasRepository;
	
	public List<Contas> listAll(){
		return contasRepository.findAll();
	}
	
	public void salvar(Contas conta) {
		contasRepository.save(conta);
	}
	
	public Contas get(Long id) {
		return contasRepository.findById(id).get();
	}
	
	public void deletar(Long id) {
		contasRepository.deleteById(id);
	}
	
	public List<Contas> listAllByDescricao(String descricao) {
		return contasRepository.findByDescricaoContaining(descricao);
	}
	
	public List<Contas> listAllByValor(Float valor) {
		return contasRepository.findByValor(valor);
	} 
	
}
