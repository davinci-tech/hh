package com.huawei.phoneservice.faq.base.util;

import android.content.res.Resources;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;

/* loaded from: classes9.dex */
public final class h {
    public static String d() {
        return Resources.getSystem().getConfiguration().getLocales().get(0).getCountry();
    }

    public static String a() {
        return c() + Constants.LINK + d().toLowerCase(Locale.ENGLISH);
    }

    public static String c() {
        return Resources.getSystem().getConfiguration().getLocales().get(0).getLanguage();
    }
}
