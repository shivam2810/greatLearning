package com.operative.demo.service;

import com.operative.demo.exception.BoardException;
import com.operative.demo.model.Player;
import com.operative.demo.request.StateChangeRequest;
import com.operative.demo.response.BoardResponse;
import com.operative.demo.response.StateChangeResponse;

public interface BoardService {

	BoardResponse create(Player player);

	StateChangeResponse stateChange(StateChangeRequest stateChangeRequest) throws BoardException;

}
