package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cpn {
    public static final String[] e = {"android.permission.ACCESS_COARSE_LOCATION"};

    public static boolean c(String str, List<ScanResult> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<ScanResult> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().SSID.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(String str, List<WifiConfiguration> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<WifiConfiguration> it = list.iterator();
        while (it.hasNext()) {
            if (cub.e(it.next().SSID).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str, List<ScanResult> list) {
        if (koq.b(list)) {
            return false;
        }
        Iterator<ScanResult> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().SSID.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean e() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            LogUtil.a("WifiInfoUtil", "bluetooth is currently enabled and ready for use");
            return true;
        }
        LogUtil.h("WifiInfoUtil", "bluetooth is disable");
        return false;
    }

    public static String b(String str, String str2) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "hagrid_wifi_ssid" + str);
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "hagrid_wifi_ssid" + str2);
        return TextUtils.isEmpty(b2) ? SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "hagrid_wifi_ssid") : b2;
    }

    public static String e(boolean z) {
        String url;
        if (z) {
            url = GRSManager.a(cpp.a()).getUrl("hilinkDevicePrimaryCloudUrl");
        } else {
            url = GRSManager.a(cpp.a()).getUrl("hilinkDeviceStandbyCloudUrl");
        }
        if (!CommonUtil.as()) {
            return url;
        }
        LogUtil.a("WifiInfoUtil", "hilink_json getDeviceCloudOtherUrl isPrimary is ", Boolean.valueOf(z), " and cloudDevoceUrl is ", url);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009);
        LogUtil.a("WifiInfoUtil", "getDeviceCloudOtherUrl currentUserSiteIdString is ", accountInfo);
        int i = 0;
        if (!TextUtils.isEmpty(accountInfo)) {
            try {
                i = Integer.parseInt(accountInfo);
            } catch (NumberFormatException unused) {
                LogUtil.b("WifiInfoUtil", "getDeviceCloudOtherUrl NumberFormatException");
            }
            LogUtil.a("WifiInfoUtil", "getDeviceCloudOtherUrl currentUserSiteId is ", Integer.valueOf(i));
        }
        LogUtil.c("WifiInfoUtil", "getDeviceCloudOtherUrl currentUserSiteId is:", Integer.valueOf(i));
        if (i == 5) {
            return CompileParameterUtil.c("GRAY_DEVICE_CLOUD_PRIMARY_URL_ASIA", "");
        }
        if (i == 7) {
            return CompileParameterUtil.c("GRAY_DEVICE_CLOUD_PRIMARY_URL_EU", "");
        }
        if (i == 8) {
            return CompileParameterUtil.c("GRAY_DEVICE_CLOUD_PRIMARY_URL_RU", "");
        }
        if (i == 1) {
            return CompileParameterUtil.c("GRAY_DEVICE_CLOUD_PRIMARY_URL_CN", "");
        }
        LogUtil.a("WifiInfoUtil", "do not hard code device cloud gray url for beta version for site id", Integer.valueOf(i));
        return CompileParameterUtil.c("GRAY_DEVICE_CLOUD_PRIMARY_URL_CN", "");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c() {
        /*
            android.content.Context r0 = defpackage.cpp.a()
            health.compact.a.GRSManager r0 = health.compact.a.GRSManager.a(r0)
            java.lang.String r1 = "healthAPPToDeviceUrl"
            java.lang.String r0 = r0.getUrl(r1)
            boolean r1 = health.compact.a.CommonUtil.as()
            if (r1 != 0) goto L15
            return r0
        L15:
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.login.ui.login.LoginInit r0 = com.huawei.login.ui.login.LoginInit.getInstance(r0)
            r1 = 1009(0x3f1, float:1.414E-42)
            java.lang.String r0 = r0.getAccountInfo(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r2 = "WifiInfoUtil"
            if (r1 != 0) goto L39
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L30
            goto L3a
        L30:
            java.lang.String r0 = "getDeviceCloudUrl NumberFormatException"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        L39:
            r0 = 0
        L3a:
            java.lang.String r1 = "getDeviceCloudOtherUrl currentUserSiteId is "
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            r1 = 5
            java.lang.String r3 = ""
            if (r0 != r1) goto L53
            java.lang.String r0 = "GRAY_DEVICE_CLOUD_URL_ASIA"
            java.lang.String r0 = health.compact.a.CompileParameterUtil.c(r0, r3)
            return r0
        L53:
            r1 = 7
            if (r0 != r1) goto L5d
            java.lang.String r0 = "GRAY_DEVICE_CLOUD_URL_EU"
            java.lang.String r0 = health.compact.a.CompileParameterUtil.c(r0, r3)
            return r0
        L5d:
            r1 = 8
            if (r0 != r1) goto L68
            java.lang.String r0 = "GRAY_DEVICE_CLOUD_URL_RU"
            java.lang.String r0 = health.compact.a.CompileParameterUtil.c(r0, r3)
            return r0
        L68:
            java.lang.String r1 = "do not hard code device cloud gray url for beta version for site id"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r0)
            java.lang.String r0 = "GRAY_DEVICE_CLOUD_URL_CN"
            java.lang.String r0 = health.compact.a.CompileParameterUtil.c(r0, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cpn.c():java.lang.String");
    }

    public static void Kg_(View view, Context context, boolean z, boolean z2) {
        if (view == null) {
            LogUtil.h("WifiInfoUtil", "initAuthDialogContent, dialogView is null");
            return;
        }
        String string = context.getResources().getString(R.string.IDS_device_haige_user_permission_message, "");
        boolean z3 = z || z2;
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.dialog_text_alert_message);
        if (z3) {
            StringBuffer stringBuffer = new StringBuffer(16);
            if (z) {
                if (Utils.o()) {
                    stringBuffer.append(System.lineSeparator()).append(context.getResources().getString(R.string._2130840570_res_0x7f020bfa));
                } else {
                    stringBuffer.append(System.lineSeparator()).append(context.getResources().getString(R.string.IDS_device_haige_user_permission_message_health_item));
                }
            }
            if (z2) {
                stringBuffer.append(System.lineSeparator()).append(context.getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_personal_infomation_message));
            }
            SpannableString spannableString = new SpannableString(context.getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_message, context.getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_params_message_new, stringBuffer)));
            nsi.cKH_(spannableString, stringBuffer.toString(), new AbsoluteSizeSpan(13, true));
            healthTextView.setText(spannableString);
            return;
        }
        healthTextView.setText(string);
    }
}
