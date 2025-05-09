package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class fe implements ga {

    /* renamed from: a, reason: collision with root package name */
    private static fe f6870a;
    private static final byte[] b = new byte[0];
    private final byte[] c = new byte[0];
    private Context d;

    @Override // com.huawei.openalliance.ad.ga
    public void b(String str) {
        if (com.huawei.openalliance.ad.utils.cz.b(str)) {
            ho.a("ReportSpHandler", "saveConfigMapSmartImpDelay(), configMap is blank");
            return;
        }
        try {
            String str2 = (String) new JSONObject(str).get("smartImpDelay");
            if (com.huawei.openalliance.ad.utils.cz.b(str2)) {
                return;
            }
            ho.a("ReportSpHandler", "saveConfigMapSmartImpDelay(), hasSmartImpDelay:" + str2);
            String[] split = str2.trim().split(",");
            if (split.length > 1) {
                a(Integer.valueOf(split[0]).intValue());
                b(Integer.valueOf(split[1]).intValue());
            }
        } catch (Throwable th) {
            ho.c("ReportSpHandler", "saveConfigMapSmartImpDelay(), exception:" + th.getClass().getSimpleName());
        }
    }

    public void b(int i) {
        synchronized (this.c) {
            c().edit().putInt("maxSmartImpDelay", i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.ga
    public int b() {
        int i;
        synchronized (this.c) {
            i = c().getInt("maxSmartImpDelay", 0);
        }
        return i;
    }

    @Override // com.huawei.openalliance.ad.ga
    public void a(String str, long j) {
        synchronized (this.c) {
            c().edit().putLong("last_rpt_time_" + str, j).commit();
        }
    }

    public void a(int i) {
        synchronized (this.c) {
            c().edit().putInt("minSmartImpDelay", i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.ga
    public long a(String str) {
        long j;
        synchronized (this.c) {
            j = c().getLong("last_rpt_time_" + str, 0L);
        }
        return j;
    }

    @Override // com.huawei.openalliance.ad.ga
    public int a() {
        int i;
        synchronized (this.c) {
            i = c().getInt("minSmartImpDelay", 0);
        }
        return i;
    }

    private SharedPreferences c() {
        return this.d.getSharedPreferences("hiad_report", 4);
    }

    private static fe b(Context context) {
        fe feVar;
        synchronized (b) {
            if (f6870a == null) {
                f6870a = new fe(context);
            }
            feVar = f6870a;
        }
        return feVar;
    }

    public static fe a(Context context) {
        return b(context);
    }

    private fe(Context context) {
        this.d = com.huawei.openalliance.ad.utils.x.i(context.getApplicationContext());
    }
}
