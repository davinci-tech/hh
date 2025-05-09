package defpackage;

import android.content.Context;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.activity.alipay.BindAlipayProgressActivity;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class obc extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f15593a = new Object();
    private static volatile obc e;
    private spn b;
    private BindAlipayProgressActivity d;

    private obc(Context context) {
        super(context);
    }

    public static obc c() {
        obc obcVar;
        synchronized (f15593a) {
            LogUtil.a("HiWearEngineBindAlipayManager", "getInstance()");
            if (e == null) {
                e = new obc(BaseApplication.getContext());
            }
            obcVar = e;
        }
        return obcVar;
    }

    public void c(BindAlipayProgressActivity bindAlipayProgressActivity) {
        this.d = bindAlipayProgressActivity;
    }

    public void e(PingCallback pingCallback) {
        LogUtil.a("HiWearEngineBindAlipayManager", "pingDevice");
        pingComand(pingCallback, getWearPkgName());
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a("HiWearEngineBindAlipayManager", "onReceiveDeviceCommand errorCode:", Integer.valueOf(i));
        BindAlipayProgressActivity bindAlipayProgressActivity = this.d;
        if (bindAlipayProgressActivity != null) {
            bindAlipayProgressActivity.a(spnVar);
        }
    }

    public void a(spn spnVar) {
        LogUtil.a("HiWearEngineBindAlipayManager", "sendCommandToDevice");
        sendComand(spnVar, new SendCallback() { // from class: obc.1
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("HiWearEngineBindAlipayManager", "sendCommandToDevice errorCode:", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.c("HiWearEngineBindAlipayManager", "sendCommandToDevice progress:", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("HiWearEngineBindAlipayManager", "sendCommandToDevice onFileTransferReport transferWay: ", str);
            }
        });
    }

    public void c(int i) {
        spn.b bVar = new spn.b();
        try {
            bVar.c(a(i));
        } catch (tnx unused) {
            LogUtil.b("HiWearEngineBindAlipayManager", "sendMessage WearEngineException");
        }
        spn e2 = bVar.e();
        this.b = e2;
        a(e2);
    }

    private byte[] a(int i) {
        LogUtil.a("HiWearEngineBindAlipayManager", "createGetBindCodeMsg:", Integer.valueOf(i));
        byte[] bArr = new byte[128];
        byte[] e2 = e(1);
        System.arraycopy(e2, 0, bArr, 0, e2.length);
        int length = e2.length;
        byte[] e3 = e(539034416);
        System.arraycopy(e3, 0, bArr, length, e3.length);
        int length2 = length + e3.length;
        byte[] e4 = e(i);
        System.arraycopy(e4, 0, bArr, length2, e4.length);
        return Arrays.copyOf(bArr, length2 + e4.length);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.BIND_ALIPAY_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.h("HiWearEngineBindAlipayManager", "onDeviceConnectionChange");
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.eg.harmony.watch.AlipayGphone";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "com.eg.harmony.watch.AlipayGphone_BDZmAwy1v1blOaTo4DAwBFZklG1fstXlaPZZLxJ2OJMGEL1FeayJ8NnzAFFGbBP4jkVH96MFJvxrFwV/E6hpwtc=";
    }

    private static byte[] e(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }
}
