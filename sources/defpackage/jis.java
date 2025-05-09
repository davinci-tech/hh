package defpackage;

import android.content.Context;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jis extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static final Object b = new Object();
    private static jis c;

    /* renamed from: a, reason: collision with root package name */
    private jfq f13879a;
    private IBaseResponseCallback d;

    private jis(Context context) {
        super(context);
        jfq c2 = jfq.c();
        this.f13879a = c2;
        if (c2 != null) {
            c2.e(12, this);
        } else {
            LogUtil.h("HwDeviceFontManager", "HwDeviceFontManager(), mHwDeviceConfigManager is null");
        }
    }

    public static jis b() {
        jis jisVar;
        synchronized (b) {
            if (c == null) {
                LogUtil.a("HwDeviceFontManager", "getInstance()");
                c = new jis(BaseApplication.getContext());
            }
            jisVar = c;
        }
        return jisVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 12;
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwDeviceFontManager", "registerDataCallback");
        this.d = iBaseResponseCallback;
    }

    public void c() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(12);
        deviceCommand.setCommandID(2);
        String d = cvx.d(0);
        String e = cvx.e(1);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(d);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("HwDeviceFontManager", "getDeviceFontInfo deviceCommand :", deviceCommand.toString());
        this.f13879a.b(deviceCommand);
    }

    private String[] c(byte[] bArr) {
        List<cwd> e;
        LogUtil.a("HwDeviceFontManager", "Enter parseDeviceFontInfo");
        cwe b2 = b(bArr);
        if (b2 == null) {
            return null;
        }
        try {
            e = b2.e();
        } catch (NumberFormatException unused) {
            LogUtil.b("HwDeviceFontManager", "parseDeviceFontInfo NumberFormatException");
        }
        if (e == null) {
            LogUtil.h("HwDeviceFontManager", "parseDeviceFontInfo tlvList is null");
            return null;
        }
        for (cwd cwdVar : e) {
            LogUtil.c("HwDeviceFontManager", "the case is", Integer.valueOf(Integer.parseInt(cwdVar.e(), 16)));
            if (Integer.parseInt(cwdVar.e(), 16) != 1) {
                LogUtil.h("HwDeviceFontManager", "DEVICE_FONT_STURCT TAG :", cwdVar.e(), "value :", cwdVar.c());
            } else {
                try {
                    String e2 = cvx.e(cwdVar.c());
                    LogUtil.c("HwDeviceFontManager", "DEVICE_FONT_STURCT TAG :", cwdVar.e(), "value :", e2);
                    if (e2 == null) {
                        LogUtil.h("HwDeviceFontManager", "language is null");
                        return null;
                    }
                    return e2.split(",");
                } catch (Exception unused2) {
                    LogUtil.b("HwDeviceFontManager", "parseDeviceFontInfo() Exception is null");
                }
            }
        }
        return null;
    }

    private cwe b(byte[] bArr) {
        LogUtil.a("HwDeviceFontManager", "Enter getTlvList");
        if (bArr == null) {
            LogUtil.h("HwDeviceFontManager", "dataContent is null");
            return null;
        }
        String d = cvx.d(bArr);
        if (d.length() <= 4) {
            return null;
        }
        try {
            return new cwl().a(d.substring(4, d.length()));
        } catch (cwg unused) {
            LogUtil.b("HwDeviceFontManager", "resloveWatchStatus TlvException");
            return null;
        }
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (i != 0) {
            LogUtil.h("HwDeviceFontManager", "onDataReceived errorCode is not SUCCESS, errorCode :", Integer.valueOf(i));
            return;
        }
        LogUtil.a("HwDeviceFontManager", "onDataReceived, errorCode :", Integer.valueOf(i), "value :", bArr);
        if (bArr == null) {
            LogUtil.h("HwDeviceFontManager", "onDataReceived, data is null");
            return;
        }
        IBaseResponseCallback iBaseResponseCallback = this.d;
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwDeviceFontManager", "onDataReceived, mBaseResponseCallback is null");
            return;
        }
        if (bArr.length < 1) {
            LogUtil.h("HwDeviceFontManager", "onDataReceived responseData length less than 1");
        } else if (bArr[1] == 2) {
            iBaseResponseCallback.d(2, c(bArr));
        } else {
            LogUtil.h("HwDeviceFontManager", "onDataReceived, default");
        }
    }
}
