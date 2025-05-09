package com.huawei.updatesdk.service.otaupdate;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.appmgr.bean.AppInfoAdapter;
import com.huawei.updatesdk.service.otaupdate.UpdateParams;
import java.util.List;

/* loaded from: classes7.dex */
public final class g {
    public static void b(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack) {
        a(context, updateParams, checkUpdateCallBack, false);
    }

    private static boolean a(AppInfoAdapter appInfoAdapter) {
        return (appInfoAdapter == null || TextUtils.isEmpty(appInfoAdapter.getAppStorePkgName()) || !TextUtils.equals(appInfoAdapter.getBusiness(), "AppTouch")) ? false : true;
    }

    public static void a(String str) {
        f.e().b(str);
    }

    public static void a(Context context, String str, CheckUpdateCallBack checkUpdateCallBack) {
        if (context == null || TextUtils.isEmpty(str) || checkUpdateCallBack == null) {
            if (checkUpdateCallBack != null) {
                Intent intent = new Intent();
                intent.putExtra("status", 1);
                checkUpdateCallBack.onUpdateInfo(intent);
                return;
            }
            return;
        }
        if (com.huawei.updatesdk.a.a.d.j.a.d(context)) {
            a(context);
            new e(context, new UpdateParams.Builder().setMustBtnOne(false).setTargetPkgName(str).build(), checkUpdateCallBack).executeOnExecutor(com.huawei.updatesdk.b.g.c.b, new Void[0]);
        } else {
            Intent intent2 = new Intent();
            intent2.putExtra("status", 2);
            checkUpdateCallBack.onUpdateInfo(intent2);
        }
    }

    private static void a(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack, boolean z) {
        if (context == null || updateParams == null || checkUpdateCallBack == null) {
            return;
        }
        a(context);
        long currentTimeMillis = System.currentTimeMillis();
        long b = com.huawei.updatesdk.b.b.a.d().b();
        if (updateParams.getMinIntervalDay() != 0 && Math.abs(currentTimeMillis - b) < updateParams.getMinIntervalDay() * 86400000) {
            com.huawei.updatesdk.a.a.a.b("UpdateSDKImpl", "Interval check time is limited and do not check app update.");
            return;
        }
        if (!com.huawei.updatesdk.a.a.d.j.a.d(context)) {
            Intent intent = new Intent();
            intent.putExtra("status", 2);
            checkUpdateCallBack.onUpdateInfo(intent);
        } else {
            com.huawei.updatesdk.b.b.a.d().a(currentTimeMillis);
            e eVar = new e(context, updateParams, checkUpdateCallBack);
            eVar.a(z);
            eVar.executeOnExecutor(com.huawei.updatesdk.b.g.c.b, new Void[0]);
        }
    }

    public static void a(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack) {
        if (context == null || updateParams == null || checkUpdateCallBack == null) {
            return;
        }
        updateParams.resetParamList();
        b(context, updateParams, checkUpdateCallBack);
    }

    public static void a(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z, boolean z2) {
        if (context == null) {
            return;
        }
        if (com.huawei.updatesdk.a.a.d.j.a.d(context)) {
            a(context);
            new e(context, new UpdateParams.Builder().setIsShowImmediate(z).setMustBtnOne(z2).build(), checkUpdateCallBack).executeOnExecutor(com.huawei.updatesdk.b.g.c.b, new Void[0]);
            return;
        }
        if (checkUpdateCallBack != null) {
            Intent intent = new Intent();
            intent.putExtra("status", 2);
            checkUpdateCallBack.onUpdateInfo(intent);
        }
        Toast.makeText(context, com.huawei.updatesdk.b.h.c.c(context, "upsdk_no_available_network_prompt_toast"), 0).show();
    }

    public static void a(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z, int i, boolean z2) {
        if (com.huawei.updatesdk.a.a.d.j.a.d(context)) {
            a(context);
            long currentTimeMillis = System.currentTimeMillis();
            long b = com.huawei.updatesdk.b.b.a.d().b();
            if (i != 0 && Math.abs(currentTimeMillis - b) < i * 86400000) {
                com.huawei.updatesdk.a.a.a.b("UpdateSDKImpl", "Interval check time is limited and do not check app update.");
                return;
            }
            com.huawei.updatesdk.b.b.a.d().a(currentTimeMillis);
            e eVar = new e(context, new UpdateParams.Builder().setIsShowImmediate(z).setMustBtnOne(z2).setMinIntervalDay(i).build(), checkUpdateCallBack);
            eVar.b(true);
            eVar.executeOnExecutor(com.huawei.updatesdk.b.g.c.b, new Void[0]);
        }
    }

    public static void a(Context context, CheckUpdateCallBack checkUpdateCallBack, AppInfoAdapter appInfoAdapter) {
        if (context == null) {
            return;
        }
        UpdateParams.Builder builder = new UpdateParams.Builder();
        if (appInfoAdapter != null) {
            if (!TextUtils.isEmpty(appInfoAdapter.getServiceZone())) {
                builder.setServiceZone(appInfoAdapter.getServiceZone());
            }
            f.e().c(appInfoAdapter.getAppStorePkgName());
            builder.setTargetPkgName(appInfoAdapter.getTargetPkgName()).setPackageList(appInfoAdapter.getPackageList()).setMustBtnOne(appInfoAdapter.isMustBtnOne()).setIsShowImmediate(appInfoAdapter.isShowImmediate()).setMinIntervalDay(appInfoAdapter.getMinIntervalDay());
        }
        UpdateParams build = builder.build();
        build.resetParamList();
        a(context, build, checkUpdateCallBack, a(appInfoAdapter));
    }

    private static void a(Context context, ApkUpgradeInfo apkUpgradeInfo, boolean z, boolean z2) {
        StringBuilder sb;
        String message;
        if (context == null || apkUpgradeInfo == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) AppUpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("app_update_parm", apkUpgradeInfo);
        bundle.putBoolean("app_must_btn", z);
        bundle.putBoolean("is_apptouch", z2);
        intent.putExtras(bundle);
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            sb = new StringBuilder("go AppUpdateActivity error: ");
            message = e.getMessage();
            sb.append(message);
            com.huawei.updatesdk.a.a.a.a("UpdateSDKImpl", sb.toString());
        } catch (SecurityException e2) {
            sb = new StringBuilder("go AppUpdateActivity security error: ");
            message = e2.getMessage();
            sb.append(message);
            com.huawei.updatesdk.a.a.a.a("UpdateSDKImpl", sb.toString());
        }
    }

    public static void a(Context context, ApkUpgradeInfo apkUpgradeInfo, boolean z) {
        a(context, apkUpgradeInfo, z, false);
    }

    public static void a(Context context, ApkUpgradeInfo apkUpgradeInfo, AppInfoAdapter appInfoAdapter) {
        if (appInfoAdapter != null && !TextUtils.isEmpty(appInfoAdapter.getAppStorePkgName())) {
            f.e().c(appInfoAdapter.getAppStorePkgName());
        }
        a(context, apkUpgradeInfo, appInfoAdapter != null && appInfoAdapter.isMustBtnOne(), a(appInfoAdapter));
    }

    private static void a(Context context) {
        com.huawei.updatesdk.a.b.a.a.a(context);
        StringBuilder sb = new StringBuilder("UpdateSDK version is: 5.0.2.300 ,flavor: envrelease ,pkgName: ");
        sb.append(context.getPackageName());
        com.huawei.updatesdk.a.a.a.b("UpdateSDKImpl", sb.toString());
        Log.i("updatesdk", sb.toString());
        if (com.huawei.updatesdk.a.a.d.i.c.e() == 3) {
            com.huawei.updatesdk.a.a.d.i.a.b(context);
        }
    }

    public static void a() {
        com.huawei.updatesdk.a.a.a.b("UpdateSDKImpl", "UpdateSDK releaseCallBack");
        d.a().a((CheckUpdateCallBack) null);
        List<AsyncTask> a2 = com.huawei.updatesdk.b.g.b.a();
        if (a2 == null || a2.isEmpty()) {
            return;
        }
        for (AsyncTask asyncTask : a2) {
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            com.huawei.updatesdk.a.a.a.b("UpdateSDKImpl", "cancel task");
        }
        a2.clear();
    }
}
