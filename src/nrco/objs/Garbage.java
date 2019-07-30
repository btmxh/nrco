/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.objs;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.utils.GameObject;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.geom.PaintedShape;
import com.lwjglwrapper.utils.geom.shapes.Circle;
import com.lwjglwrapper.utils.geom.shapes.Rect;
import com.lwjglwrapper.utils.math.MathUtils;
import nrco.states.GameState;
import nrco.ui.RectUtils;
import static nrco.ui.RectUtils.center;
import nrco.utils.RenderableTexture;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class Garbage implements GameObject{

    private GameState gameState;
    private PaintedShape<Circle> circle;
    private float maxRadius;
    private float age = 0, maxAge; //seconds
    private RenderableTexture texture;
    
    int type;

    public Garbage(GameState gameState, Vector2f position, float maxRadius, float maxAge, int type) {
        this.circle = new PaintedShape<>(new Circle(position, 0), IColor.TRANSPARENT, IColor.GREEN);
        this.gameState = gameState;
        this.maxAge = maxAge;
        this.maxRadius = maxRadius;
        this.type = type;
        texture = new RenderableTexture(gameState.getGame().assets.randomTrash(type));
    }
    
    @Override
    public void update(float delta) {
        age += delta;
        circle.getShape().radius = MathUtils.clamp(0f, circle.getShape().radius + maxRadius / maxAge * delta, maxRadius);
        if(circle.getShape().contains(LWJGL.mouse.getCursorPosition())) {
            gameState.garbageHandler.hold(this);
        }
        clampCenter();
        if(age >= maxAge) {
            gameState.garbageHandler.blowUp(this);
            int pt = 0;
            for (TrashCan can : gameState.trashCans) {
                if(can.contains(circle.getShape().center)) {
                    pt = 50;
                    if(can.getType() == this.type)  pt = 200; 
                }
            }
            gameState.addPoint(pt, circle.getShape().center);
        }
        
        IColor stroke = ((IColor) circle.getStroke());
        float[] hsv = stroke.hsv();
        hsv[0] = MathUtils.map(age / maxAge, 0, 1, 0.5f, 0f);
        circle.setStroke(IColor.hsv(hsv[0], hsv[1], hsv[2]));
    }
    
    public void onHold(float delta, int index) {
        circle.getShape().center.set(LWJGL.mouse.getCursorPosition());
        clampCenter();
    }

    @Override
    public void render() {
        LWJGL.graphics.strokeWeight(circle.getShape().contains(LWJGL.mouse.getCursorPosition())? 6:3);
        circle.render(LWJGL.graphics);
        LWJGL.graphics.strokeWeight(1);
    }
    
    public void renderTexture() {
        float textureSize = circle.getShape().radius * 1.25f;
        Vector2f center = circle.getShape().center;
        texture.render(RectUtils.center(center.x, LWJGL.window.getHeight() - center.y, textureSize, textureSize * ((Texture2D) texture.getTexture()).getTextureData().getAspectRatio()));
    }
    
    Vector2f getCenter() {
        return circle.getShape().center;
    }
    
    public void clampCenter() {
        Vector2f center = circle.getShape().center;
        center.x = MathUtils.clamp(0f, center.x, (float) LWJGL.window.getWidth());
        center.y = MathUtils.clamp(0f, center.y, (float) LWJGL.window.getHeight());
    }

    public boolean sameType(Garbage g) {
        return type == g.type;
    }
    
}
