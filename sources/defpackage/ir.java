package defpackage;

import android.content.Context;
import com.alipay.sdk.m.b.b;

/* loaded from: classes7.dex */
public class ir implements b {
    @Override // com.alipay.sdk.m.b.b
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        boolean a2 = ll.a(context);
        ja.e("getOAID", "isSupported", Boolean.valueOf(a2));
        if (a2) {
            return ll.d(context);
        }
        return null;
    }
}
