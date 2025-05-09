package com.huawei.taboo;

import android.content.Context;
import com.amap.api.services.district.DistrictSearchQuery;
import defpackage.nhz;
import defpackage.nia;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class TabooReader {
    private static nia.e c;
    private nhz d = null;
    private nhz b = null;

    public enum ParamType {
        LANGUAGE_NAME("lang", "langs"),
        REGION_NAME("region", "regions"),
        BLACK_LANG("black_lang", ""),
        CITY_NAME(DistrictSearchQuery.KEYWORDS_CITY, "citys"),
        BLACK_CITY("black_city", "");

        private String prefix;
        private String scopeName;

        ParamType(String str, String str2) {
            this.prefix = str;
            this.scopeName = str2;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public String getScopeName() {
            return this.scopeName;
        }
    }

    public String d(ParamType paramType, Locale locale, String str, Context context) {
        String d;
        String str2;
        if (context == null || paramType == null) {
            return null;
        }
        a(context);
        if (this.b == null) {
            return null;
        }
        int i = AnonymousClass5.f8727a[paramType.ordinal()];
        if (i == 1) {
            return e(this.b, paramType.getPrefix(), true);
        }
        if (i == 2) {
            return e(this.b, paramType.getPrefix(), true);
        }
        if (i == 3) {
            String b = nia.b(str);
            if (b == null || !d(paramType, b, context)) {
                return null;
            }
            d = d(this.b, locale, paramType.getPrefix() + "_" + b);
        } else if (i == 4) {
            if (locale == null || str == null || str.isEmpty()) {
                return null;
            }
            if (d(paramType, str, context)) {
                str2 = d(this.b, locale, paramType.getPrefix() + "_" + str);
            } else {
                str2 = null;
            }
            if (str2 != null) {
                return str2;
            }
            String b2 = nia.b(str, context);
            if (!d(paramType, b2, context)) {
                return null;
            }
            d = d(this.b, locale, paramType.getPrefix() + "_" + b2);
        } else {
            if (str == null || !d(paramType, str, context)) {
                return null;
            }
            d = d(this.b, locale, paramType.getPrefix() + "_" + str);
        }
        return d;
    }

    /* renamed from: com.huawei.taboo.TabooReader$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8727a;

        static {
            int[] iArr = new int[ParamType.values().length];
            f8727a = iArr;
            try {
                iArr[ParamType.BLACK_LANG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8727a[ParamType.BLACK_CITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8727a[ParamType.CITY_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8727a[ParamType.LANGUAGE_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8727a[ParamType.REGION_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public boolean d(ParamType paramType, String str, Context context) {
        a(context);
        if (this.b == null) {
            return false;
        }
        int i = AnonymousClass5.f8727a[paramType.ordinal()];
        if (i == 3) {
            return this.b.c().b().contains(str);
        }
        if (i == 4) {
            return this.b.c().a().contains(str);
        }
        if (i != 5) {
            return false;
        }
        return this.b.c().c().contains(str);
    }

    private String d(nhz nhzVar, Locale locale, String str) {
        List<String> b;
        if (locale != null && (b = nhzVar.b()) != null && !b.isEmpty()) {
            List<Map.Entry<String, Integer>> d = nia.d(b, locale, nhzVar.a());
            if (d.get(0).getValue().intValue() != -1) {
                String key = d.get(0).getKey();
                Iterator<String> it = nia.a(c, str).iterator();
                while (it.hasNext()) {
                    String c2 = nhzVar.c(key, it.next());
                    if (c2 != null && !c2.isEmpty()) {
                        return c2;
                    }
                }
            }
        }
        return null;
    }

    private String e(nhz nhzVar, String str, boolean z) {
        if (z) {
            Iterator<String> it = nia.a(c, str).iterator();
            while (it.hasNext()) {
                String b = nhzVar.c().b(it.next());
                if (b != null && !b.isEmpty()) {
                    return b;
                }
            }
            return null;
        }
        return nhzVar.c().b(str);
    }

    private void a(Context context) {
        nhz c2;
        if (this.b == null && (c2 = nhz.c(context)) != null) {
            this.b = c2;
        }
        if (c == null) {
            c = nia.e.b(context);
        }
    }
}
