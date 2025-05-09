package com.huawei.phdkit;

import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.datatype.DeviceCommand;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phdkit.DvLiteBinder;
import defpackage.mbo;
import defpackage.tqy;

/* loaded from: classes.dex */
public class DvLiteCoreBinder extends DvLiteBinder.Stub {
    private static final String TAG = "DvLiteCoreBinder";
    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.huawei.phdkit.DvLiteCoreBinder.4
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            mbo.a().a("binderDied");
            mbo.a().d();
        }
    };

    @Override // com.huawei.phdkit.DvLiteBinder
    public boolean startDiscovery(DiscoveryListener discoveryListener) {
        LogUtil.a(TAG, "startDiscovery enter");
        if (discoveryListener == null) {
            LogUtil.h("startDiscovery", "listener is null");
            return false;
        }
        mbo.a().e(discoveryListener);
        return true;
    }

    @Override // com.huawei.phdkit.DvLiteBinder
    public void sendBluetoothData(DvLiteCommand dvLiteCommand) {
        LogUtil.a(TAG, "sendBluetoothData enter");
        if (dvLiteCommand == null) {
            LogUtil.h(TAG, "command is null");
            return;
        }
        if (dvLiteCommand.getDataContents() == null || dvLiteCommand.getDataContents().length == 0) {
            LogUtil.h(TAG, "command getDataContents is null");
            return;
        }
        String d = mbo.a().d(dvLiteCommand.getUdid());
        if (d == null || d.isEmpty()) {
            LogUtil.h(TAG, "identify is null");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(dvLiteCommand.getServiceId());
        deviceCommand.setCommandID(dvLiteCommand.getCommandId());
        LogUtil.a(TAG, "sendBluetoothData getCommandId", Integer.valueOf(dvLiteCommand.getCommandId()));
        byte[] dataContents = dvLiteCommand.getDataContents();
        deviceCommand.setDataLen(dataContents.length);
        deviceCommand.setDataContent(dataContents);
        deviceCommand.setPriority(dvLiteCommand.getPriority());
        deviceCommand.setmIdentify(d);
        tqy.a(deviceCommand);
    }

    @Override // com.huawei.phdkit.DvLiteBinder
    public void connectDevice(String str, DataContentListener dataContentListener) {
        if (dataContentListener == null) {
            LogUtil.h(TAG, "listener is null");
        } else {
            mbo.a().c(str, dataContentListener);
        }
    }

    @Override // com.huawei.phdkit.DvLiteBinder
    public void subscribeDevice(String str, DeviceStateListener deviceStateListener) {
        mbo.a().c(str, deviceStateListener);
        if (deviceStateListener != null) {
            try {
                if (deviceStateListener.asBinder() != null) {
                    deviceStateListener.asBinder().linkToDeath(this.deathRecipient, 0);
                }
            } catch (RemoteException unused) {
                LogUtil.b(TAG, "subscribeDevice discoverDevices remoteException");
            }
        }
    }

    @Override // com.huawei.phdkit.DvLiteBinder
    public void unSubscribeDevice(String str, DeviceStateListener deviceStateListener) {
        if (deviceStateListener != null && deviceStateListener.asBinder() != null) {
            deviceStateListener.asBinder().unlinkToDeath(this.deathRecipient, 0);
        }
        mbo.a().a(str);
    }

    @Override // com.huawei.phdkit.DvLiteBinder
    public String getIdentify(String str) {
        return mbo.a().d(str);
    }

    @Override // com.huawei.phdkit.DvLiteBinder
    public void clearDataListener() {
        LogUtil.a(TAG, "clearDataListener enter");
        mbo.a().d();
    }

    @Override // com.huawei.phdkit.DvLiteBinder
    public void clearOneDataListener(String str) {
        LogUtil.a(TAG, "clearOneDataListener enter");
        mbo.a().e(str);
    }
}
