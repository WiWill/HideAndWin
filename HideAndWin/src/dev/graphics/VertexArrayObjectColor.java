package dev.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import dev.util.render.Buffer;

public class VertexArrayObjectColor implements IVertexArrayObject {
	private int vao;
	private int vbo;
	private int ibo;
	private int cbo;
	private int nbIndices;
	
	public VertexArrayObjectColor(float[] sommets, int[] indices, float[] couleurs){
		this.nbIndices = indices.length;
		this.vao = glGenVertexArrays();
		glBindVertexArray(this.vao);
		
		this.vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
		glBufferData(GL_ARRAY_BUFFER, Buffer.conversionFloatBuffer(sommets), GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		
		this.cbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, this.cbo);
		glBufferData(GL_ARRAY_BUFFER, Buffer.conversionFloatBuffer(couleurs), GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
		
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
