package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.activity.update.UpdateVersionActivity;
import com.huawei.ui.homehealth.device.CardDeviceFragment;
import com.huawei.ui.homehealth.device.sitting.RecommendedItem;
import com.huawei.ui.homehealth.syncwifi.WifiConnectReceiver;
import com.huawei.ui.homewear21.home.manager.hwnfcwalletmgr.WalletAppManager;
import com.huawei.ui.main.stories.userprofile.activity.WorkModeConflictDialogActivity;
import com.huawei.watchface.WatchFaceApi;
import com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity;
import defpackage.dcz;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: classes6.dex */
public class ogj {
    private static HashMap<String, Integer> b = new HashMap<>(16);
    private static boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    private static boolean f15651a = true;

    public static void cZI_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "registerNonLocalBroadcastReceiver nonLocalBroadcastReceiver == null");
            return;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
        intentFilter.addAction("com.huawei.bone.action.SYSTEM_BLUETOOTH_UNBIND_DEVICE");
        intentFilter.addAction("com.huawei.bone.action.DEVICE_UPGRADING");
        intentFilter.addAction("action_ota_check_new_version_state");
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        intentFilter.addAction("broadcast_receiver_user_setting");
        intentFilter.addAction("com.huawei.bone.action.UI_DEVICE_LIST_CHANGED");
        intentFilter.addAction("com.huawei.health.action.CLOUD_CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), broadcastReceiver, intentFilter, LocalBroadcast.c, null);
    }

    public static void cZG_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "registerBatteryBroadcast deviceBatteryReceiver == null");
            return;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "enter registerBatteryBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.BATTERY_LEVEL");
        intentFilter.addAction("com.huawei.bone.action.BATTERY_LEVEL");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), broadcastReceiver, intentFilter, LocalBroadcast.c, null);
    }

    public static void cZJ_(BroadcastReceiver broadcastReceiver, BroadcastReceiver broadcastReceiver2) {
        if (broadcastReceiver == null || broadcastReceiver2 == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "registerWatchfaceBroadcastReceiver BroadcastReceiver == null");
            return;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "enter registerWatchfaceBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PrivacyStatementActivity.ACTION_WATCHFACE_SERVICE_DISABLE);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
        }
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.SIM_STATE_CHANGED");
        BaseApplication.getContext().registerReceiver(broadcastReceiver2, intentFilter2);
    }

    public static void cZH_(final Context context, BroadcastReceiver broadcastReceiver, final boolean z) {
        if (context == null || broadcastReceiver == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "registerLoginStatusReceiver Parameters is null");
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ogj.2
            @Override // java.lang.Runnable
            public void run() {
                boolean hasLoginAccount;
                if (CommonUtil.z(context)) {
                    String logoutFlag = ThirdLoginDataStorageUtil.getLogoutFlag();
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "registerLoginStatusReceiver logoutFlag ", logoutFlag);
                    hasLoginAccount = "false".equals(logoutFlag);
                } else {
                    hasLoginAccount = HuaweiLoginManager.hasLoginAccount(context);
                }
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "registerLoginStatusReceiver isLogin ", Boolean.valueOf(hasLoginAccount));
                if (hasLoginAccount) {
                    ogj.a(z);
                }
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        BroadcastManagerUtil.bFA_(BaseApplication.getContext(), broadcastReceiver, intentFilter, LocalBroadcast.c, null);
    }

    public static void n() {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "enter registerWifiConnectedReceiver.");
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("saved_support_sync_wifi");
        if (owp.l(BaseApplication.getContext()) && "1".equals(e2)) {
            WifiConnectReceiver.a().e();
        } else {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "not have location permissions or not support sync wifi");
        }
    }

    public static void cZL_(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "unRegisterNonLocalBroadcastReceiver nonLocalBroadcastReceiver == null");
            return;
        }
        try {
            BaseApplication.getContext().unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e2) {
            LogUtil.a("CardDeviceFragment CardDeviceUtil", "unRegisterNonLocalBroadcastReceiver Exception: ", ExceptionUtils.d(e2));
        }
    }

    public static void cZM_(BroadcastReceiver broadcastReceiver, BroadcastReceiver broadcastReceiver2) {
        if (broadcastReceiver == null || broadcastReceiver2 == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "unRegisterWatchfaceBroadcastReceiver BroadcastReceiver == null");
            return;
        }
        try {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(broadcastReceiver);
            BaseApplication.getContext().unregisterReceiver(broadcastReceiver2);
        } catch (IllegalArgumentException unused) {
            LogUtil.a("CardDeviceFragment CardDeviceUtil", "unRegisterWatchfaceBroadcastReceiver Exception");
        }
    }

    public static void b(ExecutorService executorService) {
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: ogj.1
                @Override // java.lang.Runnable
                public void run() {
                    DeviceCapability d = cvs.d();
                    DeviceInfo f = oxa.a().f();
                    if (d != null && d.isSupportWatchFace() && f != null && (f.getPowerSaveModel() != 1 || !pep.d(f.getProductType()))) {
                        if (!DevicePairGuideActivity.e || CommonUtil.bh()) {
                            pep.g(BaseApplication.getContext());
                        } else {
                            LogUtil.a("CardDeviceFragment CardDeviceUtil", "initDeviceState, is first addDevice");
                        }
                    }
                    if (d != null && d.isSupportMenstrual()) {
                        ogj.a(f);
                    }
                    if (d == null || !d.isMessageAlert()) {
                        return;
                    }
                    ogj.b();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(DeviceInfo deviceInfo) {
        new jri().c(new IBaseResponseCallback() { // from class: ogj.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "queryMenstrualSwitch errorCode:", Integer.valueOf(i));
                if (i == 0 && (obj instanceof MenstrualSwitchStatus)) {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "device connected sendMenstrualSwitchInMain");
                    omz.a().b((MenstrualSwitchStatus) obj);
                }
            }
        });
        omz.a().e(deviceInfo);
    }

    public static void b() {
        ReleaseLogUtil.e("CardDeviceFragment CardDeviceUtil", "Overwrite Installation open default app");
        int parseInt = Integer.parseInt("1");
        for (String str : bfg.d) {
            String switchSettingFromLocal = jqi.a().getSwitchSettingFromLocal(str, 10001);
            ReleaseLogUtil.e("CardDeviceFragment CardDeviceUtil", "getSwitchSettingFromLocal packageName: ", str, ", value: ", switchSettingFromLocal);
            if (TextUtils.isEmpty(switchSettingFromLocal)) {
                jjb.b().b(str, parseInt);
            }
        }
    }

    public static void g() {
        List<DeviceInfo> h;
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        boolean e2 = SharedPreferenceManager.e(BaseApplication.getContext(), accountInfo);
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "isHonorSpecifiedDeviceAndPrivacyIsAgree isAgree:", Boolean.valueOf(e2));
        if (e2 || !a(BaseApplication.getContext(), accountInfo) || !dks.d(BaseApplication.getContext()) || (h = cpl.c().h()) == null || h.isEmpty()) {
            return;
        }
        for (int i = 0; i < h.size(); i++) {
            DeviceInfo deviceInfo = h.get(i);
            if (deviceInfo != null && cvz.c(deviceInfo)) {
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(deviceInfo.getDeviceIdentify());
                oae.c(BaseApplication.getContext()).e(arrayList, true);
            }
        }
    }

    private static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "HONOR_NOT_CLOUD_USE_ID_KEY";
        }
        String b2 = SharedPreferenceManager.b(context, String.valueOf(10000), "APP_UPLOAD_NATIVE_KEY");
        if (TextUtils.isEmpty(b2)) {
            SharedPreferenceManager.e(context, String.valueOf(10000), "APP_UPLOAD_NATIVE_KEY", str, new StorageParams(1));
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "nativeKey is empty");
            return false;
        }
        if (b2 != null && b2.equals(str)) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "nativeKey equals tempUserId");
            return false;
        }
        SharedPreferenceManager.e(context, String.valueOf(10000), "APP_UPLOAD_NATIVE_KEY", str, new StorageParams(1));
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "isDeleteHonorDevice");
        return true;
    }

    public static boolean h() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "sp_update_cancel_times");
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "updateLayout countShowTimes number ", b2);
        return TextUtils.isEmpty(b2) || CommonUtil.a(b2, 10) < 2;
    }

    public static boolean m() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "sp_update_check_time");
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "updateLayout isEnableDialog lastTime ", b2);
        if (TextUtils.isEmpty(b2)) {
            return true;
        }
        long g = CommonUtil.g(b2);
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "updateLayout isEnableDialog NowTime ", Long.valueOf(System.currentTimeMillis()));
        return Math.abs(System.currentTimeMillis() - g) > 86400000;
    }

    public static boolean f() {
        ArrayList arrayList = new ArrayList(10);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, "CardDeviceFragment CardDeviceUtil");
        if (deviceList != null && deviceList.size() > 0) {
            for (DeviceInfo deviceInfo : deviceList) {
                if ("main_relationship".equals(deviceInfo.getRelationship()) || cvt.c(deviceInfo.getProductType())) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() > 0) {
            if (arrayList.size() == 1) {
                DeviceInfo deviceInfo2 = (DeviceInfo) arrayList.get(0);
                if (cvt.a(deviceInfo2.getProductType(), deviceInfo2.getAutoDetectSwitchStatus())) {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "isHas aw70 run work mode return true");
                    return true;
                }
            }
            LogUtil.a("CardDeviceFragment CardDeviceUtil", "one wear device return false");
            return false;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "connectDeviceList is zero return true");
        return true;
    }

    public static void l() {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "sp_update_check_time", System.currentTimeMillis() + "", new StorageParams(0));
        oau.e("1");
    }

    public static void p() {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "sp_update_check_time", System.currentTimeMillis() + "", new StorageParams(0));
        StorageParams storageParams = new StorageParams(0);
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "sp_update_cancel_times");
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "updateLayout setDialogShowTime,number ", b2);
        if (TextUtils.isEmpty(b2)) {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "sp_update_cancel_times", "1", storageParams);
        } else {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "sp_update_cancel_times", String.valueOf(CommonUtil.a(b2, 10) + 1), storageParams);
        }
    }

    public static void e(Context context) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(context).e(BaseApplication.getContext().getResources().getString(R.string.IDS_device_fragment_connect_device)).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: ogj.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("CardDeviceFragment CardDeviceUtil", "showDisConnectedDialog,click known button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public static void cZK_(final Context context, final Activity activity, ExecutorService executorService) {
        if (context == null || activity == null || executorService == null) {
            return;
        }
        executorService.execute(new Runnable() { // from class: ogj.5
            @Override // java.lang.Runnable
            public void run() {
                final DeviceInfo a2 = jpt.a("CardDeviceFragment CardDeviceUtil");
                if (a2 == null) {
                    LogUtil.h("CardDeviceFragment CardDeviceUtil", "startDeviceUpdate deviceInfo is null");
                } else {
                    activity.runOnUiThread(new Runnable() { // from class: ogj.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Intent intent = new Intent();
                            intent.setClass(context, UpdateVersionActivity.class);
                            intent.putExtra("device_id", a2.getDeviceIdentify());
                            ogj.cZC_(context, intent, "UpdateVersionActivity");
                        }
                    });
                }
            }
        });
    }

    public static boolean d() {
        if (jkj.d().j()) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "wear device is OTAing");
            return true;
        }
        LogUtil.h("CardDeviceFragment CardDeviceUtil", "no OTAing");
        return false;
    }

    public static GridLayoutManager b(List<RecommendedItem> list) {
        if (list == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "recommendedLists == null");
            return d(2);
        }
        if (nsn.ag(BaseApplication.getContext())) {
            if (list.size() == 1) {
                return d(1);
            }
            if (list.size() == 2) {
                return d(2);
            }
            if (list.size() == 3) {
                return d(3);
            }
            if (list.size() == 4) {
                return d(4);
            }
            return d(5);
        }
        if (list.size() == 1) {
            return d(1);
        }
        return d(2);
    }

    public static GridLayoutManager d(int i) {
        return new GridLayoutManager(BaseApplication.getContext(), i, 1, false) { // from class: ogj.6
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
    }

    public static LinearLayoutManager e() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(0);
        return linearLayoutManager;
    }

    public static void e(Context context, String str) {
        if (context == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "context == null");
            return;
        }
        oau.g();
        if (WalletAppManager.getInstance().isOverseaService()) {
            WalletAppManager.getInstance().startNfcWalletH5();
        } else {
            pep.b(context);
            pep.c(context, str);
        }
    }

    public static void cZE_(Activity activity) {
        if (activity == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "activity == null");
        } else {
            new oxi(activity, "com.huawei.health.MainActivity").e(-1);
            HwWatchFaceUtil.b().e(1);
        }
    }

    public static void c(Context context, String str, String str2, String str3) {
        if (context == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "startWebViewActivity context == null");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setClassName(BaseApplication.getContext().getPackageName(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str));
        intent.putExtra("productId", str);
        dcz d = ResourceManager.e().d(str);
        intent.putExtra("uniqueId", str2);
        if (d != null) {
            if (d.n() != null) {
                intent.putExtra("name", dcx.d(str, d.n().b()));
            }
            if (d.l() != null) {
                intent.putExtra("deviceType", d.l().name());
            }
            if (d.x() != null) {
                intent.putExtra(Constants.KEY_BLE_SCAN_MODE, d.x().c());
            }
        }
        intent.putExtra("bleIntroductionType", str3);
        cZC_(context, intent, "com.huawei.operation.activity.WebViewActivity");
    }

    public static void c(Context context, String str) {
        if (context == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "openWearHome context == null");
            return;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "Enter openWearHome ");
        if (jkj.d().c(str) == 6) {
            LogUtil.a("CardDeviceFragment CardDeviceUtil", "wear device is OTAing");
            Intent intent = new Intent();
            intent.setClassName(context, "com.huawei.ui.device.activity.update.UpdateVersionActivity");
            intent.putExtra("device_id", str);
            cZC_(context, intent, "UpdateVersionActivity");
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(10099), "key_ui_nps_enter_wear_home", "true", (StorageParams) null);
        Intent intent2 = new Intent();
        intent2.setClassName(context, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent2.putExtra("device_id", str);
        intent2.putExtra("FROM_APP_HOME_PAGE_TYPE", true);
        cZC_(context, intent2, "com.huawei.ui.homewear21.home.WearHomeActivity");
        pep.d(context);
    }

    public static void d(Context context, cpm cpmVar, boolean z) {
        if (context == null || cpmVar == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "jumpToNoConnectActivity deviceInfoForWear == null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        intent.putExtra("is_cloud_device", cpmVar.g());
        intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, cpmVar.d());
        intent.putExtra("device_identify", cpmVar.a());
        intent.putExtra("device_picID", cpmVar.m());
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, cpmVar.i());
        intent.putExtra("device_only_one", z);
        cZC_(context, intent, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        pep.d(context);
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "onclick wear not connected name:", cpmVar.d(), "device_type :", Integer.valueOf(cpmVar.i()));
    }

    public static void a(Context context, int i) {
        DeviceInfo deviceInfo;
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "gotoConflictDialogActivity enter");
        if (context == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "gotoConflictDialogActivity context is null");
            return;
        }
        List<DeviceInfo> h = cpl.c().h();
        if (h != null) {
            Iterator<DeviceInfo> it = h.iterator();
            while (it.hasNext()) {
                deviceInfo = it.next();
                if (deviceInfo.getDeviceConnectState() == 2) {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "gotoConflictDialogActivity find connected device");
                    break;
                }
            }
        }
        deviceInfo = null;
        if (deviceInfo == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "gotoConflictDialogActivity connectedDevice is null");
            return;
        }
        String format = String.format(Locale.ENGLISH, context.getString(R.string._2130842682_res_0x7f02143a), a(deviceInfo.getProductType()), a(i));
        Intent intent = new Intent();
        intent.putExtra("content", format);
        intent.setClass(context, WorkModeConflictDialogActivity.class);
        cZC_(context, intent, "WorkModeConflictDialogActivity");
    }

    private static String a(int i) {
        DeviceInfo d = cpl.c().d();
        if (d == null) {
            return com.huawei.hms.hihealth.data.DeviceInfo.STR_TYPE_UNKNOWN;
        }
        String str = "PORSCHE DESIGN";
        if ((TextUtils.isEmpty(d.getDeviceName()) || !TextUtils.equals(d.getDeviceName(), "PORSCHE DESIGN")) && (TextUtils.isEmpty(d.getDeviceModel()) || !TextUtils.equals(d.getDeviceModel(), "PORSCHE DESIGN"))) {
            str = jfu.c(i).f();
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "transDeviceProductTypeIntToStr: mDeviceProductType = ", str);
        return str;
    }

    public static void b(cpm cpmVar, List<DeviceInfo> list) {
        if (cpmVar == null || list == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "handleDeviceState deviceInfoForWear == null");
            return;
        }
        if (cvt.c(cpmVar.i())) {
            LogUtil.a("CardDeviceFragment CardDeviceUtil", "handleWorkMode goingReConnected == AW70");
            for (DeviceInfo deviceInfo : list) {
                if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "Reconnected AW70 set device enable");
                    deviceInfo.setDeviceActiveState(1);
                    deviceInfo.setDeviceConnectState(1);
                } else if (cvt.c(deviceInfo.getProductType())) {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "Connected AW70 target device disable");
                    deviceInfo.setDeviceActiveState(0);
                    deviceInfo.setDeviceConnectState(3);
                } else {
                    LogUtil.h("CardDeviceFragment CardDeviceUtil", "handleDeviceState is other");
                }
            }
            return;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
        for (DeviceInfo deviceInfo2 : list) {
            if (cpmVar.a().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "handleWorkMode set device enable");
                deviceInfo2.setDeviceActiveState(1);
                deviceInfo2.setDeviceConnectState(1);
            }
            if (!cpmVar.a().equalsIgnoreCase(deviceInfo2.getDeviceIdentify()) && deviceInfo2.getAutoDetectSwitchStatus() != 1 && !obb.e(cpmVar.i()) && deviceInfo2.getDeviceActiveState() == 1) {
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "handleWorkMode target device disable");
                deviceInfo2.setDeviceActiveState(0);
                deviceInfo2.setDeviceConnectState(3);
            }
        }
        cpl.c().f();
    }

    public static void c(Context context, int i) {
        if (context == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "showTipDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(context).e(context.getResources().getString(i)).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: ogj.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("CardDeviceFragment CardDeviceUtil", "showTipDialog，click known button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public static void a(List<cjv> list, cjv cjvVar) {
        if (list == null || cjvVar == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "productInfoListAdd connectedList == null");
            return;
        }
        if (!list.isEmpty()) {
            Object i = list.get(0).i();
            if (i instanceof cpm) {
                if (((cpm) i).e() == 2) {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "add to normal");
                    e(list, cjvVar);
                    return;
                } else {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "add to first");
                    list.add(0, cjvVar);
                    return;
                }
            }
            return;
        }
        list.add(cjvVar);
    }

    private static void e(List<cjv> list, cjv cjvVar) {
        if (list.size() > 1) {
            list.add(1, cjvVar);
        } else {
            list.add(cjvVar);
        }
    }

    public static void d(ExecutorService executorService) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "isMultipleDevices");
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: ogj.10
                @Override // java.lang.Runnable
                public void run() {
                    List<DeviceInfo> h = cpl.c().h();
                    int i = 0;
                    if (h == null || h.isEmpty()) {
                        LogUtil.h("CardDeviceFragment CardDeviceUtil", "deviceInfoList wear_device_count 0");
                        SharedPreferenceManager.e(BaseApplication.getContext(), 0);
                        return;
                    }
                    Iterator<DeviceInfo> it = h.iterator();
                    while (it.hasNext()) {
                        int productType = it.next().getProductType();
                        LogUtil.a("CardDeviceFragment CardDeviceUtil", "deviceGroupInfo type:", Integer.valueOf(productType));
                        if (jfu.n(productType)) {
                            i++;
                        }
                    }
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "isMultipleDevices set wear_device_count:", Integer.valueOf(i));
                    SharedPreferenceManager.e(BaseApplication.getContext(), i);
                }
            });
        }
    }

    public static cjv c(DeviceInfo deviceInfo, List<cjv> list) {
        cjv cjvVar = null;
        if (deviceInfo == null || koq.b(list)) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "getMainRecommendedGroupInfo deviceInfo == null");
            return null;
        }
        for (cjv cjvVar2 : list) {
            if (cjvVar2 != null && cjvVar2.a() == 1 && (cjvVar2.i() instanceof cpm)) {
                cpm cpmVar = (cpm) cjvVar2.i();
                if (cpmVar.e() == 2 && TextUtils.equals(deviceInfo.getDeviceIdentify(), cpmVar.a())) {
                    cjvVar = cjvVar2;
                }
            }
        }
        return cjvVar;
    }

    public static void c(Context context, DeviceInfo deviceInfo) {
        if (context == null || deviceInfo == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "gotoAgreementDeclarationActivity deviceInfo == null");
            return;
        }
        Intent intent = new Intent();
        intent.setClass(context, AgreementDeclarationActivity.class);
        intent.putExtra("pairGuideSelectAddress", deviceInfo.getDeviceIdentify());
        Bundle bundle = new Bundle();
        bundle.putParcelable("deviceInfo", deviceInfo);
        intent.putExtras(bundle);
        intent.putExtra("device_country_code", deviceInfo.getCountryCode());
        intent.putExtra("device_emui_version", deviceInfo.getEmuiVersion());
        cZC_(context, intent, "AgreementDeclarationActivity");
    }

    public static void e(List<RecommendedItem> list) {
        if (list.size() > 0) {
            try {
                StorageParams storageParams = new StorageParams(0);
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10038), "SAVE_RECOMMENDED_LIST", new Gson().toJson(list), storageParams);
            } catch (JsonSyntaxException unused) {
                LogUtil.b("CardDeviceFragment CardDeviceUtil", "saveRecommendList JsonSyntaxException");
            }
        }
    }

    public static List<RecommendedItem> a() {
        ArrayList arrayList = new ArrayList(16);
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10038), "SAVE_RECOMMENDED_LIST");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", " mResetRecommendedLists json is null");
            return arrayList;
        }
        try {
            List<RecommendedItem> list = (List) new Gson().fromJson(b2, new TypeToken<List<RecommendedItem>>() { // from class: ogj.7
            }.getType());
            if (list != null && list.size() != 0) {
                return list;
            }
            LogUtil.h("CardDeviceFragment CardDeviceUtil", " getRecommendedList() mRecommendedLists is null or list is empty");
            return arrayList;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("CardDeviceFragment CardDeviceUtil", "getRecommendedList JsonSyntaxException");
            return arrayList;
        }
    }

    public static DeviceCapability c(String str) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "serviceCapabilityNegotiation(deviceIdentify)");
        if (cvs.e(str) == null) {
            DeviceInfo e2 = jpu.e(str, "CardDeviceFragment CardDeviceUtil");
            if (e2 != null && e2.getDeviceConnectState() == 2) {
                Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "CardDeviceFragment CardDeviceUtil");
                if (a2 != null && a2.get(str) != null) {
                    cvs.a(str, a2.get(str));
                    return a2.get(str);
                }
                LogUtil.h("CardDeviceFragment CardDeviceUtil", "serviceCapabilityNegotiation(deviceIdentify) capability is null");
                return null;
            }
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "serviceCapabilityNegotiation(deviceIdentify) device is not connected");
            return null;
        }
        return cvs.e(str);
    }

    public static void a(boolean z) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "enter automaticReconnectionDevices");
        if (iyl.d().g() != 3) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "automaticReconnectionDevices bluetooth off");
            return;
        }
        if (z) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "automaticReconnectionDevices exist device is connecting");
            return;
        }
        List<DeviceInfo> h = cpl.c().h();
        if (h == null || h.size() <= 0) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "automaticReconnectionDevices return");
            return;
        }
        DeviceInfo deviceInfo = null;
        boolean z2 = false;
        for (DeviceInfo deviceInfo2 : h) {
            if (deviceInfo2 != null) {
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "deviceInfo: ", CommonUtil.l(deviceInfo2.getDeviceIdentify()), " , connectState: ", Integer.valueOf(deviceInfo2.getDeviceConnectState()), " , activeState: ", Integer.valueOf(deviceInfo2.getDeviceActiveState()));
                if (deviceInfo2.getDeviceActiveState() == 1 && deviceInfo2.getAutoDetectSwitchStatus() != 1) {
                    LogUtil.a("CardDeviceFragment CardDeviceUtil", "update isHasActivityDevice: ", Boolean.valueOf(z2));
                    z2 = true;
                }
                boolean a2 = SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "basic_service_switch", false);
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "isSwitchedBasic: ", Boolean.valueOf(a2));
                deviceInfo = a(deviceInfo, deviceInfo2, a2);
            }
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "reconnect active brDisableDeviceInfo: ", deviceInfo, " , !isHasActivityDevice: ", Boolean.valueOf(!z2));
        if (deviceInfo == null || z2) {
            return;
        }
        deviceInfo.setDeviceActiveState(1);
        oxa.a().e(h, deviceInfo.getDeviceIdentify());
    }

    private static DeviceInfo a(DeviceInfo deviceInfo, DeviceInfo deviceInfo2, boolean z) {
        if (deviceInfo2.getDeviceConnectState() != 3 && deviceInfo2.getDeviceConnectState() != 4) {
            return deviceInfo;
        }
        if (!z) {
            return (deviceInfo2.getDeviceActiveState() == 1 || deviceInfo2.getSupportAccountSwitch() != 1) ? deviceInfo : e(deviceInfo, deviceInfo2);
        }
        if (deviceInfo2.getDeviceActiveState() == 1) {
            return deviceInfo;
        }
        DeviceInfo e2 = e(deviceInfo, deviceInfo2);
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "basic_service_switch", false);
        return e2;
    }

    private static DeviceInfo e(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        if (deviceInfo2 == null) {
            return deviceInfo;
        }
        if (deviceInfo == null) {
            return deviceInfo2;
        }
        if (deviceInfo2.getLastConnectedTime() > deviceInfo.getLastConnectedTime()) {
            deviceInfo = deviceInfo2;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "getLastAccountDevice deviceName is ", deviceInfo.getDeviceName());
        return deviceInfo;
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        oae.c(BaseApplication.getContext()).e(arrayList, true);
    }

    public static void d(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null || list == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "addPermission deviceInfo is null");
            return;
        }
        DeviceCapability c = c(deviceInfo.getDeviceIdentify());
        if (c == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "addPermission deviceCapability == null");
            return;
        }
        HashSet hashSet = new HashSet();
        hashSet.addAll(list);
        if (c.isOtaUpdate() || c.isWeatherPush()) {
            if (hashSet.add("android.permission.ACCESS_COARSE_LOCATION")) {
                list.add("android.permission.ACCESS_COARSE_LOCATION");
            }
            if (hashSet.add("android.permission.ACCESS_FINE_LOCATION")) {
                list.add("android.permission.ACCESS_FINE_LOCATION");
            }
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "addPermission permissions size ", Integer.valueOf(list.size()));
    }

    public static boolean b(long j, long j2) {
        long h = jec.h();
        return h >= j && h <= j2;
    }

    public static void d(Context context, int i, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "clickDeviceInboxInfo can not jump");
            return;
        }
        if (i == 1) {
            Intent intent = new Intent();
            intent.setClass(context, WebViewActivity.class);
            intent.putExtra("url", OperationUtils.newPathConcat(str, "from", "5"));
            cZC_(context, intent, "WebViewActivity");
            return;
        }
        if (i == 2) {
            Intent intent2 = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(OperationUtils.newPathConcat(str, "from", "5")));
            intent2.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            cZC_(context, intent2, CommonConstant.ACTION.HWID_SCHEME_URL);
            return;
        }
        LogUtil.h("CardDeviceFragment CardDeviceUtil", "clickDeviceInboxInfo error linkType");
    }

    public static Message cZz_(int i) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "createMessage errCode is ", Integer.valueOf(i));
        Message obtain = Message.obtain();
        obtain.what = 41;
        obtain.obj = Integer.valueOf(i);
        return obtain;
    }

    public static Message cZB_(int i, int i2) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "createTimeoutMessage deviceStatus = ", Integer.valueOf(i), " deviceType = ", Integer.valueOf(i2));
        Message obtain = Message.obtain();
        obtain.what = 31;
        obtain.arg1 = i;
        obtain.arg2 = i2;
        return obtain;
    }

    public static Message cZA_(boolean z, cpm cpmVar, int i) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "createTimeoutMessage isCloudDevice = ", Boolean.valueOf(z));
        Message obtain = Message.obtain();
        obtain.what = 31;
        obtain.arg1 = z ? 1 : 0;
        obtain.arg2 = i;
        obtain.obj = cpmVar;
        return obtain;
    }

    public static void cZy_(Context context, View view) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
        builder.a(context.getString(R.string.IDS_device_mgr_pair_note_can_not_connect)).cyp_(view).cyo_(R.string._2130841794_res_0x7f0210c2, new DialogInterface.OnClickListener() { // from class: ogs
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ogj.cZD_(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(true);
        c.show();
    }

    static /* synthetic */ void cZD_(DialogInterface dialogInterface, int i) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "showAlertDialog onclick PositiveButton");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static RecommendedItem c(int i, cpm cpmVar) {
        RecommendedItem recommendedItem = new RecommendedItem();
        if (cpmVar == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "createRecommendedItem deviceInfoForWear == null");
            return recommendedItem;
        }
        recommendedItem.setDeviceType(cpmVar.i());
        recommendedItem.setMac(knl.a(cpmVar.a()));
        recommendedItem.setId(i);
        return recommendedItem;
    }

    public static void cZF_(Context context, ContentValues contentValues, dcz dczVar) {
        if (context == null || contentValues == null || dczVar == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "openDeviceMainActivity context == null");
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setAction("SWITCH_PLUGINDEVICE");
        bundle.putString("arg1", "DeviceInfoList");
        bundle.putString("productId", dczVar.t());
        bundle.putString("uniqueId", contentValues.getAsString("uniqueId"));
        intent.setPackage(ofr.d);
        intent.setClassName(ofr.d, "com.huawei.health.device.ui.DeviceMainActivity");
        bundle.putParcelable("commonDeviceInfo", contentValues);
        intent.putExtras(bundle);
        cZC_(context, intent, "com.huawei.health.device.ui.DeviceMainActivity");
    }

    public static void cZC_(Context context, Intent intent, String str) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("CardDeviceFragment CardDeviceUtil", "handleActivityNotFoundException ActivityNotFoundException ", str);
        }
    }

    public static void j() {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "start product map background download");
        if (jbw.c(BaseApplication.getContext())) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ogm
                @Override // java.lang.Runnable
                public final void run() {
                    jbw.d(BaseApplication.getContext(), 0);
                }
            });
        }
    }

    public static void e(boolean z) {
        e = z;
    }

    public static void d(boolean z) {
        f15651a = z;
        nun.d(z);
    }

    public static boolean i() {
        return f15651a;
    }

    public static void a(List<cjv> list, List<cjv> list2, CardDeviceFragment cardDeviceFragment) {
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "enter setList");
        ArrayList<cpm> a2 = jfv.a();
        int i = 2;
        boolean z = true;
        int i2 = a2.size() > 1 ? 2 : 1;
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "enter setList displayType：", Integer.valueOf(i2));
        int e2 = e(a2);
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(16);
        Iterator<cpm> it = a2.iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            cjv cjvVar = new cjv();
            cjvVar.a(z ? 1 : 0);
            cjvVar.b(next.l());
            cjvVar.e(i2);
            cjvVar.c(next);
            if (next.b() == z && next.e() == i) {
                d(e2, currentTimeMillis, next, cjvVar);
                if (obb.e(next.i())) {
                    e = z;
                    arrayList.add(cjvVar);
                } else {
                    a(list2, cjvVar);
                }
            } else {
                cjvVar.a(next.h());
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "deviceInfoForWear name: ", next.d(), "deviceInfoForWear last connected time:", Long.valueOf(next.h()));
                if (obb.e(next.i())) {
                    arrayList.add(cjvVar);
                } else {
                    list.add(cjvVar);
                }
            }
            i = 2;
            z = true;
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "has wear deviceInfoForWears : ", a2.toArray());
        e(list, list2, arrayList, i2, cardDeviceFragment);
    }

    private static void d(int i, long j, cpm cpmVar, cjv cjvVar) {
        if (i > 1) {
            cjvVar.a(b.get(cpmVar.a()).intValue() + j);
        } else {
            cjvVar.a(j);
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "deviceInfoForWear name: ", cpmVar.d(), "deviceInfoForWear current connected time:", Long.valueOf(j), ",compareTime:", Long.valueOf(cjvVar.d()));
    }

    private static void e(List<cjv> list, List<cjv> list2, List<cjv> list3, int i, CardDeviceFragment cardDeviceFragment) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (cjv cjvVar : list3) {
            if (cjvVar.i() instanceof cpm) {
                String d = ((cpm) cjvVar.i()).d();
                String substring = d.contains(com.huawei.openalliance.ad.constant.Constants.LINK) ? d.substring(0, d.lastIndexOf(com.huawei.openalliance.ad.constant.Constants.LINK)) : "";
                if (linkedHashMap.containsKey(substring)) {
                    ((List) linkedHashMap.get(substring)).add(cjvVar);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(cjvVar);
                    linkedHashMap.put(substring, arrayList);
                }
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            c(list, list2, i, (Map.Entry<String, List<cjv>>) it.next());
        }
        if (e && f15651a) {
            jgi.e().b(new oeg(cardDeviceFragment), "CardDeviceFragment CardDeviceUtil");
        }
    }

    private static void c(List<cjv> list, List<cjv> list2, int i, Map.Entry<String, List<cjv>> entry) {
        cjv cjvVar = new cjv();
        List<cjv> value = entry.getValue();
        if (koq.c(value)) {
            Collections.sort(value);
            cjv cjvVar2 = value.get(0);
            if (value.size() == 1) {
                cjvVar = cjvVar2;
            } else {
                e = false;
                cjvVar.a(4);
                cjvVar.e(i);
                cjvVar.a(cjvVar2.d());
                cjvVar.c(entry.getKey());
                cjvVar.e(value);
            }
            if (cjvVar2.i() instanceof cpm) {
                if (((cpm) cjvVar2.i()).e() == 2) {
                    a(list2, cjvVar);
                } else {
                    list.add(cjvVar);
                }
            }
        }
    }

    private static int e(ArrayList<cpm> arrayList) {
        ArrayList arrayList2 = new ArrayList(16);
        Iterator<cpm> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            cpm next = it.next();
            if (next.b() == 1 && next.e() == 2) {
                i++;
                cjv cjvVar = new cjv();
                cjvVar.c(next);
                cjvVar.a(next.h());
                arrayList2.add(cjvVar);
            }
        }
        if (i > 1) {
            Collections.sort(arrayList2);
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object i3 = ((cjv) arrayList2.get(i2)).i();
                if (i3 instanceof cpm) {
                    cpm cpmVar = (cpm) i3;
                    LogUtil.c("CardDeviceFragment CardDeviceUtil", "getConnectedDevices connectedDevice name:", cpmVar.d());
                    b.put(cpmVar.a(), Integer.valueOf((size - i2) * 30));
                }
            }
        }
        LogUtil.a("CardDeviceFragment CardDeviceUtil", "getConnectedDevices connectedCount:", Integer.valueOf(i));
        return i;
    }

    public static boolean d(String str) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "CardDeviceFragment CardDeviceUtil");
        if (deviceList == null) {
            LogUtil.h("CardDeviceFragment CardDeviceUtil", "isContinueConnecting devices == null");
            return true;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (TextUtils.equals(deviceInfo.getDeviceIdentify(), str) && deviceInfo.getDeviceConnectState() == 2) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<cjv> c() {
        ArrayList<ContentValues> f = ceo.d().f();
        if (koq.b(f)) {
            LogUtil.a("CardDeviceFragment CardDeviceUtil", "no bonded devices");
            return new ArrayList<>(0);
        }
        ArrayList<cjv> arrayList = new ArrayList<>(f.size());
        Iterator<ContentValues> it = f.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("productId");
            String asString2 = next.getAsString("uniqueId");
            if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
                LogUtil.h("CardDeviceFragment CardDeviceUtil", "getDeviceList : productId or deviceIdentify is empty");
            } else {
                dcz d = ResourceManager.e().d(asString);
                LogUtil.a("CardDeviceFragment CardDeviceUtil", "getDeviceList: productId: ", asString, ", uniqueId: ", CommonUtil.l(asString2));
                if (d == null) {
                    LogUtil.h("CardDeviceFragment CardDeviceUtil", "getDeviceList : productInfo is null");
                } else {
                    dcz.b n = d.n();
                    if (n == null) {
                        LogUtil.h("CardDeviceFragment CardDeviceUtil", "getDeviceList : Manifest is null");
                    } else {
                        String d2 = n.d();
                        if (TextUtils.isEmpty(d2) || d2.trim().isEmpty()) {
                            LogUtil.h("CardDeviceFragment CardDeviceUtil", "getDeviceList : icon is empty");
                        } else if (next.getAsInteger("showInList").intValue() != 0) {
                            cjv b2 = dbp.b(d);
                            b2.FU_(next);
                            b2.a(new cke("deviceUsedTime").b(cpp.a(), next.getAsString("uniqueId")));
                            arrayList.add(b2);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static void o() {
        ReleaseLogUtil.e("CardDeviceFragment CardDeviceUtil", "openMyWatchFace");
        WatchFaceApi watchFaceApi = (WatchFaceApi) Services.a("WatchFaceApiManager", WatchFaceApi.class);
        if (watchFaceApi != null) {
            watchFaceApi.openMyWatchFace(new IBaseResponseCallback() { // from class: ogn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ogj.c(i, obj);
                }
            });
        }
    }

    static /* synthetic */ void c(int i, Object obj) {
        if (i == 0 && (obj instanceof H5ProLaunchOption.Builder)) {
            H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.watchface", ((H5ProLaunchOption.Builder) obj).build());
            jqh.c("e1");
        }
    }
}
