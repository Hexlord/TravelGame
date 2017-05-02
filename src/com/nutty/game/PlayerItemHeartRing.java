/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

/**
 * Created by SashaBoss on 15.11.2016.
 */
public class PlayerItemHeartRing extends PlayerItem {
    
    private void init() {
        setName("Кольцо в форме Сердечка");
        setDescription("При столь необычной форме кольца ощущалась его драгоценность. Вы чувствовали, оно светилось, " +
                "хоть этого и не " +
                "замечали Ваши органы зрения.");
        
    }
    
    public PlayerItemHeartRing() {
        super();
        init();
    }
    
    // См. PlayerItem
    public void onUpdate(Player player) {
        if (player.getLiveCount() <= 0) {
            renderString = "Сгорая в ярком пламени Ваше Кольцо в форме Сердечка пропадает, спасая Вам жизнь.\n";
            player.setLiveCount(1);
            renderString += "Теперь у Вас осталось " + player.getLiveCount() + " ед. жизни.\n";
            player.dropItem(this);
        }
    }
    
    
}
