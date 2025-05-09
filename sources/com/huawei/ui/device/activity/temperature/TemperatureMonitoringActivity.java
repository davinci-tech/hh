package com.huawei.ui.device.activity.temperature;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cvs;
import defpackage.ixx;
import defpackage.jlk;
import defpackage.jll;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.jqy;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class TemperatureMonitoringActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout aa;
    private int ab;
    private HealthTextView ac;
    private HealthTextView ad;
    private int ae;
    private BroadcastReceiver af;
    private LinearLayout ag;
    private HealthTextView ah;
    private HealthSwitchButton ai;
    private int aj;
    private HealthTextView ak;
    private ImageView al;
    private HealthTextView am;
    private LinearLayout an;
    private HealthTextView ap;
    private boolean b;
    private HealthTextView c;
    private CustomTitleBar d;
    private List<HealthTextView> e;
    private DeviceSettingsInteractors f;
    private DeviceCapability h;
    private HealthDivider i;
    private int j;
    private jqi k;
    private boolean l;
    private ImageView m;
    private boolean n;
    private View o;
    private int p;
    private HealthTextView q;
    private HealthTextView t;
    private RelativeLayout u;
    private LinearLayout w;
    private HealthSubHeader x;
    private HealthTextView z;
    private HealthNumberPicker y = null;
    private String[] ar = null;

    /* renamed from: a, reason: collision with root package name */
    private Context f9237a = null;
    private a v = new a(Looper.getMainLooper(), this);
    private CheckedChangeListener r = new CheckedChangeListener() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.2
        @Override // com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.CheckedChangeListener
        public void onRealCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                if (!Utils.o() && !TemperatureMonitoringActivity.this.l) {
                    TemperatureMonitoringActivity.this.c(1, 0, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.2.5
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (i == 0) {
                                e(true);
                            } else {
                                closeSwitchButton();
                            }
                        }
                    });
                } else {
                    e(true);
                }
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", 1);
                TemperatureMonitoringActivity.this.e(AnalyticsValue.CONTINUOUS_TEMPERATURE_MEASUREMENT.value(), hashMap);
                return;
            }
            e(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(boolean z) {
            TemperatureMonitoringActivity.this.f.d(1, z ? 1 : 0, -1);
        }
    };
    private CheckedChangeListener s = new CheckedChangeListener() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.5
        @Override // com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.CheckedChangeListener
        public void onRealCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                if (TemperatureMonitoringActivity.this.l) {
                    TemperatureMonitoringActivity.this.d(1);
                    return;
                } else {
                    TemperatureMonitoringActivity.this.c(2, 0, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.5.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i, Object obj) {
                            if (i == -1) {
                                closeSwitchButton();
                            }
                        }
                    });
                    return;
                }
            }
            TemperatureMonitoringActivity.this.d(0);
        }
    };
    private DialogInterface.OnClickListener g = new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            int value = TemperatureMonitoringActivity.this.y.getValue();
            if (TemperatureMonitoringActivity.this.l || value == 0) {
                TemperatureMonitoringActivity.this.a(3, value);
            } else {
                TemperatureMonitoringActivity.this.c(3, value, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.1.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.a("TemperatureMonitoringActivity", "createOpenHeartRateMonitoringDialog errorCode: ", Integer.valueOf(i2));
                    }
                });
            }
            if (!TemperatureMonitoringActivity.this.l && value == 0) {
                TemperatureMonitoringActivity.this.a(3, value);
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    };

    abstract class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private boolean ignoreSwitch;

        public abstract void onRealCheckedChanged(CompoundButton compoundButton, boolean z);

        private CheckedChangeListener() {
            this.ignoreSwitch = false;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!this.ignoreSwitch) {
                if (!TemperatureMonitoringActivity.this.ai.isPressed()) {
                    LogUtil.a("TemperatureMonitoringActivity", "mOnCheckedChangeListener isn't pressed");
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                } else if (!jll.c()) {
                    LogUtil.h("TemperatureMonitoringActivity", "mOnCheckedChangeListener isn't Sport Temperature.");
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                } else {
                    LogUtil.a("TemperatureMonitoringActivity", "mOnCheckedChangeListener, isChecked:", Boolean.valueOf(z), ", mIsHeartRateSwitchStatus:", Boolean.valueOf(TemperatureMonitoringActivity.this.l));
                    onRealCheckedChanged(compoundButton, z);
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
            }
            this.ignoreSwitch = false;
            ViewClickInstrumentation.clickOnView(compoundButton);
        }

        public void closeSwitchButton() {
            this.ignoreSwitch = true;
            TemperatureMonitoringActivity.this.ai.setChecked(false);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_temperature_monitoring);
        this.f9237a = this;
        this.k = jqi.a();
        this.f = DeviceSettingsInteractors.d(BaseApplication.getContext());
        this.h = cvs.d();
        this.b = jll.d();
        jlk.a().b(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof Boolean)) {
                    TemperatureMonitoringActivity.this.l = ((Boolean) obj).booleanValue();
                }
            }
        });
        this.n = UnitUtil.d();
        j();
        h();
    }

    private void j() {
        this.d = (CustomTitleBar) findViewById(R.id.temp_monitoring_title);
        this.c = (HealthTextView) findViewById(R.id.classification_remind);
        this.u = (RelativeLayout) findViewById(R.id.temp_monitoring_switch_layout);
        this.ac = (HealthTextView) findViewById(R.id.temp_monitoring_content);
        this.ah = (HealthTextView) findViewById(R.id.temp_monitoring_desc);
        this.o = findViewById(R.id.divide_temp);
        this.x = (HealthSubHeader) findViewById(R.id.temp_remind);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.temp_upper_remind_layout);
        this.an = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.temp_lower_remind_layout);
        this.aa = linearLayout2;
        linearLayout2.setOnClickListener(this);
        this.ak = (HealthTextView) findViewById(R.id.temperature_upper_subtitle);
        this.t = (HealthTextView) findViewById(R.id.temperature_lower_subtitle);
        this.ap = (HealthTextView) findViewById(R.id.temp_upper_value_view);
        this.q = (HealthTextView) findViewById(R.id.temp_lower_value_view);
        this.al = (ImageView) findViewById(R.id.temp_upper_arrow_right_view);
        this.m = (ImageView) findViewById(R.id.temp_lower_arrow_right_view);
        this.ai = (HealthSwitchButton) findViewById(R.id.temp_monitoring_switch);
        this.z = (HealthTextView) findViewById(R.id.temp_monitoring_note);
        this.i = (HealthDivider) findViewById(R.id.divide_line);
        this.ag = (LinearLayout) findViewById(R.id.ll_temp_study_layout);
        this.am = (HealthTextView) findViewById(R.id.temp_upper_title);
        this.ad = (HealthTextView) findViewById(R.id.temp_lower_title);
        this.w = (LinearLayout) findViewById(R.id.temp_linear_layout);
        if (Utils.o()) {
            this.ai.setOnCheckedChangeListener(this.r);
            this.d.setTitleText(getResources().getString(R.string._2130844206_res_0x7f021a2e));
            this.ac.setText(getResources().getString(R.string._2130844206_res_0x7f021a2e));
            this.ah.setText(getResources().getString(R.string._2130844207_res_0x7f021a2f));
            this.o.setVisibility(8);
            this.x.setVisibility(8);
            this.an.setVisibility(8);
            this.aa.setVisibility(8);
            this.i.setVisibility(8);
            this.z.setText(getResources().getString(R.string._2130844204_res_0x7f021a2c));
            return;
        }
        if (this.b) {
            this.ai.setOnCheckedChangeListener(this.s);
            this.d.setTitleText(getResources().getString(R.string._2130846676_res_0x7f0223d4));
            this.ac.setText(getResources().getString(R.string._2130846676_res_0x7f0223d4));
            this.ah.setText(getResources().getString(R.string._2130844200_res_0x7f021a28, 10, jlk.a().a(this.n)));
            this.c.setVisibility(0);
            this.o.setVisibility(8);
            this.x.setVisibility(8);
            this.an.setVisibility(8);
            this.aa.setVisibility(8);
            this.i.setVisibility(8);
            this.z.setText(getResources().getString(R.string._2130845006_res_0x7f021d4e, 1, 18, 2, 3));
            g();
        } else {
            this.d.setTitleText(getResources().getString(R.string._2130844195_res_0x7f021a23));
            this.u.setVisibility(8);
            this.ah.setVisibility(8);
            this.o.setVisibility(8);
            this.z.setText(getResources().getString(R.string._2130845006_res_0x7f021d4e, 1, 18, 2, 3));
            k();
        }
        i();
    }

    private void g() {
        findViewById(R.id.temperature_root_layout).setBackgroundColor(getColor(R.color._2131296690_res_0x7f0901b2));
        this.d.setTitleBarBackgroundColor(getColor(R.color._2131296690_res_0x7f0901b2));
        if (this.ag.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.ag.getLayoutParams();
            layoutParams.topMargin = (int) getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);
            layoutParams.setMarginStart((int) getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e));
            layoutParams.setMarginEnd((int) getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e));
            this.ag.setLayoutParams(layoutParams);
        }
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.resource_error);
        healthTextView.setTextSize(0, getResources().getDimension(R.dimen._2131365061_res_0x7f0a0cc5));
        healthTextView.setTextColor(getColor(R.color._2131296671_res_0x7f09019f));
        if (this.u.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.u.getLayoutParams();
            layoutParams2.setMarginStart((int) getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e));
            layoutParams2.setMarginEnd((int) getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d));
            layoutParams2.height = (int) getResources().getDimension(R.dimen._2131363076_res_0x7f0a0504);
            this.u.setLayoutParams(layoutParams2);
        }
        this.u.setGravity(16);
        this.u.setPadding((int) getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e), 0, (int) getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d), 0);
        this.u.setBackground(getDrawable(R.drawable._2131431746_res_0x7f0b1142));
        if (this.ah.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.ah.getLayoutParams();
            layoutParams3.topMargin = (int) getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);
            this.ah.setLayoutParams(layoutParams3);
        }
    }

    private void k() {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.al.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.m.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.al.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
            this.m.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    private void i() {
        if (!jll.b()) {
            LogUtil.a("TemperatureMonitoringActivity", "initTemperatureStudy not support tempStudy");
            a(true);
            return;
        }
        this.af = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                    LogUtil.h("TemperatureMonitoringActivity", "mTempDataReceiver intent is null or action is empty");
                    return;
                }
                if (!jll.c() || !jll.b()) {
                    LogUtil.h("TemperatureMonitoringActivity", "mTempDataReceiver isn't Sport Temperature or temperatureStudy.");
                } else if (!intent.getAction().equals(jll.b("tempStudyStatus"))) {
                    LogUtil.h("TemperatureMonitoringActivity", "mTempDataReceiver default");
                } else {
                    LogUtil.a("TemperatureMonitoringActivity", "mTempDataReceiver is tempStudyAction");
                    TemperatureMonitoringActivity.this.f();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(jll.b("tempStudyStatus"));
        BroadcastManagerUtil.bFA_(this.f9237a, this.af, intentFilter, LocalBroadcast.c, null);
        jlk.a().b();
    }

    /* renamed from: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity$6, reason: invalid class name */
    class AnonymousClass6 implements Runnable {
        AnonymousClass6() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ThreadPoolManager.d().d("TemperatureMonitoringActivity", new Runnable() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.6.1
                @Override // java.lang.Runnable
                public void run() {
                    final String a2 = jll.a(jpt.a("TemperatureMonitoringActivity"));
                    if (TextUtils.isEmpty(a2)) {
                        LogUtil.h("TemperatureMonitoringActivity", "mTempDataReceiver studyDbKey is empty");
                    } else {
                        final String switchSettingFromDb = jqi.a().getSwitchSettingFromDb(a2);
                        TemperatureMonitoringActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.6.1.4
                            @Override // java.lang.Runnable
                            public void run() {
                                TemperatureMonitoringActivity.this.a(CommonUtil.m(BaseApplication.getContext(), switchSettingFromDb) == 1);
                                jqi.a().setSwitchSettingToDb(a2, "");
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.v.postDelayed(new AnonymousClass6(), 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        LogUtil.a("TemperatureMonitoringActivity", "setViewStatusByTemperatureStudy isJoin : ", Boolean.valueOf(z));
        if (Utils.o()) {
            LogUtil.a("TemperatureMonitoringActivity", "setViewStatusByTemperatureStudy isOversea");
            return;
        }
        this.ag.setVisibility(z ? 8 : 0);
        int color = getResources().getColor(R.color._2131296698_res_0x7f0901ba);
        if (z) {
            color = getResources().getColor(R.color._2131299241_res_0x7f090ba9);
        }
        if (this.b) {
            this.ac.setTextColor(color);
            this.ai.setEnabled(z);
            return;
        }
        if (this.e == null) {
            ArrayList arrayList = new ArrayList(16);
            this.e = arrayList;
            arrayList.add(this.am);
            this.e.add(this.ap);
            this.e.add(this.ak);
            this.e.add(this.ad);
            this.e.add(this.q);
            this.e.add(this.t);
        }
        this.an.setEnabled(z);
        this.aa.setEnabled(z);
        Iterator<HealthTextView> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().setTextColor(color);
        }
    }

    private void h() {
        this.aj = 0;
        this.p = 0;
        this.ae = jlk.a().c(0, String.valueOf(37.5f), this.n);
        this.ab = jlk.a().c(1, String.valueOf(35.5f), this.n);
        if (Utils.o()) {
            d();
        } else {
            c();
        }
    }

    private void c() {
        jlk.a().b(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof Boolean)) {
                    TemperatureMonitoringActivity.this.l = ((Boolean) obj).booleanValue();
                }
                TemperatureMonitoringActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.8.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (TemperatureMonitoringActivity.this.l) {
                            if (TemperatureMonitoringActivity.this.b) {
                                TemperatureMonitoringActivity.this.a();
                                return;
                            } else {
                                TemperatureMonitoringActivity.this.b();
                                TemperatureMonitoringActivity.this.e();
                                return;
                            }
                        }
                        jlk.a().c();
                        if (TemperatureMonitoringActivity.this.b) {
                            TemperatureMonitoringActivity.this.ai.setChecked(false);
                            return;
                        }
                        String b = TemperatureMonitoringActivity.this.b(0, -1, (Object) null);
                        String a2 = TemperatureMonitoringActivity.this.a(0, -1, (Object) null);
                        TemperatureMonitoringActivity.this.ak.setText(b);
                        TemperatureMonitoringActivity.this.ap.setText(a2);
                        TemperatureMonitoringActivity.this.aj = 0;
                        String b2 = TemperatureMonitoringActivity.this.b(1, -1, (Object) null);
                        String a3 = TemperatureMonitoringActivity.this.a(1, -1, (Object) null);
                        TemperatureMonitoringActivity.this.t.setText(b2);
                        TemperatureMonitoringActivity.this.q.setText(a3);
                        TemperatureMonitoringActivity.this.p = 0;
                    }
                });
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        BroadcastReceiver broadcastReceiver = this.af;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        ThreadPoolManager.d().e("TemperatureMonitoringActivity", null);
        super.onDestroy();
    }

    private void a(int i) {
        LogUtil.a("TemperatureMonitoringActivity", "createTemperatureRemindDialog()");
        this.j = i;
        jlk.a().b(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0 && (obj instanceof Boolean)) {
                    TemperatureMonitoringActivity.this.l = ((Boolean) obj).booleanValue();
                }
            }
        });
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.dialog_temperature_remind, (ViewGroup) null);
        this.y = (HealthNumberPicker) inflate.findViewById(R.id.remind_number_picker);
        this.ar = jlk.a().c(this.n, i);
        l();
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.e(i == 0 ? R.string._2130844199_res_0x7f021a27 : R.string._2130844201_res_0x7f021a29).cyp_(inflate).cyn_(R.string._2130841939_res_0x7f021153, new DialogInterface.OnClickListener() { // from class: nyj
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        }).cyo_(R.string._2130841938_res_0x7f021152, this.g);
        builder.c().show();
    }

    private String b(int i) {
        if (this.j == 0) {
            if (i != 0) {
                return getResources().getString(R.string._2130844200_res_0x7f021a28, 10, this.ar[i]);
            }
            return getResources().getString(R.string._2130844387_res_0x7f021ae3, 10);
        }
        if (i != 0) {
            return getResources().getString(R.string._2130844202_res_0x7f021a2a, 10, this.ar[i]);
        }
        return getResources().getString(R.string._2130844388_res_0x7f021ae4, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i, final int i2, final IBaseResponseCallback iBaseResponseCallback) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(getResources().getString(R.string._2130844209_res_0x7f021a31)).d(this.b ? R.string._2130846678_res_0x7f0223d6 : R.string._2130844767_res_0x7f021c5f).cyR_(R.string._2130841939_res_0x7f021153, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                iBaseResponseCallback.d(-1, null);
                LogUtil.h("TemperatureMonitoringActivity", "createOpenHeartRateMonitoringDialog, cancel");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130842835_res_0x7f0214d3, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                iBaseResponseCallback.d(0, null);
                if (i != 1) {
                    if (TemperatureMonitoringActivity.this.h == null || !TemperatureMonitoringActivity.this.h.isSupportContinueHeartRate()) {
                        if (TemperatureMonitoringActivity.this.h != null && TemperatureMonitoringActivity.this.h.isSupportHeartRateEnable() && !TemperatureMonitoringActivity.this.h.isSupportContinueHeartRate()) {
                            LogUtil.a("TemperatureMonitoringActivity", "createOpenHeartRateMonitoringDialog, isSupportHeartRateEnable");
                            TemperatureMonitoringActivity.this.f.e(true);
                            TemperatureMonitoringActivity temperatureMonitoringActivity = TemperatureMonitoringActivity.this;
                            nrh.d(temperatureMonitoringActivity, temperatureMonitoringActivity.getResources().getString(R.string._2130844211_res_0x7f021a33));
                            TemperatureMonitoringActivity.this.l = true;
                            TemperatureMonitoringActivity.this.a(i, i2);
                        } else {
                            LogUtil.h("TemperatureMonitoringActivity", "createOpenHeartRateMonitoringDialog isn't support heart rate.");
                        }
                    } else {
                        LogUtil.a("TemperatureMonitoringActivity", "createOpenHeartRateMonitoringDialog, isSupportContinueHeartRate");
                        TemperatureMonitoringActivity.this.c(i, i2);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                TemperatureMonitoringActivity temperatureMonitoringActivity2 = TemperatureMonitoringActivity.this;
                nrh.d(temperatureMonitoringActivity2, temperatureMonitoringActivity2.getResources().getString(R.string._2130844211_res_0x7f021a33));
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        if (a2.isShowing()) {
            return;
        }
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i, final int i2) {
        if (!this.h.isSupportHeartRateEnable()) {
            this.f.a(1);
            LogUtil.a("TemperatureMonitoringActivity", "openContinueMeasureHeartRate open continue measure heart rate");
            nrh.d(this, getResources().getString(R.string._2130844211_res_0x7f021a33));
            this.l = true;
            a(i, i2);
            return;
        }
        LogUtil.a("TemperatureMonitoringActivity", "openContinueMeasureHeartRate isSupportHeartRateEnable");
        this.k.getSwitchSetting("heart_rate_mode", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                final int i4;
                if (i3 == 0 && (obj instanceof String)) {
                    try {
                        i4 = Integer.parseInt((String) obj);
                    } catch (NumberFormatException unused) {
                        LogUtil.a("TemperatureMonitoringActivity", "initItemSelect NumberFormatException");
                    }
                    TemperatureMonitoringActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (i4 == 1) {
                                TemperatureMonitoringActivity.this.f.c(1);
                                LogUtil.a("TemperatureMonitoringActivity", "openContinueMeasureHeartRate open cycle measure heart rate");
                            } else {
                                TemperatureMonitoringActivity.this.f.a(1);
                                LogUtil.a("TemperatureMonitoringActivity", "openContinueMeasureHeartRate open continue measure heart rate");
                            }
                            nrh.d(TemperatureMonitoringActivity.this, TemperatureMonitoringActivity.this.getResources().getString(R.string._2130844211_res_0x7f021a33));
                            TemperatureMonitoringActivity.this.l = true;
                            TemperatureMonitoringActivity.this.a(i, i2);
                        }
                    });
                }
                i4 = 1;
                TemperatureMonitoringActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (i4 == 1) {
                            TemperatureMonitoringActivity.this.f.c(1);
                            LogUtil.a("TemperatureMonitoringActivity", "openContinueMeasureHeartRate open cycle measure heart rate");
                        } else {
                            TemperatureMonitoringActivity.this.f.a(1);
                            LogUtil.a("TemperatureMonitoringActivity", "openContinueMeasureHeartRate open continue measure heart rate");
                        }
                        nrh.d(TemperatureMonitoringActivity.this, TemperatureMonitoringActivity.this.getResources().getString(R.string._2130844211_res_0x7f021a33));
                        TemperatureMonitoringActivity.this.l = true;
                        TemperatureMonitoringActivity.this.a(i, i2);
                    }
                });
            }
        });
    }

    private void l() {
        this.y.setDisplayedValues(this.ar);
        this.y.setMinValue(0);
        this.y.setMaxValue(this.ar.length - 1);
        this.y.setWrapSelectorWheel(false);
        if (this.j == 0) {
            int b = b(this.ar.length, this.aj);
            this.aj = b;
            if (b == 0) {
                int length = this.ar.length;
                int i = this.ae;
                if (length > i) {
                    b = i;
                }
            }
            this.y.setValue(b);
            return;
        }
        int b2 = b(this.ar.length, this.p);
        this.p = b2;
        if (b2 == 0) {
            int length2 = this.ar.length;
            int i2 = this.ab;
            if (length2 > i2) {
                b2 = i2;
            }
        }
        this.y.setValue(b2);
    }

    private int b(int i, int i2) {
        LogUtil.a("TemperatureMonitoringActivity", "checkTempPosition length : ", Integer.valueOf(i), ", position : ", Integer.valueOf(i2));
        if (i2 < 0 || i2 >= i) {
            return 0;
        }
        return i2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.temp_upper_remind_layout) {
            a(0);
        } else if (id == R.id.temp_lower_remind_layout) {
            a(1);
        } else {
            LogUtil.h("TemperatureMonitoringActivity", "onClick default");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        String c = jqy.c("continuous_temp_monitoring");
        LogUtil.a("TemperatureMonitoringActivity", "switch checked is ", c);
        if (CommonUtil.m(this, c) == 1) {
            this.ai.setChecked(true);
        } else {
            this.ai.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a() {
        /*
            r4 = this;
            java.lang.String r0 = "temperature_upper_remind"
            java.lang.String r0 = defpackage.jqy.c(r0)
            r1 = 0
            r2 = 1
            float r0 = java.lang.Float.parseFloat(r0)     // Catch: java.lang.NumberFormatException -> L14
            r3 = 1092616192(0x41200000, float:10.0)
            float r0 = r0 * r3
            int r0 = (int) r0
            if (r0 <= 0) goto L1f
            r0 = r2
            goto L20
        L14:
            java.lang.String r0 = "getTempClassificationUpperRemindFromCloud NumberFormatException"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r3 = "TemperatureMonitoringActivity"
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)
        L1f:
            r0 = r1
        L20:
            com.huawei.ui.commonui.switchbutton.HealthSwitchButton r3 = r4.ai
            if (r0 != r2) goto L25
            r1 = r2
        L25:
            r3.setChecked(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity.a():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String c = jqy.c("temperature_upper_remind");
        int i = TextUtils.isEmpty(c) ? -1 : 0;
        this.aj = jlk.a().c(0, c, this.n);
        String b = b(0, i, c);
        String a2 = a(0, i, c);
        this.ak.setText(b);
        this.ap.setText(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String c = jqy.c("temperature_lower_remind");
        int i = TextUtils.isEmpty(c) ? -1 : 0;
        this.p = jlk.a().c(1, c, this.n);
        String b = b(1, i, c);
        String a2 = a(1, i, c);
        this.t.setText(b);
        this.q.setText(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i, int i2, Object obj) {
        String string = getResources().getString(R.string._2130841134_res_0x7f020e2e);
        if (obj == null) {
            return string;
        }
        try {
            if (obj.equals("0") || i2 != 0 || !(obj instanceof String)) {
                return string;
            }
            String c = jll.c((String) obj);
            LogUtil.a("TemperatureMonitoringActivity", "initRemindValue, data value:", c);
            if (this.n && Float.parseFloat(c) > 90.0f) {
                c = jll.e(Float.parseFloat(c));
            }
            if (!this.n && Float.parseFloat(c) < 90.0f) {
                c = jll.d(c);
            }
            if (!a(c, i)) {
                return string;
            }
            DecimalFormat e = jll.e("0.0");
            if (e != null) {
                c = e.format(Float.parseFloat(c));
            }
            return getResources().getString(this.n ? R.string._2130843630_res_0x7f0217ee : R.string._2130844212_res_0x7f021a34, c);
        } catch (NumberFormatException unused) {
            LogUtil.b("TemperatureMonitoringActivity", "getTempUpperRemindFromCloud, NumberFormatException.");
            return string;
        }
    }

    private boolean a(String str, int i) {
        float f;
        try {
            f = Float.parseFloat(str) * 10.0f;
        } catch (NumberFormatException unused) {
            LogUtil.b("TemperatureMonitoringActivity", "isCorrectValue fixedValue : ", Float.valueOf(0.0f));
            f = 0.0f;
        }
        if (f == 0.0f) {
            return false;
        }
        if (i == 0) {
            boolean z = this.n;
            if (z && (f > 385.0f || f < 372.0f)) {
                return false;
            }
            if (z) {
                return true;
            }
            return f <= 1013.0f && f >= 990.0f;
        }
        boolean z2 = this.n;
        if (z2 && (f > 360.0f || f < 340.0f)) {
            return false;
        }
        if (z2) {
            return true;
        }
        return f <= 968.0f && f >= 932.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(int i, int i2, Object obj) {
        String string;
        int i3 = R.string._2130844388_res_0x7f021ae4;
        if (i2 == 0) {
            try {
                if (obj instanceof String) {
                    String a2 = a(i, i2, obj);
                    if (!obj.equals("0") && !obj.equals("0.0")) {
                        string = getResources().getString(i == 0 ? R.string._2130844200_res_0x7f021a28 : R.string._2130844202_res_0x7f021a2a, 10, a2);
                        return string;
                    }
                    Resources resources = getResources();
                    if (i == 0) {
                        i3 = R.string._2130844387_res_0x7f021ae3;
                    }
                    string = resources.getString(i3, 10);
                    return string;
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("TemperatureMonitoringActivity", "getTempUpperRemindFromCloud, NumberFormatException.");
                return "";
            }
        }
        Resources resources2 = getResources();
        if (i == 0) {
            i3 = R.string._2130844387_res_0x7f021ae3;
        }
        string = resources2.getString(i3, 10);
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        c(i);
        d(this.ah.getText().toString(), AnalyticsValue.TEMPERATURE_UPPER_LIMIT.value());
    }

    private void c(int i) {
        this.f.d(2, i, i == 1 ? 372 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        boolean z = this.l;
        if (z) {
            if (i != 1) {
                if (i == 2) {
                    LogUtil.a("TemperatureMonitoringActivity", "setTempLimitValue TEMPERATURE_REMIND_TYPE_CLASSIFICATION");
                    d(1);
                    return;
                }
                int[] a2 = jlk.a().a(this.n, this.j);
                String b = b(i2);
                int i3 = 0;
                int i4 = i2 == 0 ? 0 : 1;
                if (i2 != 0) {
                    i3 = a2[i2 - 1];
                    if (!this.n) {
                        i3 = jll.a(i3);
                    }
                }
                if (this.j == 0) {
                    this.ak.setText(b);
                    this.ap.setText(this.ar[i2]);
                    this.aj = i2;
                    this.f.d(2, i4, i3);
                    d(b, AnalyticsValue.TEMPERATURE_UPPER_LIMIT.value());
                    return;
                }
                this.t.setText(b);
                this.q.setText(this.ar[i2]);
                this.p = i2;
                this.f.d(3, i4, i3);
                d(b, AnalyticsValue.TEMPERATURE_LOWER_LIMIT.value());
                return;
            }
        }
        LogUtil.h("TemperatureMonitoringActivity", "setTempLimitValue, error status:", Boolean.valueOf(z));
    }

    private void d(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("tempLimitValue", str);
        e(str2, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, Map<String, Object> map) {
        ixx.d().d(BaseApplication.getContext(), str, map, 0);
    }

    static class a extends BaseHandler<TemperatureMonitoringActivity> {
        public a(Looper looper, TemperatureMonitoringActivity temperatureMonitoringActivity) {
            super(looper, temperatureMonitoringActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cSI_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TemperatureMonitoringActivity temperatureMonitoringActivity, Message message) {
            LogUtil.h("TemperatureMonitoringActivity", "TempHandler handleMessageWhenReferenceNotNull");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
