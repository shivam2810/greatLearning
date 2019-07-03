package com.operative.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "board")
@Getter
@Setter
public class Board extends MongoEntity {
	@Id
	String bId;
	char boardArray[][];
	Player player1;
	Player player2;
	Player playerChance;

	public Board() {
		// Initially the board is empty
		boardArray = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				boardArray[i][j] = ' ';
			}
		}
	}

	@Override
	public String getId() {
		return bId;
	}

}
