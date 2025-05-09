package defpackage;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean.WatchFaceListBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.device.activity.notification.NotificationSettingActivity;
import com.huawei.ui.device.activity.update.BandUpdateDialogActivity;
import com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class owl {
    private static boolean b = false;
    private static volatile owl c = null;
    private static boolean e = false;
    private Handler f;
    private ThreadPoolManager g;
    private Context i;
    private boolean o = false;
    private Handler y = new e(this);
    private String t = "";

    /* renamed from: a, reason: collision with root package name */
    private String f15984a = "";
    private int r = 0;
    private String s = "";
    private boolean m = false;
    private String j = "";
    private boolean l = false;
    private boolean k = false;
    private String n = null;
    private CustomTextAlertDialog q = null;
    private final BroadcastReceiver p = new BroadcastReceiver() { // from class: owl.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("DeviceInitUtil", "receive notify Bi broadcast");
            if (intent == null) {
                LogUtil.h("DeviceInitUtil", "mNotifyBiReportReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("DeviceInitUtil", "mNotifyBiReportReceiver(): intent: ", action);
            if ("com.huawei.health.nitification_service_bi_change".equals(action)) {
                int intExtra = intent.getIntExtra("BIStatus", -1);
                if (intExtra == -1) {
                    LogUtil.h("DeviceInitUtil", "BIStatus is -1");
                    return;
                }
                String str = Build.BRAND;
                HashMap hashMap = new HashMap(16);
                hashMap.put(JsbMapKeyNames.H5_BRAND, str);
                hashMap.put("code", Integer.valueOf(intExtra));
                ixx.d().d(owl.this.i, AnalyticsValue.NOTIFY_SERVICE_ENABLE_1090027.value(), hashMap, 0);
            }
        }
    };
    private final BroadcastReceiver v = new BroadcastReceiver() { // from class: owl.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("DeviceInitUtil", "receive weather BI broadcast");
            if (intent == null) {
                LogUtil.h("DeviceInitUtil", "mWeatherBiReportReceiver onReceive intent is null");
                return;
            }
            if (intent.getAction() == null || !intent.getAction().equals("com.huawei.health.action.ACTION_HWWEATHER_BI_CHANGE")) {
                return;
            }
            LogUtil.a("DeviceInitUtil", "mWeatherBiReportReceiver() intent: ", intent.getAction());
            int intExtra = intent.getIntExtra("BIStatus", 0);
            if (intExtra != 0) {
                owl.this.b(intExtra);
            } else {
                LogUtil.h("DeviceInitUtil", "status is 0");
            }
        }
    };
    private final BroadcastReceiver h = new BroadcastReceiver() { // from class: owl.10
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handler = owl.this.f;
            if (intent == null || handler == null) {
                LogUtil.h("DeviceInitUtil", "mDeviceStatusReceiver onReceive param is null");
                return;
            }
            LogUtil.a("DeviceInitUtil", "Achievement device broadcast intent: ", intent.getAction());
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                try {
                    Message obtainMessage = handler.obtainMessage();
                    if (!(intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo)) {
                        LogUtil.h("DeviceInitUtil", "ParcelableExtra Data is not DeviceInfo");
                        return;
                    }
                    DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                    if (deviceInfo == null) {
                        LogUtil.h("DeviceInitUtil", "deviceInfo is null");
                        return;
                    }
                    if (deviceInfo.getDeviceConnectState() == 2 && !Utils.o()) {
                        LogUtil.a("DeviceInitUtil", "connected");
                        obtainMessage.what = 5;
                        obtainMessage.obj = deviceInfo;
                    } else {
                        LogUtil.h("DeviceInitUtil", "connect failed because status: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                        obtainMessage.what = 6;
                    }
                    handler.sendMessage(obtainMessage);
                } catch (ClassCastException unused) {
                    LogUtil.b("DeviceInitUtil", "DeviceInfo deviceInfo ClassCastException");
                }
            }
        }
    };
    private final BroadcastReceiver d = new BroadcastReceiver() { // from class: owl.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                Handler handler = owl.this.f;
                if (handler == null) {
                    LogUtil.h("DeviceInitUtil", "mAutoCheckNewVersionReceiver onReceive handler is null");
                    return;
                }
                String action = intent.getAction();
                LogUtil.a("DeviceInitUtil", "mAutoCheckNewVersionReceiver onReceive: content: ", intent.getStringExtra("content"));
                if ("action_band_auto_check_new_version_result".equals(action)) {
                    int intExtra = intent.getIntExtra("result", 8);
                    LogUtil.a("DeviceInitUtil", "mAutoCheckNewVersionReceiver result: ", Integer.valueOf(intExtra));
                    if (intExtra == 14) {
                        LogUtil.a("DeviceInitUtil", "AUTO_CHECK_AW70_BAND_SUCCESS");
                        owl.this.diT_(context, intent, handler);
                        return;
                    } else if (intExtra == 15) {
                        owl.this.diS_(intent);
                        return;
                    } else {
                        LogUtil.h("DeviceInitUtil", "default result", Integer.valueOf(intExtra));
                        return;
                    }
                }
                return;
            }
            LogUtil.h("DeviceInitUtil", "mAutoCheckNewVersionReceiver onReceive intent is null");
        }
    };

    private owl(Context context) {
        this.i = context;
    }

    public static owl b(Context context) {
        if (c == null) {
            synchronized (owl.class) {
                if (c == null) {
                    c = new owl(context);
                }
            }
        }
        return c;
    }

    public static void c() {
        LogUtil.a("DeviceInitUtil", "release DeviceInitUtil instance");
        synchronized (owl.class) {
            if (c != null) {
                c = null;
            }
        }
    }

    public void a() {
        this.f = new d(this);
        r();
        s();
        v();
        this.f.sendEmptyMessage(9);
        x();
        u();
    }

    public void b() {
        ad();
        aa();
        ab();
        if (CommonUtil.ce()) {
            ac();
        }
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f = null;
        }
    }

    public void h() {
        LogUtil.a("DeviceInitUtil", "registUpdateState()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_band_auto_check_new_version_result");
        BroadcastManagerUtil.bFA_(this.i, this.d, intentFilter, LocalBroadcast.c, null);
    }

    private void x() {
        LogUtil.a("DeviceInitUtil", "restartNotifyService");
        Handler handler = this.y;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 5;
            this.y.sendMessageDelayed(obtainMessage, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    static class e extends BaseHandler<owl> {
        e(owl owlVar) {
            super(owlVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: diW_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(owl owlVar, Message message) {
            int i = message.what;
            if (i == 5) {
                owlVar.i();
            } else if (i == 7) {
                owlVar.f();
            } else {
                LogUtil.h("DeviceInitUtil", "handleMessage else");
            }
        }
    }

    static class b implements Runnable {
        private WeakReference<owl> e;

        b(owl owlVar) {
            this.e = new WeakReference<>(owlVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            owl owlVar = this.e.get();
            if (owlVar == null) {
                return;
            }
            LogUtil.a("DeviceInitUtil", "check notification is enable");
            if (CommonUtil.ab(BaseApplication.e())) {
                owlVar.b(1);
                return;
            }
            LogUtil.h("DeviceInitUtil", "check notification is disabled");
            owlVar.b(0);
            CommonUtil.aj(BaseApplication.e());
            Handler handler = owlVar.y;
            if (handler != null) {
                handler.removeMessages(7);
                handler.sendEmptyMessageDelayed(7, 20000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (!this.o) {
            if (oxa.a() == null) {
                LogUtil.h("DeviceInitUtil", "checkNotifyService DeviceStateInteractors instance is null");
                return;
            }
            DeviceInfo f = oxa.a().f();
            if (f != null && f.getDeviceConnectState() == 2) {
                DeviceCapability e2 = DeviceSettingsInteractors.d(this.i).e();
                NotificationPushInteractor notificationPushInteractor = new NotificationPushInteractor(this.i);
                if (e2 != null && e2.isMessageAlert() && notificationPushInteractor.b()) {
                    ThreadPoolManager.d().c("DeviceInitUtil", new b(this));
                    this.o = true;
                    return;
                }
                return;
            }
            LogUtil.h("DeviceInitUtil", "onResume is checking notification not connected");
            return;
        }
        LogUtil.h("DeviceInitUtil", "onResume is checking notification");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("DeviceInitUtil", "enter checkIsNotifyEnable");
        this.o = false;
        if (CommonUtil.ab(this.i)) {
            LogUtil.a("DeviceInitUtil", "Notify service is running");
            a(1);
            return;
        }
        LogUtil.h("DeviceInitUtil", "Notify service is disabled,send broadcast");
        a(0);
        if (CommonUtil.bh()) {
            LogUtil.h("DeviceInitUtil", "checkIsNotifyEnable isHuaweiSystem");
            return;
        }
        if (CommonUtil.o(this.i) == 30) {
            LogUtil.h("DeviceInitUtil", "checkIsNotifyEnable getRunningServicesSize is 30");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.NPL_SERVICE_NOT_AVALIABLE");
        intent.setPackage(this.i.getPackageName());
        this.i.sendBroadcast(intent, LocalBroadcast.c);
    }

    private void a(int i) {
        String str = Build.BRAND;
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put(JsbMapKeyNames.H5_BRAND, str);
        linkedHashMap.put("code", String.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.NOTIFY_SERVICE_ENABLE_1090028.value(), linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void diS_(Intent intent) {
        String stringExtra = intent.getStringExtra("uniqueId");
        String stringExtra2 = intent.getStringExtra("productId");
        MeasurableDevice d2 = ceo.d().d(stringExtra, false);
        if (d2 != null) {
            if (d2 instanceof ctk) {
                if (((ctk) d2).b().k() != 1) {
                    LogUtil.a("DeviceInitUtil", "is WiFiDevice but not main user.");
                    return;
                }
            } else {
                if (!TextUtils.isEmpty(stringExtra2) && "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(stringExtra2)) {
                    LogUtil.a("DeviceInitUtil", "hagrid device unconfigured network");
                    return;
                }
                LogUtil.h("DeviceInitUtil", "device not instanceof WiFiDevice");
            }
        } else {
            LogUtil.h("DeviceInitUtil", "switchToSubUserWifiPage, no bond device");
        }
        oaj.a().a(intent.getStringExtra("uniqueId"));
        diU_(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void diT_(Context context, Intent intent, Handler handler) {
        if (e) {
            LogUtil.h("DeviceInitUtil", "sIsHandleCheckSuccessAw70, do nothing");
            return;
        }
        e = true;
        handler.sendEmptyMessageDelayed(200, 5000L);
        this.k = true;
        String stringExtra = intent.getStringExtra("name");
        int intExtra = intent.getIntExtra("size", -1);
        String stringExtra2 = intent.getStringExtra("changelog");
        DeviceInfo d2 = jpu.d("DeviceInitUtil");
        if (d2 == null) {
            LogUtil.h("DeviceInitUtil", "AUTO_CHECK_AW70_BAND_SUCCESS AW70 deviceInfo is null");
            return;
        }
        String deviceName = d2.getDeviceName();
        this.n = d2.getDeviceIdentify();
        LogUtil.a("DeviceInitUtil", "AUTO_CHECK_AW70_BAND_SUCCESS checkBandNewVersionName: ", stringExtra, " checkBandNewVersionSize: ", Integer.valueOf(intExtra), " bandChangelog: ", stringExtra2, " aw70DeviceName: ", deviceName);
        a(stringExtra, intExtra, stringExtra2, deviceName, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ThreadPoolManager.d().d("cacheImage", new Runnable() { // from class: owl.6
            @Override // java.lang.Runnable
            public void run() {
                DeviceInfo f;
                DeviceSettingsInteractors d2;
                if (!CommonUtil.ce() || (f = oxa.a().f()) == null || f.getDeviceConnectState() != 2 || (d2 = DeviceSettingsInteractors.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) == null || d2.e() == null) {
                    return;
                }
                owl.this.c(d2, f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceSettingsInteractors deviceSettingsInteractors, DeviceInfo deviceInfo) {
        WatchFaceListBean watchFaceListBean;
        if (deviceSettingsInteractors.e().isSupportWatchFace() && SharedPreferenceManager.i(this.i).equals(deviceInfo.getDeviceIdentify()) && SharedPreferenceManager.f(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
            try {
                watchFaceListBean = (WatchFaceListBean) new Gson().fromJson(SharedPreferenceManager.j(com.huawei.hwcommonmodel.application.BaseApplication.getContext()), WatchFaceListBean.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.b("DeviceInitUtil", "judgeCacheImageCondition JsonSyntaxException");
                watchFaceListBean = null;
            }
            if (watchFaceListBean == null) {
                return;
            }
            List<WatchFaceListBean.WatchFaceBean> watchFaceBeanList = watchFaceListBean.getWatchFaceBeanList();
            if (watchFaceBeanList == null || watchFaceBeanList.size() == 0) {
                LogUtil.h("DeviceInitUtil", "data is not suitable");
                return;
            }
            String fileHost = watchFaceListBean.getFileHost();
            for (WatchFaceListBean.WatchFaceBean watchFaceBean : watchFaceBeanList) {
                StringBuilder sb = new StringBuilder(16);
                sb.append(fileHost);
                sb.append(watchFaceBean.getId());
                sb.append("/");
                sb.append(watchFaceBean.getLogo());
                nrf.a(this.i, sb.toString());
            }
        }
    }

    private void u() {
        if (CommonUtil.ce()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: owl.12
                @Override // java.lang.Runnable
                public void run() {
                    DeviceInfo f = oxa.a().f();
                    if (f == null || f.getDeviceConnectState() != 2) {
                        return;
                    }
                    oxa.a().d();
                    if (owl.this.f == null) {
                        return;
                    }
                    Handler handler = owl.this.f;
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.obj = f;
                    obtainMessage.what = 10;
                    handler.sendMessage(obtainMessage);
                    if (CommonUtil.x(owl.this.i)) {
                        return;
                    }
                    if (!kwx.c()) {
                        owl.this.w();
                        owl.this.a(f.getDeviceIdentify());
                    } else {
                        LogUtil.a("DeviceInitUtil", "resume is Sporting");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        this.t = KeyValDbManager.b(this.i).e("new_band_version");
        this.f15984a = KeyValDbManager.b(this.i).e("new_band_version_type");
        String e2 = KeyValDbManager.b(this.i).e("new_band_size");
        this.s = KeyValDbManager.b(this.i).e("new_band_change_log");
        String e3 = KeyValDbManager.b(this.i).e("is_new_checkbox");
        String e4 = KeyValDbManager.b(this.i).e("is_need_show_dialog");
        String e5 = KeyValDbManager.b(this.i).e("need_show_dialog_time");
        this.j = KeyValDbManager.b(this.i).e("need_show_dialog_device_name");
        this.n = KeyValDbManager.b(this.i).e("need_show_dialog_mac");
        LogUtil.a("DeviceInitUtil", "isNeedShowDialog ", e4, "needShowDialogTime", e5);
        if (Boolean.parseBoolean(e4) && kxz.c(e5)) {
            this.r = CommonUtil.a(e2, 10);
            this.m = Boolean.parseBoolean(e3);
            this.l = true;
        }
    }

    private void s() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
        BroadcastManagerUtil.bFC_(this.i, this.h, intentFilter, LocalBroadcast.c, null);
    }

    private void ad() {
        try {
            this.i.unregisterReceiver(this.h);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("DeviceInitUtil", "unregisterGetDeviceStatusBroadcast IllegalArgumentException: ", e2.getMessage());
        } catch (RuntimeException e3) {
            LogUtil.b("DeviceInitUtil", "unregisterGetDeviceStatusBroadcast RuntimeException: ", e3.getMessage());
        }
    }

    private void r() {
        LogUtil.a("DeviceInitUtil", "register weather report Bi broadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.ACTION_HWWEATHER_BI_CHANGE");
        BroadcastManagerUtil.bFA_(this.i, this.v, intentFilter, LocalBroadcast.c, null);
    }

    private void aa() {
        LogUtil.a("DeviceInitUtil", "unregister weather report Bi broadcast");
        try {
            this.i.unregisterReceiver(this.v);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("DeviceInitUtil", "unregisterHwWeatherBiBroadcast IllegalArgumentException: ", e2.getMessage());
        } catch (RuntimeException e3) {
            LogUtil.b("DeviceInitUtil", "unregisterHwWeatherBiBroadcast RuntimeException: ", e3.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, "DeviceInitUtil");
        if (deviceList != null && !deviceList.isEmpty()) {
            for (DeviceInfo deviceInfo2 : deviceList) {
                if ("main_relationship".equals(deviceInfo2.getRelationship()) || cvt.c(deviceInfo2.getProductType())) {
                    deviceInfo = deviceInfo2;
                    break;
                }
            }
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("isEMUI", String.valueOf(CommonUtil.bh() ? 1 : 0));
        if (deviceInfo != null) {
            linkedHashMap.put("deviceName", jfu.c(deviceInfo.getProductType(), deviceInfo.getDeviceName(), false));
        }
        linkedHashMap.put("error_type", String.valueOf(i));
        OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.WEATHER_PUSH_1090012.value(), linkedHashMap);
    }

    private void v() {
        LogUtil.a("DeviceInitUtil", "register notifySendData report Bi broadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.nitification_service_bi_change");
        BroadcastManagerUtil.bFC_(this.i, this.p, intentFilter, LocalBroadcast.c, null);
    }

    private void ab() {
        LogUtil.a("DeviceInitUtil", "unregister notify report Bi broadcast");
        try {
            Context context = this.i;
            if (context != null) {
                context.unregisterReceiver(this.p);
            }
        } catch (IllegalArgumentException e2) {
            LogUtil.b("DeviceInitUtil", "unregisterNotifyBiBroadcast IllegalArgumentException", e2.getMessage());
        }
    }

    static class d extends BaseHandler<owl> {
        d(owl owlVar) {
            super(owlVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: diV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(owl owlVar, Message message) {
            if (message == null) {
                LogUtil.h("DeviceInitUtil", "handleMessageWhenReferenceNotNull msg is null");
                return;
            }
            int i = message.what;
            if (i == 5) {
                if (!CommonUtil.ag(owlVar.i) && knx.e() && (message.obj instanceof DeviceInfo)) {
                    owl.a(owlVar, (DeviceInfo) message.obj);
                    return;
                }
                return;
            }
            if (i == 100) {
                boolean unused = owl.b = false;
                LogUtil.a("DeviceInitUtil", "MESSAGE_WEAR_DEVICE_CHECK_SUCCESS, sIsHandleCheckSuccess: ", Boolean.valueOf(owl.b));
                return;
            }
            if (i == 200) {
                boolean unused2 = owl.e = false;
                LogUtil.a("DeviceInitUtil", "MESSAGE_AW70_CHECK_SUCCESS, sIsHandleCheckSuccessAw70: ", Boolean.valueOf(owl.e));
                return;
            }
            if (i == 9) {
                owlVar.j();
                return;
            }
            if (i == 10) {
                DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
                if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
                    return;
                }
                owlVar.q();
                owlVar.e(deviceInfo);
                return;
            }
            LogUtil.h("DeviceInitUtil", "handleMessageWhenReferenceNotNull else");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final owl owlVar, DeviceInfo deviceInfo) {
        if (owlVar.g == null) {
            owlVar.g = ThreadPoolManager.d();
        }
        if (deviceInfo != null) {
            final String deviceIdentify = deviceInfo.getDeviceIdentify();
            jsd.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), deviceIdentify);
            final String softVersion = deviceInfo.getSoftVersion();
            final String a2 = jsd.a(deviceInfo);
            owlVar.g.execute(new Runnable() { // from class: owl.14
                @Override // java.lang.Runnable
                public void run() {
                    if (jsd.h(deviceIdentify)) {
                        LogUtil.a("DeviceInitUtil", "start upload upg log.");
                        owlVar.d(softVersion, a2, deviceIdentify);
                        jsd.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), 2, deviceIdentify);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, String str3) {
        long c2 = SharedPreferenceManager.c(this.i, "EXCE_OTA_APP_UPG_TRANS_START");
        long c3 = SharedPreferenceManager.c(this.i, "EXCE_OTA_APP_UPG_TRANS_START");
        long c4 = SharedPreferenceManager.c(this.i, "EXCE_OTA_APP_UPG_TRANS_STOP_TIME");
        boolean z = c4 > 0 && c4 - c2 > 0;
        boolean z2 = c3 > 0 || c2 > 0;
        if (jsd.d(str3) && z2 && z) {
            jsd.e("0D0B01", "EXCE_OTA_APP_UPG_CONNECT_VERSION", str, str2);
        }
    }

    private void diU_(Intent intent) {
        LogUtil.a("DeviceInitUtil", "showScaleUpdateDialog ACTION_APP_AUTO_CHECK_NEW_VERSION_RESULT:AUTO_CHECK_SUCCESS BLE");
        String stringExtra = intent.getStringExtra("productId");
        if (CommonUtil.x(this.i)) {
            LogUtil.h("DeviceInitUtil", "dialog is background");
            return;
        }
        if (cjx.e().a(stringExtra) == null) {
            LogUtil.h("DeviceInitUtil", "getBondedDevice is null");
            return;
        }
        Intent intent2 = new Intent();
        int intExtra = intent.getIntExtra("size", -1);
        String stringExtra2 = intent.getStringExtra("changelog");
        intent2.putExtra("name", intent.getStringExtra("name"));
        intent2.putExtra("size", intExtra);
        intent2.putExtra("message", stringExtra2);
        intent2.putExtra(ParamConstants.CallbackMethod.ON_SHOW, false);
        intent2.putExtra("isScale", true);
        intent2.putExtra("productId", stringExtra);
        intent2.putExtra("uniqueId", intent.getStringExtra("uniqueId"));
        intent2.setClass(this.i, BandUpdateDialogActivity.class);
        try {
            this.i.startActivity(intent2);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("DeviceInitUtil", "jump to UpdateDialog " + e2.getMessage());
        }
    }

    private void a(String str, int i, String str2, String str3, boolean z) {
        if (jrd.b()) {
            LogUtil.h("DeviceInitUtil", "band is showing");
            return;
        }
        if (jkj.d().j()) {
            LogUtil.h("DeviceInitUtil", "band is transfering");
            return;
        }
        LogUtil.a("DeviceInitUtil", "showBandAutoCheckDialog version: ", str, " size: ", Integer.valueOf(i), " changeLog: ", str2, " deviceName: ", str3, " isShowCheckbox: ", Boolean.valueOf(z));
        if (CommonUtil.x(this.i)) {
            this.t = str;
            this.r = i;
            this.s = str2;
            this.m = z;
            this.l = true;
            LogUtil.a("DeviceInitUtil", "showBandAutoCheckDialog isBackground");
            return;
        }
        if (kwx.c()) {
            LogUtil.a("DeviceInitUtil", "resume is Sporting");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("name", str);
        intent.putExtra("size", i);
        intent.putExtra("message", str2);
        intent.putExtra(ParamConstants.CallbackMethod.ON_SHOW, z);
        intent.putExtra("isAW70", this.k);
        intent.putExtra("mac", this.n);
        intent.setClass(this.i, WearDeviceUpdateDialogActivity.class);
        try {
            this.i.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("DeviceInitUtil", "exception is" + e2.getMessage());
        }
    }

    private void ac() {
        LogUtil.a("DeviceInitUtil", "unregisterAppCheckBroadcast()");
        try {
            this.i.unregisterReceiver(this.d);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("DeviceInitUtil", "unregistUpdateState IllegalArgumentException: ", e2.getMessage());
        } catch (RuntimeException e3) {
            LogUtil.b("DeviceInitUtil", "unregistUpdateState RuntimeException: ", e3.getMessage());
        } catch (Exception unused) {
            LogUtil.b("DeviceInitUtil", "unregistUpdateState Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str) {
        if (this.l) {
            LogUtil.a("DeviceInitUtil", "Enter checkIsNeedShowDialog mIsNeedShowDialog");
            jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: owl.15
                /* JADX WARN: Removed duplicated region for block: B:18:0x0074  */
                /* JADX WARN: Removed duplicated region for block: B:20:0x007a  */
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void d(int r7, java.lang.Object r8) {
                    /*
                        r6 = this;
                        java.lang.String r0 = "checkIsNeedShowDialog errorCode = "
                        java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
                        java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                        java.lang.String r1 = "DeviceInitUtil"
                        com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
                        r0 = 0
                        if (r7 != 0) goto L2d
                        boolean r7 = r8 instanceof java.lang.String
                        if (r7 != 0) goto L20
                        java.lang.String r7 = "objectData is not String"
                        java.lang.Object[] r7 = new java.lang.Object[]{r7}
                        com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
                        return
                    L20:
                        java.lang.String r8 = (java.lang.String) r8
                        java.lang.String r7 = "2"
                        boolean r7 = r7.equals(r8)
                        if (r7 == 0) goto L2b
                        goto L2d
                    L2b:
                        r7 = 1
                        goto L2e
                    L2d:
                        r7 = r0
                    L2e:
                        boolean r8 = defpackage.jrd.e()
                        java.lang.Boolean r2 = java.lang.Boolean.valueOf(r7)
                        java.lang.String r3 = " isMobileTraffic "
                        java.lang.Boolean r4 = java.lang.Boolean.valueOf(r8)
                        java.lang.String r5 = "get auto ota checkbox status,isAutoUpdate isOpen: "
                        java.lang.Object[] r2 = new java.lang.Object[]{r5, r2, r3, r4}
                        com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
                        r2 = 0
                        java.lang.String r3 = "is_need_show_dialog"
                        if (r7 == 0) goto L64
                        if (r8 == 0) goto L4d
                        goto L64
                    L4d:
                        owl r7 = defpackage.owl.this
                        defpackage.owl.b(r7, r0)
                        owl r7 = defpackage.owl.this
                        android.content.Context r7 = defpackage.owl.e(r7)
                        health.compact.a.KeyValDbManager r7 = health.compact.a.KeyValDbManager.b(r7)
                        java.lang.String r8 = java.lang.String.valueOf(r0)
                        r7.d(r3, r8, r2)
                        goto L99
                    L64:
                        android.content.Context r7 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
                        com.huawei.hwversionmgr.manager.HwVersionManager r7 = com.huawei.hwversionmgr.manager.HwVersionManager.c(r7)
                        java.lang.String r8 = r2
                        boolean r7 = r7.n(r8)
                        if (r7 == 0) goto L7a
                        owl r7 = defpackage.owl.this
                        defpackage.owl.b(r7)
                        goto L99
                    L7a:
                        java.lang.String r7 = "checkIsNeedShowDialog have no new version"
                        java.lang.Object[] r7 = new java.lang.Object[]{r7}
                        com.huawei.hwlogsmodel.LogUtil.a(r1, r7)
                        owl r7 = defpackage.owl.this
                        defpackage.owl.b(r7, r0)
                        owl r7 = defpackage.owl.this
                        android.content.Context r7 = defpackage.owl.e(r7)
                        health.compact.a.KeyValDbManager r7 = health.compact.a.KeyValDbManager.b(r7)
                        java.lang.String r8 = java.lang.String.valueOf(r0)
                        r7.d(r3, r8, r2)
                    L99:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: defpackage.owl.AnonymousClass15.d(int, java.lang.Object):void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (HwVersionManager.c(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).o(this.n)) {
            LogUtil.a("DeviceInitUtil", "device is running ota update");
            this.l = false;
            KeyValDbManager.b(this.i).d("is_need_show_dialog", String.valueOf(false), null);
            return;
        }
        if (jrd.b()) {
            LogUtil.a("DeviceInitUtil", "device ota is showing");
            this.l = false;
            KeyValDbManager.b(this.i).d("is_need_show_dialog", String.valueOf(false), null);
            return;
        }
        LogUtil.a("DeviceInitUtil", "can show dialog");
        Intent intent = new Intent();
        intent.putExtra("name", this.t);
        intent.putExtra("type", this.f15984a);
        intent.putExtra("size", this.r);
        intent.putExtra("message", this.s);
        intent.putExtra(ParamConstants.CallbackMethod.ON_SHOW, this.m);
        intent.putExtra("show_device_name", this.j);
        intent.putExtra("mac", this.n);
        intent.setClass(this.i, WearDeviceUpdateDialogActivity.class);
        try {
            this.i.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("DeviceInitUtil", "ActivityNotFoundException", e2.getMessage());
        }
        this.l = false;
        KeyValDbManager.b(this.i).d("is_need_show_dialog", String.valueOf(false), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        DeviceSettingsInteractors d2 = DeviceSettingsInteractors.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
        if (d2 == null || d2.e() == null || !d2.e().isSupportMusicControl()) {
            return;
        }
        jjd.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
        LogUtil.a("DeviceInitUtil", "initManager music");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo) {
        LogUtil.a("DeviceInitUtil", "enter notificationAlert");
        DeviceCapability e2 = DeviceSettingsInteractors.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).e();
        if (e2 == null) {
            return;
        }
        LogUtil.a("DeviceInitUtil", "enter notificationAlert isMessageAlert: ", Boolean.valueOf(e2.isMessageAlert()));
        boolean h = CommonUtil.h(this.i, "com.huawei.health.MainActivity");
        LogUtil.a("DeviceInitUtil", "is MainActivity on Top  ", Boolean.valueOf(h));
        int productType = deviceInfo.getProductType();
        if (e2.isMessageAlert() && h && productType != 32) {
            boolean c2 = jjb.b().c();
            boolean p = p();
            boolean g = g();
            LogUtil.a("DeviceInitUtil", "isClosed: ", Boolean.valueOf(c2), "isAchieve: ", Boolean.valueOf(p), "isShownThreeTimes: ", Boolean.valueOf(g));
            if (!c2 && p && !g) {
                z();
                a(kxz.d(), this.i);
                c(this.i);
            } else {
                if (c2) {
                    CustomTextAlertDialog customTextAlertDialog = this.q;
                    if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
                        return;
                    }
                    this.q.dismiss();
                    LogUtil.a("DeviceInitUtil", "dismiss notification dialog");
                    return;
                }
                LogUtil.h("DeviceInitUtil", "handle dialog else");
            }
        }
    }

    private boolean p() {
        Date a2;
        String b2 = SharedPreferenceManager.b(this.i, String.valueOf(10000), "sp_dialog_check_time");
        LogUtil.a("DeviceInitUtil", "isEnableDialog lastTime: ", b2);
        if (TextUtils.isEmpty(b2) || (a2 = kxz.a(b2)) == null) {
            return true;
        }
        return Math.abs(System.currentTimeMillis() - a2.getTime()) > 86400000;
    }

    private boolean g() {
        String b2 = SharedPreferenceManager.b(this.i, String.valueOf(10000), "dialog_show_time");
        LogUtil.a("DeviceInitUtil", "countShowTimes number: ", b2);
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        try {
            return Integer.parseInt(b2) >= 2;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private void a(String str, Context context) {
        LogUtil.a("DeviceInitUtil", "setDialogCheckTime, time: ", str);
        SharedPreferenceManager.e(context, String.valueOf(10000), "sp_dialog_check_time", str, new StorageParams(0));
    }

    private void c(Context context) {
        StorageParams storageParams = new StorageParams(0);
        String b2 = SharedPreferenceManager.b(this.i, String.valueOf(10000), "dialog_show_time");
        LogUtil.a("DeviceInitUtil", "setDialogShowTime number: ", b2);
        if (TextUtils.isEmpty(b2)) {
            SharedPreferenceManager.e(context, String.valueOf(10000), "dialog_show_time", "0", storageParams);
            return;
        }
        try {
            SharedPreferenceManager.e(context, String.valueOf(10000), "dialog_show_time", String.valueOf(Integer.parseInt(b2) + 1), storageParams);
        } catch (NumberFormatException unused) {
            LogUtil.b("DeviceInitUtil", "setDialogShowTime NumberFormatException");
        }
    }

    private void z() {
        boolean z;
        if (DeviceSettingsInteractors.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).e() != null) {
            z = DeviceSettingsInteractors.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).e().isSupportMusicControl();
            LogUtil.a("DeviceInitUtil", "showNotificationDialog, isSupportMusic: ", Boolean.valueOf(z));
        } else {
            z = false;
        }
        DeviceInfo a2 = jpt.a("DeviceInitUtil");
        if (a2 == null) {
            LogUtil.h("DeviceInitUtil", "refresh dialog Support deviceInfo is null, return");
            return;
        }
        String string = this.i.getResources().getString(R.string.IDS_add_device_smart_watch);
        if (jfu.h(a2.getProductType())) {
            string = this.i.getResources().getString(R.string.IDS_add_device_smart_band);
        }
        String format = String.format(Locale.ENGLISH, this.i.getResources().getString(R.string.IDS_device_notification_switch_closed_remind_text), string);
        String format2 = String.format(Locale.ENGLISH, this.i.getResources().getString(R.string.IDS_device_notification_open_help_remind_text), string);
        if (CommonUtil.u(this.i) && !Utils.o()) {
            d(z, format, format2);
        } else {
            e(z, format2);
        }
    }

    private void e(boolean z, String str) {
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.i).b(R.string.IDS_service_area_notice_title).cyU_(R.string._2130841561_res_0x7f020fd9, new View.OnClickListener() { // from class: owl.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                owl.this.l();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841444_res_0x7f020f64, new View.OnClickListener() { // from class: owl.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                owl.this.k();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (z) {
            jjd.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).c(true, true);
        }
        cyR_.e(str);
        CustomTextAlertDialog a2 = cyR_.a();
        this.q = a2;
        a2.setCancelable(false);
        if (this.q.isShowing()) {
            return;
        }
        this.q.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        DeviceInfo a2 = jpt.a("DeviceInitUtil");
        if (a2 != null) {
            int productType = a2.getProductType();
            String deviceName = a2.getDeviceName();
            HashMap hashMap = new HashMap(16);
            hashMap.put("click_status", "0");
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(productType, deviceName, false));
            ixx.d().d(cpp.a(), AnalyticsValue.NOTIFIDIALOG_BUTTON_1090013.value(), hashMap, 0);
            LogUtil.a("DeviceInitUtil", "handleElseSettingDialogNegative Bi: ", jfu.c(productType, deviceName, false));
        }
        LogUtil.a("DeviceInitUtil", "notification cancel click");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        String o = o();
        if (!TextUtils.isEmpty(o)) {
            LogUtil.a("DeviceInitUtil", "handleElseSettingDialogPositive Bi: ", o);
        }
        LogUtil.a("DeviceInitUtil", "notification ok click");
        Intent intent = new Intent();
        intent.putExtra("isFromDialog", true);
        intent.setClass(this.i, NotificationSettingActivity.class);
        try {
            this.i.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("DeviceInitUtil", "exception is" + e2.getMessage());
        }
    }

    private void d(boolean z, String str, String str2) {
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.i).b(str).cyU_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: owl.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceInfo a2 = jpt.a("DeviceInitUtil");
                if (a2 != null) {
                    int productType = a2.getProductType();
                    String deviceName = a2.getDeviceName();
                    HashMap hashMap = new HashMap(16);
                    hashMap.put("click_status", "1");
                    hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(productType, deviceName, false));
                    ixx.d().d(cpp.a(), AnalyticsValue.NOTIFIDIALOG_BUTTON_1090013.value(), hashMap, 0);
                    LogUtil.a("DeviceInitUtil", "handleChineseSettingDialog positive Bi: ", jfu.c(productType, deviceName, false));
                }
                LogUtil.a("DeviceInitUtil", "notification ok click");
                Intent intent = new Intent();
                intent.putExtra("isFromDialog", true);
                intent.setClass(owl.this.i, NotificationSettingActivity.class);
                try {
                    owl.this.i.startActivity(intent);
                } catch (ActivityNotFoundException e2) {
                    LogUtil.b("DeviceInitUtil", "exception is" + e2.getMessage());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: owl.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DeviceInitUtil", "notification cancel click");
                owl.this.y();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (z) {
            jjd.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).c(true, true);
        }
        cyR_.e(str2);
        CustomTextAlertDialog a2 = cyR_.a();
        this.q = a2;
        a2.setCancelable(false);
        if (this.q.isShowing()) {
            return;
        }
        this.q.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        boolean z;
        if (DeviceSettingsInteractors.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).e() != null) {
            z = DeviceSettingsInteractors.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).e().isSupportMusicControl();
            LogUtil.a("DeviceInitUtil", "showAnotherNotificationDialog, isSupportMusic: ", Boolean.valueOf(z));
        } else {
            z = false;
        }
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.i).b(R.string.IDS_service_area_notice_title).cyT_(R.string._2130842831_res_0x7f0214cf, android.R.color.holo_red_light, new View.OnClickListener() { // from class: owl.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                owl.this.n();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: owl.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                owl.this.m();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (z) {
            jjd.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).c(true, true);
        }
        cyR_.e(this.i.getString(R.string._2130842833_res_0x7f0214d1));
        CustomTextAlertDialog a2 = cyR_.a();
        a2.setCancelable(false);
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        String o = o();
        if (!TextUtils.isEmpty(o)) {
            LogUtil.a("DeviceInitUtil", "handleAnotherNotificationDialogNegative Bi: ", o);
        }
        LogUtil.a("DeviceInitUtil", "another notification ok click");
        Intent intent = new Intent();
        intent.putExtra("isFromDialog", true);
        intent.setClass(this.i, NotificationSettingActivity.class);
        try {
            this.i.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("DeviceInitUtil", "exception is" + e2.getMessage());
        }
    }

    private String o() {
        DeviceInfo a2 = jpt.a("DeviceInitUtil");
        if (a2 == null) {
            return "";
        }
        int productType = a2.getProductType();
        String deviceName = a2.getDeviceName();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click_status", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(productType, deviceName, false));
        ixx.d().d(cpp.a(), AnalyticsValue.NOTIFIDIALOG_BUTTON_1090013.value(), hashMap, 0);
        return jfu.c(productType, deviceName, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        DeviceInfo a2 = jpt.a("DeviceInitUtil");
        if (a2 != null) {
            int productType = a2.getProductType();
            String deviceName = a2.getDeviceName();
            HashMap hashMap = new HashMap(16);
            hashMap.put("click_status", "0");
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(productType, deviceName, false));
            ixx.d().d(cpp.a(), AnalyticsValue.NOTIFIDIALOG_BUTTON_1090013.value(), hashMap, 0);
            LogUtil.a("DeviceInitUtil", "handleAnotherNotificationDialogPositive Bi: ", jfu.c(productType, deviceName, false));
        }
        LogUtil.a("DeviceInitUtil", "another notification cancel click");
    }
}
