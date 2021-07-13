package com.epnfis.gameapplication;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private static Map<String,Bitmap> cacheMap=new HashMap<String,
                Bitmap>();
    private ImageCache(){}
    public static void put(String key, Bitmap image){
        cacheMap.put(key, image);
    }
    public static Bitmap get(String key){
        return cacheMap.get(key);
    }
}