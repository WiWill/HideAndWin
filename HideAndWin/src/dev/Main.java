package dev;

import dev.graphics.Game;

public class Main {

	public static void main(String[] args) {
		int width = 1024;
		int height = 768;
		Thread t;
		
		t = new Thread(new Game(width, height));
		t.start();
	}

}
