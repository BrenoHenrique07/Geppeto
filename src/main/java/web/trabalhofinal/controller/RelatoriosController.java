package web.trabalhofinal.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.trabalhofinal.service.RelatorioService;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

	private static final Logger logger = LoggerFactory.getLogger(RelatoriosController.class);
	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/todaspessoassimples")
	public ResponseEntity<byte[]> gerarRelatorioSimplesTodasPessoas() {
		logger.trace("Entrou em gerarRelatorioSimplesTodasPessoas");
		logger.debug("Gerando relatório simples de todas as pessoas");
		
		byte[] relatorio = relatorioService.gerarRelatorioSimplesTodasPessoas();
		
		logger.debug("Relatório simples de todas as pessoas gerado");
		logger.debug("Retornando o relatório simples de todas as pessoas");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PessoasSimples.pdf")
				.body(relatorio);
	}
	
}