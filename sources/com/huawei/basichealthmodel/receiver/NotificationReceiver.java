package com.huawei.basichealthmodel.receiver;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.aza;
import defpackage.azi;
import defpackage.dsl;
import defpackage.ixx;
import defpackage.iyk;
import health.compact.a.AuthorizationUtils;
import health.compact.a.LogAnonymous;

/* loaded from: classes3.dex */
public class NotificationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        int intExtra = intent.getIntExtra("reminderId", -1);
        int intExtra2 = intent.getIntExtra("id", -1);
        LogUtil.a("HealthLife_NotificationReceiver", "onReceive reminderId is ", Integer.valueOf(intExtra), " ,taskId is ", Integer.valueOf(intExtra2));
        if (!AuthorizationUtils.a(context) || !LoginInit.getInstance(context).getIsLogined() || TextUtils.isEmpty(LoginInit.getInstance(context).getAccountInfo(1011))) {
            LogUtil.a("HealthLife_NotificationReceiver", "onReceive start health app");
            Bundle bundle = new Bundle();
            bundle.putString("extra_action", "showHealthModel");
            bundle.putInt("reminderId", intExtra);
            bundle.putInt("id", intExtra2);
            str = "huaweischeme://healthapp/basicHealth?healthType=6&urlType=4&pName=com.huawei.health&id=" + bundle.getInt("id");
            kL_(context, bundle);
        } else {
            str = RecordAction.ACT_ID_TAG + intExtra2;
            dsl.ZK_(context, Uri.parse("").buildUpon().appendQueryParameter("id", String.valueOf(intExtra2)).appendQueryParameter("from", RemoteMessageConst.NOTIFICATION).build());
        }
        aza.c(intExtra);
        ixx.d().c(new iyk.e().a(1).d(azi.i()).a(str).e((String) null).c((String) null).b((String) null).b());
    }

    private void kL_(Context context, Bundle bundle) {
        if (context == null) {
            LogUtil.h("HealthLife_NotificationReceiver", "startHealthApp context is null");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            Intent intent = new Intent();
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null) {
                LogUtil.h("HealthLife_NotificationReceiver", "startHealthApp launchIntentForPackage is null");
            } else {
                ComponentName component = launchIntentForPackage.getComponent();
                if (component == null) {
                    LogUtil.h("HealthLife_NotificationReceiver", "startHealthApp componentName is null");
                } else {
                    intent.setComponent(new ComponentName(context.getPackageName(), component.getClassName()));
                }
            }
            intent.setData(Uri.parse("huaweischeme://healthapp/basicHealth?healthType=6&urlType=4&pName=com.huawei.health&id=" + bundle.getInt("id")));
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(268435456);
            intent.putExtra(KnitFragment.KEY_EXTRA, bundle);
            intent.putExtra(CommonUtil.PARAM_HEALTH_TYPE, 6);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("HealthLife_NotificationReceiver", "startHealthApp exception ", LogAnonymous.b((Throwable) e));
        }
    }
}
