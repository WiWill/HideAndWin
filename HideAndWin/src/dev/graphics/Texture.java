package dev.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import dev.util.render.Buffer;

public class Texture {
	private int largeur;
	private int hauteur;
	private int textureID;
	
	public Texture(String cheminTexture){
		this.textureID = chargementTexture(cheminTexture);
	}
	
	private int chargementTexture(String cheminTexture){
		int[] pixels = null;
		int[] res = null;
		int id;
		
		try {
			BufferedImage image = ImageIO.read(new FileInputStream(cheminTexture));
			
			this.largeur = image.getWidth();
			this.hauteur = image.getHeight();
			pixels = new int[this.largeur * this.hauteur];
			res = new int[this.largeur * this.hauteur];
			image.getRGB(0, 0, this.largeur, this.hauteur, pixels, 0, this.largeur);
		} catch (FileNotFoundException e) {
			System.err.println("Erreur d'ouverture du fichier !");
			return -1;
		} catch (IOException e) {
			System.err.println("Erreur de lecture du fichier !");
			return -1;
		}
		
		for(int i = 0; i < this.largeur * this.hauteur; i++){
			//Valeur de pixel stockee sous la forme ARGB, on filtre et recupere chaque valeur
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0x00ff0000) >> 16;
			int g = (pixels[i] & 0x0000ff00) >> 8;
			int b = (pixels[i] & 0x000000ff);
			
			//On reconstruit le pixel sous la forme ABGR (lecture RGBA pour OpenGL)
			res[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.largeur, this.hauteur, 0, GL_RGBA, GL_UNSIGNED_INT, Buffer.conversionIntBuffer(res));
		glBindTexture(GL_TEXTURE_2D, 0);
		
		return id;
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, this.textureID);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}
