package com.sun.music61.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.sun.music61.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    private static final int TIME_SLEEP = 3000;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_act);
        autoSwitchHomeActivity();
    }

    private void autoSwitchHomeActivity() {
        new Thread(() -> {
            try {
                Thread.sleep(TIME_SLEEP);
            } catch (Exception ignored) {

            } finally {
                // Code late
            }
        }).start();
    }
}
