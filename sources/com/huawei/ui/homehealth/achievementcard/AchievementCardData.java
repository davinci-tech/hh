package com.huawei.ui.homehealth.achievementcard;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.bean.WatchFaceListBean;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.device.activity.notification.NotificationSettingActivity;
import com.huawei.ui.device.activity.update.BandUpdateDialogActivity;
import com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.ceo;
import defpackage.cjx;
import defpackage.cpp;
import defpackage.ctk;
import defpackage.cvt;
import defpackage.ixx;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.jjb;
import defpackage.jjd;
import defpackage.jkj;
import defpackage.jpt;
import defpackage.jpu;
import defpackage.jqi;
import defpackage.jrd;
import defpackage.jsd;
import defpackage.knx;
import defpackage.kwx;
import defpackage.kxz;
import defpackage.nrf;
import defpackage.oaj;
import defpackage.oxa;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class AchievementCardData extends EmptyAchievementCardData {
    private static boolean b = false;
    private static boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private AchievementCardViewHolder f9363a;
    private String d;
    private final BroadcastReceiver e;
    private Context f;
    private final BroadcastReceiver g;
    private Handler h;
    private String i;
    private ExecutorService j;
    private boolean k;
    private String l;
    private String m;
    private boolean n;
    private boolean o;
    private final BroadcastReceiver p;
    private final BroadcastReceiver q;
    private int r;
    private CustomTextAlertDialog s;
    private String t;

    /* JADX INFO: Access modifiers changed from: private */
    public void cWQ_(Intent intent) {
        String stringExtra = intent.getStringExtra("uniqueId");
        String stringExtra2 = intent.getStringExtra("productId");
        MeasurableDevice d2 = ceo.d().d(stringExtra, false);
        if (d2 != null) {
            if (d2 instanceof ctk) {
                if (((ctk) d2).b().k() != 1) {
                    LogUtil.c("UIHLH_AchievementCardData", "is WiFiDevice but not main user.");
                    return;
                }
            } else {
                if (!TextUtils.isEmpty(stringExtra2) && "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(stringExtra2)) {
                    LogUtil.c("UIHLH_AchievementCardData", "hagrid device unconfigured network");
                    return;
                }
                LogUtil.a("UIHLH_AchievementCardData", "device not instanceof WiFiDevice");
            }
        } else {
            LogUtil.a("UIHLH_AchievementCardData", "switchToSubUserWifiPage, no bond device");
        }
        oaj.a().a(intent.getStringExtra("uniqueId"));
        cWS_(intent);
    }

    public AchievementCardData(Context context) {
        super(context);
        this.t = "";
        this.d = "";
        this.r = 0;
        this.m = "";
        this.k = false;
        this.i = "";
        this.n = false;
        this.o = false;
        this.l = null;
        this.s = null;
        this.q = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                LogUtil.c("UIHLH_AchievementCardData", "receive notify Bi broadcast");
                if (intent == null) {
                    LogUtil.a("UIHLH_AchievementCardData", "mNotifyBiReportReceiver intent is null");
                    return;
                }
                String action = intent.getAction();
                LogUtil.c("UIHLH_AchievementCardData", "mNotifyBiReportReceiver(): intent: ", action);
                if ("com.huawei.health.nitification_service_bi_change".equals(action)) {
                    int intExtra = intent.getIntExtra("BIStatus", -1);
                    if (intExtra == -1) {
                        LogUtil.a("UIHLH_AchievementCardData", "BIStatus is -1");
                        return;
                    }
                    String str = Build.BRAND;
                    HashMap hashMap = new HashMap(16);
                    hashMap.put(JsbMapKeyNames.H5_BRAND, str);
                    hashMap.put("code", Integer.valueOf(intExtra));
                    ixx.d().d(AchievementCardData.this.mContext, AnalyticsValue.NOTIFY_SERVICE_ENABLE_1090027.value(), hashMap, 0);
                }
            }
        };
        this.p = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.9
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                LogUtil.c("UIHLH_AchievementCardData", "receive weather BI broadcast");
                if (intent == null) {
                    LogUtil.a("UIHLH_AchievementCardData", "mWeatherBiReportReceiver onReceive intent is null");
                    return;
                }
                if (intent.getAction() == null || !intent.getAction().equals("com.huawei.health.action.ACTION_HWWEATHER_BI_CHANGE")) {
                    return;
                }
                LogUtil.c("UIHLH_AchievementCardData", "mWeatherBiReportReceiver() intent: ", intent.getAction());
                int intExtra = intent.getIntExtra("BIStatus", 0);
                if (intExtra != 0) {
                    AchievementCardData.this.d(intExtra);
                } else {
                    LogUtil.a("UIHLH_AchievementCardData", "status is 0");
                }
            }
        };
        this.g = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.10
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Handler handler = AchievementCardData.this.h;
                if (intent == null || handler == null) {
                    LogUtil.a("UIHLH_AchievementCardData", "mDeviceStatusReceiver onReceive param is null");
                    return;
                }
                LogUtil.c("UIHLH_AchievementCardData", "Achievement device broadcast intent: ", intent.getAction());
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    try {
                        Message obtainMessage = handler.obtainMessage();
                        if (!(intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo)) {
                            LogUtil.a("UIHLH_AchievementCardData", "ParcelableExtra Data is not DeviceInfo");
                            return;
                        }
                        DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                        if (deviceInfo == null) {
                            LogUtil.a("UIHLH_AchievementCardData", "deviceInfo is null");
                            return;
                        }
                        if (deviceInfo.getDeviceConnectState() == 2 && !Utils.o()) {
                            LogUtil.c("UIHLH_AchievementCardData", "connected");
                            obtainMessage.what = 5;
                            obtainMessage.obj = deviceInfo;
                        } else {
                            LogUtil.a("UIHLH_AchievementCardData", "connect failed because status: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                            obtainMessage.what = 6;
                        }
                        handler.sendMessage(obtainMessage);
                    } catch (ClassCastException unused) {
                        LogUtil.e("UIHLH_AchievementCardData", "DeviceInfo deviceInfo ClassCastException");
                    }
                }
            }
        };
        this.e = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent != null) {
                    Handler handler = AchievementCardData.this.h;
                    if (handler == null) {
                        LogUtil.a("UIHLH_AchievementCardData", "mAutoCheckNewVersionReceiver onReceive handler is null");
                        return;
                    }
                    String action = intent.getAction();
                    LogUtil.c("UIHLH_AchievementCardData", "mAutoCheckNewVersionReceiver onReceive: content: ", intent.getStringExtra("content"));
                    if ("action_band_auto_check_new_version_result".equals(action)) {
                        int intExtra = intent.getIntExtra("result", 8);
                        LogUtil.c("UIHLH_AchievementCardData", "mAutoCheckNewVersionReceiver result: ", Integer.valueOf(intExtra));
                        if (intExtra == 14) {
                            LogUtil.c("UIHLH_AchievementCardData", "AUTO_CHECK_AW70_BAND_SUCCESS");
                            AchievementCardData.this.cWR_(intent, handler);
                            return;
                        } else if (intExtra == 15) {
                            AchievementCardData.this.cWQ_(intent);
                            return;
                        } else {
                            LogUtil.a("UIHLH_AchievementCardData", "default result", Integer.valueOf(intExtra));
                            return;
                        }
                    }
                    return;
                }
                LogUtil.a("UIHLH_AchievementCardData", "mAutoCheckNewVersionReceiver onReceive intent is null");
            }
        };
        this.f = context;
        this.h = new d(this);
        l();
        m();
        p();
        this.h.sendEmptyMessage(9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cWR_(Intent intent, Handler handler) {
        if (c) {
            LogUtil.a("UIHLH_AchievementCardData", "sIsHandleCheckSuccessAw70, do nothing");
            return;
        }
        c = true;
        handler.sendEmptyMessageDelayed(200, 5000L);
        this.o = true;
        String stringExtra = intent.getStringExtra("name");
        int intExtra = intent.getIntExtra("size", -1);
        String stringExtra2 = intent.getStringExtra("changelog");
        DeviceInfo d2 = jpu.d("UIHLH_AchievementCardData");
        if (d2 == null) {
            LogUtil.a("UIHLH_AchievementCardData", "AUTO_CHECK_AW70_BAND_SUCCESS AW70 deviceInfo is null");
            return;
        }
        String deviceName = d2.getDeviceName();
        this.l = d2.getDeviceIdentify();
        LogUtil.c("UIHLH_AchievementCardData", "AUTO_CHECK_AW70_BAND_SUCCESS checkBandNewVersionName: ", stringExtra, " checkBandNewVersionSize: ", Integer.valueOf(intExtra), " bandChangelog: ", stringExtra2, " aw70DeviceName: ", deviceName);
        d(stringExtra, intExtra, stringExtra2, deviceName, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ThreadPoolManager.d().d("cacheImage", new Runnable() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.12
            @Override // java.lang.Runnable
            public void run() {
                DeviceInfo f;
                DeviceSettingsInteractors d2;
                if (!CommonUtil.ce() || (f = oxa.a().f()) == null || f.getDeviceConnectState() != 2 || (d2 = DeviceSettingsInteractors.d(BaseApplication.getContext())) == null || d2.e() == null) {
                    return;
                }
                AchievementCardData.this.a(d2, f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceSettingsInteractors deviceSettingsInteractors, DeviceInfo deviceInfo) {
        WatchFaceListBean watchFaceListBean;
        if (deviceSettingsInteractors.e().isSupportWatchFace() && SharedPreferenceManager.i(this.f).equals(deviceInfo.getDeviceIdentify()) && SharedPreferenceManager.f(BaseApplication.getContext())) {
            try {
                watchFaceListBean = (WatchFaceListBean) new Gson().fromJson(SharedPreferenceManager.j(BaseApplication.getContext()), WatchFaceListBean.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.e("UIHLH_AchievementCardData", "judgeCacheImageCondition JsonSyntaxException");
                watchFaceListBean = null;
            }
            if (watchFaceListBean == null) {
                return;
            }
            List<WatchFaceListBean.WatchFaceBean> watchFaceBeanList = watchFaceListBean.getWatchFaceBeanList();
            if (watchFaceBeanList == null || watchFaceBeanList.size() == 0) {
                LogUtil.a("UIHLH_AchievementCardData", "data is not suitable");
                return;
            }
            String fileHost = watchFaceListBean.getFileHost();
            for (WatchFaceListBean.WatchFaceBean watchFaceBean : watchFaceBeanList) {
                StringBuilder sb = new StringBuilder(16);
                sb.append(fileHost);
                sb.append(watchFaceBean.getId());
                sb.append("/");
                sb.append(watchFaceBean.getLogo());
                nrf.a(this.mContext, sb.toString());
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (CommonUtil.ce()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.15
                @Override // java.lang.Runnable
                public void run() {
                    DeviceInfo f = oxa.a().f();
                    if (f != null && f.getDeviceConnectState() == 2) {
                        oxa.a().d();
                        if (AchievementCardData.this.h == null) {
                            return;
                        }
                        Handler handler = AchievementCardData.this.h;
                        Message obtainMessage = handler.obtainMessage();
                        obtainMessage.obj = f;
                        obtainMessage.what = 10;
                        handler.sendMessage(obtainMessage);
                    }
                    if (CommonUtil.x(AchievementCardData.this.mContext)) {
                        return;
                    }
                    if (kwx.c()) {
                        com.huawei.hwlogsmodel.LogUtil.a("UIHLH_AchievementCardData", "resume is Sporting");
                    } else if (f != null) {
                        AchievementCardData.this.a(f.getDeviceIdentify());
                        AchievementCardData.this.d(f.getDeviceIdentify());
                    }
                }
            });
        }
        ReleaseLogUtil.e("UIHLH_AchievementCardData", "main card onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.t = KeyValDbManager.b(this.mContext).e("new_band_version");
        this.d = KeyValDbManager.b(this.mContext).e("new_band_version_type");
        String e = KeyValDbManager.b(this.mContext).e("new_band_size");
        this.m = KeyValDbManager.b(this.mContext).e("new_band_change_log");
        String e2 = KeyValDbManager.b(this.mContext).e("is_need_show_dialog");
        String e3 = KeyValDbManager.b(this.mContext).e("need_show_dialog_time");
        LogUtil.c("UIHLH_AchievementCardData", "isNeedShowDialog ", e2, "needShowDialogTime", e3);
        if (Boolean.parseBoolean(e2) && kxz.c(e3)) {
            this.r = CommonUtil.a(e, 10);
            this.k = Boolean.parseBoolean(KeyValDbManager.b(this.mContext).e("is_new_checkbox"));
            this.i = KeyValDbManager.b(this.mContext).e("need_show_dialog_device_name");
            String e4 = KeyValDbManager.b(this.mContext).e("need_show_dialog_mac");
            this.l = e4;
            if (e4 == null || !e4.equals(str)) {
                return;
            }
            this.n = true;
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        u();
        y();
        v();
        if (CommonUtil.ce()) {
            t();
        }
        ExecutorService executorService = this.j;
        if (executorService != null) {
            executorService.shutdown();
            this.j = null;
        }
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.h = null;
        }
        AchievementCardViewHolder achievementCardViewHolder = this.f9363a;
        if (achievementCardViewHolder != null) {
            achievementCardViewHolder.b();
            this.f9363a = null;
        }
    }

    @Override // com.huawei.ui.homehealth.achievementcard.EmptyAchievementCardData, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        if (super.getCardViewHolder(viewGroup, layoutInflater) instanceof AchievementCardViewHolder) {
            this.f9363a = (AchievementCardViewHolder) super.getCardViewHolder(viewGroup, layoutInflater);
        }
        AchievementCardViewHolder achievementCardViewHolder = this.f9363a;
        if (achievementCardViewHolder == null) {
            LogUtil.a("UIHLH_AchievementCardData", "getCardViewHolder, mAchievementCardViewHolder is null.");
            return this.f9363a;
        }
        achievementCardViewHolder.e();
        return this.f9363a;
    }

    @Override // com.huawei.ui.homehealth.achievementcard.EmptyAchievementCardData, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        LogUtil.c("UIHLH_AchievementCardData", "refreshCardData()");
    }

    private void m() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
        BroadcastManagerUtil.bFC_(this.mContext, this.g, intentFilter, LocalBroadcast.c, null);
    }

    private void u() {
        try {
            this.mContext.unregisterReceiver(this.g);
        } catch (IllegalArgumentException e) {
            LogUtil.e("UIHLH_AchievementCardData", "unregisterGetDeviceStatusBroadcast IllegalArgumentException: ", e.getMessage());
        } catch (RuntimeException e2) {
            LogUtil.e("UIHLH_AchievementCardData", "unregisterGetDeviceStatusBroadcast RuntimeException: ", e2.getMessage());
        }
    }

    private void l() {
        LogUtil.c("UIHLH_AchievementCardData", "register weather report Bi broadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.ACTION_HWWEATHER_BI_CHANGE");
        BroadcastManagerUtil.bFA_(this.mContext, this.p, intentFilter, LocalBroadcast.c, null);
    }

    private void y() {
        LogUtil.c("UIHLH_AchievementCardData", "unregister weather report Bi broadcast");
        try {
            this.mContext.unregisterReceiver(this.p);
        } catch (IllegalArgumentException e) {
            LogUtil.e("UIHLH_AchievementCardData", "unregisterHwWeatherBiBroadcast IllegalArgumentException: ", e.getMessage());
        } catch (RuntimeException e2) {
            LogUtil.e("UIHLH_AchievementCardData", "unregisterHwWeatherBiBroadcast RuntimeException: ", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> b2 = jfq.c().b(HwGetDevicesMode.ACTIVE_DEVICES, (HwGetDevicesParameter) null, "UIHLH_AchievementCardData");
        if (b2 != null && !b2.isEmpty()) {
            for (DeviceInfo deviceInfo2 : b2) {
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

    private void p() {
        LogUtil.c("UIHLH_AchievementCardData", "register notifySendData report Bi broadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.nitification_service_bi_change");
        BroadcastManagerUtil.bFC_(this.f, this.q, intentFilter, LocalBroadcast.c, null);
    }

    private void v() {
        LogUtil.c("UIHLH_AchievementCardData", "unregister notify report Bi broadcast");
        try {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver(this.q);
            }
        } catch (IllegalArgumentException e) {
            LogUtil.e("UIHLH_AchievementCardData", "unregisterNotifyBiBroadcast IllegalArgumentException", e.getMessage());
        }
    }

    static class d extends BaseHandler<AchievementCardData> {
        d(AchievementCardData achievementCardData) {
            super(achievementCardData);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cWT_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchievementCardData achievementCardData, Message message) {
            if (message == null) {
                LogUtil.a("UIHLH_AchievementCardData", "handleMessageWhenReferenceNotNull msg is null");
                return;
            }
            int i = message.what;
            if (i == 5) {
                if (!CommonUtil.ag(achievementCardData.mContext) && knx.e() && (message.obj instanceof DeviceInfo)) {
                    AchievementCardData.b(achievementCardData, (DeviceInfo) message.obj);
                    return;
                }
                return;
            }
            if (i == 100) {
                boolean unused = AchievementCardData.b = false;
                LogUtil.c("UIHLH_AchievementCardData", "MESSAGE_WEAR_DEVICE_CHECK_SUCCESS, sIsHandleCheckSuccess: ", Boolean.valueOf(AchievementCardData.b));
                return;
            }
            if (i == 200) {
                boolean unused2 = AchievementCardData.c = false;
                LogUtil.c("UIHLH_AchievementCardData", "MESSAGE_AW70_CHECK_SUCCESS, sIsHandleCheckSuccessAw70: ", Boolean.valueOf(AchievementCardData.c));
                return;
            }
            if (i == 9) {
                achievementCardData.d();
                return;
            }
            if (i == 10) {
                DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
                if (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) {
                    return;
                }
                achievementCardData.n();
                achievementCardData.c(deviceInfo);
                return;
            }
            LogUtil.a("UIHLH_AchievementCardData", "handleMessageWhenReferenceNotNull else");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final AchievementCardData achievementCardData, DeviceInfo deviceInfo) {
        if (achievementCardData.j == null) {
            achievementCardData.j = Executors.newSingleThreadExecutor();
        }
        if (deviceInfo != null) {
            final String deviceIdentify = deviceInfo.getDeviceIdentify();
            jsd.a(BaseApplication.getContext(), deviceIdentify);
            final String softVersion = deviceInfo.getSoftVersion();
            final String a2 = jsd.a(deviceInfo);
            achievementCardData.j.execute(new Runnable() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.13
                @Override // java.lang.Runnable
                public void run() {
                    if (jsd.h(deviceIdentify)) {
                        LogUtil.c("UIHLH_AchievementCardData", "start upload upg log.");
                        achievementCardData.b(softVersion, a2, deviceIdentify);
                        jsd.d(BaseApplication.getContext(), 2, deviceIdentify);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2, String str3) {
        long c2 = SharedPreferenceManager.c(this.mContext, "EXCE_OTA_APP_UPG_TRANS_START");
        long c3 = SharedPreferenceManager.c(this.mContext, "EXCE_OTA_APP_UPG_TRANS_START");
        long c4 = SharedPreferenceManager.c(this.mContext, "EXCE_OTA_APP_UPG_TRANS_STOP_TIME");
        boolean z = c4 > 0 && c4 - c2 > 0;
        boolean z2 = c3 > 0 || c2 > 0;
        if (jsd.d(str3) && z2 && z) {
            jsd.e("0D0B01", "EXCE_OTA_APP_UPG_CONNECT_VERSION", str, str2);
        }
    }

    private void cWS_(Intent intent) {
        LogUtil.c("UIHLH_AchievementCardData", "showScaleUpdateDialog ACTION_APP_AUTO_CHECK_NEW_VERSION_RESULT:AUTO_CHECK_SUCCESS BLE");
        String stringExtra = intent.getStringExtra("productId");
        if (CommonUtil.x(this.mContext)) {
            LogUtil.a("UIHLH_AchievementCardData", "dialog is background");
            return;
        }
        if (cjx.e().a(stringExtra) == null) {
            LogUtil.a("UIHLH_AchievementCardData", "getBondedDevice is null");
            return;
        }
        try {
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
            intent2.setClass(this.mContext, BandUpdateDialogActivity.class);
            this.mContext.startActivity(intent2);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("UIHLH_AchievementCardData", "showScaleUpdateDialog e ", ExceptionUtils.d(e));
        }
    }

    private void d(String str, int i, String str2, String str3, boolean z) {
        if (jrd.b()) {
            LogUtil.a("UIHLH_AchievementCardData", "band is showing");
            return;
        }
        if (jkj.d().j()) {
            LogUtil.a("UIHLH_AchievementCardData", "band is transfering");
            return;
        }
        LogUtil.c("UIHLH_AchievementCardData", "showBandAutoCheckDialog version: ", str, " size: ", Integer.valueOf(i), " changeLog: ", str2, " deviceName: ", str3, " isShowCheckbox: ", Boolean.valueOf(z));
        if (CommonUtil.x(this.mContext)) {
            this.t = str;
            this.r = i;
            this.m = str2;
            this.k = z;
            this.n = true;
            LogUtil.c("UIHLH_AchievementCardData", "showBandAutoCheckDialog isBackground");
            return;
        }
        if (kwx.c()) {
            com.huawei.hwlogsmodel.LogUtil.a("UIHLH_AchievementCardData", "resume is Sporting");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.putExtra("name", str);
            intent.putExtra("size", i);
            intent.putExtra("message", str2);
            intent.putExtra(ParamConstants.CallbackMethod.ON_SHOW, z);
            intent.putExtra("isAW70", this.o);
            intent.putExtra("mac", this.l);
            intent.setClass(this.mContext, WearDeviceUpdateDialogActivity.class);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("UIHLH_AchievementCardData", "showBandAutoCheckDialog e ", ExceptionUtils.d(e));
        }
    }

    public void b() {
        LogUtil.c("UIHLH_AchievementCardData", "registUpdateState()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_band_auto_check_new_version_result");
        BroadcastManagerUtil.bFA_(this.mContext, this.e, intentFilter, LocalBroadcast.c, null);
    }

    private void t() {
        LogUtil.c("UIHLH_AchievementCardData", "unregisterAppCheckBroadcast()");
        try {
            this.mContext.unregisterReceiver(this.e);
        } catch (IllegalArgumentException e) {
            LogUtil.e("UIHLH_AchievementCardData", "unregistUpdateState IllegalArgumentException: ", e.getMessage());
        } catch (RuntimeException e2) {
            LogUtil.e("UIHLH_AchievementCardData", "unregistUpdateState RuntimeException: ", e2.getMessage());
        } catch (Exception unused) {
            LogUtil.e("UIHLH_AchievementCardData", "unregistUpdateState Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str) {
        if (this.n) {
            LogUtil.c("UIHLH_AchievementCardData", "Enter checkIsNeedShowDialog mIsNeedShowDialog");
            jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.11
                /* JADX WARN: Removed duplicated region for block: B:18:0x0071  */
                /* JADX WARN: Removed duplicated region for block: B:20:0x0077  */
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
                        java.lang.String r1 = "UIHLH_AchievementCardData"
                        health.compact.a.LogUtil.c(r1, r0)
                        r0 = 0
                        if (r7 != 0) goto L2d
                        boolean r7 = r8 instanceof java.lang.String
                        if (r7 != 0) goto L20
                        java.lang.String r7 = "objectData is not String"
                        java.lang.Object[] r7 = new java.lang.Object[]{r7}
                        health.compact.a.LogUtil.a(r1, r7)
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
                        health.compact.a.LogUtil.c(r1, r2)
                        java.lang.String r2 = "is_need_show_dialog"
                        if (r7 == 0) goto L61
                        if (r8 == 0) goto L4c
                        goto L61
                    L4c:
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData r7 = com.huawei.ui.homehealth.achievementcard.AchievementCardData.this
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData.d(r7, r0)
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData r7 = com.huawei.ui.homehealth.achievementcard.AchievementCardData.this
                        android.content.Context r7 = r7.mContext
                        health.compact.a.KeyValDbManager r7 = health.compact.a.KeyValDbManager.b(r7)
                        java.lang.String r8 = java.lang.String.valueOf(r0)
                        r7.e(r2, r8)
                        goto L94
                    L61:
                        android.content.Context r7 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
                        com.huawei.hwversionmgr.manager.HwVersionManager r7 = com.huawei.hwversionmgr.manager.HwVersionManager.c(r7)
                        java.lang.String r8 = r2
                        boolean r7 = r7.n(r8)
                        if (r7 == 0) goto L77
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData r7 = com.huawei.ui.homehealth.achievementcard.AchievementCardData.this
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData.c(r7)
                        goto L94
                    L77:
                        java.lang.String r7 = "checkIsNeedShowDialog have no new version"
                        java.lang.Object[] r7 = new java.lang.Object[]{r7}
                        health.compact.a.LogUtil.c(r1, r7)
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData r7 = com.huawei.ui.homehealth.achievementcard.AchievementCardData.this
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData.d(r7, r0)
                        com.huawei.ui.homehealth.achievementcard.AchievementCardData r7 = com.huawei.ui.homehealth.achievementcard.AchievementCardData.this
                        android.content.Context r7 = r7.mContext
                        health.compact.a.KeyValDbManager r7 = health.compact.a.KeyValDbManager.b(r7)
                        java.lang.String r8 = java.lang.String.valueOf(r0)
                        r7.e(r2, r8)
                    L94:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.achievementcard.AchievementCardData.AnonymousClass11.d(int, java.lang.Object):void");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (jkj.d().j()) {
            LogUtil.c("UIHLH_AchievementCardData", "device is running ota update");
            this.n = false;
            KeyValDbManager.b(this.mContext).e("is_need_show_dialog", String.valueOf(false));
        } else if (jrd.b()) {
            LogUtil.c("UIHLH_AchievementCardData", "device ota is showing");
            this.n = false;
            KeyValDbManager.b(this.mContext).e("is_need_show_dialog", String.valueOf(false));
        } else {
            s();
            this.n = false;
            KeyValDbManager.b(this.mContext).e("is_need_show_dialog", String.valueOf(false));
        }
    }

    private void s() {
        LogUtil.c("UIHLH_AchievementCardData", "can show dialog");
        if (!(this.mContext instanceof Activity)) {
            LogUtil.a("UIHLH_AchievementCardData", "mContext is null or mContext is not Activity");
        } else {
            ((Activity) this.mContext).runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.14
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Intent intent = new Intent();
                        intent.putExtra("name", AchievementCardData.this.t);
                        intent.putExtra("type", AchievementCardData.this.d);
                        intent.putExtra("size", AchievementCardData.this.r);
                        intent.putExtra("message", AchievementCardData.this.m);
                        intent.putExtra(ParamConstants.CallbackMethod.ON_SHOW, AchievementCardData.this.k);
                        intent.putExtra("show_device_name", AchievementCardData.this.i);
                        intent.putExtra("mac", AchievementCardData.this.l);
                        intent.setClass(AchievementCardData.this.mContext, WearDeviceUpdateDialogActivity.class);
                        AchievementCardData.this.mContext.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        LogUtil.e("UIHLH_AchievementCardData", "handleWlanNewVersionUpdate e ", ExceptionUtils.d(e));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        DeviceSettingsInteractors d2 = DeviceSettingsInteractors.d(BaseApplication.getContext());
        if (d2 == null || d2.e() == null || !d2.e().isSupportMusicControl()) {
            return;
        }
        jjd.b(BaseApplication.getContext());
        LogUtil.c("UIHLH_AchievementCardData", "initManager music");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo) {
        LogUtil.c("UIHLH_AchievementCardData", "enter notificationAlert");
        DeviceCapability e = DeviceSettingsInteractors.d(BaseApplication.getContext()).e();
        if (e == null) {
            return;
        }
        LogUtil.c("UIHLH_AchievementCardData", "enter notificationAlert isMessageAlert: ", Boolean.valueOf(e.isMessageAlert()));
        boolean h = CommonUtil.h(this.mContext, "com.huawei.health.MainActivity");
        LogUtil.c("UIHLH_AchievementCardData", "is MainActivity on Top  ", Boolean.valueOf(h));
        int productType = deviceInfo.getProductType();
        if (e.isMessageAlert() && h && productType != 32) {
            boolean c2 = jjb.b().c();
            boolean o = o();
            boolean e2 = e();
            LogUtil.c("UIHLH_AchievementCardData", "isClosed: ", Boolean.valueOf(c2), "isAchieve: ", Boolean.valueOf(o), "isShownThreeTimes: ", Boolean.valueOf(e2));
            if (!c2 && o && !e2) {
                q();
                b(kxz.d(), this.mContext);
                e(this.mContext);
            } else {
                if (c2) {
                    CustomTextAlertDialog customTextAlertDialog = this.s;
                    if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
                        return;
                    }
                    this.s.dismiss();
                    LogUtil.c("UIHLH_AchievementCardData", "dismiss notification dialog");
                    return;
                }
                LogUtil.a("UIHLH_AchievementCardData", "handle dialog else");
            }
        }
    }

    private boolean o() {
        Date a2;
        String b2 = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "sp_dialog_check_time");
        LogUtil.c("UIHLH_AchievementCardData", "isEnableDialog lastTime: ", b2);
        if (TextUtils.isEmpty(b2) || (a2 = kxz.a(b2)) == null) {
            return true;
        }
        return Math.abs(System.currentTimeMillis() - a2.getTime()) > 86400000;
    }

    private boolean e() {
        String b2 = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "dialog_show_time");
        LogUtil.c("UIHLH_AchievementCardData", "countShowTimes number: ", b2);
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        try {
            return Integer.parseInt(b2) >= 2;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private void b(String str, Context context) {
        LogUtil.c("UIHLH_AchievementCardData", "setDialogCheckTime, time: ", str);
        SharedPreferenceManager.e(context, String.valueOf(10000), "sp_dialog_check_time", str, new StorageParams(0));
    }

    private void e(Context context) {
        StorageParams storageParams = new StorageParams(0);
        String b2 = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "dialog_show_time");
        LogUtil.c("UIHLH_AchievementCardData", "setDialogShowTime number: ", b2);
        if (TextUtils.isEmpty(b2)) {
            SharedPreferenceManager.e(context, String.valueOf(10000), "dialog_show_time", "0", storageParams);
            return;
        }
        try {
            SharedPreferenceManager.e(context, String.valueOf(10000), "dialog_show_time", String.valueOf(Integer.parseInt(b2) + 1), storageParams);
        } catch (NumberFormatException unused) {
            LogUtil.e("UIHLH_AchievementCardData", "setDialogShowTime NumberFormatException");
        }
    }

    private void q() {
        boolean z;
        if (DeviceSettingsInteractors.d(BaseApplication.getContext()).e() != null) {
            z = DeviceSettingsInteractors.d(BaseApplication.getContext()).e().isSupportMusicControl();
            LogUtil.c("UIHLH_AchievementCardData", "showNotificationDialog, isSupportMusic: ", Boolean.valueOf(z));
        } else {
            z = false;
        }
        DeviceInfo a2 = jpt.a("UIHLH_AchievementCardData");
        if (a2 == null) {
            LogUtil.a("UIHLH_AchievementCardData", "refresh dialog Support deviceInfo is null, return");
            return;
        }
        String string = this.mContext.getResources().getString(R.string.IDS_add_device_smart_watch);
        if (jfu.h(a2.getProductType())) {
            string = this.mContext.getResources().getString(R.string.IDS_add_device_smart_band);
        }
        String format = String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string.IDS_device_notification_switch_closed_remind_text), string);
        String format2 = String.format(Locale.ENGLISH, this.mContext.getResources().getString(R.string.IDS_device_notification_open_help_remind_text), string);
        if (CommonUtil.u(this.mContext) && !Utils.o()) {
            e(z, format, format2);
        } else {
            a(z, format2);
        }
    }

    private void a(boolean z, String str) {
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.mContext).b(R.string.IDS_service_area_notice_title).cyU_(R.string._2130841561_res_0x7f020fd9, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchievementCardData.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841444_res_0x7f020f64, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchievementCardData.this.j();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (z) {
            jjd.b(BaseApplication.getContext()).c(true, true);
        }
        cyR_.e(str);
        CustomTextAlertDialog a2 = cyR_.a();
        this.s = a2;
        a2.setCancelable(false);
        if (this.s.isShowing()) {
            return;
        }
        this.s.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        DeviceInfo a2 = jpt.a("UIHLH_AchievementCardData");
        if (a2 != null) {
            int productType = a2.getProductType();
            String deviceName = a2.getDeviceName();
            HashMap hashMap = new HashMap(16);
            hashMap.put("click_status", "0");
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(productType, deviceName, false));
            ixx.d().d(cpp.a(), AnalyticsValue.NOTIFIDIALOG_BUTTON_1090013.value(), hashMap, 0);
            LogUtil.c("UIHLH_AchievementCardData", "handleElseSettingDialogNegative Bi: ", jfu.c(productType, deviceName, false));
        }
        LogUtil.c("UIHLH_AchievementCardData", "notification cancel click");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        String h = h();
        if (!TextUtils.isEmpty(h)) {
            LogUtil.c("UIHLH_AchievementCardData", "handleElseSettingDialogPositive Bi: ", h);
        }
        try {
            LogUtil.c("UIHLH_AchievementCardData", "notification ok click");
            Intent intent = new Intent();
            intent.putExtra("isFromDialog", true);
            intent.setClass(this.mContext, NotificationSettingActivity.class);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("UIHLH_AchievementCardData", "handleElseSettingDialogPositive e ", ExceptionUtils.d(e));
        }
    }

    private void e(boolean z, String str, String str2) {
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.mContext).b(str).cyU_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeviceInfo a2 = jpt.a("UIHLH_AchievementCardData");
                if (a2 != null) {
                    int productType = a2.getProductType();
                    String deviceName = a2.getDeviceName();
                    HashMap hashMap = new HashMap(16);
                    hashMap.put("click_status", "1");
                    hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(productType, deviceName, false));
                    ixx.d().d(cpp.a(), AnalyticsValue.NOTIFIDIALOG_BUTTON_1090013.value(), hashMap, 0);
                    LogUtil.c("UIHLH_AchievementCardData", "handleChineseSettingDialog positive Bi: ", jfu.c(productType, deviceName, false));
                }
                try {
                    LogUtil.c("UIHLH_AchievementCardData", "notification ok click");
                    Intent intent = new Intent();
                    intent.putExtra("isFromDialog", true);
                    intent.setClass(AchievementCardData.this.mContext, NotificationSettingActivity.class);
                    AchievementCardData.this.mContext.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.e("UIHLH_AchievementCardData", "handleChineseSettingDialog e ", ExceptionUtils.d(e));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("UIHLH_AchievementCardData", "notification cancel click");
                AchievementCardData.this.r();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (z) {
            jjd.b(BaseApplication.getContext()).c(true, true);
        }
        cyR_.e(str2);
        CustomTextAlertDialog a2 = cyR_.a();
        this.s = a2;
        a2.setCancelable(false);
        if (this.s.isShowing()) {
            return;
        }
        this.s.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        boolean z;
        if (DeviceSettingsInteractors.d(BaseApplication.getContext()).e() != null) {
            z = DeviceSettingsInteractors.d(BaseApplication.getContext()).e().isSupportMusicControl();
            LogUtil.c("UIHLH_AchievementCardData", "showAnotherNotificationDialog, isSupportMusic: ", Boolean.valueOf(z));
        } else {
            z = false;
        }
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.mContext).b(R.string.IDS_service_area_notice_title).cyT_(R.string._2130842831_res_0x7f0214cf, android.R.color.holo_red_light, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchievementCardData.this.i();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.achievementcard.AchievementCardData.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchievementCardData.this.f();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (z) {
            jjd.b(BaseApplication.getContext()).c(true, true);
        }
        cyR_.e(this.mContext.getString(R.string._2130842833_res_0x7f0214d1));
        CustomTextAlertDialog a2 = cyR_.a();
        a2.setCancelable(false);
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        String h = h();
        if (!TextUtils.isEmpty(h)) {
            LogUtil.c("UIHLH_AchievementCardData", "handleAnotherNotificationDialogNegative Bi: ", h);
        }
        LogUtil.c("UIHLH_AchievementCardData", "another notification ok click");
        try {
            Intent intent = new Intent();
            intent.putExtra("isFromDialog", true);
            intent.setClass(this.mContext, NotificationSettingActivity.class);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("UIHLH_AchievementCardData", "handleAnotherNotificationDialogNegative e ", ExceptionUtils.d(e));
        }
    }

    private String h() {
        DeviceInfo a2 = jpt.a("UIHLH_AchievementCardData");
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
    public void i() {
        DeviceInfo a2 = jpt.a("UIHLH_AchievementCardData");
        if (a2 != null) {
            int productType = a2.getProductType();
            String deviceName = a2.getDeviceName();
            HashMap hashMap = new HashMap(16);
            hashMap.put("click_status", "0");
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, jfu.c(productType, deviceName, false));
            ixx.d().d(cpp.a(), AnalyticsValue.NOTIFIDIALOG_BUTTON_1090013.value(), hashMap, 0);
            LogUtil.c("UIHLH_AchievementCardData", "handleAnotherNotificationDialogPositive Bi: ", jfu.c(productType, deviceName, false));
        }
        LogUtil.c("UIHLH_AchievementCardData", "another notification cancel click");
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "UIHLH_AchievementCardData";
    }
}
