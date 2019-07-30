/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.utils;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.opengl.objects.FBO;
import com.lwjglwrapper.opengl.objects.Texture;
import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.opengl.objects.TexturedVAO;
import com.lwjglwrapper.opengl.objects.VAO;
import com.lwjglwrapper.opengl.shaders.Shader;
import com.lwjglwrapper.opengl.shaders.ShaderFile;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec2;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec3;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec4;
import com.lwjglwrapper.utils.math.MathUtils;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Welcome
 */
public class Screenshot extends VAO{
    private static Shader shader;
    private static UVec3 tint;
    private static UVec2 offset;
    
    public static void createShader() {
        shader = new Shader(ShaderFile.fromResource("/nrco/utils/shaders/fboshader.vert", GL20.GL_VERTEX_SHADER),
                            ShaderFile.fromResource("/nrco/utils/shaders/fboshader.frag", GL20.GL_FRAGMENT_SHADER));
        tint = new UVec3(shader, "tint");
        offset = new UVec2(shader, "offset");
    }
    
    public static void beginShader() {shader.bind();}
    public static void endShader() {shader.unbind();}
    public static void disposeShader() {shader.dispose();}
    public static void setTint(Vector3f tint) {
        Screenshot.tint.load(tint);
    }
    public static void setOffset(float x, float y) {
        x = MathUtils.map(x, 0, LWJGL.window.getWidth(), -1, 1) + 1;
        y = MathUtils.map(y, 0, LWJGL.window.getHeight(), -1, 1) + 1;
        offset.load(x, y);
    }
    
    private FBO fbo;
    private Texture2D texture;

    public Screenshot() {
        super();
        fbo = new FBO(GL30.GL_FRAMEBUFFER);
        fbo.bind();
        texture = fbo.createTexture();
        fbo.checkProgress(System.out, false);
        fbo.unbind();
        
        bind();
        createAttribute(0, new float[]{-1, 1, 1, 1, -1, -1, 1, -1}, 2);
        unbind();
    }
    
    public void begin() {
        fbo.bind();
    }
    public void end() {
        fbo.unbind();
    }
    public Texture2D getTexture() {
        return texture;
    }
    
    public void render() {
        bind(0);
        texture.bind(0);
        renderArray(GL11.GL_TRIANGLE_STRIP, 0, 4);
        texture.unbind();
        unbind();
    }
    
}
