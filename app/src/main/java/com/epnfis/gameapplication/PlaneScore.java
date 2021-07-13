package com.epnfis.gameapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PlaneScore implements Observer{
    protected int x; // x coordinate of Canvas
    protected int y; // y coordinate of Canvas
    protected int score;
    private int canvasWidth;
    private int canvasHeight;
    public PlaneScore(int x, int y,int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.x = canvasWidth-100;
        this.y= canvasHeight-30;
    }
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.RED);
        paint.setTextSize(40);
        canvas.drawText(String.valueOf(score), x, y, paint);
    }
    public void update(ObserverData data) {
        if(data.getNotifyType() == NotifyType.INCREMENT_SCORE){
            this.score +=data.getScore();
        }
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}