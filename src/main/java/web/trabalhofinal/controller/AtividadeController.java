package web.trabalhofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	AtividadeService atividadeservice;

	@Autowired
	AtividadeRepository atividaderepository;

	@GetMapping("/escolher")
	public String escolher() {
		return "atividade/escolher";
	}
	
	@GetMapping("/abrircadastrar")
	public String abrircadastrar() {
		return "atividade/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Atividade atividade) {
		
		atividadeservice.salvar(atividade);
		return "index.html";
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
	public String alterar(Atividade atv) {	
		atividadeservice.alterar(atv);
	
		return "atividade/pesquisar";
	}
	
	@PostMapping("/abrirremover")
	public String abrirRemover(AtividadeFilter filtro, Model model) {
		
		model.addAttribute("atividade", filtro);
		return "atividade/remover";
	}

	@PostMapping("/remover")
	public String remover(Atividade filtro) {
		
		filtro.setStatus(Status.INATIVO);
		atividadeservice.alterar(filtro);
		
		return "atividade/pesquisar";
	}

}
