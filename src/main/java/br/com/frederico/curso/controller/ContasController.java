package br.com.frederico.curso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.frederico.curso.model.Categoria;
import br.com.frederico.curso.model.Contas;
import br.com.frederico.curso.service.CategoriaService;
import br.com.frederico.curso.service.ContasService;

@Controller
@RequestMapping("/contas")
public class ContasController {

	@Autowired
	private ContasService contasService;
	
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping("listar")
	public String index(@RequestParam(value = "search", required = false) String q, Model model) {
		
		List<Contas> contas = null;
		
		if ((q != null) && (q.length() > 1) && (!q.matches("[-+]?[0-9]*\\.?[0-9]+"))) {
			contas = contasService.listAllByDescricao(q);
			model.addAttribute("search", q);
		} else if ((q != null) && (q.length() > 1) && (q.matches("[-+]?[0-9]*\\.?[0-9]+"))){
			contas = contasService.listAllByValor(Float.parseFloat(q));
			model.addAttribute("search", q);
		} else {
			contas = contasService.listAll();
			model.addAttribute("search", "");
		}
		
		model.addAttribute("contas", contas);
		
		return "index";
	}

	@RequestMapping("adicionar")
	public ModelAndView add(Contas conta) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adicionar");
		mv.addObject("conta", conta);
		
		List<Categoria> categorias = categoriaService.listAll();
		mv.addObject("categorias", categorias);
		
		return mv;
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("conta") Contas conta, BindingResult result) {

		//verifica se tem erros
		if(result.hasErrors()) {
			return add(conta);
		}
		contasService.salvar(conta);
		ModelAndView mv = new ModelAndView("redirect:/contas/listar");
		return mv;
		
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		contasService.deletar(id);
		ModelAndView mv = new ModelAndView("redirect:/contas/listar");
		return mv;
	}
	
	@RequestMapping("editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		return add(contasService.get(id));
	}

}
