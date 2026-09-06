package br.edu.ifrs.dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Banda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBanda;
	private String nomeBanda;
	private String genero;

	@OneToMany(mappedBy = "banda")
	private List<Integrante> integrantes = new ArrayList<>();

	@OneToMany(mappedBy = "banda")
	private List<Album> albuns = new ArrayList<>();
	
	public Banda() {
    }

    public Banda(String nomeBanda, String genero) {
        this.nomeBanda = nomeBanda;
        this.genero = genero;
    }

	public Long getIdBanda() {
		return idBanda;
	}

	public String getNomeBanda() {
		return nomeBanda;
	}

	public String getGenero() {
		return genero;
	}

	public List<Integrante> getIntegrantes() {
		return integrantes;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setIdBanda(Long idBanda) {
		this.idBanda = idBanda;
	}

	public void setNomeBanda(String nomeBanda) {
		this.nomeBanda = nomeBanda;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void adicionarIntegrante(Integrante integrante) {
	    integrantes.add(integrante);
	    integrante.setBanda(this);
	}
	
	public void adicionarAlbum(Album album) {
	    albuns.add(album);
	    album.setBanda(this);
	}

	@Override
	public String toString() {
		return "Banda [idBanda=" + idBanda + ", nomeBanda=" + nomeBanda + ", genero=" + genero + ", integrantes="
				+ integrantes + ", albuns=" + albuns + "]";
	}
	
	
	
	
	
}
