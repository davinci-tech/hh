package com.huawei.hwbtsdk.btmanager.btdeviceservice;

import com.google.android.gms.wearable.Node;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;
import defpackage.izr;
import java.util.List;

/* loaded from: classes5.dex */
public class WearableSwitchAdapter {
    private static WearableSwitchAdapter c;
    private static final Object d = new Object();
    private static final Object e = new Object();

    public interface ISmartWatchFoundCallback {
        void onResponse(List<Node> list);
    }

    private WearableSwitchAdapter() {
    }

    public static WearableSwitchAdapter a() {
        WearableSwitchAdapter wearableSwitchAdapter;
        synchronized (d) {
            if (c == null) {
                c = new WearableSwitchAdapter();
            }
            wearableSwitchAdapter = c;
        }
        return wearableSwitchAdapter;
    }

    public BTDeviceServiceBase d(String str, BtDeviceStateCallback btDeviceStateCallback) {
        return new izr(BaseApplication.e(), str, btDeviceStateCallback);
    }
}
