package br.com.clickbus.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clickbus.entity.State;
import br.com.clickbus.repository.StateRepository;
import br.com.clickbus.simply.SimplyStateName;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public List<String> findAllStatesNames() {
		return stateRepository.findSimplyBy().stream().map(SimplyStateName::getState).collect(Collectors.toList());
	}

	public State saveState(State state) {
		return stateRepository.save(state);
	}

}
