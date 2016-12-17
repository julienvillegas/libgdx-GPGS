package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSPropertyList;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.pods.google.games.GPGManager;
import org.robovm.pods.google.games.GPGStatusDelegate;
import org.robovm.pods.google.signin.GIDSignIn;

public class IOSLauncher extends IOSApplication.Delegate  implements GPGStatusDelegate {

    private ActionResolverIOS actionResolverIOS;
    private IOSApplication localApp;
    private UIApplication uiApp;

    @Override
    protected IOSApplication createApplication() {

        actionResolverIOS = new ActionResolverIOS(this);
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        localApp =  new IOSApplication(new MyGdxGame(actionResolverIOS), config);

        return localApp;
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();

    }

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        super.didFinishLaunching(application, launchOptions);
        GPGManager.getSharedInstance().signIn(ActionResolverIOS.CLIENT_ID, false);
        return true;
    }


    @Override
    public boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSObject annotation) {
        boolean canRespond = GIDSignIn.getSharedInstance().handleURL(url, sourceApplication, (NSPropertyList)annotation);
        if (canRespond) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void didFinishGamesSignIn(NSError error) {
        Gdx.app.log("ActionResolverIOS:signIn", "didFinishGamesSignIn");
    }

    @Override
    public void didFinishGamesSignOut(NSError error) {
        Gdx.app.log("ActionResolverIOS:signIn", "didFinishGamesSignOut");
    }

    @Override
    public void didFinishGoogleAuth(NSError error) {
        Gdx.app.log("ActionResolverIOS:signIn", "didFinishGoogleAuth");
    }

    @Override
    public boolean shouldReauthenticate(NSError error) {
        Gdx.app.log("ActionResolverIOS:signIn", "shouldReauthenticate");
        return false;
    }

    @Override
    public void willReauthenticate(NSError error) {
        Gdx.app.log("ActionResolverIOS:signIn", "willReauthenticate");
    }

    @Override
    public void didDisconnect(NSError error) {
        Gdx.app.log("ActionResolverIOS:signIn", "didDisconnect");
    }

}