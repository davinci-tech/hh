package defpackage;

import android.content.ComponentName;
import android.content.res.Resources;
import android.os.Message;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcoresleepmgr.datatype.NotificationData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.util.IllegalFormatException;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jem {
    public static boolean bGA_(Message message, long j, long j2) {
        long j3;
        boolean z;
        if (message == null) {
            ReleaseLogUtil.d("BTSYNC_SyncNotifyControllerUtil", "shouldSendSleepNotify message null");
            return false;
        }
        if (message.obj instanceof NotificationData) {
            NotificationData notificationData = (NotificationData) message.obj;
            long startTime = notificationData.getStartTime();
            j3 = notificationData.getEndTime();
            if (j3 < 1000 * j) {
                LogUtil.h("SyncNotifyControllerUtil", "requestStartTime is ", Long.valueOf(j), ",wakeTime is ", Long.valueOf(j3), ",device return info is not in ", Long.valueOf(j), Constants.LINK, Long.valueOf(j2));
                ReleaseLogUtil.d("BTSYNC_SyncNotifyControllerUtil", "shouldSendSleepNotify wakeTime is false");
                return false;
            }
            double d = ((j3 - startTime) * 1.0d) / 3600000.0d;
            z = d > 3.0d;
            LogUtil.a("SyncNotifyControllerUtil", "saveCoreSleepData notify enter,the last data's fallAsleepTime :", Long.valueOf(startTime), "wakeTime :", Long.valueOf(j3), "duration :", Double.valueOf(d), "isRunningForeground : ", Boolean.valueOf(BaseApplication.isRunningForeground()));
        } else {
            j3 = 0;
            z = false;
        }
        if (VersionControlUtil.isSupportSleepManagement()) {
            ReleaseLogUtil.e("BTSYNC_SyncNotifyControllerUtil", "shouldSendSleepNotify isShouldNotify is ", Boolean.valueOf(z), ", message.arg1 is ", Integer.valueOf(message.arg1), ", message.arg2 is ", Integer.valueOf(message.arg2));
            return z && message.arg1 == 0 && nhy.d(j3) && message.arg2 == 0;
        }
        ReleaseLogUtil.e("BTSYNC_SyncNotifyControllerUtil", "shouldSendSleepNotify isShouldNotify is ", Boolean.valueOf(z), " message.arg1 is", Integer.valueOf(message.arg1));
        return z && message.arg1 == 0 && nhy.d(j3);
    }

    public static NotificationData d(long j, long j2, int i) {
        String string;
        try {
            LogUtil.a("SyncNotifyControllerUtil", "enter createSleepNotificationData,notify fallAsleepTime is", Long.valueOf(j), "wake Time is", Long.valueOf(j2), " sleepScore is ", Integer.valueOf(i));
            int nextInt = new SecureRandom().nextInt(3);
            if (nextInt == 0) {
                string = BaseApplication.getContext().getResources().getString(R.string.IDS_notification_sleep_msg_one);
            } else if (nextInt == 1) {
                string = BaseApplication.getContext().getResources().getString(R.string.IDS_notification_sleep_msg_two);
            } else {
                string = BaseApplication.getContext().getResources().getString(R.string.IDS_notification_sleep_msg_three);
            }
            return a(j, j2, "messagecenter://sleepDetail", BaseApplication.getContext().getResources().getString(R.string.IDS_hw_show_notifications_to_sleep_details_title), String.format(Locale.ENGLISH, string, Integer.valueOf(i)));
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("SyncNotifyControllerUtil", "sleep resource value not found.");
            return null;
        } catch (IllegalFormatException unused2) {
            LogUtil.b("SyncNotifyControllerUtil", "date format is invalid.");
            return null;
        }
    }

    public static NotificationData a(long j, long j2, String str, String str2, String str3) {
        NotificationData notificationData = new NotificationData();
        notificationData.setComponentName(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity"));
        notificationData.setStartTime(j);
        notificationData.setEndTime(j2);
        notificationData.setFlag("jumpFromFileSyncNotify");
        notificationData.setDetailUri(str);
        notificationData.setMsgId("fileSyncNotify");
        notificationData.setDescription(str3);
        notificationData.setTitle(str2);
        LogUtil.a("SyncNotifyControllerUtil", "progressNotificationData success,", notificationData.toString());
        return notificationData;
    }
}
