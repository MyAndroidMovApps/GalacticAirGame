package com.epnfis.gameapplication;

public class ObserverData {
    private int score;
    private NotifyType notifyType;
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public NotifyType getNotifyType() {
        return notifyType;
    }
    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }
}