package com.huawei.operation.h5pro.jsmodules.audiocontrol;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.service.NotificationService;
import defpackage.hcn;
import defpackage.jdh;
import defpackage.jdx;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nsf;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class AudioControlOperate {
    public static final String EVENT_KEY_AUDIO = "EVENT_KEY_AUDIO";
    public static final int EVENT_VALUE_AUDIO_PAUSE = 2;
    public static final int EVENT_VALUE_AUDIO_PLAY = 1;
    public static final String IS_AUDIO_START_SERVICE = "IS_AUDIO_START_SERVICE";
    private static final String TAG = "H5PRO_AudioControlOperation";
    private static volatile boolean isStartForeground = true;
    private final int audioImageRound = hcn.a(BaseApplication.getContext(), 8.0f);
    private volatile String mAudioData;
    private volatile Notification.Builder mNotificationBuilder;
    private volatile RemoteViews mRemoteViews;
    private static final Object EVENT_LOCK_AUDIO = new Object();
    public static final AudioControlOperate INSTANCE = new AudioControlOperate();

    private AudioControlOperate() {
    }

    private void createNotificationBuilder() {
        if (this.mNotificationBuilder != null) {
            return;
        }
        LogUtil.i(TAG, "createNotificationBuilder");
        this.mNotificationBuilder = jdh.c().xf_();
        this.mNotificationBuilder.setSmallIcon(R.drawable.healthlogo_ic_notification);
        this.mNotificationBuilder.setOngoing(true);
        this.mNotificationBuilder.setAutoCancel(false);
        jdh.c().xi_(this.mNotificationBuilder);
        Iterator<ActivityManager.AppTask> it = ((ActivityManager) BaseApplication.getContext().getSystemService(ActivityManager.class)).getAppTasks().iterator();
        while (it.hasNext()) {
            ComponentName componentName = it.next().getTaskInfo().topActivity;
            if (componentName != null && TextUtils.equals(BaseApplication.getAppPackage(), componentName.getPackageName())) {
                Intent intent = new Intent();
                intent.setPackage(componentName.getPackageName());
                intent.setComponent(componentName);
                this.mNotificationBuilder.setContentIntent(PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, AppRouterExtras.COLDSTART));
                return;
            }
        }
    }

    public void play(String str) {
        synchronized (EVENT_LOCK_AUDIO) {
            if (!isActiveControl()) {
                this.mAudioData = str;
                Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) NotificationService.class);
                intent.setPackage(BaseApplication.getAppPackage());
                intent.putExtra(IS_AUDIO_START_SERVICE, true);
                isStartForeground = false;
                LogUtil.i(TAG, "play: startForegroundService");
                BaseApplication.getContext().startForegroundService(intent);
                return;
            }
            updateControlToPlay(str, null);
        }
    }

    public void play(Service service) {
        synchronized (EVENT_LOCK_AUDIO) {
            Notification updateControlToPlay = updateControlToPlay(this.mAudioData, service);
            if (updateControlToPlay != null) {
                service.startForeground(NotificationService.NOTIFICATION_ID, updateControlToPlay);
                isStartForeground = true;
                LogUtil.i(TAG, "play: startForeground");
            }
        }
    }

    private Notification updateControlToPlay(String str, Service service) {
        Notification unHosUpdateControlToPlay;
        createNotificationBuilder();
        if (EnvironmentInfo.j()) {
            unHosUpdateControlToPlay = hosUpdateControlToPlay(str);
        } else {
            unHosUpdateControlToPlay = unHosUpdateControlToPlay(str);
        }
        if (unHosUpdateControlToPlay == null) {
            exceptionStopService(service);
        }
        return unHosUpdateControlToPlay;
    }

    private Notification hosUpdateControlToPlay(String str) {
        AudioControlParamObj audioControlParamObj = (AudioControlParamObj) GsonUtil.parseJson(str, AudioControlParamObj.class);
        if (audioControlParamObj == null) {
            LogUtil.w(TAG, "hosUpdateControl: audioControlObj is null");
            return null;
        }
        if (!TextUtils.isEmpty(audioControlParamObj.getSource())) {
            this.mNotificationBuilder.setContentTitle(nsf.b(R.string._2130839707_res_0x7f02089b, audioControlParamObj.getName(), audioControlParamObj.getSource()));
        } else {
            this.mNotificationBuilder.setContentTitle(audioControlParamObj.getName());
        }
        this.mNotificationBuilder.setContentText(nsf.b(R.string._2130839706_res_0x7f02089a, audioControlParamObj.getName()));
        Notification build = this.mNotificationBuilder.build();
        jdh.c().xh_(NotificationService.NOTIFICATION_ID, build);
        return build;
    }

    private Notification unHosUpdateControlToPlay(String str) {
        final AudioControlParamObj audioControlParamObj = (AudioControlParamObj) GsonUtil.parseJson(str, AudioControlParamObj.class);
        if (audioControlParamObj == null) {
            LogUtil.w(TAG, "unHosUpdateControl: audioControlObj is null");
            return null;
        }
        this.mRemoteViews = new RemoteViews(BaseApplication.getAppPackage(), R.layout.layout_audio_control_notification);
        this.mRemoteViews.setTextViewText(R.id.tv_audioName, TextUtils.isEmpty(audioControlParamObj.getName()) ? "" : audioControlParamObj.getName());
        this.mRemoteViews.setTextViewText(R.id.tv_audioSource, TextUtils.isEmpty(audioControlParamObj.getSource()) ? "" : audioControlParamObj.getSource());
        this.mNotificationBuilder.setContent(this.mRemoteViews);
        jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlOperate$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AudioControlOperate.this.m746x508d40a8(audioControlParamObj);
            }
        });
        return setAudioEventViewStatus(1);
    }

    /* renamed from: lambda$unHosUpdateControlToPlay$0$com-huawei-operation-h5pro-jsmodules-audiocontrol-AudioControlOperate, reason: not valid java name */
    /* synthetic */ void m746x508d40a8(AudioControlParamObj audioControlParamObj) {
        Bitmap cHT_ = nrf.cHT_(BaseApplication.getContext(), audioControlParamObj.getImage());
        Bitmap cJq_ = cHT_ == null ? null : nrf.cJq_(cHT_, cHT_.getWidth(), cHT_.getHeight(), this.audioImageRound);
        if (this.mRemoteViews == null) {
            LogUtil.w(TAG, "unHosUpdateControlToPlay: remoteView is null");
            return;
        }
        if (cJq_ == null) {
            this.mRemoteViews.setImageViewResource(R.id.iv_audioImage, R.mipmap._2131820756_res_0x7f1100d4);
            this.mRemoteViews.setViewVisibility(R.id.iv_audioLogo, 4);
        } else {
            this.mRemoteViews.setImageViewBitmap(R.id.iv_audioImage, cJq_);
            this.mRemoteViews.setViewVisibility(R.id.iv_audioLogo, 0);
        }
        jdh.c().xh_(NotificationService.NOTIFICATION_ID, this.mNotificationBuilder.build());
    }

    private Notification setAudioEventViewStatus(int i) {
        LogUtil.i(TAG, "setAudioEventViewStatus: audioEvent -> " + i);
        if (this.mRemoteViews == null) {
            LogUtil.w(TAG, "setAudioEventViewStatus: mRemoteViews is null");
            return null;
        }
        if (nrt.a(BaseApplication.getContext())) {
            this.mRemoteViews.setImageViewResource(R.id.iv_audioStatus, i == 1 ? R.drawable._2131428570_res_0x7f0b04da : R.drawable._2131428571_res_0x7f0b04db);
        } else {
            this.mRemoteViews.setImageViewResource(R.id.iv_audioStatus, i == 1 ? R.drawable._2131428579_res_0x7f0b04e3 : R.drawable._2131428580_res_0x7f0b04e4);
        }
        Intent intent = new Intent("BROADCAST_ACTION_UPDATE_H5_AUDIO_PAGE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.putExtra(EVENT_KEY_AUDIO, i == 1 ? 2 : 1);
        this.mRemoteViews.setOnClickPendingIntent(R.id.rl_audioStatus, PendingIntent.getBroadcast(BaseApplication.getContext(), 0, intent, 201326592));
        Notification build = this.mNotificationBuilder.build();
        jdh.c().xh_(NotificationService.NOTIFICATION_ID, build);
        return build;
    }

    public void pause() {
        synchronized (EVENT_LOCK_AUDIO) {
            if (isActiveControl()) {
                if (EnvironmentInfo.j()) {
                    this.mNotificationBuilder.setContentText(nsf.h(R.string._2130839739_res_0x7f0208bb));
                    jdh.c().xh_(NotificationService.NOTIFICATION_ID, this.mNotificationBuilder.build());
                    return;
                }
                setAudioEventViewStatus(2);
            }
        }
    }

    public void stopServiceSynchronized() {
        synchronized (EVENT_LOCK_AUDIO) {
            stopService();
        }
    }

    private void exceptionStopService(Service service) {
        if (service != null) {
            service.startForeground(NotificationService.NOTIFICATION_ID, this.mNotificationBuilder.build());
            isStartForeground = true;
            LogUtil.i(TAG, "exceptionStopService: startForeground");
        }
        stopService();
    }

    private void stopService() {
        final Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) NotificationService.class);
        intent.setPackage(BaseApplication.getAppPackage());
        if (isStartForeground) {
            BaseApplication.getContext().stopService(intent);
            LogUtil.i(TAG, "stopService: isStartForeground -> true");
        } else {
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlOperate$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlOperate.lambda$stopService$1(intent);
                }
            }, 300L);
        }
    }

    static /* synthetic */ void lambda$stopService$1(Intent intent) {
        BaseApplication.getContext().stopService(intent);
        LogUtil.i(TAG, "stopService: isStartForeground -> false");
    }

    public void closeControl(Service service) {
        synchronized (EVENT_LOCK_AUDIO) {
            this.mRemoteViews = null;
            service.stopForeground(true);
            jdh.c().a(NotificationService.NOTIFICATION_ID);
        }
    }

    public void release() {
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlOperate$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AudioControlOperate.this.release();
                }
            });
            return;
        }
        synchronized (EVENT_LOCK_AUDIO) {
            if (isActiveControl()) {
                LogUtil.i(TAG, "release");
                jdh.c().a(NotificationService.NOTIFICATION_ID);
                stopService();
            }
        }
    }

    private static boolean isActiveControl() {
        StatusBarNotification[] activeNotifications;
        NotificationManager xB_ = CommonUtil.xB_();
        if (xB_ != null && (activeNotifications = xB_.getActiveNotifications()) != null && activeNotifications.length != 0) {
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                if (statusBarNotification.getId() == 20210309) {
                    return true;
                }
            }
        }
        return false;
    }
}
