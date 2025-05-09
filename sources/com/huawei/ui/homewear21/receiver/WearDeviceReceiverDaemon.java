package com.huawei.ui.homewear21.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import defpackage.njn;
import defpackage.tri;
import health.compact.a.CommonUtil;
import health.compact.a.Services;

/* loaded from: classes9.dex */
public class WearDeviceReceiverDaemon extends BroadcastReceiver {
    private a e;

    static class a implements ServiceConnectCallback {

        /* renamed from: a, reason: collision with root package name */
        DeviceInfo f9682a;

        a(DeviceInfo deviceInfo) {
            this.f9682a = deviceInfo;
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            LogUtil.a("WearDeviceReceiverDaemon", "profile connected");
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homewear21.receiver.WearDeviceReceiverDaemon.a.2
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.f9682a != null) {
                        a.this.a();
                    }
                }
            });
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            LogUtil.a("WearDeviceReceiverDaemon", "profile disconnected");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.f9682a.getDeviceConnectState() == 2) {
                LogUtil.a("WearDeviceReceiverDaemon", "Device connected. Check inbox benefit automatic activate.");
                ((PayApi) Services.c("TradeService", PayApi.class)).enableBenefitAutoActivation(njn.e(this.f9682a, DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL, true));
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, final Intent intent) {
        if (!CommonUtil.ce() || intent == null || context == null) {
            return;
        }
        LogUtil.a("WearDeviceReceiverDaemon", "action = ", intent.getAction(), "current time: ", Long.valueOf(System.currentTimeMillis()));
        if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homewear21.receiver.WearDeviceReceiverDaemon.2
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("WearDeviceReceiverDaemon", "current time: ", Long.valueOf(System.currentTimeMillis()));
                    Bundle extras = intent.getExtras();
                    if (extras == null) {
                        return;
                    }
                    try {
                        Parcelable parcelable = extras.getParcelable("deviceinfo");
                        if (parcelable instanceof DeviceInfo) {
                            WearDeviceReceiverDaemon.this.b((DeviceInfo) parcelable);
                        }
                    } catch (BadParcelableException unused) {
                        LogUtil.b("WearDeviceReceiverDaemon", "onReceive() BadParcelableException");
                    } catch (RuntimeException unused2) {
                        LogUtil.b("WearDeviceReceiverDaemon", "RuntimeException");
                    }
                }
            });
        } else {
            LogUtil.a("WearDeviceReceiverDaemon", "Enter else");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearDeviceReceiverDaemon", "deviceProfileUpload deviceInfo is null");
            return;
        }
        if (!tri.d(BaseApplication.getContext())) {
            LogUtil.h("WearDeviceReceiverDaemon", "deviceProfileUpload isNeedShowBeforeLogin");
        } else if (deviceInfo.getDeviceConnectState() != 2 && deviceInfo.getDeviceConnectState() != 3) {
            LogUtil.h("WearDeviceReceiverDaemon", "deviceProfileUpload device not connected or disconnect.");
        } else {
            this.e = new a(deviceInfo);
            ProfileAgent.PROFILE_AGENT.connectProfile(this.e);
        }
    }
}
