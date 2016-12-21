package com.blip.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blip.game.FlappyDemo;

/**
 * Created by Callum on 21/12/2016.
 */

public class MenuState extends State
{
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gameStateManager)
    {
        super(gameStateManager);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.draw(playBtn,cam.position.x - (playBtn.getWidth()/2),cam.position.y);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu state disposed");
    }
}
