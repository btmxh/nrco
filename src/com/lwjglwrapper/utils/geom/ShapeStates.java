/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom;

import com.lwjglwrapper.nanovg.paint.AdditionalPaint;
import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.shapes.RoundRect;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 *
 * @author Welcome
 */
public class ShapeStates implements Cloneable {

    private Shape[] noPaintShapes;
    private Paint[] strokes, fills;
    private AdditionalPaint[] afterPaints, beforePaints;

    private PaintedShape[] shapes;

    public ShapeStates(int maxShapes) {
        noPaintShapes = new Shape[maxShapes];
        strokes = new Paint[maxShapes];
        fills = new Paint[maxShapes];
        afterPaints = new AdditionalPaint[maxShapes];
        beforePaints = new AdditionalPaint[maxShapes];

        shapes = new PaintedShape[maxShapes];
    }

    public ShapeStates set(PaintedShape shape, int... modes) {
        for (int mode : modes) {
            shapes[mode] = shape;
        }
        return this;
    }

    public ShapeStates setAll(PaintedShape shape) {
        for (int mode = 0; mode < shapes.length; mode++) {
            shapes[mode] = shape;
        }
        return this;
    }

    public ShapeStates set(Shape shape, int... modes) {
        for (int mode : modes) {
            noPaintShapes[mode] = shape;
        }
        return this;
    }

    public ShapeStates setAll(Shape shape) {
        for (int mode = 0; mode < shapes.length; mode++) {
            noPaintShapes[mode] = shape;
        }
        return this;
    }

    public ShapeStates setStroke(Paint stroke, int... modes) {
        for (int mode : modes) {
            strokes[mode] = stroke;
        }
        return this;
    }

    public ShapeStates setAllStrokes(Paint stroke) {
        for (int mode = 0; mode < strokes.length; mode++) {
            strokes[mode] = stroke;
        }
        return this;
    }

    public ShapeStates setFill(Paint fill, int... modes) {
        for (int mode : modes) {
            fills[mode] = fill;
        }
        return this;
    }

    public ShapeStates setAllFills(Paint fill) {
        for (int mode = 0; mode < fills.length; mode++) {
            fills[mode] = fill;
        }
        return this;
    }

    public ShapeStates setAfterPaint(AdditionalPaint paint, int... modes) {
        for (int mode : modes) {
            afterPaints[mode] = paint;
        }
        return this;
    }

    public ShapeStates setAllAfterPaints(AdditionalPaint additionalPaint) {
        for (int mode = 0; mode < afterPaints.length; mode++) {
            afterPaints[mode] = additionalPaint;
        }
        return this;
    }
    
    public ShapeStates setBeforePaint(AdditionalPaint paint, int... modes) {
        for (int mode : modes) {
            beforePaints[mode] = paint;
        }
        return this;
    }

    public ShapeStates setAllBeforePaints(AdditionalPaint paint) {
        for (int mode = 0; mode < afterPaints.length; mode++) {
            beforePaints[mode] = paint;
        }
        return this;
    }

    public void construct(boolean ignoreExists) {
        for (int i = 0; i < shapes.length; i++) {
            if (ignoreExists && shapes[i] != null) {
                continue;
            }
            shapes[i] = new PaintedShape(noPaintShapes[i], fills[i], strokes[i], afterPaints[i], beforePaints[i]);
        }
    }

    public ShapeStates reset() {
        int len = noPaintShapes.length;
        noPaintShapes = new Shape[len];
        shapes = new PaintedShape[len];
        fills = new Paint[len];
        strokes = new Paint[len];

        return this;
    }

    public PaintedShape get(int index) {
        if (index < 0 | index >= shapes.length) {
            return null;
        }
        return shapes[index];
    }

    public static void main(String[] args) {
        ShapeStates shapes = new ShapeStates(3);
        final int NORMAL = 0, HOVER = 1, CLICKED = 2;
        shapes.setAll(new RoundRect(100, 100, 200, 400, 10));
        shapes.setAllStrokes(new IColor(0.3f));
        shapes.setFill(IColor.RED, NORMAL, CLICKED);
        shapes.setFill(IColor.GREEN, HOVER);
        shapes.construct(true);
    }

    @Override
    public ShapeStates clone() {
        try {
            ShapeStates clone = (ShapeStates) super.clone();
            clone.setTo(this);

            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new InternalError(ex);
        }
    }

    private static <T> T[] cloneArray(T[] src) {
        return Arrays.copyOf(src, src.length);
    }

    public ShapeStates setTo(ShapeStates src) {
        this.strokes = cloneArray(src.strokes);
        this.fills = cloneArray(src.fills);
        this.shapes = cloneArray(src.shapes);
        this.afterPaints = cloneArray(src.afterPaints);
        this.noPaintShapes = cloneArray(src.noPaintShapes);
        
        return this;
    }

    public int size() {
        return shapes.length;
    }

}
