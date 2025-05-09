package defpackage;

import android.content.Context;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jft extends EngineLogicBaseManager {
    private static volatile jft d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private int f13797a;
    private cwl b;
    private SendCallback c;

    private jft(Context context) {
        super(context);
        this.f13797a = 0;
        this.b = new cwl();
        this.c = new SendCallback() { // from class: jft.2
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                ReleaseLogUtil.e("DEVMGR_HighLandWearEngineManager", "sendCommand errCode:", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.c("HighLandWearEngineManager", "onSendProgress");
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("HighLandWearEngineManager", "mSendCallback onFileTransferReport transferWay: ", str);
            }
        };
        bzo.c().initHiAiEngine();
    }

    public static jft a() {
        jft jftVar;
        synchronized (e) {
            LogUtil.a("HighLandWearEngineManager", "getInstance()");
            if (d == null) {
                d = new jft(BaseApplication.getContext());
            }
            jftVar = d;
        }
        return jftVar;
    }

    public void c(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("DEVMGR_HighLandWearEngineManager", "startIntelligentManager device is disconnected.");
            return;
        }
        this.f13797a = i;
        ReleaseLogUtil.e("DEVMGR_HighLandWearEngineManager", "startIntelligentManager status :", Integer.valueOf(i));
        e(this.f13797a);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        ReleaseLogUtil.e("DEVMGR_HighLandWearEngineManager", "onReceiveDeviceCommand errorCode:", Integer.valueOf(i));
        if (spnVar != null) {
            byte[] b = spnVar.b();
            if (b == null || b.length <= 1) {
                ReleaseLogUtil.d("DEVMGR_HighLandWearEngineManager", "onReceiveDeviceCommand data illegal");
                return;
            } else {
                c(b);
                return;
            }
        }
        ReleaseLogUtil.d("DEVMGR_HighLandWearEngineManager", "onReceiveDeviceCommand Message is null");
    }

    public void c(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            ReleaseLogUtil.d("DEVMGR_HighLandWearEngineManager", "receiveMessageData data illegal");
            return;
        }
        String d2 = cvx.d(bArr);
        LogUtil.a("HighLandWearEngineManager", "receiveMessageData info is ", d2);
        e(d2);
    }

    private void e(String str) {
        try {
            List<cwd> e2 = this.b.a(str).e();
            if (e2 != null && !e2.isEmpty()) {
                int i = 0;
                for (cwd cwdVar : e2) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 0) {
                        i = CommonUtil.h(cvx.e(c));
                    } else {
                        LogUtil.h("HighLandWearEngineManager", "handleSecondLevelMessageTlv type is ", Integer.valueOf(a2));
                    }
                }
                ReleaseLogUtil.e("DEVMGR_HighLandWearEngineManager", "errorCode is:", Integer.valueOf(i));
                return;
            }
            ReleaseLogUtil.d("DEVMGR_HighLandWearEngineManager", "handleBtProxyMessage tlv error");
        } catch (cwg unused) {
            ReleaseLogUtil.c("DEVMGR_HighLandWearEngineManager", "handleBtProxyMessage error");
        }
    }

    private static byte[] a(int i) {
        String str = cvx.b(8L) + cvx.e(0) + cvx.e(1) + cvx.e(i);
        LogUtil.a("HighLandWearEngineManager", "highLandStatusHexTlv is:", str);
        return cvx.a(str);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.HIGH_LAND_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.h("HighLandWearEngineManager", "onDeviceConnectionChange:", connectState);
    }

    private void e(int i) {
        spn.b bVar = new spn.b();
        try {
            bVar.c(a(i));
        } catch (too unused) {
            ReleaseLogUtil.c("DEVMGR_HighLandWearEngineManager", "sendMessage WearEngineException");
        }
        sendComand(bVar.e(), this.c);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.watch.highlandCare.p2p";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
