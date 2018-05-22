package com.hackathon.desafio.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hackathon.desafio.bean.Membro;
import com.hackathon.desafio.repositorio.MembroRepositorio;


@Service
public class MembroService {

	@Autowired
	MembroRepositorio  membroRepo;

	//salva membro -- cada equipe so pode ter um membro daquele
	public ResponseEntity<Membro> salvarMembro(Membro membro){

//		Membro membro = new Membro();
		//boolean seEquipetemEsseMembro = false;

		//verifica se o membro ja esta na Equipe
		//		for(Membro m:equipe.getMembros()) {
		//			if(nomeCompletoMembro == m.getNomeCompletoMembro() || emailMembro == m.getEmailMembro()) {
		//				seEquipetemEsseMembro = true;
		//			}
		//		}

		//		if(seEquipetemEsseMembro == false) {
		//
//		membro.setEmailMembro(emailMembro);
		//			membro.setEquipe(equipe);
//		membro.setNomeCompletoMembro(nomeCompletoMembro);
//		membro.setTelefoneMembro(telefoneMembro);
//		membro.setTamCamisaMembro(tamCamisaMembro);
		//			return true;
		//		}

		return new ResponseEntity<Membro>(membroRepo.save(membro), HttpStatus.OK);

	}

	//pegar Membro
	public ResponseEntity<Membro> getMembro(Integer idMembro){
		return new ResponseEntity<Membro>(membroRepo.findById(idMembro).get(), HttpStatus.OK);

	}
	
	//pegar todos os membros
	public ResponseEntity<List<Membro>> getTodosMembros(){
		
		return new ResponseEntity<List<Membro>>(new ArrayList<Membro>(membroRepo.findAll()), HttpStatus.OK);
		
	}
	
	
	//atulizar Membro 
	public ResponseEntity<Membro> atulizarMembro(Membro membro) {
//		Membro membro = membroRepo.findById(idMembro).get();
//		//	            .orElseThrow(() -> new ResourceNotFoundException("Membro", "idMembro", idMembro));
//
//		membro.setEmailMembro(emailMembro);
//		membro.setEquipe(equipe);
//		membro.setNomeCompletoMembro(nomeCompletoMembro);
//		membro.setTamCamisaMembro(tamCamisaMembro);
//		membro.setTelefoneMembro(telefoneMembro);


		return new ResponseEntity<Membro>(membroRepo.save(membro), HttpStatus.OK);

	}


	//deletar Membro
	public ResponseEntity<?> deleteMembro(Integer idMembro) {
		Membro membro = membroRepo.findById(idMembro).get();
		membroRepo.delete(membro);
		//        .orElseThrow(() -> new ResourceNotFoundException("Evento", "idEvento", idEvento));		
		return ResponseEntity.ok().build();

	}



}
