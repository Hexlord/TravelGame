/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;

import java.util.Scanner;

/**
 * Created by SashaBoss on 27.10.2016.
 */
public class Input {
    
    
    private String lastCommand;
    private Scanner scanner;
    
    private void init() {
        setLastCommand("");
        scanner = new Scanner(System.in);
        
    }
    
    public Input() {
        init();
    }
    
    
    // Прочитать новую команду ввода
    public void update() {
        setLastCommand(scanner.nextLine());
        
        
    }
    
    
    // Последняя команда с ввода пользователя
    public String getLastCommand() {
        return lastCommand;
    }
    
    
    // Подменить последнюю команду ввода
    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }
}
