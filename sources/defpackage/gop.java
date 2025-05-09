package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import defpackage.iyk;

/* loaded from: classes4.dex */
public class gop {
    public static void c(iyk iykVar, String str, boolean z) {
        if (e(str)) {
            ixx.d().c(iykVar);
        } else if (e(iykVar.e(), iykVar.c()) || z) {
            ixx.d().c(iykVar);
        }
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "operationPushMsg".equals(str);
    }

    public static boolean e(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) ? false : true;
    }

    public static iyk aRd_(String str, String str2, String str3, String str4, Uri uri) {
        if ("operationPushMsg".equals(str)) {
            return new iyk.e().a(0).e(str2).c(str3).b(str4).a(uri.toString()).d(uri.getQueryParameter("pushName")).b();
        }
        return new iyk.e().a(1).b(str4).e(str2).a(uri.toString()).c(str3).d(uri.getQueryParameter("pushName")).b();
    }
}
