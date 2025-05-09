package com.huawei.openalliance.ad.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cp;
import java.io.Closeable;
import java.lang.ref.SoftReference;
import java.security.interfaces.RSAPublicKey;

/* loaded from: classes5.dex */
public class by {

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f7647a = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/oaid_pub_store/get").build();
    private static final Uri b = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/oaid_pub_store_ks/get").build();
    private static final byte[] c = new byte[0];
    private static SoftReference<RSAPublicKey> d;
    private static SoftReference<RSAPublicKey> e;

    private static RSAPublicKey e(Context context) {
        RSAPublicKey rSAPublicKey;
        synchronized (c) {
            SoftReference<RSAPublicKey> softReference = e;
            rSAPublicKey = softReference != null ? softReference.get() : null;
            if (rSAPublicKey == null) {
                final Context applicationContext = context.getApplicationContext();
                final cp.a a2 = cp.a.a(applicationContext);
                RSAPublicKey a3 = ch.a(a2.c());
                if (a3 != null) {
                    e = new SoftReference<>(a3);
                }
                m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.by.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String b2 = by.b(applicationContext);
                        ho.a("OaidSettingsUtil", "##### remote pub store: %s", b2);
                        a2.c(b2);
                    }
                });
                rSAPublicKey = a3;
            }
        }
        return rSAPublicKey;
    }

    private static RSAPublicKey d(Context context) {
        RSAPublicKey rSAPublicKey;
        synchronized (c) {
            SoftReference<RSAPublicKey> softReference = d;
            rSAPublicKey = softReference != null ? softReference.get() : null;
            if (rSAPublicKey == null) {
                final Context applicationContext = context.getApplicationContext();
                final cp.a a2 = cp.a.a(applicationContext);
                RSAPublicKey a3 = ch.a(a2.e());
                if (a3 != null) {
                    d = new SoftReference<>(a3);
                }
                m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.by.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String c2 = by.c(applicationContext);
                        ho.a("OaidSettingsUtil", "##### remote pub store KS: %s", c2);
                        a2.e(c2);
                    }
                });
                rSAPublicKey = a3;
            }
        }
        return rSAPublicKey;
    }

    public static String c(Context context) {
        StringBuilder sb;
        Uri uri;
        Cursor cursor = null;
        try {
            try {
                uri = b;
            } catch (IllegalArgumentException e2) {
                sb = new StringBuilder("remote pub ");
                sb.append(e2.getClass().getSimpleName());
                ho.c("OaidSettingsUtil", sb.toString());
                cy.a(cursor);
                return "";
            } catch (Throwable th) {
                sb = new StringBuilder("remote pub ");
                sb.append(th.getClass().getSimpleName());
                ho.c("OaidSettingsUtil", sb.toString());
                cy.a(cursor);
                return "";
            }
            if (!ao.b(context, uri)) {
                cy.a((Closeable) null);
                return "";
            }
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String string = cursor.getString(cursor.getColumnIndexOrThrow("pub_store_ks"));
                cy.a(cursor);
                return string;
            }
            cy.a(cursor);
            return "";
        } catch (Throwable th2) {
            cy.a(cursor);
            throw th2;
        }
    }

    public static String b(Context context) {
        StringBuilder sb;
        Uri uri;
        Cursor cursor = null;
        try {
            try {
                uri = f7647a;
            } catch (IllegalArgumentException e2) {
                sb = new StringBuilder("remote pub ");
                sb.append(e2.getClass().getSimpleName());
                ho.c("OaidSettingsUtil", sb.toString());
                cy.a(cursor);
                return "";
            } catch (Throwable th) {
                sb = new StringBuilder("remote pub ");
                sb.append(th.getClass().getSimpleName());
                ho.c("OaidSettingsUtil", sb.toString());
                cy.a(cursor);
                return "";
            }
            if (!ao.b(context, uri)) {
                cy.a((Closeable) null);
                return "";
            }
            cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String string = cursor.getString(cursor.getColumnIndexOrThrow("pub_store"));
                cy.a(cursor);
                return string;
            }
            cy.a(cursor);
            return "";
        } catch (Throwable th2) {
            cy.a(cursor);
            throw th2;
        }
    }

    public static Pair<String, Boolean> a(Context context) {
        String string;
        if (context == null) {
            return null;
        }
        try {
            string = Settings.Global.getString(context.getContentResolver(), "pps_oaid_c");
        } catch (Throwable th) {
            ho.c("OaidSettingsUtil", "exception happen: " + th.getClass().getSimpleName());
        }
        if (!TextUtils.isEmpty(string)) {
            ho.a("OaidSettingsUtil", "read and decrypt oaid.");
            return bx.a(context, string);
        }
        ContentResolver contentResolver = context.getContentResolver();
        String string2 = Settings.Global.getString(contentResolver, "pps_oaid");
        String string3 = Settings.Global.getString(contentResolver, "pps_track_limit");
        if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
            RSAPublicKey d2 = d(context);
            String a2 = cu.a(string2 + string3);
            boolean a3 = ch.a(context, a2, Settings.Global.getString(contentResolver, "pps_oaid_digest_pss"), d2);
            ho.a("OaidSettingsUtil", "verifySignPss result: %s", Boolean.valueOf(a3));
            if (!a3) {
                d = null;
                a3 = ch.a(a2, Settings.Global.getString(contentResolver, "pps_oaid_digest"), e(context));
                ho.a("OaidSettingsUtil", "verifySign result: %s", Boolean.valueOf(a3));
            }
            if (a3) {
                return new Pair<>(string2, Boolean.valueOf(Boolean.valueOf(string3).booleanValue()));
            }
            e = null;
            return null;
        }
        return null;
    }
}
