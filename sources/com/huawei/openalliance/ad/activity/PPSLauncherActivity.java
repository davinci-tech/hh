package com.huawei.openalliance.ad.activity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.openalliance.ad.analysis.h;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class PPSLauncherActivity extends f {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        StringBuilder sb;
        Intent launchIntentForPackage;
        super.onCreate(bundle);
        try {
            try {
                if (a(this)) {
                    ho.b("PPSLauncher", " app is active.");
                    launchIntentForPackage = new Intent();
                    launchIntentForPackage.setComponent(new ComponentName(getPackageName(), PPSBridgeActivity.class.getName()));
                    launchIntentForPackage.setFlags(268435456);
                } else {
                    ho.b("PPSLauncher", " app is not active. start launch app");
                    launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
                }
                startActivity(launchIntentForPackage);
                a();
                try {
                    finish();
                } catch (Throwable th) {
                    th = th;
                    sb = new StringBuilder("finish activity error:");
                    sb.append(th.getClass().getName());
                    ho.b("PPSLauncher", sb.toString());
                }
            } catch (Throwable th2) {
                try {
                    finish();
                } catch (Throwable th3) {
                    ho.b("PPSLauncher", "finish activity error:" + th3.getClass().getName());
                }
                throw th2;
            }
        } catch (Exception e) {
            ho.b("PPSLauncher", "intent is not supported:" + e.getClass().getName());
            try {
                finish();
            } catch (Throwable th4) {
                th = th4;
                sb = new StringBuilder("finish activity error:");
                sb.append(th.getClass().getName());
                ho.b("PPSLauncher", sb.toString());
            }
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    public boolean a(Context context) {
        for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) context.getSystemService("activity")).getRunningTasks(10)) {
            ho.a("PPSLauncher", "topActivity=%s", runningTaskInfo.topActivity.getClassName());
            ho.a("PPSLauncher", "taskInfo.numActivities=%d", Integer.valueOf(runningTaskInfo.numActivities));
            if (runningTaskInfo.topActivity.getClassName().equalsIgnoreCase(PPSLauncherActivity.class.getName()) && runningTaskInfo.numActivities < 2) {
                return false;
            }
            if (runningTaskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private void a() {
        new h(this).c(getPackageName());
    }
}
