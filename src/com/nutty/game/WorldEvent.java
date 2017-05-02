/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

/**
 * Created by SashaBoss on 07.11.2016.
 */
public class WorldEvent {
    
    protected String renderString;
    protected boolean active;
    protected boolean done;
    protected int chance;
    protected int lifeEffect;
    
    private void init() {
        renderString = "";
        done = false;
        active = false;
        chance = 0;
        lifeEffect = 0;
    }
    
    WorldEvent() {
        init();
    }
    
    public void onInit(Player player) {
        
        active = true;
    }
    
    
    // Вызывается если событие активно
    public void onUpdate(Player player, String command) {
        
    }
    
    // Вызывается по завершению события
    public void onDone(Player player) {
        if (lifeEffect == 0) {
            // do nothing
        } else {
            if (lifeEffect == 1) {
                renderString += "Получена 1 ед. жизни!\n";
            } else if (lifeEffect == -1) {
                renderString += "Потеряна 1 ед. жизни!\n";
            } else if (lifeEffect > 1) {
                renderString += "Получены " + lifeEffect + " ед. жизни!\n";
            } else if (lifeEffect < -1) {
                renderString += "Потеряны " + lifeEffect + " ед. жизни!\n";
            }
            player.setLiveCount(player.getLiveCount() + lifeEffect);
            renderString += "Теперь у вас " + player.getLiveCount() + " ед. жизни\n";
        }
    }
    
    
    // Строка вывода
    public String getRenderString() {
        return renderString;
    }
    
    public void setRenderString(String renderString) {
        this.renderString = renderString;
    }
    
    // Флаги активности и завершенности
    public boolean isDone() {
        return done;
    }
    
    public void setDone(boolean done) {
        this.done = done;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    // Влияние на жизнь и шанс происхождения
    public int getLifeEffect() {
        return lifeEffect;
    }
    
    public int getChance() {
        return chance;
    }
    
    
}
