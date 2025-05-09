package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.dashboard.BloodSugarDashboardView;
import com.huawei.ui.commonui.linechart.view.dashboard.DashboardRingView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodMeasureResultActivity;
import com.huawei.ui.main.stories.health.adapter.CustomActionDialogAdapter;
import com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import defpackage.eeu;
import defpackage.koq;
import defpackage.kor;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.qkh;
import defpackage.qkv;
import defpackage.qrp;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes8.dex */
public class BloodMeasureResultActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private BloodPressureMeasureActionManager f10020a;
    private HealthTextView b;
    private HealthTextView c;
    private Context d;
    private BloodSugarDashboardView e;
    private HealthFlowLayout f;
    private int g;
    private View h;
    private Handler i;
    private boolean j;
    private HiHealthData k;
    private List<String> l;
    private long m;
    private HealthButton n;
    private HealthTextView o;

    static class d extends BaseHandler<BloodMeasureResultActivity> {
        d(BloodMeasureResultActivity bloodMeasureResultActivity) {
            super(bloodMeasureResultActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dyQ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodMeasureResultActivity bloodMeasureResultActivity, Message message) {
            int i = message.what;
            if (i == 0) {
                if (!(message.obj instanceof List)) {
                    LogUtil.a("BloodMeasureResultActivity", "no data");
                    return;
                }
                List list = (List) message.obj;
                if (!koq.b(list)) {
                    bloodMeasureResultActivity.k = (HiHealthData) list.get(0);
                    bloodMeasureResultActivity.f();
                    return;
                } else {
                    LogUtil.a("BloodMeasureResultActivity", "refreshBloodPressure no data");
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (bloodMeasureResultActivity.g == 11) {
                LogUtil.a("BloodMeasureResultActivity", "from health_model", " lastDataTime is : ", Long.valueOf(bloodMeasureResultActivity.m));
                Intent intent = new Intent(bloodMeasureResultActivity, (Class<?>) KnitBloodPressureActivity.class);
                intent.putExtra("key_bundle_health_last_data_time", bloodMeasureResultActivity.m);
                bloodMeasureResultActivity.startActivity(intent);
                bloodMeasureResultActivity.finish();
            } else {
                bloodMeasureResultActivity.finish();
            }
            LogUtil.a("BloodMeasureResultActivity", "insertResult=", Integer.valueOf(message.arg1));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_measure_result);
        this.d = this;
        if (getIntent() != null) {
            this.g = getIntent().getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
        }
        LogUtil.a("BloodMeasureResultActivity", "fromtype: ", Integer.valueOf(this.g));
        a();
        c();
    }

    private void a() {
        ((HealthSubHeader) findViewById(R.id.blood_measure_hwSubHeader)).setSubHeaderBackgroundColor(getColor(R.color._2131296971_res_0x7f0902cb));
        this.o = (HealthTextView) findViewById(R.id.hw_health_pulse_heart);
        this.b = (HealthTextView) findViewById(R.id.input_blood_pressure_date);
        this.c = (HealthTextView) findViewById(R.id.input_blood_pressure_time);
        this.e = (BloodSugarDashboardView) findViewById(R.id.blood_sugar_dashboard_view);
        HealthButton healthButton = (HealthButton) findViewById(R.id.hw_measure_result_button);
        this.n = healthButton;
        healthButton.setAlpha(R.dimen._2131362501_res_0x7f0a02c5);
        this.n.setClickable(false);
        this.n.setOnClickListener(new View.OnClickListener() { // from class: qbn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodMeasureResultActivity.this.dyN_(view);
            }
        });
        b();
        this.i = new d(this);
        e();
    }

    public /* synthetic */ void dyN_(View view) {
        this.n.setEnabled(false);
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        this.f = (HealthFlowLayout) findViewById(R.id.measure_action_flow_layout);
        BloodPressureMeasureActionManager bloodPressureMeasureActionManager = new BloodPressureMeasureActionManager(this);
        this.f10020a = bloodPressureMeasureActionManager;
        bloodPressureMeasureActionManager.e(this.f);
        this.f10020a.e(new BloodPressureMeasureActionManager.CustomActionChangeListener() { // from class: qbo
            @Override // com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager.CustomActionChangeListener
            public final void onAddAction(String str) {
                BloodMeasureResultActivity.this.a(str);
            }
        });
        this.f10020a.b(true);
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.l.add(str);
        this.j = true;
        this.h.setVisibility(0);
        BloodPressureUtil.d(this.d, this.l);
    }

    private void b() {
        View findViewById = findViewById(R.id.measure_activity_custom);
        this.h = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: qbp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodMeasureResultActivity.this.dyP_(view);
            }
        });
    }

    public /* synthetic */ void dyP_(View view) {
        if (nsn.a(1000)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HealthRecycleView healthRecycleView = new HealthRecycleView(this.d);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.d));
        final CustomActionDialogAdapter customActionDialogAdapter = new CustomActionDialogAdapter(this.d);
        customActionDialogAdapter.c(this.l);
        healthRecycleView.setAdapter(customActionDialogAdapter);
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this.d).cyp_(healthRecycleView).e(R$string.IDS_custom_delete_tag).cyo_(R$string.IDS_settings_button_ok, new DialogInterface.OnClickListener() { // from class: qbm
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BloodMeasureResultActivity.this.dyO_(customActionDialogAdapter, dialogInterface, i);
            }
        }).cyn_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: qbk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = cyn_.c();
        cyn_.b().setTextColor(this.d.getColor(R.color._2131296518_res_0x7f090106));
        cyn_.b().setText(this.d.getString(R$string.IDS_hw_show_healthdata_bloodsugar_delete));
        cyn_.d().setTextColor(this.d.getColor(R.color._2131296532_res_0x7f090114));
        c.setCanceledOnTouchOutside(false);
        List<String> list = this.l;
        if (list != null && list.size() > 5) {
            WindowManager.LayoutParams attributes = c.getWindow().getAttributes();
            attributes.height = nsn.c(this.d, 340.0f);
            c.getWindow().setAttributes(attributes);
        }
        c.show();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dyO_(CustomActionDialogAdapter customActionDialogAdapter, DialogInterface dialogInterface, int i) {
        b(customActionDialogAdapter.c());
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void c() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("BloodMeasureResultActivity", "intent is null");
            return;
        }
        long longExtra = intent.getLongExtra("dataTime", 0L);
        LogUtil.a("BloodMeasureResultActivity", "dataTime=", Long.valueOf(longExtra));
        kor.a().a(longExtra, longExtra, 1, new b(this, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.k == null) {
            LogUtil.h("BloodMeasureResultActivity", "updateView, mHiHealthData is null");
            return;
        }
        this.n.setAlpha(R.dimen._2131362648_res_0x7f0a0358);
        this.n.setEnabled(true);
        this.b.setText(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd")).format(Long.valueOf(this.k.getEndTime())));
        this.c.setText(nsj.c((Context) this, this.k.getEndTime(), 1));
        int i = this.k.getInt("BLOOD_PRESSURE_SYSTOLIC");
        int i2 = this.k.getInt("BLOOD_PRESSURE_DIASTOLIC");
        double d2 = this.k.getDouble(BleConstants.BLOODPRESSURE_SPHYGMUS);
        this.o.setText(getResources().getQuantityString(R.plurals._2130903084_res_0x7f03002c, qrp.a(d2), Integer.valueOf((int) d2)));
        c(i, i2);
        j();
    }

    private void b(List<String> list) {
        this.l.removeAll(list);
        List<qkv> b2 = BloodPressureUtil.b();
        for (String str : this.l) {
            qkv qkvVar = new qkv(str);
            qkvVar.b(true);
            for (qkv qkvVar2 : this.f10020a.c()) {
                if (qkvVar2.i() && qkvVar2.d().equals(str)) {
                    qkvVar.c(true);
                }
            }
            b2.add(qkvVar);
        }
        BloodPressureUtil.d(b2);
        this.j = !this.l.isEmpty();
        this.f10020a.c(b2);
        this.h.setVisibility(this.j ? 0 : 8);
        BloodPressureUtil.d(this.d, this.l);
    }

    private void j() {
        int i;
        List<qkv> b2 = BloodPressureUtil.b();
        HiHealthData hiHealthData = this.k;
        if (hiHealthData != null && (i = hiHealthData.getInt("beforeMeasureActivity")) > 0) {
            BloodPressureUtil.d(i, b2);
        }
        List<String> c = BloodPressureUtil.c(this.d);
        this.l = c;
        boolean z = !c.isEmpty();
        this.j = z;
        this.h.setVisibility(z ? 0 : 8);
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            qkv qkvVar = new qkv(it.next());
            qkvVar.b(true);
            b2.add(qkvVar);
        }
        BloodPressureUtil.d(b2);
        this.f10020a.c(b2);
    }

    private void c(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int[] a2 = BloodPressureUtil.a(this);
        arrayList.add(new DashboardRingView.b(0.0f, 1.0f, getColor(R.color._2131296523_res_0x7f09010b)));
        int i3 = 0;
        while (i3 < a2.length) {
            int i4 = i3 + 1;
            arrayList.add(new DashboardRingView.b(i4, i3 + 2, a2[i3]));
            i3 = i4;
        }
        this.e.setRingAreas(0.0f, arrayList.size(), arrayList);
        int c = eeu.c(i, i2);
        this.e.setStatusText(eeu.b(i, i2), eeu.d(c));
        this.e.setCurrentValue(c + 0.5f);
        this.e.setCurrentValueText(UnitUtil.e(i, 1, 0) + "/" + UnitUtil.e(i2, 1, 0));
        this.e.setCurrentValueTextUnit(getString(R$string.IDS_device_measure_pressure_value_unit));
        this.e.c();
    }

    private void d() {
        HiHealthData hiHealthData = this.k;
        if (hiHealthData == null) {
            LogUtil.h("BloodMeasureResultActivity", "save, mHiHealthData is null");
            return;
        }
        int i = hiHealthData.getInt("BLOOD_PRESSURE_SYSTOLIC");
        int i2 = this.k.getInt("BLOOD_PRESSURE_DIASTOLIC");
        double d2 = this.k.getDouble(BleConstants.BLOODPRESSURE_SPHYGMUS);
        List<String> e = BloodPressureUtil.e(this.f10020a.c());
        double[] dArr = {i, i2, d2, BloodPressureUtil.a(this.f10020a.c())};
        Object obj = this.k.get("device_uniquecode");
        String str = obj instanceof String ? (String) obj : "-1";
        this.m = this.k.getEndTime();
        qkh.c().e(this.m, dArr, e, str, new b(this, 1));
    }

    static class b implements IBaseResponseCallback {
        private final WeakReference<BloodMeasureResultActivity> b;
        private final int e;

        b(BloodMeasureResultActivity bloodMeasureResultActivity, int i) {
            this.b = new WeakReference<>(bloodMeasureResultActivity);
            this.e = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("BloodMeasureResultActivity", "ReadComplete onResponse Success");
            BloodMeasureResultActivity bloodMeasureResultActivity = this.b.get();
            if (bloodMeasureResultActivity == null) {
                return;
            }
            Message obtainMessage = bloodMeasureResultActivity.i.obtainMessage();
            obtainMessage.what = this.e;
            obtainMessage.arg1 = i;
            obtainMessage.obj = obj;
            bloodMeasureResultActivity.i.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
