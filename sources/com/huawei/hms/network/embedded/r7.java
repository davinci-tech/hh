package com.huawei.hms.network.embedded;

import com.huawei.hms.network.inner.utils.CheckConfigUtils;
import java.io.IOException;

/* loaded from: classes9.dex */
public enum r7 {
    HTTP_1_0("http/1.0"),
    HTTP_1_1(CheckConfigUtils.Constants.HTTP_1_1),
    SPDY_3("spdy/3.1"),
    HTTP_2(CheckConfigUtils.Constants.HTTP_2),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC(g2.QUIC);


    /* renamed from: a, reason: collision with root package name */
    public final String f5459a;

    @Override // java.lang.Enum
    public String toString() {
        return this.f5459a;
    }

    public static r7 a(String str) throws IOException {
        r7 r7Var = HTTP_1_0;
        if (str.equals(r7Var.f5459a)) {
            return r7Var;
        }
        r7 r7Var2 = HTTP_1_1;
        if (str.equals(r7Var2.f5459a)) {
            return r7Var2;
        }
        r7 r7Var3 = H2_PRIOR_KNOWLEDGE;
        if (str.equals(r7Var3.f5459a)) {
            return r7Var3;
        }
        r7 r7Var4 = HTTP_2;
        if (str.equals(r7Var4.f5459a)) {
            return r7Var4;
        }
        r7 r7Var5 = SPDY_3;
        if (str.equals(r7Var5.f5459a)) {
            return r7Var5;
        }
        r7 r7Var6 = QUIC;
        if (str.equals(r7Var6.f5459a)) {
            return r7Var6;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    r7(String str) {
        this.f5459a = str;
    }
}
