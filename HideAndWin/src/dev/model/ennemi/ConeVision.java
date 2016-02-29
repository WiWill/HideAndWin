package dev.model.ennemi;

import dev.util.geom.Point2D;

public class ConeVision implements IVision {
	private Point2D origine;
	private double direction;
	private double angle;
	private int distance;
	private int nbDecoupageTriangle;
	
	public ConeVision(Point2D origine, double direction, double angle, int distance){
		this.origine = origine;
		this.direction = direction;
		this.angle = angle;
		this.distance = distance;
	}
	
	public ConeVision(int x, int y, double direction, double angle, int distance){
		this(new Point2D(x, y), direction, angle, distance);
	}
	
	public void rotation(double angle){
		this.angle += angle;
	}

	public Point2D getOrigine() {
		return origine;
	}

	public double getDirection() {
		return direction;
	}

	public double getAngle() {
		return angle;
	}

	public int getDistance() {
		return distance;
	}

	public void setOrigine(Point2D origine) {
		this.origine = origine;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getNbDecoupageTriangle() {
		return nbDecoupageTriangle;
	}

	public void setNbDecoupageTriangle(int nbDecoupageTriangle) {
		this.nbDecoupageTriangle = nbDecoupageTriangle;
	}
}
