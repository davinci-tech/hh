package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public final class tek {

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f17262a = Uri.parse("content://com.huawei.health.provider.HealthAppProvider");

    public static boolean b(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null) {
            stq.b("ProxyUtil", "contentResolver is null");
            return false;
        }
        String string = new SafeBundle(contentResolver.call(f17262a, "HEALTHWALLETINFO", (String) null, (Bundle) null)).getString("key_wether_to_auth", "false");
        stq.b("ProxyUtil", "isHealthAgreementAccepted:" + string);
        return "true".equalsIgnoreCase(string);
    }

    public static void c(final String str, final Context context) {
        synchronized (tek.class) {
            OverSeaMangerUtil.c(context);
            Log.e("ProxyUtil", "loadLocalWalletPlugin:" + str);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            svt.e().a(new Runnable() { // from class: teh
                @Override // java.lang.Runnable
                public final void run() {
                    tek.d(context, countDownLatch, str);
                }
            });
            e("loadLocalWalletPlugin", countDownLatch);
        }
    }

    static /* synthetic */ void d(Context context, CountDownLatch countDownLatch, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Class<?> cls = Class.forName("health.compact.a.DeviceConfigInit");
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            cls.getMethod("create", new Class[0]).invoke(declaredConstructor.newInstance(new Object[0]), new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            Log.e("ProxyUtil", "loadLocalWalletPlugin, plugin is not initialized successfully");
        }
        tee.a().b(context);
        countDownLatch.countDown();
        Log.i("ProxyUtil", str + ": loading time is " + (System.currentTimeMillis() - currentTimeMillis));
    }

    private static void e(String str, CountDownLatch countDownLatch) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.i("ProxyUtil", str + " in main thread");
        }
        try {
            Log.i("ProxyUtil", "lock start");
            if (countDownLatch.getCount() <= 0) {
                Log.i("ProxyUtil", "lock cancel" + str);
                return;
            }
            Log.i("ProxyUtil", "method: " + str + " awaitRst=" + countDownLatch.await(2000L, TimeUnit.MILLISECONDS) + ", time=2000");
        } catch (InterruptedException unused) {
            Log.e("ProxyUtil", "lock InterruptedException Exception");
        }
    }
}
