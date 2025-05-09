package com.huawei.maps.offlinedata.health.p2p.receiver;

import android.app.Application;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapP2pMessage;
import com.huawei.maps.offlinedata.service.device.c;
import com.huawei.maps.offlinedata.utils.a;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class OfflineMapReceiver implements IOfflineMapConnectStatusListener, IOfflineMapDataReceiver {
    private static final String TAG = "OfflineMapReceiver";
    private static volatile OfflineMapReceiver mInstance;

    private OfflineMapReceiver() {
    }

    public static OfflineMapReceiver getInstance() {
        if (mInstance == null) {
            synchronized (OfflineMapReceiver.class) {
                if (mInstance == null) {
                    mInstance = new OfflineMapReceiver();
                }
            }
        }
        return mInstance;
    }

    @Override // com.huawei.maps.offlinedata.health.p2p.receiver.IOfflineMapDataReceiver
    public void onDataReceived(int i, OfflineMapP2pMessage offlineMapP2pMessage) {
        g.a(TAG, "onDataReceived: " + i);
        c.a().a(i, offlineMapP2pMessage);
    }

    @Override // com.huawei.maps.offlinedata.health.p2p.receiver.IOfflineMapConnectStatusListener
    public void onConnectStatusChanged(Application application, final int i) {
        g.a(TAG, "onConnectStatusChanged: " + i);
        a.a(application);
        new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.health.p2p.receiver.OfflineMapReceiver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                com.huawei.maps.offlinedata.service.device.a.a().a(i);
            }
        }).start();
    }
}
