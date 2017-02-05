package com.code.albert.evilmemory.adapter;

/**
 * Created by Albert on 31/01/2017.
 */
public class RankingPlayer {
    private int icon;
    private String name;
    private int score;

    public RankingPlayer(int icon, String name, int score){
        this.icon = icon;
        this.name = name;
        this.score = score;

    }
    RankingPlayer(){

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

}
