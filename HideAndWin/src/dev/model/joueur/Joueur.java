package dev.model.joueur;

import dev.util.geom.Point2D;
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

	public IHitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(IHitbox hitbox) {
		this.hitbox = hitbox;
	}
}
