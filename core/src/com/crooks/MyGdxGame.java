package com.crooks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x,y,xv,yv;

	static final float maxVelocity = 100;    //create constants for values that will be used multiple times in many places
	static final float decelaration = 0.97f;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		move();

		Gdx.gl.glClearColor(1, 0, 0, 1);                //Every 60th of a second these 2 lines wipes the frame.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();									// This is where the code is drawn
		batch.draw(img, x, y);
		batch.end();
	}

	public void move(){
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = maxVelocity;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			yv= -maxVelocity;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xv = -maxVelocity;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			xv = maxVelocity;
		}
		float delta = Gdx.graphics.getDeltaTime();   //Amount of time passed since the last frame
		y += yv * delta;
		x += xv * delta;
		yv = decelarate(yv);  //Changes velocity by 5% per frame
		xv = decelarate(xv);

	}

	public float decelarate(float velocity){ //Slows velocity by 5% per frame
		velocity *= decelaration;
		if (Math.abs(velocity)<1){			//once it slows down to an extreme degree make it stop so the calculation is constantly running in the background.
			velocity = 0;
		}
		return velocity;
	}

}
