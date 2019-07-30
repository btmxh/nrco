/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.objs;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.nanovg.NVGFont;
import com.lwjglwrapper.utils.GameObject;
import com.lwjglwrapper.utils.IColor;
import org.joml.Vector2f;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class Points implements GameObject{
    private String representation;
    private NVGFont font;
    private IColor fontColor;
    private float textSize;
    private Vector2f position;
    
    private float age = 0, maxAge;  //seconds

    public Points(String representation, NVGFont font, IColor fontColor,
            float textSize, Vector2f position, float maxAge) {
        this.representation = representation;
        this.font = font;
        this.fontColor = fontColor;
        this.textSize = textSize;
        this.position = position;
        this.maxAge = maxAge;
    }
   
    public void render() {
        LWJGL.graphics.beginPath();
        font.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_CENTER | NanoVG.NVG_ALIGN_MIDDLE);
        LWJGL.graphics.textSize(textSize);
        LWJGL.graphics.textPaint(fontColor);
        LWJGL.graphics.text(representation, position.x, position.y);
    }

    @Override
    public void update(float delta) {
        age += delta;
        float alpha;
        if(age > maxAge / 2) {
            alpha = (maxAge - age) / maxAge * 2;
        } else {
            alpha = age / maxAge * 2;
        }
        if(age > maxAge)    alpha = 0;
        fontColor = fontColor.alpha(alpha);
        
    }
}
