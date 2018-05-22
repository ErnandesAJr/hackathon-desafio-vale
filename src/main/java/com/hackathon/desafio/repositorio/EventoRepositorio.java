package com.hackathon.desafio.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackathon.desafio.bean.Evento;



@Repository
@Transactional
public interface EventoRepositorio extends JpaRepository<Evento,Integer> {

	Evento findByNomeEvento(String nomeEvento);
	
	@Query("from Evento e where e.statusEvento == 'true'")
	List<Evento> pegarTodosEventosAberto();
	
//	@Query("Select equipes from Evento ORDER BY nome ASC")
//	List<Equipe> getListEquipeOrdenandaNome();
//	
//	@Query("Select equipes from Evento ORDER BY dataDeInscricao ASC")
//	List<Equipe> getListEquipeOrdenandaData();
	
}
