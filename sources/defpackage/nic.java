package defpackage;

import android.content.Context;
import android.icu.util.ULocale;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.taboo.TabooReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class nic {
    public static String e(Locale locale, Locale locale2, Context context) {
        if (b(context, locale) && c(locale, locale2)) {
            return b(locale, locale2);
        }
        String displayCountry = locale.getDisplayCountry(locale2);
        Locale locale3 = new Locale("my", "MM");
        if (locale.getCountry().equals("ZG")) {
            displayCountry = locale3.getDisplayCountry(locale2) + "(Zawgyi)";
        }
        String d = new TabooReader().d(TabooReader.ParamType.REGION_NAME, locale2, locale.getCountry(), context);
        return d != null ? d : displayCountry;
    }

    public static ArrayList<String> e(Context context, Locale locale) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (b(context, locale)) {
            arrayList = a(context, locale);
        } else if (nie.b(context)) {
            arrayList = c(context, locale);
        } else {
            arrayList.addAll(d(context, locale));
        }
        arrayList.add("EH");
        arrayList.add("XK");
        return arrayList;
    }

    private static String b(Locale locale, Locale locale2) {
        return (String) nie.c("com.huawei.android.app.LocaleHelperEx", "getDisplayCountry", new Class[]{Locale.class, Locale.class}, new Object[]{locale, locale2});
    }

    private static ArrayList<String> a(Context context, Locale locale) {
        return (ArrayList) nie.c("com.huawei.android.app.LocaleHelperEx", "getBlackRegions", new Class[]{Context.class, Locale.class}, new Object[]{context, locale});
    }

    private static boolean c(Locale locale, Locale locale2) {
        return nie.d("com.huawei.android.app.LocaleHelperEx", "getDisplayCountry", new Class[]{Locale.class, Locale.class}, new Object[]{locale, locale2});
    }

    private static ArrayList<String> d(Context context, Locale locale) {
        ArrayList<String> arrayList = new ArrayList<>();
        HashMap<String, String> e = nif.e(context);
        String country = locale.getCountry();
        if (e != null && !country.isEmpty()) {
            for (Map.Entry<String, String> entry : e.entrySet()) {
                String value = entry.getValue();
                List arrayList2 = new ArrayList();
                if (value.contains(",")) {
                    arrayList2 = Arrays.asList(value.split(","));
                } else {
                    arrayList2.add(value);
                }
                if (arrayList2.contains(country)) {
                    String key = entry.getKey();
                    if (key.contains(",")) {
                        arrayList.addAll(Arrays.asList(key.split(",")));
                    } else {
                        arrayList.add(key);
                    }
                }
            }
        }
        return arrayList;
    }

    private static boolean b(Context context, Locale locale) {
        return nie.d("com.huawei.android.app.LocaleHelperEx", "getBlackRegions", new Class[]{Context.class, Locale.class}, new Object[]{context, locale});
    }

    private static ArrayList<String> c(Context context, Locale locale) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(b(context));
        ULocale addLikelySubtags = ULocale.addLikelySubtags(ULocale.forLocale(locale));
        String language = addLikelySubtags.getLanguage();
        String script = addLikelySubtags.getScript();
        Iterator<String> it = a(context).iterator();
        while (it.hasNext()) {
            ULocale addLikelySubtags2 = ULocale.addLikelySubtags(ULocale.forLanguageTag(it.next()));
            String language2 = addLikelySubtags2.getLanguage();
            String script2 = addLikelySubtags2.getScript();
            String country = addLikelySubtags2.getCountry();
            if (language.equals(language2) && script.equals(script2)) {
                arrayList.add(country);
            }
        }
        return arrayList;
    }

    private static ArrayList<String> a(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        String e = e(context);
        if (e != null) {
            for (String str : e.split(",")) {
                String trim = str.trim();
                if (!trim.startsWith("*") && !trim.endsWith("*") && trim.split(Constants.LINK).length >= 2) {
                    arrayList.add(trim);
                }
            }
        }
        return arrayList;
    }

    private static ArrayList<String> b(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        String e = e(context);
        if (e != null) {
            for (String str : e.split(",")) {
                String trim = str.trim();
                if (trim.startsWith("*")) {
                    arrayList.add(trim.split(Constants.LINK)[1]);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String e(android.content.Context r4) {
        /*
            boolean r0 = defpackage.nie.b(r4)
            r1 = 0
            if (r0 == 0) goto L19
            android.content.ContentResolver r0 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L12
            java.lang.String r2 = "black_languages"
            java.lang.String r0 = com.huawei.android.provider.SettingsEx.Systemex.getString(r0, r2)     // Catch: java.lang.Throwable -> L12
            goto L1a
        L12:
            java.lang.String r0 = "TabooLocaleHelper"
            java.lang.String r2 = "Could not load default locales"
            android.util.Log.e(r0, r2)
        L19:
            r0 = r1
        L1a:
            com.huawei.taboo.TabooReader r2 = new com.huawei.taboo.TabooReader
            r2.<init>()
            com.huawei.taboo.TabooReader$ParamType r3 = com.huawei.taboo.TabooReader.ParamType.BLACK_LANG
            java.lang.String r4 = r2.d(r3, r1, r1, r4)
            if (r4 == 0) goto L28
            r0 = r4
        L28:
            if (r0 == 0) goto L3a
            java.lang.String r4 = "tl"
            java.lang.String r1 = "fil"
            java.lang.String r4 = r0.replace(r4, r1)
            java.lang.String r0 = "_"
            java.lang.String r1 = "-"
            java.lang.String r0 = r4.replace(r0, r1)
        L3a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nic.e(android.content.Context):java.lang.String");
    }
}
