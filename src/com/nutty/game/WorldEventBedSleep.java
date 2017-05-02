/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;


import com.nutty.utils.ExpressionPart;

/**
 * Created by SashaBoss on 07.11.2016.
 */
public class WorldEventBedSleep extends WorldEvent {
    
    private void init() {
        chance = 100;
    }
    
    WorldEventBedSleep() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        renderString = "Невыносимая тяжесть тянет ваши веки вниз. Вы решаете: \n(1)Забыть про учёбу и заснуть.\n(2)" +
                "Сопротивляться и подниматься с кровати.\n";
        
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            if (iValue == 1) {
                renderString = "Вы засыпаете, а проснувшись вспоминаете, что это был последний шанс сдать экзамен, и " +
                        "Вас отчисляют.\n";
                lifeEffect = -100;
                done = true;
            } else if (iValue == 2) {
                renderString = "Вы пытаетесь собрать волю в кулак, но не в силах устоять, вы засыпаете, а проснувшись" +
                        " вспоминаете, что это был последний шанс сдать экзамен, и Вас " +
                        "отчисляют.\n";
                lifeEffect = -100;
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
