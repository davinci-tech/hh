package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.RewardKeys;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LocalBroadcast;
import health.compact.a.NotificationUtil;
import java.util.Locale;

/* loaded from: classes.dex */
public class jzb {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14214a = new Object();
    private static jzb c;
    private c d;
    private boolean b = false;
    private Context e = BaseApplication.getContext();

    /* loaded from: classes5.dex */
    class c extends BroadcastReceiver {
        private c() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            if (context == null || intent == null) {
                LogUtil.h("CloudFileNotificationUtils", "NotificationClickReceiver action is null");
                return;
            }
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (extras == null || TextUtils.isEmpty(action)) {
                LogUtil.h("CloudFileNotificationUtils", "download bundle or action is null");
                return;
            }
            LogUtil.a("CloudFileNotificationUtils", "action is :", action);
            action.hashCode();
            switch (action.hashCode()) {
                case -1733864775:
                    if (action.equals("trans_done")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1644784922:
                    if (action.equals("cancel_file_download")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1252665753:
                    if (action.equals("dis_agree_file_download")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1201572598:
                    if (action.equals("cancel_file_trans")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -653215716:
                    if (action.equals("retry_file_trans")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -279472232:
                    if (action.equals("agree_file_download")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 694460052:
                    if (action.equals("retry_file_download")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 749153151:
                    if (action.equals("notification_delete")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                    jzb.this.d();
                    jzd.b().a();
                    break;
                case 4:
                    jzb.this.bLB_(0, extras);
                    jzd.b().bLT_(extras, true);
                    break;
                case 5:
                case 6:
                    jzb.this.bLz_(0, false, extras);
                    jzd.b().bLS_(extras, true);
                    break;
                case 7:
                    LogUtil.a("CloudFileNotificationUtils", "isFinish: ", Boolean.valueOf(jzb.this.b));
                    if (jzb.this.b) {
                        jzd.b().a();
                        break;
                    }
                    break;
            }
        }
    }

    public static jzb e() {
        jzb jzbVar;
        synchronized (f14214a) {
            if (c == null) {
                c = new jzb();
            }
            jzbVar = c;
        }
        return jzbVar;
    }

    protected jzb() {
        try {
            this.d = new c();
            IntentFilter intentFilter = new IntentFilter("cancel_file_download");
            intentFilter.addAction("agree_file_download");
            intentFilter.addAction("retry_file_download");
            intentFilter.addAction("notification_delete");
            intentFilter.addAction("trans_done");
            intentFilter.addAction("dis_agree_file_download");
            intentFilter.addAction("cancel_file_trans");
            intentFilter.addAction("retry_file_trans");
            BroadcastManagerUtil.bFA_(this.e, this.d, intentFilter, LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b("CloudFileNotificationUtils", "registerReceiver is error");
        }
    }

    protected void bLA_(Bundle bundle) {
        if (BaseApplication.isRunningForeground()) {
            bLt_(bundle, 1);
            return;
        }
        this.b = true;
        RemoteViews remoteViews = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_layout);
        remoteViews.setViewVisibility(R.id.notice_layout, 0);
        remoteViews.setViewVisibility(R.id.download_progress_layout, 8);
        remoteViews.setViewVisibility(R.id.download_progress_error_layout, 8);
        remoteViews.setViewVisibility(R.id.transfer_progress_layout, 8);
        this.e.getResources().getString(R$string.IDS_notify_file_content);
        String b = jyw.b(bundle.getString("hex_data"));
        LogUtil.a("CloudFileNotificationUtils", "file size: ", b);
        int bLj_ = jyw.bLj_(bundle);
        String b2 = jyw.b(bLj_, b);
        remoteViews.setTextViewText(R.id.file_notice_content, b2);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "agree_file_download"), 201326592);
        remoteViews.setOnClickPendingIntent(R.id.bt_agree, broadcast);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "dis_agree_file_download"), 201326592);
        remoteViews.setOnClickPendingIntent(R.id.bt_cancel, broadcast2);
        if (EnvironmentInfo.j() || CommonUtil.bm()) {
            Notification.Builder bLl_ = bLl_(this.e.getResources().getString(R$string.IDS_notification_title), bundle, null);
            bLl_.setContentText(b2);
            bLl_.setStyle(new Notification.BigTextStyle());
            bLl_.addAction(new Notification.Action.Builder((Icon) null, this.e.getResources().getString(R$string.IDS_notify_cancel), broadcast2).build());
            bLl_.addAction(new Notification.Action.Builder((Icon) null, this.e.getResources().getString(R$string.IDS_notify_agree), broadcast).build());
            bLq_(bLl_, bundle);
            return;
        }
        bLw_(remoteViews, bundle);
    }

    private void bLw_(RemoteViews remoteViews, Bundle bundle) {
        RemoteViews remoteViews2 = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_small_layout);
        remoteViews2.setTextViewText(R.id.notice_title, this.e.getResources().getString(R$string.IDS_notify_file_content_title));
        bLk_(remoteViews, remoteViews2, bundle, null);
    }

    private void bLt_(Bundle bundle, int i) {
        LogUtil.a("CloudFileNotificationUtils", "updateDialogFromUI, dialogStyle: ", Integer.valueOf(i));
        if (i != 4) {
            jzd.b().bLR_(bundle);
        }
        Intent intent = new Intent();
        intent.setClassName(this.e, "com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity");
        intent.setFlags(268435456);
        intent.putExtra("bundle", bundle);
        intent.putExtra(RewardKeys.DIALOG_TYPE, i);
        gnm.aPB_(BaseApplication.getContext(), intent);
    }

    private Intent bLo_(Bundle bundle, String str) {
        Intent intent = new Intent(str);
        intent.putExtras(bundle);
        intent.setPackage(BaseApplication.getAppPackage());
        return intent;
    }

    private void bLk_(RemoteViews remoteViews, RemoteViews remoteViews2, Bundle bundle, PendingIntent pendingIntent) {
        Notification.Builder bLl_ = bLl_(null, bundle, pendingIntent);
        bLl_.setCustomContentView(remoteViews2);
        bLl_.setCustomBigContentView(remoteViews);
        bLl_.setStyle(new Notification.DecoratedCustomViewStyle());
        bLq_(bLl_, bundle);
    }

    private Notification.Builder bLl_(String str, Bundle bundle, PendingIntent pendingIntent) {
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setAutoCancel(false);
        xf_.setShowWhen(false);
        xf_.setVisibility(-1);
        xf_.setSound(null);
        xf_.setPriority(0);
        if (pendingIntent != null) {
            xf_.setContentIntent(pendingIntent);
        }
        if (!TextUtils.isEmpty(str)) {
            xf_.setContentTitle(str);
        }
        xf_.setDeleteIntent(PendingIntent.getBroadcast(this.e, 0, bLo_(bundle, "notification_delete"), 201326592));
        return xf_;
    }

    private void bLq_(Notification.Builder builder, Bundle bundle) {
        Notification build = builder.build();
        bLp_(bundle, build.extras);
        if (!NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            if (this.b) {
                jzd.b().a();
                return;
            }
            return;
        }
        jdh.c().xh_(20220422, build);
    }

    private void bLp_(Bundle bundle, Bundle bundle2) {
        bundle2.putInt("notification_status", bundle.getInt("notification_status"));
        bundle2.putBundle("bundle", bundle);
    }

    protected void bLz_(int i, boolean z, Bundle bundle) {
        if (z) {
            bundle.putInt("notification_status", -1);
        }
        if (BaseApplication.isRunningForeground() && z) {
            bLt_(bundle, 2);
            bLu_(bundle);
            return;
        }
        RemoteViews remoteViews = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_layout);
        remoteViews.setViewVisibility(R.id.notice_layout, 8);
        if (z) {
            this.b = true;
            remoteViews.setViewVisibility(R.id.download_progress_error_layout, 0);
            remoteViews.setViewVisibility(R.id.download_progress_layout, 8);
        } else {
            if (this.b) {
                this.b = false;
            }
            bundle.putInt("notification_status", 1);
            remoteViews.setViewVisibility(R.id.download_progress_error_layout, 8);
            remoteViews.setViewVisibility(R.id.download_progress_layout, 0);
            remoteViews.setProgressBar(R.id.download_progress_bar, 100, i, false);
        }
        remoteViews.setViewVisibility(R.id.transfer_progress_layout, 8);
        remoteViews.setViewVisibility(R.id.transfer_error_layout, 8);
        int bLj_ = jyw.bLj_(bundle);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "cancel_file_download"), 201326592);
        remoteViews.setOnClickPendingIntent(R.id.bt_download_cancel, broadcast);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "retry_file_download"), 201326592);
        remoteViews.setOnClickPendingIntent(R.id.bt_download_retry, broadcast2);
        if (!EnvironmentInfo.j() && !CommonUtil.bm()) {
            bLv_(remoteViews, bundle, z);
        } else if (z) {
            bLm_(bundle, broadcast, broadcast2);
        } else {
            bLn_(bundle, i);
        }
    }

    private void bLv_(RemoteViews remoteViews, Bundle bundle, boolean z) {
        RemoteViews remoteViews2 = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_small_layout);
        String string = this.e.getResources().getString(R$string.IDS_notify_file_download_tips);
        if (z) {
            string = this.e.getResources().getString(R$string.IDS_notify_file_download_error_tips);
        }
        remoteViews2.setTextViewText(R.id.notice_title, string);
        bLk_(remoteViews, remoteViews2, bundle, null);
    }

    private void bLm_(Bundle bundle, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        String string = this.e.getResources().getString(R$string.IDS_notification_title);
        String string2 = this.e.getResources().getString(R$string.IDS_file_update_error_content);
        Notification.Builder bLl_ = bLl_(string, bundle, null);
        bLl_.setContentText(string2);
        bLl_.setStyle(new Notification.BigTextStyle());
        bLl_.addAction(new Notification.Action.Builder((Icon) null, this.e.getResources().getString(R$string.IDS_notify_cancel), pendingIntent).build());
        bLl_.addAction(new Notification.Action.Builder((Icon) null, this.e.getResources().getString(R$string.IDS_notify_retry), pendingIntent2).build());
        bLq_(bLl_, bundle);
    }

    private void bLn_(Bundle bundle, int i) {
        Notification.Builder bLl_ = bLl_(this.e.getResources().getString(R$string.IDS_notification_title), bundle, null);
        bLl_.setOngoing(true);
        bLl_.setContentText(String.format(Locale.ROOT, this.e.getResources().getString(R$string.IDS_notify_file_download_progress), jed.b(i, 2, 0)));
        bLl_.setProgress(100, i, false);
        bLq_(bLl_, bundle);
    }

    private void bLu_(Bundle bundle) {
        RemoteViews remoteViews = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_layout);
        remoteViews.setViewVisibility(R.id.notice_layout, 8);
        remoteViews.setViewVisibility(R.id.download_progress_error_layout, 0);
        remoteViews.setViewVisibility(R.id.download_progress_layout, 8);
        remoteViews.setViewVisibility(R.id.download_bt_layout, 8);
        PendingIntent activity = PendingIntent.getActivity(this.e, 20220422, NotificationUtil.aOv_(this.e), 201326592);
        if (EnvironmentInfo.j() || CommonUtil.bm()) {
            String string = this.e.getResources().getString(R$string.IDS_notification_title);
            String string2 = this.e.getResources().getString(R$string.IDS_file_download_error_content);
            Notification.Builder bLl_ = bLl_(string, bundle, activity);
            bLl_.setContentText(string2);
            bLl_.setStyle(new Notification.BigTextStyle());
            bLq_(bLl_, bundle);
            return;
        }
        RemoteViews remoteViews2 = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_small_layout);
        remoteViews2.setTextViewText(R.id.notice_title, this.e.getResources().getString(R$string.IDS_notify_file_download_error_tips));
        bLk_(remoteViews, remoteViews2, bundle, activity);
    }

    protected void bLB_(int i, Bundle bundle) {
        if (this.b) {
            this.b = false;
        }
        bundle.putInt("notification_status", 2);
        if (EnvironmentInfo.j() || CommonUtil.bm()) {
            Notification.Builder bLl_ = bLl_(this.e.getResources().getString(R$string.IDS_notification_title), bundle, null);
            String string = this.e.getResources().getString(R$string.IDS_file_transport_progress);
            bLl_.setOngoing(true);
            bLl_.setContentText(String.format(Locale.ROOT, string, jed.b(i, 2, 0)));
            bLl_.setProgress(100, i, false);
            bLq_(bLl_, bundle);
            return;
        }
        RemoteViews remoteViews = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_layout);
        remoteViews.setViewVisibility(R.id.notice_layout, 8);
        remoteViews.setViewVisibility(R.id.transfer_progress_layout, 0);
        remoteViews.setViewVisibility(R.id.transfer_error_layout, 8);
        remoteViews.setViewVisibility(R.id.download_progress_layout, 8);
        remoteViews.setProgressBar(R.id.transfer_progress_bar, 100, i, false);
        RemoteViews remoteViews2 = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_small_layout);
        remoteViews2.setTextViewText(R.id.notice_title, this.e.getResources().getString(R$string.IDS_file_transport_tips));
        bLk_(remoteViews, remoteViews2, bundle, null);
    }

    public void bLC_(boolean z, Bundle bundle) {
        bundle.putInt("notification_status", -1);
        if (BaseApplication.isRunningForeground()) {
            if (z) {
                bLt_(bundle, 3);
                bLx_(bundle);
                return;
            } else {
                d();
                bLt_(bundle, 4);
                return;
            }
        }
        this.b = true;
        RemoteViews remoteViews = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_layout);
        remoteViews.setViewVisibility(R.id.notice_layout, 8);
        if (z) {
            remoteViews.setViewVisibility(R.id.transfer_progress_layout, 8);
            remoteViews.setViewVisibility(R.id.transfer_error_layout, 0);
        } else {
            remoteViews.setViewVisibility(R.id.transfer_progress_layout, 0);
            remoteViews.setViewVisibility(R.id.bt_transfer_done, 0);
            remoteViews.setViewVisibility(R.id.download_progress_layout, 8);
            remoteViews.setViewVisibility(R.id.transfer_progress_bar, 8);
            remoteViews.setTextViewText(R.id.transfer_tips, this.e.getResources().getString(R$string.IDS_notify_file_transport_done));
        }
        remoteViews.setViewVisibility(R.id.download_progress_error_layout, 8);
        int bLj_ = jyw.bLj_(bundle);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "cancel_file_trans"), 201326592);
        remoteViews.setOnClickPendingIntent(R.id.bt_transfer_cancel, broadcast);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "retry_file_trans"), 201326592);
        remoteViews.setOnClickPendingIntent(R.id.bt_transfer_retry, broadcast2);
        remoteViews.setOnClickPendingIntent(R.id.bt_transfer_done, PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "trans_done"), 201326592));
        if (!EnvironmentInfo.j() && !CommonUtil.bm()) {
            bLy_(remoteViews, bundle, z);
        } else if (z) {
            bLs_(bundle, broadcast, broadcast2);
        } else {
            bLr_(bundle);
        }
    }

    private void bLy_(RemoteViews remoteViews, Bundle bundle, boolean z) {
        RemoteViews remoteViews2 = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_small_layout);
        String string = this.e.getResources().getString(R$string.IDS_notify_file_transport_done);
        if (z) {
            string = this.e.getResources().getString(R$string.IDS_file_transport_error_tips);
        }
        remoteViews2.setTextViewText(R.id.notice_title, string);
        bLk_(remoteViews, remoteViews2, bundle, null);
    }

    private void bLs_(Bundle bundle, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        String string = this.e.getResources().getString(R$string.IDS_notification_title);
        String string2 = this.e.getResources().getString(R$string.IDS_file_update_error_content);
        Notification.Builder bLl_ = bLl_(string, bundle, null);
        bLl_.setContentText(string2);
        bLl_.setStyle(new Notification.BigTextStyle());
        bLl_.addAction(new Notification.Action.Builder((Icon) null, this.e.getResources().getString(R$string.IDS_notify_cancel), pendingIntent).build());
        bLl_.addAction(new Notification.Action.Builder((Icon) null, this.e.getResources().getString(R$string.IDS_notify_retry), pendingIntent2).build());
        bLq_(bLl_, bundle);
    }

    private void bLr_(Bundle bundle) {
        if (EnvironmentInfo.j()) {
            String string = this.e.getResources().getString(R$string.IDS_notification_title);
            String string2 = this.e.getResources().getString(R$string.IDS_file_update_done);
            Notification.Builder bLl_ = bLl_(string, bundle, null);
            bLl_.setContentText(string2);
            bLl_.setShowWhen(true);
            bLq_(bLl_, bundle);
            return;
        }
        int bLj_ = jyw.bLj_(bundle);
        Notification.Builder bLl_2 = bLl_(this.e.getResources().getString(R$string.IDS_notify_file_transport_done), bundle, null);
        bLl_2.setProgress(100, 100, false);
        bLl_2.setStyle(new Notification.BigTextStyle());
        bLl_2.addAction(new Notification.Action.Builder((Icon) null, this.e.getResources().getString(R$string.IDS_notify_done), PendingIntent.getBroadcast(this.e, bLj_, bLo_(bundle, "trans_done"), 201326592)).build());
        bLq_(bLl_2, bundle);
    }

    private void bLx_(Bundle bundle) {
        RemoteViews remoteViews = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_layout);
        remoteViews.setViewVisibility(R.id.notice_layout, 8);
        remoteViews.setViewVisibility(R.id.transfer_progress_layout, 8);
        remoteViews.setViewVisibility(R.id.transfer_error_layout, 0);
        remoteViews.setViewVisibility(R.id.transfer_bt_layout, 8);
        PendingIntent activity = PendingIntent.getActivity(this.e, 20220422, NotificationUtil.aOv_(this.e), 201326592);
        if (EnvironmentInfo.j() || CommonUtil.bm()) {
            String string = this.e.getResources().getString(R$string.IDS_notification_title);
            String string2 = this.e.getResources().getString(R$string.IDS_file_update_error_content);
            Notification.Builder bLl_ = bLl_(string, bundle, activity);
            bLl_.setContentText(string2);
            bLl_.setStyle(new Notification.BigTextStyle());
            bLq_(bLl_, bundle);
            return;
        }
        RemoteViews remoteViews2 = new RemoteViews(this.e.getPackageName(), R.layout.cloud_file_file_notice_small_layout);
        remoteViews2.setTextViewText(R.id.notice_title, this.e.getResources().getString(R$string.IDS_file_transport_error_tips));
        bLk_(remoteViews, remoteViews2, bundle, activity);
    }

    public void d() {
        jdh.c().a(20220422);
    }

    public void c() {
        Notification notification;
        LogUtil.a("CloudFileNotificationUtils", "changeNotification");
        NotificationManager xB_ = CommonUtils.xB_();
        if (xB_ == null) {
            LogUtil.h("CloudFileNotificationUtils", "changeNotification, mManager is null");
            return;
        }
        try {
            StatusBarNotification[] activeNotifications = xB_.getActiveNotifications();
            if (activeNotifications == null) {
                LogUtil.h("CloudFileNotificationUtils", "activeNotifications is null");
                return;
            }
            int length = activeNotifications.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    notification = null;
                    break;
                }
                StatusBarNotification statusBarNotification = activeNotifications[i];
                if (statusBarNotification.getId() == 20220422) {
                    notification = statusBarNotification.getNotification();
                    break;
                }
                i++;
            }
            if (notification == null) {
                LogUtil.h("CloudFileNotificationUtils", "changeNotification, notification is null");
                return;
            }
            Bundle bundle = notification.extras;
            if (bundle == null) {
                LogUtil.h("CloudFileNotificationUtils", "extras is null");
                return;
            }
            Bundle bundle2 = bundle.getBundle("bundle");
            if (bundle2 == null) {
                LogUtil.h("CloudFileNotificationUtils", "notificationBundle is null");
                return;
            }
            int i2 = bundle2.getInt("notification_status");
            LogUtil.a("CloudFileNotificationUtils", "status is： ", Integer.valueOf(i2));
            if (i2 == 1) {
                bLz_(0, true, bundle2);
            } else if (i2 == 2) {
                bLC_(true, bundle2);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("CloudFileNotificationUtils", "changeNotification is error");
            xB_.cancel(20220422);
        }
    }
}
