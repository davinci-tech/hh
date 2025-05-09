package com.huawei.hms.hatool;

import android.util.Log;

/* loaded from: classes4.dex */
public class m0 {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4602a = false;
    private int b = 4;

    public boolean b(int i) {
        return this.f4602a && i >= this.b;
    }

    public void b(int i, String str, String str2) {
        a(i, "FormalHASDK", str + "=> " + str2);
    }

    public void a(int i, String str, String str2) {
        if (i == 3) {
            Log.d(str, str2);
            return;
        }
        if (i == 5) {
            Log.w(str, str2);
        } else if (i != 6) {
            Log.i(str, str2);
        } else {
            Log.e(str, str2);
        }
    }

    public void a(int i) {
        Log.i("FormalHASDK", System.lineSeparator() + "======================================= " + System.lineSeparator() + a() + "" + System.lineSeparator() + "=======================================");
        this.b = i;
        this.f4602a = true;
    }

    private static String a() {
        return "FormalHASDK_2.2.0.315" + p.a();
    }
}
