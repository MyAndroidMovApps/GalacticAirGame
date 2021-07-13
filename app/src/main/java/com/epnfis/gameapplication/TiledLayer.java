package com.epnfis.gameapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class TiledLayer {
    protected Bitmap bitmap;
    protected int[][] maps;
    protected int tiledWidth;
    protected int tiledHeight;
    protected int col,row;
    protected int viewX,viewY;
    public TiledLayer(Bitmap bitmap, int[][] maps, int tiledWidth, int
            tiledHeight, int col, int row) {
        this.bitmap = bitmap;
        this.maps = maps;
        this.tiledWidth = tiledWidth;
        this.tiledHeight = tiledHeight;
        this.col = col;
        this.row = row;
    }
    public void setViewPort(int viewX,int viewY)
    {
        this.viewX=viewX;
        this.viewY=viewY;
    }
    public abstract void draw(Canvas canvas);
    public int[][] getMaps() {
        return maps;
    }
    public void setMaps(int[][] maps) {
        this.maps = maps;
    }
    public int getTiledWidth() {
        return tiledWidth;
    }
    public void setTiledWidth(int tiledWidth) {
        this.tiledWidth = tiledWidth;
    }
    public int getTiledHeight() {
        return tiledHeight;
    }
    public void setTiledHeight(int tiledHeight) {
        this.tiledHeight = tiledHeight;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
}