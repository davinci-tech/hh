package com.huawei.openalliance.ad;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes5.dex */
public class ey implements fw {
    private static fw c;
    private static final byte[] d = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private Context f6856a;
    private final SharedPreferences b;
    private final byte[] e = new byte[0];

    @Override // com.huawei.openalliance.ad.fw
    public boolean d(String str) {
        SharedPreferences sharedPreferences;
        synchronized (this.e) {
            if (!com.huawei.openalliance.ad.utils.cz.b(str) && (sharedPreferences = this.b) != null) {
                return sharedPreferences.contains(str);
            }
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public long d() {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return 0L;
            }
            return sharedPreferences.getLong(Constants.LABEL_GEN_TIME, 0L);
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public void c(String str) {
        synchronized (this.e) {
            if (this.b == null) {
                return;
            }
            if (com.huawei.openalliance.ad.utils.cz.b(str)) {
                return;
            }
            this.b.edit().remove(str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public int c() {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return 200;
            }
            return sharedPreferences.getInt(Constants.INS_APPS_RETURN_CODE, -1);
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public void b(String str) {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putString(Constants.ENCODING_MODE, str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public String b() {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return com.huawei.openalliance.ad.utils.cz.a((Object) 1);
            }
            return sharedPreferences.getString(Constants.ENCODING_MODE, com.huawei.openalliance.ad.utils.cz.a((Object) 1));
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public void a(String str) {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putString(Constants.INS_APPS_ENCODED, str).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public void a(long j) {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putLong(Constants.LABEL_GEN_TIME, j).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public void a(int i) {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return;
            }
            sharedPreferences.edit().putInt(Constants.INS_APPS_RETURN_CODE, i).commit();
        }
    }

    @Override // com.huawei.openalliance.ad.fw
    public String a() {
        synchronized (this.e) {
            SharedPreferences sharedPreferences = this.b;
            if (sharedPreferences == null) {
                return "";
            }
            return sharedPreferences.getString(Constants.INS_APPS_ENCODED, "");
        }
    }

    private static fw b(Context context) {
        fw fwVar;
        synchronized (d) {
            if (c == null) {
                c = new ey(context);
            }
            fwVar = c;
        }
        return fwVar;
    }

    public static fw a(Context context) {
        return b(context);
    }

    private ey(Context context) {
        this.f6856a = context.getApplicationContext();
        try {
            this.b = context.getSharedPreferences("HiAd_InsAppsSharedPreferences", 0);
        } catch (Throwable th) {
            try {
                ho.c("InsAppsSpHandler", "get SharedPreference exception: %s", th.getClass().getSimpleName());
            } finally {
                this.b = null;
            }
        }
    }
}
