package br.com.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	EntityManager em;

	public void gravar(Produto produto) {
		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("SpringMVC");

		// em.getTransaction().begin();

		em.persist(produto);

		// em.getTransaction().commit();
		// em.close();
	}

	public List<Produto> listar() {

		String jpql = "select p from Produto p";
		Query query = em.createQuery(jpql);

		return query.getResultList();
	}

}
