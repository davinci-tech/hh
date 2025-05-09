package defpackage;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

/* loaded from: classes8.dex */
public class aav {
    public static boolean a(Context context) {
        try {
            return (d(context, "syncFeatureSwitch") & 32) == 32;
        } catch (Exception e) {
            abd.b("BaseQueryHwCloud", "isSupportSyncDownloadPartical error: " + e.toString());
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            return (d(context, "syncFeatureSwitch") & 8) == 8;
        } catch (Exception e) {
            abd.b("BaseQueryHwCloud", "isSupportReportPrepare error: " + e.toString());
            return false;
        }
    }

    public static int d(Context context, String str) {
        try {
            if (context == null) {
                abd.d("BaseQueryHwCloud", "getSyncFeature context is null");
                return 0;
            }
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                abd.d("BaseQueryHwCloud", "getSyncFeature packageManager is null");
                return 0;
            }
            int i = packageManager.getApplicationInfo("com.huawei.hidisk", 128).metaData.getInt(str, 0);
            abd.c("BaseQueryHwCloud", "getSyncFeature key: " + str + " value: " + i);
            return i;
        } catch (Exception e) {
            abd.b("BaseQueryHwCloud", "getSyncFeature error: " + e.toString());
            return 0;
        }
    }

    public static int c(Context context) {
        try {
            if (context == null) {
                abd.d("BaseQueryHwCloud", "getCloudMateDateVersionCode context is null");
                return 0;
            }
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                abd.d("BaseQueryHwCloud", "getCloudMateDateVersionCode packageManager is null");
                return 0;
            }
            int i = packageManager.getApplicationInfo("com.huawei.hidisk", 128).metaData.getInt("com.huawei.hicloud.ability.version", 0);
            abd.c("BaseQueryHwCloud", "metata ability.version: " + i);
            return i;
        } catch (Exception e) {
            abd.b("BaseQueryHwCloud", "getCloudMateDateVersionCode: " + e.toString());
            return 0;
        }
    }

    public static int a(String str, String str2, Context context, Uri uri) {
        ContentProviderClient contentProviderClient;
        abd.c("BaseQueryHwCloud", "reportPreByProvider, syncType = " + str);
        if (context == null) {
            abd.b("BaseQueryHwCloud", "reportPreByProvider error: context is null");
            return 1;
        }
        if (TextUtils.isEmpty(str2)) {
            abd.b("BaseQueryHwCloud", "reportPreByProvider error: reportString is empty");
            return 1;
        }
        ContentProviderClient contentProviderClient2 = null;
        try {
            try {
                ContentResolver contentResolver = context.getContentResolver();
                if (contentResolver == null) {
                    abd.b("BaseQueryHwCloud", "reportPreByProvider error: contentResolver is null");
                    return 1;
                }
                contentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
                try {
                    if (contentProviderClient == null) {
                        abd.b("BaseQueryHwCloud", "Query hiCloud Login status error: cpClient is null");
                        if (contentProviderClient != null) {
                            contentProviderClient.release();
                        }
                        return 1;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("report_param", str2);
                    contentResolver.call(uri, "reportPrepare", (String) null, bundle);
                    if (contentProviderClient == null) {
                        return 0;
                    }
                    contentProviderClient.release();
                    return 0;
                } catch (Exception e) {
                    e = e;
                    contentProviderClient2 = contentProviderClient;
                    abd.b("BaseQueryHwCloud", "reportPreByProvider error: " + e.getClass().getName());
                    if (contentProviderClient2 != null) {
                        contentProviderClient2.release();
                    }
                    return 1;
                } catch (Throwable th) {
                    th = th;
                    if (contentProviderClient != null) {
                        contentProviderClient.release();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                contentProviderClient = null;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
