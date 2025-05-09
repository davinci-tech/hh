package com.huawei.watchface;

import android.app.Application;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;

/* loaded from: classes7.dex */
public class az extends ContentObserver {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f10912a = new Object();
    private static final String c = "content://" + WatchFaceConstant.f11201a + "/childmode_status";
    private static az d;
    private OperateWatchFaceCallback b;

    public az(Handler handler) {
        super(handler);
    }

    public static az a() {
        az azVar;
        synchronized (f10912a) {
            if (d == null) {
                d = new az(null);
            }
            azVar = d;
        }
        return azVar;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        HwLog.i("HealthBalanceContentObserver", "onChange: ");
        d();
    }

    public void b() {
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext == null) {
            HwLog.i("HealthBalanceContentObserver", "registerHealthBalanceObserver: application is null.");
            return;
        }
        ContentResolver contentResolver = applicationContext.getContentResolver();
        if (contentResolver == null) {
            HwLog.i("HealthBalanceContentObserver", "registerHealthBalanceObserver: contentResolver is null.");
            return;
        }
        try {
            contentResolver.registerContentObserver(Uri.parse(c), false, this);
        } catch (SecurityException e) {
            HwLog.e("HealthBalanceContentObserver", "registerContentObserver SecurityException: " + HwLog.printException((Exception) e));
        } catch (Exception e2) {
            HwLog.e("HealthBalanceContentObserver", "registerContentObserver Exception: " + HwLog.printException(e2));
        }
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.b = operateWatchFaceCallback;
    }

    private void d() {
        try {
            int i = Settings.Secure.getInt(Environment.getApplicationContext().getContentResolver(), Constants.PATH_CHILDMODE_STATUS);
            HwLog.i("HealthBalanceContentObserver", "notifyHealthChildStatusChange status:" + i);
            this.b.transmitHealthChildStatusChange(String.valueOf(i));
        } catch (Settings.SettingNotFoundException e) {
            HwLog.e("HealthBalanceContentObserver", "checkHealthChildStatus SettingNotFoundException: " + HwLog.printException((Exception) e));
        } catch (Exception e2) {
            HwLog.e("HealthBalanceContentObserver", "checkHealthChildStatus Exception: " + HwLog.printException(e2));
        }
    }

    public String c() {
        try {
            int i = Settings.Secure.getInt(Environment.getApplicationContext().getContentResolver(), Constants.PATH_CHILDMODE_STATUS);
            HwLog.i("HealthBalanceContentObserver", "getHealthChildStatus status:" + i);
            return String.valueOf(i);
        } catch (Settings.SettingNotFoundException e) {
            HwLog.e("HealthBalanceContentObserver", "checkHealthChildStatus SettingNotFoundException: " + HwLog.printException((Exception) e));
            return "0";
        } catch (Exception e2) {
            HwLog.e("HealthBalanceContentObserver", "checkHealthChildStatus Exception: " + HwLog.printException(e2));
            return "0";
        }
    }
}
