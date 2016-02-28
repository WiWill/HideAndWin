package util;

public class Point2D {
	private int x;
	private int y;
	
	public Point2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point2D p){
		return Math.sqrt(Math.pow(p.getX() - this.x, 2) + Math.pow(p.getY() - this.y, 2));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}