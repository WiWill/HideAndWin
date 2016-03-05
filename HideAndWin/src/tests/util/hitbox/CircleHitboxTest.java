package tests.util.hitbox;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dev.util.hitbox.CircleHitbox;

public class CircleHitboxTest extends IHitboxTest {
	
	@Before
	public void doBefore(){
		this.hitbox = new CircleHitbox(0, 0, 0, 10);
	}
	
	@Test
	public void testRG1_0(){
		//Param
		final int x = 500;
		final int y = 500;
		final int z = 0;
		final int rayon = 10;
		CircleHitbox box = new CircleHitbox(x, y, z, rayon);
		
		//Resultat attendu
		final boolean result = false;
		
		//Test
		assertEquals(result, this.hitbox.collisionWithCircle(box));
	}
	
	@Test
	public void testRG1_1(){
		//Param
		final int x = 0;
		final int y = 20;
		final int rayon = 10;
		CircleHitbox box = new CircleHitbox(x, y, 0, rayon);
		
		//Resultat attendu
		final boolean result = true;
		
		//Test
		assertEquals(result, this.hitbox.collisionWithCircle(box));
	}
	
	@Test
	public void testRG1_2(){
		//Param
		final int x = 0;
		final int y = 10;
		final int rayon = 10;
		CircleHitbox box = new CircleHitbox(x, y, 0, rayon);
		
		//Resultat attendu
		final boolean result = true;
		
		//Test
		assertEquals(result, this.hitbox.collisionWithCircle(box));
	}
	
	@Test
	public void testRG1_3(){
		//Param
		final int x = 0;
		final int y = 500;
		final int rayon = 490;
		CircleHitbox box = new CircleHitbox(x, y, 0, rayon);
		
		//Resultat attendu
		final boolean result = true;
		
		//Test
		assertEquals(result, this.hitbox.collisionWithCircle(box));
	}
	
	@Test
	public void testRG1_4(){
		//Param
		final int x = 0;
		final int y = 500;
		final int rayon = 600;
		CircleHitbox box = new CircleHitbox(x, y, 0, rayon);
		
		//Resultat attendu
		final boolean result = true;
		
		//Test
		assertEquals(result, this.hitbox.collisionWithCircle(box));
	}
}
