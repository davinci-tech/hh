package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.slf4j.Marker;

/* loaded from: classes5.dex */
public class kbi {
    public static final String d = LogConfig.m() + "RemoteDetect";

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
    
        if (r4.isSupportSelfUploadDeviceLog() != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(java.lang.String r4) {
        /*
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface r0 = defpackage.jsz.b(r0)
            r1 = 1
            java.lang.String r2 = "GetFileCommonUtil"
            java.util.Map r0 = r0.queryDeviceCapability(r1, r4, r2)
            if (r0 == 0) goto L26
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L26
            java.lang.Object r4 = r0.get(r4)
            com.huawei.health.devicemgr.business.entity.DeviceCapability r4 = (com.huawei.health.devicemgr.business.entity.DeviceCapability) r4
            if (r4 == 0) goto L2f
            boolean r4 = r4.isSupportSelfUploadDeviceLog()
            if (r4 == 0) goto L2f
            goto L30
        L26:
            java.lang.String r4 = "isSelfUploadDeviceLog queryDeviceCapability is null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r4)
        L2f:
            r1 = 0
        L30:
            java.lang.String r4 = "isSelfUploadDeviceLog: "
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r0}
            java.lang.String r0 = "Dfx_GetFileCommonUtil"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kbi.b(java.lang.String):boolean");
    }

    public static String b(DeviceInfo deviceInfo) {
        ReleaseLogUtil.e("Dfx_GetFileCommonUtil", "enter startAppLogZipFile");
        if (deviceInfo == null) {
            return "";
        }
        try {
            String c = CommonUtil.c(d + "/" + jrx.d().d(deviceInfo) + ".zip");
            LogUtil.a("GetFileCommonUtil", "startAppLogZipFile targetPath: ", c);
            LinkedList<File> e = jsj.e(LogConfig.o());
            if (e != null && e.size() != 0) {
                String d2 = d(deviceInfo);
                List<File> d3 = d((List<File>) e, d2, true);
                LogUtil.a("GetFileCommonUtil", "rootFiles: ", d3.toString());
                jsj.e(d3, new File(c), "", d2);
                return c;
            }
            LogUtil.c("GetFileCommonUtil", "list is null");
            return null;
        } catch (IOException unused) {
            ReleaseLogUtil.c("Dfx_GetFileCommonUtil", "startAppLogZipFile IOException");
            return null;
        } catch (OutOfMemoryError unused2) {
            ReleaseLogUtil.c("Dfx_GetFileCommonUtil", "startAppLogZipFile OutOfMemoryError");
            return null;
        }
    }

    public static String e(DeviceInfo deviceInfo) {
        LogUtil.a("GetFileCommonUtil", "enter ziplog method");
        LinkedList<File> e = jsj.e(kbj.b + "MaintenanceLog");
        if (e == null || e.size() == 0) {
            LogUtil.a("GetFileCommonUtil", "deviceFileList is empty.");
            return null;
        }
        try {
            String e2 = e(e, deviceInfo);
            LogUtil.a("GetFileCommonUtil", "UploadDevicelog fullFileName: ", e2);
            if (!TextUtils.isEmpty(e2) && e2.contains("_")) {
                if (e2.endsWith("_Beta_mcu_upg.log")) {
                    e2 = e2.replace("_Beta_mcu_upg", "_WearableBeta");
                }
                int indexOf = e2.indexOf("_WearableBeta") + 13;
                LogUtil.a("GetFileCommonUtil", "uploadDevicelog index=", Integer.valueOf(indexOf));
                if (indexOf == -1) {
                    return null;
                }
                String c = CommonUtil.c(g(d + e2.substring(0, indexOf) + ".zip"));
                LogUtil.a("GetFileCommonUtil", "UploadDevicelog targetPath: ", c);
                LinkedList<File> e3 = jsj.e(LogConfig.o());
                if (e3 != null && e3.size() != 0) {
                    String d2 = d(deviceInfo);
                    List<File> d3 = d((List<File>) e3, d2, false);
                    LogUtil.a("GetFileCommonUtil", "rootFiles: ", d3.toString());
                    jsj.e(d3, new File(c), "", d2);
                    a(e);
                    return c;
                }
                LogUtil.c("GetFileCommonUtil", "list is null");
                return null;
            }
            LogUtil.a("GetFileCommonUtil", "uploadDevicelog fullFileName is null.");
            return null;
        } catch (IOException unused) {
            LogUtil.b("GetFileCommonUtil", "startZipFile IOException");
            return null;
        } catch (OutOfMemoryError unused2) {
            LogUtil.b("GetFileCommonUtil", "startZipFile OutOfMemoryError");
            return null;
        }
    }

    private static void a(List<File> list) {
        if (list == null || list.size() == 0) {
            LogUtil.h("GetFileCommonUtil", "list is empty");
            return;
        }
        for (File file : list) {
            if (file.exists()) {
                LogUtil.a("GetFileCommonUtil", "file name is:", file.getName(), ", deleteFiles isSuccess:", Boolean.valueOf(file.delete()));
            }
        }
    }

    private static List<File> d(List<File> list, String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            if (!z && file.getName().equals("com.huawei.version.json")) {
                arrayList.add(file);
            } else if (!z && file.getName().equals("MaintenanceLog")) {
                arrayList.add(file);
            } else if (file.getName().equals(kbj.f14253a)) {
                arrayList.add(file);
            } else if (file.getName().equals(kbj.c)) {
                arrayList.add(file);
            } else if (file.getName().equals(kbj.d)) {
                arrayList.add(file);
            } else if (file.getName().equals(kbj.j)) {
                a(file, str);
                arrayList.add(file);
            } else {
                LogUtil.h("GetFileCommonUtil", "processFiles else");
            }
        }
        return arrayList;
    }

    private static void a(File file, String str) {
        if (file == null || TextUtils.isEmpty(str)) {
            LogUtil.h("GetFileCommonUtil", "params is null");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.h("GetFileCommonUtil", "originFiles is null");
            return;
        }
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("log")) {
                c(file2, str);
            }
        }
    }

    private static void c(File file, String str) {
        try {
            ArrayList arrayList = new ArrayList(16);
            String str2 = file.getCanonicalPath().substring(0, file.getCanonicalPath().length() - 5) + new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(new Date(file.lastModified()));
            File file2 = new File(str2);
            CommonUtil.e(file.getCanonicalPath(), file2.getCanonicalPath(), true);
            arrayList.add(file2);
            jsj.e(arrayList, new File(str2 + ".zip"), "", str);
            if (!"log.0".equals(file.getName())) {
                LogUtil.a("GetFileCommonUtil", "delte file: ", Boolean.valueOf(file.delete()));
            }
            if (file2.delete()) {
                return;
            }
            LogUtil.h("GetFileCommonUtil", "updateFileList tempFile.delete() false");
        } catch (IOException unused) {
            LogUtil.b("GetFileCommonUtil", "updateFileList IOException");
        }
    }

    private static String g(String str) {
        return CommonUtil.bv() ? str.replace("_WearableBeta", "_WearableCommercial") : str;
    }

    private static String e(List<File> list, DeviceInfo deviceInfo) {
        String str;
        String d2 = d(deviceInfo);
        Iterator<File> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            File next = it.next();
            if (next.getName().contains(d2)) {
                str = next.getName();
                break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(d2)) {
                String name = list.get(i).getName();
                boolean z = name.contains("_&_&") || name.contains("bigdata.log");
                boolean contains = name.contains("gpu_update.log");
                if (!name.endsWith("_WearableBeta_mcu_upg.log") && !name.endsWith("_Beta_mcu_upg.log") && !name.endsWith("appdft_Beta.zip") && !z && !contains) {
                    return CommonUtil.c(name);
                }
            }
        }
        return CommonUtil.c(str);
    }

    public static DeviceInfo a(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "GetFileCommonUtil");
        if (deviceList == null || deviceList.size() == 0) {
            LogUtil.a("GetFileCommonUtil", "no device connected");
            return null;
        }
        for (DeviceInfo deviceInfo2 : deviceList) {
            if (str.equals(deviceInfo2.getUuid()) || str.equals(deviceInfo2.getDeviceUdid())) {
                deviceInfo = deviceInfo2;
                break;
            }
        }
        return deviceInfo == null ? deviceList.get(0) : deviceInfo;
    }

    public static DeviceInfo e(String str) {
        List<DeviceInfo> deviceList;
        if (TextUtils.isEmpty(str) && (deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "GetFileCommonUtil")) != null && deviceList.size() != 0) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (str.contains(d(deviceInfo))) {
                    return deviceInfo;
                }
            }
        }
        return null;
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("GetFileCommonUtil", "isUpload targetPath is empty");
            return false;
        }
        String e = SharedPreferenceManager.e("detect_wifi_mmkv_model_id", "detect_wifi_upload_log", "");
        LogUtil.a("GetFileCommonUtil", "uploadStringList: ", e);
        if (!TextUtils.isEmpty(e)) {
            for (String str2 : e.split("#")) {
                if (str2.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("GetFileCommonUtil", "targetPath is empty");
            return;
        }
        String e = SharedPreferenceManager.e("detect_wifi_mmkv_model_id", "detect_wifi_upload_log", "");
        if (TextUtils.isEmpty(e)) {
            return;
        }
        String[] split = e.split("#");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(str)) {
                split[i] = "";
                e(split);
            }
        }
    }

    private static void e(String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            if (!TextUtils.isEmpty(strArr[i])) {
                stringBuffer.append(strArr[i] + "#");
            }
        }
        SharedPreferenceManager.c("detect_wifi_mmkv_model_id", "detect_wifi_upload_log", stringBuffer.toString());
    }

    public static String b(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            LogUtil.a("GetFileCommonUtil", "getCurrentDeviceUnitId targetPath is null or context is null");
            return "";
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "GetFileCommonUtil");
        if (deviceList == null || deviceList.size() == 0) {
            return "";
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (str.contains(d(deviceInfo))) {
                return deviceInfo.getDeviceIdentify();
            }
        }
        return "";
    }

    public static String d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.b("GetFileCommonUtil", "getShaDeviceId currentDeviceInfo is null");
            return "";
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (deviceIdentify == null) {
            return "";
        }
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        if (!TextUtils.isEmpty(udidFromDevice)) {
            return udidFromDevice;
        }
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        String str = securityDeviceId + deviceIdentify;
        if (!deviceIdentify.equals(securityDeviceId)) {
            deviceIdentify = str;
        }
        if (deviceInfo.getProductType() >= 34 && !Utils.o()) {
            return knl.a(deviceIdentify);
        }
        return j(securityDeviceId);
    }

    public static String j(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("GetFileCommonUtil", "setDeviceMac deviceMac is null");
            return null;
        }
        if (Utils.o()) {
            String d2 = knl.d(str);
            return d2.length() >= 24 ? d2.replace(Marker.ANY_NON_NULL_MARKER, "A").replace("/", "A").replace("=", "A").substring(0, 24) : d2;
        }
        if (str.contains(":") || str.length() <= 12) {
            return str.replace(":", "");
        }
        String d3 = knl.d(str);
        return d3.length() >= 24 ? d3.replace(Marker.ANY_NON_NULL_MARKER, "A").replace("/", "A").replace("=", "A").substring(0, 24) : d3;
    }

    public static void d(IBaseCommonCallback iBaseCommonCallback, int i, String str) {
        if (iBaseCommonCallback == null) {
            LogUtil.h("GetFileCommonUtil", "callback is null");
            return;
        }
        LogUtil.a("GetFileCommonUtil", "resultCode:", Integer.valueOf(i), ", response:", str);
        try {
            iBaseCommonCallback.onResponse(i, str);
        } catch (RemoteException unused) {
            LogUtil.b("GetFileCommonUtil", "setResponse RemoteException");
        }
    }

    public static void c() {
        jeq.e().init(BaseApplication.getContext());
        jeq.e().setProductType(1);
    }

    public static void a() {
        jeq.e().uninit(BaseApplication.getContext());
        jeq.e().logout();
    }
}
