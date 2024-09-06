package com.example.mdpproject;

import android.graphics.Paint;

//Cell Class
public class Cell {

    public float startX, startY, endX, endY;
    public Paint paint;
    public boolean imgFlag;
    public int id = -1;

    String type;

    //repeated - create a color thing
    private Paint obstacleColor = new Paint();
    private Paint robotColor = new Paint();
    private Paint unexploredCellColor = new Paint();
    private Paint exploredCellColor = new Paint();

    public Cell(float startX, float startY, float endX, float endY, Paint paint) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.paint = paint;
        this.imgFlag = false;
    }

    public void setType(String type) {
        this.type = type;
        switch (type) {
            case "obstacle":
                this.paint = obstacleColor;
                break;
            case "robot":
                this.paint = robotColor;
                break;
            case "unexplored":
                this.paint = unexploredCellColor;
                break;
            case "explored":
                this.paint = exploredCellColor;
                break;

            default:
                break;
        }
    }

    public int getId() {
        return this.id;
    }

}


