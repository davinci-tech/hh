package defpackage;

import com.alipay.sdk.m.h.b;

/* loaded from: classes7.dex */
public final class jw extends b {
    public final String j;

    public jw(String str) {
        this.j = str;
    }

    @Override // com.alipay.sdk.m.h.b
    public void a() throws Exception {
        this.f859a = (byte) 1;
        byte[] bytes = this.j.getBytes("UTF-8");
        this.c = bytes;
        this.b = (byte) bytes.length;
    }
}
