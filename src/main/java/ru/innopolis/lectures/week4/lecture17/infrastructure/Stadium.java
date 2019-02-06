/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.lectures.week4.lecture17.infrastructure;

import ru.innopolis.lectures.week4.lecture17.game.Ball;
import ru.innopolis.lectures.week4.lecture17.game.Player;

public class Stadium implements IVenue {
    private Ball ball;
    private Player player;

    public Stadium(Ball ball, Player player) {
        this.ball = ball;
        this.player = player;
    }
}
