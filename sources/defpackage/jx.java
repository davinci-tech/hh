package defpackage;

import android.content.Context;
import com.alipay.sdk.m.g0.a;

/* loaded from: classes7.dex */
public class jx implements a {
    public static a b;
    public static com.alipay.sdk.m.d0.a c;

    @Override // com.alipay.sdk.m.g0.a
    public boolean logCollect(String str) {
        return c.logCollect(str);
    }

    @Override // com.alipay.sdk.m.g0.a
    public jt a(js jsVar) {
        return jp.b(c.a(jp.c(jsVar)));
    }

    public static a a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (b == null) {
            c = jd.d(context, str);
            b = new jx();
        }
        return b;
    }
}
