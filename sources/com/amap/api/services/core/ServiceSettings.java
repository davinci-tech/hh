package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.col.p0003sl.fc;
import com.amap.api.col.p0003sl.fd;
import com.amap.api.col.p0003sl.gj;
import com.amap.api.col.p0003sl.hp;
import com.amap.api.col.p0003sl.ht;
import com.amap.api.col.p0003sl.hw;

/* loaded from: classes2.dex */
public class ServiceSettings {
    public static final String CHINESE = "zh-CN";
    public static final String ENGLISH = "en";
    public static final int HTTP = 1;
    public static final int HTTPS = 2;
    private static ServiceSettings c;

    /* renamed from: a, reason: collision with root package name */
    private String f1485a = "zh-CN";
    private int b = 1;
    private int d = 20000;
    private int e = 20000;

    public int getConnectionTimeOut() {
        return this.d;
    }

    public int getSoTimeOut() {
        return this.e;
    }

    public void setConnectionTimeOut(int i) {
        if (i < 5000) {
            this.d = 5000;
        } else if (i > 30000) {
            this.d = 30000;
        } else {
            this.d = i;
        }
    }

    public void setSoTimeOut(int i) {
        if (i < 5000) {
            this.e = 5000;
        } else if (i > 30000) {
            this.e = 30000;
        } else {
            this.e = i;
        }
    }

    private ServiceSettings() {
    }

    public static ServiceSettings getInstance() {
        if (c == null) {
            c = new ServiceSettings();
        }
        return c;
    }

    public void setLanguage(String str) {
        this.f1485a = str;
    }

    public void setProtocol(int i) {
        this.b = i;
        ht.a().a(this.b == 2);
    }

    public String getLanguage() {
        return this.f1485a;
    }

    public int getProtocol() {
        return this.b;
    }

    public void setApiKey(String str) {
        hp.a(str);
    }

    public void destroyInnerAsynThreadPool() {
        try {
            gj.b();
        } catch (Throwable th) {
            fd.a(th, "ServiceSettings", "destroyInnerAsynThreadPool");
        }
    }

    public static void updatePrivacyShow(Context context, boolean z, boolean z2) {
        synchronized (ServiceSettings.class) {
            hw.a(context, z, z2, fc.a(false));
        }
    }

    public static void updatePrivacyAgree(Context context, boolean z) {
        synchronized (ServiceSettings.class) {
            hw.a(context, z, fc.a(false));
        }
    }
}
