package com.ooqle.game.ui;
/*
* @author Kenny Williams
*/

import processing.core.PImage;

public class ToggleButton extends Button
{
    private boolean selected;
    private PImage selectedImg;

    public ToggleButton(int x, int y, PImage image, PImage hoverImg, PImage selectedImg)
    {
        super(x, y, image, hoverImg);
        this.selected = false;
        this.selectedImg = selectedImg;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public PImage getCurrentImage()
    {
        if (this.isSelected())
        {
            return this.selectedImg;
        }
        return super.getCurrentImage();
    }

    public void onClick()
    {
        super.onClick();
        this.setSelected(!this.isSelected());
    }
}
