package com.huawei.healthcloud.plugintrack.trackanimation.bgm.choosedownload;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.gwg;
import health.compact.a.util.LogUtil;
import java.io.File;

/* loaded from: classes4.dex */
public class MusicPrePlayService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private MusicPrePlayBinder f3581a;
    private AudioManager b;
    private MediaPlayer e;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f3581a = new MusicPrePlayBinder();
        Object systemService = BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO);
        if (systemService instanceof AudioManager) {
            this.b = (AudioManager) systemService;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            LogUtil.c("Track_MusicPrePlayService", "onBind intent is null");
            return this.f3581a;
        }
        if (gwg.aUT_(this.b) == 0) {
            return this.f3581a;
        }
        String stringExtra = intent.getStringExtra("dynamic_music_path");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.e = MediaPlayer.create(this, Uri.fromFile(new File(stringExtra)));
        }
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.bgm.choosedownload.MusicPrePlayService.1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer2) {
                    if (mediaPlayer2 == null) {
                        LogUtil.c("Track_MusicPrePlayService", "mediaPlayer is null");
                    } else {
                        mediaPlayer2.seekTo(0);
                        mediaPlayer2.start();
                    }
                }
            });
            this.e.start();
            this.e.setLooping(true);
        }
        return this.f3581a;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.d("Track_MusicPrePlayService", "onUnbind enter");
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.e.stop();
            this.e.release();
            this.e = null;
        }
        gwg.aUN_(this.b);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                this.e.stop();
            }
            this.e.release();
        }
    }

    public class MusicPrePlayBinder extends Binder {
        public MusicPrePlayBinder() {
        }

        public MusicPrePlayService getService() {
            return MusicPrePlayService.this;
        }
    }
}
