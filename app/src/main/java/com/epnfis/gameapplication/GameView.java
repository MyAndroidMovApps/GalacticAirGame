package com.epnfis.gameapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private MainActivity gameActivity;
    private boolean isRun = true; // for game loop
    private SurfaceHolder surfaceHolder;
    //private Bitmap bluePlaneBitmap; // blue_plane.png image bitmap
    private Paint paint;
    private Sprite bluePlane;
    private int canvasWidth, canvasHeight;
    private int downX, downY;//coordinates of the mouse down
    private int moveX, moveY;//coordinates of the mouse move

    public GameView(MainActivity gameActivity) {
        super(gameActivity);
        this.gameActivity=gameActivity;
        surfaceHolder=this.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.canvasWidth = this.getWidth(); // get width of canvas
        this.canvasHeight = this.getHeight(); // get height of canvas
        this.surfaceHolder = holder;
        paint = new Paint();
        /*//draw blue plane on bottom center of canvas
        bluePlaneBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.blue_plane);
        bluePlane = new BluePlane(bluePlaneBitmap, 0, 0);
        bluePlane.setX(this.canvasWidth/2 - bluePlane.getWidth()/2);
        bluePlane.setY(this.canvasHeight - bluePlane.getHeight() - 40);
        new Thread(this).start(); // start game loop thread

         */
        //draw blue plane on bottom center of canvas
        bluePlane = new BluePlane(0, 0);
        bluePlane.setX(this.canvasWidth / 2 - bluePlane.getWidth() / 2);
        bluePlane.setY(this.canvasHeight - bluePlane.getHeight() - 40);
        new Thread(this).start(); // start game loop thread

    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        isRun = false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = (int) event.getX();
            downY = (int) event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            moveX = (int) event.getX();
            moveY = (int) event.getY();
            //Calculate the distance moved by the mouse
            int distanceX = moveX - downX;
            int distanceY = moveY - downY;
            if (bluePlane.contains(downX, downY)) {
                //The plane follows the mouse move
                bluePlane.move(distanceX, distanceY);
            }
            //Save moving coordinates to down coordinates
            downX = moveX;
            downY = moveY;
        }
        return true;
    }

    public void drawGame(Canvas canvas) {
        canvas.drawColor(Color.WHITE); //set white background
        //float x = 400; //x coordinate
        //float y = 800; //y coordinate
        //canvas.drawBitmap(bluePlaneBitmap, x, y, paint); // draw image on canvas
        bluePlane.draw(canvas); //draw plane on canvas
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
                Thread.sleep(50); // sleep 100 ms
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

}