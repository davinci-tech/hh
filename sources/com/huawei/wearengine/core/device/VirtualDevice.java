package com.huawei.wearengine.core.device;

import android.os.IBinder;
import com.huawei.hwotamanager.ICheckCallback;
import com.huawei.hwotamanager.IDeviceListCallback;
import com.huawei.hwotamanager.IUpgradeCallBack;
import com.huawei.hwotamanager.IUpgradeStatusCallBack;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.device.FoundListener;
import com.huawei.wearengine.device.GetAttributeListener;
import com.huawei.wearengine.monitor.MonitorCallback;
import com.huawei.wearengine.monitor.QueryDataCallback;
import com.huawei.wearengine.monitor.SwitchStatusCallback;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pInnerApi;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pReceiverCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;

/* loaded from: classes9.dex */
public interface VirtualDevice {
    int cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack);

    void disconnectOtaService();

    void doUpgrade(Device device, String str, IUpgradeCallBack iUpgradeCallBack);

    void getAllDeviceList(FoundListener foundListener, String str);

    void getCommonDevice(FoundListener foundListener, String str);

    void getConnectedDevices(IDeviceListCallback iDeviceListCallback);

    void getDeviceAppVersionCode(Device device, String str, String str2, P2pPingCallback p2pPingCallback);

    void getDeviceList(FoundListener foundListener, String str);

    void getDeviceNewVersion(Device device, ICheckCallback iCheckCallback);

    void getHiLinkDeviceId(Device device, GetAttributeListener getAttributeListener);

    void getUpgradeStatus(Device device, IUpgradeStatusCallBack iUpgradeStatusCallBack);

    void initModule();

    boolean isP2pReceiverExist(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2);

    void notifyData(Device device, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback);

    void ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback);

    void queryDeviceCapability(Device device, int i, GetAttributeListener getAttributeListener);

    void queryMonitorData(Device device, String str, QueryDataCallback queryDataCallback);

    void registerUpgradeListener(Device device, IUpgradeStatusCallBack iUpgradeStatusCallBack);

    void releaseModule();

    void send(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback);

    void setBinder(String str, IBinder iBinder);

    void setP2pInnerApi(P2pInnerApi p2pInnerApi);

    int subscribeDeviceDataReceiver(P2pReceiverCallback p2pReceiverCallback);

    int subscribeMonitorListener(MonitorCallback monitorCallback);

    int switchMonitorStatus(int i, String str, String str2, SwitchStatusCallback switchStatusCallback);

    void unRegisterUpgradeListener(Device device);

    int unsubscribeDeviceDataReceiver();

    int unsubscribeMonitorListener();
}
