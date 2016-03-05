package dev.util.hitbox;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import dev.util.math.Vecteur4f;

public class CircleHitbox implements IHitbox {
	private Vecteur4f centre;
	private int rayon;
	
	public CircleHitbox(Vecteur4f centre, int rayon){
		this.centre = centre;
		this.rayon = rayon;
	}
	
	public CircleHitbox(float x, float y, float z, int rayon){
		this(new Vecteur4f(x, y, z, 1), rayon);
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
				int x1 = (int) this.centre.getX() - this.rayon / 2;
				int y1 = (int) this.centre.getY() + this.rayon / 2;
				
				//haut droite
				int x2 = (int) this.centre.getX() + this.rayon / 2;
				int y2 = (int) this.centre.getY() + this.rayon / 2;
				
				//bas gauche
				int x3 = (int) this.centre.getX() - this.rayon / 2;
				int y3 = (int) this.centre.getY() - this.rayon / 2;
				
				//bas droite
				int x4 = (int) this.centre.getX() + this.rayon / 2;
				int y4 = (int) this.centre.getY() - this.rayon / 2;
				
				/*glBegin(GL_TRIANGLES);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x1, width), GraphicsUtils.convertPixelToOpenGLDimension(y1, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
				glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x4, width), GraphicsUtils.convertPixelToOpenGLDimension(y4, height), 1.0f);
				glEnd();*/
	}

	public Vecteur4f getCentre() {
		return centre;
	}

	public void setCentre(Vecteur4f centre) {
		this.centre = centre;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
}
