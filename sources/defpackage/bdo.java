package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.tencent.connect.common.Constants;
import health.compact.a.DeviceUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes8.dex */
public class bdo {
    public static final List<bdk> c = Collections.unmodifiableList(Arrays.asList(new bdk("com.huawei.calendar", "calendar_notification_main_channel_id", 1, 15), new bdk("com.huawei.calendar", "calendar_notification_screen_lock_channel_id", 1, 15), new bdk("com.huawei.calendar", "calendar_notification_later_channel_id", 1, 0), new bdk("com.huawei.calendar", "calendar_notification_refresh_channel_id", 1, 0), new bdk("com.android.calendar", "calendar_notification_main_channel_id", 1, 15), new bdk("com.android.calendar", "calendar_notification_screen_lock_channel_id", 1, 15), new bdk("com.android.calendar", "calendar_notification_later_channel_id", 1, 0), new bdk("com.android.calendar", "calendar_notification_refresh_channel_id", 1, 0), new bdk("com.hihonor.calendar", "calendar_notification_main_channel_id", 1, 15), new bdk("com.hihonor.calendar", "calendar_notification_screen_lock_channel_id", 1, 15), new bdk("com.hihonor.calendar", "calendar_notification_later_channel_id", 1, 0), new bdk("com.hihonor.calendar", "calendar_notification_refresh_channel_id", 1, 0), new bdk("com.whatsapp", "slient_notifications_", 0, 0), new bdk("com.gbox.com.whatsapp", "slient_notifications_", 0, 0)));

    public static boolean b() {
        return false;
    }

    public static String pf_(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(NotificationCompat.EXTRA_TITLE);
        Object obj = bundle.get(NotificationCompat.EXTRA_TITLE);
        if (TextUtils.isEmpty(string) && obj != null) {
            string = obj.toString();
        }
        Object obj2 = bundle.get(NotificationCompat.EXTRA_TITLE_BIG);
        return (string != null || obj2 == null) ? string : obj2.toString();
    }

    public static String pd_(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
        if (charSequence != null) {
            return charSequence.toString();
        }
        CharSequence charSequence2 = bundle.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT);
        if (charSequence2 == null) {
            return null;
        }
        return charSequence2.toString();
    }

    public static String pa_(Notification notification) {
        return notification != null ? notification.getChannelId() : "";
    }

    public static String pe_(StatusBarNotification statusBarNotification) {
        return statusBarNotification != null ? statusBarNotification.getTag() : "";
    }

    public static String oZ_(Notification notification) {
        return notification != null ? notification.category : "";
    }

    public static String pb_(Notification notification) {
        if (notification != null) {
            String group = notification.getGroup();
            if (!TextUtils.isEmpty(group)) {
                return group;
            }
        }
        return "";
    }

    public static String pg_(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            LogUtil.h("NotifiPush", "getPackageName statusBarNotification is null");
            return "";
        }
        return e(statusBarNotification.getPackageName());
    }

    public static String e(String str) {
        if (!TextUtils.isEmpty(str)) {
            String e = kiq.e("NotifiPush");
            if (!TextUtils.isEmpty(e) && str.contains(e)) {
                return e;
            }
        }
        return str;
    }

    public static void pl_(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            LogUtil.h("NotifiPush", "onNotificationPosted packageMessage: notification.extras IS NULL!");
            return;
        }
        try {
            pk_(bundle, bundle2);
            po_(bundle, bundle2);
            pn_(bundle, bundle2);
            pm_(bundle, bundle2);
        } catch (IllegalArgumentException e) {
            LogUtil.b("NotifiPush", "onNotificationPosted packageMessage IllegalArgumentException: ", ExceptionUtils.d(e));
        }
    }

    private static void pk_(Bundle bundle, Bundle bundle2) {
        int i = bundle.getInt("notification.live.operation");
        String ph_ = ph_(bundle, "notification.live.event", "OTHER");
        int i2 = bundle.getInt("notification.live.type");
        long j = bundle.getLong("notification.live.keepTime");
        bundle2.putInt("liveOperation", i);
        bundle2.putString("liveEvent", ph_);
        bundle2.putInt("liveType", i2);
        if (j > 3600) {
            j = 3600;
        }
        bundle2.putInt("liveKeepTime", (int) j);
    }

    public static String ph_(Bundle bundle, String str, String str2) {
        Object obj;
        String string = bundle.getString(str, str2);
        return (TextUtils.isEmpty(string) && (obj = bundle.get(str)) != null) ? obj.toString() : string;
    }

    private static void po_(Bundle bundle, Bundle bundle2) {
        String ph_ = ph_(bundle, "notification.live.titleOverlay", "");
        String ph_2 = ph_(bundle, "notification.live.contentOverlay", "");
        bundle2.putString("titleOverlay", k(ph_));
        bundle2.putString("contentOverlay", k(ph_2));
    }

    private static void pn_(Bundle bundle, Bundle bundle2) {
        Bundle bundle3 = bundle.getBundle("notification.live.feature");
        if (bundle3 == null) {
            return;
        }
        String ph_ = ph_(bundle3, "notification.live.feature.titleText", "");
        String ph_2 = ph_(bundle3, "notification.live.feature.contentText", "");
        bundle2.putString("featureTitleText", k(ph_));
        bundle2.putString("featureContentText", k(ph_2));
    }

    private static void pm_(Bundle bundle, Bundle bundle2) {
        boolean z = bundle.getBoolean("notification.live.externalEnable", false);
        bundle2.putBoolean("externalEnable", z);
        if (z) {
            String ph_ = ph_(bundle, "notification.live.externalTitle", "");
            String ph_2 = ph_(bundle, "notification.live.externalBody", "");
            bundle2.putString("externalTitle", k(ph_));
            bundle2.putString("externalBody", k(ph_2));
        }
    }

    private static String k(String str) {
        if (str.length() <= 128) {
            return str;
        }
        String substring = str.substring(128);
        LogUtil.a("NotifiPush", "param is oversize 128,subString content.");
        return substring;
    }

    public static boolean e() {
        if (cvz.a() == 1) {
            ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationRemoved filter isMidWareAuthority is true");
            return true;
        }
        if (!NotificationContentProviderUtil.h()) {
            ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationRemoved filter capability not support");
            return true;
        }
        if (DeviceUtil.a()) {
            return false;
        }
        ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationRemoved filter not device");
        return true;
    }

    public static boolean pi_(Notification notification, boolean z, String str) {
        if (notification == null) {
            LogUtil.h("NotifiPush", "onNotificationRemoved isFilterRemoveByFlay notification is null");
            return true;
        }
        int i = notification.flags;
        if ((i & 32) == 32 && !z) {
            ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationRemoved filter FLAG_NO_CLEAR");
            return true;
        }
        if ((i & 2) != 2 || z) {
            return false;
        }
        if (bdm.pu_(notification, str)) {
            ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationRemoved continue because it is a live type info");
            return false;
        }
        if (pj_(notification, null) && f(str)) {
            ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationRemoved continue because it is a live type info");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                LogUtil.b("NotifiPush", "onNotificationRemoved liveInfoDelete ERROR: ", ExceptionUtils.d(e));
            }
            return false;
        }
        ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationRemoved filter FLAG_ONGOING_EVENT");
        return true;
    }

    public static boolean pj_(Notification notification, String str) {
        if (notification != null && notification.extras != null) {
            try {
                int i = notification.extras.getInt("notification.live.type", 0);
                String ph_ = ph_(notification.extras, "notification.live.event", "OTHER");
                if (!TextUtils.isEmpty(str)) {
                    ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationPosted or onNotificationRemoved liveInfo liveType0x20: ", Integer.valueOf(i), ",liveEvent0x1F: ", ph_, ",pkg=", str);
                }
                if (i != 0) {
                    if (bff.b.contains(ph_.toUpperCase(Locale.ROOT))) {
                        return true;
                    }
                }
                return false;
            } catch (IllegalArgumentException e) {
                LogUtil.b("NotifiPush", "isMatchLiveInfoAndEvent ERROR: ", ExceptionUtils.d(e));
            }
        }
        return false;
    }

    public static boolean f(String str) {
        if (!NotificationContentProviderUtil.j()) {
            ReleaseLogUtil.d("Notify_NotifiPush", "onNotificationPosted FILTERED because LIVEINFO_SWITCH_IS_OFF,pkg=", str);
            return false;
        }
        if (!n(str)) {
            ReleaseLogUtil.d("Notify_NotifiPush", "onNotificationPosted FILTERED because LIVEINFO_WHITELIST_INCAPABLE,pkg=", str);
            return false;
        }
        ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationPosted or onNotificationRemoved received liveInfo allowing continue,pkg=", str);
        return true;
    }

    public static boolean n(String str) {
        List<String> b = NotificationContentProviderUtil.b();
        if (b != null) {
            return b.contains(str);
        }
        return false;
    }

    public static int c(String str, String str2, String str3, boolean z) {
        if (!TextUtils.isEmpty(str) && !g(str)) {
            if (z) {
                LogUtil.a("NotifiPush", "onNotificationPosted or onNotificationRemoved CONSIDERING_GOOGLESMS_IS_RCS");
                return 127;
            }
            if (TextUtils.equals(str, kiq.e("NotifiPush"))) {
                return 2;
            }
            if (TextUtils.equals(str, "com.tencent.mm")) {
                return 3;
            }
            if (l(str)) {
                return 11;
            }
            if (b(str, str2, str3)) {
                return 14;
            }
            if (a(str)) {
                return 15;
            }
            if (TextUtils.equals(str, bfg.e)) {
                return 128;
            }
            ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationPosted or onNotificationRemoved getMsgType type is else");
        }
        return 127;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean j(String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -990893723:
                if (str.equals("com.hihonor.contacts")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -969203187:
                if (str.equals("com.huawei.contacts")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -845193793:
                if (str.equals("com.android.contacts")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -197901245:
                if (str.equals("com.android.incallui")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -120126428:
                if (str.equals("com.google.android.dialer")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 200355416:
                if (str.equals("com.android.server.telecom")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 285500553:
                if (str.equals("com.android.dialer")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 708520957:
                if (str.equals("com.samsung.android.dialer")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1544296322:
                if (str.equals("com.android.phone")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
                return true;
            default:
                return false;
        }
    }

    public static boolean l(String str) {
        return TextUtils.equals(str, "com.tencent.mqq") || TextUtils.equals(str, "com.tencent.mobileqq") || TextUtils.equals(str, "com.tencent.mobileqqi") || TextUtils.equals(str, Constants.PACKAGE_QQ_PAD) || TextUtils.equals(str, Constants.PACKAGE_QQ_SPEED) || TextUtils.equals(str, "com.tencent.eim");
    }

    public static boolean b(String str, String str2, String str3) {
        if (TextUtils.equals(str, "com.android.phone") && !TextUtils.isEmpty(str2) && str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL")) {
            return true;
        }
        if (TextUtils.equals(str, "com.android.phone") && !TextUtils.isEmpty(str3) && str3.toUpperCase(Locale.ENGLISH).contains("PHONE_MISS")) {
            ReleaseLogUtil.e("Notify_NotifiPush", "isCallPost PHONE_MISS channel found");
            return true;
        }
        return a(str, str2, str3);
    }

    public static boolean a(String str, String str2, String str3) {
        if (TextUtils.equals(str, "com.android.server.telecom")) {
            return true;
        }
        if (TextUtils.equals(str, "com.oneplus.dialer") && !TextUtils.isEmpty(str2) && str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL")) {
            return true;
        }
        if (TextUtils.equals(str, "com.samsung.android.dialer") && !TextUtils.isEmpty(str2) && str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL")) {
            return true;
        }
        if (TextUtils.equals(str, "com.samsung.android.dialer") && !TextUtils.isEmpty(str3) && str3.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL")) {
            return true;
        }
        if (TextUtils.equals(str, "com.android.dialer") && !TextUtils.isEmpty(str2) && str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL")) {
            return true;
        }
        boolean e = e(str, str2, str3);
        if (e) {
            return e;
        }
        if (!TextUtils.equals(str, "com.android.incallui") || TextUtils.isEmpty(str3)) {
            return false;
        }
        return str3.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL");
    }

    private static boolean e(String str, String str2, String str3) {
        if (TextUtils.equals(str, "com.android.dialer") && !TextUtils.isEmpty(str3) && str3.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL")) {
            return true;
        }
        if (TextUtils.equals(str, "com.google.android.dialer") && !TextUtils.isEmpty(str2) && str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL")) {
            return true;
        }
        boolean z = !TextUtils.isEmpty(str2) && str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL");
        boolean z2 = !TextUtils.isEmpty(str3) && str3.toUpperCase(Locale.ENGLISH).contains("PHONE_MISS");
        if (TextUtils.equals(str, "com.android.contacts") && (z || z2)) {
            return true;
        }
        if (TextUtils.equals(str, "com.huawei.contacts") && !TextUtils.isEmpty(str2)) {
            return str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL");
        }
        if (!TextUtils.equals(str, "com.hihonor.contacts") || TextUtils.isEmpty(str2)) {
            return TextUtils.equals(str, "com.huawei.meetime") && !TextUtils.isEmpty(str3) && str3.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL");
        }
        return str2.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean a(String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -480218078:
                if (str.equals("com.huawei.email")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -253727798:
                if (str.equals("com.hihonor.email")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1534272944:
                if (str.equals("com.android.email")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1841532656:
                if (str.equals("com.netease.mobimail")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        return c2 == 0 || c2 == 1 || c2 == 2 || c2 == 3;
    }

    public static boolean d(String str) {
        return TextUtils.equals(str, "com.android.dialer") || TextUtils.equals(str, "com.samsung.android.dialer") || TextUtils.equals(str, "com.google.android.dialer");
    }

    public static boolean b(String str) {
        return TextUtils.equals(str, "com.android.contacts") || TextUtils.equals(str, "com.huawei.contacts") || TextUtils.equals(str, "com.hihonor.contacts");
    }

    public static boolean c(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("NotifiPush", "onNotificationPosted isFilterForMissCall PACKAGENAME_IS_EMPTY");
            return true;
        }
        boolean m = m(str2);
        boolean m2 = m(str3);
        TextUtils.equals(str, "com.android.phone");
        if (d(str) && !m && !m2) {
            ReleaseLogUtil.d("Notify_NotifiPush", "onNotificationPosted FAILED: ", str, " is not missedCall");
            return true;
        }
        if (b(str) && !m) {
            if (!TextUtils.isEmpty(str3) && str3.toUpperCase(Locale.ENGLISH).contains("PHONE_MISS")) {
                LogUtil.a("NotifiPush", "onNotificationPosted missedCall continue.");
                return false;
            }
            ReleaseLogUtil.d("Notify_NotifiPush", "onNotificationPosted FAILED: ", str, " is not missedCall");
            return true;
        }
        if (TextUtils.equals(str, "com.android.incallui") && !m && !m2) {
            ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationPosted FAILED: ", str, " is not missedCall");
            return true;
        }
        if (!TextUtils.equals(str, "com.oneplus.dialer") || m || m2) {
            return false;
        }
        ReleaseLogUtil.e("Notify_NotifiPush", "onNotificationPosted FAILED: ", str, " is not missedCall");
        return true;
    }

    private static boolean m(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toUpperCase(Locale.ENGLISH).contains("MISSEDCALL");
    }

    public static boolean i(String str) {
        return TextUtils.equals(str, "com.instagram.android") || TextUtils.equals(str, "com.gbox.com.instagram.android");
    }

    public static boolean o(String str) {
        return TextUtils.equals(str, "com.whatsapp") || TextUtils.equals(str, "com.gbox.com.whatsapp");
    }

    public static boolean g(String str) {
        return TextUtils.equals(str, "com.facebook.orca");
    }

    public static String pc_(Notification.Action action) {
        try {
            Intent intent = (Intent) PendingIntent.class.getDeclaredMethod("getIntent", new Class[0]).invoke(action.actionIntent, new Object[0]);
            if (intent != null && intent.getAction() != null) {
                return intent.getAction();
            }
            return "";
        } catch (Exception e) {
            LogUtil.b("NotifiPush", "getIntentAction occur exception:", ExceptionUtils.d(e));
        }
        return "";
    }

    public static boolean h(String str) {
        return TextUtils.equals("com.google.android.apps.messaging", str);
    }

    public static boolean e(String str, long j) {
        if (!h(str) || j == 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList(10);
        khs.a(j, arrayList);
        if (!arrayList.isEmpty() && arrayList.get(0) != null) {
            LogUtil.a("NotifiPush", "onNotificationPosted or onNotificationRemoved googleSms received SMS_FROM_INBOX,amount: ", Integer.valueOf(arrayList.size()));
            return false;
        }
        ReleaseLogUtil.d("Notify_NotifiPush", "onNotificationPosted or onNotificationRemoved GOOGLESMS_RCS_MESSAGE_SPECIFICATIONS_INCAPABLE");
        return true;
    }

    public static boolean c(String str) {
        boolean z;
        String e = SharedPreferenceManager.e(Integer.toString(10008), str, "");
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if (TextUtils.isEmpty(e) || !TextUtils.equals(format, e)) {
            SharedPreferenceManager.c(Integer.toString(10008), str, format);
            z = true;
        } else {
            z = false;
        }
        LogUtil.a("NotifiPush", "isAddDotBecauseHideText isNeedDot=", Boolean.valueOf(z));
        return z;
    }

    public static void e(String str, long j, long j2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        long j3 = j - j2;
        ReleaseLogUtil.e("Notify_NotifiPush", str, " require milliSeconds:", Long.valueOf(j3));
        sqo.ab(str + ":" + j3);
    }
}
