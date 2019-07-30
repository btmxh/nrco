/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.IColor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class ParticleHandler {
    private int i;
    private List<Particle> particles;
    
    public ParticleHandler() {
        particles = new ArrayList<>();
    }
    
    public void update(float delta) {
        if(i++ % 10 == 0) {
            particles.add(new Particle(LWJGL.mouse.getCursorPosition(), IColor.WHITE));
        }
        for (Iterator<Particle> it = particles.iterator(); it.hasNext();) {
            if(!it.next().update(delta)) {
                it.remove();
            }
        }
    } 
    
    public void render() {
        particles.forEach(p -> p.render(LWJGL.graphics));
    }
    
    class Particle {
        public Vector2f pos, vel, acc;
        public IColor color;
    
        public Particle(Vector2f position, IColor color){
            pos = new Vector2f(position);
            this.color = new IColor(color);
            acc = new Vector2f(0, 0.05f);
            vel = new Vector2f((float) Math.random() * 2 - 1, (float) Math.random() * 2 - 2);
        }

        public boolean update(float delta) {
            vel.add(acc);
            pos.add(vel);

            color = color.alpha(color.a - delta / 3f);
            
            return color.a > 0;
        }

        public void render(NVGGraphics g){
            g.circle(pos.x, pos.y, 2);
            g.fill(color);
        }
    }
}
