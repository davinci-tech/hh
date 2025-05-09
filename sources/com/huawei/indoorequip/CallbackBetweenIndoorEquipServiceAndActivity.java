package com.huawei.indoorequip;

import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;

/* loaded from: classes5.dex */
public interface CallbackBetweenIndoorEquipServiceAndActivity {
    void onNewEvent(int i);

    void onNewEvent(int i, QrCodeOrNfcInfo qrCodeOrNfcInfo);

    void onNewEvent(int i, boolean z);
}
