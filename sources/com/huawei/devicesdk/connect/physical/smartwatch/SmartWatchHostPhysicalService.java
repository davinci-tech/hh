package com.huawei.devicesdk.connect.physical.smartwatch;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;
import com.huawei.haf.application.BaseApplication;
import defpackage.bif;
import defpackage.snu;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SmartWatchHostPhysicalService extends WearableListenerService {
    private bif e = null;

    @Override // com.google.android.gms.wearable.WearableListenerService, android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.c("SmartWatchHostPhysicalService", " onCreate");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        LogUtil.c("SmartWatchHostPhysicalService", "onStart");
        if (!snu.e().getAuthorizationStatus(BaseApplication.e())) {
            LogUtil.c("SmartWatchHostPhysicalService", "aw start connect, but not authorize, so return");
            return;
        }
        bif a2 = bif.a();
        this.e = a2;
        a2.d();
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("SmartWatchHostPhysicalService", " onDestroy");
    }

    @Override // com.google.android.gms.wearable.WearableListenerService
    public void onConnectedNodes(List<Node> list) {
        super.onConnectedNodes(list);
        LogUtil.c("SmartWatchHostPhysicalService", "onConnectedNodes:" + list.size());
    }

    @Override // com.google.android.gms.wearable.WearableListenerService
    public void onPeerConnected(Node node) {
        super.onPeerConnected(node);
        LogUtil.c("SmartWatchHostPhysicalService", "onPeerConnected:" + node);
        bif bifVar = this.e;
        if (bifVar != null) {
            bifVar.b(node);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService
    public void onPeerDisconnected(Node node) {
        super.onPeerDisconnected(node);
        LogUtil.c("SmartWatchHostPhysicalService", "onPeerDisconnected:" + node);
        bif bifVar = this.e;
        if (bifVar != null) {
            bifVar.a(node);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, com.google.android.gms.wearable.DataApi.DataListener
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        LogUtil.c("SmartWatchHostPhysicalService", "onDataChanged:" + dataEventBuffer);
        bif bifVar = this.e;
        if (bifVar != null) {
            bifVar.a(dataEventBuffer);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, com.google.android.gms.wearable.CapabilityApi.CapabilityListener
    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        LogUtil.c("SmartWatchHostPhysicalService", "onCapabilityChanged:" + capabilityInfo);
        bif bifVar = this.e;
        if (bifVar != null) {
            bifVar.b(capabilityInfo);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, com.google.android.gms.wearable.MessageApi.MessageListener
    public void onMessageReceived(MessageEvent messageEvent) {
        LogUtil.c("SmartWatchHostPhysicalService", "onMessageReceived: " + messageEvent);
        bif bifVar = this.e;
        if (bifVar != null) {
            bifVar.e(messageEvent);
        }
    }

    public static void b(Context context) {
        if (context != null) {
            LogUtil.c("SmartWatchHostPhysicalService", "start service");
            try {
                context.startService(new Intent(context, (Class<?>) SmartWatchHostPhysicalService.class));
            } catch (IllegalStateException unused) {
                LogUtil.e("SmartWatchHostPhysicalService", " start error IllegalStateException");
            }
        }
    }
}
