package com.epnfis.gameapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Sprite {
    protected int x,y;//Sprite x, y coordinates
    protected Bitmap bitmap;//Pictures of sprite
    protected boolean visible =true;//Is the sprite visible
    protected int width,height;//Sprite width and height
    public Sprite(Bitmap bitmap, int x, int y) {
        super();
        this.bitmap = bitmap;
        this.width=bitmap.getWidth();
        this.height=bitmap.getHeight();
        this.x = x;
        this.y = y;
    }

    //if the mouse (x, y) is in area of Sprite
    public boolean contains(int x, int y) {
        if((x - this.x < this.width) && (y - this.y < this.height)){
            return true;
        }
        return false;
    }

    //draw sprite on canvas
    public void draw(Canvas canvas){
        if(this.isVisible()){
            canvas.drawBitmap(this.bitmap, this.x, this.y, null);
        }
    }
    //move sprite on canvas
    //distanceX: The distance moved in the x-axis
    //distanceY: The distance moved in the y-axis
    public void move(int distanceX, int distanceY) {
        this.x += distanceX;
        this.y += distanceY;
    }

    public boolean collideWith(Sprite sprite){
        if(this.isVisible() && sprite.isVisible()){
            int centerX = this.x + this.getWidth()/2;
            int centerY = this.y + this.getHeight()/2;
            int spriteCenterX = sprite.getX() + sprite.getWidth()/2;
            int spriteCenterY = sprite.getY() + sprite.getHeight()/2;
            if(Math.abs(centerX - spriteCenterX) < (this.width/2 +
                    sprite.getWidth()/2)
                    &&
                    Math.abs(centerY - spriteCenterY) < (this.height/2 +
                            sprite.getHeight()/2))
                return true;
            else
                return false;
        }
        return false;
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
    public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
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
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}