package defpackage;

import android.content.Context;
import com.alipay.sdk.m.b.b;

/* loaded from: classes7.dex */
public class iu implements b {
    public boolean c = false;

    @Override // com.alipay.sdk.m.b.b
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        if (!this.c) {
            ka.d(context);
            this.c = true;
        }
        boolean c = ka.c();
        ja.e("getOAID", "isSupported", Boolean.valueOf(c));
        if (c) {
            return ka.b(context);
        }
        return null;
    }
}
