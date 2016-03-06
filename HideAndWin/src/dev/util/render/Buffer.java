package dev.util.render;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffer {
	
	private Buffer(){}
	
	public static ByteBuffer conversionByteBuffer(byte[] bytes){
		ByteBuffer res;
		
		res = ByteBuffer.allocateDirect(bytes.length).order(ByteOrder.nativeOrder());
		res.put(bytes).flip();
		
		return res;
	}
	
	public static FloatBuffer conversionFloatBuffer(float[] floats){
		FloatBuffer res;
		
		//un float fait 4 octets, il faut multiplier la taille du buffer par 4 (shift gauche de 2)
		res = ByteBuffer.allocateDirect(floats.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		res.put(floats).flip();
		
		return res;
	}
	
	public static IntBuffer conversionIntBuffer(int[] ints){
		IntBuffer res;
		
		//un int fait 4 octets, il faut multiplier la taille du buffer par 4 (shift gauche de 2)
		res = ByteBuffer.allocateDirect(ints.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		res.put(ints).flip();
		
		return res;
	}
}
