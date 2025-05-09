package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class aa {

    /* renamed from: a, reason: collision with root package name */
    public static final String f10882a = "aa";
    private static volatile aa b;
    private boolean c = false;
    private String d = "";
    private String e = "";

    private aa() {
    }

    public static aa a() {
        aa aaVar;
        synchronized (aa.class) {
            if (b == null) {
                synchronized (aa.class) {
                    if (b == null) {
                        b = new aa();
                    }
                }
            }
            aaVar = b;
        }
        return aaVar;
    }

    public boolean b() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String d() {
        return this.e;
    }

    public void b(String str) {
        synchronized (this) {
            this.e = str;
        }
    }

    public void a(String str, String str2) {
        a(true);
        a(str);
        b(str2);
    }

    public void b(String str, String str2) {
        synchronized (this) {
            HwLog.i(f10882a, "dealLastTask");
            aa a2 = a();
            if (a2.b() && p.a(Environment.getApplicationContext()).a(a2.c())) {
                if (!TextUtils.equals(str, a2.c())) {
                    HwWatchFaceManager.getInstance(Environment.getApplicationContext()).transmitMembershipDialogStatus();
                }
                HwWatchFaceManager.getInstance(Environment.getApplicationContext()).cancelInstallWatchFace(a2.c(), a2.d());
            }
            a2.a(str, str2);
        }
    }
}
