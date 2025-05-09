package com.huawei.uikit.hwrecyclerview.widget;

import android.content.Context;
import com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManager;
import defpackage.sms;

/* loaded from: classes9.dex */
public class HwDeviceAdapter {
    public HwDeviceAdapter() {
    }

    public static HwDeviceAdapter b(Context context) {
        Object e = sms.e(context, sms.e(context, (Class<?>) HwDeviceAdapter.class, sms.c(context, 9, 1)), (Class<?>) HwDeviceAdapter.class);
        if (e instanceof HwDeviceAdapter) {
            return (HwDeviceAdapter) e;
        }
        return null;
    }

    public HwLinearLayoutManager.LayoutCallback c(Context context) {
        return null;
    }

    public HwDeviceAdapter(Context context) {
    }
}
