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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private MainActivity gameActivity;
    private boolean isRun = true; // for game loop
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Plane plane;
    private int canvasWidth, canvasHeight;
    private int downX, downY;//coordinates of the mouse down
    private int moveX, moveY;//coordinates of the mouse move
    private String planeType;
    private EnemyPlaneChain enemyPlaneChain;
    private PlaneLife planeLife;
    private PlaneScore planeScore;
    private Mediator mediator;
    private TiledLayer mapLayer;
    private MapContext mapContext;
    private int screenY;

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

        enemyPlaneChain = new EnemyPlaneChain();
        enemyPlaneChain.add(new Enemy1Handler(this.canvasWidth));
        enemyPlaneChain.add(new Enemy2Handler(this.canvasWidth));
        enemyPlaneChain.add(new EnemyBossHandler(this.canvasWidth));

        planeLife = new PlaneLife(0, 0, canvasWidth, canvasHeight);
        plane.registerObserver(planeLife);
        planeScore=new PlaneScore(0,0,canvasWidth,canvasHeight );
        plane.registerObserver(planeScore);
        mediator = new MediatorImpl(this.gameActivity);

        mapContext = new MapContext();
        mapContext.addMap(new MapLayer(this.canvasWidth,this.canvasHeight));
        mapContext.addMap(new Map2Layer(this.canvasWidth,this.canvasHeight));
        mapLayer = mapContext.next();//Show MapLayer
        //mapLayer = mapContext.next();//Show Map2Layer
        screenY = -this.canvasHeight;

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
                plane.createBullets("1");//If move show alternative bullet
            }
            //Save moving coordinates to down coordinates
            downX = moveX;
            downY = moveY;
        }
        return true;
    }

    public void drawGame(Canvas canvas) {
        canvas.drawColor(Color.WHITE); //set white background
        drawBackGroundMap(canvas);
        plane.draw(canvas); //draw plane on canvas
        plane.createBullets("2");//1=blue, 2=red_yellow
        plane.drawBullets(canvas);
        plane.moveBullet(0, -8);
        planeLife.draw(canvas);
        planeScore.draw(canvas, paint);
        enemyPlaneChain.moveEnemyPlanes(canvas);
        collideCheck(canvas);
    }
    public void drawBackGroundMap(Canvas canvas)
    {
        mapLayer.setViewPort(0, screenY);
        mapLayer.draw(canvas);
        if(screenY<=0){
            screenY++;
        }
        if(screenY>=0){
            screenY = - this.canvasHeight;
            mapLayer = mapContext.next();
        }
    }
    private void collideCheck(Canvas canvas) {
        List<Sprite> enemyPlaneList = enemyPlaneChain.getEnemyPlaneList();
        CopyOnWriteArrayList<Bullet> bulletList = plane.getBulletList();
        for (int i = 0; i < enemyPlaneList.size(); i++) {
            Sprite enemyPlane = enemyPlaneList.get(i);
            for(int j=0; j<bulletList.size();j++) {
                Bullet bullet = bulletList.get(j);
                if (bullet.collideWith(enemyPlane)) {
                    enemyPlane.setVisible(false);
                    bullet.setVisible(false);
                    mediator.handle(canvas, enemyPlane.getX(),enemyPlane.getY());
                    ObserverData data = new ObserverData();
                    data.setNotifyType(NotifyType.INCREMENT_SCORE);
                    data.setScore(100);
                    plane.notifyAll(data);
                }
                if (plane.collideWith(enemyPlane)) {
                    enemyPlane.setVisible(false);
                    mediator.handle(canvas, enemyPlane.getX(),enemyPlane.getY());
                    ObserverData data = new ObserverData();
                    data.setNotifyType(NotifyType.PLANE_DESTROTRY);
                    plane.notifyAll(data);
                    plane.setX(this.canvasWidth / 2 - plane.getWidth() / 2);
                    plane.setY(this.canvasHeight - plane.getHeight() - 30);
                }
            }
        }
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