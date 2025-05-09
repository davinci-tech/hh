package com.huawei.wearengine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.common.Constant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.wearengine.HwWearEngineNativeBinder;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.IdentityInfo;
import defpackage.tqy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class HwWearEngineNative implements ServiceConnection {
    private Context d;
    private CountDownLatch e;
    private HwWearEngineNativeBinder b = null;
    private IBinder.DeathRecipient c = new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.HwWearEngineNative.4
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            LogUtil.c("HwWearEngineNative", "binderDied enter");
            if (HwWearEngineNative.this.b != null) {
                HwWearEngineNative.this.b.asBinder().unlinkToDeath(HwWearEngineNative.this.c, 0);
                HwWearEngineNative.this.b = null;
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private ExecutorService f11221a = Executors.newSingleThreadExecutor();

    public HwWearEngineNative(Context context) {
        this.d = context;
    }

    public void fcm_(final String str, final IBinder iBinder) {
        LogUtil.a("HwWearEngineNative", "setBinder enter");
        if (TextUtils.isEmpty(str) || iBinder == null) {
            return;
        }
        if (this.b != null) {
            LogUtil.h("HwWearEngineNative", "mApiAidl is not null");
            return;
        }
        LogUtil.a("HwWearEngineNative", "setBinder start to bind HiWearService");
        this.e = new CountDownLatch(1);
        this.f11221a.execute(new Runnable() { // from class: com.huawei.wearengine.HwWearEngineNative.3
            @Override // java.lang.Runnable
            public void run() {
                HwWearEngineNative.this.a();
                try {
                    HwWearEngineNative.this.e.await(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS);
                } catch (InterruptedException unused) {
                    LogUtil.b("HwWearEngineNative", "setBinder await error");
                }
                if (HwWearEngineNative.this.b != null) {
                    try {
                        LogUtil.a("HwWearEngineNative", "setBinder mApiAidl = ", HwWearEngineNative.this.b);
                        HwWearEngineNative.this.b.setBinder(str, iBinder);
                        return;
                    } catch (Exception unused2) {
                        LogUtil.b("HwWearEngineNative", "setBinder Exception");
                        return;
                    }
                }
                LogUtil.b("HwWearEngineNative", "mApiAidl is null");
            }
        });
    }

    public boolean d(final Device device, final IdentityInfo identityInfo, final IdentityInfo identityInfo2) {
        if (this.b == null) {
            LogUtil.b("HwWearEngineNative", "isP2pReceiverExist mApiAidl is null");
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = new boolean[1];
        this.f11221a.execute(new Runnable() { // from class: com.huawei.wearengine.HwWearEngineNative.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        LogUtil.a("HwWearEngineNative", "isP2pReceiverExist send");
                        zArr[0] = HwWearEngineNative.this.b.isP2pReceiverExist(device, identityInfo, identityInfo2);
                    } catch (RemoteException unused) {
                        zArr[0] = false;
                        LogUtil.b("HwWearEngineNative", "isP2pReceiverExist RemoteException");
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        try {
            if (countDownLatch.await(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                return zArr[0];
            }
            LogUtil.h("HwWearEngineNative", "isP2pReceiverExist time out.");
            return false;
        } catch (InterruptedException unused) {
            LogUtil.b("HwWearEngineNative", "isP2pReceiverExist InterruptedException.");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("HwWearEngineNative", "bindService enter");
        Intent intent = new Intent();
        intent.setClassName(this.d.getPackageName(), "com.huawei.wearengine.service.WearEngineExtendService");
        intent.setPackage(this.d.getPackageName());
        intent.setAction("com.huawei.wearengine.action.PHONE_SERVICE");
        Intent feZ_ = tqy.feZ_(this.d, intent);
        if (feZ_ == null) {
            LogUtil.h("HwWearEngineNative", "bindService() explicitIntent = null");
            if (this.e != null) {
                LogUtil.a("HwWearEngineNative", "bindService countDown");
                this.e.countDown();
                return;
            }
            return;
        }
        LogUtil.a("HwWearEngineNative", "bindService do bind");
        this.d.bindService(feZ_, this, 1);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        LogUtil.a("HwWearEngineNative", Constant.SERVICE_CONNECT_MESSAGE);
        HwWearEngineNativeBinder asInterface = HwWearEngineNativeBinder.Stub.asInterface(iBinder);
        this.b = asInterface;
        LogUtil.a("HwWearEngineNative", "onServiceConnected mApiAidl = ", asInterface);
        HwWearEngineNativeBinder hwWearEngineNativeBinder = this.b;
        if (hwWearEngineNativeBinder != null) {
            try {
                hwWearEngineNativeBinder.asBinder().linkToDeath(this.c, 0);
            } catch (RemoteException unused) {
                LogUtil.h("HwWearEngineNative", "onServiceConnected exception");
            }
        }
        if (this.e != null) {
            LogUtil.a("HwWearEngineNative", "onServiceConnected countDown");
            this.e.countDown();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        LogUtil.h("HwWearEngineNative", "onServiceDisconnected");
        this.b = null;
    }
}
