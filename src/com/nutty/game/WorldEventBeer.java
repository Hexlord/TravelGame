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
public class WorldEventBeer extends WorldEvent {
    
    private void init() {
        chance = 15;
    }
    
    WorldEventBeer() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "На диване Вы видите недопитую банку пива и решаете: \n(1)Допить её\n(2)Выбросить вредный " +
                "напиток\n";
        
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
                    renderString += "После напитка Вы плохо себя чувствуете.\n";
                    
                } else {
                    lifeEffect = 1;
                    renderString += "Вы чувствуете приток сил после бодрящего напитка!\n";
                    
                    
                }
                done = true;
            } else if (iValue == 2) {
                renderString = "";
                if ((new Random()).nextInt(100) < 10) {
                    lifeEffect = -2;
                    renderString += "Вот ведь растяпа, бросив банку в мусорку Вы попадаете в окно, а осколок " +
                            "отломившегося " +
                            "стекла " +
                            "впивается Вам в ногу!\n";
                    
                } else if ((new Random()).nextInt(100) < 10) {
                    lifeEffect = -1;
                    renderString += "Ощущая игривость и ловко кинув банку в пробегающего кота Вы злите его, а на " +
                            "Вашем теле появляются царапины.\n";
                    
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
