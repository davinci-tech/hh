package com.huawei.harmonyos.interwork.base.ability;

import android.os.IBinder;
import com.huawei.harmonyos.interwork.base.bundle.ElementName;

/* loaded from: classes3.dex */
public interface IAbilityConnection {
    void onAbilityConnectDone(ElementName elementName, IBinder iBinder, int i);

    void onAbilityDisconnectDone(ElementName elementName, int i);
}
