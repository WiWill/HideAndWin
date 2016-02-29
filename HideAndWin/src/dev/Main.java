package dev;

import dev.graphics.Game;

public class Main {

	public static void main(String[] args) {
		int width = 1024;
		int height = 768;
		int fps = 60;
		Thread t;
		
		t = new Thread(new Game(width, height, fps));
		t.start();
	}

}
