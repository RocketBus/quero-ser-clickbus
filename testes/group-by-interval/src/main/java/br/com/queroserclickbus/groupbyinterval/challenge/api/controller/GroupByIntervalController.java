package br.com.queroserclickbus.groupbyinterval.challenge.api.controller;

import br.com.queroserclickbus.groupbyinterval.challenge.domain.service.GroupByIntervalService;
import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberInput;
import br.com.queroserclickbus.groupbyinterval.challenge.dto.CollectionNumberOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/group-by-interval", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupByIntervalController {

	@Autowired
	GroupByIntervalService groupByIntervalService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity postController(@RequestBody CollectionNumberInput collectionNumberInput) {
		CollectionNumberOutput response = groupByIntervalService.
				process(collectionNumberInput.getNumberSet(), collectionNumberInput.getRange());

		return ResponseEntity.ok(response);
	}
}
