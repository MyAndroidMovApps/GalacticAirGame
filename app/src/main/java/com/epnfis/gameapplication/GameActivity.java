package com.epnfis.gameapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameActivity extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private MainActivity gameActivity;
    private boolean isRun = true; // for game loop
    private SurfaceHolder surfaceHolder;
    private Bitmap bluePlaneBitmap; // blue_plane.png image bitmap
    private Paint paint;

    public GameActivity(MainActivity gameActivity) {
        super(gameActivity);
        this.gameActivity=gameActivity;
        surfaceHolder=this.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.surfaceHolder=holder;
        paint=new Paint();
//read bitmap resource res -> drawable -> blue_plane.png
        bluePlaneBitmap= BitmapFactory.decodeResource(getResources(),
                R.drawable.blue_plane);
        new Thread(this).start(); // start game loop thread
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        isRun = false;
    }

    @Override
    public void run() {
        while(isRun){
            Canvas canvas=null;
            try{
                canvas=surfaceHolder.lockCanvas(); // lock canvas
                synchronized (surfaceHolder){
                    drawGame(canvas); // draw images of games
                }
                Thread.sleep(100); // sleep 100 ms
            } catch (Exception e){
                e.printStackTrace();
            } finally{
                if(canvas!=null){
//unlock canvas submit
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
    public void drawGame(Canvas canvas) {
        canvas.drawColor(Color.WHITE); //set white background
        float x = 400; //x coordinate
        float y = 800; //y coordinate
        canvas.drawBitmap(bluePlaneBitmap, x, y, paint); // draw image on canvas
    }
}