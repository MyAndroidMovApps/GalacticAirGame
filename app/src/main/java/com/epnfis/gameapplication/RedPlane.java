package com.epnfis.gameapplication;

public class RedPlane extends Plane{
    public RedPlane(int x, int y) {
        super(ImageCache.get("redPlaneBitmap"), x, y);
    }
}
