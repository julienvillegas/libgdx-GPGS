package com.mygdx.game.client;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.ActionResolver;

/**
 * Created by julienvillegas on 09/12/2016.
 */

public class ActionResolverHTML implements ActionResolver {

    @Override
    public void signIn() {
        Gdx.app.log("SignIn","Dummy SignIn");
    }

    @Override
    public void signOut() {
        Gdx.app.log("SignOut","Dummy SignOut");
    }
}
