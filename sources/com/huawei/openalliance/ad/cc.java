package com.huawei.openalliance.ad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.huawei.hms.android.SystemUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;

/* loaded from: classes5.dex */
public class cc implements co {

    /* renamed from: a, reason: collision with root package name */
    protected Context f6674a;
    protected com.huawei.openalliance.ad.utils.cg b;
    private boolean c;
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.cc.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.cc.1.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent2 = intent;
                        if (intent2 != null && intent2.getExtras() != null) {
                            if (intent.getExtras().getBoolean("connected")) {
                                cc.this.c = true;
                                dc.o();
                            } else {
                                cc.this.c = false;
                            }
                        }
                    } catch (Throwable th) {
                        ho.c("BaseDeviceImpl", "onReceive error:" + th.getClass().getSimpleName());
                    }
                }
            });
        }
    };

    @Override // com.huawei.openalliance.ad.co
    public int a(View view) {
        return 0;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean a() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean a(Context context) {
        return false;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean d() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.co
    public String e() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.co
    public String f() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean g() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.co
    public Integer h() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.co
    public String i() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean l() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean m() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.co
    public String p() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.co
    public String q() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean r() {
        return false;
    }

    @Override // com.huawei.openalliance.ad.co
    public String o() {
        return com.huawei.openalliance.ad.utils.cz.l(m() ? fh.b(this.f6674a).bI() : ca.a(this.f6674a).a());
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean n() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.co
    public String k() {
        String a2 = com.huawei.openalliance.ad.utils.dd.a(SystemUtils.PRODUCT_BRAND);
        if (TextUtils.isEmpty(a2)) {
            a2 = Build.BOARD;
        }
        return a2.toUpperCase(Locale.ENGLISH);
    }

    @Override // com.huawei.openalliance.ad.co
    public String j() {
        String a2 = com.huawei.openalliance.ad.utils.dd.a("ro.product.manufacturer");
        if (TextUtils.isEmpty(a2)) {
            a2 = Build.MANUFACTURER;
        }
        return a2.toUpperCase(Locale.ENGLISH);
    }

    @Override // com.huawei.openalliance.ad.co
    public String c() {
        String a2 = com.huawei.openalliance.ad.utils.dd.a("ro.build.huawei.display.id");
        return TextUtils.isEmpty(a2) ? Build.DISPLAY : a2;
    }

    @Override // com.huawei.openalliance.ad.co
    public String b() {
        String a2 = com.huawei.openalliance.ad.utils.dd.a("ro.product.model");
        return TextUtils.isEmpty(a2) ? Build.MODEL : a2;
    }

    @Override // com.huawei.openalliance.ad.co
    public boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            ho.c("BaseDeviceImpl", "check widget available error");
            return false;
        }
    }

    private void s() {
        ho.a("BaseDeviceImpl", "registerUSBObserver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_USB_STATE);
        com.huawei.openalliance.ad.utils.ao.a(this.f6674a, this.d, intentFilter);
    }

    protected cc(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f6674a = applicationContext;
        this.b = com.huawei.openalliance.ad.utils.cg.a(applicationContext);
        s();
    }
}
