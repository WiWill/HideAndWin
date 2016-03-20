package dev.graphics;

public class VAOData {
	private float[] sommets;
	private float[] uv;
	private float[] normals;
	private int[] indices;
	
	public VAOData(float[] sommets, float[] uv, float[] normals, int[] indices){
		this.sommets = sommets;
		this.uv = uv;
		this.normals = normals;
		this.indices = indices;
	}

	public float[] getSommets() {
		return sommets;
	}

	public void setSommets(float[] sommets) {
		this.sommets = sommets;
	}

	public float[] getUv() {
		return uv;
	}

	public void setUv(float[] uv) {
		this.uv = uv;
	}

	public float[] getNormals() {
		return normals;
	}

	public void setNormals(float[] normals) {
		this.normals = normals;
	}

	public int[] getIndices() {
		return indices;
	}

	public void setIndices(int[] indices) {
		this.indices = indices;
	}
}
