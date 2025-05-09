package defpackage;

import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Utils;

/* loaded from: classes5.dex */
public class jpv {
    /* JADX WARN: Removed duplicated region for block: B:5:0x003e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x003c A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int e() {
        /*
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r1 = "time_12_24"
            java.lang.String r0 = android.provider.Settings.System.getString(r0, r1)
            java.lang.String r1 = "getSystemTimeFormat() timeFormat :"
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r0}
            java.lang.String r2 = "SettingUtil"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            if (r0 == 0) goto L25
            java.lang.String r1 = "24"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L3e
            goto L3c
        L25:
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r0 = android.text.format.DateFormat.is24HourFormat(r0)
            java.lang.String r1 = "getSystemTimeFormat() is24HourFormat :"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            if (r0 == 0) goto L3e
        L3c:
            r0 = 2
            goto L3f
        L3e:
            r0 = 1
        L3f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jpv.e():int");
    }

    public static int d() {
        String string = Settings.System.getString(BaseApplication.getContext().getContentResolver(), "date_format");
        LogUtil.a("SettingUtil", "getSystemDateFormat() dateFormat :", string);
        if (string != null && string.length() >= 2 && !"yy".equals(string.substring(0, 2))) {
            if ("MM".equals(string.substring(0, 2))) {
                return 2;
            }
            if ("dd".equals(string.substring(0, 2))) {
                return 3;
            }
            LogUtil.a("SettingUtil", "getSystemDateFormat other dataType");
        }
        return 1;
    }

    public static boolean a() {
        if (!Utils.i() || (LoginInit.getInstance(BaseApplication.getContext()).getIsLogined() && b())) {
            return true;
        }
        LogUtil.a("SettingUtil", "no login return.");
        return false;
    }

    private static boolean b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        return !TextUtils.isEmpty(accountInfo) && HiHealthManager.d(BaseApplication.getContext()).checkHiHealthLogin(accountInfo);
    }
}
