/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.ui;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.nanovg.NVGFont;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.nanovg.paint.NanoVGPaint;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.shapes.RoundRect;
import com.lwjglwrapper.utils.math.MathUtils;
import com.lwjglwrapper.utils.ui.Button;
import com.lwjglwrapper.utils.ui.Stage;
import org.joml.Rectanglef;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class IButton extends Button{
    
    public static NVGFont font;
    
    public static final int LEFT = -1, CENTER = 0, RIGHT = 1;
    
    private Rectanglef rect;
    private String text;
    private int alignment;
    private IColor color;
    
    private float alpha = 1f;
    
    public IButton(Stage stage, Rectanglef rect, String text, IColor color) {
        super(stage, true);
        this.rect = rect;
        this.text = text;
        this.color = color;
        resetShapeStates();
        setMode(NORMAL);
    }
    
    public NanoVGPaint gradient(IColor color) {
        return LWJGL.graphics.boxGradient(rect, 20, 50f, color, color.tint(0.8f));
    }

    @Override
    public void render(NVGGraphics g) {
        super.render(g);
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void setTextAlignment(int alignment) {
        this.alignment = alignment;
    }
    
    private int getNanoVGAlignFlag() {
        switch (alignment) {
            case LEFT:   return NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_MIDDLE;
            case RIGHT:  return NanoVG.NVG_ALIGN_RIGHT | NanoVG.NVG_ALIGN_MIDDLE;
            case CENTER: return NanoVG.NVG_ALIGN_CENTER| NanoVG.NVG_ALIGN_MIDDLE;
        }
        return -1;
    }

    private float getTextX(Rectanglef rect) {
        switch (alignment) {
            case LEFT:   return rect.minX + (rect.maxX - rect.minX) * 0.05f;
            case RIGHT:  return rect.maxX - (rect.maxX - rect.minX) * 0.05f;
            case CENTER: return (rect.minX + rect.maxX) / 2;
        }
        return Float.NaN;
    }

    public Rectanglef getRect() {
        return rect;
    }

    public IColor getColor() {
        return color;
    }

    public void resetShapeStates() {
        shapes.reset().setAll(new RoundRect(rect, (rect.maxX - rect.minX) * 0.05f))
                      .set(new RoundRect(RectUtils.scaleCenter(rect, 1.1f), (rect.maxX - rect.minX) * 0.05f), HOVER)
                      .setFill(gradient(color), NORMAL)
                      .setFill(gradient(color.scale(1.2f)), HOVER)
                      .setFill(gradient(color.scale(0.8f)), CLICKED)
                      .setAllAfterPaints((g) -> {
                          font.use();
                          g.textAlign(getNanoVGAlignFlag());
                          g.textPaint(IColor.WHITE);
                          g.textSize((rect.maxY - rect.minY));
                          
                          g.text(this.text, getTextX(rect), (rect.minY + rect.maxY) / 2);
                      }).construct(false);
    }
    
    public void setAlpha(float alpha) {
        color = color.alpha(alpha);
        resetShapeStates();
    }
    
}
