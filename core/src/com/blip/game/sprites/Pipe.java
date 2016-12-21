package com.blip.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Makes a pair of pipes at a given position x
 */

public class Pipe
{
    private Rectangle boundsTop,boundsBot;
    private Texture topPipe, bottomPipe;
    private Vector2 posTopPipe,posBotPipe;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int PIPE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int PIPE_WIDTH = 52;


    public Pipe(float x)
    {
        topPipe = new Texture("toppipe.png");
        bottomPipe = new Texture("bottompipe.png");
        rand = new Random();

        posTopPipe = new Vector2(x,rand.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING);
        posBotPipe = new Vector2(x,posTopPipe.y - PIPE_GAP - bottomPipe.getHeight());

        boundsTop = new Rectangle(posTopPipe.x,posTopPipe.y,topPipe.getWidth(),topPipe.getHeight());
        boundsBot = new Rectangle(posBotPipe.x,posBotPipe.y,bottomPipe.getWidth(),bottomPipe.getHeight());
    }

    public void reposition(float x)
    {
        posTopPipe.set(x,rand.nextInt(FLUCTUATION) + PIPE_GAP + LOWEST_OPENING);
        posBotPipe.set(x,posTopPipe.y - PIPE_GAP - bottomPipe.getHeight());
        boundsTop.setPosition(posTopPipe.x,posTopPipe.y);
        boundsBot.setPosition(posBotPipe.x,posBotPipe.y);
    }

    public boolean collides(Rectangle player)
    {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public Texture getTopPipe()
    {
        return topPipe;
    }

    public Texture getBottomPipe()
    {
        return bottomPipe;
    }

    public Vector2 getPosTopPipe()
    {
        return posTopPipe;
    }

    public Vector2 getPosBotPipe()
    {
        return posBotPipe;
    }

    public void dispose()
    {
        topPipe.dispose();
        bottomPipe.dispose();
    }
}
