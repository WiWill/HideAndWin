package dev.graphics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import dev.util.graphics.InformationOBJ;
import dev.util.math.Vecteur4f;

public class ModelLoader {

	private ModelLoader(){
		
	}
	
	public static VAOData chargementModelVAOData(String modelPath){
		VAOData res = null;
		
		try {
			FileInputStream fis = new FileInputStream(modelPath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			try {	
				res = (VAOData) ois.readObject(); 
			} finally {
				try {
					ois.close();
				} finally {
					fis.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static VAOData chargementModelOBJ(String modelPath){
		VAOData res;
		InformationOBJ informations;
		
		informations = recuperationInformationOBJ(modelPath);
		if(informations == null){
			return null;
		}
		res = transformationInformationOBJ(informations);
		
		return res;
	}
	
	private static InformationOBJ recuperationInformationOBJ(String modelPath){
		InformationOBJ res;
		BufferedReader br = null;
		ArrayList<Vecteur4f> sommets = new ArrayList<Vecteur4f>();
		ArrayList<Vecteur4f> uv = new ArrayList<Vecteur4f>();
		ArrayList<Vecteur4f> normals = new ArrayList<Vecteur4f>();
		ArrayList<ArrayList<Vecteur4f>> triangles = new ArrayList<ArrayList<Vecteur4f>>();
		
		try {
			br = new BufferedReader(new FileReader(modelPath));
			String ligne;
			
			while((ligne = br.readLine()) != null){
				if(ligne.length() > 1){
					if(ligne.charAt(0) == 'v'){
						//v, vt ou vn
						if(ligne.charAt(1) == 't'){
							//vt : uv
							Vecteur4f v = traitementLigneUvOBJ(ligne);
							
							if(v == null){
								return null;
							}
							uv.add(v);
						} else if(ligne.charAt(1) == 'n'){
							//vn : normal
							Vecteur4f v = traitementLigneNormalOBJ(ligne);
							
							if(v == null){
								return null;
							}
							normals.add(v);
						} else {
							//v : sommet
							Vecteur4f v = traitementLigneSommetOBJ(ligne);
							
							if(v == null){
								return null;
							}
							sommets.add(v);
						}
					} else if(ligne.charAt(0) == 'f'){
						//f : triangle
						ArrayList<Vecteur4f> av = traitementLigneTriangleOBJ(ligne);
						
						if(av == null){
							return null;
						}
						triangles.add(av);
					}
				}
			}
		} catch (FileNotFoundException e){
			System.err.println("Erreur d'ouverture du fichier !");
			return null;
		} catch (IOException e){
			System.err.println("Erreur de lecture du fichier !");
			return null;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("Erreur de fermeture du fichier !");
				return null;
			}
		}
		res = new InformationOBJ(sommets, uv, normals, triangles);
		
		return res;
	}
	
	private static Vecteur4f traitementLigneUvOBJ(String ligne){
		String[] delim = ligne.split(" ");
		
		if(delim.length != 3){
			System.err.println("Le fichier OBJ n'est pas forme correctement !");
			return null;
		}
		
		return new Vecteur4f(Float.valueOf(delim[1]), Float.valueOf(delim[2]), 0.0f, 0.0f);
	}
	
	
	private static Vecteur4f traitementLigneNormalOBJ(String ligne){
		String[] delim = ligne.split(" ");
		
		if(delim.length != 4){
			System.err.println("Le fichier OBJ n'est pas forme correctement !");
			return null;
		}
		
		return new Vecteur4f(Float.valueOf(delim[1]), Float.valueOf(delim[2]), Float.valueOf(delim[3]), 0.0f);
	}
	
	private static Vecteur4f traitementLigneSommetOBJ(String ligne){
		String[] delim = ligne.split(" ");
		
		if(delim.length != 4){
			System.err.println("Le fichier OBJ n'est pas forme correctement !");
			return null;
		}
		
		return new Vecteur4f(Float.valueOf(delim[1]), Float.valueOf(delim[2]), Float.valueOf(delim[3]), 0.0f);
	}
	
	private static ArrayList<Vecteur4f> traitementLigneTriangleOBJ(String ligne){
		String[] delim = ligne.split(" ");
		
		if(delim.length != 4){
			System.err.println("Le fichier OBJ n'est pas forme correctement !");
			return null;
		} else {
			String[] delim2;
			ArrayList<Vecteur4f> triplet = new ArrayList<Vecteur4f>();
			
			for(int i = 1; i < 4; i++){
				delim2 = delim[i].split("/");
				
				if(delim2.length != 3){
					System.err.println("Le fichier OBJ n'est pas forme correctement !");
					return null;
				} else {
					triplet.add(new Vecteur4f(Float.valueOf(delim2[0]) - 1.0f, Float.valueOf(delim2[1]) - 1.0f, Float.valueOf(delim2[2]) - 1.0f, 0.0f));
				}
			}
			return triplet;
		}
	}
	
	private static VAOData transformationInformationOBJ(InformationOBJ informations){
		VAOData res;
		ArrayList<ArrayList<Vecteur4f>> triangles = informations.getTriangles();
		ArrayList<ArrayList<Vecteur4f>> listeTriplet = new ArrayList<ArrayList<Vecteur4f>>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		float[] sommets;
		float[] uv;
		float[] normals;
		int[] ind;
		
		for(int i = 0; i < triangles.size(); i++){
			System.out.println(i + " / " + (triangles.size() - 1));
			for(int j = 0; j < triangles.get(i).size(); j++){
				int presence;
				Vecteur4f composantTriangle = triangles.get(i).get(j);
				ArrayList<Vecteur4f> triplet = new ArrayList<Vecteur4f>();
				
				triplet.add(informations.getSommets().get((int) composantTriangle.getX()));
				triplet.add(informations.getUv().get((int) composantTriangle.getY()));
				triplet.add(informations.getNormals().get((int) composantTriangle.getZ()));
				presence = composantPresent(listeTriplet, triplet);
				if(presence == -1){
					indices.add(listeTriplet.size());
					listeTriplet.add(triplet);
				} else {
					indices.add(presence);
				}
			}
		}
		
		sommets = new float[listeTriplet.size() * 3];
		uv = new float[listeTriplet.size() * 2];
		normals = new float[listeTriplet.size() * 3];
		ind = new int[indices.size()];
		
		for(int i = 0; i < listeTriplet.size(); i++){
			Vecteur4f s = listeTriplet.get(i).get(0);
			Vecteur4f u = listeTriplet.get(i).get(1);
			Vecteur4f n = listeTriplet.get(i).get(2);
			
			sommets[0 + i * 3] = s.getX();
			sommets[1 + i * 3] = s.getY();
			sommets[2 + i * 3] = s.getZ();
			uv[0 + i * 2] = u.getX();
			uv[1 + i * 2] = u.getY();
			normals[0 + i * 3] = n.getX();
			normals[1 + i * 3] = n.getY();
			normals[2 + i * 3] = n.getZ();
		}
		for(int i = 0; i < indices.size(); i++){
			ind[i] = indices.get(i);
		}
		res = new VAOData(sommets, uv, normals, ind);
		
		return res;
	}
	
	private static int composantPresent(ArrayList<ArrayList<Vecteur4f>> listeTriplet, ArrayList<Vecteur4f> triplet){
		for(int i = 0; i < listeTriplet.size(); i++){
			ArrayList<Vecteur4f> l = listeTriplet.get(i);
			
			if(l.size() == triplet.size()){
				boolean verif = true;
				
				for(int j = 0; j < l.size(); j++){
					Vecteur4f v1 = l.get(j);
					Vecteur4f v2 = triplet.get(j);
					
					if(!(v1.getX() == v2.getX() && v1.getY() == v2.getY() && v1.getZ() == v2.getZ() && v1.getW() == v2.getW())){
						verif = false;
					}
				}
				if(verif){
					return i;
				}
			}
		}
		return -1;
	}
}
