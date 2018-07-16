package com.memorygame.model;

/**
 * Created by Sarah on 7/15/18.
 */

public class Card {

    private int imageResourceId;
    private int id;
    private boolean isFlippedDown, isMarkedDone;


    public Card() {
        this.isFlippedDown = true;
        this.isMarkedDone = false;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean isMarkedDone() {
        return isMarkedDone;
    }

    public void setMarkedDone(boolean markedDone) {
        isMarkedDone = markedDone;
    }

    public boolean isFlippedDown() {
        return isFlippedDown;
    }

    public void setFlippedDown(boolean flippedDown) {
        isFlippedDown = flippedDown;
    }
}
