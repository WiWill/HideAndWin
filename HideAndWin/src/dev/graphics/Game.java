package dev.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import dev.model.ennemi.ConeVision;
import dev.model.ennemi.Ennemi;
import dev.model.joueur.Joueur;
import dev.util.hitbox.CircleHitbox;
import dev.util.math.Vecteur4f;

public class Game implements Runnable {
	private long fenetre;
	private int width;
	private int height;
	private boolean continuer = false;
	private Joueur joueur;
	private Ennemi ennemi;
	private Cube cube;
	
	public Game(int width, int height){
		this.width = width;
		this.height = height;
		this.continuer = true;
		this.joueur = new Joueur(200, 200, 0, 20);
		this.ennemi = new Ennemi(new CircleHitbox(600, 200, 0, 20), new ConeVision(new Vecteur4f(600, 200, 0, 1), 135, 15, 300));
		this.cube = new Cube(new Vecteur4f(0f, 0f, 0f, 1f));
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
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LESS);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	private void update(){
		glfwPollEvents();
	}
	
	private void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		this.joueur.render(this.width, this.height);
		this.ennemi.render(this.width, this.height);
		this.cube.render();
		
		glfwSwapBuffers(this.fenetre);
	}

}
