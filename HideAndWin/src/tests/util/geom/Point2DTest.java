package tests.util.geom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dev.util.geom.Point2D;

public class Point2DTest {
	protected Point2D point;
	
	@Before
	public void doBefore(){
		this.point = new Point2D(0, 0);
	}
	
	@Test
	public void testRG1_0(){
		//Param
		final int x = 0;
		final int y = 10;
		Point2D p = new Point2D(x, y);
		
		//Resultat attendu
		double distance = 10;
		
		//Test
		assertEquals(true, this.point.distance(p) == distance);
	}
	
	@Test
	public void testRG1_1(){
		//Param
		final int x = 10;
		final int y = 0;
		Point2D p = new Point2D(x, y);
		
		//Resultat attendu
		double distance = 10;
		
		//Test
		assertEquals(true, this.point.distance(p) == distance);
	}
	
	@Test
	public void testRG1_2(){
		//Param
		final int x = 10;
		final int y = 24;
		Point2D p = new Point2D(x, y);
		
		//Resultat attendu
		double distance = 26;

		//Test
		assertEquals(true, this.point.distance(p) == distance);
	}
}
