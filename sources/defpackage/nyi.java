package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.device.activity.update.DeviceOtaActivity;
import com.huawei.ui.device.activity.update.UpdateVersionActivity;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class nyi {
    public static String a(Context context, int i) {
        if (context == null) {
            ReleaseLogUtil.d("DEVMGR_UpdateUtils", "getErrorMessage context is null");
            return "";
        }
        ReleaseLogUtil.e("DEVMGR_UpdateUtils", "getErrorMessage result is,", Integer.valueOf(i));
        if (i == 1) {
            return context.getString(R.string._2130841542_res_0x7f020fc6);
        }
        if (i == 3) {
            return context.getString(R.string._2130844882_res_0x7f021cd2);
        }
        if (i != 4) {
            return context.getString(R.string._2130841543_res_0x7f020fc7);
        }
        return nsn.ae(BaseApplication.getContext()) ? context.getString(R.string._2130844350_res_0x7f021abe, UnitUtil.e(10.0d, 2, 0)) : context.getString(R.string._2130841495_res_0x7f020f97);
    }

    public static void e(Context context, boolean z, String str) {
        ReleaseLogUtil.e("DEVMGR_UpdateUtils", " enterDeviceOtaActivity()");
        try {
            Intent intent = new Intent();
            intent.setClass(context, DeviceOtaActivity.class);
            intent.putExtra("is_package_already_exists", z);
            intent.putExtra("device_id", str);
            intent.addFlags(131072);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("UpdateUtils", "enterDeviceOtaActivity exception：", ExceptionUtils.d(e));
        }
    }

    public static void e(final UpdateVersionActivity updateVersionActivity, final DeviceInfo deviceInfo, final boolean z) {
        if (updateVersionActivity != null) {
            LogUtil.a("UpdateUtils", "showEnterpriseAgreementDialog enter");
            CustomAlertDialog c = new CustomAlertDialog.Builder(updateVersionActivity).cyp_(View.inflate(updateVersionActivity, R.layout.dialog_enterprise_customization_agreement, null)).cyn_(R.string._2130845098_res_0x7f021daa, new DialogInterface.OnClickListener() { // from class: nyi.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.a("UpdateUtils", "start customAlertDialog, user click Negative button!");
                    dialogInterface.dismiss();
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).cyo_(R.string._2130841555_res_0x7f020fd3, new DialogInterface.OnClickListener() { // from class: nyi.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.a("UpdateUtils", "start showEnterpriseAgreementDialog,permission_ok: ", Boolean.valueOf(z));
                    if (z) {
                        updateVersionActivity.e();
                    }
                    kxz.h("1", deviceInfo.getDeviceIdentify(), updateVersionActivity);
                    jkk.d().c(deviceInfo, "1");
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).c();
            if (c == null || c.isShowing()) {
                return;
            }
            c.setCancelable(false);
            c.show();
        }
    }

    public static boolean d(DeviceInfo deviceInfo) {
        if (!cwi.c(deviceInfo, 108) || Utils.l()) {
            return Build.VERSION.SDK_INT > 30 && CommonUtil.bh() && c(deviceInfo);
        }
        LogUtil.h("UpdateUtils", "isSupportWlanTransmit is Galileo");
        return false;
    }

    public static boolean c(DeviceInfo deviceInfo) {
        return cwi.c(deviceInfo, 35) || cwi.c(deviceInfo, 76);
    }

    public static boolean a(Context context) {
        boolean z = false;
        if (context == null) {
            LogUtil.h("UpdateUtils", "isWifiEnabled context is null");
            return false;
        }
        Object systemService = context.getSystemService("wifi");
        if ((systemService instanceof WifiManager) && ((WifiManager) systemService).getWifiState() == 3) {
            z = true;
        }
        ReleaseLogUtil.e("DEVMGR_UpdateUtils", "isWifiEnabled:", Boolean.valueOf(z));
        return z;
    }

    public static class b extends Handler {
        private WeakReference<UpdateVersionActivity> b;

        public b(UpdateVersionActivity updateVersionActivity) {
            this.b = new WeakReference<>(updateVersionActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("UpdateUtils", "UpgradeHandler message is null");
                return;
            }
            UpdateVersionActivity updateVersionActivity = this.b.get();
            if (updateVersionActivity == null) {
                LogUtil.h("UpdateUtils", "UpgradeHandler activity is null");
                return;
            }
            ReleaseLogUtil.e("DEVMGR_UpdateUtils", "UpgradeHandler message is, ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                updateVersionActivity.a();
                return;
            }
            if (i == 2) {
                updateVersionActivity.d();
                return;
            }
            if (i == 3) {
                if (message.arg1 == 0 && (message.obj instanceof String)) {
                    a((String) message.obj);
                }
                updateVersionActivity.finish();
                return;
            }
            LogUtil.a("UpdateUtils", "UpgradeHandler default");
        }

        private void a(String str) {
            Intent intent = new Intent();
            intent.putExtra("UNIQUE_ID", str);
            intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginWearAbility_A_0);
            bwf.a().launchActivity(this.b.get(), intent);
            ReleaseLogUtil.e("DEVMGR_UpdateUtils", "launchActivity OtaTempActivity");
        }
    }
}
