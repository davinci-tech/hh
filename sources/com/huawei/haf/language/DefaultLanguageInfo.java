package com.huawei.haf.language;

import android.text.TextUtils;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
final class DefaultLanguageInfo implements LanguageInfo {
    private static final HashMap<String, String> d = new HashMap<>();
    private static final HashSet<String> c = new HashSet<>();

    private DefaultLanguageInfo() {
        HashMap<String, String> hashMap = d;
        if (hashMap.isEmpty()) {
            LanguageInfoHelper.a(hashMap, c);
        }
    }

    static LanguageInfo e() {
        DefaultLanguageInfo defaultLanguageInfo;
        synchronized (DefaultLanguageInfo.class) {
            defaultLanguageInfo = new DefaultLanguageInfo();
        }
        return defaultLanguageInfo;
    }

    @Override // com.huawei.haf.language.LanguageInfo
    public String getLanguageUuid(Locale locale) {
        String c2 = c(locale);
        String a2 = a(locale, c2);
        String c3 = c(locale, a2, c2);
        HashMap<String, String> hashMap = d;
        String str = hashMap.get(c3);
        return str != null ? str : hashMap.get(a2);
    }

    @Override // com.huawei.haf.language.LanguageInfo
    public String getLanguageName(String str, Locale locale) {
        if (str != null) {
            for (Map.Entry<String, String> entry : d.entrySet()) {
                if (str.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
        }
        return locale != null ? a(locale, c(locale)) : "";
    }

    @Override // com.huawei.haf.language.LanguageInfo
    public List<String> getAllLanguageUuid(boolean z) {
        HashMap<String, String> hashMap = d;
        ArrayList arrayList = new ArrayList(hashMap.size());
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (!z || !isCloudPresetLanguage(entry.getValue())) {
                arrayList.add(entry.getValue());
            }
        }
        return arrayList;
    }

    @Override // com.huawei.haf.language.LanguageInfo
    public boolean isStorePresetLanguage(String str) {
        return "en".equals(getLanguageName(str, null));
    }

    @Override // com.huawei.haf.language.LanguageInfo
    public boolean isCloudPresetLanguage(String str) {
        if (str != null) {
            return c.contains(str);
        }
        return true;
    }

    @Override // com.huawei.haf.language.LanguageInfo
    public boolean isCloudSecondLanguage(String str) {
        return LanguageInfoHelper.a(str);
    }

    private static String c(Locale locale) {
        return locale.getScript();
    }

    private static String a(Locale locale, String str) {
        char c2;
        String language = locale.getLanguage();
        language.hashCode();
        int hashCode = language.hashCode();
        if (hashCode == 3404) {
            if (language.equals("jv")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode == 3500) {
            if (language.equals("my")) {
                c2 = 1;
            }
            c2 = 65535;
        } else if (hashCode != 3679) {
            if (hashCode == 101385 && language.equals("fil")) {
                c2 = 3;
            }
            c2 = 65535;
        } else {
            if (language.equals("sr")) {
                c2 = 2;
            }
            c2 = 65535;
        }
        return c2 != 0 ? c2 != 1 ? c2 != 2 ? c2 != 3 ? language : "tl" : "b+sr+Latn" : "Qaag".equals(str) ? "b+my+Qaag" : language : "b+jv+Latn";
    }

    private static String c(Locale locale, String str, String str2) {
        char c2;
        String str3 = str + Constants.LINK + locale.getCountry();
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 3149) {
            if (str.equals("bo")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode == 3241) {
            if (str.equals("en")) {
                c2 = 1;
            }
            c2 = 65535;
        } else if (hashCode == 3246) {
            if (str.equals("es")) {
                c2 = 2;
            }
            c2 = 65535;
        } else if (hashCode != 3588) {
            if (hashCode == 3886 && str.equals(MLAsrConstants.LAN_ZH)) {
                c2 = 4;
            }
            c2 = 65535;
        } else {
            if (str.equals("pt")) {
                c2 = 3;
            }
            c2 = 65535;
        }
        return c2 != 0 ? c2 != 1 ? c2 != 2 ? c2 != 3 ? c2 != 4 ? str3 : ("Hans".equals(str2) || (TextUtils.isEmpty(str2) && "zh-CN".equals(str3))) ? "zh-CN" : !"zh-HK".equals(str3) ? "zh-TW" : "zh-HK" : Arrays.asList("pt-AO", "pt-CV", "pt-GW", "pt-MO", "pt-MZ", "pt-ST", "pt-TL").contains(str3) ? "pt-PT" : str3 : Arrays.asList("es-AR", "es-BO", "es-CL", "es-CO", "es-CR", "es-CU", "es-DO", "es-EC", "es-GT", "es-HN", "es-MX", "es-NI", "es-PA", "es-PE", "es-PR", "es-PY", "es-SV", "es-UY", "es-VE").contains(str3) ? "es-US" : str3 : "Qaag".equals(str2) ? "en-GB" : str3 : "bo-CN";
    }
}
