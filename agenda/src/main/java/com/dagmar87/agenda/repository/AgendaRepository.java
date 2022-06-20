package com.dagmar87.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dagmar87.agenda.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
	List<Agenda> findByNome(String nome);	

}
