package com.hackathon.desafio.service;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackathon.desafio.bean.Equipe;
import com.hackathon.desafio.bean.Evento;
import com.hackathon.desafio.bean.Membro;
import com.hackathon.desafio.repositorio.EquipeRepositorio;
import com.hackathon.desafio.repositorio.EventoRepositorio;
import com.hackathon.desafio.repositorio.MembroRepositorio;


@Service
public class EventoService {

	@Autowired
	EventoRepositorio eventoRepo;

	@Autowired
	EquipeRepositorio equipeRepo;
	
	@Autowired
	MembroRepositorio  membroRepo;
	
	// crio o Evento sem Equipe e depois adiciono Equipes ao Evento
	public ResponseEntity<Evento> salvarEvento(Evento evento){

//		Evento evento = new Evento();
//
//		evento.setNomeEvento(nomeEvento);
//		evento.setDescricaoEvento(descricaoEvento);
//		evento.setLocalEvento(localEvento);
//		evento.setDataEvento(dataEvento);
//		evento.setnPartiPorEvento(nPartiPorEvento);
//		evento.setnEquipesEvento(nEquipes);
//		evento.setStatusEvento(statusEvento);

		return new ResponseEntity<Evento>(eventoRepo.save(evento), HttpStatus.OK);
		
	}

	//pega o Evento
	public ResponseEntity<Evento> getEvento(Integer idEvento){
		return new ResponseEntity<Evento>(eventoRepo.findById(idEvento).get(), HttpStatus.OK);
	}

	//pega todos os Eventos 
	public ResponseEntity<List<Evento>> getTodosEventos(){
		return new ResponseEntity<List<Evento>>(new ArrayList<Evento>(eventoRepo.findAll()), HttpStatus.OK);
		
	}

	//pega todos Eventos com Status Aberto
	// Participante ---> listar as hackathons disponíveis;
	public ResponseEntity<List<Evento>>  getTodosEventoAbertos(){
//		List<Evento> eventosAbertos = new ArrayList<Evento>();
//		for(Evento e:eventoRepo.findAll()){
//			if(e.getStatusEvento() == true) {
//				eventosAbertos.add(e);
//			}
//		}

		return new ResponseEntity<List<Evento>>(new ArrayList<Evento>(eventoRepo.pegarTodosEventosAberto()), HttpStatus.OK);
		
	}

	// adicionar Equipe ao Evento
	// verifica se a Quantidade de Membro é certa para o Evento ... 
	// 										se nao for , nao adiciona	
	public ResponseEntity<Boolean> addEquipeAoEvento(Integer idEvento, Integer equipeEventoID){

		Boolean equipeEmEvento = false;
		Evento evento = eventoRepo.findById(idEvento).get();
		
		//verifica se o evento ta Aberto ou Fechado
		if(evento.getStatusEvento() == false){ // evento fechado pelo Organizador
			//return "Evento já está fechado";
			return new ResponseEntity<Boolean>((false), HttpStatus.OK);
		}else{
			Equipe equipe = equipeRepo.findById(equipeEventoID).get();

			// verifica se a Equipe ja esta no Evento
			for(Evento e:equipe.getEventos()) {

				if(e.getId() == evento.getId()) {
					equipeEmEvento = true;
				}

			}

			// As equipes não podem ter o mesmo nome na mesma hackathon;
			//verificar se tem Equipe com o mesmo nome no evento 
			for(Equipe e:evento.getEquipes()) {
				if(e.getNomeEquipe() == equipe.getNomeEquipe()) {
					equipeEmEvento = true;
				}
				
				//Um membro não pode participar de 2 times na mesma hackathon;
				// verifica se tem um membro dessa equipe ta em outra equipe no mesmo Hackathon -- pelo Nome ou email
				for(Membro m: e.getMembros()) {
					for(Membro m1:equipe.getMembros()) {
						if(m.getEmailMembro() == m1.getEmailMembro() || m.getNomeCompletoMembro() == m1.getNomeCompletoMembro()) {
							equipeEmEvento = true;
							// membro ja cadastrado
						}
					}
					
				}
				
				
			}

			if(equipeEmEvento == false) {

				//verifica se a Quantidade de membro é certa para o Evento
				if(equipe.getMembros().size() == evento.getnPartiPorEvento()) {
					evento.getEquipes().add(equipe);
					equipe.getEventos().add(evento);
					eventoRepo.save(evento);
					equipeRepo.save(equipe);

					// return " Adicionado Com Sucesso";
					return new ResponseEntity<Boolean>((true), HttpStatus.OK);

				}
				// return " Quantidade de Membros Incompatíveis";
				return new ResponseEntity<Boolean>((false), HttpStatus.OK);

			}


			//return "Equipe já está no Evento";
			return new ResponseEntity<Boolean>((false), HttpStatus.OK);
		}
	}

	// deletar Equipe do Evento
	public ResponseEntity<Boolean> delEquipeDoEvento(Integer idEvento, Integer idEquipe){

		Evento evento = eventoRepo.findById(idEvento).get();
		Equipe equipe = equipeRepo.findById(idEquipe).get();

		evento.getEquipes().remove(equipe);
		equipe.setEventos(null);

		eventoRepo.save(evento);
		equipeRepo.save(equipe);
		
		return new ResponseEntity<Boolean>((true), HttpStatus.OK);
		
	}

	// atualiza o Evento // 
	public ResponseEntity<Evento> atulizarEvento(Evento evento) {

		return new ResponseEntity<Evento>(eventoRepo.save(evento), HttpStatus.OK);
}

	//atualizar Status do Evento
	public ResponseEntity<Evento> atulizarStatusEvento(Integer eventoId,Boolean statusEvento) {
		Evento evento = eventoRepo.findById(eventoId).get();
		evento.setStatusEvento(statusEvento);
		return new ResponseEntity<Evento>(eventoRepo.save(evento), HttpStatus.OK);

	}	

	// deleta o Evento 
	public ResponseEntity<?> deleteEvento(Integer idEvento) {
		Evento evento = eventoRepo.findById(idEvento).get();
		eventoRepo.delete(evento);
		//        .orElseThrow(() -> new ResourceNotFoundException("Evento", "idEvento", idEvento));		
		return ResponseEntity.ok().build();

	}
	
	// listar todos as equipes dado um Evento
	public ResponseEntity<List<Equipe>> getTodasEquipeEvento(Integer idEvento){
		Evento evento = eventoRepo.findById(idEvento).get();
		return new ResponseEntity<List<Equipe>>(new ArrayList<Equipe>(evento.getEquipes()), HttpStatus.OK);

	}

	// listar Equipes de um Evento ( Lista com Nome das Equipes Ordenadas por Nome)--- Por nome 
	public ResponseEntity<ArrayList<String>> getTodasEquipeEventoPorNome(Integer idEvento){
		Evento evento = eventoRepo.findById(idEvento).get();
		ArrayList<String> nomeEquipes= new ArrayList<String>();
		
		for(Equipe e:evento.getEquipes()) {
			nomeEquipes.add(e.getNomeEquipe());
			Collections.sort(nomeEquipes);
		}
		return new ResponseEntity<ArrayList<String>>(new ArrayList<String>(nomeEquipes), HttpStatus.OK);

	}
	
	// listar Equipes de um Evento  ---  Por data de inscrição 
//	public ArrayList<String> getTodasEquipeEventoPorData(Integer idEvento){
//		Evento evento = eventoRepo.findById(idEvento).get();
//		ArrayList<String> nomeEquipes = new ArrayList<String>();
//		
//		for(Equipe e:evento.getEquipes()) {
//			nomeEquipes.add(e.getDataDeInscrição());
//			Collections.sort(nomeEquipes);
//		}
//		return nomeEquipes;
//	}


}
