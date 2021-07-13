package com.epnfis.gameapplication;

import android.graphics.Canvas;

public class PlaneLife extends Sprite implements Observer{
    protected int lifeCount = 3;
    private int canvasWidth;
    private int canvasHeight;
    public PlaneLife(int x, int y,int canvasWidth, int canvasHeight) {
        super(ImageCache.get("bluePlaneLifeBitmap"), x, y);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }
    public void draw(Canvas canvas){
        for(int i =0;i<lifeCount;i++){
            canvas.drawBitmap(this.bitmap, i*(this.width+2),
                    this.canvasHeight-80,null);
        }
    }
    @Override
    public void update(ObserverData data) {
        if(data.getNotifyType() == NotifyType.PLANE_DESTROTRY){
            this.lifeCount --;
        }
    }
}