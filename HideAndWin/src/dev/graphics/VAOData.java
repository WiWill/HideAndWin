package dev.graphics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class VAOData implements Serializable {
	private static final long serialVersionUID = 4841038736994409514L;
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
	
	public void sauvegardeVAOData(String sauvegarde){
		try {
			FileOutputStream fos = new FileOutputStream(sauvegarde);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			try {
				oos.writeObject(this);
				oos.flush();
			} finally {
				try {
					oos.close();
				} finally {
					fos.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
