package dev.util.graphics;

public class GraphicsUtils {
	private GraphicsUtils(){}
	
	public static float convertPixelToOpenGLDimension(int i, int largeur){
		if(i <= 0){
			return -1.0f;
		} else if(i >= largeur){
			return 1.0f;
		}
		return ((float) i / (float) largeur) * 2.0f - 1.0f;
	}
}
