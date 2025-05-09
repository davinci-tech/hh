package com.huawei.maps.offlinedata.utils;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, String> f6528a = new HashMap<String, String>() { // from class: com.huawei.maps.offlinedata.utils.f.1
        {
            put("iw", "he");
            put("in", "id");
            put("fil", "tl");
        }
    };

    public static String a(String str) {
        Map<String, String> map = f6528a;
        return map.containsKey(str) ? map.get(str) : str;
    }

    public static String a() {
        Locale locale = Locale.getDefault();
        String a2 = a(locale.getLanguage());
        if (!TextUtils.isEmpty(locale.getScript())) {
            a2 = a2 + Constants.LINK + locale.getScript();
        }
        g.a("LanguageUtil", "systemLanguage is: " + a2);
        return a2;
    }
}
