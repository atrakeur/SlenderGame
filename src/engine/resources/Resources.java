package engine.resources;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.exceptions.GameException;

public class Resources {
	
	private static boolean isInit;

	private static String userPath;
	private static String appPath;
	private static String dataPath;
	
	public static String textureFolder = "img/";
	
	public static void init() throws GameException{
		if(isInit)
			throw new GameException("Can't init Resources twice");
		
		userPath = System.getProperty("user.home");
		appPath = "./";
		dataPath = appPath +"data/";
	}
	
	public static String loadText(String filename) throws IOException{
		File file = new File(dataPath+filename);
		if(!file.exists())
			throw new IOException("File "+dataPath+filename+" don't exist");
		
		int numRead;
		char[] buffer = new char[1024];
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();
	
		while((numRead = reader.read(buffer)) != -1)
			sb.append(buffer);
		
		reader.close();
		
		return sb.toString();
	}
	
	public static BufferedImage loadImage(String filename) throws IOException{
		File file = new File(dataPath+textureFolder+filename);
		
		if(!file.exists())
			throw new IOException("File "+dataPath+textureFolder+filename+" don't exist");
		
        BufferedImage img = ImageIO.read(file);

        return img;
	}

}
