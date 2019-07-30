/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.utils.states.State;
import nrco.MainGame;
import nrco.utils.RenderableTexture;
import org.joml.Rectanglef;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public abstract class IState extends State<MainGame> {
    
    private float xoff, yoff;
    protected boolean clear = true;
    
    public IState(MainGame game) {
        super(game);
    }
    
    public void render(float xoff, float yoff) {
        if(clear) {
            GL11.glClearColor(1, 1, 1, 1);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        }
    }

    @Override
    public void render() {
        render(xoff, yoff);
    }
    
    public void setOffset(float xoff, float yoff) {
        this.xoff = xoff;
        this.yoff = yoff;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }
    
    
    
}
