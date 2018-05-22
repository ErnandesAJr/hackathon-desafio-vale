package com.hackathon.desafio.bean;
//
//import java.util.List;





import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.OneToMany;

@Entity 
public class Equipe {

//	uma equipe so pode participar de um evento
	
	
//	organizador -->	Gostaria que as equipes, ao se inscreverem
//					no evento, informassem o nome da equipe e os seguintes dados dos
//					membros: nome completo, e-mail, foto, telefone e tamanho de
//					camiseta;
//	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	private String nomeEquipe;
	
	// uma equipe pode esta em varios Eventos
	@OneToMany
	private List<Evento> eventos;
	
	@OneToMany
	private List<Membro> membros;
	
	private String dataDeInscricao;
	
//	private Integer nMembro;
//
//	public Integer getnMembro() {
//		return this.nMembro;
//	}
//
//	public void setnMembro(Integer nMembro) {
//		this.nMembro = nMembro;
//	}
	
	public String getDataDeInscrição() {
		return dataDeInscricao;
	}

	public void setDataDeInscrição(String dataDeInscricao) {
		this.dataDeInscricao = dataDeInscricao;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public List<Membro> getMembros() {
		return membros;
	}

	public void setMembros(List<Membro> membros) {
		this.membros = membros;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	

}
