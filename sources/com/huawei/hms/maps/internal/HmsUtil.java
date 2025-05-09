package com.huawei.hms.maps.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.maps.utils.LogM;
import com.huawei.hms.update.kpms.KpmsConstant;
import com.huawei.hms.utils.HMSPackageManager;

/* loaded from: classes4.dex */
public class HmsUtil {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f4951a = false;
    private static boolean b = true;
    private static boolean c = false;
    private static int d = 12;
    private static boolean e = false;

    public static void setRepeatFlag(boolean z) {
        b = z;
        mab.a(z);
    }

    public static void setHmApk(boolean z) {
        LogM.i("HmsUtil", "setHmApk: " + z);
        e = z;
    }

    public static int isHmsAvailable(Context context) {
        if (d == 0) {
            return 0;
        }
        LogM.d("HmsUtil", "isInitialized is: " + f4951a + ", repeatFlag is: " + b);
        if (f4951a || !b) {
            return 1;
        }
        AvailableAdapter availableAdapter = new AvailableAdapter(getMinHmsVersion(context));
        int isHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(context);
        LogM.d("HmsUtil", "Hms is :" + isHuaweiMobileServicesAvailable);
        f4951a = true;
        if (isHuaweiMobileServicesAvailable == 0) {
            LogM.i("HmsUtil", "Hms is avaiable");
        } else if (!availableAdapter.isUserResolvableError(isHuaweiMobileServicesAvailable)) {
            LogM.e("HmsUtil", "Hms is not avaiable 26");
        } else if (!c) {
            if (2 == isHuaweiMobileServicesAvailable && e) {
                LogM.i("HmsUtil", "Hms is resolution, isHmApk: " + e);
                a(context);
            } else {
                a(availableAdapter, context);
                c = true;
            }
        }
        d = isHuaweiMobileServicesAvailable;
        return isHuaweiMobileServicesAvailable;
    }

    public static int getMinHmsVersion(Context context) {
        String str;
        int i;
        try {
            str = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128).metaData.getString("com.huawei.hms.map.version");
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            LogM.e("HmsUtil", "NameNotFoundException ");
            str = null;
        }
        if (str != null && !"".equals(str)) {
            String[] split = str.split("\\.");
            if (split.length == 4) {
                StringBuilder sb = new StringBuilder();
                sb.append(split[0]);
                sb.append("0");
                sb.append(split[1]);
                sb.append("0");
                sb.append(split[2]);
                sb.append(split[3]);
                LogM.i("HmsUtil", "hmsVersionStr " + str);
                i = Integer.parseInt(sb.toString());
                LogM.i("HmsUtil", "baseVersion " + i);
                return i;
            }
        }
        i = 60200300;
        LogM.i("HmsUtil", "baseVersion " + i);
        return i;
    }

    public static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private static void a(AvailableAdapter availableAdapter, Context context) {
        LogM.d("HmsUtil", "Hms is resolution :");
        Activity findActivity = findActivity(context);
        if (findActivity == null) {
            LogM.e("HmsUtil", "Hms is not available26");
        } else {
            availableAdapter.startResolution(findActivity, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.maps.internal.HmsUtil.1
                @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
                public void onComplete(int i) {
                    if (i == 0) {
                        int unused = HmsUtil.d = i;
                        LogM.i("HmsUtil", "Hms is available");
                        return;
                    }
                    LogM.e("HmsUtil", "Hms update version failed: " + i);
                    boolean unused2 = HmsUtil.f4951a = false;
                    boolean unused3 = HmsUtil.b = false;
                }
            });
        }
    }

    private static void a(Context context) {
        Activity findActivity = findActivity(context);
        if (findActivity == null) {
            LogM.e("HmsUtil", "Hms is not available26");
            return;
        }
        try {
            LogM.i("HmsUtil", "4.0 framework HMSCore upgrade process");
            String hMSPackageName = HMSPackageManager.getInstance(findActivity.getApplicationContext()).getHMSPackageName();
            ComponentName componentName = new ComponentName(hMSPackageName, "com.huawei.hms.fwksdk.stub.UpdateStubActivity");
            Intent intent = new Intent();
            intent.putExtra(KpmsConstant.CALLER_PACKAGE_NAME, findActivity.getApplicationContext().getPackageName());
            intent.putExtra(KpmsConstant.UPDATE_PACKAGE_NAME, hMSPackageName);
            intent.setComponent(componentName);
            findActivity.startActivityForResult(intent, 25);
        } catch (ActivityNotFoundException unused) {
            LogM.e("HmsUtil", "not found activity :com.huawei.hms.fwksdk.stub.UpdateStubActivity");
        }
    }
}
