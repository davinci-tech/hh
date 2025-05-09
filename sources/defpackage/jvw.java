package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.File;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jvw {

    /* renamed from: a, reason: collision with root package name */
    public static final String f14140a = BaseApplication.getContext().getFilesDir() + File.separator;
    public static final String e = BaseApplication.getContext().getFilesDir() + "/fileShare/";
    private static final Object d = new Object();

    private static List<String> b(File file, String str) {
        ArrayList arrayList = null;
        if (file == null || str == null) {
            LogUtil.h("FileServiceUtil", "getFileList path or productId is null");
            return null;
        }
        File[] listFiles = file.listFiles();
        if (file.isDirectory() && listFiles != null) {
            arrayList = new ArrayList(16);
            for (File file2 : listFiles) {
                if (file2.getName().contains(str)) {
                    arrayList.add(file2.getName());
                }
            }
        }
        return arrayList;
    }

    public static Object a() {
        return d;
    }

    public static void c(int i, String str, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("FileServiceUtil", "responseSendFileList deviceInfo is null.");
            return;
        }
        if (str == null) {
            LogUtil.h("FileServiceUtil", "productId is null.");
            jvv.a(deviceInfo, false);
            return;
        }
        List<String> b = b(new File(e), str);
        if (b != null && i == 0) {
            c(b, deviceInfo);
        } else if (i == 1) {
            b(deviceInfo);
        } else {
            d(1, 100001, deviceInfo);
        }
    }

    private static void c(List<String> list, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(1);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            byte[] d2 = cvx.d(list.get(i2));
            arrayList.add(d2);
            i += d2.length;
        }
        int i3 = (i + size) - 1;
        byte[] c = cvx.c(i3);
        ByteBuffer allocate = ByteBuffer.allocate(c.length + 1 + i3);
        allocate.put((byte) 1);
        allocate.put(c);
        for (int i4 = 0; i4 < size; i4++) {
            allocate.put((byte[]) arrayList.get(i4));
            if (i4 == size - 1) {
                break;
            }
            allocate.put((byte) 59);
        }
        d(deviceCommand, allocate);
    }

    private static void b(DeviceInfo deviceInfo) {
        jvb jvbVar = jva.b().a().get(deviceInfo.getDeviceIdentify());
        int g = jvbVar != null ? jvbVar.g() : -1;
        LogUtil.a("FileServiceUtil", "now version is : ", Integer.valueOf(g));
        if (g == 0) {
            e(deviceInfo);
        } else if (g == 1 || g == 2 || g == 3) {
            a(deviceInfo);
        } else {
            LogUtil.h("FileServiceUtil", "unknown version : ", Integer.valueOf(g));
        }
    }

    private static void e(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(1);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        d(deviceCommand, ByteBuffer.wrap(new byte[]{1, BaseType.Obj, 103, 112, 115, 108, 111, 99, 97, 116, 105, 111, 110, 46, 100, 97, 116}));
    }

    private static void a(DeviceInfo deviceInfo) {
        synchronized (d) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(28);
            deviceCommand.setCommandID(1);
            jvb jvbVar = jva.b().a().get(deviceInfo.getDeviceIdentify());
            List<String> d2 = jvbVar != null ? jvbVar.d() : null;
            if (d2 != null && d2.size() != 0) {
                deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
                int size = d2.size();
                ArrayList arrayList = new ArrayList(size);
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    byte[] d3 = cvx.d(d2.get(i2));
                    arrayList.add(d3);
                    i += d3.length;
                }
                int i3 = (i + size) - 1;
                byte[] c = cvx.c(i3);
                ByteBuffer allocate = ByteBuffer.allocate(c.length + 1 + i3);
                try {
                    allocate.put((byte) 1);
                    allocate.put(c);
                    for (int i4 = 0; i4 < size; i4++) {
                        allocate.put((byte[]) arrayList.get(i4));
                        if (i4 == size - 1) {
                            break;
                        }
                        allocate.put((byte) 59);
                    }
                } catch (BufferOverflowException unused) {
                    LogUtil.h("FileServiceUtil", "BufferOverflowException");
                }
                d(deviceCommand, allocate);
                return;
            }
            LogUtil.h("FileServiceUtil", "sendEphemerisFile file name is empty.");
        }
    }

    public static void d(int i, int i2, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(i);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put(Byte.MAX_VALUE);
        allocate.put((byte) 4);
        allocate.put(cvx.g(i2));
        d(deviceCommand, allocate);
    }

    public static void d(DeviceCommand deviceCommand, ByteBuffer byteBuffer) {
        if (deviceCommand == null || byteBuffer == null) {
            LogUtil.h("FileServiceUtil", "addToList deviceCommand or byteBuffer is null");
            return;
        }
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        if (!CommonUtil.ag(com.huawei.haf.application.BaseApplication.e())) {
            LogUtil.a("FileServiceUtil", "file trance command : ", deviceCommand.toString());
        }
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
