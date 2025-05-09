package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class j {
    private static boolean b(AppInfo appInfo, Integer num) {
        if (appInfo == null) {
            return false;
        }
        return appInfo.a(num);
    }

    private static boolean b(AppInfo appInfo) {
        if (appInfo == null) {
            return true;
        }
        return appInfo.isCheckSha256() && TextUtils.isEmpty(appInfo.getSha256());
    }

    public static int b(String str, AppInfo appInfo) {
        if (appInfo == null || cz.b(str)) {
            return 1;
        }
        if (a(appInfo)) {
            return 3;
        }
        return a(str) ? 2 : 1;
    }

    private static boolean a(String str) {
        return Constants.FAST_APP_PKG.equalsIgnoreCase(str) || Constants.FAST_APP_DEV_PKG.equalsIgnoreCase(str);
    }

    public static boolean a(AppInfo appInfo, Integer num) {
        String str;
        if (appInfo == null) {
            str = " download app info is empty";
        } else if (TextUtils.isEmpty(appInfo.getPackageName())) {
            str = "app packageName is empty";
        } else {
            if ("11".equals(appInfo.getPriorInstallWay()) || b(appInfo, num) || a(appInfo) || (!TextUtils.isEmpty(appInfo.getDownloadUrl()) && !b(appInfo) && appInfo.getFileSize() > 0)) {
                return false;
            }
            str = " download app info is invalid";
        }
        ho.b("AppDownloadUtils", str);
        return true;
    }

    public static boolean a(AppInfo appInfo) {
        return (appInfo == null || TextUtils.isEmpty(appInfo.H())) ? false : true;
    }

    public static String a(String str, String str2) {
        return (TextUtils.isEmpty(str) || (!(dd.g() && "zh-CN".equalsIgnoreCase(d.c())) && dd.g())) ? str2 : str;
    }

    public static String a(String str, AppInfo appInfo) {
        return (appInfo == null || cz.b(str)) ? "app" : a(appInfo) ? ClickDestination.APPMARKET : a(str) ? ClickDestination.FASTAPP : "app";
    }

    private static String a(Context context, AppInfo appInfo, String str, int i) {
        return !TextUtils.isEmpty(appInfo.l()) ? appInfo.l() : a(str, context.getString(i));
    }

    public static String a(Context context, AppInfo appInfo, int i) {
        int i2;
        if (context == null || appInfo == null) {
            return "";
        }
        String j = appInfo.j();
        if (!a(appInfo)) {
            i2 = R.string._2130851084_res_0x7f02350c;
        } else {
            if (i == 1) {
                return context.getString(R.string._2130851010_res_0x7f0234c2);
            }
            i2 = R.string._2130851009_res_0x7f0234c1;
        }
        return a(context, appInfo, j, i2);
    }

    /* renamed from: com.huawei.openalliance.ad.utils.j$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7709a;

        static {
            int[] iArr = new int[com.huawei.openalliance.ad.download.e.values().length];
            f7709a = iArr;
            try {
                iArr[com.huawei.openalliance.ad.download.e.FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7709a[com.huawei.openalliance.ad.download.e.WAITING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7709a[com.huawei.openalliance.ad.download.e.DOWNLOADING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7709a[com.huawei.openalliance.ad.download.e.IDLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7709a[com.huawei.openalliance.ad.download.e.DOWNLOADED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7709a[com.huawei.openalliance.ad.download.e.INSTALLING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7709a[com.huawei.openalliance.ad.download.e.INSTALLED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7709a[com.huawei.openalliance.ad.download.e.WAITING_FOR_WIFI.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static String a(Context context, AppInfo appInfo) {
        if (context == null || appInfo == null) {
            return "";
        }
        if (TextUtils.isEmpty(appInfo.k())) {
            return a(appInfo.i(), context.getString("11".equals(appInfo.getPriorInstallWay()) ? appInfo.A() == 1 ? R.string._2130851130_res_0x7f02353a : R.string._2130851081_res_0x7f023509 : R.string._2130851076_res_0x7f023504));
        }
        return appInfo.k();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0026, code lost:
    
        if (r2.l() > 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.openalliance.ad.download.app.AppStatus a(com.huawei.openalliance.ad.download.app.AppDownloadTask r2) {
        /*
            com.huawei.openalliance.ad.download.e r0 = r2.i()
            int[] r1 = com.huawei.openalliance.ad.utils.j.AnonymousClass1.f7709a
            int r0 = r0.ordinal()
            r0 = r1[r0]
            switch(r0) {
                case 1: goto L22;
                case 2: goto L1f;
                case 3: goto L1c;
                case 4: goto L28;
                case 5: goto L19;
                case 6: goto L16;
                case 7: goto L13;
                case 8: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L2b
        L10:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.WAITING_FOR_WIFI
            goto L2d
        L13:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.INSTALLED
            goto L2d
        L16:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.INSTALLING
            goto L2d
        L19:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.INSTALL
            goto L2d
        L1c:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.DOWNLOADING
            goto L2d
        L1f:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.WAITING
            goto L2d
        L22:
            int r2 = r2.l()
            if (r2 <= 0) goto L2b
        L28:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.PAUSE
            goto L2d
        L2b:
            com.huawei.openalliance.ad.download.app.AppStatus r2 = com.huawei.openalliance.ad.download.app.AppStatus.DOWNLOAD
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.j.a(com.huawei.openalliance.ad.download.app.AppDownloadTask):com.huawei.openalliance.ad.download.app.AppStatus");
    }
}
