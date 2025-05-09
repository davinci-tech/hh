package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jvu {
    public static void d(DeviceInfo deviceInfo) {
        jwb e = jvs.e(deviceInfo);
        if (e == null || e.l() == 0) {
            LogUtil.h("FileServiceConsultUtil", "responseFileManagerConsult transferSize is zero.");
            jvv.a(deviceInfo, false);
            return;
        }
        e.i((int) (e.j() / e.l()));
        LogUtil.c("FileServiceConsultUtil", "responseFileManagerConsult.");
        if (e.b() != null && e.e() == 0) {
            a(100000, deviceInfo);
        } else if (e.e() == 1) {
            b(100000, deviceInfo);
        } else {
            LogUtil.h("FileServiceConsultUtil", "responseFileManagerConsult fileType is unknown.");
            jvv.a(deviceInfo, false);
        }
    }

    public static void a(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(2);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jvw.d(deviceCommand, e(i));
    }

    private static ByteBuffer e(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put(Byte.MAX_VALUE);
        allocate.put((byte) 4);
        allocate.put(cvx.g(i));
        return allocate;
    }

    public static void b(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(2);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jvw.d(deviceCommand, e(i));
    }

    public static void c(String str, DeviceInfo deviceInfo) {
        LogUtil.c("FileServiceConsultUtil", "responseSingleFile");
        if (deviceInfo == null) {
            LogUtil.h("FileServiceConsultUtil", "responseSingleFile deviceInfo is null.");
            return;
        }
        jwb e = jvs.e(deviceInfo);
        if (str == null || e == null) {
            jvw.d(3, 100001, deviceInfo);
            return;
        }
        if (!jwa.d(jvw.e + str) || jwa.b(new File(jvw.e, str), e) == 0) {
            jvw.d(3, 100001, deviceInfo);
            return;
        }
        long b = jwa.b(new File(jvw.e, str), e);
        LogUtil.c("FileServiceConsultUtil", "responseSingleFile size : ", Long.valueOf(b));
        b(b, 0L, deviceInfo);
    }

    private static void b(long j, long j2, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(3);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        ByteBuffer allocate = ByteBuffer.allocate(12);
        allocate.put((byte) 2).put((byte) 4).put(cvx.g((int) j)).put((byte) 3).put((byte) 4).put(cvx.g((int) j2));
        jvw.d(deviceCommand, allocate);
    }

    public static void d(String str, DeviceInfo deviceInfo) {
        LogUtil.a("FileServiceConsultUtil", "responseStarSingleFile");
        if (str == null) {
            jvw.d(3, 100001, deviceInfo);
            return;
        }
        jwb e = jvs.e(deviceInfo);
        if (e == null) {
            LogUtil.h("FileServiceConsultUtil", "responseStarSingleFile ephemerisUtil is null.");
            return;
        }
        String str2 = knl.a(deviceInfo.getDeviceIdentify()) + str;
        File file = new File(jvw.f14140a, str2);
        long b = jwa.b(file, e);
        if (jwa.d(jvw.f14140a + str2) && b != 0) {
            LogUtil.a("FileServiceConsultUtil", "responseStarSingleFile size : ", Long.valueOf(b), " sAllSize:", Integer.valueOf(e.a()));
            a(b, c(file), deviceInfo);
        } else {
            jvw.d(3, 100001, deviceInfo);
        }
    }

    private static byte[] c(File file) {
        byte[] bArr = new byte[(int) file.length()];
        FileInputStream fileInputStream = null;
        try {
            try {
                fileInputStream = FileUtils.openInputStream(file);
                LogUtil.a("FileServiceConsultUtil", "read length : ", Integer.valueOf(fileInputStream.read(bArr)));
            } catch (IOException e) {
                LogUtil.b("FileServiceConsultUtil", "getFileBytes exception : ", ExceptionUtils.d(e));
            }
            IoUtils.e(fileInputStream);
            return jvx.a(bArr);
        } catch (Throwable th) {
            IoUtils.e(fileInputStream);
            throw th;
        }
    }

    public static void a(long j, byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("FileServiceConsultUtil", "sendStarSingleFileInfo() fileSize : ", Long.valueOf(j));
        if (bArr == null) {
            LogUtil.h("FileServiceConsultUtil", "crc is null");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(3);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        byte[] c = cvx.c(bArr.length);
        ByteBuffer allocate = ByteBuffer.allocate(c.length + 7 + bArr.length);
        allocate.put((byte) 2).put((byte) 4).put(cvx.g((int) j)).put((byte) 3).put(c).put(bArr);
        jvw.d(deviceCommand, allocate);
    }

    public static void d(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(6);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        jvw.d(deviceCommand, e(i));
    }

    public static void c(int i, DeviceInfo deviceInfo) {
        LogUtil.c("FileServiceConsultUtil", "responseResultNotification validity : ", Integer.valueOf(i));
        d(100000, deviceInfo);
    }

    public static void b(DeviceInfo deviceInfo) {
        LogUtil.a("FileServiceConsultUtil", "initData()");
        if (deviceInfo != null) {
            jvf.c().remove(deviceInfo.getDeviceIdentify());
            jva.b().a().remove(deviceInfo.getDeviceIdentify());
        }
    }
}
