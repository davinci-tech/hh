package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.datatype.NotificationData;
import com.huawei.haf.common.base.BaseNotification;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Utils;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jrn {

    /* renamed from: a, reason: collision with root package name */
    private static jrn f14037a;
    private static final Object d = new Object();
    private int b = 0;
    private Context c;

    private jrn(Context context) {
        this.c = context;
    }

    public static jrn b(Context context) {
        jrn jrnVar;
        synchronized (d) {
            if (f14037a == null) {
                f14037a = new jrn(context);
            }
            jrnVar = f14037a;
        }
        return jrnVar;
    }

    public void d(NotificationData notificationData, BaseNotification baseNotification) {
        a(notificationData, false, baseNotification);
    }

    public void a(NotificationData notificationData, boolean z, BaseNotification baseNotification) {
        if (notificationData == null) {
            LogUtil.h("SyncNotifyController", "notificationData is null");
            return;
        }
        if (!CommonUtil.bh() && !NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            LogUtil.h("SyncNotifyController", "notifyMsg Notification bar permission is not enabled.");
            return;
        }
        LogUtil.a("SyncNotifyController", "enter notify ,notificationData is", notificationData.toString());
        int e = e(notificationData.getDetailUri());
        Intent intent = new Intent();
        intent.setComponent(notificationData.getComponentName());
        intent.putExtra("startTime", notificationData.getStartTime());
        intent.putExtra("endTime", notificationData.getEndTime());
        intent.putExtra(com.huawei.health.messagecenter.model.CommonUtil.DETAIL_URI, notificationData.getDetailUri());
        intent.putExtra("jumpFromFileSyncNotify", notificationData.getFlag());
        intent.putExtra("msgId", notificationData.getMsgId());
        PendingIntent activity = PendingIntent.getActivity(this.c, e, intent, 201326592);
        Notification.Builder xf_ = baseNotification.xf_();
        xf_.setSmallIcon(R$drawable.healthlogo_ic_notification);
        xf_.setAutoCancel(true);
        xf_.setContentTitle(notificationData.getTitle());
        xf_.setContentText(notificationData.getDescription());
        xf_.setContentIntent(activity);
        xf_.setStyle(new Notification.BigTextStyle().bigText(notificationData.getDescription()));
        xf_.setGroupSummary(z);
        xf_.setPriority(0);
        baseNotification.xh_(e, xf_.build());
        if (notificationData.getDetailUri().contains("messagecenter://sleepDetail")) {
            LogUtil.a("SyncNotifyController", "sleep notification show success!");
            b(OperationKey.SLEEP_FILE_SYNC_SEND_NOTIFICATION_2119014.value());
        } else {
            LogUtil.a("SyncNotifyController", "sport notification show success!");
            b(OperationKey.SPORT_FILE_SYNC_SEND_NOTIFICATION_2119015.value());
        }
    }

    public void a() {
        jdh.c().b();
    }

    private void b(String str) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("click", String.valueOf(1));
        OpAnalyticsUtil.getInstance().setEvent2nd(str, linkedHashMap);
    }

    public static NotificationData d() {
        if (!e() || Utils.o()) {
            return null;
        }
        LogUtil.a("SyncNotifyController", "enter createEcgNotificationData");
        return c(0L, 0L, "messagecenter://ecgDetail?ecgPageType=collection", BaseApplication.getContext().getResources().getString(R$string.IDS_quick_app_ecg), BaseApplication.getContext().getResources().getString(R$string.IDS_notifications_ecg_record));
    }

    public static NotificationData c() {
        if (!e()) {
            return null;
        }
        LogUtil.a("SyncNotifyController", "enter createEcgAnalysisNotificationData");
        return c(0L, 0L, "messagecenter://ecgDetail?ecgPageType=electrocardiogram", BaseApplication.getContext().getResources().getString(R$string.IDS_quick_app_ecg_analysis), BaseApplication.getContext().getResources().getString(R$string.IDS_notifications_ecg_details));
    }

    private static boolean e() {
        LogUtil.a("SyncNotifyController", "createEcgNotificationData : ", KeyValDbManager.b(BaseApplication.getContext()).e("health_msg_switch_noticebar"));
        return !"0".equals(r0);
    }

    public static NotificationData b() {
        LogUtil.a("SyncNotifyController", "enter createSportNotificationData");
        return c(0L, 0L, "messagecenter://sportHistory", BaseApplication.getContext().getResources().getString(BaseApplication.getContext().getResources().getIdentifier("IDS_hw_show_notifications_sport_title", "string", BaseApplication.getAppPackage())), BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_notifications_to_sport));
    }

    public static NotificationData b(String str, Map<String, String> map, String str2, String str3) {
        LogUtil.a("SyncNotifyController", "enter createHealthNotificationData");
        Uri.Builder authority = new Uri.Builder().scheme("messagecenter").authority(str);
        authority.appendQueryParameter("title", str2);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                authority.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        return c(0L, 0L, authority.toString(), str2, str3);
    }

    public static NotificationData c(long j, long j2, String str, String str2, String str3) {
        NotificationData notificationData = new NotificationData();
        notificationData.setComponentName(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity"));
        notificationData.setStartTime(j);
        notificationData.setEndTime(j2);
        notificationData.setFlag("jumpFromFileSyncNotify");
        notificationData.setDetailUri(str);
        notificationData.setMsgId("fileSyncNotify");
        notificationData.setDescription(str3);
        if (!TextUtils.isEmpty(str2)) {
            notificationData.setTitle(str2);
        }
        LogUtil.a("SyncNotifyController", "progressNotificationData success,", notificationData.toString());
        return notificationData;
    }

    private int e(String str) {
        if (!TextUtils.isEmpty(str) && str.contains("messagecenter://sleepDetail")) {
            return 6001;
        }
        if (!TextUtils.isEmpty(str) && str.contains("messagecenter://sportHistory")) {
            return 7001;
        }
        if (!TextUtils.isEmpty(str) && str.contains("messagecenter://ecgDetail?ecgPageType=electrocardiogram")) {
            return 1784534443;
        }
        if (!TextUtils.isEmpty(str) && str.contains("messagecenter://ecgDetail?ecgPageType=collection")) {
            return 100233;
        }
        if (!TextUtils.isEmpty(str) && str.contains("messagecenter://healthDetail")) {
            if (Uri.parse(str) == null) {
                return this.b;
            }
            return Uri.parse(str).getQueryParameter("title").hashCode();
        }
        if (!TextUtils.isEmpty(str) && str.contains("sleep-management")) {
            return -1846814471;
        }
        int i = this.b;
        if (i < 5000) {
            this.b = i + 1;
        } else {
            this.b = 0;
        }
        return this.b;
    }
}
