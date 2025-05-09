package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
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
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes3.dex */
public class bif implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f385a = new Object();
    private static bif d = null;
    private static int e = 1000;
    private BtDeviceResponseCallback b;
    private BtDeviceResponseCallback c;
    private HandlerThread g;
    private int h;
    private GoogleApiClient i;
    private DeviceInfo j;
    private MessageReceiveCallback k;
    private MessageClient l;
    private Handler n;
    private String o;
    private DeviceStatusChangeCallback q;
    private Wearable.WearableOptions r;
    private int m = 0;
    private int f = 0;

    private bif() {
        HandlerThread handlerThread = new HandlerThread("SmartWatchPhysicalService");
        this.g = handlerThread;
        this.n = null;
        this.b = null;
        this.c = null;
        handlerThread.start();
        this.n = new Handler(this.g.getLooper());
        this.r = new Wearable.WearableOptions.Builder().setLooper(this.g.getLooper()).build();
        this.l = Wearable.getMessageClient(BaseApplication.e(), this.r);
    }

    public static bif a() {
        bif bifVar;
        synchronized (bif.class) {
            if (d == null) {
                d = new bif();
            }
            bifVar = d;
        }
        return bifVar;
    }

    public void d() {
        LogUtil.c("SmartWatchPhysicalService", "Enter gmsConnect.");
        GoogleApiClient googleApiClient = this.i;
        if (googleApiClient != null) {
            googleApiClient.connect();
            return;
        }
        GoogleApiClient build = new GoogleApiClient.Builder(BaseApplication.e()).addApi(Wearable.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).setHandler(this.n).build();
        this.i = build;
        build.connect();
    }

    public void b(Node node) {
        if (node == null) {
            LogUtil.e("SmartWatchPhysicalService", "onPeerConnected node is null");
            return;
        }
        LogUtil.c("SmartWatchPhysicalService", "onPeerConnected, connected watch: ", node.getDisplayName(), ",ID = ", node.getId());
        b(node, 2, 100000);
        if (this.i.isConnected()) {
            return;
        }
        this.i.reconnect();
    }

    public void a(Node node) {
        if (node == null) {
            LogUtil.e("SmartWatchPhysicalService", "onPeerDisconnected node is null");
        } else {
            LogUtil.c("SmartWatchPhysicalService", "onPeerDisconnected:", "aw device disconnected with displayName = ", node.getDisplayName(), " id = ", node.getId());
            b(node, 0, 100000);
        }
    }

    public void a(DataEventBuffer dataEventBuffer) {
        LogUtil.c("SmartWatchPhysicalService", "onDataChanged:" + dataEventBuffer);
        ArrayList freezeIterable = FreezableUtils.freezeIterable(dataEventBuffer);
        LogUtil.c("SmartWatchPhysicalService", "onDataChanged, rev_path = ", "/wear_request_data", ", key = ", "wear_request_data");
        c(freezeIterable);
    }

    public void b(CapabilityInfo capabilityInfo) {
        if (capabilityInfo == null) {
            LogUtil.c("SmartWatchPhysicalService", "onCapabilityChanged, capabilityInfo is null");
            return;
        }
        LogUtil.c("SmartWatchPhysicalService", "onCapabilityChanged:", capabilityInfo);
        Set<Node> nodes = capabilityInfo.getNodes();
        if (nodes == null || nodes.size() == 0) {
            LogUtil.c("SmartWatchPhysicalService", "onCapabilityChanged nodeSet is null ");
            DeviceStatusChangeCallback deviceStatusChangeCallback = this.q;
            if (deviceStatusChangeCallback != null) {
                deviceStatusChangeCallback.onConnectStatusChanged(this.j, 0, bln.e(2, 303));
                return;
            }
            return;
        }
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            b(it.next(), 2, 100000);
        }
    }

    public void e(MessageEvent messageEvent) {
        LogUtil.c("SmartWatchPhysicalService", "onMessageReceived: ", messageEvent);
        if (messageEvent == null || messageEvent.getPath() == null) {
            LogUtil.a("SmartWatchPhysicalService", "onMessageReceived messageEvent or path is null");
            return;
        }
        LogUtil.c("SmartWatchPhysicalService", "Enter onMessageReceived() RequestId = ", Integer.valueOf(messageEvent.getRequestId()), "Path = ", messageEvent.getPath(), ";nodeId:", messageEvent.getSourceNodeId());
        String path = messageEvent.getPath();
        if ("/wear_request_message".equalsIgnoreCase(path)) {
            byte[] data = messageEvent.getData();
            LogUtil.c("SmartWatchPhysicalService", "onMessageReceived message bytes = ", blq.d(data));
            if (data.length > 6 && data[4] == 1 && data[5] == 21) {
                j();
            }
        }
        if (path.startsWith("/wear_request_data")) {
            byte[] data2 = messageEvent.getData();
            if (data2 != null) {
                blt.d("SmartWatchPhysicalService", data2, "MessageAPI Device-->SDK: ");
                if (this.k != null && this.j != null) {
                    b(data2, 0);
                    LogUtil.c("SmartWatchPhysicalService", "onMessageReceived() handle success");
                    return;
                } else {
                    LogUtil.e("SmartWatchPhysicalService", "onMessageReceived(), mBTDeviceStateCallback is null");
                    return;
                }
            }
            LogUtil.a("SmartWatchPhysicalService", "onMessageReceived(), bytes is null");
        }
    }

    private void c(List<DataEvent> list) {
        for (DataEvent dataEvent : list) {
            if (dataEvent.getType() == 1) {
                String path = dataEvent.getDataItem().getUri().getPath();
                LogUtil.c("SmartWatchPhysicalService", "onDataChanged, watch path = ", path);
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                if (path != null && path.startsWith("/wear_request_data")) {
                    b(dataMap, "wear_request_data");
                } else if ("/wear".equals(path)) {
                    b(dataMap, "byte");
                } else if ("/wear_request_asset".equals(path)) {
                    b(dataMap);
                } else {
                    LogUtil.c("SmartWatchPhysicalService", "onDataChanged(), no Support ");
                }
            } else if (dataEvent.getType() == 2) {
                LogUtil.c("SmartWatchPhysicalService", "onDataChanged() DataItem Deleted: ", dataEvent.getDataItem().toString());
            } else {
                LogUtil.a("SmartWatchPhysicalService", "onDataChanged(), no Support ");
            }
        }
    }

    private void b(DataMap dataMap, String str) {
        if (dataMap != null) {
            byte[] byteArray = dataMap.getByteArray(str);
            if (byteArray != null) {
                blt.d("SmartWatchPhysicalService", byteArray, "Device-->SDK: ");
                b(byteArray, 100000);
                return;
            } else {
                LogUtil.a("SmartWatchPhysicalService", "checkByteOne onDataChanged(), bytes is null");
                return;
            }
        }
        LogUtil.a("SmartWatchPhysicalService", "onDataChanged(), dataMap is null");
    }

    private void b(DataMap dataMap) {
        if (dataMap == null) {
            LogUtil.c("SmartWatchPhysicalService", "onDataChanged(), PATH_WEAR_REQUEST_ASSET dataMap is null");
            return;
        }
        Asset asset = dataMap.getAsset("wear_request_asset");
        if (asset != null) {
            try {
                byte[] a2 = a(asset);
                blt.d("SmartWatchPhysicalService", a2, "Asset Device-->SDK: ");
                BtDeviceResponseCallback btDeviceResponseCallback = this.b;
                if (btDeviceResponseCallback != null) {
                    btDeviceResponseCallback.onResponse(0, a2);
                    LogUtil.c("SmartWatchPhysicalService", "onDataChanged() asset handle success");
                } else {
                    LogUtil.a("SmartWatchPhysicalService", "onDataChanged(), mBTDeviceFileCallback is null");
                }
                if (this.c != null) {
                    String string = dataMap.getString("wear_request_asset_name");
                    if (!TextUtils.isEmpty(string)) {
                        try {
                            String str = BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + "watchfaceAsset" + File.separator + string;
                            if (a2 != null) {
                                e(a2, str);
                                return;
                            }
                            return;
                        } catch (IOException unused) {
                            LogUtil.e("SmartWatchPhysicalService", "assetFile IOException");
                            return;
                        }
                    }
                    LogUtil.a("SmartWatchPhysicalService", "can not report assetFile, name is null");
                }
            } catch (IllegalArgumentException unused2) {
                LogUtil.e("SmartWatchPhysicalService", "checkAsset IllegalArgumentException");
            }
        }
    }

    public byte[] a(Asset asset) {
        int available;
        if (asset == null) {
            throw new IllegalArgumentException("Asset must be non-null");
        }
        InputStream inputStream = Wearable.DataApi.getFdForAsset(this.i, asset).await(5L, TimeUnit.SECONDS).getInputStream();
        if (inputStream == null) {
            LogUtil.a("SmartWatchPhysicalService", "transToByteArrayFromAsset,Requested an unknown Asset.");
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = null;
        try {
            try {
                available = inputStream.available();
                LogUtil.c("SmartWatchPhysicalService", "transToByteArrayFromAsset() length = ", Integer.valueOf(available));
            } catch (IOException unused) {
                LogUtil.e("SmartWatchPhysicalService", "output stream write error");
                try {
                    inputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException unused2) {
                    LogUtil.e("SmartWatchPhysicalService", "transToByteArrayFromAsset ,Stream close err");
                }
            }
            if (available == 0) {
                return null;
            }
            byte[] bArr2 = new byte[available];
            for (int read = inputStream.read(bArr2, 0, available); read != -1; read = inputStream.read(bArr2, 0, available)) {
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            bArr = byteArrayOutputStream.toByteArray();
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException unused3) {
                LogUtil.e("SmartWatchPhysicalService", "transToByteArrayFromAsset ,Stream close err");
            }
            return bArr;
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException unused4) {
                LogUtil.e("SmartWatchPhysicalService", "transToByteArrayFromAsset ,Stream close err");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(byte[] r13, java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bif.e(byte[], java.lang.String):void");
    }

    private void b(Node node, int i, int i2) {
        DeviceInfo deviceInfo;
        synchronized (f385a) {
            if (i == this.h) {
                LogUtil.c("SmartWatchPhysicalService", "connectState no change");
                return;
            }
            this.h = i;
            if (node != null && !TextUtils.isEmpty(e(node))) {
                this.o = node.getId();
            }
            DeviceStatusChangeCallback deviceStatusChangeCallback = this.q;
            if (deviceStatusChangeCallback == null || (deviceInfo = this.j) == null) {
                return;
            }
            deviceStatusChangeCallback.onConnectStatusChanged(deviceInfo, i, i2);
        }
    }

    private void b(byte[] bArr, int i) {
        biu biuVar = new biu();
        biuVar.d(bArr);
        MessageReceiveCallback messageReceiveCallback = this.k;
        if (messageReceiveCallback != null) {
            messageReceiveCallback.onDataReceived(this.j, biuVar, i);
        }
    }

    public void j() {
        PackageInfo packageInfo;
        try {
            packageInfo = BaseApplication.e().getPackageManager().getPackageInfo("com.huawei.bone", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.c("SmartWatchPhysicalService", "NameNotFoundException e= ", "PackageManager.NameNotFoundException");
            packageInfo = null;
        }
        if (packageInfo == null) {
            LogUtil.c("SmartWatchPhysicalService", "packageInfo is null");
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageInfo.packageName);
        ResolveInfo next = BaseApplication.e().getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        if (next != null) {
            String str = next.activityInfo.packageName;
            String str2 = next.activityInfo.name;
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.addFlags(268435456);
            intent2.setComponent(new ComponentName(str, str2));
            try {
                BaseApplication.e().startActivity(intent2);
                LogUtil.c("SmartWatchPhysicalService", "startAPP ,startActivity intent = ", intent2);
            } catch (ActivityNotFoundException unused2) {
                LogUtil.e("startJumpActivity not find JumpActivity", new Object[0]);
            }
        }
    }

    public void b(MessageReceiveCallback messageReceiveCallback) {
        this.k = messageReceiveCallback;
    }

    public void b(DeviceStatusChangeCallback deviceStatusChangeCallback) {
        this.q = deviceStatusChangeCallback;
    }

    public void b(DeviceInfo deviceInfo) {
        this.j = deviceInfo;
    }

    public String c() {
        return this.o;
    }

    public GoogleApiClient e() {
        return this.i;
    }

    public Collection<Node> b() {
        ExecutionException e2;
        List<Node> list;
        InterruptedException e3;
        List list2 = null;
        try {
            list = (List) Tasks.await(Wearable.getNodeClient(BaseApplication.e(), this.r).getConnectedNodes(), e, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e4) {
            e3 = e4;
            list = null;
        } catch (ExecutionException e5) {
            e2 = e5;
            list = null;
        } catch (TimeoutException e6) {
            e = e6;
        }
        try {
            for (Node node : list) {
                LogUtil.c("SmartWatchPhysicalService", "getNodes() already found watch：", node.getDisplayName(), ",ID = ", node.getId());
            }
            return list;
        } catch (InterruptedException e7) {
            e3 = e7;
            LogUtil.e("SmartWatchPhysicalService", "get nodes ExecutionException, ", ExceptionUtils.d(e3));
            return list;
        } catch (ExecutionException e8) {
            e2 = e8;
            LogUtil.e("SmartWatchPhysicalService", "get nodes ExecutionException, ", ExceptionUtils.d(e2));
            return list;
        } catch (TimeoutException e9) {
            e = e9;
            list2 = list;
            LogUtil.e("SmartWatchPhysicalService", "get nodes TimeoutException, ", ExceptionUtils.d(e));
            return list2;
        }
    }

    public String e(Node node) {
        if (node != null) {
            return (node.getDisplayName().startsWith("Blacktip") || node.getDisplayName().startsWith("CSN-Temp")) ? "" : node.getId();
        }
        LogUtil.e("SmartWatchPhysicalService", "getLeoNodeId node is null");
        return "";
    }

    public boolean a(String str, byte[] bArr) {
        if (!TextUtils.isEmpty(str)) {
            this.o = str;
        }
        blt.d("SmartWatchPhysicalService", bArr, "sendBTDeviceData SDK-->Device : ");
        int i = this.m;
        String str2 = "/mobile_response_data";
        if (i > 0) {
            int i2 = this.f;
            if (i2 > 0 && i2 < i + 1) {
                str2 = "/mobile_response_data" + this.f;
            } else {
                this.f = 0;
            }
            LogUtil.c("SmartWatchPhysicalService", "mCurrentChannel = ", Integer.valueOf(this.f));
            this.f++;
        }
        LogUtil.c("SmartWatchPhysicalService", "sendBTDeviceData, path = ", str2, ", dataKey = ", "mobile_response_data");
        PutDataMapRequest create = PutDataMapRequest.create(str2);
        create.getDataMap().putByteArray("mobile_response_data", bArr);
        create.getDataMap().putLong("current_time", System.currentTimeMillis());
        if (!TextUtils.isEmpty(this.o)) {
            create.getDataMap().putString("NODE", this.o);
            LogUtil.c("SmartWatchPhysicalService", " NODE = ", this.o);
        } else {
            LogUtil.a("SmartWatchPhysicalService", "sendBTDeviceData NODE = null");
        }
        if (!c(this.l.sendMessage(this.o, str2, bArr))) {
            e(create);
        }
        return true;
    }

    public void c(String str, String str2) {
        LogUtil.c("SmartWatchPhysicalService", "sendBTDeviceAssetData,path = ", "/mobile_response_asset", " ; key : ", "mobile_response_asset", " assetPath = ", str2);
        byte[] b = bli.b(str2);
        PutDataMapRequest create = PutDataMapRequest.create("/mobile_response_asset");
        create.getDataMap().putAsset("mobile_response_asset", a(b));
        create.getDataMap().putString("mobile_response_asset_name", str2);
        create.getDataMap().putLong("current_time", System.currentTimeMillis());
        if (str != null) {
            create.getDataMap().putString("NODE", str);
            LogUtil.c("SmartWatchPhysicalService", "NODE = ", str);
        } else {
            LogUtil.e("SmartWatchPhysicalService", "sendBTFilePath NODE = null");
        }
        e(create);
        LogUtil.c("SmartWatchPhysicalService", "SDK-->Device : Asset : ", create);
    }

    public void a(BtDeviceResponseCallback btDeviceResponseCallback) {
        this.b = btDeviceResponseCallback;
    }

    public void c(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("SmartWatchPhysicalService", " etFileCallback :", btDeviceResponseCallback);
        this.c = btDeviceResponseCallback;
    }

    public void c(int i) {
        LogUtil.c("SmartWatchPhysicalService", "Enter setPathExtendNum with pathExtendNum = ", Integer.valueOf(i));
        this.m = i;
    }

    private boolean c(Task<Integer> task) {
        try {
            LogUtil.c("SmartWatchPhysicalService", "send isSuccess successful isSuccess ID is ", Integer.valueOf(((Integer) Tasks.await(task, e, TimeUnit.MILLISECONDS)).intValue()));
            return true;
        } catch (InterruptedException e2) {
            LogUtil.e("SmartWatchPhysicalService", "sendMessage ExecutionException, ", ExceptionUtils.d(e2));
            return false;
        } catch (ExecutionException e3) {
            LogUtil.e("SmartWatchPhysicalService", "sendMessage ExecutionException, ", ExceptionUtils.d(e3));
            return false;
        } catch (TimeoutException e4) {
            LogUtil.e("SmartWatchPhysicalService", "sendMessage TimeoutException, ", ExceptionUtils.d(e4));
            return false;
        }
    }

    private void e(PutDataMapRequest putDataMapRequest) {
        PutDataRequest asPutDataRequest = putDataMapRequest.asPutDataRequest();
        LogUtil.c("SmartWatchPhysicalService", "Generating DataItem: ", asPutDataRequest, " isConnected : ", Boolean.valueOf(this.i.isConnected()));
        if (!this.i.isConnected()) {
            this.i.reconnect();
        } else {
            asPutDataRequest.setUrgent();
            Wearable.DataApi.putDataItem(this.i, asPutDataRequest).setResultCallback(new ResultCallback<DataApi.DataItemResult>() { // from class: bif.3
                @Override // com.google.android.gms.common.api.ResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onResult(DataApi.DataItemResult dataItemResult) {
                    LogUtil.c("SmartWatchPhysicalService", "syncDataItem() Sending image was successful: ", Boolean.valueOf(dataItemResult.getStatus().isSuccess()));
                }
            });
        }
    }

    private static Asset a(byte[] bArr) {
        return Asset.createFromBytes(bArr);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        LogUtil.c("SmartWatchPhysicalService", "onConnected");
        Collection<Node> b = b();
        if (b != null) {
            Iterator<Node> it = b.iterator();
            while (it.hasNext()) {
                b(it.next(), 2, 100000);
            }
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        LogUtil.c("SmartWatchPhysicalService", " onConnectionSuspended index=", Integer.valueOf(i));
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult == null || this.q == null) {
            return;
        }
        LogUtil.c("SmartWatchPhysicalService", "onConnectionFailed errorCode :", Integer.valueOf(connectionResult.getErrorCode()), "errorMessage :", connectionResult.getErrorMessage());
        this.q.onConnectStatusChanged(this.j, 3, bln.e(3, connectionResult.getErrorCode()));
    }
}
