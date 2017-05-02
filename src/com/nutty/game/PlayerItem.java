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
public class PlayerItem {
    
    protected String name;
    protected String description;
    protected String renderString;
    
    private void init() {
        setRenderString("");
        setName(null);
        setDescription(null);
    }
    
    public PlayerItem() {
        init();
    }
    
    // Базовая функция, вызываемая на каждом шаге игрока при наличии у него этого предмета
    public void onUpdate(Player player) {
        renderString = "";
    }
    
    // Инфо о предмете
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    // Текстовый вывод предмета
    public String getRenderString() {
        return renderString;
    }
    
    public void setRenderString(String renderString) {
        this.renderString = renderString;
    }
}
