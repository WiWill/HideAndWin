package dev.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import dev.util.render.Buffer;

public class VertexArrayObjectTexture implements IVertexArrayObject {
	private int vao;
	private int vbo;
	private int ibo;
	private int tco;
	private int nbo;
	private int nbIndices;
	
	public VertexArrayObjectTexture(float[] sommets, int[] indices, float[] uv, float[] normals){
		this.nbIndices = indices.length;
		this.vao = glGenVertexArrays();
		glBindVertexArray(this.vao);
		
		this.vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
		glBufferData(GL_ARRAY_BUFFER, Buffer.conversionFloatBuffer(sommets), GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		
		this.tco = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, this.tco);
		glBufferData(GL_ARRAY_BUFFER, Buffer.conversionFloatBuffer(uv), GL_STATIC_DRAW);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
		
		this.nbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, this.nbo);
		glBufferData(GL_ARRAY_BUFFER, Buffer.conversionFloatBuffer(normals), GL_STATIC_DRAW);
		glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(2);
		
		this.ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Buffer.conversionIntBuffer(indices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	@Override
	public void bind(){
		glBindVertexArray(this.vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.ibo);
	}
	
	@Override
	public void unbind(){
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	@Override
	public void draw(){
		glDrawElements(GL_TRIANGLES, this.nbIndices, GL_UNSIGNED_INT, 0);
	}
	
	@Override
	public void render(){
		bind();
		draw();
		unbind();
	}
}
