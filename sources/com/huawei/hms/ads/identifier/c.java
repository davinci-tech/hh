package com.huawei.hms.ads.identifier;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import com.huawei.hms.ads.identifier.h;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.Closeable;

/* loaded from: classes9.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f4318a = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/oaid_scp/get").build();
    private static final Uri b = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/oaid/query").build();
    private static final Uri c = new Uri.Builder().scheme("content").authority("com.huawei.hwid.pps.apiprovider").path("/oaid/sync").build();

    /* JADX INFO: Access modifiers changed from: private */
    public static String g(Context context) {
        if (context == null) {
            return "";
        }
        try {
            Uri uri = f4318a;
            if (!a(context, uri)) {
                Log.w("InfoProviderUtil", "uri is invalid");
                return "";
            }
            Cursor query = context.getContentResolver().query(uri, null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("op_wk");
                int columnIndex = query.getColumnIndex("exem_cnt");
                String string = query.getString(columnIndexOrThrow);
                if (columnIndex >= 0) {
                    int i = query.getInt(columnIndex);
                    h.a a2 = h.a.a(context);
                    a2.a(i);
                    if (!TextUtils.isEmpty(string)) {
                        Log.d("InfoProviderUtil", "reset exemption record");
                        a2.d();
                    }
                }
                j.a(query);
                return string;
            }
            j.a(query);
            return "";
        } catch (Throwable th) {
            try {
                Log.w("InfoProviderUtil", "get remote key ex: " + th.getClass().getSimpleName());
                return "";
            } finally {
                j.a((Closeable) null);
            }
        }
    }

    public static boolean e(Context context) {
        return a(context, c);
    }

    public static boolean d(Context context) {
        return a(context, b);
    }

    public static AdvertisingIdClient.Info c(Context context) {
        if (!d(context)) {
            return new AdvertisingIdClient.Info(Constants.NIL_UUID, true);
        }
        try {
            Cursor query = context.getContentResolver().query(b, null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("oaid");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("limit_track");
                String string = query.getString(columnIndexOrThrow);
                AdvertisingIdClient.Info info = new AdvertisingIdClient.Info(string, Constants.NIL_UUID.equalsIgnoreCase(string) ? true : Boolean.valueOf(query.getString(columnIndexOrThrow2)).booleanValue());
                j.a(query);
                return info;
            }
            AdvertisingIdClient.Info info2 = new AdvertisingIdClient.Info(Constants.NIL_UUID, true);
            j.a(query);
            return info2;
        } catch (Throwable th) {
            try {
                Log.w("InfoProviderUtil", "query oaid via provider ex: " + th.getClass().getSimpleName());
                j.a((Closeable) null);
                return new AdvertisingIdClient.Info(Constants.NIL_UUID, true);
            } catch (Throwable th2) {
                j.a((Closeable) null);
                throw th2;
            }
        }
    }

    public static void b(Context context) {
        if (e(context)) {
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(c, null, null, new String[]{context.getApplicationContext().getPackageName(), String.valueOf(30474310)}, null);
            } finally {
                try {
                } finally {
                }
            }
            if (cursor != null && cursor.moveToFirst()) {
                Log.i("InfoProviderUtil", "sync sdk version result: " + cursor.getString(cursor.getColumnIndexOrThrow("sync_result")));
            }
        }
    }

    private static boolean a(Context context, Uri uri) {
        Integer b2;
        if (context == null || uri == null || (b2 = j.b(context)) == null || 30462100 > b2.intValue()) {
            return false;
        }
        return j.a(context, uri);
    }

    public static void a(final Context context, final e eVar) {
        if (context == null) {
            return;
        }
        Log.d("InfoProviderUtil", "refresh scp");
        final h.a a2 = h.a.a(context);
        if (TextUtils.isEmpty(a2.a())) {
            j.f4328a.execute(new Runnable() { // from class: com.huawei.hms.ads.identifier.c.3
                @Override // java.lang.Runnable
                public void run() {
                    String g = c.g(context);
                    Log.d("InfoProviderUtil", "get scp result:" + TextUtils.isEmpty(g));
                    a2.a(g);
                    e eVar2 = eVar;
                    if (eVar2 != null) {
                        eVar2.a();
                    }
                }
            });
        } else if (eVar != null) {
            eVar.a();
        }
    }

    public static AdvertisingIdClient.Info a(final Context context) {
        if (!a(context, f4318a)) {
            return null;
        }
        String string = Settings.Global.getString(context.getContentResolver(), "pps_oaid_c");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        final h.a a2 = h.a.a(context);
        String a3 = a2.a();
        if (!TextUtils.isEmpty(a3)) {
            String a4 = h.a(string, a3);
            if (!TextUtils.isEmpty(a4)) {
                return new AdvertisingIdClient.Info(a4, Constants.NIL_UUID.equalsIgnoreCase(a4));
            }
            Log.d("InfoProviderUtil", "decrypt oaid failed.");
            j.f4328a.execute(new Runnable() { // from class: com.huawei.hms.ads.identifier.c.2
                @Override // java.lang.Runnable
                public void run() {
                    a2.a(c.g(context));
                }
            });
            return null;
        }
        Log.d("InfoProviderUtil", "scp is empty");
        j.f4328a.execute(new Runnable() { // from class: com.huawei.hms.ads.identifier.c.1
            @Override // java.lang.Runnable
            public void run() {
                if (!h.a.this.f() && h.a.this.c()) {
                    Log.d("InfoProviderUtil", "within key update interval.");
                    return;
                }
                h.a.this.b();
                h.a.this.a(c.g(context));
            }
        });
        if (!a2.f()) {
            return new AdvertisingIdClient.Info(Constants.NIL_UUID, true);
        }
        Log.d("InfoProviderUtil", "update exemption record");
        a2.e();
        return null;
    }
}
