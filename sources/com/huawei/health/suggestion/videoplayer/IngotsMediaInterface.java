package com.huawei.health.suggestion.videoplayer;

import android.text.TextUtils;
import android.view.Surface;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Map;

/* loaded from: classes8.dex */
public abstract class IngotsMediaInterface {
    private static final String TAG = "Ingots_IngotsMediaInterface";
    private String mCurrentDataSource;
    private Map<String, String> mDataSource;
    private boolean mIsLoop;

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract boolean isPlaying();

    public abstract void pause();

    public abstract void prepare();

    public abstract void release();

    public abstract void seekTo(long j);

    public abstract void setSurface(Surface surface);

    public abstract void setVolume(float f, float f2);

    public abstract void start();

    public String getCurrentDataSource() {
        return this.mCurrentDataSource;
    }

    public void setCurrentDataSource(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "setCurrentDataSource() currentDataSource is null");
        } else {
            this.mCurrentDataSource = str;
        }
    }

    public Map<String, String> getDataSource() {
        return this.mDataSource;
    }

    public void setDataSource(Map<String, String> map) {
        if (map.isEmpty()) {
            LogUtil.h(TAG, "setDataSource() dataSource is null");
        } else {
            this.mDataSource = map;
        }
    }

    public boolean isLoop() {
        return this.mIsLoop;
    }

    public void setLoop(boolean z) {
        this.mIsLoop = z;
    }
}
