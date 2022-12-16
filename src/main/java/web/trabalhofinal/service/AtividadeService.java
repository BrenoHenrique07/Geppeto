package web.trabalhofinal.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.trabalhofinal.model.Atividade;
import web.trabalhofinal.model.filter.AtividadeFilter;
import web.trabalhofinal.repository.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	AtividadeRepository atividaderepository;
	
	@Transactional
	public void salvar(Atividade atividade) {
		atividaderepository.save(atividade);
	}
	
	@Transactional
	public void alterar(Atividade atividade) {
		atividaderepository.save(atividade);
	}
	
	@Transactional
	public void deletar(Long codigo) {
		atividaderepository.deleteById(codigo);
	}
	
	public List<Atividade> consultas(AtividadeFilter filtro) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controleprojetos");
		EntityManager em = emf.createEntityManager();
		
		List<Atividade> atvs;

		if (filtro.getCodigo() != null) {
			String jpql = "Select c from Atividade c where c.codigo = :cod";
			atvs = em.createQuery(jpql, Atividade.class).setParameter("cod", filtro.getCodigo())
					.setFirstResult(0)
                    .setMaxResults(10)
					.getResultList();
		} else if (!filtro.getNome().isEmpty()) {
			String jpql = "Select c from Atividade c where c.nome = :cod2";
			atvs = em.createQuery(jpql, Atividade.class).setParameter("cod2", filtro.getNome())
					.setFirstResult(0)
                    .setMaxResults(10)
					.getResultList();
		} else {
			String jpql = "Select c from Atividade c where status = 'ATIVO'";
			atvs = em.createQuery(jpql, Atividade.class)
					.setFirstResult(0)
                    .setMaxResults(10)
					.getResultList();
		}
		
		return atvs;
	}
}
