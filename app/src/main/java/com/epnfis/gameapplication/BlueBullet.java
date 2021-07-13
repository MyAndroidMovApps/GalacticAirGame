package com.epnfis.gameapplication;

import android.graphics.Bitmap;

public class BlueBullet extends Bullet {
    public BlueBullet(int x, int y) {
        super(ImageCache.get("blueBulletBitmap"), x, y);
    }
}
