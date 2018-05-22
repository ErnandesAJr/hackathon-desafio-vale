package com.hackathon.desafio.repositorio;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.desafio.bean.Membro;


@Repository
@Transactional
public interface MembroRepositorio extends JpaRepository<Membro,Integer>{
	
	Membro findByNomeCompletoMembro(String nomeCompletoMembro);
	
}
