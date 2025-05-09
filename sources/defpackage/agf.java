package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hmf.tasks.TaskCompletionSource;
import java.util.Locale;

/* loaded from: classes3.dex */
public class agf {
    private static boolean hE_(Context context, Uri uri, String str) {
        if (uri == null || TextUtils.isEmpty(str)) {
            return false;
        }
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(uri.getAuthority(), 0);
        if (resolveContentProvider == null) {
            Log.w("MarketHomeCountryByProvider", "isProviderValid, invalid provider: " + uri);
            return false;
        }
        ApplicationInfo applicationInfo = resolveContentProvider.applicationInfo;
        if (applicationInfo == null || !TextUtils.equals(str, applicationInfo.packageName)) {
            return false;
        }
        Log.i("MarketHomeCountryByProvider", "valid provider uri = " + uri);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void e(com.huawei.hmf.tasks.TaskCompletionSource<java.lang.String> r11, android.content.Context r12, java.lang.String r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.agf.e(com.huawei.hmf.tasks.TaskCompletionSource, android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static void c(TaskCompletionSource<String> taskCompletionSource, Context context, String str) {
        Exception exc;
        String d = age.d(context);
        if (d == null) {
            Log.w("MarketHomeCountryByProvider", "get verify market package name is null");
            d(context);
            exc = new Exception("get verify market package name is null");
        } else {
            String format = String.format(Locale.ROOT, "content://%s.commondata/item/1", d);
            if (hE_(context, Uri.parse(format), d)) {
                e(taskCompletionSource, context, format, str);
                return;
            } else {
                Log.w("MarketHomeCountryByProvider", "homeCountry uri is invalid");
                d(context);
                exc = new Exception("homeCountry uri is invalid");
            }
        }
        taskCompletionSource.setException(exc);
    }

    private static void d(Context context) {
        agc.b(context).a(null);
        agc.b(context).a();
    }
}
