package com.operative.demo.request;

import com.operative.demo.model.Move;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateChangeRequest {
	String bId;
	String pId;
	Move move;
}
