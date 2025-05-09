package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class jwa {
    private static ThreadPoolManager c = ThreadPoolManager.e(3, 3, FileUtil.TAG);
    private static final String e = LogConfig.j();

    public static boolean d(String str) {
        LogUtil.c(FileUtil.TAG, "isFileExists filePath: ", str);
        if (str == null) {
            LogUtil.h(FileUtil.TAG, "isFileExists filePath is null");
            return false;
        }
        if (new File(str).exists()) {
            LogUtil.c(FileUtil.TAG, "isFileExists return true");
            return true;
        }
        LogUtil.c(FileUtil.TAG, "isFileExists return false");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.lang.String] */
    public static long b(File file, jwb jwbVar) {
        FileInputStream fileInputStream;
        long j = 0;
        if (file == null || jwbVar == null || jwbVar.l() <= 0) {
            LogUtil.h(FileUtil.TAG, "getFileSize parameter error");
            return 0L;
        }
        jwbVar.d().clear();
        FileInputStream fileInputStream2 = null;
        FileInputStream fileInputStream3 = null;
        FileInputStream fileInputStream4 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        if (!file.exists()) {
            return 0L;
        }
        fileInputStream = new FileInputStream(file);
        try {
            jwbVar.d(fileInputStream.available());
            j = jwbVar.a();
            byte[] bArr = new byte[jwbVar.l()];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                if (read != jwbVar.l()) {
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    blt.c(FileUtil.TAG, bArr2, " num: ", Integer.valueOf(read), "getFileSize data: ");
                    jwbVar.d().add((byte[]) bArr2.clone());
                } else {
                    blt.c(FileUtil.TAG, bArr, " num: ", Integer.valueOf(read), " getFileSize info: ");
                    jwbVar.d().add((byte[]) bArr.clone());
                }
            }
            ?? r4 = "getFileSize fileInfoList.size() : ";
            LogUtil.a(FileUtil.TAG, "getFileSize fileInfoList.size() : ", Integer.valueOf(jwbVar.d().size()));
            try {
                fileInputStream.close();
                fileInputStream2 = r4;
            } catch (IOException unused3) {
                LogUtil.b(FileUtil.TAG, "loadCertificates Exception.");
                fileInputStream2 = r4;
            }
        } catch (FileNotFoundException unused4) {
            fileInputStream3 = fileInputStream;
            LogUtil.b(FileUtil.TAG, "getFileSize FileNotFoundException.");
            fileInputStream2 = fileInputStream3;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                    fileInputStream2 = fileInputStream3;
                } catch (IOException unused5) {
                    LogUtil.b(FileUtil.TAG, "loadCertificates Exception.");
                    fileInputStream2 = fileInputStream3;
                }
            }
            return j;
        } catch (IOException unused6) {
            fileInputStream4 = fileInputStream;
            LogUtil.b(FileUtil.TAG, "getFileSize IOException.");
            fileInputStream2 = fileInputStream4;
            if (fileInputStream4 != null) {
                try {
                    fileInputStream4.close();
                    fileInputStream2 = fileInputStream4;
                } catch (IOException unused7) {
                    LogUtil.b(FileUtil.TAG, "loadCertificates Exception.");
                    fileInputStream2 = fileInputStream4;
                }
            }
            return j;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused8) {
                    LogUtil.b(FileUtil.TAG, "loadCertificates Exception.");
                }
            }
            throw th;
        }
        return j;
    }

    public static void a(Context context, final String str) {
        Object[] objArr = new Object[2];
        objArr[0] = "setCreateLogFile context == null is ";
        objArr[1] = Boolean.valueOf(context == null);
        LogUtil.a(FileUtil.TAG, objArr);
        if (context == null || CommonUtil.ag(context)) {
            return;
        }
        c.execute(new Runnable() { // from class: jwa.5
            @Override // java.lang.Runnable
            public void run() {
                if (jwa.i(str) && jwa.h(str)) {
                    return;
                }
                LogUtil.a(FileUtil.TAG, "setCreateLogFile trigger the upgrade and check whether the upg.log file exists");
                jwa.c(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean i(String str) {
        File file = new File(CommonUtil.c(e));
        if (!file.exists()) {
            LogUtil.a(FileUtil.TAG, "isHasUpgLog isResult: ", Boolean.valueOf(file.mkdirs()));
        }
        File[] listFiles = file.listFiles();
        String a2 = jsd.a(g(str));
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.getName().contains("_mcu_upg") && file2.getName().contains(a2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean h(String str) {
        DeviceInfo g = g(str);
        if (g == null) {
            LogUtil.h(FileUtil.TAG, "currentDeviceInfo is null");
            return false;
        }
        String a2 = jsd.a(g);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h(FileUtil.TAG, "shaDeviceId is null or empty");
            return false;
        }
        LinkedList<File> c2 = jsj.c(CommonUtil.c(e));
        if (c2 == null) {
            LogUtil.h(FileUtil.TAG, "equalUpgLog otaFiles is null.");
            return false;
        }
        Iterator<File> it = c2.iterator();
        while (it.hasNext()) {
            if (it.next().getName().contains(a2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str) {
        b(g(str));
    }

    private static void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h(FileUtil.TAG, "onCreateFile currentDeviceInfo is null");
            return;
        }
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        if (TextUtils.isEmpty(udidFromDevice)) {
            if (deviceInfo.getDeviceIdentify() == null) {
                return;
            } else {
                udidFromDevice = c(deviceInfo);
            }
        }
        jsc.b(BaseApplication.getContext(), udidFromDevice);
        String c2 = kxz.c();
        int productType = deviceInfo.getProductType();
        String d = jrx.d().d(productType, jrx.d().b(productType, deviceInfo.getDeviceModel()), deviceInfo.getDeviceModel());
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(e);
        stringBuffer.append(d);
        stringBuffer.append("_");
        String softVersion = deviceInfo.getSoftVersion();
        stringBuffer.append(softVersion);
        stringBuffer.append("_");
        stringBuffer.append(udidFromDevice);
        stringBuffer.append("_");
        stringBuffer.append(c2);
        stringBuffer.append("_Beta_mcu_upg.log");
        e(stringBuffer, d, udidFromDevice, softVersion);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.lang.String] */
    private static void e(StringBuffer stringBuffer, String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        String c2 = CommonUtil.c(stringBuffer.toString());
        BufferedWriter bufferedWriter = null;
        bufferedWriter = null;
        bufferedWriter = null;
        bufferedWriter = null;
        bufferedWriter = null;
        r4 = null;
        bufferedWriter = null;
        bufferedWriter = null;
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(c2, true);
                try {
                    outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
                    try {
                        BufferedWriter bufferedWriter3 = new BufferedWriter(outputStreamWriter);
                        try {
                            ?? file = new File(c2);
                            boolean exists = file.exists();
                            bufferedWriter = file;
                            if (!exists) {
                                boolean createNewFile = file.createNewFile();
                                bufferedWriter = file;
                                if (createNewFile) {
                                    LogUtil.b(FileUtil.TAG, "create uploadlog success");
                                    bufferedWriter = "create uploadlog success";
                                }
                            }
                            bufferedWriter3.write("");
                            bufferedWriter3.write(jsd.e(BaseApplication.getContext(), str, str2, str3));
                            try {
                                bufferedWriter3.flush();
                                bufferedWriter3.close();
                                outputStreamWriter.close();
                                fileOutputStream.close();
                            } catch (IOException unused) {
                                LogUtil.b(FileUtil.TAG, "writeFileSdcard ioException");
                            }
                        } catch (IOException unused2) {
                            bufferedWriter = bufferedWriter3;
                            LogUtil.b(FileUtil.TAG, "IOException ioException");
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.flush();
                                    bufferedWriter.close();
                                } catch (IOException unused3) {
                                    LogUtil.b(FileUtil.TAG, "writeFileSdcard ioException");
                                }
                            }
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (OutOfMemoryError unused4) {
                            bufferedWriter = bufferedWriter3;
                            LogUtil.b(FileUtil.TAG, "createOtaLog uploadLog OutOfMemoryError");
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.flush();
                                    bufferedWriter.close();
                                } catch (IOException unused5) {
                                    LogUtil.b(FileUtil.TAG, "writeFileSdcard ioException");
                                }
                            }
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedWriter2 = bufferedWriter3;
                            if (bufferedWriter2 != null) {
                                try {
                                    bufferedWriter2.flush();
                                    bufferedWriter2.close();
                                } catch (IOException unused6) {
                                    LogUtil.b(FileUtil.TAG, "writeFileSdcard ioException");
                                    throw th;
                                }
                            }
                            if (outputStreamWriter != null) {
                                outputStreamWriter.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (IOException unused7) {
                    } catch (OutOfMemoryError unused8) {
                    }
                } catch (IOException unused9) {
                    outputStreamWriter = null;
                } catch (OutOfMemoryError unused10) {
                    outputStreamWriter = null;
                } catch (Throwable th2) {
                    th = th2;
                    outputStreamWriter = null;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStreamWriter = outputStreamWriter;
                bufferedWriter2 = bufferedWriter;
            }
        } catch (IOException unused11) {
            fileOutputStream = null;
            outputStreamWriter = null;
        } catch (OutOfMemoryError unused12) {
            fileOutputStream = null;
            outputStreamWriter = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
            outputStreamWriter = null;
        }
    }

    private static DeviceInfo g(String str) {
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, FileUtil.TAG);
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, FileUtil.TAG);
            if (deviceList2.size() > 0) {
                deviceInfo = deviceList2.get(0);
            }
        }
        if (deviceInfo != null) {
            return deviceInfo;
        }
        List<DeviceInfo> deviceList3 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, FileUtil.TAG);
        return deviceList3.size() > 0 ? deviceList3.get(0) : deviceInfo;
    }

    private static String c(DeviceInfo deviceInfo) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        String securityDeviceId = deviceInfo.getSecurityDeviceId();
        String str = securityDeviceId + deviceIdentify;
        if (!deviceIdentify.equals(securityDeviceId)) {
            deviceIdentify = str;
        }
        if (deviceInfo.getProductType() >= 34 && !Utils.o()) {
            return knl.a(deviceIdentify);
        }
        return jsd.n(securityDeviceId);
    }
}
