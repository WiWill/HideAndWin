package dev.util.hitbox;

public interface IHitbox {
	
	public boolean collision(IHitbox box);
	
	public boolean collisionWithCircle(CircleHitbox box);
}
