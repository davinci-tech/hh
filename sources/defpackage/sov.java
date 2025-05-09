package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import health.compact.a.LogUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class sov {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f17185a = {123, 2, 0, 5};
    private static final int e = 4;
    private static final int b = 6;

    public static boolean b(Context context, sol solVar, BufferedOutputStream bufferedOutputStream) {
        if (context == null || solVar == null || bufferedOutputStream == null) {
            LogUtil.a("Unite_FileUtils", "(context == null) || (commonFileInfo == null) || (outputStream == null)");
            return false;
        }
        try {
            bufferedOutputStream.write(solVar.b().array());
            bufferedOutputStream.flush();
            LogUtil.c("Unite_FileUtils", "createPackFileByte end");
            return true;
        } catch (IOException e2) {
            LogUtil.e("Unite_FileUtils", "createPackFileByte IOException e ", ExceptionUtils.d(e2));
            return false;
        }
    }

    public static void a(Context context, sol solVar) {
        if (context == null || solVar == null) {
            LogUtil.a("Unite_FileUtils", "setCommonFileInfoFlag (context == null) || (fileInfo == null)");
            return;
        }
        File file = new File(b(context, solVar.m(), solVar.l(), b(solVar)));
        d(file, file.getParentFile());
        sop.a(file, solVar.l());
    }

    private static void d(File file, File file2) {
        try {
            if (file2.exists() && !file2.isDirectory()) {
                LogUtil.c("Unite_FileUtils", "isWrongDirDeleteSuccess", Boolean.valueOf(file2.delete()), "5.44.1 get file name is empty, please check.");
            }
            if (!file2.exists()) {
                LogUtil.c("Unite_FileUtils", "checkFile file mkdir:", Boolean.valueOf(file2.mkdirs()));
            }
            if (file.exists()) {
                return;
            }
            LogUtil.c("Unite_FileUtils", "checkFile file create is:", Boolean.valueOf(file.createNewFile()));
        } catch (IOException e2) {
            LogUtil.e("Unite_FileUtils", "checkFile IOException e ", ExceptionUtils.d(e2));
        }
    }

    public static String a(sol solVar, int i, int i2, FileInputStream fileInputStream) {
        FileChannel fileChannel = null;
        if (solVar == null || fileInputStream == null) {
            LogUtil.a("Unite_FileUtils", "sha256FileFromOffset (info == null) || (fileInputStream == null)");
            return null;
        }
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                ByteBuffer allocate = ByteBuffer.allocate(i2);
                fileChannel = fileInputStream.getChannel();
                fileChannel.position(i);
                int i3 = 0;
                while (true) {
                    int read = fileChannel.read(allocate);
                    if (read != -1) {
                        messageDigest.update(allocate.array(), 0, read);
                        allocate.clear();
                        i3++;
                    } else {
                        byte[] digest = messageDigest.digest();
                        LogUtil.c("Unite_FileUtils", "sha256FileFromOffset offset : ", Integer.valueOf(i), "sha256FileFromOffset count : ", Integer.valueOf(i3));
                        return blq.d(digest);
                    }
                }
            } finally {
                blv.d(null);
                blv.d(fileInputStream);
            }
        } catch (IOException | NoSuchAlgorithmException e2) {
            LogUtil.e("Unite_FileUtils", "sha256FileFromOffset exception : ", ExceptionUtils.d(e2));
            blv.d(fileChannel);
            blv.d(fileInputStream);
            return "";
        }
    }

    public static FileInputStream d(Context context, sol solVar) {
        if (context == null || solVar == null) {
            LogUtil.a("Unite_FileUtils", "getInputStream (context == null) || (commonFileInfo == null)");
            return null;
        }
        try {
            return new FileInputStream(b(context, solVar.m(), solVar.l(), b(solVar)));
        } catch (IOException e2) {
            LogUtil.e("Unite_FileUtils", "getInputStream IOException e : ", ExceptionUtils.d(e2));
            return null;
        }
    }

    public static boolean e(Context context, sol solVar) {
        if (context == null || solVar == null) {
            LogUtil.a("Unite_FileUtils", "deleteCommonFile (context == null) || (commonFileInfo == null)");
            return false;
        }
        String b2 = b(context, solVar.m(), solVar.l(), b(solVar));
        if (b2 == null) {
            LogUtil.a("Unite_FileUtils", "deleteCommonFile path is null");
            return false;
        }
        File file = new File(b2);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static String b(Context context, String str, int i, String str2) {
        if (context == null) {
            return "";
        }
        try {
            if (sop.c(str, i)) {
                return context.getFilesDir().getPath() + File.separator + "common_file_request" + File.separator + a(str2) + File.separator + str;
            }
            return bky.d(context.getFilesDir().getCanonicalPath() + File.separator + "common_file_request" + File.separator + a(str2) + File.separator + str);
        } catch (IOException e2) {
            LogUtil.e("Unite_FileUtils", "getFilePath IOException e", ExceptionUtils.d(e2));
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.BufferedOutputStream c(android.content.Context r4, defpackage.sol r5) {
        /*
            java.lang.String r0 = "Unite_FileUtils"
            r1 = 0
            if (r4 == 0) goto L66
            if (r5 != 0) goto L8
            goto L66
        L8:
            java.lang.String r2 = r5.m()
            int r3 = r5.l()
            java.lang.String r5 = b(r5)
            java.lang.String r4 = b(r4, r2, r3, r5)
            if (r4 != 0) goto L24
            java.lang.String r4 = "getBufferedOutputStream path is null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.LogUtil.a(r0, r4)
            return r1
        L24:
            java.io.File r5 = new java.io.File     // Catch: java.lang.IllegalArgumentException -> L37 java.io.IOException -> L4e
            r5.<init>(r4)     // Catch: java.lang.IllegalArgumentException -> L37 java.io.IOException -> L4e
            java.io.File r4 = r5.getParentFile()     // Catch: java.lang.IllegalArgumentException -> L35 java.io.IOException -> L4e
            d(r5, r4)     // Catch: java.lang.IllegalArgumentException -> L35 java.io.IOException -> L4e
            java.io.FileOutputStream r4 = org.apache.commons.io.FileUtils.openOutputStream(r5)     // Catch: java.lang.IllegalArgumentException -> L35 java.io.IOException -> L4e
            goto L5d
        L35:
            r4 = move-exception
            goto L39
        L37:
            r4 = move-exception
            r5 = r1
        L39:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.io.FileNotFoundException -> L40
            r2.<init>(r5)     // Catch: java.io.FileNotFoundException -> L40
            r4 = r2
            goto L5d
        L40:
            java.lang.String r5 = "getBufferedOutputStream IllegalArgumentException e"
            java.lang.String r4 = com.huawei.haf.common.exception.ExceptionUtils.d(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r5, r4}
            health.compact.a.LogUtil.e(r0, r4)
            goto L5c
        L4e:
            r4 = move-exception
            java.lang.String r5 = "getBufferedOutputStream IOException e"
            java.lang.String r4 = com.huawei.haf.common.exception.ExceptionUtils.d(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r5, r4}
            health.compact.a.LogUtil.e(r0, r4)
        L5c:
            r4 = r1
        L5d:
            if (r4 != 0) goto L60
            return r1
        L60:
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream
            r5.<init>(r4)
            return r5
        L66:
            java.lang.String r4 = "getBufferedOutputStream (context == null) || (commonFileInfo == null)"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.LogUtil.a(r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sov.c(android.content.Context, sol):java.io.BufferedOutputStream");
    }

    public static String e(File file) {
        FileInputStream fileInputStream;
        if (file == null || !file.exists()) {
            LogUtil.e("Unite_FileUtils", "getFileShaHex file not exit");
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException unused) {
            LogUtil.e("Unite_FileUtils", "getFileShaHex FileNotFoundException");
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            return null;
        }
        try {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bArr = new byte[1024];
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    messageDigest.update(bArr, 0, 0);
                } else {
                    while (read != -1) {
                        messageDigest.update(bArr, 0, read);
                        read = fileInputStream.read(bArr);
                    }
                }
                return blq.d(messageDigest.digest());
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException unused2) {
                    LogUtil.e("Unite_FileUtils", "getFileShaHex IOException");
                }
            }
        } catch (IOException unused3) {
            LogUtil.e("Unite_FileUtils", "getFileShaHex IOException");
            try {
                fileInputStream.close();
                return "";
            } catch (IOException unused4) {
                LogUtil.e("Unite_FileUtils", "getFileShaHex IOException");
                return "";
            }
        } catch (NoSuchAlgorithmException unused5) {
            LogUtil.e("Unite_FileUtils", "getFileShaHex NoSuchAlgorithmException");
            try {
                fileInputStream.close();
                return "";
            } catch (IOException unused6) {
                LogUtil.e("Unite_FileUtils", "getFileShaHex IOException");
                return "";
            }
        }
    }

    public static void a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("Unite_FileUtils", "setFileSecurityLevel filePath or riskLevel is empty.");
            return;
        }
        try {
            Class<?> cls = Class.forName("com.huawei.fileprotect.HwSfpPolicyManager");
            Object invoke = cls.getMethod("setLabel", Context.class, String.class, String.class, String.class, Integer.TYPE).invoke(cls.getMethod("getDefault", new Class[0]).invoke(cls, new Object[0]), BaseApplication.e(), str, "SecurityLevel", str2, Integer.valueOf(i));
            if ((invoke instanceof Integer ? ((Integer) invoke).intValue() : -1) == 0) {
                LogUtil.c("Unite_FileUtils", "setFileSecurityLevel success!");
            }
        } catch (SecurityException unused) {
            LogUtil.e("Unite_FileUtils", "setFileSecurityLevel SecurityException");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            LogUtil.e("Unite_FileUtils", "setFileSecurityLevel other Exception");
        } catch (ClassNotFoundException unused3) {
            LogUtil.e("Unite_FileUtils", "setFileSecurityLevel ClassNotFoundException");
        } catch (NoSuchMethodException unused4) {
            LogUtil.e("Unite_FileUtils", "setFileSecurityLevel NoSuchMethodException");
        } finally {
            LogUtil.c("Unite_FileUtils", "setFileSecurityLevel failed! result = ", -1);
        }
    }

    public static CommonFileInfoParcel e(FileInfo fileInfo) {
        CommonFileInfoParcel commonFileInfoParcel = new CommonFileInfoParcel();
        if (fileInfo != null) {
            commonFileInfoParcel.setFileName(fileInfo.getFileName());
            commonFileInfoParcel.setFileType(fileInfo.getFileType());
            commonFileInfoParcel.setFilePath(fileInfo.getFilePath());
            commonFileInfoParcel.setSourcePackageName(fileInfo.getPackageName());
            commonFileInfoParcel.setInTheQueue(false);
            commonFileInfoParcel.setIdentify(fileInfo.getIdentify());
        }
        return commonFileInfoParcel;
    }

    public static boolean e(byte[] bArr) {
        if (bArr == null || bArr.length < b) {
            return false;
        }
        for (int i = 0; i < e; i++) {
            if (f17185a[i] != bArr[i + 2]) {
                return false;
            }
        }
        return true;
    }

    public static int b(String str) {
        int i;
        if (str == null || !str.contains("_")) {
            LogUtil.e("Unite_FileUtils", "mapKey error");
            return -1;
        }
        LogUtil.c("Unite_FileUtils", "getDevicefiled entry");
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Integer.parseInt(str.split("_")[1]);
            } catch (NumberFormatException unused) {
                LogUtil.e("Unite_FileUtils", "getDevicefiled numberFormatException");
            }
            LogUtil.c("Unite_FileUtils", "getDevicefiled entry， deviceFileid:", Integer.valueOf(i));
            return i;
        }
        i = 0;
        LogUtil.c("Unite_FileUtils", "getDevicefiled entry， deviceFileid:", Integer.valueOf(i));
        return i;
    }

    public static String e(String str, int i) {
        String str2;
        LogUtil.c("Unite_FileUtils", "getAppfiled entry");
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = str + "_" + i;
        }
        LogUtil.c("Unite_FileUtils", "getMapKey entry， mapKey:", blt.a(str2));
        return str2;
    }

    public static int d(String str) {
        int nextInt;
        LogUtil.c("Unite_FileUtils", "getSecureRandom entry, uniqueSign is :", blt.a(str));
        if (!TextUtils.isEmpty(str)) {
            byte[] bArr = new byte[0];
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                LogUtil.c("Unite_FileUtils", "seed get error :", blt.a(str));
            }
            nextInt = new SecureRandom(bArr).nextInt(100000000);
        } else {
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(new byte[20]);
            nextInt = secureRandom.nextInt(100000000);
        }
        LogUtil.c("Unite_FileUtils", "seRandom is :", Integer.valueOf(nextInt));
        return nextInt;
    }

    public static String a(String str) {
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            str2 = str.replaceAll(":", "");
        } else {
            LogUtil.c("Unite_FileUtils", "deviceMac is error ");
        }
        String d = blq.d(iyx.c(blq.c(str2)));
        return TextUtils.isEmpty(d) ? str2 : d;
    }

    public static void b(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        b(file2);
                    } else if (!file2.delete()) {
                        LogUtil.a("Unite_FileUtils", "delete fail.");
                    }
                }
            }
            if (file.delete()) {
                return;
            }
            LogUtil.a("Unite_FileUtils", "delete fail.");
        }
    }

    private static Map<String, UniteDevice> d() {
        HashMap hashMap = new HashMap();
        for (UniteDevice uniteDevice : bgl.c().getDeviceList().values()) {
            DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
            if (deviceInfo.isUsing() && deviceInfo.getDeviceConnectState() == 2) {
                hashMap.put(uniteDevice.getIdentify(), uniteDevice);
            }
        }
        LogUtil.c("Unite_FileUtils", "getDeviceList deviceMap size is :", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    public static UniteDevice e(String str) {
        LogUtil.c("Unite_FileUtils", "deviceMac is :", blt.a(str));
        if (str == null) {
            return null;
        }
        for (UniteDevice uniteDevice : d().values()) {
            if (uniteDevice.getDeviceInfo().getDeviceMac().equals(str)) {
                LogUtil.c("Unite_FileUtils", "uniteDevice get success");
                return uniteDevice;
            }
        }
        return null;
    }

    public static String b(sol solVar) {
        if (solVar == null) {
            return null;
        }
        if (solVar.i() == null) {
            LogUtil.c("Unite_FileUtils", "fileInfo.getDevice is null");
        }
        String a2 = a(solVar.i());
        LogUtil.c("Unite_FileUtils", "getDeviceMac is :", blt.a(a2));
        return a2;
    }

    public static String a(UniteDevice uniteDevice) {
        if (uniteDevice != null) {
            DeviceInfo deviceInfo = uniteDevice.getDeviceInfo();
            LogUtil.c("Unite_FileUtils", "uniteDevice.getIdentify() is :", blt.a(uniteDevice.getIdentify()));
            if (deviceInfo != null) {
                return deviceInfo.getDeviceMac();
            }
        }
        return null;
    }

    public static DeviceInfo d(sol solVar) {
        if (solVar == null) {
            return null;
        }
        if (solVar.i() == null) {
            LogUtil.c("Unite_FileUtils", "fileInfo.getDevice is null");
        }
        UniteDevice i = solVar.i();
        DeviceInfo deviceInfo = i != null ? i.getDeviceInfo() : null;
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceInfo is null :";
        objArr[1] = Boolean.valueOf(deviceInfo == null);
        LogUtil.c("Unite_FileUtils", objArr);
        return deviceInfo;
    }
}
