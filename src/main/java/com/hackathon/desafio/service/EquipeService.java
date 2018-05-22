package com.hackathon.desafio.service;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackathon.desafio.bean.Equipe;
import com.hackathon.desafio.bean.Membro;
import com.hackathon.desafio.repositorio.EquipeRepositorio;
import com.hackathon.desafio.repositorio.MembroRepositorio;


@Service
public class EquipeService {

	@Autowired
	EquipeRepositorio  equipeRepo;

	@Autowired
	MembroRepositorio  membroRepo;

	// posso criar uma equipe sem Evento , para que eu possa escrever essa equipe em varios Eventos diferentes
	public ResponseEntity<Equipe> salvarEquipe(Equipe equipe){

		return new ResponseEntity<Equipe>(equipeRepo.save(equipe), HttpStatus.OK);
	}

	//listar todas as equipes
	public ResponseEntity<List<Equipe>> getTodasEquipes(){
		return new ResponseEntity<List<Equipe>>(new ArrayList<Equipe>(this.equipeRepo.findAll()), HttpStatus.OK);
	}

	//pegar todas as equipe que nao tem Eventos
	public ResponseEntity<List<Equipe>> getEquipeSemEvento(){
		return new ResponseEntity<List<Equipe>>(new ArrayList<Equipe>(this.equipeRepo.buscarEquipeSemEvento()), HttpStatus.OK);

	}


	//deletar uma equipe	
	public ResponseEntity<Boolean> deleteEquipe(Integer idEquipe) {
		Equipe equipe = equipeRepo.findById(idEquipe).get();

		equipeRepo.delete(equipe);
		//        .orElseThrow(() -> new ResourceNotFoundException("Evento", "idEvento", idEvento));		
		return ResponseEntity.ok().build();

	}

	//atualizar Equipe
	public ResponseEntity<?> atulizarEquipe(Equipe equipe) {
		//		Equipe equipe = equipeRepo.findById(idEquipe).get();
		//		//	            .orElseThrow(() -> new ResourceNotFoundException("Evento", "idEvento", idEvento));
		//
		//		equipe.setEventos(eventos);
		//		equipe.setMembros(membros);
		//		equipe.setNomeEquipe(nomeEquipe);

		return new ResponseEntity<Equipe>(equipeRepo.save(equipe), HttpStatus.OK);
	}

	// adicionar Membro a Equipe
	public ResponseEntity<Boolean> addMembroAEquipe(Integer idMembro, Integer idEquipe){

		Boolean membroEmEquipe = false;
		Equipe equipe = equipeRepo.findById(idEquipe).get();
		Membro membro = membroRepo.findById(idMembro).get();


		// verifica se a Membro ja esta no Equipe
		for(Membro m:equipe.getMembros()) {

			if(m.getId() == membro.getId()) {
				membroEmEquipe = true;
			}

		}
		if(membroEmEquipe == false) {
			equipe.getMembros().add(membro);
			membro.setEquipe(equipe);
			membroRepo.save(membro);
			equipeRepo.save(equipe);
			return new ResponseEntity<Boolean>((true), HttpStatus.OK);

		}
		//return "Membro já está na Equipe";
		return new ResponseEntity<Boolean>((false), HttpStatus.OK);
	}

	// deletar Membro do Equipe
	public ResponseEntity<Boolean>  delMembroDoEquipe(Integer idMembro, Integer idEquipe){

		Membro membro = membroRepo.findById(idMembro).get();
		Equipe equipe = equipeRepo.findById(idEquipe).get();

		equipe.getMembros().remove(membro);
		membro.setEquipe(null);
		//
		membroRepo.delete(membro);
		equipeRepo.save(equipe);

		return new ResponseEntity<Boolean>((true), HttpStatus.OK);
	}
	// listar todos os membros dessa equipe
	public ResponseEntity<List<Membro>> getTodasMembroEquipe(Integer idEquipe){
		Equipe equipe = equipeRepo.findById(idEquipe).get();

		return new ResponseEntity<List<Membro>>(new ArrayList<Membro>(equipe.getMembros()), HttpStatus.OK);
	}

}
