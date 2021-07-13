package com.epnfis.gameapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy1Handler implements ChainHandler {
    private Bitmap enemyPlaneImage;
    private int canvasWidth;
    public Enemy1Handler(int canvasWidth) {
        this.enemyPlaneImage = ImageCache.get("enemyPlane1Bitmap");
        this.canvasWidth = canvasWidth;
    }
    @Override
    public List<Sprite> create() {
        Random rn = new Random();
        List<Sprite> spriteList = new ArrayList<Sprite>();
        for (int i = 0; i < 2; i++) {
            int x = rn.nextInt(canvasWidth -
                    enemyPlaneImage.getWidth());
            int y = -enemyPlaneImage.getHeight();
            Sprite enemyPlane = new EnemyPlane(enemyPlaneImage, x,
                    y);
            spriteList.add(enemyPlane);
        }
        return spriteList;
    }
}