package com.hackathon.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.desafio.bean.Equipe;
import com.hackathon.desafio.bean.Evento;
import com.hackathon.desafio.bean.Membro;
import com.hackathon.desafio.service.EquipeService;

@RestController
@RequestMapping(path = "/equipes/")
public class EquipeController {
	
	@Autowired
	EquipeService equipeService;
	

	//salva Equipe
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ResponseEntity<Equipe> salvarEquipe(@RequestParam String nomeEquipe, @RequestParam List<Membro> membros, @RequestParam String dataDeInscricao){
		return equipeService.salvarEquipe(nomeEquipe, membros,dataDeInscricao);
	}
	
	//deleta Equipe
	@RequestMapping(path="/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletarEquipe(@RequestParam Integer idEquipe){
		return 	equipeService.deleteEquipe(idEquipe);
	}
	
	//pega todas as equipes
	@RequestMapping(path="/listarEquipes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Equipe>> getAllEquipes() {
	    return equipeService.getTodasEquipes();
	}
		
	//atualizar Equipe
	@RequestMapping(path="/atualizarEquipe/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarEquipe(@PathVariable(value = "id") Integer idEquipe,@RequestParam String nomeEquipe,
			@RequestParam List<Membro> membros,	@RequestParam List<Evento> eventos) {

	    return equipeService.atulizarEquipe(idEquipe, nomeEquipe, membros, eventos);	
	}
	
	// adiciona um membro na Equipe
	@RequestMapping(path="/{idEquipe}/adicionarmembro", method=RequestMethod.POST)
	public ResponseEntity<Boolean> adicionarMembroAEEquipe(@PathVariable("idEquipe") Integer idEquipe, 
	        @RequestParam Integer idMembro ){

	  return equipeService.addMembroAEquipe(idMembro, idEquipe);		
	}
	
	// remove um membro da equipe
	@RequestMapping(path="/{idEquipe}/removermembro",method = RequestMethod.POST)
	public ResponseEntity<Boolean> removerMembroDaEquipe(@PathVariable("idEquipe") Integer idEquipe, @RequestParam Integer idMembro){
		

		return 	equipeService.delMembroDoEquipe(idMembro, idEquipe);
	}
	
	//pegar todos membro de uma equipe
	@RequestMapping(path="/{idEquipe}/listarMembrosEquipe", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Membro>> getTodosMembroEquipe(@PathVariable("idEquipe") Integer idEquipe) {
	    return equipeService.getTodasMembroEquipe(idEquipe);
	}

	
}
