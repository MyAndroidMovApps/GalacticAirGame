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
    private Plane plane;
    private int canvasWidth, canvasHeight;
    private int downX, downY;//coordinates of the mouse down
    private int moveX, moveY;//coordinates of the mouse move
    private String planeType;

    public GameView(MainActivity gameActivity, String planeType) {
        super(gameActivity);
        this.gameActivity=gameActivity;
        this.planeType = planeType;
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
        //create plane PlaneFactory
        plane = PlaneFactory.create(this.planeType, 0, 0);
        plane.setX(this.canvasWidth / 2 - plane.getWidth() / 2);
        plane.setY(this.canvasHeight - plane.getHeight() - 40);
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
            if (plane.contains(downX, downY)) {
                //The plane follows the mouse move
                plane.move(distanceX, distanceY);
            }
            //Save moving coordinates to down coordinates
            downX = moveX;
            downY = moveY;
        }
        return true;
    }

    public void drawGame(Canvas canvas) {
        canvas.drawColor(Color.WHITE); //set white background
        plane.draw(canvas); //draw plane on canvas
        plane.createBullets("1");
        plane.drawBullets(canvas);
        plane.moveBullet(0, -8);
    }
    // game loop to repeat draw
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