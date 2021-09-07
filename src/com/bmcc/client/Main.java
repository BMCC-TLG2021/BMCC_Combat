package com.bmcc.client;

import com.bmcc.controller.Game;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.play();
    }


    private Main() {
        //no instance
    }

}