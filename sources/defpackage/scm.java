package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

/* loaded from: classes7.dex */
public class scm {
    public static List<String> b() {
        String[] iSOCountries = Locale.getISOCountries();
        ArrayList<String> e = nic.e(BaseApplication.getContext(), Locale.getDefault());
        HashSet hashSet = new HashSet(24);
        Iterator<String> it = e.iterator();
        while (it.hasNext()) {
            try {
                hashSet.add(new Locale("en", it.next()).getISO3Country());
            } catch (MissingResourceException e2) {
                LogUtil.b("CountryUtils", "MissingResourceException:", e2.getMessage());
            }
        }
        HashSet hashSet2 = new HashSet(iSOCountries.length);
        for (String str : iSOCountries) {
            Locale locale = new Locale("en", str);
            String country = locale.getCountry();
            if (!TextUtils.isEmpty(country.trim()) && country.matches("[a-zA-Z]+") && !"ZG".equals(country) && !"SP".equals(country) && CommonUtil.e(locale, locale) != null && !CommonUtil.e(locale, locale).trim().isEmpty()) {
                try {
                    if (hashSet.contains(locale.getISO3Country())) {
                        LogUtil.b("CountryUtils", "getSystemCountry in blackList");
                    } else {
                        hashSet2.add(nic.e(locale, Locale.getDefault(), BaseApplication.getContext()));
                    }
                } catch (MissingResourceException e3) {
                    LogUtil.b("CountryUtils", "MissingResourceException:", e3.getMessage());
                }
            }
        }
        ArrayList arrayList = new ArrayList(hashSet2.size());
        arrayList.addAll(hashSet2);
        Collections.sort(arrayList, Collator.getInstance(Locale.CHINA));
        return arrayList;
    }

    public static String c(Locale locale) {
        return locale == null ? "" : d(locale.getCountry());
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = "";
        for (String str3 : Locale.getISOCountries()) {
            Locale locale = new Locale("", str3);
            if (str.equalsIgnoreCase(locale.getCountry())) {
                str2 = nic.e(locale, Locale.getDefault(), BaseApplication.getContext());
                if (!"".equals(str2)) {
                    break;
                }
            }
        }
        return str2;
    }

    public static String c(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        for (String str2 : Locale.getISOCountries()) {
            if (str.equalsIgnoreCase(nic.e(new Locale("", str2), Locale.getDefault(), BaseApplication.getContext()))) {
                return str2;
            }
        }
        return "";
    }
}
