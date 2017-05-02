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
public class WorldEventMetroGop extends WorldEvent {
    
    private void init() {
        chance = 55;
    }
    
    WorldEventMetroGop() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "К Вам подходит грязной наружности мужик, и произносит: \"Гони деньги, иначе пожалеешь!\" " +
                "\n(1)Отказать\n(2)Драться\n";
        
        if (player.getItemOfType(new PlayerItemMoney()) == null) {
            renderString += "(3)Отдать деньги";
        }
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        
        
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            boolean hasMoney = player.getItemOfType(new PlayerItemMoney()) != null;
            
            if (iValue == 1) {
                renderString = "";
                if ((new Random()).nextInt(100) < 25) {
                    lifeEffect = -5;
                    renderString += "Его глаза сверкнули и он нанёс Вам колющий удар ножом, обыскал Вас и убежал" +
                            ".\n";
                    if (hasMoney) {
                        player.dropItem(player.getItemOfType(new PlayerItemMoney()));
                    }
                    
                } else {
                    lifeEffect = -1;
                    renderString += "Он злится и ударяет Вас в поддых, после чего скрывается.\n";
                    
                }
                done = true;
            } else if (iValue == 2) {
                renderString = "";
                if ((new Random()).nextInt(100) < 35) {
                    lifeEffect = -2;
                    renderString += "Вы резко напали на него, но напоролись на сильный ответный удар, после чего он " +
                            "убежал.";
                    
                } else {
                    
                    lifeEffect = 0;
                    renderString += "Вы оттолкнули его и убежали.\n";
                }
                done = true;
            } else if (iValue == 3 && hasMoney) {
                player.dropItem(player.getItemOfType(new PlayerItemMoney()));
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
