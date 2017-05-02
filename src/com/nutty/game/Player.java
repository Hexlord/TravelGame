/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

import java.util.ArrayList;

/**
 * Created by SashaBoss on 27.10.2016.
 */
public class Player {
    
    private boolean isPassedExam;
    private boolean isFailedExam;
    private WorldLocation currentLocation;
    private WorldLocation prevLocation;
    private int liveCount;
    private ArrayList<PlayerItem> items;
    private String renderString;
    
    private void init(int liveCount, WorldLocation startLocation) {
        setPassedExam(false);
        setFailedExam(false);
        prevLocation = startLocation;
        this.currentLocation = startLocation;
        this.liveCount = liveCount;
        this.items = new ArrayList<>();
        setRenderString("");
    }
    
    public Player(int liveCount, WorldLocation startLocation) {
        init(liveCount, startLocation);
    }
    
    // Текущая локация
    public WorldLocation getCurrentLocation() {
        return currentLocation;
    }
    
    public void setCurrentLocation(WorldLocation currentLocation) {
        this.prevLocation = this.currentLocation;
        this.currentLocation = currentLocation;
    }
    
    // Список предметов
    public int getItemCount() {
        return items.size();
    }
    
    public void giveItem(PlayerItem item, boolean message) {
        if (item == null) {
            throw new RuntimeException("Can not drop null item");
        }
        
        items.add(item);
        if (message) {
            setRenderString(getRenderString() + "Вы получили предмет: \"" + item.getName() + "\"!\n");
            setRenderString(getRenderString() + item.getDescription() + "\n");
        }
    }
    
    public void giveItem(PlayerItem item) {
        giveItem(item, true);
    }
    
    public PlayerItem getItem(int iItem) {
        if (iItem < 0 || iItem >= items.size()) {
            throw new RuntimeException("Item index out of bounds");
        }
        return items.get(iItem);
    }
    
    public PlayerItem getItemOfType(PlayerItem type) {
        for (int iItem = 0; iItem < getItemCount(); iItem++) {
            PlayerItem item = getItem(iItem);
            if (item.getClass() == type.getClass()) {
                return item;
            }
        }
        return null;
    }
    
    public void dropItem(PlayerItem item, boolean message) {
        if (item == null) {
            throw new RuntimeException("Can not drop null item");
        }
        
        if (message) {
            setRenderString(getRenderString() + "Вы потеряли предмет: \"" + item.getName() + "\"!\n");
        }
        items.remove(item);
    }
    
    public void dropItem(PlayerItem item) {
        dropItem(item, true);
    }
    
    public void dropItemAll(boolean message) {
        while (items.size() > 0) {
            dropItem(items.get(0), message);
        }
    }
    
    public void dropItemAll() {
        dropItemAll(true);
    }
    
    
    // Жизни
    public int getLiveCount() {
        return liveCount;
    }
    
    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }
    
    // Текстовый вывод игрока
    public String getRenderString() {
        return renderString;
    }
    
    public void setRenderString(String renderString) {
        this.renderString = renderString;
    }
    
    
    // Предыдущая локация
    public WorldLocation getPrevLocation() {
        return prevLocation;
    }
    
    
    // Флаги победы/поражения
    public Boolean isPassedExam() {
        return isPassedExam;
    }
    
    public void setPassedExam(Boolean passedExam) {
        isPassedExam = passedExam;
    }
    
    public Boolean isFailedExam() {
        return isFailedExam;
    }
    
    public void setFailedExam(Boolean failedExam) {
        isFailedExam = failedExam;
    }
}
