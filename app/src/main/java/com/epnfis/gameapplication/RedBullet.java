package com.epnfis.gameapplication;

import android.graphics.Bitmap;

public class RedBullet extends Bullet {
    public RedBullet(int x, int y) {
        super(ImageCache.get("redBulletBitmap"), x, y);
    }
}
