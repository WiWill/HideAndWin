package dev.util.hitbox;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import dev.util.geom.Point2D;
import dev.util.graphics.GraphicsUtils;

public class CircleHitbox implements IHitbox {
	private Point2D centre;
	private int rayon;
	
	public CircleHitbox(Point2D centre, int rayon){
		this.centre = centre;
		this.rayon = rayon;
	}
	
	public CircleHitbox(int x, int y, int rayon){
		this(new Point2D(x, y), rayon);
	}

	@Override
	public boolean collision(IHitbox box) {
		if(box instanceof CircleHitbox){
			return collisionWithCircle((CircleHitbox) box);
		}
		return false;
	}
	
	@Override
	public boolean collisionWithCircle(CircleHitbox box) {
		return this.centre.distance(box.getCentre()) 
				<= this.rayon + box.getRayon();
	}
	
	@Override
	public void render(int width, int height) {
		//haut gauche
				int x1 = this.centre.getX() - this.rayon / 2;
				int y1 = this.centre.getY() + this.rayon / 2;
				
				//haut droite
				int x2 = this.centre.getX() + this.rayon / 2;
				int y2 = this.centre.getY() + this.rayon / 2;
				
				//bas gauche
				int x3 = this.centre.getX() - this.rayon / 2;
				int y3 = this.centre.getY() - this.rayon / 2;
				
				//bas droite
				int x4 = this.centre.getX() + this.rayon / 2;
				int y4 = this.centre.getY() - this.rayon / 2;
				
				glBegin(GL_TRIANGLES);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x1, width), GraphicsUtils.convertPixelToOpenGLDimension(y1, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x4, width), GraphicsUtils.convertPixelToOpenGLDimension(y4, height), 1.0f);
				glEnd();
	}

	public Point2D getCentre() {
		return centre;
	}

	public int getRayon() {
		return rayon;
	}

	public void setCentre(Point2D centre) {
		this.centre = centre;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
}
