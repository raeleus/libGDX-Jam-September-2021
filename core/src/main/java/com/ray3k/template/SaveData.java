package com.ray3k.template;

import com.ray3k.template.entities.SoldierEntity.*;

public class SaveData {
    public SoldierType[] types = {SoldierType.MILITIA, null, null, null};
    public int coins = 0;
    public int level = 1;
    public int reinforcedLevel = 0;
    public int[] moveSpeed = {0, 0, 0, 0};//assault
    public int[] damage = {0, 0, 0, 0};//sniper
    public int[] range = {0, 0, 0, 0};//sniper
    public int[] health = {0, 0, 0, 0};//assault
    public int[] splash = {0, 0, 0, 0};//heavy
    public int[] squadSize = {0, 0, 0, 0};//heavy
}
