	package com.hackathon.desafio.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity 
public class Membro{

//	nome completo, e-mail, foto, telefone e tamanho de
//	camiseta;
//  um membro participa de uma Equipe ... não há reaproveitamento de membro ... criar uma equipe precisa por um membro de novo	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	private String nomeCompletoMembro;
	
	private String emailMembro;
	
	private String telefoneMembro;
	
	private String tamCamisaMembro;
	
	@OneToOne
	private Equipe equipe;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNomeCompletoMembro() {
		return nomeCompletoMembro;
	}

	public void setNomeCompletoMembro(String nomeCompletoMembro) {
		this.nomeCompletoMembro = nomeCompletoMembro;
	}

	public String getEmailMembro() {
		return emailMembro;
	}

	public void setEmailMembro(String emailMembro) {
		this.emailMembro = emailMembro;
	}

	public String getTelefoneMembro() {
		return telefoneMembro;
	}

	public void setTelefoneMembro(String telefoneMembro) {
		this.telefoneMembro = telefoneMembro;
	}

	public String getTamCamisaMembro() {
		return tamCamisaMembro;
	}

	public void setTamCamisaMembro(String tamCamisaMembro) {
		this.tamCamisaMembro = tamCamisaMembro;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
}
