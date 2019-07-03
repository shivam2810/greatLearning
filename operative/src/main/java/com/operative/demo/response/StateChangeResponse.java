package com.operative.demo.response;

import com.operative.demo.model.Board;
import com.operative.demo.model.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateChangeResponse {
	Board board;
	Player chanceTo;
}
