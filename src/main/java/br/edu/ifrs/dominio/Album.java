package br.edu.ifrs.dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlbum;
	private String nomeAlbum;

	@ManyToOne
	private Banda banda;
	@OneToMany(mappedBy = "album")
	private List<Musica> musicas = new ArrayList<>();
	
	public Album() {
		
	}
	
	public Album(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}

	public Long getIdAlbum() {
		return idAlbum;
	}

	public String getNomeAlbum() {
		return nomeAlbum;
	}

	public Banda getBanda() {
		return banda;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public void adicionarMusica(Musica musica) {
		musicas.add(musica);
		musica.setAlbum(this);
	}

	@Override
	public String toString() {
	    return "Album [idAlbum=" + idAlbum
	            + ", nomeAlbum=" + nomeAlbum + "]";
	}
}
