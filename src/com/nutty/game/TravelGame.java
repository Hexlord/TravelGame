/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

import static java.lang.System.exit;

/**
 * Created by SashaBoss on 26.10.2016.
 */
public class TravelGame {
    
    private final double UPDATE_STEP = (1.0 / 60.0);
    private final double UPDATE_STEP_LIMIT = (10.0);
    
    private final int GAME_START_LIFE_COUNT = (3);
    
    
    private Input input;
    private World world;
    private Renderer renderer;
    
    private void init() {
        input = new Input();
        world = new World(GAME_START_LIFE_COUNT);
        renderer = new Renderer();
        
        try {
            WorldReader worldReader = new WorldReader("world.txt", world);
        } catch (Exception e) {
            System.out.println("Файл сохранения не найден");
            
        }
        
        
    }
    
    public TravelGame() {
        init();
    }
    
    
    // Программный цикл обновления систем основанный на вводе команд
    public void run() {
        
        boolean isRunning = true;
        
        double lastUpdate = System.currentTimeMillis();
        
        world.update(input);
        renderer.update(world);
        
        while (isRunning) {
            double delta = lastUpdate - System.currentTimeMillis();
            
            if (delta / UPDATE_STEP > UPDATE_STEP_LIMIT) {
                lastUpdate = System.currentTimeMillis() - UPDATE_STEP * UPDATE_STEP_LIMIT;
            }
            
            if (delta >= UPDATE_STEP) {
                lastUpdate += UPDATE_STEP;
                
                // ignore time cycle
            }
            
            // command cycle
            input.update();
            
            if (input.getLastCommand().equals("exit")) {
                isRunning = false;
                break;
            }
            
            world.update(input);
            renderer.update(world);
            
            
        }
        
        try {
            WorldWriter worldWriter = new WorldWriter("world.txt", world);
        } catch (Exception e) {
            System.out.println("Ошибка 1 - Не удалось сохранить игру");
            exit(1);
        }
        
    }
    
    
}
