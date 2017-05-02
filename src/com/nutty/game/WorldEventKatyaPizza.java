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
public class WorldEventKatyaPizza extends WorldEvent {
    
    private void init() {
        chance = 25;
    }
    
    WorldEventKatyaPizza() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "На диване Вы видите недоеденную пиццу и решаете: \n(1)Доесть её\n(2)Выбросить её\n";
        
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            if (iValue == 1) {
                renderString = "";
                if ((new Random()).nextInt(100) < 33) {
                    lifeEffect = -1;
                    renderString += "После протухшей пиццы Вы плохо себя чувствуете.\n";
                    
                } else {
                    lifeEffect = 1;
                    renderString += "Вы чувствуете приток сил после вкусной пиццы!\n";
                    
                    
                }
                done = true;
            } else if (iValue == 2) {
                renderString = "";
                if ((new Random()).nextInt(100) < 10) {
                    lifeEffect = -2;
                    renderString += "Вот ведь растяпа, бросив пиццу в мусорку Вы попадаете в Катю, а в ответ она " +
                            "впивается ногтями Вам в ногу!\n";
                    
                } else if ((new Random()).nextInt(100) < 15) {
                    lifeEffect = 1;
                    renderString += "Выброси пиццу, Вы замечаете на кухне аппетитное яблоко, которое с удовольствием " +
                            "съедаете.\n";
                    
                } else {
                    lifeEffect = 0;
                    
                    
                }
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
