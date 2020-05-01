package com.pacman.g60.Model.Elements;


import com.pacman.g60.Model.Elements.DynamicElement;
import com.pacman.g60.Model.Position;

public class Hero extends DynamicElement {
    private Integer health,coins;
    final private Integer maxHealth = 10;
    public Hero(Position pos)
    {
        super(pos);
        this.health = maxHealth;
        this.coins = 0;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getMaxHealth()
    {
        return this.maxHealth;
    }
}
