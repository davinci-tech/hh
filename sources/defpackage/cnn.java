package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class cnn {

    /* renamed from: a, reason: collision with root package name */
    private int f798a;
    private String b;
    private WeakReference<Dialog> c;
    private String d;
    private boolean e;
    private cmv g;
    private cld h;

    private cnn() {
        this.e = false;
        this.f798a = -1;
        this.c = new WeakReference<>(null);
    }

    public static cnn b() {
        return a.c;
    }

    public void b(String str, String str2, cld cldVar) {
        this.d = str;
        this.b = str2;
        this.h = cldVar;
    }

    public void c(cmv cmvVar) {
        this.g = cmvVar;
    }

    public void e(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        if (nsn.o()) {
            LogUtil.h("SetUnitManager", "clickRequestDeviceShareItem click too fast.");
            return;
        }
        if (!g()) {
            LogUtil.a("SetUnitManager", "is not ble device");
            hagridDeviceManagerFragment.showSelectBindDeviceDialog();
        } else if (i()) {
            this.e = true;
            c();
        } else {
            nrh.b(BaseApplication.getContext(), R.string.IDS_plugin_device_weight_device_not_connect);
        }
    }

    private boolean g() {
        return ceo.d().d(this.d, true) instanceof cxh;
    }

    private void f() {
        Activity activity = BaseApplication.getActivity();
        if (activity instanceof DeviceMainActivity) {
            ((DeviceMainActivity) activity).a(this.b);
        }
    }

    private boolean i() {
        cld cldVar = this.h;
        if (cldVar != null) {
            return cldVar.b();
        }
        return false;
    }

    private void c() {
        LogUtil.a("SetUnitManager", "getUnitFromController mUnitType=", Integer.valueOf(this.f798a));
        if (i()) {
            EventBus.d(new EventBus.b("get_weight_unit"));
            return;
        }
        if (this.f798a == -1) {
            this.f798a = 1;
        }
        this.g.sendEmptyMessage(4);
    }

    public void d(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory() || !hagridDeviceManagerFragment.isAdded()) {
            LogUtil.h("SetUnitManager", "processHandlerMgsGetWeightUnit fragment is destroy");
            return;
        }
        if (this.e && this.f798a != -1) {
            a(hagridDeviceManagerFragment);
        }
        this.e = false;
    }

    private void a(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        Dialog dialog = this.c.get();
        if (dialog != null && dialog.isShowing()) {
            LogUtil.h("SetUnitManager", "showPickerDialog() dialog isShowing.");
            return;
        }
        Activity mainActivity = hagridDeviceManagerFragment.getMainActivity();
        if (mainActivity == null) {
            LogUtil.h("SetUnitManager", "settingUnitDialog, mainActivity is null");
            return;
        }
        LogUtil.a("SetUnitManager", "showGenderPickerDialog()");
        if (mainActivity.getSystemService("layout_inflater") instanceof LayoutInflater) {
            View inflate = ((LayoutInflater) mainActivity.getSystemService("layout_inflater")).inflate(R.layout.hw_show_settings_weight_unit_view, (ViewGroup) null);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(mainActivity);
            builder.a(mainActivity.getResources().getString(R.string.IDS_hw_device_body_scale_show_unit)).czg_(inflate).czc_(R.string._2130841939_res_0x7f021153, new View.OnClickListener() { // from class: cnp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    cnn.Jh_(view);
                }
            });
            CustomViewDialog e = builder.e();
            if (!Jg_(inflate)) {
                LogUtil.c("SetUnitManager", "showPickerDialog() dialog layout fail");
                this.c = new WeakReference<>(null);
                return;
            } else {
                e.show();
                this.c = new WeakReference<>(e);
                return;
            }
        }
        LogUtil.h("SetUnitManager", "inflater not instanceof LayoutInflater");
    }

    static /* synthetic */ void Jh_(View view) {
        LogUtil.a("SetUnitManager", "settingUnitDialog NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean Jg_(View view) {
        LogUtil.a("SetUnitManager", "initializeDialogLayout()");
        if (view == null) {
            return false;
        }
        final HealthRadioButton healthRadioButton = (HealthRadioButton) nsy.cMd_(view, R.id.settings_weight_unit_imgview1);
        final HealthRadioButton healthRadioButton2 = (HealthRadioButton) nsy.cMd_(view, R.id.settings_weight_unit_imgview2);
        final HealthRadioButton healthRadioButton3 = (HealthRadioButton) nsy.cMd_(view, R.id.settings_weight_unit_imgview3);
        View cMd_ = nsy.cMd_(view, R.id.settings_weight_unit_view_layout1);
        View cMd_2 = nsy.cMd_(view, R.id.settings_weight_unit_view_layout2);
        View cMd_3 = nsy.cMd_(view, R.id.settings_weight_unit_view_layout3);
        cMd_.setOnClickListener(new View.OnClickListener() { // from class: cnl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                cnn.this.Jj_(healthRadioButton, healthRadioButton2, healthRadioButton3, view2);
            }
        });
        cMd_2.setOnClickListener(new View.OnClickListener() { // from class: cnr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                cnn.this.Jk_(healthRadioButton, healthRadioButton2, healthRadioButton3, view2);
            }
        });
        cMd_3.setOnClickListener(new View.OnClickListener() { // from class: cno
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                cnn.this.Jl_(healthRadioButton, healthRadioButton2, healthRadioButton3, view2);
            }
        });
        Jf_(view, healthRadioButton, healthRadioButton2, healthRadioButton3);
        return true;
    }

    /* synthetic */ void Jj_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3, View view) {
        LogUtil.a("SetUnitManager", "set unit WEIGHT_UNIT_G");
        healthRadioButton.setChecked(true);
        healthRadioButton2.setChecked(false);
        healthRadioButton3.setChecked(false);
        if (this.c.get() != null) {
            this.c.get().dismiss();
        }
        a(3);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void Jk_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3, View view) {
        LogUtil.a("SetUnitManager", "set unit WEIGHT_UNIT_KG");
        healthRadioButton.setChecked(false);
        healthRadioButton2.setChecked(true);
        healthRadioButton3.setChecked(false);
        if (this.c.get() != null) {
            this.c.get().dismiss();
        }
        a(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void Jl_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3, View view) {
        LogUtil.a("SetUnitManager", "set unit WEIGHT_UNIT_LB");
        healthRadioButton.setChecked(false);
        healthRadioButton2.setChecked(false);
        healthRadioButton3.setChecked(true);
        if (this.c.get() != null) {
            this.c.get().dismiss();
        }
        a(2);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void Jf_(View view, HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.settings_weight_unit_g_layout);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.settings_weight_unit_lb_layout);
        boolean d = d(this.d);
        LogUtil.a("SetUnitManager", "not supported weight unit : ", Boolean.valueOf(d));
        if (d) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(8);
            d(1, healthRadioButton, healthRadioButton2, healthRadioButton3);
            return;
        }
        if (UnitUtil.h()) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(0);
        } else if (Utils.o()) {
            linearLayout.setVisibility(8);
            linearLayout2.setVisibility(0);
        } else {
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(0);
        }
        d(this.f798a, healthRadioButton, healthRadioButton2, healthRadioButton3);
    }

    private boolean d(String str) {
        String d = knl.d(str);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("weightUnit");
        stringBuffer.append(d);
        return CommonUtil.a(cvx.a(KeyValDbManager.b(com.huawei.haf.application.BaseApplication.e()).e(stringBuffer.toString())), 2);
    }

    private void d(int i, HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3) {
        LogUtil.a("SetUnitManager", "refreshGenderImageView unitType:", Integer.valueOf(i));
        if (i == 1) {
            healthRadioButton.setChecked(false);
            healthRadioButton2.setChecked(true);
            healthRadioButton3.setChecked(false);
        } else if (i == 2) {
            healthRadioButton.setChecked(false);
            healthRadioButton2.setChecked(false);
            healthRadioButton3.setChecked(true);
        } else {
            healthRadioButton.setChecked(true);
            healthRadioButton2.setChecked(false);
            healthRadioButton3.setChecked(false);
        }
    }

    public void Jn_(Bundle bundle) {
        int i = bundle.getInt("ret");
        LogUtil.a("SetUnitManager", "set weight value ", Integer.valueOf(i));
        if (i == 0) {
            Ji_(bundle);
        }
    }

    private void Ji_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("SetUnitManager", "illegal argument, processSetWeightUnitResult bundle is null");
            return;
        }
        int i = bundle.getInt("weightUnit");
        String a2 = a();
        LogUtil.a("SetUnitManager", "processSetWeightUnitResult unitType:", Integer.valueOf(i));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), a2, String.valueOf(i), (StorageParams) null);
        cgt.e().r();
        if (this.g == null) {
            LogUtil.h("SetUnitManager", "EVEBUS_SET_WEIGHT_UNIT_RESULT onEvent myHandler is null.");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 14;
        obtain.arg1 = i;
        this.g.sendMessage(obtain);
    }

    public void Jm_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("SetUnitManager", "illegal argument, processGetWeightUnitResult bundle is null");
            return;
        }
        int i = bundle.getInt("weightUnit", 1);
        this.f798a = i;
        LogUtil.a("SetUnitManager", "get weight unit result unitType:", Integer.valueOf(i));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), a(), String.valueOf(i), (StorageParams) null);
        cgt.e().r();
        cmv cmvVar = this.g;
        if (cmvVar == null) {
            LogUtil.h("SetUnitManager", "EVEBUS_GET_WEIGHT_UNIT_RESULT onEvent myHandler is null.");
            return;
        }
        cmvVar.sendEmptyMessage(4);
        Message obtain = Message.obtain();
        obtain.what = 14;
        obtain.arg1 = i;
        this.g.sendMessage(obtain);
    }

    private String a() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("weightUnit");
        if (!TextUtils.isEmpty(this.d)) {
            stringBuffer.append(this.d);
        }
        return stringBuffer.toString();
    }

    private void a(int i) {
        if (!g()) {
            LogUtil.a("SetUnitManager", "is not ble device");
            f();
        } else {
            if (i()) {
                this.f798a = i;
                Bundle bundle = new Bundle();
                bundle.putInt("weightUnit", i);
                EventBus.d(new EventBus.b("set_weight_unit", bundle));
                return;
            }
            nrh.c(BaseApplication.getContext(), R.string.IDS_plugin_device_weight_device_not_connect);
        }
    }

    public void c(ctk ctkVar) {
        if (ctkVar == null) {
            LogUtil.h("R_Weight_SetUnitManager", "getUnit mWiFiDevice is null");
        } else {
            csf.c(ctkVar.d(), new ICloudOperationResult() { // from class: cns
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    cnn.this.e((CloudCommonReponse) obj, str, z);
                }
            });
        }
    }

    /* synthetic */ void e(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (z && (cloudCommonReponse instanceof WifiDeviceServiceInfoRsp)) {
            c((WifiDeviceServiceInfoRsp) cloudCommonReponse);
        } else if (cloudCommonReponse != null) {
            LogUtil.h("SetUnitManager", "getWeightDeviceUnit fail", cloudCommonReponse.getResultCode());
        } else {
            LogUtil.h("R_Weight_SetUnitManager", "getWeightDeviceUnit cloudCommonResponse is null");
        }
    }

    private void c(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp) {
        List<DeviceServiceInfo> deviceServiceInfo = wifiDeviceServiceInfoRsp.getDeviceServiceInfo();
        if (deviceServiceInfo != null && deviceServiceInfo.size() > 0) {
            Iterator<DeviceServiceInfo> it = deviceServiceInfo.iterator();
            while (it.hasNext()) {
                Map<String, String> data = it.next().getData();
                if (data != null && data.containsKey("weightUnit")) {
                    e(data.get("weightUnit"));
                }
            }
            return;
        }
        LogUtil.h("R_Weight_SetUnitManager", "getUnitResult deviceServiceInfos is null");
    }

    private void e(String str) {
        if (String.valueOf(1).equals(str)) {
            this.f798a = 1;
        } else if (String.valueOf(3).equals(str)) {
            this.f798a = 3;
        } else if (String.valueOf(2).equals(str)) {
            this.f798a = 2;
        } else {
            LogUtil.h("SetUnitManager", "setUnitType unit is error", str);
        }
        LogUtil.a("SetUnitManager", "setUnitType mUnitType:", Integer.valueOf(this.f798a));
    }

    public int e() {
        int i = 0;
        try {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), a());
            LogUtil.a("SetUnitManager", "initUnitType weightUnit:", b);
            i = !TextUtils.isEmpty(b) ? Integer.parseInt(b) : -1;
            this.f798a = i;
        } catch (NumberFormatException unused) {
            LogUtil.b("SetUnitManager", "initUnitType, NumberFormatException");
        }
        return i;
    }

    public void d() {
        this.e = false;
    }

    static class a {
        private static final cnn c = new cnn();
    }
}
