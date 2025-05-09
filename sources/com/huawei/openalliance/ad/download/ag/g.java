package com.huawei.openalliance.ad.download.ag;

import android.content.Context;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.ho;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private AppDownloadTask f6735a;
    private Timer b;
    private Context c;

    static class a extends TimerTask {

        /* renamed from: a, reason: collision with root package name */
        private AppDownloadTask f6736a;
        private Context b;

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (this.b != null) {
                ho.a("TaskDelTimer", "del task");
                d.a(this.b).b((d) this.f6736a);
            }
        }

        public a(Context context, AppDownloadTask appDownloadTask) {
            this.f6736a = appDownloadTask;
            this.b = context;
        }
    }

    public void a() {
        if (this.b == null) {
            this.b = new Timer();
        }
        ho.a("TaskDelTimer", "start timer");
        this.b.schedule(new a(this.c, this.f6735a), 2000L);
    }

    public g(Context context, AppDownloadTask appDownloadTask) {
        this.f6735a = appDownloadTask;
        this.c = context.getApplicationContext();
    }
}
