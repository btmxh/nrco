/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.ui;

import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.nanovg.paint.GradientPaint;
import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.Logger;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.ShapeStates;
import com.lwjglwrapper.utils.ui.Component;
import com.lwjglwrapper.utils.ui.Stage;

/**
 *
 * @author Welcome
 */
public class AlphaStage extends Stage{
    
    public AlphaStage(Window window, NVGGraphics g) {
        super(window, g);
    }

    public void setAlpha(float alpha) {
        for (Component comp : components) {
            setAlpha(comp, alpha);
        }
    }
    
    private void setAlpha(Component comp, float alpha) {
        ShapeStates shapes = comp.getShapeStates();
        for (int i = 0; i < shapes.size(); i++) {
            PaintedShape shape = shapes.get(i);
            shape.setFill(alpha(shape.getFill(), alpha));
            shape.setStroke(alpha(shape.getStroke(), alpha));
        }
    }

    private Paint alpha(Paint paint, float alpha) {
        if(paint instanceof IColor) {
            return ((IColor) paint).alpha(alpha);
        } else if(paint instanceof GradientPaint) {
            GradientPaint gpaint = (GradientPaint) paint;
            gpaint.setColor(gpaint.getIColor().alpha(alpha), gpaint.getOColor().alpha(alpha));
            return gpaint;
        }
        return paint;
    }
}
