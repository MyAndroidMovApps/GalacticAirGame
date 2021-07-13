package com.epnfis.gameapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Sprite {
    protected int x,y;//Sprite x, y coordinates
    protected Bitmap bitmap;//Pictures of sprite
    protected boolean visiable=true;//Is the sprite visible
    protected int width,height;//Sprite width and height
    public Sprite(Bitmap bitmap, int x, int y) {
        super();
        this.bitmap = bitmap;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.x = x;
        this.y = y;
    }
    //draw sprite on canvas
    public void draw(Canvas canvas){
        if(this.isVisiable()){
            canvas.drawBitmap(this.bitmap, this.x, this.y, null);
        }
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public boolean isVisiable(){
        return visiable;
    }
    public void setVisiable(boolean visiable){
        this.visiable = visiable;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getWidth() {
        return this.width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return this.height;
    }
}