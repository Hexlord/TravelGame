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
public class PlayerItemMoney extends PlayerItem {
    
    private void init() {
        setName("Деньги");
        setDescription("Сотня рублей крупной купюрой.");
        
    }
    
    public PlayerItemMoney() {
        super();
        init();
    }
    
    // См. PlayerItem
    public void onUpdate(Player player) {
        
    }
    
    
}
