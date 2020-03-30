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
import com.tetrisgame.utils.Settings;

import java.util.Set;

public class OptionsScreen implements Screen {

    TetrisGame game;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    TextButton.TextButtonStyle textButtonStyleOn;
    TextButton.TextButtonStyle textButtonStyleOff;

    TextButton backToMenuButton;
    TextButton field10x15;
    TextButton field15x22;
    TextButton field20x30;

    TextButton difEasy;
    TextButton difMedium;
    TextButton difHard;

    int[] selectedField = new int[]{0,0,0};
    int[] selectedDif = new int[]{0,0,0};

    public OptionsScreen(TetrisGame game)
    {
        this.game = game;

        switch(Settings.FIELD_WIDTH)
        {
            case 10:{
                selectedField = new int[]{1, 0, 0};
                break;
            }
            case 15:{
                selectedField = new int[]{0,1,0};
                break;
            }
            case 20:
            {
                selectedField = new int[]{0,0,1};
                break;
            }
        }

        if (Settings.GAME_TICK == 0.5f) {
            selectedDif = new int[]{1, 0, 0};
        } else if (Settings.GAME_TICK == 0.33f) {
            selectedDif = new int[]{0, 1, 0};
        } else if (Settings.GAME_TICK == 0.25f) {
            selectedDif = new int[]{0, 0, 1};
        }
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        textButtonStyleOn = new TextButton.TextButtonStyle();
        textButtonStyleOn.font = AssetsManager.getMainFont();

        textButtonStyleOff = new TextButton.TextButtonStyle();
        textButtonStyleOff.font = AssetsManager.getMainFont();
        textButtonStyleOff.fontColor = Color.GRAY;

        backToMenuButton = new TextButton("BACK TO MENU", textButtonStyleOn);
        backToMenuButton.setHeight(50);
        backToMenuButton.setWidth(320);
        backToMenuButton.setPosition(20,10);

        backToMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }

        });

        field10x15 = new TextButton("10:15", selectedField[0] == 1 ? textButtonStyleOn: textButtonStyleOff);
        field10x15.setHeight(50);
        field10x15.setHeight(100);
        field10x15.setPosition(280,255);

        field10x15.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedField = new int[] {1,0,0};
                field10x15.setStyle(textButtonStyleOn);
                field15x22.setStyle(textButtonStyleOff);
                field20x30.setStyle(textButtonStyleOff);

                Settings.FIELD_WIDTH = 10;
                Settings.FIELD_HEIGHT = 15;
            }
        });

        field15x22 = new TextButton("15:22",  selectedField[1] == 1 ? textButtonStyleOn: textButtonStyleOff);
        field15x22.setHeight(50);
        field15x22.setHeight(100);
        field15x22.setPosition(396,255);

        field15x22.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedField = new int[] {0,1,0};
                field10x15.setStyle(textButtonStyleOff);
                field15x22.setStyle(textButtonStyleOn);
                field20x30.setStyle(textButtonStyleOff);

                Settings.FIELD_WIDTH = 15;
                Settings.FIELD_HEIGHT = 22;
            }
        });

        field20x30 = new TextButton("20:30",  selectedField[2] == 1 ? textButtonStyleOn: textButtonStyleOff);
        field20x30.setHeight(50);
        field20x30.setHeight(100);
        field20x30.setPosition(518,255);

        field20x30.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedField = new int[] {0,0,1};
                field10x15.setStyle(textButtonStyleOff);
                field15x22.setStyle(textButtonStyleOff);
                field20x30.setStyle(textButtonStyleOn);

                Settings.FIELD_WIDTH = 20;
                Settings.FIELD_HEIGHT = 30;
            }
        });

        difEasy = new TextButton("EASY",  selectedDif[0] == 1 ? textButtonStyleOn: textButtonStyleOff);
        difEasy.setHeight(50);
        difEasy.setHeight(100);
        difEasy.setPosition(290,195);

        difEasy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedDif = new int[] {1,0,0};
                difEasy.setStyle(textButtonStyleOn);
                difMedium.setStyle(textButtonStyleOff);
                difHard.setStyle(textButtonStyleOff);

                Settings.GAME_TICK = 0.5f;
            }
        });

        difMedium = new TextButton("MEDIUM",  selectedDif[1] == 1 ? textButtonStyleOn: textButtonStyleOff);
        difMedium.setHeight(50);
        difMedium.setHeight(100);
        difMedium.setPosition(382,195);

        difMedium.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedDif = new int[] {0,1,0};
                difEasy.setStyle(textButtonStyleOff);
                difMedium.setStyle(textButtonStyleOn);
                difHard.setStyle(textButtonStyleOff);

                Settings.GAME_TICK = 0.33f;
            }
        });

        difHard = new TextButton("HARD",  selectedDif[2] == 1 ? textButtonStyleOn: textButtonStyleOff);
        difHard.setHeight(50);
        difHard.setHeight(100);
        difHard.setPosition(534,195);

        difHard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectedDif = new int[] {0,0,1};
                difEasy.setStyle(textButtonStyleOff);
                difMedium.setStyle(textButtonStyleOff);
                difHard.setStyle(textButtonStyleOn);

                Settings.GAME_TICK = 0.25f;
            }
        });

        stage.addActor(backToMenuButton);
        stage.addActor(field10x15);
        stage.addActor(field15x22);
        stage.addActor(field20x30);
        stage.addActor(difEasy);
        stage.addActor(difMedium);
        stage.addActor(difHard);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        AssetsManager.getMainFont().getData().setScale(3,2.55f);
        AssetsManager.getMainFont().draw(spriteBatch, "OPTIONS",190,440);

        AssetsManager.getMainFont().getData().setScale(2,1.70f);
        AssetsManager.getMainFont().draw(spriteBatch, "PLAYFIELD:",20,320);


        field10x15.draw(spriteBatch,1);
        field15x22.draw(spriteBatch,1);
        field20x30.draw(spriteBatch,1);

        AssetsManager.getMainFont().getData().setScale(1.6f,1.70f);
        difEasy.draw(spriteBatch, 1);
        difMedium.draw(spriteBatch,1);
        difHard.draw(spriteBatch,1);

        AssetsManager.getMainFont().getData().setScale(2,1.70f);
        AssetsManager.getMainFont().draw(spriteBatch, "DIFFICULTY:",20,260);


        AssetsManager.getMainFont().getData().setScale(2,1.70f);
        backToMenuButton.draw(spriteBatch,1);

        spriteBatch.end();

        stage.act(delta);
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
