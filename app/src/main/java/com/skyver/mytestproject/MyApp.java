package com.skyver.mytestproject;

import android.app.Application;

import com.zxy.recovery.callback.RecoveryCallback;
import com.zxy.recovery.core.Recovery;

import timber.log.Timber;

/**
 * Created by skyver on 5/22/17.
 */

public class MyApp extends Application {

    private static MyApp instance;

    @Override
    public void onCreate() {

        //initFireCrasher();

        super.onCreate();

        initTimber();

        //////////////////////Recovery FRAMEWORK
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(true)
                .recoverStack(true)
                .mainPage(ActivityForFatalEror.class)
                .recoverEnabled(true)
                .callback(new RecoveryCallback() {
                    @Override
                    public void stackTrace(String s) {
                        Timber.d("stackTrace() "+s);
                        //you need to add your crash reporting tool here
                        //Ex: Crashlytics.logException(throwable);

                    }

                    @Override
                    public void cause(String s) {
                        Timber.d("cause() "+s);

                    }

                    @Override
                    public void exception(String s, String s1, String s2, int i) {
                        Timber.d("exception() s: "+s+"\ns1: "+s1+"\ns2; "+s2);

                    }

                    @Override
                    public void throwable(Throwable throwable) {
                        Timber.d("throwable() "+throwable);

                    }
                })
                .silent(true, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                //.skip(TestActivity.class)
                .init(this);
        //////////////////////

        //Fabric.with(this, new Crashlytics());

        //FirebaseAnalytics.getInstance(this);

        // other things...

    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }
}
