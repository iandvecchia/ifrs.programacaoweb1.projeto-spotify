package br.edu.ifrs.dominio;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Musica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMusica;
	private String nomeMusica;
	private int duracaoSeg;

	@ManyToOne
	private Album album;
	
	

    public Musica() {
    }
    
	public Musica(String nomeMusica, int duracaoSeg) {
		this.nomeMusica = nomeMusica;
		this.duracaoSeg = duracaoSeg;
	}

	public Long getIdMusica() {
		return idMusica;
	}

	public String getNomeMusica() {
		return nomeMusica;
	}

	public int getDuracaoSeg() {
		return duracaoSeg;
	}

	public Album getAlbum() {
		return album;
	}

	public void setIdMusica(Long idMusica) {
		this.idMusica = idMusica;
	}

	public void setNomeMusica(String nomeMusica) {
		this.nomeMusica = nomeMusica;
	}

	public void setDuracaoSeg(int duracaoSeg) {
		this.duracaoSeg = duracaoSeg;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Musica [idMusica=" + idMusica + ", nomeMusica=" + nomeMusica + ", duracaoSeg=" + duracaoSeg + ", album="
				+ album + "]";
	}
	
	

}
