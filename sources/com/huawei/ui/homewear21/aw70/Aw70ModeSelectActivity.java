package com.huawei.ui.homewear21.aw70;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homewear21.aw70.Aw70ModeSelectActivity;
import com.huawei.ui.main.R$string;
import defpackage.cvs;
import defpackage.cvt;
import defpackage.cvx;
import defpackage.ixx;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.oxa;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class Aw70ModeSelectActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f9636a = 1;
    private RelativeLayout aa;
    private RelativeLayout ab;
    private Context b;
    private HealthTextView c;
    private HealthTextView d;
    private DeviceInfo e;
    private HealthTextView f;
    private ImageView g;
    private ImageView h;
    private HealthTextView i;
    private RelativeLayout j;
    private HealthRadioButton k;
    private String l;
    private a m;
    private DeviceSettingsInteractors n;
    private CustomViewDialog o;
    private HealthRadioButton p;
    private HealthTextView q;
    private int r;
    private ImageView s;
    private LinearLayout t;
    private HealthTextView u;
    private HealthTextView v;
    private ImageView w;
    private HealthTextView x;
    private HealthTextView y;
    private String z;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("AW70_Aw70ModeSelectActivity", "AW70onCreate");
        setContentView(R.layout.fragment_aw70_select_mode);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "onCreate intent is null");
            finish();
            return;
        }
        this.l = intent.getStringExtra("device_id");
        DeviceInfo a2 = oxa.a().a(this.l);
        this.e = a2;
        if (a2 != null) {
            int productType = a2.getProductType();
            this.r = productType;
            LogUtil.a("AW70_Aw70ModeSelectActivity", "onCreate mProductType is ", Integer.valueOf(productType));
            a();
            j();
            return;
        }
        LogUtil.a("AW70_Aw70ModeSelectActivity", "onCreate mProductType is null");
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("AW70_Aw70ModeSelectActivity", "Inside onResume!");
        b(new IBaseResponseCallback() { // from class: com.huawei.ui.homewear21.aw70.Aw70ModeSelectActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof byte[]) {
                    String d = cvx.d((byte[]) obj);
                    LogUtil.a("AW70_Aw70ModeSelectActivity", "getSwitch berfore onResponse objectData is ", d);
                    try {
                        int parseInt = Integer.parseInt(d.substring(d.length() - 2, d.length()));
                        if (parseInt == 0) {
                            Aw70ModeSelectActivity.this.a(4);
                            Aw70ModeSelectActivity.this.f9636a = 0;
                            LogUtil.a("AW70_Aw70ModeSelectActivity", "onResponse AUTO_SELECT_OFF value is ", Integer.valueOf(parseInt));
                        } else if (parseInt == 1) {
                            Aw70ModeSelectActivity.this.a(3);
                            Aw70ModeSelectActivity.this.f9636a = 1;
                            LogUtil.a("AW70_Aw70ModeSelectActivity", "onResponse AUTO_SELECT_ON value is ", Integer.valueOf(parseInt));
                        } else {
                            LogUtil.h("AW70_Aw70ModeSelectActivity", "onResponse other value is wrong");
                        }
                    } catch (NumberFormatException e2) {
                        LogUtil.b("AW70_Aw70ModeSelectActivity", "onResponse() e ", e2.getMessage());
                    }
                }
            }
        });
        this.z = SharedPreferenceManager.b(this, Integer.toString(10000), "KEY_AW70_WARE_MODE");
        g();
    }

    private void g() {
        if (b()) {
            h();
            return;
        }
        if ("HANDRING_MODE".equals(this.z)) {
            this.i.setText(getString(R.string._2130842737_res_0x7f021471, new Object[]{getString(R.string._2130842735_res_0x7f02146f)}));
            this.v.setText(getString(R.string._2130842736_res_0x7f021470));
        } else if ("RUNNING_MODE".equals(this.z)) {
            this.i.setText(getString(R.string._2130842735_res_0x7f02146f));
            this.v.setText(getString(R.string._2130842737_res_0x7f021471, new Object[]{getString(R.string._2130842736_res_0x7f021470)}));
        } else {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "judgeViewWordShow get wareMode failed");
        }
    }

    private void h() {
        if ("HANDRING_MODE".equals(this.z)) {
            this.i.setText(getString(R.string._2130842737_res_0x7f021471, new Object[]{getString(R.string._2130842735_res_0x7f02146f)}));
            this.v.setText(getString(R.string._2130843181_res_0x7f02162d));
        } else if ("RUNNING_MODE".equals(this.z)) {
            this.i.setText(getString(R.string._2130842735_res_0x7f02146f));
            this.v.setText(getString(R.string._2130842737_res_0x7f021471, new Object[]{getString(R.string._2130843181_res_0x7f02162d)}));
        } else {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "judgeViewWordShow get wareMode failed");
            return;
        }
        if (e()) {
            this.t.setVisibility(0);
            this.q.setText(getString(R.string._2130843689_res_0x7f021829));
            this.x.setText(getString(R.string._2130843690_res_0x7f02182a));
            this.y.setText(getString(R.string._2130843691_res_0x7f02182b));
            this.u.setText(getString(R.string._2130843692_res_0x7f02182c));
            return;
        }
        if (cvt.a(this.r)) {
            this.t.setVisibility(8);
            this.q.setText(getString(R.string._2130843182_res_0x7f02162e));
        } else {
            LogUtil.h("AW70_Aw70ModeSelectActivity", "showRunModeDescribeAw70 is other device");
        }
    }

    private boolean e() {
        DeviceInfo deviceInfo = this.e;
        if (deviceInfo == null) {
            return false;
        }
        int productType = deviceInfo.getProductType();
        LogUtil.a("AW70_Aw70ModeSelectActivity", "productType:", Integer.valueOf(productType));
        if (productType == 63) {
            return true;
        }
        DeviceCapability e2 = cvs.e(this.e.getDeviceIdentify());
        return productType == 36 && e2 != null && e2.isSupportWorkoutCapabilicy();
    }

    private void d() {
        this.i = (HealthTextView) findViewById(R.id.aw70_handring_mode_tv);
        this.v = (HealthTextView) findViewById(R.id.aw70_running_mode_tv);
        this.q = (HealthTextView) findViewById(R.id.aw70_running_mode_describe_tv);
        this.f = (HealthTextView) findViewById(R.id.aw70_change_mode_tv);
        this.d = (HealthTextView) findViewById(R.id.aw70_run_mode_position_describe_tv);
        this.c = (HealthTextView) findViewById(R.id.aw70_auto_change_mode_describe_tv);
        this.x = (HealthTextView) findViewById(R.id.aw70_support_running_tv);
        this.y = (HealthTextView) findViewById(R.id.aw70_support_basketball);
        this.u = (HealthTextView) findViewById(R.id.aw70_support_cycling);
        this.j = (RelativeLayout) findViewById(R.id.aw70_auto_change_mode_rly);
        this.t = (LinearLayout) findViewById(R.id.aw70_running_mode_describe_lv);
        this.h = (ImageView) findViewById(R.id.aw70_change_mode_img);
        this.w = (ImageView) findViewById(R.id.aw70_running_mode_select_img);
        this.g = (ImageView) findViewById(R.id.aw70_handring_mode_describe_iv);
        this.s = (ImageView) findViewById(R.id.aw70_running_mode_describe_iv);
        this.j.setOnClickListener(this);
    }

    private void a() {
        d();
        if (getResources().getDrawable(R.drawable.ic_health_list_arrow_gray) instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_health_list_arrow_gray);
            if (LanguageUtil.bc(this)) {
                bitmapDrawable = nrz.cKn_(this, R.drawable.ic_health_list_arrow_gray);
            }
            this.h.setImageDrawable(bitmapDrawable);
            this.w.setImageDrawable(bitmapDrawable);
        }
        Context context = BaseApplication.getContext();
        this.b = context;
        this.n = DeviceSettingsInteractors.d(context);
        this.m = new a(this);
        this.f.setText(getString(R.string._2130842704_res_0x7f021450));
        this.c.setText(getString(R.string._2130842740_res_0x7f021474));
        if (b()) {
            this.g.setImageResource(R.mipmap._2131821456_res_0x7f110390);
            this.s.setImageResource(R.mipmap._2131821455_res_0x7f11038f);
            this.d.setText(getString(R.string._2130849052_res_0x7f022d1c, new Object[]{getString(R.string._2130843181_res_0x7f02162d)}));
        } else {
            this.d.setText(getString(R.string._2130849052_res_0x7f022d1c, new Object[]{getString(R.string._2130842736_res_0x7f021470)}));
        }
        this.z = SharedPreferenceManager.b(this, Integer.toString(10000), "KEY_AW70_WARE_MODE");
        g();
        c();
    }

    private void c() {
        b(new IBaseResponseCallback() { // from class: com.huawei.ui.homewear21.aw70.Aw70ModeSelectActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                try {
                    String d = cvx.d((byte[]) obj);
                    LogUtil.a("AW70_Aw70ModeSelectActivity", "getSwitch berfore onResponse objData is ", d);
                    int parseInt = Integer.parseInt(d.substring(d.length() - 2));
                    if (parseInt == 0) {
                        Aw70ModeSelectActivity.this.a(4);
                        Aw70ModeSelectActivity.this.f9636a = 0;
                        LogUtil.a("AW70_Aw70ModeSelectActivity", "onResponse AUTO_SELECT_OFF value is ", Integer.valueOf(parseInt));
                    } else if (parseInt == 1) {
                        Aw70ModeSelectActivity.this.a(3);
                        Aw70ModeSelectActivity.this.f9636a = 1;
                        LogUtil.a("AW70_Aw70ModeSelectActivity", "onResponse AUTO_SELECT_ON value is ", Integer.valueOf(parseInt));
                    } else {
                        LogUtil.a("AW70_Aw70ModeSelectActivity", "onResponse other value is wrong");
                    }
                } catch (NumberFormatException e2) {
                    LogUtil.a("AW70_Aw70ModeSelectActivity", "onResponse() e = ", e2.getMessage());
                }
            }
        });
    }

    /* renamed from: com.huawei.ui.homewear21.aw70.Aw70ModeSelectActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "setRunningModePosition() dialog start");
            View inflate = ((LayoutInflater) Aw70ModeSelectActivity.this.getSystemService("layout_inflater")).inflate(R.layout.fragment_aw70_select_auto_mode, (ViewGroup) null);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(Aw70ModeSelectActivity.this);
            builder.a(Aw70ModeSelectActivity.this.getString(R.string._2130842738_res_0x7f021472)).czg_(inflate).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: oxb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Aw70ModeSelectActivity.AnonymousClass1.djo_(view2);
                }
            });
            Aw70ModeSelectActivity.this.o = builder.e();
            if (Aw70ModeSelectActivity.this.djm_(inflate)) {
                Aw70ModeSelectActivity.this.o.show();
                ViewClickInstrumentation.clickOnView(view);
            } else {
                LogUtil.c("AW70_Aw70ModeSelectActivity", "setRunningModePosition() dialog layout fail");
                Aw70ModeSelectActivity.this.o = null;
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        public static /* synthetic */ void djo_(View view) {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "onClick dialog dismiss is fail");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void j() {
        this.j.setClickable(true);
        this.j.setOnClickListener(new AnonymousClass1());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.j) {
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
            this.o = builder.e();
            builder.czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: owz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    Aw70ModeSelectActivity.djn_(view2);
                }
            });
            this.o.show();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void djn_(View view) {
        LogUtil.a("AW70_Aw70ModeSelectActivity", "onClick dialog is dismiss");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.n.e(this.l, this.f9636a, new IBaseResponseCallback() { // from class: com.huawei.ui.homewear21.aw70.Aw70ModeSelectActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("AW70_Aw70ModeSelectActivity", "setActivityReminder errCode is " + i + ", objData is " + obj);
                if (i != 0) {
                    nrh.e(Aw70ModeSelectActivity.this.b, R.string._2130841440_res_0x7f020f60);
                }
            }
        });
        if (this.f9636a == 1) {
            a(3);
        } else {
            a(4);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        String value = AnalyticsValue.HEALTH_PLUGIN_DEVICE_AW70_AUTO_SELECT_2060028.value();
        ixx.d().d(this, value, hashMap, 0);
        LogUtil.a("AW70_Aw70ModeSelectActivity", "BI save notification click event finish, value is " + value);
    }

    private void b(IBaseResponseCallback iBaseResponseCallback) {
        this.n.c(this.l, iBaseResponseCallback);
    }

    static class a extends Handler {
        private final WeakReference<Aw70ModeSelectActivity> b;

        a(Aw70ModeSelectActivity aw70ModeSelectActivity) {
            this.b = new WeakReference<>(aw70ModeSelectActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Aw70ModeSelectActivity aw70ModeSelectActivity = this.b.get();
            if (aw70ModeSelectActivity == null) {
                super.handleMessage(message);
                return;
            }
            LogUtil.a("AW70_Aw70ModeSelectActivity", "Handle Message is " + message.what);
            int i = message.what;
            if (i == 3) {
                aw70ModeSelectActivity.b(3);
            } else {
                if (i != 4) {
                    return;
                }
                aw70ModeSelectActivity.b(4);
            }
        }
    }

    private boolean b() {
        return cvt.a(this.r) || e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (b()) {
            if (i == 3) {
                this.c.setText(getString(R.string._2130843183_res_0x7f02162f));
                this.f.setText(R.string._2130842704_res_0x7f021450);
                return;
            } else {
                if (i != 4) {
                    return;
                }
                this.c.setText(getString(R.string._2130842740_res_0x7f021474));
                this.f.setText(R.string._2130842703_res_0x7f02144f);
                return;
            }
        }
        if (i == 3) {
            this.c.setText(getString(R.string._2130842739_res_0x7f021473));
            this.f.setText(R.string._2130842704_res_0x7f021450);
        } else {
            if (i != 4) {
                return;
            }
            this.c.setText(getString(R.string._2130842740_res_0x7f021474));
            this.f.setText(R.string._2130842703_res_0x7f02144f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        Message obtainMessage = this.m.obtainMessage();
        obtainMessage.what = i;
        this.m.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean djm_(View view) {
        LogUtil.a("AW70_Aw70ModeSelectActivity", "initializeSettingModeDialogLayout()");
        if (view == null) {
            return false;
        }
        this.p = (HealthRadioButton) view.findViewById(R.id.settings_mode_imgview1);
        this.k = (HealthRadioButton) view.findViewById(R.id.settings_mode_imgview2);
        this.ab = (RelativeLayout) view.findViewById(R.id.settings_mode_view_layout1);
        this.aa = (RelativeLayout) view.findViewById(R.id.settings_mode_view_layout2);
        this.ab.setOnClickListener(new e(this));
        this.aa.setOnClickListener(new c(this));
        int i = this.f9636a;
        if (i == 1) {
            this.p.setChecked(true);
            this.k.setChecked(false);
        } else if (i == 0) {
            this.k.setChecked(true);
            this.p.setChecked(false);
        } else {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "initializeSettingModeDialogLayout() fail! data uninitialize");
        }
        return true;
    }

    class e implements View.OnClickListener {
        private final WeakReference<Aw70ModeSelectActivity> b;

        e(Aw70ModeSelectActivity aw70ModeSelectActivity) {
            this.b = new WeakReference<>(aw70ModeSelectActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "MyOpenOnClickListener onClick()");
            Aw70ModeSelectActivity aw70ModeSelectActivity = this.b.get();
            if (aw70ModeSelectActivity != null) {
                aw70ModeSelectActivity.k.setChecked(false);
                aw70ModeSelectActivity.p.setChecked(true);
                aw70ModeSelectActivity.f9636a = 1;
                Aw70ModeSelectActivity.this.i();
                Aw70ModeSelectActivity.this.o.dismiss();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.b("AW70_Aw70ModeSelectActivity", "MyOpenOnClickListener Aw70ModeSelectActivity is null");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class c implements View.OnClickListener {
        private final WeakReference<Aw70ModeSelectActivity> c;

        c(Aw70ModeSelectActivity aw70ModeSelectActivity) {
            this.c = new WeakReference<>(aw70ModeSelectActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("AW70_Aw70ModeSelectActivity", "MyCloseOnClickListener onClick()");
            Aw70ModeSelectActivity aw70ModeSelectActivity = this.c.get();
            if (aw70ModeSelectActivity != null) {
                aw70ModeSelectActivity.k.setChecked(true);
                aw70ModeSelectActivity.p.setChecked(false);
                aw70ModeSelectActivity.f9636a = 0;
                Aw70ModeSelectActivity.this.i();
                Aw70ModeSelectActivity.this.o.dismiss();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.b("AW70_Aw70ModeSelectActivity", "MyCloseOnClickListener Aw70ModeSelectActivity is null");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
