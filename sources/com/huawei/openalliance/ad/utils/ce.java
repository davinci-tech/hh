package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.PlacementAdLoader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes5.dex */
public class ce {
    private static ce b;
    private static final byte[] c = new byte[0];
    private static final Set<String> i = new HashSet();
    private static final byte[] k = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private Context f7653a;
    private TimerTask d;
    private Timer e;
    private gc f;
    private long g;
    private int h = 4;
    private boolean j = false;

    public boolean c() {
        boolean z;
        synchronized (k) {
            z = this.j;
        }
        return z;
    }

    public void b() {
        ho.a("PlacementRequestTimer", "stop timer");
        Timer timer = this.e;
        if (timer != null) {
            timer.cancel();
        }
        this.d = null;
        this.e = null;
        synchronized (k) {
            this.j = false;
        }
    }

    public void a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        for (String str : strArr) {
            i.add(str);
        }
    }

    public void a(long j) {
        this.g = j;
    }

    public void a(int i2) {
        this.h = i2;
    }

    public void a() {
        ho.a("PlacementRequestTimer", "start timer");
        synchronized (k) {
            if (this.j) {
                ho.c("PlacementRequestTimer", "timer already running");
                return;
            }
            this.j = true;
            int bl = this.f.bl();
            ho.a("PlacementRequestTimer", "interval:%d", Integer.valueOf(bl));
            if (bl <= 0) {
                return;
            }
            if (this.e == null) {
                this.e = new Timer();
            }
            if (this.d == null) {
                this.d = new a(this.f7653a, (String[]) i.toArray(new String[0]), this.h);
            }
            int i2 = bl * 60000;
            long c2 = ao.c() - this.g;
            long j = i2;
            long j2 = c2 < j ? j - c2 : 0L;
            ho.a("PlacementRequestTimer", "schedule task, delay:%s,intervalMills:%d", Long.valueOf(j2), Integer.valueOf(i2));
            this.e.schedule(this.d, j2, j);
        }
    }

    /* loaded from: classes9.dex */
    static class a extends TimerTask {

        /* renamed from: a, reason: collision with root package name */
        private Context f7654a;
        private String[] b;
        private int c;

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ho.a("PlacementRequestTimer", "task excute, preload placement ad");
            new PlacementAdLoader.Builder(this.f7654a).setAutoCache(true).setDeviceType(this.c).setAdIds(this.b).setTest(false).build().preLoadAds();
            ce.a(this.f7654a).a(ao.c());
        }

        public a(Context context, String[] strArr, int i) {
            this.f7654a = context;
            this.b = (String[]) Arrays.copyOf(strArr, strArr.length);
            this.c = i;
        }
    }

    public static ce a(Context context) {
        ce ceVar;
        synchronized (c) {
            if (b == null) {
                b = new ce(context);
            }
            ceVar = b;
        }
        return ceVar;
    }

    private ce(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f7653a = applicationContext;
        this.f = fh.b(applicationContext);
    }
}
