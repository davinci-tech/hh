package com.amap.api.col.p0003sl;

import android.app.Application;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.amap.api.location.APSService;
import com.amap.api.location.UmidtokenInfo;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.utils.e;
import com.autonavi.aps.amapapi.utils.f;
import com.autonavi.aps.amapapi.utils.g;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.common.Constant;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public final class d {
    private static boolean G = true;
    private static boolean I = false;
    private static AtomicBoolean J = new AtomicBoolean(false);
    public static volatile boolean g = false;
    private Context C;
    private g D;

    /* renamed from: a, reason: collision with root package name */
    com.autonavi.aps.amapapi.model.a f955a;
    public c c;
    j j;
    Intent m;
    AMapLocationClientOption b = new AMapLocationClientOption();
    h d = null;
    private boolean E = false;
    private volatile boolean F = false;
    ArrayList<AMapLocationListener> e = new ArrayList<>();
    boolean f = false;
    public boolean h = true;
    public boolean i = true;
    Messenger k = null;
    Messenger l = null;
    int n = 0;
    private boolean H = true;
    b o = null;
    boolean p = false;
    AMapLocationClientOption.AMapLocationMode q = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
    Object r = new Object();
    g s = null;
    boolean t = false;
    e u = null;
    private AMapLocationClientOption K = new AMapLocationClientOption();
    private i L = null;
    String v = null;
    private ServiceConnection M = new ServiceConnection() { // from class: com.amap.api.col.3sl.d.2
        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            d.this.k = null;
            d.this.E = false;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                d.this.k = new Messenger(iBinder);
                d.this.E = true;
                d.this.t = true;
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", Constant.SERVICE_CONNECT_MESSAGE);
            }
        }
    };
    AMapLocationQualityReport w = null;
    boolean x = false;
    boolean y = false;
    private volatile boolean N = false;
    a z = null;
    String A = null;
    boolean B = false;

    public d(Context context, Intent intent, Looper looper) {
        this.m = null;
        this.C = context;
        this.m = intent;
        b(looper);
    }

    public final boolean a() {
        return this.E;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj, long j) {
        synchronized (this.r) {
            if (this.z != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                if (obj instanceof Bundle) {
                    obtain.setData((Bundle) obj);
                } else {
                    obtain.obj = obj;
                }
                this.z.sendMessageDelayed(obtain, j);
            }
        }
    }

    private void h() {
        synchronized (this.r) {
            a aVar = this.z;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.z = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        synchronized (this.r) {
            a aVar = this.z;
            if (aVar != null) {
                aVar.removeMessages(i);
            }
        }
    }

    private a a(Looper looper) {
        a aVar;
        synchronized (this.r) {
            aVar = new a(looper);
            this.z = aVar;
        }
        return aVar;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            this.K = aMapLocationClientOption.m78clone();
            a(1018, aMapLocationClientOption.m78clone(), 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "setLocationOption");
        }
    }

    public final void a(AMapLocationListener aMapLocationListener) {
        try {
            a(1002, aMapLocationListener, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "setLocationListener");
        }
    }

    public final void b(AMapLocationListener aMapLocationListener) {
        try {
            a(1005, aMapLocationListener, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "unRegisterLocationListener");
        }
    }

    public final void b() {
        c cVar;
        try {
            if (this.K.getCacheCallBack() && (cVar = this.c) != null) {
                cVar.sendEmptyMessageDelayed(13, this.K.getCacheCallBackTime());
            }
        } catch (Throwable unused) {
        }
        try {
            a(1003, (Object) null, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "startLocation");
        }
    }

    public final void c() {
        try {
            a(1004, (Object) null, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "stopLocation");
        }
    }

    public final void d() {
        try {
            i iVar = this.L;
            if (iVar != null) {
                iVar.b();
                this.L = null;
            }
            a(1011, (Object) null, 0L);
            this.p = true;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "onDestroy");
        }
    }

    public final AMapLocation e() {
        AMapLocation aMapLocation = null;
        try {
            j jVar = this.j;
            if (jVar != null && (aMapLocation = jVar.b()) != null) {
                aMapLocation.setTrustedLevel(3);
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "getLastKnownLocation");
        }
        return aMapLocation;
    }

    public final void a(WebView webView) {
        if (this.L == null) {
            this.L = new i(this.C, webView);
        }
        this.L.a();
    }

    public final void f() {
        try {
            i iVar = this.L;
            if (iVar != null) {
                iVar.b();
                this.L = null;
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "stopAssistantLocation");
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0029 -> B:7:0x002e). Please report as a decompilation issue!!! */
    private void b(Looper looper) {
        try {
            if (looper == null) {
                if (Looper.myLooper() == null) {
                    this.c = new c(this.C.getMainLooper());
                } else {
                    this.c = new c();
                }
            } else {
                this.c = new c(looper);
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "init 1");
        }
        try {
            try {
                this.j = new j(this.C);
            } catch (Throwable th2) {
                com.autonavi.aps.amapapi.utils.b.a(th2, "ALManager", "init 2");
            }
            b bVar = new b("amapLocManagerThread", this);
            this.o = bVar;
            bVar.setPriority(5);
            this.o.start();
            this.z = a(this.o.getLooper());
        } catch (Throwable th3) {
            com.autonavi.aps.amapapi.utils.b.a(th3, "ALManager", "init 5");
        }
        try {
            this.d = new h(this.C, this.c);
            this.D = new g(this.C, this.c);
        } catch (Throwable th4) {
            com.autonavi.aps.amapapi.utils.b.a(th4, "ALManager", "init 3");
        }
        if (this.s == null) {
            this.s = new g();
        }
        a(this.C);
    }

    private static void a(final Context context) {
        if (J.compareAndSet(false, true)) {
            la.a().a(new lb() { // from class: com.amap.api.col.3sl.d.1
                @Override // com.amap.api.col.p0003sl.lb
                public final void runTask() {
                    hr.e();
                    hr.a(context);
                    hr.h(context);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                boolean z = (th instanceof IllegalStateException) && th.getMessage().contains("sending message to a Handler on a dead thread");
                if ((th instanceof RemoteException) || z) {
                    this.k = null;
                    this.E = false;
                }
                com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.v)) {
            this.v = com.autonavi.aps.amapapi.utils.b.b(this.C);
        }
        bundle.putString("c", this.v);
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.setData(bundle);
        obtain.replyTo = this.l;
        Messenger messenger = this.k;
        if (messenger != null) {
            messenger.send(obtain);
        }
    }

    private boolean i() {
        boolean z = false;
        int i = 0;
        while (this.k == null) {
            try {
                Thread.sleep(100L);
                i++;
                if (i >= 50) {
                    break;
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "checkAPSManager");
            }
        }
        if (this.k == null) {
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setErrorCode(10);
            if (!i.k(this.C.getApplicationContext())) {
                aMapLocation.setLocationDetail("请检查配置文件是否配置服务，并且manifest中service标签是否配置在application标签内#1003");
            } else {
                aMapLocation.setLocationDetail("启动ApsServcie失败#1001");
            }
            bundle.putParcelable("loc", aMapLocation);
            obtain.setData(bundle);
            obtain.what = 1;
            this.c.sendMessage(obtain);
        } else {
            z = true;
        }
        if (!z) {
            if (!i.k(this.C.getApplicationContext())) {
                g.a((String) null, 2103);
            } else {
                g.a((String) null, 2101);
            }
        }
        return z;
    }

    private void a(Intent intent) {
        try {
            this.C.bindService(intent, this.M, 1);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "startServiceImpl");
        }
    }

    public final class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        public c() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                super.handleMessage(message);
                if (d.this.p) {
                    return;
                }
                int i = message.what;
                if (i == 1) {
                    Message obtainMessage = d.this.z.obtainMessage();
                    obtainMessage.what = 11;
                    obtainMessage.setData(message.getData());
                    d.this.z.sendMessage(obtainMessage);
                    return;
                }
                if (i != 2) {
                    if (i == 13) {
                        if (d.this.f955a != null) {
                            d dVar = d.this;
                            dVar.a(dVar.f955a);
                            return;
                        } else {
                            AMapLocation aMapLocation = new AMapLocation("LBS");
                            aMapLocation.setErrorCode(33);
                            d.this.a(aMapLocation);
                            return;
                        }
                    }
                    switch (i) {
                        case 5:
                            Bundle data = message.getData();
                            data.putBundle("optBundle", com.autonavi.aps.amapapi.utils.b.a(d.this.b));
                            d.this.a(10, data);
                            return;
                        case 6:
                            Bundle data2 = message.getData();
                            if (d.this.d != null) {
                                d.this.d.a(data2);
                                return;
                            }
                            return;
                        case 7:
                            d.this.H = message.getData().getBoolean("ngpsAble");
                            return;
                        case 8:
                            g.a((String) null, 2141);
                            break;
                        case 9:
                            boolean unused = d.I = message.getData().getBoolean("installMockApp");
                            return;
                        case 10:
                            d.this.a((AMapLocation) message.obj);
                            return;
                        default:
                            switch (i) {
                                case 100:
                                    g.a((String) null, 2155);
                                    break;
                                case 101:
                                    break;
                                case 102:
                                    Bundle data3 = message.getData();
                                    data3.putBundle("optBundle", com.autonavi.aps.amapapi.utils.b.a(d.this.b));
                                    d.this.a(15, data3);
                                    return;
                                case 103:
                                    Bundle data4 = message.getData();
                                    if (d.this.D != null) {
                                        d.this.D.a(data4);
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                            Message obtain = Message.obtain();
                            obtain.what = 1028;
                            obtain.obj = message.obj;
                            d.this.z.sendMessage(obtain);
                            if (d.this.K == null || !d.this.K.getCacheCallBack() || d.this.c == null) {
                                return;
                            }
                            d.this.c.removeMessages(13);
                            return;
                    }
                }
                Message obtain2 = Message.obtain();
                obtain2.what = 12;
                obtain2.obj = message.obj;
                d.this.z.sendMessage(obtain2);
                if (d.this.K == null || !d.this.K.getCacheCallBack() || d.this.c == null) {
                    return;
                }
                d.this.c.removeMessages(13);
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "AmapLocationManager$MainHandler", 0 == 0 ? "handleMessage" : null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AMapLocation aMapLocation) {
        try {
            if (aMapLocation.getErrorCode() != 0) {
                aMapLocation.setLocationType(0);
            }
            if (aMapLocation.getErrorCode() == 0) {
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
                if ((latitude == 0.0d && longitude == 0.0d) || latitude < -90.0d || latitude > 90.0d || longitude < -180.0d || longitude > 180.0d) {
                    g.a("errorLatLng", aMapLocation.toStr());
                    aMapLocation.setLocationType(0);
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("LatLng is error#0802");
                }
            }
            if (GeocodeSearch.GPS.equalsIgnoreCase(aMapLocation.getProvider()) || !this.d.b()) {
                aMapLocation.setAltitude(i.c(aMapLocation.getAltitude()));
                aMapLocation.setBearing(i.a(aMapLocation.getBearing()));
                aMapLocation.setSpeed(i.a(aMapLocation.getSpeed()));
                b(aMapLocation);
                Iterator<AMapLocationListener> it = this.e.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onLocationChanged(aMapLocation);
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
    }

    private void b(AMapLocation aMapLocation) {
        StringBuilder sb;
        if (aMapLocation != null) {
            try {
                String locationDetail = aMapLocation.getLocationDetail();
                if (TextUtils.isEmpty(locationDetail)) {
                    sb = new StringBuilder();
                } else {
                    sb = new StringBuilder(locationDetail);
                }
                boolean c2 = i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF");
                boolean c3 = i.c(this.C, "WYW5kcm9pZC5wZXJtaXNzaW9uLkNIQU5HRV9XSUZJX1NUQVRF");
                boolean c4 = i.c(this.C, "WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19MT0NBVElPTl9FWFRSQV9DT01NQU5EUw==");
                boolean c5 = i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU=");
                boolean c6 = i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19GSU5FX0xPQ0FUSU9O");
                boolean c7 = i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19DT0FSU0VfTE9DQVRJT04=");
                sb.append(c2 ? "#pm1" : "#pm0");
                sb.append(c3 ? "1" : "0");
                sb.append(c4 ? "1" : "0");
                sb.append(c5 ? "1" : "0");
                sb.append(c6 ? "1" : "0");
                sb.append(c7 ? "1" : "0");
                aMapLocation.setLocationDetail(sb.toString());
            } catch (Throwable unused) {
            }
        }
    }

    private void c(AMapLocation aMapLocation) {
        Message obtainMessage = this.c.obtainMessage();
        obtainMessage.what = 10;
        obtainMessage.obj = aMapLocation;
        this.c.sendMessage(obtainMessage);
    }

    private void d(AMapLocation aMapLocation) {
        synchronized (this) {
            if (aMapLocation == null) {
                try {
                    aMapLocation = new AMapLocation("");
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("coarse amapLocation is null#2005");
                } catch (Throwable th) {
                    com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "handlerCoarseLocation part2");
                    return;
                }
            }
            if (this.w == null) {
                this.w = new AMapLocationQualityReport();
            }
            this.w.setLocationMode(this.b.getLocationMode());
            if (this.D != null) {
                this.w.setGPSSatellites(aMapLocation.getSatellites());
                this.w.setGpsStatus(this.D.b());
            }
            this.w.setWifiAble(i.g(this.C));
            this.w.setNetworkType(i.h(this.C));
            this.w.setNetUseTime(0L);
            this.w.setInstallHighDangerMockApp(I);
            aMapLocation.setLocationQualityReport(this.w);
            try {
                if (this.F) {
                    g.a(this.C, aMapLocation);
                    c(aMapLocation.m77clone());
                    f.a(this.C).a(aMapLocation);
                    f.a(this.C).b();
                }
            } catch (Throwable th2) {
                com.autonavi.aps.amapapi.utils.b.a(th2, "ALManager", "handlerCoarseLocation part");
            }
            if (this.p) {
                return;
            }
            if (this.D != null) {
                l();
            }
            a(14, (Bundle) null);
        }
    }

    private void a(AMapLocation aMapLocation, com.autonavi.aps.amapapi.a aVar) {
        synchronized (this) {
            if (aMapLocation == null) {
                try {
                    aMapLocation = new AMapLocation("");
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("amapLocation is null#0801");
                } catch (Throwable th) {
                    com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "handlerLocation part3");
                    return;
                }
            }
            if (!GeocodeSearch.GPS.equalsIgnoreCase(aMapLocation.getProvider())) {
                aMapLocation.setProvider("lbs");
            }
            if (this.w == null) {
                this.w = new AMapLocationQualityReport();
            }
            this.w.setLocationMode(this.b.getLocationMode());
            h hVar = this.d;
            if (hVar != null) {
                this.w.setGPSSatellites(hVar.e());
                this.w.setGpsStatus(this.d.d());
            }
            this.w.setWifiAble(i.g(this.C));
            this.w.setNetworkType(i.h(this.C));
            if (aMapLocation.getLocationType() == 1 || GeocodeSearch.GPS.equalsIgnoreCase(aMapLocation.getProvider())) {
                this.w.setNetUseTime(0L);
            }
            if (aVar != null) {
                this.w.setNetUseTime(aVar.a());
            }
            this.w.setInstallHighDangerMockApp(I);
            aMapLocation.setLocationQualityReport(this.w);
            try {
                if (this.F) {
                    a(aMapLocation, this.A);
                    if (aVar != null) {
                        aVar.d(i.b());
                    }
                    g.a(this.C, aMapLocation, aVar);
                    g.a(this.C, aMapLocation);
                    c(aMapLocation.m77clone());
                    f.a(this.C).a(aMapLocation);
                    f.a(this.C).b();
                }
            } catch (Throwable th2) {
                com.autonavi.aps.amapapi.utils.b.a(th2, "ALManager", "handlerLocation part2");
            }
            if (this.p) {
                return;
            }
            if (this.b.isOnceLocation()) {
                l();
                a(14, (Bundle) null);
            }
        }
    }

    private void a(AMapLocation aMapLocation, String str) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("loc", aMapLocation);
        bundle.putString("lastLocNb", str);
        a(1014, bundle, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        try {
            Bundle data = message.getData();
            AMapLocation aMapLocation = (AMapLocation) data.getParcelable("loc");
            String string = data.getString("lastLocNb");
            e(aMapLocation);
            if (this.j.a(aMapLocation, string)) {
                this.j.d();
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "doSaveLastLocation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (this.e == null) {
            this.e = new ArrayList<>();
        }
        if (this.e.contains(aMapLocationListener)) {
            return;
        }
        this.e.add(aMapLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        synchronized (this) {
            if ((Build.VERSION.SDK_INT < 29 && !i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19DT0FSU0VfTE9DQVRJT04=") && !i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19GSU5FX0xPQ0FUSU9O")) || ((Build.VERSION.SDK_INT < 31 && Build.VERSION.SDK_INT >= 29 && !i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19GSU5FX0xPQ0FUSU9O")) || (Build.VERSION.SDK_INT >= 31 && !i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19DT0FSU0VfTE9DQVRJT04=") && !i.c(this.C, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19GSU5FX0xPQ0FUSU9O")))) {
                k();
                return;
            }
            if (this.b == null) {
                this.b = new AMapLocationClientOption();
            }
            if (this.F) {
                return;
            }
            this.F = true;
            int i = AnonymousClass3.f958a[this.b.getLocationMode().ordinal()];
            long j = 0;
            if (i == 1) {
                a(1027, (Object) null, 0L);
                a(1017, (Object) null, 0L);
                a(1016, (Object) null, 0L);
                return;
            }
            if (i == 2) {
                if (i.m(this.C)) {
                    a(1016);
                    a(1017, (Object) null, 0L);
                    a(1026, (Object) null, 0L);
                    return;
                } else {
                    a(1016);
                    a(1027, (Object) null, 0L);
                    a(1015, (Object) null, 0L);
                    return;
                }
            }
            if (i == 3) {
                if (i.m(this.C)) {
                    a(1016);
                    a(1017, (Object) null, 0L);
                    a(1026, (Object) null, 0L);
                } else {
                    a(1027, (Object) null, 0L);
                    a(1015, (Object) null, 0L);
                    if (this.b.isGpsFirst() && this.b.isOnceLocation()) {
                        j = this.b.getGpsFirstTimeout();
                    }
                    a(1016, (Object) null, j);
                }
            }
        }
    }

    /* renamed from: com.amap.api.col.3sl.d$3, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f958a;

        static {
            int[] iArr = new int[AMapLocationClientOption.AMapLocationMode.values().length];
            f958a = iArr;
            try {
                iArr[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f958a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f958a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void k() {
        AMapLocation aMapLocation = new AMapLocation("");
        aMapLocation.setErrorCode(12);
        aMapLocation.setLocationDetail("定位权限被禁用,请授予应用定位权限 #1201");
        if (this.w == null) {
            this.w = new AMapLocationQualityReport();
        }
        AMapLocationQualityReport aMapLocationQualityReport = new AMapLocationQualityReport();
        this.w = aMapLocationQualityReport;
        aMapLocationQualityReport.setGpsStatus(4);
        this.w.setGPSSatellites(0);
        this.w.setLocationMode(this.b.getLocationMode());
        this.w.setWifiAble(i.g(this.C));
        this.w.setNetworkType(i.h(this.C));
        this.w.setNetUseTime(0L);
        aMapLocation.setLocationQualityReport(this.w);
        g.a((String) null, 2121);
        c(aMapLocation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        try {
            a(1025);
            h hVar = this.d;
            if (hVar != null) {
                hVar.a();
            }
            g gVar = this.D;
            if (gVar != null) {
                gVar.a();
            }
            a(1016);
            this.F = false;
            this.n = 0;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "stopLocation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(AMapLocationListener aMapLocationListener) {
        if (!this.e.isEmpty() && this.e.contains(aMapLocationListener)) {
            this.e.remove(aMapLocationListener);
        }
        if (this.e.isEmpty()) {
            l();
        }
    }

    final void g() {
        a(12, (Bundle) null);
        this.h = true;
        this.i = true;
        this.E = false;
        this.t = false;
        l();
        g gVar = this.s;
        if (gVar != null) {
            gVar.b(this.C);
        }
        f.a(this.C).a();
        g.a(this.C);
        e eVar = this.u;
        if (eVar != null) {
            eVar.b().sendEmptyMessage(11);
        } else {
            ServiceConnection serviceConnection = this.M;
            if (serviceConnection != null) {
                this.C.unbindService(serviceConnection);
            }
        }
        try {
            if (this.B) {
                this.C.stopService(q());
            }
        } catch (Throwable unused) {
        }
        this.B = false;
        ArrayList<AMapLocationListener> arrayList = this.e;
        if (arrayList != null) {
            arrayList.clear();
            this.e = null;
        }
        this.M = null;
        h();
        b bVar = this.o;
        if (bVar != null) {
            try {
                e.a(bVar, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
            } catch (Throwable unused2) {
                this.o.quit();
            }
        }
        this.o = null;
        c cVar = this.c;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        j jVar = this.j;
        if (jVar != null) {
            jVar.c();
            this.j = null;
        }
    }

    private void m() {
        com.autonavi.aps.amapapi.model.a b2 = b(new com.autonavi.aps.amapapi.b(true));
        if (i()) {
            Bundle bundle = new Bundle();
            String str = (b2 == null || !(b2.getLocationType() == 2 || b2.getLocationType() == 4)) ? "0" : "1";
            bundle.putBundle("optBundle", com.autonavi.aps.amapapi.utils.b.a(this.b));
            bundle.putString("isCacheLoc", str);
            a(0, bundle);
            if (this.F) {
                a(13, (Bundle) null);
            }
        }
    }

    private void a(com.autonavi.aps.amapapi.b bVar, com.autonavi.aps.amapapi.a aVar) {
        try {
            bVar.a(this.C);
            bVar.a(this.b);
            bVar.b(aVar);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "initApsBase");
        }
    }

    private com.autonavi.aps.amapapi.model.a a(com.autonavi.aps.amapapi.b bVar, boolean z) {
        if (!this.b.isLocationCacheEnable()) {
            return null;
        }
        try {
            return bVar.a(z);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "doFirstCacheLoc");
            return null;
        }
    }

    private static void a(com.autonavi.aps.amapapi.b bVar) {
        try {
            bVar.d();
            bVar.a(new AMapLocationClientOption().setNeedAddress(false));
            bVar.a(true, new com.autonavi.aps.amapapi.a());
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "apsLocation:doFirstNetLocate 2");
        }
    }

    private static void a(com.autonavi.aps.amapapi.b bVar, com.autonavi.aps.amapapi.model.a aVar) {
        if (aVar != null) {
            try {
                if (aVar.getErrorCode() == 0) {
                    bVar.b(aVar);
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "apsLocation:doFirstAddCache");
            }
        }
    }

    private com.autonavi.aps.amapapi.model.a b(com.autonavi.aps.amapapi.b bVar) {
        com.autonavi.aps.amapapi.model.a aVar;
        Throwable th;
        com.autonavi.aps.amapapi.model.a aVar2;
        boolean z;
        String k;
        c cVar;
        j jVar;
        AMapLocation aMapLocation = null;
        this.f955a = null;
        com.autonavi.aps.amapapi.a aVar3 = new com.autonavi.aps.amapapi.a();
        try {
            aVar3.c(i.b());
            try {
                String apikey = AMapLocationClientOption.getAPIKEY();
                if (!TextUtils.isEmpty(apikey)) {
                    ho.a(this.C, apikey);
                }
            } catch (Throwable th2) {
                com.autonavi.aps.amapapi.utils.b.a(th2, "ALManager", "apsLocation setAuthKey");
            }
            try {
                String umidtoken = UmidtokenInfo.getUmidtoken();
                if (!TextUtils.isEmpty(umidtoken)) {
                    hr.a(umidtoken);
                }
            } catch (Throwable th3) {
                com.autonavi.aps.amapapi.utils.b.a(th3, "ALManager", "apsLocation setUmidToken");
            }
            a(bVar, aVar3);
            boolean l = com.autonavi.aps.amapapi.utils.a.l();
            boolean z2 = false;
            try {
            } catch (Throwable th4) {
                com.autonavi.aps.amapapi.utils.b.a(th4, "ALManager", "apscach");
            }
            if (this.K.getCacheCallBack()) {
                aVar = a(bVar, this.K.getCacheCallBack());
                if (aVar != null) {
                    if (!com.autonavi.aps.amapapi.utils.a.a(aVar.getTime())) {
                        if (this.K.getCacheCallBack()) {
                            int cacheTimeOut = this.K.getCacheTimeOut();
                            long a2 = i.a() - aVar.getTime();
                            if (a2 > 0 && a2 < cacheTimeOut) {
                                this.f955a = aVar;
                                aVar.setLocationType(10);
                            }
                        }
                    }
                }
                aVar = null;
            } else {
                aVar = a(bVar, false);
            }
            if (aVar == null) {
                try {
                    aVar = bVar.a(!l, aVar3);
                    if (aVar != null) {
                        if (aVar.getErrorCode() == 0) {
                            z2 = true;
                        }
                    }
                } catch (Throwable th5) {
                    try {
                        com.autonavi.aps.amapapi.utils.b.a(th5, "ALManager", "apsLocation:doFirstNetLocate");
                    } catch (Throwable th6) {
                        th = th6;
                        try {
                            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "apsLocation");
                            aVar2 = aVar;
                            return aVar2;
                        } finally {
                            try {
                                bVar.e();
                            } catch (Throwable unused) {
                            }
                        }
                    }
                }
                aVar2 = aVar;
                z = z2;
                z2 = true;
            } else {
                aVar2 = aVar;
                z = false;
            }
            if (aVar2 != null) {
                try {
                    k = aVar2.k();
                    aMapLocation = aVar2.m77clone();
                } catch (Throwable th7) {
                    th = th7;
                    aVar = aVar2;
                    com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "apsLocation");
                    aVar2 = aVar;
                    return aVar2;
                }
            } else {
                k = null;
            }
            try {
                if (this.b.isLocationCacheEnable() && (jVar = this.j) != null) {
                    aMapLocation = jVar.a(aMapLocation, k, this.b.getLastLocationLifeCycle());
                }
            } catch (Throwable th8) {
                com.autonavi.aps.amapapi.utils.b.a(th8, "ALManager", "fixLastLocation");
            }
            try {
                if (this.K.getCacheCallBack() && (cVar = this.c) != null) {
                    cVar.removeMessages(13);
                }
            } catch (Throwable unused2) {
            }
            try {
                Bundle bundle = new Bundle();
                if (aMapLocation != null) {
                    bundle.putParcelable("loc", aMapLocation);
                    bundle.putString("nb", aVar2.k());
                    bundle.putParcelable("statics", aVar3);
                }
                a(bundle);
                if (z) {
                    a(bVar, aVar2);
                }
            } catch (Throwable th9) {
                com.autonavi.aps.amapapi.utils.b.a(th9, "ALManager", "apsLocation:callback");
            }
            if (z2 && l && !g) {
                g = true;
                a(bVar);
            }
        } catch (Throwable th10) {
            aVar = null;
            th = th10;
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "apsLocation");
            aVar2 = aVar;
            return aVar2;
        }
        return aVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        try {
            if (G || (!this.t && !this.N)) {
                G = false;
                this.N = true;
                m();
            } else {
                try {
                    if (this.t && !a() && !this.y) {
                        this.y = true;
                        p();
                    }
                } catch (Throwable th) {
                    this.y = true;
                    com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "doLBSLocation reStartService");
                }
                if (i()) {
                    this.y = false;
                    Bundle bundle = new Bundle();
                    bundle.putBundle("optBundle", com.autonavi.aps.amapapi.utils.b.a(this.b));
                    bundle.putString(FitRunPlayAudio.PLAY_TYPE_D, UmidtokenInfo.getUmidtoken());
                    if (!this.d.b()) {
                        a(1, bundle);
                    }
                }
            }
        } catch (Throwable th2) {
            try {
                com.autonavi.aps.amapapi.utils.b.a(th2, "ALManager", "doLBSLocation");
                try {
                    if (this.b.isOnceLocation()) {
                        return;
                    }
                    o();
                } catch (Throwable unused) {
                }
            } finally {
                try {
                    if (!this.b.isOnceLocation()) {
                        o();
                    }
                } catch (Throwable unused2) {
                }
            }
        }
    }

    private void o() {
        if (this.b.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
            a(1016, (Object) null, this.b.getInterval() >= 1000 ? this.b.getInterval() : 1000L);
        }
    }

    static final class b extends HandlerThread {

        /* renamed from: a, reason: collision with root package name */
        d f960a;

        public b(String str, d dVar) {
            super(str);
            this.f960a = dVar;
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                this.f960a.j.a();
                f.a(this.f960a.C);
                this.f960a.p();
                d dVar = this.f960a;
                if (dVar != null && dVar.C != null) {
                    com.autonavi.aps.amapapi.utils.a.b(this.f960a.C);
                    com.autonavi.aps.amapapi.utils.a.a(this.f960a.C);
                }
                super.onLooperPrepared();
            } catch (Throwable unused) {
            }
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        try {
            if (this.l == null) {
                this.l = new Messenger(this.c);
            }
            a(q());
        } catch (Throwable unused) {
        }
    }

    private Intent q() {
        String str;
        if (this.m == null) {
            this.m = new Intent(this.C, (Class<?>) APSService.class);
        }
        try {
            if (!TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY())) {
                str = AMapLocationClientOption.getAPIKEY();
            } else {
                str = hn.f(this.C);
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "startServiceImpl p2");
            str = "";
        }
        this.m.putExtra(VideoPlayFlag.PLAY_IN_ALL, str);
        this.m.putExtra(com.huawei.hms.scankit.b.H, hn.c(this.C));
        this.m.putExtra(FitRunPlayAudio.PLAY_TYPE_D, UmidtokenInfo.getUmidtoken());
        return this.m;
    }

    public final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v6 */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Throwable th;
            int i;
            Object obj = null;
            try {
                super.handleMessage(message);
                i = message.what;
            } catch (Throwable th2) {
                th = th2;
            }
            if (i == 11) {
                d.this.a(message.getData());
                return;
            }
            if (i == 12) {
                d.this.b(message);
                return;
            }
            if (i != 1011) {
                try {
                    switch (i) {
                        case 1002:
                            d.this.c((AMapLocationListener) message.obj);
                            return;
                        case 1003:
                            d.this.j();
                            d.this.a(13, (Bundle) null);
                            return;
                        case 1004:
                            d.this.l();
                            d.this.a(14, (Bundle) null);
                            return;
                        case 1005:
                            d.this.d((AMapLocationListener) message.obj);
                            return;
                        default:
                            try {
                                switch (i) {
                                    case 1014:
                                        d.this.a(message);
                                        break;
                                    case 1015:
                                        d.this.d.a(d.this.b);
                                        d.this.a(1025, (Object) null, 300000L);
                                        break;
                                    case 1016:
                                        if (i.m(d.this.C)) {
                                            d.this.r();
                                            break;
                                        } else if (d.this.d.b()) {
                                            d.this.a(1016, (Object) null, 1000L);
                                            break;
                                        } else {
                                            d.this.n();
                                            break;
                                        }
                                    case 1017:
                                        d.this.d.a();
                                        d.this.a(1025);
                                        break;
                                    case 1018:
                                        d.this.b = (AMapLocationClientOption) message.obj;
                                        if (d.this.b != null) {
                                            d.this.s();
                                            break;
                                        }
                                        break;
                                    default:
                                        switch (i) {
                                            case 1023:
                                                d.this.c(message);
                                                break;
                                            case 1024:
                                                d.this.d(message);
                                                break;
                                            case 1025:
                                                if (d.this.d.f()) {
                                                    d.this.d.a();
                                                    d.this.d.a(d.this.b);
                                                }
                                                d.this.a(1025, (Object) null, 300000L);
                                                break;
                                            case 1026:
                                                d.this.D.a(d.this.b);
                                                break;
                                            case 1027:
                                                d.this.D.a();
                                                break;
                                            case 1028:
                                                d.this.f((AMapLocation) message.obj);
                                                break;
                                        }
                                }
                            } catch (Throwable th3) {
                                obj = message;
                                th = th3;
                                break;
                            }
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                Object obj2 = obj;
                th = th;
                message = obj2;
                if (message == 0) {
                    message = "handleMessage";
                }
                com.autonavi.aps.amapapi.utils.b.a(th, "AMapLocationManage$MHandlerr", message);
                return;
            }
            d.this.a(14, (Bundle) null);
            d.this.g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        try {
            new com.autonavi.aps.amapapi.a().f("#2001");
            g.a((String) null, 2153);
            com.autonavi.aps.amapapi.model.a aVar = new com.autonavi.aps.amapapi.model.a("");
            aVar.setErrorCode(20);
            aVar.setLocationDetail("模糊权限下不支持低功耗定位#2001");
            f(aVar);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "apsLocation:callback");
        }
    }

    private void e(AMapLocation aMapLocation) {
        AMapLocation a2;
        if (aMapLocation == null) {
            return;
        }
        try {
            if (j.b == null) {
                j jVar = this.j;
                a2 = jVar != null ? jVar.b() : null;
            } else {
                a2 = j.b.a();
            }
            g.a(a2, aMapLocation);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.d.b(this.b);
        this.D.b(this.b);
        if (this.F && !this.b.getLocationMode().equals(this.q)) {
            l();
            j();
        }
        this.q = this.b.getLocationMode();
        if (this.s != null) {
            if (this.b.isOnceLocation()) {
                this.s.a(this.C, 0);
            } else {
                this.s.a(this.C, 1);
            }
            this.s.a(this.C, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(AMapLocation aMapLocation) {
        try {
            if (this.i && this.k != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", com.autonavi.aps.amapapi.utils.b.a(this.b));
                a(0, bundle);
                if (this.F) {
                    a(13, (Bundle) null);
                }
                this.i = false;
            }
            d(aMapLocation);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "resultGpsLocationSuccess");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        com.autonavi.aps.amapapi.a aVar;
        AMapLocation aMapLocation;
        com.autonavi.aps.amapapi.a aVar2;
        h hVar;
        AMapLocation aMapLocation2 = null;
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                aMapLocation = (AMapLocation) bundle.getParcelable("loc");
                this.A = bundle.getString("nb");
                aVar2 = (com.autonavi.aps.amapapi.a) bundle.getParcelable("statics");
                if (aMapLocation != null) {
                    try {
                        if (aMapLocation.getErrorCode() == 0 && (hVar = this.d) != null) {
                            hVar.c();
                            if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                h.y = aMapLocation;
                            }
                        }
                    } catch (Throwable th) {
                        aVar = aVar2;
                        th = th;
                        com.autonavi.aps.amapapi.utils.b.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
                        aVar2 = aVar;
                        a(aMapLocation2, aVar2);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                aVar = null;
                com.autonavi.aps.amapapi.utils.b.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
                aVar2 = aVar;
                a(aMapLocation2, aVar2);
            }
        } else {
            aVar2 = null;
            aMapLocation = null;
        }
        h hVar2 = this.d;
        aMapLocation2 = hVar2 != null ? hVar2.a(aMapLocation, this.A) : aMapLocation;
        a(aMapLocation2, aVar2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Message message) {
        try {
            AMapLocation aMapLocation = (AMapLocation) message.obj;
            if (this.h && this.k != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", com.autonavi.aps.amapapi.utils.b.a(this.b));
                a(0, bundle);
                if (this.F) {
                    a(13, (Bundle) null);
                }
                this.h = false;
            }
            a(aMapLocation, (com.autonavi.aps.amapapi.a) null);
            a(1025);
            a(1025, (Object) null, 300000L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "resultGpsLocationSuccess");
        }
    }

    public final void a(int i, Notification notification) {
        if (i == 0 || notification == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("i", i);
            bundle.putParcelable("h", notification);
            a(1023, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "disableBackgroundLocation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Message message) {
        if (message == null) {
            return;
        }
        try {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            int i = data.getInt("i", 0);
            Notification notification = (Notification) data.getParcelable("h");
            Intent q = q();
            q.putExtra("i", i);
            q.putExtra("h", notification);
            q.putExtra(it.f, 1);
            a(q, true);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "doEnableBackgroundLocation");
        }
    }

    public final void a(boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean(it.j, z);
            a(1024, bundle, 0L);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "disableBackgroundLocation");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Message message) {
        if (message == null) {
            return;
        }
        try {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            boolean z = data.getBoolean(it.j, true);
            Intent q = q();
            q.putExtra(it.j, z);
            q.putExtra(it.f, 2);
            a(q, false);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "ALManager", "doDisableBackgroundLocation");
        }
    }

    private boolean t() {
        if (i.j(this.C)) {
            if (e.b(((Application) this.C.getApplicationContext()).getBaseContext(), "checkSelfPermission", "android.permission.FOREGROUND_SERVICE") != 0) {
                return false;
            }
        }
        return true;
    }

    private void a(Intent intent, boolean z) {
        Context context = this.C;
        if (context != null) {
            if (z) {
                if (!t()) {
                    Log.e("amapapi", "-------------调用后台定位服务，缺少权限：android.permission.FOREGROUND_SERVICE--------------");
                    return;
                } else {
                    try {
                        this.C.getClass().getMethod("startForegroundService", Intent.class).invoke(this.C, intent);
                    } catch (Throwable unused) {
                        this.C.startService(intent);
                    }
                }
            } else {
                context.startService(intent);
            }
            this.B = true;
        }
    }
}
