package br.edu.ifrs.dao;

import java.util.List;

import br.edu.ifrs.dominio.Banda;
import jakarta.persistence.EntityManager;

public class BandaDAOJPA implements BandaDAO {
	private EntityManager em;

	public BandaDAOJPA(EntityManager em) {
		this.em = em;
	}

	@Override
	public void salvar(Banda banda) {

		em.getTransaction().begin();

		em.persist(banda);

		em.getTransaction().commit();
	}

	@Override
	public Banda buscarPorId(Long id) {

		return em.find(Banda.class, id);
	}

	@Override
	public List<Banda> listarTodos() {

		String jpql = "SELECT b FROM Banda b";

		return em.createQuery(jpql, Banda.class).getResultList();
	}

	@Override
	public Banda atualizar(Banda banda) {

		em.getTransaction().begin();

		Banda bandaAtualizada = em.merge(banda);

		em.getTransaction().commit();

		return bandaAtualizada;
	}

	@Override
	public void remover(Long id) {

		em.getTransaction().begin();

		Banda banda = em.find(Banda.class, id);

		if (banda != null) {
			em.remove(banda);
		}

		em.getTransaction().commit();
	}

	@Override
	public List<Banda> listarPorGenero(String genero) {

		String jpql = "SELECT b FROM Banda b WHERE b.genero = :genero";

		return em.createQuery(jpql, Banda.class).setParameter("genero", genero).getResultList();
	}

}
