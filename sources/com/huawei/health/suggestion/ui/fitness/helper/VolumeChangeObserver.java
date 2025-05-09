package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class VolumeChangeObserver {

    /* renamed from: a, reason: collision with root package name */
    private AudioManager f3167a;
    private VolumeChangeListener b;
    private Context c;
    private boolean d = false;
    private c e;

    public interface VolumeChangeListener {
        void onVolumeChanged(int i);
    }

    public VolumeChangeObserver(Context context) {
        this.c = context;
        if (context != null) {
            this.f3167a = (AudioManager) context.getApplicationContext().getSystemService(PresenterUtils.AUDIO);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public VolumeChangeListener c() {
        return this.b;
    }

    public void a(VolumeChangeListener volumeChangeListener) {
        this.b = volumeChangeListener;
    }

    public int d() {
        AudioManager audioManager = this.f3167a;
        if (audioManager != null) {
            return audioManager.getStreamVolume(3);
        }
        return -1;
    }

    public void a() {
        this.e = new c(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.VOLUME_CHANGED_ACTION);
        Context context = this.c;
        if (context != null) {
            context.registerReceiver(this.e, intentFilter);
            this.d = true;
        }
    }

    public void b() {
        Context context;
        if (!this.d || (context = this.c) == null) {
            return;
        }
        context.unregisterReceiver(this.e);
        this.b = null;
        this.d = false;
    }

    static class c extends BroadcastReceiver {
        private WeakReference<VolumeChangeObserver> b;

        c(VolumeChangeObserver volumeChangeObserver) {
            this.b = new WeakReference<>(volumeChangeObserver);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            VolumeChangeObserver volumeChangeObserver;
            VolumeChangeListener c;
            if (intent == null) {
                LogUtil.h("Suggestion_VolumeChangeObserver", "onReceive intent is null");
            } else {
                if (!Constants.VOLUME_CHANGED_ACTION.equals(intent.getAction()) || intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) != 3 || (volumeChangeObserver = this.b.get()) == null || (c = volumeChangeObserver.c()) == null) {
                    return;
                }
                c.onVolumeChanged(volumeChangeObserver.d());
            }
        }
    }
}
