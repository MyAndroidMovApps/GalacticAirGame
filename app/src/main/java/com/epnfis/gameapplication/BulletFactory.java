package com.epnfis.gameapplication;

public class BulletFactory {
    public static Bullet create(String type, int x, int y){
        if ("1".equals(type)){
            return new BlueBullet(x, y);
        }else if ("2".equals(type)){
            return new RedBullet(x, y);
        }
        return null;
    }
}
