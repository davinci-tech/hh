package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.unitedevice.config.IwearLinkConfig;
import health.compact.a.CloudUtils;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class dyf implements IwearLinkConfig {
    public static void d() {
        if (EnvironmentUtils.e()) {
            snq.d(new dyf());
            ReleaseLogUtil.b("DEVMGR_WearLinkConfig", "setWearLinkConfig end");
        }
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public void opEvent2nd(String str, LinkedHashMap<String, String> linkedHashMap, boolean z) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig opEvent2nd");
        if (z) {
            linkedHashMap.put(OpAnalyticsConstants.DEVICE_SERIAL_NUMBER, CommonUtil.i());
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(str, linkedHashMap);
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public void wearLinkRiskEvent(String str, String str2) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig wearLinkRiskEvent");
        sqo.d(str, str2);
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public String getCommonCountryCode(Context context) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig getCommonCountryCode");
        return GRSManager.a(context).getCommonCountryCode();
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public boolean getAuthorizationStatus(Context context) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig getAuthorizationStatus");
        if (context == null) {
            context = BaseApplication.e();
        }
        boolean equals = "true".equals(KeyValDbManager.b(context).e("key_wether_to_auth"));
        if (!equals) {
            LogUtil.c("WearLinkConfig", "getAuthorizationStatus key_wether_to_auth is false");
        }
        return equals;
    }

    private static String d(int i) {
        String str = "";
        for (Map.Entry<String, Integer> entry : cup.b().entrySet()) {
            if (entry != null && entry.getKey() != null) {
                str = entry.getKey();
                if ((cup.b().get(str) != null ? cup.b().get(str).intValue() : 0) == i) {
                    break;
                }
            }
        }
        return str;
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public String getBrand(int i) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig getBrand");
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(d(i));
        if (pluginInfoByUuid == null) {
            LogUtil.e("WearLinkConfig", "pluginInfo is null");
            return "";
        }
        cvj f = pluginInfoByUuid.f();
        if (f == null) {
            LogUtil.e("WearLinkConfig", "pluginInfoWear is null");
            return "";
        }
        return f.f();
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public boolean isLogin(Context context) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig isLogin");
        boolean a2 = CloudUtils.a();
        LogUtil.c("WearLinkConfig", "isReconnectionDevice isAllowLogin ", Boolean.valueOf(a2));
        boolean z = true;
        if (!a2) {
            return true;
        }
        if (CommonUtil.z(context)) {
            String logoutFlag = ThirdLoginDataStorageUtil.getLogoutFlag();
            LogUtil.c("WearLinkConfig", "isReconnectionDevice logoutFlag ", logoutFlag);
            if (!"false".equals(logoutFlag) && !TextUtils.isEmpty(logoutFlag)) {
                z = false;
            }
        } else {
            z = LoginInit.getInstance(context).getIsLogined();
        }
        ReleaseLogUtil.b("DEVMGR_WearLinkConfig", "isReconnectionDevice isLogin ", Boolean.valueOf(z));
        return z;
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public boolean noCloudVersion() {
        LogUtil.c("WearLinkConfig", "WearLinkConfig noCloudVersion");
        return CloudUtils.d();
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public void applyOrReleaseResource(boolean z, String str, int i, long j, String str2) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig applyOrReleaseResource apply = ", Boolean.valueOf(z));
        if (z) {
            PowerKitManager.e().d(str, i, j, str2);
        } else {
            PowerKitManager.e().e(str, i, str2);
        }
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public String getSwitchSetting(String str, String str2) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig getSwitchSetting");
        return cun.c().getSwitchSetting(str, str2);
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public String getDeviceRelation(String str) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig getDeviceRelation");
        return cuo.d().getDeviceRelation(str);
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public List<DeviceInfo> getDeviceMgrList(int i, String str) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig getDeviceList");
        return jsz.b(BaseApplication.e()).getDeviceList(HwGetDevicesMode.getValue(i), null, str);
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public void notification(int i) {
        LogUtil.c("WearLinkConfig", "WearLinkConfig notification eventId = ", Integer.valueOf(i));
        if (i == 4 || i == 5) {
            d(BaseApplication.e(), i);
        } else {
            b(BaseApplication.e(), i);
        }
    }

    @Override // com.huawei.unitedevice.config.IwearLinkConfig
    public void setProbabilityProblemEvent(String str, String str2) {
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(str, str2);
    }

    private void d(Context context, int i) {
        String string;
        Resources resources = context.getResources();
        if (i == 4) {
            string = resources.getString(R.string.IDS_wifi_go_permission_tip);
        } else if (i != 5) {
            return;
        } else {
            string = resources.getString(R.string.IDS_wlan_go_permission_tip);
        }
        LogUtil.c("WearLinkConfig", "show notify");
        int identifier = resources.getIdentifier("IDS_hw_app_name", "string", BaseApplication.d());
        if (identifier == 0) {
            identifier = resources.getIdentifier("IDS_app_name_health", "string", BaseApplication.d());
        }
        NotificationChannel notificationChannel = new NotificationChannel("channel_common_id", identifier == 0 ? "channel_common_name" : context.getString(identifier), 4);
        notificationChannel.enableVibration(false);
        notificationChannel.setSound(null, null);
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        from.createNotificationChannel(notificationChannel);
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        Notification build = new NotificationCompat.Builder(context, "channel_common_id").setSmallIcon(R.drawable.healthlogo_ic_notification).setAutoCancel(true).setContentText(string).setContentIntent(PendingIntent.getActivity(context, 0, intent, AppRouterExtras.COLDSTART)).setStyle(new NotificationCompat.BigTextStyle()).setPriority(0).build();
        build.flags = 536;
        from.notify(2022050515, build);
    }

    private void b(Context context, int i) {
        String string;
        if (i == 1) {
            Toast.makeText(context, R.string.IDS_device_mgr_device_pair_guide_tips, 1).show();
            return;
        }
        if (i == 2) {
            string = context.getResources().getString(R.string.IDS_device_unbind_fail_from_system_list);
        } else if (i != 3) {
            return;
        } else {
            string = context.getString(R.string.IDS_blite_guide_paire_fail_no_hilink_device_string);
        }
        Toast.makeText(context, string, 1).show();
    }
}
