package dev.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dev.util.math.Matrice4f;
import dev.util.math.Vecteur4f;
import dev.util.render.Buffer;

public class Shader {
	private int programID;
	private Map<String, Integer> locations = new HashMap<String, Integer>();
	
	public Shader(String vertexPath, String fragmentPath){
		this.programID = creationShader(vertexPath, fragmentPath);
	}

	private int creationShader(String vertexPath, String fragmentPath) {
		int prog = glCreateProgram();
		int vertexID = glCreateShader(GL_VERTEX_SHADER);
		int fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
		String vertexSource = chargerFichierTexte(vertexPath);
		String fragmentSource = chargerFichierTexte(fragmentPath);
		
		glShaderSource(vertexID, vertexSource);
		glShaderSource(fragmentID, fragmentSource);
		glCompileShader(vertexID);
		glCompileShader(fragmentID);
		
		if(glGetShaderi(vertexID, GL_COMPILE_STATUS) == GLFW_FALSE){
			System.err.println("Erreur de compilation du vertex shader !");
		}
		if(glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GLFW_FALSE){
			System.err.println("Erreur de compilation du fragment shader !");
		}
		
		glAttachShader(prog, vertexID);
		glAttachShader(prog, fragmentID);
		glLinkProgram(prog);
		glValidateProgram(prog);
		
		return prog;
	}
	
	private String chargerFichierTexte(String path){
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(path));
			String ligne;
			
			while((ligne = br.readLine()) != null){
				sb.append(ligne);
				sb.append("\n");
			}
		} catch (FileNotFoundException e){
			System.err.println("Erreur d'ouverture du fichier !");
		} catch (IOException e){
			System.err.println("Erreur de lecture du fichier !");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("Erreur de fermeture du fichier !");
			}
		}
		
		return sb.toString();
	}
	
	public int getUniform(String nom){
		int res;
		
		if(this.locations.containsKey(nom)){
			return this.locations.get(nom);
		}
		
		res = glGetUniformLocation(this.programID, nom);
		if(res == -1){
			System.err.println("La variable " + nom + " n'existe pas !");
		} else {
			this.locations.put(nom, res);
		}
		
		return res;
	}
	
	public void setUniform1i(String nom, int val){
		glUniform1i(getUniform(nom), val);	
	}
	
	public void setUniform1f(String nom, float val){
		glUniform1f(getUniform(nom), val);	
	}
	
	public void setUniform2f(String nom, float x, float y){
		glUniform2f(getUniform(nom), x, y);	
	}
	
	public void setUniform3f(String nom, Vecteur4f v){
		glUniform3f(getUniform(nom), v.getX(), v.getY(), v.getZ());	
	}
	
	public void setUniformMat4f(String nom, Matrice4f m){
		glUniformMatrix4fv(getUniform(nom), false, Buffer.conversionFloatBuffer(m.getMatrice()));	
	}
	
	public void enable(){
		glUseProgram(this.programID);
	}
	
	public void disable(){
		glUseProgram(0);
	}

	public int getProgramID() {
		return programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}
}
