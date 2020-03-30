package com.tetrisgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tetrisgame.TetrisGame;
import com.tetrisgame.utils.AssetsManager;

public class EvaluationScreen implements Screen {
    String gameScore;
    TetrisGame game;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    TextButton newGameButton;
    TextButton backToMenuButton;

    public EvaluationScreen(String gameScore, TetrisGame game)
    {
        this.gameScore = gameScore;
        this.game = game;
    }


    @Override
    public void show() {
        stage= new Stage();
        Gdx.input.setInputProcessor(stage);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = AssetsManager.getMainFont();
        newGameButton = new TextButton("NEW GAME",textButtonStyle);
        newGameButton.setText("NEW GAME");
        newGameButton.setHeight(60);
        newGameButton.setWidth(200);
        newGameButton.setPosition(225,125);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ConnectionWaitingScreen(game));
            }

        });

        backToMenuButton = new TextButton("BACK TO MENU", textButtonStyle);
        backToMenuButton.setHeight(60);
        backToMenuButton.setWidth(200);
        backToMenuButton.setPosition(225,70);

        backToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }

        });

        stage.addActor(backToMenuButton);
        stage.addActor(newGameButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        AssetsManager.getMainFont().getData().setScale(3,2.55f);
        AssetsManager.getMainFont().draw(spriteBatch, "SCORE",225,410);

        AssetsManager.getMainFont().getData().setScale(4,3.4f);
        AssetsManager.getMainFont().draw(spriteBatch,gameScore,120,350);

        AssetsManager.getMainFont().getData().setScale(2,1.7f);
        newGameButton.draw(spriteBatch,1);

        backToMenuButton.draw(spriteBatch,1);
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
