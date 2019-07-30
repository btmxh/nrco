/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.utils;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.opengl.objects.Texture;
import com.lwjglwrapper.opengl.objects.TexturedVAO;
import com.lwjglwrapper.opengl.objects.VAO;
import com.lwjglwrapper.opengl.shaders.Shader;
import com.lwjglwrapper.opengl.shaders.ShaderFile;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UFloat;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec2;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec3;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec4;
import com.lwjglwrapper.utils.math.MathUtils;
import java.util.ArrayList;
import java.util.List;
import org.joml.Rectanglef;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Welcome
 */
public class RenderableTexture extends TexturedVAO{
    
    private static Shader shader;
    private static UVec4 ubounds;
    private static UVec2 offset;
    private static UFloat alpha;
    private static UVec3 tint;
    private static List<RenderableTexture> created = new ArrayList<>();
    
    public static void createShader() {
        shader = new Shader(ShaderFile.fromResource("/nrco/utils/shaders/rtshader.vert", GL20.GL_VERTEX_SHADER),
                            ShaderFile.fromResource("/nrco/utils/shaders/rtshader.frag", GL20.GL_FRAGMENT_SHADER));
        ubounds = new UVec4(shader, "bounds");
        offset = new UVec2(shader, "offset");
        alpha = new UFloat(shader, "alpha");
        tint = new UVec3(shader, "tint");
        
        begin();
        setAlpha(1);
        end();
    }
    
    public static void begin() {shader.bind();}
    public static void end() {shader.unbind();}
    public static void disposeShader() {
        shader.dispose();
        created.forEach(VAO::dispose);
    }
    public static void setOffset(float x, float y) {
        x = MathUtils.map(x, 0, LWJGL.window.getWidth(), -1, 1) + 1;
        y = MathUtils.map(-y, 0, LWJGL.window.getHeight(), -1, 1) + 1;
        
        offset.load(x, y);
    }
    
    public static void setAlpha(float a) {
        alpha.load(a);
    }
    public static void setTint(Vector3f tint) {
        RenderableTexture.tint.load(tint);
    }
    
    private Vector4f bounds;
    
    public RenderableTexture(Texture texture) {
        super(0, texture);
        bind();
        createAttribute(0, new float[]{-1, 1, 1, 1, -1, -1, 1, -1}, 2);
        unbind();
    }
    
    private void render(Vector4f bounds) {
        ubounds.load(bounds);
        
        bind(0);
        renderArray(GL11.GL_TRIANGLE_STRIP, 0, 4);
        unbind(0);
    }
    
    public void render(Rectanglef bounds) {
        render(new Vector4f(MathUtils.map(bounds.minX, 0, LWJGL.window.getWidth(), -1, 1),
                            MathUtils.map(bounds.minY, 0, LWJGL.window.getHeight(), -1, 1),
                            MathUtils.map(bounds.maxX, 0, LWJGL.window.getWidth(), -1, 1),
                            MathUtils.map(bounds.maxY, 0, LWJGL.window.getHeight(), -1, 1)));
    }
    
    
    
}
