package dev.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Game implements Runnable {
	private long fenetre;
	private int width;
	private int height;
	private boolean continuer = false;
	
	public Game(int width, int height){
		this.width = width;
		this.height = height;
		this.continuer = true;
	}

	@Override
	public void run() {
		init();
		while(continuer){
			update();
			render();
			if(glfwWindowShouldClose(this.fenetre) == GLFW_TRUE){
				this.continuer = false;
			}
		}
	}
	
	private void init(){
		GLFWVidMode videomode;
		
		if(glfwInit() != GLFW_TRUE){
			System.err.println("Erreur d'initialisation d'OpenGL");
			System.exit(-1);
		}
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		this.fenetre = glfwCreateWindow(this.width, this.height, "Hide And Win - mHelba", NULL, NULL);
		videomode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(this.fenetre, (videomode.width() - this.width) / 2, (videomode.height() - this.height) / 2);
		glfwMakeContextCurrent(this.fenetre);
		glfwSwapInterval(1);
		glfwShowWindow(this.fenetre);
		GL.createCapabilities();
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
	}
	
	private void update(){
		glfwPollEvents();
	}
	
	private void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(this.fenetre);
	}

}
