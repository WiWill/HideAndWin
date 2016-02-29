package dev.model.ennemi;

import dev.util.hitbox.IHitbox;

public class Ennemi {
	private IHitbox hitbox;
	private IVision vision;
	
	public Ennemi(IHitbox hitbox, IVision vision){
		this.hitbox = hitbox;
		this.vision = vision;
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
