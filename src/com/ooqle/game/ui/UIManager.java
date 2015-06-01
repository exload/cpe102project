package com.ooqle.game.ui;
/*
* @author Kenny Williams
*/

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class UIManager
{
    private static List<Button> buttons = new ArrayList<>();

    public static Button createButton(int x, int y, PImage image, PImage hoverImg)
    {
        Button btn = new Button(x, y, image, hoverImg);
        buttons.add(btn);
        return btn;
    }

    public static List<Button> getButtons()
    {
        return buttons;
    }

    public static boolean withinBounds(int mouseX, int mouseY, Button btn)
    {
        return mouseX >= btn.getX() && mouseX <= btn.getX() + btn.getWidth()
                && mouseY >= btn.getY() && mouseY <= btn.getY() + btn.getHeight();
    }

    public static void updateMousePosition(int mouseX, int mouseY)
    {
        for (Button btn : UIManager.getButtons())
        {
            if (UIManager.withinBounds(mouseX, mouseY, btn))
            {
                btn.setHover(true);
            } else
            {
                btn.setHover(false);
            }
        }
    }

    public static void drawUI(PApplet game)
    {
        for (Button btn : buttons)
        {
            if(btn.isHover())
            {
                game.image(btn.getHoverImage(), btn.getX(), btn.getY());
            }else
            {
                game.image(btn.getImage(), btn.getX(), btn.getY());
            }
        }
    }
}
