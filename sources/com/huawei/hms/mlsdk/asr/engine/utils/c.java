package com.huawei.hms.mlsdk.asr.engine.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import java.util.Locale;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private String f5090a;

    public c(Context context, boolean z) {
        this.f5090a = "UNKNOWN";
        if (context == null) {
            throw new NullPointerException("context must be not null.Please provide app's Context");
        }
        try {
            b();
            if (c()) {
                SmartLogger.d("com.huawei.hms.mlsdk.asr.engine.utils.c", "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.VENDOR_COUNTRY);
            } else {
                a(context, z);
                if (c()) {
                    SmartLogger.d("com.huawei.hms.mlsdk.asr.engine.utils.c", "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.SIM_COUNTRY);
                } else {
                    this.f5090a = a("get", "ro.product.locale.region", "android.os.SystemProperties", "UNKNOWN");
                    SmartLogger.e("com.huawei.hms.mlsdk.asr.engine.utils.c", "getLocaleCountryCode=" + this.f5090a);
                    if (!"cn".equalsIgnoreCase(this.f5090a)) {
                        SmartLogger.e("com.huawei.hms.mlsdk.asr.engine.utils.c", "getLocaleCountryCode from system language is not reliable.");
                        this.f5090a = "UNKNOWN";
                    }
                    if (c()) {
                        SmartLogger.d("com.huawei.hms.mlsdk.asr.engine.utils.c", "getCountryCode get country code from {%s}", GrsBaseInfo.CountryCodeSource.LOCALE_INFO);
                    }
                }
            }
        } catch (Exception unused) {
            SmartLogger.w("com.huawei.hms.mlsdk.asr.engine.utils.c", "get CountryCode error");
        }
        this.f5090a = this.f5090a.toUpperCase(Locale.ENGLISH);
    }

    private void b() {
        if (Build.BRAND.equals("HONOR")) {
            this.f5090a = a("get", CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP_HN, "android.os.SystemProperties", "UNKNOWN");
        } else {
            this.f5090a = a("get", CountryCodeBean.VENDORCOUNTRY_SYSTEMPROP, "android.os.SystemProperties", "UNKNOWN");
        }
        StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("getVendorCountry=");
        a2.append(this.f5090a);
        SmartLogger.e("com.huawei.hms.mlsdk.asr.engine.utils.c", a2.toString());
        if ("eu".equalsIgnoreCase(this.f5090a) || "la".equalsIgnoreCase(this.f5090a)) {
            SmartLogger.e("com.huawei.hms.mlsdk.asr.engine.utils.c", "getVendorCountry equals eu or la ,not reliable");
            this.f5090a = "UNKNOWN";
            return;
        }
        String str = this.f5090a;
        if (str == null || str.length() != 2) {
            this.f5090a = "UNKNOWN";
        }
    }

    private boolean c() {
        return !"UNKNOWN".equals(this.f5090a);
    }

    public String a() {
        return this.f5090a;
    }

    private void a(Context context, boolean z) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        if (telephonyManager != null) {
            if (!z || telephonyManager.getPhoneType() == 2) {
                this.f5090a = telephonyManager.getSimCountryIso();
                StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("getSimCountryCode by not enableNetwork, countryCode=");
                a2.append(this.f5090a);
                SmartLogger.e("com.huawei.hms.mlsdk.asr.engine.utils.c", a2.toString());
            } else {
                this.f5090a = telephonyManager.getNetworkCountryIso();
                StringBuilder a3 = com.huawei.hms.mlsdk.asr.o.a.a("getSimCountryCode by enableNetwork, countryCode=");
                a3.append(this.f5090a);
                SmartLogger.e("com.huawei.hms.mlsdk.asr.engine.utils.c", a3.toString());
            }
        }
        String str = this.f5090a;
        if (str == null || str.length() != 2) {
            this.f5090a = "UNKNOWN";
        }
    }

    private static String a(String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            try {
                Class<?> cls = Class.forName(str3);
                return (String) cls.getMethod(str, String.class, String.class).invoke(cls, str2, str4);
            } catch (Exception e) {
                SmartLogger.e("c", "getProperty catch exception: ", e);
                return str4;
            }
        }
        SmartLogger.w("c", "reflect class for method has exception.");
        return str4;
    }
}
