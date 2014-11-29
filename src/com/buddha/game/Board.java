package com.buddha.game;

import static com.buddha.game.State.*;

public class Board implements World {

	private State[][] _grid;
	private State[][] _tempgrid;
	private int _width, _height;
	
	static String lineEnd = System.lineSeparator();

	int getHeight() {
		return _height;
	}

	int getWidth() {
		return _width;
	}

	public Board(State[][] stateArray) {
		_width = stateArray[0].length;
		_height = stateArray.length;
		_grid = new State[_height][_width];
		_tempgrid = new State[_height][_width];
		for (int y = 0; y < _height; y++)
			for (int x = 0; x < _width; x++)
				_grid[y][x] = stateArray[y][x];
	}

	public Board(int width, int height) {
		_grid = new State[height][width];
		_tempgrid = new State[height][width];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				_grid[y][x] = getRandomState();
		_width = width;
		_height = height;
	}

	@Override
	public int getLiveNeighbours(int x, int y) {
		int liveNeighbourCount = 0;

		if (getCellState(x - 1,y) == LIVE)
			liveNeighbourCount++;
		if (getCellState(x - 1,y + 1) == LIVE)
			liveNeighbourCount++;
		if (getCellState(x - 1,y - 1) == LIVE)
			liveNeighbourCount++;
		if (getCellState(x,y - 1) == LIVE)
			liveNeighbourCount++;
		if (getCellState(x,y + 1) == LIVE)
			liveNeighbourCount++;
		if (getCellState(x + 1,y + 1) == LIVE)
			liveNeighbourCount++;
		if (getCellState(x + 1,y) == LIVE)
			liveNeighbourCount++;
		if (getCellState(x + 1,y - 1) == LIVE)
			liveNeighbourCount++;

		return liveNeighbourCount;
	}

	@Override
	public World getNextGen() {
		int liveNeighbours = 0;
		State state;
		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				liveNeighbours = getLiveNeighbours(x, y);
				state = _grid[y][x];
				if (state == DEAD && liveNeighbours == 3)
					_tempgrid[y][x] = LIVE;
				else if (state != DEAD && (liveNeighbours == 2 || liveNeighbours == 3))
					_tempgrid[y][x] = LIVE;
				else
					_tempgrid[y][x] = DEAD;
			}
		}

		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				_grid[y][x] = _tempgrid[y][x];
			}
		}

		return this;
	}

	@Override
	public State getCellState(int x, int y) {

		if (x < 0 || x >= _width || y < 0 || y >= _height)
			return NONEXISTENT;

		return _grid[y][x];
	}

	@Override
	public void setCellState(int x, int y, State state) {
		if (getCellState(x, y) != NONEXISTENT)
			_grid[y][x] = state;
	}

	@Override
	public void print() {
		System.out.println(toString());

	}
	
	@Override
	public String toString(){
		
		StringBuffer string = new StringBuffer();
		for (State[] row : _grid) {
		//	string.append("|");
			for (State cell : row)
				string.append(cell);
			string.append(lineEnd);
		}
		return string.toString();
	}

	@Override
	public boolean equals(Object board) {
		Board castedBoard = (Board) board;

		if (_width != castedBoard.getWidth() || _height != castedBoard.getHeight())
			return false;

		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				if (_grid[y][x] != castedBoard.getCellState(x, y))
					return false;
			}
		}
		return true;
	}

}
