package com.sun.music61.screen;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.sun.music61.R;
import com.sun.music61.data.model.Track;
import com.sun.music61.screen.play.PlayFragment;
import com.sun.music61.screen.service.PlayTrackListener;
import com.sun.music61.screen.service.PlayTrackService;
import com.sun.music61.util.ActivityUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.sun.music61.util.CommonUtils.Font;

public class MainActivity extends AppCompatActivity implements PlayTrackListener {

    private ServiceConnection mConnection;
    private PlayTrackService mService;

    public static Intent newInstance(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static void replaceFragment(@NonNull AppCompatActivity activity, Fragment fragment) {
        ActivityUtils.replaceFragmentToActivity(activity.getSupportFragmentManager(), fragment,
                R.id.contentMain);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Notes : add this code before setContentView
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder().setDefaultFontPath(Font.ARKHIP)
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        setContentView(R.layout.main_activity);
        initServiceConnection();
    }

    private void initServiceConnection() {
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PlayTrackService.TrackBinder trackBinder = (PlayTrackService.TrackBinder) service;
                mService = trackBinder.getService();
                // Default Fragment
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), MainFragment.newInstance(),
                        R.id.contentMain);
                addPlayTrackListener();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(PlayTrackService.getIntent(this), mConnection, BIND_AUTO_CREATE);
    }

    private void addPlayTrackListener() {
        mService.addListeners(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeService();
        removeListener();
    }

    private void removeListener() {
        mService.removeListener(this);
    }

    private void removeService() {
        unbindService(mConnection);
    }

    @Override
    public boolean onSupportNavigateUp() {
        replaceFragment(this, MainFragment.newInstance());
        return false;
    }

    @Override
    public void onState(int state) {
        // Code late
    }

    @Override
    public void onTrackChanged(Track track) {
        // Code late
    }

    public PlayTrackService getService() {
        return mService;
    }
}
