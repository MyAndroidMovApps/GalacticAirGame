package com.epnfis.gameapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Plane extends Sprite{
    private CopyOnWriteArrayList<Bullet> bulletList = new CopyOnWriteArrayList<Bullet>();
    private int bulletSpeed = 8;
    public Plane(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
    }
    protected List<Observer> observerList=new ArrayList<Observer>();
    public void createBullets(String bulletType){
        if(bulletSpeed == 0) {
            if (this.bulletList.size() < 100) {
                Bullet bullet = BulletFactory.create(bulletType, -100, -100);
                int x = this.getX() + this.getWidth() / 2 - bullet.getWidth() / 2;
                int y = this.getY() - bullet.getHeight();
                bullet.setX(x);
                bullet.setY(y);
                bullet.setVisible(true);
                this.loadBullet(bullet);
                bulletSpeed = 8;
            }
        }else{
            bulletSpeed --;
        }
    }
    public void drawBullets(Canvas canvas) {
        Iterator<Bullet> iter = this.bulletList.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            if (bullet.isVisible()) {
                bullet.draw(canvas);
            }
        }
    }

    public void moveBullet(int distanceX, int distanceY) {
        Iterator<Bullet> iter = this.bulletList.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            if (bullet.isVisible()) {
                bullet.move(distanceX, distanceY);
            }
        }
        for (int i = this.bulletList.size() - 1; i >= 0; i--) {
            Bullet bullet = this.bulletList.get(i);
            //if the bullet move up out of canvas remove it
            if (bullet.getY() + bullet.getHeight() <= 0) {
                this.bulletList.remove(bullet);
            }
        }
    }
    public void loadBullet(Bullet bullet) {
        bulletList.add(bullet);
    }
    public void registerObserver(Observer observer){
        observerList.add(observer);
    }
    public void notifyAll(ObserverData data){
        for(int i=0;i<observerList.size();i++){
            Observer observer=observerList.get(i);
            observer.update(data);
        }
    }
    public CopyOnWriteArrayList<Bullet> getBulletList() {
        return bulletList;
    }
}
