package com.huawei.health.ecologydevice.ui.util;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.dcz;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.Locale;

/* loaded from: classes3.dex */
public class PermissionDialogHelper {

    /* renamed from: a, reason: collision with root package name */
    private Activity f2340a;
    private PermissionDialogCallback b;

    public interface OpenBlueToothAction {
        void onPermissionDenied();

        void onPermissionGranted();
    }

    public interface PermissionDialogCallback {
        void onResult(boolean z);
    }

    public PermissionDialogHelper(Activity activity, PermissionDialogCallback permissionDialogCallback) {
        this.f2340a = activity;
        this.b = permissionDialogCallback;
    }

    public void d(dcz dczVar, String str) {
        int c = dczVar.x().c();
        LogUtil.a("PermissionDialogHelper", "ProductIntroductionFragment onClick scanMode is ", Integer.valueOf(c));
        if ((c != 1 && c != 2) || BluetoothAdapter.getDefaultAdapter().getState() == 12) {
            HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
            if (honourDeviceConstantsApi.isHonourDevice(str)) {
                LogUtil.a("PermissionDialogHelper", "isHonourDevice");
                if (!honourDeviceConstantsApi.getAm16PermissionTip()) {
                    LogUtil.a("PermissionDialogHelper", "isHonourDevice showPermissionDialogTip");
                    e();
                    return;
                } else {
                    LogUtil.a("PermissionDialogHelper", "isHonourDevice checkHavePermission");
                    b(PermissionUtil.PermissionType.PHONE_STATE);
                    return;
                }
            }
            this.b.onResult(BluetoothAdapter.getDefaultAdapter().isEnabled());
            return;
        }
        this.b.onResult(BluetoothAdapter.getDefaultAdapter().isEnabled());
    }

    public static boolean b(String str) {
        boolean z = Build.VERSION.SDK_INT >= 29;
        if (!"825c82bd-84fe-44a0-9884-6a764bd73183".contains(str) || CommonUtil.bh() || !z) {
            return false;
        }
        LogUtil.h("PermissionDialogHelper", "not support hdp protocol");
        return true;
    }

    private void e() {
        Activity activity = this.f2340a;
        if (activity != null) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f2340a).e(String.format(Locale.ENGLISH, activity.getResources().getString(R.string.IDS_plugin_device_am16_permission_tips), this.f2340a.getResources().getString(R.string._2130848991_res_0x7f022cdf))).czA_(this.f2340a.getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: diu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czE_(this.f2340a.getString(R.string._2130841555_res_0x7f020fd3), new View.OnClickListener() { // from class: dix
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermissionDialogHelper.this.VC_(view);
                }
            }).e();
            e.setCancelable(false);
            e.show();
        }
    }

    public /* synthetic */ void VC_(View view) {
        ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).setAm16PermissionTip();
        b(PermissionUtil.PermissionType.PHONE_STATE);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(PermissionUtil.PermissionType permissionType) {
        if (this.f2340a != null) {
            LogUtil.a("PermissionDialogHelper", "checkHavePermission");
            PermissionUtil.b(this.f2340a, permissionType, new CustomPermissionAction(this.f2340a) { // from class: com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    PermissionDialogHelper permissionDialogHelper = PermissionDialogHelper.this;
                    permissionDialogHelper.Vw_(permissionDialogHelper.f2340a);
                }

                @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onForeverDenied(PermissionUtil.PermissionType permissionType2) {
                    super.onForeverDenied(permissionType2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Vw_(Activity activity) {
        PermissionUtil.b(activity, PermissionUtil.PermissionType.AUDIO_CALLS, new PermissionsResultAction() { // from class: com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.3
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("PermissionDialogHelper", "onGranted");
                PermissionDialogHelper.this.b.onResult(BluetoothAdapter.getDefaultAdapter().isEnabled());
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("PermissionDialogHelper", "onDenied ", str);
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                super.onForeverDenied(permissionType);
                nsn.c(PermissionDialogHelper.this.f2340a, PermissionDialogHelper.this.f2340a.getString(R.string.IDS_device_am16_permission_str));
            }
        });
    }

    public static PermissionUtil.PermissionType d() {
        if (Build.VERSION.SDK_INT > 30) {
            return PermissionUtil.PermissionType.SCAN;
        }
        return PermissionUtil.PermissionType.LOCATION;
    }

    private static PermissionUtil.PermissionResult c(Context context, PermissionUtil.PermissionType permissionType) {
        if (context == null) {
            LogUtil.a("PermissionDialogHelper", "Permission context is null!");
            return PermissionUtil.PermissionResult.DENIED;
        }
        if (Build.VERSION.SDK_INT < 29) {
            LogUtil.a("PermissionDialogHelper", "Permission small than Q result be Denied");
            return PermissionUtil.PermissionResult.DENIED;
        }
        return PermissionUtil.e(context, permissionType);
    }

    public static void VB_(Activity activity, PermissionUtil.PermissionType permissionType, CustomPermissionAction customPermissionAction) {
        if (activity == null) {
            LogUtil.h("PermissionDialogHelper", "Activity is null!");
            customPermissionAction.onDenied("activity is null!");
        } else if (c(activity, permissionType) != PermissionUtil.PermissionResult.GRANTED) {
            PermissionUtil.b(activity, permissionType, customPermissionAction);
        } else {
            LogUtil.a("PermissionDialogHelper", "Permission permission check onGrand");
            customPermissionAction.onGranted();
        }
    }

    public static boolean Vy_(Activity activity) {
        return Build.VERSION.SDK_INT <= 30 || PermissionUtil.e(activity, PermissionUtil.PermissionType.SCAN) == PermissionUtil.PermissionResult.GRANTED;
    }

    public static void Vx_(Activity activity, OpenBlueToothAction openBlueToothAction) {
        if (activity == null) {
            LogUtil.b("PermissionDialogHelper", "openBlueTooth the activity is null!");
        } else if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.SCAN, new AnonymousClass5(activity, openBlueToothAction, activity));
        } else {
            LogUtil.a("PermissionDialogHelper", "openBlueTooth Phone is below to Android 12");
            openBlueToothAction.onPermissionGranted();
        }
    }

    /* renamed from: com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper$5, reason: invalid class name */
    public class AnonymousClass5 extends CustomPermissionAction {
        final /* synthetic */ Activity d;
        final /* synthetic */ OpenBlueToothAction e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Context context, OpenBlueToothAction openBlueToothAction, Activity activity) {
            super(context);
            this.e = openBlueToothAction;
            this.d = activity;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a("PermissionDialogHelper", "openBlueTooth doActionWithPermissions onGranted");
            this.e.onPermissionGranted();
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            LogUtil.a("PermissionDialogHelper", "openBlueTooth doActionWithPermissions onDenied");
            this.e.onPermissionDenied();
            nrh.b(this.d, R.string._2130846464_res_0x7f022300);
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            LogUtil.a("PermissionDialogHelper", "openBlueTooth doActionWithPermissions onForeverDenied");
            final OpenBlueToothAction openBlueToothAction = this.e;
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: div
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermissionDialogHelper.AnonymousClass5.VD_(PermissionDialogHelper.OpenBlueToothAction.this, view);
                }
            };
            final OpenBlueToothAction openBlueToothAction2 = this.e;
            onForeverDenied(permissionType, onClickListener, new View.OnClickListener() { // from class: diz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermissionDialogHelper.AnonymousClass5.VE_(PermissionDialogHelper.OpenBlueToothAction.this, view);
                }
            });
        }

        public static /* synthetic */ void VD_(OpenBlueToothAction openBlueToothAction, View view) {
            openBlueToothAction.onPermissionDenied();
            ViewClickInstrumentation.clickOnView(view);
        }

        public static /* synthetic */ void VE_(OpenBlueToothAction openBlueToothAction, View view) {
            openBlueToothAction.onPermissionDenied();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static void VA_(final Activity activity, final int i) {
        Vx_(activity, new OpenBlueToothAction() { // from class: com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.4
            @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
            public void onPermissionGranted() {
                try {
                    activity.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), i);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("PermissionDialogHelper", "openBlueTooth from activity ActivityNotFoundException!");
                }
            }

            @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
            public void onPermissionDenied() {
                LogUtil.b("PermissionDialogHelper", "openBlueTooth from activity onPermissionDenied!");
            }
        });
    }

    public static void e(final Fragment fragment, final int i) {
        if (fragment == null) {
            LogUtil.b("PermissionDialogHelper", "openBlueTooth from fragment the fragment is null!");
        } else {
            Vx_(fragment.getActivity(), new OpenBlueToothAction() { // from class: com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.1
                @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                public void onPermissionGranted() {
                    try {
                        Fragment.this.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), i);
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("PermissionDialogHelper", "openBlueTooth from fragment ActivityNotFoundException!");
                    }
                }

                @Override // com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper.OpenBlueToothAction
                public void onPermissionDenied() {
                    LogUtil.b("PermissionDialogHelper", "openBlueTooth from fragment onPermissionDenied!");
                }
            });
        }
    }
}
