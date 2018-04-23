package br.com.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.daos.ProdutoDAO;
import br.com.models.Produto;
import br.com.models.TipoPreco;
import br.com.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form") // or @RequestMapping(value = "/form")
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView("produtos/form");

		mav.addObject("tipos", TipoPreco.values());

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Produto produto, BindingResult result, RedirectAttributes ra) {
		if(result.hasErrors()) {
			return form();
		}
		
		produtoDao.gravar(produto);
		ra.addFlashAttribute("sucesso", "Tudão foi salvo com sucesso, GG");
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView mav = new ModelAndView("produtos/lista");
		mav.addObject("produtos", produtos);
		return mav;
	}
}
