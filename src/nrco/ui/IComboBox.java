/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.ui;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.nanovg.NVGFont;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.Shape;
import com.lwjglwrapper.utils.ui.ComboBox;
import com.lwjglwrapper.utils.ui.Stage;
import org.joml.Rectanglef;
import org.joml.Vector2f;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class IComboBox extends ComboBox{

    private Rectanglef bounds;
    private NVGFont textFont;
    
    private static final IColor COLOR = new IColor(0.34f, 0.80f, 0.45f);
    private static final IColor HOVER_COLOR = new IColor(0.82f);
    
    public IComboBox(Stage stage, float cellHeight, Rectanglef bounds, NVGFont textFont) {
        super(stage, true, new Vector2f(bounds.minX, bounds.maxY), cellHeight);
        this.bounds = bounds;
        this.textFont = textFont;
        shapes.reset().set(new PaintedShape(normalShape(), null, null), NORMAL)
                      .set(new PaintedShape(hoverShape(), null, null), HOVER)
                      .set(new PaintedShape(clickedShape(), null, null), CLICKED);
    }

    private Shape normalShape() {
        return createShape(COLOR, IColor.GREEN.brighter(), IColor.GREEN);
    }

    private Shape hoverShape() {
        return createShape(HOVER_COLOR, IColor.GREEN.brighter(), IColor.GREEN);
    }

    private Shape clickedShape() {
        return createShape(COLOR.darker(), IColor.GREEN.brighter(), IColor.GREEN);
    }
    
    private Shape createShape(IColor color, IColor barColor, IColor arrowColor) {
        return new Shape() {
            @Override
            public Rectanglef boundBox() {
                return bounds;
            }

            @Override
            public void render(NVGGraphics g) {
                g.roundRect(bounds.minX, bounds.minY, bounds.maxX - bounds.minX, bounds.maxY - bounds.minY, 16);
                g.fill(color);
                g.roundRect(bounds.maxX - 20, bounds.minY, 20, bounds.maxY - bounds.minY, 0, 16, 16, 0);
                g.fill(barColor);
                float x1 = bounds.maxX - 15, x2 = bounds.maxX - 5, x3 = bounds.maxX - 10;
                float y1 = bounds.minY + (RectUtils.height(bounds) - 10) / 2, //y1 == y2
                      y3 = bounds.minY + 5;
                
                g.triangle(x1, y1, x2, y1, x3, y3);
                g.fill(arrowColor);
                
                y1 = bounds.maxY - (RectUtils.height(bounds) - 10) / 2;
                y3 = bounds.maxY - 5;
                
                g.triangle(x1, y1, x2, y1, x3, y3);
                g.fill(arrowColor);
                
                if(cells.isEmpty() | selectedIndex < 0) return;
                
                textFont.use();
                
                g.textAlign(NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_MIDDLE);
                g.textSize((bounds.maxY - bounds.minY) * 0.8f);
                g.textPaint(IColor.WHITE);
                g.text(((IComboBoxCell) cells.get(selectedIndex)).content, bounds.minX + 26, (bounds.minY + bounds.maxY) / 2);
            }
        };
    }

    public void setBounds(Rectanglef bounds) {
        this.bounds = bounds;
        this.cellCorners = new Vector2f(bounds.minX, bounds.maxY);
    }

    public void set(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
    
    public static class IComboBoxCell extends ComboBoxCell {
        
        private String content;
        private static final IColor HOVER_COLOR = new IColor(0.23f, 0.60f, 0.86f);
        
        public IComboBoxCell(ComboBox comboBox, String content) {
            super(comboBox, true);
            this.content = content;
            
            shapes.set(new PaintedShape(normalShape(), null, null), NORMAL)
                  .set(new PaintedShape(hoverShape(), null, null), HOVER)
                  .set(new PaintedShape(clickedShape(), null, null), CLICKED);
        }
        
        public Shape normalShape() {
            return createShape(COLOR);
        }
        public Shape hoverShape() {
            return createShape(HOVER_COLOR);
        }
        public Shape clickedShape() {
            return createShape(COLOR.darker());
        }
        
        private Shape createShape(IColor color) {
            return new Shape() {
                @Override
                public Rectanglef boundBox() {
                    IComboBox comboBox = (IComboBox) IComboBoxCell.this.comboBox;
                    return RectUtils.xywh(16, 0, RectUtils.width(comboBox.bounds) - 32, comboBox.getCellHeight());
                }

                @Override
                public void render(NVGGraphics g) {
                    IComboBox comboBox = (IComboBox) IComboBoxCell.this.comboBox;
                    Rectanglef bounds = comboBox.bounds;
                    g.rect(16, 0, RectUtils.width(bounds) - 32, comboBox.getCellHeight());
                    g.fill(color);
                    
                    comboBox.textFont.use();
                    g.textAlign(NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_MIDDLE);
                    g.textSize((bounds.maxY - bounds.minY) * 0.8f);
                    g.textPaint(IColor.WHITE);
                    g.text(content, 26, comboBox.getCellHeight() / 2);
                    
                }
            };
        }

        @Override
        public void render(NVGGraphics g) {
            super.render(g);
        }

        @Override
        public void tick() {
            super.tick();
        }
        
        
        
        
        
    }
}
