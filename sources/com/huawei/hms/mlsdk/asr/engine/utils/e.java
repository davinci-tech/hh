package com.huawei.hms.mlsdk.asr.engine.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Pair;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.mlsdk.asr.o.f;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.hms.mlsdk.common.MLApplicationSetting;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes4.dex */
public class e {
    private static HandlerThread g;

    /* renamed from: a, reason: collision with root package name */
    private f f5091a;
    private boolean c;
    private List<String> d;
    private com.huawei.hms.mlsdk.asr.o.e b = null;
    private Runnable f = new a();
    private Handler e = new Handler(g.getLooper());

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SmartLogger.i("WebsocketClient", "reconnectServer....");
            if (e.this.a()) {
                return;
            }
            e eVar = e.this;
            eVar.f5091a = eVar.f();
            if (e.this.b != null) {
                e eVar2 = e.this;
                eVar2.a(eVar2.b);
            }
            e.this.d();
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread("Reconnect Message");
        g = handlerThread;
        handlerThread.start();
    }

    public e(boolean z, List<String> list) {
        this.f5091a = null;
        this.d = list;
        this.c = z;
        this.f5091a = f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public f f() {
        SecureSSLSocketFactoryNew secureSSLSocketFactoryNew;
        SecureX509TrustManager secureX509TrustManager;
        f.d dVar = new f.d(MLApplication.getInstance().getAppContext());
        Pair pair = null;
        try {
            secureSSLSocketFactoryNew = SecureSSLSocketFactoryNew.getInstance(MLApplication.getInstance().getAppContext(), new SecureRandom());
        } catch (Exception e) {
            SmartLogger.e("WebsocketClient", e.getMessage());
            secureSSLSocketFactoryNew = null;
        }
        try {
            secureX509TrustManager = new SecureX509TrustManager(MLApplication.getInstance().getAppContext());
        } catch (Exception e2) {
            SmartLogger.e("WebsocketClient", e2.getMessage());
            secureX509TrustManager = null;
        }
        f.d a2 = dVar.a(secureSSLSocketFactoryNew, secureX509TrustManager).a(false).a(this.d);
        HashMap hashMap = new HashMap();
        MLApplication mLApplication = MLApplication.getInstance();
        MLApplicationSetting appSetting = mLApplication.getAppSetting();
        if (appSetting != null) {
            UUID randomUUID = UUID.randomUUID();
            hashMap.put("Content-Type", HealthEngineRequestManager.CONTENT_TYPE);
            hashMap.put("X-Request-ID", String.valueOf(randomUUID));
            SmartLogger.i("AsrProcessor", "X-Request-ID: " + randomUUID);
            hashMap.put("X-User-Agent", "X-User-Agent");
            hashMap.put("appId", appSetting.getAppId());
            hashMap.put("HMS-APPLICATION-ID", appSetting.getAppId());
            hashMap.put("X-Package-Name", appSetting.getPackageName());
            hashMap.put("X-Country-Code", new c(mLApplication.getAppContext(), false).a());
            hashMap.put("supplierId", "supplierId");
            hashMap.put("accept", "application/json");
            hashMap.put("certFingerprint", appSetting.getCertFingerprint());
            hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + MLApplication.getInstance().getAuthorizationToken());
            hashMap.put("X-Mlkit-Version", "3.16.7.302");
            SmartLogger.i("WebsocketClient", "X-Mlkit-Version: " + appSetting.getMLSdkVersion());
            try {
                List<ResolveInfo> queryIntentServices = MLApplication.getInstance().getAppContext().getPackageManager().queryIntentServices(new Intent("com.huawei.hms.core.aidlservice"), 128);
                if (queryIntentServices.size() != 0) {
                    Iterator<ResolveInfo> it = queryIntentServices.iterator();
                    if (it.hasNext()) {
                        ResolveInfo next = it.next();
                        pair = new Pair(next.serviceInfo.applicationInfo.packageName, next);
                    }
                }
            } catch (RuntimeException e3) {
                SmartLogger.e("WebsocketClient", "getHmsCoreInfo RuntimeException e: " + e3);
            } catch (Exception e4) {
                SmartLogger.e("WebsocketClient", "getHmsCoreInfo Exception e: " + e4);
            }
            if (pair != null) {
                String str = (String) pair.first;
                SmartLogger.d("WebsocketClient", "HMScore PackageName is :" + str);
                hashMap.put("isHmsCore", a(mLApplication.getAppContext(), str) ? "1" : "0");
            } else {
                hashMap.put("isHmsCore", a(mLApplication.getAppContext(), "com.huawei.hwid") ? "1" : "0");
            }
        }
        return new f(a2.a(hashMap).b(this.c));
    }

    public void c() {
        synchronized (this) {
            Handler handler = this.e;
            if (handler != null) {
                handler.postDelayed(this.f, 5000L);
            }
        }
    }

    public void d() {
        synchronized (this) {
            if (!a()) {
                this.f5091a.d();
            }
        }
    }

    public void e() {
        synchronized (this) {
            SmartLogger.i("WebsocketClient", "onStopConnect");
            f fVar = this.f5091a;
            if (fVar != null) {
                fVar.a((com.huawei.hms.mlsdk.asr.o.e) null);
                this.f5091a.e();
                this.b = null;
            }
            Handler handler = this.e;
            if (handler != null) {
                handler.removeCallbacks(this.f);
            }
        }
    }

    public boolean b() {
        f fVar = this.f5091a;
        return fVar != null && fVar.b();
    }

    public void a(com.huawei.hms.mlsdk.asr.o.e eVar) {
        synchronized (this) {
            this.b = eVar;
            if (eVar != null) {
                this.f5091a.a(eVar);
            }
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this) {
            f fVar = this.f5091a;
            if (fVar != null) {
                z = fVar.c();
            }
        }
        return z;
    }

    public boolean a(String str) {
        boolean a2;
        synchronized (this) {
            a2 = a() ? this.f5091a.a(str) : false;
        }
        return a2;
    }

    public boolean a(byte[] bArr) {
        boolean a2;
        synchronized (this) {
            a2 = a() ? this.f5091a.a(bArr) : false;
        }
        return a2;
    }

    private boolean a(Context context, String str) {
        try {
            return (context.getPackageManager().getPackageInfo(str, 0).applicationInfo.flags & 1) != 0;
        } catch (RuntimeException e) {
            SmartLogger.e("WebsocketClient", "isPackageInternal Exception e: " + e);
            return false;
        } catch (Exception e2) {
            SmartLogger.e("WebsocketClient", "isPackageInternal Exception e: " + e2);
            return false;
        }
    }
}
