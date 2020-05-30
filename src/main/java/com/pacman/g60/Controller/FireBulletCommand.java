package com.pacman.g60.Controller;

import com.pacman.g60.Application;

import com.pacman.g60.Model.Elements.Hero;
import com.pacman.g60.Model.Models.ArenaModel;

public class FireBulletCommand implements Command {
    private ArenaModel arenaModel;

    public FireBulletCommand(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    @Override
    public void execute() {
        Hero hero = arenaModel.getHero();
        int heroAmmo = hero.getAmmo();

        if (heroAmmo > 0)
        {
            Application.Direction heroDir = hero.getDirection();
        }
    }
}
