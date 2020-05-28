package com.pacman.g60.Controller;


import com.pacman.g60.Application;
import com.pacman.g60.Model.ArenaModel;
import com.pacman.g60.View.ArenaView;

import java.io.IOException;

public class ArenaController {
    private ArenaView arenaView;
    private ArenaModel arenaModel;
    private boolean win;
    private boolean over;
    
    public ArenaController(ArenaModel arenaModel, ArenaView arenaView){
        this.arenaModel = arenaModel;
        this.arenaView = arenaView;
    }
    
    private void start(){
        win = false;
        over = false;
        arenaView.start();
    }
    
    public void run() throws IOException {
        start();
        
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
            
            if (arenaModel.getHero().getHealth() <= 0){ lose(); good = false; }
            if (!arenaModel.getShouldGameContinue()  ){ win(); good = false; }

            arenaView.draw(arenaModel);
        }
    }
    
    private void lose(){
        win = false;
        over = true;
    }
    
    private void win(){
        win = true;
        over = true;
    }

    public boolean isOver() { return over; }

    public boolean isWin() { return win; }
    
    public void executeCommand(Command command)
    {
        command.execute();
    }





}
