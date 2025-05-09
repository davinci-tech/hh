package com.huawei.hms.kit.awareness.capture;

import com.huawei.hms.kit.awareness.b.HHI;
import com.huawei.hms.kit.awareness.status.BluetoothStatus;

/* loaded from: classes9.dex */
public class BluetoothStatusResponse extends HHI<BluetoothStatus> {
    @Override // com.huawei.hms.kit.awareness.b.HHI
    public String getErrorMsg() {
        return "getBluetoothStatus failed: ";
    }

    public BluetoothStatus getBluetoothStatus() {
        return getStatus();
    }

    public BluetoothStatusResponse(BluetoothStatus bluetoothStatus) {
        super(bluetoothStatus);
    }
}
