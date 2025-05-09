package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes2.dex */
public final class hq {
    public static String a(Context context, String str, String str2) {
        try {
            return hv.b(hn.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            is.a(th, "CI", "Sco");
            return null;
        }
    }

    public static String a() {
        try {
            String valueOf = String.valueOf(System.currentTimeMillis());
            String str = !hn.a() ? "0" : "1";
            int length = valueOf.length();
            return valueOf.substring(0, length - 2) + str + valueOf.substring(length - 1);
        } catch (Throwable th) {
            is.a(th, "CI", "TS");
            return null;
        }
    }

    public static String a(Context context) {
        try {
            a aVar = new a((byte) 0);
            aVar.d = hn.c(context);
            aVar.i = hn.d(context);
            return a(aVar);
        } catch (Throwable th) {
            is.a(th, "CI", "IX");
            return null;
        }
    }

    public static byte[] a(byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return hs.a(bArr);
    }

    public static byte[] a(Context context, boolean z, boolean z2) {
        try {
            return b(b(context, z, z2));
        } catch (Throwable th) {
            is.a(th, "CI", "gz");
            return null;
        }
    }

    public static String b(Context context) {
        return c(context);
    }

    private static String c(Context context) {
        try {
            return a(b(context, false, false));
        } catch (Throwable th) {
            is.a(th, "CI", "gCXi");
            return null;
        }
    }

    private static byte[] b(byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        PublicKey d = ia.d();
        if (bArr.length > 117) {
            byte[] bArr2 = new byte[117];
            System.arraycopy(bArr, 0, bArr2, 0, 117);
            byte[] a2 = hs.a(bArr2, d);
            byte[] bArr3 = new byte[bArr.length + 11];
            System.arraycopy(a2, 0, bArr3, 0, 128);
            System.arraycopy(bArr, 117, bArr3, 128, bArr.length - 117);
            return bArr3;
        }
        return hs.a(bArr, d);
    }

    private static String a(a aVar) {
        return hs.b(b(aVar));
    }

    private static byte[] b(a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                a(byteArrayOutputStream, aVar.f1142a);
                a(byteArrayOutputStream, aVar.b);
                a(byteArrayOutputStream, aVar.c);
                a(byteArrayOutputStream, aVar.d);
                a(byteArrayOutputStream, aVar.e);
                a(byteArrayOutputStream, aVar.f);
                a(byteArrayOutputStream, aVar.g);
                a(byteArrayOutputStream, aVar.h);
                a(byteArrayOutputStream, aVar.i);
                a(byteArrayOutputStream, aVar.j);
                a(byteArrayOutputStream, aVar.k);
                a(byteArrayOutputStream, aVar.l);
                a(byteArrayOutputStream, aVar.m);
                a(byteArrayOutputStream, aVar.n);
                a(byteArrayOutputStream, aVar.o);
                a(byteArrayOutputStream, aVar.p);
                a(byteArrayOutputStream, aVar.q);
                a(byteArrayOutputStream, aVar.r);
                a(byteArrayOutputStream, aVar.s);
                a(byteArrayOutputStream, aVar.t);
                a(byteArrayOutputStream, aVar.u);
                a(byteArrayOutputStream, aVar.v);
                a(byteArrayOutputStream, aVar.w);
                a(byteArrayOutputStream, aVar.x);
                a(byteArrayOutputStream, aVar.y);
                a(byteArrayOutputStream, aVar.z);
                byte[] b = b(ia.b(byteArrayOutputStream.toByteArray()));
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                return b;
            } catch (Throwable th2) {
                th = th2;
                try {
                    is.a(th, "CI", "gzx");
                    return null;
                } finally {
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
        }
    }

    private static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (!TextUtils.isEmpty(str)) {
            ia.a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, ia.a(str));
        } else {
            ia.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        }
    }

    private static a b(Context context, boolean z, boolean z2) {
        a aVar = new a((byte) 0);
        aVar.f1142a = hr.v(context);
        aVar.b = hr.k(context);
        String h = hr.h(context);
        if (h == null) {
            h = "";
        }
        aVar.c = h;
        aVar.d = hn.c(context);
        aVar.e = Build.MODEL;
        aVar.f = Build.MANUFACTURER;
        aVar.g = Build.DEVICE;
        aVar.h = hn.b(context);
        aVar.i = hn.d(context);
        aVar.j = String.valueOf(Build.VERSION.SDK_INT);
        aVar.k = hr.y(context);
        aVar.l = hr.r(context);
        StringBuilder sb = new StringBuilder();
        sb.append(hr.o(context));
        aVar.m = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(hr.n(context));
        aVar.n = sb2.toString();
        aVar.o = hr.A(context);
        aVar.p = hr.m(context);
        aVar.q = "";
        aVar.r = "";
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] d = hr.d();
            aVar.s = d[0];
            aVar.t = d[1];
        }
        aVar.w = hr.a();
        String a2 = hr.a(context);
        if (!TextUtils.isEmpty(a2)) {
            aVar.x = a2;
        } else {
            aVar.x = "";
        }
        aVar.y = "aid=" + hr.j(context);
        if ((z2 && io.d) || io.e) {
            String g = hr.g(context);
            if (!TextUtils.isEmpty(g)) {
                aVar.y += "|oaid=" + g;
            }
        }
        String a3 = hr.a(context, ",");
        if (!TextUtils.isEmpty(a3)) {
            aVar.y += "|multiImeis=" + a3;
        }
        String x = hr.x(context);
        if (!TextUtils.isEmpty(x)) {
            aVar.y += "|meid=" + x;
        }
        aVar.y += "|serial=" + hr.i(context);
        String b = hr.b();
        if (!TextUtils.isEmpty(b)) {
            aVar.y += "|adiuExtras=" + b;
        }
        aVar.y += "|storage=" + hr.f() + "|ram=" + hr.z(context) + "|arch=" + hr.g();
        String b2 = iq.a().b();
        if (!TextUtils.isEmpty(b2)) {
            aVar.z = b2;
        } else {
            aVar.z = "";
        }
        return aVar;
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        String f1142a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;
        String v;
        String w;
        String x;
        String y;
        String z;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }
}
