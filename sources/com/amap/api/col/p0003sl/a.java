package com.amap.api.col.p0003sl;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.amap.api.services.district.DistrictSearchQuery;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.autonavi.aps.amapapi.utils.g;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.hianalytics.core.transport.Utils;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class a {
    private static boolean A = false;
    Context b;

    /* renamed from: a, reason: collision with root package name */
    g f884a = null;
    PendingIntent c = null;
    String d = null;
    GeoFenceListener e = null;
    private Object z = new Object();
    volatile int f = 1;
    ArrayList<GeoFence> g = new ArrayList<>();
    c h = null;
    Object i = new Object();
    Object j = new Object();
    HandlerC0017a k = null;
    b l = null;
    volatile boolean m = false;
    volatile boolean n = false;
    volatile boolean o = false;
    com.amap.api.col.p0003sl.b p = null;
    com.amap.api.col.p0003sl.c q = null;
    AMapLocationClient r = null;
    volatile AMapLocation s = null;
    long t = 0;
    AMapLocationClientOption u = null;
    int v = 0;
    AMapLocationListener w = new AMapLocationListener() { // from class: com.amap.api.col.3sl.a.1
        /* JADX WARN: Removed duplicated region for block: B:15:0x005d A[Catch: all -> 0x009f, TryCatch #0 {all -> 0x009f, blocks: (B:2:0x0000, B:6:0x0007, B:9:0x000e, B:11:0x001b, B:13:0x0025, B:15:0x005d, B:17:0x0068, B:19:0x0073, B:20:0x0085, B:22:0x0093, B:25:0x0035), top: B:1:0x0000 }] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0068 A[Catch: all -> 0x009f, TryCatch #0 {all -> 0x009f, blocks: (B:2:0x0000, B:6:0x0007, B:9:0x000e, B:11:0x001b, B:13:0x0025, B:15:0x005d, B:17:0x0068, B:19:0x0073, B:20:0x0085, B:22:0x0093, B:25:0x0035), top: B:1:0x0000 }] */
        @Override // com.amap.api.location.AMapLocationListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onLocationChanged(com.amap.api.location.AMapLocation r13) {
            /*
                r12 = this;
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                boolean r0 = r0.y     // Catch: java.lang.Throwable -> L9f
                if (r0 == 0) goto L7
                return
            L7:
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                boolean r0 = r0.o     // Catch: java.lang.Throwable -> L9f
                if (r0 != 0) goto Le
                return
            Le:
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                r0.s = r13     // Catch: java.lang.Throwable -> L9f
                r0 = 0
                r2 = 0
                r3 = 8
                r4 = 1
                r5 = 0
                if (r13 == 0) goto L59
                int r6 = r13.getErrorCode()     // Catch: java.lang.Throwable -> L9f
                int r7 = r13.getErrorCode()     // Catch: java.lang.Throwable -> L9f
                if (r7 != 0) goto L35
                com.amap.api.col.3sl.a r13 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                long r7 = com.autonavi.aps.amapapi.utils.i.b()     // Catch: java.lang.Throwable -> L9f
                r13.t = r7     // Catch: java.lang.Throwable -> L9f
                com.amap.api.col.3sl.a r13 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                r7 = 5
                r13.a(r7, r2, r0)     // Catch: java.lang.Throwable -> L9f
                r13 = r4
                goto L5b
            L35:
                int r7 = r13.getErrorCode()     // Catch: java.lang.Throwable -> L9f
                java.lang.String r8 = r13.getErrorInfo()     // Catch: java.lang.Throwable -> L9f
                java.lang.String[] r9 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L9f
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9f
                java.lang.String r11 = "locationDetail:"
                r10.<init>(r11)     // Catch: java.lang.Throwable -> L9f
                java.lang.String r13 = r13.getLocationDetail()     // Catch: java.lang.Throwable -> L9f
                r10.append(r13)     // Catch: java.lang.Throwable -> L9f
                java.lang.String r13 = r10.toString()     // Catch: java.lang.Throwable -> L9f
                r9[r5] = r13     // Catch: java.lang.Throwable -> L9f
                java.lang.String r13 = "定位失败"
                com.amap.api.col.p0003sl.a.a(r13, r7, r8, r9)     // Catch: java.lang.Throwable -> L9f
                goto L5a
            L59:
                r6 = r3
            L5a:
                r13 = r5
            L5b:
                if (r13 == 0) goto L68
                com.amap.api.col.3sl.a r13 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                r13.v = r5     // Catch: java.lang.Throwable -> L9f
                com.amap.api.col.3sl.a r13 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                r3 = 6
                r13.a(r3, r2, r0)     // Catch: java.lang.Throwable -> L9f
                return
            L68:
                android.os.Bundle r13 = new android.os.Bundle     // Catch: java.lang.Throwable -> L9f
                r13.<init>()     // Catch: java.lang.Throwable -> L9f
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                boolean r0 = r0.m     // Catch: java.lang.Throwable -> L9f
                if (r0 != 0) goto L85
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                r1 = 7
                r0.b(r1)     // Catch: java.lang.Throwable -> L9f
                java.lang.String r0 = "interval"
                r1 = 2000(0x7d0, double:9.88E-321)
                r13.putLong(r0, r1)     // Catch: java.lang.Throwable -> L9f
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                r0.a(r3, r13, r1)     // Catch: java.lang.Throwable -> L9f
            L85:
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                int r1 = r0.v     // Catch: java.lang.Throwable -> L9f
                int r1 = r1 + r4
                r0.v = r1     // Catch: java.lang.Throwable -> L9f
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                int r0 = r0.v     // Catch: java.lang.Throwable -> L9f
                r1 = 3
                if (r0 < r1) goto L9f
                java.lang.String r0 = "location_errorcode"
                r13.putInt(r0, r6)     // Catch: java.lang.Throwable -> L9f
                com.amap.api.col.3sl.a r0 = com.amap.api.col.p0003sl.a.this     // Catch: java.lang.Throwable -> L9f
                r1 = 1002(0x3ea, float:1.404E-42)
                r0.a(r1, r13)     // Catch: java.lang.Throwable -> L9f
            L9f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.a.AnonymousClass1.onLocationChanged(com.amap.api.location.AMapLocation):void");
        }
    };
    final int x = 3;
    volatile boolean y = false;

    public a(Context context) {
        this.b = null;
        try {
            this.b = context.getApplicationContext();
            j();
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManger", "<init>");
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x002b -> B:12:0x0030). Please report as a decompilation issue!!! */
    private void j() {
        if (!this.o) {
            this.o = true;
        }
        if (this.n) {
            return;
        }
        try {
            if (Looper.myLooper() == null) {
                this.h = new c(this.b.getMainLooper());
            } else {
                this.h = new c();
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManger", "init 1");
        }
        try {
            b bVar = new b("fenceActionThread");
            this.l = bVar;
            bVar.setPriority(5);
            this.l.start();
            this.k = new HandlerC0017a(this.l.getLooper());
        } catch (Throwable th2) {
            com.autonavi.aps.amapapi.utils.b.a(th2, "GeoFenceManger", "init 2");
        }
        try {
            this.p = new com.amap.api.col.p0003sl.b(this.b);
            this.q = new com.amap.api.col.p0003sl.c();
            this.u = new AMapLocationClientOption();
            this.r = new AMapLocationClient(this.b);
            this.u.setLocationCacheEnable(true);
            this.u.setNeedAddress(false);
            this.r.setLocationListener(this.w);
            if (this.f884a == null) {
                this.f884a = new g();
            }
        } catch (Throwable th3) {
            com.autonavi.aps.amapapi.utils.b.a(th3, "GeoFenceManger", "initBase");
        }
        this.n = true;
        try {
            String str = this.d;
            if (str != null && this.c == null) {
                a(str);
            }
        } catch (Throwable th4) {
            com.autonavi.aps.amapapi.utils.b.a(th4, "GeoFenceManger", "init 4");
        }
        if (A) {
            return;
        }
        A = true;
        g.a(this.b, "O020", (JSONObject) null);
    }

    static final class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: com.amap.api.col.3sl.a$a, reason: collision with other inner class name */
    final class HandlerC0017a extends Handler {
        public HandlerC0017a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        a.this.b(message.getData());
                        break;
                    case 1:
                        a.this.c(message.getData());
                        break;
                    case 2:
                        a.this.e(message.getData());
                        break;
                    case 3:
                        a.this.d(message.getData());
                        break;
                    case 4:
                        a.this.f(message.getData());
                        break;
                    case 5:
                        a.this.e();
                        break;
                    case 6:
                        a aVar = a.this;
                        aVar.a(aVar.s);
                        break;
                    case 7:
                        a.this.d();
                        break;
                    case 8:
                        a.this.j(message.getData());
                        break;
                    case 9:
                        a.this.a(message.getData());
                        break;
                    case 10:
                        a.this.c();
                        break;
                    case 11:
                        a.this.h(message.getData());
                        break;
                    case 12:
                        a.this.g(message.getData());
                        break;
                    case 13:
                        a.this.g();
                        break;
                }
            } catch (Throwable unused) {
            }
        }
    }

    final class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        public c() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                Bundle data = message.getData();
                switch (message.what) {
                    case 1000:
                        a.this.i(data);
                        return;
                    case 1001:
                        try {
                            a.this.b((GeoFence) data.getParcelable("geoFence"));
                            return;
                        } catch (Throwable th) {
                            th.printStackTrace();
                            return;
                        }
                    case 1002:
                        try {
                            a.this.c(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                            return;
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
            } catch (Throwable unused) {
            }
        }
    }

    public final PendingIntent a(String str) {
        synchronized (this.z) {
            try {
                Intent intent = new Intent(str);
                intent.setPackage(hn.c(this.b));
                if (Build.VERSION.SDK_INT >= 31 && this.b.getApplicationInfo().targetSdkVersion >= 31) {
                    this.c = PendingIntent.getBroadcast(this.b, 0, intent, 33554432);
                } else {
                    this.c = PendingIntent.getBroadcast(this.b, 0, intent, 0);
                }
                this.d = str;
                ArrayList<GeoFence> arrayList = this.g;
                if (arrayList != null && !arrayList.isEmpty()) {
                    Iterator<GeoFence> it = this.g.iterator();
                    while (it.hasNext()) {
                        GeoFence next = it.next();
                        next.setPendingIntent(this.c);
                        next.setPendingIntentAction(this.d);
                    }
                }
            } finally {
                return this.c;
            }
        }
        return this.c;
    }

    public final void a(int i) {
        try {
            j();
            if (i > 7 || i <= 0) {
                i = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i);
            a(9, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    final void a(Bundle bundle) {
        int i = 1;
        if (bundle != null) {
            try {
                i = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        if (this.f != i) {
            ArrayList<GeoFence> arrayList = this.g;
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator<GeoFence> it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    next.setStatus(0);
                    next.setEnterTime(-1L);
                }
            }
            n();
        }
        this.f = i;
    }

    public final void a(GeoFenceListener geoFenceListener) {
        try {
            this.e = geoFenceListener;
        } catch (Throwable unused) {
        }
    }

    public final void a(DPoint dPoint, float f, String str) {
        try {
            j();
            Bundle bundle = new Bundle();
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("fenceRadius", f);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(0, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    final void b(Bundle bundle) {
        String str;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            int i = 1;
            if (bundle == null || bundle.isEmpty()) {
                str = "";
            } else {
                DPoint dPoint = (DPoint) bundle.getParcelable("centerPoint");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint != null) {
                    if (dPoint.getLatitude() <= 90.0d && dPoint.getLatitude() >= -90.0d && dPoint.getLongitude() <= 180.0d && dPoint.getLongitude() >= -180.0d) {
                        GeoFence a2 = a(bundle, false);
                        i = c(a2);
                        if (i == 0) {
                            arrayList.add(a2);
                        }
                    }
                    a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putInt("errorCode", i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1000, bundle2);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doAddGeoFenceRound");
        }
    }

    public final void a(List<DPoint> list, String str) {
        try {
            j();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("pointList", new ArrayList<>(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    final void c(Bundle bundle) {
        String str;
        GeoFence a2;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            int i = 1;
            if (bundle != null && !bundle.isEmpty()) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("pointList");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (parcelableArrayList != null && parcelableArrayList.size() > 2 && (i = c((a2 = a(bundle, true)))) == 0) {
                    arrayList.add(a2);
                }
            } else {
                str = "";
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle2.putInt("errorCode", i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle2);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doAddGeoFencePolygon");
        }
    }

    private GeoFence a(Bundle bundle, boolean z) {
        GeoFence geoFence = new GeoFence();
        ArrayList arrayList = new ArrayList();
        DPoint dPoint = new DPoint();
        if (z) {
            geoFence.setType(1);
            arrayList = bundle.getParcelableArrayList("pointList");
            if (arrayList != null) {
                dPoint = b(arrayList);
            }
            geoFence.setMaxDis2Center(b(dPoint, arrayList));
            geoFence.setMinDis2Center(a(dPoint, arrayList));
        } else {
            geoFence.setType(0);
            dPoint = (DPoint) bundle.getParcelable("centerPoint");
            if (dPoint != null) {
                arrayList.add(dPoint);
            }
            float f = bundle.getFloat("fenceRadius", 1000.0f);
            float f2 = f > 0.0f ? f : 1000.0f;
            geoFence.setRadius(f2);
            geoFence.setMinDis2Center(f2);
            geoFence.setMaxDis2Center(f2);
        }
        geoFence.setActivatesAction(this.f);
        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(arrayList);
        geoFence.setPointList(arrayList2);
        geoFence.setCenter(dPoint);
        geoFence.setPendingIntentAction(this.d);
        geoFence.setExpiration(-1L);
        geoFence.setPendingIntent(this.c);
        StringBuilder sb = new StringBuilder();
        sb.append(com.amap.api.col.p0003sl.c.a());
        geoFence.setFenceId(sb.toString());
        g gVar = this.f884a;
        if (gVar != null) {
            gVar.a(this.b, 2);
        }
        return geoFence;
    }

    public final void a(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        try {
            j();
            if (f <= 0.0f || f > 50000.0f) {
                f = 3000.0f;
            }
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f);
            bundle.putInt("searchSize", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            a(3, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    final void d(Bundle bundle) {
        b(2, bundle);
    }

    public final void a(String str, String str2, String str3, int i, String str4) {
        try {
            j();
            if (i <= 0) {
                i = 10;
            }
            if (i > 25) {
                i = 25;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString("poiType", str2);
            bundle.putString(DistrictSearchQuery.KEYWORDS_CITY, str3);
            bundle.putInt("searchSize", i);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            a(2, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    final void e(Bundle bundle) {
        b(1, bundle);
    }

    public final void a(String str, String str2) {
        try {
            j();
            Bundle bundle = new Bundle();
            bundle.putString("keyWords", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            a(4, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    final void f(Bundle bundle) {
        b(3, bundle);
    }

    private static boolean a(int i, String str, String str2, DPoint dPoint) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (i != 1) {
            if (i == 2) {
                if (dPoint == null) {
                    return false;
                }
                if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                    a("添加围栏失败", 0, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                    return false;
                }
            }
        } else if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return true;
    }

    private void b(int i, Bundle bundle) {
        String str;
        int i2;
        String str2;
        int i3;
        int i4;
        String a2;
        int i5;
        Bundle bundle2 = new Bundle();
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            if (bundle != null) {
                try {
                    if (!bundle.isEmpty()) {
                        List<GeoFence> arrayList2 = new ArrayList<>();
                        String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                        String string2 = bundle.getString("keyWords");
                        String string3 = bundle.getString(DistrictSearchQuery.KEYWORDS_CITY);
                        String string4 = bundle.getString("poiType");
                        DPoint dPoint = (DPoint) bundle.getParcelable("centerPoint");
                        int i6 = bundle.getInt("searchSize", 10);
                        float f = bundle.getFloat("aroundRadius", 3000.0f);
                        if (a(i, string2, string4, dPoint)) {
                            Bundle bundle3 = new Bundle();
                            bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                            bundle3.putString("pendingIntentAction", this.d);
                            bundle3.putLong("expiration", -1L);
                            bundle3.putInt("activatesAction", this.f);
                            try {
                                if (i == 1) {
                                    str2 = "errorCode";
                                    i4 = 2;
                                    bundle3.putFloat("fenceRadius", 1000.0f);
                                    a2 = this.p.a(this.b, "http://restsdk.amap.com/v3/place/text?", string2, string4, string3, String.valueOf(i6));
                                } else if (i == 2) {
                                    double b2 = i.b(dPoint.getLatitude());
                                    double b3 = i.b(dPoint.getLongitude());
                                    int intValue = Float.valueOf(f).intValue();
                                    bundle3.putFloat("fenceRadius", 200.0f);
                                    str2 = "errorCode";
                                    i4 = 2;
                                    a2 = this.p.a(this.b, "http://restsdk.amap.com/v3/place/around?", string2, string4, String.valueOf(i6), String.valueOf(b2), String.valueOf(b3), String.valueOf(intValue));
                                } else {
                                    a2 = i != 3 ? null : this.p.a(this.b, "http://restsdk.amap.com/v3/config/district?", string2);
                                    str2 = "errorCode";
                                    i4 = 2;
                                }
                                if (a2 != null) {
                                    int a3 = 1 == i ? com.amap.api.col.p0003sl.c.a(a2, arrayList2, bundle3) : 0;
                                    if (i4 == i) {
                                        a3 = com.amap.api.col.p0003sl.c.b(a2, arrayList2, bundle3);
                                    }
                                    if (3 == i) {
                                        a3 = this.q.c(a2, arrayList2, bundle3);
                                    }
                                    if (a3 == 10000) {
                                        if (arrayList2.isEmpty()) {
                                            i5 = 16;
                                        } else {
                                            int a4 = a(arrayList2);
                                            if (a4 == 0) {
                                                try {
                                                    arrayList.addAll(arrayList2);
                                                } catch (Throwable th) {
                                                    th = th;
                                                    i2 = a4;
                                                    str = str2;
                                                    try {
                                                        com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doAddGeoFenceNearby");
                                                        bundle2.putInt(str, 8);
                                                        a(1000, bundle2);
                                                        return;
                                                    } catch (Throwable th2) {
                                                        bundle2.putInt(str, i2);
                                                        a(1000, bundle2);
                                                        throw th2;
                                                    }
                                                }
                                            }
                                            i3 = a4;
                                        }
                                    } else {
                                        i5 = d(a3);
                                    }
                                } else {
                                    i5 = 4;
                                }
                                i3 = i5;
                            } catch (Throwable th3) {
                                th = th3;
                                str = "errorCode";
                                i2 = 0;
                                com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doAddGeoFenceNearby");
                                bundle2.putInt(str, 8);
                                a(1000, bundle2);
                                return;
                            }
                        } else {
                            str2 = "errorCode";
                            i3 = 1;
                        }
                        try {
                            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                            bundle2.putParcelableArrayList("resultList", arrayList);
                            bundle2.putInt(str2, i3);
                            a(1000, bundle2);
                        } catch (Throwable th4) {
                            th = th4;
                            i2 = i3;
                            str = str2;
                            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doAddGeoFenceNearby");
                            bundle2.putInt(str, 8);
                            a(1000, bundle2);
                            return;
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }
            str2 = "errorCode";
            i3 = 1;
            bundle2.putInt(str2, i3);
            a(1000, bundle2);
        } catch (Throwable th6) {
            th = th6;
            str = "errorCode";
        }
    }

    public final void a() {
        try {
            this.o = false;
            a(10, (Bundle) null, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "removeGeoFence");
        }
    }

    public final boolean a(GeoFence geoFence) {
        try {
            ArrayList<GeoFence> arrayList = this.g;
            if (arrayList != null && !arrayList.isEmpty()) {
                if (!this.g.contains(geoFence)) {
                    return false;
                }
                if (this.g.size() == 1) {
                    this.o = false;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("fc", geoFence);
                a(11, bundle, 0L);
                return true;
            }
            this.o = false;
            a(10, (Bundle) null, 0L);
            return true;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
            return false;
        }
    }

    public final void a(String str, boolean z) {
        try {
            j();
            Bundle bundle = new Bundle();
            bundle.putString("fid", str);
            bundle.putBoolean("ab", z);
            a(12, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "setGeoFenceAble");
        }
    }

    final void g(Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                String string = bundle.getString("fid");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                boolean z = bundle.getBoolean("ab", true);
                ArrayList<GeoFence> arrayList = this.g;
                if (arrayList != null && !arrayList.isEmpty()) {
                    Iterator<GeoFence> it = this.g.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        GeoFence next = it.next();
                        if (next.getFenceId().equals(string)) {
                            next.setAble(z);
                            break;
                        }
                    }
                }
                if (!z) {
                    if (k()) {
                        g();
                        return;
                    }
                    return;
                }
                n();
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doSetGeoFenceAble");
            }
        }
    }

    private boolean k() {
        ArrayList<GeoFence> arrayList = this.g;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<GeoFence> it = this.g.iterator();
            while (it.hasNext()) {
                if (it.next().isAble()) {
                    return false;
                }
            }
        }
        return true;
    }

    public final List<GeoFence> b() {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            return (ArrayList) this.g.clone();
        } catch (Throwable unused) {
            return new ArrayList();
        }
    }

    final void h(Bundle bundle) {
        try {
            if (this.g != null) {
                GeoFence geoFence = (GeoFence) bundle.getParcelable("fc");
                if (this.g.contains(geoFence)) {
                    this.g.remove(geoFence);
                }
                if (this.g.size() <= 0) {
                    c();
                } else {
                    n();
                }
            }
        } catch (Throwable unused) {
        }
    }

    final void c() {
        if (this.n) {
            ArrayList<GeoFence> arrayList = this.g;
            if (arrayList != null) {
                arrayList.clear();
                this.g = null;
            }
            if (this.o) {
                return;
            }
            m();
            AMapLocationClient aMapLocationClient = this.r;
            if (aMapLocationClient != null) {
                aMapLocationClient.stopLocation();
                this.r.onDestroy();
            }
            this.r = null;
            b bVar = this.l;
            if (bVar != null) {
                bVar.quitSafely();
            }
            this.l = null;
            this.p = null;
            synchronized (this.z) {
                PendingIntent pendingIntent = this.c;
                if (pendingIntent != null) {
                    pendingIntent.cancel();
                }
                this.c = null;
            }
            l();
            g gVar = this.f884a;
            if (gVar != null) {
                gVar.b(this.b);
            }
            this.m = false;
            this.n = false;
        }
    }

    private void l() {
        try {
            synchronized (this.j) {
                c cVar = this.h;
                if (cVar != null) {
                    cVar.removeCallbacksAndMessages(null);
                }
                this.h = null;
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "destroyResultHandler");
        }
    }

    private int c(GeoFence geoFence) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            if (this.g.contains(geoFence)) {
                return 17;
            }
            this.g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "addGeoFence2List");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private int a(List<GeoFence> list) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            Iterator<GeoFence> it = list.iterator();
            while (it.hasNext()) {
                c(it.next());
            }
            return 0;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "addGeoFenceList");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    final void a(int i, Bundle bundle, long j) {
        try {
            synchronized (this.i) {
                HandlerC0017a handlerC0017a = this.k;
                if (handlerC0017a != null) {
                    Message obtainMessage = handlerC0017a.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.setData(bundle);
                    this.k.sendMessageDelayed(obtainMessage, j);
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    final void b(int i) {
        try {
            synchronized (this.i) {
                HandlerC0017a handlerC0017a = this.k;
                if (handlerC0017a != null) {
                    handlerC0017a.removeMessages(i);
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    private void m() {
        try {
            synchronized (this.i) {
                HandlerC0017a handlerC0017a = this.k;
                if (handlerC0017a != null) {
                    handlerC0017a.removeCallbacksAndMessages(null);
                }
                this.k = null;
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "destroyActionHandler");
        }
    }

    final void a(int i, Bundle bundle) {
        try {
            synchronized (this.j) {
                c cVar = this.h;
                if (cVar != null) {
                    Message obtainMessage = cVar.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.setData(bundle);
                    this.h.sendMessage(obtainMessage);
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    final void i(Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                int i = bundle.getInt("errorCode");
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("resultList");
                if (parcelableArrayList == null) {
                    parcelableArrayList = new ArrayList();
                }
                String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (string == null) {
                    string = "";
                }
                GeoFenceListener geoFenceListener = this.e;
                if (geoFenceListener != null) {
                    geoFenceListener.onGeoFenceCreateFinished((ArrayList) parcelableArrayList.clone(), i, string);
                }
                if (i == 0) {
                    n();
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
            }
        }
    }

    private void n() {
        if (this.y || this.k == null) {
            return;
        }
        if (p()) {
            a(6, (Bundle) null, 0L);
            a(5, (Bundle) null, 0L);
        } else {
            b(7);
            a(7, (Bundle) null, 0L);
        }
    }

    private static Bundle a(GeoFence geoFence, String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        if (str == null) {
            str = "";
        }
        bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    final void c(int i) {
        try {
            if (this.b != null) {
                synchronized (this.z) {
                    if (this.c == null) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtras(a((GeoFence) null, (String) null, (String) null, 4, i));
                    this.c.send(this.b, 0, intent);
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    final void b(GeoFence geoFence) {
        try {
            synchronized (this.z) {
                if (this.b != null) {
                    if (this.c == null && geoFence.getPendingIntent() == null) {
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtras(a(geoFence, geoFence.getFenceId(), geoFence.getCustomId(), geoFence.getStatus(), 0));
                    String str = this.d;
                    if (str != null) {
                        intent.setAction(str);
                    }
                    intent.setPackage(hn.c(this.b));
                    if (geoFence.getPendingIntent() != null) {
                        geoFence.getPendingIntent().send(this.b, 0, intent);
                    } else {
                        this.c.send(this.b, 0, intent);
                    }
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "resultTriggerGeoFence");
        }
    }

    final void a(AMapLocation aMapLocation) {
        ArrayList<GeoFence> arrayList;
        try {
            if (this.y || (arrayList = this.g) == null || arrayList.isEmpty() || aMapLocation == null || aMapLocation.getErrorCode() != 0) {
                return;
            }
            Iterator<GeoFence> it = this.g.iterator();
            while (it.hasNext()) {
                GeoFence next = it.next();
                if (next.isAble() && b(aMapLocation, next) && a(next, this.f)) {
                    next.setCurrentLocation(aMapLocation);
                    d(next);
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    private void d(GeoFence geoFence) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("geoFence", geoFence);
        a(1001, bundle);
    }

    final void d() {
        try {
            if (this.r != null) {
                o();
                this.u.setOnceLocation(true);
                this.r.setLocationOption(this.u);
                this.r.startLocation();
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doStartOnceLocation");
        }
    }

    private void o() {
        try {
            if (this.m) {
                b(8);
            }
            AMapLocationClient aMapLocationClient = this.r;
            if (aMapLocationClient != null) {
                aMapLocationClient.stopLocation();
            }
            this.m = false;
        } catch (Throwable unused) {
        }
    }

    final void j(Bundle bundle) {
        try {
            if (this.r != null) {
                long j = 2000;
                if (bundle != null && !bundle.isEmpty()) {
                    j = bundle.getLong("interval", 2000L);
                }
                this.u.setOnceLocation(false);
                this.u.setInterval(j);
                this.r.setLocationOption(this.u);
                if (this.m) {
                    return;
                }
                this.r.stopLocation();
                this.r.startLocation();
                this.m = true;
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doStartContinueLocation");
        }
    }

    private boolean p() {
        return this.s != null && i.a(this.s) && i.b() - this.t < PreConnectManager.CONNECT_INTERNAL;
    }

    final void e() {
        try {
            if (!this.y && i.a(this.s)) {
                float a2 = a(this.s, this.g);
                if (a2 == Float.MAX_VALUE) {
                    return;
                }
                if (a2 < 1000.0f) {
                    b(7);
                    Bundle bundle = new Bundle();
                    bundle.putLong("interval", 2000L);
                    a(8, bundle, 500L);
                    return;
                }
                if (a2 < 5000.0f) {
                    o();
                    b(7);
                    a(7, (Bundle) null, PreConnectManager.CONNECT_INTERNAL);
                } else {
                    o();
                    b(7);
                    a(7, (Bundle) null, (long) (((a2 - 4000.0f) / 100.0f) * 1000.0f));
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    private static DPoint b(List<DPoint> list) {
        DPoint dPoint = new DPoint();
        if (list == null) {
            return dPoint;
        }
        try {
            double d = 0.0d;
            double d2 = 0.0d;
            for (DPoint dPoint2 : list) {
                d += dPoint2.getLatitude();
                d2 += dPoint2.getLongitude();
            }
            return new DPoint(i.b(d / list.size()), i.b(d2 / list.size()));
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceUtil", "getPolygonCenter");
            return dPoint;
        }
    }

    private static float a(AMapLocation aMapLocation, List<GeoFence> list) {
        float f = Float.MAX_VALUE;
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0 && list != null && !list.isEmpty()) {
            DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            for (GeoFence geoFence : list) {
                if (geoFence.isAble()) {
                    float a2 = i.a(dPoint, geoFence.getCenter());
                    if (a2 > geoFence.getMinDis2Center() && a2 < geoFence.getMaxDis2Center()) {
                        return 0.0f;
                    }
                    if (a2 > geoFence.getMaxDis2Center()) {
                        f = Math.min(f, a2 - geoFence.getMaxDis2Center());
                    }
                    if (a2 < geoFence.getMinDis2Center()) {
                        f = Math.min(f, geoFence.getMinDis2Center() - a2);
                    }
                }
            }
        }
        return f;
    }

    static float a(DPoint dPoint, List<DPoint> list) {
        float f = Float.MAX_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                f = Math.min(f, i.a(dPoint, it.next()));
            }
        }
        return f;
    }

    static float b(DPoint dPoint, List<DPoint> list) {
        float f = Float.MIN_VALUE;
        if (dPoint != null && list != null && !list.isEmpty()) {
            Iterator<DPoint> it = list.iterator();
            while (it.hasNext()) {
                f = Math.max(f, i.a(dPoint, it.next()));
            }
        }
        return f;
    }

    private static boolean a(AMapLocation aMapLocation, DPoint dPoint, float f) {
        return i.a(new double[]{dPoint.getLatitude(), dPoint.getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude()}) <= f;
    }

    private static boolean b(AMapLocation aMapLocation, List<DPoint> list) {
        if (list.size() < 3) {
            return false;
        }
        return com.autonavi.aps.amapapi.utils.b.a(new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()), list);
    }

    private static boolean a(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z = false;
        try {
            if (!i.a(aMapLocation) || geoFence == null || geoFence.getPointList() == null || geoFence.getPointList().isEmpty()) {
                return false;
            }
            int type = geoFence.getType();
            if (type != 0) {
                if (type != 1) {
                    if (type != 2) {
                        if (type != 3) {
                            return false;
                        }
                    }
                }
                Iterator<List<DPoint>> it = geoFence.getPointList().iterator();
                while (it.hasNext()) {
                    if (b(aMapLocation, it.next())) {
                        z = true;
                    }
                }
                return z;
            }
            return a(aMapLocation, geoFence.getCenter(), geoFence.getRadius());
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, Utils.TAG, "isInGeoFence");
            return false;
        }
    }

    private static boolean b(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z = false;
        try {
            if (a(aMapLocation, geoFence)) {
                if (geoFence.getEnterTime() == -1) {
                    if (geoFence.getStatus() == 1) {
                        return false;
                    }
                    geoFence.setEnterTime(i.b());
                    geoFence.setStatus(1);
                } else {
                    if (geoFence.getStatus() == 3 || i.b() - geoFence.getEnterTime() <= 600000) {
                        return false;
                    }
                    geoFence.setStatus(3);
                }
            } else {
                if (geoFence.getStatus() == 2) {
                    return false;
                }
                try {
                    geoFence.setStatus(2);
                    geoFence.setEnterTime(-1L);
                } catch (Throwable th) {
                    th = th;
                    z = true;
                    com.autonavi.aps.amapapi.utils.b.a(th, Utils.TAG, "isFenceStatusChanged");
                    return z;
                }
            }
            return true;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean a(GeoFence geoFence, int i) {
        boolean z = false;
        if ((i & 1) == 1) {
            try {
                if (geoFence.getStatus() == 1) {
                    z = true;
                }
            } catch (Throwable th) {
                boolean z2 = z;
                com.autonavi.aps.amapapi.utils.b.a(th, Utils.TAG, "remindStatus");
                return z2;
            }
        }
        if ((i & 2) == 2 && geoFence.getStatus() == 2) {
            z = true;
        }
        if ((i & 4) == 4) {
            if (geoFence.getStatus() == 3) {
                return true;
            }
        }
        return z;
    }

    private static int d(int i) {
        if (i != 1 && i != 7 && i != 4 && i != 5 && i != 16 && i != 17) {
            switch (i) {
                case 10000:
                    i = 0;
                    break;
                case 10001:
                case 10002:
                case 10007:
                case 10008:
                case 10009:
                case 10012:
                case 10013:
                    i = 7;
                    break;
                case 10003:
                case 10004:
                case 10005:
                case 10006:
                case 10010:
                case 10011:
                case PrebakedEffectId.RT_CALENDAR_DATE /* 10014 */:
                case 10015:
                case 10016:
                case 10017:
                    i = 4;
                    break;
                default:
                    switch (i) {
                        case 20000:
                        case 20001:
                        case 20002:
                            i = 1;
                            break;
                        default:
                            i = 8;
                            break;
                    }
            }
        }
        if (i != 0) {
            a("添加围栏失败", i, "searchErrCode is ".concat(String.valueOf(i)), new String[0]);
        }
        return i;
    }

    static void a(String str, int i, String str2, String... strArr) {
        StringBuffer stringBuffer = new StringBuffer("===========================================\n");
        stringBuffer.append("              " + str + "                ").append("\n-------------------------------------------\n");
        stringBuffer.append("errorCode:".concat(String.valueOf(i))).append("\n");
        stringBuffer.append("错误信息:".concat(String.valueOf(str2))).append("\n");
        if (strArr.length > 0) {
            for (String str3 : strArr) {
                stringBuffer.append(str3).append("\n");
            }
        }
        stringBuffer.append("===========================================\n");
        Log.i("fenceErrLog", stringBuffer.toString());
    }

    public final void f() {
        try {
            j();
            this.y = true;
            a(13, (Bundle) null, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "pauseGeoFence");
        }
    }

    final void g() {
        try {
            b(7);
            b(8);
            AMapLocationClient aMapLocationClient = this.r;
            if (aMapLocationClient != null) {
                aMapLocationClient.stopLocation();
            }
            this.m = false;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "doPauseGeoFence");
        }
    }

    public final void h() {
        try {
            j();
            if (this.y) {
                this.y = false;
                n();
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceManager", "resumeGeoFence");
        }
    }

    public final boolean i() {
        return this.y;
    }
}
