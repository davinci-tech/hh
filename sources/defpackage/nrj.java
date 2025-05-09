package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class nrj {
    private static Context c = BaseApplication.getContext();

    public static boolean b() {
        return cJZ_() != null && d(BaseApplication.getContext()) >= 761701558;
    }

    private static int d(Context context) {
        int i = 0;
        try {
            i = context.getPackageManager().getPackageInfo("com.google.android.wearable.app.cn", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("CassiniPairUpdateApp", "getVersionError, NameNotFoundException");
        }
        LogUtil.a("CassiniPairUpdateApp", "wear os version is: ", Integer.valueOf(i));
        return i;
    }

    public static PackageInfo cJZ_() {
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        try {
            return packageManager.getPackageInfo("com.google.android.wearable.app.cn", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("CassiniPairUpdateApp", "onClick() androidWearNameCn, error NameNotFoundException.");
            try {
                return packageManager.getPackageInfo("com.google.android.wearable.app", 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                LogUtil.b("CassiniPairUpdateApp", "onClick() androidWearName, error NameNotFoundException.");
                return null;
            }
        }
    }

    public static void cJY_(Context context, View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        LogUtil.a("CassiniPairUpdateApp", "Enter installShowDialog.");
        if (context == null) {
            LogUtil.h("CassiniPairUpdateApp", "context is null.");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(BaseApplication.getContext().getResources().getString(R$string.IDS_qrcode_update_experience));
        builder.e(BaseApplication.getContext().getResources().getString(R$string.IDS_qrcode_wear_os_upgrade_content));
        builder.cyS_(BaseApplication.getContext().getResources().getString(R$string.IDS_qrcode_continue_pair), onClickListener);
        builder.cyV_(BaseApplication.getContext().getResources().getString(R$string.IDS_device_to_intelligent_home_linkage_go_to_download), new View.OnClickListener() { // from class: nrj.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CassiniPairUpdateApp", "Enter PositiveButton OK.");
                if (CommonUtil.aa(BaseApplication.getContext())) {
                    if (jad.d(55)) {
                        LogUtil.a("CassiniPairUpdateApp", "Enter gotoChinese");
                        nrj.c(nrj.c);
                    } else {
                        LogUtil.a("CassiniPairUpdateApp", "Enter gotoOverSea");
                        nrj.b(nrj.c);
                    }
                } else {
                    nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getString(R$string.CS_network_connect_error));
                }
                View.OnClickListener onClickListener3 = onClickListener2;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public static void cKb_(Context context, View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        LogUtil.a("CassiniPairUpdateApp", "Enter uninstallShowDialog.");
        if (context == null) {
            LogUtil.h("CassiniPairUpdateApp", "context is null.");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(BaseApplication.getContext().getResources().getString(R$string.IDS_qrcode_wear_os_down_title));
        builder.e(BaseApplication.getContext().getResources().getString(R$string.IDS_qrcode_wear_os_down_content));
        builder.cyS_(BaseApplication.getContext().getResources().getString(R$string.IDS_settings_button_cancal), onClickListener);
        builder.cyV_(BaseApplication.getContext().getResources().getString(R$string.IDS_device_to_intelligent_home_linkage_go_to_download), new View.OnClickListener() { // from class: nrj.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CassiniPairUpdateApp", "Enter PositiveButton OK.");
                if (CommonUtil.aa(BaseApplication.getContext())) {
                    if (jad.d(55)) {
                        LogUtil.a("CassiniPairUpdateApp", "Enter gotoChinese ");
                        nrj.c(nrj.c);
                    } else {
                        LogUtil.a("CassiniPairUpdateApp", "Enter gotoOverSea ");
                        nrj.b(nrj.c);
                    }
                    view.setTag("network_connected");
                } else {
                    view.setTag("network_disconnected");
                    nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getString(R$string.CS_network_connect_error));
                }
                View.OnClickListener onClickListener3 = onClickListener2;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public static void c(Context context) {
        LogUtil.a("CassiniPairUpdateApp", "enterAppStoreChina():");
        if (context == null) {
            LogUtil.h("CassiniPairUpdateApp", "enterAppStoreChina, mContext is null.");
            return;
        }
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            LogUtil.h("CassiniPairUpdateApp", "enterAppStoreChina, Network is not Connected!");
            nrh.e(context, R$string.CS_network_connect_error);
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.google.android.wearable.app.cn"));
            intent.addFlags(268435456);
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("CassiniPairUpdateApp", "EnterAppStoreChina start Wear Os install error.");
            nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getString(R$string.IDS_device_hauwei_watch_download_android_wear_tips));
        }
    }

    public static void b(Context context) {
        LogUtil.a("CassiniPairUpdateApp", "enterAppStoreOversea():");
        if (context == null) {
            LogUtil.h("CassiniPairUpdateApp", "enterAppStoreOversea, mContext is null.");
            return;
        }
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            LogUtil.h("CassiniPairUpdateApp", "enterAppStoreOversea, Network is not Connected!");
            nrh.e(context, R$string.CS_network_connect_error);
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.google.android.wearable.app"));
            intent.addFlags(268435456);
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("CassiniPairUpdateApp", "EnterAppStoreOversea start Wear Os install error.");
            nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getString(R$string.IDS_device_hauwei_watch_download_android_wear_tips));
        }
    }

    public static boolean cKa_(Activity activity) {
        PackageInfo cJZ_ = cJZ_();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        if (cJZ_ != null) {
            intent.setPackage(cJZ_.packageName);
        }
        ResolveInfo next = BaseApplication.getContext().getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        if (next == null) {
            return false;
        }
        String str = next.activityInfo.packageName;
        String str2 = next.activityInfo.name;
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent2.setComponent(new ComponentName(str, str2));
        intent2.setAction("com.android.setupwizard.PARTNER_SETUP");
        if (activity != null) {
            try {
                activity.startActivity(intent2);
            } catch (ActivityNotFoundException e) {
                LogUtil.b("CassiniPairUpdateApp", "wearOsIntent exception", e.getMessage());
            }
        }
        return true;
    }
}
