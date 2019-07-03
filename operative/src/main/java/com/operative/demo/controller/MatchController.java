package com.operative.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.operative.demo.exception.BoardException;
import com.operative.demo.model.Player;
import com.operative.demo.request.StateChangeRequest;
import com.operative.demo.response.BoardResponse;
import com.operative.demo.response.StateChangeResponse;
import com.operative.demo.service.BoardService;

@RestController
@RequestMapping("/v1/match")
public class MatchController {
	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<BoardResponse> createBoard(@RequestBody Player player) {
		final BoardResponse saveBoard = boardService.create(player);
		return new ResponseEntity<BoardResponse>(saveBoard, HttpStatus.OK);
	}

	@RequestMapping(value = "/stateChange", method = RequestMethod.POST)
	public ResponseEntity<StateChangeResponse> stateChange(@RequestBody StateChangeRequest stateChangeRequest)
			throws BoardException {
		final StateChangeResponse saveBoard = boardService.stateChange(stateChangeRequest);
		return new ResponseEntity<StateChangeResponse>(saveBoard, HttpStatus.OK);
	}
}
