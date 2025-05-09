package com.huawei.hwdevice.mainprocess.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.SyncMusicBinder;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jiw;
import defpackage.jjd;
import defpackage.jjm;
import defpackage.jjn;
import defpackage.jjt;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class SyncMusicService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private SyncMusicBinder f6319a = new SyncMusicBinder(this);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("SyncMusicService", "OnCreate");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("SyncMusicService", "onStartCommand");
        if (intent == null) {
            stopSelf(i2);
            return 2;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            LogUtil.a("SyncMusicService", "onStartCommand failed, action is null");
            stopSelf(i2);
            return 2;
        }
        jiw.a().b(jjt.e().l());
        LogUtil.a("SyncMusicService", action);
        action.hashCode();
        if (action.equals("StartServiceFromHealthKit")) {
            LogUtil.a("SyncMusicService", "StartServiceFromHealthKit！");
            jjd.b(BaseApplication.getContext()).b(jjt.e().g());
            stopSelf(i2);
        } else if (action.equals("syncMusicListTaskBegin")) {
            f();
            stopSelf(i2);
        } else {
            LogUtil.h("SyncMusicService", "unknown actions！");
            stopSelf(i2);
        }
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.a("SyncMusicService", "onDestroy");
        jjt.e().c();
        SyncMusicBinder syncMusicBinder = this.f6319a;
        if (syncMusicBinder != null) {
            syncMusicBinder.setSyncMusicListener(null);
            this.f6319a = null;
        }
        jiw.a().b(null);
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a("onBind", new Object[0]);
        jjt.e().c(this.f6319a, intent != null ? intent.getStringExtra("device_id") : "");
        return this.f6319a;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.a("onUnbind", new Object[0]);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        LogUtil.a("onTaskRemoved", new Object[0]);
        jjt.e().c();
        super.onTaskRemoved(intent);
    }

    public boolean i() {
        return jjt.e().m();
    }

    public int a() {
        return jjt.e().d();
    }

    public String c() {
        return jjt.e().i();
    }

    public MusicSong e() {
        return jjt.e().b();
    }

    public String b() {
        return jjt.e().b().getSongName();
    }

    public List<MusicSong> g() {
        return jjt.e().h();
    }

    public List<MusicSong> h() {
        return jjt.e().j();
    }

    public void e(List<Integer> list) {
        jjn.a().b(list);
    }

    public void b(List<NegotiationData.TypeStruct> list) {
        jjn.a().e(list);
    }

    public void e(boolean z) {
        jjn.a().b(z);
    }

    public void d() {
        jjt.e().a();
    }

    private void f() {
        LogUtil.a("SyncMusicService", "startSyncMusicTaskByAction enter");
        ArrayList<MusicSong> c = jjm.e().c();
        if (koq.b(c)) {
            LogUtil.a("SyncMusicService", "get mToBeTransferedMusicList empty");
            stopSelf();
        } else if (jjt.e().f()) {
            LogUtil.a("SyncMusicService", "add more songs to Transferlist！");
            jjt.e().c(c);
        } else {
            LogUtil.a("SyncMusicService", "dealNewTransferTask！");
            jjt.e().d(c);
        }
    }
}
