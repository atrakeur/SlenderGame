package engine.textures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import engine.resources.Resources;

public class TextureLoader {
	
	public static String textureFolder = "./img/";
	
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	/**
     * Load a texture
     *
     * @param resourceName The location of the resource to load
     * @return The loaded texture
     * @throws IOException Indicates a failure to access the resource
     */
    public static Texture getTexture(String resourceName) throws IOException {
        Texture tex = textures.get(resourceName);

        if (tex != null) {
            return tex;
        }
        
        BufferedImage image = Resources.loadImage(resourceName);
        tex = new Texture(resourceName, image);

        textures.put(resourceName,tex);

        return tex;
    }
    
}
