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
public class WorldEventStairwellGop extends WorldEvent {
    
    private void init() {
        chance = 85;
    }
    
    WorldEventStairwellGop() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "К Вам подходит уверенного вида молодой человек, и произносит: \"Дай сотен на семки, ёпта!\" " +
                "\n(1)" +
                "Дать сто рублей\n(2)Отказать\n";
        
        if (player.getItemOfType(new PlayerItemMoney()) == null) {
            setActive(false);
        }
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        
        
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            if (iValue == 1) {
                renderString = "";
                if ((new Random()).nextInt(100) < 50) {
                    lifeEffect = -1;
                    renderString += "\"Ха-ха, бывай\" - Он уходит, больно хлопая Вас по плечу.\n";
                    player.dropItem(player.getItemOfType(new PlayerItemMoney()));
                    
                } else {
                    lifeEffect = 0;
                    renderString += "\"Спасибо внатуре, ха\" - Он благодарит Вас и уходит.\n";
                    player.dropItem(player.getItemOfType(new PlayerItemMoney()));
                    
                }
                done = true;
            } else if (iValue == 2) {
                renderString = "";
                if ((new Random()).nextInt(100) < 15) {
                    lifeEffect = -2;
                    renderString += "Васян ударяет Вас " +
                            "по почкам, " +
                            "отобрав деньги он уходит. \n";
                    player.dropItem(player.getItemOfType(new PlayerItemMoney()));
                    
                } else {
                    
                    lifeEffect = 0;
                    renderString += "Уважая Ваш авторитет, как человека интелегентного и влиятельного, Васян " +
                            "скрывается из виду.\n";
                }
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
