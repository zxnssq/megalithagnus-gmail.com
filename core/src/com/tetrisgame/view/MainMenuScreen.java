package com.tetrisgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tetrisgame.TetrisGame;
import com.tetrisgame.utils.AssetsManager;

public class MainMenuScreen implements Screen
{
    TetrisGame game;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    TextButton newGameButton;
    TextButton optionsButton;
    TextButton exitButton;

    public MainMenuScreen(TetrisGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = AssetsManager.getMainFont();
        newGameButton = new TextButton("NEW GAME",textButtonStyle);
        newGameButton.setHeight(60);
        newGameButton.setWidth(200);
        newGameButton.setPosition(225,175);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ConnectionWaitingScreen(game));
            }
        });

        optionsButton = new TextButton("OPTIONS", textButtonStyle);
        optionsButton.setHeight(60);
        optionsButton.setWidth(200);
        optionsButton.setPosition(225,135);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game));
            }
        });

        exitButton = new TextButton("EXIT", textButtonStyle);
        exitButton.setHeight(60);
        exitButton.setWidth(200);
        exitButton.setPosition(225,95);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(newGameButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(90,300,450,10);

        shapeRenderer.end();

        spriteBatch.begin();

        AssetsManager.getMainFont().getData().setScale(6,5.1f);
        AssetsManager.getMainFont().draw(spriteBatch, "TETRIS",90,410);

        AssetsManager.getMainFont().getData().setScale(2,1.7f);

        newGameButton.draw(spriteBatch,1);
        optionsButton.draw(spriteBatch,1);
        exitButton.draw(spriteBatch,1);
        spriteBatch.end();

        stage.act(delta);
        stage.draw();
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

