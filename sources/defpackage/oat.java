package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitManager;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapP2pMessage;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.maps.offlinedata.health.p2p.receiver.OfflineMapReceiver;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes6.dex */
public class oat extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f15586a = new Object();
    private static volatile oat d;
    private IOfflineMapSendCallback b;
    private SendCallback c;

    private oat() {
        super(BaseApplication.e());
        this.b = new IOfflineMapSendCallback() { // from class: oat.3
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "mOfflineMapDefaultSendCallback sendCommand errCode:", Integer.valueOf(i));
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "mOfflineMapDefaultSendCallback sendCommand process:", Long.valueOf(j));
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "mOfflineMapDefaultSendCallback onFileTransferReport transferWay: ", str);
            }
        };
        this.c = new SendCallback() { // from class: oat.5
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "mDefaultSendCallback sendCommand errCode:", Integer.valueOf(i));
                oat.this.b.onSendResult(i);
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "mDefaultSendCallback sendCommand process:", Long.valueOf(j));
                oat.this.b.onSendProgress(j);
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "mDefaultSendCallback onFileTransferReport transferWay: ", str);
                oat.this.b.onFileTransferReport(str);
            }
        };
    }

    public static oat c() {
        if (d == null) {
            synchronized (f15586a) {
                if (d == null) {
                    d = new oat();
                }
            }
        }
        return d;
    }

    public void d(Context context, String str) {
        OfflineMapInitManager.setLogHealth(new oas());
        OfflineMapWearEngineManager.getInstance().setWearEngine4OfflineMap(context, str, new oaq());
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        ReleaseLogUtil.b("DEVMGR_OfflineMapEngineManager", "onReceiveDeviceCommand errorCode ", Integer.valueOf(i));
        if (spnVar != null) {
            if (spnVar.d() == 2) {
                ReleaseLogUtil.a("DEVMGR_OfflineMapEngineManager", "onReceiveDeviceCommand type is file, pls check.");
                return;
            }
            byte[] b = spnVar.b();
            Object[] objArr = new Object[2];
            objArr[0] = "dataInfo is null ";
            objArr[1] = Boolean.valueOf(b == null);
            LogUtil.a("DEVMGR_OfflineMapEngineManager", objArr);
            OfflineMapP2pMessage.Builder builder = new OfflineMapP2pMessage.Builder();
            builder.setPayload(b);
            OfflineMapReceiver.getInstance().onDataReceived(i, builder.build());
            return;
        }
        LogUtil.b("DEVMGR_OfflineMapEngineManager", "onReceiveDeviceCommand Message is null");
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.OFFLINE_MAP_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        int i;
        if (connectState == ConnectState.CONNECTED) {
            i = 2;
        } else if (connectState == ConnectState.DISCONNECTED) {
            i = 3;
        } else {
            i = connectState == ConnectState.CONNECTING ? 1 : -1;
        }
        ReleaseLogUtil.b("DEVMGR_OfflineMapEngineManager", " statusCode ", Integer.valueOf(i));
        OfflineMapReceiver.getInstance().onConnectStatusChanged(BaseApplication.vZ_(), i);
    }

    public void e(OfflineMapP2pMessage offlineMapP2pMessage) {
        LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendMsgToDevice enter ");
        spn.b bVar = new spn.b();
        bVar.c(offlineMapP2pMessage.getData());
        d(bVar.e(), this.c);
    }

    public void d(spn spnVar, SendCallback sendCallback) {
        sendComand(spnVar, sendCallback);
    }

    public void c(OfflineMapP2pMessage offlineMapP2pMessage, IOfflineMapSendCallback iOfflineMapSendCallback, int i) {
        if (offlineMapP2pMessage == null || iOfflineMapSendCallback == null) {
            ReleaseLogUtil.c("DEVMGR_OfflineMapEngineManager", "msg is null or callback is null.");
            return;
        }
        spn.b bVar = new spn.b();
        byte[] data = offlineMapP2pMessage.getData();
        File file = offlineMapP2pMessage.getFile();
        Object[] objArr = new Object[6];
        objArr[0] = "sendMsgToDevice3 enter msgId ";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = " sendData ";
        objArr[3] = Boolean.valueOf(data != null);
        objArr[4] = " sendFile ";
        objArr[5] = Boolean.valueOf(file != null);
        ReleaseLogUtil.b("DEVMGR_OfflineMapEngineManager", objArr);
        if (data != null) {
            bVar.c(data);
        } else if (file != null) {
            bVar.a(file);
        }
        sendComand(bVar.e(), b(iOfflineMapSendCallback, i));
    }

    private SendCallback b(final IOfflineMapSendCallback iOfflineMapSendCallback, final int i) {
        if (iOfflineMapSendCallback == null) {
            return this.c;
        }
        return new SendCallback() { // from class: oat.2
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i2) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendMsgToDevice onSendResult ", Integer.valueOf(i2), " msgId ", Integer.valueOf(i));
                iOfflineMapSendCallback.onSendResult(i2);
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendMsgToDevice onSendProgress ", Long.valueOf(j), " msgId ", Integer.valueOf(i));
                iOfflineMapSendCallback.onSendProgress(j);
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendMsgToDevice onFileTransferReport ", str, " msgId ", Integer.valueOf(i));
                iOfflineMapSendCallback.onFileTransferReport(str);
            }
        };
    }

    public void e(String str, IOfflineMapSendCallback iOfflineMapSendCallback, int i) {
        ReleaseLogUtil.b("DEVMGR_OfflineMapEngineManager", "sendCancelCommand enter ", Integer.valueOf(i));
        snt sntVar = new snt();
        if (str == null) {
            ReleaseLogUtil.c("DEVMGR_OfflineMapEngineManager", "invalid cancel param!");
            return;
        }
        String[] split = str.split("/");
        sntVar.e(str);
        sntVar.a(split[split.length - 1]);
        sntVar.i(getWearPkgName());
        sntVar.e(getManagerModule());
        sntVar.f(getWearFingerprint());
        cancelCommand(sph.d(WearEngineModule.OFFLINE_MAP_MODULE.getValue()), sntVar, b(iOfflineMapSendCallback, i));
    }

    public void d() {
        if (!a()) {
            ReleaseLogUtil.c("DEVMGR_OfflineMapEngineManager", "sendDevicePullSuccessMsg, device is not connected!");
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(36);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(6);
        allocate.putInt(111);
        allocate.flip();
        ByteBuffer allocate2 = ByteBuffer.allocate(allocate.capacity());
        allocate2.put(allocate);
        allocate2.flip();
        spn.b bVar = new spn.b();
        bVar.c(allocate2.array());
        ReleaseLogUtil.b("DEVMGR_OfflineMapEngineManager", "sendDevicePullSuccessMsg ");
        sendComand(bVar.e(), new SendCallback() { // from class: oat.1
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendDevicePullSuccessMsg onSendResult ", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendDevicePullSuccessMsg onSendProgress ", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendDevicePullSuccessMsg onFileTransferReport ", str);
            }
        });
    }

    public void e(final IOfflineMapPingCallback iOfflineMapPingCallback, String str) {
        pingComand(new PingCallback() { // from class: oar
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public final void onPingResult(int i) {
                oat.e(IOfflineMapPingCallback.this, i);
            }
        }, getWearPkgName());
    }

    static /* synthetic */ void e(IOfflineMapPingCallback iOfflineMapPingCallback, int i) {
        LogUtil.a("DEVMGR_OfflineMapEngineManager", "sendPingToDevices onPingResult ", Integer.valueOf(i));
        iOfflineMapPingCallback.onPingResult(i);
    }

    public boolean a() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "DEVMGR_OfflineMapEngineManager");
        return deviceInfo != null && deviceInfo.getDeviceConnectState() == 2;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return OfflineMapWearEngineManager.WEAR_PACKAGE_NAME;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
