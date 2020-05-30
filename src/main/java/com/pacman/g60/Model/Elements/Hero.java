package com.pacman.g60.Model.Elements;



import com.pacman.g60.Model.Elements.Hierarchy.*;
import com.pacman.g60.Model.Models.ArenaModel;
import com.pacman.g60.Model.Position;



public class Hero extends DynamicElement implements ControlledByPlayer, LivingElement, MeleeAttackerElement, RangedAttackerElement, OrientedElement {
    private Integer health,coins,ammo;
    private Weapon weapon;
    static final private Integer maxHealth = 10;

    @Override
    public Object clone() {
        Hero res = (Hero)super.clone();
        res.health = Integer.valueOf(health);
        res.coins = Integer.valueOf(coins);
        res.ammo = Integer.valueOf(ammo);
        res.weapon = this.weapon;
        return res;
    }

    public Hero(Position pos)
    {
        super(pos);
        this.health = maxHealth;
        this.coins = 0;
        this.ammo = 0;
        this.weapon = new Knife();
    }

    @Override
    public boolean beCollected(ArenaModel arenaModel) {
        return false;
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

    public void incCoins(){this.coins++;}

    public void incAmmo(){this.ammo++;}

    public Integer getAmmo()
    {
        return this.ammo;
    }

    public void decAmmo()
    {
        this.ammo--;
        if (this.ammo < 0) this.ammo = 0;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    public Weapon getWeapon()
    {
        return this.weapon;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getMaxHealth()
    {
        return this.maxHealth;
    }

    @Override
    public void updateHealth(Integer diff) {

        this.health += diff;
        if (this.health > maxHealth) this.health = maxHealth;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
