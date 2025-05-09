package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hwcloudmodel.agreement.AccessTokenManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import health.compact.a.CommonUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class mxv {
    public static void d(Context context, String str) {
        b(context, str, 0, null);
    }

    public static void b(Context context, String str) {
        b(context, "com.huawei.health.ecg.collection", 0, str);
    }

    public static void b(final Context context, final String str, final int i, final String str2) {
        LogUtil.a("QuickAppUtil", "startQuickApp ", str);
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("QuickAppUtil", "startQuickApp activity is null or packageName is empty");
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LogUtil.h("QuickAppUtil", "startQuickApp switch to sub thread");
            ThreadPoolManager.d().execute(new Runnable() { // from class: mxu
                @Override // java.lang.Runnable
                public final void run() {
                    mxv.b(context, str, i, str2);
                }
            });
        } else {
            if (c(context, i, str2, str)) {
                return;
            }
            Context context2 = BaseApplication.getContext();
            if ("com.huawei.health.mc".equals(str) || !CommonUtil.z(context2)) {
                return;
            }
            LogUtil.a("QuickAppUtil", "startQuickApp isHmsLiteEnable.");
            a(context2);
        }
    }

    private static boolean c(Context context, int i, String str, String str2) {
        str2.hashCode();
        if (str2.equals("com.huawei.health.h5.ecgce")) {
            d(context, i, str, "com.huawei.health.h5.ecgce");
        } else {
            if (!str2.equals("com.huawei.health.ecg.collection")) {
                return false;
            }
            d(context, i, str, "com.huawei.health.h5.ecg");
        }
        return true;
    }

    private static void d(Context context, int i, String str, String str2) {
        nsd.a(false);
        if (i == 5) {
            LogUtil.a("QuickAppUtil", "open the ecg collection.");
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEART_ECG_2090012.value(), hashMap, 0);
            a(15);
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        e(i, builder);
        if (!TextUtils.isEmpty(str)) {
            builder.addCustomizeArg("enterType", str);
        }
        bzs.e().loadH5ProApp(context, str2, builder);
        if (TextUtils.isEmpty(str) || !(context instanceof Activity)) {
            return;
        }
        ((Activity) context).finish();
    }

    private static void e(int i, H5ProLaunchOption.Builder builder) {
        if (i != 0) {
            builder.addCustomizeArg("from", String.valueOf(i));
        }
    }

    public static void a(int i) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(i);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public static void a(Context context) {
        final Activity activity = BaseApplication.getActivity();
        if (HuaweiLoginManager.checkIsInstallHuaweiAccount(context) && CommonUtil.g(context) >= 40000000) {
            LoginInit.getInstance(context).logoutWhenTokenInvalid(null);
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setClassName(context, "com.huawei.health.StTimeoutActivity");
            context.startActivity(intent);
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: mya
            @Override // java.lang.Runnable
            public final void run() {
                mxv.crA_(activity);
            }
        });
    }

    static /* synthetic */ void crA_(final Activity activity) {
        LogUtil.a("QuickAppUtil", "showHmsUpdateDialog dialog in main thread");
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(activity).b(R$string.IDS_hw_wechat_rank_show_common_title).d(R$string.IDS_vmall_hms_core_installed_alert).cyU_(R$string.IDS_settings_jawbone_download, new View.OnClickListener() { // from class: mxz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mxv.cry_(activity, view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: mxx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void cry_(Activity activity, View view) {
        crs_(activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void crs_(final Activity activity) {
        final AccessTokenManager accessTokenManager = new AccessTokenManager(activity);
        accessTokenManager.hmsConnect(BaseApplication.getContext(), new HuaweiApiClient.ConnectionCallbacks() { // from class: mxv.5
            @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
            public void onConnected() {
                AccessTokenManager accessTokenManager2 = AccessTokenManager.this;
                if (accessTokenManager2 != null) {
                    accessTokenManager2.shutDownThread();
                }
            }

            @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
            public void onConnectionSuspended(int i) {
                AccessTokenManager accessTokenManager2 = AccessTokenManager.this;
                if (accessTokenManager2 != null) {
                    accessTokenManager2.shutDownThread();
                }
            }
        }, new HuaweiApiClient.OnConnectionFailedListener() { // from class: mxy
            @Override // com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener
            public final void onConnectionFailed(ConnectionResult connectionResult) {
                mxv.cru_(AccessTokenManager.this, activity, connectionResult);
            }
        });
    }

    static /* synthetic */ void cru_(AccessTokenManager accessTokenManager, Activity activity, ConnectionResult connectionResult) {
        if (accessTokenManager != null) {
            accessTokenManager.shutDownThread();
        }
        int errorCode = connectionResult.getErrorCode();
        LogUtil.h("QuickAppUtil", "connectionFailed errorCode = ", Integer.valueOf(errorCode));
        HuaweiApiAvailability huaweiApiAvailability = HuaweiApiAvailability.getInstance();
        if (huaweiApiAvailability.isUserResolvableError(errorCode)) {
            LogUtil.a("QuickAppUtil", "availability.isUserResolvableError(errorCode):true");
            huaweiApiAvailability.resolveError(activity, errorCode, 47000);
        }
    }

    public static void d(Context context) {
        LogUtil.a("QuickAppUtil", "showHmsUpdateDialog dialog in main thread");
        final Activity activity = BaseApplication.getActivity();
        activity.runOnUiThread(new Runnable() { // from class: mxs
            @Override // java.lang.Runnable
            public final void run() {
                mxv.crx_(activity);
            }
        });
    }

    static /* synthetic */ void crx_(final Activity activity) {
        LogUtil.a("QuickAppUtil", "showHmsUpdateDialog dialog in main thread");
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(activity).b(R$string.IDS_hw_wechat_rank_show_common_title).d(R$string.IDS_vmall_hms_core_installed_alert).cyU_(R$string.IDS_settings_jawbone_download, new View.OnClickListener() { // from class: mxt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                mxv.crv_(activity, view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: mxw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void crv_(Activity activity, View view) {
        crs_(activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void crt_(Activity activity, String str, String str2) {
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addCustomizeArg("enterType", str2);
        builder.addCustomizeArg("donateId", str);
        bzs.e().loadH5ProApp(activity, cwo.b() ? "com.huawei.health.h5.ecg" : "com.huawei.health.h5.ecgce", builder);
    }

    public static void crB_(Activity activity, String str, String str2) {
        String str3 = cwo.b() ? "com.huawei.health.ecg.collection" : "com.huawei.health.h5.ecgce";
        if ("returnCard".equals(str2)) {
            b(activity, str3, 0, "interpretation");
            return;
        }
        if ("monthReport".equals(str2)) {
            b(activity, str3, 0, "monthReport");
            return;
        }
        if ("donate".equals(str2)) {
            crt_(activity, str, "donate");
        } else if ("electrocardiogram".equals(str2)) {
            nsd.e(false);
            b(activity, "com.huawei.health.h5.ecgce", 5, null);
        } else if ("collection".equals(str2)) {
            b(activity, "com.huawei.health.ecg.collection", 5, null);
        } else {
            b(activity, str3, 5, null);
        }
        activity.finish();
    }
}
