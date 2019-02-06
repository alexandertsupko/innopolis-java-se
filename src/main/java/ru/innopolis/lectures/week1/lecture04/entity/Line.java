package ru.innopolis.lectures.week1.lecture04.entity;

import java.io.Serializable;

public class Line implements Serializable {
    private Point point1;
    private Point point2;
    private int index;

    public Line(Point point1, Point point2, int index) {
        this.point1 = point1;
        this.point2 = point2;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Line{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", index=" + index +
                "} ref=" + super.toString();
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
