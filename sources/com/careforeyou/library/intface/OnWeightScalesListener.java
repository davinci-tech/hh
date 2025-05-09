package com.careforeyou.library.intface;

import android.bluetooth.BluetoothGattCharacteristic;
import com.careforeyou.library.bean.CsFatScale;
import defpackage.ng;
import defpackage.nr;

/* loaded from: classes2.dex */
public interface OnWeightScalesListener {
    void bluetoothWriteChannelDone(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void broadcastChipseaData(nr nrVar);

    void matchUserMsg(ng ngVar);

    void specialFatScaleInfo(CsFatScale csFatScale);
}
