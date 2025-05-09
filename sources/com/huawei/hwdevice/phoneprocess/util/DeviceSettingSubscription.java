package com.huawei.hwdevice.phoneprocess.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.jqi;
import defpackage.jqy;
import defpackage.jre;
import defpackage.koq;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class DeviceSettingSubscription {
    private static volatile DeviceSettingSubscription b;
    private static final Object c = new Object();
    private String h;
    private List<Integer> i;
    private ExecutorService f = Executors.newSingleThreadExecutor();
    private Map<String, CallBack> o = new HashMap();
    private boolean g = true;

    /* renamed from: a, reason: collision with root package name */
    private boolean f6357a = false;
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            DeviceInfo deviceInfo;
            Object[] objArr = new Object[2];
            objArr[0] = "mConnectStateChangedReceiver action:";
            objArr[1] = intent == null ? Constants.NULL : intent.getAction();
            LogUtil.a("DeviceSettingSubscription", objArr);
            if (intent == null || (action = intent.getAction()) == null || !action.equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                return;
            }
            try {
                deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
            } catch (ClassCastException unused) {
                LogUtil.b("DeviceSettingSubscription", "ClassCastException");
                deviceInfo = null;
            }
            if (deviceInfo == null) {
                LogUtil.h("DeviceSettingSubscription", "mConnectStateChangedReceiver deviceInfo is null");
                return;
            }
            LogUtil.a("DeviceSettingSubscription", "mConnectStateChangedReceiver ConnectState is :", Integer.valueOf(deviceInfo.getDeviceConnectState()), " deviceName: ", deviceInfo.getDeviceName(), " mIsFist:");
            if (deviceInfo.getDeviceConnectState() == 2) {
                try {
                    BaseApplication.getContext().unregisterReceiver(DeviceSettingSubscription.this.e);
                } catch (IllegalArgumentException e) {
                    LogUtil.h("DeviceSettingSubscription", "unregister mConnectStateChangedReceiver exception: ", e.getMessage());
                }
                if (DeviceSettingSubscription.this.f6357a) {
                    return;
                }
                DeviceSettingSubscription.this.f6357a = true;
                DeviceSettingSubscription.this.j();
                DeviceSettingSubscription.this.g();
                DeviceSettingSubscription.this.i();
                DeviceSettingSubscription.this.d();
            }
        }
    };
    protected BroadcastReceiver d = new BroadcastReceiver() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Object[] objArr = new Object[2];
            objArr[0] = "mLoginStatusReceiver action:";
            objArr[1] = intent == null ? Constants.NULL : intent.getAction();
            LogUtil.a("DeviceSettingSubscription", objArr);
            if (intent == null) {
                LogUtil.h("DeviceSettingSubscription", "onReceive mLoginStatusReceiver intent is null");
            } else if ("com.huawei.plugin.account.login".equals(intent.getAction())) {
                DeviceSettingSubscription.this.d();
            }
        }
    };
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Object[] objArr = new Object[2];
            objArr[0] = "HiHealth service start action:";
            objArr[1] = intent == null ? Constants.NULL : intent.getAction();
            LogUtil.a("DeviceSettingSubscription", objArr);
            if (intent != null && "com.huawei.health.action.HIHEALTH_SERVICE_START".equals(intent.getAction())) {
                DeviceSettingSubscription.this.j();
            }
        }
    };

    /* loaded from: classes5.dex */
    public interface CallBack {
        void onCall(DeviceInfo deviceInfo, String str, String str2);
    }

    private DeviceSettingSubscription() {
        c();
        h();
    }

    private void c() {
        this.o.put("continuous_temp_monitoring", new CallBack() { // from class: kjt
            @Override // com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.CallBack
            public final void onCall(DeviceInfo deviceInfo, String str, String str2) {
                kiw.c().e(deviceInfo, str2);
            }
        });
        this.o.put("temperature_upper_remind", new CallBack() { // from class: kka
            @Override // com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.CallBack
            public final void onCall(DeviceInfo deviceInfo, String str, String str2) {
                kiw.c().b(deviceInfo, str, str2);
            }
        });
        this.o.put("temperature_lower_remind", new CallBack() { // from class: kjz
            @Override // com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.CallBack
            public final void onCall(DeviceInfo deviceInfo, String str, String str2) {
                kiw.c().b(deviceInfo, str, str2);
            }
        });
        this.o.put("custom.temperature_unit", new CallBack() { // from class: kkc
            @Override // com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.CallBack
            public final void onCall(DeviceInfo deviceInfo, String str, String str2) {
                kiw.c().a(deviceInfo, str, str2);
            }
        });
        this.o.put("weather_switch_status", new CallBack() { // from class: kjy
            @Override // com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.CallBack
            public final void onCall(DeviceInfo deviceInfo, String str, String str2) {
                kjl.b().a();
            }
        });
    }

    private void h() {
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.e, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        BroadcastManagerUtil.bFE_(BaseApplication.getContext(), this.j, new IntentFilter("com.huawei.health.action.HIHEALTH_SERVICE_START"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.d, intentFilter, LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.g) {
            this.h = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_FLY), "DEVICE_SETTING_SUBSCRIPTION_USER_ID");
        }
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        boolean z = !Objects.equals(this.h, e);
        if (z) {
            this.h = e;
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(PrebakedEffectId.RT_FLY), "DEVICE_SETTING_SUBSCRIPTION_USER_ID", this.h, new StorageParams(1));
            e();
        }
        if (this.g || z) {
            this.g = false;
            a(false);
        }
    }

    private void e() {
        LogUtil.a("DeviceSettingSubscription", "cleanSpData");
        this.f.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = new ArrayList(DeviceSettingSubscription.this.o.keySet()).iterator();
                while (it.hasNext()) {
                    jqy.b((String) it.next(), "");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        synchronized (this) {
            HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(102, new HiSubscribeListener() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.5
                @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
                public void onResult(List<Integer> list, List<Integer> list2) {
                    DeviceSettingSubscription.this.i = list;
                    LogUtil.a("DeviceSettingSubscription", "subscribeHiHealthData success");
                }

                @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
                public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                    if (i != 102) {
                        return;
                    }
                    LogUtil.a("DeviceSettingSubscription", "subscribeHiHealthData onChange changeKey:", str);
                    if ("HiSyncUserData".equals(str)) {
                        DeviceSettingSubscription.this.a(true);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z) {
        LogUtil.a("DeviceSettingSubscription", "updateDeviceSetting isNotifyDevice:", Boolean.valueOf(z));
        this.f.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.10
            @Override // java.lang.Runnable
            public void run() {
                jqi.a().getSwitchSetting(new ArrayList(DeviceSettingSubscription.this.o.keySet()), new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.10.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("DeviceSettingSubscription", "extracted getSwitchSetting errorCode:", Integer.valueOf(i));
                        if (i == 0 && (obj instanceof List)) {
                            DeviceInfo a2 = jre.a("DeviceSettingSubscription");
                            for (Object obj2 : (List) obj) {
                                if (obj2 instanceof HiUserPreference) {
                                    HiUserPreference hiUserPreference = (HiUserPreference) obj2;
                                    String key = hiUserPreference.getKey();
                                    String value = hiUserPreference.getValue();
                                    jqy.b(key, value);
                                    if (z) {
                                        DeviceSettingSubscription.this.g(a2, key, value);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    public static DeviceSettingSubscription b() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new DeviceSettingSubscription();
                }
            }
        }
        return b;
    }

    public void g(final DeviceInfo deviceInfo, final String str, final String str2) {
        this.f.execute(new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription.8
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("DeviceSettingSubscription", "notifyDeviceSettingChanger execute key:", str);
                CallBack callBack = (CallBack) DeviceSettingSubscription.this.o.get(str);
                if (callBack != null) {
                    callBack.onCall(deviceInfo, str, str2);
                }
            }
        });
    }

    public void a() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.e);
        } catch (IllegalArgumentException unused) {
            LogUtil.h("DeviceSettingSubscription", "unregisterReceiver mConnectStateChangedReceiver is exception");
        }
        try {
            BaseApplication.getContext().unregisterReceiver(this.j);
        } catch (IllegalArgumentException unused2) {
            LogUtil.h("DeviceSettingSubscription", "unregisterReceiver mHiHealthServiceReceiver is exception");
        }
        if (koq.c(this.i)) {
            HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.i, new HiUnSubscribeListener() { // from class: kkb
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("DeviceSettingSubscription", " isSuccess=", Boolean.valueOf(z));
                }
            });
        }
    }
}
