package defpackage;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.ToLongFunction;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes3.dex */
public class bls {
    private static final String b = LogConfig.o() + "MaintenanceLog/";

    private static boolean c(int i) {
        return i == 5 || i == 1 || i == 7 || i == 14 || i == 2 || i == 3 || i == 8 || i == 13 || i == 15 || i == 16;
    }

    private bls() {
    }

    public static void a(String str, String str2, int i, DeviceInfo deviceInfo) {
        synchronized (bls.class) {
            if (i == 1) {
                LogUtil.c("LogAppEventUtil", "addAppEventLog enter otaEventLog");
                d(str, str2, b, "_WearableBeta_gpu_update.log", deviceInfo);
            } else {
                d(str, str2, b, "_WearableBeta_app_bigdata.log", deviceInfo);
            }
        }
    }

    public static void d(final String str, final String str2, final String str3, final String str4, final DeviceInfo deviceInfo) {
        synchronized (bls.class) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bls.3
                @Override // java.lang.Runnable
                public void run() {
                    DeviceInfo c;
                    String str5;
                    String str6 = str2;
                    LogUtil.c("LogAppEventUtil", "addAppEventLog enter eventData: ", str, ",deviceName: ", str6, ", fileNameEnd = ", str4);
                    if (TextUtils.isEmpty(str)) {
                        ReleaseLogUtil.a("Dfx_LogAppEventUtil", "addAppEventLog eventData is empty");
                        return;
                    }
                    if (TextUtils.equals(str4, "_WearableBeta_gpu_update.log")) {
                        DeviceInfo deviceInfo2 = deviceInfo;
                        if (deviceInfo2 == null) {
                            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "addAppEventLog ota deviceInfo is null");
                            return;
                        } else {
                            str5 = deviceInfo2.getDeviceName();
                            c = deviceInfo;
                        }
                    } else {
                        if (TextUtils.isEmpty(str6)) {
                            str6 = SharedPreferenceManager.e(String.valueOf(10), "app_big_data_device_name", "");
                        }
                        c = blc.c(bls.c());
                        str5 = str6;
                    }
                    if (TextUtils.isEmpty(str5)) {
                        ReleaseLogUtil.a("Dfx_LogAppEventUtil", "createAppEventLogFile deviceName is empty");
                        return;
                    }
                    if (c != null) {
                        LogUtil.a("LogAppEventUtil", "addAppEventLog final deviceInfo: DeviceName: ", str5, ",SN: ", blt.a(c.getDeviceIdentify()));
                    } else {
                        ReleaseLogUtil.a("Dfx_LogAppEventUtil", "addAppEventLog is not connected device");
                    }
                    bls.c(BaseApplication.e(), c, str5, str3, str4, System.lineSeparator() + str);
                    LinkedList<File> j = bls.j(bky.d(str3));
                    if (j == null || j.size() == 0) {
                        ReleaseLogUtil.a("Dfx_LogAppEventUtil", "appEventFiles size is 0");
                        return;
                    }
                    try {
                        if (TextUtils.equals(str4, "_WearableBeta_gpu_update.log")) {
                            File c2 = bls.c((List<Map.Entry<String, Long>>) bls.o(str5));
                            if (c2 != null) {
                                bls.d(str, c2);
                                return;
                            } else {
                                ReleaseLogUtil.a("Dfx_LogAppEventUtil", "ota files size is 0 or file is null");
                                return;
                            }
                        }
                        for (File file : j) {
                            if (file.getName().contains(bls.e(c)) && file.getName().contains("_WearableBeta_app_bigdata.log")) {
                                LogUtil.c("LogAppEventUtil", "appEventFiles: ", file.getName());
                                bls.d(str, file);
                            } else {
                                ReleaseLogUtil.a("Dfx_LogAppEventUtil", "addAppEventLog not contains WEARABLE_BETA_APP_EVENT_LOG");
                            }
                        }
                    } catch (OutOfMemoryError unused) {
                        ReleaseLogUtil.a("Dfx_LogAppEventUtil", "addAppEventLog OutOfMemoryError");
                    }
                }
            });
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static UniteDevice c() {
        ArrayList<UniteDevice> arrayList = new ArrayList(bgl.c().getDeviceList().values());
        if (arrayList.isEmpty()) {
            return null;
        }
        for (UniteDevice uniteDevice : arrayList) {
            com.huawei.devicesdk.entity.DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
            if (deviceInfo.isUsing() && deviceInfo.getDeviceConnectState() == 2 && deviceInfo.getDeviceType() > 0) {
                return uniteDevice;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0115, code lost:
    
        if (r5 == 1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0117, code lost:
    
        health.compact.a.LogUtil.c("LogAppEventUtil", "createAppEventLogFile other type fileNameEnd = " + r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x012f, code lost:
    
        e(r8, r10, r11);
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010a A[Catch: all -> 0x0138, TryCatch #0 {, blocks: (B:5:0x0004, B:7:0x0012, B:11:0x001f, B:18:0x0048, B:19:0x00f7, B:25:0x0117, B:28:0x012f, B:29:0x0133, B:30:0x0100, B:33:0x010a, B:36:0x0061, B:38:0x006c, B:40:0x0081, B:43:0x008e, B:45:0x009d, B:46:0x00b3, B:53:0x00d8, B:54:0x00f0, B:55:0x00f4, B:56:0x00bf, B:59:0x00c9, B:62:0x0066, B:63:0x002f, B:66:0x0039), top: B:4:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006c A[Catch: all -> 0x0138, TryCatch #0 {, blocks: (B:5:0x0004, B:7:0x0012, B:11:0x001f, B:18:0x0048, B:19:0x00f7, B:25:0x0117, B:28:0x012f, B:29:0x0133, B:30:0x0100, B:33:0x010a, B:36:0x0061, B:38:0x006c, B:40:0x0081, B:43:0x008e, B:45:0x009d, B:46:0x00b3, B:53:0x00d8, B:54:0x00f0, B:55:0x00f4, B:56:0x00bf, B:59:0x00c9, B:62:0x0066, B:63:0x002f, B:66:0x0039), top: B:4:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f4 A[Catch: all -> 0x0138, TryCatch #0 {, blocks: (B:5:0x0004, B:7:0x0012, B:11:0x001f, B:18:0x0048, B:19:0x00f7, B:25:0x0117, B:28:0x012f, B:29:0x0133, B:30:0x0100, B:33:0x010a, B:36:0x0061, B:38:0x006c, B:40:0x0081, B:43:0x008e, B:45:0x009d, B:46:0x00b3, B:53:0x00d8, B:54:0x00f0, B:55:0x00f4, B:56:0x00bf, B:59:0x00c9, B:62:0x0066, B:63:0x002f, B:66:0x0039), top: B:4:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0066 A[Catch: all -> 0x0138, TryCatch #0 {, blocks: (B:5:0x0004, B:7:0x0012, B:11:0x001f, B:18:0x0048, B:19:0x00f7, B:25:0x0117, B:28:0x012f, B:29:0x0133, B:30:0x0100, B:33:0x010a, B:36:0x0061, B:38:0x006c, B:40:0x0081, B:43:0x008e, B:45:0x009d, B:46:0x00b3, B:53:0x00d8, B:54:0x00f0, B:55:0x00f4, B:56:0x00bf, B:59:0x00c9, B:62:0x0066, B:63:0x002f, B:66:0x0039), top: B:4:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(android.content.Context r7, com.huawei.health.devicemgr.business.entity.DeviceInfo r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bls.c(android.content.Context, com.huawei.health.devicemgr.business.entity.DeviceInfo, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r11v11, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v12, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v19, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r11v20, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v9 */
    private static String d(File file) {
        Throwable th;
        InputStreamReader inputStreamReader;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        LogUtil.c("LogAppEventUtil", "enter getFileReadLineName");
        if (file == 0) {
            LogUtil.a("LogAppEventUtil", "getFileReadLineName is null");
            return "";
        }
        BufferedReader bufferedReader2 = 0;
        bufferedReader2 = 0;
        bufferedReader2 = 0;
        bufferedReader2 = 0;
        bufferedReader2 = 0;
        r5 = null;
        bufferedReader2 = 0;
        bufferedReader2 = 0;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                fileInputStream = new FileInputStream((File) file);
                try {
                    file = new InputStreamReader(fileInputStream, "UTF-8");
                    try {
                        bufferedReader = new BufferedReader(file);
                    } catch (IOException unused) {
                    } catch (JSONException unused2) {
                    }
                    try {
                        bufferedReader2 = bufferedReader.readLine();
                    } catch (IOException unused3) {
                        bufferedReader2 = bufferedReader;
                        LogUtil.a("LogAppEventUtil", "readLine IOException");
                        if (bufferedReader2 != 0) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused4) {
                                file = new Object[]{"finally IOException"};
                                LogUtil.a("LogAppEventUtil", file);
                            }
                        }
                        if (file != 0) {
                            file.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return "";
                    } catch (JSONException unused5) {
                        bufferedReader2 = bufferedReader;
                        LogUtil.c("LogAppEventUtil", "getFileReadLineName JSONException");
                        if (bufferedReader2 != 0) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused6) {
                                file = new Object[]{"finally IOException"};
                                LogUtil.a("LogAppEventUtil", file);
                            }
                        }
                        if (file != 0) {
                            file.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return "";
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader3 = bufferedReader;
                        inputStreamReader = file;
                        if (bufferedReader3 != null) {
                            try {
                                bufferedReader3.close();
                            } catch (IOException unused7) {
                                LogUtil.a("LogAppEventUtil", "finally IOException");
                                throw th;
                            }
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException unused8) {
                    file = 0;
                } catch (JSONException unused9) {
                    file = 0;
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = null;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader3 = bufferedReader2;
                inputStreamReader = file;
            }
        } catch (IOException unused10) {
            file = 0;
            fileInputStream = null;
        } catch (JSONException unused11) {
            file = 0;
            fileInputStream = null;
        } catch (Throwable th5) {
            th = th5;
            inputStreamReader = null;
            fileInputStream = null;
        }
        if (bufferedReader2 == 0) {
            try {
                bufferedReader.close();
                file.close();
                fileInputStream.close();
            } catch (IOException unused12) {
                file = new Object[]{"finally IOException"};
                LogUtil.a("LogAppEventUtil", file);
            }
            return "";
        }
        String string = new JSONObject((String) bufferedReader2).getString("wear_device_name");
        LogUtil.c("LogAppEventUtil", "getFileReadLineName deviceName: ", string);
        try {
            bufferedReader.close();
            file.close();
            fileInputStream.close();
        } catch (IOException unused13) {
            LogUtil.a("LogAppEventUtil", "finally IOException");
        }
        return string;
    }

    private static void d(String str, DeviceInfo deviceInfo) {
        String str2;
        LogUtil.c("LogAppEventUtil", "enter writeFileHeadAndRename");
        if (deviceInfo == null) {
            LogUtil.a("LogAppEventUtil", "writeFileHeadAndRename currentDeviceInfo is null");
            return;
        }
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf != -1) {
            str2 = str.substring(lastIndexOf + 1, str.length());
            LogUtil.c("LogAppEventUtil", "writeFileHeadAndRename fileNamePath: ", str2);
            if (!str2.contains("_&_&")) {
                LogUtil.c("LogAppEventUtil", "writeFileHeadAndRename isWriteFileHead is false");
                return;
            }
        } else {
            str2 = "";
        }
        int productType = deviceInfo.getProductType();
        String b2 = b(productType, c(productType, deviceInfo.getDeviceModel(), deviceInfo.getDeviceName()), deviceInfo.getDeviceModel());
        String softVersion = deviceInfo.getSoftVersion();
        String e = e(deviceInfo);
        b(str, b2, softVersion, e);
        String[] split = str2.split("_");
        if (split.length > 3) {
            split[0] = b2;
            split[1] = softVersion;
            split[2] = e;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (int i = 0; i < split.length; i++) {
            if (i != split.length - 1) {
                stringBuffer.append(split[i] + "_");
            } else {
                stringBuffer.append(split[i]);
            }
        }
        LogUtil.c("LogAppEventUtil", "oldFileName: ", str2, ", newFileName: ", stringBuffer.toString());
        d(str2, stringBuffer.toString());
    }

    private static void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("LogAppEventUtil", "fileReName oldPath or newPath is empty");
            return;
        }
        StringBuilder sb = new StringBuilder();
        String str3 = b;
        sb.append(str3);
        sb.append(str);
        File file = new File(sb.toString());
        if (!file.exists()) {
            LogUtil.a("LogAppEventUtil", "fileReName file is not exists");
            return;
        }
        if (file.renameTo(new File(str3 + str2))) {
            LogUtil.c("LogAppEventUtil", "fileReName is success");
        } else {
            LogUtil.c("LogAppEventUtil", "fileReName is failed");
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
    /* JADX WARN: Failed to calculate best type for var: r8v0 ??
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
    /* JADX WARN: Failed to calculate best type for var: r8v0 ??
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
    /* JADX WARN: Failed to calculate best type for var: r8v1 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v1 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v10 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v11 ??
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
    /* JADX WARN: Failed to calculate best type for var: r8v12 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v13 ??
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
    /* JADX WARN: Failed to calculate best type for var: r8v2 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v2 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v3 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v3 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v4 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v4 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v6 ??
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
    /* JADX WARN: Failed to calculate best type for var: r8v7 ??
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
    /* JADX WARN: Failed to calculate best type for var: r8v8 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r8v9 ??
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
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x00f7: MOVE (r6 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:54:0x00f6 */
    private static void b(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bls.b(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private static boolean b(DeviceInfo deviceInfo, String str) {
        return (deviceInfo == null || deviceInfo.getDeviceName() == null || !deviceInfo.getDeviceName().equals(str)) ? false : true;
    }

    private static void c(DeviceInfo deviceInfo, String str, String str2, String str3) {
        String str4;
        String str5;
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        synchronized (bls.class) {
            if (b(deviceInfo, str)) {
                int productType = deviceInfo.getProductType();
                str5 = b(productType, c(productType, deviceInfo.getDeviceModel(), deviceInfo.getDeviceName()), deviceInfo.getDeviceModel());
                str4 = deviceInfo.getSoftVersion();
            } else {
                str4 = "&";
                str5 = str;
            }
            String e = e(deviceInfo);
            if (!b(deviceInfo, str) || TextUtils.isEmpty(e)) {
                e = "&";
            }
            String str6 = str2 + str5 + "_" + str4 + "_" + e + "_" + d() + str3;
            LogUtil.c("LogAppEventUtil", "appEventPath is: ", str6);
            String d = bky.d(str6);
            FileOutputStream fileOutputStream2 = null;
            r4 = null;
            fileOutputStream2 = null;
            fileOutputStream2 = null;
            fileOutputStream2 = null;
            BufferedWriter bufferedWriter2 = null;
            try {
                try {
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException unused) {
                outputStreamWriter = null;
                bufferedWriter = null;
            } catch (IllegalArgumentException e2) {
                e = e2;
                outputStreamWriter = null;
                bufferedWriter = null;
            } catch (OutOfMemoryError unused2) {
                outputStreamWriter = null;
                bufferedWriter = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                outputStreamWriter = null;
            }
            if (TextUtils.isEmpty(d)) {
                LogUtil.a("LogAppEventUtil", "onCreateFile filePath is empty");
                b((BufferedWriter) null, (OutputStreamWriter) null, (FileOutputStream) null);
                return;
            }
            File file = FileUtils.getFile(d);
            if (file == null) {
                LogUtil.a("LogAppEventUtil", "onCreateFile pathFile is null");
                b((BufferedWriter) null, (OutputStreamWriter) null, (FileOutputStream) null);
                return;
            }
            fileOutputStream = FileUtils.openOutputStream(file, true);
            try {
                outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            } catch (IOException unused3) {
                outputStreamWriter = null;
                bufferedWriter = null;
            } catch (IllegalArgumentException e3) {
                e = e3;
                outputStreamWriter = null;
                bufferedWriter = null;
            } catch (OutOfMemoryError unused4) {
                outputStreamWriter = null;
                bufferedWriter = null;
            } catch (Throwable th3) {
                th = th3;
                outputStreamWriter = null;
                b(bufferedWriter2, outputStreamWriter, fileOutputStream);
                throw th;
            }
            try {
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                try {
                    File file2 = new File(d);
                    if (!file2.exists() && file2.createNewFile()) {
                        ReleaseLogUtil.a("Dfx_LogAppEventUtil", "onCreateFile success");
                    }
                    bufferedWriter.write("");
                    String deviceName = b(deviceInfo, str) ? deviceInfo.getDeviceName() : str;
                    if (!b(deviceInfo, str)) {
                        str5 = "";
                        e = "";
                        str4 = "";
                    }
                    String a2 = a(BaseApplication.e(), str5, deviceName, e, str4);
                    ReleaseLogUtil.a("Dfx_LogAppEventUtil", "onCreateFile write");
                    bufferedWriter.write(a2);
                    b(bufferedWriter, outputStreamWriter, fileOutputStream);
                } catch (IOException unused5) {
                    fileOutputStream2 = fileOutputStream;
                    ReleaseLogUtil.c("Dfx_LogAppEventUtil", "onCreateFile ioException");
                    b(bufferedWriter, outputStreamWriter, fileOutputStream2);
                } catch (IllegalArgumentException e4) {
                    e = e4;
                    fileOutputStream2 = fileOutputStream;
                    ReleaseLogUtil.c("Dfx_LogAppEventUtil", "onCreateFile readFileException: ", ExceptionUtils.d(e));
                    b(bufferedWriter, outputStreamWriter, fileOutputStream2);
                } catch (OutOfMemoryError unused6) {
                    fileOutputStream2 = fileOutputStream;
                    ReleaseLogUtil.c("Dfx_LogAppEventUtil", "onCreateFile OutOfMemoryError");
                    b(bufferedWriter, outputStreamWriter, fileOutputStream2);
                } catch (Throwable th4) {
                    th = th4;
                    bufferedWriter2 = bufferedWriter;
                    b(bufferedWriter2, outputStreamWriter, fileOutputStream);
                    throw th;
                }
            } catch (IOException unused7) {
                bufferedWriter = null;
            } catch (IllegalArgumentException e5) {
                e = e5;
                bufferedWriter = null;
            } catch (OutOfMemoryError unused8) {
                bufferedWriter = null;
            } catch (Throwable th5) {
                th = th5;
                bufferedWriter = null;
                outputStreamWriter = outputStreamWriter;
                bufferedWriter2 = bufferedWriter;
                b(bufferedWriter2, outputStreamWriter, fileOutputStream);
                throw th;
            }
        }
    }

    private static String d() {
        try {
            String f = f(HwDrmConstant.TIME_FORMAT);
            LogUtil.c("LogAppEventUtil", "getCurrentTimeMinutes: currentTime is ", f);
            return f;
        } catch (Exception unused) {
            LogUtil.e("LogAppEventUtil", "getCurrentTimeMinutes exception");
            return "";
        }
    }

    private static String f(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LinkedList<File> j(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "listLinkedAppEventFiles path is null");
            return null;
        }
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "listLinkedAppEventFiles directory.listFiles is null");
            return null;
        }
        LinkedList<File> linkedList = new LinkedList<>();
        LogUtil.c("LogAppEventUtil", "listLinkedAppEventFiles files: ", Integer.valueOf(listFiles.length));
        for (File file : listFiles) {
            String name = file.getName();
            if ((!file.isDirectory() && name.contains("_WearableBeta_app_bigdata.log")) || ((!file.isDirectory() && name.contains("_WearableBeta_gpu_update.log")) || file.isDirectory())) {
                linkedList.add(file);
            } else {
                ReleaseLogUtil.a("Dfx_LogAppEventUtil", "listLinkedAppEventFiles else");
            }
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.FileOutputStream] */
    public static void d(String str, File file) {
        ?? r4;
        OutputStreamWriter outputStreamWriter;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        BufferedWriter bufferedWriter;
        synchronized (bls.class) {
            String str2 = "LogAppEventUtil";
            LogUtil.c("LogAppEventUtil", "writeAppEventDate enter eventData: ", str, ", fileName = " + file.getName());
            BufferedWriter bufferedWriter2 = null;
            try {
                try {
                    String d = bky.d(b + file.getName());
                    fileOutputStream = new FileOutputStream(d, true);
                    try {
                        outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                        try {
                            bufferedWriter = new BufferedWriter(outputStreamWriter);
                        } catch (IOException unused) {
                        } catch (OutOfMemoryError unused2) {
                        }
                        try {
                            File file2 = new File(d);
                            if (!file2.exists() || file2.length() <= 0) {
                                ReleaseLogUtil.c("Dfx_LogAppEventUtil", "writeAppEventDate newFile is not exists");
                            } else {
                                bufferedWriter.write(System.lineSeparator() + str);
                            }
                            b(bufferedWriter, outputStreamWriter, fileOutputStream);
                        } catch (IOException unused3) {
                            bufferedWriter2 = bufferedWriter;
                            ReleaseLogUtil.c("Dfx_LogAppEventUtil", "writeAppEventDate ioException");
                            fileOutputStream2 = fileOutputStream;
                            b(bufferedWriter2, outputStreamWriter, fileOutputStream2);
                        } catch (OutOfMemoryError unused4) {
                            bufferedWriter2 = bufferedWriter;
                            ReleaseLogUtil.c("Dfx_LogAppEventUtil", "writeAppEventDate OutOfMemoryError");
                            fileOutputStream2 = fileOutputStream;
                            b(bufferedWriter2, outputStreamWriter, fileOutputStream2);
                        } catch (Throwable th) {
                            th = th;
                            bufferedWriter2 = bufferedWriter;
                            r4 = fileOutputStream;
                            b(bufferedWriter2, outputStreamWriter, (FileOutputStream) r4);
                            throw th;
                        }
                    } catch (IOException unused5) {
                        outputStreamWriter = null;
                    } catch (OutOfMemoryError unused6) {
                        outputStreamWriter = null;
                    } catch (Throwable th2) {
                        th = th2;
                        outputStreamWriter = null;
                        r4 = fileOutputStream;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStreamWriter = 2;
                    bufferedWriter2 = null;
                    r4 = str2;
                }
            } catch (IOException unused7) {
                fileOutputStream = null;
                outputStreamWriter = null;
            } catch (OutOfMemoryError unused8) {
                fileOutputStream = null;
                outputStreamWriter = null;
            } catch (Throwable th4) {
                th = th4;
                r4 = 0;
                outputStreamWriter = null;
            }
        }
    }

    public static void a() {
        LinkedList<File> j = j(bky.d(b));
        if (j != null) {
            try {
                if (j.size() > 0) {
                    ReleaseLogUtil.b("Dfx_LogAppEventUtil", "deleteAppEventLog appEventFiles: ", Integer.valueOf(j.size()));
                    Iterator<File> it = j.iterator();
                    while (it.hasNext()) {
                        b(it.next());
                    }
                }
            } catch (OutOfMemoryError unused) {
                ReleaseLogUtil.c("Dfx_LogAppEventUtil", "deleteAppEventLog OutOfMemoryError");
            }
        }
    }

    private static void b(File file) {
        String path = file.getPath();
        LogUtil.c("LogAppEventUtil", "filePath: ", path);
        if (TextUtils.isEmpty(path)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "filePath is null.");
            return;
        }
        File file2 = new File(path);
        try {
            LogUtil.c("LogAppEventUtil", "file: ", file2.getCanonicalPath());
        } catch (IOException unused) {
            LogUtil.c("LogAppEventUtil", "deleteFileDeal IOException");
        }
        if (file2.exists() && !file2.isDirectory() && file2.getName().contains("_WearableBeta_app_bigdata.log")) {
            ReleaseLogUtil.b("Dfx_LogAppEventUtil", "file delete: ", Boolean.valueOf(file2.delete()));
        } else {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "file not exists or not app event");
        }
    }

    private static String a(Context context, String str, String str2, String str3, String str4) {
        String g = g("unknown");
        String a2 = a(context, "com.huawei.health");
        if (TextUtils.isEmpty(a2)) {
            a2 = "";
        }
        String b2 = b(System.currentTimeMillis());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("file_time", b2);
            jSONObject.put("wear_device_type", str);
            jSONObject.put("wear_device_name", str2);
            jSONObject.put("wear_device_version", str4);
            jSONObject.put("wear_device_id", str3);
            jSONObject.put("health_version", a2);
            jSONObject.put("phone_os_version", Build.VERSION.RELEASE);
            jSONObject.put("phone_brand", Build.BRAND);
            jSONObject.put("phone_device", Build.DISPLAY);
            jSONObject.put("phone_model", Build.MODEL);
            jSONObject.put("phone_ui_version", g);
            jSONObject.put("phone_sn", blo.d(b()));
            return jSONObject.toString();
        } catch (JSONException unused) {
            ReleaseLogUtil.c("Dfx_LogAppEventUtil", "getAppVersion jsonException");
            return "";
        }
    }

    private static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date(j));
    }

    private static String b() {
        ReleaseLogUtil.b("Dfx_LogAppEventUtil", "getSnInfo enter");
        String str = Build.SERIAL;
        if (Build.VERSION.SDK_INT >= 29) {
            String c = bkx.c();
            if (!TextUtils.isEmpty(c)) {
                str = c.toUpperCase(Locale.ENGLISH);
            } else {
                ReleaseLogUtil.c("Dfx_LogAppEventUtil", "androidId is isEmpty");
            }
        } else if (bma.a(BaseApplication.e(), new String[]{"android.permission.READ_PHONE_STATE"})) {
            str = bkx.a();
        }
        LogUtil.c("LogAppEventUtil", "sn: ", blt.a(str));
        return str;
    }

    private static String g(String str) {
        String ai = CommonUtil.ai();
        return (TextUtils.isEmpty(ai) || "default".equals(ai)) ? str : ai;
    }

    private static String a(Context context, String str) {
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
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e("LogAppEventUtil", "PackageManager.NameNotFoundException");
            return null;
        }
    }

    private static boolean h(String str) {
        ReleaseLogUtil.b("Dfx_LogAppEventUtil", "isCreateAppEventLog enter");
        String d = bky.d(b);
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "isCreateAppEventLog pathName is empty");
            return false;
        }
        File file = new File(bky.d(d));
        if (!file.exists()) {
            LogUtil.c("LogAppEventUtil", "isCreateAppEventLog mkdirs result: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.a("LogAppEventUtil", "isCreateAppEventLog files size is 0");
            return true;
        }
        TreeMap treeMap = new TreeMap();
        treeMap.clear();
        LogUtil.c("LogAppEventUtil", "isCreateAppEventLog files.length is: ", Integer.valueOf(listFiles.length));
        for (File file2 : listFiles) {
            if (file2.getName().contains("_WearableBeta_app_bigdata.log")) {
                LogUtil.c("LogAppEventUtil", "isCreateAppEventLog file.getName(): ", file2.getName());
                treeMap.put(file2.getName(), Long.valueOf(i(file2.getName())));
            }
        }
        ArrayList arrayList = new ArrayList(treeMap.entrySet());
        treeMap.clear();
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Long>>() { // from class: bls.5
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
                return entry.getValue().compareTo(entry2.getValue());
            }
        });
        if (arrayList.size() == 0) {
            LogUtil.a("LogAppEventUtil", "isCreateAppEventLog bigDataFileTempMap size is 0");
            return true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext() && arrayList.size() > 2) {
            String str2 = (String) ((Map.Entry) it.next()).getKey();
            LogUtil.c("LogAppEventUtil", "isCreateAppEventLog isDeleteSuccess: ", Boolean.valueOf(new File(b + str2).delete()), ",fileName:", str2);
            it.remove();
        }
        boolean a2 = arrayList.size() <= 2 ? a(arrayList, str) : true;
        arrayList.clear();
        return a2;
    }

    private static boolean e(String str, String str2) {
        ReleaseLogUtil.b("Dfx_LogAppEventUtil", "isCreateOtaEventLog enter");
        List<Map.Entry<String, Long>> o = o(str);
        boolean z = true;
        if (o == null || o.size() == 0) {
            LogUtil.a("LogAppEventUtil", "isCreateOtaEventLog bigDataFileTempMap size is 0");
            return true;
        }
        Iterator<Map.Entry<String, Long>> it = o.iterator();
        while (it.hasNext() && o.size() > 2) {
            String key = it.next().getKey();
            LogUtil.c("LogAppEventUtil", "isCreateOtaEventLog isDeleteSuccess: ", Boolean.valueOf(new File(b + key).delete()), ",fileName:", key);
            it.remove();
        }
        if (o.size() == 1) {
            if (new File(b + it.next().getKey()).length() + str2.length() <= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) {
                z = false;
            }
        } else {
            z = e(o, str2);
        }
        o.clear();
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Map.Entry<String, Long>> o(String str) {
        String d = bky.d(b);
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "sortOTAFilesByTimestamp pathName is empty");
            return null;
        }
        File file = new File(bky.d(d));
        if (!file.exists()) {
            LogUtil.c("LogAppEventUtil", "sortOTAFilesByTimestamp mkdirs result: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.a("LogAppEventUtil", "sortOTAFilesByTimestamp files size is 0");
            return null;
        }
        TreeMap treeMap = new TreeMap();
        treeMap.clear();
        for (File file2 : listFiles) {
            if (file2.getName().contains("_WearableBeta_gpu_update.log") && d(file2).contains(str)) {
                LogUtil.c("LogAppEventUtil", "sortOTAFilesByTimestamp file.getName(): ", file2.getName());
                treeMap.put(file2.getName(), Long.valueOf(i(file2.getName())));
            }
        }
        ArrayList arrayList = new ArrayList(treeMap.entrySet());
        LogUtil.c("LogAppEventUtil", "sortOTAFilesByTimestamp files.length is: ", Integer.valueOf(arrayList.size()));
        treeMap.clear();
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Long>>() { // from class: bls.2
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
                return entry.getValue().compareTo(entry2.getValue());
            }
        });
        return arrayList;
    }

    private static void a(String str, String str2) {
        String d = bky.d(str);
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "deleteFileDeal pathName is empty");
            return;
        }
        File file = new File(bky.d(d));
        if (!file.exists()) {
            LogUtil.c("LogAppEventUtil", "deleteFileDeal mkdirs result: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.a("LogAppEventUtil", "deleteFileDeal files size is 0");
            return;
        }
        TreeMap treeMap = new TreeMap();
        treeMap.clear();
        LogUtil.c("LogAppEventUtil", "deleteFileDeal files.length is: ", Integer.valueOf(listFiles.length));
        for (File file2 : listFiles) {
            if (file2.getName().contains(str2)) {
                LogUtil.c("LogAppEventUtil", "deleteFileDeal file.getName(): ", file2.getName());
                treeMap.put(file2.getName(), Long.valueOf(i(file2.getName())));
            }
        }
        ArrayList arrayList = new ArrayList(treeMap.entrySet());
        treeMap.clear();
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Long>>() { // from class: bls.4
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
                return entry.getValue().compareTo(entry2.getValue());
            }
        });
        if (arrayList.size() <= 2) {
            LogUtil.a("LogAppEventUtil", "deleteFileDeal bigDataFileTempMap size is then or 2");
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext() && arrayList.size() > 2) {
            String str3 = (String) ((Map.Entry) it.next()).getKey();
            LogUtil.c("LogAppEventUtil", "deleteFileDeal isDeleteSuccess: ", Boolean.valueOf(new File(str + str3).delete()), ",fileName:", str3);
            it.remove();
        }
        LogUtil.c("LogAppEventUtil", "deleteFileDeal listSize: ", Integer.valueOf(arrayList.size()));
        arrayList.clear();
    }

    private static void c(String str, String str2, String str3) {
        c(str, str2, str3, 2, 4);
    }

    private static void c(String str, String str2, String str3, int i, int i2) {
        String d = bky.d(str2);
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "deleteFileFilterDevices pathName is empty");
            return;
        }
        File file = new File(bky.d(d));
        if (!file.exists()) {
            LogUtil.c("LogAppEventUtil", "deleteFileFilterDevices mkdirs result: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.a("LogAppEventUtil", "deleteFileFilterDevices files size is 0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (File file2 : listFiles) {
            if (file2.getName().contains(str3)) {
                LogUtil.c("LogAppEventUtil", "deleteFileFilterDevices file.getName(): ", file2.getName());
                if (d(file2).contains(str)) {
                    arrayList.add(file2.getName());
                } else {
                    arrayList2.add(file2.getName());
                }
            }
        }
        a(arrayList);
        a(arrayList2);
        if (arrayList.size() > i) {
            b(arrayList, str2, i);
        }
        d(str2, i2 - arrayList.size(), arrayList2);
    }

    private static void b(List<String> list, String str, int i) {
        LogUtil.c("LogAppEventUtil", "filterOtherDevicesFiles size: ", Integer.valueOf(list.size()));
        if (list.size() - i > 0) {
            c(list, str, i);
        }
    }

    private static void a(List<String> list) {
        list.sort(Comparator.comparingLong(new ToLongFunction() { // from class: blr
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                long i;
                i = bls.i((String) obj);
                return i;
            }
        }));
    }

    private static void d(String str, int i, List<String> list) {
        if (list.size() <= i) {
            LogUtil.a("LogAppEventUtil", "filterOtherDevicesFiles size is less then or ", Integer.valueOf(i));
            return;
        }
        LogUtil.c("LogAppEventUtil", "filterOtherDevicesFiles size: ", Integer.valueOf(list.size()));
        if (list.size() - i > 0) {
            c(list, str, i);
        }
    }

    private static void c(List<String> list, String str, int i) {
        for (int i2 = 0; i2 < list.size() - i; i2++) {
            String str2 = str + list.get(i2);
            LogUtil.c("LogAppEventUtil", "deleteElementLimited isDeleteSuccess: ", Boolean.valueOf(new File(str2).delete()), ", fileName: ", str2);
        }
        list.subList(0, list.size() - i).clear();
        LogUtil.c("LogAppEventUtil", "deleteElementLimited final size: ", Integer.valueOf(list.size()));
    }

    private static boolean a(List<Map.Entry<String, Long>> list, String str) {
        LogUtil.c("LogAppEventUtil", "isCreateAppEventLog list size: ", Integer.valueOf(list.size()));
        Iterator<Map.Entry<String, Long>> it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String key = it.next().getKey();
            File file = new File(b + key);
            if (key.contains("_WearableBeta_app_bigdata.log") && d(file).contains(str)) {
                LogUtil.c("LogAppEventUtil", "isCreateAppEventLog contains current deviceName");
                z = false;
            } else {
                LogUtil.c("LogAppEventUtil", "has not this device appbigdata");
            }
        }
        return z;
    }

    private static boolean e(List<Map.Entry<String, Long>> list, String str) {
        LogUtil.c("LogAppEventUtil", "isCreateOTAEventLogPart list size: ", Integer.valueOf(list.size()));
        File c = c(list);
        return c == null || c.length() + ((long) str.length()) > PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File c(List<Map.Entry<String, Long>> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        File file = new File(b + list.get(list.size() - 1).getKey());
        LogUtil.c("LogAppEventUtil", "getLatestOTAFile list size: ", Integer.valueOf(list.size()), ", fileName = " + file.getName());
        return file;
    }

    private static void a(DeviceInfo deviceInfo) {
        String d = bky.d(b);
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "dealEventLogFile pathName is empty");
            return;
        }
        File file = new File(bky.d(d));
        if (!file.exists()) {
            LogUtil.c("LogAppEventUtil", "dealEventLogFile mkdirs result: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "dealEventLogFile files is empty");
            return;
        }
        for (File file2 : listFiles) {
            if (file2.getName().contains("_WearableBeta_app_bigdata.log")) {
                if (deviceInfo != null && deviceInfo.getDeviceName() != null && d(file2).contains(deviceInfo.getDeviceName()) && file2.length() <= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) {
                    ReleaseLogUtil.b("Dfx_LogAppEventUtil", "dealEventLogFile has this device id appEvent log");
                    d(b + file2.getName(), deviceInfo);
                }
                if (file2.length() > PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) {
                    ReleaseLogUtil.b("Dfx_LogAppEventUtil", "dealEventLogFile isDeleteSuccess: ", Boolean.valueOf(file2.delete()));
                } else {
                    ReleaseLogUtil.b("Dfx_LogAppEventUtil", "dealEventLogFile file size less 2M");
                }
            }
        }
    }

    private static void e(DeviceInfo deviceInfo, String str, String str2) {
        ArrayList<File> c = c(deviceInfo, str, str2);
        if (c == null || c.size() == 0) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "dealEventLogFile files is empty");
            return;
        }
        Iterator<File> it = c.iterator();
        while (it.hasNext()) {
            File next = it.next();
            ReleaseLogUtil.b("Dfx_LogAppEventUtil", "dealEventLogFile has this device id appEvent log");
            d(str + next.getName(), deviceInfo);
        }
        c(deviceInfo.getDeviceName(), str, str2);
    }

    private static ArrayList<File> c(DeviceInfo deviceInfo, String str, String str2) {
        String d = bky.d(str);
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "dealEventLogFile pathName is empty");
            return null;
        }
        File file = new File(bky.d(d));
        if (!file.exists()) {
            LogUtil.c("LogAppEventUtil", "dealEventLogFile mkdirs result: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            ReleaseLogUtil.a("Dfx_LogAppEventUtil", "dealEventLogFile files is empty");
            return null;
        }
        ArrayList<File> arrayList = new ArrayList<>();
        for (File file2 : listFiles) {
            if (deviceInfo != null && deviceInfo.getDeviceName() != null && d(file2).contains(deviceInfo.getDeviceName()) && file2.getName().contains(str2)) {
                arrayList.add(file2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("LogAppEventUtil", "getShaDeviceId currentDeviceInfo is null");
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
            return bmr.a(deviceIdentify);
        }
        return l(securityDeviceId);
    }

    private static String l(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("LogAppEventUtil", "setDeviceMac deviceMac is null");
            return null;
        }
        if (Utils.o()) {
            String a2 = a(str);
            return a2.length() >= 24 ? a2.replace(Marker.ANY_NON_NULL_MARKER, SampleEvent.SEPARATOR).replace("/", SampleEvent.SEPARATOR).replace("=", SampleEvent.SEPARATOR).substring(0, 24) : a2;
        }
        if (str.contains(":") || str.length() <= 12) {
            return str.replace(":", "");
        }
        String a3 = a(str);
        return a3.length() >= 24 ? a3.replace(Marker.ANY_NON_NULL_MARKER, SampleEvent.SEPARATOR).replace("/", SampleEvent.SEPARATOR).replace("=", SampleEvent.SEPARATOR).substring(0, 24) : a3;
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("LogAppEventUtil", "encryptMacSha256 param is empty");
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(messageDigest.digest(), 2);
        } catch (NoSuchAlgorithmException e) {
            LogUtil.e("LogAppEventUtil", "encryptMacSha256 e=", e.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long i(String str) {
        LogUtil.c("LogAppEventUtil", "getAppBigDataTime fileName: ", str);
        String[] split = str.split("_");
        if (split == null || split.length <= 3) {
            return 0L;
        }
        return k(split[3]);
    }

    private static long k(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            LogUtil.e("LogAppEventUtil", "parse fail:", str);
            return 0L;
        }
    }

    private static String c(int i, String str, String str2) {
        if (c(i)) {
            return a(i);
        }
        if (i == 10) {
            return "Leo";
        }
        if (i == 23 || i == 24) {
            return "AW70";
        }
        if (i == 36 || i == 37) {
            return "AW70-B39";
        }
        if (i == 44) {
            return "Andes_B19";
        }
        if (i == 45) {
            return "Andes_B29";
        }
        switch (i) {
            case 18:
                return "Crius";
            case 19:
                return "Terra";
            case 20:
                return "Talos";
            case 21:
                return "Fortuna";
            default:
                return d(i, str, str2);
        }
    }

    private static String d(int i, String str, String str2) {
        return c(str2, str);
    }

    private static String c(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        if (TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            return "HUAWEI WEAR";
        }
        for (int i = 0; i < str.length(); i++) {
            if (!"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".contains(str.charAt(i) + "") || str.contains("_")) {
                return "HUAWEI WEAR";
            }
        }
        return str;
    }

    private static String b(int i, String str, String str2) {
        return (i < 19 || i == 20 || TextUtils.isEmpty(str2)) ? str : str2;
    }

    public static boolean c(Context context, String str) {
        ActivityManager activityManager;
        if (context == null || str == null || !(context.getSystemService("activity") instanceof ActivityManager) || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
        ComponentName componentName = !bkz.e(runningTasks) ? runningTasks.get(0).topActivity : null;
        if (componentName == null) {
            return false;
        }
        return componentName.getClassName().contains(str);
    }

    private static void d(BufferedWriter bufferedWriter, BufferedReader bufferedReader, FileReader fileReader) {
        if (bufferedWriter != null) {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException unused) {
                LogUtil.e("LogAppEventUtil", "closeStream ioException");
                return;
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (fileReader != null) {
            fileReader.close();
        }
    }

    private static void b(BufferedWriter bufferedWriter, OutputStreamWriter outputStreamWriter, FileOutputStream fileOutputStream) {
        if (bufferedWriter != null) {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException unused) {
                LogUtil.e("LogAppEventUtil", "writeFileSdcard ioException");
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

    private static String a(int i) {
        if (i == 1) {
            return "B2";
        }
        if (i == 2) {
            return "K1";
        }
        if (i == 3) {
            return "W1";
        }
        if (i == 5) {
            return "B0";
        }
        if (i == 7) {
            return "Gemini";
        }
        if (i == 8) {
            return "Metis";
        }
        switch (i) {
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
