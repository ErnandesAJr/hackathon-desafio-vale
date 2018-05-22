package com.hackathon.desafio.repositorio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackathon.desafio.bean.Equipe;


@Repository
@Transactional
public interface EquipeRepositorio extends JpaRepository<Equipe,Integer> {
	
	Equipe findBynomeEquipe (String nomeEquipe);

	@Query("from Equipe e where e.evento is null")
	List<Equipe> buscarEquipeSemEvento();

	
	



}
