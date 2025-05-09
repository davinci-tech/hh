package com.huawei.ui.device.activity.update;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.views.device.RoundProgressImageView;
import defpackage.ceo;
import defpackage.cfl;
import defpackage.cgk;
import defpackage.cgt;
import defpackage.ciy;
import defpackage.cjx;
import defpackage.cld;
import defpackage.cpa;
import defpackage.dcz;
import defpackage.ddh;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oaj;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WeightDeviceOtaActivity extends BaseActivity implements View.OnClickListener {
    private static int d = 50;

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9266a;
    private String aa;
    private HealthTextView ab;
    private int ac;
    private LinearLayout ad;
    private b ag;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView e;
    private File f;
    private HealthButton i;
    private ContentValues j;
    private boolean k;
    private ImageView m;
    private boolean p;
    private LinearLayout q;
    private oaj r;
    private LinearLayout s;
    private boolean t;
    private RoundProgressImageView u;
    private RelativeLayout v;
    private HealthTextView w;
    private HealthTextView x;
    private String y;
    private HealthTextView z;
    private Context g = null;
    private PowerManager.WakeLock ah = null;
    private boolean l = false;
    private boolean n = false;
    private EventBus.ICallback h = new EventBus.ICallback() { // from class: com.huawei.ui.device.activity.update.WeightDeviceOtaActivity.5
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            Intent Km_ = bVar.Km_();
            if (Km_ == null) {
                LogUtil.h("DeviceOtaActivity", "update intent == null");
                return;
            }
            if ("upgrade_update_status".equals(bVar.e())) {
                int intExtra = Km_.getIntExtra("update_status", 0);
                if (intExtra == 0) {
                    if (!WeightDeviceOtaActivity.this.l) {
                        WeightDeviceOtaActivity.this.f();
                        LogUtil.a("DeviceOtaActivity", "update success.");
                        return;
                    } else {
                        LogUtil.h("DeviceOtaActivity", "is already failed,do not update pages.");
                        return;
                    }
                }
                if (intExtra == 2) {
                    LogUtil.h("DeviceOtaActivity", "update fail. CS error");
                    WeightDeviceOtaActivity.this.c(2);
                    return;
                } else {
                    LogUtil.h("DeviceOtaActivity", "update fail");
                    WeightDeviceOtaActivity.this.a(intExtra);
                    return;
                }
            }
            if ("upgrade_update_progress".equals(bVar.e())) {
                if (!WeightDeviceOtaActivity.this.l) {
                    oaj oajVar = WeightDeviceOtaActivity.this.r;
                    oaj unused = WeightDeviceOtaActivity.this.r;
                    oajVar.e(11);
                    WeightDeviceOtaActivity.this.d(Km_.getIntExtra("update_progress", 0));
                    return;
                }
                LogUtil.h("DeviceOtaActivity", "mEventCallback is already failed");
                return;
            }
            if ("set_scale_version_code".equals(bVar.e())) {
                WeightDeviceOtaActivity.this.l = false;
                WeightDeviceOtaActivity.this.m();
            } else {
                LogUtil.h("DeviceOtaActivity", "no action: ", bVar.e());
            }
        }
    };
    private IHealthDeviceCallback o = new IHealthDeviceCallback() { // from class: com.huawei.ui.device.activity.update.WeightDeviceOtaActivity.1
        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
            LogUtil.a("DeviceOtaActivity", "onDataChanged");
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
            LogUtil.a("DeviceOtaActivity", "onDataChanged list");
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
            LogUtil.a("DeviceOtaActivity", "onProgressChanged");
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
            WeightDeviceOtaActivity.this.a(healthDevice, i);
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
            LogUtil.h("DeviceOtaActivity", "onFailed");
        }
    };

    static class b extends Handler {
        WeakReference<WeightDeviceOtaActivity> b;

        b(WeightDeviceOtaActivity weightDeviceOtaActivity) {
            this.b = new WeakReference<>(weightDeviceOtaActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeightDeviceOtaActivity weightDeviceOtaActivity = this.b.get();
            if (weightDeviceOtaActivity == null) {
                LogUtil.h("DeviceOtaActivity", "handleMessage activity is null");
                return;
            }
            int i = message.what;
            if (i == 4) {
                if (message.arg1 >= 100) {
                    message.arg1 = 99;
                }
                weightDeviceOtaActivity.e(message.arg1);
            } else {
                if (i != 5) {
                    if (i == 6) {
                        LogUtil.a("DeviceOtaActivity", "MSG_UPGRADE_FAILED msg.arg1: ", Integer.valueOf(message.arg1));
                        weightDeviceOtaActivity.e(cTa_(message, weightDeviceOtaActivity));
                        return;
                    } else {
                        LogUtil.h("DeviceOtaActivity", "else msg");
                        return;
                    }
                }
                if (!weightDeviceOtaActivity.l) {
                    weightDeviceOtaActivity.r.b();
                    weightDeviceOtaActivity.h();
                    LogUtil.a("DeviceOtaActivity", "MSG_UPGRADE_SUCCESS");
                    return;
                }
                LogUtil.a("DeviceOtaActivity", "is already failed");
            }
        }

        private String cTa_(Message message, WeightDeviceOtaActivity weightDeviceOtaActivity) {
            int i = message.arg1;
            if (i == 1) {
                return String.format(Locale.ENGLISH, weightDeviceOtaActivity.g.getString(R.string._2130841489_res_0x7f020f91), oaj.a().l());
            }
            if (i == 1002) {
                return weightDeviceOtaActivity.g.getString(R.string._2130841490_res_0x7f020f92);
            }
            if (i == 4) {
                LogUtil.a("DeviceOtaActivity", "battery: ", UnitUtil.e(WeightDeviceOtaActivity.d, 2, 0));
                return weightDeviceOtaActivity.g.getResources().getString(R.string._2130843428_res_0x7f021724);
            }
            if (i != 5) {
                switch (i) {
                    case 104007:
                        return weightDeviceOtaActivity.g.getString(R.string._2130841439_res_0x7f020f5f);
                    case 104008:
                        return weightDeviceOtaActivity.g.getString(R.string.IDS_device_wifi_ota_activation_prompt_msg);
                    default:
                        return weightDeviceOtaActivity.g.getString(R.string._2130841496_res_0x7f020f98);
                }
            }
            return weightDeviceOtaActivity.g.getString(R.string._2130841493_res_0x7f020f95);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthDevice healthDevice, int i) {
        if (isFinishing()) {
            LogUtil.h("DeviceOtaActivity", "activity has finished");
            return;
        }
        Object[] objArr = new Object[4];
        objArr[0] = "onStatusChanged ";
        objArr[1] = healthDevice == null ? "" : healthDevice.getDeviceName();
        objArr[2] = ", status: ";
        objArr[3] = Integer.valueOf(i);
        LogUtil.a("DeviceOtaActivity", objArr);
        if (i == 3) {
            this.n = false;
            int s = this.r.s();
            oaj oajVar = this.r;
            if (s == 12 || oajVar.s() == 14) {
                LogUtil.a("DeviceOtaActivity", "no show fail view");
                return;
            }
            b bVar = this.ag;
            if (bVar == null || this.ac >= 100) {
                return;
            }
            Message obtainMessage = bVar.obtainMessage();
            obtainMessage.what = 6;
            obtainMessage.arg1 = 104007;
            this.ag.sendMessage(obtainMessage);
            return;
        }
        if (i != 2) {
            if (i == 14) {
                this.t = true;
                return;
            } else if (i == 13) {
                this.t = false;
                return;
            } else {
                LogUtil.h("DeviceOtaActivity", "onStatusChanged default");
                return;
            }
        }
        this.n = true;
        if (this.l) {
            LogUtil.a("DeviceOtaActivity", "is already failed, do nothing.");
            if (cpa.ah(this.y) || cpa.r(this.y)) {
                this.i.getBackground().setAlpha(150);
                this.i.setClickable(false);
                this.i.setTextColor(872113442);
                return;
            }
            return;
        }
        if (!cpa.ah(this.y)) {
            m();
        }
        b bVar2 = this.ag;
        if (bVar2 == null || this.ac >= 100) {
            return;
        }
        Message obtainMessage2 = bVar2.obtainMessage();
        obtainMessage2.what = 4;
        obtainMessage2.arg1 = 0;
        this.ag.sendMessage(obtainMessage2);
        this.r.e(-1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = this;
        LogUtil.a("DeviceOtaActivity", "onCreate()");
        Intent intent = getIntent();
        if (intent != null) {
            this.y = intent.getStringExtra("productId");
            ContentValues contentValues = (ContentValues) intent.getParcelableExtra("commonDeviceInfo");
            this.j = contentValues;
            if (contentValues != null) {
                this.aa = contentValues.getAsString("uniqueId");
            }
            if (TextUtils.isEmpty(this.y)) {
                LogUtil.h("DeviceOtaActivity", "mProductId is null");
                finish();
                return;
            }
            oaj a2 = oaj.a();
            this.r = a2;
            a2.g(this.y);
            this.p = intent.getBooleanExtra("isUpdateDialog", false);
            this.k = intent.getBooleanExtra("fromsetting", false);
            if (!this.r.e(this.r.m())) {
                LogUtil.h("DeviceOtaActivity", "Abnormal startup activity and otaFilePath is not exist.");
                finish();
                return;
            }
            this.ag = new b(this);
            setContentView(R.layout.activity_device_ota);
            e();
            ciy.c().b(true);
            b();
            g();
            EventBus.d(this.h, 0, "upgrade_update_status", "upgrade_update_progress", "set_scale_version_code");
            EventBus.d(new EventBus.b("get_scale_version_code"));
            return;
        }
        LogUtil.h("DeviceOtaActivity", "intent is null");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        oaj oajVar = this.r;
        if (oajVar != null) {
            oajVar.e(12);
            d(100);
            this.l = false;
            Message obtainMessage = this.ag.obtainMessage();
            obtainMessage.what = 5;
            this.ag.sendMessage(obtainMessage);
            if (this.f != null) {
                this.r.c();
                try {
                    c(this.f.getCanonicalPath());
                } catch (IOException unused) {
                    LogUtil.b("DeviceOtaActivity", "showSuccess IOException");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        int s = this.r.s();
        oaj oajVar = this.r;
        if (s == 12 || s == 14 || s == 11) {
            if (this.ac == 100 && s == 11) {
                o();
                return;
            } else {
                LogUtil.h("DeviceOtaActivity", "no startTransferOtaFile");
                return;
            }
        }
        if (this.ag == null || oajVar.r()) {
            return;
        }
        o();
    }

    private void b() {
        MeasurableDevice c;
        Bundle bundle = new Bundle();
        bundle.putInt("type", -1);
        bundle.putString("productId", this.y);
        if (!TextUtils.isEmpty(this.aa)) {
            c = ceo.d().d(this.aa, false);
        } else {
            c = ceo.d().c(this.y);
        }
        if (this.y == null) {
            LogUtil.h("DeviceOtaActivity", "getConnectedStatus error productId is null");
            return;
        }
        dcz d2 = ResourceManager.e().d(this.y);
        if (d2 == null) {
            LogUtil.h("DeviceOtaActivity", "getConnectedStatus error productInfo is null");
            return;
        }
        MeasureKit e = cfl.a().e(d2.s());
        if (e == null) {
            return;
        }
        MeasureController measureController = e.getMeasureController();
        if (measureController != null && c != null) {
            measureController.prepare(c, this.o, bundle);
        } else {
            LogUtil.h("DeviceOtaActivity", "getConnectedStatus() control or device is null.");
        }
    }

    private boolean c(String str) {
        String[] list;
        File file;
        File file2 = new File(str);
        if (!file2.exists() || !file2.isDirectory() || (list = file2.list()) == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < list.length; i++) {
            if (str.endsWith(File.separator)) {
                file = new File(str + list[i]);
            } else {
                file = new File(str + File.separator + list[i]);
            }
            if (file.isFile() && !file.delete()) {
                LogUtil.h("DeviceOtaActivity", "temp.delete() fail");
            }
            if (file.isDirectory()) {
                c(str + "/" + list[i]);
                a(str + "/" + list[i]);
                z = true;
            }
        }
        return z;
    }

    private void a(String str) {
        c(str);
        File file = new File(str);
        if (!file.exists() || file.delete()) {
            return;
        }
        LogUtil.h("DeviceOtaActivity", "myFilePath.delete() fail");
    }

    private void e() {
        LogUtil.a("DeviceOtaActivity", "Enter initView");
        this.l = false;
        this.u = (RoundProgressImageView) nsy.cMc_(this, R.id.center_ota_circle);
        this.v = (RelativeLayout) nsy.cMc_(this, R.id.rele_percent_ota);
        this.w = (HealthTextView) nsy.cMc_(this, R.id.text_percent);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.text_per_sign);
        this.x = healthTextView;
        healthTextView.setText("%");
        this.x.setVisibility(8);
        this.f9266a = (HealthTextView) nsy.cMc_(this, R.id.text_circle_tip);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.rela_ota_failed);
        this.s = linearLayout;
        linearLayout.setVisibility(8);
        this.b = (HealthTextView) nsy.cMc_(this, R.id.text_device_version_num);
        this.e = (HealthTextView) nsy.cMc_(this, R.id.text_device_version_size);
        this.ad = (LinearLayout) nsy.cMc_(this, R.id.lin_tip);
        this.ab = (HealthTextView) nsy.cMc_(this, R.id.text_tip);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.text_tip_content);
        this.m = (ImageView) nsy.cMc_(this, R.id.imageview_line);
        this.q = (LinearLayout) nsy.cMc_(this, R.id.lin_new_feature);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.text_new_feature_content);
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.button);
        this.i = healthButton;
        healthButton.setOnClickListener(this);
        ((CustomTitleBar) nsy.cMc_(this, R.id.update_title)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightDeviceOtaActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WeightDeviceOtaActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.z.setTextColor(this.g.getResources().getColor(R.color._2131297827_res_0x7f090623));
        String string = this.g.getString(R.string._2130841487_res_0x7f020f8f);
        if (nsn.ae(BaseApplication.getContext())) {
            string = this.g.getString(R.string._2130844349_res_0x7f021abd);
        }
        this.z.setText(String.format(Locale.ENGLISH, string, this.r.t()));
        this.c.setText(this.r.f());
        this.b.setText(this.r.h());
        this.e.setText(nsn.b(this.g, this.r.j()));
        d = 30;
        LogUtil.a("DeviceOtaActivity", "batteryPercent: ", 30);
        j();
        this.r.e(-1);
    }

    private void j() {
        this.v.setVisibility(0);
        this.ad.setVisibility(0);
        this.ab.setText(this.g.getString(R.string.IDS_service_area_notice_title));
        this.f9266a.setText(R.string._2130842348_res_0x7f0212ec);
        this.m.setVisibility(0);
        this.q.setVisibility(0);
        this.s.setVisibility(8);
        e(0);
        this.z.setTextColor(this.g.getResources().getColor(R.color._2131297827_res_0x7f090623));
        String string = this.g.getString(R.string._2130841487_res_0x7f020f8f);
        if (nsn.ae(BaseApplication.getContext())) {
            string = this.g.getString(R.string._2130844349_res_0x7f021abd);
        }
        this.z.setText(String.format(Locale.ENGLISH, string, this.r.t()));
        this.r.e(0);
        this.i.getBackground().setAlpha(150);
        this.i.setClickable(false);
        this.i.setTextColor(872113442);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        this.f9266a.setText(R.string._2130842348_res_0x7f0212ec);
        this.w.setText(UnitUtil.bCR_(this.g, "[\\d]", UnitUtil.e(i, 2, 0), R.style.percent_number_style_num, R.style.percent_number_style_sign));
        if (this.v.getVisibility() == 8) {
            j();
        }
        this.u.a(i);
        this.ac = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (this.g == null || this.l) {
            LogUtil.h("DeviceOtaActivity", "showOtaErrorMsg error, contentText: ", str, " mIsFailed: ", Boolean.valueOf(this.l));
            return;
        }
        this.l = true;
        this.r.b((Boolean) false);
        e(0);
        this.v.setVisibility(8);
        this.s.setVisibility(0);
        this.ab.setText(R.string._2130841496_res_0x7f020f98);
        this.z.setTextColor(-301790);
        this.z.setText(str);
        this.r.e(0);
        this.i.getBackground().setAlpha(255);
        this.i.setClickable(true);
        this.i.setTextColor(-301790);
        this.i.setText(R.string._2130841467_res_0x7f020f7b);
        c();
        this.r.e((Boolean) false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getWindow().addFlags(128);
        if (this.k) {
            return;
        }
        if (!TextUtils.isEmpty(this.aa)) {
            cld.HJ_(this, this.y, this.aa).onResume();
        } else {
            cld.HI_(this, this.y).onResume();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        getWindow().clearFlags(128);
    }

    private void o() {
        if (cpa.c(this.y, this.aa)) {
            l();
            return;
        }
        if (cpa.ae(this.y)) {
            n();
            return;
        }
        if (this.n && this.t) {
            String m = this.r.m();
            ddh.c().c(m, null, this.g.getFilesDir() + "", new ResourceFileListener() { // from class: com.huawei.ui.device.activity.update.WeightDeviceOtaActivity.2
                @Override // com.huawei.health.device.manager.ResourceFileListener
                public void onResult(int i, String str) {
                    if (i != 200) {
                        LogUtil.h("DeviceOtaActivity", "unUpdateZip-->onResult() resultCode: ", Integer.valueOf(i));
                        return;
                    }
                    LogUtil.a("DeviceOtaActivity", "file dir name: ", str);
                    WeightDeviceOtaActivity.this.f = new File(WeightDeviceOtaActivity.this.g.getFilesDir() + File.separator + str);
                    if (WeightDeviceOtaActivity.this.f.isDirectory()) {
                        File[] listFiles = WeightDeviceOtaActivity.this.f.listFiles();
                        Intent intent = new Intent();
                        if (listFiles == null || listFiles.length != 2) {
                            Message obtainMessage = WeightDeviceOtaActivity.this.ag.obtainMessage();
                            obtainMessage.what = 6;
                            obtainMessage.arg1 = 5;
                            WeightDeviceOtaActivity.this.ag.sendMessage(obtainMessage);
                            return;
                        }
                        for (File file : listFiles) {
                            WeightDeviceOtaActivity.this.cSZ_(file, intent);
                        }
                        WeightDeviceOtaActivity.this.r.b((Boolean) true);
                        EventBus.d(new EventBus.b("weight_device_ota_update", intent));
                        return;
                    }
                    LogUtil.h("DeviceOtaActivity", "is not directory");
                }
            }, true);
            return;
        }
        LogUtil.h("DeviceOtaActivity", "scale device not wake up");
        a(104008);
    }

    private void l() {
        this.r.b((Boolean) true);
        Intent intent = new Intent();
        intent.putExtra("scaleUniqueId", this.aa);
        intent.putExtra("scaleNewVersion", this.r.g());
        intent.putExtra("scaleFilePath", this.r.m());
        EventBus.d(new EventBus.b("weight_device_tlv_ota_update", intent));
    }

    private void n() {
        String m = this.r.m();
        Intent intent = new Intent();
        intent.putExtra("scalePath", m);
        EventBus.d(new EventBus.b("weight_device_ota_update", intent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cSZ_(File file, Intent intent) {
        try {
            if (file == null) {
                LogUtil.h("DeviceOtaActivity", "file is null");
                return;
            }
            String canonicalPath = file.getCanonicalPath();
            String canonicalPath2 = new File(this.g.getFilesDir() + File.separator).getCanonicalPath();
            if (TextUtils.isEmpty(canonicalPath) || TextUtils.isEmpty(canonicalPath2) || !canonicalPath.startsWith(canonicalPath2)) {
                return;
            }
            if (!TextUtils.isEmpty(file.getName()) && file.getName().toUpperCase(Locale.ENGLISH).contains("SCALE")) {
                intent.putExtra("scalePath", canonicalPath);
            } else {
                intent.putExtra("blePath", canonicalPath);
            }
        } catch (IOException unused) {
            LogUtil.b("DeviceOtaActivity", "canonicalPath IOException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        cgt.e().cleanup();
        this.m.setVisibility(8);
        e(100);
        this.i.getBackground().setAlpha(255);
        this.i.setClickable(true);
        this.i.setTextColor(-301790);
        this.i.setText(R.string._2130841772_res_0x7f0210ac);
        this.f9266a.setText(R.string._2130841488_res_0x7f020f90);
        this.z.setTextColor(this.g.getResources().getColor(R.color._2131297827_res_0x7f090623));
        this.z.setText(String.format(Locale.ENGLISH, this.g.getString(R.string._2130841464_res_0x7f020f78), this.r.t()));
        this.r.e(14);
        b bVar = this.ag;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
        c();
        if (!TextUtils.isEmpty(this.aa)) {
            cpa.j(this.aa, "");
        } else {
            cpa.j(this.y, "");
        }
        this.r.b();
        this.r.e((Boolean) true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        oaj oajVar = this.r;
        if (oajVar != null) {
            oajVar.b((Boolean) true);
            LogUtil.a("DeviceOtaActivity", "onFileTransferState percentage = " + i);
            b bVar = this.ag;
            if (bVar != null) {
                Message obtainMessage = bVar.obtainMessage();
                obtainMessage.what = 4;
                obtainMessage.arg1 = i;
                this.ag.sendMessage(obtainMessage);
                return;
            }
            return;
        }
        LogUtil.h("DeviceOtaActivity", "onFileTransferState mOtaInteractors is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("DeviceOtaActivity", "onUpgradeFailed: onUpgradeFailed: ", Integer.valueOf(i));
        oaj oajVar = this.r;
        if (oajVar != null) {
            LogUtil.a("DeviceOtaActivity", "is transfering: ", Boolean.valueOf(oajVar.r()));
            this.r.b((Boolean) false);
        } else {
            LogUtil.h("DeviceOtaActivity", "mOtaInteractors is null");
        }
        b bVar = this.ag;
        if (bVar != null) {
            Message obtainMessage = bVar.obtainMessage();
            obtainMessage.what = 6;
            obtainMessage.arg1 = i;
            if (i == 4) {
                d = 20;
                LogUtil.a("DeviceOtaActivity", "device ota upgrade threshold value: ", 20);
            }
            this.ag.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("DeviceOtaActivity", "onFileRespond: checkResult: ", Integer.valueOf(i));
        oaj oajVar = this.r;
        if (oajVar != null) {
            LogUtil.a("DeviceOtaActivity", "is transfering: ", Boolean.valueOf(oajVar.r()));
            this.r.b((Boolean) false);
        } else {
            LogUtil.h("DeviceOtaActivity", "mOtaInteractors is null");
        }
        if (this.ag != null) {
            LogUtil.a("DeviceOtaActivity", "DeviceUpgradeCallback, i != 1");
            Message obtainMessage = this.ag.obtainMessage();
            obtainMessage.what = 6;
            obtainMessage.arg1 = 1002;
            this.ag.sendMessage(obtainMessage);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        int s = this.r.s();
        LogUtil.a("DeviceOtaActivity", "onBackPressed() status ", Integer.valueOf(s));
        if (s == 0) {
            LogUtil.a("DeviceOtaActivity", "onBackPressed, upgrade fail quit");
            d();
            super.onBackPressed();
        } else {
            if (s == 14) {
                LogUtil.a("DeviceOtaActivity", "onBackPressed upgrade success quit");
                this.r.q();
                d();
                super.onBackPressed();
                return;
            }
            LogUtil.h("DeviceOtaActivity", "onBackPressed upgrade process invalid");
            i();
        }
    }

    private void i() {
        String string = this.g.getString(R.string._2130841487_res_0x7f020f8f);
        if (nsn.ae(BaseApplication.getContext())) {
            string = this.g.getString(R.string._2130844349_res_0x7f021abd);
        }
        new CustomTextAlertDialog.Builder(this.g).b(R.string.IDS_service_area_notice_title).e(String.format(Locale.ENGLISH, string, this.r.t())).cyR_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WeightDeviceOtaActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DeviceOtaActivity", "click negative button");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c();
        if (!this.k) {
            if (!TextUtils.isEmpty(this.aa)) {
                cld.HJ_(this, this.y, this.aa).onDestroy();
            } else {
                cld.HI_(this, this.y).onDestroy();
            }
        }
        ciy.c().b(false);
        cgk.d().e(this.o);
        cgt.e().b(this.o);
        try {
            b bVar = this.ag;
            if (bVar != null) {
                bVar.removeCallbacksAndMessages(null);
                this.ag = null;
            }
        } catch (Exception unused) {
            LogUtil.b("DeviceOtaActivity", "onDestroy Exception");
        }
        if (this.r != null) {
            LogUtil.a("DeviceOtaActivity", "onDestroy updateInteractor release");
            this.r.b((Boolean) false);
            this.r.u();
        }
        this.g = null;
        LogUtil.a("DeviceOtaActivity", "onDestroy()");
        EventBus.a(this.h, "upgrade_update_status", "upgrade_update_progress", "set_scale_version_code");
        CommonUtil.a(this.g);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("DeviceOtaActivity", "onclick ");
        int id = view.getId();
        if (id != R.id.button) {
            LogUtil.h("DeviceOtaActivity", "viewId: ", Integer.valueOf(id));
        } else {
            if (this.r.s() == 0) {
                LogUtil.a("DeviceOtaActivity", "STATUS_INITIAL");
                e();
                g();
                b();
                EventBus.d(new EventBus.b("get_scale_version_code"));
                if (this.r.e(this.r.m())) {
                    LogUtil.a("DeviceOtaActivity", "升级文件存在");
                } else {
                    LogUtil.h("DeviceOtaActivity", "升级文件不存在");
                    d();
                    finish();
                }
            }
            if (this.r.s() == 14) {
                LogUtil.a("DeviceOtaActivity", "STATUS_OTA_SUCESS");
                this.r.q();
                d();
                finish();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        HealthDevice a2;
        ciy.c().b(false);
        cgk.d().e(this.o);
        cgt.e().b(this.o);
        if (this.y == null || !this.p) {
            return;
        }
        if (!TextUtils.isEmpty(this.aa)) {
            a2 = ceo.d().d(this.aa, false);
        } else {
            a2 = cjx.e().a(this.y);
        }
        if (a2 != null) {
            dcz d2 = ResourceManager.e().d(this.y);
            if (d2 == null) {
                LogUtil.h("DeviceOtaActivity", "releaseConnect error because thi productInfo is null");
                return;
            }
            MeasureKit g = cjx.e().g(d2.s());
            if (g == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("type", -1);
            bundle.putString("productId", this.y);
            bundle.putString("uniqueId", this.aa);
            bundle.putParcelable("commonDeviceInfo", this.j);
            MeasureController measureController = g.getMeasureController();
            if (measureController != null) {
                measureController.prepare(a2, null, bundle);
            }
        }
        if (!TextUtils.isEmpty(this.aa)) {
            cjx.e().e(this.y, this.aa, -1);
        } else {
            cjx.e().a(this.y, -1);
        }
    }

    private void c() {
        PowerManager.WakeLock wakeLock = this.ah;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        LogUtil.a("DeviceOtaActivity", "releasePower, release wacklock.");
        this.ah.release();
        this.ah = null;
    }

    private void g() {
        if (this.ah != null) {
            LogUtil.h("DeviceOtaActivity", "setPower, mWakeLock is null.");
        } else if (getSystemService("power") instanceof PowerManager) {
            PowerManager.WakeLock newWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, "DeviceOtaActivity");
            this.ah = newWakeLock;
            newWakeLock.acquire(600000L);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
