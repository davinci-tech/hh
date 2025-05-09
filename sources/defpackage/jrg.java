package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.MagicBuild;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jrg {
    private static boolean b = false;
    private static final Object e = new Object();
    private static Map<String, ArrayList<Long>> c = new ConcurrentHashMap(16);
    private static Handler d = new Handler(Looper.getMainLooper()) { // from class: jrg.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 10) {
                return;
            }
            LogUtil.a("HwNotificationUtil", "handleMessage negotiate again");
            if (message.obj instanceof DeviceInfo) {
                LogUtil.a("HwNotificationUtil", "handleMessage negotiate autoSetup");
                khe.b().e((DeviceInfo) message.obj, false);
            }
        }
    };

    public static boolean a() {
        return b;
    }

    public static void d(boolean z) {
        b = z;
    }

    private jrg() {
    }

    public static DeviceCommand d(boolean z, String str) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(4);
        byte[] a2 = cvx.a((cvx.e(129) + cvx.e(6)) + (cvx.e(2) + cvx.e(1) + cvx.e(z ? 1 : 0)) + (cvx.e(3) + cvx.e(1) + cvx.e(z ? 1 : 0)));
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        LogUtil.a(str, "NotificationEable deviceCommand," + ((cvx.e(deviceCommand.getServiceID()) + cvx.e(deviceCommand.getCommandID())) + cvx.d(deviceCommand.getDataContent())));
        return deviceCommand;
    }

    public static ContentResolver bJc_(Context context, String str) {
        String str2 = (CommonUtil.bj() || (!CommonUtil.y(context) && MagicBuild.f13130a)) ? "com.hihonor.settings.action.OPEN_TO_EXTERNAL_APPS_PROVIDER" : "com.huawei.settings.action.OPEN_TO_EXTERNAL_APPS_PROVIDER";
        LogUtil.a(str, "getNotificationEnableContentResolver action:", str2);
        Intent intent = new Intent(str2);
        intent.setPackage("com.android.settings");
        List<ResolveInfo> queryIntentContentProviders = context.getPackageManager().queryIntentContentProviders(intent, 0);
        if (queryIntentContentProviders == null || queryIntentContentProviders.size() == 0) {
            LogUtil.a(str, "getNotificationEnableContentResolver no providers");
            sqo.z("getNotificationEnableContentResolver no providers");
            return null;
        }
        Iterator<ResolveInfo> it = queryIntentContentProviders.iterator();
        while (it.hasNext()) {
            if (it.next().providerInfo.authority.equals("com.android.settings.open_to_externalapps")) {
                ContentResolver contentResolver = context.getContentResolver();
                LogUtil.a(str, "getNotificationEnableContentResolver find matched providers");
                return contentResolver;
            }
        }
        LogUtil.a(str, "getNotificationEnableContentResolver no matched providers");
        sqo.z("getNotificationEnableContentResolver no matched providers");
        return null;
    }

    public static boolean bJe_(ContentResolver contentResolver, boolean z, String str) {
        LogUtil.a(str, "setNotificationEnable,isOpen = " + z);
        if (contentResolver == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putString("operation", k.g);
        } else {
            bundle.putString("operation", "disable");
        }
        try {
            Bundle call = contentResolver.call(Uri.parse("content://com.android.settings.open_to_externalapps"), "changeNotificationAccessSettings", BaseApplication.getAppPackage(), bundle);
            if (call != null && call.getBoolean("is_operation_succ")) {
                return true;
            }
            LogUtil.a(str, "openNotification failed.");
            return false;
        } catch (IllegalArgumentException e2) {
            LogUtil.a(str, "some error in openNotificationEnable！" + e2.getMessage());
            sqo.z("some error in openNotificationEnable");
            return false;
        } catch (SecurityException e3) {
            LogUtil.a(str, "some error in openNotificationEnable！" + e3.getMessage());
            sqo.z("some error in openNotificationEnable");
            return false;
        }
    }

    public static Bitmap bJb_(Context context, String str) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(str, "mipmap", applicationInfo.packageName);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        return BitmapFactory.decodeResource(resources, identifier, options);
    }

    public static String b(String str) {
        try {
            PackageManager packageManager = BaseApplication.getContext().getPackageManager();
            return packageManager.getPackageInfo(str, 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HwNotificationUtil", "getAppName NameNotFoundException");
            return "";
        }
    }

    public static PackageInfo bJd_(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = BaseApplication.getContext().getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HwNotificationUtil", "getPackageInfo, NameNotFoundException.");
            packageInfo = null;
        }
        LogUtil.c("HwNotificationUtil", "getPackageInfo completed.");
        return packageInfo;
    }

    public static byte[] bIZ_(String str, Drawable drawable) {
        if (drawable == null && !TextUtils.isEmpty(str)) {
            LogUtil.h("HwNotificationUtil", "getBitmapByteArray iconDrawable is null. pkgName:", str);
            drawable = bJa_(str);
        }
        if (!(drawable instanceof BitmapDrawable)) {
            LogUtil.h("HwNotificationUtil", "getBitmapByteArray, instanceof fail.");
            return new byte[0];
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap == null) {
            LogUtil.h("HwNotificationUtil", "getBitmapByteArray, bitmap is null.");
            return new byte[0];
        }
        return nrf.cHV_(nrf.cJx_(bitmap, 100, 100), true);
    }

    public static Drawable bJa_(String str) {
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null) {
            LogUtil.h("HwNotificationUtil", "getDrawableByPkgName packageManager is null");
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                LogUtil.h("HwNotificationUtil", "getDrawableByPkgName packageInfo is null");
                return null;
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null) {
                LogUtil.h("HwNotificationUtil", "getDrawableByPkgName applicationInfo is null");
                return null;
            }
            return packageManager.getApplicationIcon(applicationInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HwNotificationUtil", "getDrawableByPkgName NameNotFoundException");
            return null;
        }
    }

    public static boolean b() {
        return e(BaseApplication.getContext().getPackageName());
    }

    public static boolean e(String str) {
        LogUtil.a("HwNotificationUtil", "packageName:", str);
        String string = Settings.Secure.getString(BaseApplication.getContext().getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            if (CommonUtil.as()) {
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("getFlagError", "Model:" + Build.MODEL + ", notificationAuthorizeEnable flag is empty, isHuaweiSystem " + CommonUtil.bh());
            }
            ReleaseLogUtil.d("DEVMGR_HwNotificationUtil", "notificationAuthorizeEnable flag is empty");
            return false;
        }
        for (String str2 : string.split(":")) {
            ComponentName unflattenFromString = ComponentName.unflattenFromString(str2);
            if (unflattenFromString != null && TextUtils.equals(str, unflattenFromString.getPackageName())) {
                LogUtil.a("HwNotificationUtil", "isNotificationAuthorizeEnabled packageName:", unflattenFromString.getPackageName());
                return true;
            }
        }
        return false;
    }

    public static void bIY_(StatusBarNotification statusBarNotification, String str) {
        if (statusBarNotification == null) {
            LogUtil.h("HwNotificationUtil", "deviceReply StatusBarNotification is null");
            return;
        }
        Notification.Action[] actionArr = statusBarNotification.getNotification().actions;
        String str2 = "deviceReply: " + statusBarNotification.getPackageName();
        String str3 = "deviceReply: " + statusBarNotification.getKey();
        if (actionArr == null || actionArr.length == 0) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationUtil", str2, " has no action button so that deviceReply INCAPABLE!");
            return;
        }
        for (int i = 0; i < actionArr.length; i++) {
            Notification.Action action = actionArr[i];
            if (action != null) {
                if (action.getRemoteInputs() == null) {
                    LogUtil.a("HwNotificationUtil", str2, " has ", Integer.valueOf(actionArr.length), " actions,and No.", Integer.valueOf(i + 1), " actionButton named ", actionArr[i].title, " deviceReply INCAPABLE!");
                } else {
                    LogUtil.a("HwNotificationUtil", str2, " has ", Integer.valueOf(actionArr.length), " actionButtons,and No.", Integer.valueOf(i + 1), " actionButton named ", actionArr[i].title, " deviceReply SUPPORT! key: ", str3);
                    Bundle bundle = new Bundle();
                    RemoteInput[] remoteInputs = actionArr[i].getRemoteInputs();
                    for (RemoteInput remoteInput : remoteInputs) {
                        bundle.putCharSequence(remoteInput.getResultKey(), str);
                    }
                    Intent intent = new Intent();
                    intent.addFlags(268435456);
                    RemoteInput.addResultsToIntent(remoteInputs, intent, bundle);
                    ReleaseLogUtil.e("DEVMGR_HwNotificationUtil", str2, " deviceReply start to send");
                    try {
                        actionArr[i].actionIntent.send(BaseApplication.getContext(), 0, intent);
                    } catch (PendingIntent.CanceledException e2) {
                        ReleaseLogUtil.c("DEVMGR_HwNotificationUtil", "deviceReply function CanceledException: ", ExceptionUtils.d(e2));
                    }
                }
            }
        }
        ReleaseLogUtil.e("DEVMGR_HwNotificationUtil", str2, " deviceReply finished");
    }

    public static void b(byte[] bArr, DeviceInfo deviceInfo) {
        cwl cwlVar = new cwl();
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() <= 4) {
            LogUtil.a("HwNotificationUtil", "processMiddleWearResult info.length() less than 4");
            return;
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            LogUtil.h("HwNotificationUtil", "processMiddleWearResult dataInfos is invalid");
            return;
        }
        if (bArr[1] != 2) {
            LogUtil.h("HwNotificationUtil", "processMiddleWearResult not is 5.33.2");
            return;
        }
        String substring = d2.substring(4);
        LogUtil.a("HwNotificationUtil", "processMiddleWearResult tlvString: ", substring);
        try {
            boolean d3 = d(cwlVar.a(substring).a(), deviceInfo);
            LogUtil.a("HwNotificationUtil", "processMiddleWearResult isMiddleWearSupportHealth: ", Boolean.valueOf(d3));
            if (!d3) {
                LogUtil.h("HwNotificationUtil", "processMiddleWearResult middleWear is not Support Health");
                return;
            }
        } catch (cwg unused) {
            LogUtil.b("HwNotificationUtil", "processMiddleWearResult TlvException");
            sqo.aa("processMiddleWearResult TlvException");
        }
        e(deviceInfo);
    }

    private static boolean d(List<cwe> list, DeviceInfo deviceInfo) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HwNotificationUtil", "processData tlvFatherList is null");
            return true;
        }
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            List<cwd> e2 = it.next().e();
            LogUtil.a("HwNotificationUtil", "tlvList.size = ", Integer.valueOf(e2.size()));
            if (e2.size() >= 3) {
                b = true;
                LogUtil.a("HwNotificationUtil", "processData 2102 ");
            }
            for (cwd cwdVar : e2) {
                if (CommonUtil.w(cwdVar.e()) != 2) {
                    LogUtil.a("HwNotificationUtil", "processData command id is not MiddleWear");
                    return true;
                }
                LogUtil.a("HwNotificationUtil", "processData callback result value:" + CommonUtil.w(cwdVar.c()));
                int w = CommonUtil.w(cwdVar.c()) / 256;
                int w2 = CommonUtil.w(cwdVar.c()) % 256;
                ReleaseLogUtil.e("DEVMGR_HwNotificationUtil", "processData callback result type:", Integer.valueOf(w), " value:", Integer.valueOf(w2));
                if (w == 1) {
                    a(w2 == 0);
                    c(w, w2);
                } else if (w == 2) {
                    e(w2 == 0);
                    c(deviceInfo, w2);
                    c(w2);
                } else if (w == 3) {
                    b(w2 == 0);
                } else if (w == 4) {
                    LogUtil.a("HwNotificationUtil", "processData alarm clock featureInfoValue:", Integer.valueOf(w2));
                } else {
                    LogUtil.a("HwNotificationUtil", "The featureInfoType is invalid");
                }
            }
        }
        return true;
    }

    private static void c(int i) {
        if (i == 0) {
            iyv.d(2);
        } else {
            iyv.d(1);
        }
    }

    private static void c(int i, int i2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put("negotiateType", String.valueOf(i));
        if (i2 == 0) {
            linkedHashMap.put("negotiateResult", "true");
        } else {
            linkedHashMap.put("negotiateResult", "false");
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
    }

    private static void c(DeviceInfo deviceInfo, int i) {
        if (i == 0) {
            c.remove(deviceInfo.getDeviceIdentify());
        }
        if (i != 0) {
            d(deviceInfo);
            boolean a2 = a(deviceInfo.getDeviceIdentify());
            LogUtil.a("HwNotificationUtil", "processData isAllowNegotiateAgain: ", Boolean.valueOf(a2));
            if (a2) {
                LogUtil.a("HwNotificationUtil", "processData negotiate again msg.");
                Message obtain = Message.obtain();
                obtain.what = 10;
                obtain.obj = deviceInfo;
                d.sendMessageDelayed(obtain, 60000L);
            }
        }
    }

    private static void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationUtil", "addDeviceInfo deviceInfo is null");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationUtil", "addDeviceInfo deviceIdentify is null");
            return;
        }
        ArrayList<Long> arrayList = c.get(deviceIdentify);
        if (arrayList == null) {
            LogUtil.h("HwNotificationUtil", "addDeviceInfo deviceInfo is not exist.");
            ArrayList<Long> arrayList2 = new ArrayList<>(16);
            arrayList2.add(Long.valueOf(System.currentTimeMillis()));
            c.put(deviceIdentify, arrayList2);
            return;
        }
        synchronized (e) {
            arrayList.add(Long.valueOf(System.currentTimeMillis()));
            LogUtil.h("HwNotificationUtil", "addDeviceInfo deviceInfo is exist:", Integer.valueOf(arrayList.size()));
        }
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationUtil", "addDeviceInfo deviceIdentify is null");
            return false;
        }
        ArrayList<Long> arrayList = c.get(str);
        if (arrayList == null) {
            ReleaseLogUtil.d("DEVMGR_HwNotificationUtil", "isAllowNegotiateChannel list is null");
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            synchronized (e) {
                Iterator<Long> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (currentTimeMillis - it.next().longValue() > AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL) {
                        it.remove();
                    }
                }
            }
        } catch (NullPointerException e2) {
            LogUtil.b("HwNotificationUtil", "isAllowNegotiateChannel NullPointerException: ", ExceptionUtils.d(e2));
        } catch (ConcurrentModificationException e3) {
            LogUtil.b("HwNotificationUtil", "isAllowNegotiateChannel ConcurrentModificationException: ", ExceptionUtils.d(e3));
        }
        if (arrayList.size() <= 2) {
            return true;
        }
        ReleaseLogUtil.d("DEVMGR_HwNotificationUtil", "isAllowNegotiateChannel remainCount is more than 2.");
        return false;
    }

    private static void e(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(33);
        deviceCommand.setCommandID(2);
        byte[] a2 = cvx.a(cvx.e(127) + cvx.e(4) + cvx.b(100000L));
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void a(boolean z) {
        LogUtil.a("HwNotificationUtil", "setForbidden:", Boolean.valueOf(z));
        NotificationContentProviderUtil.a(z);
    }

    public static void e(boolean z) {
        LogUtil.a("HwNotificationUtil", "setForbiddenPhone:", Boolean.valueOf(z));
        Intent intent = new Intent("midware_phone_flag");
        intent.putExtra("phone_flag", z);
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void b(boolean z) {
        LogUtil.a("HwNotificationUtil", "setForbiddenPrompt:", Boolean.valueOf(z));
        Intent intent = new Intent("com.huawei.intelligent.action.NOTIFY_MSG");
        intent.putExtra("phone_flag", z);
        BaseApplication.getContext().sendBroadcast(intent, "com.huawei.permission.INTELLIGENT_NOTIFICATION_MSG_BRACELET");
    }

    public static void c(boolean z) {
        SharedPreferenceManager.e("KEEP_APP_ALIVE_MODULE", "DELAYED_RECEIVE_NOTIFICATION", z);
    }

    public static boolean d() {
        return SharedPreferenceManager.a("KEEP_APP_ALIVE_MODULE", "DELAYED_RECEIVE_NOTIFICATION", false);
    }

    public static void c(Context context, String str) {
        synchronized (jrg.class) {
            LogUtil.a("HwNotificationUtil", "notifyKeepAppAlive tag:", str);
            if (context == null) {
                LogUtil.h("HwNotificationUtil", "notifyKeepAppAlive context == null");
                return;
            }
            if (!lcu.e()) {
                LogUtil.h("HwNotificationUtil", "notifyKeepAppAlive no KeepAlive");
                return;
            }
            if (lcu.d(context)) {
                LogUtil.h("HwNotificationUtil", "notifyKeepAppAlive isAgreeWhite");
                return;
            }
            long b2 = SharedPreferenceManager.b("KEEP_APP_ALIVE_MODULE", "KEY_NOTIFY_KEEP_APP_ALIVE_TIME", 0L);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - b2 < 604800000) {
                LogUtil.h("HwNotificationUtil", "notifyKeepAppAlive < timeout");
                return;
            }
            SharedPreferenceManager.e("KEEP_APP_ALIVE_MODULE", "KEY_NOTIFY_KEEP_APP_ALIVE_TIME", currentTimeMillis);
            String string = context.getResources().getString(R$string.IDS_keep_app_alive_notice_message);
            Notification.Builder xf_ = jdh.c().xf_();
            xf_.setContentTitle(context.getResources().getString(R$string.IDS_keep_app_alive_notice_title));
            xf_.setContentText(string);
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            xf_.setStyle(new Notification.BigTextStyle().bigText(string));
            xf_.setAutoCancel(true);
            Intent intent = new Intent();
            intent.setClassName(context, "com.huawei.health.browseraction.HwSchemeBasicHealthActivity");
            intent.putExtra("notification_keep_app_alive", "notification_keep_app_alive");
            xf_.setContentIntent(PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, AppRouterExtras.COLDSTART));
            jdh.c().xh_(20240720, xf_.build());
        }
    }

    public static boolean bIX_(Intent intent) throws Exception {
        if (intent == null) {
            LogUtil.h("HwNotificationUtil", "checkKeepAppAliveArgument  intent is null");
            return false;
        }
        if (!intent.hasExtra("notification_keep_app_alive")) {
            return false;
        }
        String stringExtra = intent.getStringExtra("notification_keep_app_alive");
        if ("notification_keep_app_alive".equals(stringExtra)) {
            return true;
        }
        throw new Exception("NOTIFICATION_KEEP_APP_ALIVE_VALUE not equals, notifyValue:" + stringExtra);
    }
}
