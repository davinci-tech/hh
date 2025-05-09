package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.huawei.devicesdk.connect.physical.PhysicalLayerBase;
import com.huawei.devicesdk.connect.physical.smartwatch.SmartWatchFileCallback;
import com.huawei.devicesdk.connect.physical.smartwatch.SmartWatchHostPhysicalService;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;

/* loaded from: classes3.dex */
public class bhz extends PhysicalLayerBase implements SmartWatchFileCallback {
    private GoogleApiClient b;
    private ExtendHandler d;
    private DeviceInfo e = null;
    private a c = new a(this);
    private String h = "";

    /* renamed from: a, reason: collision with root package name */
    private Node f383a = null;

    protected bhz() {
        d();
    }

    private void d() {
        this.d = HandlerCenter.yt_(this.c, "InoperableSmartWatchPhysicalService");
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void connectDevice(DeviceInfo deviceInfo) {
        LogUtil.c("InoperableSmartWatchPhysicalService", "connectDevice.");
        if (deviceInfo == null) {
            LogUtil.e("InoperableSmartWatchPhysicalService", "btDevice is null.");
            e(3, bln.e(7, 303));
            return;
        }
        this.e = deviceInfo;
        SmartWatchHostPhysicalService.b(BaseApplication.e());
        bif.a().b(deviceInfo);
        bif.a().b(this.mStatusChangeCallback);
        bif.a().b(this.mMessageReceiveCallback);
        this.h = bif.a().c();
        e(1, 100000);
        GoogleApiClient e = bif.a().e();
        this.b = e;
        if (e == null) {
            LogUtil.c("InoperableSmartWatchPhysicalService", "blockingConnect mGoogleApiClient is null");
            return;
        }
        if (e.isConnected()) {
            a();
            return;
        }
        if (this.b.isConnecting()) {
            LogUtil.c("InoperableSmartWatchPhysicalService", "GoogleApiClient isConnecting");
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.google.android.wearable.app.cn", "com.google.android.gms.wearable.service.WearableService"));
        try {
            BaseApplication.e().startService(intent);
        } catch (IllegalStateException e2) {
            LogUtil.e("InoperableSmartWatchPhysicalService", "connectDevice IllegalStateException", ExceptionUtils.d(e2));
        }
        LogUtil.c("InoperableSmartWatchPhysicalService", "started gms service");
        this.b.connect();
        LogUtil.e("InoperableSmartWatchPhysicalService", "GMS connect fail, GoogleApiClient try connect");
    }

    private void a() {
        LogUtil.c("InoperableSmartWatchPhysicalService", "Enter connectedSuccess");
        ExtendHandler extendHandler = this.d;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Collection<Node> b = bif.a().b();
        if (b == null || b.size() == 0) {
            LogUtil.c("InoperableSmartWatchPhysicalService", " StateThread no watch in iterator");
            c();
            return;
        }
        LogUtil.c("InoperableSmartWatchPhysicalService", "StateThread watch names.size() is ", Integer.valueOf(b.size()));
        for (Node node : b) {
            if (node != null) {
                this.h = node.getId();
                d(node);
                e(2, 100000);
                return;
            }
        }
        c();
    }

    private void c() {
        LogUtil.c("InoperableSmartWatchPhysicalService", "reconnect device, NodeId = ", this.h);
        if (this.e != null && !TextUtils.isEmpty(this.h)) {
            tsm.c().a(this.h);
        } else {
            e(3, bln.e(7, 303));
        }
    }

    private void d(Node node) {
        synchronized (this) {
            this.f383a = node;
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void disconnectDevice() {
        LogUtil.c("InoperableSmartWatchPhysicalService", "start to disconnect.");
        destroy();
        e();
    }

    private void e() {
        e(0, 100000);
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public boolean sendData(bim bimVar, String str) {
        LogUtil.c("InoperableSmartWatchPhysicalService", "sendBTDeviceData");
        if (bimVar == null) {
            LogUtil.e("InoperableSmartWatchPhysicalService", "sendData error. bluetooth frame data is null");
            return false;
        }
        List<bil> e = bimVar.e();
        if (bkz.e(e)) {
            LogUtil.e("InoperableSmartWatchPhysicalService", "the packages getted is empty", blt.a(this.mDeviceInfo));
            return true;
        }
        for (bil bilVar : e) {
            if (bilVar == null) {
                LogUtil.e("InoperableSmartWatchPhysicalService", "onePackage is null");
                return false;
            }
            bif.a().a(this.h, bilVar.d());
        }
        return true;
    }

    private void e(int i, int i2) {
        if (this.f383a != null) {
            this.mDeviceInfo.setDeviceName(this.f383a.getDisplayName());
        }
        if (this.mStatusChangeCallback == null || this.e == null) {
            return;
        }
        LogUtil.c("InoperableSmartWatchPhysicalService", "report connect state : ", Integer.valueOf(i));
        this.mStatusChangeCallback.onConnectStatusChanged(this.e, i, i2);
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void destroy() {
        ExtendHandler extendHandler = this.d;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.d = null;
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.smartwatch.SmartWatchFileCallback
    public void sendBtFilePath(String str) {
        LogUtil.c("InoperableSmartWatchPhysicalService", "sendBTFilePath");
        bif.a().c(this.h, str);
    }

    @Override // com.huawei.devicesdk.connect.physical.smartwatch.SmartWatchFileCallback
    public void setFileCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("InoperableSmartWatchPhysicalService", "setFileCallback");
        bif.a().a(btDeviceResponseCallback);
    }

    @Override // com.huawei.devicesdk.connect.physical.smartwatch.SmartWatchFileCallback
    public void setSmartWatchAssetCallback(BtDeviceResponseCallback btDeviceResponseCallback) {
        LogUtil.c("InoperableSmartWatchPhysicalService", "setAwAssetCallback");
        bif.a().c(btDeviceResponseCallback);
    }

    @Override // com.huawei.devicesdk.connect.physical.smartwatch.SmartWatchFileCallback
    public void setPathExtendNum(int i) {
        LogUtil.c("InoperableSmartWatchPhysicalService", "Enter setPathExtendNum ");
        bif.a().c(i);
    }

    static class a implements Handler.Callback {
        private WeakReference<bhz> c;

        a(bhz bhzVar) {
            this.c = new WeakReference<>(bhzVar);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            WeakReference<bhz> weakReference;
            if (message == null || (weakReference = this.c) == null) {
                return false;
            }
            bhz bhzVar = weakReference.get();
            if (bhzVar == null) {
                LogUtil.a("InoperableSmartWatchPhysicalService", "handleMessage inoperableSmartWatchPhysicalService is null");
                return false;
            }
            if (message.what != 1) {
                bhzVar.b();
                return true;
            }
            bhzVar.b();
            return true;
        }
    }
}
