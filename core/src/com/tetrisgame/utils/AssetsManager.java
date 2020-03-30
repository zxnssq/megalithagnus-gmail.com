package com.tetrisgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetsManager
{
    static BitmapFont mainFont;
    static Sound tickSound;
    static Sound comboSound;
    static Sound endingSound;

    public static void init()
    {

        mainFont = new BitmapFont(Gdx.files.internal("main.fnt"));
        tickSound = Gdx.audio.newSound(Gdx.files.internal("nes-03-01.wav"));
        comboSound = Gdx.audio.newSound(Gdx.files.internal("nes-10-10.wav"));
        endingSound = Gdx.audio.newSound(Gdx.files.internal("nes-15-00.wav"));
    }

    public static BitmapFont getMainFont() {
        return mainFont;
    }

    public static Sound getTickSound() {
        return tickSound;
    }

    public static Sound getComboSound() {
        return comboSound;
    }

    public static Sound getEndingSound() {
        return endingSound;
    }
}
