/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

import com.nutty.utils.MyFile;

/**
 * Created by SashaBoss on 26.10.2016.
 */
public class WorldReader {
    
    private void init() {
        
    }
    
    public WorldReader() {
        
        init();
    }
    
    public WorldReader(String filePath, World world) throws Exception {
        
        init();
        readFromFile(filePath, world);
    }
    
    
    // Загрузка из файла
    public void readFromFile(String filePath, World world) throws Exception {
        
        //TO DO: read player position and live count
        MyFile file = new MyFile(filePath, MyFile.MY_FILE_MODE.MY_FILE_MODE_READ);
        
        String line = file.readLine();
        if (line == null) {
            return;
        }
        
        world.getPlayer().setLiveCount(Integer.valueOf(line));
        line = file.readLine();
        world.getPlayer().setPassedExam(Boolean.valueOf(line));
        
        world.getPlayer().dropItemAll(false);
        int itemCount = Integer.valueOf(file.readLine());
        for (int iItem = 0; iItem < itemCount; iItem++) {
            String className = file.readLine();
            Class cls = Class.forName(className);
            Object item = (Object) cls.newInstance();
            world.getPlayer().giveItem((PlayerItem) item, false);
        }
        
        
        world.getPlayer().setCurrentLocation(world.getLocation(world.getLocationId(file.readLine())));
        for (int iLocation = 0; iLocation < world.getLocationCount(); iLocation++) {
            WorldLocation location = world.getLocation(iLocation);
            for (int iEvent = 0; iEvent < location.getEventCount(); iEvent++) {
                WorldEvent event = location.getEvent(iEvent);
                if (Boolean.valueOf(file.readLine())) {
                    event.onInit(world.getPlayer());
                }
                
                
                event.setActive(Boolean.valueOf(file.readLine()));
                event.setDone(Boolean.valueOf(file.readLine()));
            }
        }
        
    }
    
    
}
