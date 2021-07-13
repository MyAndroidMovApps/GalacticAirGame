package com.epnfis.gameapplication;

import android.graphics.Canvas;

public class Bom extends Sprite implements Media
{
    public Bom() {
        super(ImageCache.get("bomBitmap"),0, 0);
    }
    @Override
    public void action(Canvas canvas, int x, int y) {
        this.setVisible(true);
        this.x = x;
        this.y = y;
        super.draw(canvas);
        this.setVisible(false);
    }
}