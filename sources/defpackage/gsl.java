package defpackage;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class gsl {
    public static int b(Context context) {
        int i = 0;
        if (context == null) {
            LogUtil.a("MmOpenApiCaller", "getMmApiLevel failed. context is null.");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.tencent.mm", 128);
            if (applicationInfo.metaData == null) {
                return 0;
            }
            i = applicationInfo.metaData.getInt("wechat_ext_api_level", 0);
            if (i > 0) {
                return i;
            }
            if (TextUtils.isEmpty(applicationInfo.metaData.getString("wechat_fun_support"))) {
                return 1;
            }
            return i;
        } catch (PackageManager.NameNotFoundException unused) {
            ReleaseLogUtil.a("R_MmOpenApiCaller", "getMmApiLevel failed. can't find weChart package.");
            return i;
        }
    }

    public static int b(Context context, String str, long j, long j2) {
        Cursor cursor;
        Uri parse;
        ContentProviderClient acquireUnstableContentProviderClient;
        if (context == null || str == null || str.isEmpty()) {
            return 20;
        }
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                parse = Uri.parse(String.format("content://com.tencent.mm.sdk.comm.provider/setWechatSportStep?appid=%s", str));
                LogUtil.c("MmOpenApiCaller", "uri:", parse.toString(), " time:", Long.valueOf(j), " step:", Long.valueOf(j2));
                acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(parse);
            } catch (Throwable th) {
                th = th;
            }
        } catch (RemoteException e) {
            e = e;
            cursor = null;
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        try {
            if (acquireUnstableContentProviderClient == null) {
                ReleaseLogUtil.c("R_MmOpenApiCaller", "setSportStep providerClient == null");
                aSY_(acquireUnstableContentProviderClient, null);
                return 20;
            }
            Cursor query = acquireUnstableContentProviderClient.query(parse, null, null, new String[]{Long.toString(j2), Long.toString(j), Integer.toString(1)}, null);
            int aSV_ = aSV_(query);
            aSY_(acquireUnstableContentProviderClient, query);
            return aSV_;
        } catch (RemoteException e3) {
            e = e3;
            cursor = null;
            contentProviderClient = acquireUnstableContentProviderClient;
            ReleaseLogUtil.c("R_MmOpenApiCaller", "setSportStep failed with remoteException: ", LogAnonymous.b((Throwable) e));
            aSY_(contentProviderClient, cursor);
            return 19;
        } catch (Exception e4) {
            e = e4;
            cursor = null;
            contentProviderClient = acquireUnstableContentProviderClient;
            ReleaseLogUtil.c("R_MmOpenApiCaller", "setSportStep failed with exception: ", LogAnonymous.b((Throwable) e));
            aSY_(contentProviderClient, cursor);
            return 19;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            contentProviderClient = acquireUnstableContentProviderClient;
            aSY_(contentProviderClient, cursor);
            throw th;
        }
    }

    private static int aSV_(Cursor cursor) {
        if (cursor == null) {
            return 16;
        }
        if (!cursor.moveToFirst()) {
            return 18;
        }
        if (cursor.getColumnCount() == 0) {
            return 17;
        }
        return cursor.getInt(cursor.getColumnIndex("retCode"));
    }

    public static c b(Context context, String str) {
        Cursor cursor;
        Uri parse;
        ContentProviderClient acquireUnstableContentProviderClient;
        c cVar = new c();
        if (context != null && str != null && !str.isEmpty()) {
            ContentProviderClient contentProviderClient = null;
            try {
                try {
                    parse = Uri.parse(String.format("content://com.tencent.mm.sdk.comm.provider/getWechatSportConfig?appid=%s", str));
                    acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(parse);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                } catch (RemoteException e) {
                    e = e;
                    cursor = null;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    cVar.f12910a = 19;
                    ReleaseLogUtil.c("R_MmOpenApiCaller", "setSportStep failed with remoteException: ", LogAnonymous.b((Throwable) e));
                    aSY_(contentProviderClient, cursor);
                    return cVar;
                } catch (Exception e2) {
                    e = e2;
                    cursor = null;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    cVar.f12910a = 19;
                    ReleaseLogUtil.c("R_MmOpenApiCaller", "setSportStep failed with exception: ", LogAnonymous.b((Throwable) e));
                    aSY_(contentProviderClient, cursor);
                    return cVar;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    aSY_(contentProviderClient, cursor);
                    throw th;
                }
            } catch (RemoteException e3) {
                e = e3;
                cursor = null;
            } catch (Exception e4) {
                e = e4;
                cursor = null;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
            if (acquireUnstableContentProviderClient == null) {
                ReleaseLogUtil.c("R_MmOpenApiCaller", "setSportStep providerClient == null");
                cVar.f12910a = 20;
                aSY_(acquireUnstableContentProviderClient, null);
                return cVar;
            }
            Cursor query = acquireUnstableContentProviderClient.query(parse, null, null, null, null);
            aSW_(query, cVar);
            aSY_(acquireUnstableContentProviderClient, query);
            return cVar;
        }
        cVar.f12910a = 20;
        return cVar;
    }

    private static void aSY_(ContentProviderClient contentProviderClient, Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                ReleaseLogUtil.c("R_MmOpenApiCaller", "release cursor failed with exception: ", LogAnonymous.b((Throwable) e));
            }
        }
        if (contentProviderClient != null) {
            try {
                contentProviderClient.close();
            } catch (Exception e2) {
                ReleaseLogUtil.c("R_MmOpenApiCaller", "release providerClient failed with exception: ", LogAnonymous.b((Throwable) e2));
            }
        }
    }

    private static void aSW_(Cursor cursor, c cVar) throws JSONException {
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                aSX_(cVar, cursor);
                return;
            } else {
                cVar.f12910a = 18;
                return;
            }
        }
        cVar.f12910a = 16;
    }

    private static void aSX_(c cVar, Cursor cursor) throws JSONException {
        int columnIndex;
        int columnIndex2 = cursor.getColumnIndex("retCode");
        if (columnIndex2 != -1) {
            cVar.f12910a = cursor.getInt(columnIndex2);
        } else {
            cVar.f12910a = 17;
        }
        if (cVar.f12910a == 1 && (columnIndex = cursor.getColumnIndex("sportConfig")) != -1) {
            String string = cursor.getString(columnIndex);
            b bVar = new b();
            JSONObject jSONObject = new JSONObject(string);
            bVar.e = jSONObject.getLong("version");
            bVar.b = jSONObject.getLong("interval");
            cVar.d = bVar;
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private int f12910a;
        private Object d;

        public int b() {
            return this.f12910a;
        }

        public Object c() {
            return this.d;
        }

        public String toString() {
            return "MmResult{retCode=" + this.f12910a + ", data=" + this.d + '}';
        }
    }

    public static class b {
        private long b;
        private long e;

        public long d() {
            return this.b;
        }
    }
}
