package br.com.clickbus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clickbus.dto.CreateStateDto;
import br.com.clickbus.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping
	public List<String> findAllStatesNames() {
		return stateService.findAllStatesNames();
	}
	
	@PostMapping
	public ResponseEntity<Void> saveState(CreateStateDto stateDto) {
		stateService.saveState(stateDto.buildState());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
}
