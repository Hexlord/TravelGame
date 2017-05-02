/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;


import com.nutty.utils.ExpressionPart;

import java.util.Random;

/**
 * Created by SashaBoss on 07.11.2016.
 */
public class WorldEventBusStopSkorospeykin extends WorldEvent {
    
    private void init() {
        chance = 100;
    }
    
    WorldEventBusStopSkorospeykin() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "Стоя на остановке Вы решаете: \n" +
                "(1)Вернуться на ул. Фанарную\n";
        
        if (player.getItemOfType(new PlayerItemMoney()) != null) {
            renderString += "(2)Ждать автобус\n";
        }
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            boolean hasCoin = false;
            if (player.getItemOfType(new PlayerItemMoney()) != null) {
                hasCoin = true;
            }
            if (iValue == 1) {
                renderString = "";
                player.setCurrentLocation(player.getPrevLocation());
            } else if (iValue == 2 && hasCoin) {
                
                if ((new Random()).nextInt(100) < 5) {
                    renderString = "А вот и автобус! Вы наконец-то отправились в институт.\n";
                    player.dropItem(player.getItemOfType(new PlayerItemMoney()));
                } else if ((new Random()).nextInt(100) < 26) {
                    lifeEffect = -1;
                    renderString = "Автобуса всё нет. Холод терзает Вас.\n";
                } else if ((new Random()).nextInt(100) < 13) {
                    lifeEffect = 0;
                    renderString = "Ну когда же, блин, приедет мой автобус?\n";
                } else if ((new Random()).nextInt(100) < 13) {
                    lifeEffect = 0;
                    renderString = "Ах, сколько еще мне ждать?\n";
                } else {
                    lifeEffect = 0;
                    renderString = "Автобус ещё не приехал.\n";
                }
                
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
