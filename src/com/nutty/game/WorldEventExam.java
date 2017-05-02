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
public class WorldEventExam extends WorldEvent {
    
    private void init() {
        chance = 100;
    }
    
    WorldEventExam() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "Ваш билет по программированию под номером 13:\nМожете ли вы переопределить статический метод " +
                "в " +
                "Java? Если создать метод с таким же " +
                "именем в классе-потомке, вызовет ли это ошибку компиляции?: \n(1)Да, Да\n(2)Да, Нет\n(3)Нет, Да\n" +
                "(4)Нет, Нет\n";
        
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            if (iValue == 1) {
                renderString = "Какая глупость! Не верно!!! Вы отчислены!\n";
                player.setFailedExam(true);
                done = true;
                
            } else if (iValue == 2) {
                renderString = "Не верно!!! Вы отчислены!\n";
                player.setFailedExam(true);
                done = true;
            } else if (iValue == 3) {
                renderString = "Верно!!! А Вы знаток! Вот Ваша 5-ка, позвольте пожать Вам Руку!\n";
                player.setPassedExam(true);
                done = true;
            } else if (iValue == 4) {
                renderString = "Не верно!!! Вы отчислены!\n";
                player.setFailedExam(true);
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
