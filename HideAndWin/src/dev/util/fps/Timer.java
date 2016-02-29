package dev.util.fps;

public class Timer {
	private int fps;
	private int milis;
	private long avantBoucle;
	private long apresBoucle;
	private long derniereSeconde;
	
	public Timer(int fpsVoulu){
		this.milis = (int) (1000.0f / (float) fpsVoulu);
		this.derniereSeconde = System.currentTimeMillis();
	}
	
	public void setAvantBoucle(long l){
		this.avantBoucle = l;
	}
	
	public void setApresBoucle(long l){
		this.apresBoucle = l;
	}
	
	public void update(){
		long t = System.currentTimeMillis();
		long sleep;
		
		if(t - this.derniereSeconde >= 1000){
			System.out.println(fps);
			fps = 0;
			this.derniereSeconde = t;
		} else {
			fps++;
		}
		sleep = apresBoucle - avantBoucle <= milis ? this.milis - (apresBoucle - avantBoucle) : 0;
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
