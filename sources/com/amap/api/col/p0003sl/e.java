package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.utils.g;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public final class e {
    static boolean g = false;
    Context e;
    private List<Messenger> w;
    private boolean o = false;
    private boolean p = false;

    /* renamed from: a, reason: collision with root package name */
    String f981a = null;
    b b = null;
    private long q = 0;
    private long r = 0;
    private com.autonavi.aps.amapapi.model.a s = null;
    AMapLocation c = null;
    private long t = 0;
    private int u = 0;
    a d = null;
    private j v = null;
    com.autonavi.aps.amapapi.b f = null;
    HashMap<Messenger, Long> h = new HashMap<>();
    g i = null;
    long j = 0;
    long k = 0;
    String l = null;
    private boolean x = true;
    private String y = "";
    AMapLocationClientOption m = null;
    AMapLocationClientOption n = new AMapLocationClientOption();

    static /* synthetic */ com.autonavi.aps.amapapi.model.a b(String str) {
        return a(10, str);
    }

    public e(Context context) {
        this.e = null;
        this.e = context;
    }

    public final void a() {
        try {
            this.i = new g();
            b bVar = new b("amapLocCoreThread");
            this.b = bVar;
            bVar.setPriority(5);
            this.b.start();
            this.d = new a(this.b.getLooper());
            this.w = new ArrayList();
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public final Handler b() {
        return this.d;
    }

    public final void a(Intent intent) {
        a aVar;
        if (!"true".equals(intent.getStringExtra("as")) || (aVar = this.d) == null) {
            return;
        }
        aVar.sendEmptyMessageDelayed(9, 100L);
    }

    final class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                try {
                    e.this.v = new j(e.this.e);
                } catch (Throwable th) {
                    com.autonavi.aps.amapapi.utils.b.a(th, "APSManager$ActionThread", "init 2");
                }
                try {
                    com.autonavi.aps.amapapi.utils.a.b(e.this.e);
                    com.autonavi.aps.amapapi.utils.a.a(e.this.e);
                } catch (Throwable th2) {
                    com.autonavi.aps.amapapi.utils.b.a(th2, "APSManager$ActionThread", "init 3");
                }
                e.this.f = new com.autonavi.aps.amapapi.b(false);
                super.onLooperPrepared();
            } catch (Throwable th3) {
                com.autonavi.aps.amapapi.utils.b.a(th3, "APSManager$ActionThread", "onLooperPrepared");
            }
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "APSManager$ActionThread", "run");
            }
        }
    }

    public final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Removed duplicated region for block: B:46:0x0110 A[Catch: all -> 0x011e, TryCatch #3 {all -> 0x011e, blocks: (B:7:0x0058, B:10:0x005e, B:12:0x011a, B:19:0x0063, B:20:0x0071, B:22:0x0075, B:24:0x007d, B:26:0x0089, B:27:0x0092, B:29:0x009a, B:31:0x00a6, B:32:0x00ae, B:34:0x00b2, B:36:0x00ba, B:38:0x00c6, B:40:0x00db, B:41:0x00e1, B:42:0x00e7, B:43:0x00ed, B:44:0x00fa, B:45:0x0105, B:46:0x0110, B:60:0x0051), top: B:59:0x0051 }] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x005c  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void handleMessage(android.os.Message r9) {
            /*
                Method dump skipped, instructions count: 314
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.3sl.e.a.handleMessage(android.os.Message):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Messenger messenger) {
        this.h.remove(messenger);
    }

    private static com.autonavi.aps.amapapi.model.a a(int i, String str) {
        try {
            com.autonavi.aps.amapapi.model.a aVar = new com.autonavi.aps.amapapi.model.a("");
            aVar.setErrorCode(i);
            aVar.setLocationDetail(str);
            return aVar;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    private void b(Messenger messenger) {
        try {
            this.f.f();
            if (com.autonavi.aps.amapapi.utils.a.k()) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("installMockApp", true);
                a(messenger, 9, bundle);
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "initAuth");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        try {
            if (this.o) {
                com.autonavi.aps.amapapi.b bVar = this.f;
                if (bVar != null) {
                    bVar.a();
                    return;
                }
                return;
            }
            com.autonavi.aps.amapapi.utils.b.a(this.e);
            if (bundle != null) {
                this.n = com.autonavi.aps.amapapi.utils.b.a(bundle.getBundle("optBundle"));
            }
            this.f.a(this.e);
            this.f.b();
            a(this.n);
            this.f.c();
            this.o = true;
            this.x = true;
            this.y = "";
            List<Messenger> list = this.w;
            if (list == null || list.size() <= 0) {
                return;
            }
            e();
        } catch (Throwable th) {
            this.x = false;
            th.printStackTrace();
            this.y = th.getMessage();
            com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "init");
        }
    }

    private void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            com.autonavi.aps.amapapi.b bVar = this.f;
            if (bVar != null) {
                bVar.a(aMapLocationClientOption);
            }
            if (aMapLocationClientOption != null) {
                g = aMapLocationClientOption.isKillProcess();
                if (this.m != null) {
                    if (aMapLocationClientOption.isOffset() != this.m.isOffset() || aMapLocationClientOption.isNeedAddress() != this.m.isNeedAddress() || aMapLocationClientOption.isLocationCacheEnable() != this.m.isLocationCacheEnable() || this.m.getGeoLanguage() != aMapLocationClientOption.getGeoLanguage()) {
                        this.r = 0L;
                    }
                    if (aMapLocationClientOption.isOffset() != this.m.isOffset() || this.m.getGeoLanguage() != aMapLocationClientOption.getGeoLanguage()) {
                        this.c = null;
                    }
                }
                this.m = aMapLocationClientOption;
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "setExtra");
        }
    }

    public final void c() {
        try {
            HashMap<Messenger, Long> hashMap = this.h;
            if (hashMap != null) {
                hashMap.clear();
                this.h = null;
            }
            try {
                List<Messenger> list = this.w;
                if (list != null) {
                    list.clear();
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "apm", "des1");
            }
            j jVar = this.v;
            if (jVar != null) {
                jVar.c();
                this.v = null;
            }
            this.o = false;
            this.p = false;
            this.f.e();
            a aVar = this.d;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.d = null;
            b bVar = this.b;
            if (bVar != null) {
                try {
                    com.autonavi.aps.amapapi.utils.e.a(bVar, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable unused) {
                    this.b.quit();
                }
            }
            this.b = null;
            if (this.i != null && this.j != 0 && this.k != 0) {
                long b2 = i.b();
                long j = this.j;
                g.a(this.e, this.i.c(this.e), this.i.d(this.e), this.k, b2 - j);
                this.i.e(this.e);
            }
            g.a(this.e);
            iv.b();
            if (g) {
                Process.killProcess(Process.myPid());
            }
        } catch (Throwable th2) {
            com.autonavi.aps.amapapi.utils.b.a(th2, "apm", "tdest");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (i.m(this.e)) {
            return;
        }
        try {
            com.autonavi.aps.amapapi.b bVar = this.f;
            if (bVar == null || bVar == null) {
                return;
            }
            bVar.a(this.d);
            this.f.g();
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "startColl");
        }
    }

    private static void a(Messenger messenger, int i, Bundle bundle) {
        if (messenger != null) {
            try {
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = i;
                messenger.send(obtain);
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "sendMessage");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty() || this.p) {
                    return;
                }
                this.p = true;
                b(messenger);
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "doInitAuth");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Messenger messenger, AMapLocation aMapLocation, String str, com.autonavi.aps.amapapi.a aVar) {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(AMapLocation.class.getClassLoader());
        bundle.putParcelable("loc", aMapLocation);
        bundle.putString("nb", str);
        bundle.putParcelable("statics", aVar);
        this.h.put(messenger, Long.valueOf(i.b()));
        a(messenger, 1, bundle);
    }

    private static AMapLocationClientOption b(Bundle bundle) {
        AMapLocationClientOption aMapLocationClientOption = null;
        try {
            aMapLocationClientOption = com.autonavi.aps.amapapi.utils.b.a(bundle.getBundle("optBundle"));
            try {
                String string = bundle.getString(FitRunPlayAudio.PLAY_TYPE_D);
                if (!TextUtils.isEmpty(string)) {
                    hr.a(string);
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "APSManager", "doLocation setUmidToken");
            }
        } catch (Throwable th2) {
            com.autonavi.aps.amapapi.utils.b.a(th2, "APSManager", "parseBundle");
        }
        return aMapLocationClientOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(17:26|27|(2:28|29)|(14:34|(1:36)(2:61|(1:63))|37|38|(1:40)|41|(1:43)|44|(2:46|47)(1:59)|48|49|(1:53)|55|56)|64|37|38|(0)|41|(0)|44|(0)(0)|48|49|(2:51|53)|55|56) */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0162, code lost:
    
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0163, code lost:
    
        com.autonavi.aps.amapapi.utils.b.a(r13, "ApsServiceCore", "fixLastLocation");
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0127 A[Catch: all -> 0x016c, TryCatch #2 {all -> 0x016c, blocks: (B:4:0x0008, B:9:0x0010, B:11:0x0029, B:13:0x002f, B:16:0x0047, B:18:0x004c, B:20:0x0079, B:22:0x0086, B:24:0x008f, B:26:0x00a0, B:38:0x011f, B:40:0x0127, B:41:0x012d, B:43:0x0131, B:44:0x013c, B:46:0x0140, B:55:0x0168, B:58:0x0163, B:67:0x00f9, B:49:0x014f, B:51:0x0155, B:53:0x0159, B:29:0x00a9, B:31:0x00b9, B:34:0x00c3, B:36:0x00cb, B:37:0x00eb, B:61:0x00d3, B:63:0x00dc, B:64:0x00e4), top: B:3:0x0008, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0131 A[Catch: all -> 0x016c, TryCatch #2 {all -> 0x016c, blocks: (B:4:0x0008, B:9:0x0010, B:11:0x0029, B:13:0x002f, B:16:0x0047, B:18:0x004c, B:20:0x0079, B:22:0x0086, B:24:0x008f, B:26:0x00a0, B:38:0x011f, B:40:0x0127, B:41:0x012d, B:43:0x0131, B:44:0x013c, B:46:0x0140, B:55:0x0168, B:58:0x0163, B:67:0x00f9, B:49:0x014f, B:51:0x0155, B:53:0x0159, B:29:0x00a9, B:31:0x00b9, B:34:0x00c3, B:36:0x00cb, B:37:0x00eb, B:61:0x00d3, B:63:0x00dc, B:64:0x00e4), top: B:3:0x0008, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0140 A[Catch: all -> 0x016c, TRY_LEAVE, TryCatch #2 {all -> 0x016c, blocks: (B:4:0x0008, B:9:0x0010, B:11:0x0029, B:13:0x002f, B:16:0x0047, B:18:0x004c, B:20:0x0079, B:22:0x0086, B:24:0x008f, B:26:0x00a0, B:38:0x011f, B:40:0x0127, B:41:0x012d, B:43:0x0131, B:44:0x013c, B:46:0x0140, B:55:0x0168, B:58:0x0163, B:67:0x00f9, B:49:0x014f, B:51:0x0155, B:53:0x0159, B:29:0x00a9, B:31:0x00b9, B:34:0x00c3, B:36:0x00cb, B:37:0x00eb, B:61:0x00d3, B:63:0x00dc, B:64:0x00e4), top: B:3:0x0008, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(android.os.Messenger r12, android.os.Bundle r13) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.e.b(android.os.Messenger, android.os.Bundle):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            com.autonavi.aps.amapapi.utils.a.c(this.e);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "doCallOtherSer");
        }
    }

    final void a(Messenger messenger, Bundle bundle, String str) {
        AMapLocationClientOption b2;
        float f;
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                double d = bundle.getDouble("lat");
                double d2 = bundle.getDouble("lon");
                float f2 = bundle.getFloat("radius");
                long j = bundle.getLong("time");
                if ("FINE_LOC".equals(str)) {
                    AMapLocation aMapLocation = new AMapLocation(GeocodeSearch.GPS);
                    aMapLocation.setLatitude(d);
                    aMapLocation.setLocationType(1);
                    aMapLocation.setLongitude(d2);
                    aMapLocation.setAccuracy(f2);
                    aMapLocation.setTime(j);
                    this.f.a(aMapLocation);
                }
                if (com.autonavi.aps.amapapi.utils.a.h() && (b2 = b(bundle)) != null && b2.isNeedAddress()) {
                    a(b2);
                    AMapLocation aMapLocation2 = this.c;
                    if (aMapLocation2 != null) {
                        f = i.a(new double[]{d, d2, aMapLocation2.getLatitude(), this.c.getLongitude()});
                        if (f < com.autonavi.aps.amapapi.utils.a.i() * 3) {
                            a(messenger, str);
                        }
                    } else {
                        f = -1.0f;
                    }
                    if (f == -1.0f || f > com.autonavi.aps.amapapi.utils.a.i()) {
                        a(bundle);
                        com.autonavi.aps.amapapi.model.a a2 = this.f.a(d, d2);
                        this.c = a2;
                        if (a2 == null || TextUtils.isEmpty(a2.getAdCode())) {
                            return;
                        }
                        a(messenger, str);
                    }
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "ApsServiceCore", "doLocationGeo");
            }
        }
    }

    private void a(Messenger messenger, String str) {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(AMapLocation.class.getClassLoader());
        bundle.putInt("I_MAX_GEO_DIS", com.autonavi.aps.amapapi.utils.a.i() * 3);
        bundle.putInt("I_MIN_GEO_DIS", com.autonavi.aps.amapapi.utils.a.i());
        bundle.putParcelable("loc", this.c);
        if ("COARSE_LOC".equals(str)) {
            a(messenger, 103, bundle);
        } else {
            a(messenger, 6, bundle);
        }
    }

    public final boolean a(String str) {
        if (TextUtils.isEmpty(this.l)) {
            this.l = com.autonavi.aps.amapapi.utils.b.b(this.e);
        }
        return !TextUtils.isEmpty(str) && str.equals(this.l);
    }

    public final void b(Intent intent) {
        String stringExtra = intent.getStringExtra(VideoPlayFlag.PLAY_IN_ALL);
        if (!TextUtils.isEmpty(stringExtra)) {
            ho.a(this.e, stringExtra);
        }
        String stringExtra2 = intent.getStringExtra(com.huawei.hms.scankit.b.H);
        this.f981a = stringExtra2;
        hn.a(stringExtra2);
        String stringExtra3 = intent.getStringExtra(FitRunPlayAudio.PLAY_TYPE_D);
        if (TextUtils.isEmpty(stringExtra3)) {
            return;
        }
        hr.a(stringExtra3);
    }

    public static void d() {
        g = false;
    }
}
