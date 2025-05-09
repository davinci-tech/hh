package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbtsdk.btcommon.BluetoothSwitchStateUtil;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity;
import java.util.Locale;

/* loaded from: classes7.dex */
public class rho {

    /* renamed from: a, reason: collision with root package name */
    private ServiceItemActivity f16766a;
    private String b;
    private final BtSwitchStateCallback c = new BtSwitchStateCallback() { // from class: rho.3
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i) {
            if (rho.this.d != null) {
                rho.this.d.c(rho.this.c);
                LogUtil.a("DeleteDeviceDialogUtils", "onBtSwitchStateCallback bluetoothSwitchState:", Integer.valueOf(i));
                if (i == 3) {
                    rho.this.d();
                    return;
                }
                return;
            }
            LogUtil.h("DeleteDeviceDialogUtils", "mBluetoothSwitchStateUtil is null");
        }
    };
    private BluetoothSwitchStateUtil d;
    private NoTitleCustomAlertDialog e;
    private NoTitleCustomAlertDialog f;
    private NoTitleCustomAlertDialog i;
    private String j;

    public rho(String str, String str2, ServiceItemActivity serviceItemActivity) {
        this.b = str;
        this.f16766a = serviceItemActivity;
        this.j = str2;
        this.d = new BluetoothSwitchStateUtil(this.f16766a);
    }

    public void c() {
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f16766a).e(this.f16766a.getResources().getString(R$string.IDS_device_bluetooth_open_request)).czC_(R$string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: rhu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    rho.this.dOw_(view);
                }
            }).czz_(R$string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: rhq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    rho.this.dOx_(view);
                }
            }).e();
            this.e = e;
            e.setCancelable(false);
            this.e.show();
            return;
        }
        d();
    }

    /* synthetic */ void dOw_(View view) {
        LogUtil.a("DeleteDeviceDialogUtils", "checkBluetoothStatus, open bluetooth");
        BluetoothSwitchStateUtil bluetoothSwitchStateUtil = this.d;
        if (bluetoothSwitchStateUtil == null) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            bluetoothSwitchStateUtil.d(this.c);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void dOx_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        noTitleCustomAlertDialog.dismiss();
        this.e = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("DeleteDeviceDialogUtils", "Enter deleteDevice");
        if (jgf.b().a(this.b)) {
            a();
        } else {
            b();
        }
    }

    private void a() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f16766a).e(this.f16766a.getResources().getString(R$string.IDS_device_ota_later_note)).czC_(R$string.IDS_user_permission_know, new View.OnClickListener() { // from class: rht
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                rho.dOu_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void dOu_(View view) {
        LogUtil.a("DeleteDeviceDialogUtils", "showTipDialog click");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        jqi.a().getSwitchSetting("intelligent_home_linkage", this.b, new IBaseResponseCallback() { // from class: rho.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                String str;
                boolean z = false;
                if (i == 0 && (obj instanceof String)) {
                    String str2 = (String) obj;
                    if (str2.contains("&&")) {
                        String[] split = str2.split("&&");
                        LogUtil.a("DeleteDeviceDialogUtils", "getIntelligentStatus splits length is ", Integer.valueOf(split.length));
                        if (split.length == 5) {
                            str = split[0];
                            z = true;
                            LogUtil.a("DeleteDeviceDialogUtils", "getIntelligentStatus isEnable = ", Boolean.valueOf(z));
                            rho.this.e(z, str);
                        }
                    }
                }
                str = "";
                LogUtil.a("DeleteDeviceDialogUtils", "getIntelligentStatus isEnable = ", Boolean.valueOf(z));
                rho.this.e(z, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final boolean z, final String str) {
        this.f16766a.runOnUiThread(new Runnable() { // from class: rho.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("DeleteDeviceDialogUtils", "updateIntelligentStatus enter");
                if (z) {
                    rho.this.c(str);
                } else {
                    rho.this.i();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str) {
        LogUtil.a("DeleteDeviceDialogUtils", "enter showUnbindIntelligentDialog");
        if (this.i == null) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f16766a).e(String.format(Locale.ENGLISH, this.f16766a.getString(R$string.IDS_delete_device_to_intelligent), this.j, this.f16766a.getString(R$string.IDS_app_name_health_1), this.f16766a.getString(R$string.IDS_device_intelligent_home))).czC_(R$string.IDS_unbind_device_wear_home_unpair, new View.OnClickListener() { // from class: rhs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    rho.this.dOz_(str, view);
                }
            }).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rhr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    rho.this.dOA_(view);
                }
            }).e();
            this.i = e;
            e.setCancelable(false);
            this.i.show();
        }
    }

    /* synthetic */ void dOz_(String str, View view) {
        LogUtil.c("DeleteDeviceDialogUtils", "showUnbindIntelligentDialog ok click");
        e(str);
        this.i.dismiss();
        this.i = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void dOA_(View view) {
        LogUtil.c("DeleteDeviceDialogUtils", "showUnbindIntelligentDialog cancel click");
        this.i.dismiss();
        this.i = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(String str) {
        jgf.b().b(str);
        this.f16766a.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("DeleteDeviceDialogUtils", "enter showUnbindDialog");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f16766a).e(String.format(Locale.ROOT, this.f16766a.getResources().getString(R$string.IDS_delete_device_tips), this.j, this.f16766a.getString(R$string.IDS_app_name_health_1))).czC_(R$string.IDS_unbind_device_wear_home_unpair, new View.OnClickListener() { // from class: rhn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                rho.this.dOy_(view);
            }
        }).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rhm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                rho.dOv_(view);
            }
        }).e();
        this.f = e;
        e.setCancelable(false);
        this.f.show();
    }

    /* synthetic */ void dOy_(View view) {
        LogUtil.a("DeleteDeviceDialogUtils", "showUnbindDialog ok click");
        e((String) null);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dOv_(View view) {
        LogUtil.a("DeleteDeviceDialogUtils", "showUnbindDialog cancel click");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e() {
        BluetoothSwitchStateUtil bluetoothSwitchStateUtil = this.d;
        if (bluetoothSwitchStateUtil != null) {
            bluetoothSwitchStateUtil.b();
        }
    }
}
