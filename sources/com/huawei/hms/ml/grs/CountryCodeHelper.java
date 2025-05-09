package com.huawei.hms.ml.grs;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.hms.ml.common.utils.SmartLog;
import java.util.Locale;

/* loaded from: classes9.dex */
public class CountryCodeHelper {
    private static final String ANDRIOD_SYSTEMPROP = "android.os.SystemProperties";
    private static final int COUNTRYCODE_SIZE = 2;
    private static final String HONOR_VENDORCOUNTRY_SYSTEMPROP = "msc.sys.country";
    private static final String LOCALE_COUNTRYSYSTEMPROP = "ro.product.locale.region";
    private static final String LOCALE_INFO = "LOCALE_INFO";
    private static final String NETWORK_COUNTRY = "NETWORK_COUNTRY";
    private static final String SIM_COUNTRY = "SIM_COUNTRY";
    private static final String SPECIAL_COUNTRYCODE_CN = "cn";
    private static final String SPECIAL_COUNTRYCODE_EU = "eu";
    private static final String SPECIAL_COUNTRYCODE_LA = "la";
    private static final String TAG = "ML_CountryCodeHelper";
    private static final String UNKNOWN = "UNKNOWN";
    private static final String VENDORCOUNTRY_SYSTEMPROP = "ro.hw.country";
    private static final String VENDOR_COUNTRY = "VENDOR_COUNTRY";
    private String countryCode;
    private String countrySource;

    private CountryCodeHelper() {
        this.countrySource = "UNKNOWN";
        this.countryCode = "UNKNOWN";
        this.countryCode = "UNKNOWN".toUpperCase(Locale.ENGLISH);
    }

    static final class Holder {
        static CountryCodeHelper sInstance = new CountryCodeHelper();

        private Holder() {
        }
    }

    public static CountryCodeHelper getInstance() {
        return Holder.sInstance;
    }

    public String getCountrySource() {
        return this.countrySource;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public CountryCodeHelper prepare(Context context, boolean z) {
        if (context == null) {
            throw new NullPointerException("context must be not null.Please provide app's Context");
        }
        try {
            getVendorCountryCode();
        } catch (Exception unused) {
            SmartLog.w(TAG, "get CountryCode error");
        }
        if (isCodeValidate()) {
            SmartLog.d(TAG, "getCountryCode get country code from {VENDOR_COUNTRY}");
            return this;
        }
        getSimCountryCode(context, z);
        if (isCodeValidate()) {
            SmartLog.d(TAG, "getCountryCode get country code from {SIM_COUNTRY}");
            return this;
        }
        getLocaleCountryCode();
        if (isCodeValidate()) {
            SmartLog.d(TAG, "getCountryCode get country code from {LOCALE_INFO}");
        }
        return this;
    }

    private boolean isCodeValidate() {
        return !"UNKNOWN".equals(this.countryCode);
    }

    private void checkCodeLenth() {
        String str = this.countryCode;
        if (str == null || str.length() != 2) {
            this.countryCode = "UNKNOWN";
            this.countrySource = "UNKNOWN";
        }
    }

    private void getVendorCountryCode() {
        this.countrySource = "VENDOR_COUNTRY";
        if (Build.BRAND.equals("HONOR")) {
            this.countryCode = getProperty("get", "msc.sys.country", ANDRIOD_SYSTEMPROP, "UNKNOWN");
        } else {
            this.countryCode = getProperty("get", "ro.hw.country", ANDRIOD_SYSTEMPROP, "UNKNOWN");
        }
        SmartLog.e(TAG, "getVendorCountry=" + this.countryCode);
        if (SPECIAL_COUNTRYCODE_EU.equalsIgnoreCase(this.countryCode) || SPECIAL_COUNTRYCODE_LA.equalsIgnoreCase(this.countryCode)) {
            SmartLog.e(TAG, "getVendorCountry equals eu or la ,not reliable");
            this.countryCode = "UNKNOWN";
            this.countrySource = "UNKNOWN";
            return;
        }
        checkCodeLenth();
    }

    private void getSimCountryCode(Context context, boolean z) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
        if (telephonyManager != null) {
            if (z && telephonyManager.getPhoneType() != 2) {
                this.countryCode = telephonyManager.getNetworkCountryIso();
                this.countrySource = "NETWORK_COUNTRY";
                SmartLog.e(TAG, "getSimCountryCode by enableNetwork, countryCode=" + this.countryCode);
            } else {
                this.countryCode = telephonyManager.getSimCountryIso();
                this.countrySource = "SIM_COUNTRY";
                SmartLog.e(TAG, "getSimCountryCode by not enableNetwork, countryCode=" + this.countryCode);
            }
        }
        checkCodeLenth();
    }

    private void getLocaleCountryCode() {
        String property = getProperty("get", "ro.product.locale.region", ANDRIOD_SYSTEMPROP, "UNKNOWN");
        this.countryCode = property;
        this.countrySource = "LOCALE_INFO";
        if (SPECIAL_COUNTRYCODE_CN.equalsIgnoreCase(property)) {
            return;
        }
        SmartLog.e(TAG, "getLocaleCountryCode from system language is not reliable.");
        this.countryCode = "UNKNOWN";
        this.countrySource = "UNKNOWN";
    }

    private static String getProperty(String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            try {
                Class<?> cls = Class.forName(str3);
                String str5 = (String) cls.getMethod(str, String.class, String.class).invoke(cls, str2, str4);
                String str6 = str5;
                return str5;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(e.getMessage());
            } catch (Exception e2) {
                SmartLog.e(TAG, "getProperty catch exception: ", e2);
                return str4;
            }
        }
        SmartLog.w(TAG, "reflect class for method has exception.");
        return str4;
    }
}
