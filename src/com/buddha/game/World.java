package com.buddha.game;

public interface World {
	public int getLiveNeighbours(int x, int y);
	public World getNextGen();
	public State getCellState(int x, int y);
	public void setCellState(int x, int y, State state);
	public void print();
}
