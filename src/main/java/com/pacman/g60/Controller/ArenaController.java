package com.pacman.g60.Controller;


import com.pacman.g60.Application;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.View.ArenaView;

import java.io.IOException;



public class ArenaController {
    private ArenaView arenaView;
    private ArenaModel arenaModel;
    
    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
    }
    
    public void run() throws IOException {
        boolean good = true;
        int i = 0;
        while(good){
            
            try {
                Thread.sleep(10);
            } catch(InterruptedException e){
                
            }
            
            if(i%10 == 0) {

                executeCommand(new UpdateAllEnemyPosCommand(this.arenaModel));
                executeCommand(new CheckHeroAdjacencyCommand(this.arenaModel));
                executeCommand(new CheckForDeathCommand(this.arenaModel));

                i = 0;
            }
            ++i;

            ArenaView.COMMAND cmd = arenaView.pollCommand();
            if(!(cmd == null)) {
                switch (cmd) {
                    case EXIT:
                        good = false;
                        break;
                    case UP:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.UP)); break;
                    case DOWN:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.DOWN)); break;
                    case LEFT:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.LEFT)); break;
                    case RIGHT:
                        executeCommand(new MoveHeroCommand(this.arenaModel, Application.Direction.RIGHT)); break;
                    case ATTACK:
                        executeCommand(new AttackCommand(this.arenaModel)); break;
                }
            }
            arenaView.draw(arenaModel);
        }
    }

    public void executeCommand(Command command)
    {
        command.execute();
    }





}
