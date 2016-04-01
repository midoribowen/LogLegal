package com.epicodus.loglegal;

import android.app.Application;

import com.firebase.client.Firebase;

public class LogLegalApplication extends Application {
    private static LogLegalApplication app;
    private Firebase mFirebaseRef;

    public static LogLegalApplication getAppInstance() {
        return app;
    }

    public Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase(this.getString(R.string.firebase_url));
    }

}
