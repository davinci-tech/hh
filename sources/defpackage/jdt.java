package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jdt {
    public static void c(Context context, boolean z, c cVar) {
        if (context == null) {
            LogUtil.h("PhoneStateUtils", "setPhoneStateListener context is null");
            return;
        }
        if (cVar == null) {
            LogUtil.h("PhoneStateUtils", "setPhoneStateListener listener is null");
            return;
        }
        LogUtil.a("PhoneStateUtils", "setPhoneStateListener isListen = ", Boolean.valueOf(z));
        Object systemService = context.getSystemService("phone");
        if (systemService instanceof TelephonyManager) {
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            if (z) {
                bFY_(context, telephonyManager, cVar);
            } else {
                telephonyManager.listen(cVar, 0);
            }
        }
    }

    private static void bFY_(Context context, final TelephonyManager telephonyManager, final c cVar) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 31) {
            telephonyManager.listen(cVar, 32);
            return;
        }
        if (PermissionUtil.e(context, new String[]{"android.permission.READ_PHONE_STATE"})) {
            telephonyManager.listen(cVar, 32);
            return;
        }
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            activity = BaseApplication.getActivity();
        }
        cVar.b();
        PermissionUtil.bFX_(activity, new String[]{"android.permission.READ_PHONE_STATE"}, new PermissionsResultAction() { // from class: jdt.2
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("R_PhoneStateUtils", "setTelephonyListen onGranted");
                telephonyManager.listen(cVar, 32);
                cVar.d();
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("R_PhoneStateUtils", "setTelephonyListen onDenied");
                cVar.d();
            }
        });
    }

    public static class c extends PhoneStateListener {
        public void b() {
        }

        public void d() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
        }
    }
}
