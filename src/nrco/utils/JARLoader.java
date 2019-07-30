/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.utils;

import com.lwjglwrapper.nanovg.NVGFont;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.opengl.objects.TextureData;
import com.lwjglwrapper.utils.Utils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

/**
 *
 * @author Welcome
 */
public class JARLoader {

    public static Texture2D loadTexture(String path) {
        return new Texture2D(loadTextureData(path));
    }

    public static TextureData loadTextureData(String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer pixels = STBImage.stbi_load_from_memory(
                    Utils.ioResourceToByteBuffer(JARLoader.class.getResourceAsStream(path), 8 * 1024), w, h, comp, 4);

            TextureData texData = new TextureData(w.get(), h.get(), pixels);

            return texData;
        } catch (IOException ex) {
            Logger.getLogger(JARLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
