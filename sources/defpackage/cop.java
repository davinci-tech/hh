package defpackage;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.view.View;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.health.R;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class cop {
    private CustomViewDialog d;
    private NoTitleCustomAlertDialog e;

    public boolean d(Context context) {
        if (context != null) {
            return PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED;
        }
        LogUtil.h("GpslUtil", "checkGpsIsAcceptPermission, context is null");
        return false;
    }

    public void c(Context context) {
        LogUtil.a("GpslUtil", "enter createGpsAskDialog ****");
        if (context == null) {
            LogUtil.h("GpslUtil", "createGpsAskDialog, context or handler is null");
            return;
        }
        if (context instanceof DeviceMainActivity) {
            final DeviceMainActivity deviceMainActivity = (DeviceMainActivity) context;
            if (deviceMainActivity.isFinishing()) {
                LogUtil.h("GpslUtil", "createGpsAskDialog,activity is finishing");
                return;
            }
            NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
            if (noTitleCustomAlertDialog != null) {
                if (noTitleCustomAlertDialog.isShowing()) {
                    this.e.dismiss();
                }
                this.e = null;
            }
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(R.string._2130844117_res_0x7f0219d5).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cov
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cop.this.JQ_(view);
                }
            }).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: cow
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cop.this.JR_(deviceMainActivity, view);
                }
            }).e();
            this.e = e;
            e.setCancelable(false);
            this.e.show();
        }
    }

    /* synthetic */ void JQ_(View view) {
        LogUtil.a("GpslUtil", "createGpsAskDialog setNegativeButton");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.e = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void JR_(DeviceMainActivity deviceMainActivity, View view) {
        LogUtil.a("GpslUtil", "createGpsAskDialog enable");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.e = null;
        }
        d(deviceMainActivity);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(final DeviceMainActivity deviceMainActivity) {
        PermissionUtil.b(deviceMainActivity, PermissionUtil.PermissionType.LOCATION, new PermissionsResultAction() { // from class: cop.1
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("GpslUtil", "requestPermission onGranted");
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("GpslUtil", "requestPermission onDenied");
                cop.this.a(deviceMainActivity);
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                super.onForeverDenied(permissionType);
                LogUtil.a("GpslUtil", "requestPermission onForeverDenied");
                cop.this.a(deviceMainActivity);
            }
        });
    }

    public void a(final DeviceMainActivity deviceMainActivity) {
        if (deviceMainActivity == null) {
            LogUtil.h("GpslUtil", "createGpsDialog, context is null");
            return;
        }
        LogUtil.a("GpslUtil", "enter createGpsDialog");
        if (deviceMainActivity.isFinishing()) {
            LogUtil.h("GpslUtil", "createGpsDialog,activity is finishing");
            return;
        }
        CustomViewDialog customViewDialog = this.d;
        if (customViewDialog != null) {
            if (customViewDialog.isShowing()) {
                this.d.dismiss();
            }
            this.d = null;
        }
        View inflate = View.inflate(deviceMainActivity, R.layout.dialog_user_auth_message, null);
        String string = deviceMainActivity.getResources().getString(R.string._2130844118_res_0x7f0219d6, "");
        ((HealthTextView) inflate.findViewById(R.id.dialog_text_alert_message)).setText(deviceMainActivity.getResources().getString(R.string._2130844119_res_0x7f0219d7, ""));
        CustomViewDialog e = new CustomViewDialog.Builder(deviceMainActivity).c(true).a(string).czg_(inflate).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: coq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cop.this.JS_(view);
            }
        }).cze_(R.string._2130842041_res_0x7f0211b9, new View.OnClickListener() { // from class: cox
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cop.this.JT_(deviceMainActivity, view);
            }
        }).e();
        this.d = e;
        e.setCancelable(false);
        this.d.show();
    }

    /* synthetic */ void JS_(View view) {
        CustomViewDialog customViewDialog = this.d;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
            this.d = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void JT_(DeviceMainActivity deviceMainActivity, View view) {
        CustomViewDialog customViewDialog = this.d;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
            this.d = null;
        }
        b(deviceMainActivity, 2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(DeviceMainActivity deviceMainActivity, int i) {
        if (deviceMainActivity == null) {
            LogUtil.h("GpslUtil", "startAppSettingActivity, context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", deviceMainActivity.getPackageName(), null));
        deviceMainActivity.startActivityForResult(intent, i);
    }

    public boolean b(Context context) {
        boolean e = e(context);
        LogUtil.a("GpslUtil", "gpsLocationisEnable isGpsEnable is ", Boolean.valueOf(e));
        return e;
    }

    public boolean e(Context context) {
        boolean z;
        boolean z2;
        if (context == null) {
            LogUtil.h("GpslUtil", "context is null");
            return false;
        }
        Object systemService = context.getSystemService("location");
        if (systemService instanceof LocationManager) {
            LocationManager locationManager = (LocationManager) systemService;
            z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        } else {
            z = false;
            z2 = false;
        }
        return z2 || z;
    }
}
