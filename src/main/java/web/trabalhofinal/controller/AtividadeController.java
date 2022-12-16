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

import web.trabalhofinal.model.Atividade;
import web.trabalhofinal.model.Status;
import web.trabalhofinal.model.filter.AtividadeFilter;
import web.trabalhofinal.repository.AtividadeRepository;
import web.trabalhofinal.service.AtividadeService;

@Controller
@RequestMapping("/atividades")
public class AtividadeController {
	
	private static final Logger logger = LoggerFactory.getLogger(AtividadeController.class);
	
	@Autowired
	AtividadeService atividadeservice;

	@Autowired
	AtividadeRepository atividaderepository;

	@GetMapping("/escolher")
	public String escolher() {
		return "atividade/escolher";
	}
	
	@GetMapping("/abrircadastrar")
	public String abrircadastrar(Atividade atividade) {
		return "atividade/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Atividade atividade, BindingResult resultado) {
		
		if (resultado.hasErrors()) {
			logger.info("A atividade recebida para cadastrar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "atividade/cadastrar";
		} else {
			atividadeservice.salvar(atividade);
			return "index.html";
		}
	}

	@GetMapping("/abrirpesquisar")
	public String abrirpesquiasr() {

		return "atividade/pesquisar";
	}

	@GetMapping("/pesquisar")
	public String pesquisar(AtividadeFilter filtro, Model model) {
		
		List<Atividade> atvs = atividadeservice.consultas(filtro);
		model.addAttribute("atividades", atvs);
		
		return  "atividade/mostrartodas";
	}
	
	@PostMapping("/abriralterar")
	public String abriralterar(Atividade filtro, Model model) {
		
		model.addAttribute("atividade", filtro);
		return "atividade/alterar";
	}

	@PostMapping("/alterar")
	public String alterar(@Valid Atividade atividade, BindingResult resultado) {
		
		if (resultado.hasErrors()) {
			logger.info("A atividade recebida para cadastrar não é válida.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "atividade/alterar";
		} else {
			atividadeservice.alterar(atividade);
			return "atividade/pesquisar";
		}
	}
	
	@PostMapping("/abrirremover")
	public String abrirRemover(Atividade atividade, Model model) {
		
		model.addAttribute("atividade", atividade);
		return "atividade/remover";
	}

	@PostMapping("/remover")
	public String remover(Atividade atividade) {
		
		atividade.setStatus(Status.INATIVO);
		atividadeservice.alterar(atividade);
		
		return "atividade/pesquisar";
	}

}
