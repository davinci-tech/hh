package defpackage;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class jki {
    private static final Object b = new Object();
    private static jki d = new jki();

    private jki() {
    }

    public static jki d() {
        jki jkiVar;
        synchronized (b) {
            if (d == null) {
                d = new jki();
            }
            jkiVar = d;
        }
        return jkiVar;
    }

    public static void c(final Context context, final boolean z) {
        LogUtil.a("SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear enter:");
        if (context == null) {
            ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear context is null");
            return;
        }
        DeviceInfo d2 = jpt.d("SyncHwMusicAccountUtils");
        if (d2 == null) {
            ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear, deviceInfo is null.");
            return;
        }
        DeviceCapability e = cvs.e(d2.getDeviceIdentify());
        if (e == null || !e.isSupportMusicInfoList()) {
            ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear, isn't Support Music.");
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: jkf
            @Override // java.lang.Runnable
            public final void run() {
                jki.c();
            }
        });
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: jke
            @Override // java.lang.Runnable
            public final void run() {
                jki.d(context, countDownLatch, z);
            }
        });
        try {
            LogUtil.a("SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear isOnTime:", Boolean.valueOf(countDownLatch.await(3L, TimeUnit.SECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.h("SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear InterruptedException");
        }
    }

    static /* synthetic */ void c() {
        a("com.huawei.music");
        a("com.android.mediacenter");
    }

    static /* synthetic */ void d(Context context, CountDownLatch countDownLatch, boolean z) {
        ContentResolver contentResolver = context.getContentResolver();
        ContentProviderClient contentProviderClient = null;
        try {
            try {
            } catch (RuntimeException unused) {
                LogUtil.b("SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear DeadObjectException");
                if (0 == 0) {
                    return;
                }
            }
            if (contentResolver == null) {
                ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear: resolver is null");
                return;
            }
            contentProviderClient = contentResolver.acquireUnstableContentProviderClient(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"));
            countDownLatch.countDown();
            if (contentProviderClient == null) {
                ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear, client is null.");
                if (contentProviderClient != null) {
                    contentProviderClient.close();
                    return;
                }
                return;
            }
            bHI_(context, z, contentResolver);
            if (contentProviderClient == null) {
                return;
            }
            contentProviderClient.close();
        } catch (Throwable th) {
            if (0 != 0) {
                contentProviderClient.close();
            }
            throw th;
        }
    }

    private static void bHI_(Context context, boolean z, ContentResolver contentResolver) {
        Bundle call = contentResolver.call(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"), "getVIPInfo", (String) null, (Bundle) null);
        if (call != null && !call.isEmpty()) {
            bHH_(context, call);
        } else {
            ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear: bundle is null");
        }
        if (!z) {
            LogUtil.a("SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear: hasGetHistroy is false.");
        } else if (contentResolver.call(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"), "recentlyPlayChanged", (String) null, (Bundle) null) != null) {
            jjj.d(false);
            LogUtil.a("SyncHwMusicAccountUtils", "notify music app get history record success.");
        }
    }

    private static void a(String str) {
        LogUtil.a("SyncHwMusicAccountUtils", "applyForMusicResource enter. musicPackageName: ", str);
        PowerKitManager.e().e(str, 100, "user-getHwMusicAccountSendToWear");
        PowerKitManager.e().a(str, 100, "user-getHwMusicAccountSendToWear");
    }

    private static void bHH_(Context context, Bundle bundle) {
        LogUtil.a("SyncHwMusicAccountUtils", " getHwMusicAccountSendToWear dealAccountInfoAction enter");
        String string = bundle.getString(JsbMapKeyNames.H5_USER_ID);
        String string2 = bundle.getString("validPeriod");
        String string3 = bundle.getString("musicAppType");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", " dealAccountInfoAction bundle value is invaliad!");
            return;
        }
        try {
            int parseInt = Integer.parseInt(string2);
            int parseInt2 = Integer.parseInt(string3);
            jjz jjzVar = new jjz();
            jjzVar.a(parseInt2);
            jjzVar.b(parseInt);
            jjzVar.d(string);
            List<DeviceInfo> b2 = jpt.b("SyncHwMusicAccountUtils");
            if (b2 == null || b2.size() == 0) {
                ReleaseLogUtil.d("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear has no ConnectDevices");
                return;
            }
            Iterator<DeviceInfo> it = b2.iterator();
            while (it.hasNext()) {
                DeviceCapability e = cvs.e(it.next().getDeviceIdentify());
                if (e != null && e.isSupportMusicInfoList()) {
                    jjj.b(jjzVar);
                    ReleaseLogUtil.e("R_SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear when succeed");
                }
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("SyncHwMusicAccountUtils", "getHwMusicAccountSendToWear NumberFormatException");
        }
    }

    public static void c(Context context) {
        List<DeviceInfo> b2 = jpt.b("SyncHwMusicAccountUtils");
        if (b2 == null || b2.size() == 0) {
            LogUtil.h("SyncHwMusicAccountUtils", "notifyHistoryRecord no connect device.");
            return;
        }
        Iterator<DeviceInfo> it = b2.iterator();
        while (it.hasNext()) {
            DeviceCapability e = cvs.e(it.next().getDeviceIdentify());
            if (e != null && e.isSupportMusicInfoList()) {
                e(context);
                LogUtil.a("SyncHwMusicAccountUtils", "notifyHistoryRecord when succeed");
            }
        }
    }

    private static void e(Context context) {
        ContentProviderClient contentProviderClient;
        LogUtil.a("SyncHwMusicAccountUtils", "notifyMusicApp enter.");
        a("com.huawei.music");
        a("com.android.mediacenter");
        ContentProviderClient contentProviderClient2 = null;
        ContentResolver contentResolver = context != null ? context.getContentResolver() : null;
        try {
            try {
                if (contentResolver != null) {
                    contentProviderClient = contentResolver.acquireUnstableContentProviderClient(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"));
                    try {
                        if (contentProviderClient == null) {
                            LogUtil.h("SyncHwMusicAccountUtils", "notifyMusicApp, client is null.");
                            if (contentProviderClient != null) {
                                contentProviderClient.close();
                                return;
                            }
                            return;
                        }
                        if (contentResolver.call(Uri.parse("content://com.android.mediacenter.healthprovider/InfoForWatch"), "recentlyPlayChanged", (String) null, (Bundle) null) != null) {
                            LogUtil.a("SyncHwMusicAccountUtils", "notifyMusicApp success.");
                        }
                        contentProviderClient2 = contentProviderClient;
                    } catch (RuntimeException unused) {
                        contentProviderClient2 = contentProviderClient;
                        LogUtil.b("SyncHwMusicAccountUtils", "notifyMusicApp DeadObjectException");
                        if (contentProviderClient2 == null) {
                            return;
                        }
                        contentProviderClient2.close();
                    } catch (Throwable th) {
                        th = th;
                        if (contentProviderClient != null) {
                            contentProviderClient.close();
                        }
                        throw th;
                    }
                } else {
                    LogUtil.h("SyncHwMusicAccountUtils", "notifyMusicApp: resolver is null");
                }
                if (contentProviderClient2 == null) {
                    return;
                }
            } catch (RuntimeException unused2) {
            }
            contentProviderClient2.close();
        } catch (Throwable th2) {
            th = th2;
            contentProviderClient = contentProviderClient2;
        }
    }
}
