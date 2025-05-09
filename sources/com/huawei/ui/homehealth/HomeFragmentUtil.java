package com.huawei.ui.homehealth;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.api.SyncApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.util.HealthSyncUtil;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.util.DownloadUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.js.JsInteractAddition;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.SecurityManagerSettingSwitchDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.adddevice.OneKeyScanActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homehealth.achievementcard.AchievementCardData;
import com.huawei.ui.homehealth.adapter.HomeCardAdapter;
import com.huawei.ui.homehealth.functionsetcard.FunctionSetCardData;
import com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesCardData;
import com.huawei.ui.homehealth.healthtrend.HealthTrendCardData;
import com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData;
import com.huawei.ui.homehealth.operationcard.OperationCardData;
import com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardData;
import com.huawei.ui.homehealth.threecirclecard.model.StepsViewModel;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import com.huawei.ui.main.stories.nps.component.NpsConstantValue;
import com.huawei.ui.main.stories.nps.harid.HagridNpsManager;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudCallBack;
import defpackage.cgs;
import defpackage.cjn;
import defpackage.csc;
import defpackage.csf;
import defpackage.cts;
import defpackage.cun;
import defpackage.efb;
import defpackage.ehu;
import defpackage.gmc;
import defpackage.gmz;
import defpackage.ixx;
import defpackage.iyb;
import defpackage.jdl;
import defpackage.jdx;
import defpackage.jqi;
import defpackage.knl;
import defpackage.koq;
import defpackage.njn;
import defpackage.nmh;
import defpackage.nrs;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.oaj;
import defpackage.odl;
import defpackage.odo;
import defpackage.ohr;
import defpackage.opj;
import defpackage.ovg;
import defpackage.owl;
import defpackage.owq;
import defpackage.oxa;
import defpackage.oxd;
import defpackage.pep;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class HomeFragmentUtil {
    static void a(Context context) {
        LogUtil.a("HomeFragmentUtil", "Process Cloud Sync by Pullrefresh");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(context).synCloud(hiSyncOption, null);
        SharedPreferenceManager.e(context, Integer.toString(10000), "last_sync_time", String.valueOf(System.currentTimeMillis()), new StorageParams());
    }

    static void a(AbstractBaseCardData abstractBaseCardData) {
        if (abstractBaseCardData != null) {
            abstractBaseCardData.onDestroy();
        }
    }

    static void b(AbstractBaseCardData abstractBaseCardData) {
        if (abstractBaseCardData != null) {
            abstractBaseCardData.onResume();
        }
    }

    static boolean d() {
        long j;
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "showFamilyHealthDialogTime");
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(b2)) {
            return true;
        }
        try {
            j = Long.parseLong(b2);
        } catch (NumberFormatException unused) {
            LogUtil.b("HomeFragmentUtil", "HomeFragmentUtil NumberFormatException");
            j = 0;
        }
        return currentTimeMillis - j > 604800000;
    }

    public static void cWu_(Context context, BroadcastReceiver broadcastReceiver) {
        LogUtil.a("HomeFragmentUtil", "enter registerAw70SyncFinishBroadcast");
        BroadcastManagerUtil.bFA_(context, broadcastReceiver, new IntentFilter("com.huawei.health.action.AW70_FITNESS_DETAIL_SYNC_SUCCESS_ACTION"), LocalBroadcast.c, null);
    }

    public static void cWJ_(Context context, BroadcastReceiver broadcastReceiver) {
        LogUtil.a("HomeFragmentUtil", "enter registerWifiUserInfoBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.health.action.ACTION_WIFI_USERINFO_ACTION");
        intentFilter.addAction("com.huawei.health.action.ACTION_WIFI_USERINFO_ACTION");
        intentFilter.addAction("com.huawei.health.action.ACTION_WIFI_DEVICE_UNIT_ACTION");
        intentFilter.addAction("com.huawei.health.action.ACTION_WIFI_OTA_UPDATE_ACTION");
        if (context != null) {
            BroadcastManagerUtil.bFC_(context, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
        }
    }

    public static void c() {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME", NpsConstantValue.QUERY_SYSTEM_BUSY, (StorageParams) null);
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", "weight_notify_key", "true", (StorageParams) null);
        }
    }

    public static void cWx_(Handler handler, final Context context) {
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("HomeFragmentUtil", "downloadIntensityResource");
                    Intent intent = new Intent();
                    intent.setAction("DOWNLOAD_INTENSITY_FILE");
                    BroadcastManagerUtil.bFG_(context, intent);
                }
            }, 3500L);
        }
    }

    public static void e(odo.b bVar, AchievementCardData achievementCardData, FunctionSetCardData functionSetCardData, FunctionMenuCardData functionMenuCardData, ohr ohrVar, OperationCardData operationCardData, ArrayList<AbstractBaseCardData> arrayList, ArrayList<AbstractBaseCardData> arrayList2, OperaMsgCardData operaMsgCardData, boolean z, HealthTrendCardData healthTrendCardData, HealthHeadLinesCardData healthHeadLinesCardData) {
        bVar.d(achievementCardData);
        bVar.c(functionSetCardData);
        bVar.b(ohrVar);
        bVar.h(operationCardData);
        bVar.b(arrayList);
        bVar.a(arrayList2);
        bVar.i(operaMsgCardData);
        if (efb.o()) {
            bVar.b(functionMenuCardData);
        }
        if (!z) {
            bVar.a(healthHeadLinesCardData);
        }
        if (!efb.m() || healthTrendCardData == null) {
            return;
        }
        bVar.e(healthTrendCardData);
    }

    public static void cWG_(Context context, BroadcastReceiver broadcastReceiver) {
        if (context == null) {
            LogUtil.b("HomeFragmentUtil", "setWearBroadcast failed, cause context is null!");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        intentFilter.addAction("com.huawei.haf.intent.action.UPDATE_LANGUAGE_READY");
        intentFilter.addAction("com.huawei.bone.action.open_gps");
        intentFilter.setPriority(500);
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        intentFilter.addAction("com.huawei.bone.action_band_psi_activated");
        intentFilter.addAction("com.huawei.health.fitness_detail_sync_success");
        intentFilter.addAction("com.huawei.health.weight_detail_sync_success");
        BroadcastManagerUtil.bFC_(context, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
    }

    public static void cWK_(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            LogUtil.a("HomeFragmentUtil", "unregisterWearBroadcast");
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HomeFragmentUtil", "unregisterWearBroadcast exception");
        } catch (RuntimeException unused2) {
            LogUtil.b("HomeFragmentUtil", "unregisterWearBroadcast exception");
        }
    }

    public static void cWE_(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HomeFragmentUtil", "unregisterBatteryBroadcast exception");
        } catch (RuntimeException unused2) {
            LogUtil.b("HomeFragmentUtil", "unregisterBatteryBroadcast exception");
        }
    }

    public static void cWC_(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HomeFragmentUtil", "unregisterUpgradeBroadcast");
        } catch (RuntimeException unused2) {
            LogUtil.b("HomeFragmentUtil", "unregisterUpgradeBroadcast");
        }
    }

    public static void cWt_(Intent intent) {
        if (intent == null || !"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction()) || intent.getExtras() == null) {
            return;
        }
        int i = intent.getExtras().getInt("BATTERY_LEVEL");
        DeviceInfo f2 = oxa.a().f();
        if (f2 == null || f2.getDeviceConnectState() != 2) {
            return;
        }
        LogUtil.a("HomeFragmentUtil", "has connected device battery:", Integer.valueOf(i));
    }

    public static void a(Context context, final CommonUiBaseResponse commonUiBaseResponse) {
        HiHealthManager.d(context).fetchUserData(new HiCommonListener() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.6
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.c("HomeFragmentUtil", "fetchUserData onSuccess,data = ", obj);
                if (obj == null || !koq.e(obj, HiUserInfo.class)) {
                    return;
                }
                List list = (List) obj;
                if (list.size() > 0) {
                    LogUtil.a("HomeFragmentUtil", "fetchUserData onSuccess");
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    LogUtil.c("HomeFragmentUtil", "height==", Integer.valueOf(hiUserInfo.getHeight()), "weight==", Float.valueOf(hiUserInfo.getWeight()));
                    CommonUiBaseResponse.this.onResponse(0, hiUserInfo);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("HomeFragmentUtil", "fetchUserData onFailure");
            }
        });
    }

    public static void d(Context context, Object obj) {
        if (obj instanceof HiUserInfo) {
            HiUserInfo hiUserInfo = (HiUserInfo) obj;
            HashMap hashMap = new HashMap(16);
            hashMap.put("id", LoginInit.getInstance(context).getUsetId());
            hashMap.put("uid", String.valueOf(0));
            hashMap.put(CommonConstant.KEY_GENDER, String.valueOf(hiUserInfo.getGender() == 2 ? 1 : hiUserInfo.getGender()));
            hashMap.put("age", String.valueOf(cgs.e(hiUserInfo.getBirthday(), hiUserInfo.getAge())));
            hashMap.put("height", String.valueOf(hiUserInfo.getHeight()));
            hashMap.put("isDelete", String.valueOf(0));
            hashMap.put("currentWeight", String.valueOf(hiUserInfo.getWeight()));
            hashMap.put("month", String.valueOf(cgs.a(hiUserInfo.getBirthday())));
            csf.c(hashMap, cts.b, context);
            return;
        }
        LogUtil.h("HomeFragmentUtil", "wifiUserInfoAction objectData error");
    }

    public static void j(Context context) {
        LogUtil.a("HomeFragmentUtil", "onReceive WifiUnitBroadcast.");
        String b2 = SharedPreferenceManager.b(context, String.valueOf(10000), "wifi_scale_unit_change");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.b("HomeFragmentUtil", "default unit");
            b2 = "false";
        }
        String valueOf = String.valueOf(UnitUtil.h());
        LogUtil.a("HomeFragmentUtil", "lastUnit = ", b2, "currentUnit = ", valueOf);
        if (b2.equals(valueOf)) {
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(10000), "wifi_scale_unit_change", valueOf, (StorageParams) null);
        csf.d(context);
    }

    public static class g implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<HomeFragment> f9362a;

        g(HomeFragment homeFragment) {
            this.f9362a = new WeakReference<>(homeFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Handler handler;
            LogUtil.a("HomeFragmentUtil", "notifyWearSDKRefreshData, errCode=", Integer.valueOf(i));
            LogUtil.c("HomeFragmentUtil", "notifyWearSDKRefreshData, objData", obj);
            HomeFragment homeFragment = this.f9362a.get();
            if (homeFragment == null || (handler = homeFragment.h) == null) {
                return;
            }
            if (i != -1 && i != 1) {
                if (i != 5) {
                    return;
                }
                handler.removeMessages(1005);
                LogUtil.a("HomeFragmentUtil", "Pull Refresh Run Card by errCode=", Integer.valueOf(i));
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 1005;
                handler.sendMessage(obtainMessage);
                return;
            }
            if (!homeFragment.a()) {
                LogUtil.h("HomeFragmentUtil", "InterfaceBaseResponseCallbackImpl card not initialized, return");
                return;
            }
            if (BaseApplication.isRunningForeground() || HomeFragment.d) {
                LogUtil.a("HomeFragmentUtil", "Refresh Cards by detail data, errCode=", Integer.valueOf(i));
                handler.removeMessages(1004);
                Message obtainMessage2 = handler.obtainMessage();
                obtainMessage2.what = 1004;
                handler.sendMessage(obtainMessage2);
            }
        }
    }

    public static void cWv_(HealthTextView healthTextView, ImageView imageView, String str, Context context) {
        int i;
        String string;
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.pic_family_health, BaseApplication.getContext().getTheme()));
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            i = 0;
        }
        if (i == 1) {
            string = context.getString(R.string._2130843939_res_0x7f021923);
        } else if (i == 2) {
            string = context.getString(R.string._2130843940_res_0x7f021924);
        } else {
            string = context.getString(R.string._2130843938_res_0x7f021922);
        }
        healthTextView.setText(string);
    }

    public static void e(Context context, String str) {
        int i;
        SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "showFamilyHealthDialogTime", String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        if (TextUtils.isEmpty(str)) {
            SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "familyHealthDialogFrequency", "1", (StorageParams) null);
            return;
        }
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("HomeFragmentUtil", "updateFamilyHealthDialogTimeAndFrequency number format exception");
            i = 0;
        }
        SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "familyHealthDialogFrequency", String.valueOf(i + 1), (StorageParams) null);
    }

    public static void i(Context context) {
        LogUtil.a("HomeFragmentUtil", "outsideOpenActivityOrAddDevice the device is not leo");
        Intent intent = new Intent(context, (Class<?>) OneKeyScanActivity.class);
        intent.setAction("com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("HomeFragmentUtil", "ActivityNotFoundException", e2.getMessage());
        }
        LogUtil.b("HomeFragmentUtil", "cheange istoEsimOrWallet false");
    }

    public static boolean b() {
        LogUtil.a("HomeFragmentUtil", "outsideOpenActivityOrAddDevice enter");
        LogUtil.a("HomeFragmentUtil", "outsideOpenActivityOrAddDevice openSim:", new GuideInteractors(BaseApplication.getContext()).d());
        return !TextUtils.isEmpty(r0);
    }

    public static void a(final Context context, final GuideInteractors guideInteractors, final String str, final boolean z) {
        final DeviceInfo f2 = oxa.a().f();
        DeviceCapability e2 = DeviceSettingsInteractors.d(context).e();
        if (e2 != null) {
            LogUtil.a("HomeFragmentUtil", "outsideOpenActivity isSuppport ESim: ", Boolean.valueOf(e2.isSupportEsim()));
            LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        if (z) {
                            HomeFragmentUtil.e(str, f2.getDeviceIdentify(), context);
                        }
                        guideInteractors.c((String) null);
                        return;
                    }
                    LogUtil.h("HomeFragmentUtil", "browsingToLogin errorCode is not success", Integer.valueOf(i));
                }
            }, "");
        } else {
            LogUtil.b("HomeFragmentUtil", "outsideOpenActivity null ==mDeviceCapability ");
        }
    }

    public static void e(String str, String str2, Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, str);
        if ("com.huawei.sim.multisim.MultiSimAuth".equals(str)) {
            intent.setAction("com.huawei.sim.multisim.MultiSimAuth");
            LogUtil.a("HomeFragmentUtil", "gotoEsim:", intent.getAction());
        }
        LogUtil.a("HomeFragmentUtil", "gotoActivity:", str);
        if (ComponentInfo.PluginPay_A_31.equals(str)) {
            pep.c(context, str2);
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("HomeFragmentUtil", "ActivityNotFoundException", e2.getMessage());
        }
    }

    public static boolean e(boolean z, String str, DeviceCapability deviceCapability) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 262455616) {
            if (str.equals("com.huawei.sim.esim.view.WirelessManagerActivity")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 288622784) {
            if (hashCode == 1426352741 && str.equals("com.huawei.sim.multisim.MultiSimAuth")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals(ComponentInfo.PluginPay_A_31)) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 != 0) {
            if (c2 != 1) {
                if (c2 != 2) {
                    return false;
                }
                if (!deviceCapability.isSupportMultiSim()) {
                    return z;
                }
            } else if (!deviceCapability.isSupportPay() && !deviceCapability.isSupportWalletOpenCard()) {
                return z;
            }
        } else if (!deviceCapability.isSupportEsim()) {
            return z;
        }
        return true;
    }

    public static boolean e(int i) {
        boolean z = i == 3 || i == 10;
        LogUtil.c("HomeFragmentUtil", "Enter isWatch type:", Integer.valueOf(i), " isWatch:", Boolean.valueOf(z));
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void cWB_(Context context, Intent intent, LinearLayout linearLayout, HealthTextView healthTextView, ohr ohrVar, ovg ovgVar, View view) {
        ThreeCircleCardData d2;
        LogUtil.a("HomeFragmentUtil", "SyncCloudDataReceiver onReceive to enter, action = ", intent.getAction());
        String stringExtra = intent.getStringExtra("sync_cloud_data_status");
        double doubleExtra = intent.getDoubleExtra("sync_cloud_data_process", 0.0d);
        if ("start_sync_cloud_data".equals(stringExtra) || "ongoing_sync_cloud_data".equals(stringExtra)) {
            ((StepsViewModel) new ViewModelProvider((ViewModelStoreOwner) context).get(StepsViewModel.class)).e(true);
            linearLayout.setVisibility(0);
            healthTextView.setText(UnitUtil.e(doubleExtra, 2, 0));
            if (ohrVar != null) {
                ohrVar.a();
            }
            SharedPreferenceManager.e(Integer.toString(10000), "last_cold_start_sync_time", System.currentTimeMillis());
            return;
        }
        if ("sync_cloud_data_finish".equals(stringExtra) || "sync_cloud_data_fail".equals(stringExtra)) {
            linearLayout.setVisibility(8);
            ((StepsViewModel) new ViewModelProvider((ViewModelStoreOwner) context).get(StepsViewModel.class)).e(false);
            if (ovgVar == null || (d2 = ovgVar.d()) == null) {
                return;
            }
            d2.a();
            return;
        }
        if ("sync_cloud_data_setting_flag".equals(stringExtra)) {
            linearLayout.setVisibility(8);
            owq.a(false);
            SharedPreferenceManager.e(context, Integer.toString(10000), "sync_cloud_data_show_process_flag", "false", (StorageParams) null);
        } else if ("sync_cloud_data_backstage".equals(stringExtra)) {
            linearLayout.setVisibility(8);
            owq.a(false);
        } else {
            LogUtil.a("HomeFragmentUtil", "SyncCloudDataReceiver onReceive is other status");
        }
    }

    public static class f implements RecommendCloudCallBack {
        @Override // com.huawei.ui.main.stories.recommendcloud.service.RecommendCloudCallBack
        public void onResponce(String str, int i) {
            LogUtil.a("HomeFragmentUtil", "RecommendCloud doRefreshBatch ", str, " resCode = ", Integer.valueOf(i));
            if (RecommendConstants.HEALTH_SLEEP_RECOMMEND.equals(str)) {
                if (i == 0) {
                    SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), RecommendConstants.SERVICE_RECOMMEND_FILE, "1", new StorageParams());
                } else {
                    SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), RecommendConstants.SERVICE_RECOMMEND_FILE, "0", new StorageParams());
                }
            }
        }
    }

    public static MessageObject a(String str, String str2, String str3) {
        MessageObject messageObject = new MessageObject();
        messageObject.setFlag(0);
        messageObject.setExpireTime(jdl.b(System.currentTimeMillis(), 1));
        messageObject.setPosition(2);
        messageObject.setNotified(0);
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setMsgId("9145");
        messageObject.setModule("WIFI_NOTIFICATION_MODULE_AUTH");
        messageObject.setMsgContent(str);
        messageObject.setDetailUri(str2);
        messageObject.setMsgTitle(str3);
        messageObject.setWeight(1);
        return messageObject;
    }

    public static void a(MarketingApi marketingApi, MarketingOption.Builder builder) {
        builder.setTypeId(51);
        marketingApi.triggerMarketingResourceEvent(builder.build());
        builder.setTypeId(52);
        marketingApi.triggerMarketingResourceEvent(builder.build());
    }

    public static class d extends BaseHandler<HomeFragment> {
        d(HomeFragment homeFragment) {
            super(homeFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cWM_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HomeFragment homeFragment, Message message) {
            int i = message.what;
            if (i == 6) {
                LogUtil.a("HomeFragmentUtil", "MSG_CHECK_USER_LEVEL");
                return;
            }
            if (i == 9) {
                LogUtil.a("HomeFragmentUtil", "MSG_CHECK_HUAWEI_WEAR_DEVICE_STATUS");
                if (CommonUtil.ce()) {
                    oxd.a().d();
                    return;
                }
                return;
            }
            if (i == 42) {
                Object obj = message.obj;
                if (obj instanceof InputBoxTemplate) {
                    homeFragment.j = (InputBoxTemplate) obj;
                    return;
                }
                return;
            }
            if (i == 234) {
                if (homeFragment.getUserVisibleHint()) {
                    LogUtil.a("HomeFragmentUtil", "MSG_CHECK_SET_MARKETING_RESOURCE");
                    homeFragment.e();
                    return;
                }
                return;
            }
            if (i == 10007) {
                c(homeFragment);
                return;
            }
            if (i == 103) {
                LogUtil.a("HomeFragmentUtil", "show todo list");
                homeFragment.e.g();
                return;
            }
            if (i == 104) {
                LogUtil.a("HomeFragmentUtil", "SEND_NOTIFY_STEP_BROADCAST");
                ObserverManagerUtil.c("NOTIFY_STEP_CARD_CHANGED", String.valueOf(message.obj));
                return;
            }
            if (i == 1004) {
                if (!hasMessages(1006)) {
                    ReleaseLogUtil.b("Dfx_HomeFragmentUtil", "CHECK_DUPLICATE_CARD_RESUME");
                    sendMessageDelayed(obtainMessage(1006), 120000L);
                    e(homeFragment);
                    return;
                }
                LogUtil.a("HomeFragmentUtil", "card data refresh not finished");
                return;
            }
            if (i != 1005) {
                return;
            }
            LogUtil.a("HomeFragmentUtil", "Pull Refresh Run Card");
            opj.b(2);
            homeFragment.d();
            opj.b(4);
            homeFragment.d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(HomeFragment homeFragment) {
            if (HomeFragment.d) {
                LogUtil.a("HomeFragmentUtil", "sHasPullRefresh");
                HomeFragment.d = false;
                d(homeFragment);
                b(homeFragment);
                LogUtil.a("HomeFragmentUtil", "delay 2s send message");
                g(homeFragment);
            }
        }

        private void g(HomeFragment homeFragment) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.d.2
                @Override // java.lang.Runnable
                public void run() {
                    String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME");
                    LogUtil.a("HomeFragmentUtil", "dialog status", b);
                    if (NpsConstantValue.QUERY_SYSTEM_BUSY.equals(b)) {
                        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME", JsInteractAddition.BI_ERROR_CODE_INVALID_AT, (StorageParams) null);
                        LogUtil.a("HomeFragmentUtil", "dialog  build status", SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "KEY_ISCREAETDIAOG_KEY_ISCREAETDIAOG_TO_HOME"));
                        d.this.sendEmptyMessageDelayed(10007, 2000L);
                    }
                }
            });
        }

        private void d(final HomeFragment homeFragment) {
            if (homeFragment.f == null) {
                return;
            }
            if (homeFragment.b()) {
                HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.d.5
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a("HomeFragmentUtil", "isRecycleViewDraggingOrComputing");
                        HomeFragment homeFragment2 = homeFragment;
                        if (homeFragment2 == null || homeFragment2.f == null) {
                            return;
                        }
                        homeFragment.f.a();
                    }
                }, 100L);
            } else {
                homeFragment.f.a();
            }
        }

        private void c(HomeFragment homeFragment) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "KEY_CONNECTION_TO_WEAR");
            LogUtil.a("HomeFragmentUtil", "app and device", b);
            if (CommonUtil.bh()) {
                return;
            }
            LogUtil.a("HomeFragmentUtil", "this phone is not huawei");
            if (CommonUtil.e(homeFragment.c, "com.huawei.bone") && "1".equals(b)) {
                LogUtil.a("HomeFragmentUtil", "app and device is not connect");
                HomeFragment.aa(homeFragment);
            }
        }

        private void e(final HomeFragment homeFragment) {
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.d.3
                @Override // java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.b("Dfx_HomeFragmentUtil", "Pull Refresh Cards");
                    d.this.a(homeFragment);
                    opj.b(-1);
                    homeFragment.d();
                    if (homeFragment.h != null) {
                        homeFragment.h.removeMessages(1006);
                    }
                }
            }, 200L);
        }

        private void b(HomeFragment homeFragment) {
            if (Utils.o()) {
                if (Utils.i()) {
                    HomeFragmentUtil.a(homeFragment.c);
                }
                odl.e(homeFragment.c);
                return;
            }
            HomeFragmentUtil.a(homeFragment.c);
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f9359a;
        private String b;
        private Context c;
        private Bundle d;
        private String e;

        a(Bundle bundle, Context context, String str, String str2, String str3) {
            this.d = bundle;
            this.c = context;
            this.b = str;
            this.f9359a = str2;
            this.e = str3;
        }

        a(Context context, String str, String str2, String str3) {
            this.c = context;
            this.b = str;
            this.f9359a = str2;
            this.e = str3;
        }

        public String a() {
            return this.b;
        }

        public String c() {
            return this.f9359a;
        }

        public String d() {
            return this.e;
        }

        public a e() {
            Bundle bundle = this.d;
            if (bundle != null) {
                String string = bundle.getString("deviceId");
                String string2 = this.d.getString("pushContent");
                if (!TextUtils.isEmpty(string)) {
                    this.b = this.c.getString(R.string.IDS_device_wifi_subuser_auth_success_push_msg, string2);
                    this.f9359a = "messagecenter://wifi_device?deviceId=" + string;
                    this.e = this.c.getString(R.string.IDS_messagecenter_device_scale);
                } else {
                    LogUtil.b("HomeFragmentUtil", "wifiMultiUserNotify productid is null");
                }
            }
            return this;
        }

        public a b() {
            this.b = this.c.getString(R.string.IDS_device_wifi_release_auth_msg);
            this.f9359a = "messagecenter://wifi_device_auth_release";
            this.e = this.c.getString(R.string.IDS_wifi_device_mult_user_release_auth_notifi_title);
            return this;
        }
    }

    public static class c implements EventBus.ICallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<Context> f9360a;

        c(Context context) {
            this.f9360a = new WeakReference<>(context);
        }

        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            WeakReference<Context> weakReference = this.f9360a;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.b("HomeFragmentUtil", "mContext is null");
                return;
            }
            if (bVar.Km_() != null && "evebus_weight_measure_notification".equals(bVar.e())) {
                e(bVar);
            } else if ("multi_user_auto_cancle_dialog".equals(bVar.e())) {
                cWL_(bVar.Kl_());
            }
        }

        private void e(EventBus.b bVar) {
            boolean z = BaseApplication.getContext().getSharedPreferences(Integer.toString(10000), 0).getBoolean(MessageConstant.KEY_PUSH_CONFLICT_FLAG, false);
            boolean booleanExtra = bVar.Km_().getBooleanExtra("isDelUser", false);
            LogUtil.a("HomeFragmentUtil", "EventBusCallback isDelUser:", Boolean.valueOf(booleanExtra), " isConflict:", Boolean.valueOf(z), " Size:", Integer.valueOf(ClaimWeightDataManager.INSTANCE.getClaimDataCatch().size()));
            if (ClaimWeightDataManager.INSTANCE.getClaimDataCatch().size() == 0) {
                LogUtil.b("HomeFragmentUtil", "Size == 0");
                return;
            }
            if ((!z || booleanExtra) && (!booleanExtra || z)) {
                return;
            }
            LogUtil.a("HomeFragmentUtil", "EventBusCallback cancle Notification");
            ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).cancelNotification(CommonUtil.m(this.f9360a.get(), "111"));
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0088  */
        /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void cWL_(android.os.Bundle r9) {
            /*
                r8 = this;
                java.lang.ref.WeakReference<android.content.Context> r0 = r8.f9360a
                java.lang.String r1 = "HomeFragmentUtil"
                if (r0 != 0) goto L10
                java.lang.String r9 = "wifiMultiUserNotify mContext is null"
                java.lang.Object[] r9 = new java.lang.Object[]{r9}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
                return
            L10:
                java.lang.Object r0 = r0.get()
                r4 = r0
                android.content.Context r4 = (android.content.Context) r4
                if (r4 != 0) goto L23
                java.lang.String r9 = "wifiMultiUserNotify context is null"
                java.lang.Object[] r9 = new java.lang.Object[]{r9}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
                return
            L23:
                if (r9 == 0) goto L96
                java.lang.String r0 = "pushType"
                java.lang.String r0 = r9.getString(r0)
                boolean r2 = android.text.TextUtils.isEmpty(r0)
                if (r2 != 0) goto L8c
                java.lang.String r2 = "release_auth"
                boolean r2 = r2.equals(r0)
                java.lang.String r3 = ""
                r5 = 0
                if (r2 == 0) goto L54
                com.huawei.ui.homehealth.HomeFragmentUtil$a r9 = new com.huawei.ui.homehealth.HomeFragmentUtil$a
                r9.<init>(r4, r5, r5, r3)
                com.huawei.ui.homehealth.HomeFragmentUtil$a r9 = r9.b()
                java.lang.String r0 = r9.a()
                java.lang.String r1 = r9.c()
                java.lang.String r9 = r9.d()
            L51:
                r3 = r9
                r5 = r1
                goto L82
            L54:
                java.lang.String r2 = "auth_success"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L78
                com.huawei.ui.homehealth.HomeFragmentUtil$a r0 = new com.huawei.ui.homehealth.HomeFragmentUtil$a
                r5 = 0
                r6 = 0
                java.lang.String r7 = ""
                r2 = r0
                r3 = r9
                r2.<init>(r3, r4, r5, r6, r7)
                com.huawei.ui.homehealth.HomeFragmentUtil$a r9 = r0.e()
                java.lang.String r0 = r9.a()
                java.lang.String r1 = r9.c()
                java.lang.String r9 = r9.d()
                goto L51
            L78:
                java.lang.String r9 = "wifiMultiUserNotify pushType is error "
                java.lang.Object[] r9 = new java.lang.Object[]{r9, r0}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
                r0 = r5
            L82:
                boolean r9 = android.text.TextUtils.isEmpty(r5)
                if (r9 != 0) goto L9f
                r8.d(r0, r5, r3)
                goto L9f
            L8c:
                java.lang.String r9 = "wifiMultiUserNotify msg is null"
                java.lang.Object[] r9 = new java.lang.Object[]{r9}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
                goto L9f
            L96:
                java.lang.String r9 = "wifiMultiUserNotify bundle is null"
                java.lang.Object[] r9 = new java.lang.Object[]{r9}
                com.huawei.hwlogsmodel.LogUtil.b(r1, r9)
            L9f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.HomeFragmentUtil.c.cWL_(android.os.Bundle):void");
        }

        private void d(String str, String str2, String str3) {
            LogUtil.a("HomeFragmentUtil", "showAuthNotify detailUri", str2);
            MessageObject a2 = HomeFragmentUtil.a(str, str2, str3);
            MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            LogUtil.a("HomeFragmentUtil", "start data notification");
            messageCenterApi.showNotification(this.f9360a.get(), a2);
            messageCenterApi.insertMessage(a2);
        }
    }

    public static class b implements DownloadUtil.DownloadListener {
        @Override // com.huawei.indoorequip.util.DownloadUtil.DownloadListener
        public void onDownloadFinish(boolean z) {
            LogUtil.a("HomeFragmentUtil", "down is Succeed", Boolean.valueOf(z));
        }
    }

    public static class InnerAw70BroadcastReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("HomeFragmentUtil", "before onReceive AW70SyncFinshBrodcast");
            if (intent == null || !"com.huawei.health.action.AW70_FITNESS_DETAIL_SYNC_SUCCESS_ACTION".equals(intent.getAction())) {
                return;
            }
            LogUtil.a("HomeFragmentUtil", "onReceive AW70SyncFinshBrodcast");
        }
    }

    public static void a(DeviceCapability deviceCapability, Context context) {
        if (deviceCapability != null) {
            LogUtil.a("HomeFragmentUtil", "isSupportAtmosphere : ", Boolean.valueOf(deviceCapability.isSupportAtmosphere()));
            if (deviceCapability.isSupportAtmosphere()) {
                DeviceSettingsInteractors.d(context).d();
                return;
            } else {
                LogUtil.a("HomeFragmentUtil", "This device do not support 5.15.3");
                return;
            }
        }
        LogUtil.a("HomeFragmentUtil", "pushLocalPressInfo can not get mDeviceCapability.");
    }

    public static void d(DeviceCapability deviceCapability, final Context context) {
        if (deviceCapability != null && deviceCapability.isWeatherPush()) {
            LogUtil.a("HomeFragmentUtil", "isSupportWeatherPush : ", Boolean.valueOf(deviceCapability.isWeatherPush()));
            jqi.a().getSwitchSetting("weather_switch_status", new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HomeFragmentUtil", "pushWeatherInfo errorCode = ", Integer.valueOf(i));
                    boolean z = (i == 0 && (obj instanceof String) && "false".equals((String) obj)) ? false : true;
                    LogUtil.a("HomeFragmentUtil", "WEATHER_SWITCH_STATUS isWeatherSwitchOpen = ", Boolean.valueOf(z));
                    if (z) {
                        DeviceSettingsInteractors.d(context).c();
                    }
                }
            });
        } else {
            LogUtil.h("HomeFragmentUtil", "pushWeatherData can not get mDeviceCapability.");
        }
    }

    public static void e(Context context) {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.triggerMarketingResourceEvent(new MarketingOption.Builder().setContext(context).setPageId(18).setTypeId(2).build());
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.b("HomeFragmentUtil", "setInitMarketing context is null");
            return false;
        }
        nmh.d(ehu.b);
        nmh.c(3001);
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            return false;
        }
        LogUtil.a("HomeFragmentUtil", "setInitMarketing");
        MarketingOption.Builder builder = new MarketingOption.Builder();
        builder.setContext(context);
        builder.setPageId(18);
        marketingApi.requestMarketingResource(builder.build());
        HashMap hashMap = new HashMap();
        hashMap.put("open_specific_page", "Health");
        builder.setTypeId(49);
        builder.setTriggerEventParams(hashMap);
        marketingApi.triggerMarketingResourceEvent(builder.build());
        b(marketingApi, builder);
        return true;
    }

    public static void b(MarketingApi marketingApi, MarketingOption.Builder builder) {
        a(marketingApi, builder);
    }

    public static void d(Context context) {
        if (context == null) {
            LogUtil.b("HomeFragmentUtil", "initHiHealth error: context is null");
            return;
        }
        final SharedPreferences sharedPreferences = context.getSharedPreferences("HiHealth_UUID", 0);
        if (sharedPreferences.getString("PHONE_UUID", null) != null) {
            return;
        }
        HiHealthManager.d(context).fetchPhoneDataClient(new HiDataClientListener() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.8
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list) {
                if (koq.b(list)) {
                    return;
                }
                sharedPreferences.edit().putString("PHONE_UUID", list.get(0).getDeviceUuid()).commit();
            }
        });
    }

    public static void f(Context context) {
        if (opj.d() == -2) {
            opj.b(CardFlowInteractors.a(context, "HomeCardRefreshIndex", -2));
            CardFlowInteractors.c(context, "HomeCardRefreshIndex", -2);
        }
    }

    public static void c(Context context) {
        opj.b(-2);
        CardFlowInteractors.c(context, "HomeCardRefreshIndex", -2);
    }

    public static void e(final HomeFragment homeFragment) {
        if (homeFragment == null) {
            LogUtil.h("HomeFragmentUtil", "showSecurityManagerSettingSwitchDialog fragment is null");
        } else {
            GRSManager.a(BaseApplication.getContext()).e("domainHealthVmall", new GrsQueryCallback() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.9
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(final String str) {
                    HomeFragment.this.h.post(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.9.4
                        @Override // java.lang.Runnable
                        public void run() {
                            HomeFragmentUtil.d(str, HomeFragment.this);
                        }
                    });
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.h("HomeFragmentUtil", "showSecurityManagerSettingSwitchDialog onCallBackFail");
                }
            });
        }
    }

    public static void d(String str, HomeFragment homeFragment) {
        if (TextUtils.isEmpty(str) || homeFragment == null) {
            LogUtil.h("HomeFragmentUtil", "showSecurityManagerSettingSwitchDialogOnMainThread url or homeFragment is null");
            return;
        }
        SecurityManagerSettingSwitchDialog.Builder builder = new SecurityManagerSettingSwitchDialog.Builder(homeFragment.c);
        builder.d(str);
        builder.d().show();
    }

    public static void b(CustomTitleBar customTitleBar, Context context) {
        customTitleBar.setLeftButtonVisibility(8);
        customTitleBar.setTitleText(context.getString(R.string._2130843252_res_0x7f021674));
        if (nrs.a(context)) {
            customTitleBar.setTitleSize(context.getResources().getDimension(R.dimen._2131362673_res_0x7f0a0371));
        } else {
            customTitleBar.setTitleSize(context.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
        if (nsn.s()) {
            customTitleBar.setTitleSize(context.getResources().getDimension(R.dimen._2131363048_res_0x7f0a04e8));
        }
        customTitleBar.setTitleBarBackgroundColor(context.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        customTitleBar.setTitleTextColor(context.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        customTitleBar.setRightButtonVisibility(0);
        customTitleBar.setRightButtonDrawable(context.getResources().getDrawable(R.drawable._2131429707_res_0x7f0b094b), nsf.h(R.string._2130850635_res_0x7f02334b));
    }

    public static void cWy_(Context context, BroadcastReceiver broadcastReceiver) {
        LogUtil.a("HomeFragmentUtil", "enter registerBattery");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.BATTERY_LEVEL");
        intentFilter.addAction("com.huawei.bone.action.BATTERY_LEVEL");
        if (context == null) {
            LogUtil.a("HomeFragmentUtil", "registerBattery mContext is null.Activity is finished");
        } else {
            BroadcastManagerUtil.bFC_(context, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
        }
    }

    public static void cWF_(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HomeFragmentUtil", "unregisterWifiUserInfoBroadcast exception");
        } catch (RuntimeException unused2) {
            LogUtil.b("HomeFragmentUtil", "unregisterWifiUserInfoBroadcast exception");
        }
    }

    public static void cWD_(Context context, BroadcastReceiver broadcastReceiver) {
        LogUtil.a("HomeFragmentUtil", "enter unregisterAw70SyncFinishBroadcast");
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HomeFragmentUtil", "unregisterAw70SyncFinishBroadcast ");
        } catch (RuntimeException unused2) {
            LogUtil.b("HomeFragmentUtil", "unregisterAw70SyncFinishBroadcast ");
        }
    }

    public static void b(Context context, CommonUiBaseResponse commonUiBaseResponse) {
        a(context, commonUiBaseResponse);
    }

    public static CustomViewDialog.Builder cWA_(View view, final Context context, final Context context2) {
        return new CustomViewDialog.Builder(context).c(false).czg_(view).cze_(R.string._2130843905_res_0x7f021901, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("HomeFragmentUtil", "show family healty dialog click");
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 2);
                ixx.d().d(context, AnalyticsValue.HEALTH_FAMILY_ZONE_2040078.value(), hashMap, 0);
                ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).gotoFamilyHealth(context2, knl.e("sIsFirstHealth"));
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    public static void cWI_(FragmentActivity fragmentActivity, Intent intent, int i, final Context context) {
        if (i == 0) {
            b(context, new CommonUiBaseResponse() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.13
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i2, Object obj) {
                    HomeFragmentUtil.d(context, obj);
                }
            });
            return;
        }
        if (i == 1) {
            j(context);
        } else if (i == 2) {
            csc.d().LI_(fragmentActivity, intent.getExtras());
        } else {
            LogUtil.b("HomeFragmentUtil", "type is error.");
        }
    }

    public static void d(final Context context, final PermissionsResultAction permissionsResultAction) {
        LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    PermissionUtil.b(context, PermissionUtil.PermissionType.CAMERA, permissionsResultAction);
                }
            }
        }, "");
    }

    public static void b(final Context context, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HomeFragmentUtil", "deviceInfo is null.");
            return;
        }
        final PayApi payApi = (PayApi) Services.c("TradeService", PayApi.class);
        final DeviceBenefitQueryParam e2 = njn.e(deviceInfo, DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL, true);
        payApi.checkDeviceBenefitMessage(e2);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.3
            @Override // java.lang.Runnable
            public void run() {
                if (!TextUtils.isEmpty(SharedPreferenceManager.b(context, Integer.toString(10000), "DEVICE_BIND_INBOX_KEY"))) {
                    LogUtil.a("HomeFragmentUtil", "device benefit is triggered.");
                } else {
                    payApi.activateDeviceBenefitPage(e2);
                }
            }
        });
    }

    public static void cWw_(Context context, Handler handler, AchievementCardData achievementCardData) {
        if (CommonUtil.ce()) {
            handler.removeMessages(9);
            handler.sendEmptyMessageDelayed(9, 3000L);
            if (efb.a()) {
                achievementCardData.b();
            } else {
                owl.b(context).h();
            }
        }
    }

    public static void e(Context context, FragmentActivity fragmentActivity, DeviceInfo deviceInfo) {
        DeviceCapability e2 = DeviceSettingsInteractors.d(context).e(deviceInfo.getDeviceIdentify());
        if (e2 == null || !e2.isSupportSosTransmission()) {
            return;
        }
        LogUtil.a("HomeFragmentUtil", "showEmergencyContactChangeDialog capability is support");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "emergency_info", "false", (StorageParams) null);
        if (fragmentActivity == null || fragmentActivity.isFinishing()) {
            return;
        }
        gmc.d(context);
    }

    public static void cWz_(Handler handler) {
        handler.postDelayed(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.5
            @Override // java.lang.Runnable
            public void run() {
                if (!WearHomeActivity.e()) {
                    LogUtil.a("HomeFragmentUtil", "scrollToSmartDeviceCard return WearHomeActivity.isFromWearDetail():", Boolean.valueOf(WearHomeActivity.e()));
                } else {
                    WearHomeActivity.a(false);
                }
            }
        }, 10L);
    }

    public static void cWH_(Intent intent, Context context, boolean z) {
        if (Utils.o() || intent == null) {
            return;
        }
        if (z) {
            LogUtil.a("HomeFragmentUtil", "managerWeightNps isWearDeviceBind true, return!");
        } else {
            if (!HagridNpsManager.getInstance().isBindWifiDevice()) {
                LogUtil.a("HomeFragmentUtil", "managerWeightNps isBindHagridDevice false");
                return;
            }
            oaj.a().cTF_(intent.getExtras());
            oxa.a().a(context);
        }
    }

    public static void c(Context context, AchievementCardData achievementCardData, ohr ohrVar, ovg ovgVar, FunctionSetCardData functionSetCardData, FunctionMenuCardData functionMenuCardData, OperationCardData operationCardData, OperaMsgCardData operaMsgCardData, boolean z, HealthTrendCardData healthTrendCardData, HealthHeadLinesCardData healthHeadLinesCardData, HomeCardAdapter homeCardAdapter) {
        if (!efb.a()) {
            owl.b(context).b();
            owl.c();
        }
        if (efb.a()) {
            a(achievementCardData);
            a(ohrVar);
            a(ovgVar);
            a(functionSetCardData);
            a(operationCardData);
            a(operaMsgCardData);
            if (efb.o()) {
                a(functionMenuCardData);
            }
            if (!z) {
                a(healthHeadLinesCardData);
            }
            if (efb.m() && healthTrendCardData != null) {
                a(healthTrendCardData);
            }
            if (homeCardAdapter != null) {
                homeCardAdapter.c();
            }
        }
    }

    public static String e() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "UIHLH_HomeFragment");
        return deviceInfo != null ? deviceInfo.getDeviceIdentify() : "";
    }

    public static boolean e(int i, int i2, int i3) {
        if ((e(i2) && e(i3)) || i2 == i3 || i3 == -1 || i == 2) {
            return false;
        }
        LogUtil.a("HomeFragmentUtil", "get a connect change report,but it is not belong to current device,so return");
        return true;
    }

    public static void a() {
        LogUtil.a("HomeFragmentUtil", "PullRefresh start");
        try {
            HealthSyncUtil.b(SyncApi.FITNESS);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HomeFragmentUtil", "FitnessAdvice syncData failed");
        }
        try {
            HealthSyncUtil.b(SyncApi.HEALTH_LIFE);
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("HomeFragmentUtil", "HealthLifeModel syncData failed");
        }
    }

    public static void c(Observer observer) {
        try {
            ObserverManagerUtil.d(observer, "ALLOW_VISIBLE_HINT_LOAD");
        } catch (IllegalArgumentException | IllegalStateException unused) {
            LogUtil.b("HomeFragmentUtil", "registerAllowVisibleHintObserver exception");
        }
    }

    public static void d(Observer observer) {
        try {
            ObserverManagerUtil.e(observer, "ALLOW_VISIBLE_HINT_LOAD");
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HomeFragmentUtil", "unregisterAllowVisibleHintLoadObserver IllegalArgumentException");
        }
    }

    public static void g(final Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.HomeFragmentUtil.2
            @Override // java.lang.Runnable
            public void run() {
                cjn.e(context).a();
            }
        });
    }

    public static void c(Context context, String str) {
        ixx.d().d(context, AnalyticsValue.HEALTH_FAMILY_ZONE_2040079.value(), new HashMap(), 0);
        LogUtil.a("HomeFragmentUtil", "Family health Dialog show showFamilyHealthDialogFrequency", str);
    }

    public static class e {
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00d8, code lost:
        
            if (java.lang.System.currentTimeMillis() > (r5 + 2592000000L)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x00f9, code lost:
        
            if (java.lang.System.currentTimeMillis() > (a(r17, "first_date_enter_app_" + r2) + 604800000)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0103, code lost:
        
            if (java.lang.System.currentTimeMillis() > (r5 + 2592000000L)) goto L38;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static boolean b(android.content.Context r17) {
            /*
                Method dump skipped, instructions count: 276
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.HomeFragmentUtil.e.b(android.content.Context):boolean");
        }

        public static void b(Context context, String str) {
            SharedPreferenceManager.e(context, String.valueOf(10000), "anal_and_impro_show_count_" + str, String.valueOf(CommonUtil.h(SharedPreferenceManager.b(context, String.valueOf(10000), "anal_and_impro_show_count_" + str)) + 1), (StorageParams) null);
        }

        public static void d(Context context, String str, long j) {
            SharedPreferenceManager.e(context, String.valueOf(10000), str, String.valueOf(j), (StorageParams) null);
            LogUtil.c("HomeFragmentUtil", "saveValueByKey key is ", str, " value is ", Long.valueOf(j));
        }

        public static void b(boolean z) {
            gmz.d().c(11, z, (String) null, (IBaseResponseCallback) null);
            a(z);
            csf.e(z);
        }

        public static void d() {
            Context context = BaseApplication.getContext();
            HashMap hashMap = new HashMap(16);
            hashMap.put("type", "0");
            hashMap.put("entrance", "e1");
            hashMap.put("service_improvement_status", "0");
            iyb iybVar = new iyb();
            iybVar.d(hashMap);
            HashMap hashMap2 = new HashMap(1);
            hashMap2.put("pageId", "Today_0001");
            iybVar.e(hashMap2);
            ixx.d().a(context, AnalyticsValue.HEALTH_MINE_SETTINGS_IMPROVE_PLAN_2040021.value(), iybVar, 0);
        }

        private static int d(Context context, String str) {
            return CommonUtil.h(SharedPreferenceManager.b(context, String.valueOf(10000), "anal_and_impro_show_count_" + str));
        }

        private static long a(Context context, String str) {
            String b = SharedPreferenceManager.b(context, String.valueOf(10000), str);
            LogUtil.c("HomeFragmentUtil", "getValueByKey key is ", str, " value is ", b);
            return CommonUtil.g(b);
        }

        private static void a(boolean z) {
            Context context = BaseApplication.getContext();
            HashMap hashMap = new HashMap(16);
            hashMap.put("entrance", "e1");
            hashMap.put("click", "1");
            if (z) {
                hashMap.put("type", "1");
                hashMap.put("service_improvement_status", "1");
            } else {
                hashMap.put("type", "3");
                hashMap.put("service_improvement_status", "0");
            }
            iyb iybVar = new iyb();
            iybVar.d(hashMap);
            HashMap hashMap2 = new HashMap(1);
            hashMap2.put("pageId", "Today_0001");
            iybVar.e(hashMap2);
            ixx.d().a(context, AnalyticsValue.HEALTH_MINE_SETTINGS_IMPROVE_PLAN_2040021.value(), iybVar, 0);
            KeyValDbManager.b(context).e("key_user_experience_plan_check_box", String.valueOf(z));
        }

        private static void e(Context context, String str) {
            String b = SharedPreferenceManager.b(context, Integer.toString(10015), "anal_and_impro_new_user_" + str);
            String e = SharedPreferenceManager.e(Integer.toString(10015), "user_first_login_app", "");
            if (TextUtils.isEmpty(b)) {
                if ("first_login".equals(e)) {
                    if (a(context, "first_date_enter_app_" + str) == 0) {
                        b = "new_user";
                        SharedPreferenceManager.e(context, Integer.toString(10015), "anal_and_impro_new_user_" + str, b, (StorageParams) null);
                    }
                }
                b = "old_user";
                SharedPreferenceManager.e(context, Integer.toString(10015), "anal_and_impro_new_user_" + str, b, (StorageParams) null);
            }
            LogUtil.c("HomeFragmentUtil", "judgeUserStatus userStatus =", b, " first_login =", e);
        }
    }
}
