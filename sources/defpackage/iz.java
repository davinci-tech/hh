package defpackage;

import android.content.Context;
import com.alipay.sdk.m.b.b;

/* loaded from: classes7.dex */
public class iz implements b {
    @Override // com.alipay.sdk.m.b.b
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        boolean e = kf.e();
        ja.e("getOAID", "isSupported", Boolean.valueOf(e));
        if (e) {
            return kf.d(context);
        }
        return null;
    }
}
