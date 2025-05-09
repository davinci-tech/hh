package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwcrowdtestapi.HealthFeedbackParams;
import com.huawei.hwcrowdtestapi.HealthSendLogCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback.DeviceDfxUploadCallback;
import com.huawei.hwdevice.phoneprocess.util.EventLogUploadService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.HwEncryptUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.ProcessUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes5.dex */
public final class jsd {
    public static final String b = m();

    /* renamed from: a, reason: collision with root package name */
    public static final String f14045a = n();
    public static final String c = LogConfig.o();
    public static final String d = BaseApplication.getAppPackage() + ".h5pro";
    private static final String j = LogConfig.j();
    private static volatile DeviceDfxUploadCallback s = null;
    private static final Object e = new Object();
    private static final Object f = new Object();
    private static List<File> q = new ArrayList(16);
    private static List<String> n = new ArrayList(16);
    private static List<String> o = new ArrayList(16);
    private static String r = null;
    private static String p = "";
    private static final String g = ProcessUtil.b("_PhoneService");
    private static final String i = ProcessUtil.b("_DaemonService");
    private static boolean k = false;
    private static boolean h = false;
    private static long l = 0;
    private static ExecutorService m = Executors.newSingleThreadExecutor();

    private static boolean a(int i2) {
        return i2 == 5 || i2 == 1 || i2 == 7 || i2 == 14 || i2 == 2 || i2 == 3 || i2 == 8 || i2 == 13 || i2 == 15 || i2 == 16;
    }

    private jsd() {
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected() && !PowerKitManager.e().b() && activeNetworkInfo.getType() == 1;
    }

    public static boolean a(Context context) {
        return CommonUtil.aa(context);
    }

    public static void e(boolean z) {
        k = z;
    }

    public static void a(boolean z) {
        LogUtil.a("UploadLogUtil", "setSendFeedbackUseFlow, isUseFlow:", Boolean.valueOf(z));
        h = z;
    }

    public static boolean e() {
        return k;
    }

    public static boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UploadLogUtil", "isHasZip path is empty");
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.c("UploadLogUtil", "isHasZip isExits:", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return false;
        }
        boolean z = false;
        for (File file2 : listFiles) {
            if (file2.getName().contains("WearAPPBeta") || file2.getName().contains("WearableBeta")) {
                z = true;
            }
        }
        return z;
    }

    private static long k() {
        File file = new File(b);
        if (!file.exists()) {
            LogUtil.c("UploadLogUtil", "getZipLength isExits: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        long j2 = 0;
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.getName().contains("WearAPPBeta") || file2.getName().contains("WearableBeta")) {
                    j2 += file2.length();
                }
            }
        }
        LogUtil.a("UploadLogUtil", "log total size: ", Long.valueOf(j2));
        return j2;
    }

    private static void e(File file, String str) {
        LinkedList<File> linkedList;
        String path = file.getPath();
        LogUtil.a("UploadLogUtil", "encryFilePath: ", path);
        if (TextUtils.isEmpty(path)) {
            LogUtil.a("UploadLogUtil", "encryFilePath is null.");
            return;
        }
        File file2 = new File(path);
        try {
            LogUtil.a("UploadLogUtil", "encryFilePath: ", file2.getCanonicalPath());
        } catch (IOException e2) {
            LogUtil.a("UploadLogUtil", "deleteEncryFile:", e2.getMessage());
        }
        if (file2.exists()) {
            if (!file2.isDirectory()) {
                if (file2.getName().contains(a(f(str))) || file2.getName().contains("bigdata.log")) {
                    LogUtil.a("UploadLogUtil", "encryFile delete :", Boolean.valueOf(file2.delete()));
                    return;
                } else {
                    LogUtil.c("UploadLogUtil", "encryFile delete fail!");
                    return;
                }
            }
            try {
                linkedList = jsj.e(file2.getCanonicalPath());
            } catch (IOException e3) {
                LogUtil.a("UploadLogUtil", "deleteEncryFile()", e3.getMessage());
                linkedList = null;
            }
            a(linkedList, str);
            return;
        }
        LogUtil.c("UploadLogUtil", "encryFile not exists!");
    }

    private static void a(List<File> list, String str) {
        if (list != null) {
            for (File file : list) {
                LogUtil.c("UploadLogUtil", "deleteFiles file.path = " + file.getAbsolutePath());
                e(file, str);
                LogUtil.c("UploadLogUtil", "deleteFiles file.exist = " + new File(file.getAbsolutePath()).exists());
            }
        }
    }

    private static void b(Context context, String str, String str2) {
        synchronized (f) {
            n.add(str);
        }
        LogUtil.a("UploadLogUtil", "add file: ", str);
        d(context, str, 0, str2);
    }

    private static void d(final Context context, final String str, final int i2, String str2) {
        LogUtil.a("UploadLogUtil", "startUploadLog deviceDataId: ", CommonUtil.l(str2) + ", targetPath = " + str);
        final String c2 = c(str, str2);
        if (i2 == 0) {
            a(context, c2);
            l = System.currentTimeMillis();
            SharedPreferenceManager.d(context, System.currentTimeMillis(), "EXCE_DFT_APP_UPLOADSTART");
            DeviceInfo f2 = f(c2);
            if (f2 != null) {
                String a2 = a(f2);
                p = p(str);
                e("910402", "EXCE_DFT_APP_UPLOADSTART", d(l) + "", a2);
            }
        }
        LogUtil.a("UploadLogUtil", "setIntentData Assemble uploaded fields targetPath is ", str);
        HealthFeedbackParams healthFeedbackParams = new HealthFeedbackParams();
        b(healthFeedbackParams, str, c2);
        LogUtil.a("UploadLogUtil", "setIntentData Assemble uploaded fields targetPath isExist = " + new File(str).exists() + ", HealthCrowdTestProxy.sendLog");
        jeq.e().sendLog(context, healthFeedbackParams, str, false, new HealthSendLogCallback() { // from class: jsd.2
            @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
            public void onSuccess(String str3) {
                synchronized (jsd.f) {
                    jsd.n.remove(str);
                }
                jsd.e(str3, str, i2, jsd.l, c2);
            }

            @Override // com.huawei.hwcrowdtestapi.HealthSendLogCallback
            public void onFailed(String str3) {
                DeviceInfo f3;
                synchronized (jsd.f) {
                    jsd.n.remove(str);
                }
                LogUtil.a("UploadLogUtil", "startUploadLog: file ", str, " upload failed: ", str3);
                if (i2 != 0 || (f3 = jsd.f(c2)) == null) {
                    return;
                }
                SharedPreferenceManager.d(context, ParamConstants.CallbackMethod.ON_FAIL, "EXCE_DFT_APP_UPLOADRESULT");
                jsd.a(context, c2);
                String unused = jsd.p = jsd.p(str);
                jsd.e("910403", "EXCE_DFT_APP_UPLOADSTOP", ((System.currentTimeMillis() - jsd.l) / 1000) + "", jsd.a(f3));
            }
        });
    }

    private static String c(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "UploadLogUtil");
        if (deviceList == null || deviceList.size() == 0) {
            return "";
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (str.contains(a(deviceInfo))) {
                return deviceInfo.getDeviceIdentify();
            }
        }
        return str2;
    }

    public static void b(Context context, String str, String str2, String str3, String str4) {
        DeviceInfo deviceInfo;
        if (EnvironmentUtils.c()) {
            deviceInfo = jpt.d("UploadLogUtil");
            if (deviceInfo == null) {
                deviceInfo = jpu.d("UploadLogUtil");
            }
        } else if (EnvironmentUtils.e()) {
            deviceInfo = keb.d("UploadLogUtil");
            if (deviceInfo == null) {
                deviceInfo = keb.c("UploadLogUtil");
            }
        } else {
            LogUtil.h("UploadLogUtil", "addOtaCoreSleepLog other Process");
            deviceInfo = null;
        }
        if (deviceInfo != null) {
            String a2 = a(deviceInfo);
            a(context, deviceInfo.getDeviceIdentify());
            e(str, str2, str3, str4, a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String p(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf == -1) {
            return str;
        }
        String substring = str.substring(lastIndexOf + 1, str.length());
        return substring.length() > 4 ? substring.substring(0, substring.length() - 4) : substring;
    }

    private static void b(HealthFeedbackParams healthFeedbackParams, String str, String str2) {
        String str3;
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf != -1) {
            String substring = str.substring(lastIndexOf + 1, str.length());
            LogUtil.a("UploadLogUtil", "fileNamePath: ", substring);
            String[] split = substring.split("_");
            String a2 = (split == null || split.length <= 0) ? "WEAR" : a(split[0]);
            DeviceInfo f2 = f(str2);
            if (f2 != null) {
                str3 = f2.getSecurityDeviceId();
                LogUtil.a("UploadLogUtil", "addUploadParams deviceInfo is not null");
            } else {
                str3 = "0000000000000000";
            }
            LogUtil.a("UploadLogUtil", "addUploadParams deviceDataId: ", CommonUtil.l(str2), ";deviceData: ", CommonUtil.l(str3));
            if (!TextUtils.isEmpty(str3) && str3.contains(":")) {
                str3 = str3.replace(":", "");
            }
            CommonUtil.ac(str3);
            healthFeedbackParams.setDeviceModel(a2);
            if (!TextUtils.isEmpty(str3)) {
                healthFeedbackParams.setDeviceSn(str3);
            }
            if (Utils.o()) {
                healthFeedbackParams.setProductType(20);
            } else {
                healthFeedbackParams.setProductType(1);
            }
        }
    }

    public static String a(String str) {
        if (str == null) {
            return "WEAR";
        }
        for (String str2 : jqa.f14020a) {
            if (str.toUpperCase(Locale.ENGLISH).contains(str2.toUpperCase(Locale.ENGLISH))) {
                return str2;
            }
        }
        return "WEAR";
    }

    public static boolean d(String str) {
        String a2 = a(f(str));
        if (StringUtils.g(a2)) {
            LogUtil.a("UploadLogUtil", "shaDeviceId is null or empty");
            return false;
        }
        LinkedList<File> c2 = jsj.c(CommonUtil.c(j));
        if (c2 == null) {
            LogUtil.a("UploadLogUtil", "equalUpgLog otaFiles is null.");
            return false;
        }
        Iterator<File> it = c2.iterator();
        while (it.hasNext()) {
            if (it.next().getName().contains(a2)) {
                LogUtil.a("UploadLogUtil", "this device's upg log is exists.");
                return true;
            }
        }
        return false;
    }

    public static void o(final String str) {
        m.execute(new Runnable() { // from class: jsd.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("UploadLogUtil", "Mobile. Upload all zip files");
                if (jsd.i(jsd.b)) {
                    jsd.h(jsd.b, str);
                }
                if (jsd.i(jsd.f14045a)) {
                    jsd.h(jsd.f14045a, str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h(String str, String str2) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.a("UploadLogUtil", "startUploadLogWithNetwork files is null");
            return;
        }
        for (File file : listFiles) {
            if (h) {
                LogUtil.c("UploadLogUtil", "startUploadLogWithNetwork, isSendFeedbackUseFlow");
                String str3 = str + file.getName();
                if (s(str3)) {
                    LogUtil.a("UploadLogUtil", "targetPath: ", str3);
                    o.remove(str3);
                    a(BaseApplication.getContext(), file, str3, str2);
                }
            } else if (file.getName().contains("WearAPPBeta") || file.getName().contains("WearableBeta")) {
                b(BaseApplication.getContext(), str + file.getName(), str2);
            }
        }
        h = false;
    }

    private static boolean s(String str) {
        for (int i2 = 0; i2 < o.size(); i2++) {
            if (str.equals(o.get(i2))) {
                return true;
            }
        }
        return false;
    }

    private static void a(Context context, File file, String str, String str2) {
        if ((file.getName().contains("WearAPPBeta") || file.getName().contains("WearableBeta")) && file.getName().contains("device")) {
            b(context, str, str2);
        }
    }

    public static void e(DeviceDfxUploadCallback deviceDfxUploadCallback) {
        s = deviceDfxUploadCallback;
    }

    public static void a(Context context, boolean z, String str) {
        LogUtil.a("UploadLogUtil", "uploadLog isAuto is: ", Boolean.valueOf(z));
        LogUtil.a("UploadLogUtil", "isHasZip: ", Boolean.valueOf(i(b)), " isHasHonorZip: ", Boolean.valueOf(i(f14045a)));
        if (y(str)) {
            if (!k) {
                e(context, z, str);
            }
            String str2 = c + "com.huawei.version.json";
            File file = new File(str2);
            if (file.exists()) {
                LogUtil.c("UploadLogUtil", "version delete is ", Boolean.valueOf(file.delete()));
            }
            try {
                if (file.createNewFile()) {
                    d(context, str2);
                } else {
                    LogUtil.c("UploadLogUtil", "createNewFile failed!");
                }
            } catch (IOException e2) {
                LogUtil.b("UploadLogUtil", "PackageManager.IOException is:" + e2.getMessage());
            } catch (OutOfMemoryError unused) {
                LogUtil.c("UploadLogUtil", "uploadLog OutOfMemoryError");
            }
            r = null;
            if (CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")) {
                a(str, z);
            }
            c(context, z, str);
            if (h) {
                LogUtil.a("UploadLogUtil", "isSendFeedbackUseFlow");
                o(str);
            }
            if (!k || s == null) {
                return;
            }
            s.startUpload(k());
            s = null;
        }
    }

    private static void a(String str, boolean z) {
        LogUtil.a("UploadLogUtil", "createTempFile enter");
        DeviceInfo f2 = f(str);
        if (f2 != null) {
            if (!(l(f2.getDeviceIdentify()) && z) && g(str)) {
                int productType = f2.getProductType();
                String deviceModel = f2.getDeviceModel();
                String e2 = e(productType, c(productType, deviceModel), deviceModel);
                String udidFromDevice = f2.getUdidFromDevice();
                if (TextUtils.isEmpty(udidFromDevice)) {
                    String deviceIdentify = f2.getDeviceIdentify();
                    if (deviceIdentify == null) {
                        return;
                    }
                    String securityDeviceId = f2.getSecurityDeviceId();
                    String str2 = securityDeviceId + deviceIdentify;
                    if (!deviceIdentify.equals(securityDeviceId)) {
                        deviceIdentify = str2;
                    }
                    if (f2.getProductType() >= 34 && !Utils.o()) {
                        udidFromDevice = knl.a(deviceIdentify);
                    } else {
                        udidFromDevice = n(securityDeviceId);
                    }
                }
                String format = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(new Date());
                String str3 = c + "MaintenanceLog/";
                w(str3);
                r = str3 + e2 + "_" + f2.getSoftVersion() + "_" + udidFromDevice + "_" + format + "_WearableBeta.log";
                try {
                    if (new File(r).createNewFile()) {
                        return;
                    }
                    LogUtil.a("UploadLogUtil", "dir file fail");
                } catch (IOException e3) {
                    LogUtil.b("UploadLogUtil", "create dir file IOException:" + e3.getMessage());
                }
            }
        }
    }

    private static void w(String str) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.a("UploadLogUtil", "mkdirs is ", Boolean.valueOf(file.mkdirs()));
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            LogUtil.a("UploadLogUtil", "file delete is", Boolean.valueOf(file2.delete()));
        }
    }

    public static void e(Context context, boolean z, String str) {
        if (z && jsf.e(context)) {
            LogUtil.h("UploadLogUtil", "uploadZip:isAuto isBatteryControl not upload");
            return;
        }
        String str2 = b;
        k(str2);
        String str3 = f14045a;
        k(str3);
        LogUtil.a("UploadLogUtil", "uploadZip ENCYPTION_PATH: " + str2 + ", isHasZip = " + i(str2));
        boolean c2 = c(context);
        LogUtil.a("UploadLogUtil", "uploadZip isWifi: ", Boolean.valueOf(c2));
        if (c2) {
            LogUtil.a("UploadLogUtil", "uploadLog External conditions met");
            if (i(str2)) {
                i(str2, str);
            }
            if (i(str3)) {
                i(str3, str);
            }
        }
    }

    private static void i(String str, String str2) {
        LogUtil.a("UploadLogUtil", "startUploadZipFile path:", str);
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.a("UploadLogUtil", "uploadZip files is null.");
            return;
        }
        for (File file : listFiles) {
            LogUtil.a("UploadLogUtil", "startUploadZipFile file path: " + file.getAbsolutePath());
            e(file.getName(), str, BaseApplication.getContext(), str2);
        }
    }

    private static boolean y(String str) {
        boolean e2 = knx.e();
        boolean o2 = Utils.o();
        LogUtil.a("UploadLogUtil", "isTiyan = ", Boolean.valueOf(e2), " BuildConfig.SUPPORT_LOG_FEEDBACK = ", Boolean.valueOf(CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK")), "isOversea = ", Boolean.valueOf(o2));
        boolean z = CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") && e2;
        return (z && !o2) || (o2 && (z && q(str) && "true".equals(KeyValDbManager.b(BaseApplication.getContext()).e("is_support_dfx_status"))));
    }

    private static boolean q(String str) {
        return cwi.c(f(str), 34);
    }

    public static boolean j() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "upload_big_data_last_time");
        if (TextUtils.isEmpty(b2)) {
            ReleaseLogUtil.d("Dfx_UploadLogUtil", "logCanUploadBigData lastTime is isEmpty, isCanUploadBigData is true");
            return true;
        }
        String h2 = h();
        if (TextUtils.isEmpty(h2)) {
            LogUtil.a("UploadLogUtil", "isMoreThanOneDay currentTime is isEmpty, isCanUploadBigData is false");
            return false;
        }
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "isMoreThanOneDay, lastTime: ", b2, ", currentTime: ", h2);
        try {
            if (Long.parseLong(h2) - Long.parseLong(b2) >= 86400000) {
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "upload_big_data_last_time", "", (StorageParams) null);
                ReleaseLogUtil.e("Dfx_UploadLogUtil", "isMoreThanOneDay moreThan 24 hour, isCanUploadBigData is true");
                return true;
            }
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Dfx_UploadLogUtil", "isMoreThanOneDay NumberFormatException");
        }
        return false;
    }

    public static boolean i() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(9), "upload_ota_data_last_time");
        if (TextUtils.isEmpty(b2)) {
            ReleaseLogUtil.d("Dfx_UploadLogUtil", "logCanUploadOTAData lastTime is isEmpty, isCanUploadOTAData is true");
            return true;
        }
        String h2 = h();
        if (TextUtils.isEmpty(h2)) {
            ReleaseLogUtil.d("Dfx_UploadLogUtil", "isMoreThanOneDay currentTime is isEmpty, isCanUploadOTAData is false");
            return false;
        }
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "isMoreThanOneDay, lastTime: ", b2, ", currentTime: ", h2);
        try {
            if (Long.parseLong(h2) - Long.parseLong(b2) >= 86400000) {
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(9), "upload_ota_data_last_time", "", (StorageParams) null);
                ReleaseLogUtil.e("Dfx_UploadLogUtil", "isMoreThanOneDay moreThan 24 hour, isCanUploadOTAData is true");
                return true;
            }
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Dfx_UploadLogUtil", "isMoreThanOneDay NumberFormatException");
        }
        return false;
    }

    private static String h() {
        long time = Calendar.getInstance().getTime().getTime();
        LogUtil.b("UploadLogUtil", "getDayDateTime: strCurTime: ", String.valueOf(time));
        return String.valueOf(time);
    }

    public static void e(Context context, String str) {
        k(b);
        k(f14045a);
        boolean c2 = c(context);
        boolean l2 = l();
        boolean e2 = knx.e();
        boolean t = t(str);
        boolean o2 = Utils.o();
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "isOversea: ", Boolean.valueOf(o2), " isWifi: ", Boolean.valueOf(c2), " isHasEventLog: ", Boolean.valueOf(l2), " isHasCrash: ", false, " isTiyan: ", Boolean.valueOf(e2), " isHasDeviceEventLog: ", Boolean.valueOf(t));
        if ((jad.d(48) || o2) && e2) {
            if (t) {
                c(context, str);
            }
            if (c2) {
                ReleaseLogUtil.e("Dfx_UploadLogUtil", "uploadReleaseEventLog upload device log file");
                f(context, str);
            }
        }
    }

    private static boolean l() {
        File file = new File(b);
        if (!file.exists()) {
            LogUtil.c("UploadLogUtil", "isExits: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return false;
        }
        boolean z = false;
        for (File file2 : listFiles) {
            if (file2.getName().toLowerCase(Locale.ENGLISH).contains("event") || file2.getName().toLowerCase(Locale.ENGLISH).contains(VastAttribute.ERROR) || file2.getName().toLowerCase(Locale.ENGLISH).contains("wearablebeta_dump.log")) {
                z = true;
            }
        }
        return z;
    }

    private static boolean t(String str) {
        File file = new File(c + "MaintenanceLog");
        if (!file.exists()) {
            LogUtil.c("UploadLogUtil", "isExits: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return false;
        }
        for (File file2 : listFiles) {
            LogUtil.a("UploadLogUtil", "isHasDeviceEventLog file is: ", file2.getName());
            String c2 = CommonUtil.c(file2.getName());
            if (c2 != null && d(c2, str)) {
                return true;
            }
        }
        return false;
    }

    private static void f(Context context, String str) {
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "uploadReleaseLog()");
        if (jsf.e(context)) {
            LogUtil.h("UploadLogUtil", "uploadReleaseEventLog:isAuto isBatteryControl not upload");
        } else {
            d(context, b, str);
            d(context, f14045a, str);
        }
    }

    private static void d(Context context, String str, String str2) {
        List<File> e2 = e(str, str2);
        if (e2 == null || e2.size() <= 0) {
            return;
        }
        for (File file : e2) {
            String name = file.getName();
            String str3 = str + file.getName();
            ReleaseLogUtil.e("Dfx_UploadLogUtil", "targetPath: ", str3);
            if (c(context)) {
                if (TextUtils.isEmpty(str2)) {
                    if (str3.contains("bigdata.log")) {
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "upload_big_data_last_time", h(), (StorageParams) null);
                    }
                    if (str3.contains("gpu_update.log")) {
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(9), "upload_ota_data_last_time", h(), (StorageParams) null);
                    }
                }
                b(context, str3, name, str2);
            }
        }
    }

    private static List<File> e(String str, String str2) {
        LinkedList linkedList = new LinkedList();
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file : listFiles) {
                String c2 = CommonUtil.c(file.getName());
                LogUtil.a("UploadLogUtil", "getEventLogList fileName: ", c2);
                if (c2 != null) {
                    if (d(c2, str2)) {
                        linkedList.add(file);
                    } else {
                        e(file, str2);
                    }
                }
            }
        }
        return linkedList;
    }

    private static void b(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) EventLogUploadService.class);
        intent.putExtra("x-huid", o());
        intent.putExtra("x-version", CommonUtil.e(context));
        intent.putExtra("ts", new Date().getTime());
        intent.putExtra("tokenType", ThirdLoginDataStorageUtil.getTokenTypeValue());
        intent.putExtra("appId", context.getPackageName());
        intent.putExtra("siteId", Integer.valueOf(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)));
        intent.putExtra("iversion", 1);
        intent.putExtra("source", 2);
        intent.putExtra("filePath", str);
        intent.putExtra("countryCode", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        String b2 = jrx.b(str2);
        String str4 = "";
        if ("".equals(b2)) {
            intent.putExtra("model", Build.MODEL);
            intent.putExtra(FaqConstants.FAQ_EMUIVERSION, r("unknown"));
        } else {
            intent.putExtra("model", b2);
            intent.putExtra(FaqConstants.FAQ_EMUIVERSION, "5.0");
        }
        intent.putExtra("osVersion", Build.VERSION.RELEASE);
        intent.putExtra(FaqConstants.FAQ_ROMVERSION, d(BaseApplication.getContext()));
        DeviceInfo f2 = f(c(str, str3));
        if (f2 != null) {
            String a2 = knl.a(f2.getSecurityDeviceId());
            if (!TextUtils.isEmpty(a2)) {
                str4 = a2.toLowerCase(Locale.ENGLISH);
            }
        } else {
            str4 = "0000000000000000";
        }
        intent.putExtra(FaqConstants.FAQ_SHASN, TextUtils.isEmpty(str4) ? "0000000000000000" : str4);
        try {
            context.startService(intent);
        } catch (IllegalStateException unused) {
            ReleaseLogUtil.c("UploadLogUtil", "setEventIntentData IllegalStateException");
        } catch (SecurityException e2) {
            ReleaseLogUtil.c("Dfx_UploadLogUtil", "setEventIntentData SecurityException: ", ExceptionUtils.d(e2));
        }
    }

    private static String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("UploadLogUtil", "getAppVersionName() NameNotFoundException");
            return "2.0";
        }
    }

    private static String o() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        return accountInfo == null ? "" : accountInfo;
    }

    private static void c(Context context, String str) {
        FileInputStream fileInputStream;
        int i2;
        int available;
        byte[] bArr;
        byte[] bArr2;
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "encrypt event log, deviceId = ", CommonUtil.l(str));
        String str2 = c + "MaintenanceLog";
        List<File> e2 = e(str2, str);
        if (e2 == null) {
            LogUtil.h("UploadLogUtil", "encryptEventLog linkedList is null");
            return;
        }
        boolean i3 = i();
        FileOutputStream fileOutputStream = null;
        int i4 = 0;
        FileInputStream fileInputStream2 = null;
        int i5 = 0;
        while (i5 < e2.size()) {
            File file = e2.get(i5);
            String name = file.getName();
            if (!(name.contains("bigdata.log") && TextUtils.isEmpty(str) && !j()) && (!name.contains("gpu_update.log") || ((name.contains(a(f(str))) && !TextUtils.isEmpty(str)) || !(!i3)))) {
                try {
                    try {
                        fileInputStream = new FileInputStream(new File(CommonUtil.c(str2 + "/" + name)));
                        try {
                            available = fileInputStream.available();
                            bArr = new byte[available + 4];
                            System.arraycopy(e(available), i4, bArr, i4, 4);
                            bArr2 = new byte[available];
                        } catch (FileNotFoundException unused) {
                        } catch (IOException unused2) {
                        } catch (Exception unused3) {
                        } catch (OutOfMemoryError unused4) {
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                    }
                } catch (FileNotFoundException unused5) {
                } catch (IOException unused6) {
                } catch (Exception unused7) {
                } catch (OutOfMemoryError unused8) {
                }
                if (fileInputStream.read(bArr2) != -1) {
                    System.arraycopy(bArr2, 0, bArr, 4, available);
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(CommonUtil.c(jrx.a(name))));
                    try {
                        byte[] c2 = HwEncryptUtil.c(context).c(2, bArr);
                        fileOutputStream2.write(c2, 0, c2.length);
                        fileOutputStream2.flush();
                        e(file, str);
                        jsf.c(fileInputStream, fileOutputStream2);
                        fileOutputStream = fileOutputStream2;
                    } catch (FileNotFoundException unused9) {
                        fileOutputStream = fileOutputStream2;
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("UploadLogUtil", "encrypt file error FileNotFoundException");
                        fileInputStream = fileInputStream2;
                        jsf.c(fileInputStream, fileOutputStream);
                        fileInputStream2 = fileInputStream;
                        i2 = 0;
                        i5++;
                        i4 = i2;
                    } catch (IOException unused10) {
                        fileOutputStream = fileOutputStream2;
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("UploadLogUtil", "encrypt file error IOException");
                        fileInputStream = fileInputStream2;
                        jsf.c(fileInputStream, fileOutputStream);
                        fileInputStream2 = fileInputStream;
                        i2 = 0;
                        i5++;
                        i4 = i2;
                    } catch (Exception unused11) {
                        fileOutputStream = fileOutputStream2;
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("UploadLogUtil", "encrypt file error exception");
                        fileInputStream = fileInputStream2;
                        jsf.c(fileInputStream, fileOutputStream);
                        fileInputStream2 = fileInputStream;
                        i2 = 0;
                        i5++;
                        i4 = i2;
                    } catch (OutOfMemoryError unused12) {
                        fileOutputStream = fileOutputStream2;
                        fileInputStream2 = fileInputStream;
                        i2 = 0;
                        LogUtil.b("UploadLogUtil", "encryptEventLog OutOfMemoryError");
                        jsf.c(fileInputStream2, fileOutputStream);
                        i5++;
                        i4 = i2;
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = fileOutputStream2;
                        jsf.c(fileInputStream, fileOutputStream);
                        throw th;
                    }
                    fileInputStream2 = fileInputStream;
                    i2 = 0;
                } else {
                    jsf.c(fileInputStream, fileOutputStream);
                    fileInputStream2 = fileInputStream;
                    i2 = 0;
                }
            } else {
                i2 = i4;
            }
            i5++;
            i4 = i2;
        }
    }

    private static byte[] e(int i2) {
        byte[] bArr = new byte[4];
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[i3] = (byte) ((i2 >> (24 - (i3 * 8))) & 255);
        }
        return bArr;
    }

    public static boolean g(String str) {
        boolean z = cvs.e(str) != null && cvs.e(str).isSupportSelfUploadDeviceLog();
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "isSelfUploadDeviceLog: ", Boolean.valueOf(z));
        return z;
    }

    public static boolean d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }
        boolean d2 = blk.e().d(deviceInfo.getDeviceIdentify(), 10);
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "isSupportSmartMcuDeviceLog isSupportDual: ", Boolean.valueOf(d2));
        d(deviceInfo.getDeviceIdentify(), d2);
        return d2;
    }

    private static void d(String str, boolean z) {
        LogUtil.a("UploadLogUtil", "enter setSmartMcuDeviceLogResult");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), str + "_mcu_device_result", String.valueOf(z), (StorageParams) null);
    }

    private static boolean l(String str) {
        LogUtil.a("UploadLogUtil", "enter getSmartMcuDeviceLogResult");
        Context context = BaseApplication.getContext();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_mcu_device_result");
        return "true".equals(SharedPreferenceManager.b(context, String.valueOf(10), sb.toString()));
    }

    private static void b(Context context, boolean z, boolean z2, String str) {
        String e2;
        LogUtil.a("UploadLogUtil", "enter UploadDevicelog");
        synchronized (e) {
            String o2 = LogConfig.o();
            String str2 = c + "MaintenanceLog";
            b(z2, str2, str);
            LinkedList<File> e3 = jsj.e(str2);
            if (e3 == null || e3.size() == 0) {
                LogUtil.a("UploadLogUtil", "UploadDevicelog() deviceFileList is null.");
                return;
            }
            try {
                e2 = e((List<File>) e3, str);
                LogUtil.a("UploadLogUtil", "UploadDevicelog fullFileName: ", e2);
            } catch (OutOfMemoryError unused) {
                LogUtil.b("UploadLogUtil", "UploadDevicelog OutOfMemoryError");
            }
            if (!TextUtils.isEmpty(e2) && e2.contains("_")) {
                if (e2.endsWith("_Beta_mcu_upg.log")) {
                    e2 = e2.replace("_Beta_mcu_upg", "_WearableBeta");
                }
                int indexOf = e2.indexOf("_WearableBeta") + 13;
                LogUtil.a("UploadLogUtil", "uploadDevicelog index=", Integer.valueOf(indexOf));
                if (indexOf != -1) {
                    StringBuilder sb = new StringBuilder();
                    String str3 = b;
                    sb.append(str3);
                    sb.append(e2.substring(0, indexOf));
                    sb.append(".zip");
                    String e4 = jrx.e(CommonUtil.c(e(sb.toString(), z2)));
                    LogUtil.a("UploadLogUtil", "UploadDevicelog targetPath: ", e4);
                    LinkedList<File> e5 = jsj.e(o2);
                    if (e5 != null && e5.size() != 0) {
                        LogUtil.a("UploadLogUtil", "list: ", e5.toString());
                        q.clear();
                        d(z2, e5, str);
                        a(z2, z, e3, e4, str);
                        boolean c2 = cwi.c(f(str), 217);
                        LogUtil.a("UploadLogUtil", "UploadDeviceLog isSupportSmartWifiLog = " + c2 + ", isSendFeedbackUseFlow = " + h);
                        if (!bky.i() && c2 && h) {
                            LinkedList<File> b2 = jsj.b(str3);
                            LogUtil.a("UploadLogUtil", "UploadDeviceLog deviceListFiles: ", b2.toString());
                            Iterator<File> it = b2.iterator();
                            while (it.hasNext()) {
                                o.add(b + it.next().getName());
                            }
                        }
                    }
                    LogUtil.c("UploadLogUtil", "list is null");
                    return;
                }
                return;
            }
            LogUtil.a("UploadLogUtil", "uploadDevicelog fullFileName is null.");
        }
    }

    private static String e(String str, boolean z) {
        int lastIndexOf = str.lastIndexOf("_");
        if (lastIndexOf == -1) {
            return str;
        }
        if (z) {
            return str.substring(0, lastIndexOf) + "_00000000_device" + str.substring(lastIndexOf, str.length());
        }
        return (str.substring(0, lastIndexOf) + "_00000000_app" + str.substring(lastIndexOf, str.length())).replace("-HONOR", "");
    }

    private static String e(List<File> list, String str) {
        String str2;
        String a2 = a(f(str));
        Iterator<File> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = "";
                break;
            }
            File next = it.next();
            if (next.getName().contains(a2)) {
                str2 = next.getName();
                break;
            }
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).getName().contains(a2)) {
                String name = list.get(i2).getName();
                boolean z = true;
                boolean z2 = name.contains("_&_&") || name.contains("bigdata.log");
                if (!name.contains("_&_&") && !name.contains("gpu_update.log")) {
                    z = false;
                }
                if (!name.endsWith("_WearableBeta_mcu_upg.log") && !name.endsWith("_Beta_mcu_upg.log") && !name.endsWith("appdft_Beta.zip") && !z2 && !z) {
                    return CommonUtil.c(name);
                }
            }
        }
        return CommonUtil.c(str2);
    }

    private static void b(boolean z, String str, String str2) {
        if (z) {
            String str3 = j;
            File file = new File(str3);
            LogUtil.a("UploadLogUtil", "ota log path: ", str3, " devicePath: ", str, " oldFile.exists() is ", Boolean.valueOf(file.exists()));
            if (!file.exists()) {
                LogUtil.a("UploadLogUtil", "otaLogPath exists false");
                return;
            }
            File file2 = new File(str);
            if (!file2.exists()) {
                LogUtil.a("UploadLogUtil", "isSuccess = ", Boolean.valueOf(file2.mkdirs()));
            }
            String[] list = file.list();
            if (list == null) {
                LogUtil.a("UploadLogUtil", "ota log path file list = null");
                return;
            }
            for (int i2 = 0; i2 < list.length; i2++) {
                if (list[i2].endsWith("appdft_Beta.zip")) {
                    LogUtil.a("UploadLogUtil", "appdft is not cut");
                } else {
                    File file3 = new File(CommonUtil.c(j + list[i2]));
                    if (file3.isFile() && file3.exists()) {
                        String a2 = a(f(str2));
                        if (file3.getName().contains("_Beta_mcu_upg.log") && file3.getName().contains(a2)) {
                            b(file3, str);
                        }
                    } else {
                        LogUtil.c("UploadLogUtil", "enter else");
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00f5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0147 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void b(java.io.File r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jsd.b(java.io.File, java.lang.String):void");
    }

    private static void f() {
        String str = r;
        if (str == null || str.length() <= 0) {
            return;
        }
        File file = new File(r);
        if (file.exists()) {
            if (file.delete()) {
                r = null;
                LogUtil.c("UploadLogUtil", "dir delete success!");
            } else {
                LogUtil.a("UploadLogUtil", "dir delete failed!");
            }
        }
    }

    private static void k(String str) {
        ArrayList<File> d2 = jsf.d(str);
        if (d2 == null) {
            LogUtil.a("UploadLogUtil", "deleteTenDayFile(), not have ten days log");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH);
        String format = simpleDateFormat.format(new Date());
        LogUtil.c("UploadLogUtil", "deleteTenDayFile, newDate = ", format);
        String str2 = "";
        for (int i2 = 0; i2 < d2.size(); i2++) {
            File file = d2.get(i2);
            String[] split = file.getName().split("_");
            if (split.length > 4) {
                str2 = split[3];
            } else {
                LogUtil.c("UploadLogUtil", "deleteTenDayFile(), file name contains no date.");
            }
            try {
                long time = (simpleDateFormat.parse(format).getTime() - simpleDateFormat.parse(str2).getTime()) / 86400000;
                if (time > 10 && !file.getName().contains("_WearableBeta_app_bigdata.log") && !file.getName().contains("_WearableBeta_gpu_update.log")) {
                    LogUtil.a("UploadLogUtil", "deleteTenDayFile, delete days = ", Long.valueOf(time), " file = ", file.getName());
                    LogUtil.c("UploadLogUtil", "deleteTenDayFile isDelete: ", Boolean.valueOf(file.delete()));
                }
            } catch (ParseException e2) {
                LogUtil.b("UploadLogUtil", "PackageManager.ParseException is:" + e2.getMessage());
            }
        }
    }

    private static String b(Context context, String str) {
        if (context == null || str == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager != null ? packageManager.getPackageInfo(str, 16384) : null;
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.b("UploadLogUtil", "PackageManager.NameNotFoundException is:" + e2.getMessage());
            return null;
        }
    }

    private static void g(String str, String str2) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(CommonUtil.c(str));
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
        } catch (UnsupportedEncodingException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
        } catch (OutOfMemoryError unused) {
        }
        try {
            fileOutputStream.write(str2.getBytes("UTF-8"));
            try {
                fileOutputStream.close();
            } catch (IOException e5) {
                LogUtil.b("UploadLogUtil", "writeFileSdcard ioException ", e5.getMessage());
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("UploadLogUtil", "PackageManager.FileNotFoundException is:" + e.getMessage());
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e7) {
                    LogUtil.b("UploadLogUtil", "writeFileSdcard ioException ", e7.getMessage());
                }
            }
        } catch (UnsupportedEncodingException e8) {
            e = e8;
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("UploadLogUtil", "PackageManager.UnsupportedEncodingException is:" + e.getMessage());
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e9) {
                    LogUtil.b("UploadLogUtil", "writeFileSdcard ioException ", e9.getMessage());
                }
            }
        } catch (IOException e10) {
            e = e10;
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("UploadLogUtil", "PackageManager.IOException is:" + e.getMessage());
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e11) {
                    LogUtil.b("UploadLogUtil", "writeFileSdcard ioException ", e11.getMessage());
                }
            }
        } catch (OutOfMemoryError unused2) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.c("UploadLogUtil", "writeFileSdcard OutOfMemoryError");
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e12) {
                    LogUtil.b("UploadLogUtil", "writeFileSdcard ioException ", e12.getMessage());
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e13) {
                    LogUtil.b("UploadLogUtil", "writeFileSdcard ioException ", e13.getMessage());
                }
            }
            throw th;
        }
    }

    private static String r(String str) {
        String ai = CommonUtil.ai();
        return (TextUtils.isEmpty(ai) || "default".equals(ai)) ? str : ai;
    }

    public static boolean g() {
        File[] listFiles = new File(b).listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return true;
        }
        LogUtil.c("UploadLogUtil", "file size: " + listFiles.length);
        int length = listFiles.length;
        for (int i2 = 0; i2 < length; i2++) {
            File file = listFiles[i2];
            LogUtil.c("UploadLogUtil", "file name: ", file.getName());
            if (file.getName().endsWith("WearAPPBeta.zip") || file.getName().endsWith("WearableBeta.zip")) {
                return false;
            }
        }
        return true;
    }

    private static String m() {
        String str = LogConfig.m() + "bbb/";
        if (!new File(str).exists()) {
            str = LogConfig.m() + "SmartWearableDFX/";
        }
        LogUtil.c("UploadLogUtil", "obtainSavePath path is: ", str);
        return str;
    }

    private static String n() {
        String str = LogConfig.m() + "SmartWearableDFXHonor/";
        File file = new File(str);
        if (!file.exists()) {
            ReleaseLogUtil.e("Dfx_UploadLogUtil", "obtainHonorSaveFolder mkdirs ", Boolean.valueOf(file.mkdirs()));
        }
        return str;
    }

    public static boolean h(String str) {
        String c2 = CommonUtil.c(j);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("UploadLogUtil", "pathName is empty");
            return false;
        }
        File file = new File(CommonUtil.c(c2));
        if (!file.exists()) {
            LogUtil.a("UploadLogUtil", "mkdirs result: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        String a2 = a(f(str));
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.getName().contains("_mcu_upg") && file2.getName().contains(a2)) {
                    LogUtil.a("UploadLogUtil", "has this device id upg log");
                    return true;
                }
            }
        }
        return false;
    }

    public static void e(String str, String str2, String str3, String str4) {
        synchronized (jsd.class) {
            e(str, str2, str3, "", str4);
        }
    }

    public static void e(String str, String str2, String str3, String str4, String str5) {
        synchronized (jsd.class) {
            LinkedList<File> c2 = jsj.c(CommonUtil.c(j));
            if (c2 == null || c2.size() == 0) {
                return;
            }
            try {
                for (File file : c2) {
                    if (!file.getName().contains(str5)) {
                        LogUtil.a("UploadLogUtil", "addOtaLog file not contains shaDeviceId.");
                    } else {
                        LogUtil.a("UploadLogUtil", "addOtaLog otalog otaFiles size:", Integer.valueOf(c2.size()), "; otaFiles=", c2.toString(), "; file=", file.getName());
                        a(str, str2, str3, str4, file);
                    }
                }
            } catch (OutOfMemoryError unused) {
                LogUtil.a("UploadLogUtil", "addOtaLog UploadOtaLog OutOfMemoryError");
            }
        }
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Failed to calculate best type for var: r5v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v10 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v11 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v12 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v13 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v4 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v5 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v6 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v7 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r5v8 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0091: MOVE (r3 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:38:0x0090 */
    private static void a(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.io.File r15) {
        /*
            java.lang.String r0 = "UploadLogUtil"
            r1 = 0
            r2 = 1
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            r4.<init>()     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            java.lang.String r5 = defpackage.jsd.j     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            r4.append(r5)     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            java.lang.String r15 = r15.getName()     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            r4.append(r15)     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            java.lang.String r15 = r4.toString()     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            java.lang.String r15 = health.compact.a.CommonUtil.c(r15)     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            r4.<init>(r15, r2)     // Catch: java.lang.Throwable -> L70 java.lang.OutOfMemoryError -> L74 java.io.IOException -> L80
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L69 java.lang.OutOfMemoryError -> L6c java.io.IOException -> L6e
            java.lang.String r6 = "UTF-8"
            r5.<init>(r4, r6)     // Catch: java.lang.Throwable -> L69 java.lang.OutOfMemoryError -> L6c java.io.IOException -> L6e
            java.io.BufferedWriter r6 = new java.io.BufferedWriter     // Catch: java.lang.OutOfMemoryError -> L76 java.io.IOException -> L82 java.lang.Throwable -> L8f
            r6.<init>(r5)     // Catch: java.lang.OutOfMemoryError -> L76 java.io.IOException -> L82 java.lang.Throwable -> L8f
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            r3.<init>(r15)     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            boolean r15 = r3.exists()     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            if (r15 == 0) goto L5e
            long r7 = r3.length()     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            r9 = 0
            int r15 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r15 <= 0) goto L5e
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            r15.<init>()     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            java.lang.String r3 = java.lang.System.lineSeparator()     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            r15.append(r3)     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            java.lang.String r11 = c(r12, r13, r11, r14)     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            r15.append(r11)     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            java.lang.String r11 = r15.toString()     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
            r6.write(r11)     // Catch: java.lang.Throwable -> L62 java.lang.OutOfMemoryError -> L65 java.io.IOException -> L67
        L5e:
            b(r6, r5, r4)
            goto L8e
        L62:
            r11 = move-exception
            r3 = r6
            goto L94
        L65:
            r3 = r6
            goto L76
        L67:
            r3 = r6
            goto L82
        L69:
            r11 = move-exception
            r5 = r3
            goto L94
        L6c:
            r5 = r3
            goto L76
        L6e:
            r5 = r3
            goto L82
        L70:
            r11 = move-exception
            r4 = r3
            r5 = r4
            goto L94
        L74:
            r4 = r3
            r5 = r4
        L76:
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L8f
            java.lang.String r12 = "addOtaLog UploadOtaLog OutOfMemoryError"
            r11[r1] = r12     // Catch: java.lang.Throwable -> L8f
            com.huawei.hwlogsmodel.LogUtil.a(r0, r11)     // Catch: java.lang.Throwable -> L8f
            goto L8b
        L80:
            r4 = r3
            r5 = r4
        L82:
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L8f
            java.lang.String r12 = "Exception ioException"
            r11[r1] = r12     // Catch: java.lang.Throwable -> L8f
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)     // Catch: java.lang.Throwable -> L8f
        L8b:
            b(r3, r5, r4)
        L8e:
            return
        L8f:
            r11 = move-exception
            r12 = r3
            r3 = r5
            r5 = r3
            r3 = r12
        L94:
            b(r3, r5, r4)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jsd.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.File):void");
    }

    private static void m(String str) {
        LinkedList<File> c2 = jsj.c(CommonUtil.c(j));
        if (c2 != null) {
            try {
                if (c2.size() > 0) {
                    LogUtil.a("UploadLogUtil", "deleteUpgLog otalog otaFiles ", Integer.valueOf(c2.size()));
                    a(c2, str);
                }
            } catch (OutOfMemoryError unused) {
                LogUtil.a("UploadLogUtil", "deleteUpgLog UploadOtaLog OutOfMemoryError");
            }
        }
    }

    public static void d(Context context, int i2, String str) {
        if (context == null) {
            return;
        }
        if (jsf.e(context)) {
            LogUtil.h("UploadLogUtil", "uploadUpglog:isAuto isBatteryControl not upload");
            return;
        }
        synchronized (e) {
            LinkedList<File> c2 = jsj.c(CommonUtil.c(j));
            if (c2 != null && c2.size() != 0) {
                String a2 = a(f(str));
                try {
                    for (File file : c2) {
                        LogUtil.a("UploadLogUtil", "uploadUpglog otalog otaFiles ", Integer.valueOf(c2.size()), " uploadUpglog otaFiles ", c2.toString(), " uploadUpglog otalog otaFiles file ", file.getName());
                        String name = file.getName();
                        if (!file.getName().contains(a2)) {
                            LogUtil.a("UploadLogUtil", "file name not contains shaDeviceId");
                        } else {
                            String x = x(name);
                            LogUtil.a("UploadLogUtil", "uploadUpglog otalog otaFiles targetPath ", x);
                            String c3 = CommonUtil.c(x);
                            if (x != null && c3 != null) {
                                LinkedList linkedList = new LinkedList();
                                linkedList.add(file);
                                jsj.e(linkedList, new File(c3), "", a2);
                                linkedList.clear();
                                LogUtil.a("UploadLogUtil", " uploadUpglog log");
                                if (i2 == 2) {
                                    b(context, c3, x, i2, str);
                                } else {
                                    d(context, x, i2, str);
                                }
                            }
                            return;
                        }
                    }
                } catch (IOException e2) {
                    LogUtil.b("UploadLogUtil", "Exception ioException ", e2.getMessage());
                } catch (OutOfMemoryError unused) {
                    LogUtil.a("UploadLogUtil", "uploadUpglog UploadOtaLog OutOfMemoryError");
                }
            }
        }
    }

    private static void b(Context context, String str, String str2, int i2, String str3) {
        File file = new File(str);
        if (!file.isFile()) {
            LogUtil.a("UploadLogUtil", "uploadUpglog() fileZip is not a file.");
            return;
        }
        long length = file.length();
        LogUtil.a("UploadLogUtil", "LogCanUpload length is", Long.valueOf(length));
        if (b(context, length)) {
            LogUtil.a("UploadLogUtil", "LogCanUpload is true");
            d(context, str2, i2, str3);
        }
    }

    private static String x(String str) {
        String str2;
        if (!TextUtils.isEmpty(str) && str.contains("_WearableBeta_mcu_upg.log")) {
            int indexOf = str.indexOf("_WearableBeta") + 13;
            LogUtil.a("UploadLogUtil", "uploadUpglog otalog otaFiles index ", Integer.valueOf(indexOf));
            if (indexOf != -1) {
                str2 = j + str.substring(0, indexOf) + ".zip";
                int lastIndexOf = str2.lastIndexOf("_");
                if (lastIndexOf != -1) {
                    str2 = str2.substring(0, lastIndexOf) + "_00000000_device" + str2.substring(lastIndexOf, str2.length());
                }
                if (TextUtils.isEmpty(str) && str.contains("_Beta_mcu_upg.log")) {
                    int indexOf2 = str.indexOf("_Beta") + 5;
                    LogUtil.a("UploadLogUtil", "uploadUpglog otalog otaFiles index ", Integer.valueOf(indexOf2));
                    if (indexOf2 == -1) {
                        return str2;
                    }
                    String str3 = j + str.substring(0, indexOf2) + ".zip";
                    int lastIndexOf2 = str3.lastIndexOf("_");
                    if (lastIndexOf2 == -1) {
                        return str3;
                    }
                    return str3.substring(0, lastIndexOf2) + "_00000000_appdft" + str3.substring(lastIndexOf2, str3.length());
                }
            }
        }
        str2 = "";
        return TextUtils.isEmpty(str) ? str2 : str2;
    }

    public static void e(String str) {
        DeviceInfo f2 = f(str);
        if (f2 == null) {
            LogUtil.a("UploadLogUtil", "currentDeviceInfo is null or device is Aw70");
        } else {
            c(f2);
        }
    }

    private static String c(String str, String str2, String str3, String str4) {
        JSONException e2;
        Context context = BaseApplication.getContext();
        String str5 = "";
        if (context == null) {
            LogUtil.a("UploadLogUtil", "getOtaLog context is null");
            return "";
        }
        String d2 = d(System.currentTimeMillis());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eventid", str3);
            if (str3.equals("0D0B01")) {
                String b2 = jrx.b(jSONObject, context, str, str2);
                jsc.f(context);
                return b2;
            }
            try {
                if (str3.equals("910401")) {
                    str = c(jSONObject, context, str, str2);
                    SharedPreferenceManager.d(context, 0L, "EXCE_DFT_APP_SYNSTART_TIME");
                    SharedPreferenceManager.d(context, "", "EXCE_DFT_APP_UPLOADRESULT");
                } else {
                    if (str3.equals("910402")) {
                        jSONObject.put("datatime", str2);
                        jSONObject.put(str, p);
                        return jSONObject.toString();
                    }
                    if (str3.startsWith("03")) {
                        LogUtil.a("UploadLogUtil", "getOtaLog SLEEP RECORD START");
                        jSONObject.put("datatime", str4);
                        jSONObject.put(str, str2);
                        return jSONObject.toString();
                    }
                    jSONObject.put("datatime", d2);
                    jSONObject.put(str, str2);
                    String b3 = SharedPreferenceManager.b(context, "EXCE_DFT_APP_UPLOADRESULT");
                    if (str3.equals("910403")) {
                        b3 = b3 + "," + p;
                    }
                    jSONObject.put("EXCE_DFT_APP_UPLOADRESULT", b3);
                    str = jSONObject.toString();
                    SharedPreferenceManager.d(context, 0L, "EXCE_DFT_APP_UPLOADSTART");
                    SharedPreferenceManager.d(context, "", "EXCE_DFT_APP_UPLOADRESULT");
                }
                return str;
            } catch (JSONException e3) {
                e2 = e3;
                str5 = str;
                LogUtil.a("UploadLogUtil", "getOtaLog jsonException ", e2.getMessage());
                return str5;
            }
        } catch (JSONException e4) {
            e2 = e4;
        }
    }

    private static String c(JSONObject jSONObject, Context context, String str, String str2) {
        long c2 = SharedPreferenceManager.c(context, "EXCE_DFT_APP_SYNSTART_TIME");
        String b2 = SharedPreferenceManager.b(context, "EXCE_DFT_APP_SYNSTART");
        String d2 = d(c2);
        try {
            jSONObject.put("datatime", d2);
            jSONObject.put("EXCE_DFT_APP_SYNSTART", d2);
            jSONObject.put(str, str2);
            jSONObject.put("EXCE_DFT_APP_SYNRESULT", b2);
        } catch (JSONException unused) {
            LogUtil.b("UploadLogUtil", "JSONException jsonException");
        }
        return jSONObject.toString();
    }

    public static String e(Context context, String str, String str2, String str3) {
        String r2 = r("unknown");
        String b2 = b(context, "com.huawei.crowdtest");
        if (TextUtils.isEmpty(b2)) {
            b2 = "";
        }
        String b3 = b(context, "com.huawei.bone");
        if (TextUtils.isEmpty(b3)) {
            b3 = "";
        }
        String b4 = b(context, "com.huawei.health");
        if (TextUtils.isEmpty(b4)) {
            b4 = "";
        }
        String d2 = d(System.currentTimeMillis());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("log_version", "1.0.0.1");
            jSONObject.put("file_time", d2);
            jSONObject.put("wear_device_type", str);
            jSONObject.put("wear_device_id", str2);
            jSONObject.put("wear_device_version", str3);
            jSONObject.put("phone_os_version", Build.VERSION.RELEASE);
            jSONObject.put("betaclub_version", b2);
            jSONObject.put("phone_bone_version", b3);
            jSONObject.put("phone_brand", Build.BRAND);
            jSONObject.put("phone_device", Build.DISPLAY);
            jSONObject.put("health_version", b4);
            jSONObject.put("phone_model", Build.MODEL);
            jSONObject.put("phone_ui_version", r2);
            return jSONObject.toString();
        } catch (JSONException e2) {
            LogUtil.a("UploadLogUtil", "JSONException jsonException ", e2.getMessage());
            return "";
        }
    }

    public static String d(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date(j2));
    }

    public static void a(Context context, final String str) {
        if (context == null) {
            LogUtil.a("UploadLogUtil", "setCreateLogFile context is null.");
        } else {
            if (CommonUtil.ag(context)) {
                return;
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: jsd.1
                @Override // java.lang.Runnable
                public void run() {
                    if (jsd.h(str) && jsd.d(str)) {
                        return;
                    }
                    jsd.e(str);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean b(android.content.Context r10, long r11) {
        /*
            r0 = 0
            java.lang.String r2 = "goal_steps_perference"
            java.lang.String r3 = "update_log_time"
            long r0 = health.compact.a.SharedPreferenceManager.b(r2, r3, r0)
            java.util.Date r4 = defpackage.jec.e()
            long r4 = defpackage.jec.n(r4)
            int r6 = health.compact.a.SharedPreferenceManager.e(r10)
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            r1 = 0
            r7 = 1
            if (r0 == 0) goto L21
            health.compact.a.SharedPreferenceManager.b(r10, r1)
            goto L25
        L21:
            r0 = 100
            if (r6 > r0) goto L27
        L25:
            r0 = r7
            goto L28
        L27:
            r0 = r1
        L28:
            java.lang.String r8 = "LogCanUpload isNoSameDay is "
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r9}
            java.lang.String r9 = "UploadLogUtil"
            com.huawei.hwlogsmodel.LogUtil.a(r9, r8)
            boolean r8 = c(r10)
            if (r8 != 0) goto L52
            r8 = 1048576(0x100000, double:5.180654E-318)
            long r11 = r11 / r8
            r8 = 1
            int r11 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r11 > 0) goto L51
            if (r0 == 0) goto L51
            health.compact.a.SharedPreferenceManager.e(r2, r3, r4)
            int r6 = r6 + r7
            health.compact.a.SharedPreferenceManager.b(r10, r6)
            return r7
        L51:
            return r1
        L52:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jsd.b(android.content.Context, long):boolean");
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Failed to calculate best type for var: r9v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v10 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v11 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v12 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v4 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v5 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v6 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v7 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v8 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r9v9 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0124: MOVE (r5 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY]), block:B:60:0x0123 */
    private static void c(com.huawei.health.devicemgr.business.entity.DeviceInfo r11) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jsd.c(com.huawei.health.devicemgr.business.entity.DeviceInfo):void");
    }

    private static void b(BufferedWriter bufferedWriter, OutputStreamWriter outputStreamWriter, FileOutputStream fileOutputStream) {
        if (bufferedWriter != null) {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException unused) {
                LogUtil.b("UploadLogUtil", "writeFileSdcard ioException");
                return;
            }
        }
        if (outputStreamWriter != null) {
            outputStreamWriter.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }

    public static String a(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.b("UploadLogUtil", "getShaDeviceId currentDeviceInfo is null");
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
        return n(securityDeviceId);
    }

    public static String n(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("UploadLogUtil", "setDeviceMac deviceMac is null");
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

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, String str2, int i2, long j2, String str3) {
        LogUtil.a("UploadLogUtil", "handleOnSuccess uploadFileName = " + str + ", targetPath = " + str2 + ", uploadType = " + i2 + ", startUploadTime = " + j2 + ", deviceId = " + CommonUtil.l(str3) + ", targetPath isExist:" + new File(str2).exists());
        if (i2 == 2) {
            LogUtil.a("UploadLogUtil", "upload log success.");
            m(str3);
            jsc.b(BaseApplication.getContext());
        } else if (i2 == 0) {
            DeviceInfo f2 = f(str3);
            if (f2 != null) {
                SharedPreferenceManager.d(BaseApplication.getContext(), "success", "EXCE_DFT_APP_UPLOADRESULT");
                LogUtil.a("UploadLogUtil", "start create log file.");
                a(BaseApplication.getContext(), str3);
                p = p(str2);
                e("910403", "EXCE_DFT_APP_UPLOADSTOP", ((System.currentTimeMillis() - j2) / 1000) + "", a(f2));
            }
        } else {
            LogUtil.a("UploadLogUtil", "handleOnSuccess targetPath:", str2);
        }
        LogUtil.a("UploadLogUtil", "startUploadLog: file: ", str2, "; upload success: " + str + ", handleOnSuccess targetPath isExist = " + new File(str2).exists());
    }

    private static void d(Context context, String str) {
        String b2 = b(context, "com.huawei.crowdtest");
        if (TextUtils.isEmpty(b2)) {
            b2 = "";
        }
        String b3 = b(context, "com.huawei.bone");
        if (TextUtils.isEmpty(b3)) {
            b3 = "";
        }
        String b4 = b(context, "com.huawei.health");
        String str2 = TextUtils.isEmpty(b4) ? "" : b4;
        g(str, "{\"os_version\":\"" + Build.VERSION.RELEASE + "\",\"betaclubVersion\":\"" + b2 + "\",\"boneVersion\":\"" + b3 + "\",\"brand\":\"" + Build.BRAND + "\",\"device\":\"" + Build.DISPLAY + "\",\"health_version\":\"" + str2 + "\",\"model\":\"" + Build.MODEL + "\",\"ui_version\":\"" + r("unknown") + "\"}");
    }

    private static void c(Context context, boolean z, String str) {
        DeviceInfo f2 = f(str);
        boolean c2 = cwi.c(f2, 217);
        boolean d2 = jsf.d();
        if (bky.i()) {
            if (!d2) {
                return;
            }
        } else if (!d2 && (!c2 || !jsf.b())) {
            return;
        }
        if (f2 == null) {
            LogUtil.h("UploadLogUtil", "zipLogFile() deviceInfo is null");
            return;
        }
        if (l(f2.getDeviceIdentify()) && z) {
            LogUtil.a("UploadLogUtil", "zipLogFile() isSupportSmartMcuDeviceLog");
            b(context, z, true, str);
        } else if (!g(str)) {
            LogUtil.a("UploadLogUtil", "zipLogFile() not isSelfUploadDeviceLog");
            b(context, z, true, str);
        }
        if (jsf.a(context, f2)) {
            b(context, z, false, str);
        } else {
            a(jsj.e(c + "MaintenanceLog"), str);
        }
        SharedPreferenceManager.e(String.valueOf(10), "contain_app_log", true);
    }

    private static void d(boolean z, List<File> list, String str) {
        if (z) {
            for (File file : list) {
                if (file.getName().equals("com.huawei.version.json")) {
                    q.add(file);
                }
                if (file.getName().equals("MaintenanceLog")) {
                    q.add(file);
                }
            }
            return;
        }
        if (CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") && g(str)) {
            f();
        }
        String a2 = a(f(str));
        for (File file2 : list) {
            if (g.equals(file2.getName())) {
                File[] listFiles = file2.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    LogUtil.h("UploadLogUtil", "originFiles is null");
                    return;
                }
                for (File file3 : listFiles) {
                    if (file3.getName().startsWith("log")) {
                        a(file3, a2);
                    }
                }
                q.add(file2);
            } else if (BaseApplication.getAppPackage().equals(file2.getName())) {
                q.add(file2);
            } else if (i.equals(file2.getName())) {
                q.add(file2);
            } else if (d.equals(file2.getName())) {
                q.add(file2);
            } else {
                LogUtil.c("UploadLogUtil", "no files to be added");
            }
        }
    }

    private static void a(File file, String str) {
        try {
            ArrayList arrayList = new ArrayList(16);
            String str2 = file.getCanonicalPath().substring(0, file.getCanonicalPath().length() - 5) + new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH).format(new Date(file.lastModified()));
            File file2 = new File(str2);
            CommonUtil.e(file.getCanonicalPath(), file2.getCanonicalPath(), true);
            arrayList.add(file2);
            jsj.e(arrayList, new File(str2 + ".zip"), "", str);
            if (!"log.0".equals(file.getName())) {
                LogUtil.a("UploadLogUtil", "delte file: ", Boolean.valueOf(file.delete()));
            }
            if (file2.delete()) {
                return;
            }
            LogUtil.h("UploadLogUtil", "updateFileList tempFile.delete() false");
        } catch (IOException unused) {
            LogUtil.b("UploadLogUtil", "updateFileList IOException");
        }
    }

    private static void a(boolean z, boolean z2, List<File> list, String str, String str2) {
        try {
            jsj.e(q, new File(str), "", a(f(str2)));
            if (!z) {
                jsj.d();
            }
            if (h) {
                o.add(str);
            }
        } catch (IOException e2) {
            LogUtil.b("UploadLogUtil", "deleteZipFiles ioException is:", e2.getMessage());
        }
        if (list.size() > 0 && !z) {
            a(list, str2);
        }
        LogUtil.a("UploadLogUtil", " uploadLog Prepare to upload device log files.");
        if (z2 && jsf.e(BaseApplication.getContext())) {
            LogUtil.h("UploadLogUtil", "deleteZipFiles:isAuto isBatteryControl not upload");
        } else {
            if (k || !c(BaseApplication.getContext())) {
                return;
            }
            LogUtil.a("UploadLogUtil", " uploadLog upload device log files");
            b(BaseApplication.getContext(), str, str2);
        }
    }

    private static void e(String str, String str2, Context context, String str3) {
        synchronized (f) {
            LogUtil.a("UploadLogUtil", "enter uploadZipFile: ", Integer.valueOf(n.size()));
            for (int i2 = 0; i2 < n.size(); i2++) {
                if (n.get(i2).contains(str)) {
                    LogUtil.a("UploadLogUtil", "current file is already upload");
                    return;
                }
            }
            if (str.contains("WearAPPBeta") || str.contains("WearableBeta")) {
                b(context, str2 + str, str3);
            }
        }
    }

    public static String c(int i2, String str) {
        if (a(i2)) {
            return d(i2);
        }
        if (i2 == 10) {
            return "Leo";
        }
        if (i2 == 23 || i2 == 24) {
            return "AW70";
        }
        if (i2 == 36 || i2 == 37) {
            return "AW70-B39";
        }
        if (i2 == 44) {
            return "Andes_B19";
        }
        if (i2 == 45) {
            return "Andes_B29";
        }
        switch (i2) {
            case 18:
                return "Crius";
            case 19:
                return "Terra";
            case 20:
                return "Talos";
            case 21:
                return "Fortuna";
            default:
                return e(i2, str);
        }
    }

    private static String e(int i2, String str) {
        String f2;
        if (ProcessUtil.b().endsWith(":PhoneService")) {
            f2 = juu.a(i2).c();
        } else {
            f2 = jfu.c(i2).f();
        }
        return b(f2, str);
    }

    private static String b(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        if (TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            return "HUAWEI WEAR";
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(str.charAt(i2) + "") || str.contains("_")) {
                return "HUAWEI WEAR";
            }
        }
        return str;
    }

    public static String e(int i2, String str, String str2) {
        return (i2 < 19 || i2 == 20 || TextUtils.isEmpty(str2)) ? str : str2;
    }

    private static boolean d(String str, String str2) {
        if (!cwi.c(f(str2), 129)) {
            return str.toLowerCase(Locale.ENGLISH).contains("event") || str.toLowerCase(Locale.ENGLISH).contains(VastAttribute.ERROR) || str.toLowerCase(Locale.ENGLISH).contains("wearablebeta_dump.log") || str.toLowerCase(Locale.ENGLISH).contains("power.log") || str.toLowerCase(Locale.ENGLISH).contains("fs.log") || str.toLowerCase(Locale.ENGLISH).contains("dump.log") || str.toLowerCase(Locale.ENGLISH).contains("bigdata.log") || str.toLowerCase(Locale.ENGLISH).contains("gpu_update.log");
        }
        ReleaseLogUtil.e("Dfx_UploadLogUtil", "device support log trust list is true");
        return true;
    }

    public static String j(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&:&");
        return split.length > 1 ? split[1] : "";
    }

    private static String e(Object obj, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UploadLogUtil", "devcieId is empty");
            return "";
        }
        if (obj == null) {
            obj = new Object();
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(obj.toString());
        sb.append("&:&");
        sb.append(str);
        return sb.toString();
    }

    public static DeviceInfo f(String str) {
        LogUtil.a("UploadLogUtil", "enter getConnectedDevice");
        DeviceInfo deviceInfo = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UploadLogUtil", "deviceId is empty");
            return null;
        }
        if (EnvironmentUtils.c()) {
            DeviceInfo e2 = jpt.e(str, "UploadLogUtil");
            if (e2 == null) {
                e2 = jpt.a("UploadLogUtil");
            }
            if (e2 == null) {
                e2 = jpu.d("UploadLogUtil");
            }
            deviceInfo = e2;
            Object[] objArr = new Object[2];
            objArr[0] = "isHealthMainProcess currentDeviceInfo is null : ";
            objArr[1] = Boolean.valueOf(deviceInfo == null);
            LogUtil.a("UploadLogUtil", objArr);
        } else if (EnvironmentUtils.e()) {
            DeviceInfo c2 = keb.c(str, "UploadLogUtil");
            if (c2 == null) {
                c2 = keb.b("UploadLogUtil");
            }
            if (c2 == null) {
                c2 = keb.c("UploadLogUtil");
            }
            deviceInfo = c2;
            Object[] objArr2 = new Object[2];
            objArr2[0] = "isPhoneServiceProcess currentDeviceInfo is null : ";
            objArr2[1] = Boolean.valueOf(deviceInfo == null);
            LogUtil.a("UploadLogUtil", objArr2);
        } else {
            LogUtil.h("UploadLogUtil", "getConnectedDevice is other Process");
        }
        return deviceInfo;
    }

    public static void c(TransferFileInfo transferFileInfo, int i2, Object obj, IBaseResponseCallback iBaseResponseCallback) {
        if (transferFileInfo == null || obj == null || iBaseResponseCallback == null) {
            LogUtil.h("UploadLogUtil", "transferFileInfo =", transferFileInfo, ", object = ", obj, ", callback = ", iBaseResponseCallback);
        } else if (transferFileInfo.getType() == 0) {
            iBaseResponseCallback.d(i2, e(obj, transferFileInfo.getDeviceSn()));
        } else {
            iBaseResponseCallback.d(i2, obj);
        }
    }

    public static boolean b() {
        LogUtil.a("UploadLogUtil", "enter getIsManualCollectLog");
        return "true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "_IS_MANUAL_COLLECT_LOG"));
    }

    public static void c(boolean z) {
        LogUtil.a("UploadLogUtil", "enter setIsManualCollectLog");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "_IS_MANUAL_COLLECT_LOG", String.valueOf(z), (StorageParams) null);
    }

    private static String d(int i2) {
        if (i2 == 1) {
            return "B2";
        }
        if (i2 == 2) {
            return "K1";
        }
        if (i2 == 3) {
            return "W1";
        }
        if (i2 == 5) {
            return "B0";
        }
        if (i2 == 7) {
            return "Gemini";
        }
        if (i2 == 8) {
            return "Metis";
        }
        switch (i2) {
            case 13:
                return "NYX";
            case 14:
                return "GRUS";
            case 15:
                return "Eris";
            case 16:
                return "Janus";
            default:
                return "HUAWEI WEAR";
        }
    }
}
