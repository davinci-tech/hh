package com.alipay.sdk.m.n0;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.m.l0.b;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import defpackage.km;
import defpackage.ko;
import defpackage.kq;
import defpackage.ks;
import defpackage.kx;
import defpackage.le;
import defpackage.lg;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public class d {
    public static d d;
    public Context c;
    public le f;
    public kx g;
    public String h;
    public String i;
    public kx j;
    public static final Object e = new Object();
    public static final String b = ".UTSystemConfig" + File.separator + "Global";

    /* renamed from: a, reason: collision with root package name */
    public String f865a = null;
    public Pattern m = Pattern.compile("[^0-9a-zA-Z=/+]+");

    public d(Context context) {
        this.c = null;
        this.f = null;
        this.i = "xx_utdid_key";
        this.h = "xx_utdid_domain";
        this.g = null;
        this.j = null;
        this.c = context;
        this.j = new kx(context, b, "Alvin2", false, true);
        this.g = new kx(context, ".DataStorage", "ContextData", false, true);
        this.f = new le();
        this.i = String.format("K_%d", Integer.valueOf(ks.b(this.i)));
        this.h = String.format("D_%d", Integer.valueOf(ks.b(this.h)));
    }

    private byte[] c() throws Exception {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nextInt = new Random().nextInt();
        byte[] a2 = km.a(currentTimeMillis);
        byte[] a3 = km.a(nextInt);
        byteArrayOutputStream.write(a2, 0, 4);
        byteArrayOutputStream.write(a3, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            str = kq.a(this.c);
        } catch (Exception unused) {
            str = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(km.a(ks.b(str)), 0, 4);
        byteArrayOutputStream.write(km.a(ks.b(a(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    private void d(String str) {
        kx kxVar;
        if (str == null || (kxVar = this.g) == null || str.equals(kxVar.d(this.i))) {
            return;
        }
        this.g.c(this.i, str);
        this.g.c();
    }

    public static d e(Context context) {
        if (context != null && d == null) {
            synchronized (e) {
                if (d == null) {
                    d dVar = new d(context);
                    d = dVar;
                    dVar.e();
                }
            }
        }
        return d;
    }

    private void e() {
        boolean z;
        kx kxVar = this.j;
        if (kxVar != null) {
            if (ks.e(kxVar.d("UTDID2"))) {
                String d2 = this.j.d("UTDID");
                if (!ks.e(d2)) {
                    e(d2);
                }
            }
            boolean z2 = true;
            if (ks.e(this.j.d("DID"))) {
                z = false;
            } else {
                this.j.c("DID");
                z = true;
            }
            if (ks.e(this.j.d("EI"))) {
                z2 = z;
            } else {
                this.j.c("EI");
            }
            if (!ks.e(this.j.d("SI"))) {
                this.j.c("SI");
            } else if (!z2) {
                return;
            }
            this.j.c();
        }
    }

    private void e(String str) {
        kx kxVar;
        if (a(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() != 24 || (kxVar = this.j) == null) {
                return;
            }
            kxVar.c("UTDID2", str);
            this.j.c();
        }
    }

    private String j() {
        kx kxVar = this.j;
        if (kxVar == null) {
            return null;
        }
        String d2 = kxVar.d("UTDID2");
        if (ks.e(d2) || this.f.e(d2) == null) {
            return null;
        }
        return d2;
    }

    public String a() {
        synchronized (this) {
            String j = j();
            if (a(j)) {
                d(this.f.e(j));
                this.f865a = j;
                return j;
            }
            String d2 = this.g.d(this.i);
            if (!ks.e(d2)) {
                String a2 = new lg().a(d2);
                if (!a(a2)) {
                    a2 = this.f.a(d2);
                }
                if (a(a2) && !ks.e(a2)) {
                    this.f865a = a2;
                    e(a2);
                    return this.f865a;
                }
            }
            return null;
        }
    }

    public String b() {
        synchronized (this) {
            String str = this.f865a;
            if (str != null) {
                return str;
            }
            return d();
        }
    }

    private boolean a(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !this.m.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }

    public String d() {
        synchronized (this) {
            String a2 = a();
            this.f865a = a2;
            if (!TextUtils.isEmpty(a2)) {
                return this.f865a;
            }
            try {
                byte[] c = c();
                if (c != null) {
                    String c2 = b.c(c, 2);
                    this.f865a = c2;
                    e(c2);
                    String d2 = this.f.d(c);
                    if (d2 != null) {
                        d(d2);
                    }
                    return this.f865a;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    public static String a(byte[] bArr) throws Exception {
        byte[] bArr2 = {69, 114, 116, -33, 125, -54, -31, 86, -11, BaseType.Float, -78, -96, -17, -99, 64, 23, -95, -126, -82, -64, 113, 116, -16, -103, 49, -30, 9, -39, PublicSuffixDatabase.i, -80, -68, -78, -117, 53, 30, -122, 64, -104, 74, -49, 106, 85, -38, -93};
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(ko.d(bArr2), mac.getAlgorithm()));
        return b.c(mac.doFinal(bArr), 2);
    }
}
