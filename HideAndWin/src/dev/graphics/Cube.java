package dev.graphics;

import static org.lwjgl.opengl.GL11.*;
import dev.util.math.Matrice4f;
import dev.util.math.Vecteur4f;

public class Cube {
	private Vecteur4f position;
	private Vecteur4f[] sommets = new Vecteur4f[36];
	
	public Cube(Vecteur4f position){
		this.position = position;
		init();
	}
	
	private void init(){
		this.sommets[0] = new Vecteur4f(-1.0f, 1.0f, -1.0f, 1);
		this.sommets[1] = new Vecteur4f(-1.0f, -1.0f, -1.0f, 1);
		this.sommets[2] = new Vecteur4f(-1.0f, 1.0f, 1.0f, 1);
		this.sommets[3] = new Vecteur4f(-1.0f, 1.0f, 1.0f, 1);
		this.sommets[4] = new Vecteur4f(-1.0f, -1.0f, -1.0f, 1);
		this.sommets[5] = new Vecteur4f(-1.0f, -1.0f, 1.0f, 1);
		
		this.sommets[6] = new Vecteur4f(1.0f, 1.0f, 1.0f, 1);
		this.sommets[7] = new Vecteur4f(1.0f, -1.0f, 1.0f, 1);
		this.sommets[8] = new Vecteur4f(1.0f, 1.0f, -1.0f, 1);
		this.sommets[9] = new Vecteur4f(1.0f, 1.0f, -1.0f, 1);
		this.sommets[10] = new Vecteur4f(1.0f, -1.0f, 1.0f, 1);
		this.sommets[11] = new Vecteur4f(1.0f, -1.0f, -1.0f, 1);
		
		this.sommets[12] = new Vecteur4f(-1.0f, -1.0f, 1.0f, 1);
		this.sommets[13] = new Vecteur4f(-1.0f, -1.0f, -1.0f, 1);
		this.sommets[14] = new Vecteur4f(1.0f, -1.0f, 1.0f, 1);
		this.sommets[15] = new Vecteur4f(1.0f, -1.0f, 1.0f, 1);
		this.sommets[16] = new Vecteur4f(-1.0f, -1.0f, -1.0f, 1);
		this.sommets[17] = new Vecteur4f(1.0f, -1.0f, -1.0f, 1);
		
		this.sommets[18] = new Vecteur4f(-1.0f, 1.0f, -1.0f, 1);
		this.sommets[19] = new Vecteur4f(-1.0f, 1.0f, 1.0f, 1);
		this.sommets[20] = new Vecteur4f(1.0f, 1.0f, -1.0f, 1);
		this.sommets[21] = new Vecteur4f(1.0f, 1.0f, -1.0f, 1);
		this.sommets[22] = new Vecteur4f(-1.0f, 1.0f, 1.0f, 1);
		this.sommets[23] = new Vecteur4f(1.0f, 1.0f, 1.0f, 1);
		
		this.sommets[24] = new Vecteur4f(1.0f, 1.0f, -1.0f, 1);
		this.sommets[25] = new Vecteur4f(1.0f, -1.0f, -1.0f, 1);
		this.sommets[26] = new Vecteur4f(-1.0f, 1.0f, -1.0f, 1);
		this.sommets[27] = new Vecteur4f(-1.0f, 1.0f, -1.0f, 1);
		this.sommets[28] = new Vecteur4f(1.0f, -1.0f, -1.0f, 1);
		this.sommets[29] = new Vecteur4f(-1.0f, -1.0f, -1.0f, 1);
		
		this.sommets[30] = new Vecteur4f(-1.0f, 1.0f, 1.0f, 1);
		this.sommets[31] = new Vecteur4f(-1.0f, -1.0f, 1.0f, 1);
		this.sommets[32] = new Vecteur4f(1.0f, 1.0f, 1.0f, 1);
		this.sommets[33] = new Vecteur4f(1.0f, 1.0f, 1.0f, 1);
		this.sommets[34] = new Vecteur4f(-1.0f, -1.0f, 1.0f, 1);
		this.sommets[35] = new Vecteur4f(1.0f, -1.0f, 1.0f, 1);
	}
	
	public void render(){
		Vecteur4f[] res = new Vecteur4f[36];
		Matrice4f matriceHomogene;
		
		matriceHomogene = Matrice4f.translation(this.position)
				.multipliee(Matrice4f.rotationAxeZ(45f)
						.multipliee(Matrice4f.rotationAxeY(45f)
								.multipliee(Matrice4f.rotationAxeX(45f)
										.multipliee(Matrice4f.homothetie(0.25f)
								)
						)
				)
		);
		for(int i = 0; i < 36; i++){
			res[i] = this.sommets[i].transformation(matriceHomogene);
		}
		glBegin(GL_TRIANGLES);
		for(int i = 0; i < 12; i++){
			glColor3f(1.0f / (float) i, 1.0f / (float) i, 1.0f / (float) i);
			glVertex3f(res[0 + i * 3].getX(), res[0 + i * 3].getY(), res[0 + i * 3].getZ());
			glVertex3f(res[1 + i * 3].getX(), res[1 + i * 3].getY(), res[1 + i * 3].getZ());
			glVertex3f(res[2 + i * 3].getX(), res[2 + i * 3].getY(), res[2 + i * 3].getZ());
		}
		glEnd();
	}
}
