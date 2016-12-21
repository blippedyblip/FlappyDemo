package com.blip.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.blip.game.FlappyDemo;

/**
 * Created by Callum on 21/12/2016.
 */

public class PlayState extends State
{
    private Texture bird;

    public PlayState(GameStateManager gameStateManager)
    {
        super(gameStateManager);
        bird = new Texture("bird.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
    }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(bird,50,50);
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {

    }
}
