package defpackage;

import android.content.Context;

/* loaded from: classes9.dex */
public class pfo {

    /* renamed from: a, reason: collision with root package name */
    private Context f16113a;
    private String b;
    private String c;
    private String d;
    private boolean e;

    public pfo(Context context, String str, boolean z) {
        this.c = str;
        this.e = z;
        this.f16113a = context;
    }

    public String a() {
        return this.c;
    }

    public boolean e() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public String a(String str, boolean z) {
        String a2 = pfr.a(this.f16113a, this.c, str, z);
        this.b = a2;
        return a2;
    }

    public String a(boolean z) {
        String c = pfr.c(this.f16113a, this.c, z);
        this.d = c;
        return c;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AuthCapabilityBean{authType=");
        stringBuffer.append(this.c);
        stringBuffer.append(", authName='").append(this.d).append("', authSubName='");
        stringBuffer.append(this.b).append("', isOpen=");
        stringBuffer.append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
