package com.huawei.hwbtsdk.btmanager.btdeviceservice;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import defpackage.snu;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class BtDeviceAwHostService extends WearableListenerService {
    private BTDeviceAWService d = null;

    @Override // com.google.android.gms.wearable.WearableListenerService, android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "BTDeviceAWHostService onCreate");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "BTDeviceAWHostService onStart");
        if (!snu.e().getAuthorizationStatus(BaseApplication.e())) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "aw start connect, but not authorize, so return");
            return;
        }
        BTDeviceAWService d = BTDeviceAWService.d(this);
        this.d = d;
        d.c(this);
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "BTDeviceAWHostService onDestroy");
    }

    @Override // com.google.android.gms.wearable.WearableListenerService
    public void onConnectedNodes(List<Node> list) {
        super.onConnectedNodes(list);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "onConnectedNodes:", Integer.valueOf(list.size()));
    }

    @Override // com.google.android.gms.wearable.WearableListenerService
    public void onPeerConnected(Node node) {
        super.onPeerConnected(node);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "onPeerConnected:", node);
        BTDeviceAWService bTDeviceAWService = this.d;
        if (bTDeviceAWService != null) {
            bTDeviceAWService.c(node);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService
    public void onPeerDisconnected(Node node) {
        super.onPeerDisconnected(node);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "onPeerDisconnected:", node);
        BTDeviceAWService bTDeviceAWService = this.d;
        if (bTDeviceAWService != null) {
            bTDeviceAWService.e(node);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, com.google.android.gms.wearable.DataApi.DataListener
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BtDeviceAwHostService", "onDataChanged:", dataEventBuffer);
        BTDeviceAWService bTDeviceAWService = this.d;
        if (bTDeviceAWService != null) {
            bTDeviceAWService.onDataChanged(dataEventBuffer);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, com.google.android.gms.wearable.CapabilityApi.CapabilityListener
    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BtDeviceAwHostService", "onCapabilityChanged:", capabilityInfo);
        BTDeviceAWService bTDeviceAWService = this.d;
        if (bTDeviceAWService != null) {
            bTDeviceAWService.onCapabilityChanged(capabilityInfo);
        }
    }

    @Override // com.google.android.gms.wearable.WearableListenerService, com.google.android.gms.wearable.MessageApi.MessageListener
    public void onMessageReceived(MessageEvent messageEvent) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BtDeviceAwHostService", "onMessageReceived: ", messageEvent);
        BTDeviceAWService bTDeviceAWService = this.d;
        if (bTDeviceAWService != null) {
            bTDeviceAWService.onMessageReceived(messageEvent);
        }
    }

    public static void e(Context context) {
        if (context != null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "BTDeviceAWHostService start");
            try {
                context.startService(new Intent(context, (Class<?>) BtDeviceAwHostService.class));
            } catch (Exception e) {
                LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtDeviceAwHostService", "BTDeviceAWHostService error", "Exception", ExceptionUtils.d(e));
            }
        }
    }
}
