package com.buddha.game;

import java.awt.Dimension;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public enum State {
	NONEXISTENT(""), LIVE("██"), DEAD("  ");

	public static State getRandomState() {
		if (Math.round(Math.random()) == 1)
			return LIVE;

		return DEAD;
	}
	
	private String value;

    State(String value) {
        this.value = value;
    }

	@Override
	public String toString() {
		
		return value;
	}
	
	
}
