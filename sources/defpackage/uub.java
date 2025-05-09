package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.webkit.ProxyConfig;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.openid.appauth.browser.BrowserMatcher;

/* loaded from: classes7.dex */
public final class uub {

    /* renamed from: a, reason: collision with root package name */
    static final Intent f17560a = new Intent().setAction(CommonConstant.ACTION.HWID_SCHEME_URL).addCategory("android.intent.category.BROWSABLE").setData(Uri.fromParts("http", "", null));

    /* JADX WARN: Removed duplicated region for block: B:19:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<defpackage.uuc> d(android.content.Context r9) {
        /*
            android.content.pm.PackageManager r9 = r9.getPackageManager()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.content.Intent r1 = defpackage.uub.f17560a
            r2 = 0
            android.content.pm.ResolveInfo r3 = r9.resolveActivity(r1, r2)
            if (r3 == 0) goto L17
            android.content.pm.ActivityInfo r3 = r3.activityInfo
            java.lang.String r3 = r3.packageName
            goto L18
        L17:
            r3 = 0
        L18:
            r4 = 131136(0x20040, float:1.8376E-40)
            java.util.List r1 = r9.queryIntentActivities(r1, r4)
            java.util.Iterator r1 = r1.iterator()
        L23:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L79
            java.lang.Object r4 = r1.next()
            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
            boolean r5 = fgv_(r4)
            if (r5 != 0) goto L36
            goto L23
        L36:
            android.content.pm.ActivityInfo r5 = r4.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            java.lang.String r5 = r5.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            r6 = 64
            android.content.pm.PackageInfo r5 = r9.getPackageInfo(r5, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            android.content.pm.ActivityInfo r6 = r4.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            java.lang.String r6 = r6.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            boolean r6 = fgu_(r9, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            if (r6 == 0) goto L61
            uuc r6 = new uuc     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            r7 = 1
            r6.<init>(r5, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            android.content.pm.ActivityInfo r8 = r4.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            java.lang.String r8 = r8.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            boolean r8 = r8.equals(r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            if (r8 == 0) goto L5e
            r0.add(r2, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            goto L62
        L5e:
            r0.add(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
        L61:
            r7 = r2
        L62:
            uuc r6 = new uuc     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            r6.<init>(r5, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            android.content.pm.ActivityInfo r4 = r4.activityInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            java.lang.String r4 = r4.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            boolean r4 = r4.equals(r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            if (r4 == 0) goto L75
            r0.add(r7, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            goto L23
        L75:
            r0.add(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L23
            goto L23
        L79:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uub.d(android.content.Context):java.util.List");
    }

    public static uuc a(Context context, BrowserMatcher browserMatcher) {
        List<uuc> d = d(context);
        HashMap hashMap = new HashMap();
        uuc uucVar = null;
        for (uuc uucVar2 : d) {
            if (browserMatcher.matches(uucVar2)) {
                hashMap.put(uucVar2.d, uucVar2);
                if (uucVar2.b.booleanValue() || uucVar == null) {
                    uucVar = uucVar2;
                }
            }
        }
        return a(hashMap, uucVar, context);
    }

    private static uuc a(Map<String, uuc> map, uuc uucVar, Context context) {
        if (map.containsKey("com.android.chrome")) {
            return map.get("com.android.chrome");
        }
        if (map.containsKey(Constants.HW_BROWSER_PACKAGE)) {
            return map.get(Constants.HW_BROWSER_PACKAGE);
        }
        if (map.containsKey("com.android.browser")) {
            return map.get("com.android.browser");
        }
        String c = c(context);
        return ("".equals(c) || !map.containsKey(c)) ? uucVar : map.get(c);
    }

    private static String c(Context context) {
        ResolveInfo resolveActivity;
        return (context == null || (resolveActivity = context.getPackageManager().resolveActivity(f17560a, 65536)) == null || resolveActivity.activityInfo == null || "com.huawei.android.internal.app".equalsIgnoreCase(resolveActivity.activityInfo.packageName)) ? "" : resolveActivity.activityInfo.packageName;
    }

    private static boolean fgu_(PackageManager packageManager, String str) {
        Intent intent = new Intent();
        intent.setAction("android.support.customtabs.action.CustomTabsService");
        intent.setPackage(str);
        return packageManager.resolveService(intent, 0) != null;
    }

    private static boolean fgv_(ResolveInfo resolveInfo) {
        if (resolveInfo.filter == null || !resolveInfo.filter.hasAction(CommonConstant.ACTION.HWID_SCHEME_URL) || !resolveInfo.filter.hasCategory("android.intent.category.BROWSABLE") || resolveInfo.filter.schemesIterator() == null || resolveInfo.filter.authoritiesIterator() != null) {
            return false;
        }
        Iterator<String> schemesIterator = resolveInfo.filter.schemesIterator();
        boolean z = false;
        boolean z2 = false;
        while (schemesIterator.hasNext()) {
            String next = schemesIterator.next();
            z |= "http".equals(next);
            z2 |= ProxyConfig.MATCH_HTTPS.equals(next);
            if (z && z2) {
                return true;
            }
        }
        return false;
    }
}
