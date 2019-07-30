/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.cameras;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.display.Mouse;
import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.utils.input.KeyBindings;
import com.lwjglwrapper.utils.math.MathUtils;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class FirstPersonCamera extends Camera {

    private float hspeed, vspeed, rspeed;
    private boolean fps;    //user doesn't need to hold down the mouse to look around, like fps cameras in Minecraft or First Person Shooter games

    public FirstPersonCamera(float fov, Window window, float zNear, float zFar) {
        super(fov, window, zNear, zFar);
    }

    public FirstPersonCamera(Vector3f position, float fov, Window window,
            float zNear, float zFar) {
        super(position, fov, window, zNear, zFar);
    }

    public FirstPersonCamera(Vector3f position, Vector3f rotation, float fov,
            Window window, float zNear, float zFar) {
        super(position, rotation, fov, window, zNear, zFar);
    }

    public void setSpeed(float hspeed, float vspeed, float rspeed) {
        this.hspeed = hspeed;
        this.vspeed = vspeed;
        this.rspeed = rspeed;
    }

    @Override
    public void move(KeyBindings keys, float delta) {
        super.move(keys, delta);
        moveHorizontal(keys, delta);
        moveVertical(keys, delta);
        calculateRotation(LWJGL.mouse);
    }

    private void moveHorizontal(KeyBindings keys, float delta) {
        Vector2f vel = new Vector2f();
        if (keys.anyDown("forward")) {
            vel.add(-1, 0);
        }
        if (keys.anyDown("backward")) {
            vel.add(1, 0);
        }
        if (keys.anyDown("left")) {
            vel.add(0, 1);
        }
        if (keys.anyDown("right")) {
            vel.add(0, -1);
        }
        if (vel.equals(0, 0)) {
            return;
        }
        
        vel = MathUtils.rotate(vel, 90 + rotation.y).normalize(hspeed * delta);
        
        position.add(vel.x, 0, vel.y);
    }

    private void moveVertical(KeyBindings keys, float delta) {
        if(keys.anyDown("up")) {
            position.add(0, vspeed * delta, 0);
        } else if(keys.anyDown("down")) {
            position.add(0, -vspeed * delta, 0);
        }
    }
    private void calculateRotation(Mouse mouse) {
        if(mouse.mouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT) | fps) {
            rotation.x += mouse.getDeltaY() * rspeed;
            rotation.y -= -mouse.getDeltaX() * rspeed;
            
            rotation.x = MathUtils.clamp(-89.9f, rotation.x, 89.9f);
        }
    }

    public void setFirstPersonCameraMode(boolean fps) {
        this.fps = fps;
    }
    
    

}
