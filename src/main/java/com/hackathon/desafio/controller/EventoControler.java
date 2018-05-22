package com.hackathon.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hackathon.desafio.bean.Equipe;
import com.hackathon.desafio.bean.Evento;
import com.hackathon.desafio.service.EquipeService;
import com.hackathon.desafio.service.EventoService;

@RestController
@RequestMapping(path = "/eventos/")
public class EventoControler {

	@Autowired
	EventoService eventoService;

	@Autowired
	EquipeService equipeService;

	//visualizar detalhes do Evento
	//	@RequestMapping(path="/{id}")
	//	public ModelAndView detalhesEvento(@PathVariable("id") Integer id, @RequestParam(required=false) String erro){
	//
	//	  ModelAndView model = new ModelAndView("detalhes-eventos");
	//	  Evento evento = eventoService.getEvento(id);
	//	  List<Equipe> equipeSemEvento = equipeService.getEquipeSemEvento();
	//
	//	  model.addObject("equipeSemEvento", equipeSemEvento);
	//	  model.addObject("evento", evento);
	//	  model.addObject("erro", erro);
	//
	//	  return model;
	//	}


	//criar Evento 
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ResponseEntity<Evento> salvarEvento(@RequestBody Evento evento){

		return eventoService.salvarEvento(evento);


	}

	// Organizador  ---> listar as equipes participantes e
	//					seus respectivos membros. 
	//					Caso o número de equipes seja maior do
	//					que 10, favor exibir em outra página;

	// listar equipes participantes de um evento
	@RequestMapping(path="/{id}/listarEquipes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Equipe>> listarEquipesEvento(@PathVariable(value = "id") Integer idEvento) {
		return eventoService.getTodasEquipeEvento(idEvento);	    		
	}

	// listar equipes participantes de um evento --> por nome

	//pega todos os Eventos ... Aberto ou Fechados
	@RequestMapping(path="/todosEventos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Evento>> getAllEventos() {
		return eventoService.getTodosEventos();
	}

	// Participante --->  gostaria de listar as hackathons disponíveis;
	// pega todos os Eventos com Status Aberto
	@RequestMapping(path="/todosEventosAbertos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Evento>> getAllEventosAbertos() {
		return eventoService.getTodosEventoAbertos();
	}

	// Atualizar Evento
	// Organizador --->  gostaria de encerrar as inscrições de uma
	// 					hackathon a qual organizo; Alterar o Status
	@RequestMapping(path="/atualizarEvento/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Evento> atualizarEvento(@RequestBody Evento evento) {

		return eventoService.atulizarEvento(evento);
	}

	// Alterar o Status do Evento ... Aberto ( 1 ) ou Fechado ( 0 )
	@RequestMapping(path="/atualizarEvento/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Evento> atualizarStatusEvento(@PathVariable(value = "id") Integer eventoId,@RequestParam Boolean statusEvento) {

		return eventoService.atulizarStatusEvento(eventoId,statusEvento);
	}	


	//deletar Evento
	@RequestMapping(path="/eventos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEvento(@PathVariable(value = "id") Integer idEvento) {

		eventoService.deleteEvento(idEvento);
		return ResponseEntity.ok().build();
	}

	//adicionar Equipe ao Evento
	// Ṕarticipante ---> gostaria de inscrever minha equipe na
	//					 hackathon;
	// verifica se o Evento ta aberto para cadastrar ou se a Equipe ja esta cadastrada no Evento
	@RequestMapping(path="/{idEvento}/adicionarequipe", method=RequestMethod.POST)
	public ResponseEntity<Boolean> adicionarEquipeAoEvento(@PathVariable("idEvento") Integer idEvento, 
			@RequestParam Integer equipeSemEventoID ){

		return eventoService.addEquipeAoEvento(idEvento, equipeSemEventoID);

	}

	//remover Equipe de um Evento

	// Participante ---> gostaria de cancelar a inscrição da minha
	// 					 equipe em uma hackathon;

	@RequestMapping(path="/{idEvento}/removerequipe",method = RequestMethod.POST)
	public ResponseEntity<Boolean> removerEquipeDoEvento(@PathVariable("idEvento") Integer idEvento, @RequestParam Integer idEquipe){

		return eventoService.delEquipeDoEvento(idEvento, idEquipe); 
	}




} 	
