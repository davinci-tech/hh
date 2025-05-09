package com.huawei.multisimservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.IAttachedDeviceMultiSim;
import com.huawei.multisimservice.IOpenMultiSim;
import com.huawei.multisimservice.IServiceBinder;
import com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback;
import com.huawei.multisimservice.model.IOpenMultiSimCalbcak;
import com.huawei.multisimservice.model.SimInfo;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import defpackage.ixp;
import defpackage.jdh;
import defpackage.ktx;
import defpackage.sqo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class MultiSimService extends Service {
    private String b = "";
    private IOpenMultiSimCalbcak j = null;
    private IAttachedDeviceMultiSimCallback c = null;

    /* renamed from: a, reason: collision with root package name */
    private final IServiceBinder.Stub f6542a = new IServiceBinder.Stub() { // from class: com.huawei.multisimservice.MultiSimService.2
        @Override // com.huawei.multisimservice.IServiceBinder
        public IBinder getServiceBinder(String str) {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "getServiceBinder input packageName is:", str);
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("MultiSimService", "getServiceBinder input packageName error");
                sqo.o("getServiceBinder input packageName error.");
                return null;
            }
            if (ixp.e(BaseApplication.getContext(), str)) {
                MultiSimService.this.b = str;
                ktx.e().e(MultiSimService.this.b);
                if ("com.huawei.hwmultisim".equals(str)) {
                    LogUtil.a("MultiSimService", "getServiceBinder return mAttachedDeviceBinder");
                    return MultiSimService.this.d;
                }
                LogUtil.a("MultiSimService", "getServiceBinder return mIOpenMultiSimBinder");
                return MultiSimService.this.e;
            }
            ReleaseLogUtil.d("DEVMGR_MultiSimService", "AuthUtils failure with", str);
            sqo.o("AuthUtils failure with" + str);
            return null;
        }
    };
    private final IAttachedDeviceMultiSim.Stub d = new IAttachedDeviceMultiSim.Stub() { // from class: com.huawei.multisimservice.MultiSimService.4
        @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
        public void registerCallback(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "IAttachedDeviceMultiSim registerCallback ");
            MultiSimService.this.c = iAttachedDeviceMultiSimCallback;
        }

        @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
        public void unRegisterCallback(IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback) {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "IAttachedDeviceMultiSim unRegisterCallback ");
            MultiSimService.this.c = null;
            ktx.e().b(iAttachedDeviceMultiSimCallback);
        }

        @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
        public void getAttachedDeviceMultiSimInfo() {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "IAttachedDeviceMultiSim getAttachedDeviceMultiSimInfo");
            MultiSimService.this.e();
        }

        @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
        public void downloadESimProfile(String str) {
            MultiSimService.this.c(str);
        }

        @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
        public void multiSimStatusNotify(int i, String str) {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "multiSimStatusNotifyRequest multiSimStatus is:", Integer.valueOf(i));
            ktx.e().c(i, str);
        }

        @Override // com.huawei.multisimservice.IAttachedDeviceMultiSim
        public void deleteESimProfile(List<SimInfo> list) {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "IAttachedDeviceMultiSim deleteESimProfile profileInfoList is:", list);
            ktx.e().b(list);
        }
    };
    private final IOpenMultiSim.Stub e = new IOpenMultiSim.Stub() { // from class: com.huawei.multisimservice.MultiSimService.1
        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void registerCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "IAttachedDeviceMultiSim registerCallback");
            MultiSimService.this.j = iOpenMultiSimCalbcak;
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void unRegisterCallback(IOpenMultiSimCalbcak iOpenMultiSimCalbcak) {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "IAttachedDeviceMultiSim unRegisterCallback");
            MultiSimService.this.j = null;
            ktx.e().d(iOpenMultiSimCalbcak);
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void getAttachedDeviceMultiSimInfo() {
            ReleaseLogUtil.e("DEVMGR_MultiSimService", "IOpenMultiSim.IAttachedDeviceMultiSim getAttachedDeviceMultiSimInfo");
            MultiSimService.this.e();
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void downloadEsimProfile(String str, long j, String str2) {
            LogUtil.a("MultiSimService", "downloadEsimProfile activationCode :", str, ", app:", MultiSimService.this.b);
            if (!TextUtils.isEmpty(str2)) {
                MultiSimService.this.c();
                ktx e = ktx.e();
                if (e != null) {
                    e.e(MultiSimService.this.b);
                    if (e.c(MultiSimService.this.b)) {
                        e.a(str, j, str2);
                        return;
                    }
                    return;
                }
                ReleaseLogUtil.d("DEVMGR_MultiSimService", "hwMultiSimManager is null.");
                sqo.o("hwMultiSimManager is null.");
                return;
            }
            ReleaseLogUtil.e("MultiSimService", "sign is empty.");
            sqo.o("sign is empty.");
            downloadESimProfile(str);
        }

        @Override // com.huawei.multisimservice.IOpenMultiSim
        public void downloadESimProfile(String str) {
            LogUtil.a("MultiSimService", "IOpenMultiSim.IAttachedDeviceMultiSim downloadESimProfile,activationCode is:", str, ",downloadESimProfile with app:", MultiSimService.this.b);
            MultiSimService.this.c();
            ktx e = ktx.e();
            if (e != null) {
                e.e(MultiSimService.this.b);
                if (e.c(MultiSimService.this.b)) {
                    if (MultiSimService.this.b.equals("com.sinovatech.unicom.ui")) {
                        e.a(str, 0L, "");
                        return;
                    } else {
                        e.a(str);
                        return;
                    }
                }
                return;
            }
            ReleaseLogUtil.d("DEVMGR_MultiSimService", "downloadEsimProfile get hwMultiSimManager is null");
            sqo.o("downloadEsimProfile get hwMultiSimManager is null.");
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("endTime", String.valueOf(System.currentTimeMillis()));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_ESIM_MANAGER_60010002.value(), linkedHashMap);
    }

    public MultiSimService() {
        LogUtil.a("MultiSimService", "MultiSimService construct");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.a("MultiSimService", "onStartCommand");
        super.onStartCommand(intent, i, i2);
        LogUtil.a("MultiSimService", "onStartCommand notification");
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setAutoCancel(true);
        xf_.setPriority(0);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setGroup("MultiSimService");
        startForeground(20200608, xf_.build());
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ReleaseLogUtil.e("DEVMGR_MultiSimService", "onBind service");
        return this.f6542a;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        this.b = "";
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        jdh.c().a(20200608);
        ReleaseLogUtil.e("DEVMGR_MultiSimService", "onDestroy");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("MultiSimService", "getDeviceInfoAndAuth with app:", this.b);
        ktx e = ktx.e();
        if (e == null) {
            ReleaseLogUtil.d("DEVMGR_MultiSimService", "getDeviceInfoAndAuth get hwMultiSimManager is null");
            sqo.o("getDeviceInfoAndAuth get hwMultiSimManager is null.");
            return;
        }
        e.e(this.b);
        IAttachedDeviceMultiSimCallback iAttachedDeviceMultiSimCallback = this.c;
        if (iAttachedDeviceMultiSimCallback != null) {
            e.c(iAttachedDeviceMultiSimCallback);
        } else {
            IOpenMultiSimCalbcak iOpenMultiSimCalbcak = this.j;
            if (iOpenMultiSimCalbcak != null) {
                e.a(iOpenMultiSimCalbcak);
            } else {
                LogUtil.h("MultiSimService", "getDeviceInfoAndAuth default.");
            }
        }
        e.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogUtil.a("MultiSimService", "IAttachedDeviceMultiSim downloadESimProfile activationCode is:", str, ",downloadESimProfile with app:", this.b);
        ktx e = ktx.e();
        e.e(this.b);
        if (e.c(this.b) || "com.huawei.hwmultisim".equals(this.b)) {
            e.a(str);
        }
    }
}
