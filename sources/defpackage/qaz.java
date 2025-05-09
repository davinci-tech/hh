package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateGuideActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.adapter.MultiDeviceslistAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class qaz {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16373a;
    private Context b;
    private ListView f;
    private CustomAlertDialog g;
    private CustomAlertDialog.Builder i;
    private MultiDeviceslistAdapter j;
    private static ArrayList<dcz> d = new ArrayList<>();
    private static ArrayList<cjv> c = new ArrayList<>();
    private static final String[] e = {"e4b0b1d5-2003-4d88-8b5f-c4f64542040b", "a8ba095d-4123-43c4-a30a-0240011c58de"};

    public qaz(Context context, boolean z) {
        this.b = context;
        this.f16373a = z;
        j();
    }

    private void j() {
        c(c);
    }

    private void a(HealthDevice.HealthDeviceKind healthDeviceKind) {
        LogUtil.a("PressureAdjustMultiDevicesDialog", "enter initDevicesList");
        c.clear();
        d.clear();
        ArrayList<String> a2 = cjx.e().a(healthDeviceKind);
        for (int i = 0; i < a2.size(); i++) {
            dcz d2 = ResourceManager.e().d(a2.get(i));
            if (d2 != null && a(d2.t()) && !d2.n().d().trim().isEmpty()) {
                d.add(d2);
            }
        }
        ArrayList<ctk> a3 = cjx.e().a();
        if (a3 != null && a3.size() > 0) {
            for (int i2 = 0; i2 < a3.size(); i2++) {
                ctk ctkVar = a3.get(i2);
                if (a(ctkVar.getProductId())) {
                    cjv cjvVar = new cjv();
                    cjvVar.a(0);
                    cjvVar.c(ctkVar);
                    c.add(cjvVar);
                }
            }
            return;
        }
        LogUtil.h("PressureAdjustMultiDevicesDialog", "WiFiDevice is null or WiFiDevice size is 0");
    }

    private void c(List<cjv> list) {
        if (g()) {
            LogUtil.a("PressureAdjustMultiDevicesDialog", "initMultiUsersListDialog()");
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.pressure_multi_devices_list_layout, (ViewGroup) null);
            this.f = (ListView) inflate.findViewById(R.id.pressure_multi_devices_list);
            MultiDeviceslistAdapter multiDeviceslistAdapter = new MultiDeviceslistAdapter(this.b, list);
            this.j = multiDeviceslistAdapter;
            this.f.setAdapter((ListAdapter) multiDeviceslistAdapter);
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.b);
            this.i = builder;
            builder.e(R$string.IDS_device_wifi_pressure_calibrate_multi_devices_title).cyp_(inflate).cyo_(R$string.IDS_settings_button_cancal, new b()).e(false);
            this.g = this.i.c();
            this.f.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: qaz.5
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    qaz.this.d(i);
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("PressureAdjustMultiDevicesDialog", "select MultiUsers position = ", Integer.valueOf(i));
        this.j.notifyDataSetChanged();
        cjv item = this.j.getItem(i);
        if (item != null) {
            if (item.a() == 1) {
                qba.b(this.b, new IBaseResponseCallback() { // from class: qaz.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        Intent intent = new Intent(qaz.this.b, (Class<?>) PressureCalibrateActivity.class);
                        intent.putExtra("pressure_is_have_datas", qaz.this.f16373a);
                        gnm.aPB_(qaz.this.b, intent);
                    }
                });
            } else if (item.a() == 0) {
                try {
                    dcz d2 = ResourceManager.e().d(((ctk) item.i()).getProductId());
                    if (d2 != null) {
                        e(d2.t());
                    } else {
                        LogUtil.h("PressureAdjustMultiDevicesDialog", "healthDeviceProductInfo is null");
                    }
                } catch (ClassCastException e2) {
                    LogUtil.b("PressureAdjustMultiDevicesDialog", "Object to WiFiDevice ClassCastException:", e2.getMessage());
                }
            } else {
                LogUtil.a("PressureAdjustMultiDevicesDialog", "getDeviceType not match");
            }
        }
        if (this.g.isShowing()) {
            this.g.dismiss();
        }
    }

    public void d() {
        CustomAlertDialog customAlertDialog;
        if (g() && c.size() > 0 && (customAlertDialog = this.g) != null && !customAlertDialog.isShowing()) {
            this.i.a(e());
            this.g.show();
        }
    }

    public boolean c() {
        a(HealthDevice.HealthDeviceKind.HDK_UNKNOWN);
        DeviceInfo a2 = jpt.a("PressureAdjustMultiDevicesDialog");
        if (a2 != null && 2 == a2.getDeviceConnectState()) {
            if (cvs.d() != null && cvs.d().isSupportPressAutoMonitor()) {
                cjv cjvVar = new cjv();
                cjvVar.a(1);
                cjvVar.c(a2);
                c.add(cjvVar);
            } else {
                LogUtil.h("PressureAdjustMultiDevicesDialog", "Wear device not support pressure adjust");
            }
        } else {
            LogUtil.h("PressureAdjustMultiDevicesDialog", "No exist wear device or device is not connected");
        }
        LogUtil.a("PressureAdjustMultiDevicesDialog", "isShowMultiDevicesList productInfos size is " + c.size());
        if (c.size() < 2) {
            return false;
        }
        this.j.a(c);
        return true;
    }

    public boolean b() {
        return !koq.b(d);
    }

    public void a() {
        if (koq.b(d)) {
            return;
        }
        Intent intent = new Intent(this.b, (Class<?>) WifiDevicePressureCalibrateGuideActivity.class);
        intent.putExtra("health_wifi_device_userId", MultiUsersManager.INSTANCE.getMainUser().i());
        dcz dczVar = d.get(0);
        if (dczVar != null) {
            cpw.a(false, "PressureAdjustMultiDevicesDialog", "health_wifi_device_productId = " + dczVar.t());
            intent.putExtra("health_wifi_device_productId", dczVar.t());
            gnm.aPB_(this.b, intent);
            return;
        }
        cpw.d(false, "PressureAdjustMultiDevicesDialog", "startToPressureAdjustByWifiDevicedeviceInfo is null ");
    }

    private void e(String str) {
        Intent intent = new Intent(this.b, (Class<?>) WifiDevicePressureCalibrateGuideActivity.class);
        intent.putExtra("health_wifi_device_userId", MultiUsersManager.INSTANCE.getMainUser().i());
        intent.putExtra("health_wifi_device_productId", str);
        cpw.a(false, "PressureAdjustMultiDevicesDialog", "health_wifi_device_productId = " + str + ", userid = " + MultiUsersManager.INSTANCE.getMainUser().i());
        gnm.aPB_(this.b, intent);
    }

    private int e() {
        int Va_ = dij.Va_(this.f);
        int height = ((WindowManager) this.b.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getHeight() / 2;
        if (Va_ >= height) {
            Va_ = height;
        }
        return Va_ + (c.size() - 1);
    }

    private boolean a(String str) {
        for (String str2 : e) {
            if (str2.equals(str)) {
                LogUtil.a("PressureAdjustMultiDevicesDialog", "isSupportPressureDevcie()=true ", "productType = ", str);
                return true;
            }
        }
        LogUtil.a("PressureAdjustMultiDevicesDialog", "isSupportPressureDevcie()=false ", "productType = ", str);
        return false;
    }

    private boolean g() {
        Context context = this.b;
        if (!(context instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) context;
        return (activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    static class b implements DialogInterface.OnClickListener {
        private b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }
}
