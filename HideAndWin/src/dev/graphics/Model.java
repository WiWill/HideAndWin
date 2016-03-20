package dev.graphics;

import dev.util.math.Matrice4f;
import dev.util.math.Vecteur4f;

public class Model {
	private Vecteur4f position;
	private Shader shader;
	private VAOData vaoData;
	private VertexArrayObjectTexture vao;
	private Texture texture;
	float rotationX = 0.0f, rotationY = 0.0f, rotationZ = 0.0f;
	
	public Model(Vecteur4f position, String vertPath, String fragPath, String texturePath, VAOData vaoData){
		this.position = position;
		this.vaoData = vaoData;
		//this.vaoData.sauvegardeVAOData("model3D/VAOData/voitureMiniCooper.vaodata");
		init(vertPath, fragPath, texturePath);
	}
	
	private void init(String vertPath, String fragPath, String texturePath){
		this.shader = new Shader(vertPath, fragPath);
		this.vao = new VertexArrayObjectTexture(this.vaoData.getSommets(), this.vaoData.getIndices(), this.vaoData.getUv(), this.vaoData.getNormals());
		this.texture = new Texture(texturePath);
	}
	
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
						.multipliee(Matrice4f.homothetie(0.04f)
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
