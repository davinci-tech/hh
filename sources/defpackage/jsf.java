package defpackage;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.common.utils.PowerUtil;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class jsf {
    private static final String[] c = {"SAGA", "ARNOLD", "BESSEL", "COLOMBO"};
    private static final String d = LogConfig.o();

    public static boolean c() {
        return Utils.o();
    }

    public static boolean e(Context context) {
        boolean z;
        int b = PowerUtil.b(context);
        if (b >= 0 && b < 10) {
            boolean a2 = PowerUtil.a(context);
            LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "phone power is less than 10 isCharging:", Boolean.valueOf(a2));
            if (!a2) {
                z = true;
                LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "isBatteryControl:", Boolean.valueOf(z));
                return z;
            }
        }
        z = false;
        LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "isBatteryControl:", Boolean.valueOf(z));
        return z;
    }

    private static boolean c(File[] fileArr) {
        if (fileArr == null) {
            return false;
        }
        for (File file : fileArr) {
            String name = file.getName();
            if (!name.endsWith("_WearableBeta_mcu_upg.log") && !name.endsWith("_Beta_mcu_upg.log") && !name.endsWith("appdft_Beta.zip")) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<File> d(String str) {
        ArrayList<File> arrayList = new ArrayList<>(16);
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return null;
        }
        LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "getStorageFileList(), size:", Integer.valueOf(listFiles.length));
        for (File file : listFiles) {
            if (!file.isDirectory()) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    public static boolean a(Context context, DeviceInfo deviceInfo) {
        boolean z;
        if (context != null && deviceInfo != null) {
            boolean a2 = SharedPreferenceManager.a(String.valueOf(10), "contain_app_log", true);
            LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "isCollectAppLog, isContainAppLog:", Boolean.valueOf(a2), ", deviceName:", deviceInfo.getDeviceName(), ", deviceModel:", deviceInfo.getDeviceModel());
            if (a2) {
                return true;
            }
            int i = 0;
            while (true) {
                String[] strArr = c;
                if (i >= strArr.length) {
                    z = false;
                    break;
                }
                if (deviceInfo.getDeviceName().toUpperCase(Locale.ENGLISH).contains(strArr[i]) || deviceInfo.getDeviceModel().toUpperCase(Locale.ENGLISH).contains(strArr[i])) {
                    break;
                }
                i++;
            }
            z = true;
            if (!a2 && z) {
                return true;
            }
        }
        return false;
    }

    public static void e() {
        List<DeviceInfo> a2 = a();
        if (a2 == null || a2.isEmpty()) {
            ReleaseLogUtil.d("Dfx_Utils", "connectedDeviceList is empty");
            return;
        }
        for (int i = 0; i < a2.size(); i++) {
            DeviceInfo deviceInfo = a2.get(i);
            if (deviceInfo != null) {
                boolean c2 = c(knl.d(deviceInfo.getDeviceIdentify()));
                ReleaseLogUtil.e("Dfx_Utils", "getMaintenanceFile(), isCanMaintenance: ", Boolean.valueOf(c2));
                if (!c2) {
                    ReleaseLogUtil.e("Dfx_Utils", "isCanCollectLog isNotMoreThanOneDay");
                } else {
                    ReleaseLogUtil.e("Dfx_Utils", "dfxLogCollectSendBroadcast enter");
                    kjw.b();
                    return;
                }
            }
        }
    }

    private static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String dayDateTime = new jrx().getDayDateTime();
        ReleaseLogUtil.e("Dfx_Utils", "strCurrentTime: ", dayDateTime);
        if (d(str, dayDateTime)) {
            ReleaseLogUtil.e("Dfx_Utils", "isCanCollectLog isMoreThanOneDay");
        } else {
            int b = b(str);
            boolean a2 = a(str);
            ReleaseLogUtil.e("Dfx_Utils", "needMaintenance(), retryNumber: ", Integer.valueOf(b), ", isSuccess: ", Boolean.valueOf(a2));
            if (b >= 3 || a2) {
                ReleaseLogUtil.e("Dfx_Utils", "needMaintenance(), today has success maintenance or retry > 3.");
                return false;
            }
        }
        return true;
    }

    private static boolean d(String str, String str2) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), str + "_time");
        ReleaseLogUtil.e("Dfx_Utils", "isMoreThanOneDay, strLastTime: ", b);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h(com.huawei.hianalytics.core.transport.Utils.TAG, "strLastTime is isEmpty");
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h(com.huawei.hianalytics.core.transport.Utils.TAG, "strCurrentTime is isEmpty");
            return false;
        }
        LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "delayedOneDay(), strLastTime: ", b, ", strCurTime: ", str2);
        try {
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Dfx_Utils", "delayedOneDay(), delayedEightHour exception");
        }
        return Long.parseLong(str2) - Long.parseLong(b) >= 86400000;
    }

    private static int b(String str) {
        ReleaseLogUtil.e("Dfx_Utils", "enter getRetryNumber");
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), str + "_RETRY_NUMBER");
        if (!nsn.a(b)) {
            return 0;
        }
        try {
            int parseInt = Integer.parseInt(b);
            ReleaseLogUtil.e("Dfx_Utils", "getRetryNumber, retryNumber: ", Integer.valueOf(parseInt));
            return parseInt;
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Dfx_Utils", "getRetryNumber has exception");
            return 0;
        }
    }

    private static boolean a(String str) {
        LogUtil.a(com.huawei.hianalytics.core.transport.Utils.TAG, "enter getMaintenanceResult");
        Context context = BaseApplication.getContext();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_result");
        return "true".equals(SharedPreferenceManager.b(context, String.valueOf(10), sb.toString()));
    }

    private static List<DeviceInfo> a() {
        ReleaseLogUtil.e("Dfx_Utils", "processName: ", CommonUtil.d(Process.myPid()));
        return cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, com.huawei.hianalytics.core.transport.Utils.TAG);
    }

    public static boolean d() {
        File file = new File(d + "MaintenanceLog");
        if (!file.exists()) {
            LogUtil.c(com.huawei.hianalytics.core.transport.Utils.TAG, "isHasDeviceLog isExist: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        return listFiles != null && listFiles.length > 0 && c(listFiles);
    }

    public static boolean b() {
        File file = new File(jsd.b);
        if (!file.exists()) {
            LogUtil.c(com.huawei.hianalytics.core.transport.Utils.TAG, "isHasDeviceZip isExist: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        boolean z = listFiles != null && listFiles.length > 0 && c(listFiles);
        LogUtil.c(com.huawei.hianalytics.core.transport.Utils.TAG, "isHasDeviceZip isHasSmartWearableDFXLog = " + z);
        return z;
    }

    public static void c(InputStream inputStream, FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                LogUtil.b(com.huawei.hianalytics.core.transport.Utils.TAG, "closeFile() ioException is:", e.getMessage());
                return;
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
