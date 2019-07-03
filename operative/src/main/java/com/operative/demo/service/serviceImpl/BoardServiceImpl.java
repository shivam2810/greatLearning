package com.operative.demo.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operative.demo.exception.BoardException;
import com.operative.demo.model.Board;
import com.operative.demo.model.Player;
import com.operative.demo.queryBuilder.BoardQueryBuilder;
import com.operative.demo.repository.BoardRepository;
import com.operative.demo.request.StateChangeRequest;
import com.operative.demo.response.BoardResponse;
import com.operative.demo.response.StateChangeResponse;
import com.operative.demo.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository boardRepository;

	@Override
	public synchronized BoardResponse create(Player player) {
		final BoardResponse boardResponse = new BoardResponse();
		final BoardQueryBuilder boardQueryBuilder = new BoardQueryBuilder();
		boardQueryBuilder.player2Id();
		final Board board = boardRepository.findOne(boardQueryBuilder.toQuery());
		if (board != null && !board.getPlayer1().equals(player)) {
			board.setPlayer2(player);
			boardRepository.save(board);
			boardResponse.setBoard(board);
			return boardResponse;
		} else {
			final Board newBoard = new Board();
			newBoard.setPlayer1(player);
			final Board addedBoard = boardRepository.save(newBoard);
			boardResponse.setBoard(addedBoard);
		}
		return boardResponse;
	}

	@Override
	public StateChangeResponse stateChange(StateChangeRequest stateChangeRequest) throws BoardException {
		final BoardQueryBuilder boardQueryBuilder = new BoardQueryBuilder();
		boardQueryBuilder.boardId(stateChangeRequest.getBId());
		final Board board = boardRepository.findOne(boardQueryBuilder.toQuery());
		char valueInArray;
		if (stateChangeRequest.getPId().equals(board.getPlayer1().getPId())) {
			valueInArray = '1';
		} else {
			valueInArray = '2';
		}
		final char[][] boardArray = board.getBoardArray();
		boardArray[stateChangeRequest.getMove().getRow()][stateChangeRequest.getMove().getColumn()] = valueInArray;
		if (gameOver(boardArray)) {
			boardRepository.save(board);
			throw new BoardException("Board is Over" + stateChangeRequest.getPId() + "won the game");
		} else {
			boardRepository.save(board);
		}
		final StateChangeResponse stateChangeResponse = new StateChangeResponse();
		stateChangeResponse.setBoard(board);
		stateChangeResponse.setChanceTo(valueInArray == '1' ? board.getPlayer1() : board.getPlayer2());
		return stateChangeResponse;

	}

	// A function that returns true if any of the row
	// is crossed with the same player's move
	private boolean rowCrossed(char board[][]) {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return (true);
			}
		}
		return (false);
	}

	// A function that returns true if any of the column
	// is crossed with the same player's move
	private boolean columnCrossed(char board[][]) {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
				return (true);
			}
		}
		return (false);
	}

	// A function that returns true if any of the diagonal
	// is crossed with the same player's move
	private boolean diagonalCrossed(char board[][]) {
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return (true);
		}

		if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
			return (true);
		}

		return (false);
	}

	private boolean gameOver(char board[][]) {
		return (rowCrossed(board) || columnCrossed(board) || diagonalCrossed(board));
	}

}
