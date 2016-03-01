package dev.model.joueur;

import static org.lwjgl.opengl.GL11.*;
import dev.util.geom.Point2D;
import dev.util.graphics.GraphicsUtils;
import dev.util.hitbox.CircleHitbox;
import dev.util.hitbox.IHitbox;

public class Joueur {
	private IHitbox hitbox;
	
	public Joueur(IHitbox hitbox){
		this.hitbox = hitbox;
	}
	
	public Joueur(int x, int y, int rayonCircleHitbox){
		this.hitbox = new CircleHitbox(x, y, rayonCircleHitbox);
	}
	
	public Joueur(Point2D position, int rayonCircleHitbox){
		this.hitbox = new CircleHitbox(position, rayonCircleHitbox);
	}
	
	public void render(int width, int height){
		if(this.hitbox instanceof CircleHitbox){
			this.renderHitbox(width, height);
		}
	}
	
	private void renderHitbox(int width, int height){
		CircleHitbox c = (CircleHitbox) this.hitbox;
		//haut gauche
		int x1 = c.getCentre().getX() - c.getRayon() / 2;
		int y1 = c.getCentre().getY() + c.getRayon() / 2;
		
		//haut droite
		int x2 = c.getCentre().getX() + c.getRayon() / 2;
		int y2 = c.getCentre().getY() + c.getRayon() / 2;
		
		//bas gauche
		int x3 = c.getCentre().getX() - c.getRayon() / 2;
		int y3 = c.getCentre().getY() - c.getRayon() / 2;
		
		//bas droite
		int x4 = c.getCentre().getX() + c.getRayon() / 2;
		int y4 = c.getCentre().getY() - c.getRayon() / 2;
		
		glBegin(GL_TRIANGLES);
		glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x1, width), GraphicsUtils.convertPixelToOpenGLDimension(y1, height), 1.0f);
		glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
		glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
		glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
		glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
		glColor3f(0.0f, 0.0f, 1.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x4, width), GraphicsUtils.convertPixelToOpenGLDimension(y4, height), 1.0f);
		glEnd();
	}

	public IHitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(IHitbox hitbox) {
		this.hitbox = hitbox;
	}
}
