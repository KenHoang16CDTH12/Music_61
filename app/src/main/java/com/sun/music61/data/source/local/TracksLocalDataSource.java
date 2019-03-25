package com.sun.music61.data.source.local;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.common.base.Function;
import com.sun.music61.data.model.Track;
import com.sun.music61.data.source.TracksDataSource;

public class TracksLocalDataSource implements TracksDataSource.LocalDataSource {

    @Nullable
    private static TracksLocalDataSource sInstance;
    @NonNull
    private Function<Cursor, Track> mTrackMapperFunction;

    private TracksLocalDataSource() {
    }

    public static TracksLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TracksLocalDataSource();
        }
        return sInstance;
    }

    public static void destroyInstance() {
        sInstance = null;
    }
}
