package com.huawei.hwdevice.phoneprocess.mgr.sleepsync;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.callback.FitnessCloudCallBack;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.intelligent.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataRequest;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataResponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixx;
import defpackage.jbs;
import defpackage.jqi;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class SleepSyncManager {
    private static SleepSyncManager c;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f6349a;
    private jbs f;
    private DeviceInfo j;
    protected int b = 0;
    protected int d = 0;
    private e g = new e();
    private jqi i = jqi.a();
    private ExtendHandler h = HandlerCenter.yt_(this.g, "SleepSyncManager");

    public SleepSyncManager(Context context) {
        this.f6349a = context;
        this.f = jbs.a(this.f6349a);
    }

    public static SleepSyncManager e() {
        SleepSyncManager sleepSyncManager;
        synchronized (e) {
            if (c == null) {
                c = new SleepSyncManager(BaseApplication.getContext());
            }
            sleepSyncManager = c;
        }
        return sleepSyncManager;
    }

    public void a(DeviceInfo deviceInfo) {
        LogUtil.a("SleepSyncManager", "action is ACTION_SYNC_SLEEP_MASK");
        this.b = 0;
        this.d = 0;
        this.j = deviceInfo;
        d();
    }

    protected void d() {
        LogUtil.a("SleepSyncManager", "activeTransportDeviceData enter");
        DeviceInfo deviceInfo = this.j;
        if (deviceInfo == null) {
            LogUtil.h("SleepSyncManager", "activeTransportDeviceData mDeviceInfo is null");
        } else {
            this.i.getSwitchSetting("intelligent_home_linkage", deviceInfo.getDeviceIdentify(), new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.sleepsync.SleepSyncManager.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("SleepSyncManager", "activeTransportDeviceData errorCode is: ", Integer.valueOf(i));
                    if (i == 0 && (obj instanceof String)) {
                        SleepSyncManager.this.c((String) obj);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (str.contains("&&")) {
            String[] split = str.split("&&");
            LogUtil.a("SleepSyncManager", "INTELLIGENT_HOME_LINKAGE new split : ", Integer.valueOf(split.length));
            if (split.length == 5) {
                String str2 = split[0];
                String str3 = split[1];
                String str4 = split[2];
                String str5 = split[3];
                String str6 = split[4];
                LogUtil.a("SleepSyncManager", "checkDeviceIdIsPermanent deviceId : ", CommonUtil.l(str2), " ,expires : ", str3, " ,productId : ", str4, " ,enable : ", str5, " ,isClick : ", str6);
                if (Boolean.parseBoolean(str5) && Boolean.parseBoolean(str6)) {
                    d(str2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ExtendHandler extendHandler = this.h;
        if (extendHandler != null) {
            extendHandler.removeMessages(10010);
        }
        this.d = 0;
        if (this.b < 3) {
            d();
        } else {
            this.b = 0;
        }
    }

    private void d(String str) {
        ReleaseLogUtil.e("R_SleepSyncManager", "transportDeviceData enter");
        Message obtain = Message.obtain();
        obtain.what = 10011;
        this.h.sendEmptyMessage(obtain.what, 60000L);
        a("sleep", "1", str, new FitnessCloudCallBack() { // from class: com.huawei.hwdevice.phoneprocess.mgr.sleepsync.SleepSyncManager.5
            @Override // com.huawei.callback.FitnessCloudCallBack
            public void onResponce(Object obj) {
                if (!(obj instanceof TransferDeviceDataResponse)) {
                    LogUtil.h("SleepSyncManager", "objectData is error");
                    return;
                }
                if (((TransferDeviceDataResponse) obj).getResultCode().intValue() == 0) {
                    SleepSyncManager.this.b = 0;
                    SleepSyncManager.this.d = 0;
                    SleepSyncManager.this.h.removeMessages(10011);
                    ReleaseLogUtil.e("R_SleepSyncManager", "transportDeviceData is success");
                    return;
                }
                ReleaseLogUtil.d("R_SleepSyncManager", "transportDeviceData is fail");
            }
        });
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090018.value(), new HashMap(16), 0);
    }

    private void a(String str, String str2, String str3, final FitnessCloudCallBack fitnessCloudCallBack) {
        if (fitnessCloudCallBack == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        PowerKitManager.e().a("SleepSyncManager", 512, "auto-health-transDeviceData");
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        deviceServiceInfo.setSid(str);
        HashMap hashMap = new HashMap(16);
        hashMap.put("state", str2);
        deviceServiceInfo.setData(hashMap);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(deviceServiceInfo);
        TransferDeviceDataRequest transferDeviceDataRequest = new TransferDeviceDataRequest();
        transferDeviceDataRequest.setDevId(str3);
        transferDeviceDataRequest.setServices(arrayList);
        this.f.a(transferDeviceDataRequest, new ICloudOperationResult<TransferDeviceDataResponse>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.sleepsync.SleepSyncManager.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void operationResult(TransferDeviceDataResponse transferDeviceDataResponse, String str4, boolean z) {
                if (transferDeviceDataResponse != null) {
                    fitnessCloudCallBack.onResponce(transferDeviceDataResponse);
                    PowerKitManager.e().e("SleepSyncManager", 512, "auto-health-transDeviceData");
                }
            }
        });
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("SleepSyncManager", "message is null");
                return false;
            }
            LogUtil.a("SleepSyncManager", "handleMessage message is ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 10010) {
                SleepSyncManager.this.b();
                return true;
            }
            if (i == 10011) {
                if (SleepSyncManager.this.h != null) {
                    SleepSyncManager.this.h.removeMessages(10011);
                }
                SleepSyncManager.this.d++;
                LogUtil.a("SleepSyncManager", "connectedThree :", Integer.valueOf(SleepSyncManager.this.d));
                if (SleepSyncManager.this.d == 3) {
                    SleepSyncManager.this.b++;
                    LogUtil.a("SleepSyncManager", "connected :", Integer.valueOf(SleepSyncManager.this.b));
                    Message obtain = Message.obtain();
                    obtain.what = 10010;
                    SleepSyncManager.this.h.sendEmptyMessage(obtain.what, 600000L);
                }
                if (SleepSyncManager.this.d < 3) {
                    SleepSyncManager.this.d();
                }
                return true;
            }
            LogUtil.h("SleepSyncManager", "handleMessage default");
            return false;
        }
    }
}
