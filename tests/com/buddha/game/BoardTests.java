package com.buddha.game;

import static org.junit.Assert.*;
import static com.buddha.game.State.*;

import org.junit.Test;

public class BoardTests {

	@Test
	public void testBoard() throws InterruptedException {
		Board board = new Board(10, 5);
		assertFalse(new Board(5,5).equals(board));
		assertTrue(board.equals(board));
		
				
		board.print();
//		new board(5,5).print();
//		new board(5,5).print();
	}

	@Test
	public void testGetLiveNeighbours() {
		State[][] stateArray1 = {{LIVE,LIVE,DEAD},
								 {DEAD,LIVE,DEAD},
								 {LIVE,DEAD,LIVE}};
		
		Board board = new Board(stateArray1);
		
		assertTrue(board.getLiveNeighbours(0, 0)==2);
		assertTrue(board.getLiveNeighbours(0, 1)==4);
		assertTrue(board.getLiveNeighbours(0, 2)==1);
		assertTrue(board.getLiveNeighbours(1, 0)==2);
		assertTrue(board.getLiveNeighbours(1, 1)==4);
		assertTrue(board.getLiveNeighbours(1, 2)==3);
		assertTrue(board.getLiveNeighbours(2, 0)==2);
		assertTrue(board.getLiveNeighbours(2, 1)==3);
		assertTrue(board.getLiveNeighbours(2, 2)==1);
		
	}

	@Test
	public void testGetNextGen() {
		State[][] stateArray1 = {{LIVE,LIVE,DEAD},
								 {DEAD,LIVE,DEAD},
								 {LIVE,DEAD,LIVE}};
		
		State[][] nextGen1 = {{LIVE,LIVE,DEAD},
							  {DEAD,DEAD,LIVE},
							  {DEAD,LIVE,DEAD}};
		
		State[][] stateArray2 = {{LIVE,LIVE,DEAD,LIVE},
								 {DEAD,DEAD,DEAD,DEAD},
								 {LIVE,DEAD,LIVE,LIVE}};

		State[][] nextGen2 = {{DEAD,DEAD,DEAD,DEAD},
							  {LIVE,DEAD,DEAD,LIVE},
							  {DEAD,DEAD,DEAD,DEAD}};

		
		Board board = new Board(stateArray1);
		assertTrue(board.getNextGen().equals(new Board(nextGen1)));
		
		board = new Board(stateArray2);
		assertTrue(board.getNextGen().equals(new Board(nextGen2)));
	}

}
