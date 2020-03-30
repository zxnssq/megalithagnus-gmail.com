package com.tetrisgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetrisgame.utils.AssetsManager;
import com.tetrisgame.view.GameplayScreen;
import com.tetrisgame.view.MainMenuScreen;

public class TetrisGame extends Game {

	private Screen screen;

	@Override
	public void create() {
		AssetsManager.init();


		screen = new MainMenuScreen(this);
		setScreen(screen);
	}


}
