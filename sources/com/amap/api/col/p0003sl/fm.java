package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.fz;
import com.amap.api.col.p0003sl.ho;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class fm {

    /* renamed from: a, reason: collision with root package name */
    public static hz f1035a;
    private static fm b;
    private static Context c;
    private a d;
    private HandlerThread e = new HandlerThread("manifestThread") { // from class: com.amap.api.col.3sl.fm.1
        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            Thread.currentThread().setName("ManifestConfigThread");
            hz a2 = fc.a(false);
            fm.c(fm.c);
            ho.a(fm.c, a2, "11K;001;184;185", new ho.a() { // from class: com.amap.api.col.3sl.fm.1.1
                @Override // com.amap.api.col.3sl.ho.a
                public final void a(ho.b bVar) {
                    a aVar;
                    JSONObject optJSONObject;
                    JSONObject optJSONObject2;
                    Message message = new Message();
                    if (bVar != null) {
                        try {
                            if (bVar.g != null) {
                                message.obj = new fn(bVar.g.b, bVar.g.f1136a);
                            }
                        } catch (Throwable th) {
                            try {
                                fd.a(th, "ManifestConfig", "run");
                                if (aVar != null) {
                                    return;
                                } else {
                                    return;
                                }
                            } finally {
                                message.what = 3;
                                if (fm.this.d != null) {
                                    fm.this.d.sendMessage(message);
                                }
                            }
                        }
                    }
                    if (bVar != null && bVar.f != null && (optJSONObject2 = bVar.f.optJSONObject("184")) != null) {
                        fm.d(optJSONObject2);
                        gh.a(fm.c, "amap_search", "cache_control", optJSONObject2.toString());
                    }
                    if (bVar != null && bVar.f != null && (optJSONObject = bVar.f.optJSONObject("185")) != null) {
                        fm.c(optJSONObject);
                        gh.a(fm.c, "amap_search", "parm_control", optJSONObject.toString());
                    }
                    message.what = 3;
                    if (fm.this.d != null) {
                        fm.this.d.sendMessage(message);
                    }
                }
            });
            try {
                Thread.sleep(PreConnectManager.CONNECT_INTERNAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        try {
            String str = (String) gh.b(context, "amap_search", "cache_control", "");
            if (!TextUtils.isEmpty(str)) {
                d(new JSONObject(str));
            }
            String str2 = (String) gh.b(context, "amap_search", "parm_control", "");
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            c(new JSONObject(str2));
        } catch (Throwable th) {
            fd.a(th, "ManifestConfig", "ManifestConfig-readAuthFromCache");
        }
    }

    private fm(Context context) {
        c = context;
        f1035a = fc.a(false);
        try {
            b();
            this.d = new a(Looper.getMainLooper());
            this.e.start();
        } catch (Throwable th) {
            fd.a(th, "ManifestConfig", "ManifestConfig");
        }
    }

    private static void b() {
        fy.a();
    }

    public static fm a(Context context) {
        if (b == null) {
            b = new fm(context);
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                boolean a2 = ho.a(jSONObject.optString("passAreaAble"), true);
                boolean a3 = ho.a(jSONObject.optString("truckAble"), true);
                boolean a4 = ho.a(jSONObject.optString("poiPageAble"), true);
                boolean a5 = ho.a(jSONObject.optString("rideAble"), true);
                boolean a6 = ho.a(jSONObject.optString("walkAble"), true);
                boolean a7 = ho.a(jSONObject.optString("passPointAble"), true);
                boolean a8 = ho.a(jSONObject.optString("keyWordLenAble"), true);
                int optInt = jSONObject.optInt("poiPageMaxSize", 25);
                int optInt2 = jSONObject.optInt("passAreaMaxCount", 100);
                int optInt3 = jSONObject.optInt("walkMaxLength", 100);
                int optInt4 = jSONObject.optInt("passPointMaxCount", 6);
                int optInt5 = jSONObject.optInt("poiPageMaxNum", 100);
                int optInt6 = jSONObject.optInt("truckMaxLength", 5000);
                int optInt7 = jSONObject.optInt("rideMaxLength", 1200);
                int optInt8 = jSONObject.optInt("passAreaMaxArea", 100000000);
                int optInt9 = jSONObject.optInt("passAreaPointCount", 16);
                int optInt10 = jSONObject.optInt("keyWordLenMaxNum", 100);
                gc.a().a(a2);
                gc.a().c(optInt2);
                gc.a().i(optInt8);
                gc.a().j(optInt9);
                gc.a().b(a3);
                gc.a().g(optInt6);
                gc.a().c(a4);
                gc.a().f(optInt5);
                gc.a().a(optInt);
                gc.a().b(optInt10);
                gc.a().g(a8);
                gc.a().d(a5);
                gc.a().h(optInt7);
                gc.a().e(a6);
                gc.a().d(optInt3);
                gc.a().f(a7);
                gc.a().e(optInt4);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has("able")) {
                    fz.a a2 = a(jSONObject, true, (fz.a) null);
                    fz.a().a(a2);
                    if (a2.a()) {
                        a("regeo", jSONObject, a2);
                        a("geo", jSONObject, a2);
                        a("placeText", jSONObject, a2);
                        a("placeAround", jSONObject, a2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void a(String str, JSONObject jSONObject, fz.a aVar) {
        if (jSONObject != null && jSONObject.has(str)) {
            fz.a().a(str, a(jSONObject.optJSONObject(str), false, aVar));
        }
    }

    private static fz.a a(JSONObject jSONObject, boolean z, fz.a aVar) {
        boolean optBoolean;
        fz.a aVar2 = null;
        if (jSONObject == null) {
            return null;
        }
        try {
            fz.a aVar3 = new fz.a();
            boolean z2 = true;
            try {
                if (z) {
                    String optString = jSONObject.optString("able");
                    if (aVar != null && !aVar.a()) {
                        z2 = false;
                    }
                    optBoolean = ho.a(optString, z2);
                } else {
                    if (aVar != null && !aVar.a()) {
                        z2 = false;
                    }
                    optBoolean = jSONObject.optBoolean("able", z2);
                }
                int optInt = jSONObject.optInt("timeoffset", aVar != null ? (int) aVar.b() : 86400);
                int optInt2 = jSONObject.optInt("num", aVar != null ? aVar.c() : 10);
                double optDouble = jSONObject.optDouble("limitDistance", aVar != null ? aVar.d() : 0.0d);
                aVar3.a(optBoolean);
                aVar3.a(optInt);
                aVar3.a(optInt2);
                aVar3.a(optDouble);
                return aVar3;
            } catch (Throwable th) {
                th = th;
                aVar2 = aVar3;
                th.printStackTrace();
                return aVar2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    final class a extends Handler {

        /* renamed from: a, reason: collision with root package name */
        String f1038a;

        public a(Looper looper) {
            super(looper);
            this.f1038a = "handleMessage";
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message != null && message.what == 3) {
                try {
                    fn fnVar = (fn) message.obj;
                    if (fnVar == null) {
                        fnVar = new fn(false, false);
                    }
                    iv.a(fm.c, fc.a(fnVar.a()));
                    fm.f1035a = fc.a(fnVar.a());
                } catch (Throwable th) {
                    fd.a(th, "ManifestConfig", this.f1038a);
                }
            }
        }
    }
}
