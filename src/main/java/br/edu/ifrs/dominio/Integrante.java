package br.edu.ifrs.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Integrante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIntegrante;
	private String nomeIntegrante;
	
	@ManyToOne
	private Banda banda;
	
	public Integrante() {
		
	}
	
	public Integrante(String nomeIntegrante) {
		this.nomeIntegrante = nomeIntegrante;
		
	}

	public Long getIdIntegrante() {
		return idIntegrante;
	}

	public String getNomeIntegrante() {
		return nomeIntegrante;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setIdIntegrante(Long idIntegrante) {
		this.idIntegrante = idIntegrante;
	}

	public void setNomeIntegrante(String nomeIntegrante) {
		this.nomeIntegrante = nomeIntegrante;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	@Override
	public String toString() {
        return "Integrante [idIntegrante=" + idIntegrante
                + ", nomeIntegrante=" + nomeIntegrante + "]";
    }
	
	

}
