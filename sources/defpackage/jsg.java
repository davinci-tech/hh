package defpackage;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.gson.Gson;
import com.huawei.callback.DeviceStatusCallback;
import com.huawei.datatype.DeviceStatusParam;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder;
import com.huawei.hwdevice.phoneprocess.mgr.btproxy.BtProxyNetworkChangeReceiver;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.PhoneJobService;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.syncmgr.SyncOption;
import com.huawei.ui.commonui.R$string;
import defpackage.jsg;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class jsg {
    private static BtProxyNetworkChangeReceiver c = null;
    public static boolean e = false;
    private static Map<String, DeviceStatusCallback> b = new ConcurrentHashMap(16);
    private static String d = "";

    public static void d(String str, DeviceStatusCallback deviceStatusCallback) {
        LogUtil.a("ConnectManager", "Enter registerLocalDeviceStatusCallback method. moduleName: ", str);
        if (str == null || deviceStatusCallback == null) {
            LogUtil.h("ConnectManager", "registerLocalDeviceStatusCallback empty parameter");
        } else {
            b.put(str, deviceStatusCallback);
        }
    }

    public static void e(DeviceInfo deviceInfo, DeviceStatusParam deviceStatusParam) {
        boolean z;
        if (deviceInfo == null) {
            LogUtil.h("ConnectManager", "deviceInfo is null");
            return;
        }
        boolean c2 = cwi.c(deviceInfo, 183);
        LogUtil.a("ConnectManager", "initDeviceConnectInfo isSupportActivate: ", Boolean.valueOf(c2));
        if (deviceInfo.getDeviceConnectState() == 3) {
            kck.c().a();
            jwc.a().d(deviceInfo);
            boolean c3 = cwi.c(deviceInfo, 60, MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA);
            LogUtil.a("ConnectManager", "device not connect isSupportBtproxy: ", Boolean.valueOf(c3));
            for (DeviceInfo deviceInfo2 : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "btproxy")) {
                if (deviceInfo2.getDeviceConnectState() == 2 && (cwi.c(deviceInfo2, 60, MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA) || deviceInfo2.getProductType() == 57)) {
                    z = false;
                    break;
                }
            }
            z = true;
            LogUtil.a("ConnectManager", "isNeedStopService: ", Boolean.valueOf(z));
            if (z && (deviceInfo.getProductType() == 57 || c3)) {
                juj.d().a();
                b();
            }
            khs.k();
            kih.e().e(deviceInfo.getDeviceIdentify(), 20230830);
        }
        jwy.b().e(deviceInfo);
        b(deviceInfo, deviceStatusParam);
        if (deviceInfo.getDeviceConnectState() == 1) {
            juu.f(deviceInfo.getProductType());
        }
        d(deviceInfo);
        m(deviceInfo);
        e(deviceInfo);
        g(deviceInfo);
        ker.a().b();
        k(deviceInfo);
        n(deviceInfo);
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("ConnectManager", "initMenstrualCapability deviceInfo not connected");
            jrd.d(false);
            ket.a().c();
            a(deviceInfo);
            r(deviceInfo);
            return;
        }
        kdl.c().d();
        kck.c().d();
        h(deviceInfo);
        b(deviceInfo);
        WatchFaceUtil.deleteWatchFaceAlbumBackgroundDir();
        e();
        if (!c2 || TextUtils.isEmpty(deviceInfo.getPsiSignature())) {
            return;
        }
        kih.e().c(deviceInfo);
    }

    private static void n(DeviceInfo deviceInfo) {
        LogUtil.a("ConnectManager", "initSmartGestureRegister enter");
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 1) {
            jwe.e().c();
        } else if (deviceConnectState == 3 || deviceConnectState == 4) {
            jwe.e().a();
        }
    }

    private static void k(DeviceInfo deviceInfo) {
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 1) {
            jtw.e().a();
        } else if (deviceConnectState == 3 || deviceConnectState == 4) {
            jtw.e().c();
        }
    }

    private static void a(DeviceInfo deviceInfo) {
        if (Utils.m() || deviceInfo.getIsDemoWatch() != 1 || Utils.o() || deviceInfo.getDeviceConnectState() != 4) {
            return;
        }
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jsh
            @Override // java.lang.Runnable
            public final void run() {
                Toast.makeText(BaseApplication.getContext(), com.huawei.haf.application.BaseApplication.e().getResources().getString(R$string.IDS_device_mgr_demo_watch_connect_failed), 1).show();
            }
        });
    }

    private static void e() {
        if (e) {
            return;
        }
        e = true;
        LogUtil.a("ConnectManager", "initChargingService start");
        try {
            JobInfo.Builder requiresDeviceIdle = new JobInfo.Builder(10009, new ComponentName(BaseApplication.getContext(), (Class<?>) PhoneJobService.class)).setRequiresCharging(true).setRequiresDeviceIdle(false);
            if (BaseApplication.getContext().getSystemService("jobscheduler") instanceof JobScheduler) {
                LogUtil.a("ConnectManager", "initChargingService schedule ", Integer.valueOf(((JobScheduler) BaseApplication.getContext().getSystemService("jobscheduler")).schedule(requiresDeviceIdle.build())));
            }
        } catch (IllegalArgumentException e2) {
            LogUtil.b("ConnectManager", "initChargingService ", ExceptionUtils.d(e2));
        }
    }

    private static void e(DeviceInfo deviceInfo) {
        if (Utils.i()) {
            if (deviceInfo.getDeviceConnectState() == 3) {
                jul.d();
            } else if (deviceInfo.getDeviceConnectState() == 2) {
                jul.e(deviceInfo);
            }
        }
    }

    private static void m(DeviceInfo deviceInfo) {
        if (Utils.i() && cwi.c(deviceInfo, 169)) {
            LogUtil.a("ConnectManager", "DEVICE_SUPPORT_LOSE_WEIGHT");
            if (deviceInfo.getDeviceConnectState() == 3) {
                kfu.b();
            } else if (deviceInfo.getDeviceConnectState() == 2) {
                kfu.e();
            }
        }
    }

    private static void g(DeviceInfo deviceInfo) {
        LogUtil.a("ConnectManager", "initHealthGlanceManagerModule");
        if (deviceInfo.getDeviceConnectState() == 3) {
            kci.d();
        } else if (deviceInfo.getDeviceConnectState() == 2) {
            kci.c();
        }
    }

    private static void b(final DeviceInfo deviceInfo) {
        LogUtil.a("ConnectManager", "connectStartSyncData enter.");
        ThreadPoolManager.d().execute(new Runnable() { // from class: jsg.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("ConnectManager", " Disconnected and reconnected trigger Sync CoreSleep");
                nhu.c().startSynCoreSleep(SyncOption.builder().c(true).b(true).c(), new IBaseResponseCallback() { // from class: jsg.2.5
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("ConnectManager", "handleConnectStateChanged errorCode=", Integer.valueOf(i));
                        if (i == 0) {
                            jsg.s(DeviceInfo.this);
                        }
                    }
                });
                nhu.h().syncStressDetailData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void s(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("ConnectManager", "syncBasicData deviceInfo is null");
        } else {
            jwy.b().e(false, deviceInfo, new IBaseResponseCallback() { // from class: jsg.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i != 0) {
                        LogUtil.h("ConnectManager", "syncBasicData errorCode: ", Integer.valueOf(i));
                    } else {
                        ThreadPoolManager.d().execute(new Runnable() { // from class: jsg.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LogUtil.a("ConnectManager", "syncCloud start");
                                HiSyncOption hiSyncOption = new HiSyncOption();
                                hiSyncOption.setSyncModel(2);
                                hiSyncOption.setSyncAction(0);
                                hiSyncOption.setSyncDataType(20000);
                                hiSyncOption.setSyncMethod(2);
                                hiSyncOption.setSyncScope(1);
                                HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
                                LogUtil.a("ConnectManager", "syncCloud end");
                            }
                        });
                    }
                }
            });
        }
    }

    private static void d(DeviceInfo deviceInfo) {
        if (Utils.o() || !tri.d(BaseApplication.getContext())) {
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2 && deviceInfo.getDeviceConnectState() != 3) {
            LogUtil.h("ConnectManager", "deviceUpload device not connected or disconnect.");
        } else {
            ProfileAgent.PROFILE_AGENT.connectProfile(new AnonymousClass3(deviceInfo));
        }
    }

    /* renamed from: jsg$3, reason: invalid class name */
    class AnonymousClass3 implements ServiceConnectCallback {
        final /* synthetic */ DeviceInfo d;

        AnonymousClass3(DeviceInfo deviceInfo) {
            this.d = deviceInfo;
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            LogUtil.a("ConnectManager", "deviceUpload profile connected");
            ProfileAgent.PROFILE_AGENT.setConnected(true);
            ThreadPoolManager d = ThreadPoolManager.d();
            final DeviceInfo deviceInfo = this.d;
            d.execute(new Runnable() { // from class: jsi
                @Override // java.lang.Runnable
                public final void run() {
                    jsg.AnonymousClass3.d(DeviceInfo.this);
                }
            });
        }

        static /* synthetic */ void d(DeviceInfo deviceInfo) {
            if (deviceInfo != null) {
                jqf jqfVar = new jqf();
                jqfVar.d(deviceInfo, jqfVar.a());
                ProfileAgent.PROFILE_AGENT.disconnectProfile();
            }
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            ProfileAgent.PROFILE_AGENT.setConnected(false);
            LogUtil.a("ConnectManager", "deviceUpload profile disconnected");
        }
    }

    private static void h(DeviceInfo deviceInfo) {
        i(deviceInfo);
        t(deviceInfo);
        if ("main_relationship".equals(deviceInfo.getRelationship())) {
            jtx.c(deviceInfo);
        }
        r(deviceInfo);
        q(deviceInfo);
        j(deviceInfo);
        if (cwi.c(deviceInfo, 96)) {
            kfo.a().e();
        }
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (d2 != null && d2.isSupportSyncWifi() && CommonUtil.ar()) {
            kfv.d().a();
        }
        if (d2 != null && deviceInfo.getDeviceConnectState() == 2) {
            LogUtil.a("ConnectManager", "device：" + deviceInfo.getDeviceName() + "btType：" + deviceInfo.getDeviceBluetoothType() + "getDeviceCapability：", new Gson().toJson(d2, DeviceCapability.class));
        }
        LogUtil.a("ConnectManager", "initProcessAfterDeviceConnect triggerByDeviceConnect");
        kff.c().d();
        jsd.d(deviceInfo);
        if (EnvironmentInfo.r()) {
            a();
        }
        if (!Utils.o() && cwi.c(deviceInfo, 97)) {
            kih.e().d(deviceInfo);
        }
        o(deviceInfo);
    }

    private static void o(DeviceInfo deviceInfo) {
        khe.b().b(deviceInfo);
        khs.k();
        LogUtil.a("ConnectManager", "connected success save connect_wear is true");
        NotificationContentProviderUtil.b("connect_wear", 1);
        khu.a(BaseApplication.getContext()).c(khs.d(deviceInfo), deviceInfo);
        SharedPreferenceManager.e("SUPPORT_NOTIFY_LIVE_TYPE", "SUPPORT_NOTIFY_LIVE_TYPE", khs.g());
        SharedPreferenceManager.e("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", khs.i());
        LogUtil.a("ConnectManager", "notificationServiceHandle save capability data!");
    }

    private static void j(DeviceInfo deviceInfo) {
        jwc.a().c(deviceInfo);
    }

    private static void a() {
        LogUtil.a("ConnectManager", "sendUpdateTimeToDevice start.");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String format = simpleDateFormat.format(new Date());
        if ("".equals(d)) {
            LogUtil.a("ConnectManager", "sLastSyncTime is empty string.");
            d = format;
            d();
            return;
        }
        try {
            Date parse = simpleDateFormat2.parse(format);
            Date parse2 = simpleDateFormat2.parse(d);
            long time = parse.getTime() - parse2.getTime();
            LogUtil.a("ConnectManager", "currentTime:", parse, ",mLaterTime:", parse2, ",timeTap:", Long.valueOf(time));
            if ((time / 1000) / 60 <= 25) {
                LogUtil.h("ConnectManager", "the interval is less than 25 minutes.");
                d = format;
            } else {
                d = format;
                d();
            }
        } catch (ParseException unused) {
            d = format;
        }
    }

    private static void d() {
        long c2 = kkn.c();
        if (c2 <= 0 || c2 > k.b.m) {
            return;
        }
        kkn.b(c2);
    }

    private static void b() {
        BtProxyNetworkChangeReceiver btProxyNetworkChangeReceiver = c;
        if (btProxyNetworkChangeReceiver != null) {
            btProxyNetworkChangeReceiver.f();
        }
    }

    private static void q(DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 60, MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA);
        LogUtil.a("ConnectManager", "isSupportBtproxy: ", Boolean.valueOf(c2));
        if (deviceInfo.getDeviceConnectState() != 2 || deviceInfo.getPowerSaveModel() == 1) {
            return;
        }
        LogUtil.a("ConnectManager", "deviceInfo.getProductType: ", Integer.valueOf(deviceInfo.getProductType()));
        if (deviceInfo.getProductType() == 57 || c2) {
            juj.d().a();
            b();
            BtProxyNetworkChangeReceiver c3 = BtProxyNetworkChangeReceiver.c();
            c = c3;
            c3.e(deviceInfo);
        }
    }

    private static void i(DeviceInfo deviceInfo) {
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (d2 == null || !d2.isSupportMenstrual()) {
            return;
        }
        LogUtil.a("ConnectManager", "initMenstrualCapability sendMenstrualCapability");
        new jri().c(new IBaseResponseCallback() { // from class: jsg.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("ConnectManager", "queryMenstrualSwitch errorCode:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof MenstrualSwitchStatus)) {
                    LogUtil.a("ConnectManager", "device connected sendMenstrualSwitch");
                    kcu.d().a((MenstrualSwitchStatus) obj);
                }
            }
        });
        kcu.d().b();
    }

    private static void t(DeviceInfo deviceInfo) {
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (d2 == null || !d2.isSupportSosTransmission()) {
            return;
        }
        LogUtil.a("ConnectManager", "capability supportSosTransmission");
        kdk.a().b(deviceInfo);
    }

    private static void b(DeviceInfo deviceInfo, DeviceStatusParam deviceStatusParam) {
        p(deviceInfo);
        l(deviceInfo);
        f(deviceInfo);
        kiv.b().e(deviceInfo);
        c(deviceInfo, deviceStatusParam);
    }

    private static void p(DeviceInfo deviceInfo) {
        LogUtil.a("ConnectManager", "notifyContactSyncMgr: ");
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (d2 == null || !d2.isSupportSyncContacts() || !d2.isSupportSyncHiCall()) {
            LogUtil.h("ConnectManager", "notifyContactSyncMgr: not support sync contacts");
            return;
        }
        jza c2 = jza.c();
        if (deviceInfo.getDeviceConnectState() == 2) {
            c2.a(deviceInfo);
        } else {
            c2.c(deviceInfo);
        }
    }

    private static void l(DeviceInfo deviceInfo) {
        LogUtil.a("ConnectManager", "notifyCloudFileConfigManager ");
        if (!cwi.c(deviceInfo, 104)) {
            LogUtil.h("ConnectManager", "not support CloudFileConfigManager");
            return;
        }
        jzd b2 = jzd.b();
        if (deviceInfo.getDeviceConnectState() != 2) {
            b2.e(deviceInfo);
        }
    }

    private static void r(DeviceInfo deviceInfo) {
        LogUtil.a("ConnectManager", "enter sendToThemeConnectInfo");
        if (jst.d(deviceInfo.getDeviceIdentify()).isSupportWatchFaceAppId()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("mDeviceConnectState", deviceInfo.getDeviceConnectState());
                jSONObject.put("mDeviceName", deviceInfo.getDeviceName());
            } catch (JSONException unused) {
                LogUtil.b("ConnectManager", "onDeviceConnectionStateChangedMethod, JSONException");
            }
            KitWearBinder.handleConnectStateReceive(jSONObject.toString());
        }
    }

    private static void f(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceConnectState() == 2 && cwi.c(deviceInfo, 112)) {
            kcf.b();
        }
    }

    private static void c(DeviceInfo deviceInfo, DeviceStatusParam deviceStatusParam) {
        Iterator<Map.Entry<String, DeviceStatusCallback>> it = b.entrySet().iterator();
        if (it == null) {
            LogUtil.a("ConnectManager", "notifyConnectStateViaCallback iterator is null.");
            return;
        }
        while (it.hasNext()) {
            DeviceStatusCallback value = it.next().getValue();
            if (value != null) {
                value.onDeviceConnectedChanged(deviceInfo, deviceInfo.getDeviceConnectState(), deviceStatusParam);
            }
        }
    }
}
