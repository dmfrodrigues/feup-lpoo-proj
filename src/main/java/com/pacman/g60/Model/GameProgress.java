package com.pacman.g60.Model;

import java.util.*;

public class GameProgress {
    public static class LevelProgress implements Comparable {
        private Integer level;
        private Integer coins;
        private Integer time;
        private Date when;
        
        public LevelProgress(Integer level, Integer coins, Integer time, Date when){
            this.level = level;
            this.coins = coins;
            this.time  = time ;
            this.when  = when ;
        }
        
        public Integer getLevel() { return level; }
        public Integer getCoins() { return coins; }
        public Integer getTime () { return time ; }
        public Date    getWhen () { return when ; }

        @Override
        public int compareTo(Object o) {
            LevelProgress p = (LevelProgress)o;
            if(level != p.level) throw new IllegalArgumentException();
            if(!coins.equals(p.coins)) return (coins-p.coins);
            if(!time .equals(p.time )) return (time -p.time );
            return 0;
        }
    }
    
    private Map<Integer, LevelProgress> progressMap;
    
    public GameProgress(){
        progressMap = new HashMap<>();
    }
    
    public void addProgress(LevelProgress progress){
        LevelProgress p = progressMap.get(progress.getLevel());
        if(p == null || p.compareTo(progress) < 0) progressMap.put(progress.getLevel(), progress);
    }
    
    public LevelProgress getProgress(Integer level){ return progressMap.get(level); }
    
    public Set<Integer> getLevels(){ return progressMap.keySet(); }
}
