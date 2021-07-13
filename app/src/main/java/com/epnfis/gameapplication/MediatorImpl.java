package com.epnfis.gameapplication;

import android.content.Context;
import android.graphics.Canvas;

public class MediatorImpl implements Mediator{
    private Bom bom;
    private BomMusic bomMusic;
    public MediatorImpl(Context context){
        bom = new Bom();
        bomMusic = new BomMusic(context);
    }
    @Override
    public void handle(Canvas canvas, int x, int y){
        bom.action(canvas, x, y);
        bomMusic.action(canvas, x, y);
    }
}