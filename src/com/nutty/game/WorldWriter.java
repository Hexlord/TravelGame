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
public class WorldWriter {
    
    private void init() {
        
    }
    
    public WorldWriter() {
        
        init();
    }
    
    public WorldWriter(String filePath, World world) throws Exception {
        
        init();
        writeToFile(filePath, world);
    }
    
    
    // Сохранение в файл
    public void writeToFile(String filePath, World world) throws Exception {
        
        //TO DO: read player position and live count
        
        MyFile file = new MyFile(filePath, MyFile.MY_FILE_MODE.MY_FILE_MODE_WRITE);
        file.writeLine(Integer.toString(world.getPlayer().getLiveCount()));
        file.writeLine(Boolean.toString(world.getPlayer().isPassedExam()));
        // TO DO: save items
        file.writeLine(Integer.toString(world.getPlayer().getItemCount()));
        for (int iItem = 0; iItem < world.getPlayer().getItemCount(); iItem++) {
            PlayerItem item = world.getPlayer().getItem(iItem);
            file.writeLine(item.getClass().getName());
        }
        
        file.writeLine(world.getPlayer().getCurrentLocation().getName());
        for (int iLocation = 0; iLocation < world.getLocationCount(); iLocation++) {
            WorldLocation location = world.getLocation(iLocation);
            for (int iEvent = 0; iEvent < location.getEventCount(); iEvent++) {
                WorldEvent event = location.getEvent(iEvent);
                file.writeLine(Boolean.toString(event.isActive()));
                file.writeLine(Boolean.toString(event.isDone()));
            }
        }
        file.clear();
    }
    
    
}
