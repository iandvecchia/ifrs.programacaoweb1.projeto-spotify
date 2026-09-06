package br.edu.ifrs.spotify;

import java.util.List;

import br.edu.ifrs.dao.BandaDAO;
import br.edu.ifrs.dao.BandaDAOJPA;
import br.edu.ifrs.dominio.Album;
import br.edu.ifrs.dominio.Banda;
import br.edu.ifrs.dominio.Integrante;
import br.edu.ifrs.dominio.Musica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Principal {

	public static void main(String[] args) {


        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("musica-pu");

        EntityManager em =
                emf.createEntityManager();

        BandaDAO bandaDAO =
                new BandaDAOJPA(em);

        try {

            // =====================================================
            // 1. CRIAR AS BANDAS
            // =====================================================

            Banda metallica =
                    new Banda("Metallica", "Heavy Metal");

            Banda guns =
                    new Banda("Guns N' Roses", "Hard Rock");

            Banda redHot =
                    new Banda("Red Hot Chili Peppers", "Alternative Rock");

            Banda fooFighters =
                    new Banda("Foo Fighters", "Alternative Rock");


            // =====================================================
            // 2. CRIAR INTEGRANTES
            // =====================================================

            Integrante james =
                    new Integrante("James Hetfield");

            Integrante lars =
                    new Integrante("Lars Ulrich");

            metallica.adicionarIntegrante(james);
            metallica.adicionarIntegrante(lars);


            Integrante axl =
                    new Integrante("Axl Rose");

            Integrante slash =
                    new Integrante("Slash");

            guns.adicionarIntegrante(axl);
            guns.adicionarIntegrante(slash);


            Integrante anthony =
                    new Integrante("Anthony Kiedis");

            Integrante john =
                    new Integrante("John Frusciante");

            redHot.adicionarIntegrante(anthony);
            redHot.adicionarIntegrante(john);


            Integrante dave =
                    new Integrante("Dave Grohl");

            Integrante ilan =
                    new Integrante("Ilan Rubin");

            fooFighters.adicionarIntegrante(dave);
            fooFighters.adicionarIntegrante(ilan);


            // =====================================================
            // 3. CRIAR ÁLBUNS
            // =====================================================

            Album blackAlbum =
                    new Album("Black Album");

            Album appetite =
                    new Album("Appetite for Destruction");

            Album stadium =
                    new Album("Stadium Arcadium");

            Album wastingLight =
                    new Album("Wasting Light");


            metallica.adicionarAlbum(blackAlbum);
            guns.adicionarAlbum(appetite);
            redHot.adicionarAlbum(stadium);
            fooFighters.adicionarAlbum(wastingLight);


            // =====================================================
            // 4. CRIAR MÚSICAS
            // =====================================================

            Musica enterSandman =
                    new Musica("Enter Sandman", 331);

            Musica paradiseCity =
                    new Musica("Paradise City", 406);

            Musica daniCalifornia =
                    new Musica("Dani California", 282);

            Musica dearRosemary =
                    new Musica("Dear Rosemary", 269);


            blackAlbum.adicionarMusica(enterSandman);
            appetite.adicionarMusica(paradiseCity);
            stadium.adicionarMusica(daniCalifornia);
            wastingLight.adicionarMusica(dearRosemary);


            // =====================================================
            // 5. PERSISTIR TODOS OS OBJETOS
            // Cada objeto possui seu próprio persist
            // =====================================================

            em.getTransaction().begin();

            // Bandas
            em.persist(metallica);
            em.persist(guns);
            em.persist(redHot);
            em.persist(fooFighters);

            // Integrantes
            em.persist(james);
            em.persist(lars);

            em.persist(axl);
            em.persist(slash);

            em.persist(anthony);
            em.persist(john);

            em.persist(dave);
            em.persist(ilan);

            // Álbuns
            em.persist(blackAlbum);
            em.persist(appetite);
            em.persist(stadium);
            em.persist(wastingLight);

            // Músicas
            em.persist(enterSandman);
            em.persist(paradiseCity);
            em.persist(daniCalifornia);
            em.persist(dearRosemary);

            em.getTransaction().commit();

            System.out.println("\nDados iniciais persistidos com sucesso!");


            // =====================================================
            // 6. TESTAR BUSCAR POR ID
            // =====================================================

            System.out.println("\n--- BUSCAR POR ID ---");

            Banda bandaEncontrada =
                    bandaDAO.buscarPorId(guns.getIdBanda());

            System.out.println(bandaEncontrada);


            // =====================================================
            // 7. TESTAR LISTAR TODOS
            // =====================================================

            System.out.println("\n--- LISTAR TODAS AS BANDAS ---");

            List<Banda> bandas =
                    bandaDAO.listarTodos();

            for (Banda banda : bandas) {
                System.out.println(banda);
            }


            // =====================================================
            // 8. TESTAR JPQL - LISTAR POR GÊNERO
            // =====================================================

            System.out.println("\n--- BANDAS DE ALTERNATIVE ROCK ---");

            List<Banda> bandasAlternative =
                    bandaDAO.listarPorGenero("Alternative Rock");

            for (Banda banda : bandasAlternative) {
                System.out.println(banda);
            }


            // =====================================================
            // 9. TESTAR ATUALIZAÇÃO
            // Adicionar Duff McKagan ao Guns N' Roses
            // =====================================================

            System.out.println(
                    "\n--- ADICIONANDO DUFF MCKAGAN AO GUNS N' ROSES ---");

            Integrante duff =
                    new Integrante("Duff McKagan");

            guns.adicionarIntegrante(duff);

            /*
             * Como não estamos utilizando cascade,
             * o novo integrante precisa de seu próprio persist.
             */
            em.getTransaction().begin();

            em.persist(duff);

            em.getTransaction().commit();

            /*
             * Executamos também o método atualizar()
             * para exercitar o merge() do DAO.
             */
            guns = bandaDAO.atualizar(guns);

            System.out.println(
                    "Banda atualizada: " + guns.getNomeBanda());

            System.out.println("Integrantes:");

            for (Integrante integrante : guns.getIntegrantes()) {
                System.out.println(integrante.getNomeIntegrante());
            }


            // =====================================================
            // 10. TESTAR REMOVER
            // Metallica será removida no final
            // =====================================================

            System.out.println("\n--- REMOVENDO METALLICA ---");

            Long idMetallica =
                    metallica.getIdBanda();

            /*
             * Não temos cascade REMOVE.
             *
             * Portanto:
             *
             * Musica -> Album -> Integrantes -> Banda
             */

            em.getTransaction().begin();

            // Música do álbum
            em.remove(enterSandman);

            // Álbum
            em.remove(blackAlbum);

            // Integrantes
            em.remove(james);
            em.remove(lars);

            em.getTransaction().commit();

            /*
             * Agora não existem mais registros apontando
             * para a banda Metallica.
             */
            bandaDAO.remover(idMetallica);

            System.out.println("Metallica removida com sucesso!");


            // =====================================================
            // 11. LISTAGEM FINAL
            // =====================================================

            System.out.println("\n--- BANDAS RESTANTES ---");

            List<Banda> bandasRestantes =
                    bandaDAO.listarTodos();

            for (Banda banda : bandasRestantes) {
                System.out.println(banda);
            }


        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
            emf.close();
        }
	}

}
