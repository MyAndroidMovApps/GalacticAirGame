package com.epnfis.gameapplication;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EnemyPlaneChain {
    private LinkedList<ChainHandler> chainList =new
            LinkedList<ChainHandler>();
    private int timeline=240;
    private List<Sprite> enemyPlaneList = new ArrayList<Sprite>();
    public void add(ChainHandler chainHandler){
        chainList.add(chainHandler);
    }
    public void moveEnemyPlanes(Canvas canvas){
        if(enemyPlaneList == null){
            return;
        }
        if(timeline >0 && timeline % 80 == 0 && chainList.size() >0){
            ChainHandler currentChainHandler = chainList.poll();
            List<Sprite> spriteList= currentChainHandler.create();
            enemyPlaneList.addAll(spriteList);
        }
        for(int i=0;i<enemyPlaneList.size();i++){
            Sprite enemyPlane = enemyPlaneList.get(i);
            enemyPlane.draw(canvas);
            enemyPlane.move(0, 5);
        }
        timeline -- ;
    }
}
