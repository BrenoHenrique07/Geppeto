package web.trabalhofinal.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.trabalhofinal.model.Projeto;
import web.trabalhofinal.model.filter.ProjetoFilter;
import web.trabalhofinal.repository.ProjetoRepository;

@Service
public class ProjetoService {
	
	@Autowired
	ProjetoRepository projetorepository;
	
	@Transactional
	public void salvar(Projeto projeto) {
		projetorepository.save(projeto);
	}
	
	@Transactional
	public void alterar(Projeto projeto) {
		projetorepository.save(projeto);
	}
	
	@Transactional
	public void deletar(Long codigo) {
		projetorepository.deleteById(codigo);
	}
	
	public List<Projeto> consultas(ProjetoFilter filtro) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controleprojetos");
		EntityManager em = emf.createEntityManager();
		
		List<Projeto> prs;

		if (filtro.getCodigo() != null) {
			String jpql = "Select c from Projeto c where c.codigo = :cod";
			prs = em.createQuery(jpql, Projeto.class).setParameter("cod", filtro.getCodigo())
					.setFirstResult(0)
                    .setMaxResults(10)
					.getResultList();
		} else if (!filtro.getNome().isEmpty()) {
			String jpql = "Select c from Projeto c where c.nome = :cod2";
			prs = em.createQuery(jpql, Projeto.class).setParameter("cod2", filtro.getNome())
					.setFirstResult(0)
                    .setMaxResults(10)
					.getResultList();
		} else {
			String jpql = "Select c from Projeto c where status = 'ATIVO'";
			prs = em.createQuery(jpql, Projeto.class)
					.setFirstResult(0)
                    .setMaxResults(10)
					.getResultList();
		}
		
		return prs;
	}
}

