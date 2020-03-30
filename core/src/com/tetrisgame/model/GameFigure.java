package com.tetrisgame.model;

import com.tetrisgame.utils.FigureTypes;
import com.tetrisgame.utils.Settings;

public class GameFigure
{
    int x = 0;
    int y = -1;
    int[][] figure;

    public GameFigure(int type)
    {
        figure = FigureTypes.getFigure(type);
        x = (Settings.FIELD_WIDTH-figure[0].length)/2;
    }

    public int[][] getFigure() {
        return figure;
    }

    public int[][] rotated()
    {
        int[][] f = new int[figure[0].length][figure.length];
        for(int i = 0; i < figure.length; i++)
        {
            for(int j = 0; j < figure[0].length; j++)
            {
                f[j][figure.length-1 - i] = figure[i][j];
            }
        }
        return f;
    }

    public void rotate()
    {
        figure = rotated();
    }
}
