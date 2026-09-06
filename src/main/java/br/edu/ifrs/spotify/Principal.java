package br.edu.ifrs.spotify;

import br.edu.ifrs.dominio.Album;
import br.edu.ifrs.dominio.Banda;
import br.edu.ifrs.dominio.Integrante;
import br.edu.ifrs.dominio.Musica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Principal {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {

			emf = Persistence.createEntityManagerFactory("musica-pu");
			em = emf.createEntityManager();

			// -----------------------------
			// Criando a banda
			// -----------------------------
			Banda banda = new Banda("Metallica", "Heavy Metal");

			// -----------------------------
			// Criando os integrantes
			// -----------------------------
			Integrante integrante1 = new Integrante("James Hetfield");
			Integrante integrante2 = new Integrante("Lars Ulrich");

			banda.adicionarIntegrante(integrante1);
			banda.adicionarIntegrante(integrante2);

			// -----------------------------
			// Criando o álbum
			// -----------------------------
			Album album = new Album("Master of Puppets");

			banda.adicionarAlbum(album);

			// -----------------------------
			// Criando as músicas
			// -----------------------------
			Musica musica1 = new Musica("Battery", 312);
			Musica musica2 = new Musica("Master of Puppets", 515);

			album.adicionarMusica(musica1);
			album.adicionarMusica(musica2);

			// -----------------------------
			// Início da transação
			// -----------------------------
			em.getTransaction().begin();

			// Persistindo cada entidade
			em.persist(banda);

			em.persist(integrante1);
			em.persist(integrante2);

			em.persist(album);

			em.persist(musica1);
			em.persist(musica2);

			// Confirmando a transação
			em.getTransaction().commit();

			System.out.println("Dados cadastrados com sucesso!");

		} catch (Exception e) {

			System.out.println("Erro ao persistir os dados.");

			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			e.printStackTrace();

		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}
	}

}
