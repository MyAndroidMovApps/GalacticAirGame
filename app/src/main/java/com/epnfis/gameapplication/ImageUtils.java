package com.epnfis.gameapplication;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageUtils{
    public static Bitmap scaleBitmap(Bitmap bitmap,int width,int height){
        int srcWidth=bitmap.getWidth();
        int srcHeight=bitmap.getHeight();
        float scaleWidth=(float)width/srcWidth;
        float scaleHeight=(float)height/srcHeight;
        Matrix matrix=new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, srcWidth, srcHeight,
                matrix, true);
    }
}