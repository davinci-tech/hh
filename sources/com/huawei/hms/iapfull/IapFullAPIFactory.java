package com.huawei.hms.iapfull;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class IapFullAPIFactory {
    public static final String PAY_TRADE_TYPE = "Pay";
    public static final String WITHHOLD_TRADE_TYPE = "Withhold";

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, String> f4671a = new a();

    public static boolean isWebPaySupport(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "reservedInfor is null!";
        } else {
            if (!WITHHOLD_TRADE_TYPE.equals(str2)) {
                String a2 = y0.a(str);
                y0.b("IapFullAPIFactory", "country is " + a2);
                return (TextUtils.isEmpty(a2) || "CN".equals(a2)) ? false : true;
            }
            str3 = "tradeType is withhold";
        }
        y0.b("IapFullAPIFactory", str3);
        return false;
    }

    public static boolean isCountryAndCurrencySupport(String str, String str2) {
        y0.b("IapFullAPIFactory", "start check Country and Currency");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            y0.a("IapFullAPIFactory", "isCountryAndCurrencySupport country or currency is  null");
            return false;
        }
        Map<String, String> map = f4671a;
        Locale locale = Locale.US;
        if (map.containsKey(str.toUpperCase(locale))) {
            return map.get(str.toUpperCase(locale)).contains(str2.toUpperCase(locale));
        }
        return false;
    }

    public static IIapFullAPIVer4 createIapFullAPIVer4(Context context) {
        return new com.huawei.hms.iapfull.a(context);
    }

    public static IIapFullAPI createIapFullAPI(Context context) {
        return new com.huawei.hms.iapfull.a(context);
    }

    class a extends HashMap<String, String> {
        a() {
            put("CN", "CNY");
        }
    }
}
