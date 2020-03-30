package com.tetrisgame.utils;

import com.badlogic.gdx.graphics.Color;

public class NumToColor
{
    public static Color convert(int num)
    {
        switch (num){
            case 0:
            {
                return Color.BLACK;
            }

            case 1:
            {
                return Color.WHITE;
            }

            case 2:
            {
                return Color.GREEN;
            }

            case 3:
            {
                return Color.YELLOW;
            }

            case 4:
            {
                return Color.BLUE;
            }

            case 5:
            {
                return Color.RED;
            }

        }
        return null;
    }

}
