package dev.graphics;

import dev.util.fps.Timer;

public class Game implements Runnable {
	private int width;
	private int height;
	private boolean continuer = false;
	private Timer timer;
	
	public Game(int width, int height, int fps){
		this.width = width;
		this.height = height;
		this.continuer = true;
		this.timer = new Timer(fps);
	}

	@Override
	public void run() {
		init();
		while(continuer){
			this.timer.setAvantBoucle(System.currentTimeMillis());
			
			update();
			render();
			
			this.timer.setApresBoucle(System.currentTimeMillis());
			this.timer.update();
		}
	}
	
	private void init(){
		
	}
	
	private void update(){
		
	}
	
	private void render(){
		
	}

}
