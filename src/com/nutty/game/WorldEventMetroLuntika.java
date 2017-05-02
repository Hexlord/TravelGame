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
public class WorldEventMetroLuntika extends WorldEvent {
    
    private void init() {
        chance = 100;
    }
    
    WorldEventMetroLuntika() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "Чтобы проехать в метро Вам нужен жетон Метро, но купить их здесь нельзя по прихоти " +
                "сценариста данной игры: \n" +
                "(1)Выйти из метро\n";
        
        if (player.getItemOfType(new PlayerItemMetroCoin()) != null) {
            renderString += "(2)Воспользоваться жетоном\n";
        }
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            boolean hasCoin = false;
            if (player.getItemOfType(new PlayerItemMetroCoin()) != null) {
                hasCoin = true;
            }
            if (iValue == 1) {
                renderString = "";
                player.setCurrentLocation(player.getPrevLocation());
            } else if (iValue == 2 && hasCoin) {
                renderString = "";
                player.dropItem(player.getItemOfType(new PlayerItemMetroCoin()));
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
