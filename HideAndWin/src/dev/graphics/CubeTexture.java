package dev.graphics;

import dev.util.math.Matrice4f;
import dev.util.math.Vecteur4f;

public class CubeTexture implements ICube {
	private Vecteur4f position;
	private float[] sommets;
	private float[] uv;
	private int[] indices;
	private Shader shader;
	private VertexArrayObjectTexture vao;
	private Texture texture;
	float rotationX = 0.0f, rotationY = 0.0f, rotationZ = 0.0f;
	
	public CubeTexture(Vecteur4f position, String vertPath, String fragPath, String texturePath){
		this.position = position;
		init(vertPath, fragPath, texturePath);
	}
	
	private void init(String vertPath, String fragPath, String texturePath){
		this.sommets = new float[]{
				-1.0f, 1.0f, -1.0f,
				-1.0f, -1.0f, -1.0f,
				-1.0f, 1.0f, 1.0f,
				-1.0f, -1.0f, 1.0f,
				1.0f, 1.0f, 1.0f,
				1.0f, -1.0f, 1.0f,
				1.0f, 1.0f, -1.0f,
				1.0f, -1.0f, -1.0f
		};

		this.uv = new float[]{
			1.0f, 0.0f,
			1.0f, 0.0f,
			1.0f, 1.0f,
			0.0f, 0.0f,
			0.0f, 1.0f,
			0.0f, 1.0f,
			1.0f, 1.0f,
			1.0f, 1.0f,
		};
		
		this.indices = new int[]{
			0, 1, 2, 2, 1, 3,
			4, 5, 6, 6, 5, 7,
			3, 1, 5, 5, 1, 7,
			0, 2, 6, 6, 2, 4,
			6, 7, 0, 0, 7, 1,
			2, 3, 4, 4, 3, 5
		};
		
		this.shader = new Shader(vertPath, fragPath);
		this.vao = new VertexArrayObjectTexture(this.sommets, this.indices, this.uv);
		this.texture = new Texture(texturePath);
	}
	
	@Override
	public void render(){
		Matrice4f matriceHomogene;
		
		rotationX++;
		if(rotationX >= 360f){
			rotationX = 0f;
		}
		rotationY++;
		if(rotationY >= 360f){
			rotationY = 0f;
		}
		rotationZ++;
		if(rotationZ >= 360f){
			rotationZ = 0f;
		}

		matriceHomogene = Matrice4f.translation(this.position)
				.multipliee(Matrice4f.rotationXYZ(rotationX, rotationY, rotationZ)
						.multipliee(Matrice4f.homothetie(0.25f)
				)
		);
		
		this.shader.enable();
		this.shader.setUniformMat4f("ModelViewProjectionMatrix", matriceHomogene);
		this.texture.bind();
		this.vao.render();
		this.texture.unbind();
		this.shader.disable();
	}
}
