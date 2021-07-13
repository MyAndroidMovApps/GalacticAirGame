package com.epnfis.gameapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyBossHandler implements ChainHandler {
    private Bitmap enemyPlaneImage;
    private int canvasWidth;
    public EnemyBossHandler(int canvasWidth) {
        this.enemyPlaneImage = ImageCache.get("enemyBossBitmap");
        this.canvasWidth = canvasWidth;
    }
    @Override
    public List<Sprite> create() {
        Random rn = new Random();
        List<Sprite> spriteList = new ArrayList<Sprite>();
        int x = rn.nextInt(canvasWidth - enemyPlaneImage.getWidth());
        int y = -enemyPlaneImage.getHeight();
        Sprite enemyPlane = new EnemyPlane(enemyPlaneImage, x, y);
        spriteList.add(enemyPlane);
        return spriteList;
    }
}