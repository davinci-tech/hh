package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.datatype.DataDeviceInfo;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.operation.utils.Constants;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jot {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13995a = new Object();
    private static DataDeviceInfo b;
    private static DataDeviceInfo c;
    private static jot e;
    private String h;
    private cwl i = new cwl();
    private Context d = BaseApplication.getContext();

    private jot() {
    }

    public static jot a() {
        jot jotVar;
        synchronized (f13995a) {
            if (e == null) {
                e = new jot();
            }
            jotVar = e;
        }
        return jotVar;
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        b("", iBaseResponseCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void b(String str, IBaseResponseCallback iBaseResponseCallback) {
        ByteBuffer b2;
        DeviceInfo e2;
        Object[] objArr = new Object[4];
        objArr[0] = "getFirmwareVersion() mDataDeviceInfo :  ";
        objArr[1] = c;
        objArr[2] = " callback :";
        objArr[3] = iBaseResponseCallback == 0 ? Constants.NULL : iBaseResponseCallback;
        LogUtil.a("DeviceVersionInfoSetting", objArr);
        DataDeviceInfo dataDeviceInfo = c;
        if (!TextUtils.isEmpty(str) && (e2 = jpu.e("DeviceVersionInfoSetting")) != null && str.equals(e2.getDeviceIdentify())) {
            LogUtil.a("DeviceVersionInfoSetting", "aw70DeviceInfo: ", iyl.d().e(e2.getDeviceIdentify()));
            dataDeviceInfo = b;
        }
        if (dataDeviceInfo != null && (TextUtils.isEmpty(str) || (str.equals(dataDeviceInfo.getDeviceBtMac()) && !TextUtils.isEmpty(dataDeviceInfo.getDeviceModel())))) {
            if (iBaseResponseCallback != 0) {
                iBaseResponseCallback.d(0, dataDeviceInfo);
                return;
            }
            return;
        }
        synchronized (f13995a) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(1);
            deviceCommand.setCommandID(7);
            deviceCommand.setPriority(2);
            if (!TextUtils.isEmpty(str)) {
                deviceCommand.setmIdentify(str);
            }
            if (e() == 0) {
                b2 = ByteBuffer.allocate(2);
                b2.put((byte) 2);
                b2.put((byte) 0);
            } else {
                b2 = b();
            }
            e(deviceCommand, b2, 7, iBaseResponseCallback, true);
        }
    }

    private void e(DeviceCommand deviceCommand, ByteBuffer byteBuffer, int i, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        if (iBaseResponseCallback != null && z) {
            jfh.b(i, iBaseResponseCallback);
        }
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        jfq.c().b(deviceCommand);
    }

    private ByteBuffer b() {
        ByteBuffer allocate = ByteBuffer.allocate(22);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        allocate.put((byte) 2);
        allocate.put((byte) 0);
        allocate.put((byte) 3);
        allocate.put((byte) 0);
        allocate.put((byte) 4);
        allocate.put((byte) 0);
        allocate.put((byte) 5);
        allocate.put((byte) 0);
        allocate.put((byte) 6);
        allocate.put((byte) 0);
        d(allocate);
        return allocate;
    }

    private void d(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) 7);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 8);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 9);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) 10);
        byteBuffer.put((byte) 0);
        byteBuffer.put(BaseType.Float);
        byteBuffer.put((byte) 0);
    }

    public Object b(String str, byte[] bArr) {
        DeviceInfo b2 = jpt.b(str, "DeviceVersionInfoSetting");
        DeviceInfo d = jpu.d("DeviceVersionInfoSetting");
        if (str != null && b2 != null && str.equals(b2.getDeviceIdentify())) {
            return b(bArr, str);
        }
        if (str != null && d != null && str.equals(d.getDeviceIdentify())) {
            return c(bArr, str);
        }
        LogUtil.a("DeviceVersionInfoSetting", "device is invalid");
        return null;
    }

    private Object b(byte[] bArr, String str) {
        Object a2;
        LogUtil.a("DeviceVersionInfoSetting", "Enter caseForNotAw70Device");
        synchronized (f13995a) {
            DataDeviceInfo dataDeviceInfo = new DataDeviceInfo();
            c = dataDeviceInfo;
            dataDeviceInfo.setDeviceOtaPackageName("");
            HwVersionManager.c(this.d).s("");
            if (e() == 0) {
                String str2 = "";
                if (bArr.length > 3 && bArr[2] == Byte.MAX_VALUE) {
                    LogUtil.b("DeviceVersionInfoSetting", "getResult() get V0 device version info timeout.");
                } else {
                    str2 = c(bArr);
                }
                c.setDeviceSoftVersion(str2);
                a2 = c;
            } else {
                a2 = a(bArr, str);
            }
        }
        return a2;
    }

    private String c(byte[] bArr) {
        LogUtil.a("DeviceVersionInfoSetting", "Enter getV0DeviceVersion().");
        if (bArr == null) {
            LogUtil.b("DeviceVersionInfoSetting", "Parameter is incorrect.");
            return "";
        }
        if (14 != bArr.length) {
            LogUtil.b("DeviceVersionInfoSetting", "V0 device version info is incorrect.");
            return "";
        }
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("DeviceVersionInfoSetting", "getDeviceVersion dataInfo is null");
            return "";
        }
        try {
            if (d.length() < 26) {
                LogUtil.b("DeviceVersionInfoSetting", "getDeviceVersion invalid data");
                return "";
            }
            return String.valueOf(Integer.parseInt(d.substring(6, 8), 16) / 100) + String.valueOf(Integer.parseInt(d.substring(8, 10), 16)) + "." + String.format(Locale.ENGLISH, "%02X.", Integer.valueOf(Integer.parseInt(d.substring(10, 14), 16))) + String.format(Locale.ENGLISH, "%02X.", Integer.valueOf(Integer.parseInt(d.substring(14, 18), 16))) + String.format(Locale.ENGLISH, "%02X.", Integer.valueOf(Integer.parseInt(d.substring(18, 22), 16))) + String.format(Locale.ENGLISH, "%02X", Integer.valueOf(Integer.parseInt(d.substring(22, 26), 16)));
        } catch (NumberFormatException e2) {
            LogUtil.a("DeviceVersionInfoSetting", "getDeviceVersion NumberFormatException", e2.getMessage());
            return "";
        }
    }

    private Object a(byte[] bArr, String str) {
        try {
            String d = cvx.d(bArr);
            if (d != null && d.length() >= 4) {
                cwe a2 = this.i.a(d.substring(4, d.length()));
                e(a2, str);
                e(a2);
                return c;
            }
            LogUtil.h("DeviceVersionInfoSetting", "getString() in valid data");
            return null;
        } catch (cwg e2) {
            LogUtil.b("DeviceVersionInfoSetting", "Exception = " + e2.getMessage());
            return null;
        }
    }

    private void e(cwe cweVar, String str) {
        for (cwd cwdVar : cweVar.e()) {
            try {
                switch (Integer.parseInt(cwdVar.e(), 16)) {
                    case 1:
                        c.setBtVersion(cvx.e(cwdVar.c()));
                        continue;
                    case 2:
                        c.setDeviceType(Integer.parseInt(cwdVar.c(), 16));
                        continue;
                    case 3:
                        c.setDeviceVersion(cvx.e(cwdVar.c()));
                        continue;
                    case 4:
                        c.setDevicePhoneNumber(cvx.e(cwdVar.c()));
                        continue;
                    case 5:
                        c.setDeviceBtMac(cvx.e(cwdVar.c()));
                        continue;
                    case 6:
                        c.setDeviceImei(cvx.e(cwdVar.c()));
                        continue;
                    case 7:
                        c.setDeviceSoftVersion(cvx.e(cwdVar.c()));
                        e(cwdVar, str);
                        continue;
                    default:
                        Object[] objArr = new Object[1];
                        objArr[0] = "enter setDeviceBaseAttribute default branch";
                        LogUtil.a("DeviceVersionInfoSetting", objArr);
                        continue;
                }
            } catch (NumberFormatException e2) {
                LogUtil.a("DeviceVersionInfoSetting", "setDeviceBaseAttribute NumberFormatException", e2.getMessage());
            }
            LogUtil.a("DeviceVersionInfoSetting", "setDeviceBaseAttribute NumberFormatException", e2.getMessage());
        }
    }

    private void e(cwd cwdVar, String str) {
        boolean c2 = cwi.c(jpt.b(str, "DeviceVersionInfoSetting"), 58);
        LogUtil.a("DeviceVersionInfoSetting", "setBandDeviceVersion isSupportDetect :", Boolean.valueOf(c2));
        DeviceCapability e2 = cvs.e(str);
        if (c2 || (e2 != null && e2.isOtaUpdate())) {
            HwVersionManager.c(this.d).b(str, cvx.e(cwdVar.c()));
            LogUtil.a("DeviceVersionInfoSetting", "support OTA set device version :", cvx.e(cwdVar.c()));
        }
        if (e2 == null || !e2.isSupportWatchFace()) {
            return;
        }
        HwWatchFaceUtil.b().c(cvx.e(cwdVar.c()));
        LogUtil.a("DeviceVersionInfoSetting", "support WatchFace ,set device version :" + cvx.e(cwdVar.c()));
    }

    private void e(cwe cweVar) {
        for (cwd cwdVar : cweVar.e()) {
            try {
                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                if (parseInt == 15) {
                    c.setDeviceOtaPackageName(cvx.e(cwdVar.c()));
                    LogUtil.a("DeviceVersionInfoSetting", "setDeviceOtaPackageName :", cvx.e(cwdVar.c()));
                } else if (parseInt != 20) {
                    switch (parseInt) {
                        case 8:
                            c.setDeviceOpenSourceVersion(cvx.e(cwdVar.c()));
                            break;
                        case 9:
                            c.setDeviceSn(cvx.e(cwdVar.c()));
                            break;
                        case 10:
                            c.setDeviceModel(cvx.e(cwdVar.c()));
                            this.h = cvx.e(cwdVar.c());
                            break;
                        case 11:
                            c.setDeviceEmmcId(cvx.e(cwdVar.c()));
                            break;
                        default:
                            Object[] objArr = new Object[1];
                            objArr[0] = "enter setDeviceAttribute default branch";
                            LogUtil.a("DeviceVersionInfoSetting", objArr);
                            break;
                    }
                } else {
                    HwVersionManager.c(this.d).s(String.valueOf(Integer.parseInt(cwdVar.c(), 16)));
                    LogUtil.a("DeviceVersionInfoSetting", "setOtaVersionSupport :", Integer.valueOf(Integer.parseInt(cwdVar.c(), 16)));
                }
            } catch (NumberFormatException e2) {
                LogUtil.a("DeviceVersionInfoSetting", "setDeviceAttribute NumberFormatException", e2.getMessage());
            }
        }
    }

    private Object c(byte[] bArr, String str) {
        String d;
        LogUtil.a("DeviceVersionInfoSetting", "enter caseForAw70Device method");
        try {
            b = new DataDeviceInfo();
            d = cvx.d(bArr);
        } catch (cwg e2) {
            LogUtil.b("DeviceVersionInfoSetting", "Exception tlvException:  ", e2.getMessage());
        }
        if (d != null && d.length() >= 4) {
            cwe a2 = this.i.a(d.substring(4, d.length()));
            d(a2);
            a(a2, str);
            return b;
        }
        LogUtil.h("DeviceVersionInfoSetting", "getString() in valid data");
        return null;
    }

    private void d(cwe cweVar) {
        for (cwd cwdVar : cweVar.e()) {
            try {
                switch (Integer.parseInt(cwdVar.e(), 16)) {
                    case 1:
                        b.setBtVersion(cvx.e(cwdVar.c()));
                        continue;
                    case 2:
                        b.setDeviceType(Integer.parseInt(cwdVar.c(), 16));
                        continue;
                    case 3:
                        b.setDeviceVersion(cvx.e(cwdVar.c()));
                        continue;
                    case 4:
                        b.setDevicePhoneNumber(cvx.e(cwdVar.c()));
                        continue;
                    case 5:
                        b.setDeviceBtMac(cvx.e(cwdVar.c()));
                        continue;
                    case 6:
                        b.setDeviceImei(cvx.e(cwdVar.c()));
                        continue;
                    default:
                        Object[] objArr = new Object[1];
                        objArr[0] = "setAw70DeviceBaseAttribute Enter default branch";
                        LogUtil.a("DeviceVersionInfoSetting", objArr);
                        continue;
                }
            } catch (NumberFormatException e2) {
                LogUtil.a("DeviceVersionInfoSetting", "setAw70DeviceBaseAttribute NumberFormatException", e2.getMessage());
            }
            LogUtil.a("DeviceVersionInfoSetting", "setAw70DeviceBaseAttribute NumberFormatException", e2.getMessage());
        }
    }

    private void a(cwe cweVar, String str) {
        for (cwd cwdVar : cweVar.e()) {
            try {
                switch (Integer.parseInt(cwdVar.e(), 16)) {
                    case 7:
                        b.setDeviceSoftVersion(cvx.e(cwdVar.c()));
                        HwVersionManager.c(this.d).b(str, cvx.e(cwdVar.c()));
                        continue;
                    case 8:
                        b.setDeviceOpenSourceVersion(cvx.e(cwdVar.c()));
                        continue;
                    case 9:
                        b.setDeviceSn(cvx.e(cwdVar.c()));
                        continue;
                    case 10:
                        b.setDeviceModel(cvx.e(cwdVar.c()));
                        this.h = cvx.e(cwdVar.c());
                        continue;
                    case 11:
                        b.setDeviceEmmcId(cvx.e(cwdVar.c()));
                        continue;
                    default:
                        Object[] objArr = new Object[1];
                        objArr[0] = "setAw70DeviceAttribute Enter default branch";
                        LogUtil.a("DeviceVersionInfoSetting", objArr);
                        continue;
                }
            } catch (NumberFormatException e2) {
                LogUtil.a("DeviceVersionInfoSetting", "setAw70DeviceAttribute NumberFormatException", e2.getMessage());
            }
            LogUtil.a("DeviceVersionInfoSetting", "setAw70DeviceAttribute NumberFormatException", e2.getMessage());
        }
    }

    private int e() {
        DeviceInfo a2 = jpt.a("DeviceVersionInfoSetting");
        if (a2 != null) {
            return a2.getDeviceProtocol();
        }
        return -1;
    }

    public void c() {
        g();
    }

    private static void g() {
        c = null;
    }

    public void d() {
        h();
    }

    private static void h() {
        b = null;
    }

    public void a(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> a2;
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || (a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "DeviceVersionInfoSetting")) == null) {
            return;
        }
        DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability != null && deviceCapability.isSupportGetFirmwareVersion()) {
            a(new IBaseResponseCallback() { // from class: jor
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.a("DeviceVersionInfoSetting", "autoSendCommend getFirmwareVersion response: " + obj);
                }
            });
        } else {
            LogUtil.b("DeviceVersionInfoSetting", "autoSetUp not Support GetFirmwareVersion");
        }
    }
}
