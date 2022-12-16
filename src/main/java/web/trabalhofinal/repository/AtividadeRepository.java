package web.trabalhofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.trabalhofinal.model.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
	
}
