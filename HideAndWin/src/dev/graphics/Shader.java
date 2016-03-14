package dev.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Shader {
	private int programID;
	
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

	public int getProgramID() {
		return programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}
}
