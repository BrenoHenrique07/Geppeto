package web.trabalhofinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.trabalhofinal.model.Projeto;
import web.trabalhofinal.model.filter.ProjetoFilter;
import web.trabalhofinal.repository.ProjetoRepository;
import web.trabalhofinal.service.ProjetoService;

@Controller
@RequestMapping("projetos")
public class ProjetoController {
	
	private static final Logger logger = LoggerFactory.getLogger(AtividadeController.class);
	
	@Autowired
	ProjetoRepository projetorepository;
	
	@Autowired
	ProjetoService projetoservice;
	
	@GetMapping("/escolher")
	public String escolher() {
		return "projeto/escolher";
	}

	@GetMapping("/abrircadastrar")
	public String abrircadastrar(Projeto projeto) {
		return "projeto/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Projeto projeto, BindingResult resultado) {

		if (resultado.hasErrors()) {
			logger.info("O projeto recebida para cadastrar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "projeto/cadastrar";
		} else {
			projetoservice.salvar(projeto);
			return "index.html";
		}
	}
	
	@GetMapping("/abrirpesquisar")
	public String abrirpesquiasr() {

		return "projeto/pesquisar";
	}

	@GetMapping("/pesquisar")
	public String pesquisar(ProjetoFilter filtro, Model model) {
		
		List<Projeto> pjs = projetoservice.consultas(filtro);
		model.addAttribute("projetos", pjs);
		
		return  "projeto/mostrartodos";
	}
}
