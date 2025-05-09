package com.huawei.hianalytics;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.hianalytics.core.log.LogAdapter;

/* loaded from: classes4.dex */
public class z0 implements LogAdapter {

    /* renamed from: a, reason: collision with other field name */
    public boolean f119a = false;

    /* renamed from: a, reason: collision with root package name */
    public int f3969a = 4;

    /* renamed from: a, reason: collision with other field name */
    public String f118a = "FormalHASDK";

    @Override // com.huawei.hianalytics.core.log.LogAdapter
    public void init(int i, String str) {
        if (this.f119a) {
            return;
        }
        this.f3969a = i;
        this.f119a = true;
        this.f118a = str;
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("======================================= ");
        sb.append(System.lineSeparator());
        sb.append(this.f118a + "_3.2.13.501");
        sb.append(System.lineSeparator());
        sb.append("=======================================");
        Log.i(str, sb.toString());
    }

    @Override // com.huawei.hianalytics.core.log.LogAdapter
    public void println(int i, String str, String str2) {
        a(i, TextUtils.isEmpty(this.f118a) ? "FormalHASDK" : this.f118a, str + "==> " + str2);
    }

    @Override // com.huawei.hianalytics.core.log.LogAdapter
    public boolean isLoggable(int i) {
        return this.f119a && i >= this.f3969a;
    }

    public final void a(int i, String str, String str2) {
        int length = str2.length();
        int i2 = 3000;
        int i3 = 0;
        for (int i4 = 0; i4 < (length / 3000) + 1; i4++) {
            if (length > i2) {
                String substring = str2.substring(i3, i2);
                if (i == 3) {
                    Log.d(str, substring);
                } else if (i == 5) {
                    Log.w(str, substring);
                } else if (i != 6) {
                    Log.i(str, substring);
                } else {
                    Log.e(str, substring);
                }
                int i5 = i2;
                i2 += 3000;
                i3 = i5;
            } else if (i == 3) {
                Log.d(str, str2.substring(i3, length));
            } else if (i == 5) {
                Log.w(str, str2.substring(i3, length));
            } else if (i != 6) {
                Log.i(str, str2.substring(i3, length));
            } else {
                Log.e(str, str2.substring(i3, length));
            }
        }
    }
}
