package defpackage;

import android.content.Context;
import com.alipay.sdk.m.b.b;

/* loaded from: classes7.dex */
public class io implements b {

    /* renamed from: a, reason: collision with root package name */
    public com.alipay.sdk.m.r0.b f13508a;
    public boolean d = false;
    public boolean b = false;

    @Override // com.alipay.sdk.m.b.b
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        if (!this.d) {
            com.alipay.sdk.m.r0.b bVar = new com.alipay.sdk.m.r0.b();
            this.f13508a = bVar;
            this.b = bVar.c(context, null) == 1;
            this.d = true;
        }
        ja.e("getOAID", "isSupported", Boolean.valueOf(this.b));
        if (this.b && this.f13508a.d()) {
            return this.f13508a.b();
        }
        return null;
    }
}
