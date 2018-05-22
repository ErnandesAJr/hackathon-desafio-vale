package com.hackathon.desafio.bean;
//import java.util.List;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


//Organizador -- >(CRUD) hackathon com
//					nome do evento, descrição, local, data, número de participantes por
//					equipe e número de equipes, (STATUS)


//			  -- >	gostaria de encerrar as inscrições de uma
//					hackathon a qual organizo;


@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	private String nomeEvento;
	
	private String descricaoEvento;
	
	private String localEvento;
	
	private String dataEvento;
	
	private Integer nPartiPorEvento;
	
	private Integer nEquipesEvento;
	

	@OneToMany
	List<Equipe> equipes;
	
	private Boolean statusEvento;
	
	public Boolean getStatusEvento() {
		return statusEvento;
	}

	public void setStatusEvento(Boolean statusEvento) {
		this.statusEvento = statusEvento;
	}
	
	public Evento() {
		
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}

	public String getLocalEvento() {
		return localEvento;
	}

	public void setLocalEvento(String localEvento) {
		this.localEvento = localEvento;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Integer getnPartiPorEvento() {
		return nPartiPorEvento;
	}

	public void setnPartiPorEvento(Integer nPartiPorEvento) {
		this.nPartiPorEvento = nPartiPorEvento;
	}

	public Integer getnEquipesEvento() {
		return nEquipesEvento;
	}

	public void setnEquipesEvento(Integer nEquipes) {
		this.nEquipesEvento = nEquipes;
	}
		
	
}
