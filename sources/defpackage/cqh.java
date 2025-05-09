package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.callback.ScaleDialogCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cqh;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cqh {
    private static cqh b;
    private static final Object c = new Object();
    private CustomAlertDialog d;
    private NoTitleCustomAlertDialog e;

    public static cqh c() {
        cqh cqhVar;
        synchronized (c) {
            if (b == null) {
                b = new cqh();
            }
            cqhVar = b;
        }
        return cqhVar;
    }

    public boolean KT_(Activity activity, String str) {
        if (activity == null || TextUtils.isEmpty(str)) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "checkPairStatusAndBluetooth activity is null or uniqueId is null");
            return false;
        }
        if (!(ceo.d().d(str, true) instanceof cxh)) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "checkPairStatusAndBluetooth pair invalid");
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                Lf_(activity, 1);
                return true;
            }
        }
        return false;
    }

    public void Lf_(final Activity activity, int i) {
        Dialog dialog;
        if (activity == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showOpenBluetoothSwitch context is null");
            return;
        }
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showOpenBluetoothSwitch dialogType: ", Integer.valueOf(i));
        if (i == 1) {
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
            builder.b(R.string.IDS_device_bluetooth_open).d(R.string.IDS_device_bluetooth_open_request).cyR_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: crb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.KF_(view);
                }
            }).cyU_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: crf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.this.KW_(activity, view);
                }
            });
            dialog = builder.a();
        } else if (i == 2) {
            String string = activity.getResources().getString(R.string.IDS_app_name_health);
            String string2 = activity.getResources().getString(R.string.IDS_device_bt_open_request_info);
            NoTitleCustomAlertDialog.Builder builder2 = new NoTitleCustomAlertDialog.Builder(activity);
            builder2.e(String.format(Locale.ENGLISH, string2, string)).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: crd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.this.KX_(activity, view);
                }
            }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: crg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.KG_(view);
                }
            });
            dialog = builder2.e();
        } else {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showOpenBluetoothSwitch else type");
            dialog = null;
        }
        if (dialog != null) {
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    static /* synthetic */ void KF_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showOpenBluetoothSwitch title NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void KW_(Activity activity, View view) {
        KY_(activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void KX_(Activity activity, View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showOpenBluetoothSwitch no title enable bluetooth");
        KY_(activity);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void KG_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showOpenBluetoothSwitch no title NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void KY_(Activity activity) {
        if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(activity, PermissionUtil.PermissionType.SCAN, new CustomPermissionAction(activity) { // from class: cqh.5
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    cqh.this.a();
                }
            });
        } else {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        iyl.d().d(new BtSwitchStateCallback() { // from class: cqn
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
            public final void onBtSwitchStateCallback(int i) {
                ReleaseLogUtil.e("PluginDevice_ScaleDialogHelper", "enable bluetooth result ", Integer.valueOf(i));
            }
        });
    }

    public void KZ_(final Activity activity) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showBluetoothGpsDialog enter");
        if (activity == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showBluetoothGpsDialog, context is null");
        } else {
            if (activity.isFinishing()) {
                LogUtil.h("PluginDevice_ScaleDialogHelper", "showBluetoothGpsDialog,activity is finishing");
                return;
            }
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(activity).e(R.string._2130844111_res_0x7f0219cf).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cqk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.Ku_(view);
                }
            }).czC_(R.string._2130844112_res_0x7f0219d0, new View.OnClickListener() { // from class: cqm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.Kv_(activity, view);
                }
            }).e();
            e.setCancelable(false);
            e.show();
        }
    }

    static /* synthetic */ void Ku_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showBluetoothGpsDialog negativeButton onclick");
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Kv_(Activity activity, View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showBluetoothGpsDialog setPositiveButton startActivity");
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        try {
            ctv.Mj_(activity, intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("PluginDevice_ScaleDialogHelper", "showBluetoothGpsDialog ActivityNotFoundException");
            intent.setAction("android.settings.SETTINGS");
            ctv.Mj_(activity, intent);
        }
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showBluetoothGpsDialog setPositiveButton startSystemActivity done");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void La_(final Activity activity) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showConfigNetworkGpsDialog enter");
        if (activity == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showConfigNetworkGpsDialog, context is null");
            return;
        }
        if (activity.isFinishing()) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showConfigNetworkGpsDialog,activity is finishing");
            return;
        }
        if (this.e == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
            builder.e(R.string.IDS_device_wifi_gps_service_prompt_msg).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: cqr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.Kw_(activity, view);
                }
            }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cqy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cqh.Kx_(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.e = e;
            e.setCancelable(false);
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog == null || noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        this.e.show();
    }

    static /* synthetic */ void Kw_(Activity activity, View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showConfigNetworkGpsDialog: click positive button");
        Intent intent = new Intent();
        intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
        try {
            ctv.Mj_(activity, intent);
        } catch (ActivityNotFoundException unused) {
            intent.setAction("android.settings.SETTINGS");
            ctv.Mj_(activity, intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Kx_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showConfigNetworkGpsDialog: click negative button");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c(final Context context) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showWifiSwitchEnableDialog enter");
        if (context == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showWifiEnableDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(Utils.o() ? R.string.IDS_device_wifi_open_over_sea : R.string.IDS_device_wifi_open_wifi_tip_msg).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cra
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KS_(view);
            }
        }).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.health.device.util.ScaleDialogHelper$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KP_(context, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void KS_(View view) {
        LogUtil.c("PluginDevice_ScaleDialogHelper", "showWifiEnableDialog() do nothing");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void KP_(Context context, View view) {
        Object systemService = context.getApplicationContext().getSystemService("wifi");
        if (systemService instanceof WifiManager) {
            if (((WifiManager) systemService).setWifiEnabled(true)) {
                LogUtil.a("PluginDevice_ScaleDialogHelper", "showWifiEnableDialog() wifi is open");
            } else {
                cub.k(context);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(Context context, final ScaleDialogCallback scaleDialogCallback) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showWifiSwitchEnableDialog callback enter");
        if (context == null || scaleDialogCallback == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showWifiSwitchEnableDialog callback or context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(Utils.o() ? R.string.IDS_device_wifi_open_over_sea : R.string.IDS_device_wifi_open_wifi_tip_msg).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cqi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KQ_(ScaleDialogCallback.this, view);
            }
        }).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: cqo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KR_(ScaleDialogCallback.this, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void KQ_(ScaleDialogCallback scaleDialogCallback, View view) {
        LogUtil.c("PluginDevice_ScaleDialogHelper", "showWifiSwitchEnableDialog() negative do nothing");
        scaleDialogCallback.operationResult(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void KR_(ScaleDialogCallback scaleDialogCallback, View view) {
        LogUtil.c("PluginDevice_ScaleDialogHelper", "showWifiSwitchEnableDialog() positive");
        scaleDialogCallback.operationResult(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d(Context context) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showOtaDisconnectDialog enter");
        if (context == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showOtaDisconnectDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(R.string.IDS_device_wifi_ota_prompt_msg);
        builder.czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: cqe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KL_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void KL_(View view) {
        LogUtil.h("PluginDevice_ScaleDialogHelper", "showOtaDisconnectDialog click positive");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(Context context, final ScaleDialogCallback scaleDialogCallback) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showWeightMeasureFailedDialog enter");
        if (context == null || scaleDialogCallback == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showWeightMeasureFailedDialog callback or context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(R.string.IDS_device_measure_fail);
        builder.czC_(R.string.IDS_device_permisson, new View.OnClickListener() { // from class: cqd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KM_(ScaleDialogCallback.this, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void KM_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void Lh_(Activity activity) {
        if (activity == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showWifiOtaActivationPromptDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(R.string.IDS_device_wifi_ota_prompt_msg).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: cre
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KN_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        if (e.isShowing() || activity.isFinishing()) {
            return;
        }
        e.show();
    }

    static /* synthetic */ void KN_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showWifiOtaActivationPromptDialog PositiveButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void Li_(Activity activity) {
        if (activity == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showWifiOtaGuideInactivePromptDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(R.string.IDS_device_wifi_ota_activation_prompt_msg).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: cqg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KO_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        if (e.isShowing() || activity.isFinishing()) {
            return;
        }
        e.show();
    }

    static /* synthetic */ void KO_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showWifiOtaGuideInactivePromptDialog PositiveButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void Lg_(final Activity activity) {
        if (activity == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showOpenPhoneWifiList context is null");
            return;
        }
        int i = nsn.ae(BaseApplication.getContext()) ? R.string.IDS_device_open_tablet_wifi : R.string.IDS_device_open_phone_wifi;
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(i).czC_(R.string.IDS_device_wifi_go_connect, new View.OnClickListener() { // from class: cqv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KH_(activity, view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cqw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KI_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        if (e.isShowing() || activity.isFinishing()) {
            return;
        }
        e.show();
    }

    static /* synthetic */ void KH_(Activity activity, View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showOpenPhoneWifiList click positive");
        try {
            activity.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
        } catch (ActivityNotFoundException unused) {
            LogUtil.a("PluginDevice_ScaleDialogHelper", "showOpenPhoneWifiList ActivityNotFoundException");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void KI_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showOpenPhoneWifiList click cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void Le_(Activity activity) {
        if (activity == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showLoadingFailDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(R.string.IDS_device_hagrid_loading_info_failed_prompt).czC_(R.string.IDS_device_measureactivity_result_confirm, new View.OnClickListener() { // from class: cqu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KE_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void KE_(View view) {
        LogUtil.a("PluginDevice_ScaleDialogHelper", "showLoadingFailDialog click");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void Ld_(Activity activity, final ScaleDialogCallback scaleDialogCallback) {
        if (activity == null || scaleDialogCallback == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showDeviceShareNotBelongUserDialog context is null");
            return;
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(BaseApplication.getContext().getString(R.string.IDS_device_bluetooth_open)).e(BaseApplication.getContext().getString(R.string.IDS_device_hygride_paired_fail_tips)).cyV_(BaseApplication.getContext().getString(R.string.IDS_device_hagrid_scale_use_guide_know_detail), new View.OnClickListener() { // from class: cqt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KC_(ScaleDialogCallback.this, view);
            }
        }).cyS_(BaseApplication.getContext().getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: cqs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KD_(ScaleDialogCallback.this, view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void KC_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void KD_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void Lc_(Activity activity, final ScaleDialogCallback scaleDialogCallback) {
        if (activity == null || scaleDialogCallback == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showDeviceBindInvalidDialog context is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
        builder.e(R.string.IDS_device_bluetooth_rebind_msg_new).czC_(R$string.IDS_btsdk_confirm_repair, new View.OnClickListener() { // from class: cqz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KA_(ScaleDialogCallback.this, view);
            }
        }).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: cqx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KB_(ScaleDialogCallback.this, view);
            }
        });
        builder.e().show();
    }

    static /* synthetic */ void KA_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void KB_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void Lb_(Activity activity, View view, final ScaleDialogCallback scaleDialogCallback) {
        if (activity == null || scaleDialogCallback == null || view == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showConfigureAutoUpgradeDialog context is null");
            return;
        }
        CustomAlertDialog customAlertDialog = this.d;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            this.d.dismiss();
            LogUtil.h("PluginDevice_ScaleDialogHelper", "configureAutoUpgradeDialog is showing");
        }
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(activity);
        builder.cyp_(view).e(R.string.IDS_device_auto_upgrade_tip).cyo_(R.string.IDS_device_upgrade_now, new DialogInterface.OnClickListener() { // from class: cqq
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                cqh.this.KU_(scaleDialogCallback, dialogInterface, i);
            }
        }).cyn_(R.string.IDS_device_hygride_button_cancel, new DialogInterface.OnClickListener() { // from class: cqp
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                cqh.this.KV_(scaleDialogCallback, dialogInterface, i);
            }
        });
        CustomAlertDialog c2 = builder.c();
        this.d = c2;
        c2.setCancelable(false);
        this.d.show();
    }

    /* synthetic */ void KU_(ScaleDialogCallback scaleDialogCallback, DialogInterface dialogInterface, int i) {
        scaleDialogCallback.operationResult(0);
        this.d = null;
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* synthetic */ void KV_(ScaleDialogCallback scaleDialogCallback, DialogInterface dialogInterface, int i) {
        scaleDialogCallback.operationResult(1);
        this.d = null;
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public void d(Context context, final ScaleDialogCallback scaleDialogCallback) {
        if (context == null || scaleDialogCallback == null) {
            LogUtil.h("PluginDevice_ScaleDialogHelper", "showOpenSystemMessageSwitch context is null");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(R.string._2130841240_res_0x7f020e98).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: cql
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KJ_(ScaleDialogCallback.this, view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cqj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.KK_(ScaleDialogCallback.this, view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void KJ_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void KK_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(Context context, final ScaleDialogCallback scaleDialogCallback) {
        String string;
        String string2;
        if (Utils.o()) {
            string = context.getString(R.string._2130840662_res_0x7f020c56);
            string2 = context.getString(R.string.IDS_device_wifi_tip_over_sea);
        } else {
            string = context.getString(R.string._2130840661_res_0x7f020c55);
            string2 = context.getString(R.string.IDS_device_request_auto_sync_data_open_wifi_tip_msg);
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(string).e(string2).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: crc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.Ky_(ScaleDialogCallback.this, view);
            }
        }).cyU_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: cqf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cqh.Kz_(ScaleDialogCallback.this, view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    static /* synthetic */ void Ky_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Kz_(ScaleDialogCallback scaleDialogCallback, View view) {
        scaleDialogCallback.operationResult(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b() {
        e();
    }

    private static void e() {
        synchronized (c) {
            b = null;
        }
    }
}
