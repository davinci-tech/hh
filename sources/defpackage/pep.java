package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPay;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.notification.NotificationSettingActivity;
import com.huawei.ui.device.activity.update.UpdateVersionActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.watchface.WatchFaceApi;
import com.huawei.watchface.WatchFaceType;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import com.huawei.wear.oversea.overseamanger.OverSeaQueryCallBack;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class pep {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f16099a = {"android.permission.RECEIVE_SENSITIVE_NOTIFICATIONS"};
    private static Handler b = new Handler(Looper.getMainLooper()) { // from class: pep.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.h("WearHomeUtils", "handleMessage message is:", Integer.valueOf(message.what));
            if (message.what == 0) {
                bzu.b().initHealthPayAdapter(BaseApplication.getContext());
                Intent intent = new Intent();
                if (!LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1014).equals("CN")) {
                    intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginPay_A_58);
                    LogUtil.a("WearHomeUtils", "go to WearWalletOverSeaMainActivity");
                } else {
                    intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginPay_A_47);
                    LogUtil.a("WearHomeUtils", "go to WearWalletMainActivity");
                }
                intent.setFlags(268435456);
                bzu.b().launchActivity(pep.c, intent);
                return;
            }
            if (message.what == 7 || message.what == 6) {
                pep.dmX_(message);
            } else {
                pep.b(message.what);
            }
        }
    };
    private static Context c;
    private static CommonDialog21 d;
    private static NoTitleCustomAlertDialog e;
    private static String g;

    public static boolean d(int i) {
        return i == 78 || i == 57;
    }

    public static void dmT_(Context context, BroadcastReceiver broadcastReceiver, String... strArr) {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : strArr) {
            intentFilter.addAction(str);
        }
        if (context != null) {
            try {
                BroadcastManagerUtil.bFC_(context, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("MainUI", 0, "WearHomeUtils", "IllegalArgumentException");
            }
        }
    }

    public static void dmU_(Context context, BroadcastReceiver broadcastReceiver, int i, String str) {
        if (context != null) {
            try {
                IntentFilter intentFilter = new IntentFilter(str);
                intentFilter.setPriority(i);
                BroadcastManagerUtil.bFC_(context, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("MainUI", 0, "WearHomeUtils", "registerPriorityBroadcast IllegalArgumentException");
            }
        }
    }

    public static void dmY_(Context context, BroadcastReceiver broadcastReceiver) {
        if (context != null) {
            try {
                context.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e2) {
                LogUtil.b("MainUI", 0, "WearHomeUtils", e2.getMessage());
            }
        }
    }

    public static void dmS_(BroadcastReceiver broadcastReceiver, String... strArr) {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : strArr) {
            intentFilter.addAction(str);
        }
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public static void dmZ_(BroadcastReceiver broadcastReceiver) {
        try {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("MainUI", 0, "WearHomeUtils", e2.getMessage());
        }
    }

    public static void b(Context context) {
        PluginPay.getInstance(context).setAdapter(ixb.p());
        ReleaseLogUtil.e("R_WearHomeUtils", "initPluginPayAdapter");
    }

    public static boolean a(String str) {
        return e(str, 2);
    }

    public static boolean e(String str, int i) {
        return System.currentTimeMillis() - CommonUtil.g(KeyValDbManager.b(BaseApplication.getContext()).e(str)) > ((long) i) * 86400000;
    }

    public static void b(String str) {
        KeyValDbManager.b(BaseApplication.getContext()).e(str, String.valueOf(System.currentTimeMillis()));
    }

    public static void g(final Context context) {
        LogUtil.a("WearHomeUtils", "initWatchfaceAdapter");
        final String arrays = Arrays.toString(Thread.currentThread().getStackTrace());
        ThreadPoolManager.d().d("WearHomeUtils", new Runnable() { // from class: pep.2
            @Override // java.lang.Runnable
            public void run() {
                jls.e(context);
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("watchFaceInitAar", "stackTraceMessage :" + arrays);
            }
        });
    }

    public static void e(final Context context, final String str) {
        LogUtil.a("WearHomeUtils", "initWatchfaceAdapter");
        final String arrays = Arrays.toString(Thread.currentThread().getStackTrace());
        ThreadPoolManager.d().d("WearHomeUtils", new Runnable() { // from class: pep.1
            @Override // java.lang.Runnable
            public void run() {
                jls.e(context);
                jls.c(context, str);
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("watchFaceInitAar", "stackTraceMessageH5 :" + arrays);
            }
        });
    }

    public static void c(Context context, String str) {
        if (context == null) {
            ReleaseLogUtil.d("R_WearHomeUtils", "goToCardHolderTransitActivity context is null");
            return;
        }
        caa.a();
        if (!ocp.b(str)) {
            ReleaseLogUtil.e("R_WearHomeUtils", "goToCardHolderTransitActivity not isSupportQuery");
            bzu.b().initHealthPayAdapter(context);
            Intent intent = new Intent();
            if (j()) {
                intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginPay_A_47);
                ReleaseLogUtil.e("R_WearHomeUtils", "go to WearWalletMainActivity");
            } else {
                intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginPay_A_31);
                ReleaseLogUtil.e("R_WearHomeUtils", "go to CardHolderActivity");
            }
            bzu.b().launchActivity(context, intent);
            ReleaseLogUtil.e("R_WearHomeUtils", "launchActivity WearWalletMainActivity");
            return;
        }
        j(context, str);
    }

    public static void e(Context context) {
        SharedPreferenceManager.e(String.valueOf(10008), "click_satcom_card", true);
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginPay_A_69);
        bzu.b().launchActivity(context, intent);
        ReleaseLogUtil.e("R_WearHomeUtils", "gotoSatcomCardActivity");
    }

    private static void j(final Context context, final String str) {
        c = context;
        h(context);
        OverSeaMangerUtil.c(context).a(ocp.a(cvs.a()), new OverSeaQueryCallBack() { // from class: pep.3
            @Override // com.huawei.wear.oversea.overseamanger.OverSeaQueryCallBack
            public int onSuccess(int i) {
                ReleaseLogUtil.e("R_WearHomeUtils", "OverSeaQueryCallBack onSuccess");
                pep.a(context);
                pep.d(0, str);
                return i;
            }

            @Override // com.huawei.wear.oversea.overseamanger.OverSeaQueryCallBack
            public int onFail(int i) {
                ReleaseLogUtil.e("R_WearHomeUtils", "OverSeaQueryCallBack fail returnCode is:", Integer.valueOf(i));
                pep.a(context);
                if (i != 5 || !Utils.l()) {
                    pep.d(i, str);
                } else {
                    pep.d(2, str);
                }
                return i;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(int i, String str) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = str;
        b.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dmX_(final Message message) {
        int i;
        if (message.what == 7) {
            i = R$string.IDS_network_connect_error;
        } else {
            i = R$string.IDS_hwh_home_healthshop_unable_connect_server_tips;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(c);
        builder.e(i).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: pep.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pep.e.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R$string.IDS_network_set, new View.OnClickListener() { // from class: pep.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                if (message.what == 7) {
                    try {
                        pep.c.startActivity(new Intent("android.settings.SETTINGS"));
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("WearHomeUtils", "ActivityNotFoundException BindAlipayProgressActivity");
                    }
                } else {
                    LogUtil.h("WearHomeUtils", "network error retry");
                    if (message.obj != null && !(message.obj instanceof String)) {
                        LogUtil.a("WearHomeUtils", "showRetryDialog object instance error");
                    } else if (message.obj instanceof String) {
                        str = (String) message.obj;
                        pep.c(pep.c, str);
                    }
                    str = "";
                    pep.c(pep.c, str);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e = e2;
        e2.setCancelable(false);
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(c);
        if (i == 3 || i == 1) {
            builder.e(R.string.wallet_unenable_device);
        } else if (i == 2) {
            builder.e(R.string._2130844514_res_0x7f021b62);
        } else if (i == 4) {
            builder.e(R.string._2130844513_res_0x7f021b61);
        } else {
            builder.e(R.string._2130845018_res_0x7f021d5a);
            LogUtil.h("WearHomeUtils", "code is:", Integer.valueOf(i));
        }
        builder.czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: pep.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pep.e.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e = e2;
        e2.setCancelable(false);
        e.show();
    }

    private static void h(Context context) {
        if (d != null) {
            a(c);
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            new CommonDialog21(context, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(context);
            d = a2;
            a2.e(context.getString(com.huawei.ui.main.R$string.wallet_load_service));
            d.setCancelable(false);
            d.a();
            d.show();
            ReleaseLogUtil.e("R_WearHomeUtils", "showLoadingDialog...mLoadingDialog.show()");
        }
    }

    public static void a(Context context) {
        CommonDialog21 commonDialog21;
        if (!(context instanceof Activity) || ((Activity) context).isFinishing() || (commonDialog21 = d) == null) {
            return;
        }
        commonDialog21.cancel();
        d = null;
        LogUtil.a("WearHomeUtils", "destroy mLoadingDialog");
    }

    private static boolean j() {
        ReleaseLogUtil.e("R_WearHomeUtils", "isOpenCardNewWay");
        if (ixb.p() != null) {
            return ixb.p().isWalletOpenCard();
        }
        return false;
    }

    public static String e(Context context, String str, String str2, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return b(context, str, z);
        }
        if (!str2.contains(Constants.LINK)) {
            return str2;
        }
        if (str2.contains("HUAWEI CM-R1P") || str2.contains("HUAWEI AM-R1")) {
            return str2.replace(Constants.LINK, " -");
        }
        if (!z || str2.length() <= 4) {
            return z2 ? str2.replace(Constants.LINK, " -") : str2;
        }
        return str2.substring(0, str2.length() - 4);
    }

    public static String b(Context context, String str) {
        return b(context, str, false);
    }

    public static String b(Context context, String str, boolean z) {
        String str2;
        if (context == null) {
            LogUtil.h("WearHomeUtils", "getTitle, context is null");
            return "";
        }
        context.getResources().getString(R.string._2130841377_res_0x7f020f21);
        DeviceInfo b2 = oxa.a().b(str);
        if (b2 != null) {
            if (TextUtils.isEmpty(b2.getDeviceName())) {
                str2 = oae.c(context).b(b2.getProductType());
                ReleaseLogUtil.e("WearHomeUtils", "getTitle transDeviceProductTypeIntToStr titleName:", str2);
            } else {
                str2 = b2.getDeviceName();
                ReleaseLogUtil.e("R_WearHomeUtils", "getTitle getDeviceName titleName:", str2);
                g = str2;
            }
        } else {
            str2 = g;
            ReleaseLogUtil.e("R_WearHomeUtils", "getTitle mTitleNameValue titleName:", str2);
        }
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        if (str2.contains("HUAWEI CM-R1P")) {
            String substring = str2.substring(0, 13);
            ReleaseLogUtil.e("R_WearHomeUtils", "getTitle  CMR1P  titleName:", substring);
            return substring;
        }
        if (str2.contains("HUAWEI AM-R1")) {
            String substring2 = str2.substring(0, 12);
            ReleaseLogUtil.e("R_WearHomeUtils", "getTitle  AMR1  titleName:", substring2);
            return substring2;
        }
        String c2 = c(z, str2);
        ReleaseLogUtil.e("R_WearHomeUtils", "getTitle  getOnlyOneDeviceName  titleName:", c2);
        return c2;
    }

    private static String c(boolean z, String str) {
        return (z && str.contains(Constants.LINK)) ? str.substring(0, str.length() - 4) : str;
    }

    public static void dmW_(Activity activity, int i) {
        int i2;
        if (activity == null) {
            return;
        }
        if (activity.getApplicationContext().getTheme() == null) {
            LogUtil.h("WearHomeUtils", "setActivityTheme theme is null");
            return;
        }
        LogUtil.a("WearHomeUtils", "loadApplicationTheme() themeType:", Integer.valueOf(i));
        if (!"com.huawei.health".equals(BaseApplication.getAppPackage())) {
            i2 = 0;
        } else if (i == 2) {
            i2 = activity.getResources().getIdentifier("HealthTransparentTheme", TemplateStyleRecord.STYLE, "com.huawei.health");
        } else {
            i2 = activity.getResources().getIdentifier("HealthTheme", TemplateStyleRecord.STYLE, "com.huawei.health");
        }
        if (i2 == 0) {
            LogUtil.a("WearHomeUtils", "themeResources is DEFAULT_THEME_ID");
        } else {
            LogUtil.a("WearHomeUtils", "themeResources is ", Integer.valueOf(i2));
            activity.setTheme(i2);
        }
    }

    public static void d(Context context) {
        if (context instanceof Activity) {
            if (LanguageUtil.bc(context)) {
                ((Activity) context).overridePendingTransition(R.anim._2130772078_res_0x7f01006e, R.anim._2130772080_res_0x7f010070);
            } else {
                ((Activity) context).overridePendingTransition(R.anim._2130772058_res_0x7f01005a, R.anim._2130772062_res_0x7f01005e);
            }
        }
    }

    public static void c(Context context) {
        if (context instanceof Activity) {
            if (LanguageUtil.bc(context)) {
                ((Activity) context).overridePendingTransition(0, R.anim._2130772062_res_0x7f01005e);
            } else {
                ((Activity) context).overridePendingTransition(0, R.anim._2130772080_res_0x7f010070);
            }
        }
    }

    public static void d(final Context context, String str) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(context).e(str).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: pep.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WearHomeUtils", "user choose open gps");
                Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                Context context2 = context;
                if (context2 instanceof Activity) {
                    ((Activity) context2).startActivityForResult(intent, 0);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: pep.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WearHomeUtils", "user choose cancel");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public static void dmR_(Context context, String str, Intent intent) {
        if (TextUtils.isEmpty(str) || intent == null) {
            LogUtil.h("WearHomeUtils", "jumpSpecifiedActivity params is null");
            return;
        }
        int intExtra = intent.getIntExtra("key_page_id", -1);
        if (intExtra == -1) {
            LogUtil.h("WearHomeUtils", "jumpSpecifiedActivity pageId is -1");
            return;
        }
        LogUtil.h("WearHomeUtils", "jumpSpecifiedActivity jump");
        try {
            Intent intent2 = new Intent();
            if (intExtra == 0) {
                intent2.setClassName(context, NotificationSettingActivity.class.getCanonicalName());
            } else {
                intent2.setClassName(context, UpdateVersionActivity.class.getCanonicalName());
            }
            intent2.setPackage(BaseApplication.getAppPackage());
            intent2.putExtra("device_id", str);
            context.startActivity(intent2);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeUtils", "jumpSpecifiedActivity ActivityNotFoundException");
        }
    }

    public static boolean c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "isSupportEditSms deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 82);
    }

    public static boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "isSupportSmsReject deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 16);
    }

    public static boolean f(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "isSupportSmsReject deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 73);
    }

    public static void a(Context context, int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("dialogType", 1);
        String value = AnalyticsValue.ECG_DIALOG_SHOW_COUNT.value();
        ixx.d().d(context, value, hashMap, 0);
        LogUtil.a("WearHomeUtils", "BI ecg dialog, value: ", value, ", typeMap: ", hashMap.toString());
    }

    public static boolean e(DeviceInboxInfo deviceInboxInfo) {
        if (deviceInboxInfo == null) {
            LogUtil.h("WearHomeUtils", "isEffectiveExclusiveGuardian deviceInboxInfo is null");
            return false;
        }
        if (deviceInboxInfo.getBenefitType() == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            long effectiveStartTime = deviceInboxInfo.getEffectiveStartTime();
            long effectiveEndTime = deviceInboxInfo.getEffectiveEndTime();
            LogUtil.a("WearHomeUtils", "isEffectiveExclusiveGuardian currentTime = ", Long.valueOf(currentTimeMillis), ",effectiveStartTime = ", Long.valueOf(effectiveStartTime), ",effectiveEndTime = ", Long.valueOf(effectiveEndTime));
            if (currentTimeMillis >= effectiveStartTime && currentTimeMillis <= effectiveEndTime) {
                return true;
            }
        }
        return false;
    }

    public static String c(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("WearHomeUtils", "getExclusiveGuardianData context is null");
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WearHomeUtils", "getExclusiveGuardianData deviceIdentify is empty");
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("WearHomeUtils", "getExclusiveGuardianData keywords is empty");
            return "";
        }
        String b2 = b(str, str2);
        if (TextUtils.isEmpty(b2)) {
            LogUtil.h("WearHomeUtils", "getExclusiveGuardianData key is empty");
            return "";
        }
        LogUtil.a("WearHomeUtils", "getExclusiveGuardianData key = ", b2);
        return SharedPreferenceManager.b(context, String.valueOf(10008), b2);
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (context == null) {
            LogUtil.h("WearHomeUtils", "saveExclusiveGuardianData context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WearHomeUtils", "saveExclusiveGuardianData deviceIdentify is empty");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("WearHomeUtils", "saveExclusiveGuardianData keywords is empty");
            return;
        }
        String b2 = b(str, str2);
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        LogUtil.a("WearHomeUtils", "saveExclusiveGuardianData key = ", b2);
        SharedPreferenceManager.e(context, String.valueOf(10008), b2, str3, (StorageParams) null);
    }

    private static String b(String str, String str2) {
        String accountInfo = LoginInit.getInstance(c).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("WearHomeUtils", "getExclusiveGuardianStatusKey userId is empty");
            return "";
        }
        return accountInfo + "_" + str2 + "_" + knl.d(str);
    }

    public static void b(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addOutgoingCallPermission deviceInfo is null");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            LogUtil.h("WearHomeUtils", "addOutgoingCallPermission is family_pair_mode");
            return;
        }
        DeviceCapability e2 = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(deviceInfo.getDeviceIdentify());
        if (e2 != null && e2.isMessageAlert() && oae.c(c).g()) {
            list.add("android.permission.PROCESS_OUTGOING_CALLS");
        }
    }

    public static void f(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addSmsPermission deviceInfo is null");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            LogUtil.h("WearHomeUtils", "addSmsPermission is family_pair_mode");
            return;
        }
        if (e(deviceInfo) || f(deviceInfo)) {
            list.add("android.permission.SEND_SMS");
            list.add("android.permission.READ_SMS");
            LogUtil.h("WearHomeUtils", "addSmsPermission add sms all permission");
        } else {
            DeviceCapability e2 = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(deviceInfo.getDeviceIdentify());
            if (e2 == null || !e2.isSupportSendSosSms()) {
                return;
            }
            list.add("android.permission.SEND_SMS");
        }
    }

    public static void d(DeviceCapability deviceCapability, List<String> list) {
        if (deviceCapability == null || !deviceCapability.isWeatherPush()) {
            LogUtil.h("WearHomeUtils", "addForegroundLocationPermission not support WeatherPush");
        } else {
            list.add("android.permission.ACCESS_COARSE_LOCATION");
            list.add("android.permission.ACCESS_FINE_LOCATION");
        }
    }

    public static boolean dmV_(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        if (activity == null || strArr == null || strArr.length == 0) {
            LogUtil.h("WearHomeUtils", "checkPermissions activity == null");
            return false;
        }
        if (!a("wear_local_permission_time")) {
            LogUtil.h("WearHomeUtils", "request local permission < 48h");
            return false;
        }
        if (jdi.c(BaseApplication.getContext(), strArr)) {
            LogUtil.h("WearHomeUtils", "has local permission");
            return false;
        }
        jdi.bFL_(activity, strArr, permissionsResultAction);
        KeyValDbManager.b(BaseApplication.getContext()).e("wear_local_permission_time", String.valueOf(System.currentTimeMillis()));
        return true;
    }

    public static void d(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addAnswerPhoneCallsPermission deviceInfo is null");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            LogUtil.h("WearHomeUtils", "addAnswerPhoneCallsPermission is family_pair_mode");
            return;
        }
        if (!f()) {
            LogUtil.h("WearHomeUtils", "addAnswerPhoneCallsPermission not support call phone");
            return;
        }
        DeviceCapability e2 = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(deviceInfo.getDeviceIdentify());
        if (e2 == null) {
            LogUtil.h("WearHomeUtils", "addAnswerPhoneCallsPermission deviceCapability is null");
        } else if (e2.isSupportCallingOperationType() || deviceInfo.getProductType() == 57) {
            list.add("android.permission.ANSWER_PHONE_CALLS");
        }
    }

    public static void c(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addReadCallLogPermission deviceInfo is null");
            return;
        }
        DeviceCapability e2 = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(deviceInfo.getDeviceIdentify());
        if (e2 == null || !e2.isMessageAlert()) {
            LogUtil.h("WearHomeUtils", "addReadCallLogPermission not support MessageAlert");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            LogUtil.h("WearHomeUtils", "addReadCallLogPermission is family_pair_mode");
        } else if (!f()) {
            LogUtil.h("WearHomeUtils", "addReadCallLogPermission not support call phone");
        } else {
            list.add("android.permission.READ_CALL_LOG");
        }
    }

    public static void a(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addCallPhonePermission deviceInfo is null");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            LogUtil.h("WearHomeUtils", "addCallPhonePermission is family_pair_mode");
            return;
        }
        if (!f()) {
            LogUtil.h("WearHomeUtils", "addCallPhonePermission not support call phone");
            return;
        }
        DeviceCapability e2 = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(deviceInfo.getDeviceIdentify());
        if (e2 == null) {
            LogUtil.h("WearHomeUtils", "addCallPhonePermission deviceCapability is null");
        } else if (e2.isSupportCallingOperationType() || deviceInfo.getProductType() == 57) {
            list.add("android.permission.CALL_PHONE");
        }
    }

    public static void i(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addReadContactsPermission deviceInfo is null");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            LogUtil.h("WearHomeUtils", "addReadContactsPermission is family_pair_mode");
            return;
        }
        DeviceCapability e2 = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(deviceInfo.getDeviceIdentify());
        if (e2 == null) {
            LogUtil.h("WearHomeUtils", "addReadContactsPermission deviceCapability is null");
        } else if (e2.isMessageAlert() || e2.isContacts() || e2.isSupportSosTransmission()) {
            list.add("android.permission.READ_CONTACTS");
        }
    }

    public static void h(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addReadPhoneStatePermission deviceInfo is null");
            return;
        }
        DeviceCapability e2 = DeviceSettingsInteractors.d(BaseApplication.getContext()).e(deviceInfo.getDeviceIdentify());
        if (e2 == null) {
            LogUtil.h("WearHomeUtils", "addReadPhoneStatePermission deviceCapability is null");
        } else if (e2.isMessageAlert() || e2.isSupportCallingOperationType() || e2.isSupportEsim()) {
            list.add("android.permission.READ_PHONE_STATE");
        }
    }

    public static void e(DeviceInfo deviceInfo, List<String> list) {
        if (deviceInfo == null) {
            LogUtil.h("WearHomeUtils", "addReadAndWriteCalendarPermission deviceInfo is null");
            return;
        }
        boolean c2 = cwi.c(deviceInfo, 171);
        boolean c3 = cwi.c(deviceInfo, 184);
        if (!c2 && !c3) {
            LogUtil.a("WearHomeUtils", "Not SupportOtherPhoneCalendarSync");
        } else {
            list.add("android.permission.READ_CALENDAR");
            list.add("android.permission.WRITE_CALENDAR");
        }
    }

    public static void a(int i, String str, boolean z) {
        SharedPreferenceManager.e(Integer.toString(10008), "watchface_" + i + iyl.d().e(str), z);
    }

    public static boolean a(int i, String str) {
        return SharedPreferenceManager.a(Integer.toString(10008), "watchface_" + i + iyl.d().e(str), false);
    }

    public static String c() {
        if (b() != null) {
            return WatchFaceUtil.isSupportMyWatch() ? "1" : "0";
        }
        ReleaseLogUtil.d("R_WearHomeUtils", "isCurrentSupportMyWatchFace currentDevice is null.");
        return "2";
    }

    public static DeviceInfo b() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "WearHomeUtils");
    }

    public static void a(Context context, String str) {
        if (!str.contains("photo-dial-new")) {
            e(context, str);
            return;
        }
        String c2 = c();
        ReleaseLogUtil.e("R_WearHomeUtils", "startWatchFaceMarket onResponse supportMyWatchFace：", c2);
        if ("1".equals(c2)) {
            a(WatchFaceType.ALBUM.value());
        } else {
            e(context, str);
        }
    }

    public static void g() {
        String c2 = c();
        ReleaseLogUtil.e("R_WearHomeUtils", "startMyWatchFace onResponse supportMyWatchFace：", c2);
        if ("1".equals(c2)) {
            a(WatchFaceType.ALBUM.value());
        } else if ("0".equals(c2) || "2".equals(c2)) {
            nrh.b(BaseApplication.getContext(), R.string.IDS_device_not_support_my_watch_face_deeplink);
        } else {
            LogUtil.a("WearHomeUtils", "startMyWatchFace fail");
        }
    }

    private static void a(int i) {
        WatchFaceApi watchFaceApi = (WatchFaceApi) Services.a("WatchFaceApiManager", WatchFaceApi.class);
        if (watchFaceApi == null) {
            ReleaseLogUtil.e("R_WearHomeUtils", "startMyWatchFaceH5 watchFaceApi is null");
        } else {
            watchFaceApi.openWatchFaceDetail(i, new IBaseResponseCallback() { // from class: pep.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 == 0 && (obj instanceof H5ProLaunchOption.Builder)) {
                        H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.watchface", ((H5ProLaunchOption.Builder) obj).build());
                        jqh.c("e3");
                    }
                }
            });
        }
    }

    public static void c(int i, String str) {
        SharedPreferenceManager.d(Integer.toString(10008), "watchface_" + i + iyl.d().e(str));
    }

    public static boolean f() {
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null) {
            LogUtil.h("WearHomeUtils", "isSupportCallPhone packageManager is null");
            return true;
        }
        boolean hasSystemFeature = packageManager.hasSystemFeature("android.hardware.telephony");
        LogUtil.a("WearHomeUtils", "isSupportCallPhone isSupportCallPhone:", Boolean.valueOf(hasSystemFeature));
        return hasSystemFeature;
    }

    public static String d(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceIdType() == 1) {
            return cvx.e(deviceInfo.getUuid());
        }
        if (deviceInfo.getDeviceIdType() == 10) {
            return deviceInfo.getUuid();
        }
        return cvx.e(deviceInfo.getUuid());
    }

    public static boolean d() {
        return Build.BRAND.toLowerCase(Locale.ENGLISH).contains("nubia");
    }

    public static void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("WearHomeUtils", "device info is enpty");
            return;
        }
        Boolean valueOf = Boolean.valueOf(obb.c());
        if (!valueOf.booleanValue()) {
            LogUtil.a("WearHomeUtils", "downloadPluginBackground needDownload is ", valueOf);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("PluginHiAiEngine");
        AppBundle.e().preDownloadPlugins(BaseApplication.getContext(), arrayList, true, true);
        h(deviceInfo);
    }

    private static void h(DeviceInfo deviceInfo) {
        long parseLong;
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("downloadHiAiPlugin");
        if (TextUtils.isEmpty(e2) || !"voiceAssistant".equals(e2)) {
            LogUtil.a("WearHomeUtils", "showDownloadToast showFlag is ", e2);
            return;
        }
        String str = "keyToastShowTime" + dis.b(deviceInfo.getDeviceIdentify());
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), str);
        if (!TextUtils.isEmpty(b2)) {
            try {
                parseLong = Long.parseLong(b2);
            } catch (NumberFormatException e3) {
                LogUtil.b("WearHomeUtils", "showDownloadToast NumberFormatException:", ExceptionUtils.d(e3));
            }
            long currentTimeMillis = (System.currentTimeMillis() - parseLong) / 1000;
            LogUtil.a("WearHomeUtils", "showDownloadToast spanTime is ", Long.valueOf(currentTimeMillis));
            if (!TextUtils.isEmpty(e2) || currentTimeMillis <= k.b.m) {
            }
            nrh.e(BaseApplication.getContext(), R.string._2130845680_res_0x7f021ff0);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), str, String.valueOf(System.currentTimeMillis()), (StorageParams) null);
            return;
        }
        parseLong = 0;
        long currentTimeMillis2 = (System.currentTimeMillis() - parseLong) / 1000;
        LogUtil.a("WearHomeUtils", "showDownloadToast spanTime is ", Long.valueOf(currentTimeMillis2));
        if (TextUtils.isEmpty(e2)) {
        }
    }

    public static boolean i() {
        boolean z = Utils.o() && !OperationUtils.isOperation(LoginInit.getInstance(c).getAccountInfo(1010));
        LogUtil.a("WearHomeUtils", "isSupportDetectionVersion isUnSupportOperation:", Boolean.valueOf(z), "  SDK_INT = ", Integer.valueOf(Build.VERSION.SDK_INT));
        return !z && Build.VERSION.SDK_INT >= 28;
    }

    public static boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null || Utils.o()) {
            return false;
        }
        boolean c2 = cwi.c(deviceInfo, 176);
        ReleaseLogUtil.e("R_WearHomeUtils", "isSupportNotificationLive isSupportOperation:", Boolean.valueOf(c2));
        if (!c2) {
            c2 = cwi.c(deviceInfo, 219);
            ReleaseLogUtil.e("R_WearHomeUtils", "isSupportNotificationLiveLevel isSupportOperation:", Boolean.valueOf(c2));
        }
        return c2 && EnvironmentInfo.j();
    }

    public static boolean d(Context context, int i) {
        LogUtil.a("WearHomeUtils", "isShowMissSensitiveNotificationDialog productType:", Integer.valueOf(i));
        if (Build.VERSION.SDK_INT < 35) {
            LogUtil.a("WearHomeUtils", "isShowMissSensitiveNotificationDialog sdk version:", Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (jdi.c(context.getApplicationContext(), f16099a)) {
            LogUtil.a("WearHomeUtils", "isShowMissSensitiveNotificationDialog has Sensitive Permission");
            return false;
        }
        if (i == 75) {
            LogUtil.a("WearHomeUtils", "isShowMissSensitiveNotificationDialog deviceInfo is blot");
            return false;
        }
        if (e("wear_sensitive_notification_time", 30)) {
            return true;
        }
        ReleaseLogUtil.e("R_WearHomeUtils", "isShowMissSensitiveNotificationDialog isTimeExpired:false");
        return false;
    }

    public static boolean i(Context context) {
        if (Build.VERSION.SDK_INT < 35) {
            LogUtil.a("WearHomeUtils", "isShowReopenNotificationTip sdk version:", Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (!jdi.c(context.getApplicationContext(), f16099a)) {
            LogUtil.a("WearHomeUtils", "isShowReopenNotificationTip not Sensitive Permission");
            return false;
        }
        if (!jrg.b()) {
            LogUtil.a("WearHomeUtils", "isShowReopenNotificationTip NotificationAuthorizeEnabled false");
            return false;
        }
        String e2 = SharedPreferenceManager.e("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", "");
        LogUtil.a("WearHomeUtils", "isShowReopenNotificationTip sensitivePermissionStatus:", e2);
        if (!SensitivePermissionStatus.RESTART.getValue().equals(e2)) {
            return false;
        }
        if (e("wear_reopen_notification_time", 30)) {
            return true;
        }
        ReleaseLogUtil.e("R_WearHomeUtils", "isShowReopenNotificationTip isTimeExpired:false");
        return false;
    }
}
