/*
 * Sasha Knorre
 *
 * No Rights Reserved
 *
 */

package com.nutty.game;


import com.nutty.utils.ExpressionPart;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created by SashaBoss on 26.10.2016.
 */
public class World {
    
    
    private boolean isGameOver;
    private boolean firstUpdate;
    private int startLives = 0;
    
    private String renderString;
    private ArrayList<WorldLocation> locations;
    private Player player;
    
    private void init(int liveCount) {
        
        isGameOver = false;
        firstUpdate = true;
        locations = new ArrayList<>();
        startLives = liveCount;
        
        generateWorld();
        
        
    }
    
    // Создание локаций, событий и игрока
    private void generateWorld() {
        WorldLocation location0 = new WorldLocation("Дом",
                "Вы сидите у тёплого очага и слушаете как скрипят стены под гнётом уличной вьюги. Окна разукрашены " +
                        "ледяными узорами, а порог истоптан снегом. Вам нужно идти на учёбу" +
                        ".", 1, 2);
        location0.addEvent(new WorldEventBeer());
        locations.add(location0);
        
        WorldLocation location1 = new WorldLocation("Уютная кровать", "Тёплая, мягкая, манящая, она поглощает Вас.", 0);
        location1.addEvent(new WorldEventBedSleep());
        locations.add(location1);
        
        WorldLocation location2 = new WorldLocation("Подъезд", "Узкий и тёмный, он пугает Вас и видом, и " +
                "запахом.", 0,
                3, 4);
        location2.addEvent(new WorldEventStairwellGop());
        locations.add(location2);
        
        WorldLocation location3 = new WorldLocation("Соседка", "Старая и не прибранная квартира ленивой хозяйки. " +
                "Здесь" +
                " живёт Ваш давний друг, Катя.", 2);
        location3.addEvent(new WorldEventBeer());
        location3.addEvent(new WorldEventKatya());
        location3.addEvent(new WorldEventKatyaPizza());
        locations.add(location3);
        
        WorldLocation location4 = new WorldLocation("Улица Фонарная", "Знакомая с детства улица предстаёт Вашему " +
                "взору. Стоят деревянные лавочки заметённые снегом. Идёт девочка и несёт на руках одетую в костюм " +
                "собаку. Из сугробов выглядывают капоты автомобилей.", 2, 5, 6, 7);
        location4.addEvent(new WorldEventStreetWalk());
        locations.add(location4);
        
        WorldLocation location5 = new WorldLocation("Ларёк Седьмая Помидорка", "Внутри данного магазина Вы видите " +
                "овощи, фрукты, напитки, мороженное, а также продовца - молодую Людмилу Ивановну.", 4);
        location5.addEvent(new WorldEventShopQuestion());
        locations.add(location5);
        
        WorldLocation location6 = new WorldLocation("Станция метро Лунтика", "Старая как мир, эта станция никогда не " +
                "закрывалась на ремонт, что Вы считали её плюсом, потому что она никогда не подводила Вас, в отличии " +
                "от автобусов и трамваев,\nиз-за которых Вы нередко не успевали на пары по Программированию", 8);
        location6.addEvent(new WorldEventMetroLuntika());
        location6.addEvent(new WorldEventMetroGop());
        locations.add(location6);
        
        WorldLocation location7 = new WorldLocation("Автобусная остановка Скороспейкина", "Сюда ходит автобус №14 " +
                "который может отвезти Вас в институт. Нужно лишь верить, что он приедет.", 8);
        location7.addEvent(new WorldEventBusStopSkorospeykin());
        locations.add(location7);
        
        WorldLocation location8 = new WorldLocation("Институт", "Трех-этажное здание, давно устаревшее, с плохими " +
                "стенами и окнами," +
                " оно всё же" +
                " радует глаз.");
        location8.addEvent(new WorldEventExam());
        locations.add(location8);
        
        
        player = new Player(startLives, location0);
        player.giveItem(new PlayerItemMoney(), false);
    }
    
    public World(int lifeCount) {
        init(lifeCount);
    }
    
    
    // Проверка вводимых команд и соответствующая им реакция + подготовка вывода текста
    public void update(Input input) {
        
        renderString = "";
        
        
        String command = input.getLastCommand();
        
        if (command.equals("new")) {
            restart();
        }
        
        if (isGameOver || player.isPassedExam() || player.isFailedExam()) {
            return;
        }
        
        if (command.equals("help") || firstUpdate) {
            prepareHelpInfo();
            
        }
        if (command.equals("status")) {
            prepareStatusInfo();
        }
        
        
        WorldEvent activeEvent = null;
        
        for (int iEvent = 0; iEvent < player.getCurrentLocation().getEventCount(); iEvent++) {
            WorldEvent event = player.getCurrentLocation().getEvent(iEvent);
            if (event.isActive()) {
                activeEvent = event;
                break;
            }
        }
        
        if (activeEvent != null) {
            activeEvent.onUpdate(player, command);
            if (activeEvent.isDone()) {
                activeEvent.onDone(player);
                
            }
            
            
        } else {
            playerMovement(command, player);
            
        }
        
        // render stuff
        
        renderString += "Местоположение: " + player.getCurrentLocation().getName() + "\n";
        
        activeEvent = null;
        
        for (int iEvent = 0; iEvent < player.getCurrentLocation().getEventCount(); iEvent++) {
            WorldEvent event = null;
            try {
                event = player.getCurrentLocation().getEvent(iEvent);
            } catch (Exception e) {
                System.out.println("Event index out of bounds");
            }
            if (event.isActive()) {
                activeEvent = event;
                break;
            }
        }
        
        if (activeEvent != null) {
            renderString += activeEvent.getRenderString();
            if (activeEvent.isDone()) {
                activeEvent.setActive(false);
                for (int iEvent = 0; iEvent < player.getCurrentLocation().getEventCount(); iEvent++) {
                    WorldEvent event = null;
                    try {
                        event = player.getCurrentLocation().getEvent(iEvent);
                    } catch (Exception e) {
                        System.out.println("Event index out of bounds");
                    }
                    if (event.isActive()) {
                        activeEvent = event;
                        renderString += activeEvent.getRenderString();
                        break;
                    }
                }
            }
        }
        
        for (int iItem = 0; iItem < player.getItemCount(); iItem++) {
            
            PlayerItem item = player.getItem(iItem);
            item.onUpdate(player);
            renderString += item.getRenderString();
        }
        
        renderString += player.getRenderString();
        player.setRenderString("");
        
        if (activeEvent == null || !activeEvent.isActive()) {
            
            renderString += "Описание: " + player.getCurrentLocation().getDescription() + "\n";
            renderString += "Ты можешь пойти (напечатай цифру):\n";
            for (int iPath = 0; iPath < player.getCurrentLocation().getPathCount(); iPath++) {
                
                Integer path = player.getCurrentLocation().getPath(iPath);
                WorldLocation location = getLocation(path);
                renderString += "(" + (iPath + 1) + ") " + location.getName() + "\n";
                
            }
        }
        
        firstUpdate = false;
        
        examCheck();
        deathCheck();
        
    }
    
    // Перезапуск игры с начала
    private void restart() {
        init(startLives);
    }
    
    
    // Вывод после команды help
    private void prepareHelpInfo() {
        renderString += "------------------\n{HELP}\nПриветствую тебя, Студент! Твоя цель - добраться до " +
                "института.\n";
        
        if (player.getLiveCount() < 3) {
            renderString += "Но будь осторожен, ведь у тебя осталось только " + player
                    .getLiveCount() + " ед. жизни.\n";
        } else {
            renderString += "У тебя " + player
                    .getLiveCount() + " ед. жизни.\n";
        }
        
        renderString += "Напиши \"help\" чтобы вновь увидеть данное сообщение, \"status\" чтобы " +
                "увидеть информацию о себе, \"new\" чтобы начать заного, \"exit\" чтобы выйти.\n------------------\n";
    }
    
    // Вывод после команды status
    private void prepareStatusInfo() {
        renderString += "------------------\n{STATUS}\nЖизни: " + player.getLiveCount() + ", Местоположение: " + player
                .getCurrentLocation().getName() + ", Предметы:\n";
        for (int iItem = 0; iItem < player.getItemCount(); iItem++) {
            renderString += "(" + (iItem + 1) + ") ";
            PlayerItem item = player.getItem(iItem);
            renderString += item.getName() + "\n";
        }
        renderString += "------------------\n";
    }
    
    // Анализ и исполнение команды на передвижение
    private void playerMovement(String command, Player player) {
        WorldLocation currentLocation = player.getCurrentLocation();
        
        if (ExpressionPart.ExpressionPartType.toPartType(command) == ExpressionPart.ExpressionPartType
                .EXPRESSION_PART_TYPE_INTEGER) {
            Integer iPath = Integer.valueOf(command) - 1;
            if (iPath >= 0 && iPath < currentLocation.getPathCount()) {
                try {
                    Integer path = currentLocation.getPath(iPath);
                    WorldLocation nextLocation = locations.get(path);
                    player.setCurrentLocation(nextLocation);
                    procEvents(nextLocation);
                    
                } catch (Exception e) {
                    System.out.println("Path index out of bounds");
                }
            }
        }
        
    }
    
    // Проверка происходит после обновления предметов, чтобы спасающие от смерти предметы успели восстановить жизнь
    private void deathCheck() {
        if (player.getLiveCount() <= 0) {
            renderString += "Игра окончена. Вы проиграли.\nНапишите \"new\" чтобы начать заного.\n";
            isGameOver = true;
        }
    }
    
    // Проверка условий выигрыша/поражения
    private void examCheck() {
        if (player.isPassedExam()) {
            renderString += "Игра окончена. Вы выиграли!!! Поздравляем Вас!!!\nНапишите \"new\" чтобы начать заного" +
                    ".\n";
            isGameOver = true;
        }
        if (player.isFailedExam()) {
            renderString += "Игра окончена. Вы проиграли, но были очень близки к цели.\nНапишите \"new\" чтобы начать" +
                    " заного" +
                    ".\n";
            isGameOver = true;
        }
    }
    
    // Посещение локации даёт возможность событиям этих локаций активироваться
    private void procEvents(WorldLocation location) {
        for (int iEvent = 0; iEvent < location.getEventCount(); iEvent++) {
            WorldEvent event = null;
            try {
                event = location.getEvent(iEvent);
            } catch (Exception e) {
                System.out.println("Event index out of bounds");
                return;
            }
            if (!event.isDone()) {
                
                if ((new Random().nextInt(99) + 1) <= event.getChance()) {
                    event.onInit(player);
                }
                
            }
        }
    }
    
    // Игрок
    public Player getPlayer() {
        return player;
    }
    
    // Список локаций
    public int getLocationCount() {
        return locations.size();
    }
    
    public WorldLocation getLocation(int iLocation) throws RuntimeException {
        if (iLocation < 0 || iLocation >= locations.size()) {
            throw new RuntimeException("Location index out of bounds");
        }
        
        return locations.get(iLocation);
    }
    
    public Integer getLocationId(String searchLocation) {
        for (int iLocation = 0; iLocation < getLocationCount(); iLocation++) {
            WorldLocation location = getLocation(iLocation);
            if (Objects.equals(location.getName(), searchLocation)) {
                return iLocation;
            }
        }
        
        return null;
    }
    
    // Строка вывода
    public String getRenderString() {
        return renderString;
    }
    
}
