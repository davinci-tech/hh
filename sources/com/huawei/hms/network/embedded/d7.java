package com.huawei.hms.network.embedded;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* loaded from: classes9.dex */
public final class d7 {
    public static String a(String str, String str2, Charset charset) {
        return "Basic " + eb.a(str + ":" + str2, charset).b();
    }

    public static String a(String str, String str2) {
        return a(str, str2, StandardCharsets.ISO_8859_1);
    }
}
