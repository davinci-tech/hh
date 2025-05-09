package com.huawei.openalliance.ad;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.wear.oversea.util.SystemPropertyValues;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class cd extends cc {
    protected abstract String s();

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public String i() {
        String h = this.b.h();
        if (TextUtils.isEmpty(h)) {
            h = u();
        } else if (com.huawei.openalliance.ad.utils.dh.a("getMagicuiVersionName")) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.cd.2
                @Override // java.lang.Runnable
                public void run() {
                    cd.this.u();
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, h)) {
            return null;
        }
        return h;
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean g() {
        return !TextUtils.isEmpty(f());
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public String f() {
        String g = this.b.g();
        if (TextUtils.isEmpty(g)) {
            g = v();
        } else if (com.huawei.openalliance.ad.utils.dh.a("getHosVersionName")) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.cd.3
                @Override // java.lang.Runnable
                public void run() {
                    cd.this.v();
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, g)) {
            return null;
        }
        return g;
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public String e() {
        String f = this.b.f();
        if (TextUtils.isEmpty(f)) {
            f = t();
        } else if (com.huawei.openalliance.ad.utils.dh.a("getEmuiVersionName")) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.cd.1
                @Override // java.lang.Runnable
                public void run() {
                    cd.this.t();
                }
            });
        }
        if (TextUtils.equals(Constants.NOT_FOUND, f)) {
            return null;
        }
        return f;
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean d() {
        String a2 = com.huawei.openalliance.ad.utils.dd.a("ro.product.locale.region");
        if (!TextUtils.isEmpty(a2)) {
            return "cn".equalsIgnoreCase(a2);
        }
        String a3 = com.huawei.openalliance.ad.utils.dd.a(SystemPropertyValues.PROP_LOCALE_KEY);
        if (!TextUtils.isEmpty(a3)) {
            return a3.toLowerCase(Locale.ENGLISH).contains("cn");
        }
        String d = com.huawei.openalliance.ad.utils.dd.d();
        if (TextUtils.isEmpty(d)) {
            return false;
        }
        return "cn".equalsIgnoreCase(d);
    }

    @Override // com.huawei.openalliance.ad.cc, com.huawei.openalliance.ad.co
    public boolean a(Context context) {
        try {
            int i = Settings.Secure.getInt(context.getContentResolver(), "display_notch_status");
            ho.a("BaseHwnDeviceImpl", "isNotchEnable, displayNotch: %s", Integer.valueOf(i));
            return i == 0;
        } catch (Throwable th) {
            ho.b("BaseHwnDeviceImpl", "isNotchEnable err:" + th.getClass().getSimpleName());
            return a((View) null) > 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String v() {
        String b = b(s());
        this.b.e(b);
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String u() {
        String b = b("ro.build.version.magic");
        this.b.f(b);
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String t() {
        String b = b("ro.build.version.emui");
        this.b.d(b);
        return b;
    }

    private String b(String str) {
        String a2 = com.huawei.openalliance.ad.utils.dd.a(str);
        return a2 == null ? Constants.NOT_FOUND : a2;
    }

    protected cd(Context context) {
        super(context);
    }
}
