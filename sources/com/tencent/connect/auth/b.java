package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static b f11278a = null;
    static final /* synthetic */ boolean d = true;
    private static int e;
    public HashMap<String, a> b = new HashMap<>();
    public final String c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public IUiListener f11279a;
        public com.tencent.connect.auth.a b;
        public String c;
    }

    public static b a() {
        if (f11278a == null) {
            f11278a = new b();
        }
        return f11278a;
    }

    public static int b() {
        int i = e + 1;
        e = i;
        return i;
    }

    public String a(a aVar) {
        int b = b();
        try {
            this.b.put("" + b, aVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "" + b;
    }

    public String c() {
        int ceil = (int) Math.ceil((Math.random() * 20.0d) + 3.0d);
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int length = charArray.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < ceil; i++) {
            stringBuffer.append(charArray[(int) (Math.random() * length)]);
        }
        return stringBuffer.toString();
    }
}
