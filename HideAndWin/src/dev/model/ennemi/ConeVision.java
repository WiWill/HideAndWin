package dev.model.ennemi;

import static org.lwjgl.opengl.GL11.*;
import dev.util.math.Vecteur4f;

public class ConeVision implements IVision {
	private Vecteur4f origine;
	private double direction;
	private double angle;
	private int distance;
	private int nbDecoupageTriangle;
	
	public ConeVision(Vecteur4f origine, double direction, double angle, int distance){
		this.origine = origine;
		this.direction = direction;
		this.angle = angle;
		this.distance = distance;
	}
	
	public ConeVision(float x, float y, float z, double direction, double angle, int distance){
		this(new Vecteur4f(x, y, z, 1), direction, angle, distance);
	}
	
	public void rotation(double angle){
		this.angle += angle;
	}
	
	@Override
	public void render(int width, int height){
		int x1 = (int) this.origine.getX();
		int y1 = (int) this.origine.getY();
		int x2 = (int) (this.distance * Math.cos(Math.toRadians(this.direction + this.angle)) + this.origine.getX());
		int y2 = (int) (this.distance * Math.sin(Math.toRadians(this.direction + this.angle)) + this.origine.getY());
		int x3 = (int) (this.distance * Math.cos(Math.toRadians(this.direction - this.angle)) + this.origine.getX());
		int y3 = (int) (this.distance * Math.sin(Math.toRadians(this.direction - this.angle)) + this.origine.getY());
		
		/*glBegin(GL_TRIANGLES);
		glColor3f(0.0f, 1.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x1, width), GraphicsUtils.convertPixelToOpenGLDimension(y1, height), 1.0f);
		glColor3f(0.0f, 1.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
		glColor3f(0.0f, 1.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
		glEnd();*/
	}

	public Vecteur4f getOrigine() {
		return origine;
	}

	public void setOrigine(Vecteur4f origine) {
		this.origine = origine;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getDistance() {
		return distance;
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
