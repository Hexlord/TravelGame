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
public class WorldLocation {
    
    
    private String name;
    private String description;
    private ArrayList<Integer> paths;
    private ArrayList<WorldEvent> events;
    
    private void init() {
        name = null;
        description = null;
        paths = new ArrayList<>();
        events = new ArrayList<>();
    }
    
    public WorldLocation() {
        init();
    }
    
    public WorldLocation(String name, String description, Integer... paths) {
        init();
        this.name = name;
        this.description = description;
        for (Integer path : paths) {
            this.paths.add(path);
            
        }
    }
    
    // Инфо о локации
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
    
    
    // Список путей в другие локации
    public int getPathCount() {
        return paths.size();
    }
    
    public void addPath(Integer path) {
        paths.add(path);
    }
    
    public Integer getPath(int iPath) throws RuntimeException {
        if (iPath < 0 || iPath >= paths.size()) {
            throw new RuntimeException("Index out of bounds");
        }
        
        return paths.get(iPath);
    }
    
    
    // Список событий
    public int getEventCount() {
        return events.size();
    }
    
    public WorldEvent getEvent(int iEvent) throws RuntimeException {
        if (iEvent < 0 || iEvent >= events.size()) {
            throw new RuntimeException("Index out of bounds");
            
        }
        
        return events.get(iEvent);
        
    }
    
    public void addEvent(WorldEvent event) {
        events.add(event);
    }
    
    
}
