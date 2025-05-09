package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class jjb extends HwBaseManager {
    private static jjb c;
    private static final Object e = new Object();
    private Context d;

    private jjb(Context context) {
        super(context);
        this.d = context;
    }

    public static jjb b() {
        jjb jjbVar;
        synchronized (e) {
            if (c == null) {
                c = new jjb(BaseApplication.getContext());
            }
            jjbVar = c;
        }
        return jjbVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 2;
    }

    public void d(String str, int i, boolean z) {
        ReleaseLogUtil.e("R_DEVMGR_HwNotificationManager", "setAuthorizeStatus: ", Integer.valueOf(i), " isHarmonyOS30SendNotify:", Boolean.valueOf(z));
        jqi.a().setSwitchSettingToLocal(str, String.valueOf(i), 10035);
        NotificationContentProviderUtil.d(i, z);
    }

    public boolean c() {
        String switchSettingFromLocal = jqi.a().getSwitchSettingFromLocal("notificationStatus", 10035);
        if (!TextUtils.isEmpty(switchSettingFromLocal)) {
            ReleaseLogUtil.e("R_DEVMGR_HwNotificationManager", "isAuthorizeEnabled value is: ", switchSettingFromLocal);
            try {
                return Integer.parseInt(switchSettingFromLocal) == 1;
            } catch (NumberFormatException unused) {
                LogUtil.b("HwNotificationManager", "isAuthorizeEnabled NumberFormatException");
                return false;
            }
        }
        boolean b = jrg.b();
        ReleaseLogUtil.e("R_DEVMGR_HwNotificationManager", "isAuthorizeEnabled value is null, isEnabled:", Boolean.valueOf(b));
        if (b) {
            d("notificationStatus", 1, false);
            return true;
        }
        d("notificationStatus", 0, false);
        return false;
    }

    public int a(String str) {
        String switchSettingFromLocal = jqi.a().getSwitchSettingFromLocal(str, 10001);
        LogUtil.a("HwNotificationManager", "getAppPushEnable packageName: ", str, ", value: ", switchSettingFromLocal);
        if (TextUtils.isEmpty(switchSettingFromLocal)) {
            return 0;
        }
        return CommonUtil.m(BaseApplication.getContext(), switchSettingFromLocal);
    }

    public void b(String str, int i) {
        if (TextUtils.equals("com.tencent.mm", str) || TextUtils.equals("com.android.mms", str) || TextUtils.equals("com.tencent.mobileqq", str) || TextUtils.equals(bfg.e, str)) {
            ReleaseLogUtil.e("R_DEVMGR_HwNotificationManager", "setAppPushEnable packageName:", str, " closeStatus:", Integer.valueOf(i));
        }
        jqi.a().setSwitchSettingToLocal(str, String.valueOf(i), 10001);
        if (!c()) {
            LogUtil.h("HwNotificationManager", "setAppPushEnable not authorizeEnabled so return ", str);
        } else {
            NotificationContentProviderUtil.b(str, i);
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public boolean onDataMigrate() {
        int i;
        LogUtil.a("HwNotificationManager", "onDataMigrate enter");
        SharedPreferences sharedPreferences = this.d.getSharedPreferences("notification_setting_preferences", 0);
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = this.d.getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(queryIntentActivities, new ResolveInfo.DisplayNameComparator(packageManager));
        for (int i2 = 0; i2 < queryIntentActivities.size(); i2++) {
            ActivityInfo activityInfo = queryIntentActivities.get(i2).activityInfo;
            String str = activityInfo.packageName;
            String obj = activityInfo.loadLabel(packageManager).toString();
            if (!TextUtils.equals(str, obj) && (i = sharedPreferences.getInt(str, 0)) == 1) {
                LogUtil.c("HwNotificationManager", "onDataMigrate:", obj, ",", str, ",", Integer.valueOf(i2));
                b(str, i);
            }
        }
        return true;
    }

    public void b(boolean z, boolean z2) {
        LogUtil.a("HwNotificationManager", "reportAppStatus isNotification:", Boolean.valueOf(z), " isPrompt:", Boolean.valueOf(z2));
        jqi.a().sendSetSwitchSettingCmd(a(z, z2), "", 33, 1);
    }

    public byte[] a(boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(2));
        sb.append(cvx.e(2));
        sb.append(cvx.e(1));
        sb.append(cvx.e(z ? 1 : 0));
        sb.append(cvx.e(2));
        sb.append(cvx.e(2));
        sb.append(cvx.e(2));
        sb.append(cvx.e(1));
        sb.append(cvx.e(2));
        sb.append(cvx.e(2));
        sb.append(cvx.e(3));
        sb.append(cvx.e(z2 ? 1 : 0));
        String str = cvx.e(129) + cvx.e(sb.toString().length() / 2) + sb.toString();
        LogUtil.a("HwNotificationManager", "packageCommand: ", str);
        return cvx.a(str);
    }

    public boolean e() {
        if (jfq.c() != null) {
            return jog.c().b();
        }
        return true;
    }

    public void b(boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwNotificationManager", "setWearPushSwitchStatus isEnable: ", Boolean.valueOf(z));
        jog.c().a(z, iBaseResponseCallback);
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("HwNotificationManager", "autoSetUp deviceInfo is invalid");
            return;
        }
        DeviceCapability e2 = cvs.e(deviceInfo.getDeviceIdentify());
        if (e2 != null && e2.isSupportMessageAlertInfo()) {
            boolean c2 = c();
            LogUtil.a("HwNotificationManager", "autoSetUp isAuthorizeEnabled: ", Boolean.valueOf(c2));
            DeviceCommand d = jrg.d(c2, "HwNotificationManager");
            d.setmIdentify(deviceInfo.getDeviceIdentify());
            jfq.c().b(d);
            return;
        }
        LogUtil.a("HwNotificationManager", "autoSetUp currentDevice is not support notification");
    }
}
