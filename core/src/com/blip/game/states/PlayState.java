package com.blip.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.blip.game.FlappyDemo;
import com.blip.game.sprites.Bird;
import com.blip.game.sprites.Pipe;

/**
 * Created by Callum on 21/12/2016.
 */

public class PlayState extends State
{
    private static final int PIPE_SPACING = 125;
    private static final int PIPE_COUNT = 4;
    private static final int GROUND_OFFSET = -50;
    private Bird bird;
    private Texture bg;
    private Pipe pipe;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Array<Pipe> pipes;

    public PlayState(GameStateManager gameStateManager)
    {
        super(gameStateManager);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bird = new Bird(50, 250);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_OFFSET);

        pipes = new Array<Pipe>();

        for (int i = 1; i <= PIPE_COUNT; i++)
        {
            pipes.add(new Pipe(i * (PIPE_SPACING + Pipe.PIPE_WIDTH)));
        }
    }

    @Override
    public void handleInput()
    {
        if (Gdx.input.justTouched())
        {
            bird.jump();
        }
    }

    @Override
    public void update(float deltaTime)
    {
        handleInput();
        updateGround();
        bird.update(deltaTime);
        cam.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < pipes.size; i++)
        {
            Pipe pipe = pipes.get(i);

            //if the camera position is greater than the pipe position, reposition said pipe
            if (cam.position.x - (cam.viewportWidth / 2) > pipe.getPosTopPipe().x + pipe.getTopPipe().getWidth())
            {
                pipe.reposition(pipe.getPosTopPipe().x + ((Pipe.PIPE_WIDTH + PIPE_SPACING) * PIPE_COUNT));

                if (FlappyDemo.volume < 1f) FlappyDemo.volume += 0.05f;
                else FlappyDemo.volume = 1f;

                System.out.println(FlappyDemo.volume);
            }

            if (pipe.collides(bird.getBounds()))
            {
                gameStateManager.set(new PlayState(gameStateManager));
                FlappyDemo.volume = 0.1f;
            }
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET)
        {
            gameStateManager.set(new PlayState(gameStateManager));
            FlappyDemo.volume = 0.1f;
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch)
    {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Pipe pipe : pipes)
        {
            spriteBatch.draw(pipe.getTopPipe(), pipe.getPosTopPipe().x, pipe.getPosTopPipe().y);
            spriteBatch.draw(pipe.getBottomPipe(), pipe.getPosBotPipe().x, pipe.getPosBotPipe().y);
        }

        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);

        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        bg.dispose();
        bird.dispose();
        for (Pipe pipe : pipes)
        {
            pipe.dispose();
        }
        System.out.println("Play state disposed");
    }

    private void updateGround()
    {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
        {
            groundPos1.add(ground.getWidth() * 2, 0);
        }

        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
        {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
