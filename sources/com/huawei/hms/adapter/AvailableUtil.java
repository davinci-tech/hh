package com.huawei.hms.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AndroidException;
import com.huawei.hms.adapter.ui.UpdateAdapter;
import com.huawei.hms.common.HmsCheckedState;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.AgHmsUpdateState;

/* loaded from: classes4.dex */
public class AvailableUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4264a = new Object();
    private static boolean b = false;
    private static boolean c = false;

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f4265a;

        a(Context context) {
            this.f4265a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.i("AvailableUtil", "enter asyncCheckHmsV3UpdateInfo");
            if (AvailableUtil.isInstallerLibExist(this.f4265a)) {
                UpdateAdapter.invokeMethod("com.huawei.hms.adapter.ui.InstallerAdapter", "checkHmsUpdateInfo", new Object[]{this.f4265a});
                HMSLog.i("AvailableUtil", "quit asyncCheckHmsV3UpdateInfo");
            } else {
                AgHmsUpdateState.getInstance().setCheckedState(HmsCheckedState.NOT_NEED_UPDATE);
                HMSLog.e("AvailableUtil", "asyncCheckHmsV3UpdateInfo installer is not exist");
            }
        }
    }

    public static void asyncCheckHmsUpdateInfo(Context context) {
        if (HmsCheckedState.UNCHECKED != AgHmsUpdateState.getInstance().getCheckedState()) {
            HMSLog.i("AvailableUtil", "asyncCheckHmsUpdateInfo, not need to check");
        } else {
            new Thread(new a(context), "Thread-asyncCheckHmsV3UpdateInfo").start();
        }
    }

    public static boolean isInstallerLibExist(Context context) {
        Bundle bundle;
        Object obj;
        boolean z;
        if (b) {
            HMSLog.i("AvailableUtil", "installerInit exist: " + c);
            return c;
        }
        synchronized (f4264a) {
            if (!b) {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    HMSLog.e("AvailableUtil", "In isAvailableLibExist, Failed to get 'PackageManager' instance.");
                    try {
                        Class.forName("com.huawei.hms.update.manager.UpdateManager");
                        z = true;
                    } catch (ClassNotFoundException unused) {
                        HMSLog.e("AvailableUtil", "In isInstallerLibExist, Failed to find class UpdateManager.");
                    }
                } else {
                    try {
                        ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
                        if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("availableHMSCoreInstaller")) != null && String.valueOf(obj).equalsIgnoreCase("yes")) {
                            HMSLog.i("AvailableUtil", "available exist: true");
                            z = true;
                        }
                    } catch (AndroidException unused2) {
                        HMSLog.e("AvailableUtil", "In isInstallerLibExist, Failed to read meta data for the availableHMSCoreInstaller.");
                    } catch (RuntimeException e) {
                        HMSLog.e("AvailableUtil", "In isInstallerLibExist, Failed to read meta data for the availableHMSCoreInstaller.", e);
                    }
                    z = false;
                }
                c = z;
                b = true;
            }
        }
        HMSLog.i("AvailableUtil", "available exist: " + c);
        return c;
    }
}
