package com.ooqle.game.ui;
/*
* @author Kenny Williams
*/

import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Button
{
    private boolean hover;
    private int width;
    private int height;
    private int x;
    private int y;
    private PImage image;
    private PImage hoverImg;

    private List<ClickHandler> clickHandlerList;

    public Button(int x, int y, PImage image, PImage hoverImg)
    {
        this.hover = false;
        this.x = x;
        this.y = y;
        this.width = image.width;
        this.height = image.height;
        this.image = image;
        this.hoverImg = hoverImg;
        this.clickHandlerList = new ArrayList<>();
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public PImage getImage()
    {
        return image;
    }

    public void setImage(PImage image)
    {
        this.image = image;
    }

    public PImage getHoverImage()
    {
        return this.hoverImg;
    }

    public PImage getCurrentImage()
    {
        if(isHover())
        {
            return this.getImage();
        }
        return this.hoverImg;
    }

    public void addClickHandler(ClickHandler handler)
    {
        clickHandlerList.add(handler);
    }

    public void onClick()
    {
        clickHandlerList.forEach(com.ooqle.game.ui.ClickHandler::onClick);
    }

    public boolean isHover()
    {
        return hover;
    }

    public void setHover(boolean hover)
    {
        this.hover = hover;
    }
}
