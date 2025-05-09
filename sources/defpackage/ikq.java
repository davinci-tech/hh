package defpackage;

import android.content.Context;

/* loaded from: classes4.dex */
public class ikq {
    public static String e(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append("_").append(str2).append("_").append(str3);
        return stringBuffer.toString();
    }

    public static void c(Context context) {
        ikr.b(context).c();
        iks.e().a();
        iku.a(context).e();
        ikk.a(context).d();
        iko.a().e();
        iis.d().c();
    }
}
