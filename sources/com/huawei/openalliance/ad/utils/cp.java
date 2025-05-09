package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* loaded from: classes5.dex */
public abstract class cp {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f7663a = new byte[0];
    private static final byte[] b = new byte[0];
    private static SoftReference<byte[]> c;

    private static String g(Context context) {
        final a a2 = a.a(context);
        String f = a2.f();
        if (!cz.b(f)) {
            return f;
        }
        final String b2 = b(64);
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.cp.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.f(b2);
            }
        });
        return b2;
    }

    private static byte[] f(Context context) {
        ho.b("SecretUtil", "regenerateWorkKey");
        a.a(context).a("");
        return an.a(e(context));
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001f, code lost:
    
        if (android.text.TextUtils.isEmpty(r2) != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String e(android.content.Context r4) {
        /*
            if (r4 != 0) goto L5
            java.lang.String r4 = ""
            return r4
        L5:
            byte[] r0 = com.huawei.openalliance.ad.utils.cp.f7663a
            monitor-enter(r0)
            com.huawei.openalliance.ad.utils.cp$a r1 = com.huawei.openalliance.ad.utils.cp.a.a(r4)     // Catch: java.lang.Throwable -> L27
            java.lang.String r2 = r1.a()     // Catch: java.lang.Throwable -> L27
            if (r2 != 0) goto L13
            goto L21
        L13:
            byte[] r3 = d(r4)     // Catch: java.lang.Throwable -> L27
            java.lang.String r2 = com.huawei.openalliance.ad.utils.f.b(r2, r3)     // Catch: java.lang.Throwable -> L27
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L27
            if (r3 == 0) goto L25
        L21:
            java.lang.String r2 = a(r4, r1)     // Catch: java.lang.Throwable -> L27
        L25:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L27
            return r2
        L27:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L27
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.cp.e(android.content.Context):java.lang.String");
    }

    private static byte[] d(Context context) {
        String str;
        if (context == null) {
            return new byte[0];
        }
        a a2 = a.a(context);
        try {
            return a(an.a(c(context)).toCharArray(), an.a(a2.b()));
        } catch (NoSuchAlgorithmException unused) {
            str = "get userRootKey NoSuchAlgorithmException";
            ho.c("SecretUtil", str);
            return null;
        } catch (InvalidKeySpecException unused2) {
            str = "get userRootKey InvalidKeySpecException";
            ho.c("SecretUtil", str);
            return null;
        }
    }

    public static class a {
        private static final byte[] g = new byte[0];
        private static a h;

        /* renamed from: a, reason: collision with root package name */
        private SharedPreferences f7665a;
        private SharedPreferences b;
        private SharedPreferences c;
        private final byte[] d = new byte[0];
        private final byte[] e = new byte[0];
        private final byte[] f = new byte[0];
        private Context i;

        public void f(String str) {
            synchronized (this.f) {
                this.f7665a.edit().putString("aes_work_key1", str).apply();
            }
        }

        public String f() {
            String string;
            synchronized (this.f) {
                string = this.f7665a.getString("aes_work_key1", "");
            }
            return string;
        }

        public void e(String str) {
            synchronized (this.f) {
                SharedPreferences sharedPreferences = this.f7665a;
                if (sharedPreferences == null) {
                    return;
                }
                sharedPreferences.edit().putString("pub_store_door_ks", str).apply();
            }
        }

        public String e() {
            synchronized (this.f) {
                SharedPreferences sharedPreferences = this.f7665a;
                if (sharedPreferences == null) {
                    return "";
                }
                return sharedPreferences.getString("pub_store_door_ks", "");
            }
        }

        public void d(String str) {
            synchronized (this.e) {
                if (this.c == null) {
                    return;
                }
                this.c.edit().putString("read_first_chapter", f.a(str, cp.b(this.i))).apply();
            }
        }

        public String d() {
            synchronized (this.e) {
                SharedPreferences sharedPreferences = this.c;
                if (sharedPreferences == null) {
                    return "";
                }
                String string = sharedPreferences.getString("read_first_chapter", "");
                if (TextUtils.isEmpty(string)) {
                    return string;
                }
                return f.b(string, cp.b(this.i));
            }
        }

        public void c(String str) {
            synchronized (this.f) {
                SharedPreferences sharedPreferences = this.f7665a;
                if (sharedPreferences == null) {
                    return;
                }
                sharedPreferences.edit().putString("pub_store_door", str).apply();
            }
        }

        public String c() {
            synchronized (this.f) {
                SharedPreferences sharedPreferences = this.f7665a;
                if (sharedPreferences == null) {
                    return "";
                }
                return sharedPreferences.getString("pub_store_door", "");
            }
        }

        void b(String str) {
            synchronized (this.f) {
                SharedPreferences sharedPreferences = this.f7665a;
                if (sharedPreferences == null) {
                    return;
                }
                sharedPreferences.edit().putString("catch_a_cat", str).commit();
            }
        }

        String b() {
            synchronized (this.f) {
                SharedPreferences sharedPreferences = this.f7665a;
                if (sharedPreferences == null) {
                    return null;
                }
                String string = sharedPreferences.getString("catch_a_cat", null);
                if (string == null) {
                    string = an.a(cp.a());
                    b(string);
                }
                return string;
            }
        }

        void a(String str) {
            synchronized (this.d) {
                SharedPreferences sharedPreferences = this.b;
                if (sharedPreferences == null) {
                    return;
                }
                sharedPreferences.edit().putString("get_a_book", str).commit();
            }
        }

        String a() {
            synchronized (this.d) {
                SharedPreferences sharedPreferences = this.b;
                if (sharedPreferences == null) {
                    return null;
                }
                return sharedPreferences.getString("get_a_book", null);
            }
        }

        public static a a(Context context) {
            a aVar;
            synchronized (g) {
                if (h == null) {
                    h = new a(context);
                }
                aVar = h;
            }
            return aVar;
        }

        private a(Context context) {
            this.f7665a = null;
            this.b = null;
            this.c = null;
            try {
                this.i = context.getApplicationContext();
                Context i = x.i(context);
                this.f7665a = i.getSharedPreferences("hiad_sp_story_book_file", 4);
                this.b = i.getSharedPreferences("hiad_sp_bed_rock_file", 4);
                this.c = i.getSharedPreferences("hiad_sp_red_stone_file", 4);
            } catch (Throwable th) {
                ho.c("SecretUtil", "get SharedPreference error: %s", th.getClass().getSimpleName());
            }
        }
    }

    private static byte[] c(Context context) {
        return a(context, g(context));
    }

    private static SecureRandom c() {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (Exception e) {
            ho.c("SecretUtil", "getInstanceStrong, exception: %s", e.getClass().getSimpleName());
            secureRandom = null;
        }
        return secureRandom == null ? new SecureRandom() : secureRandom;
    }

    public static byte[] b(Context context) {
        byte[] bArr;
        byte[] f;
        synchronized (f7663a) {
            SoftReference<byte[]> softReference = c;
            bArr = softReference != null ? softReference.get() : null;
            if (bArr == null) {
                try {
                    f = an.b(e(context));
                } catch (UnsupportedEncodingException unused) {
                    ho.c("SecretUtil", "getWorkKeyBytes UnsupportedEncodingException");
                    f = f(context);
                    bArr = f;
                    c = new SoftReference<>(bArr);
                    return bArr;
                } catch (Throwable th) {
                    ho.c("SecretUtil", "getWorkKeyBytes " + th.getClass().getSimpleName());
                    f = f(context);
                    bArr = f;
                    c = new SoftReference<>(bArr);
                    return bArr;
                }
                bArr = f;
                c = new SoftReference<>(bArr);
            }
        }
        return bArr;
    }

    public static byte[] b() {
        return a(16);
    }

    private static String b(int i) {
        try {
            SecureRandom c2 = c();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(Integer.toHexString(c2.nextInt(16)));
            }
            return sb.toString();
        } catch (Throwable th) {
            ho.c("SecretUtil", "generate aes key1 err:" + th.getClass().getSimpleName());
            return "";
        }
    }

    public static byte[] a(char[] cArr, byte[] bArr) {
        return SecretKeyFactory.getInstance(Build.VERSION.SDK_INT > 26 ? "PBKDF2WithHmacSHA256" : "PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(cArr, bArr, 10000, 256)).getEncoded();
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr.length <= bArr2.length) {
            bArr2 = bArr;
            bArr = bArr2;
        }
        int length = bArr.length;
        int length2 = bArr2.length;
        byte[] bArr3 = new byte[length];
        int i = 0;
        while (i < length2) {
            bArr3[i] = (byte) (bArr2[i] ^ bArr[i]);
            i++;
        }
        while (i < bArr.length) {
            bArr3[i] = bArr[i];
            i++;
        }
        return bArr3;
    }

    private static byte[] a(String str, String str2, String str3) {
        byte[] a2 = an.a(str);
        byte[] a3 = an.a(str2);
        return a(a(a2, a3), an.a(str3));
    }

    private static byte[] a(Context context, String str) {
        return a(str, context.getString(R.string._2130851160_res_0x7f023558), context.getString(R.string._2130851161_res_0x7f023559));
    }

    public static byte[] a(Context context) {
        return a(context, cy.a());
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        c().nextBytes(bArr);
        return bArr;
    }

    public static byte[] a() {
        return a(16);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        return upperCase.contains("CBC") || upperCase.contains("LOW") || upperCase.contains("MD5") || upperCase.contains("EXP") || upperCase.contains("SRP") || upperCase.contains("DSS") || upperCase.contains("PSK") || upperCase.contains("RC4") || upperCase.contains("DES") || upperCase.contains("TLS_EMPTY_RENEGOTIATION_INFO_SCSV") || upperCase.contains("TEA") || upperCase.contains("SHA0") || upperCase.contains("MD2") || upperCase.contains("MD4") || upperCase.contains("RIPEMD") || upperCase.contains("DESX") || upperCase.contains("DES40") || upperCase.contains("RC2") || upperCase.contains("ANON") || upperCase.contains("NULL") || upperCase.contains("TLS_RSA");
    }

    public static String a(String str, String str2) {
        String b2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        synchronized (b) {
            try {
                b2 = f.b(str, an.b(str2));
            } catch (Throwable th) {
                ho.c("SecretUtil", "decrypt oaid ex: %s", th.getClass().getSimpleName());
                return null;
            }
        }
        return b2;
    }

    private static String a(Context context, a aVar) {
        String a2 = an.a(b());
        aVar.a(f.a(a2, d(context)));
        return a2;
    }
}
