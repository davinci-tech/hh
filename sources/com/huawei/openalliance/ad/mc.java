package com.huawei.openalliance.ad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class mc {

    /* renamed from: a, reason: collision with root package name */
    private b f7201a;
    private a b;
    private Context c;
    private AudioManager d;
    private boolean e = false;

    public interface b {
        void a();
    }

    public void a(b bVar) {
        this.f7201a = bVar;
    }

    public void a() {
        if (this.e) {
            try {
                this.c.unregisterReceiver(this.b);
            } catch (Exception e) {
                ho.b("VolumeChangeObserver", "unregisterReceiver, " + e.getClass().getSimpleName());
            }
            this.f7201a = null;
            this.e = false;
        }
    }

    public float a(boolean z) {
        AudioManager audioManager = this.d;
        if (audioManager != null) {
            return md.a(audioManager, z);
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b b() {
        return this.f7201a;
    }

    static class a extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<mc> f7202a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            mc mcVar;
            b b;
            try {
                if (!((Constants.VOLUME_CHANGED_ACTION.equals(intent.getAction()) && intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", 0) == 3) || intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", 0) == 1) || (mcVar = this.f7202a.get()) == null || (b = mcVar.b()) == null) {
                    return;
                }
                b.a();
            } catch (Throwable th) {
                ho.c("VolumeChangeObserver", "onReceive error:" + th.getClass().getSimpleName());
            }
        }
    }

    public mc(Context context) {
        this.c = context;
        this.d = (AudioManager) context.getApplicationContext().getSystemService(PresenterUtils.AUDIO);
    }
}
