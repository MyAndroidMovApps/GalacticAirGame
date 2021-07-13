package com.epnfis.gameapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;

public class BomMusic implements Runnable, Media {
    private MediaPlayer player;
    private Context context;
    public BomMusic(Context context){
        this.context=context;
        player= MediaPlayer.create(context, R.raw.hit);
    }
    public void run() {
        player.start();
    }
    @Override
    public void action(Canvas canvas, int x, int y) {
        new Thread(this).start();
    }
}