package dev.util.math;

public class Vecteur4f {
	private float x;
	private float y;
	private float z;
	private float w;
	
	public Vecteur4f(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public float distance(Vecteur4f v){
		return (float) Math.sqrt(Math.pow(v.getX() - this.x, 2) + Math.pow(v.getY() - this.y, 2) + Math.pow(v.getZ() - this.z, 2));
	}
	
	public Vecteur4f transformation(Matrice4f matrice){
		float[] v = new float[4];
		float x = 0.0f;
		float y = 0.0f;
		float z = 0.0f;
		float w = 0.0f;
		
		v[0] = this.x;
		v[1] = this.y;
		v[2] = this.z;
		v[3] = this.w;
		for(int i = 0; i < Matrice4f.taille; i++){
			x += matrice.matrice[i + 0 * Matrice4f.taille] * v[i];
			y += matrice.matrice[i + 1 * Matrice4f.taille] * v[i];
			z += matrice.matrice[i + 2 * Matrice4f.taille] * v[i];
			w += matrice.matrice[i + 3 * Matrice4f.taille] * v[i];
		}
		return new Vecteur4f(x, y, z, w);
	}
	
	public static float[] conversionVecteur4fVersTableau3f(Vecteur4f[] v){
		float[] res = new float[v.length * 3];
		
		for(int i = 0; i < v.length; i++){
			res[0 + i * 3] = v[i].getX();
			res[1 + i * 3] = v[i].getY();
			res[2 + i * 3] = v[i].getZ();
		}
		
		return res;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append(this.x);
		sb.append(",");
		sb.append(this.y);
		sb.append(",");
		sb.append(this.z);
		sb.append(",");
		sb.append(this.w);
		sb.append("}");
		
		return sb.toString();
	}
}
