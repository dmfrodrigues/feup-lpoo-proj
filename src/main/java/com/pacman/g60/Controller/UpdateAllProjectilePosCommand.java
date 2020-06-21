/**
 * Copyright (C) 2020 Diogo Rodrigues, João Matos
 */

package com.pacman.g60.Controller;

import com.pacman.g60.Model.Elements.Bullet;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Models.ArenaModel;

import java.util.List;

public class UpdateAllProjectilePosCommand extends CompositeCommand {
    private ArenaModel arenaModel;

    public UpdateAllProjectilePosCommand(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    private void setup()
    {
        List<Element> elems = arenaModel.getElements();
        for (Element elem : elems)
        {
            if (elem instanceof Bullet)
            {
                if (((Bullet)elem).getMoving())
                {
                    UpdateProjectilePosCommand command = new UpdateProjectilePosCommand((Bullet)elem,arenaModel);
                    this.addCommand(command);
                }

            }
        }
    }

    @Override
    public void execute()
    {
        setup();
        super.execute();
    }

}
