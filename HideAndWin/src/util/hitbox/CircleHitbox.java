package util.hitbox;

import util.Point2D;

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
