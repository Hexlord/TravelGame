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
public class WorldEventKatya extends WorldEvent {
    
    private void init() {
        chance = 100;
    }
    
    WorldEventKatya() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "Катя рада Вас видеть и хочет услышать Ваше мнение о её новом наряде - Чёрном вечернем платье:" +
                " \n(1)Похвалить выбор Кати\n(2)Раскритиковать\n";
        
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            if (iValue == 1) {
                renderString = "";
                if ((new Random()).nextInt(100) < 65) {
                    lifeEffect = 2;
                    renderString += "Катя очень радуется Вашим словам, а ещё признается в любви и приносит Вам " +
                            "подарок" +
                            ".\n";
                    player.giveItem(new PlayerItemHeartRing());
                } else {
                    lifeEffect = 0;
                    renderString += "Кате не верит Вашим словам и расстраивается.\n";
                    
                    
                }
                done = true;
            } else if (iValue == 2) {
                renderString = "";
                lifeEffect = -1;
                renderString += "Катя злится и начинает плакать, но вскоре перестаёт. Больше она с Вами не " +
                        "заговаривает. С досады Вы бахнули себе кулаком по лбу. \n";
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
