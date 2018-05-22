package com.hackathon.desafio.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.hackathon.desafio.bean.Equipe;
import com.hackathon.desafio.bean.Membro;
import com.hackathon.desafio.service.MembroService;

@Controller
@RequestMapping(path = "/membro/")
public class MembroController {

	@Autowired
	MembroService membroService;



	// salva Membro 
	// para criar um membro sem equipe ... para depois adicionar ele na equipe ... 
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ResponseEntity<Membro> salvarMembro(@RequestParam String nomeCompletoMembro, @RequestParam String emailMembro,
			@RequestParam String telefoneMembro,@RequestParam String tamCamisaMembro){
		
//		boolean itsOk = membroService.salvarMembro(nomeCompletoMembro, emailMembro, telefoneMembro, tamCamisaMembro);
		return membroService.salvarMembro(nomeCompletoMembro, emailMembro, telefoneMembro, tamCamisaMembro);
//		
//		if(itsOk ==  false) {
//			String erro = "Membro já está na equipe"; //so pode ter um membro daquele por equipe
//			model.addObject("erro", erro);
//		}
		

	}
	
	// atualizar Membro
	@RequestMapping(path="/atualizarMembro/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Membro> atualizarMembro(@PathVariable(value = "id") Integer idMembro, @RequestParam String nomeCompletoMembro, @RequestParam String emailMembro,
			@RequestParam String telefoneMembro,@RequestParam String tamCamisaMembro, Equipe equipe) {

	    return membroService.atulizarMembro(idMembro, nomeCompletoMembro, emailMembro, telefoneMembro, tamCamisaMembro, equipe);
	
	}
	
	//deletar um membro
	@RequestMapping(path="/membros/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMembro(@PathVariable(value = "id") Integer idMembro) {

		membroService.deleteMembro(idMembro);
	    return ResponseEntity.ok().build();
	}
	
	//listar Todos os Membros
	@RequestMapping(path="/todosMembros", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Membro>> getAllMembros() {
	    return membroService.getTodosMembros();
	}
	
	

}
