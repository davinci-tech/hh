package defpackage;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class did {

    /* renamed from: a, reason: collision with root package name */
    private ddk f11672a;
    private boolean d;

    private did() {
        this.d = false;
    }

    static class e {
        private static final did c = new did();
    }

    public static did c() {
        return e.c;
    }

    public boolean c(String str) {
        return b(str) == 1;
    }

    public boolean d(String str) {
        return dij.g(str);
    }

    public int b(String str) {
        LogUtil.a("AutoConnectHelper", "uniqueId ", CommonUtil.l(str));
        int a2 = SharedPreferenceManager.a(String.valueOf(10000), str, 2);
        LogUtil.a("AutoConnectHelper", "connectMode = ", Integer.valueOf(a2));
        return a2;
    }

    public void b(String str, int i) {
        LogUtil.a("AutoConnectHelper", "uniqueId ", CommonUtil.l(str));
        if (i == 0) {
            i = 2;
        }
        SharedPreferenceManager.b(String.valueOf(10000), str, i);
    }

    public boolean e() {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ComponentName componentName;
        Object systemService = BaseApplication.getContext().getSystemService("activity");
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        if (activityManager == null || (runningTasks = activityManager.getRunningTasks(1)) == null || runningTasks.isEmpty() || (componentName = runningTasks.get(0).topActivity) == null) {
            return false;
        }
        LogUtil.c("AutoConnectHelper", "top activity is ", componentName.getClassName());
        return djx.b() && ((SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class)).isSportServiceRunning();
    }

    public boolean a(String str) {
        return c(str) && !e();
    }

    public boolean c(String str, String str2) {
        synchronized (this) {
            LogUtil.a("AutoConnectHelper", "connectDevice");
            if (!c().a(str2)) {
                LogUtil.a("AutoConnectHelper", "Automatic connection is not supported.");
                return false;
            }
            if (dds.c().f()) {
                LogUtil.a("AutoConnectHelper", "device connected");
                return false;
            }
            if (this.d && !TextUtils.isEmpty(dds.c().d())) {
                LogUtil.a("AutoConnectHelper", "The device that is currently being connected is ", CommonUtil.l(dds.c().d()));
                return false;
            }
            if (!TextUtils.isEmpty(str2)) {
                this.d = true;
                b(str, str2);
                this.f11672a.b();
                return true;
            }
            LogUtil.a("AutoConnectHelper", "query device mac is error");
            return false;
        }
    }

    private void b(String str, String str2) {
        ddk ddkVar = this.f11672a;
        if (ddkVar == null) {
            LogUtil.a("AutoConnectHelper", "initRopeDataManager");
            this.f11672a = new ddk(str, str2, null);
        } else {
            ddkVar.c(str, str2);
        }
    }

    public void d(String str, String str2) {
        ContentValues contentValues;
        ArrayList<ContentValues> bondedDeviceByProductId = ((DeviceManagerApi) Services.c("PluginDevice", DeviceManagerApi.class)).getBondedDeviceByProductId(str);
        if (bondedDeviceByProductId == null || bondedDeviceByProductId.size() <= 0) {
            return;
        }
        Iterator<ContentValues> it = bondedDeviceByProductId.iterator();
        while (true) {
            if (!it.hasNext()) {
                contentValues = null;
                break;
            } else {
                contentValues = it.next();
                if (contentValues.getAsString("uniqueId").equals(str2)) {
                    break;
                }
            }
        }
        if (contentValues == null) {
            LogUtil.h("AutoConnectHelper", "removeOrAddAutoConnectInfo device info is null");
        } else if (c(contentValues.getAsString("uniqueId"))) {
            UR_(contentValues);
        } else {
            US_(contentValues);
        }
    }

    private void UR_(ContentValues contentValues) {
        Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("operation", 1);
        intent.putExtra("productId", contentValues.getAsString("productId"));
        intent.putExtra("uniqueId", contentValues.getAsString("uniqueId"));
        intent.putExtra(Wpt.MODE, contentValues.getAsInteger(Wpt.MODE));
        intent.putExtra("auto", contentValues.getAsInteger("auto"));
        intent.putExtra(EventMonitorRecord.ADD_TIME, contentValues.getAsLong(EventMonitorRecord.ADD_TIME));
        intent.putExtra("name", contentValues.getAsString("name"));
        intent.putExtra("kind", contentValues.getAsString("kind"));
        LogUtil.a("AutoConnectHelper", "addAutoConnectInfo = ", contentValues.getAsString("name"));
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
    }

    private void US_(ContentValues contentValues) {
        Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("operation", -1);
        intent.putExtra("productId", contentValues.getAsString("productId"));
        intent.putExtra("uniqueId", contentValues.getAsString("uniqueId"));
        LogUtil.a("AutoConnectHelper", "removeAutoConnectInfo = ", contentValues.getAsString("name"));
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
    }

    public void c(boolean z) {
        this.d = z;
    }

    public boolean d() {
        return this.d;
    }

    public void e(String str, String str2) {
        LogUtil.a("AutoConnectHelper", "updateMonitorProfileInfo");
        if (!d(str)) {
            LogUtil.a("AutoConnectHelper", "is not Support Auto Connect");
        } else if (!c(str2)) {
            LogUtil.a("AutoConnectHelper", "is share mode");
        } else {
            d(str, str2);
        }
    }
}
