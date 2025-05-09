package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import defpackage.bir;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public class sor {
    private static final String c = BaseApplication.e().getFilesDir() + "/fileShare/";
    private static final String b = "watchfacePhoto" + File.separator + WatchFaceProvider.BACKGROUND_LABEL;
    private static final String d = "watchfaceVideo" + File.separator + WatchFaceProvider.BACKGROUND_LABEL;

    public static boolean b(String str) {
        return str == null || str.indexOf(FeedbackWebConstants.INVALID_FILE_NAME_PRE) < 0;
    }

    public static int b(int i, int i2) {
        int i3;
        if (i2 == 0) {
            return 0;
        }
        if (i % i2 == 0) {
            i3 = i / i2;
        } else {
            i3 = (i / i2) + 1;
        }
        LogUtil.c("TransFileCommandUtills", "sendDataToDevice, file_array :", Integer.valueOf(i3));
        return i3;
    }

    public static int b(int i, int i2, int i3) {
        if ((i - i3) / i2 != 0) {
            LogUtil.c("TransFileCommandUtills", "send max, size :", Integer.valueOf(i2));
            return i2;
        }
        int i4 = i % i2;
        LogUtil.c("TransFileCommandUtills", "send not max, size :", Integer.valueOf(i4));
        return i4;
    }

    public static void e(String str, long j, int i, String str2, UniteDevice uniteDevice) {
        ByteBuffer allocate;
        LogUtil.c("TransFileCommandUtills", "enter sendFileInfo fileName ", str);
        byte[] e = spl.e(str, j, i);
        if (i == 1) {
            String[] split = str.split("_");
            if (split.length != 2) {
                LogUtil.a("TransFileCommandUtills", "sendFileInfo, deviceCommand error");
                return;
            }
            byte[] g = g(split[0]);
            byte[] o = o(split[1]);
            allocate = ByteBuffer.allocate(e.length + g.length + o.length);
            allocate.put(e).put(g).put(o);
            LogUtil.c("TransFileCommandUtills", "sendFileInfo, get WatchInfo success");
        } else if (i == 2 && !TextUtils.isEmpty(str2) && !"huaweiOnlineMusic".equals(str2)) {
            byte[] h = h(str2);
            allocate = ByteBuffer.allocate(e.length + h.length);
            allocate.put(e).put(h);
        } else {
            LogUtil.a("TransFileCommandUtills", "sendFileInfo error");
            allocate = ByteBuffer.allocate(e.length);
            allocate.put(e);
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(allocate.capacity() + 2);
        allocate2.put((byte) 40).put((byte) 2).put(allocate.array());
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate2.array());
        bir.a aVar = new bir.a();
        blt.d("TransFileCommandUtills", allocate.array(), "getStartSendCommand deviceCommand: ");
        c(uniteDevice, aVar.b(birVar));
    }

    private static byte[] g(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 5;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    private static byte[] o(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 6;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    private static byte[] h(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 7;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    public static void c(UniteDevice uniteDevice, bir birVar) {
        sol c2 = TransferFileQueueManager.d().c("ready_send_command");
        if (c2 != null) {
            b(c2.g(), uniteDevice, birVar, c2.u());
        }
    }

    public static void b(String str, String str2, int i, int i2, int i3, DeviceInfo deviceInfo) {
        ByteBuffer allocate;
        byte[] f = f(str2);
        byte[] f2 = f(i);
        byte[] d2 = d(i3);
        if (i2 != -1) {
            byte[] c2 = c(i2);
            allocate = ByteBuffer.allocate(f.length + 2 + f2.length + c2.length + d2.length);
            allocate.put((byte) 40).put((byte) 1).put(f).put(f2).put(c2).put(d2);
        } else {
            int length = f.length;
            allocate = ByteBuffer.allocate(length + 2 + f2.length + d2.length);
            allocate.put((byte) 40).put((byte) 1).put(f).put(f2).put(d2);
        }
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        aVar.c(true);
        LogUtil.c("TransFileCommandUtills", "sendFileHashFailed, deviceCommand :", birVar.toString());
        b(str, a(deviceInfo), aVar.b(birVar), i);
    }

    public static void c(String str, String str2, int i, int i2, DeviceInfo deviceInfo, String str3, String str4) {
        byte[] f = f(str2);
        byte[] f2 = f(i);
        byte[] d2 = d(i2);
        byte[] i3 = i(str3);
        byte[] j = j(str4);
        int length = f.length;
        int length2 = f2.length;
        int length3 = d2.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 2 + length2 + length3 + i3.length + j.length);
        allocate.put((byte) 40).put((byte) 1).put(f).put(f2).put(d2).put(i3).put(j);
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        aVar.c(true);
        LogUtil.c("TransFileCommandUtills", "sendHiWearFileHashFailed, deviceCommand :", birVar.toString());
        b(str, a(deviceInfo), aVar.b(birVar), i);
    }

    private static byte[] f(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 1;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    private static byte[] f(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 2;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] d(int i) {
        byte[] i2 = blq.i(i);
        byte[] bArr = new byte[i2.length + 2];
        bArr[0] = Byte.MAX_VALUE;
        bArr[1] = 4;
        System.arraycopy(i2, 0, bArr, 2, i2.length);
        return bArr;
    }

    private static byte[] c(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 5;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] i(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 3;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    private static byte[] j(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 4;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    private static void b(String str, UniteDevice uniteDevice, bir birVar, int i) {
        int e;
        if (i == 7) {
            e = blk.e().e(uniteDevice.getIdentify(), 40, str);
        } else {
            e = blk.e().e(uniteDevice.getIdentify(), 40, Integer.toString(i));
        }
        birVar.c(e);
        byte[] e2 = birVar.e();
        if (e2 != null && e2.length > 2) {
            LogUtil.c("TransFileCommandUtills", "sendDeviceData fileType: ", Integer.valueOf(i), " packageName: ", str, " socketChannel: ", Integer.valueOf(e), " serviceId: ", Byte.valueOf(e2[0]), " commandId: ", Byte.valueOf(e2[1]));
        }
        spk.b().b(uniteDevice, birVar);
    }

    public static void d(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        byte[] i2 = i(i);
        byte[] i3 = blq.i(20001);
        int length = i3.length + 2;
        byte[] bArr = new byte[length];
        bArr[0] = Byte.MAX_VALUE;
        bArr[1] = 4;
        System.arraycopy(i3, 0, bArr, 2, i3.length);
        ByteBuffer allocate = ByteBuffer.allocate(length + i2.length + 2);
        allocate.put((byte) 40).put((byte) 3).put(i2).put(bArr);
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        LogUtil.c("TransFileCommandUtills", "sendFileHashFailed, deviceCommand :", allocate.toString());
        c(a(deviceInfo), aVar.b(birVar));
    }

    private static byte[] i(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    public static void e(int i, String str, DeviceInfo deviceInfo, int i2) {
        ByteBuffer allocate;
        if (deviceInfo == null) {
            return;
        }
        byte[] i3 = i(i);
        byte[] a2 = blq.a(str);
        byte[] d2 = blq.d(a2.length);
        int length = a2.length + 1 + d2.length;
        byte[] bArr = new byte[length];
        bArr[0] = 3;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(a2, 0, bArr, d2.length + 1, a2.length);
        if (i2 != 0) {
            byte[] i4 = blq.i(i2);
            byte[] d3 = blq.d(i4.length);
            byte[] g = blq.g(4);
            allocate = ByteBuffer.allocate(i3.length + length + 2 + g.length + d3.length + i4.length);
            allocate.put((byte) 40).put((byte) 3).put(i3).put(bArr);
            allocate.put(g).put(d3).put(i4);
        } else {
            allocate = ByteBuffer.allocate(i3.length + length + 2);
            allocate.put((byte) 40).put((byte) 3).put(i3).put(bArr);
        }
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        aVar.c(true);
        LogUtil.c("TransFileCommandUtills", "sendFileHashResult, deviceCommand :", allocate.toString());
        c(a(deviceInfo), aVar.b(birVar));
    }

    public static void d(int i, boolean z, DeviceInfo deviceInfo) {
        ByteBuffer allocate;
        LogUtil.c("TransFileCommandUtills", "enter sendConsultAck fileId :", Integer.valueOf(i), ", isNormal :", Boolean.valueOf(z));
        if (deviceInfo == null) {
            return;
        }
        byte[] g = blq.g(i);
        int length = g.length + 2;
        byte[] bArr = new byte[length];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        byte[] b2 = b();
        if (z) {
            allocate = ByteBuffer.allocate(b2.length + length + 5);
            allocate.put((byte) 40).put((byte) 4).put(b2).put(bArr).put(new byte[]{9, 1, 1});
        } else {
            allocate = ByteBuffer.allocate(b2.length + length + 2);
            allocate.put((byte) 40).put((byte) 4).put(b2).put(bArr);
        }
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        LogUtil.c("TransFileCommandUtills", "sendConsultAck, deviceCommand :", allocate.toString());
        c(a(deviceInfo), aVar.b(birVar));
    }

    private static byte[] b() {
        byte[] i = blq.i(100000);
        byte[] bArr = new byte[i.length + 2];
        bArr[0] = Byte.MAX_VALUE;
        bArr[1] = 4;
        System.arraycopy(i, 0, bArr, 2, i.length);
        return bArr;
    }

    public static void e(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        byte[] b2 = b();
        byte[] g = blq.g(i);
        int length = g.length + 2;
        byte[] bArr = new byte[length];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        ByteBuffer allocate = ByteBuffer.allocate(b2.length + length + 2);
        allocate.put((byte) 40).put((byte) 7).put(b2).put(bArr);
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        LogUtil.c("TransFileCommandUtills", "getStartSendCommand deviceCommand:", allocate.toString());
        c(a(deviceInfo), aVar.b(birVar));
    }

    public static void b(UniteDevice uniteDevice, int i, sol solVar) {
        ByteBuffer allocate;
        if (uniteDevice == null) {
            return;
        }
        byte[] b2 = b(i);
        if (solVar != null && solVar.u() == 7) {
            byte[] c2 = c(solVar.m());
            byte[] a2 = a(solVar.ae());
            byte[] e = e(solVar.g());
            allocate = ByteBuffer.allocate(b2.length + c2.length + a2.length + e.length + 2);
            allocate.put((byte) 40).put((byte) 9).put(b2).put(c2).put(a2).put(e);
        } else {
            allocate = ByteBuffer.allocate(b2.length + 2);
            allocate.put((byte) 40).put((byte) 9).put(b2);
        }
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        blt.d("TransFileCommandUtills", allocate.array(), "getStartSendCommand deviceCommand:");
        c(uniteDevice, aVar.b(birVar));
    }

    private static byte[] b(int i) {
        byte[] g = blq.g(i);
        byte[] bArr = new byte[g.length + 2];
        bArr[0] = 1;
        bArr[1] = 1;
        System.arraycopy(g, 0, bArr, 2, g.length);
        return bArr;
    }

    private static byte[] c(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 2;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    private static byte[] a(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 3;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    private static byte[] e(String str) {
        byte[] c2 = blq.c(str);
        byte[] d2 = blq.d(c2.length);
        byte[] bArr = new byte[c2.length + 1 + d2.length];
        bArr[0] = 4;
        System.arraycopy(d2, 0, bArr, 1, d2.length);
        System.arraycopy(c2, 0, bArr, d2.length + 1, c2.length);
        return bArr;
    }

    public static void c(int i, int i2, int i3, String str, DeviceInfo deviceInfo) {
        byte[] bArr;
        if (deviceInfo == null) {
            return;
        }
        byte[] i4 = i(i);
        byte[] a2 = a(i2);
        byte[] e = e(i3);
        if (!TextUtils.isEmpty(str)) {
            byte[] a3 = blq.a(str);
            byte[] d2 = blq.d(a3.length);
            bArr = new byte[a3.length + 1 + d2.length];
            bArr[0] = 5;
            System.arraycopy(d2, 0, bArr, 1, d2.length);
            System.arraycopy(a3, 0, bArr, d2.length + 1, a3.length);
        } else {
            byte[] i5 = blq.i(20001);
            bArr = new byte[i5.length + 2];
            bArr[0] = Byte.MAX_VALUE;
            bArr[1] = 4;
            System.arraycopy(i5, 0, bArr, 2, i5.length);
        }
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + e.length + a2.length + i4.length + 2);
        allocate.put((byte) 40).put((byte) 5).put(i4).put(a2).put(e).put(bArr);
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        bir.a aVar = new bir.a();
        aVar.c(true);
        LogUtil.c("TransFileCommandUtills", "sendFileSubFrameHashResult, deviceCommand :", allocate.toString());
        c(a(deviceInfo), aVar.b(birVar));
    }

    private static byte[] a(int i) {
        byte[] i2 = blq.i(i);
        byte[] bArr = new byte[i2.length + 2];
        bArr[0] = 2;
        bArr[1] = 4;
        System.arraycopy(i2, 0, bArr, 2, i2.length);
        return bArr;
    }

    private static byte[] e(int i) {
        byte[] i2 = blq.i(i);
        byte[] bArr = new byte[i2.length + 2];
        bArr[0] = 3;
        bArr[1] = 4;
        System.arraycopy(i2, 0, bArr, 2, i2.length);
        return bArr;
    }

    public static boolean d(int i, String str) {
        if (str == null) {
            LogUtil.a("TransFileCommandUtills", "checkFileExist fileType :", Integer.valueOf(i), ", fileName = null");
            return false;
        }
        if (i != 3) {
            if (i == 4 || i == 5) {
                return d(str);
            }
            if (i != 10 && i != 11) {
                LogUtil.a("TransFileCommandUtills", "checkFileExist fileType :", Integer.valueOf(i), ", file type is not support.");
                return b(str, i);
            }
        }
        return e(str, i);
    }

    public static boolean d(String str) {
        if (str == null || str.isEmpty()) {
            LogUtil.a("TransFileCommandUtills", "checkPayFileExist fileName null or empty");
            return false;
        }
        File file = new File(c + str);
        LogUtil.c("TransFileCommandUtills", " checkPayFileExist file exists :", Boolean.valueOf(file.exists()));
        return file.exists();
    }

    private static boolean b(String str, int i) {
        if (str == null || TextUtils.isEmpty(str)) {
            LogUtil.a("TransFileCommandUtills", "checkPayFileExist fileName null or empty");
            return false;
        }
        String d2 = d(str, i);
        if (d2 != null) {
            File file = new File(d2);
            LogUtil.c("TransFileCommandUtills", " checkOtherFileExist file exists :", Boolean.valueOf(file.exists()));
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    public static String d(String str, int i) {
        String str2 = null;
        try {
            str2 = BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + i + File.separator + str;
            LogUtil.c("TransFileCommandUtills", " getCommonFilePath file filepath :", str2);
            return str2;
        } catch (IOException unused) {
            LogUtil.e("TransFileCommandUtills", " getCommonFilePath file Exception");
            return str2;
        }
    }

    public static boolean e(String str, int i) {
        File[] listFiles;
        try {
            File file = new File((i == 10 || i == 11) ? BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + d : BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + b);
            LogUtil.c("TransFileCommandUtills", "checkFileExist file exist :", Boolean.valueOf(file.exists()), " , isDirectory :", Boolean.valueOf(file.isDirectory()));
            if (file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (i == 10 && name.contains(str)) {
                        LogUtil.a("TransFileCommandUtills", "checkFileExist exists WATCH_VIDEO_FILE_TYPE");
                        return true;
                    }
                    if (name.contains(str) && name.endsWith(".png")) {
                        LogUtil.c("TransFileCommandUtills", "checkFileExist exists");
                        return true;
                    }
                }
                LogUtil.c("TransFileCommandUtills", "checkFileExist not exists");
            }
            return false;
        } catch (IOException unused) {
            LogUtil.e("TransFileCommandUtills", "checkFileExist IOException");
            return false;
        }
    }

    public static String a(String str, int i, int i2) throws IOException {
        String str2;
        if (i == 10) {
            return BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + d + File.separator + str;
        }
        if (i == 11) {
            str2 = BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + d + File.separator + str;
        } else {
            str2 = BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + b + File.separator + str;
        }
        int lastIndexOf = str2.lastIndexOf(".");
        if (i2 != 1) {
            if (lastIndexOf < str2.length()) {
                str2 = str2.substring(0, lastIndexOf) + WatchFaceConstant.BIN_SUFFIX;
            } else {
                str2 = "";
            }
        }
        LogUtil.c("TransFileCommandUtills", "deviceStartTransfer filePath :", str2);
        return str2;
    }

    private static UniteDevice a(DeviceInfo deviceInfo) {
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setDeviceInfo(deviceInfo);
        uniteDevice.setIdentify(deviceInfo.getDeviceMac());
        return uniteDevice;
    }

    public static byte[] a(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[i2];
        try {
            System.arraycopy(bArr, i, bArr2, 0, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.e("TransFileCommandUtills", "getSendBytes, ArrayIndexOutOfBoundsException");
        }
        return bArr2;
    }
}
