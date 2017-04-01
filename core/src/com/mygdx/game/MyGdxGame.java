package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGdxGame extends ApplicationAdapter {

	private Stage stage;
	private Label statusLabel;

	private ActionResolver localActionActionResolver;

	public MyGdxGame(ActionResolver anActionResolver){
		this.localActionActionResolver = anActionResolver;
	}
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		Image logo = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("libgdx.png")))));
		logo.setPosition(Gdx.graphics.getWidth()/2-logo.getWidth()/2,(float)(Gdx.graphics.getHeight()*0.75));
		stage.addActor(logo);

		Label informationLabel = new Label("Simple Google Games Services Integration with Mobidevelop's roboVM",skin);
		informationLabel.setAlignment(Align.center);
		informationLabel.setWidth(Gdx.graphics.getWidth());
		informationLabel.setWrap(true);
		informationLabel.setPosition(0,Gdx.graphics.getHeight()/2);
		stage.addActor(informationLabel);

		statusLabel = new Label("Status: signed out",skin);
		statusLabel.setAlignment(Align.center);
		statusLabel.setWidth(Gdx.graphics.getWidth());
		statusLabel.setWrap(true);
		statusLabel.setPosition(0,Gdx.graphics.getHeight()/2-statusLabel.getHeight()*2);
		stage.addActor(statusLabel);

		TextButton signInButton = new TextButton("Sign In",skin);
		signInButton.setTouchable(Touchable.enabled);
		signInButton.setBounds(0,0,signInButton.getWidth(),signInButton.getHeight());
		signInButton.setPosition(Gdx.graphics.getWidth()/3-signInButton.getWidth()/2,Gdx.graphics.getHeight()/4-signInButton.getHeight()/2);
		signInButton.setWidth(Gdx.graphics.getWidth()/12*2);
		signInButton.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				updateStatus("clicked Sign In");
				localActionActionResolver.signIn();
			}

			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				return true;
			}

		});
		stage.addActor(signInButton);

		TextButton signOutButton = new TextButton("Sign Out",skin);
		signOutButton.setPosition(Gdx.graphics.getWidth()/3*2-signOutButton.getWidth()/2,Gdx.graphics.getHeight()/4-signOutButton.getHeight()/2);
		signOutButton.setWidth(Gdx.graphics.getWidth()/12*2);
		signOutButton.setTouchable(Touchable.disabled);
		signOutButton.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				updateStatus("clicked Sign out");
				localActionActionResolver.signOut();
			}

			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
			{
				return true;
			}
		});
		stage.addActor(signOutButton);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	public void updateStatus(String comment){
		statusLabel.setText("Status:"+comment);
	}
}
