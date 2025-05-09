package com.huawei.health.manager;

import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.IHealthDeviceOperManager;
import com.huawei.health.manager.util.RemoteCallerFilter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.BluetoothMonitor;

/* loaded from: classes.dex */
public class HealthDeviceOperateRemoteProxy extends IHealthDeviceOperManager.Stub {
    private ExtendHandler c;
    private DaemonService d;
    private String j;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2767a = false;
    private boolean b = false;
    private boolean e = false;

    public HealthDeviceOperateRemoteProxy(DaemonService daemonService) {
        this.d = null;
        LogUtil.a("HealthDeviceOper_RemoteProxy", "HealthDeviceOperateRemoteProxy is Set Up");
        if (daemonService != null) {
            this.d = daemonService;
        } else {
            LogUtil.a("HealthDeviceOper_RemoteProxy", "service == null");
        }
    }

    private void d() {
        LogUtil.a("HealthDeviceOper_RemoteProxy", "HealthDeviceOperateRemoteProxy create thread");
        this.c = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.health.manager.HealthDeviceOperateRemoteProxy.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message.what != 1) {
                    return false;
                }
                BluetoothMonitor.a().a(HealthDeviceOperateRemoteProxy.this.j);
                HealthDeviceOperateRemoteProxy.this.b = false;
                return true;
            }
        }, "HealthDeviceOper_RemoteProxy");
        this.e = true;
    }

    @Override // com.huawei.health.IHealthDeviceOperManager.Stub, android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (!RemoteCallerFilter.d()) {
            throw new RemoteException("hw permission check failed");
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    @Override // com.huawei.health.IHealthDeviceOperManager
    public void holdDevice(String str) throws RemoteException {
        if (str == null) {
            LogUtil.h("HealthDeviceOper_RemoteProxy", "deviceInfo is null");
            return;
        }
        LogUtil.a("HealthDeviceOper_RemoteProxy", "HealthDeviceOperateRemoteProxy holdDevice() deviceInfo = " + str);
        this.j = str;
        this.f2767a = true;
        if (!this.e) {
            d();
        }
        if (!this.b) {
            BluetoothMonitor.a().e(str);
            this.b = true;
        }
        this.c.removeMessages(1);
        this.c.sendEmptyMessage(1, OpAnalyticsConstants.H5_LOADING_DELAY);
    }

    @Override // com.huawei.health.IHealthDeviceOperManager
    public void releaseDevice(String str) throws RemoteException {
        if (str == null) {
            LogUtil.h("HealthDeviceOper_RemoteProxy", "macAddress is null");
            return;
        }
        this.b = false;
        this.c.removeMessages(1);
        if (this.f2767a) {
            BluetoothMonitor.a().a(str);
            this.f2767a = false;
        }
        if (this.c != null) {
            LogUtil.a("HealthDeviceOper_RemoteProxy", "HealthDeviceOperateRemoteProxy finish thread");
            this.c.quit(false);
            this.c = null;
            this.e = false;
        }
    }
}
