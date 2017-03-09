package com.immortal.ayaypaw.UpdateData;

/**
 * Created by ACER on 09/01/2016.
 */
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
       // Parse.initialize(this);
          Parse.initialize(this, "7ORiDnFbGsNrGUn3wdtt6EqsM3tfsnhCR6rnPStL", "BpBc3xOCDIMSLBKe5jIIYl8hiA5raodtj2AQlKGK");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
