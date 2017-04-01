package com.mygdx.game;

import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.pods.google.games.GPGManager;
import org.robovm.pods.google.signin.GIDSignIn;
import org.robovm.pods.google.signin.GIDSignInUIDelegateAdapter;

/**
 * Created by julienvillegas on 09/12/2016.
 */

public class ActionResolverIOS implements ActionResolver {

    final public static String CLIENT_ID = "911237470974-mbig7g1ngcmb4rt1giq8sjcqpq86nl26.apps.googleusercontent.com";

    public ActionResolverIOS(IOSLauncher app){

        GPGManager.getSharedInstance().setStatusDelegate(app);

        GIDSignIn.getSharedInstance().setUiDelegate(new GIDSignInUIDelegateAdapter() {
            private UIViewController libgdxViewController;

            @Override
            public void willDispatch(GIDSignIn signIn, NSError error) {
                Foundation.log("willDispatch()");
            }

            @Override
            public void presentViewController(GIDSignIn signIn, UIViewController viewController) {
                Foundation.log("presentViewController()");
                libgdxViewController =  UIApplication.getSharedApplication().getKeyWindow().getRootViewController();
                libgdxViewController.presentViewController(viewController, true, null);
                UIApplication.getSharedApplication().getKeyWindow().setRootViewController(viewController);

            }

            @Override
            public void dismissViewController(GIDSignIn signIn, UIViewController viewController) {
                Foundation.log("dismissViewController()");
                viewController.dismissViewController(true, null);
            }
        });

    }

    @Override
    public void signIn() {
        GPGManager.getSharedInstance().signIn(CLIENT_ID, true);
    }

    @Override
    public void signOut() {
        GPGManager.getSharedInstance().signOut();
    }

}
