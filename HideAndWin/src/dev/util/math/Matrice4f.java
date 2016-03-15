package dev.util.math;

public class Matrice4f {
	public static int taille = 4;
	public float[] matrice = new float[taille * taille];
	
	public Matrice4f(){
		
	}
	
	public static Matrice4f identite(){
		Matrice4f res = new Matrice4f();
		
		for(int i = 0; i < taille * taille; i++){
			res.matrice[i] = 0.0f;
		}
		res.matrice[0 + 0 * taille] = 1.0f;
		res.matrice[1 + 1 * taille] = 1.0f;
		res.matrice[2 + 2 * taille] = 1.0f;
		res.matrice[3 + 3 * taille] = 1.0f;
		return res;
	}
	
	public static Matrice4f translation(Vecteur4f vecteur){
		Matrice4f res = identite();
		
		res.matrice[3 + 0 * taille] = vecteur.getX();
		res.matrice[3 + 1 * taille] = vecteur.getY();
		res.matrice[3 + 2 * taille] = vecteur.getZ();
		return res;
	}
	
	public static Matrice4f homothetie(float k){
		Matrice4f res = identite();
		
		res.matrice[0 + 0 * taille] = k;
		res.matrice[1 + 1 * taille] = k;
		res.matrice[2 + 2 * taille] = k;
		return res;
	}
	
	public static Matrice4f rotationAxeX(float angleDegree){
		double angle = (float) Math.toRadians(angleDegree);
		float cosAngle = (float) Math.cos(angle);
		float sinAngle = (float) Math.sin(angle);
		Matrice4f res = identite();
		
		res.matrice[1 + 1 * taille] = cosAngle;
		res.matrice[2 + 1 * taille] = - sinAngle;
		res.matrice[1 + 2 * taille] = sinAngle;
		res.matrice[2 + 2 * taille] = cosAngle;
		return res;
	}
	
	public static Matrice4f rotationAxeY(float angleDegree){
		double angle = (float) Math.toRadians(angleDegree);
		float cosAngle = (float) Math.cos(angle);
		float sinAngle = (float) Math.sin(angle);
		Matrice4f res = identite();
		
		res.matrice[0 + 0 * taille] = cosAngle;
		res.matrice[2 + 0 * taille] = sinAngle;
		res.matrice[0 + 2 * taille] = - sinAngle;
		res.matrice[2 + 2 * taille] = cosAngle;
		return res;
	}
	
	public static Matrice4f rotationAxeZ(float angleDegree){
		double angle = (float) Math.toRadians(angleDegree);
		float cosAngle = (float) Math.cos(angle);
		float sinAngle = (float) Math.sin(angle);
		Matrice4f res = identite();
		
		res.matrice[0 + 0 * taille] = cosAngle;
		res.matrice[1 + 0 * taille] = - sinAngle;
		res.matrice[0 + 1 * taille] = sinAngle;
		res.matrice[1 + 1 * taille] = cosAngle;
		return res;
	}
	
	public static Matrice4f rotationXYZ(float angleX, float angleY, float angleZ){
		boolean modif = false;
		Matrice4f res = null;
		
		if(angleZ != 0.0f){
			res = Matrice4f.rotationAxeZ(angleZ);
			modif = true;
		}
		if(angleY != 0.0f){
			res = modif ? res.multipliee(Matrice4f.rotationAxeY(angleY)) : Matrice4f.rotationAxeY(angleY);
			modif = true;
		}
		if(angleX != 0.0f){
			res = modif ? res.multipliee(Matrice4f.rotationAxeX(angleX)) : Matrice4f.rotationAxeX(angleX);
			modif = true;
		}
		return modif ? res : Matrice4f.identite();
	}
	
	public Matrice4f multipliee(Matrice4f matrice){
		Matrice4f res = new Matrice4f();
		
		for(int i = 0; i < taille; i++){
			for(int j = 0; j < taille; j++){
				float r = 0.0f;
				
				for(int k = 0; k < taille; k++){
					r += this.matrice[j + k * taille] * matrice.matrice[k + i * taille];
				}
				res.matrice[j + i * taille] = r;
			}
		}
		return res;
	}

	public float[] getMatrice() {
		return matrice;
	}

	public void setMatrice(float[] matrice) {
		this.matrice = matrice;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for(int i = 0; i < taille; i++){
			sb.append("(");
			for(int j = 0; j < taille; j++){
				sb.append(this.matrice[j + i * taille]);
				if(j < taille - 1){
					sb.append(", ");
				}
			}
			sb.append(")");
			if(i < taille - 1){
				sb.append("\n");
			}
		}
		sb.append("]\n");
		
		return sb.toString();
	}
}
