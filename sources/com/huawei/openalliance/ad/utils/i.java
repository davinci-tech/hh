package com.huawei.openalliance.ad.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import com.huawei.android.hms.ppskit.RemoteInstallReq;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.constant.IntentFailError;
import com.huawei.openalliance.ad.download.DownloadFileProvider;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.hsf.a;
import com.huawei.openalliance.ad.mu;
import com.huawei.openalliance.ad.ow;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.te;
import com.huawei.openalliance.ad.tf;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.net.URISyntaxException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, List<String>> f7704a;

    private static byte[] h(Context context, String str) {
        StringBuilder sb;
        PackageInfo packageInfo;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null && packageInfo.signatures.length > 0) {
                    ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
                    try {
                        byte[] encoded = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream2).getEncoded();
                        cy.a((Closeable) byteArrayInputStream2);
                        return encoded;
                    } catch (RuntimeException e2) {
                        e = e2;
                        byteArrayInputStream = byteArrayInputStream2;
                        sb = new StringBuilder("getPackageSignatureBytes RuntimeException:");
                        sb.append(e.getClass().getSimpleName());
                        ho.d("ApkUtil", sb.toString());
                        cy.a((Closeable) byteArrayInputStream);
                        ho.b("ApkUtil", "Failed to get application signature certificate fingerprint.");
                        return new byte[0];
                    } catch (Throwable th) {
                        th = th;
                        byteArrayInputStream = byteArrayInputStream2;
                        sb = new StringBuilder("getPackageSignatureBytes Exception:");
                        sb.append(th.getClass().getSimpleName());
                        ho.d("ApkUtil", sb.toString());
                        cy.a((Closeable) byteArrayInputStream);
                        ho.b("ApkUtil", "Failed to get application signature certificate fingerprint.");
                        return new byte[0];
                    }
                }
            } catch (Throwable th2) {
                cy.a((Closeable) byteArrayInputStream);
                throw th2;
            }
        } catch (RuntimeException e3) {
            e = e3;
        } catch (Throwable th3) {
            th = th3;
        }
        cy.a((Closeable) byteArrayInputStream);
        ho.b("ApkUtil", "Failed to get application signature certificate fingerprint.");
        return new byte[0];
    }

    public static boolean g(Context context, String str) {
        return a(f7704a.get(str), e(context, str));
    }

    public static int f(Context context, String str) {
        try {
            PackageInfo b2 = b(context, str);
            if (b2 == null) {
                return 0;
            }
            return b2.versionCode;
        } catch (AndroidRuntimeException | Exception unused) {
            ho.c("ApkUtil", "getAppVersionCode fail");
            return 0;
        }
    }

    public static String e(Context context, String str) {
        byte[] h = h(context, str);
        if (h == null || h.length == 0) {
            return null;
        }
        return an.a(cu.a(h));
    }

    public static String e(Context context) {
        if (a(context, "com.huawei.hwid")) {
            return "com.huawei.hwid";
        }
        return a(context, "com.huawei.hms") ? "com.huawei.hms" : a(context, "com.huawei.hwid.tv") ? "com.huawei.hwid.tv" : "com.huawei.hwid";
    }

    public static String d(Context context) {
        String e2 = e(context);
        if (TextUtils.isEmpty(e2)) {
            return null;
        }
        if (a(f7704a.get(e2), e(context, e2))) {
            return e2;
        }
        return null;
    }

    public static ApplicationInfo d(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getApplicationInfo(str, 128);
            }
            ho.b("ApkUtil", "pm is null");
            return null;
        } catch (Throwable th) {
            ho.c("ApkUtil", "getApplicationInfo " + th.getClass().getSimpleName());
            return null;
        }
    }

    public static boolean c(Context context) {
        return !TextUtils.isEmpty(d(context));
    }

    public static String c(Context context, String str) {
        String str2;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return a(packageManager, str);
            }
            return null;
        } catch (RuntimeException unused) {
            str2 = "getAppName RuntimeException";
            ho.c("ApkUtil", str2);
            return null;
        } catch (Exception unused2) {
            str2 = "getAppName Exception";
            ho.c("ApkUtil", str2);
            return null;
        }
    }

    public static String b(Context context, Intent intent) {
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            return null;
        }
        return resolveActivity.activityInfo.packageName;
    }

    public static Integer b(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(e(context), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("ppskit_ver_code")) == null) {
                return null;
            }
            String obj2 = obj.toString();
            if (ho.a()) {
                ho.a("ApkUtil", "ppsKitVerCode:%s", obj2);
            }
            return cz.h(obj2);
        } catch (Throwable th) {
            ho.b("ApkUtil", "getPpsKitVerCode ex: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public static PackageInfo b(Context context, String str) {
        String str2;
        if (ho.a()) {
            ho.a("ApkUtil", "getPackageInfo, packageName:%s", str);
        }
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageInfo(str, 128);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            str2 = "getPackageInfo NameNotFoundException";
            ho.c("ApkUtil", str2);
            return null;
        } catch (Throwable unused2) {
            str2 = "getPackageInfo Exception";
            ho.c("ApkUtil", str2);
            return null;
        }
    }

    public static Intent b(Context context, String str, String str2, tf tfVar) {
        String str3;
        try {
        } catch (ActivityNotFoundException unused) {
            cl.a(context, tfVar, IntentFailError.ACTIVITY_NOT_FOUND_EXP);
            str3 = "parseAndCheckIntent, activity not exist";
            ho.c("ApkUtil", str3);
            return null;
        } catch (URISyntaxException unused2) {
            cl.a(context, tfVar, IntentFailError.PARSE_INTENT_ERROR);
            str3 = "parseAndCheckIntent, parse uri fail";
            ho.c("ApkUtil", str3);
            return null;
        } catch (Exception e2) {
            cl.a(context, tfVar, "unknown exception:" + e2.getClass().getSimpleName());
            str3 = "handle intent url fail";
            ho.c("ApkUtil", str3);
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            cl.a(context, tfVar, IntentFailError.INTENT_URI_EMPTY);
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            boolean z = true;
            Intent parseUri = Intent.parseUri(Uri.decode(str), 1);
            if (!a(parseUri, str2)) {
                cl.a(context, tfVar, IntentFailError.NOT_SAME_PACKAGE);
                return null;
            }
            if (!TextUtils.isEmpty(str2)) {
                parseUri.setPackage(str2);
            }
            if (parseUri.getData() != null) {
                parseUri = parseUri.setDataAndTypeAndNormalize(parseUri.getData(), parseUri.getType());
            }
            ApplicationInfo d2 = d(context, context.getApplicationContext().getPackageName());
            if (Build.VERSION.SDK_INT < 30 || d2 == null || d2.targetSdkVersion < 30 || cd.a(context, "android.permission.QUERY_ALL_PACKAGES")) {
                z = false;
            } else {
                ho.a("ApkUtil", "has no QUERY_ALL_PACKAGES permission");
                cl.a(context, tfVar, IntentFailError.NO_PERMISSION);
            }
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(parseUri, 0);
            if (bg.a(queryIntentActivities)) {
                if (z) {
                    return null;
                }
                ho.c("ApkUtil", "can not find the activity");
                cl.a(context, tfVar, IntentFailError.ACTIVITY_NOT_FOUND);
                return null;
            }
            if (!a(queryIntentActivities)) {
                ho.c("ApkUtil", "parseAndCheckIntent, activity not exists or not exported.");
                cl.a(context, tfVar, IntentFailError.ACTIVITY_NOT_EXPORTED);
                return null;
            }
            if (fh.b(context).a(parseUri.getPackage())) {
                a(parseUri);
            }
            if (!queryIntentActivities.isEmpty()) {
                return parseUri;
            }
        } else {
            cl.a(context, tfVar, IntentFailError.CAN_NOT_GET_PKG_MANAGER);
        }
        return null;
    }

    private static boolean a(List<String> list, String str) {
        if (list == null || TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(List<ResolveInfo> list) {
        if (bg.a(list)) {
            return false;
        }
        Iterator<ResolveInfo> it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (!it.next().activityInfo.exported) {
                z = false;
            }
        }
        return z;
    }

    public static boolean a(String str) {
        return "com.huawei.hwid".equals(str) || "com.huawei.hms".equals(str) || "com.huawei.hwid.tv".equals(str);
    }

    public static boolean a(Intent intent, String str) {
        ComponentName component;
        if (intent != null && !TextUtils.isEmpty(str) && (component = intent.getComponent()) != null) {
            String packageName = component.getPackageName();
            if (!TextUtils.isEmpty(packageName) && !str.equalsIgnoreCase(packageName)) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Context context, String str, String str2, tf tfVar) {
        ho.b("ApkUtil", "openApp intent");
        try {
        } catch (ActivityNotFoundException unused) {
            ho.c("ApkUtil", "activity not exist");
            cl.a(context, tfVar, IntentFailError.ACTIVITY_NOT_FOUND_EXP);
        } catch (Exception e2) {
            cl.a(context, tfVar, "unknown exception:" + e2.getClass().getSimpleName());
            ho.c("ApkUtil", "handle intent url fail");
        }
        if (context.getPackageManager() == null) {
            cl.a(context, tfVar, IntentFailError.CAN_NOT_GET_PKG_MANAGER);
            return false;
        }
        Intent b2 = b(context, str2, str, tfVar);
        if (b2 == null) {
            return false;
        }
        b2.addFlags(268435456);
        if (tfVar != null) {
            tfVar.a(b2);
        }
        a(context, b2, tfVar);
        return true;
    }

    public static boolean a(Context context, String str, tf tfVar) {
        ho.b("ApkUtil", "open app main page");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            cl.a(context, tfVar, IntentFailError.DO_OPEN_MAIN_PAGE_GET_INTENT_ERROR);
            return false;
        }
        launchIntentForPackage.addFlags(268435456);
        launchIntentForPackage.setPackage(str);
        if (tfVar != null) {
            tfVar.b(null);
        }
        a(context, launchIntentForPackage, tfVar);
        return true;
    }

    public static boolean a(Context context, String str) {
        return b(context, str) != null;
    }

    public static void a(AppDownloadTask appDownloadTask, EventType eventType, int i) {
        qq C;
        if (appDownloadTask == null || (C = appDownloadTask.C()) == null) {
            return;
        }
        if (EventType.APPINSTALLSTART == eventType) {
            C.b(Integer.valueOf(i), appDownloadTask.F(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        } else if (EventType.APPINSTALLFAIL == eventType) {
            C.c(Integer.valueOf(i), appDownloadTask.F(), appDownloadTask.O(), appDownloadTask.P(), appDownloadTask.G());
        }
    }

    public static void a(Intent intent) {
        intent.addFlags(536870912);
        intent.addFlags(32768);
        ho.b("ApkUtil", "addFlagsToIntent: Success");
    }

    public static void a(Context context, String str, String str2, ow.a aVar) {
        String str3;
        Uri fromFile;
        ho.a("ApkUtil", "installApkViaHsf, packageName: %s", str2);
        d dVar = new d(aVar, context, str, str2);
        String str4 = Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE;
        PackageInfo b2 = b(context, Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE);
        if (b2 == null) {
            str4 = Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE_NEW;
            b2 = b(context, Constants.REMOTE_APP_INSTALL_HOST_HSF_PACKAGE_NEW);
        }
        if (b2 == null) {
            dVar.a();
            return;
        }
        try {
            if (b2.versionCode >= 90000300) {
                fromFile = DownloadFileProvider.a(context, context.getPackageName() + ".hiad.fileprovider", new File(str));
            } else {
                fromFile = Uri.fromFile(new File(str));
            }
            context.getApplicationContext().grantUriPermission(str4, fromFile, 1);
            com.huawei.openalliance.ad.hsf.a a2 = com.huawei.openalliance.ad.hsf.a.a(context);
            if (aVar != null) {
                aVar.a();
            }
            a2.a(str2, fromFile.toString(), dVar);
        } catch (IllegalArgumentException unused) {
            str3 = "installApkViaHsf IllegalArgumentException";
            ho.c("ApkUtil", str3);
            dVar.a();
        } catch (Exception e2) {
            str3 = "installApkViaHsf " + e2.getClass().getSimpleName();
            ho.c("ApkUtil", str3);
            dVar.a();
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f7705a;
        private String b;

        public int hashCode() {
            String str = this.f7705a;
            return str != null ? str.hashCode() : super.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof a)) {
                a aVar = (a) obj;
                String str = this.f7705a;
                if (str != null && str.equals(aVar.f7705a)) {
                    return true;
                }
            }
            return false;
        }

        public String b() {
            return this.b;
        }

        public String a() {
            return this.f7705a;
        }
    }

    public static void a(Context context, String str, String str2) {
        String str3;
        ho.a("ApkUtil", "installApkManually, packageName: %s", str2);
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
            if (a(context, Constants.SYSTEM_PKG_INSTALLER)) {
                intent.setPackage(Constants.SYSTEM_PKG_INSTALLER);
            }
            Uri a2 = DownloadFileProvider.a(context, context.getPackageName() + ".hiad.fileprovider", new File(str));
            intent.addFlags(1);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.addFlags(32768);
            intent.setDataAndType(a2, "application/vnd.android.package-archive");
            if (!dd.b(context, intent)) {
                ho.b("ApkUtil", "system package installer is unavailable");
                intent.setPackage(null);
            }
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            str3 = "installApkManually ActivityNotFoundException";
            ho.c("ApkUtil", str3);
        } catch (Exception e2) {
            str3 = "installApkManually " + e2.getClass().getSimpleName();
            ho.c("ApkUtil", str3);
        }
    }

    static abstract class b {

        /* renamed from: a, reason: collision with root package name */
        private final ow.a f7706a;
        private final Context b;
        private final String c;
        private final String d;

        protected abstract int d();

        void a(final int i) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.i.b.1
                @Override // java.lang.Runnable
                public void run() {
                    ho.a("ApkUtil", "App install failed, reason :%d", Integer.valueOf(i));
                    AppDownloadTask a2 = com.huawei.openalliance.ad.download.app.e.h().a(b.this.d);
                    if (a2 != null) {
                        qq C = a2.C();
                        if (C != null) {
                            C.c(Integer.valueOf(b.this.d()), a2.F(), a2.O(), a2.P(), a2.G());
                        }
                        if (5 == i && com.huawei.openalliance.ad.download.e.INSTALLING == a2.i()) {
                            if (b.this.f7706a != null) {
                                b.this.f7706a.a(i);
                                return;
                            }
                            return;
                        }
                    }
                    if (1 == i) {
                        if (b.this.f7706a != null) {
                            b.this.f7706a.a(i);
                        }
                    } else {
                        b bVar = b.this;
                        bVar.a(bVar.d, b.this.c, a2, b.this.f7706a);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, String str2, AppDownloadTask appDownloadTask, ow.a aVar) {
            if (aVar != null) {
                aVar.b();
            }
            if (appDownloadTask != null) {
                i.a(appDownloadTask, EventType.APPINSTALLSTART, 2);
                appDownloadTask.d(2);
            }
            i.a(this.b, str2, str);
        }

        b(ow.a aVar, Context context, String str, String str2) {
            this.f7706a = aVar;
            this.b = context;
            this.c = str;
            this.d = str2;
        }
    }

    public static void a(Context context, RemoteInstallReq remoteInstallReq, String str, String str2, ow.a aVar) {
        String simpleName;
        ho.a("ApkUtil", "installApkViaHMS, packageName: %s", str2);
        e eVar = new e(aVar, context, str, str2);
        if (b(context, e(context)) == null) {
            eVar.a("HMS is not found");
            return;
        }
        try {
            Uri a2 = DownloadFileProvider.a(context, context.getPackageName() + ".hiad.fileprovider", new File(str));
            context.getApplicationContext().grantUriPermission(e(context), a2, 1);
            a(context, remoteInstallReq, a2, aVar, eVar);
        } catch (IllegalArgumentException unused) {
            ho.c("ApkUtil", "installApkViaHMS IllegalArgumentException");
            simpleName = "IllegalArgumentException";
            eVar.a(simpleName);
        } catch (Exception e2) {
            ho.c("ApkUtil", "installApkViaHMS " + e2.getClass().getSimpleName());
            simpleName = e2.getClass().getSimpleName();
            eVar.a(simpleName);
        }
    }

    private static void a(Context context, RemoteInstallReq remoteInstallReq, Uri uri, ow.a aVar, e eVar) {
        ho.b("ApkUtil", "installViaHmsAidl");
        mu a2 = mu.a(context);
        if (aVar != null) {
            aVar.a();
        }
        a2.a(remoteInstallReq, uri, eVar);
    }

    public static void a(Context context, Intent intent, tf tfVar) {
        if (tfVar != null && cz.b(tfVar.d())) {
            tfVar.a(b(context, intent));
        }
        if (context == null || intent == null) {
            return;
        }
        try {
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
            te.a(context, tfVar);
        } catch (Throwable th) {
            ho.c("ApkUtil", "start activity error");
            new com.huawei.openalliance.ad.analysis.c(context).a(tfVar, th.getMessage());
        }
    }

    static class d extends b implements a.b {
        @Override // com.huawei.openalliance.ad.utils.i.b
        protected int d() {
            return 3;
        }

        @Override // com.huawei.openalliance.ad.hsf.a.b
        public void c() {
            ho.b("HsfPackageInstallResult", "onInstallFailed");
            a(4);
        }

        @Override // com.huawei.openalliance.ad.hsf.a.b
        public void b() {
            ho.b("HsfPackageInstallResult", "onInstallSuccess");
        }

        @Override // com.huawei.openalliance.ad.hsf.a.b
        public void a() {
            ho.b("HsfPackageInstallResult", "onServiceBindFail");
            a(2);
        }

        d(ow.a aVar, Context context, String str, String str2) {
            super(aVar, context, str, str2);
        }
    }

    public static Set<a> a(Context context, Intent intent) {
        StringBuilder sb;
        String sb2;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                if (!a(queryIntentActivities)) {
                    ho.c("ApkUtil", "queryIntentActivities, activity not exists or not exported.");
                    return null;
                }
                if (!queryIntentActivities.isEmpty()) {
                    HashSet hashSet = new HashSet();
                    for (ResolveInfo resolveInfo : queryIntentActivities) {
                        if (resolveInfo != null && resolveInfo.activityInfo != null) {
                            a aVar = new a();
                            aVar.f7705a = resolveInfo.activityInfo.packageName;
                            if (!TextUtils.isEmpty(aVar.f7705a)) {
                                aVar.b = a(packageManager, aVar.f7705a);
                                hashSet.add(aVar);
                            }
                        }
                    }
                    return hashSet;
                }
            }
        } catch (ActivityNotFoundException unused) {
            sb2 = "queryIntentActivities, activity not exist";
            ho.c("ApkUtil", sb2);
            return null;
        } catch (RuntimeException e2) {
            e = e2;
            sb = new StringBuilder("queryIntentActivities RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            sb2 = sb.toString();
            ho.c("ApkUtil", sb2);
            return null;
        } catch (Exception e3) {
            e = e3;
            sb = new StringBuilder("queryIntentActivities Exception:");
            sb.append(e.getClass().getSimpleName());
            sb2 = sb.toString();
            ho.c("ApkUtil", sb2);
            return null;
        }
        return null;
    }

    static class e extends mu.a {

        /* renamed from: a, reason: collision with root package name */
        private c f7708a;

        @Override // com.huawei.android.hms.ppskit.c
        public void a(boolean z, int i) {
            ho.b("ApkUtil", "HMS - onInstallResult: " + z + " reason: " + i);
            if (z) {
                return;
            }
            this.f7708a.a(i);
        }

        @Override // com.huawei.openalliance.ad.mu.a
        public void a(String str) {
            c cVar;
            int i;
            ho.c("ApkUtil", "HMS - onServiceCallFailed: " + str);
            if (Constants.FAIL_REASON_SERVICE_DISCONNET.equals(str)) {
                cVar = this.f7708a;
                i = 5;
            } else {
                cVar = this.f7708a;
                i = 2;
            }
            cVar.a(i);
        }

        e(ow.a aVar, Context context, String str, String str2) {
            this.f7708a = new c(aVar, context, str, str2);
        }
    }

    private static String a(PackageManager packageManager, String str) {
        String str2;
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                return packageManager.getApplicationLabel(applicationInfo).toString();
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            str2 = "getAppName NameNotFoundException";
            ho.c("ApkUtil", str2);
            return null;
        } catch (Exception e2) {
            str2 = "getAppName Exception:" + e2.getClass().getSimpleName();
            ho.c("ApkUtil", str2);
            return null;
        }
    }

    public static String a(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            str = "getPackageInfo NameNotFoundException";
            ho.c("ApkUtil", str);
            return null;
        } catch (Throwable unused2) {
            str = "getPackageInfo Exception";
            ho.c("ApkUtil", str);
            return null;
        }
        return null;
    }

    static class c extends b {
        @Override // com.huawei.openalliance.ad.utils.i.b
        protected int d() {
            return 4;
        }

        c(ow.a aVar, Context context, String str, String str2) {
            super(aVar, context, str, str2);
        }
    }

    public static PackageInfo a(PackageManager packageManager, String str, int i) {
        if (packageManager == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return packageManager.getPackageInfo(str, i);
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f7704a = hashMap;
        hashMap.put("com.huawei.hwid", Arrays.asList("b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05"));
        hashMap.put("com.huawei.hms", Arrays.asList("e49d5c2c0e11b3b1b96ca56c6de2a14ec7dab5ccc3b5f300d03e5b4dba44f539"));
        hashMap.put("com.huawei.hwid.tv", Arrays.asList("3517262215d8d3008cbf888750b6418edc4d562ac33ed6874e0d73aba667bc3c"));
    }
}
