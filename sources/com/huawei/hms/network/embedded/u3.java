package com.huawei.hms.network.embedded;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;

/* loaded from: classes9.dex */
public class u3 {
    public static final String d = "SceneHelper";
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final String i = "metro";
    public static final String j = "railway";
    public static final String k = "airport";
    public static final String l = "RegisterScene";
    public static final String m = "pid";
    public static final String n = "uid";
    public static final String o = "packageName";
    public static final String p = "hms_cp_bundle_key";
    public static final String q = "content://com.huawei.hms.contentprovider/com.huawei.hms.wireless/qoe";
    public static final Uri r = Uri.parse("content://com.huawei.hms.contentprovider/com.huawei.hms.wireless/observe_metro");
    public static final Uri s = Uri.parse(r5.i);
    public static final String t = "RegisterWeakSignal";
    public static final String u = "QueryMetroInfo";
    public static volatile u3 v;

    /* renamed from: a, reason: collision with root package name */
    public Context f5514a;
    public a b = null;
    public String c;

    public void registerSceneChangeListener(Context context) {
        this.f5514a = context;
        ExecutorsUtils.newThread(new b(), l).start();
    }

    public String getScene() {
        return this.c;
    }

    public static u3 getInstance() {
        if (v == null) {
            synchronized (u3.class) {
                if (v == null) {
                    v = new u3();
                }
            }
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString(p, q);
            bundle.putInt(m, Process.myPid());
            bundle.putInt("uid", Process.myUid());
            bundle.putString("packageName", this.f5514a.getPackageName());
            this.c = a(context.getContentResolver().call(s, u, this.f5514a.getPackageName(), bundle));
        } catch (Exception unused) {
            Logger.e(d, "providerCallToGetScene meet exception");
            this.c = null;
        }
        Logger.i(d, "scene: " + this.c);
    }

    private String a(Bundle bundle) {
        if (bundle == null) {
            Logger.i(d, "update scene bundle is null");
            return "";
        }
        Object obj = bundle.get("Scene");
        if (obj == null) {
            return "";
        }
        int parseInt = Integer.parseInt(obj.toString());
        Logger.v(d, "getSceneFromBundle scene:" + parseInt);
        return a(parseInt);
    }

    private String a(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? "" : k : j : i;
    }

    public class a extends ContentObserver {

        /* renamed from: a, reason: collision with root package name */
        public Context f5515a;

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Logger.v(u3.d, "scenne change");
            u3.this.a(this.f5515a);
        }

        public a(Handler handler, Context context) {
            super(handler);
            this.f5515a = context;
        }
    }

    public class b implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            Bundle bundle = new Bundle();
            bundle.putString(u3.p, u3.q);
            try {
                Bundle call = u3.this.f5514a.getContentResolver().call(u3.s, u3.t, u3.this.f5514a.getPackageName(), bundle);
                if (call == null) {
                    Logger.i(u3.d, "register scene bundle is null");
                } else {
                    Logger.v(u3.d, "register scene bundle not null");
                    for (String str : call.keySet()) {
                        Logger.v(u3.d, "key:" + str + " value:" + call.getInt(str));
                    }
                }
                u3 u3Var = u3.this;
                u3Var.b = u3Var.new a(null, u3Var.f5514a);
                u3.this.f5514a.getContentResolver().registerContentObserver(u3.r, true, u3.this.b);
                Logger.i(u3.d, "register scene callback success");
                u3 u3Var2 = u3.this;
                u3Var2.a(u3Var2.f5514a);
            } catch (Exception e) {
                Logger.i(u3.d, "register scene callback fail");
                Logger.v(u3.d, "register scene callback fail:" + e.getMessage());
            }
        }

        public b() {
        }
    }
}
