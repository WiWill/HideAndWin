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
	private ICube cube1;
	private ICube cube2;
	private ICube cube3;
	private ICube cube4;
	private Model model;
	
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
		
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LESS);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		this.joueur = new Joueur(200, 200, 0, 20);
		this.ennemi = new Ennemi(new CircleHitbox(600, 200, 0, 20), new ConeVision(new Vecteur4f(600, 200, 0, 1), 135, 15, 300));
		//this.cube1 = new CubeTexture(new Vecteur4f(0f, 0f, 0f, 1f), "shaders/cube/texture/vert.txt", "shaders/cube/texture/frag.txt", "shaders/cube/texture/uv.bmp");
		this.model = new Model(new Vecteur4f(0f, 0f, 0f, 1f), "shaders/cube/texture/vert.txt", "shaders/cube/texture/frag.txt", "model3D/OBJ/deer.bmp", ModelLoader.chargementModelVAOData("model3D/VAOData/deer.vaodata"));
		//this.cube2 = new CubeCouleur(new Vecteur4f(2f, 0f, 0f, 1f), "shaders/cube/couleur/vert.txt", "shaders/cube/couleur/frag.txt");
		//this.cube3 = new CubeCouleur(new Vecteur4f(-2f, 0f, 0f, 1f), "shaders/cube/couleur/vert.txt", "shaders/cube/couleur/frag.txt");
		//this.cube4 = new CubeCouleur(new Vecteur4f(0f, 2f, 0f, 1f), "shaders/cube/couleur/vert.txt", "shaders/cube/couleur/frag.txt");
	}
	
	private void update(){
		glfwPollEvents();
	}
	
	private void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		this.joueur.render(this.width, this.height);
		this.ennemi.render(this.width, this.height);
		//this.cube1.render();
		this.model.render();
		//this.cube2.render();
		//this.cube3.render();
		//this.cube4.render();
		
		glfwSwapBuffers(this.fenetre);
	}

}
