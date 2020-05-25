package com.pacman.g60.Controller;

import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.Model.Elements.Element;
import com.pacman.g60.Model.Elements.Hierarchy.LivingElement;

import java.util.ArrayList;
import java.util.List;

public class CheckForDeathCommand implements Command {
    private ArenaModel arenaModel;

    public CheckForDeathCommand(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    @Override
    public void execute() {
        List<Element> elems = arenaModel.getElements();
        List<Element> toBeRemoved = new ArrayList<>();
        for (Element element : elems)
        {
            if (element instanceof LivingElement && !(((LivingElement) element).isAlive()))
            {
                toBeRemoved.add(element);
            }
        }
        for (Element element : toBeRemoved)
        {
            arenaModel.removeElement(element);
        }
    }
}
