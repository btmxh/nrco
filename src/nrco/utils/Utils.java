/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.utils;

import com.lwjglwrapper.LWJGL;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL21;

/**
 *
 * @author Welcome
 */
public class Utils {
    public static void screenshot() {
        int pboID = GL20.glGenBuffers();
        GL11.glReadBuffer(GL11.GL_FRONT);
        GL20.glBindBuffer(GL21.GL_PIXEL_PACK_BUFFER, pboID);
        GL20.glBufferData(GL21.GL_PIXEL_PACK_BUFFER, LWJGL.window.getWidth() * LWJGL.window.getHeight() * 4, GL21.GL_STREAM_READ);
        GL11.glReadPixels(0, 0, LWJGL.window.getWidth(), LWJGL.window.getHeight(), GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, 0);
        GL20.glBindBuffer(GL21.GL_PIXEL_PACK_BUFFER, pboID);
        ByteBuffer buffer = GL15.glMapBuffer(GL21.GL_PIXEL_PACK_BUFFER, GL15.GL_READ_WRITE, null);
        /* stuff */
        GL20.glUnmapBuffer(GL21.GL_PIXEL_PACK_BUFFER);
        GL20.glBindBuffer(GL21.GL_PIXEL_PACK_BUFFER, 0); 
        byte[] array = new byte[LWJGL.window.getWidth() * LWJGL.window.getHeight() * 4];
        for (int i = 0; i < array.length; i++) {
            array[i] = buffer.get();
        }
        int ptr = 0;
        BufferedImage image = new BufferedImage(LWJGL.window.getWidth(), LWJGL.window.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        for (int x = 0; x < LWJGL.window.getHeight(); x++) {
            for (int y = 0; y < LWJGL.window.getWidth(); y++) {
                int r = array[ptr++];
                int g = array[ptr++];
                int b = array[ptr++];
                int a = array[ptr++];
                r &= 0xFF;
                g &= 0xFF;
                b &= 0xFF;
                a &= 0xFF;
                Color color = new Color(r, g, b, a);
                image.setRGB(y, x, color.getRGB());
            }
        }
        try {
            ImageIO.write(image, "png", new File("D:\\image.png"));
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
