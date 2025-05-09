package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public class jvv {
    private static Handler b;
    protected static final Object c = new Object();

    public static void a() {
        HandlerThread handlerThread = new HandlerThread("FileApplicationUtil");
        handlerThread.start();
        b = new c(handlerThread.getLooper());
    }

    static class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("FileApplicationUtil", "handleMessage message is null.");
                return;
            }
            super.handleMessage(message);
            String str = message.obj instanceof String ? (String) message.obj : null;
            int i = message.what;
            if (i == 1) {
                jvv.e(str);
            } else if (i == 2) {
                LogUtil.a("FileApplicationUtil", "app send file timeout.");
                jvs.c(str, false);
            } else {
                LogUtil.h("FileApplicationUtil", "handleMessage unknown.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str) {
        jwb jwbVar = jvf.c().get(str);
        if (jwbVar == null) {
            LogUtil.h("FileApplicationUtil", "handleSendFile ephemerisUtil is null.");
            return;
        }
        int o = jwbVar.o();
        synchronized (c) {
            int size = jwbVar.i().size();
            LogUtil.a("FileApplicationUtil", "SEND_FILE sendNumIndex :", Integer.valueOf(o), ", and offset :", Integer.valueOf(jwbVar.f()), ", indexList.size() :", Integer.valueOf(size));
            Message obtain = Message.obtain();
            obtain.obj = str;
            obtain.what = 1;
            b.sendMessageDelayed(obtain, jwbVar.n());
            byte[] d = d(jwbVar);
            if (o >= size) {
                LogUtil.h("FileApplicationUtil", "index error.");
                b.removeMessages(1, str);
                return;
            }
            if (d != null && d.length != 0) {
                if (!CommonUtil.ag(BaseApplication.e())) {
                    LogUtil.a("FileApplicationUtil", "byteBuffer.length :", Integer.valueOf(d.length), ", indexList.get(sendNumIndex) :", jwbVar.i().get(o), ", sendNumIndex :", Integer.valueOf(o));
                }
                b(d, str, jwbVar);
                jwbVar.f(o + 1);
                if (jwbVar.o() == jwbVar.i().size()) {
                    b.removeMessages(1, str);
                    jwbVar.f(0);
                }
            } else {
                LogUtil.h("FileApplicationUtil", "SEND_FILE byteBuffer is null or byteBuffer.length is zero, indexList.get(sendNumIndex) :", jwbVar.i().get(o), ", sendNumIndex :", Integer.valueOf(o));
                b.removeMessages(1, str);
            }
        }
    }

    public static void d(DeviceInfo deviceInfo) {
        if (b != null) {
            LogUtil.a("FileApplicationUtil", "ready send FILE_SEND_BUSY_TIMEOUT.");
            b.sendMessageDelayed(bKw_(2, deviceInfo), 90000L);
        } else {
            LogUtil.h("FileApplicationUtil", "sendFileBusyTag sHandler is null.");
        }
    }

    public static void a(DeviceInfo deviceInfo, boolean z) {
        c(deviceInfo, !z);
        LogUtil.a("FileApplicationUtil", "removeOutTimeMsg deviceInfo.");
        if (deviceInfo != null) {
            jvs.c(deviceInfo.getDeviceIdentify(), z);
        }
    }

    public static void c(DeviceInfo deviceInfo, boolean z) {
        if (b != null) {
            LogUtil.a("FileApplicationUtil", "removeOutTimeMsg removeMessages. isSendTimeout: ", Boolean.valueOf(z));
            if (deviceInfo != null) {
                b.removeMessages(2, deviceInfo.getDeviceIdentify());
            }
            if (z) {
                d(deviceInfo);
            }
        }
    }

    private static void b(byte[] bArr, String str, jwb jwbVar) {
        if (jwbVar == null) {
            LogUtil.h("FileApplicationUtil", "sendStarFileData ephemerisUtil is null.");
            return;
        }
        if (jwbVar.i().size() <= jwbVar.o()) {
            LogUtil.h("FileApplicationUtil", "sendStarFileData IndexList is error.");
            return;
        }
        int intValue = jwbVar.i().get(jwbVar.o()).intValue();
        LogUtil.a("FileApplicationUtil", "sendStarFileData sendIndex :", Integer.valueOf(intValue), ", offset :", Integer.valueOf(jwbVar.f()));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(5);
        byte[] f = cvx.f(intValue - jwbVar.f());
        ByteBuffer allocate = ByteBuffer.allocate(f.length + bArr.length);
        allocate.put(f);
        allocate.put(bArr);
        deviceCommand.setmIdentify(str);
        jvw.d(deviceCommand, allocate);
    }

    private static byte[] d(jwb jwbVar) {
        synchronized (c) {
            LogUtil.a("FileApplicationUtil", "getCutData fileInfoList size :", Integer.valueOf(jwbVar.d().size()), ", indexList.size :", Integer.valueOf(jwbVar.i().size()));
            if (jwbVar.o() >= jwbVar.i().size()) {
                return new byte[0];
            }
            int intValue = jwbVar.i().get(jwbVar.o()).intValue();
            if (intValue < jwbVar.d().size()) {
                return jwbVar.d().get(intValue);
            }
            return new byte[0];
        }
    }

    public static void b(String str, String str2, int i, DeviceInfo deviceInfo) {
        String str3;
        if (deviceInfo == null) {
            LogUtil.h("FileApplicationUtil", "responseApplicationData deviceInfo is null.");
            return;
        }
        jwb e = jvs.e(deviceInfo);
        if (e == null) {
            LogUtil.h("FileApplicationUtil", "responseApplicationData ephemerisUtil is null.");
            return;
        }
        LogUtil.a("FileApplicationUtil", "responseApplicationData fileName :", str, ", fileBitmap :", str2, ", offset :", Integer.valueOf(i), ", transSize :", Integer.valueOf(e.l()));
        if (i == 0) {
            e.c(0);
            b(e);
        } else if (e.l() == 0) {
            e.c(0);
        } else {
            e.c(Math.abs(i / e.l()));
        }
        if (e.e() == 0) {
            str3 = jvw.e;
        } else if (e.e() == 1) {
            str3 = jvw.f14140a;
            str = knl.a(deviceInfo.getDeviceIdentify()) + str;
        } else {
            LogUtil.h("FileApplicationUtil", "responseApplicationData file type unknown.");
            str = "";
            str3 = "";
        }
        if (!jwa.d(str3 + str)) {
            jvw.d(4, 100001, deviceInfo);
        } else {
            b(e, deviceInfo, str, str2, i);
        }
    }

    private static void b(jwb jwbVar, DeviceInfo deviceInfo, String str, String str2, int i) {
        LogUtil.a("FileApplicationUtil", "sendFile fileType :", Integer.valueOf(jwbVar.e()));
        if (jwbVar.e() == 0) {
            c(str, i, deviceInfo);
            b(jvw.e + str, jwbVar, deviceInfo);
            return;
        }
        if (jwbVar.e() == 1) {
            b(str, i, deviceInfo);
            LogUtil.a("FileApplicationUtil", "sendFile fileBitmap :", str2);
            if (!"".equals(str2) && !"FF".equals(str2)) {
                b(str2, jwbVar);
                b.removeMessages(1, deviceInfo.getDeviceIdentify());
            } else {
                LogUtil.a("FileApplicationUtil", "sendFile index :", Integer.valueOf(jwbVar.c()));
                if (i != 0 && jwbVar.l() != 0 && jwbVar.h() != 0) {
                    int l = (i / jwbVar.l()) / jwbVar.h();
                    LogUtil.a("FileApplicationUtil", "sendFile index_change :", Integer.valueOf(l));
                    jwbVar.a(l);
                    b(jwbVar);
                }
            }
            b.sendMessageDelayed(bKw_(1, deviceInfo), jwbVar.n());
            return;
        }
        LogUtil.h("FileApplicationUtil", "sendFile unknown.");
    }

    private static Message bKw_(int i, DeviceInfo deviceInfo) {
        Message obtain = Message.obtain();
        obtain.what = i;
        if (deviceInfo != null) {
            obtain.obj = deviceInfo.getDeviceIdentify();
        }
        return obtain;
    }

    private static void b(jwb jwbVar) {
        int i;
        LogUtil.a("FileApplicationUtil", "initIndexList numbers :", Integer.valueOf(jwbVar.c()), " maxDataSize :", Long.valueOf(jwbVar.j()), " transSize :", Integer.valueOf(jwbVar.l()), " offset :", Integer.valueOf(jwbVar.f()), " allSize :", Integer.valueOf(jwbVar.a()), " sendNum :", Integer.valueOf(jwbVar.h()));
        synchronized (c) {
            jwbVar.i().clear();
            if (jwbVar.j() == 0 || jwbVar.l() == 0) {
                i = 1;
            } else {
                int a2 = jwbVar.a() - (jwbVar.l() * jwbVar.f());
                LogUtil.a("FileApplicationUtil", "initIndexList numberLess :", Integer.valueOf(a2), ", (transSize * sendNum) :", Integer.valueOf(jwbVar.l() * jwbVar.h()));
                if (a2 >= jwbVar.l() * jwbVar.h()) {
                    i = (int) (jwbVar.j() / jwbVar.l());
                } else {
                    int l = a2 / jwbVar.l();
                    i = a2 % jwbVar.l() != 0 ? l + 1 : l;
                }
            }
            LogUtil.a("FileApplicationUtil", "initIndexList number :", Integer.valueOf(i));
            for (int i2 = 0; i2 < i; i2++) {
                jwbVar.i().add(Integer.valueOf((jwbVar.h() * jwbVar.c()) + i2));
            }
            LogUtil.a("FileApplicationUtil", "initIndexList indexList.size() :", Integer.valueOf(jwbVar.i().size()));
        }
    }

    private static void b(String str, jwb jwbVar) {
        synchronized (c) {
            jwbVar.i().clear();
            int length = str.length() / 2;
            LogUtil.a("FileApplicationUtil", "getBitmapInfo() number :", Integer.valueOf(length));
            int i = 0;
            while (i < length) {
                int i2 = i * 2;
                String binaryString = Integer.toBinaryString(CommonUtil.w(str.substring(i2, i2 + 2)));
                int i3 = i + 1;
                LogUtil.a("FileApplicationUtil", "getBitmapInfo() index :", Integer.valueOf(i3), ", info :", binaryString);
                String d = d(binaryString);
                LogUtil.a("FileApplicationUtil", "getBitmapInfo() index :", Integer.valueOf(i3), ", info :", d);
                String stringBuffer = new StringBuffer(d).reverse().toString();
                LogUtil.a("FileApplicationUtil", " getBitmapInfo() index :", Integer.valueOf(i3), ", bitmap :", stringBuffer);
                for (int i4 = 0; i4 < stringBuffer.length(); i4++) {
                    if (stringBuffer.charAt(i4) == '0' && i4 >= 0 && i4 <= 7) {
                        jwbVar.i().add(Integer.valueOf(jwbVar.f() + (jwbVar.h() * i) + i4));
                    }
                }
                i = i3;
            }
        }
    }

    private static String d(String str) {
        if (str.length() >= 8) {
            return str;
        }
        String str2 = "";
        for (int i = 0; i < 8 - str.length(); i++) {
            str2 = "0" + str2;
        }
        return str2 + str;
    }

    public static void c(String str, long j, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(4);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        byte[] d = cvx.d(str);
        byte[] c2 = cvx.c(d.length);
        byte[] g = cvx.g((int) j);
        ByteBuffer allocate = ByteBuffer.allocate(c2.length + 7 + d.length);
        allocate.put((byte) 2).put(c2).put(d).put((byte) 3).put((byte) 4).put(g);
        jvw.d(deviceCommand, allocate);
    }

    public static void b(String str, long j, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(28);
        deviceCommand.setCommandID(4);
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        byte[] d = cvx.d(str);
        byte[] c2 = cvx.c(d.length);
        byte[] g = cvx.g((int) j);
        ByteBuffer allocate = ByteBuffer.allocate(c2.length + 13 + d.length);
        allocate.put(new byte[]{Byte.MAX_VALUE, 4, 0, 1, -122, -96}).put((byte) 2).put(c2).put(d).put((byte) 3).put((byte) 4).put(g);
        jvw.d(deviceCommand, allocate);
    }

    public static void b(String str, jwb jwbVar, DeviceInfo deviceInfo) {
        LogUtil.a("FileApplicationUtil", "Enter sendFilePath path :", str);
        if (!jwa.d(str)) {
            LogUtil.h("FileApplicationUtil", "sendFilePath file not exists.");
        } else if ("ANDROID_WEAR".equals(jwbVar.b())) {
            LogUtil.a("FileApplicationUtil", "sendFilePath start sendAwFilePath.");
            jsz.b().b(str, deviceInfo);
        } else {
            LogUtil.h("FileApplicationUtil", "sendFilePath BT 2.0.");
        }
    }
}
