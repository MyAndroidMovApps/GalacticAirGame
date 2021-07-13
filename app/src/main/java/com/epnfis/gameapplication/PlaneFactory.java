package com.epnfis.gameapplication;

public class PlaneFactory {
    public static Plane create(String type, int x, int y){
        if ("1".equals(type)){
            return new BluePlane(x, y);
        }else if ("2".equals(type)){
            return new RedPlane(x, y);
        }
        return null;
    }
}
