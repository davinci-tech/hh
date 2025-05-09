package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;

/* loaded from: classes5.dex */
public class fm implements gf {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6876a = new byte[0];
    private static gf c;
    private final byte[] b = new byte[0];
    private Context d;

    @Override // com.huawei.openalliance.ad.gf
    public void a(String str, long j) {
        synchronized (this.b) {
            SharedPreferences a2 = a();
            SharedPreferences.Editor edit = a2.edit();
            long j2 = a2.getLong("last_time_" + str, 0L);
            edit.putLong("last_time_" + str, j);
            if (com.huawei.openalliance.ad.utils.di.a(j2)) {
                edit.putInt("show_times_" + str, a2.getInt("show_times_" + str, 0) + 1);
            } else {
                edit.putInt("show_times_" + str, 1);
            }
            edit.commit();
            b();
        }
    }

    @Override // com.huawei.openalliance.ad.gf
    public int a(String str) {
        synchronized (this.b) {
            SharedPreferences a2 = a();
            if (!com.huawei.openalliance.ad.utils.di.a(a2.getLong("last_time_" + str, 0L))) {
                return 0;
            }
            return a2.getInt("show_times_" + str, 0);
        }
    }

    private void b() {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.fm.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences a2 = fm.this.a();
                SharedPreferences.Editor edit = a2.edit();
                if (System.currentTimeMillis() - a2.getLong("last_clean_time", 0L) <= 43200000) {
                    ho.b("TptSpHandler", "less than half day");
                    return;
                }
                ho.b("TptSpHandler", "begin clean");
                for (Map.Entry<String, ?> entry : a2.getAll().entrySet()) {
                    if (entry != null && entry.getKey() != null) {
                        String key = entry.getKey();
                        if (key.startsWith("last_time_") && (entry.getValue() instanceof Long) && (((Long) entry.getValue()) == null || !com.huawei.openalliance.ad.utils.di.a(((Long) entry.getValue()).longValue()))) {
                            edit.remove(key);
                            String a3 = fm.this.a(key, "last_time_");
                            ho.a("TptSpHandler", "slotId = %s", a3);
                            if (!TextUtils.isEmpty(a3)) {
                                edit.remove("show_times_" + a3);
                            }
                        }
                    }
                }
                edit.putLong("last_clean_time", System.currentTimeMillis());
                edit.commit();
            }
        });
    }

    private static gf b(Context context) {
        gf gfVar;
        synchronized (f6876a) {
            if (c == null) {
                c = new fm(context);
            }
            gfVar = c;
        }
        return gfVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return null;
            }
            int indexOf = str.indexOf(str2);
            int length = str2.length();
            if (indexOf < 0 || indexOf >= str.length() - length) {
                return null;
            }
            return str.substring(indexOf + length);
        } catch (Throwable th) {
            ho.c("TptSpHandler", "get slotId by prefix err: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public static gf a(Context context) {
        return b(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SharedPreferences a() {
        return this.d.getSharedPreferences("HiAd_def_tpt_sp", 0);
    }

    private fm(Context context) {
        this.d = com.huawei.openalliance.ad.utils.x.i(context.getApplicationContext());
    }
}
