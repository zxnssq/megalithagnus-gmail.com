package com.tetrisgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetrisgame.TetrisGame;
import com.tetrisgame.model.FigureManager;
import com.tetrisgame.utils.AssetsManager;

public class ConnectionWaitingScreen implements Screen
{
    TetrisGame game;
    float time = 0;
    private SpriteBatch spriteBatch;

    public ConnectionWaitingScreen(TetrisGame game)
    {
        this.game = game;

    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        time += delta;
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        AssetsManager.getMainFont().getData().setScale(3,2.55f);
        AssetsManager.getMainFont().draw(spriteBatch, "WAITING...",175,310);

        spriteBatch.end();

        if(time > 0.2)
        {
            pause();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

        FigureManager.CheckConnection();
        game.setScreen(new GameplayScreen(game));
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
