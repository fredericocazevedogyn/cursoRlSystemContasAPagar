package br.com.frederico.curso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.frederico.curso.model.Categoria;
import br.com.frederico.curso.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping("listar")
	public String index(Model model) {
		return null;
	}

	@RequestMapping("adicionar")
	public ModelAndView add(Categoria categoria) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adicionar");
		mv.addObject("conta", categoria);
		return mv;
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("conta") Categoria categoria, BindingResult result) {

		//verifica se tem erros
		if(result.hasErrors()) {
			return add(categoria);
		}
		categoriaService.salvar(categoria);
		ModelAndView mv = new ModelAndView("redirect:/contas/listar");
		return mv;
		
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		categoriaService.deletar(id);
		ModelAndView mv = new ModelAndView("redirect:/contas/listar");
		return mv;
	}
	
	@RequestMapping("editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		return add(categoriaService.get(id));
	}
	
}
