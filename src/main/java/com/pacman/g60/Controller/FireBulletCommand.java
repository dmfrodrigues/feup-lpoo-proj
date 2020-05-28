package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Hero;

public class FireBulletCommand implements Command {
    private ArenaModel arenaModel;

    public FireBulletCommand(ArenaModel arenaModel)
    {
        this.arenaModel = arenaModel;
    }

    @Override
    public void execute() {
        Hero hero = arenaModel.getHero();
        Integer heroAmmo = hero.getAmmo();

        if (heroAmmo > 0)
        {
            hero.decAmmo();

        }
    }


}
