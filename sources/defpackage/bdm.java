package defpackage;

import android.app.Notification;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes8.dex */
public class bdm {
    public static boolean pu_(Notification notification, String str) {
        return SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", false) && pt_(notification, str);
    }

    public static boolean pt_(Notification notification, String str) {
        if (notification != null && notification.extras != null) {
            try {
                int i = notification.extras.getInt("notification.live.type", 0);
                if (!TextUtils.isEmpty(str)) {
                    ReleaseLogUtil.e("Notify_LiveNotificationUtils", "onNotificationPosted or onNotificationRemoved liveInfo liveType0x20: ", Integer.valueOf(i), ",pkg=", str);
                }
                if (i != 0) {
                    if (a(str)) {
                        return true;
                    }
                }
                return false;
            } catch (IllegalArgumentException e) {
                LogUtil.b("LiveNotificationUtils", "isMatchLiveInfoAndEvent ERROR: ", ExceptionUtils.d(e));
            }
        }
        return false;
    }

    public static boolean a(String str) {
        if (!NotificationContentProviderUtil.j()) {
            ReleaseLogUtil.d("Notify_LiveNotificationUtils", "onNotificationPosted FILTERED because LIVEINFO_SWITCH_IS_OFF,pkg=", str);
            return false;
        }
        if (!d(str)) {
            ReleaseLogUtil.d("Notify_LiveNotificationUtils", "onNotificationPosted FILTERED because LIVEINFO_WHITELIST_INCAPABLE,pkg=", str);
            return false;
        }
        ReleaseLogUtil.e("Notify_LiveNotificationUtils", "onNotificationPosted or onNotificationRemoved received liveInfo allowing continue,pkg=", str);
        return true;
    }

    public static boolean d(String str) {
        List<String> c = NotificationContentProviderUtil.c(SharedPreferenceManager.e("NOTIFY_LIVE_LEVEL_VALUE", "NOTIFY_LIVE_LEVEL_VALUE", "levelDefault"));
        if (c != null) {
            return c.contains(str);
        }
        return false;
    }
}
