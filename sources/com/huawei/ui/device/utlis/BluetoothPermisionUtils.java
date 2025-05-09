package com.huawei.ui.device.utlis;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class BluetoothPermisionUtils {
    public static boolean c(Context context) {
        if (Build.VERSION.SDK_INT <= 30) {
            return PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED;
        }
        return e(context);
    }

    public static boolean e(Context context) {
        if (Build.VERSION.SDK_INT <= 30) {
            ReleaseLogUtil.e("R_BluetoothPermisionUtils", "checkNearbyPermission SDK_INT <= R");
            return true;
        }
        if (context == null) {
            ReleaseLogUtil.e("R_BluetoothPermisionUtils", "checkNearbyPermission context == null");
            return false;
        }
        PermissionUtil.PermissionResult e = PermissionUtil.e(context, PermissionUtil.PermissionType.SCAN);
        ReleaseLogUtil.e("R_BluetoothPermisionUtils", "checkNearbyPermission result=", e);
        return e == PermissionUtil.PermissionResult.GRANTED;
    }

    public static void cTI_(final Activity activity, final boolean z) {
        if (activity == null) {
            LogUtil.h("BluetoothPermisionUtils", "gotoSettingActivity activity == null");
        } else {
            new CustomTextAlertDialog.Builder(activity).b(R$string.IDS_hwh_home_other_permissions_title).e(activity.getString(R$string.IDS_hw_bluetooth_permission_guide)).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: oav
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BluetoothPermisionUtils.cTJ_(z, activity, view);
                }
            }).cyU_(R$string.IDS_hwh_motiontrack_permission_guide_go_set, new View.OnClickListener() { // from class: oay
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BluetoothPermisionUtils.cTK_(activity, z, view);
                }
            }).a().show();
        }
    }

    public static /* synthetic */ void cTJ_(boolean z, Activity activity, View view) {
        if (z) {
            activity.finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void cTK_(Activity activity, boolean z, View view) {
        nsn.ak(activity);
        if (z) {
            activity.finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static abstract class NearbyPermissionAction extends CustomPermissionAction {
        private final Context mContext;

        public NearbyPermissionAction(Context context) {
            super(context);
            this.mContext = context;
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            BluetoothPermisionUtils.cTI_((Activity) this.mContext, false);
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            BluetoothPermisionUtils.cTI_((Activity) this.mContext, false);
        }
    }
}
