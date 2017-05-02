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
public class WorldEventStreetWalk extends WorldEvent {
    
    private void init() {
        chance = 75;
    }
    
    WorldEventStreetWalk() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "Шагая по улице Вы начали подскользываться и решили: \n(1)Продолжить перебирать " +
                "ногами\n" +
                "(2)Принять устойчивое положение\n(3)Бесцельно замахать руками\n";
        
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            if (iValue == 1) {
                renderString = "";
                if ((new Random()).nextInt(100) < 10) {
                    lifeEffect = -1;
                    renderString += "Вы всё таки подскользнулись и слегка ушиблись.\n";
                    
                } else {
                    lifeEffect = 0;
                    renderString += "Вы преодолели обледеневшую часть тропы и приметили для себя больше там не " +
                            "ходить.\n";
                    
                    
                }
                done = true;
            } else if (iValue == 2) {
                renderString = "";
                lifeEffect = 0;
                renderString += "Широко расставив ноги Вы крепко вцепились в силы трения и смогли удержаться от " +
                        "падения.\n";
                done = true;
            } else if (iValue == 3) {
                if ((new Random()).nextInt(100) < 95) {
                    lifeEffect = 0;
                    renderString += "Это стало шоком для Вас, но Вы взлетели! Нет, показалось... Удачно упав, Вы " +
                            "отряхнулись и увидели перед собой Жетон Метрополитена, который поспешили поднять.\n";
                    player.giveItem(new PlayerItemMetroCoin());
                } else {
                    lifeEffect = -2;
                    renderString += "Вы болезненно упали на одну из свох вытянутых рук и повредили сустав.\n";
                    
                    
                }
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
