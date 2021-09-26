package com.ray3k.template;

import com.ray3k.template.entities.SoldierEntity.*;

public class SaveData {
    public SoldierType[] types = {SoldierType.ASSAULT, SoldierType.SNIPER, SoldierType.HEAVY, null};
    public int coins = 0;
    public int level = 8;
    public int reinforcedLevel = 0;
    public int[] moveSpeed = {2, 0, 0, 0};//assault
    public int[] damage = {0, 0, 0, 0};//sniper
    public int[] range = {2, 0, 0, 0};//sniper
    public int[] health = {1, 0, 0, 0};//assault
    public int[] splash = {0, 0, 0, 0};//heavy
    public int[] squadSize = {1, 0, 0, 0};//heavy
}
