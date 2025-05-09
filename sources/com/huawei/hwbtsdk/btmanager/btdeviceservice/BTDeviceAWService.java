package com.huawei.hwbtsdk.btmanager.btdeviceservice;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.SmartWatchCallback;
import com.huawei.hwbtsdk.btmanager.btdeviceservice.WearableSwitchAdapter;
import defpackage.bli;
import defpackage.blt;
import defpackage.ize;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
public class BTDeviceAWService implements DataApi.DataListener, MessageClient.OnMessageReceivedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, CapabilityApi.CapabilityListener {
    private static Map<String, SmartWatchCallback> b = new HashMap(16);
    private static BTDeviceAWService c = null;
    private static int d = 1000;
    private OnGetMessageTask f;
    private GoogleApiClient g;
    private Context h;
    private HandlerThread i;
    private MessageClient l;
    private SmartWatchCallback m;
    private WearableSwitchAdapter.ISmartWatchFoundCallback n;
    private Handler o;
    private Wearable.WearableOptions r;

    /* renamed from: a, reason: collision with root package name */
    private BtDeviceResponseCallback f6180a = null;
    private BtDeviceResponseCallback e = null;
    private int k = 0;
    private int j = 0;

    private String a(String str) {
        return str;
    }

    private BTDeviceAWService(Context context) {
        this.h = null;
        HandlerThread handlerThread = new HandlerThread("BTDeviceAWServiceData");
        this.i = handlerThread;
        this.o = null;
        this.h = context;
        handlerThread.start();
        this.o = new Handler(this.i.getLooper());
        Wearable.WearableOptions build = new Wearable.WearableOptions.Builder().setLooper(this.i.getLooper()).build();
        this.r = build;
        this.l = Wearable.getMessageClient(this.h, build);
    }

    public static BTDeviceAWService d(Context context) {
        BTDeviceAWService bTDeviceAWService;
        synchronized (BTDeviceAWService.class) {
            if (c == null) {
                c = new BTDeviceAWService(context);
            }
            bTDeviceAWService = c;
        }
        return bTDeviceAWService;
    }

    public Map<String, SmartWatchCallback> d() {
        return b;
    }

    public void d(SmartWatchCallback smartWatchCallback) {
        this.m = smartWatchCallback;
    }

    public void c(Context context) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "Enter gmsConnect.");
        GoogleApiClient googleApiClient = this.g;
        if (googleApiClient != null) {
            googleApiClient.connect();
            return;
        }
        GoogleApiClient build = new GoogleApiClient.Builder(context).addApi(Wearable.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).setHandler(this.o).build();
        this.g = build;
        build.connect();
    }

    public GoogleApiClient b() {
        return this.g;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        LogUtil.c("DEVMGR_SETTING", "BTDeviceAWService", "onConnected");
        for (Map.Entry<String, SmartWatchCallback> entry : d().entrySet()) {
            SmartWatchCallback value = entry.getValue();
            if (value != null) {
                value.onDeviceConnectionStateChanged(entry.getKey(), 2);
            }
        }
        e();
    }

    @Override // com.google.android.gms.wearable.CapabilityApi.CapabilityListener
    public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        if (this.h == null) {
            return;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onCapabilityChanged:" + capabilityInfo);
        Set<Node> nodes = capabilityInfo.getNodes();
        if (nodes == null || nodes.size() == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onCapabilityChanged nodeSet is null ");
            for (Map.Entry<String, SmartWatchCallback> entry : d().entrySet()) {
                if (entry != null && entry.getKey() != null && d().get(entry.getKey()) != null) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onCapabilityChanged callback");
                    d().get(entry.getKey()).onDeviceConnectionStateChanged(entry.getKey(), 3);
                }
            }
            return;
        }
        Iterator<Map.Entry<String, SmartWatchCallback>> it = d().entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            SmartWatchCallback smartWatchCallback = d().get(key);
            if (smartWatchCallback != null) {
                boolean z = false;
                for (Node node : nodes) {
                    String b2 = b(node);
                    if (b2.equals(key)) {
                        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onCapabilityChanged onPeerConnected,", key);
                        smartWatchCallback.onDeviceConnectionStateChanged(b2, 2);
                        b(node, 2);
                        z = true;
                    }
                }
                if (!z) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onCapabilityChanged onPeerDisConnected,", key);
                    smartWatchCallback.onDeviceConnectionStateChanged(key, 3);
                }
            }
        }
    }

    private String b(Node node) {
        return node.getId();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        LogUtil.c("DEVMGR_SETTING", "BTDeviceAWService", " onConnectionSuspended index=", Integer.valueOf(i));
    }

    @Override // com.google.android.gms.wearable.DataApi.DataListener
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        ArrayList freezeIterable = FreezableUtils.freezeIterable(dataEventBuffer);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "onDataChanged, rev_path = ", "/wear_request_data", ", key = ", "wear_request_data");
        b(freezeIterable, "wear_request_data");
    }

    private void b(List<DataEvent> list, String str) {
        for (DataEvent dataEvent : list) {
            if (dataEvent.getType() == 1) {
                String path = dataEvent.getDataItem().getUri().getPath();
                if (path == null) {
                    return;
                }
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "onDataChanged, watch path = ", path);
                if (d(str, path, DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap())) {
                    return;
                }
            } else if (dataEvent.getType() == 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "onDataChanged() DataItem Deleted: " + dataEvent.getDataItem().toString());
            } else {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onDataChanged(), no Support ");
            }
        }
    }

    private boolean d(String str, String str2, DataMap dataMap) {
        if (str2.startsWith("/wear_request_data")) {
            return e(str, dataMap);
        }
        if ("/wear".equals(str2)) {
            if (dataMap != null) {
                a(dataMap);
                return false;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onDataChanged(), RECEIVE_PATH dataMap is null");
            return false;
        }
        if (!"/wear_request_asset".equals(str2)) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onDataChanged(), no Support ");
            return false;
        }
        if (dataMap != null) {
            d(dataMap);
            return false;
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onDataChanged(), PATH_WEAR_REQUEST_ASSET dataMap is null");
        return false;
    }

    private boolean e(String str, DataMap dataMap) {
        if (dataMap != null) {
            return d(str, dataMap);
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onDataChanged(), dataMap is null");
        return false;
    }

    private void d(DataMap dataMap) {
        Asset asset = dataMap.getAsset("wear_request_asset");
        if (asset != null) {
            try {
                byte[] e = e(asset);
                blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, e, 0, "BTDeviceAWService", "Asset Device-->SDK:");
                BtDeviceResponseCallback btDeviceResponseCallback = this.f6180a;
                if (btDeviceResponseCallback != null) {
                    btDeviceResponseCallback.onResponse(0, e);
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "onDataChanged() asset handle success");
                } else {
                    LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onDataChanged(), mBTDeviceFileCallback is null");
                }
                if (this.e != null) {
                    String string = dataMap.getString("wear_request_asset_name");
                    if (!TextUtils.isEmpty(string)) {
                        try {
                            c(e, this.h.getFilesDir().getCanonicalPath() + File.separator + "watchfaceAsset" + File.separator + string);
                            return;
                        } catch (IOException unused) {
                            LogUtil.e("BTDeviceAWService", "assetFile IOException");
                            return;
                        }
                    }
                    LogUtil.c("BTDeviceAWService", "can not report assetFile, name is null");
                    return;
                }
                return;
            } catch (IllegalArgumentException unused2) {
                LogUtil.e("BTDeviceAWService", "checkAsset IllegalArgumentException");
                return;
            }
        }
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onDataChanged(), asset is null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(byte[] r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceAWService.c(byte[], java.lang.String):void");
    }

    private void a(DataMap dataMap) {
        byte[] byteArray = dataMap.getByteArray("byte");
        if (byteArray != null) {
            blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, byteArray, 0, "BTDeviceAWService", "Device-->SDK:");
        } else {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "checkByteTwo onDataChanged(), bytes is null");
        }
        for (Map.Entry<String, SmartWatchCallback> entry : d().entrySet()) {
            SmartWatchCallback value = entry.getValue();
            if (value != null && byteArray != null) {
                value.onDataReceived(entry.getKey(), byteArray.length, byteArray);
            }
        }
    }

    private boolean d(String str, DataMap dataMap) {
        byte[] byteArray = dataMap.getByteArray(str);
        if (byteArray != null) {
            blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, byteArray, 0, "BTDeviceAWService", "Device-->SDK:");
        } else {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "checkByteOne onDataChanged(), bytes is null");
        }
        for (Map.Entry<String, SmartWatchCallback> entry : d().entrySet()) {
            SmartWatchCallback value = entry.getValue();
            if (value != null && byteArray != null) {
                value.onDataReceived(entry.getKey(), byteArray.length, byteArray);
            }
        }
        return false;
    }

    public void c(PutDataMapRequest putDataMapRequest) {
        PutDataRequest asPutDataRequest = putDataMapRequest.asPutDataRequest();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "Generating DataItem: ", asPutDataRequest, " isConnected : ", Boolean.valueOf(this.g.isConnected()));
        if (!this.g.isConnected()) {
            this.g.reconnect();
        } else {
            asPutDataRequest.setUrgent();
            Wearable.DataApi.putDataItem(this.g, asPutDataRequest).setResultCallback(new ResultCallback<DataApi.DataItemResult>() { // from class: com.huawei.hwbtsdk.btmanager.btdeviceservice.BTDeviceAWService.2
                @Override // com.google.android.gms.common.api.ResultCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onResult(DataApi.DataItemResult dataItemResult) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "syncDataItem() Sending image was successful:" + dataItemResult.getStatus().isSuccess());
                }
            });
        }
    }

    public boolean d(String str, byte[] bArr) {
        blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, bArr, 0, "BTDeviceAWService", "sendBTDeviceData SDK-->Device : ");
        int i = this.k;
        String str2 = "/mobile_response_data";
        if (i > 0) {
            int i2 = this.j;
            if (i2 > 0 && i2 < i + 1) {
                str2 = "/mobile_response_data" + Integer.toString(this.j);
            } else {
                this.j = 0;
            }
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "mCurrentChannel = ", Integer.valueOf(this.j));
            this.j++;
        }
        String str3 = str2;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "sendBTDeviceData,path = ", str3, ", key = ", "mobile_response_data");
        PutDataMapRequest create = PutDataMapRequest.create(str3);
        if (bArr != null && bArr.length > 0) {
            ReleaseLogUtil.b("R_BTDeviceAWService", "wearOs send data is not empty.");
        }
        create.getDataMap().putByteArray("mobile_response_data", bArr);
        create.getDataMap().putLong("current_time", System.currentTimeMillis());
        if (str != null) {
            create.getDataMap().putString("NODE", str);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", " NODE = ", str);
        } else {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "sendBTDeviceData NODE = null");
        }
        if (!e(this.l.sendMessage(str, str3, bArr))) {
            c(create);
        }
        return true;
    }

    private boolean e(Task<Integer> task) {
        try {
            LogUtil.c("BTDeviceAWService", "send isSuccess successful isSuccess ID is ", Integer.valueOf(((Integer) Tasks.await(task, d, TimeUnit.MILLISECONDS)).intValue()));
            return true;
        } catch (InterruptedException e) {
            LogUtil.e("BTDeviceAWService", "sendMessage ExecutionException, ", e.getMessage());
            return false;
        } catch (ExecutionException e2) {
            LogUtil.e("BTDeviceAWService", "sendMessage ExecutionException, ", e2.getMessage());
            return false;
        } catch (TimeoutException e3) {
            LogUtil.e("BTDeviceAWService", "sendMessage TimeoutException, ", e3.getMessage());
            return false;
        } catch (Exception unused) {
            LogUtil.e("BTDeviceAWService", "sendMessage gms Exception");
            return false;
        }
    }

    private void e() {
        Collection<Node> c2 = c();
        ArrayList arrayList = new ArrayList(16);
        if (c2 != null) {
            for (Node node : c2) {
                arrayList.add(node);
                b(node, 2);
            }
        }
        WearableSwitchAdapter.ISmartWatchFoundCallback iSmartWatchFoundCallback = this.n;
        if (iSmartWatchFoundCallback != null) {
            iSmartWatchFoundCallback.onResponse(arrayList);
        }
    }

    private void b(Node node, int i) {
        if (this.m == null || TextUtils.isEmpty(a(node))) {
            return;
        }
        this.m.onDeviceConnectionStateChanged(node.getId(), i);
    }

    public String a(Node node) {
        return (node.getDisplayName().startsWith("Blacktip") || node.getDisplayName().startsWith("CSN-Temp")) ? "" : node.getId();
    }

    public Collection<Node> c() {
        ExecutionException e;
        List<Node> list;
        InterruptedException e2;
        List list2 = null;
        try {
            list = (List) Tasks.await(Wearable.getNodeClient(BaseApplication.e(), this.r).getConnectedNodes(), d, TimeUnit.MILLISECONDS);
            try {
                for (Node node : list) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "getNodes() already found watch：", node.getDisplayName(), ",ID = ", node.getId());
                }
                return list;
            } catch (InterruptedException e3) {
                e2 = e3;
                LogUtil.e("BTDeviceAWService", "get nodes ExecutionException, ", e2.getMessage());
                return list;
            } catch (ExecutionException e4) {
                e = e4;
                LogUtil.e("BTDeviceAWService", "get nodes ExecutionException, ", e.getMessage());
                return list;
            } catch (TimeoutException e5) {
                e = e5;
                list2 = list;
                LogUtil.e("BTDeviceAWService", "get nodes TimeoutException, ", e.getMessage());
                return list2;
            }
        } catch (InterruptedException e6) {
            e2 = e6;
            list = null;
        } catch (ExecutionException e7) {
            e = e7;
            list = null;
        } catch (TimeoutException e8) {
            e = e8;
        }
    }

    @Override // com.google.android.gms.wearable.MessageClient.OnMessageReceivedListener, com.google.android.gms.wearable.MessageApi.MessageListener
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent == null || messageEvent.getPath() == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onMessageReceived messageEvent or path is null");
            return;
        }
        String sourceNodeId = messageEvent.getSourceNodeId();
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "Enter onMessageReceived() RequestId = ", Integer.valueOf(messageEvent.getRequestId()), "Path = ", messageEvent.getPath(), ";nodeId:", sourceNodeId);
        String path = messageEvent.getPath();
        OnGetMessageTask onGetMessageTask = this.f;
        if (onGetMessageTask != null) {
            onGetMessageTask.execute(path);
        }
        if ("/wear_request_message".equalsIgnoreCase(path)) {
            byte[] data = messageEvent.getData();
            blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, data, 0, "BTDeviceAWService", "onMessageReceived message bytes = ");
            if (data.length > 6 && data[4] == 1 && data[5] == 21) {
                a();
            }
        }
        if (path.startsWith("/wear_request_data")) {
            byte[] data2 = messageEvent.getData();
            if (data2 != null) {
                blt.d(HiAnalyticsConstant.KeyAndValue.NUMBER_01, data2, 0, "BTDeviceAWService", "MessageAPI Device-->SDK: ");
                SmartWatchCallback smartWatchCallback = d().get(a(sourceNodeId));
                if (smartWatchCallback != null) {
                    smartWatchCallback.onDataReceived(a(sourceNodeId), data2.length, data2);
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onMessageReceived() handle success");
                    return;
                } else {
                    LogUtil.a("0xA0200009", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onMessageReceived(), mBTDeviceStateCallback is null");
                    return;
                }
            }
            LogUtil.a("0xA0200009", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "onMessageReceived(), bytes is null");
        }
    }

    public void a() {
        PackageInfo packageInfo;
        try {
            packageInfo = this.h.getPackageManager().getPackageInfo("com.huawei.bone", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "NameNotFoundException e= ", "PackageManager.NameNotFoundException");
            packageInfo = null;
        }
        if (packageInfo == null) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "packageInfo is null");
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageInfo.packageName);
        ResolveInfo next = this.h.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        if (next != null) {
            String str = next.activityInfo.packageName;
            String str2 = next.activityInfo.name;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.addFlags(268435456);
            intent2.setComponent(new ComponentName(str, str2));
            try {
                this.h.startActivity(intent2);
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "startAPP ,startActivity intent = ", intent2);
            } catch (ActivityNotFoundException unused2) {
                LogUtil.e("startJumpActivity not find JumpActivity", new Object[0]);
            }
        }
    }

    public static abstract class OnGetMessageTask extends AsyncTask<String, Void, String> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public abstract void onPostExecute(String str);

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            return strArr[0];
        }
    }

    public void c(Node node) {
        LogUtil.c("DEVMGR_SETTING", "BTDeviceAWService", "onPeerConnected, connected watch: ", node.getDisplayName(), ",ID = ", node.getId());
        String b2 = b(node);
        SmartWatchCallback smartWatchCallback = d().get(b2);
        if (smartWatchCallback != null) {
            smartWatchCallback.onDeviceConnectionStateChanged(b2, 2);
        }
        b(node, 2);
        if (this.g.isConnected()) {
            return;
        }
        this.g.reconnect();
    }

    public void e(Node node) {
        LogUtil.c("DEVMGR_SETTING", "BTDeviceAWService", "aw device disconnected with displayName = ", node.getDisplayName(), " id = ", node.getId());
        String b2 = b(node);
        SmartWatchCallback smartWatchCallback = d().get(b2);
        if (smartWatchCallback != null) {
            LogUtil.c("DEVMGR_SETTING", "BTDeviceAWService", " onPeerDisconnected lost");
            ize.e(1003001);
            smartWatchCallback.onDeviceConnectionStateChanged(b2, 3);
        }
        b(node, 3);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult != null) {
            LogUtil.c("DEVMGR_SETTING", "BTDeviceAWService", "onConnectionFailed errorCode :", Integer.valueOf(connectionResult.getErrorCode()));
            ize.e(connectionResult.getErrorCode() + 1000000);
        }
        for (Map.Entry<String, SmartWatchCallback> entry : d().entrySet()) {
            SmartWatchCallback value = entry.getValue();
            if (value != null) {
                value.onDeviceConnectionStateChanged(entry.getKey(), 3);
            }
        }
    }

    public void a(String str, String str2) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "sendBTDeviceAssetData,path = ", "/mobile_response_asset", " ; key : ", "mobile_response_asset", " assetPath = ", str2);
        byte[] b2 = bli.b(str2);
        PutDataMapRequest create = PutDataMapRequest.create("/mobile_response_asset");
        create.getDataMap().putAsset("mobile_response_asset", d(b2));
        create.getDataMap().putString("mobile_response_asset_name", str2);
        create.getDataMap().putLong("current_time", System.currentTimeMillis());
        if (str != null) {
            create.getDataMap().putString("NODE", str);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "NODE = ", str);
        } else {
            LogUtil.a("0xA0200009", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "sendBTFilePath NODE = null");
        }
        c(create);
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceAWService", "SDK-->Device : Asset : ", create);
    }

    private static Asset d(byte[] bArr) {
        return Asset.createFromBytes(bArr);
    }

    public void a(BtDeviceResponseCallback btDeviceResponseCallback) {
        this.f6180a = btDeviceResponseCallback;
    }

    public void b(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("BTDeviceAWService", " etFileCallback :", btDeviceResponseCallback);
        this.e = btDeviceResponseCallback;
    }

    public byte[] e(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Asset must be non-null");
        }
        InputStream inputStream = Wearable.DataApi.getFdForAsset(this.g, asset).await(5L, TimeUnit.SECONDS).getInputStream();
        if (inputStream == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "transToByteArrayFromAsset,Requested an unknown Asset.");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                int available = inputStream.available();
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "transToByteArrayFromAsset() length = ", Integer.valueOf(available));
                if (available == 0) {
                    return null;
                }
                byte[] bArr = new byte[available];
                while (true) {
                    int read = inputStream.read(bArr, 0, available);
                    if (read == -1) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        try {
                            inputStream.close();
                            byteArrayOutputStream.close();
                            return byteArray;
                        } catch (IOException unused) {
                            LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "transToByteArrayFromAsset ,Stream close err");
                            return byteArray;
                        }
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (IOException unused2) {
                LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "output stream write error");
                try {
                    inputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException unused3) {
                    LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "transToByteArrayFromAsset ,Stream close err");
                }
                return null;
            }
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException unused4) {
                LogUtil.e("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "transToByteArrayFromAsset ,Stream close err");
            }
        }
    }

    public void a(int i) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceAWService", "Enter setPathExtendNum with pathExtendNum = ", Integer.valueOf(i));
        this.k = i;
    }
}
