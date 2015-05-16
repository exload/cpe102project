package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import processing.core.PImage;

public class Background
{
    private PImage img;

    public Background(PImage img)
    {
        this.img = img;
    }

    public PImage getImage()
    {
        return img;
    }

    public void setImage(PImage img)
    {
        this.img = img;
    }
}
