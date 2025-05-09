package com.huawei.wearengine.core.device;

import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import java.util.List;

/* loaded from: classes9.dex */
public interface DeviceManagerCaller {
    List<Device> getDeviceList() throws RemoteException;
}
