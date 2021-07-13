package com.epnfis.gameapplication;

import java.util.LinkedList;

public class MapContext {
    private LinkedList<TiledLayer> tiledLayerList=new LinkedList<TiledLayer>();
    private int index;
    public MapContext(){
    }
    public void addMap(TiledLayer tiledLayer){
        tiledLayerList.add(tiledLayer);
    }
    public TiledLayer next(){
        if(index >= tiledLayerList.size())
        {
            index =0;
        }
        TiledLayer tiledLayer = tiledLayerList.get(index);
        index++;
        return tiledLayer;
    }
}