package com.tetrisgame.utils;

public class FigureTypes
{
    public static int[][] getFigure(int type)
    {
        switch (type)
        {
            case 0:
            {
                return new int[][]{{1,1,1,1}};
            }

            case 1:
            {
                return new int[][]{{0,1,0},{1,1,1}};
            }

            case 2:
            {
                return new int[][]{{1,0,0},{1,1,1}};
            }

            case 3:
            {
                return new int[][]{{1, 1},{1, 1}};
            }

            case 4:
            {
                return new int[][]{{0,0,1},{1,1,1}};
            }

            case 5:
            {
                return new int[][]{{1,1,0},{0,1,1}};
            }

            case 6:
            {
                return new int[][]{{0,1,1},{1,1,0}};
            }
        }
        return null;
    }
}
