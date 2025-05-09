package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.IntervalMethods;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cp;

/* loaded from: classes5.dex */
public class bx {

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f7645a = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/oaid/query").build();
    private static final Uri b = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/oaid_scp/get").build();

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(Context context) {
        Object th;
        Cursor cursor;
        if (context == null) {
            return "";
        }
        try {
            fh.b(context).t(IntervalMethods.REQ_OAID_DECRYPT_KEY);
            cursor = ao.a(context, b);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        return cursor.getString(cursor.getColumnIndexOrThrow("op_wk"));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        ho.c("OaidProviderUtil", "get remote key ex: %s", th.getClass().getSimpleName());
                        return "";
                    } finally {
                        cy.a(cursor);
                    }
                }
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    public static boolean b(Context context) {
        return a(context, f7645a);
    }

    public static boolean a(Context context, Uri uri) {
        Integer b2;
        if (context == null || uri == null || (b2 = i.b(context)) == null || 30462100 > b2.intValue()) {
            return false;
        }
        return ao.b(context, uri);
    }

    public static Pair<String, Boolean> a(final Context context, String str) {
        String str2;
        if (context == null || !a(context, b) || TextUtils.isEmpty(str)) {
            return null;
        }
        final cp.a a2 = cp.a.a(context);
        String d = a2.d();
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.bx.1
            @Override // java.lang.Runnable
            public void run() {
                if (fh.b(context).b(IntervalMethods.REQ_OAID_DECRYPT_KEY)) {
                    ho.a("OaidProviderUtil", "within oaid key time interval.");
                    return;
                }
                String d2 = bx.d(context);
                if (ho.a()) {
                    ho.a("OaidProviderUtil", "async get remote key: %s", cz.g(d2));
                }
                a2.d(d2);
            }
        });
        if (TextUtils.isEmpty(d)) {
            str2 = "scp is empty";
        } else {
            String a3 = cp.a(str, d);
            if (!TextUtils.isEmpty(a3)) {
                return new Pair<>(a3, Boolean.valueOf(Constants.NIL_UUID.equalsIgnoreCase(a3)));
            }
            str2 = "decrypt oaid failed.";
        }
        ho.a("OaidProviderUtil", str2);
        return null;
    }

    public static Pair<String, Boolean> a(Context context) {
        Cursor cursor;
        if (context != null) {
            Uri uri = f7645a;
            if (a(context, uri)) {
                try {
                    cursor = ao.a(context, uri);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                int columnIndexOrThrow = cursor.getColumnIndexOrThrow("oaid");
                                int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("limit_track");
                                String string = cursor.getString(columnIndexOrThrow);
                                return new Pair<>(string, Boolean.valueOf(cz.e(string, Constants.NIL_UUID) ? true : Boolean.valueOf(cursor.getString(columnIndexOrThrow2)).booleanValue()));
                            }
                        } catch (Throwable th) {
                            th = th;
                            try {
                                ho.c("OaidProviderUtil", "query oaid via provider ex: %s", th.getClass().getSimpleName());
                                cy.a(cursor);
                                return new Pair<>(Constants.NIL_UUID, true);
                            } finally {
                                cy.a(cursor);
                            }
                        }
                    }
                    return new Pair<>(Constants.NIL_UUID, true);
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                }
            }
        }
        return new Pair<>(Constants.NIL_UUID, true);
    }
}
