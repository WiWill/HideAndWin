package dev.model.ennemi;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import dev.util.graphics.GraphicsUtils;
import dev.util.hitbox.CircleHitbox;
import dev.util.hitbox.IHitbox;

public class Ennemi {
	private IHitbox hitbox;
	private IVision vision;
	
	public Ennemi(IHitbox hitbox, IVision vision){
		this.hitbox = hitbox;
		this.vision = vision;
	}
	
	public void render(int width, int height){
		if(this.hitbox instanceof CircleHitbox){
			this.renderHitbox(width, height);
		}
		if(this.vision instanceof ConeVision){
			((ConeVision) vision).render(width, height);
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
		glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x1, width), GraphicsUtils.convertPixelToOpenGLDimension(y1, height), 1.0f);
		glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
		glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
		glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x3, width), GraphicsUtils.convertPixelToOpenGLDimension(y3, height), 1.0f);
		glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x2, width), GraphicsUtils.convertPixelToOpenGLDimension(y2, height), 1.0f);
		glColor3f(1.0f, 0.0f, 0.0f); glVertex3f(GraphicsUtils.convertPixelToOpenGLDimension(x4, width), GraphicsUtils.convertPixelToOpenGLDimension(y4, height), 1.0f);
		glEnd();
	}

	public IHitbox getHitbox() {
		return hitbox;
	}

	public IVision getVision() {
		return vision;
	}

	public void setHitbox(IHitbox hitbox) {
		this.hitbox = hitbox;
	}

	public void setVision(IVision vision) {
		this.vision = vision;
	}
}
