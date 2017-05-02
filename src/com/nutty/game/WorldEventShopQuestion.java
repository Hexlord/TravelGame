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
public class WorldEventShopQuestion extends WorldEvent {
    
    private void init() {
        chance = 100;
    }
    
    WorldEventShopQuestion() {
        super();
        init();
        
    }
    
    public void onInit(Player player) {
        super.onInit(player);
        
        renderString = "Увидев знакомого поситителя Люда, отрываясь от книги, восторженно приветствует Вас, сегодня у" +
                " неё " +
                "определенно " +
                "хорошее настроение, Вы решаете: \n" +
                "(1)Попросить жетон на метро\n(2)Подметить восхитительную красоту Люды\n(3)Поздороваться в ответ\n(4)" +
                "Проигнорировать\n";
        
    }
    
    // См. WorldEvent
    public void onUpdate(Player player, String command) {
        super.onUpdate(player, command);
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iValue = Integer.valueOf(command);
            if (iValue == 1) {
                renderString = "";
                if ((new Random()).nextInt(100) < 40) {
                    lifeEffect = 0;
                    renderString += "Удивлённо взглянув на Вас, Люда молча протягивает Вам жетон от метро и " +
                            "принимается читать какую-то книгу.\n";
                    
                    player.giveItem(new PlayerItemMetroCoin());
                } else {
                    lifeEffect = 0;
                    renderString += "Удивлённо взглянув на Вас, Люда принимается искать у себя в сумочке жетон " +
                            "Метрополитена, но не находит ни одного.\n";
                    
                    
                }
                done = true;
            } else if (iValue == 2) {
                renderString = "";
                if ((new Random()).nextInt(100) < 75) {
                    lifeEffect = 1;
                    renderString += "Радостная и благодарная она угощает Вас пачкой Кириешек, а также, помня про " +
                            "Ваш сегодняшний экзамен, интересуется не взяли ли Вы жетон Метро, и, получив " +
                            "отрицательный ответ протягивает один Вам.\n";
                    player.giveItem(new PlayerItemMetroCoin());
                } else {
                    lifeEffect = 1;
                    renderString += "Слегка обрадовавшись Вашей похвале она решает угостить Вас своим недопитым кофе," +
                            " которое она более не хотела пить.\n";
                }
                done = true;
            } else if (iValue == 3) {
                renderString = "";
                lifeEffect = 0;
                renderString += "Люда утремила свой взор в книгу и как будто отключила все свои органы чувств.\n";
                
                done = true;
            } else if (iValue == 4) {
                renderString = "";
                if ((new Random()).nextInt(100) < 50) {
                    lifeEffect = -1;
                    renderString += "Не выдержав такого поведения от старого знакомого она швырнула в Вас книгу, " +
                            "попав Вам в лоб" +
                            ".\n";
                    
                } else {
                    lifeEffect = 0;
                    renderString += "Слегка удивившись Люда принялась дальше читать свою книгу.\n";
                    
                    
                }
                done = true;
            }
        }
    }
    
    public void onDone(Player player) {
        
        super.onDone(player);
        
    }
}
