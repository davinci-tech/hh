package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;

/* loaded from: classes2.dex */
public final class ki {

    /* renamed from: a, reason: collision with root package name */
    private Context f1257a;
    private String b;
    private String c;
    private String d;
    private String e;

    public ki(Context context, String str, String str2, String str3) throws hm {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new hm("无效的参数 - IllegalArgumentException");
        }
        this.f1257a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public final void a(String str) throws hm {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new hm("无效的参数 - IllegalArgumentException");
        }
        this.e = str;
    }

    private byte[] b(String str) {
        byte[] a2;
        if (!TextUtils.isEmpty(str) && (a2 = ia.a(this.e)) != null) {
            return ia.a(a2.length);
        }
        return new byte[]{0, 0};
    }

    public final byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream;
        int i = 0;
        byte[] bArr = new byte[0];
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ia.a(byteArrayOutputStream, this.c);
                ia.a(byteArrayOutputStream, this.d);
                ia.a(byteArrayOutputStream, this.b);
                ia.a(byteArrayOutputStream, String.valueOf(hr.o(this.f1257a)));
                try {
                    i = (int) (System.currentTimeMillis() / 1000);
                } catch (Throwable unused) {
                }
                byteArrayOutputStream.write(a(i));
                byteArrayOutputStream.write(b(this.e));
                byteArrayOutputStream.write(ia.a(this.e));
                bArr = byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                th = th;
                try {
                    iv.c(th, "se", "tds");
                } finally {
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
        return bArr;
    }

    private static byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }
}
