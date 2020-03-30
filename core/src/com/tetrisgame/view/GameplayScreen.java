package com.tetrisgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tetrisgame.TetrisGame;
import com.tetrisgame.model.FigureManager;
import com.tetrisgame.model.GameProcessor;
import com.tetrisgame.utils.AssetsManager;
import com.tetrisgame.utils.NumToColor;
import com.tetrisgame.utils.Settings;

import java.io.IOException;

public class GameplayScreen implements Screen
{
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private GameProcessor gameProcessor;
    private TetrisGame game;



    public GameplayScreen(TetrisGame game)
    {
        this.game = game;
        gameProcessor = new GameProcessor();
    }

    @Override
    public void show()
    {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(100/255f, 100/255f, 100/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            gameProcessor.tryDoMoveFigureRight();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            gameProcessor.tryDoMoveFigureLeft();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            gameProcessor.tryDoForceFigureDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            gameProcessor.tryDoRotateFigure();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenuScreen(game));
        }


        boolean response = false;
        try {
            response = gameProcessor.process(delta);
        } catch (IOException e) {
            game.setScreen(new MainMenuScreen(game));
        } catch (ClassNotFoundException e) {
            game.setScreen(new MainMenuScreen(game));
        }

        if (!response) {
            AssetsManager.getEndingSound().play();
            this.pause();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.setScreen(new EvaluationScreen(gameProcessor.getGameScore(),game));
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for(int i = 0; i < Settings.FIELD_HEIGHT; i++)
        {
            for (int j = 0; j < Settings.FIELD_WIDTH; j++)
            {
                shapeRenderer.setColor(NumToColor.convert(gameProcessor.getGameFieldGrid()[i][j]));
                shapeRenderer.rect(100 + j*(250/Settings.FIELD_WIDTH), 400 - i*(375/ Settings.FIELD_HEIGHT),(250/Settings.FIELD_WIDTH)-1,(375/ Settings.FIELD_HEIGHT)-1);
            }
        }

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(390,350,100,75);
        for(int i = 0; i < gameProcessor.getNextFigureD().length; i++)
        {
            for(int j = 0; j < gameProcessor.getNextFigureD()[0].length; j++)
            {
                if(gameProcessor.getNextFigureD()[i][j] != 0) {
                    shapeRenderer.setColor(NumToColor.convert(gameProcessor.getNextFigureD()[i][j]));
                    shapeRenderer.rect(
                            400 +(80 - gameProcessor.getNextFigureD()[0].length*20)/2 + j * 20,
                            370 +(40 - gameProcessor.getNextFigureD().length*20)/2 + (gameProcessor.getNextFigureD().length - 1 - i) * 20,
                            19,
                            19);
                }
            }
        }

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(390,50,230,75);

        shapeRenderer.end();


        spriteBatch.begin();
        AssetsManager.getMainFont().getData().setScale(1,0.85f);
        AssetsManager.getMainFont().draw(spriteBatch, "NEXT",413,443);

        AssetsManager.getMainFont().getData().setScale(2,1.7f);
        AssetsManager.getMainFont().draw(spriteBatch, "SCORE",400,155);

        AssetsManager.getMainFont().getData().setScale(2,1.7f);
        AssetsManager.getMainFont().draw(spriteBatch, gameProcessor.getGameScore(),400,100);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
