package dev.util.graphics;

import java.util.ArrayList;

import dev.util.math.Vecteur4f;

public class InformationOBJ {
	private ArrayList<Vecteur4f> sommets = new ArrayList<Vecteur4f>();
	private ArrayList<Vecteur4f> uv = new ArrayList<Vecteur4f>();
	private ArrayList<Vecteur4f> normals = new ArrayList<Vecteur4f>();
	private ArrayList<ArrayList<Vecteur4f>> triangles = new ArrayList<ArrayList<Vecteur4f>>();
	
	public InformationOBJ(ArrayList<Vecteur4f> sommets, ArrayList<Vecteur4f> uv, ArrayList<Vecteur4f> normals, ArrayList<ArrayList<Vecteur4f>> triangles){
		this.sommets = sommets;
		this.uv = uv;
		this.normals = normals;
		this.triangles = triangles;
	}

	public ArrayList<Vecteur4f> getSommets() {
		return sommets;
	}

	public void setSommets(ArrayList<Vecteur4f> sommets) {
		this.sommets = sommets;
	}

	public ArrayList<Vecteur4f> getUv() {
		return uv;
	}

	public void setUv(ArrayList<Vecteur4f> uv) {
		this.uv = uv;
	}

	public ArrayList<Vecteur4f> getNormals() {
		return normals;
	}

	public void setNormals(ArrayList<Vecteur4f> normals) {
		this.normals = normals;
	}

	public ArrayList<ArrayList<Vecteur4f>> getTriangles() {
		return triangles;
	}

	public void setTriangles(ArrayList<ArrayList<Vecteur4f>> triangles) {
		this.triangles = triangles;
	}
}
