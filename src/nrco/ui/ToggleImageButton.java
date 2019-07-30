/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.ui;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.utils.math.MathUtils;
import java.text.DecimalFormat;
import nrco.utils.RenderableTexture;
import org.joml.Rectanglef;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class ToggleImageButton {
    private boolean toggled, hover;
    private RenderableTexture off, on;
    private Rectanglef bounds;
    private OnToggledListener otl = (t) -> {};

    public ToggleImageButton(Texture2D off, Texture2D on, Rectanglef bounds) {
        this.off = new RenderableTexture(off);
        this.on = new RenderableTexture(on);
        this.bounds = bounds;
    }
    
    public void update(float delta) {
        hover = false;
        Vector2f cursorPosition = LWJGL.mouse.getCursorPosition();
        Vector2f flippedPosition = new Vector2f(cursorPosition.x, LWJGL.window.getHeight() - cursorPosition.y);
        if(MathUtils.contains(bounds, flippedPosition)) {
            if(LWJGL.mouse.anyMouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                toggled = !toggled;
                otl.onToggled(toggled);
            } else hover = true;
            
        }
    }
    
    public void render() {
        Rectanglef bounds = new Rectanglef(this.bounds);
        if(hover)   bounds = RectUtils.scaleCenter(bounds, 1.2f);
        (toggled? on:off).render(bounds);
    }

    public void setOnToggledListener(OnToggledListener otl) {
        this.otl = otl;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
    
    
    
    public interface OnToggledListener {
        public void onToggled(boolean toggled);
    }
    
}
