package defpackage;

import android.net.Uri;
import health.compact.a.CommonUtils;

/* loaded from: classes.dex */
public class jex {
    private static String c = CommonUtils.f(".version.provider");

    /* renamed from: a, reason: collision with root package name */
    private static String f13777a = "content://" + c + "/";

    public static Uri bGM_(String str) {
        return Uri.parse(new StringBuffer().append(f13777a).append(str).toString());
    }
}
