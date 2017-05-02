/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

/**
 * Created by SashaBoss on 15.11.2016.
 */
public class PlayerItemMetroCoin extends PlayerItem {
    
    private void init() {
        setName("Жетон Метрополитена");
        setDescription("Немного мокрый, поднятый из снега. Он позволит воспользоваться Метро Вашего города Х.");
        
    }
    
    public PlayerItemMetroCoin() {
        super();
        init();
    }
    
    // См. PlayerItem
    public void onUpdate(Player player) {
        
    }
    
    
}
