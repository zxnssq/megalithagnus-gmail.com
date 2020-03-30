package com.tetrisgame.model;

public class GameField
{
    private int[][] grid;

    public GameField(int height, int width)
    {
        grid = new int[height][width];

        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                grid[i][j] = 0;
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }
}
