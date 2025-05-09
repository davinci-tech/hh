package com.huawei.operation.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlOperate;
import com.huawei.operation.utils.Constants;
import defpackage.jdh;
import java.util.Locale;

/* loaded from: classes5.dex */
public class NotificationService extends Service {
    public static final int NOTIFICATION_ID = 20210309;
    private static final String TAG = "H5PRO_NotificationService";
    private String mMessageContent = null;
    private String mMessageUrl = null;
    private SystemLocaleChangeReceiver mSystemLocaleChangeReceiver;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "onCreate");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (intent == null) {
            LogUtil.w(TAG, "onStartCommand: intent is null");
            return 2;
        }
        if (intent.getBooleanExtra(AudioControlOperate.IS_AUDIO_START_SERVICE, false)) {
            LogUtil.i(TAG, "onStartCommand: IS_AUDIO_START_SERVICE");
            AudioControlOperate.INSTANCE.play(this);
            return 2;
        }
        LogUtil.i(TAG, "onStartCommand: !IS_AUDIO_START_SERVICE");
        registerSystemLanguageChange();
        createDefaultNotification(intent.getStringExtra(Constants.NOTIFICATION_MESSAGE), intent.getStringExtra(Constants.NOTIFICATION_URL));
        return 2;
    }

    private void startForegroundProtect(Notification notification) {
        try {
            startForeground(NOTIFICATION_ID, notification);
        } catch (Exception unused) {
            LogUtil.e(TAG, "startForeground Exception");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.i(TAG, "onDestroy");
        unregisterSystemLanguageChange();
        AudioControlOperate.INSTANCE.closeControl(this);
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        LogUtil.i(TAG, "onTaskRemoved");
        unregisterSystemLanguageChange();
        AudioControlOperate.INSTANCE.closeControl(this);
        super.onTaskRemoved(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createDefaultNotification(String str, String str2) {
        Notification createNotification = createNotification(String.format(Locale.ENGLISH, BaseApplication.e().getResources().getString(R.string._2130839706_res_0x7f02089a), str), str2);
        jdh.c().xh_(NOTIFICATION_ID, createNotification);
        startForegroundProtect(createNotification);
        this.mMessageContent = str;
        this.mMessageUrl = str2;
    }

    private Notification createNotification(String str, String str2) {
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setContentText(str);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setContentIntent(getPendingIntent(str2));
        xf_.setOngoing(true);
        xf_.setAutoCancel(false);
        jdh.c().xi_(xf_);
        return xf_.build();
    }

    private PendingIntent getPendingIntent(String str) {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?type=1&address=" + str));
        intent.setPackage("com.huawei.health");
        return PendingIntent.getActivity(BaseApplication.e(), 0, intent, AppRouterExtras.COLDSTART);
    }

    private void registerSystemLanguageChange() {
        if (this.mSystemLocaleChangeReceiver == null) {
            LogUtil.i(TAG, "Enter registerSystemLanguageChange()");
            SystemLocaleChangeReceiver systemLocaleChangeReceiver = new SystemLocaleChangeReceiver();
            this.mSystemLocaleChangeReceiver = systemLocaleChangeReceiver;
            BroadcastManager.wk_(systemLocaleChangeReceiver);
        }
    }

    private void unregisterSystemLanguageChange() {
        synchronized (this) {
            if (this.mSystemLocaleChangeReceiver != null) {
                LogUtil.i(TAG, "Enter unregisterSystemLanguageChange()");
                BroadcastManager.wy_(this.mSystemLocaleChangeReceiver);
                this.mSystemLocaleChangeReceiver = null;
                this.mMessageContent = null;
                this.mMessageUrl = null;
            }
        }
    }

    class SystemLocaleChangeReceiver extends BroadcastReceiver {
        private SystemLocaleChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.e(NotificationService.TAG, "SystemLocaleChangeReceiver intent is null");
                return;
            }
            LogUtil.i(NotificationService.TAG, "mReceiver  onReceive  intent.getAction(): ", intent.getAction());
            if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                LogUtil.i(NotificationService.TAG, "SystemLocaleChangeReceiver language change");
                if (TextUtils.isEmpty(NotificationService.this.mMessageContent) || TextUtils.isEmpty(NotificationService.this.mMessageUrl)) {
                    return;
                }
                jdh.c().a(NotificationService.NOTIFICATION_ID);
                NotificationService notificationService = NotificationService.this;
                notificationService.createDefaultNotification(notificationService.mMessageContent, NotificationService.this.mMessageUrl);
            }
        }
    }
}
