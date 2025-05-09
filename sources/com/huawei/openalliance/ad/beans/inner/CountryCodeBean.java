package com.huawei.openalliance.ad.beans.inner;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.CountryConfig;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.aj;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.x;
import java.util.Locale;

/* loaded from: classes5.dex */
public class CountryCodeBean {
    public static final String COUNTRYCODE_CN = "CN";
    private static final int COUNTRYCODE_SIZE = 2;
    private static final String LOCALE_COUNTRYSYSTEMPROP = "ro.product.locale";
    private static final String LOCALE_INFO = "LOCALE_INFO";
    public static final String LOCALE_REGION_COUNTRYSYSTEMPROP = "ro.product.locale.region";
    public static final String OVERSEAS = "OVERSEAS";
    private static final String SIM_COUNTRY = "SIM_COUNTRY";
    private static final String SPECIAL_COUNTRYCODE_CN = "cn";
    private static final String SPECIAL_COUNTRYCODE_EU = "eu";
    private static final String SPECIAL_COUNTRYCODE_LA = "la";
    private static final String TAG = "CountryCodeBean";
    public static final String UNKNOWN = "UNKNOWN";
    public static final String VENDORCOUNTRY_SYSTEMPROP = "ro.hw.country";
    public static final String VENDORCOUNTRY_SYSTEMPROP_HN = "msc.sys.country";
    private static final String VENDOR_COUNTRY = "VENDOR_COUNTRY";
    public static final String VENDOR_SYSTEMPROP = "ro.hw.vendor";
    public static final String VENDOR_SYSTEMPROP_HN = "msc.sys.vendor";
    private static boolean isGrsAvailable = aj.c();
    protected String countryCode;

    protected void b(Context context, boolean z) {
        if (context == null) {
            throw new NullPointerException("context must be not null.Please provide app's Context");
        }
        try {
            if (bz.c(context) && bz.a(context).d()) {
                this.countryCode = "CN";
                ho.b(TAG, "getCountryCode get country code from chinaROM");
                return;
            }
            if (bz.b(context)) {
                a(context);
                if (b()) {
                    ho.b(TAG, "get issue_country code from VENDOR_COUNTRY");
                    return;
                }
            }
            b(context);
            if (b()) {
                ho.b(TAG, "get issue_country code from SIM_COUNTRY");
                return;
            }
            if (x.n(context)) {
                ho.b(TAG, "pad skip locale get issue_country code from grs ip");
                return;
            }
            c(context);
            if (b()) {
                ho.b(TAG, "get issue_country code from LOCALE_INFO");
            } else {
                ho.c(TAG, "fail to get grs countryCode");
            }
        } catch (Exception unused) {
            ho.c(TAG, "get CountryCode error");
        }
    }

    protected void a(Context context, boolean z) {
        if (isGrsAvailable && bz.b(context) && !x.n(context)) {
            try {
                this.countryCode = new GrsCountryCodeBean().a(context);
            } catch (Throwable th) {
                ho.c(TAG, "getIssueCountryCode via grs sdk: %s", th.getClass().getSimpleName());
            }
            this.countryCode = this.countryCode.toUpperCase(Locale.ENGLISH);
        }
        b(context, z);
        this.countryCode = this.countryCode.toUpperCase(Locale.ENGLISH);
    }

    public String a() {
        return this.countryCode;
    }

    private void d(Context context) {
        int lastIndexOf;
        try {
            this.countryCode = dd.a("ro.product.locale.region");
            ho.b(TAG, "countryCode by ro.product.locale.region is:" + this.countryCode);
            if (TextUtils.isEmpty(this.countryCode) || "UNKNOWN".equals(this.countryCode)) {
                String a2 = dd.a("ro.product.locale");
                if (!TextUtils.isEmpty(a2) && (lastIndexOf = a2.lastIndexOf(Constants.LINK)) != -1) {
                    this.countryCode = a2.substring(lastIndexOf + 1);
                    ho.b(TAG, "countryCode by ro.product.locale is:" + this.countryCode);
                }
            }
            if (SPECIAL_COUNTRYCODE_CN.equalsIgnoreCase(this.countryCode)) {
                return;
            }
            this.countryCode = "UNKNOWN";
        } catch (Exception unused) {
            ho.c(TAG, "get getProductCountryCode error");
        }
    }

    private void d() {
        String d = dd.d();
        this.countryCode = d;
        if (TextUtils.isEmpty(d)) {
            this.countryCode = "UNKNOWN";
        }
    }

    private void c(Context context, boolean z) {
        StringBuilder sb;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            if (telephonyManager != null) {
                if (!z || telephonyManager.getPhoneType() == 2) {
                    this.countryCode = telephonyManager.getSimCountryIso();
                    sb = new StringBuilder("countryCode by SimCountryIso is: ");
                } else {
                    this.countryCode = telephonyManager.getNetworkCountryIso();
                    sb = new StringBuilder("countryCode by NetworkCountryIso is: ");
                }
                sb.append(this.countryCode);
                ho.b(TAG, sb.toString());
            }
            c();
        } catch (Exception unused) {
            ho.c(TAG, "get getSimCountryCode error");
        }
    }

    private void c(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                d();
            } else {
                d(context);
            }
        } catch (Exception unused) {
            ho.c(TAG, "get getLocaleCountryCode error");
        }
    }

    private void c() {
        String str = this.countryCode;
        if (str == null || str.length() != 2) {
            this.countryCode = "UNKNOWN";
        }
    }

    private boolean b() {
        return !"UNKNOWN".equals(this.countryCode);
    }

    private void b(Context context) {
        c(context, false);
    }

    private void a(Context context) {
        try {
            String a2 = dd.a(bz.e(context) ? VENDORCOUNTRY_SYSTEMPROP_HN : VENDORCOUNTRY_SYSTEMPROP);
            this.countryCode = a2;
            if (a2 == null) {
                this.countryCode = "UNKNOWN";
            }
            if (!SPECIAL_COUNTRYCODE_EU.equalsIgnoreCase(this.countryCode) && !SPECIAL_COUNTRYCODE_LA.equalsIgnoreCase(this.countryCode) && CountryConfig.isValidCountryCode(this.countryCode)) {
                c();
                return;
            }
            this.countryCode = "UNKNOWN";
        } catch (Exception unused) {
            ho.c(TAG, "get getVendorCountryCode error");
        }
    }

    public CountryCodeBean(Context context, boolean z) {
        this.countryCode = "UNKNOWN";
        if (context == null) {
            return;
        }
        a(context.getApplicationContext(), z);
    }

    public CountryCodeBean(Context context) {
        this(context, false);
    }
}
