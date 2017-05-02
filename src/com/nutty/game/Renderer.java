/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

/**
 * Created by SashaBoss on 26.10.2016.
 */
public class Renderer {
    
    private void init() {
        
    }
    
    public Renderer() {
        init();
        
    }
    
    // Вывод мира в консоль
    public void update(World renderWorld) {
        
        
        
        System.out.println(renderWorld.getRenderString());
    }
    
    
}
