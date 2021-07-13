package com.epnfis.gameapplication;

import android.graphics.Bitmap;

public class BluePlane extends Plane {
    public BluePlane(int x, int y) {
        //super(bitmap, x, y);
        super(ImageCache.get("bluePlaneBitmap"), x, y);
    }
}
