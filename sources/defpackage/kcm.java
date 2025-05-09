package defpackage;

import android.content.Context;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.baseapi.hiaiengine.HiAiIntelligentSleepApi;
import com.huawei.health.baseapi.hiaiengine.IntelligentResultListener;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class kcm extends EngineLogicBaseManager {
    private static volatile kcm b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private IntelligentResultListener f14286a;
    private cwl d;
    private SendCallback e;

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
    }

    private kcm(Context context) {
        super(context);
        this.d = new cwl();
        this.f14286a = new IntelligentResultListener() { // from class: kcm.2
            @Override // com.huawei.health.baseapi.hiaiengine.IntelligentResultListener
            public void onResult(boolean z, boolean z2, boolean z3, boolean z4, long j) {
                ReleaseLogUtil.e("DEVMGR_HiWearEngineIntelligentManager", "receive intelligent query result ", "isLastUsed :", Boolean.valueOf(z), ", isEye :", Boolean.valueOf(z2), ", isMovement :", Boolean.valueOf(z3), ", isLight :", Boolean.valueOf(z4), ", timeStamp :", Long.valueOf(j));
                if (z || z2 || z3 || z4) {
                    kcm.this.d(1);
                } else {
                    kcm.this.d(2);
                }
                kcm.this.e(z, z2, z3, z4, j);
            }

            @Override // com.huawei.health.baseapi.hiaiengine.IntelligentResultListener
            public void onNotSupportResult() {
                kcm.this.d(0);
            }
        };
        this.e = new SendCallback() { // from class: kcm.5
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                ReleaseLogUtil.e("DEVMGR_HiWearEngineIntelligentManager", "sendCommand errCode:", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("HiWearEngineIntelligentManager", "mSendCallback onFileTransferReport transferWay: ", str);
            }
        };
        bzo.c().initHiAiEngine();
    }

    private static kcm c() {
        kcm kcmVar;
        synchronized (c) {
            LogUtil.a("HiWearEngineIntelligentManager", "getInstance()");
            if (b == null) {
                b = new kcm(BaseApplication.getContext());
            }
            kcmVar = b;
        }
        return kcmVar;
    }

    private static void e() {
        if (b == null) {
            LogUtil.h("HiWearEngineIntelligentManager", "current instance is null");
            return;
        }
        synchronized (c) {
            b = null;
        }
    }

    public static void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "startIntelligentManager device is disconnected.");
        } else if (!d(deviceInfo) && !CommonUtil.av()) {
            ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "startIntelligentManager not SupportCheckMobileLight and emui < 11.0");
            e();
        } else {
            c();
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        ReleaseLogUtil.e("DEVMGR_HiWearEngineIntelligentManager", "onReceiveDeviceCommand errorCode ", Integer.valueOf(i));
        if (spnVar != null) {
            byte[] b2 = spnVar.b();
            if (b2 == null || b2.length <= 1) {
                ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "data illegal");
                return;
            } else {
                b(b2);
                return;
            }
        }
        ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "onReceiveDeviceCommand Message is null");
    }

    public void b(byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "data illegal");
            return;
        }
        String d = cvx.d(bArr);
        LogUtil.a("HiWearEngineIntelligentManager", "receiveMessageData info is ", d);
        d(d);
    }

    private void d(String str) {
        try {
            List<cwd> e = this.d.a(str).e();
            if (e != null && !e.isEmpty()) {
                boolean z = false;
                boolean z2 = false;
                for (cwd cwdVar : e) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c2 = cwdVar.c();
                    if (a2 == 2) {
                        if (!cvx.e(10000).equals(c2)) {
                            LogUtil.a("HiWearEngineIntelligentManager", "device vision = ", c2);
                        }
                    } else if (a2 == 1) {
                        z = true;
                    } else if (a2 != 3) {
                        LogUtil.h("HiWearEngineIntelligentManager", "handleSecondLevelMessageTlv type is ", Integer.valueOf(a2));
                    } else if (CommonUtil.w(c2) == 1) {
                        z2 = true;
                    } else {
                        LogUtil.h("HiWearEngineIntelligentManager", "handleSecondLevelMessageTlv value is ", c2);
                    }
                }
                if (z && z2) {
                    d();
                    return;
                }
                return;
            }
            ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "handleBtProxyMessage tlv error");
        } catch (cwg unused) {
            ReleaseLogUtil.c("DEVMGR_HiWearEngineIntelligentManager", "handleBtProxyMessage error");
        }
    }

    private void d() {
        if (a()) {
            boolean a2 = ScreenUtil.a();
            ReleaseLogUtil.e("DEVMGR_HiWearEngineIntelligentManager", "startHiAiIntelligentSleepApi isScreenOn :", Boolean.valueOf(a2));
            if (a2) {
                this.f14286a.onResult(false, false, false, true, System.currentTimeMillis());
                return;
            } else if (!CommonUtil.av()) {
                this.f14286a.onResult(false, false, false, false, 0L);
                return;
            } else {
                b();
                return;
            }
        }
        if (CommonUtil.av()) {
            b();
        } else {
            ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "startHiAiIntelligentSleepApi is not EMUI11.0");
        }
    }

    private void b() {
        HiAiIntelligentSleepApi hiAiIntelligentSleepApi = bzo.c().getHiAiIntelligentSleepApi();
        if (hiAiIntelligentSleepApi == null) {
            ReleaseLogUtil.d("DEVMGR_HiWearEngineIntelligentManager", "startHiAiIntelligentSleepApi mHiAiIntelligentSleepApi is null");
            this.f14286a.onResult(false, false, false, false, 0L);
            jpr.a("recordSleep");
        } else {
            ReleaseLogUtil.e("DEVMGR_HiWearEngineIntelligentManager", "HiAI plugin exist");
            hiAiIntelligentSleepApi.startQuery(this.f14286a);
        }
    }

    private static boolean d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HiWearEngineIntelligentManager", "isSupportCheckMobileLight deviceInfo is null");
            return false;
        }
        boolean c2 = cwi.c(deviceInfo, MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT);
        ReleaseLogUtil.e("DEVMGR_HiWearEngineIntelligentManager", "isSupportCheckMobileLight: ", Boolean.valueOf(c2));
        return c2;
    }

    private static boolean a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HiWearEngineIntelligentManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("HiWearEngineIntelligentManager", "isSupportCheckMobileLight connectMainDevices is null or empty");
            return false;
        }
        return d(deviceList.get(0));
    }

    private byte[] b(boolean z, boolean z2, boolean z3, boolean z4, long j) {
        String str = cvx.b(1L) + cvx.e(2) + cvx.e(1) + b(z) + cvx.e(3) + cvx.e(1) + b(z2) + cvx.e(4) + cvx.e(1) + b(z4) + cvx.e(5) + cvx.e(4) + cvx.b(j);
        if (a()) {
            str = str + cvx.e(6) + cvx.e(1) + b(z3);
        }
        LogUtil.a("HiWearEngineIntelligentManager", "getPayLoadData mobileIsUseHexTlv :", str);
        return cvx.a(str);
    }

    private String b(boolean z) {
        return cvx.e(z ? 1 : 0);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.INTELLIGENT_MODULE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z, boolean z2, boolean z3, boolean z4, long j) {
        spn.b bVar = new spn.b();
        try {
            bVar.c(b(z, z2, z4, z3, j));
        } catch (too unused) {
            ReleaseLogUtil.c("DEVMGR_HiWearEngineIntelligentManager", "sendMessage WearEngineException");
        }
        sendComand(bVar.e(), this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", Integer.valueOf(i));
        String value = AnalyticsValue.INTELLIGENT_AWARENESS_STATUS_21300037.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LogUtil.a("HiWearEngineIntelligentManager", "BI intelligent phone status click event finish, value: ", value, ", typeMap: ", hashMap.toString());
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.watch.health.p2p";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
