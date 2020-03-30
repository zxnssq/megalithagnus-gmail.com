package com.tetrisgame.model;

import com.badlogic.gdx.assets.AssetManager;
import com.tetrisgame.utils.AssetsManager;
import com.tetrisgame.utils.Settings;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static com.tetrisgame.model.FigureManager.getNextFigure;

public class GameProcessor
{
    GameField gameField;
    GameFigure currentFigure;
    GameFigure nextFigure;


    int gameScore = 0;
    int combo = 0;

    int[][] field;

    float totalTime = 0;
    float tempTime = 0;

    public GameProcessor()
    {
        gameField = new GameField(Settings.FIELD_HEIGHT, Settings.FIELD_WIDTH);
        try {
            currentFigure = getNextFigure();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            nextFigure = getNextFigure();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        field = new int[Settings.FIELD_HEIGHT][Settings.FIELD_WIDTH];

    }

    public int[][] getGameFieldGrid() {
        return field;
    }

    public boolean process(float delta) throws IOException, ClassNotFoundException
    {
        totalTime += delta;
        tempTime += delta;

        for(int i = 0; i < Settings.FIELD_HEIGHT; i++)
        {
            for(int j = 0; j < Settings.FIELD_WIDTH; j++)
            {
                field[i][j] = gameField.getGrid()[i][j];

                if(i >= currentFigure.y && j >= currentFigure.x)
                {
                    if(i < currentFigure.y + currentFigure.getFigure().length && j < currentFigure.x + currentFigure.getFigure()[0].length)
                    {
                        if(currentFigure.getFigure()[i - currentFigure.y][j - currentFigure.x] != 0)
                            field[i][j] = currentFigure.getFigure()[i - currentFigure.y][j - currentFigure.x];
                    }
                }
            }
        }

        if (tempTime > Settings.GAME_TICK)
        {
            tempTime = 0;
            AssetsManager.getTickSound().play(0.5f);


            if (tryMoveFigureDown())
            {
                currentFigure.y += 1;
            }
            else
            {
                try {
                    fixFigure();
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    return false;
                }
                checkLines();
            }
        }

        return true;
    }

    private void fixFigure() throws IOException, ClassNotFoundException {
        for(int i = 0; i < currentFigure.getFigure().length; i++)
        {
            for(int j = 0; j < currentFigure.getFigure()[0].length; j++)
            {
                if(gameField.getGrid()[currentFigure.y + i][currentFigure.x + j] == 0)
                    gameField.getGrid()[currentFigure.y + i][currentFigure.x + j] = currentFigure.getFigure()[i][j];
            }
        }
        currentFigure = nextFigure;
        nextFigure = getNextFigure();

        gameScore += 50;
    }

    private void checkLines()
    {
        combo += 1;

        for(int i = 0; i < gameField.getGrid().length; i++)
        {
            boolean hasSpaces = false;
            for (int k = 0; k < gameField.getGrid()[i].length; k++) {
                if(gameField.getGrid()[i][k] == 0)
                    hasSpaces = true;
            }
            if(!hasSpaces)
            {
                for(int j = i; j >0; j--)
                {
                    gameField.getGrid()[j] = gameField.getGrid()[j-1];
                }
                gameField.getGrid()[0] = new int[Settings.FIELD_WIDTH];

                gameScore += combo*50;
                combo = 0;
                AssetsManager.getComboSound().play();
            }
        }

    }

    private boolean checkGameEnded()
    {
        for(int i = 0; i < Settings.FIELD_WIDTH; i++)
        {
            if(gameField.getGrid()[0][i] !=0)
            {
                return true;
            }
        }
        return false;
    }

    private boolean tryMoveFigureDown()
    {
        if (currentFigure.y + currentFigure.getFigure().length >= Settings.FIELD_HEIGHT)
        {
            return false;
        }
        for(int i = 0; i < currentFigure.getFigure().length; i++)
        {
            for(int j = 0; j < currentFigure.getFigure()[0].length; j++)
            {
                if(gameField.getGrid()[currentFigure.y + i + 1][currentFigure.x + j] != 0 && currentFigure.getFigure()[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    public boolean tryDoMoveFigureRight()
    {


        if(currentFigure.x + currentFigure.getFigure()[0].length >= Settings.FIELD_WIDTH)
        {
            return false;
        }
        if(currentFigure.y > 0) {
            for (int i = 0; i < currentFigure.getFigure().length; i++) {
                for (int j = 0; j < currentFigure.getFigure()[0].length; j++) {
                    if (gameField.getGrid()[currentFigure.y + i][currentFigure.x + j + 1] != 0 && currentFigure.getFigure()[i][j] != 0)
                        return false;
                }
            }
        }
        currentFigure.x += 1;
        return true;
    }

    public boolean tryDoMoveFigureLeft()
    {
        if(currentFigure.x <= 0)
        {
            return false;
        }
        if(currentFigure.y > 0) {
            for (int i = 0; i < currentFigure.getFigure().length; i++) {
                for (int j = 0; j < currentFigure.getFigure()[0].length; j++) {
                    if (gameField.getGrid()[currentFigure.y + i][currentFigure.x + j - 1] != 0 && currentFigure.getFigure()[i][j] != 0)
                        return false;
                }
            }
        }
        currentFigure.x -= 1;
        return true;
    }

    public void tryDoForceFigureDown()
    {
        while(tryMoveFigureDown())
        {
            currentFigure.y += 1;
        }
        try {
            fixFigure();
        }
        catch (Exception e)
        {

        }
        checkLines();
    }

    public boolean tryDoRotateFigure()
    {
        int[][] r = currentFigure.rotated();

        if (currentFigure.y + r.length >= Settings.FIELD_HEIGHT)
        {
            return false;
        }
        if(currentFigure.y < 0)
        {
            return false;
        }
        for(int i = 0; i < r.length; i++)
        {
            for(int j = 0; j < r[0].length; j++)
            {
                try {
                    if (gameField.getGrid()[currentFigure.y + i][currentFigure.x + j] != 0 && r[i][j] != 0)
                        return false;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        }
        currentFigure.rotate();
        return true;
    }

    public int[][] getNextFigureD()
    {
        return nextFigure.figure;
    }

    public String getGameScore() {
        String str = gameScore + "";
        int l = str.length();
        if (str.length() < 8)
        {
            for(int i = 0; i < 8-l; i ++)
            {
                str = "0" + str;
            }
        }
        return str;
    }
}
