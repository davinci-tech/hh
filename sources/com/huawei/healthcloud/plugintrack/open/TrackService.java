package com.huawei.healthcloud.plugintrack.open;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class TrackService extends Service {
    private TrackDataRemoteProxy e = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("Track_TrackService", "onCreate");
        this.e = new TrackDataRemoteProxy(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.a("Track_TrackService", "TrackService onStartCommand");
        super.onStartCommand(intent, i, i2);
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.e;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_TrackService", "TrackService onDestroy");
        this.e.onServiceDestroy();
        this.e = null;
    }
}
