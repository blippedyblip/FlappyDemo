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
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            gameStateManager.set(new PlayState(gameStateManager));
            dispose();
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
        spriteBatch.begin();
        spriteBatch.draw(background,0,0, FlappyDemo.WIDTH,FlappyDemo.HEIGHT);
        spriteBatch.draw(playBtn,(FlappyDemo.WIDTH/2) - (playBtn.getWidth()/2),FlappyDemo.HEIGHT/2);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
    }
}
