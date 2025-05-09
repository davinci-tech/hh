package com.huawei.healthcloud.plugintrack.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.notification.HealthSportingNotificationHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.jdh;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes4.dex */
public class KeepForegroundService extends Service {
    private Map<String, d> b = new HashMap();
    private LinkedList<String> d = new LinkedList<>();
    private HealthSportingNotificationHelper e;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("Track_KeepForegroundService", "KeepForegroundService onCreate");
        startForeground(20200523, jdh.c().xf_().build());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (aXA_(intent)) {
            LogUtil.h("Track_KeepForegroundService", "start fail wrong input");
            return 2;
        }
        String stringExtra = intent.getStringExtra("id");
        if (aXx_(intent) && !this.d.contains(stringExtra) && "HwGpsLocationManager".equals(stringExtra)) {
            LogUtil.a("Track_KeepForegroundService", "not exists HwGpsLocationManager info, should not to stop");
            return 2;
        }
        if (!aXy_(intent)) {
            LogUtil.h("Track_KeepForegroundService", "start fail");
            stopSelf(i2);
            return 2;
        }
        if (this.d.isEmpty()) {
            LogUtil.a("Track_KeepForegroundService", "stop success");
            stopSelf();
            return 2;
        }
        if (aXz_(intent) && !aXx_(intent)) {
            jdh.c().a(10012);
            aXv_(intent);
            LogUtil.a("Track_KeepForegroundService", "is Not To Stop createSportNotification");
        } else {
            a();
            LogUtil.a("Track_KeepForegroundService", "is Not To Stop createDefaultNotification");
        }
        LogUtil.a("Track_KeepForegroundService", "onStartCommand");
        return 2;
    }

    private boolean aXA_(Intent intent) {
        return (intent != null && intent.hasExtra("stringKey") && intent.hasExtra("id")) ? false : true;
    }

    private boolean aXz_(Intent intent) {
        return intent != null && intent.getIntExtra("NOTIFICATION_TYPE", 0) == 1;
    }

    private void a() {
        Notification aXu_ = aXu_(this.b.get(this.d.getLast()).a());
        jdh.c().xh_(20200523, aXu_);
        startForeground(20200523, aXu_);
    }

    private void aXv_(Intent intent) {
        boolean z;
        boolean z2;
        boolean z3;
        int i;
        if (intent != null) {
            int intExtra = intent.getIntExtra(BleConstants.SPORT_TYPE, 258);
            boolean booleanExtra = intent.getBooleanExtra("isFromTreadmill", false);
            boolean booleanExtra2 = intent.getBooleanExtra("isNeedControlAction", false);
            z = intent.getBooleanExtra("isSportFromWear", false);
            i = intExtra;
            z2 = booleanExtra;
            z3 = booleanExtra2;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            i = 258;
        }
        HealthSportingNotificationHelper healthSportingNotificationHelper = new HealthSportingNotificationHelper(BaseApplication.getContext(), i, true, z2, false);
        this.e = healthSportingNotificationHelper;
        healthSportingNotificationHelper.a(z3);
        this.e.d(z);
        Bundle bundle = new Bundle();
        bundle.putInt(BleConstants.SPORT_TYPE, i);
        bundle.putString("duration", UnitUtil.d(0));
        bundle.putString("bundle_key_service", UnitUtil.d(0));
        this.e.updateSportViewFragment(bundle);
        startForeground(10012, jdh.c().xf_().build());
    }

    private boolean aXy_(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            String string = BaseApplication.getContext().getString(intent.getIntExtra("stringKey", 0));
            d dVar = new d(string);
            String stringExtra = intent.getStringExtra("id");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(stringExtra)) {
                LogUtil.b("Track_KeepForegroundService", "wrong input");
                return false;
            }
            if (aXx_(intent)) {
                this.b.remove(stringExtra);
                this.d.remove(stringExtra);
            } else {
                this.d.remove(stringExtra);
                this.d.add(stringExtra);
                this.b.put(stringExtra, dVar);
            }
            LogUtil.a("Track_KeepForegroundService", "id ", stringExtra, "mIds ", this.d.toString());
            return true;
        } catch (Resources.NotFoundException e) {
            LogUtil.b("Track_KeepForegroundService", "String NotFoundException", e.toString());
            return false;
        }
    }

    private boolean aXx_(Intent intent) {
        return intent.getBooleanExtra("isStop", false);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        jdh.c().a(20200523);
        jdh.c().a(10012);
        HealthSportingNotificationHelper healthSportingNotificationHelper = this.e;
        if (healthSportingNotificationHelper != null) {
            healthSportingNotificationHelper.d();
            this.e = null;
        }
        ReleaseLogUtil.e("Track_KeepForegroundService", "KeepForegroundService onDestroy");
    }

    private Notification aXu_(String str) {
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setContentIntent(aXw_());
        xf_.setOngoing(true);
        xf_.setAutoCancel(false);
        jdh.c().xi_(xf_);
        if (str != null) {
            xf_.setContentText(str);
            xf_.setStyle(new Notification.BigTextStyle().bigText(str));
        }
        return xf_.build();
    }

    private PendingIntent aXw_() {
        Intent launchIntentForPackage = getApplicationContext().getPackageManager().getLaunchIntentForPackage(BaseApplication.getAppPackage());
        if (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) {
            return PendingIntent.getActivity(getApplicationContext(), 0, new Intent().setPackage(BaseApplication.getAppPackage()), 201326592);
        }
        launchIntentForPackage.setComponent(new ComponentName(BaseApplication.getAppPackage(), launchIntentForPackage.getComponent().getClassName()));
        launchIntentForPackage.setAction("android.intent.action.MAIN");
        launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        launchIntentForPackage.setFlags(268435456);
        launchIntentForPackage.putExtra("mLaunchSource", 5);
        return PendingIntent.getActivity(getApplicationContext(), 0, launchIntentForPackage, 201326592);
    }

    static class d {
        private String d;

        public d(String str) {
            this.d = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a() {
            return this.d;
        }
    }
}
