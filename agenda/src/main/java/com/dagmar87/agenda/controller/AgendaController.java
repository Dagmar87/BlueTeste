package com.dagmar87.agenda.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dagmar87.agenda.model.Agenda;
import com.dagmar87.agenda.repository.AgendaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AgendaController {
	
	@Autowired
	AgendaRepository agendaRepository;
	
	@GetMapping("/agendas")
	public ResponseEntity<List<Agenda>> getAllAgendas(@RequestParam(required = false) String nome){
		try {
			List<Agenda> agendas = new ArrayList<Agenda>();
			if(nome == null) {
				agendaRepository.findAll().forEach(agendas::add);
			}
			else {
				agendaRepository.findByNome(nome).forEach(agendas::add);
			}
			if (agendas.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
			return new ResponseEntity<>(agendas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/agendas/{id}")
	public ResponseEntity<Agenda> getAgendaById(@PathVariable("id") long id) {
		Optional<Agenda> agendaData = agendaRepository.findById(id);
		if (agendaData.isPresent()) {
			return new ResponseEntity<>(agendaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/agendas")
	public ResponseEntity<Agenda> createAgenda(@RequestBody Agenda agenda) {
		try {
			Agenda _agenda = agendaRepository
					.save(new Agenda(agenda.getNome(), agenda.getEmail(), agenda.getTelefone()));					
			return new ResponseEntity<>(_agenda, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/agendas/{id}")
	public ResponseEntity<Agenda> updateAgenda(@PathVariable("id") long id, @RequestBody Agenda agenda) {
		Optional<Agenda> agendaData = agendaRepository.findById(id);
		if (agendaData.isPresent()) {
			Agenda _agenda = agendaData.get();
			_agenda.setNome(agenda.getNome());
			_agenda.setEmail(agenda.getEmail());
			_agenda.setTelefone(agenda.getTelefone());
			return new ResponseEntity<>(agendaRepository.save(_agenda), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/agendas/{id}")
	public ResponseEntity<HttpStatus> deleteAgenda(@PathVariable("id") long id) {
		try {
			agendaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/agendas")
	  public ResponseEntity<HttpStatus> deleteAllAgendas() {
	    try {
	      agendaRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
