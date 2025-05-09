package com.huawei.ui.thirdpartservice.interactors.callback;

import defpackage.sjy;
import java.util.List;

/* loaded from: classes7.dex */
public interface SupportDeviceResultCallback {
    void obtainBindDeviceList(List<sjy> list);

    default void obtainSupportDeviceList(List<sjy> list) {
    }

    void onError(int i, String str);
}
