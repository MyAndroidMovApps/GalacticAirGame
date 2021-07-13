package com.epnfis.gameapplication;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Map2Layer extends TiledLayer {
    private int canvasWidth;
    private int canvasHeight;
    private static int[][] maps = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 5, 1, 1, 5, 1, 1, 1, 5, 5 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 4, 1, 1 },
            { 1, 4, 1, 1, 1, 4, 1, 1, 1, 1 },
            { 1, 1, 1, 4, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 4, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 4, 4, 1, 1, 4, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 3, 3, 3, 1, 1, 1 },
            { 1, 1, 3, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 3, 1, 1, 3, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 3, 1 },
            { 1, 1, 1, 1, 1, 1, 3, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 2, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 2, 1, 1, 1, 2, 1 },
            { 1, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
            { 1, 2, 1, 1, 1, 1, 2, 1, 1, 2 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };
    public Map2Layer(int canvasWidth, int canvasHeight) {
        super(ImageCache.get("map2Bitmap"), maps, 216, 216, 10, 30);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }
    public void draw(Canvas canvas) {
        for(int i=0;i<maps.length;i++)
        {
            for(int j=0;j<maps[i].length;j++)
            {
                int x=j*tiledWidth;
                int y=i*tiledHeight;
                canvas.save();
                canvas.translate(viewX, viewY);
                canvas.clipRect(new
                        Rect(x,y,x+tiledWidth,y+tiledHeight));
                canvas.drawBitmap(bitmap, x-(maps[i][j]-1)*tiledWidth, y,
                        null);
                canvas.restore();
            }
        }
    }
}